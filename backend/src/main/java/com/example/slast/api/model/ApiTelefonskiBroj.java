package com.example.slast.api.model;

import com.example.slast.service.models.TelefonskiBroj;

import java.util.UUID;

public record ApiTelefonskiBroj (

    UUID uuid,
    String pozivniBroj,
    String ostatakBroj
){
  public static ApiTelefonskiBroj from(TelefonskiBroj telefonskiBroj) {
    return new ApiTelefonskiBroj(
        telefonskiBroj.getUuid(),
        telefonskiBroj.getPozivniBroj(),
        telefonskiBroj.getOstatakBroj()
    );
  }

}
