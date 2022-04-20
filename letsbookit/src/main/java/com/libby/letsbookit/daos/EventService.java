//package com.libby.letsbookit.daos;
//
//import com.libby.letsbookit.model.Event;
//import com.libby.letsbookit.model.Market;
//import com.libby.letsbookit.repositories.EventRepository;
//import com.libby.letsbookit.repositories.MarketRepository;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EventService {
//
//  @Autowired
//  private EventRepository eventRepository;
//
//  @Autowired
//  private MarketRepository marketRepository;
//
//  private LocalDateTime helperDateConverter(String date) {
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    return LocalDateTime.parse(date,formatter);
//  }
//
//  /**
//   * Provides the business logic to create an event object and then add it as a record to the
//   * database.
//   *
//   * @param name The name of the event.
//   * @param start The start time of the event.
//   * @param end The end time of the event.
//   * @param location The location of the event.
//   * @param venueLayout The layout of the venue where the event will be held.
//   * @return
//   */
//  public Integer createEvent(String name, String start, String end,
//      String location, String venueLayout) {
//    // convert the time
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    LocalDateTime startTimeObject = LocalDateTime.parse(start, formatter);
//    LocalDateTime endTimeObject = LocalDateTime.parse(end, formatter);
//    System.out.println("1");
//    // creat the event object
//    Event event = new Event(name, startTimeObject, endTimeObject, location, venueLayout);
//    // get the market
//    System.out.println("2");
//    // TODO : the issue is here - why?
//    Market market = this.marketRepository.findById(event.getMarket().getId()).orElse(null);
//    System.out.println("3");
//    if (null == market) {
//      market = new Market();
//    }
//    System.out.println("4");
//    market.setName(event.getMarket().getName());
//    event.setMarket(market);
//    this.eventRepository.save(event);
//    System.out.println("4");
//    return event.getId();
//  }
//
//  public List<Event> getAll() {
//    return (List<Event>) this.eventRepository.findAll();
//  }
//
//  public Event getById(Integer id) {
//    Optional<Event> eventOptional = this.eventRepository.findById(id);
//    Event eventObject = eventOptional.get();
//    return eventObject;
//  }
//
////  /**
////   *
////   * @param id
////   * @param name
////   * @param start
////   * @param end
////   * @param location
////   * @param venueLayout
////   * @return
////   */
////  public Integer updateEvent(Integer id, String name, String start, String end, String location,
////      String venueLayout) {
////    Event event = (Event) this.getById(id);
////    event.setName(name);
////    event.setStart(this.helperDateConverter(start));
////    event.setEnd(this.helperDateConverter(end));
////    event.setLocation(location);
////    event.setVenueLayout(venueLayout);
////    this.eventRepository.save(event);
////    return event.getId();
////  }
////
////  public void deleteEvent(Integer id) {
////    this.eventRepository.deleteById(id);
////  }
//}
