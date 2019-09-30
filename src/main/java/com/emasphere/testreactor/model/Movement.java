package com.emasphere.testreactor.model;

import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;
import java.util.UUID;

import static java.math.RoundingMode.HALF_UP;
import static java.time.temporal.ChronoUnit.MONTHS;

@Value
@Document
public class Movement {

    private static BigDecimal MIN = new BigDecimal(10.05).setScale(2, HALF_UP);
    private static BigDecimal MAX = new BigDecimal(1000.95).setScale(2, HALF_UP);
    @Id
    private UUID id;
    private BigDecimal amount;
    @Indexed
    private MovementKey movementKey;

    public static Movement generateRandom(int injectionNumber, YearMonth start, YearMonth end, int numberOfAccounts) {
        long numberOfMonths = MONTHS.between(start, end);
        Random random = new Random();
        YearMonth displayMonth = numberOfMonths == 0 ? start : start.plus(random.nextInt((int) numberOfMonths), MONTHS);
        String account = "ACC" + random.nextInt(numberOfAccounts);
        BigDecimal amount = generateRandomBigDecimalFromRange(random, MIN, MAX);
        return new Movement(UUID.randomUUID(), amount, new MovementKey(injectionNumber, displayMonth.toString(), account));
    }

    public static BigDecimal generateRandomBigDecimalFromRange(Random random, BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(random.nextDouble()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, HALF_UP);
    }

}
