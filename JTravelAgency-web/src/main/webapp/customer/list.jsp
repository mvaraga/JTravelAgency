<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="customer.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean" var="actionBean"/>
        <script>
            $(document).ready(function() {
                $("li.nav-customer").addClass("active");
            });
        </script>
        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean" class="new-form">
            <fieldset><legend><f:message key="customer.list.newcustomer"/></legend>
                <%@include file="form.jsp"%>
                <div class="new-form-input">
                    <s:submit name="add" class="btn btn-default"><f:message key="customer.list.submit"/></s:submit>
                    </div>
                </fieldset>
        </s:form>

        <div class="block-title"><f:message key="customer.list.allcustomers"/></div>
        <table class="table table-bordered table-striped">
            <tr>
                <th>id</th>
                <th><f:message key="customer.firstName"/></th>
                <th><f:message key="customer.lastName"/></th>

                <th><f:message key="customer.list.action"/></th>
            </tr>
            <c:forEach items="${actionBean.customers}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td><c:out value="${customer.firstName}"/></td>
                    <td><c:out value="${customer.lastName}"/></td>

                    <td>
                        <div class="list-action-edit">
                            <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean" event="edit" class="btn btn-default btn-sm">
                                <s:param name="customer.id" value="${customer.id}"/><span class="glyphicon glyphicon-pencil"></span> <f:message key="customer.list.action.edit"/></s:link>
                            </div>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean">
                            <s:hidden name="customer.id" value="${customer.id}"/>
                            <button name="delete" type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> <f:message key="customer.list.action.delete"/></button>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>