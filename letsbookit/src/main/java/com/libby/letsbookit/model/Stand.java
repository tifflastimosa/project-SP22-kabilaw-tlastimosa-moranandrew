package com.libby.letsbookit.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stands")
public class Stand {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  // TODO: How to implement fks?
  // TODO: Change this from Integer to Event object to establish relationship
  private Integer eventId;

  @Column(name = "table_name")
  private String tableName;
  @Column(name = "table_notes")
  private String tableNotes;
  @Column(name = "booked")
  private Boolean booked;
  @Column(name = "price")
  private Float price;

  public Stand(Integer id, Integer eventId, String tableName, String tableNotes,
      Boolean booked, Float price) {
    this.id = id;
    this.eventId = eventId;
    this.tableName = tableName;
    this.tableNotes = tableNotes;
    this.booked = booked;
    this.price = price;
  }

  public Stand() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getEventId() {
    return eventId;
  }

  public void setEventId(Integer eventId) {
    this.eventId = eventId;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableNotes() {
    return tableNotes;
  }

  public void setTableNotes(String tableNotes) {
    this.tableNotes = tableNotes;
  }

  public Boolean getBooked() {
    return booked;
  }

  public void setBooked(Boolean booked) {
    this.booked = booked;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }


}
