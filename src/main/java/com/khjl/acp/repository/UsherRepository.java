package com.khjl.acp.repository;

import com.khjl.acp.domain.Usher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsherRepository extends JpaRepository<Usher, Long> {
    
}
