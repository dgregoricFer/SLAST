package com.example.slast.service;

import com.example.slast.db.DbZaposlenik;
import com.example.slast.repository.ZaposlenikRepository;
import com.example.slast.service.models.Zaposlenik;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZaposlenikService {

  @Autowired
  private ZaposlenikRepository zaposlenikRepository;

  public List<Zaposlenik> getAllZaposlenici () {
    return zaposlenikRepository.findAll().stream().map(Zaposlenik::from).toList();
  }

  public Zaposlenik updateZaposlenik(Zaposlenik zaposlenik) {
    return Zaposlenik.from(zaposlenikRepository.save(
      zaposlenikRepository.findByUuid(zaposlenik.getUuid()).get().updateZaposlenik(zaposlenik)
    ));
  }

  public Zaposlenik createZaposlenik(Zaposlenik zaposlenik) {
    return Zaposlenik.from(
            zaposlenikRepository.save(DbZaposlenik.create(zaposlenik))
    );
  }

}
