<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<title>検定申込み窓口簡略化システム</title>--><title>学生用</title>
<link href="style_moshikomi.css" rel="stylesheet" type="text/css" />
<link href="mosikomidesignstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	int backflg = (int) request.getAttribute("backflg");
	
	String sno = "";
	String gno = (String)session.getAttribute("gno");
	String menjuken = "";
	
	// 12月19日追加
	String errflg = (String)request.getAttribute("errflg");
	String message = "<p style=\"text-align:center\">申込みに必要な情報を入力してください</p>";
	if(errflg.equals("1")){
		message = "<p style=\"text-align:center; color:red\">未入力の項目があります</p>";
	}
	
	if(backflg == 0){
		sno = (String) request.getAttribute("sno");
	}else if(backflg == 1){
		sno = (String) request.getAttribute("sno");
		gno = (String) session.getAttribute("gno"); // 12月19日に変更
		menjuken = (String) request.getAttribute("menjuken");
	}
%>
<div id="container">
	<h1>検定申込み窓口簡略化システム</h1>
	<div id="header">	
		<div id="nav">
		<ul>
			<li style = "font-size:23px;list-style: none;"><b>Hello<br/><%= gno %></b></li>
			<li><a href="./GHome">ホーム</a></li>
			<li><a href="./GSiken">試験一覧</a></li>
			<li><a href="https://192.168.54.212/r01JC21Login/msLogout?id=1013">ログアウト</a></li>
		</ul>
	</div>
	<div id="contents">
	<h2>基本情報技術者試験午前免除修了試験用<br />申込み画面</h2>
	<form method="post" action="./Kakunin">
		<%= message %>
		<p class = "s"><span>※</span>は必須です。</p>
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
				<table border ="1" align ="left" cellpadding ="10"><!--本文-->
				<tr>
				<th>学籍番号</th><td><%= gno %></td>
				</tr>
				<tr>
				<th class= "required">受験番号</th><td><input type="text" name="menjuken" pattern="^[0-9A-Za-z]+$" title="半角英数のみ" style="font-size: 28px"value=<%= menjuken %>></input></td>
				</tr>
				</table>
			</div>
			</div>
			<br/>
			<div align="right">
				<input type="submit" value="確認画面へ" style="font-size: 20px"></input>	
			</div>
		</div>
		<input type="hidden" name="sno" value=<%= sno %>>
		<input type="hidden" name="menjo" value="-">
		<input type="hidden" name="henko" value="-">
		<input type="hidden" name="year" value="-">
		<input type="hidden" name="month" value="-">
		</form>
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