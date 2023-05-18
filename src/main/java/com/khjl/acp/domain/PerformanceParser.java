package com.khjl.acp.domain;

import com.khjl.acp.entity.Performance;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO 클래스 모듈화
public class PerformanceParser {

    private Document document;
    private int year;
    private int month;

    // 생성 시 정적 팩토리 메서드 사용
    private PerformanceParser() {
    }

    public static PerformanceParser fromUrl(String url, int year, int month) {
        PerformanceParser parser = new PerformanceParser();

        try {
            setSSL();
            parser.document = Jsoup.connect(url).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        parser.year = year;
        parser.month = month;

        return parser;
    }

    // https ssl 인증 우회 로직
    // TODO 인증 로직 검증
    private static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {

                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public List<Performance> getPerformances() {
        Element table = document.getElementsByTag("table").first();
        Element tableBody = table.getElementsByTag("tbody").first();

        List<String> rawPerformances = tableBody.getElementsByTag("td").stream()
                .flatMap(td -> {
                    Element em = td.getElementsByTag("em").first();
                    String day;
                    if (em != null) {
                        day = em.text().trim();
                    } else {
                        day = "";
                    }

                    return td.getElementsByTag("a").stream()
                            .filter(Objects::nonNull)
                            .map(a -> String.format("%2s", day) + a.attr("onmouseover").trim());
                })
                .collect(Collectors.toList());

        List<Performance> performances = new ArrayList<>();

        for (String rawPerformance : rawPerformances) {
            // day 분리
            int day = Integer.parseInt(rawPerformance.substring(0, 2).trim());

            // ' split
            String[] parameters = rawPerformance.substring(21).split("', '");
            String name = parameters[0];
            String type = parameters[1];
            String hall = parameters[2];
            String rating = parameters[5];

            // datetime 생성
            // TODO 한 공연 당 여러 개 시간 존재하는 경우 해결법
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, 0, 0);

            performances.add(
                    Performance.builder()
                            .dateTime(dateTime)
                            .name(name)
                            .type(type)
                            .hall(hall)
                            .rating(rating)
                            .build()
            );

        }

        return performances;
    }

}
