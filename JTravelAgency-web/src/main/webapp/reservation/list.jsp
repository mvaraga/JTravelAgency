<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="index.reservations.link">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean" var="actionBean"/>
        <script>
            $(document).ready(function() {
                $("li.nav-reservation").addClass("active");
            });
        </script>
        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean" class="new-form">
            <fieldset><legend><f:message key="reservation.list.newreservation"/></legend>
                <%@include file="form.jsp"%>
                <div class="new-form-input">
                    <s:submit name="add" class="btn btn-default"><f:message key="form.submit"/></s:submit>
                    </div>
                </fieldset>
        </s:form>

        <div class="block-title"><f:message key="reservation.list.allreservations"/></div>
        <table class="table table-bordered table-striped">
            <tr>
                <th>id</th>
                <th><f:message key="reservation.customer"/></th>
                <th><f:message key="reservation.trip"/></th>
                <th><f:message key="reservation.excursions"/></th>
                <th><f:message key="form.action"/></th>
            </tr>
            <c:forEach items="${actionBean.reservations}" var="reservation">
                <tr>
                    <td>${reservation.id}</td>
                    <td>
                        <c:out value="${reservation.customer.firstName}"/>
                        <c:out value="${reservation.customer.lastName}"/>
                    </td>
                    <td><c:out value="${reservation.trip.destination}"/></td>
                    <td>
                        <c:forEach items="${reservation.excursions}" var="excursion">
                            <c:out value="${excursion.description}, "/>
                        </c:forEach>
                    </td>
                    <td>
                        <div class="list-action-edit">
                            <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean" event="edit" class="btn btn-default btn-sm">
                                <s:param name="reservation.id" value="${reservation.id}"/><span class="glyphicon glyphicon-pencil"></span> <f:message key="form.edit"/>
                            </s:link>
                            </div>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean">
                            <s:hidden name="reservation.id" value="${reservation.id}"/>
                            <button name="delete" type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> <f:message key="form.delete"/></button>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>