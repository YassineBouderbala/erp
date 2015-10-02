<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form action="j_spring_security_check" method="post">
		<table>
			<tr>
				<td>Login</td>
			</tr>
			<tr>
				<td><input type="text" name="j_username"></td>
			</tr>
			<tr>
				<td><input type="password" name="j_password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"></td>
				<td><a href="<c:url value="/j_spring_security_logout" />">Logout</a></td>
			</tr>
		</table>
	</form>
</body>
</html>