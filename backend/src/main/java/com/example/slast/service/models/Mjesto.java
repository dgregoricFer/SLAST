package com.example.slast.service.models;


import com.example.slast.api.model.ApiMjesto;
import com.example.slast.db.DbMjesto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mjesto {

  private UUID uuid;
  private String nazivMjesta;

  public static Mjesto from(DbMjesto dbMjesto) {
    return new Mjesto(
        dbMjesto.getUuid(),
        dbMjesto.getNazivMjesta()
    );
  }

  public static Mjesto to(ApiMjesto apiMjesto) {
    return new Mjesto(
            apiMjesto.uuid(),
            apiMjesto.nazivMjesta()
    );
  }

}
