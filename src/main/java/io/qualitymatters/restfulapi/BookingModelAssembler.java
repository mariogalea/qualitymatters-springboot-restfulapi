package io.qualitymatters.restfulapi;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class BookingModelAssembler implements RepresentationModelAssembler<Booking, EntityModel<Booking>> {


  /*
   * Converts the non-model object booking, into a model based entity.
   */
  @SuppressWarnings("null")
  @Override
  public EntityModel<Booking> toModel(Booking booking) {

    return EntityModel.of(booking, //
        linkTo(methodOn(BookingController.class).one(booking.getId())).withRel("selfLink"),
        linkTo(methodOn(BookingController.class).all()).withRel("bookingsLink"));
  }
}