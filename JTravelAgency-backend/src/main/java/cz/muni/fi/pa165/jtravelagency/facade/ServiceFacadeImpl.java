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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    

    @Override
    @Secured({"ROLE_ADMIN"})
    public void createCustomer(CustomerDTO customerDTO) {
        if((customerDTO==null)||(customerDTO.getId()!=null)) {throw new IllegalArgumentException();}
        customerService.create(customerDTO);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void createTrip(TripDTO tripDTO) {
        if((tripDTO==null)||(tripDTO.getId()!=null)) {throw new IllegalArgumentException();}
        tripService.create(tripDTO);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void createExcursion(ExcursionDTO excursionDTO) {
        if((excursionDTO==null)||(excursionDTO.getId()!=null)) {throw new IllegalArgumentException();}
        excursionService.create(excursionDTO);
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        if(id==null) {throw new IllegalArgumentException();}
        return customerService.get(id);
    }

    @Override
    public TripDTO getTrip(Long id) {
        if (id==null) {throw new IllegalArgumentException();}
        return tripService.get(id);
    }

    @Override
    public ExcursionDTO getExcursion(Long id) {
        if (id==null) {throw new IllegalArgumentException();}
        return excursionService.get(id);
    }

    @Override
    public ReservationDTO getReservation(Long id) {
        if (id==null) {throw new IllegalArgumentException();}
        return reservationService.get(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAll();
    }

    @Override
    public List<TripDTO> getAllTrips() {
        return tripService.getAll();
    }

    @Override
    public List<ExcursionDTO> getAllExcursions() {
       return excursionService.getAll();
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAll();
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if ((customerDTO==null)||(customerDTO.getId()==null)) {throw new IllegalArgumentException();}
       customerService.update(customerDTO);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void updateTrip(TripDTO tripDTO) {
        if ((tripDTO==null)||(tripDTO.getId()==null)) {throw new IllegalArgumentException();}
        tripService.update(tripDTO);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void updateExcursion(ExcursionDTO excursionDTO) {
        if ((excursionDTO==null)||(excursionDTO.getId()==null)) {throw new IllegalArgumentException();}
       excursionService.update(excursionDTO);
    }

    @Override
    public void updateReservation(ReservationDTO reservationDTO) {
        if ((reservationDTO==null)||(reservationDTO.getId()==null)) {throw new IllegalArgumentException();}
        reservationService.update(reservationDTO);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteCustomer(CustomerDTO customerDTO) {
        for(ReservationDTO r: reservationService.getByCustomer(customerDTO)){
            reservationService.delete(r);
        }
        customerService.delete(customerDTO);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteTrip(TripDTO tripDTO) {
        for(ReservationDTO r : reservationService.getByTrip(tripDTO)){
            reservationService.delete(r);
        }
        tripService.delete(tripDTO);
    }

    @Override
    @Secured({"ROLE_ADMIN"})
    public void deleteExcursion(ExcursionDTO excursionDTO) {
        for(ReservationDTO r : reservationService.getAll()){
            r.getExcursions().remove(excursionDTO);
            reservationService.update(r);
        }
        excursionService.delete(excursionDTO);
    }

    @Override
    public void deleteReservation(ReservationDTO reservationDTO) {
        reservationService.delete(reservationDTO);
    }

    @Override
    @Transactional
    public ReservationDTO makeReservation(CustomerDTO customerDTO, TripDTO tripDTO, List<ExcursionDTO> excursionDTOs) {
        
        if ((customerDTO==null) || (customerDTO.getId()==null)) {throw new IllegalArgumentException();}
        if ((tripDTO==null) || (tripDTO.getId()==null)) {throw new IllegalArgumentException();}
        if (excursionDTOs != null) {

          for (int i = 0; i < excursionDTOs.size(); i++) {
               if (excursionDTOs.get(i).getId() == null) {
                  throw new IllegalArgumentException();
                }
            }

        }
        ReservationDTO reservationDTO=new ReservationDTO();
        reservationDTO.setCustomer(customerDTO);
        reservationDTO.setTrip(tripDTO);
        reservationDTO.setExcursions(excursionDTOs);
        
        reservationService.create(reservationDTO);
        
        return reservationDTO;
    }
    
    @Override
    public List<ReservationDTO> getReservationsByCustomer(CustomerDTO customer) {
        return reservationService.getByCustomer(customer);
    }
    
    @Override
    public CustomerDTO getCustomerByUsername(String username){
        return customerService.getCustomerByUsername(username);
    }
}