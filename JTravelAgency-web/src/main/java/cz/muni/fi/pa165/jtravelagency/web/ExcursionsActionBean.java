/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.web;

import cz.muni.fi.pa165.jtravelagency.dto.ExcursionDTO;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter
 */
public class ExcursionsActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(ExcursionsActionBean.class);
    
    @SpringBean
    protected ServiceFacade facade;
    
    private List<ExcursionDTO> excursions;
    
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        excursions = facade.getAllExcursions();
        return new ForwardResolution("/customer/list.jsp");
    }
    
    public List<ExcursionDTO> getExcursions() {
        return excursions;
    }
}
