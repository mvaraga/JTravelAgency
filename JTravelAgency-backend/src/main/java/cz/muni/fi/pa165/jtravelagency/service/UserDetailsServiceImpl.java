/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.service;

import cz.muni.fi.pa165.jtravelagency.dao.CustomerDAO;
import cz.muni.fi.pa165.jtravelagency.dto.CustomerDTO;
import cz.muni.fi.pa165.jtravelagency.entity.Customer;
import cz.muni.fi.pa165.jtravelagency.facade.ServiceFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Radka
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private CustomerDAO customerDAO;

    @Transactional(readOnly = true)
 
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{        
        Customer cust = customerDAO.findCustomerWithUsername(username);
        if (cust == null) {
            throw new UsernameNotFoundException("user with name" + username + " not found");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
        return new User(cust.getUserName(), cust.getPassword(), true, true, true, true, authorities);
    }
}
