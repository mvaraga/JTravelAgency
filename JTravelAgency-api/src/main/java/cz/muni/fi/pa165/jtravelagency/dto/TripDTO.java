/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.dto;

import java.math.BigDecimal;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.DateTime;

/**
 *
 * @author jakub
 */
@XmlRootElement
public class TripDTO {

    
    private Long id;
    
    private DateTime dateFrom;
    
    private DateTime dateTo;
       
    private String destination;
    
    private int availableTrips;
    
   // private List<ExcursionDTO> excursionsDTO;

    private BigDecimal price;
    
    
    public TripDTO() {
    }
    
        @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlain() {
        return this.toString();
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
    public DateTime getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(DateTime dateTo) {
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
    public DateTime getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(DateTime dateFrom) {
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
    public List<ExcursionDTO> getExcursions() {
        return null;//excursionsDTO;
    }

    /**
     * @param excursions the excursions to set
     */
    public void setExcursions(List<ExcursionDTO> excursionsDTO) {
        //this.excursionsDTO = excursionsDTO;
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
        final TripDTO other = (TripDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TripDTO{" + "id=" + id + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", destination=" + destination + ", availableTrips=" + availableTrips + ", price=" + price + '}';
    }

}
