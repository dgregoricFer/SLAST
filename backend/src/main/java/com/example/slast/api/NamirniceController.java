package com.example.slast.api;

import com.example.slast.api.model.ApiNamirnice;
import com.example.slast.service.NamirniceService;
import java.util.List;
import java.util.UUID;

import com.example.slast.service.models.Namirnice;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/namirnice")
public class NamirniceController {

  @Autowired
  private NamirniceService namirniceService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ApiNamirnice> getAllNamirnice() {

    log.info("fetching all namirnice begins");

    val response =  namirniceService.getAllNamirnice().stream().map(ApiNamirnice::from).toList();

    log.info("fetching namirnice ends {}", response);

    return response;
  }

  @PostMapping("/update")
  public ApiNamirnice updateNamirnica(@RequestBody ApiNamirnice apiNamirnice) {
    log.info("fetching all namirnice begins");

    return ApiNamirnice.from(namirniceService.updateNamirnica(Namirnice.to(apiNamirnice)));

  }

  @PostMapping
  public List<ApiNamirnice> saveNewNamirnica(@RequestBody ApiNamirnice apiNamirnice) {

    return namirniceService.saveNamirnice(Namirnice.to(apiNamirnice)).stream().map(ApiNamirnice::from).toList();
  }

  @DeleteMapping("/{uuidNamirnice}")
  public void deleteNamirnica(@PathVariable UUID uuidNamirnice) {

    namirniceService.deleteNamirnica(uuidNamirnice);
  }


}
