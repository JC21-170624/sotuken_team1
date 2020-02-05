<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<title>検定申込み窓口簡略化システム</title>--><title>学生用</title>
<link href="style_gsiken.css" rel="stylesheet" type="text/css" />
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
		<h2>試験一覧</h2>
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
				<%
					List<String[]> list = (List<String[]>) request.getAttribute("list");
					int loopCount = 0; /* for文の中で、今何回目のループなのか調べている変数 */
					String cc = "";
				%>
				<form method="post" action="./Mosikomi">
				<table border ="1" align ="left"  cellpadding ="10"><!--本文-->
					<tr>
						<th></th><th>試験名</th><th>受け付け期間</th><th>試験日</th><th>料金</th><!--<th></th>で増やす-->
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
						<td><input type="radio" value ="<%=s[0] %>" name="sno" <%= cc %>></input></td>
						<td><%=s[1]%></td> <td><%=s[2]%></td> <td><%=s[3]%></td> <td><%=s[4]%>円</td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			</div>
			</br>
			<div align="right">
				<input type ="submit" value ="申込み画面へ" style="font-size: 20px"></input>
			</div>
			</form>
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