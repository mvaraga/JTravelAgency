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
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author
 */
public class DTOAndDAOMapper {

    
    public static TripDTO entityToDto(Trip trip) {
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
    
    public static Trip dtoToEntity(TripDTO tripDTO) {
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
        if (rDto ==null) {return null;}
      /**
        Reservation reservation = new Reservation();
        reservation.setId(rDto.getId());
        reservation.setTrip(dtoToEntity(rDto.getTrip()));
        ArrayList<Excursion> excursions=new ArrayList<Excursion>();
        for (ExcursionDTO e : rDto.getExcursions()) {
            excursions.add(DTOAndDAOMapper.dtoToEntity(e));
        }
        reservation.setExcursions(excursions);
       reservation.setCustomer(dtoToEntity(rDto.getCustomer()));
        
        return reservation;
       
       */
        
        Mapper mapper = new DozerBeanMapper();
        Reservation destObject =
        mapper.map(rDto, Reservation.class);
        return destObject;
    }

    public static ReservationDTO entityToDto(Reservation r) {
        if (r==null) {return null;}
       /**
        ReservationDTO rDto=new ReservationDTO();
        rDto.setCustomer(entityToDto(r.getCustomer()));
        rDto.setId(r.getId());
        rDto.setTrip(entityToDto(r.getTrip()));
        ArrayList<ExcursionDTO> excursions=new ArrayList<ExcursionDTO>();
        for(Excursion e: r.getExcursions()){
            excursions.add(entityToDto(e));
        }
        rDto.setExcursions(excursions);
        return rDto;
       */
        Mapper mapper = new DozerBeanMapper();
        ReservationDTO destObject =
        mapper.map(r, ReservationDTO.class);
        return destObject;

    }
    
    public static Excursion dtoToEntity(ExcursionDTO excursionDTO) {
        Excursion excursion = new Excursion();
        excursion.setId(excursionDTO.getId());
        excursion.setDescription(excursionDTO.getDescription());
        excursion.setExcursionDate(excursionDTO.getExcursionDate());
        excursion.setPrice(excursionDTO.getPrice());
        excursion.setTrip(dtoToEntity(excursionDTO.getTrip()));
        return excursion;
    }
    
    public static ExcursionDTO entityToDto(Excursion excursion) {
        ExcursionDTO excursionDTO = new ExcursionDTO();
        excursionDTO.setId(excursion.getId());
        excursionDTO.setDescription(excursion.getDescription());
        excursionDTO.setExcursionDate(excursion.getExcursionDate());
        excursionDTO.setPrice(excursion.getPrice());
        excursionDTO.setTrip(entityToDto(excursion.getTrip()));
        return excursionDTO;
    }
}
