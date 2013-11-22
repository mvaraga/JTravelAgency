<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <s:hidden name="customer.status" value="REGULAR"/>
        <th><s:label for="b1" name="customer.firstName"/></th>
        <td><s:text id="b1" name="customer.firstName"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="customer.lastName"/></th>
        <td><s:text id="b2" name="customer.lastName"/></td>
    </tr>

</table>