/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.dao;

import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import cz.muni.fi.pa165.jtravelagency.entity.Reservation;
import cz.muni.fi.pa165.jtravelagency.entity.Trip;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Peter Petrinec
 */
@Repository
public class ExcursionDAOImpl implements ExcursionDAO {
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public ExcursionDAOImpl() {
    }
    
    public ExcursionDAOImpl(EntityManager em) {
        if(em == null) {
            throw new IllegalArgumentException("Entity manager cannot be null");
        }
        this.em = em;
    }
    
    @Override
    public void createExcursion(Excursion excursion) {
        validateExcursion(excursion);
        if(excursion.getId() != null) {
            throw new IllegalArgumentException("Excursion's id is null.");
        }
       
        em.persist(excursion);
        //em.flush();
    }

    @Override
    public Excursion getExcursion(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        return em.find(Excursion.class, id);
    }

    @Override
    public void updateExcursion(Excursion excursion) {
        validateExcursion(excursion);
        if(excursion.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        em.merge(excursion);
        //em.flush();
        //em.detach(excursion);
    }

    @Override
    public void deleteExcursion(Excursion excursion) {
        validateExcursion(excursion);
        if(excursion.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        Excursion excursionToDelete = em.find(Excursion.class, excursion.getId());
        em.remove(excursionToDelete);
       // em.flush();
    }

    @Override
    public List<Excursion> getAllExcursions() {
        Query query = em.createNamedQuery("getAllExcursions");
        return query.getResultList();
    }
    
    @Override
    public Trip getTrip(Excursion excursion) {
        return excursion.getTrip();
    }
    
    @Override
    public List<Excursion> getExcursionsByTrip(Trip trip) {
        TypedQuery<Excursion> query = em.createNamedQuery(
                "getExcursionsByTrip", Excursion.class);
        query.setParameter("tripId", trip.getId());
        return (List<Excursion>) query.getResultList();
    }
    
    private void validateExcursion(Excursion excursion) {
        if(excursion == null) {
            throw new IllegalArgumentException("Excursion cannot be null.");
        }
        if(excursion.getDescription() == null) {
            throw new IllegalArgumentException("Excursion's description cannot "
                    + "be null.");
        }
        if(excursion.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Excursion's description cannot "
                    + "be empty.");
        }
        if(excursion.getPrice() == null) {
            throw new IllegalArgumentException("Excursion's price cannot "
                    + "be null");
        }
        if(excursion.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Excursion's price cannot "
                    + "be a negative number");
        }
        if(excursion.getExcursionDate() == null) {
            throw new IllegalArgumentException("Excursion's date cannot "
                    + "be null.");
        }
        if(excursion.getTrip() == null) {
            throw new IllegalArgumentException("Excursion's trip cannot "
                    + "be null.");
        }
        if(excursion.getTrip().getId() == null) {
            throw new IllegalArgumentException("Excursion's trip's id cannot "
                    + "be null.");
        }
    }
}
