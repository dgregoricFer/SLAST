package com.example.slast.service;

import com.example.slast.db.DbNamirnice;
import com.example.slast.repository.NamirniceRepository;
import com.example.slast.service.models.Namirnice;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NamirniceService {

    @Autowired
    private NamirniceRepository namirniceRepository;

    public List<Namirnice> getAllNamirnice() {
        return namirniceRepository.findAll().stream().map(Namirnice::from).toList();
    }

    public Namirnice updateNamirnica(Namirnice namirnice) {
       namirniceRepository.save(
               findByUuid(namirnice.getUuid()).updateNamirnica(namirnice)
       );
       return namirnice;
    };

    public List<Namirnice> saveNamirnice(Namirnice namirnice) {
        namirniceRepository.save(DbNamirnice.from(namirnice));
        return getAllNamirnice();
    }

    private DbNamirnice findByUuid(UUID uuid){
        return namirniceRepository.findByUuid(uuid).get();
    }

    public void deleteNamirnica(UUID uuid) {
        val namirnica = findByUuid(uuid);
        namirniceRepository.delete(namirnica);
    }
}
