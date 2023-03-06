package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import domain.ProductVO;
import domain.ReplVO;
import service.Service;
import util.FileUpload;

@WebServlet("/main/*")
public class MainController extends HttpServlet {

	private Service service;
	private FileUpload upload;
	
	@Override
	public void init() throws ServletException {
		ProductDao dao = new ProductDao();
		service = new Service(dao);
		upload = new FileUpload("product/");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pre="/WEB-INF/views/main/";
		String suf=".jsp";
		RequestDispatcher rd =null;
		
		List<ProductVO> ProductList =service.list();
		List<ReplVO> BestRepl= service.getreple(); 
		
		request.setAttribute("repl", BestRepl);
		request.setAttribute("ProductList", ProductList);
		
		
		rd = request.getRequestDispatcher(pre+"mainPage"+suf);	
		rd.forward(request, response);
		
		
		
	}
	
	
	

}
