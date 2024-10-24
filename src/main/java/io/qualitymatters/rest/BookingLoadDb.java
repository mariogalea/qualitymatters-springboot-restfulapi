package io.qualitymatters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * This class executes once the Bean CommandLineRunner once the app context is loaded. and created 2 entities in the DB
 */
@Configuration
public class BookingLoadDb {

    private static final Logger log = LoggerFactory.getLogger(BookingLoadDb.class);

    @Bean
    CommandLineRunner initDatabase(BookingJpaRepo repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Booking("Mario", "Galea", 600, true, new Date(), "All Inclusive Resident")));
            log.info("Preloading " + repository.save(new Booking("Vincent", "Vega", 900, true, new Date(), "Marcellus Wallace Henchman")));
        };
    }
}