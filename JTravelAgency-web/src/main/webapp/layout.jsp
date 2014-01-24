<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
            <script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.sk.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-select.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" />
            <s:layout-component name="header"/>
        </head>
        <body>
            <header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="${pageContext.request.contextPath}"><f:message key="index.welcome"/></a>
                    </div>
                    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
                        <ul class="nav navbar-nav">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <li class="nav-trip"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean"><f:message key="index.trips.link"/></s:link></li>
                                <li class="nav-customer"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean"><f:message key="index.customers.link"/></s:link></li>
                                <li class="nav-excursion"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean"><f:message key="index.excursions.link"/></s:link></li>
                                <li class="nav-reservation"><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean"><f:message key="index.reservations.link"/></s:link></li>
                            </sec:authorize>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <span class="glyphicon glyphicon-shopping-cart"></span>
                                    </sec:authorize>
                                    <sec:authentication property="principal.username" />
                                </a>
                            </li>
                            <li>
                                <div>
                                    <button type="submit" class="btn btn-success" onclick="window.location.href = '${pageContext.request.contextPath}/j_spring_security_logout'">Logout</button>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>
            <div class="container">
                <div id="notice"><s:messages/><s:errors/></div>
                <s:layout-component name="body"/>
            </div>
        </body>
    </html>
</s:layout-definition>
