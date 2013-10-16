/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.joda.time.LocalDate;

/**
 *
 * Jakub Marecek (404364)
 */

public interface TripDAO {
    
    /**
     * 
     * @param trip 
     */
    public void createTrip(Trip trip);
    
    
    /**
     * 
     * @param id
     * @return 
     */
    public Trip getTrip(Long id);
    
    
    /**
     * 
     * @param trip 
     */
    public void updateTrip(Trip trip);
    
    
    /**
     * 
     * @param trip 
     */
    public void deleteTrip(Trip trip);
    
    /**
     * 
     * @return 
     */
    public List<Trip> getAllTrips();
    
    /**
     * 
     * @param from
     * @param to
     * @return 
     */
    public List<Trip> findTripsByDateRange(LocalDate from, LocalDate to);
    
    /**
     * 
     * @param destination
     * @return 
     */
    public List<Trip> findTripsByDestination(String destination);
}
