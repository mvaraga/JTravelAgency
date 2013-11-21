<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="trip.destination"/></th>
        <td><s:text id="b1" name="trip.destination"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="trip.dateFrom"/></th>
        <td><s:text formatType="datetime" formatPattern="yyyy-MM-dd" id="b2" name="trip.dateFrom"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="trip.dateTo"/></th>
        <td><s:text formatType="datetime" formatPattern="yyyy-MM-dd" id="b3" name="trip.dateTo"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="trip.price"/></th>
        <td><s:text id="b4" name="trip.price"/></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="trip.availableTrips"/></th>
        <td><s:text id="b5" name="trip.availableTrips"/></td>
    </tr>
</table>