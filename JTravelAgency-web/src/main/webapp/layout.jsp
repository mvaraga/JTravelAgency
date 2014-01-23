<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
            <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
            <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/jquery-ui-timepicker-addon.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.css" />
            <script>
                $(function() {
                    $("#datepicker").datetimepicker({dateFormat: "dd.mm.yy"});
                    $("#datepicker2").datetimepicker({dateFormat: "dd.mm.yy"});
                });
            </script>
            <s:layout-component name="header"/>
        </head>
        <body>
            <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/pa165/"><f:message key="index.welcome"/></a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="nav-trip"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean"><f:message key="index.trips.link"/></s:link></li>
                            <li class="nav-customer"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean"><f:message key="index.customers.link"/></s:link></li>
                            <li class="nav-excursion"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean"><f:message key="index.excursions.link"/></s:link></li>
                            <li class="nav-reservation"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean"><f:message key="index.reservations.link"/></s:link></li>
                            <li><a href="/pa165/login.jsp">Login page - temporary</a></li>
                            </ul>
                        </div><!--/.nav-collapse -->
                    </div>
                </div>
                <div class="container">
                <div id="notice"><s:messages/><s:errors/></div>
                <s:layout-component name="body"/>
            </div>
            </body>
        </html>
</s:layout-definition>