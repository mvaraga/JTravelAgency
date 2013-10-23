/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.dao.util;

import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;

/**
 *
 * @author
 */
public class DTOAndDAOMapper {

    
    private static TripDTO EntityTripToDTOTrip(Trip trip) {
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
    
    private static Trip DTOTripToEntityTrip(TripDTO tripDTO) {
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
}
