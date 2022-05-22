package com.example.slast.db;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "zahtjev")
@Getter
@Setter
@ToString
public class DbZahtjev {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idZahtjeva;

    @Column
    @NotNull
    private Date trenutakStvaranje;

    @Column
    @NotNull
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zaposlenika")
    private DbZaposlenik autorNarudzbe;

    @Column
    @NotNull
    private String opisZahtjeva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_namirnice")
    private DbNamirnice namirnice;

}
