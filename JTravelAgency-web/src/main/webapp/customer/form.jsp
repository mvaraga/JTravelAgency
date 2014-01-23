<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<table>
    <tr>
        <th><s:label for="b1" name="customer.firstName"/></th>
        <th><s:label for="b2" name="customer.lastName"/></th>
    </tr>
    <tr>
        <td><s:text id="b1" name="customer.firstName" class="form-control"/></td>
        <td><s:text id="b2" name="customer.lastName" class="form-control"/></td>
    </tr>
</table>