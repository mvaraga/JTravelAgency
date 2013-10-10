/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.List;

/**
 *
 * @author Peter Petrinec
 */
public interface ExcursionDAO {
    /*
     * Creates new excursion
     */
    void createExcurtion(Excursion excursion);
    
    /*
     * Gets excursion specified by its id
     * 
     * @param id Id of the excursion
     */
    Excursion getExcursion(Long id);
    
    /*
     * Updates excursion
     * 
     * @param excursion Excursion to 
     */
    void updateExcursion(Excursion excursion);
    
    /*
     * Deletes excursion from database.
     * 
     * @param excursion Excursion to delete
     */
    void deleteExcursion(Excursion excursion);
    
    /*
     * Gets all excursions from database.
     */
    List<Excursion> getAllExcursions();
    
    /*
     * Gets trip associated with the excursion.
     * 
     * @param excursion 
     */
    Trip getTrip(Excursion excursion);
}
