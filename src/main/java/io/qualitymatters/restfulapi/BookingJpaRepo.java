package io.qualitymatters.restfulapi;

import org.springframework.data.jpa.repository.JpaRepository;

/**
  This interface will allow access to JPA via CRUD (Create, Read, Update, Delete) to the H2 in-memory database.
 */
public interface BookingJpaRepo extends JpaRepository<Booking, Long> {


}