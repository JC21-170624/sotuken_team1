<%@page import="java.util.*"%>
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
	String ydate = "";
	String[] ccy = {"","","","","",""};
	String[] ccm = {"","","","","","","","","","","",""};
	
	String year = "";
	String month = "";
	
	Calendar cal = Calendar.getInstance();
    int yeari = cal.get(Calendar.YEAR);
    int[] years = {yeari,yeari+1,yeari+2,yeari+3,yeari+4,yeari+5};
	
	if(backflg == 0){
		sno = (String) request.getAttribute("sno");
	}else if(backflg == 1){
		sno = (String) request.getAttribute("sno");
		gno = (String) request.getAttribute("gno");
		ydate = (String) request.getAttribute("ydate");
		year = ydate.substring(0,4);
		int yeari2 = Integer.parseInt(year); // 戻りの遷移で受け取った受験予定年月から年を取得しintに変換
		month = ydate.substring(5);
		
		if(yeari2 == years[0]){
			ccy[0] = "Selected";
		}else if(yeari2 == years[1]){
			ccy[1] = "Selected";
		}else if(yeari2 == years[2]){
			ccy[2] = "Selected";
		}else if(yeari2 == years[3]){
			ccy[3] = "Selected";
		}else if(yeari2 == years[4]){
			ccy[4] = "Selected";
		}else if(yeari2 == years[5]){
			ccy[5] = "Selected";
		}
		
		if(month.equals("1")){
			ccm[0] = "Selected";
		}else if(month.equals("2")){
			ccm[1] = "Selected";
		}else if(month.equals("3")){
			ccm[2] = "Selected";
		}else if(month.equals("4")){
			ccm[3] = "Selected";
		}else if(month.equals("5")){
			ccm[4] = "Selected";
		}else if(month.equals("6")){
			ccm[5] = "Selected";
		}else if(month.equals("7")){
			ccm[6] = "Selected";
		}else if(month.equals("8")){
			ccm[7] = "Selected";
		}else if(month.equals("9")){
			ccm[8] = "Selected";
		}else if(month.equals("10")){
			ccm[9] = "Selected";
		}else if(month.equals("11")){
			ccm[10] = "Selected";
		}else if(month.equals("12")){
			ccm[11] = "Selected";
		}
	}
%>	
<div id="container">
	<div id="header">
		<h1>検定申込み窓口簡略化システム</h1>
	</div>
	<div id="nav">
		<ul>
			<li style = "font-size:23px;list-style: none;"><b>Hello<br/><%= gno %></b></li>
			<li><a href="./GHome">ホーム</a></li>
			<li><a href="./GSiken">試験一覧</a></li>
			<li><a href="https://192.168.54.212/r01JC21Login/msLogout?id=1111">ログアウト</a></li>
		</ul>
	</div>
	<div id="contents">
		<h2>ITパスポート試験<br />申込み画面</h2>
		<form method="post" action="./Kakunin">
		<p style="text-align:center">申込みに必要な情報を入力してください</p>
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
				<table border ="1" align ="left" cellpadding ="10"><!--本文-->
					<tr>
						<th>学籍番号</th>	<td><%= gno %></td>
					</tr>
					<tr>
						<th>受験予定年月</th>	<td><select name ="year" style="font-size: 28px" ><option value ="<%= years[0] %>" <%= ccy[0] %>><%= years[0] %></option>
																	 <option value ="<%= years[1] %>" <%= ccy[1] %>><%= years[1] %></option>
																	 <option value ="<%= years[2] %>" <%= ccy[2] %>><%= years[2] %></option>
																	 <option value ="<%= years[3] %>" <%= ccy[3] %>><%= years[3] %></option>
																	 <option value ="<%= years[4] %>" <%= ccy[4] %>><%= years[4] %></option>
																	 <option value ="<%= years[5] %>" <%= ccy[5] %>><%= years[5] %></option>
												 </select>年
																	 
												<select name ="month" style="font-size: 28px" ><option value ="1" <%= ccm[0] %>>1</option>
																	  <option value ="2" <%= ccm[1] %>>2</option>
																	  <option value ="3" <%= ccm[2] %>>3</option>
																	  <option value ="4" <%= ccm[3] %>>4</option>
																	  <option value ="5" <%= ccm[4] %>>5</option>
																	  <option value ="6" <%= ccm[5] %>>6</option>
																	  <option value ="7" <%= ccm[6] %>>7</option>
																	  <option value ="8" <%= ccm[7] %>>8</option>
																	  <option value ="9" <%= ccm[8] %>>9</option>
																	  <option value ="10" <%= ccm[9] %>>10</option>
																	  <option value ="11" <%= ccm[10] %>>11</option>
																	  <option value ="12" <%= ccm[11] %>>12</option>
												 </select>月
											</td>
					</tr>
				</table>
			</div>
			</div>
			</br>
			<div align="right">
				<input type ="submit" value ="確認画面へ" style="font-size: 20px" ></input>
			</div>
		</div>
		</div>
		<input type="hidden" name="sno" value=<%= sno %>></input>
		<input type="hidden" name="menjo" value="-">
		<input type="hidden" name="henko" value="-">
		<input type="hidden" name="menjuken" value="-">
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