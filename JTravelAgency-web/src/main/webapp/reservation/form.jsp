<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="r1" name="reservation.customer"/></th>
        
                <td>
            <s:select name="customerId">
                <s:options-collection id="r1" collection="${actionBean.customers}" value="id" label="lastName"/>
            </s:select>
        </td>
            
    </tr>
<tr>
    <th><s:label for="r2" name="reservation.trip"/></th>
                  
         <td>
    
              <s:select name="tripId">
                <s:options-collection id="r2" collection="${actionBean.trips}" value="id" label="destination"/>
            </s:select>
        </td>
   </tr>
   <tr>
       <th><s:label for="r3" name="reservation.excursions"/></th>
       <td>
               <s:select name="excursionId">
                <s:options-collection id="r3" collection="${actionBean.excursions}" value="id" label="description"/>
            </s:select>          
       </td>
   </tr>
</table>
