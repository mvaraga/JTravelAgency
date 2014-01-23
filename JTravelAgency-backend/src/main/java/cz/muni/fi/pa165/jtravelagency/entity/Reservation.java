/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author jakub
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllReservations",
            query = "SELECT e FROM Reservation e"),
    @NamedQuery(name = "getReservationsByTrip",
            query = "SELECT e FROM Reservation e WHERE e.trip.id = :trip"),
  //  @NamedQuery(name = "getReservationsByExcursion",
    //        query = "SELECT DISTINCT e FROM Reservation e, Reservation_excursion re WHERE e.id=re.reservation_id AND re.excursion_id=:excursion"),
    @NamedQuery(name = "getReservationsByCustomer",
            query = "SELECT e FROM Reservation e WHERE e.customer.id = :customer"),})
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Trip trip;
    
    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable(name = "RESERVATION_EXCURSION")
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
    
        @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
