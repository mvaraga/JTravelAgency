/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import java.util.List;

/**
 *
 * @author Peter Petrinec
 */
public interface ExcrursionService {
    
    /**
     * 
     * @param excursionDTO 
     */
    void create(ExcursionDTO excursionDTO);
    
    /**
     * 
     * @param id
     * @return 
     */
    ExcursionDTO get(Long id);
    
    /**
     * 
     * @param excursionDTO 
     */
    void update(ExcursionDTO excursionDTO);
    
    /**
     * 
     * @param excursionDTO 
     */
    void delete(ExcursionDTO excursionDTO);
    
    /**
     * 
     * @return 
     */
    List<ExcursionDTO> getAll();
    
    /**
     * 
     * @param excursionDTO
     * @return 
     */
    //TripDTO getTrip(ExcursionDTO excursionDTO);
}
