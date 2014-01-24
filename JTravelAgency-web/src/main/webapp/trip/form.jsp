<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<script type="text/javascript">
        $(function () {
            $('#datetimepicker8').datetimepicker({
                language: 'sk',
                //pickTime: false
            });
            $('#datetimepicker9').datetimepicker({
                language: 'sk',
                //pickTime: false
            });
            $("#datetimepicker8").on("change.dp",function (e) {
               $('#datetimepicker9').data("DateTimePicker").setStartDate(e.date);
            });
            $("#datetimepicker9").on("change.dp",function (e) {
               $('#datetimepicker8').data("DateTimePicker").setEndDate(e.date);
            });
        });
    </script>
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

        <td>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker8'>
                    <s:text formatType="datetime" id="datepicker" name="dateFrom" class="form-control"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </td>
        <td><div class="form-group">
                <div class='input-group date' id='datetimepicker9'>
                    <s:text formatType="datetime" id="datepicker2" name="dateTo" class="form-control"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div></td>
        <td><s:text id="b4" name="trip.price" class="form-control"/></td>
        <td><s:text id="b5" name="trip.availableTrips" class="form-control"/></td>
    </tr>
</table>