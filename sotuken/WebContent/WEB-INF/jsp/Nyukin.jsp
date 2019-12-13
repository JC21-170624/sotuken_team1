<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja" lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>検定申込み窓口簡略化システム</title>
<link href="style_nyukin.css" rel="stylesheet" type="text/css" />
<link href="designstyle.css" rel="stylesheet" type="text/css" />

</head>
<body>

<%
	String[] list = (String[])request.getAttribute("list");
	int bchk = (int)request.getAttribute("bchk");
	int mno = (int)request.getAttribute("mno");
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
		<h2>申込み入金確認</h2>
		<form method="get" action="./Message2">
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
				
				<p>試験名    : <%= list[9] %></p>
				<p>学籍番号: <%= list[1] %></p>
				
				<br/>
				<br/>
				<table border ="1" cellpadding="10">
					<tr>
						<th>受験番号</th><td><input type="text" name="juken_no" style="font-size: 28px" ></td>
					</tr>
				</table>
				<br/>
				<%
					if(bchk == 1){ 
				%>
				<table border ="1" align ="left" cellpadding="10"><!--本文-->
				
					<tr>
						<th>バウチャ期限</th>
						<td>
						<select name="year">
						<option value="-">-</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
						<option value="2029">2029</option>
						<option value="2030">2030</option>
						</select>
						年</td>
						<td><select name="month">
						<option value="-">-</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						</select>
						月</td>
						<td><select name="day">
						<option value="-">-</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
						</select>
						日</td><!--<th></th>で増やす-->
					</tr>
					
				</table>
				<%
					} else {
				%>
				<input type="hidden" name="year" value="-"></input>
				<input type="hidden" name="month" value="-"></input>
				<input type="hidden" name="day" value="-"></input>
				<%
					}
				%>
			</div>
			</div>
			<input type="hidden" name="mno" value=<%= mno %>></input>
		<br/>
				<div style="float: left"><button type="submit" name="kakunin" value="back" style="font-size: 20px" >戻る</button></div>
				<div style="text-align: right"><button type="submit" name="kakunin" value="go" style="font-size: 20px" >入金完了</button></div>　
			<br/>
			</div>
		</div>
		</div>
		</form>
	<div id="footer"><!--フッター-->
		<i id="copyright"><small>Copyright © 2019-2020 Tohoku Computer College All Rights Reserved.</small></i>
	</div>
</div>
</body>
</html>