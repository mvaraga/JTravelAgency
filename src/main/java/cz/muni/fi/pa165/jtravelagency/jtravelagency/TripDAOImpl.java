/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jakub Marecek (404364)
 */

public class TripDAOImpl implements TripDAO {

    private EntityManager em;
    
    
    public TripDAOImpl(EntityManager em) {
        if (em == null) throw new IllegalArgumentException("Entity Manager can not be null");
        this.em = em;
    }
    
    
    public void createTrip(Trip trip) {
        if (trip == null) throw new IllegalArgumentException("Trip can not be null");
        if (trip.getId() != null) throw new IllegalArgumentException("Trip id has to bed null when new trip is created");
        validateTrip(trip);
        em.persist(trip);
        em.flush();
        em.detach(trip);
    }

    public Trip getTrip(Long id) {
        if (id == null) throw new IllegalArgumentException("Trip can not be null when getting trip");
        return em.find(Trip.class, id);
    }

    public void updateTrip(Trip trip) {
        if (trip == null) throw new IllegalArgumentException("Trip can not be null when updated");
        if (trip.getId() == null) throw new IllegalArgumentException("Trip id can not be null when trip is updated");
        validateTrip(trip);
        em.merge(trip);
        em.flush();
        em.detach(trip);
    }

    public void deleteTrip(Trip trip) {
        if (trip == null) throw new IllegalArgumentException("Trip can not be null when updated");
        if (trip.getId() == null) throw new IllegalArgumentException("Trip id can not be null when trip is updated");
        validateTrip(trip);
        em.remove(trip);
        em.flush();
        em.detach(trip);        
    }

    public List<Trip> getAllTrips() {
        TypedQuery<Trip> query = em.createNamedQuery(
                "getAllTrips", Trip.class);
        return (List<Trip>) query.getResultList();
    }

    public List<Trip> findTripsByDateRange(Date from, Date to) {
        TypedQuery<Trip> query = em.createNamedQuery(
                "findTripsByDateRange", Trip.class);        
        query.setParameter("from", from);
        query.setParameter("to", to);
        return (List<Trip>) query.getResultList();
    }

    public List<Trip> findTripsByDestination(String destination) {
        TypedQuery<Trip> query = em.createNamedQuery(
                "findTripsByDestination", Trip.class);        
        query.setParameter("destination", destination);
        return (List<Trip>) query.getResultList();
    }

    public List<Trip> findTripsByPrice(BigDecimal price) {
        TypedQuery<Trip> query = em.createNamedQuery(
                "findTripsByPrice", Trip.class);        
        query.setParameter("price", price);
        return (List<Trip>) query.getResultList();
    }
    
    private void validateTrip(Trip trip) {
        if (trip.getDateFrom() == null) throw new IllegalArgumentException("Trip date from can not be null");
        if (trip.getDateTo() == null)  throw new IllegalArgumentException("Trip date to can not be null");
        if (trip.getDestination() == null) throw new IllegalArgumentException("Trip destination can not be null");
    }
}
