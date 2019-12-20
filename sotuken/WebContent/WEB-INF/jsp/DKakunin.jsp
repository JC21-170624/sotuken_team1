<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_dkakunin.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />

</head>
<body>

<%
	String[] list = (String[])request.getAttribute("list");
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
			<li><a href="./KLogin">ログアウト</a></li>
		</ul>
	</div>
	
	<div id="contents">
		<h2>削除確認画面</h2>
		<form method="get" action="./Message4">
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
				<table border ="1" cellpadding="10">
					<tr>
						<th>試験名</th><th>学籍番号</th><th>申込み日</th><th>一部免除申請番号</th><th>返金の有無</th><th>午前免除修了試験用受験番号</th><th>受験予定年月</th><th>入金の有無</th>
					</tr>
					<tr>
						<td><%= list[9] %></td><td><%= list[1] %></td><td><%= list[3] %></td><td><%= list[4] %></td><td><%= list[5] %></td><td><%= list[6] %></td><td><%= list[7] %></td><td><%= list[8] %></td>
					</tr>
				</table>
			</div>
			</div>
			<br/>
			<p style="text-align: center">上記の申込みの情報を削除してもよろしいですか？</p>
			<br/>
				<div style="float: left"><button type="submit" name="kakunin" value="back" style="font-size: 20px" >いいえ</button></div>
				<div style="text-align: right"><button type="submit" name="kakunin" value="delete" style="font-size: 20px" >はい</button></div>　
			<br/>
			</div>
		</div>
		</div>
		<input type="hidden" name="mno" value=<%= list[0] %>></input>
		</form>
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