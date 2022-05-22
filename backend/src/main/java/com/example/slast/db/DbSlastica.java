package com.example.slast.db;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "slastica")
@Getter
@Setter
@ToString
public class DbSlastica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSlastice;

    @Column
    @NotNull
    @Type(type="uuid-char")
    private UUID uuid;

    @Column
    @NotNull
    private String naziv;

    @ToString.Exclude
    @Column
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "slast_namirnice",
            joinColumns = @JoinColumn(name = "id_slastice"),
            inverseJoinColumns = @JoinColumn(name = "id_namirnice"))
    private Set<DbNamirnice> namirnice = new HashSet<>();
}
