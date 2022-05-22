package com.example.slast.repository;

import com.example.slast.db.DbNarudzba;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NarudzbaRepository extends JpaRepository<DbNarudzba, Integer> {
}
