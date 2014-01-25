<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="index.trips.link">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsForReservationActionBean" var="actionBean"/>
        <script>
            $(window).on('load', function() {
                //$("select[name='excursionsIds']").attr('data-selected-text-format', 'count>2');
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
        <c:forEach items="${actionBean.trips}" var="trip" varStatus="loop">
            <div style="vertical-align: middle;">
                <table class="table table-bordered table-striped" style="width: 700px;">
                    <tr>
                        <td rowspan="2" style="width: 179px;">
                            <img alt="100%x180" style="height: 180px; width: 171px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKsAAAC0CAYAAADraNxXAAAFeUlEQVR4Xu3YvUscURiF8buFgtoq2oqtNoIo/vtWKoidWIudrJ0gfiTcCXczTHZmN+menMcqupnsed/z82Z2ZvP5/Efxyw0ANjATK6AlI3YbEKsQMBsQK6Yqg4pVA5gNiBVTlUHFqgHMBsSKqcqgYtUAZgNixVRlULFqALMBsWKqMqhYNYDZgFgxVRlUrBrAbECsmKoMKlYNYDYgVkxVBhWrBjAbECumKoOKVQOYDYgVU5VBxaoBzAbEiqnKoGLVAGYDYsVUZVCxagCzAbFiqjKoWDWA2YBYMVUZVKwawGxArJiqDCpWDWA2IFZMVQYVqwYwGxArpiqDilUDmA2IFVOVQcWqAcwGxIqpyqBi1QBmA2LFVGVQsWoAswGxYqoyqFg1gNmAWDFVGVSsGsBsQKyYqgwqVg1gNiBWTFUGFasGMBsQK6Yqg4pVA5gNiBVTlUHFqgHMBsSKqcqgYtUAZgNixVRlULFqALMBsWKqMqhYNYDZgFgxVRlUrBrAbECsmKoMKlYNYDYgVkxVBhWrBjAbECumKoOKVQOYDYgVU5VBxaoBzAbEiqnKoGLVAGYDYsVUZVCxagCzAbFiqjKoWDWA2YBYMVUZVKwawGxArJiqDCpWDWA2IFZMVQYVqwYwGxArpiqDilUDmA1EY/3+/i7X19fl7e2tXF5elq2trfLw8FCen5//KHB/f7+cnJx0P1923TqNj1339PRUHh8fF//E8fFxOTg46L6fz+fl/v6+e8/61X9tnff8n/5OLNbPz89ydXVVvr6+ymw2W2Dtl7sM1zrXLQMydl37+fb2djk/Py93d3fl9fW1y7OxsdFl7L9Wf7Hq36uvpX1FYu3DqYWPYW2n7OHhYTk6OipT17UTcGdnp8PUrq0n4e7u7uIXY/h+7bq9vb3u5O5f9/Hx0Z247f3bCZx6usZivb29Laenp91J1r8NaKdVg1lPsHaS1Z9NXddOxYuLi3Jzc7M4EaeumzpZX15elmJteD1ZgzYwde85PFVX3R7U11fdIoy939h1wwztZBVrENI26hieVR+gpl6vp2v9r73/gWzq/f7lNkCsYu2eBvRPyPbBZriaMawNXr11eH9//+OT+9TTh+F9af1+c3PTe9be8iPvWVedrMPTbh2sDWJ9unB2dtbdC9c/9z+5L8M6dbK2D2Y+DfjVgFgHz1nrUlbdG/7NCbnO89nhc9b+f/M+Z/19VERjDbzzQY8sVnR9WeHFmtU3elqxouvLCi/WrL7R04oVXV9WeLFm9Y2eVqzo+rLCizWrb/S0YkXXlxVerFl9o6cVK7q+rPBizeobPa1Y0fVlhRdrVt/oacWKri8rvFiz+kZPK1Z0fVnhxZrVN3pasaLrywov1qy+0dOKFV1fVnixZvWNnlas6Pqywos1q2/0tGJF15cVXqxZfaOnFSu6vqzwYs3qGz2tWNH1ZYUXa1bf6GnFiq4vK7xYs/pGTytWdH1Z4cWa1Td6WrGi68sKL9asvtHTihVdX1Z4sWb1jZ5WrOj6ssKLNatv9LRiRdeXFV6sWX2jpxUrur6s8GLN6hs9rVjR9WWFF2tW3+hpxYquLyu8WLP6Rk8rVnR9WeHFmtU3elqxouvLCi/WrL7R04oVXV9WeLFm9Y2eVqzo+rLCizWrb/S0YkXXlxVerFl9o6cVK7q+rPBizeobPa1Y0fVlhRdrVt/oacWKri8rvFiz+kZPK1Z0fVnhxZrVN3pasaLrywov1qy+0dOKFV1fVnixZvWNnlas6Pqywos1q2/0tGJF15cVXqxZfaOnFSu6vqzwYs3qGz2tWNH1ZYUXa1bf6GnFiq4vK7xYs/pGTytWdH1Z4cWa1Td6WrGi68sKL9asvtHTihVdX1b4n4Ds4UXrUfTOAAAAAElFTkSuQmCC">
                        </td>
                        <td style="height: 56px;">
                            <h4><c:out value="${trip.destination}"/></h>
                        </td>
                    </tr>
                    <tr>
                        <td style="position: relative;">
                            <div style="position: absolute; bottom: 0; right: 0; padding: 0 8px 8px 0;">
                                <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsForReservationActionBean">
                                    <s:hidden name="customer" value="admin"/>
                                    <s:hidden name="trip" value="${trip.id}"/>
                                    <s:select name="excursions" multiple="true" class="selectpicker-mult">
                                        <s:options-collection id="r3" collection="${actionBean.excursions[loop.index]}" value="id" label="description"/>
                                    </s:select>
                                    <button name="reserve" type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-shopping-cart"></span> <f:message key="tripsforreservation.reserve"/></button>
                                </s:form>
                            </div>
                        </td>
                    </tr>

                </table>
            </div>
        </c:forEach>
    </s:layout-component>
</s:layout-render>
