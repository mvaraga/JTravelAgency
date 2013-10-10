/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author jakub
 */
public class ReservationDAOImpl implements ReservationDAO {

    EntityManager em;

    public ReservationDAOImpl(EntityManager em) {
        if (em == null) {
            throw new IllegalArgumentException("Entity manager cannot be null");
        }
        this.em = em;
    }

    public void createReservation(Reservation reservation) {
        validateReservation(reservation);
        if (reservation.getId() != null) {
            throw new IllegalArgumentException("Already in DB");
        }

        em.persist(reservation);
    }

    public void updateReservation(Reservation reservation) {
        validateReservation(reservation);
        if (reservation.getId() == null) {
            throw new IllegalArgumentException("ReservationID cannot be null");
        }

        em.merge(reservation);
        em.flush();
        em.detach(reservation);
    }

    public Reservation getReservation(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id of reservation cannot be null");
        }
        return em.find(Reservation.class, id);
    }

    public void deleteReservation(Reservation reservation) {
        validateReservation(reservation);
        if (reservation.getId() == null) {
            throw new IllegalArgumentException("ID of reservation is null");
        }
        em.remove(reservation);
        em.flush();
        em.detach(reservation);
    }

    public List<Reservation> getAllReservations() {
        TypedQuery<Reservation> query = em.createNamedQuery(
                "getAllReservations", Reservation.class);
      
        return (List<Reservation>) query.getResultList();
    }

    public List<Reservation> getReservationByTrip(Trip trip) {
        TypedQuery<Reservation> query = em.createNamedQuery(
                "getReservationsByTrip", Reservation.class);
          query.setParameter("trip",trip.getId());
        return (List<Reservation>) query.getResultList();
    }

    public List<Reservation> getReservationByCustomer(Customer customer) {
        TypedQuery<Reservation> query = em.createNamedQuery(
                "getReservationsByCustomer", Reservation.class);
        query.setParameter("customer",customer.getId());
        return (List<Reservation>) query.getResultList();
    }

    private void validateReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
        if (reservation.getCustomer() == null) {
            throw new IllegalArgumentException("Customer must be set");
        }
        if (reservation.getTrip() == null) {
            throw new IllegalArgumentException("Trip must be set");
        }
        if (reservation.getExcursions() == null) {
            throw new IllegalArgumentException("At least one excursion must be chosen");
        }

        //teda spravime tu validaciu tak, ze aspon jedna exkurzia musi byt vybrata .. ci??
    }
}
