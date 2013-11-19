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
    

    @Override
    public void createCustomer(CustomerDTO customerDTO) {
        if((customerDTO==null)||(customerDTO.getId()!=null)) {throw new IllegalArgumentException();}
        customerService.create(customerDTO);
    }

    @Override
    public void createTrip(TripDTO tripDTO) {
        if((tripDTO==null)||(tripDTO.getId()!=null)) {throw new IllegalArgumentException();}
        tripService.create(tripDTO);
    }

    @Override
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

    /**
    public List<ExcursionDTO> getExcursionsByTrip(TripDTO trip) {
        if ((trip==null)||(trip.getId()==null)) {throw new IllegalArgumentException();}
        return excursionService.
    }
*/
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
    public List<ReservationDTO> getReservationByCustomer(CustomerDTO customerDTO) {
        if ((customerDTO==null)||(customerDTO.getId()==null)) {throw new IllegalArgumentException();}
        return reservationService.getByCustomer(customerDTO);
    }

    @Override
    public List<ReservationDTO> getReservationByTrip(TripDTO tripDTO) {
        if ((tripDTO==null)||(tripDTO.getId()==null)) {throw  new IllegalArgumentException();}
        return reservationService.getByTrip(tripDTO);
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
    public void updateTrip(TripDTO tripDTO) {
        if ((tripDTO==null)||(tripDTO.getId()==null)) {throw new IllegalArgumentException();}
        tripService.update(tripDTO);
    }

    @Override
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
    public void deleteCustomer(CustomerDTO customerDTO) {
        customerService.delete(customerDTO);
    }

    @Override
    public void deleteTrip(TripDTO tripDTO) {
        tripService.delete(tripDTO);
    }

    @Override
    public void deleteExcursion(ExcursionDTO excursionDTO) {
        excursionService.delete(excursionDTO);
    }

    @Override
    public void deleteReservation(ReservationDTO reservationDTO) {
        reservationService.delete(reservationDTO);
    }

    @Override
    public void setDeletedStatusToCustomer(CustomerDTO customerDTO) {
        customerService.setDeletedStatus(customerDTO);
    }

    @Override
    public List<TripDTO> findTripsByDateRange(LocalDate from, LocalDate to) {
        return tripService.findAllByDateRange(from, to);
    }

    @Override
    public List<TripDTO> findTripsByDestination(String destination) {
        return tripService.findAllByDestination(destination);
    }

    @Override
    public void addExcursionToTrip(ExcursionDTO excursionDTO, TripDTO tripDTO) {
        if ((excursionDTO==null)||(excursionDTO.getId()==null)) {throw new IllegalArgumentException();}
        if ((tripDTO==null)||(tripDTO.getId()==null)) {throw new IllegalArgumentException();}
        
        List<ExcursionDTO> excursionsDTO=new ArrayList<ExcursionDTO>();
        excursionsDTO=tripDTO.getExcursions();
        excursionsDTO.add(excursionDTO);
        tripDTO.setExcursions(excursionsDTO);
        
        tripService.update(tripDTO);
    }

    @Override
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
    public void addExcursionToReservation(ExcursionDTO excursionDTO, ReservationDTO reservationDTO) {
        if ((excursionDTO==null)||(reservationDTO==null)) {throw new IllegalArgumentException();}
        if ((excursionDTO.getId()==null)||(reservationDTO.getId()==null)) {throw new IllegalArgumentException();}
        
        List<ExcursionDTO> excursionDTOs=new ArrayList<ExcursionDTO>();
        excursionDTOs=reservationDTO.getExcursions();
        excursionDTOs.add(excursionDTO);
        reservationDTO.setExcursions(excursionDTOs);
        reservationService.update(reservationDTO);
    }

}