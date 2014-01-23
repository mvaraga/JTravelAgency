<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<s:layout-render name="/layout.jsp" titlekey="index.excursions.link">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" var="actionBean"/>
        <script>
            $(document).ready(function() {
                $("li.nav-excursion").addClass("active");
            });
        </script>
        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" class="new-form">
            <fieldset><legend><f:message key="excursion.list.newexcursion"/></legend>
                <%@include file="form.jsp"%>
                <div class="new-form-input">
                    <s:submit name="add" class="btn btn-default"><f:message key="form.submit"/></s:submit>
                    </div>
                </fieldset>
        </s:form>

        <div class="block-title"><f:message key="excursion.list.allexcursions"/></div>
        <table class="table table-bordered table-striped">
            <tr>
                <th>id</th>
                <th><f:message key="excursion.excursionDate"/></th>
                <th><f:message key="excursion.description"/></th>
                <th><f:message key="excursion.price"/></th>
                <th><f:message key="excursion.trip"/></th>
                <th><f:message key="form.action"/></th>
            </tr>
            <c:forEach items="${actionBean.excursions}" var="excursion">
                <tr>
                    <td>${excursion.id}</td>
                    <td><joda:format value="${excursion.excursionDate}" pattern="dd.MM.yyyy HH:mm" /></td>
                    <td><c:out value="${excursion.description}"/></td>
                    <td><c:out value="${excursion.price}"/></td>
                    <td><c:out value="${excursion.trip.destination}"/></td>
                    <td>
                        <div class="list-action-edit">
                            <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" event="edit" class="btn btn-default btn-sm">
                                <s:param name="excursion.id" value="${excursion.id}"/><span class="glyphicon glyphicon-pencil"></span> <f:message key="form.edit"/>
                            </s:link>
                        </div>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean">
                            <s:hidden name="excursion.id" value="${excursion.id}"/>
                            <button name="delete" type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> <f:message key="form.delete"/></button>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>