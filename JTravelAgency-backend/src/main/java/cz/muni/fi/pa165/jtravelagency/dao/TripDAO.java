/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.dao;

import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import java.util.List;
import org.joda.time.DateTime;

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
    public List<Trip> findTripsByDateRange(DateTime from, DateTime to);
    
    /**
     * 
     * @param destination
     * @return 
     */
    public List<Trip> findTripsByDestination(String destination);
}
