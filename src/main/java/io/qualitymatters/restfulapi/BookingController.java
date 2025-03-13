package io.qualitymatters.restfulapi;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Data returned by each method is sent to the Response Body - RestController Annotation.
 *  Routes for each operation (@GetMapping, @PostMapping, @PutMapping and @DeleteMapping) are equivalent to HTTP GET, POST, PUT, and DELETE calls)
 */
@RestController
class BookingController {

  private final BookingJpaRepo repository;
  private final BookingModelAssembler assembler;

  BookingController(BookingJpaRepo repository, BookingModelAssembler assembler) {

    this.repository = repository;
    this.assembler = assembler;
  }


  /* 
   * Get all Bookings - HTTP GET Method
   *
   * CollectionModel<> is another Spring HATEOAS container. It encapsulates collections of resources 
   * instead of a single resource entity, such as EntityModel<>.
   * CollectionModel<>, too, lets you include links.
   * Do not let that first statement slip by. What does "encapsulating collections" mean? Collections of employees?
   * Not quite.
   * It should encapsulate collections of bookings resources.
  */
  @GetMapping("/bookings")
  CollectionModel<EntityModel<Booking>> all() {

  List<EntityModel<Booking>> bookings = repository.findAll().stream()
      .map(assembler::toModel) //
      .collect(Collectors.toList());

      return CollectionModel.of(bookings, linkTo(methodOn(BookingController.class).all()).withSelfRel());
  }

      
  /*
   * Upate Booking By ID - HTTP POST Method 
   */
  @PostMapping("/bookings")
  Booking newBooking(@RequestBody Booking newBooking) {
    return repository.save(newBooking);
  }

  /*
   * Get Mapping By ID - HTTP GET Method
   *
   * The return type - EntityModel<T> is a container (of type Booking) from Spring HATEOAS that includes both data and a collection of links. 
   * linkTo(methodOn(Booking Controller.class).one(id)).withSelfRel() asks that Spring 
   * HATEOAS to build a link (WebMVCLinkBuilder)to the one method of BookingController and flag it as a self link.
   * linkTo(methodOn(BookingController.class).all()).withRel("bookings") asks Spring 
   * HATEOAS to build a link to the aggregate root, all(), and call it "bookings".

   */
  @GetMapping("/bookings/{id}")
  EntityModel<Booking> one(@PathVariable Long id) {
    
    Booking booking = repository.findById(id)
    .orElseThrow(() -> new BookingNotFoundException(id));

    return assembler.toModel(booking);


    /*
      return EntityModel.of(booking,
        linkTo(methodOn(BookingController.class).one(id)).withSelfRel(),
        linkTo(methodOn(BookingController.class).all()).withRel("bookings"));
    */
  }


  /* 
   * Update Booking - HTTP PUT Method
   */ 
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


  /*
   * Delete Booking - HTTP DEL Method
   */
  @DeleteMapping("/bookings/{id}")
  void deleteBooking(@PathVariable Long id) {
    repository.deleteById(id);
  }

}