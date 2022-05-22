package com.example.slast.api.model;

import com.example.slast.db.DbNamirnice;
import com.example.slast.service.models.Slastice;

import java.util.List;
import java.util.UUID;

public record ApiSlatice(

        UUID uuid,
        String naziv,
        List<DbNamirnice> namirnice
) {

    public static ApiSlatice from(Slastice slastice) {
        return new ApiSlatice(
                slastice.getUuid(),
                slastice.getNaziv(),
                slastice.getNamirnice()
        );
    }
}
