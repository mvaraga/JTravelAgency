/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.ReservationDAO;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.dto.TripDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import java.util.List;

/**
 *
 * @author jakub
 */
public class ReservationServiceImpl implements ReservationService {
    
    private ReservationDAO reservationDAO;

    public void setReservationDAO(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }
    
    public void create(ReservationDTO reservation){}
    public void delete(ReservationDTO  reservation){}
    public void update(ReservationDTO  reservation){}
    public ReservationDTO  get(Long id){
        return null;
    }
    public List<ReservationDTO> getAll(){
        return null;
    }
    public List<ReservationDTO> getByTrip(TripDTO trip){
        return null;
    }
    public List<ReservationDTO> getByCustomer(CustomerDTO customer){
        return null;
    }
    
  

  
   
    
}
