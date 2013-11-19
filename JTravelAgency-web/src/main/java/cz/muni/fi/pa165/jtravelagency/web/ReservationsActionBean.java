/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.web;

import cz.muni.fi.pa165.jtravelagency.dto.ReservationDTO;
import cz.muni.fi.pa165.jtravelagency.service.CustomerService;
import cz.muni.fi.pa165.jtravelagency.service.ExcursionService;
import cz.muni.fi.pa165.jtravelagency.service.ReservationService;
import cz.muni.fi.pa165.jtravelagency.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Radka
 */
public class ReservationsActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(ReservationsActionBean.class);
    private ReservationService reservationService;
    private TripService tripService;
    private CustomerService customerService;
    private ExcursionService excursionService;
    
    private ReservationDTO reservationDTO;
}
