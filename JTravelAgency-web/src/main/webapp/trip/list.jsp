<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<s:layout-render name="/layout.jsp" titlekey="trip.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean" var="actionBean"/>
        <script>
            $(document).ready(function() {
                $("li.nav-trip").addClass("active");
            });
        </script>
        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean" class="new-form">
            <fieldset><legend><f:message key="trip.list.newtrip"/></legend>
                <%@include file="form.jsp"%>
                <div class="new-form-input">
                    <s:submit name="add" class="btn btn-default"><f:message key="form.submit"/></s:submit>
                    </div>
                </fieldset>
        </s:form>

        <div class="block-title"><f:message key="trip.list.alltrips"/></div>

        <table class="table table-bordered table-striped">
            <tr>
                <th>id</th>
                <th><f:message key="trip.destination"/></th>
                <th><f:message key="trip.date_from"/></th>
                <th><f:message key="trip.date_to"/></th>
                <th><f:message key="trip.price"/></th>
                <th><f:message key="trip.available_trips"/></th>
                <th><f:message key="form.action"/></th>
            </tr>
            <c:forEach items="${actionBean.trips}" var="trip">
                <tr>
                    <td>${trip.id}</td>
                    <td><c:out value="${trip.destination}"/></td>
                    <td><joda:format value="${trip.dateFrom}" pattern="dd.MM.yyyy" /></td>
                    <td><joda:format value="${trip.dateTo}" pattern="dd.MM.yyyy" /></td>
                    <td><c:out value="${trip.price}"/></td>        
                    <td><c:out value="${trip.availableTrips}"/></td>
                    <td>
                        <div class="list-action-edit">
                            <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean" event="edit" class="btn btn-default btn-sm">
                                <s:param name="trip.id" value="${trip.id}"/><span class="glyphicon glyphicon-pencil"></span> <f:message key="form.edit"/></s:link>
                            </div>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean">
                            <s:hidden name="trip.id" value="${trip.id}"/>
                            <button name="delete" type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> <f:message key="form.delete"/></button>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>