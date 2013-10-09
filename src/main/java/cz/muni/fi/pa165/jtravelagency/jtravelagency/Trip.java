/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jakub
 */
public class Trip {
    
    private Long id;
    
    private Date dateTo;
    
    private Date dateFrom;
    
    private int availableTrips;
    
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
    
    
}
