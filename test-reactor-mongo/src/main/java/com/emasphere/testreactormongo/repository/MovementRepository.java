package com.emasphere.testreactormongo.repository;

import com.emasphere.testreactormongo.model.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface MovementRepository extends ReactiveMongoRepository<Movement, UUID> {


}
