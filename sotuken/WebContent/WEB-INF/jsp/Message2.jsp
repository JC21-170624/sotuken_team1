<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_message2.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%
	String message = (String) request.getAttribute("message");
%>

<div id="container">
	<div id="header">
		<h1>検定申込み窓口簡略化システム</h1>
	</div>
	<div id="nav">
		<h3>メニュー</h3>
		<ul>
			<li><a href="./KHome">ホーム</a></li>
			<li><a href="./KSiken">試験一覧</a></li>
			<li><a href="./Mkanri">申込み管理</a></li>
			<li><a href="http://192.168.54.191:8080/KenteiMosikomi/KLogin">ログアウト</a></li>
		</ul>
	</div>
	<div id="contents">
		<h2>メッセージ</h2>
			<div align="center">
				<div id="empty">
					<p style="font-size: 30px;"><%= message %></p>
				</div>
			</div>
	</div>
	<br/>
	<br/>
	<br/>
	<br/>
	<div id="footer"><!--フッター-->
		<i id="copyright"><small>Copyright © 2019-2020 Tohoku Computer College All Rights Reserved.</small></i>
	</div>
</div>
</body>
</html>