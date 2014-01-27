/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.jtravelagency.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Radka
*/

public class UsernamePasswordAuthenticationFilterImpl extends UsernamePasswordAuthenticationFilter {
    static final String ORIGIN = "Origin";
/**
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        if (request.getHeader(ORIGIN)!=null) {
            String origin = request.getHeader(ORIGIN);
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        }
        if (request.method == 'OPTIONS') {
            response.writer.print('OK');
            response.writer.flush();
            return;
        }
        return super.attemptAuthentication(request, response);
     
    }
*/
}
