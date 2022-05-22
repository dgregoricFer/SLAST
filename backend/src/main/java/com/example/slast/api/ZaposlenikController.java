package com.example.slast.api;

import com.example.slast.api.model.ApiNamirnice;
import com.example.slast.api.model.ApiZaposlenik;
import com.example.slast.service.ZaposlenikService;
import java.util.List;

import com.example.slast.service.models.Namirnice;
import com.example.slast.service.models.Zaposlenik;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/zaposlenici")
public class ZaposlenikController {

  @Autowired
  ZaposlenikService zaposlenikService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ApiZaposlenik> getAllZaposlenici() {

    log.info("fetching all zaposlenici begins");

    val response =  zaposlenikService.getAllZaposlenici().stream().map(ApiZaposlenik::from).toList();

    log.info("fetching zaposlenici ends {}", response);

    return response;
  }

  @PostMapping("/update")
  public ApiZaposlenik updateZaposlenik(@RequestBody ApiZaposlenik apiZaposlenik) {
    log.info("fetching all namirnice begins");

    return ApiZaposlenik.from(zaposlenikService.updateZaposlenik(Zaposlenik.to(apiZaposlenik)));
  }

  @PostMapping
  public ApiZaposlenik createZaposlenik(@RequestBody ApiZaposlenik apiZaposlenik) {
    return ApiZaposlenik.from(
            zaposlenikService.createZaposlenik(Zaposlenik.to(apiZaposlenik))
    );
  }

}
