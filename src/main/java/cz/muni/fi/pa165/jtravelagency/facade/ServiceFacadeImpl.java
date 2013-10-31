/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.facade;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.service.CustomerService;
import cz.muni.fi.pa165.jtravelagency.service.ExcursionService;
import cz.muni.fi.pa165.jtravelagency.service.ReservationService;
import cz.muni.fi.pa165.jtravelagency.service.TripService;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author
 */

@Transactional
public class ServiceFacadeImpl implements ServiceFacade {
    
    @Autowired
    private ExcursionService excursionService;
    
         @Autowired
     private ReservationService reservationService;
     @Autowired 
     private TripService tripService;
     @Autowired
     private CustomerService customerService;

    public void setExcursionService(ExcursionService excursionService) {
        this.excursionService = excursionService;
    }

    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void setTripService(TripService tripService) {
        this.tripService = tripService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

     
    

    public void createCustomer(CustomerDTO customerDTO) {
        if((customerDTO==null)||(customerDTO.getId()!=null)) {throw new IllegalArgumentException();}
        customerService.create(customerDTO);
    }

    public void createTrip(TripDTO tripDTO) {
        
    }

    public void createExcursion(ExcursionDTO excursionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CustomerDTO getCustomer(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TripDTO getTrip(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TripDTO getExcursionsByTrip(TripDTO trip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ExcursionDTO getExcursion(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ReservationDTO getReservation(Long id) {
        return null;
    }

    public ReservationDTO getReservationByCustomer(CustomerDTO customerDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ReservationDTO getReservationByTrip(TripDTO tripDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ReservationDTO getReservationByExcursions(List<ExcursionDTO> excursionDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CustomerDTO> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TripDTO> getAllTrips() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ExcursionDTO> getAllExcursions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ReservationDTO> getAllReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateCustomer(CustomerDTO customerDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateTrip(TripDTO tripDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateExcursion(ExcursionDTO excursionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateReservation(ReservationDTO reservationDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteCustomer(CustomerDTO customerDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteTrip(TripDTO tripDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteExcursion(ExcursionDTO excursionDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteReservation(ReservationDTO reservationDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDeletedStatusToCustomer(CustomerDTO customerDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TripDTO> findTripsByDateRange(LocalDate from, LocalDate to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TripDTO> findTripsByDestination(String destination) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addExcursionToTrip(ExcursionDTO excursionDTO, TripDTO tripDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ReservationDTO makeReservation(CustomerDTO customerDTO, TripDTO tripDTO, List<ExcursionDTO> excursionDTOs) {
        
        if ((customerDTO==null) || (customerDTO.getId()==null)) {throw new IllegalArgumentException();}
         if ((tripDTO==null) || (tripDTO.getId()==null)) {throw new IllegalArgumentException();}
          if (excursionDTOs==null) {throw new IllegalArgumentException();}
          
          for(int i=0;i<excursionDTOs.size();i++) {
             if (excursionDTOs.get(i).getId()==null) {throw new IllegalArgumentException();}
          }
          
        
        ReservationDTO reservationDTO=new ReservationDTO();
        reservationDTO.setCustomer(customerDTO);
        reservationDTO.setTrip(tripDTO);
        reservationDTO.setExcursions(excursionDTOs);
        
        reservationService.create(reservationDTO);
        
        return reservationDTO;
    }

    public void addExcursionToReservation(ExcursionDTO excursionDTO, ReservationDTO reservationDTO) {
        if ((excursionDTO==null)||(reservationDTO==null)) {throw new IllegalArgumentException();}
        if ((excursionDTO.getId()==null)||(reservationDTO.getId()==null)) {throw new IllegalArgumentException();}
        
        List<ExcursionDTO> excursionDTOs=new ArrayList<ExcursionDTO>();
        excursionDTOs=reservationDTO.getExcursions();
        excursionDTOs.add(excursionDTO);
        reservationDTO.setExcursions(excursionDTOs);
        reservationService.update(reservationDTO);
    }

    public void removeExcursionFromReservation(ExcursionDTO excursionDTO, ReservationDTO reservationDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeExcursionFromTrip(ExcursionDTO excursionDTO, TripDTO tripDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
