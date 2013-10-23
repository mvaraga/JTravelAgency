/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.TripDAO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import java.util.List;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author jakub
 */
public class TripServiceImpl implements TripService {
    
    
    @Autowired
    TripDAO tripDAO;
    
    public void setTripDAO(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }
    
    @Override
    public void create(TripDTO tripDTO) {
        //Trip trip = TripDTOToTripEntity(tripDTO);
        //tripDAO.createTrip(trip);
        //tripDTO.setId(trip.getId());
    }

    @Override
    public TripDTO get(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TripDTO trip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TripDTO trip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TripDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TripDTO> findAllByDateRange(LocalDate from, LocalDate to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TripDTO> findAllByDestination(String destination) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
