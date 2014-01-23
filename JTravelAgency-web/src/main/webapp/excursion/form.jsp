<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<script>$(window).on('load', function() {
        $('.selectpicker').selectpicker();
    });
</script>
<table>
    <tr>
        <th><s:label for="datepicker" name="excursion.excursionDate"/></th>
        <th><s:label for="e2" name="excursion.description"/></th>
        <th><s:label for="e3" name="excursion.price"/></th>
        <th><s:label for="e4" name="excursion.trip"/></th>
    </tr>
    <tr>
        <td><s:text formatType="datetime" id="datepicker" name="date" class="form-control"/></td>
        <td><s:text id="e2" name="excursion.description" class="form-control"/></td>
        <td><s:text id="e3" name="excursion.price" formatType="number" formatPattern=".##" class="form-control"/></td>
        <td>
            <s:select name="tripId" class="selectpicker">
                <s:options-collection id="e4" collection="${actionBean.trips}" value="id" label="destination"/>
            </s:select>
        </td>
    </tr>
</table>