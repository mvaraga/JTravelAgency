<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<table>
    <tr>
        <th><s:label for="b1" name="trip.destination"/></th>
        <th><s:label for="datepicker" name="trip.date_from"/></th>
        <th><s:label for="datepicker2" name="trip.date_to"/></th>
        <th><s:label for="b4" name="trip.price"/></th>
        <th><s:label for="b5" name="trip.available_trips"/></th>
    </tr>
    <tr>
        <td><s:text id="b1" name="trip.destination" class="form-control"/></td>
        <td><s:text formatType="datetime" id="datepicker" name="dateFrom" class="form-control"/></td>
        <td><s:text formatType="datetime" id="datepicker2" name="dateTo" class="form-control"/></td>
        <td><s:text id="b4" name="trip.price" class="form-control"/></td>
        <td><s:text id="b5" name="trip.availableTrips" class="form-control"/></td>
    </tr>
</table>