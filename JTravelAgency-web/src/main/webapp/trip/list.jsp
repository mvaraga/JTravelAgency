<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<s:layout-render name="/layout.jsp" titlekey="trip.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean" var="actionBean"/>

        <p><f:message key="trip.list.alltrips"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="trip.destination"/></th>
                <th><f:message key="trip.date_from"/></th>
                <th><f:message key="trip.date_to"/></th>
                <th><f:message key="trip.price"/></th>
                <th><f:message key="trip.available_trips"/></th>
                <th></th>
                <th></th>
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
                     <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean" event="edit">
                         <s:param name="trip.id" value="${trip.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean">
                            <s:hidden name="trip.id" value="${trip.id}"/>
                            <s:submit name="delete"><f:message key="trip.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean">
            <fieldset><legend><f:message key="trip.list.newtrip"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="trip.list.newtrip"/></s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>