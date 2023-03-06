package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.RDao;
import domain.ReplVO;
import service.Rservice;
@WebServlet("/repl/*")
public class ReplController extends HttpServlet {
	
	private Rservice service;
	private Gson gson;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new Rservice(new RDao()); 
		gson = new Gson();
	}
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		String pathInfo = request.getPathInfo();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String pre="/WEB-INF/views/product/";
		String suf=".jsp";
		RequestDispatcher rd =null;
		String page=null;
		
		if(pathInfo.equals("/add")) {//입력
			
			String paramPno = request.getParameter("pno");
			int pno = Integer.parseInt(paramPno);
			String id = request.getParameter("id");
			String replcontent = request.getParameter("replcontent");
			String replStar = request.getParameter("replp");
			
			ReplVO vo = ReplVO.builder().pno(pno).id(id).replcontent(replcontent).replStar(replStar).build();
			service.addRepl(vo);
			
			String result = "댓글 등록 완료";//보낼 메세지
			result = gson.toJson(result);
			out.print(result);
			return;
			
		}else if(pathInfo.equals("/del")) {//삭제
			
			String paramRno = request.getParameter("rno");
			int rno = Integer.parseInt(paramRno);
			
			service.removeReple(rno);
			
			String result = "댓글 삭제 완료";
			result = gson.toJson(result);
			out.print(result);
			return;
			
		}else if(pathInfo.equals("/mod")) {//수정
			ReplVO vo = ReplVO.builder().rno(Integer.parseInt(request.getParameter("rno"))).replcontent(request.getParameter("content")).build();
			
			service.modRepl(vo);
			
			String result = "댓글 수정 완료";
			result = gson.toJson(result);
			out.print(result);
			return;
			
		}else {
			page = "404";
			rd = request.getRequestDispatcher("/WEB-INF/views/commons/"+page+suf);	
			rd.forward(request, response);
			return;
		}
		
		 
		
	
		
	}

}
