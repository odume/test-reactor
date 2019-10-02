package com.emasphere.testreactormongo.controller;

import com.emasphere.testreactormongo.model.CrunchMovement;
import com.emasphere.testreactormongo.service.CrunchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CrunchController {

    private final CrunchService crunchService;

    public CrunchController(CrunchService crunchService) {
        this.crunchService = crunchService;
    }

    @GetMapping("/movement/crunch")
    public Flux<CrunchMovement> startCrunch() {
        return crunchService.crunchMovements();
    }
}
