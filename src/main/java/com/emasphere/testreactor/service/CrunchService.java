package com.emasphere.testreactor.service;

import com.emasphere.testreactor.model.CrunchMovement;
import com.emasphere.testreactor.model.Movement;
import com.emasphere.testreactor.repository.MovementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Service
public class CrunchService {

    private static final Logger logger = LoggerFactory.getLogger(CrunchService.class);
    private final MovementRepository repository;

    public CrunchService(MovementRepository repository) {
        this.repository = repository;
    }

    public Flux<CrunchMovement> crunchMovements() {
        return repository.findAll(Sort.by("movementKey"))
                .log()
                .groupBy(Movement::getMovementKey)
                .doOnNext(group -> logger.info("Processing group with key {}", group.key()))
                .flatMap(group -> group.reduce(
                        new CrunchMovement(group.key()),
                        (crunch, movement) -> {
                            logger.info("Crunching movement {} into {}", movement, crunch);
                            BigDecimal newAmount = crunch.getAmount().add(movement.getAmount());
                            return crunch.setAmount(newAmount);
                        }
                        ),
                        256
                );
    }
}
