package com.example.slast;

import com.example.slast.db.*;
import com.example.slast.repository.NamirniceRepository;
import com.example.slast.repository.SlasticeRepository;
import com.example.slast.repository.ZaposlenikRepository;
import com.example.slast.service.models.Namirnice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SlastApplication implements CommandLineRunner {

    @Autowired
    private SlasticeRepository slasticeRepository;

    @Autowired
    private NamirniceRepository namirniceRepository;

    @Autowired
    private ZaposlenikRepository zaposlenikRepository;
    public static void main(String[] args) {
        SpringApplication.run(SlastApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        DbSlastica slastica = new DbSlastica();
        slastica.setUuid(UUID.randomUUID());
        slastica.setNaziv("Muffin");



        DbNamirnice namirnice1 = new DbNamirnice();
        namirnice1.setUuid(UUID.randomUUID());
        namirnice1.setNazivNamirnice("cokolada");
        namirnice1.setKolicina(15);
        namirnice1.setZadnjaPromjena(new Date(System.currentTimeMillis()));

        DbNamirnice namirnice2 = new DbNamirnice();
        namirnice2.setUuid(UUID.randomUUID());
        namirnice2.setNazivNamirnice("biskvit");
        namirnice2.setKolicina(25);
        namirnice2.setZadnjaPromjena(new Date(System.currentTimeMillis()));

        slastica.getNamirnice().add(namirnice1);
        slastica.getNamirnice().add(namirnice2);

        namirnice2.getSlastice().add(slastica);
        namirnice1.getSlastice().add(slastica);

        DbSlastica slastica2 = new DbSlastica();
        slastica2.setUuid(UUID.randomUUID());
        slastica2.setNaziv("Ledeni vjetar");

        DbNamirnice namirnice3 = new DbNamirnice();
        namirnice3.setUuid(UUID.randomUUID());
        namirnice3.setNazivNamirnice("jagode");
        namirnice3.setKolicina(20);
        namirnice3.setZadnjaPromjena(new Date(System.currentTimeMillis()));

        slastica2.getNamirnice().add(namirnice1);
        slastica2.getNamirnice().add(namirnice2);
        slastica2.getNamirnice().add(namirnice3);

        namirnice2.getSlastice().add(slastica2);
        namirnice1.getSlastice().add(slastica2);
        namirnice3.getSlastice().add(slastica2);

        List<DbNamirnice> namirniceList = new ArrayList<>();
        namirniceList.add(namirnice1);
        namirniceList.add(namirnice2);
        namirniceList.add(namirnice3);

        List<DbSlastica> slasticaList = new ArrayList<>();
        slasticaList.add(slastica);
        slasticaList.add(slastica2);

        DbMjesto mjestoVoditelj = new DbMjesto();
        mjestoVoditelj.setUuid(UUID.randomUUID());
        mjestoVoditelj.setNazivMjesta("zagreb");

        DbTelefonskiBroj telefonskiBrojVoditelj = new DbTelefonskiBroj();
        telefonskiBrojVoditelj.setUuid(UUID.randomUUID());
        telefonskiBrojVoditelj.setPozivniBroj("385");
        telefonskiBrojVoditelj.setOstatakBroja("915784352");


        DbZaposlenik voditelj = new DbZaposlenik();
        voditelj.setVoditelj(true);
        voditelj.setUuid(UUID.randomUUID());
        voditelj.setIme("Cmeeter");
        voditelj.setKorisnickoIme("cmee");
        voditelj.setPrezime("Cmeegljevic");
        voditelj.setIdMjesto(mjestoVoditelj);
//        voditelj.setIdTelBr(telefonskiBrojVoditelj);


        slasticeRepository.saveAllAndFlush(slasticaList);
        zaposlenikRepository.saveAndFlush(voditelj);
        zaposlenikRepository.saveAndFlush(voditelj);

    }
}
