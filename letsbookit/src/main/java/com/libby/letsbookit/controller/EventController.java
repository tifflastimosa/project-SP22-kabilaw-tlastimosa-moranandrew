package com.libby.letsbookit.controller;

import com.libby.letsbookit.service.EventService;
import com.libby.letsbookit.model.Event;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.Positive;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * EventController class provides CRUD requests for the client and updates the view as data changes.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/events")
public class EventController{

  // connect with the service layer
  @Autowired
  private EventService eventService;

  // POST Request
  /**
   * Create an Event for a Market (a Farmer's Market, Convention, etc.). Composition relationship
   * and thus Event cannot exist without Market. In database design foreign key, market_id,
   * cannot be null.
   *
   * An event is not created when there is no market_id, market_id is a negative number,
   * the end date is before the start date, or when the start date is before the current time.
   *
   * @param event Event date(s) of the Market.
   * @param marketId The unique id of the Market record. Used to retrieve the Market.
   * @return On successful creation, Http 201 created status, on failure, Http 400 bad request.
   */
  @PostMapping(value = "/{marketId}/add-event")
  public ResponseEntity<Event> addEvent(@RequestBody Event event,
                                        @PathVariable(value = "marketId") Integer marketId) {
    try {
      // In Postman, on successful creation will get 201 created status
      LocalDateTime currentTime = LocalDateTime.now();
      // Logic exists here to provide client feedback to client
      if (marketId > 0
          && event.getStart().isBefore(event.getEnd())
          && event.getStart().isAfter(currentTime)) {
        return new ResponseEntity<>(this.eventService.addEvent(event, marketId),
                                    HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      // otherwise
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  // GET requests

  /**
   * Gets all events from the database.
   *
   * @return Returns HTTP status, if the request is good or bad, and also returns a list of all
   * events in the database.
   */
  @GetMapping
  public ResponseEntity<List<Event>> getAllEvents() {
    List<Event> events = this.eventService.getAll();
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
    Event event = this.eventService.getById(id);
    if (event != null) {
      return new ResponseEntity<>(event, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }

  /**
   * Client request to get all events given a location.
   *
   * @param location The location of the event.
   * @return A list of all Events when given a location.
   */
  @GetMapping(value = "/find/{location}")
  public ResponseEntity<List<Event>> findEventByLocation(@PathVariable(value = "location") String location) {
    try {
      List<Event> eventsByLocation = this.eventService.getEventByLocation(location);
      // if there are no events, then return http request that there is no content
      if (eventsByLocation.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      // otherwise return list of events with the given location
      return new ResponseEntity<>(this.eventService.getEventByLocation(location), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  // PUT request

  /**
   * Client request to update an Event record from the database.
   *
   * @param id The unique id, primary key of the Event object to be udpated.
   * @param event The event object that contains new information of the Event to be updated in the
   *              database.
   * @return The Event object with the updated information.
   */
  @PutMapping(value = "/update/{id}")
  public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") @Positive Integer id,
                                             @RequestBody Event event) {
    try {
      return new ResponseEntity<>(this.eventService.updateEvent(id, event), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  // DELETE Request

  /**
   * Deletes an event from the database when given an id.
   *
   * @param id the primary key, unique id of the event.
   * @return Returns HTTP status, if the request is good or bad.
   */
  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") Integer id) {
    try {
      this.eventService.deleteEvent(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
