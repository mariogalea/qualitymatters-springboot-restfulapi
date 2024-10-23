package io.qualitymatters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class BookingLoadDb {

    private static final Logger log = LoggerFactory.getLogger(BookingLoadDb.class);

    @Bean
    CommandLineRunner initDatabase(BookingJpaRepo repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Booking("Mario","Galea", 600, true, new Date(), "All Inclusive Resident")));
            log.info("Preloading " + repository.save(new Booking("Vincent","Vega", 900, true, new Date(), "Carries a Briefcase full of Gold.  Marcelo Wallace Henchman")));
        };
    }
}
