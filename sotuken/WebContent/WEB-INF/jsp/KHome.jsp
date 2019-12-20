<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_khome.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%
	List<String[]> list = (List<String[]>)request.getAttribute("list");
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
			<li><a href="./Login">ログアウト</a></li>
		</ul>
	</div>
	<div id="contents">
		<h2>受験者一覧</h2>
		<form method="get" action="./Search_KHome">
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
				<table border ="1" align ="left" cellpadding="10"><!--本文-->
				<caption><!--テーブル名--></caption>
					<tr>
						<th>学籍番号</th><td><input type="number" maxlength="6" name="gno" style="font-size: 28px"></td>
					</tr>
					<tr>
						<th>試験名</th><td>
							<select name="sno" style="font-size: 28px">
							<option value="">未選択</option>
							<option value="S001">情報処理技術者試験</option>
							<option value="S002">基本情報技術者午前免除修了試験</option>
							<option value="S003">ITパスポート試験</option>
							<option value="S004">Javaプログラミング能力認定試験 1級</option>
							<option value="S005">Javaプログラミング能力認定試験 2級</option>
							<option value="S006">Javaプログラミング能力認定試験 3級</option>
							<option value="S009">OracleMaster Silver オンライン試験</option>
							<option value="S010">OracleMaster Silver VUE会場試験</option>
							<option value="S007">OracleMaster Bronze オンライン試験</option>
							<option value="S008">OracleMaster Bronze VUE会場試験</option>
							</select>
						</td>
					</tr>
				</table>
			</div>
			</div>
			</br>
			<div align="right">
			<div id="kensaku">
			<button type="submit" name="reset" value="reset" style="font-size: 20px; width: 100px; heght: 40px;">リセット</button>
			<button type="submit" name="reset" value="noreset" style="font-size: 20px; width: 80px; heght: 40px;">検索</button>
			</div>
			</div>
		</div>
		</div>
		</br>
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
			<h4 style="text-align: left">申込みと入金を完了し、受験を控えている学生のリスト</h4>
				<table border ="1" align ="left" cellpadding="10"><!--本文-->
				<caption><!--テーブル名--></caption>
					<tr>
						<th>試験名</th><th>学籍番号</th><th>受験番号</th><th>バウチャ期限</th><th>試験日</th>
					</tr>
					<%
					for (String[] s : list) {
					%>
					<tr>
						<td><%= s[1] %></td><td><%= s[2] %></td><td><%= s[3] %></td><td><%= s[4] %></td><td><%= s[5] %></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			</div>
		</div>
		</div>
	</form>
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