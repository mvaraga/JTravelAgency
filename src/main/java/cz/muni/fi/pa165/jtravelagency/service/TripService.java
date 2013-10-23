/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import java.util.List;
import org.joda.time.LocalDate;

/**
 *
 * @author jakub
 */
public interface TripService {

    /**
     * 
     * @param trip 
     */
    public void create(TripDTO trip);
    
    
    /**
     * 
     * @param id
     * @return 
     */
    public TripDTO get(Long id);
    
    
    /**
     * 
     * @param trip 
     */
    public void update(TripDTO trip);
    
    
    /**
     * 
     * @param trip 
     */
    public void delete(TripDTO trip);
    
    /**
     * 
     * @return 
     */
    public List<TripDTO> getAll();
    
    /**
     * 
     * @param from
     * @param to
     * @return 
     */
    public List<TripDTO> findAllByDateRange(LocalDate from, LocalDate to);
    
    /**
     * 
     * @param destination
     * @return 
     */
    public List<TripDTO> findAllByDestination(String destination);
}
