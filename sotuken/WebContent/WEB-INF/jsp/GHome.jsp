<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<title>検定申込み窓口簡略化システム</title>--><title>学生用</title>
<link href="style_ghome.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	String gno = (String)session.getAttribute("gno");
%>
<div id="container">
	<div id="header">
		<div id= "title"><h1>検定申込み窓口簡略化システム</h1></div>
	</div>
	<div id="nav">
		<ul>
			<li style = "font-size:23px;list-style: none;"><b>Hello<br/><%= gno %></b></li>
			<li><a href="./GHome">ホーム</a></li>
			<li><a href="./GSiken">試験一覧</a></li>
			<li><a href="https://192.168.54.212/r01JC21Login/msLogout?id=1013">ログアウト</a></li>
		</ul>
	</div>
	<div id="contents">
		<h2>ホーム</h2>
		<form method="post" action="./JukenKakunin">
		<div align="center">
		<div id="empty1">
		<div align="left">
		<div id="empty2">
			<div align="left">
			<div id="a">
				<%
					List<String[]> list = (List<String[]>) request.getAttribute("list");
					List<String[]> list2 = (List<String[]>) request.getAttribute("list2");
					int loopCount = 0; /* for文の中で、今何回目のループなのか調べている変数 */
					String cc = "";
				%>
				<table border ="1" align ="left" cellpadding="10"><!--本文-->
				<p>支払い後試験一覧</p>
					<tr>
						<th>/</th><th>試験名</th><th>受験番号</th><th>バウチャー期限</th><th>試験日</th>
					</tr>
					<%
						for (String[] s : list) {
							cc = "";
							loopCount = loopCount + 1;
							if(loopCount == 1){
								cc = "Checked";
							}
					%>
					<tr>
						<td><input type="radio" value ="<%=s[4] %>" name="nno" <%= cc %>></input></td><td><%=s[0] %></td><td><%=s[1] %></td><td><%=s[2] %></td><td><%=s[3] %></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			<br />
				<div align="right">
						<input type ="submit" value ="受験後処理" style="font-size: 20px"></input>
				</div>
			</div>
		</div>		
			<br />
		<div id="empty3">
			<div align="left">
			<div id="b">
				<table border ="1" align ="left" cellpadding="10"><!--本文-->
				<p>支払い前試験一覧</p>
					<tr>
						<th>試験名</th><th>受付期間</th><th>料金</th>
					</tr>
					<%
						for (String[] s : list2) {
					%>
					<tr>
						<td><%=s[0] %></td><td><%=s[1] %></td><td><%=s[2] %></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			</div>
		</div>
		</div>
		</div>
		</div>
		<%
			if(loopCount == 0){
		%>
			<input type="hidden" name="nno" value=""></input>
		<%		
			}
		%>
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