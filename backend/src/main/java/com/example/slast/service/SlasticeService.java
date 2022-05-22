package com.example.slast.service;

import com.example.slast.api.model.ApiSlatice;
import com.example.slast.repository.NamirniceRepository;
import com.example.slast.repository.SlasticeRepository;
import com.example.slast.service.models.Slastice;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlasticeService {

    @Autowired
    private SlasticeRepository slasticeRepository;

    @Autowired
    private NamirniceRepository namirniceRepository;

    public List<Slastice> getAllSlastice () {
        return slasticeRepository.findAll().stream().map(Slastice::from).toList();
    }

    public List<Slastice> filterSlastice(String filterCriteria) {
        val slastice = slasticeRepository.findAll();
        val slast = slastice.stream().filter(e -> e.getNaziv().toLowerCase().contains(filterCriteria))
                .map(Slastice::from).toList();

        return slast;
    }

    public List<Slastice> findOneSlastica(Integer uuid) {
        return slasticeRepository.findAllByIdSlastice(uuid).stream().map(Slastice::from).toList();
    }

    public void addUSlasticu(UUID uuidNamirnice, UUID uuidSlastice) {
        val namirnica = namirniceRepository.findByUuid(uuidNamirnice).get();
        val slastica = slasticeRepository.findByUuid(uuidSlastice).get();
        val slasticaNamirnice = slastica.getNamirnice();
        slasticaNamirnice.add(namirnica);
        slastica.setNamirnice(slasticaNamirnice);
        namirnica.getSlastice().add(slastica);
        slasticeRepository.save(slastica);

    }

    public void deleteSlastica(UUID uuidSlastice) {
        val slastica = slasticeRepository.findByUuid(uuidSlastice).get();
        slasticeRepository.delete(slastica);
    }

    public Slastice updateSlastica(UUID uuidSlastice, String naziv) {
        val slastica = slasticeRepository.findByUuid(uuidSlastice).get();
        slastica.setNaziv(naziv);
        return Slastice.from(slasticeRepository.save(slastica));
    }
}
