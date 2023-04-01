<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<body>

<h3>Information for all employees</h3>
<br>
<security:authorize access="hasRole('HR')">
<input type="button" value="salary" onclick="window.location.href = 'hr_info' ">
Only for hr staff.
</security:authorize>

<br>
<security:authorize access="hasRole('MANAGER')">
<input type="button" value="performance" onclick="window.location.href = 'manager_info' ">
Only for managers.
</security:authorize>
</body>
</html>
