<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>User 1</title>
</head>
<body>

<h2>Enter User1 information</h2>
<form:form action="${pageContext.servletContext.contextPath}/addUser1">
  <table>
    <tr>
      <td>Login</td>
      <td>
        <input type="text" name="login" value="" size="20" autocomplete="off">
      </td>
    </tr>
    <tr>
      <td>Password</td>
      <td>
        <input type="text" name="password" value="" size="20" autocomplete="off">
      </td>
    </tr>
    <tr>
      <td>Level</td>
      <td>
        <input type="text" name="level" value="" size="20" autocomplete="off">
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="<spring:message text="Submit"/>">
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>