package com.emasphere.testreactor.repository;

import com.emasphere.testreactor.model.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface MovementRepository extends ReactiveMongoRepository<Movement, UUID> {


}
