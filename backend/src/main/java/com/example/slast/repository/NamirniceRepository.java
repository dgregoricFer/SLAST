package com.example.slast.repository;

import com.example.slast.db.DbNamirnice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NamirniceRepository extends JpaRepository<DbNamirnice, Integer> {

    Optional<DbNamirnice> findByUuid(UUID uuid);
}
