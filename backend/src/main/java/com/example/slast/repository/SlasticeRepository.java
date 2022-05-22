package com.example.slast.repository;

import com.example.slast.db.DbSlastica;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SlasticeRepository extends JpaRepository<DbSlastica, Integer> {

    @EntityGraph(attributePaths = {"namirnice", "namirnice.nazivNamirnice", "namirnice.kolicina", "namirnice.zadnjaPromjena"})
    List<DbSlastica> findAll();

    @EntityGraph(attributePaths = {"namirnice","namirnice.uuid", "namirnice.nazivNamirnice", "namirnice.kolicina", "namirnice.zadnjaPromjena"})
    Optional<DbSlastica> findByUuid(UUID uuid);
    @Query(value = "SELECT * FROM slastica t1 INNER JOIN slast_namirnice t2 ON t1.id = t2.id_namirnice WHERE t2.id_slastice = ?1 ")
    List<DbSlastica> findAllByIdSlastice (Integer slasticaId);


}
