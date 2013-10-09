/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author jakub
 */
@Entity
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
   

    
    @ManyToOne 
    private Customer customer;
    
    @ManyToOne
    private Trip trip;
    
    @OneToMany
    private List<Excursion> excursions;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the trip
     */
    public Trip getTrip() {
        return trip;
    }

    /**
     * @param trip the trip to set
     */
    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    /**
     * @return the excursions
     */
    public List<Excursion> getExcursions() {
        return excursions;
    }

    /**
     * @param excursions the excursions to set
     */
    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }
    
    
}
