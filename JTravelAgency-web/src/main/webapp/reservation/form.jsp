<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<script>$(window).on('load', function() {
        $('.selectpicker').selectpicker();
        $('#excursionsIds').attr('multiple');
        var lang = $("html").attr("lang");
        if (lang == "sk") {
            $('.selectpicker-mult').selectpicker({
                title: 'Vybrať exkurzie...',
            });
        } else {
            $('.selectpicker-mult').selectpicker({
                title: 'Select excursions...',
            });
        }
    });
</script>
<table>
    <tr>
        <th><s:label for="r1" name="reservation.customer"/></th>
        <th><s:label for="r2" name="reservation.trip"/></th>
        <th><s:label for="r3" name="reservation.excursions"/></th>
    </tr>
    <tr>
        <td>
            <s:select name="customerId" class="selectpicker">
                <s:options-collection id="r1" collection="${actionBean.customers}" value="id" label="lastName"/>
            </s:select>
        </td>
        <td>
            <s:select name="tripId" class="selectpicker">
                <s:options-collection id="r2" collection="${actionBean.trips}" value="id" label="destination"/>
            </s:select>
        </td>
        <td>
            <s:select name="excursionsIds" multiple="true" class="selectpicker-mult">
                <s:options-collection id="r3" collection="${actionBean.excursions}" value="id" label="description"/>
            </s:select>          
        </td>
    </tr>
</table>
