package com.example.slast.db;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "narudzba")
@Getter
@Setter
@ToString
public class DbNarudzba {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idNarudzbe;

    @Column
    @NotNull
    private String adresa;

    @Column
    @NotNull
    private boolean poklonPakiranje;

    @Column
    @NotNull
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zaposlenika")
    private DbZaposlenik idZaposlenika;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mjesto")
    private DbMjesto idMjesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tel_br")
    private DbTelefonskiBroj idTelBr;

}
