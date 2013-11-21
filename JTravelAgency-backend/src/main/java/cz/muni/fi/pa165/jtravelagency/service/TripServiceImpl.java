/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.TripDAO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import cz.muni.fi.pa165.jtravelagency.util.DTOAndEntityMapper;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author jakub
 */
@Service
public class TripServiceImpl implements TripService {
    
    
    @Autowired
    TripDAO tripDAO;
    
    public void setTripDAO(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }
    
    
    @Override
    public void create(TripDTO tripDTO) {
        if (tripDTO == null) {
            throw new IllegalArgumentException("TripDTO is null.");
        }
        if (tripDTO.getId() != null) {
            throw new IllegalArgumentException("TripDTO's id is null.");
        }
        validateTripDTO(tripDTO);
        //Trip trip = DTOAndEntityMapper.dtoToEntity(tripDTO, Trip.class);
        Trip trip = DTOAndEntityMapper.dtoToEntity(tripDTO);
        tripDAO.createTrip(trip);
        tripDTO.setId(trip.getId());
    }

    @Override
    public TripDTO get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can not be null.");
        }
        Trip trip = tripDAO.getTrip(id);
        return DTOAndEntityMapper.entityToDto(trip);
    }

    @Override
    public void update(TripDTO tripDTO) {
        if (tripDTO == null) {
            throw new IllegalArgumentException("TripDTO is null.");
        }
        if (tripDTO.getId() == null) {
            throw new IllegalArgumentException("ID can not be null.");
        }
        validateTripDTO(tripDTO);
        //Trip trip = DTOAndEntityMapper.dtoToEntity(tripDTO, Trip.class);
        Trip trip = DTOAndEntityMapper.dtoToEntity(tripDTO);
        tripDAO.updateTrip(trip);
    }

    @Override
    public void delete(TripDTO tripDTO) {
        if (tripDTO == null) {
            throw new IllegalArgumentException("TripDTO is null.");
        }
        if (tripDTO.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        validateTripDTO(tripDTO);
        //Trip trip = DTOAndEntityMapper.dtoToEntity(tripDTO, Trip.class);
        Trip trip = DTOAndEntityMapper.dtoToEntity(tripDTO);
        tripDAO.deleteTrip(trip);
    }

    @Override
    public List<TripDTO> getAll() {
        List<Trip> trips = tripDAO.getAllTrips();
        List<TripDTO> tripDTOs = new ArrayList<TripDTO>();
        for(Trip t : trips) {
            //tripDTOs.add(DTOAndEntityMapper.entityToDto(t, TripDTO.class));
            tripDTOs.add(DTOAndEntityMapper.entityToDto(t));
        }
        return tripDTOs;
    }

    @Override
    public List<TripDTO> findAllByDateRange(LocalDate from, LocalDate to) {
        if (from == null) {
            throw new IllegalArgumentException("TripDTO's date from can not be null.");
        }
        if (to == null) {
            throw new IllegalArgumentException("TripDTO's to from can not be null.");
        }
        List<Trip> trips = tripDAO.findTripsByDateRange(from, to);
        List<TripDTO> tripDTOs = new ArrayList<TripDTO>();
        for(Trip t : trips) {
            //tripDTOs.add(DTOAndEntityMapper.entityToDto(t, TripDTO.class));
            tripDTOs.add(DTOAndEntityMapper.entityToDto(t));
        }
        return tripDTOs;
    }

    @Override
    public List<TripDTO> findAllByDestination(String destination) {
        List<Trip> trips = tripDAO.findTripsByDestination(destination);
        List<TripDTO> tripDTOs = new ArrayList<TripDTO>();
        for(Trip t : trips) {
            //tripDTOs.add(DTOAndEntityMapper.entityToDto(t, TripDTO.class));
            tripDTOs.add(DTOAndEntityMapper.entityToDto(t));
        }
        return tripDTOs;
    }
    
    private void validateTripDTO(TripDTO tripDTO) {
        if (tripDTO.getDateFrom() == null) {
            throw new IllegalArgumentException("TripDTO's date from can not be null");
        }
        if (tripDTO.getDateTo() == null) {
            throw new IllegalArgumentException("TripDTO's date to can not be null");
        }
        if (tripDTO.getDestination() == null) {
            throw new IllegalArgumentException("TripDTO's destination can not be null");
        }
    }
}
