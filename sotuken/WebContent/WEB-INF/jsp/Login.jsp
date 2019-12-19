<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_login.css" rel="stylesheet" type="text/css" />
<link href="logindesignstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	String inschk = (String)request.getAttribute("inschk");
	String message = "";
	
	if(inschk.equals("1")){
		message = "ログイン処理に失敗しました。申し訳ありませんが時間をおいてから再度お試しください。";
	}
%>
<div id="container">

		<h1>検定申込み窓口簡略化システム</h1>

	<div id="login">
		<div align="center">
			<p style="font-size: 20px"><%= message %></p>
			<a href="https://192.168.54.212/r01JC21Login/msLoginDirect?id=1111">ログイン</a><br />
			<a href="./KHome">管理者ログイン</a>
		</div>
	</div>
	
</div>
</body>
</html>