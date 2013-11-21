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
</table>
