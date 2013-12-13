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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public List<ExcursionDTO> getExcursions() {
        return excursions;
    }

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
    

