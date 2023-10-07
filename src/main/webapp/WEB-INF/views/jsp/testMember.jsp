<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.test_key {
	width: 100px;
	display: inline-block;
}
</style>

<title>test member</title>
</head>
<body>

	<h2>/member/join</h2>
	<form action="/member/rest/join" method="post">
		<span class="test_key">id:</span><input type="text" name="memberId" /><br />
		<span class="test_key">pw:</span><input type="text" name="memberPw" /><br /> 
		<span class="test_key">name:</span><input type="text" name="memberName" /><br /> 
		<span class="test_key">email:</span><input type="text" name="memberEmail" /><br />

		<button type="submit">전송</button>
		<button type="reset">리셋</button>
	</form>
	<br/>
	<br/>
	
</body>
</html>