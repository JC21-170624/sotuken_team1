<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_moshikomi.css" rel="stylesheet" type="text/css" />
<link href="mosikomidesignstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	int backflg = (int) request.getAttribute("backflg");
	
	String sno = "";
	String gno = (String)session.getAttribute("gno");
	String menjo = "";
	String henko = "";
	String cc0 = "Checked";
	String cc1 = "";
	String cc2 = "";
	
	if(backflg == 0){
		sno = (String) request.getAttribute("sno");
	}else if(backflg == 1){
		sno = (String) request.getAttribute("sno");
		gno = (String) request.getAttribute("gno");
		menjo = (String) request.getAttribute("menjo");
		henko = (String) request.getAttribute("henko");
	}
	
	if(henko.length() != 0){
		if(henko.equals("no")){
			cc0 = "Checked";
		}else if(henko.equals("sg")){
			cc1 = "Checked";
		}else{
			cc2 = "Checked";
		}
	}else{
		cc0 = "Checked";
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
	<h2>情報処理技術者試験<br />申込み画面</h2>
			<form  method="post" action="./Kakunin">
			<p style="text-align:center">申込みに必要な情報を入力してください</p>
			<div align="center"/>
		<div id="empty">
			<div align="left">
			<div id="a">
			<table border ="1" align ="center" cellpadding ="10"><!--本文-->
				<tr>
				<th>学籍番号</th><td><%= gno %></td>
				</tr>
				<tr>
				<th>一部免除申請番号</th><td><input type="text" name="menjo" pattern="^[0-9A-Za-z]+$" title="半角英数のみ" style="font-size: 28px" value=<%= menjo %>></input></td>
				</tr>
				<tr>
				<th>FE受験から変更する場合</th><td><input type="radio" name="henko" value="no" <%= cc0 %>/>選択しない<input type="radio" name="henko" value="sg" <%= cc1 %>/>SGを受験する<input type="radio" name="henko" value="mo" <%= cc2 %>/>返金を希望する</td>
				</tr>
				</table>
			</div>
			</div>
			<br/>
			<div align="right">
				<input type="submit" value="確認画面へ" style="font-size: 20px" ></input>	
			</div>
		</div>
		</div>
		<input type="hidden" name="sno" value=<%= sno %>></input>
		<input type="hidden" name="menjuken" value="-"></input>
		<input type="hidden" name="year" value="-"></input>
		<input type="hidden" name="month" value="-"></input>
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