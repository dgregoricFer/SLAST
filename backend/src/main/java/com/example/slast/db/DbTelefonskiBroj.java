package com.example.slast.db;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "telefonski_broj")
@Getter
@Setter
@ToString
public class DbTelefonskiBroj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tel_br")
    private Integer idTelBr;

    @Column
    @NotNull
    @Type(type="uuid-char")
    private UUID uuid;

    @Column
    @NotNull
    private String pozivniBroj;

    @Column
    @NotNull
    private String ostatakBroja;
}
