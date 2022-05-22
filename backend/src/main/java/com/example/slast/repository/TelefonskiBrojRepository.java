package com.example.slast.repository;

import com.example.slast.db.DbTelefonskiBroj;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TelefonskiBrojRepository extends JpaRepository<DbTelefonskiBroj, Integer> {

}
