<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_moshikomi.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	int backflg = (int) request.getAttribute("backflg");
	
	String sno = "";
	String gno = (String)session.getAttribute("gno");
	String menjo = "";
	String henko = "";
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
		if(henko.equals("SGを受験する")){
			cc1 = "Checked";
		}else{
			cc2 = "Checked";
		}
	}else{
		cc1 = "Checked";
	}
%>
<div id="container">
	<h1>検定申込み窓口簡略化システム</h1>
	<div id="header">	
		<div id="nav">
		<h3>メニュー</h3>
			<ul>
  			<li><a href="./GHome">ホーム</a></li>
  			<li><a href="./GSiken">試験一覧</a></li>
  			<li><a href="./Login">ログアウト</a></li>
			</ul>
		</div>
	</div>
	<div id="contents">
	<h2>情報処理技術者試験<br />申込み画面</h2>
			<form  method="get" action="./Kakunin">
			<p style="text-align:center">申込みに必要な情報を入力してください</p>
			<div align="center"/>
		<div id="empty">
			<div align="left">
			<div id="a">
			<table border ="1" align ="center" cellpadding ="10"><!--本文-->
				<tr>
				<th>学籍番号</th><th><%= gno %></th>
				</tr>
				<tr>
				<th>一部免除申請番号</th><th><input type="number" name="menjo" style="font-size: 28px" value=<%= menjo %>></input></th>
				</tr>
				<tr>
				<th>FE受験から変更する</th><th><input type="radio" name="henko" value="SGを受験する" <%= cc1 %>/>SGを受験する<input type="radio" name="henko" value="返金を希望する" <%= cc2 %>/>返金を希望する</th>
				</tr>
				</table>
			</div>
			</div>
			<br/>
			<div align="right">
				<input type="submit" value="確認画面へ" style="font-size: 20px" ></input>	
		</div>
		</div>
		<input type="hidden" name="sno" value=<%= sno %>></input>
		<input type="hidden" name="menjuken" value="-"></input>
		<input type="hidden" name="year" value="-"></input>
		<input type="hidden" name="month" value="-"></input>
		</form>
	</div>
	<div id="footer"><!--フッター-->
		<i id="copyright"><small>Copyright © 2019-2020 Tohoku Computer College All Rights Reserved.</small></i>
	</div>
	
</div>

</body>
</html>