<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	$(function(){
		$("#button1").click(
			function(){
				var sendData = $("#loginform").serialize();
			
				$.ajax(
					{
						type:"post",
						url:"login2.action",
						data:sendData,
						success:function(backData){
							if(backData.key='success'){
								alert("登陆成功");
							}else if(backData.key='fail'){
								alert("登录失败");
							}
						}
					}		
				);
				
			}		
		);
	});

</script>
</head>

<body>
<form id="loginform">
	 用户名:<input type="text" name="e_name" id="e_name"><br><br>
	 密&nbsp;&nbsp;&nbsp;码:<input type="password" name="e_pass" id="e_pass"><br>
	 <input type="button" name="button1" id="button1" value="登录">
	</form>
</body>
</html>