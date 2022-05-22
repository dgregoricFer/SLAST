package com.example.slast.db;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "mjera")
@Getter
@Setter
@ToString
public class DbMjera {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMjere;

    @Column
    @NotNull
    UUID uuid;

    @Column
    @NotNull
    private String naziv;

}
