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
	String gno = "";
	String ydate = "";
	String[] ccy = {"","","","","",""};
	String[] ccm = {"","","","","","","","","","","",""};
	
	String year = "";
	String month = "";
	
	if(backflg == 0){
		sno = (String) request.getAttribute("sno");
	}else if(backflg == 1){
		sno = (String) request.getAttribute("sno");
		gno = (String) request.getAttribute("gno");
		ydate = (String) request.getAttribute("ydate");
		year = ydate.substring(0,4);
		month = ydate.substring(5);
		
		if(year.equals("2019")){
			ccy[0] = "Selected";
		}else if(year.equals("2020")){
			ccy[1] = "Selected";
		}else if(year.equals("2021")){
			ccy[2] = "Selected";
		}else if(year.equals("2022")){
			ccy[3] = "Selected";
		}else if(year.equals("2023")){
			ccy[4] = "Selected";
		}else if(year.equals("2024")){
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
		<h3>メニュー</h3>
		<ul>
			<li><a href="./GHome">ホーム</a></li>
  			<li><a href="./GSiken">試験一覧</a></li>
  			<li><a href="./Login">ログアウト</a></li>
		</ul>
	</div>
	<div id="contents">
		<h2>ITパスポート試験<br />申込み画面</h2>
		<form method="get" action="./Kakunin">
		<div align="center">
		<div id="empty">
			<div align="left">
			<div id="a">
				<table border ="1" align ="left" cellpadding ="10"><!--本文-->
					<tr>
						<th>学籍番号</th>	<td><input name ="gno" type="number" maxlength="6" style="font-size: 28px" value=<%= gno %>></input></td>
					</tr>
					<tr>
						<th>受験予定年月</th>	<td><select name ="year" style="font-size: 28px" ><option value ="2019" <%= ccy[0] %>>2019</option>
																	 <option value ="2020" <%= ccy[1] %>>2020</option>
																	 <option value ="2021" <%= ccy[2] %>>2021</option>
																	 <option value ="2022" <%= ccy[3] %>>2022</option>
																	 <option value ="2023" <%= ccy[4] %>>2023</option>
																	 <option value ="2024" <%= ccy[5] %>>2024</option>
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
		<input type="hidden" name="sno" value=<%= sno %>>
		<input type="hidden" name="menjo" value="-">
		<input type="hidden" name="henko" value="-">
		<input type="hidden" name="menjuken" value="-">
		</form>
	</div>
	<div id="footer"><!--フッター-->
		<i id="copyright"><small>Copyright © 2019-2020 Tohoku Computer College All Rights Reserved.</small></i>
	</div>
</div>
</body>
</html>