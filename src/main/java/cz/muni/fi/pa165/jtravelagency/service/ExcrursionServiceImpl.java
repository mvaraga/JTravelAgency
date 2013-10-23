/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.ExcursionDAO;
import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Excursion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Peter Petrinec
 */
public class ExcrursionServiceImpl implements ExcrursionService{
    
    @Autowired
    private ExcursionDAO excursionDAO;
    
    public void create(ExcursionDTO excursionDTO) {
        if (excursionDTO.getId() != null) {
            throw new IllegalArgumentException("Excursion's id is null.");
        }
        validateExcursion(excursionDTO);
        Excursion excursion = ExcursionDTOToEntity(excursionDTO);
        excursionDAO.createExcursion(excursion);
        excursionDTO.setId(excursion.getId());
    }

    public ExcursionDTO get(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        Excursion excursion = excursionDAO.getExcursion(id);
        return ExcursionDTOFromEntity(excursion);
    }

    public void update(ExcursionDTO excursionDTO) {
        validateExcursion(excursionDTO);
        if(excursionDTO.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        Excursion excursion = ExcursionDTOToEntity(excursionDTO);
        excursionDAO.updateExcursion(excursion);
    }

    public void delete(ExcursionDTO excursionDTO) {
        validateExcursion(excursionDTO);
        if(excursionDTO.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        Excursion excursion = ExcursionDTOToEntity(excursionDTO);
        excursionDAO.deleteExcursion(excursion);
    }

    public List<ExcursionDTO> getAll() {
        List<Excursion> excursions = excursionDAO.getAllExcursions();
        List<ExcursionDTO> excursionDTOs = new ArrayList<ExcursionDTO>();
        for(Excursion e : excursions) {
            excursionDTOs.add(ExcursionDTOFromEntity(e));
        }
        return excursionDTOs;
    }
    
    private void validateExcursion(ExcursionDTO excursionDTO) {
        if(excursionDTO == null) {
            throw new IllegalArgumentException("Excursion cannot be null.");
        }
        if(excursionDTO.getDescription() == null) {
            throw new IllegalArgumentException("Excursion's description cannot "
                    + "be null.");
        }
        if(excursionDTO.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Excursion's description cannot "
                    + "be empty.");
        }
        if(excursionDTO.getPrice() == null) {
            throw new IllegalArgumentException("Excursion's price cannot "
                    + "be null");
        }
        if(excursionDTO.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Excursion's price cannot "
                    + "be a negative number");
        }
        if(excursionDTO.getExcursionDate() == null) {
            throw new IllegalArgumentException("Excursion's date cannot "
                    + "be null.");
        }
    }
    
    private Excursion ExcursionDTOToEntity(ExcursionDTO excursionDTO) {
        Excursion excursion = new Excursion();
        excursion.setId(excursionDTO.getId());
        excursion.setDescription(excursionDTO.getDescription());
        excursion.setExcursionDate(excursionDTO.getExcursionDate());
        excursion.setPrice(excursionDTO.getPrice());
        //previezt z TripDTO na Trip
        //excursion.setTrip(excursion.getTrip());
        return excursion;
    }
    
    private ExcursionDTO ExcursionDTOFromEntity(Excursion excursion) {
        ExcursionDTO excursionDTO = new ExcursionDTO();
        excursionDTO.setId(excursion.getId());
        excursionDTO.setDescription(excursion.getDescription());
        excursionDTO.setExcursionDate(excursion.getExcursionDate());
        excursionDTO.setPrice(excursion.getPrice());
        //previezt z Trip na TripDTO
        //excursionDTO.setTrip(excursion.getTrip());
        return excursionDTO;
    }
}
