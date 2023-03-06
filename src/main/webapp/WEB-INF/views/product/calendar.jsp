<%@page import="java.util.Calendar"%>
<%@page import="util.MyCalendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
/* 기본스타일  */	
	table{ background-color: #F2F2F2;}
	
	tr{height: 60px;}
	td{width: 100px; text-align: right; font-size: 15pt; font-family: D2coding;}
/* 타이틀 스타일 */
	th#title {font-size: 20pt; font-weight: bold; color: #FFBF00; font-family: D2coding; }

/* 요일 스타일 */
	td.sunday{ text-align: center; font-weight: bold; color: red; font-family: D2coding; }
	td.saturday{ text-align: center; font-weight: bold; color: blue; font-family: D2coding; }
	td.etcday{ text-align: center; font-weight: bold; color: black; font-family: D2coding; }

/* 날짜 스타일 */
	td.sun{ text-align: right; font-size: 15pt; color: red; font-family: D2coding; vertical-align: top;}
	td.sat{ text-align: right; font-size: 15pt; color: blue; font-family: D2coding; vertical-align: top;}
	td.etc{ text-align: right; font-size: 15pt; color: black; font-family: D2coding; vertical-align: top;}
	
	td.redbefore{ text-align: right; font-size: 12pt; color: red; font-family: D2coding; vertical-align: top;}
	td.before{ text-align: right; font-size: 12pt; color: gray; font-family: D2coding; vertical-align: top;}
	

</style>
<title>Insert title here</title>
</head>
<body>

<%
	Date date = new Date();
	LocalDate now = LocalDate.now();
	int year = now.getYear();
	int month = now.getMonthValue();
	Calendar calendar = Calendar.getInstance();
	
	int CurrentMonth =calendar.get(calendar.get(Calendar.MONTH));
	
	int today =calendar.get(calendar.DAY_OF_WEEK);

	try{
		year = Integer.parseInt(request.getParameter("year"));
		month = Integer.parseInt(request.getParameter("month"));
		
		if(month>=13){
			year++;
			month =1;
		}else if(month <=0){
			year--;
			month =12;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>

<table >
	<tr>

		<th>
		<%-- <a href ="?year=<%=year%>&month=<%month-1%>">이전 달</a> --%>
		<input type="button" value="이전 달" onclick="location.href='?year=<%=year%>&month=<%=month-1%>'">
		</th>
		

		<th id = "title" colspan = "5">
		<%=year%>년  <%=month%>월
		</th>
		

		<th>
		<%-- <a href ="?year=<%=year%>&month=<%month+1%>">다음 달</a> --%>
		<input type="button" value="다음 달" onclick="location.href='?year=<%=year%>&month=<%=month+1%>'">
		
		</th>
	</tr>

	<tr>
		<td class = "sunday">일</td>
		<td class = "etcday">월</td>
		<td class = "etcday">화</td>
		<td class = "etcday">수</td>
		<td class = "etcday">목</td>
		<td class = "etcday">금</td>
		<td class = "saturday">토</td>
	</tr>
	

	<tr>
	<%

		int first = MyCalendar.weekDay(year, month, 1);
		int start = 0 ;
		start = month ==1? MyCalendar.lastDay(year-1, 12)- first : MyCalendar.lastDay(year, month-1)- first;
		for(int i= 1; i<= first; i++){
			if(i==1){
				out.println("<td class = 'redbefore'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
			}else{
				out.println("<td class = 'before'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
			}
		}

		for(int i = 1; i <= MyCalendar.lastDay(year, month); i++){
	
			switch(MyCalendar.weekDay(year, month, i)){
				case 0 :
				
					out.println("<td class ='sun'>" +i+"</td>");
					break;
					
				case 6 :
			
					out.println("<td class ='sat'>" + i +"</td>");
					break;
					
				default :
			
					out.println("<td class ='etc'>" + i +"</td>");
					break;
					
			}
			

			if(MyCalendar.weekDay(year, month, i) == 6 && i != MyCalendar.lastDay(year, month)){
				out.println("</tr><tr>");			
			}
		}
		if(MyCalendar.weekDay(year, month, MyCalendar.lastDay(year, month)) !=6){
			for(int i = MyCalendar.weekDay(year, month, MyCalendar.lastDay(year, month))+1; i < 7; i++){
				out.println("<td></td>");	
			}
		}
		


	%>
	</tr>
	
</table>

</body>
</html>