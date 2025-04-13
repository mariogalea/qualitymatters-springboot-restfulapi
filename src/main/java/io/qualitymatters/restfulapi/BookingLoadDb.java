package io.qualitymatters.restfulapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class executes once the Bean CommandLineRunner on the app context is loaded and c 2 entities in the DB
 */
@Configuration
public class BookingLoadDb {

    private static final Logger log = LoggerFactory.getLogger(BookingLoadDb.class);

    @Bean
    CommandLineRunner initDatabase(BookingJpaRepo repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Booking("Mario", "Galea")));
            log.info("Preloading " + repository.save(new Booking("Vincent", "Vega")));
            log.info("Preloading " + repository.save(new Booking("Jackie", "Brown")));
            log.info("Preloading " + repository.save(new Booking("QUentin", "Tarantino")));
            log.info("Preloading " + repository.save(new Booking("Uma", "Thurman")));
            log.info("Preloading " + repository.save(new Booking("Marcelo", "Wallace")));
            log.info("Preloading " + repository.save(new Booking("Mia", "Wallace")));
            log.info("Preloading " + repository.save(new Booking("Leo", "Fender")));

        };
    }
}