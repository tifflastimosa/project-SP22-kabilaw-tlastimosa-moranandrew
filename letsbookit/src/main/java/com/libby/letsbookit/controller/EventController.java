package com.libby.letsbookit.controller;

import com.libby.letsbookit.daos.userservice.IUserService;
import com.libby.letsbookit.model.Event;
import com.libby.letsbookit.model.Event.Roles;
import com.libby.letsbookit.model.Event.Event;
import com.libby.letsbookit.model.User.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * EventController class provides CRUD requests for the client and updates the view as data changes.
 */
@RestController
@RequestMapping("/events")

public class EventController{

  // connect with the service layer
  @Autowired
  private IUserService userService;

  /**
   * Allows the client to request to create an Event that will be added to the database.
   *
   * @param marketId the foreign key that represents the marketId.
   * @param name the name of the event.
   * @param start the start time of the event.
   * @param end the end time of the event.
   * @param location the location of the event.
   * @param venueLayout the layout of the venue where the event will be held.
   * @return Returns HTTP status, if the request is good or bad, and also returns the id.
   */

  @PostMapping(value = "/create")
  public ResponseEntity<Integer> createEvent(@RequestParam(value = "marketId") Integer marketId,
      @RequestParam(value = "name") String name, @RequestParam(value = "start") LocalDateTime start,
      @RequestParam(value = "end") String end, @RequestParam(value = "location") String location,
      @RequestParam(value = "venueLayout") String venueLayout) {

    try {
     // return new ResponseEntity<>(this., HttpStatus.OK);

      } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }

  // Gets event requests

  /**
   * Gets all events from the database.
   *
   * @return Returns HTTP status, if the request is good or bad, and also returns a list of all
   * events in the database.
   */
  @GetMapping(value = "/all")
  public ResponseEntity<List<Event>> getAllEvents() {
    List<Event> events = this.createEvent().getAll();
    if (!events.isEmpty()) {
      return new ResponseEntity<>(events, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  /**
   * Gets an event from the database when given an id.
   *
   * @param id the autogenerated id of an event.
   * @return Returns HTTP status, if the request is good or bad, and also returns a list of all
   * events in the database.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<Event> getEvent(@PathVariable Integer id) {
    User user = this.userService.getById(id);
    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  }




}
