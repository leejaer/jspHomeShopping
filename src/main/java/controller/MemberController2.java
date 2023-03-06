package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.util.Introspection;

import com.google.gson.Gson;

import dao.MemberDao;
import domain.LoginVO;
import domain.MemberVO;
import domain.ProductVO;
import service.Mservice;

@WebServlet("/member/*")
public class MemberController2 extends HttpServlet {
	
	private Mservice service;
	private Gson gson;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new Mservice(new MemberDao()); 
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
		String pre="/WEB-INF/views/member/";
		String suf=".jsp";
		RequestDispatcher rd =null;
		String page=null;
		
		
		
		if(pathInfo.equals("/joinForm")) {
			page= "joinForm";
		}else if(pathInfo.equals("/join")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String pwd = (String) request.getAttribute("pwd");
			String addr = request.getParameter("addr");
			String email = request.getParameter("email");
			String tellparam = request.getParameter("tel");
			int tell = Integer.parseInt(tellparam);
			MemberVO vo = MemberVO.builder().id(id).name(name).pwd(pwd).addr(addr).email(email).tell(tell).build();
			service.addMember(vo);
			String result = gson.toJson(vo.getId()+"님 가입을 환영합니다");
			out.print(result);
			return;
		}else if(pathInfo.equals("/loginForm")){
			page= "loginForm";
		}else if(pathInfo.equals("/login")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo =MemberVO.builder().id(id).pwd(pwd).build();
			if(service.loginService(vo)) {
				HttpSession session = request.getSession();
				String grade = service.getMemberGrade(vo.getId());
				service.attend(vo.getId());
				LoginVO loginVO = new LoginVO();
				loginVO.setId(vo.getId());
				session.setAttribute("grade", grade);
				session.setAttribute("ok", loginVO);
				String attribute = (String) session.getAttribute("userUri");
				if (attribute!= null) {
					session.removeAttribute("userUri");
					String result = gson.toJson("성공");
					out.print(result);
					return;
				}
				String result = gson.toJson(vo.getId()+"반갑습니다");
				out.print(result);
				return;
			}else {
				String result = gson.toJson("실패");
				out.print(result);
				return;
			}
		}else if(pathInfo.equals("/logout")){
			HttpSession session = request.getSession(false);
			session.removeAttribute("ok");
			response.sendRedirect(contextPath+"/member/loginForm");
			return;
		}else if(pathInfo.equals("/memberDetail")){
			String id = request.getParameter("id");
			MemberVO vo = service.findOne(id);
			request.setAttribute("detail", vo);
			page ="memberDetail";
			
		}else if(pathInfo.equals("/basket")) {
			String paramPno = request.getParameter("pno");
			int pno = Integer.parseInt(paramPno);
			String id = request.getParameter("userid");
			String answer = service.addBasket(pno,id);
			String result = gson.toJson(answer);
			out.print(result);
			return;
			
		}else if(pathInfo.equals("/basketList")) {
			String id = request.getParameter("id");
			List<ProductVO> vo =service.findBasket(id);
			request.setAttribute("vo", vo);
			page = "basket";
		}else if(pathInfo.equals("/directBuy")){
			List<ProductVO> list =new ArrayList<ProductVO>();
			String paramPno = request.getParameter("pno");
			int pno = Integer.parseInt(paramPno);
			ProductVO vo = service.productDetail(pno);
			String id = request.getParameter("userid");
			list.add(vo);
			request.setAttribute("vo", list);
			page = "basket";
		}else if(pathInfo.equals("/update")) {
			String mno1 = request.getParameter("mno");
			int mno = Integer.parseInt(mno1);
			String pwd = (String) request.getAttribute("pwd");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");
			String tel = request.getParameter("tel");
			int tell = Integer.parseInt(tel);
			MemberVO vo = MemberVO.builder().mno(mno).pwd(pwd).email(email).addr(addr).tell(tell).build();
			service.chage(vo);
			String result = gson.toJson("수정완료");
			out.print(result);
			return;
		}else if(pathInfo.equals("/calenadar")) {
			page ="calenadar";
			request.getRequestDispatcher("/WEB-INF/views/main/"+page+suf);
			return;
		}else if(pathInfo.equals("/cumstomerService")) {
			page ="cumstomerService";
		}else if(pathInfo.equals("/test02")) {
			page ="test02";
		}
		else if(pathInfo.equals("/memberList")) {
			List<MemberVO> memberList =service.list();
			request.setAttribute("memberList", memberList);
			page ="memberList";
		}else if(pathInfo.equals("/ban")) {
			service.banMember(request.getParameter("mno"));
			response.sendRedirect(contextPath+"/member/memberList");
			return;
		}

		else {
			page = "404";
			rd = request.getRequestDispatcher("/WEB-INF/views/commons/"+page+suf);	
			rd.forward(request, response);
			return;
			
		}
		
		rd = request.getRequestDispatcher(pre+page+suf);	
		rd.forward(request, response);
	}
	
	
	

}
