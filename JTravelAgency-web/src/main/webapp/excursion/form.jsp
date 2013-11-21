<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="e1" name="excursion.excursionDate"/></th>
        <td><s:text formatType="datetime" formatPattern="yyyy-MM-dd" id="e1" name="date"/></td>
    </tr>
    <tr>
        <th><s:label for="e2" name="excursion.description"/></th>
        <td><s:text id="e2" name="excursion.description"/></td>
    </tr>
    <tr>
        <th><s:label for="e3" name="excursion.price"/></th>
        <td><s:text id="e3" name="excursion.price"/></td>
    </tr>
    <tr>
        <th><s:label for="e4" name="excursion.trip"/></th>
        <td>
            <s:select name="tripId">
                <s:options-collection id="e4" collection="${actionBean.trips}" value="id" label="destination"/>
            </s:select>
        </td>
    </tr>
</table>