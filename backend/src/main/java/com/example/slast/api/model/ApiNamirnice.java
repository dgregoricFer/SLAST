package com.example.slast.api.model;

import com.example.slast.service.models.Namirnice;
import java.util.Date;
import java.util.UUID;

public record ApiNamirnice (

    UUID uuid,
    String nazivNamirnice,
    Integer kolicina,
    Date zadnjaPromjena
) {

  public static ApiNamirnice from(Namirnice namirnice) {
    return new ApiNamirnice(
        namirnice.getUuid(),
        namirnice.getNazivNamirnice(),
        namirnice.getKolicina(),
        namirnice.getZadnjaPromjena()
    );
  }

}
