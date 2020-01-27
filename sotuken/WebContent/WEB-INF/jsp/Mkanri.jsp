<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_mkanri.css" rel="stylesheet" type="text/css" />
<link href="style_mkanridesign.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%
	List<String[]> list = (List<String[]>) request.getAttribute("list");
	int loopCount = 0;  /* for文の中で、今何回目のループなのか調べている変数 */
	String cc = "";     /* tableの最も上の行のラジオボタンが、始めからチェックされている状態にするために必要な変数 */
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
		<h2>申込み管理</h2>
		<form method="get" action="./Search_Mkanri">
		<div align="center">
		<div id="empty1">
			<div align="left">
			<div id="a">
				<table border ="1" align ="left" cellpadding="10"><!--本文-->
				<caption><!--テーブル名--></caption>
					<tr>
						<th>学籍番号</th><td><input type="number" maxlength="6" name="gno" style="font-size: 28px" /></td>
					</tr>
					<tr>
						<th>試験名</th><td>
							<select name="sno" style="font-size: 28px">
							<option value="">未選択</option>
							<option value="S001">情報処理技術者試験(ITパスポート試験を除く)</option>
							<option value="S002">基本情報技術者午前免除修了試験</option>
							<option value="S003">ITパスポート試験</option>
							<option value="S004">Javaプログラミング能力認定試験 1級</option>
							<option value="S005">Javaプログラミング能力認定試験 2級</option>
							<option value="S006">Javaプログラミング能力認定試験 3級</option>
							<option value="S007">OracleMaster オンライン試験</option>
							<option value="S008">OracleMaster VUE会場試験</option>
							</select>
						</td>
					</tr>
				</table>
			</div>
			</div>
			<br/>
			<div align="right">
			<div id="kensaku">
			<button type="submit" name="reset" value="noreset" style="font-size: 20px">検索</button>
			<button type="submit" name="reset" value="reset" style="font-size: 20px">リセット</button>
			</div>
			</div>
		</div>
		</div>
		</form>
		<br />
		<form method="get" action="./Nyukin">
		<div align="center">
		<div id="empty2">
			<div align="left">
			<div id="b">
				<table border ="1" align ="left" cellpadding="6"><!--本文-->
				<caption><!--テーブル名--></caption>
					<tr>
						<th>/</th><th>試験名</th><th>学籍番号</th><th>申込み日</th><th>一部免除申請番号</th><th>返金希望の有無</th><th>受験番号(FE午前修了試験用)</th><th>予定受験日</th><th>入金の有無</th>
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
						<td><input type="radio" name="mno" value="<%= s[0] %>" <%= cc %>/></td><td><%= s[2] %></td><td><%= s[1] %></td><td><%= s[3] %></td><td><%= s[4] %></td>
						<td><%= s[5] %></td><td><%= s[6] %></td><td><%= s[7] %></td><td><%= s[8] %></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			</div>
			<br />
			<div align= "right">
			<div id= "toroku">
			<button type="submit" name="mkanri" value="money" style="font-size: 20px">入金・登録</button>
			<button type="submit" name="mkanri" value="delete" style="font-size: 20px">削除</button>
			</div>
			</div>
		</div>
		</div>
		</form>
		<%
			if(loopCount == 0){
		%>
			<input type="hidden" name="mno" value="">
		<%		
			}
		%>
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