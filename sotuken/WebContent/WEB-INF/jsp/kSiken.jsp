<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--<title>検定申込み窓口簡略化システム</title>--><title>管理者用</title>
<link href="style_ksiken.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
			<li><a href="./KLogout">ログアウト</a></li>
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
				<form method="get" action="./Mosikomi">
				<table border ="1" align ="left"  cellpadding ="10"><!--本文-->
					<tr>
						<th>試験名</th><th>受け付け期間</th><th>試験日</th><th>料金</th><!--<th></th>で増やす-->
					</tr>
					<%
						for (String[] s : list) {
					%>
					<tr>
						<td><%=s[0]%></td> <td><%=s[1]%></td> <td><%=s[2]%></td> <td><%=s[3]%>円</td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			</div>
			</br>
			</form>
		</div>
		</div>
	</div>
	</br>
	</br>
	</br>
	</br>
	<div id="footer"><!--フッター-->
		<i id="copyright"><small>Copyright © 2019-2020 Tohoku Computer College All Rights Reserved.</small></i>
	</div>
</div>
</body>
</html>