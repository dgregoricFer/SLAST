package com.example.slast.service.models;


import com.example.slast.api.model.ApiZaposlenik;
import com.example.slast.db.DbZaposlenik;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Zaposlenik {

  private UUID uuid;
  private String ime;
  private String prezime;
  private boolean isVoditelj;
  private String korisnickoIme;
  private Mjesto mjesto;
//  private TelefonskiBroj telefonskiBroj;

  public static Zaposlenik from(DbZaposlenik dbZaposlenik) {
    return new Zaposlenik(
        dbZaposlenik.getUuid(),
        dbZaposlenik.getIme(),
        dbZaposlenik.getPrezime(),
        dbZaposlenik.isVoditelj(),
        dbZaposlenik.getKorisnickoIme(),
        Mjesto.from(dbZaposlenik.getIdMjesto())
//        TelefonskiBroj.from(dbZaposlenik.getIdTelBr())
    );
  }

  public static Zaposlenik to(ApiZaposlenik apiZaposlenik) {
    return new Zaposlenik(
            apiZaposlenik.uuid(),
            apiZaposlenik.ime(),
            apiZaposlenik.prezime(),
            apiZaposlenik.isVoditelj(),
            apiZaposlenik.korisnickoIme(),
            Mjesto.to(apiZaposlenik.mjesto())
//            TelefonskiBroj.to(apiZaposlenik.telefonskiBroj())
    );
  }

}
