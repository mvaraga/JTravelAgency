<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="reservation.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean" var="actionBean"/>

        <p><f:message key="reservation.list.allreservations"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="reservation.customer"/></th>
                <th><f:message key="reservation.trip"/></th>
                <th><f:message key="reservation.excursions"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.reservation}" var="reservation">
                <tr>
                    <td>${reservation.id}</td>
                    <td><c:out value="${reservation.customer.lastName}"/></td>
                    <td><c:out value="${reservation.trip.destination}"/></td>

                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean" event="edit"><s:param name="reservation.id" value="${reservation.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean">
                            <s:hidden name="reservation.id" value="${reservation.id}"/>
                            <s:submit name="delete"><f:message key="reservation.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean">
            <fieldset><legend><f:message key="reservation.list.newreservation"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="reservation.list.create"/></s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>