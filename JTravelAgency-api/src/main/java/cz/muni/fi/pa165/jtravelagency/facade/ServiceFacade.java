/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.facade;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author jakub
 */
public interface ServiceFacade {
    
    
    /*
     * Create Methods
     */
    public void createCustomer(CustomerDTO cutomerDTO);
    
    public void createTrip(TripDTO tripDTO);
    
    public void createExcursion(ExcursionDTO excursionDTO);
    
    
    
    /*
     * Get Methods
     */
    public CustomerDTO getCustomer(Long id);
    
    public TripDTO getTrip(Long id);
    
   // public List<ExcursionDTO> getExcursionsByTrip(TripDTO trip);
    //treba pridat do servisnej
    public ExcursionDTO getExcursion(Long id);
    
    public ReservationDTO getReservation(Long id);
    
    public List<ReservationDTO> getReservationsByCustomer(CustomerDTO customerDTO);
    
    public List<ReservationDTO> getReservationByTrip(TripDTO tripDTO);
    
  

    
    /*
     * Get All Methods
     */
    public List<CustomerDTO> getAllCustomers();
    
    public List<TripDTO> getAllTrips();
    
    public List<ExcursionDTO> getAllExcursions();
    
    public List<ReservationDTO> getAllReservations();
    
    
    /*
     * Update Methods
     */
    public void updateCustomer(CustomerDTO customerDTO);
    
    public void updateTrip(TripDTO tripDTO);
    
    public void updateExcursion(ExcursionDTO excursionDTO);
    
    public void updateReservation(ReservationDTO reservationDTO);
    
    
    /*
     * Delete Methods
     */
    public void deleteCustomer(CustomerDTO customerDTO);
    
    public void deleteTrip(TripDTO tripDTO);
    
    public void deleteExcursion(ExcursionDTO excursionDTO);
    
    public void deleteReservation(ReservationDTO reservationDTO);
    
    /*
     * 
     */
    public List<TripDTO> findTripsByDateRange(DateTime from, DateTime to);
    
    /*
     * 
     */
    public List<TripDTO> findTripsByDestination(String destination);
    
    
    /*
     * 
     */
    public void addExcursionToTrip(ExcursionDTO excursionDTO, TripDTO tripDTO);
    
    
    /*
     * 
     */
    public ReservationDTO makeReservation(CustomerDTO customerDTO, TripDTO tripDTO, List<ExcursionDTO> excursionDTOs);
    
    public void addExcursionToReservation(ExcursionDTO excursionDTO, ReservationDTO reservationDTO);
    
}
