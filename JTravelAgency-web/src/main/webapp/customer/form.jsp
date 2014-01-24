<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<script>
    $(document).ready(function() {
        $("#password").attr("type", "password");
        //$("#password").attr("required", '');
    });
</script>
<table>
    <tr>
        <th><s:label for="username" name="customer.userName"/></th>
        <th><s:label for="name" name="customer.firstName"/></th>
        <th><s:label for="surname" name="customer.lastName"/></th>
        <th><s:label for="password" name="customer.password"/></th>
    </tr>
    <tr>
        <td><s:text id="username" name="customer.userName" class="form-control"/></td>
        <td><s:text id="name" name="customer.lastName" class="form-control"/></td>
        <td><s:text id="surname" name="customer.lastName" class="form-control"/></td>
        <td><s:text id="password" name="customer.password" class="form-control"/></td>
    </tr>
</table>