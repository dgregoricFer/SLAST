package com.example.slast.service.models;

import com.example.slast.db.DbNamirnice;
import com.example.slast.db.DbSlastica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Slastice {

    private UUID uuid;
    private String naziv;
    private List<DbNamirnice> namirnice;

    public static Slastice from(DbSlastica dbSlastica) {
        return new Slastice(
                dbSlastica.getUuid(),
                dbSlastica.getNaziv(),
                dbSlastica.getNamirnice().stream().toList()
        );
    }
}
