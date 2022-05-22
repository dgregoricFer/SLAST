package com.example.slast.db;


import com.example.slast.service.models.Zaposlenik;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "zaposlenik")
@Getter
@Setter
@ToString
public class DbZaposlenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zaposlenika")
    private Integer idZaposlenika;

    @Column
    @NotNull
    @Type(type="uuid-char")
    private UUID uuid;

    @Column
    @NotNull
    private String ime;

    @Column
    @NotNull
    private String prezime;

    @Column
    @NotNull
    private boolean isVoditelj;

    @Column(unique = true)
    @NotNull
    private String korisnickoIme;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mjesto")
    private DbMjesto idMjesto;


//    @OneToMany(fetch = FetchType.LAZY)
//    private DbTelefonskiBroj idTelBr;

    public DbZaposlenik updateZaposlenik(Zaposlenik zaposlenik) {
        ime = zaposlenik.getIme();
        prezime = zaposlenik.getPrezime();
        korisnickoIme = zaposlenik.getKorisnickoIme();
        isVoditelj = zaposlenik.isVoditelj();
        return this;
    }

    public static DbZaposlenik create(Zaposlenik zaposlenik) {
        DbZaposlenik zaposlenik1 = new DbZaposlenik();
        zaposlenik1.setPrezime(zaposlenik.getPrezime());
        zaposlenik1.setIme(zaposlenik.getIme());
        zaposlenik1.setVoditelj(zaposlenik.isVoditelj());
        zaposlenik1.setKorisnickoIme(zaposlenik.getKorisnickoIme());
        zaposlenik1.setIdMjesto(DbMjesto.fromCreate(zaposlenik.getMjesto()));
        zaposlenik1.setUuid(UUID.randomUUID());
        return zaposlenik1;
    }



}
