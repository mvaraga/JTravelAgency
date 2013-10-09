/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;



/**
 *
 *  Jakub Marecek (404364)
 */


@Entity
@NamedQueries({
    @NamedQuery(name = "getAllTrips",
            query = "SELECT e FROM Trip e"),
    @NamedQuery(name = "findTripsByDateRange",
            query = "SELECT e FROM Trip e WHERE e.dateFrom >= :from AND e.dateTo <= :to"),
    @NamedQuery(name = "findTripsByDestination",
            query = "SELECT e FROM Trip e WHERE e.destination = :destination"),
    @NamedQuery(name = "findTripsByPrice",
            query = "SELECT e FROM Trip e WHERE e.price = :price")
    
})
public class Trip implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="date_to")
    private Date dateTo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="date_from")
    private Date dateFrom;
    
    private String destination;
    
    @Column(name="available_trips")
    private int availableTrips;
    
    @OneToMany(mappedBy="trip", cascade=CascadeType.ALL)
    private List<Excursion> excursions;

    private BigDecimal price;
    
    public Trip() {
    }
    
    
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
     * @return the dateTo
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    
    /**
     * 
     * @return 
     */
    public String getDestination() {
        return destination;
    }

    /**
     * 
     * @param destination 
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    /**
     * @return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the availableTrips
     */
    public int getAvailableTrips() {
        return availableTrips;
    }

    /**
     * @param availableTrips the availableTrips to set
     */
    public void setAvailableTrips(int availableTrips) {
        this.availableTrips = availableTrips;
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
    
    /**
     * 
     * @return  the price
     */
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
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
        final Trip other = (Trip) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
