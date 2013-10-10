/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.jtravelagency;

import java.util.List;

/**
 *
 * @author jakub
 */
public interface ReservationDAO {
    
    public void createReservation(Reservation reservation);
    public void deleteReservation(Reservation reservation);
    public void updateReservation(Reservation reservation);
    public Reservation getReservation(Long id);
    public List<Reservation> getAllReservations();
    public List<Reservation> getReservationByTrip(Trip trip);
    public List<Reservation> getReservationByCustomer(Customer customer);
    
    
}
