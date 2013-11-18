/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author jakub
 */
public class ReservationDTO {
    
    private Long id;
    

    private CustomerDTO customer;
    
 
    private TripDTO trip;
    
 
    private List<ExcursionDTO> excursions;

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
    public CustomerDTO getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    /**
     * @return the trip
     */
    public TripDTO getTrip() {
        return trip;
    }

    /**
     * @param trip the trip to set
     */
    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    /**
     * @return the excursions
     */
    public List<ExcursionDTO> getExcursions() {
        return excursions;
    }

    /**
     * @param excursions the excursions to set
     */
    public void setExcursions(List<ExcursionDTO> excursions) {
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
        final ReservationDTO other = (ReservationDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" + "id=" + id + ", customer=" + customer + ", trip=" + trip + '}';
    }


}
    

