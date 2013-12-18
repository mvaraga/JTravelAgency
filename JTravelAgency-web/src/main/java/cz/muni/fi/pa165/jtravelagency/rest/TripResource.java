/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.jtravelagency.rest;

import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author majo
 */
public class TripResource {

    private Long id;
    private String dateFrom;
    private String dateTo;
    private String destination;
    private int availableTrips;
    private BigDecimal price;

    public TripResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public int getAvailableTrips() {
        return availableTrips;
    }

    public void setAvailableTrips(int availableTrips) {
        this.availableTrips = availableTrips;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TripResource{" + "id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", destination=" + destination + ", availableTrips=" + availableTrips + ", price=" + price + '}';
    }
    
    public TripDTO toDto(){
        String pattern = "dd.MM.yyyy HH:mm";
        TripDTO trip = new TripDTO();
        trip.setId(id);
        trip.setAvailableTrips(availableTrips);
        trip.setDestination(destination);
        trip.setPrice(price);
        trip.setDateFrom(DateTime.parse(dateFrom, DateTimeFormat.forPattern(pattern)));
        trip.setDateTo(DateTime.parse(dateTo, DateTimeFormat.forPattern(pattern)));        
        return trip;
    }

}
