<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/EmpLoginController.html"  method="post">
		<table>
			<tr>
				<td>username</td>
				<td><input type="text" name="userName"></td>
			</tr>
			
			<tr>
				<td>password</td>
				<td><input type="password" name="password"></td>
			</tr>
			
			<tr>
				<td>realname</td>
				<td><input type="text" name="realName"></td>
			</tr>
			<tr>
				<td colspan="2" ><input type="submit" name="submit"></td>
			</tr>
		
		</table>
	
	</form>





</body>
</html>