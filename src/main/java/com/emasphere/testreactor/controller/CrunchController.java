package com.emasphere.testreactor.controller;

import com.emasphere.testreactor.model.CrunchMovement;
import com.emasphere.testreactor.service.CrunchService;
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
