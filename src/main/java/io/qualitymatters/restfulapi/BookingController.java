package io.qualitymatters.restfulapi;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Data returned by each method is sent to the Response Body - RestController Annotation.
 *  Routes for each operation (@GetMapping, @PostMapping, @PutMapping and @DeleteMapping) are equivalent to HTTP GET, POST, PUT, and DELETE calls)
 */
@RestController
@Tag(name = "Booking Management", description = "RestfulAPIs for managing bookings")
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
   * Do not let that first statement slip by. What does "encapsulating collections" mean? Collections of bookings?
   * Not quite.
   * It should encapsulate collections of bookings resources.
  */
  @Operation(summary = "Get all Bookings", description = "Retrieve a collection of bookings in the system")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully",
                  content = @Content(schema = @Schema(implementation = Booking.class)))
  })
  @GetMapping("/bookings")
  CollectionModel<EntityModel<Booking>> all() {

  List<EntityModel<Booking>> bookings = repository.findAll().stream()
      .map(assembler::toModel) //
      .collect(Collectors.toList());

      return CollectionModel.of(bookings, linkTo(methodOn(BookingController.class).all()).withRel("bookingsLink"));
  }
  
  /*
   * Upate Booking By ID - HTTP POST Method 
   */
  @Operation(summary = "Add a new booking", description = "Add a new booking to the system")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Booking created successfully",
                  content = @Content(schema = @Schema(implementation = Booking.class))),
          @ApiResponse(responseCode = "400", description = "Invalid request data",
                  content = @Content(schema = @Schema()))
  })
  @PostMapping("/bookings/new")
  Booking newBooking(@RequestBody Booking newBooking) {
    return repository.save(newBooking);
  }

  /*
   * Get Booking By ID - HTTP GET Method
   *
   * The return type - EntityModel<T> is a container (of type Booking) from Spring HATEOAS that includes both data and a collection of links. 
   * linkTo(methodOn(Booking Controller.class).one(id)).withSelfRel() asks that Spring 
   * HATEOAS to build a link (WebMVCLinkBuilder)to the one method of BookingController and flag it as a self link.
   * linkTo(methodOn(BookingController.class).all()).withRel("bookings") asks Spring 
   * HATEOAS to build a link to the aggregate root, all(), and call it "bookings".
   */
  @Operation(summary = "Get booking by ID", description = "Retrieve booking details using the ID")
  @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema()))
    })
  @GetMapping("/bookings/{id}")
  EntityModel<Booking> one(@PathVariable Long id) {
    
    Booking booking = repository.findById(id)
    .orElseThrow(() -> new BookingNotFoundException(id));

    return assembler.toModel(booking);

  }

  /* 
   * Update Booking - HTTP PUT Method
   */ 
  @Operation(summary = "Update a booking", description = "Update an existing booking's details")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Booking updated successfully",
                  content = @Content(schema = @Schema(implementation = Booking.class))),
          @ApiResponse(responseCode = "404", description = "Booking not found",
                  content = @Content(schema = @Schema()))
  })
  @PutMapping("/bookings/update/{id}")
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
  @Operation(summary = "Delete a booking", description = "Delete a booking from the system using the ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found",
                    content = @Content(schema = @Schema()))
    })
  @DeleteMapping("/bookings/delete/{id}")
  void deleteBooking(@PathVariable Long id) {
    repository.deleteById(id);
  }

}