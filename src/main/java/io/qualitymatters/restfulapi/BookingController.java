package io.qualitymatters.restfulapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Data returned by each method is sent to the Response Body - RestController Annotation.
 *  Routes for each operation (@GetMapping, @PostMapping, @PutMapping and @DeleteMapping) are equivalent to HTTP GET, POST, PUT, and DELETE calls)
 */
@RestController
class BookingController {

  private final BookingJpaRepo repository;

  BookingController(BookingJpaRepo repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/bookings")
  List<Booking> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/bookings")
  Booking newBooking(@RequestBody Booking newBooking) {
    return repository.save(newBooking);
  }

  
  // Retrieve Single booking
  @GetMapping("/bookings/{id}")
  Booking one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new BookingNotFoundException(id));
  }


  // Replace Booking
  @PutMapping("/bookings/{id}")
  Booking replaceBooking(@RequestBody Booking newBooking, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(booking -> {
        booking.setFirstName(newBooking.getFirstName());
        booking.setLastName(newBooking.getLastName());
        return repository.save(booking);
      })
      .orElseGet(() -> {
        return repository.save(newBooking);
      });
  }

  // Delete Booking
  @DeleteMapping("/bookings/{id}")
  void deleteBooking(@PathVariable Long id) {
    repository.deleteById(id);
  }

}