/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.util;

import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
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
}
