package com.example.slast.repository;

import com.example.slast.db.DbMjesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MjestoRepository extends JpaRepository<DbMjesto, Integer> {

}
