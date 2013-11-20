<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="customer.firstName"/></th>
        <td><s:text id="b1" name="customer.firstName"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="customer.lastName"/></th>
        <td><s:text id="b2" name="customer.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="customer.status"/></th>
        <td><s:select id="b4" name="customer.status"><s:options-enumeration enum="cz.muni.fi.pa165.jtravelagency.dto.CustomerStatus"/></s:select></td>
    </tr>
</table>