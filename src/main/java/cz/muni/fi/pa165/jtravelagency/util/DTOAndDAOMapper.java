/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.util;

import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class DTOAndDAOMapper {

    
    public static TripDTO EntityToDto(Trip trip) {
        if (trip == null) {
            return null;
        }
        TripDTO tripDTO = new TripDTO();
        
        tripDTO.setId(trip.getId());
        tripDTO.setDateFrom(trip.getDateFrom());
        tripDTO.setDateTo(trip.getDateTo());
        tripDTO.setDestination(trip.getDestination());
        tripDTO.setAvailableTrips(trip.getAvailableTrips());
        //tripDTO.setExcursions(trip.getExcursions());
        tripDTO.setPrice(trip.getPrice());
        return tripDTO;
    }
    
    public static Trip DtoToEntity(TripDTO tripDTO) {
        if (tripDTO == null) {
            return null;
        }
        Trip trip = new Trip();
        
        trip.setDateFrom(tripDTO.getDateFrom());
        trip.setDateTo(tripDTO.getDateTo());
        trip.setDestination(tripDTO.getDestination());
        trip.setAvailableTrips(tripDTO.getAvailableTrips());
        //trip.setExcursions(tripDTO.getExcursions());
        trip.setPrice(tripDTO.getPrice());
        return trip;
    }
    
    public static Customer dtoToEntity(CustomerDTO cDto) {
        if (cDto == null) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId(cDto.getId());
        customer.setFirstName(cDto.getFirstName());
        customer.setLastName(cDto.getLastName());
        customer.setStatus(cDto.getStatus());
        List<Reservation> list = new ArrayList<Reservation>();
        for (ReservationDTO r : cDto.getReservations()) {
            list.add(DTOAndDAOMapper.dtoToEntity(r));
        }
        customer.setReservations(list);

        return customer;
    }

    public static CustomerDTO entityToDto(Customer c) {
        if (c == null) {
            return null;
        }
        CustomerDTO customerDto = new CustomerDTO();

        customerDto.setId(c.getId());
        customerDto.setFirstName(c.getFirstName());
        customerDto.setLastName(c.getLastName());
        customerDto.setStatus(c.getStatus());
        List<ReservationDTO> list = new ArrayList<ReservationDTO>();
        for (Reservation r : c.getReservations()) {
            list.add(DTOAndDAOMapper.entityToDto(r));
        }
        customerDto.setReservations(list);

        return customerDto;
    }


    public static Reservation dtoToEntity(ReservationDTO rDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ReservationDTO entityToDto(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Excursion DtoToEntity(ExcursionDTO excursionDTO) {
        Excursion excursion = new Excursion();
        excursion.setId(excursionDTO.getId());
        excursion.setDescription(excursionDTO.getDescription());
        excursion.setExcursionDate(excursionDTO.getExcursionDate());
        excursion.setPrice(excursionDTO.getPrice());
        excursion.setTrip(DtoToEntity(excursionDTO.getTrip()));
        return excursion;
    }
    
    public static ExcursionDTO EntityToDto(Excursion excursion) {
        ExcursionDTO excursionDTO = new ExcursionDTO();
        excursionDTO.setId(excursion.getId());
        excursionDTO.setDescription(excursion.getDescription());
        excursionDTO.setExcursionDate(excursion.getExcursionDate());
        excursionDTO.setPrice(excursion.getPrice());
        excursionDTO.setTrip(EntityToDto(excursion.getTrip()));
        return excursionDTO;
    }
}
