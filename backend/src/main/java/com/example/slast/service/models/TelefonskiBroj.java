package com.example.slast.service.models;


import com.example.slast.api.model.ApiTelefonskiBroj;
import com.example.slast.db.DbTelefonskiBroj;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefonskiBroj {

  private UUID uuid;
  private String pozivniBroj;
  private String ostatakBroj;

  public static TelefonskiBroj from(DbTelefonskiBroj telefonskiBroj) {
    return new TelefonskiBroj(
        telefonskiBroj.getUuid(),
        telefonskiBroj.getPozivniBroj(),
        telefonskiBroj.getOstatakBroja()
    );
  }

  public static TelefonskiBroj to(ApiTelefonskiBroj apiTelefonskiBroj) {
    return new TelefonskiBroj(
            apiTelefonskiBroj.uuid(),
            apiTelefonskiBroj.pozivniBroj(),
            apiTelefonskiBroj.ostatakBroj()
    );
  }

}
