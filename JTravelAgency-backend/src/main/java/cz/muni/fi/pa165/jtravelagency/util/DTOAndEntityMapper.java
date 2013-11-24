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
 * @author mvaraga
 */
public class DTOAndEntityMapper {

    public static <T1, T2> T1 entityToDto(T2 srcObject, Class<T1> destClass) {
        if (srcObject == null) {
            return null;
        }
        Mapper mapper = new DozerBeanMapper();
        T1 destObject = mapper.map(srcObject, destClass);
        return destObject;
    }

    public static <T1, T2> T1 dtoToEntity(T2 dto, Class<T1> destClass) {
        return entityToDto(dto, destClass);
    }
    
    
    public static Excursion dtoToEntity(ExcursionDTO excursionDTO) {
         if (excursionDTO == null) {
             return null;
         }   

         
         
         Excursion excursion = new Excursion();
         excursion.setId(excursionDTO.getId());
         excursion.setDescription(excursionDTO.getDescription());
         excursion.setExcursionDate(excursionDTO.getExcursionDate());
         excursion.setPrice(excursionDTO.getPrice());
         excursion.setTrip(dtoToEntity(excursionDTO.getTrip()));
         return excursion;
     }
 
     public static ExcursionDTO entityToDto(Excursion excursion) {
         if (excursion == null) {
             return null;
         }   

         
         ExcursionDTO excursionDTO = new ExcursionDTO();
         excursionDTO.setId(excursion.getId());
         excursionDTO.setDescription(excursion.getDescription());
         excursionDTO.setExcursionDate(excursion.getExcursionDate());
         excursionDTO.setPrice(excursion.getPrice());
         excursionDTO.setTrip(entityToDto(excursion.getTrip()));
         return excursionDTO;
     }
    
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
        trip.setId(tripDTO.getId());
        trip.setDateFrom(tripDTO.getDateFrom());
        trip.setDateTo(tripDTO.getDateTo());
        trip.setDestination(tripDTO.getDestination());
        trip.setAvailableTrips(tripDTO.getAvailableTrips());
        //trip.setExcursions(tripDTO.getExcursions());
        trip.setPrice(tripDTO.getPrice());
        return trip;
    }
    
        public static Reservation dtoToEntity(ReservationDTO reservationDTO) {
         if (reservationDTO == null) {
             return null;
         }   
         Reservation reservation = new Reservation();
         reservation.setId(reservationDTO.getId());
         reservation.setCustomer(dtoToEntity(reservationDTO.getCustomer(), Customer.class));
         reservation.setTrip(dtoToEntity(reservationDTO.getTrip()));
         List<Excursion> exc=new ArrayList<Excursion>();
         for(int i=0;i<reservationDTO.getExcursions().size();i++){
             exc.add(dtoToEntity(reservationDTO.getExcursions().get(i)));
         }
         reservation.setExcursions(exc);
         
         return reservation;
     }
        
            public static ReservationDTO entityToDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId(reservation.getId());
        reservationDTO.setCustomer(entityToDto(reservation.getCustomer(),CustomerDTO.class));
        reservationDTO.setTrip(entityToDto(reservation.getTrip()));
        List<ExcursionDTO> exc=new ArrayList<ExcursionDTO>();
        for(int i=0;i<reservation.getExcursions().size();i++){
            exc.add(entityToDto(reservation.getExcursions().get(i)));
        }
        reservationDTO.setExcursions(exc);
        return reservationDTO;
    }
}
