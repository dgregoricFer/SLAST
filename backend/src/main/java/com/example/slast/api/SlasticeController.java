package com.example.slast.api;

import com.example.slast.api.model.ApiSlatice;
import com.example.slast.api.model.ApiUpdateSlastica;
import com.example.slast.service.SlasticeService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/slastice")
public class SlasticeController {

    @Autowired
    private SlasticeService slasticeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApiSlatice> getAllSlastice() {

        log.info("fetching all namirnice begins");

        val response =  slasticeService.getAllSlastice().stream().map(ApiSlatice::from).toList();

        log.info("fetching namirnice ends {}", response);

        return response;
    }

    @PostMapping("/{uuidNamirnice}/{uuidSlastice}")
    public void dodajUSlasticu(@PathVariable UUID uuidNamirnice, @PathVariable UUID uuidSlastice) {

        slasticeService.addUSlasticu(uuidNamirnice, uuidSlastice);
    }

    @GetMapping("/{uuid}")
    public List<ApiSlatice> nadiJednu(@PathVariable Integer uuid) {
        return slasticeService.findOneSlastica(uuid).stream().map(ApiSlatice::from).toList();
    }

    @GetMapping("/filter/{filterCriteria}")
    public List<ApiSlatice> filtriraj(@PathVariable String filterCriteria) {
        return slasticeService.filterSlastice(filterCriteria).stream().map(ApiSlatice::from).toList();
    }

    @DeleteMapping("/{uuidSlastice}")
    public void deleteSlastica(@PathVariable UUID uuidSlastice) {

        slasticeService.deleteSlastica(uuidSlastice);
    }

//    @PostMapping("removeNamirnica/{uuidNamirnice}/{uuidSlastice}")
//    public void removeNamirnicaFromSlastica(@PathVariable UUID uuidNamirnice, @PathVariable UUID uuidSlastice) {
//
//    }

    @PostMapping( "/update")
    public ApiSlatice updateSlastica(@RequestBody ApiUpdateSlastica apiUpdateSlastica) {
        return ApiSlatice.from(slasticeService.updateSlastica(apiUpdateSlastica.uuid(), apiUpdateSlastica.nazivSlastice()));
    }
}
