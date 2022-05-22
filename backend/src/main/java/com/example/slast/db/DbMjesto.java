package com.example.slast.db;

import com.example.slast.service.models.Mjesto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "mjesto")
@Getter
@Setter
@ToString
public class DbMjesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mjesto")
    private Integer idMjesto;

    @Column
    @NotNull
    @Type(type="uuid-char")
    private UUID uuid;

    @Column
    @NotNull
    private String nazivMjesta;

    public static DbMjesto fromCreate(Mjesto mjesto) {
        DbMjesto mjesto1 = new DbMjesto();
        mjesto1.setNazivMjesta(mjesto.getNazivMjesta());
        mjesto1.setUuid(UUID.randomUUID());
        return mjesto1;
    }
}
