<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="trip.destination"/></th>
        <td><s:text id="b1" name="trip.destination"/></td>
    </tr>
    <tr>
        <th><s:label for="datepicker" name="trip.date_from"/></th>
        <td><s:text formatType="datetime" id="datepicker" name="dateFrom"/></td>
    </tr>
    <tr>
        <th><s:label for="datepicker2" name="trip.date_to"/></th>
        <td><s:text formatType="datetime" id="datepicker2" name="dateTo"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="trip.price"/></th>
        <td><s:text id="b4" name="trip.price"/></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="trip.available_trips"/></th>
        <td><s:text id="b5" name="trip.availableTrips"/></td>
    </tr>
</table>