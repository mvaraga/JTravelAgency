/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.dto;


import java.util.List;
//import javax.ws.rs.GET;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mvaraga
 */

//@XmlRootElement
public class CustomerDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private List<ReservationDTO> reservations;

    public CustomerDTO() {
    }
    
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getPlain() {
//        return this.toString();
//    }
//    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final CustomerDTO other = (CustomerDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", reservations=" + reservations + '}';
    }
    
}
