package com.example.slast.api.model;

import com.example.slast.service.models.Mjesto;

import java.util.UUID;

public record ApiMjesto(

    UUID uuid,
    String nazivMjesta
) {

  public static ApiMjesto from(Mjesto mjesto) {
    return new ApiMjesto(
        mjesto.getUuid(),
        mjesto.getNazivMjesta()
    );
  }
}
