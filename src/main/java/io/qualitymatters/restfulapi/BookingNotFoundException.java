package io.qualitymatters.restfulapi;

public class BookingNotFoundException extends RuntimeException {

    BookingNotFoundException(Long id) {
      super("Could not find Booking " + id);
    }
  }