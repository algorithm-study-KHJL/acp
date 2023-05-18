package com.khjl.acp.entity.repository;

import com.khjl.acp.entity.Usher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsherRepository extends JpaRepository<Usher, Long> {

    List<Usher> findByName(String name);

}
