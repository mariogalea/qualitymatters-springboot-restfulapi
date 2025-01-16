package io.qualitymatters.restfulapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Data returned by each method is sent to the Response Body - RestController Annotation.
 *  Routes for each operation (@GetMapping, @PostMapping, @PutMapping and @DeleteMapping) are equivalent to HTTP GET, POST, PUT, and DELETE calls)
 */
@RestController
public class BookingController {

    private final BookingJpaRepo repository;

    //Dependency Inject BookingJpaRepo Class
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
    Booking newEmployee(@RequestBody Booking newBooking) {
        return repository.save(newBooking);
    }

    // Single item
    @GetMapping("/bookings/{id}")
    Booking one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Booking replaceEmployee(@RequestBody Booking newBooking, @PathVariable Long id) {

        return repository.findById(id)
                .map(booking -> {
                    booking.setFirstName(newBooking.getFirstName());
                    booking.setLastName(newBooking.getLastName());
                    booking.setTotalPrice(newBooking.getTotalPrice());
                    booking.setIsDepositPaid(newBooking.getIsDepositPaid());
                    booking.setCheckIn(newBooking.getCheckIn());
                    booking.setAdditionalNeeds(newBooking.getAdditionalNeeds());
                    return repository.save(booking);
                })
                .orElseGet(() -> {
                    return repository.save(newBooking);
                });
    }

    @DeleteMapping("/booking/{id}")
    void deleteBooking(@PathVariable Long id) {
        repository.deleteById(id);
    }

}