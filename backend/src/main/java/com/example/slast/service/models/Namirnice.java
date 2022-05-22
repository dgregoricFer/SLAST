package com.example.slast.service.models;

import com.example.slast.api.model.ApiNamirnice;
import com.example.slast.db.DbNamirnice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Namirnice {

    private UUID uuid;
    private String nazivNamirnice;
    private Integer kolicina;
    private Date zadnjaPromjena;

    public static Namirnice from(DbNamirnice dbNamirnice) {
        return new Namirnice(
                dbNamirnice.getUuid(),
                dbNamirnice.getNazivNamirnice(),
                dbNamirnice.getKolicina(),
                dbNamirnice.getZadnjaPromjena()
        );
    }

    public static Namirnice to(ApiNamirnice apiNamirnice) {
        return new Namirnice(
                apiNamirnice.uuid(),
                apiNamirnice.nazivNamirnice(),
                apiNamirnice.kolicina(),
                apiNamirnice.zadnjaPromjena()
        );
    }
}
