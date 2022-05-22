package com.example.slast.api.model;

import com.example.slast.service.models.Zaposlenik;

import java.util.UUID;

public record ApiZaposlenik (

    UUID uuid,
    String ime,
    String prezime,
    boolean isVoditelj,
    String korisnickoIme,
    ApiMjesto mjesto
//    ApiTelefonskiBroj telefonskiBroj

){

  public static ApiZaposlenik from(Zaposlenik zaposlenik) {
    return new ApiZaposlenik(
        zaposlenik.getUuid(),
        zaposlenik.getIme(),
        zaposlenik.getPrezime(),
        zaposlenik.isVoditelj(),
        zaposlenik.getKorisnickoIme(),
        ApiMjesto.from(zaposlenik.getMjesto())
//        ApiTelefonskiBroj.from(zaposlenik.getTelefonskiBroj())
    );
  }
}
