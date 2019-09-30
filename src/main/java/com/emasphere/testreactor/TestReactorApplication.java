package com.emasphere.testreactor;

import com.emasphere.testreactor.model.Movement;
import com.emasphere.testreactor.repository.MovementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.YearMonth;

@SpringBootApplication
public class TestReactorApplication {

    private static final Logger logger = LoggerFactory.getLogger(TestReactorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestReactorApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialize(MovementRepository repository) {

        return (args) -> {
            repository.deleteAll().block();

            Flux.just(
                    Tuples.of(1, 1000, YearMonth.of(2018, 1), YearMonth.of(2018, 12)),
                    Tuples.of(2, 100, YearMonth.of(2019, 1), YearMonth.of(2019, 1)),
                    Tuples.of(3, 100, YearMonth.of(2019, 2), YearMonth.of(2019, 2)),
                    Tuples.of(4, 100, YearMonth.of(2019, 3), YearMonth.of(2019, 3)),
                    Tuples.of(5, 100, YearMonth.of(2019, 4), YearMonth.of(2019, 4))
            )
                    .flatMap(t -> Flux.range(1, t.getT2()).map(i -> Movement.generateRandom(t.getT1(), t.getT3(), t.getT4(), 10)))
                    .flatMap(repository::save)
                    .subscribe(m -> logger.info("saved movement {}", m));
        };
    }
}
