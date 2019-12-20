<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_jukenkakunin.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<% 
	String[] list = (String[]) request.getAttribute("list");
%>


<div id="container" />
	<h1>検定申込み窓口簡略化システム</h1>
	<div id="header" />	
		<div id="nav">
		<h3>メニュー</h3>
			<ul>
  			<li><a href="./GHome">ホーム</a></li>
  			<li><a href="./GSiken">試験一覧</a></li>
  			<li><a href="https://192.168.54.212/r01JC21Login/msLogout?id=1111">ログアウト</a></li>
			</ul>
		</div>
	<div id="contents">
	<h2>受験後処理確認</h2>
	<form  method="get" action="./Message3">
			<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
			<table border ="1" align ="center" cellpadding="10"><!--本文-->
				<tr>
				<th>試験名</th><th>受験番号</th><th>バウチャー期限</th><th>試験日</th>
				</tr>
				<tr>
				<td><%= list[1] %></td><td><%= list[2] %></td><td><%= list[3] %></td><td><%= list[4] %></td>
				</tr>
				</table>
			</div>
			</div>
			<input type="hidden" name="nno" value=<%= list[0] %>></input>
			<br />
			<P style="text-align: center">以上の試験を受験し終えた場合は受験後処理ボタンをクリックしてください。</P>
			<br/>
				<div style="float: left"><button type="submit" name="jukengo" value="back" style="font-size: 20px">戻る</button></div>
				<div style="text-align: right"><button type="submit" name="jukengo" value="go" style="font-size: 20px">受験後処理</button></div>　
			<br/>
		
		</div>
		</div>
	</form>
	<br/>
	<br/>
	<br/>
	<br/>
	<div id="footer"><!--フッター-->
		<i id="copyright"><small>Copyright © Tohoku Computer College All Rights Reserved.</small></i>
	</div>
	
</div>
</body>
</html>