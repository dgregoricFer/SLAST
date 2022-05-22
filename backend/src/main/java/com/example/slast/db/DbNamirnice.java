package com.example.slast.db;

import com.example.slast.service.models.Namirnice;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "namirnice")
@Getter
@Setter
@ToString
public class DbNamirnice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNamirnice;

    @NotNull
    @Column
    @Type(type="uuid-char")
    UUID uuid;

    @Column
    @NotNull
    private String nazivNamirnice;

    @Column
    @NotNull
    private Integer kolicina;

    @Column
    @NotNull
    private Date zadnjaPromjena;

    @ToString.Exclude
    @JsonBackReference
    @ManyToMany(mappedBy = "namirnice", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<DbSlastica> slastice = new HashSet<>();


    public static DbNamirnice from(Namirnice namirnice) {
        val dbNamirnica = new DbNamirnice();
        dbNamirnica.setUuid(UUID.randomUUID());
        dbNamirnica.setNazivNamirnice(namirnice.getNazivNamirnice());
        dbNamirnica.setKolicina(namirnice.getKolicina());
        dbNamirnica.setZadnjaPromjena(namirnice.getZadnjaPromjena());
        return dbNamirnica;
    }

    public DbNamirnice updateNamirnica(Namirnice namirnice) {
        nazivNamirnice = namirnice.getNazivNamirnice();
        kolicina = namirnice.getKolicina();
        zadnjaPromjena = namirnice.getZadnjaPromjena();
        return this;
    }

}
