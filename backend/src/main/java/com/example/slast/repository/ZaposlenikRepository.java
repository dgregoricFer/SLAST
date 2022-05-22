package com.example.slast.repository;

import com.example.slast.db.DbZaposlenik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ZaposlenikRepository extends JpaRepository<DbZaposlenik, Integer> {

    Optional<DbZaposlenik> findByUuid(UUID uuid);
}
