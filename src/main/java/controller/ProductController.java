package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ProductDao;
import domain.HashVO;
import domain.ProductVO;
import domain.ReplVO;
import domain.productBill;
import service.Service;
import util.FileUpload;

@WebServlet("/product/*")
public class ProductController extends HttpServlet {
	
	private Service service;
	private FileUpload upload;
	private Gson gson;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ProductDao dao = new ProductDao();
		service = new Service(dao);
		upload = new FileUpload("product/");
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
		String page= null;
		if(pathInfo.equals("/serchList")) {//리스트
			
			List<ProductVO> ProductList =service.list();
			request.setAttribute("ProductList", ProductList);
			
			page = "list";
			
		}else if(pathInfo.equals("/detail")) {//상세
			
			String parampno = request.getParameter("pno");
			int pno = Integer.parseInt(parampno);
			List<HashVO> hashList =service.getHash(pno);
			ProductVO ProductList =service.detail(pno);
			List<ReplVO> list =  service.ReflDetail(pno);
			
			request.setAttribute("hash", hashList);
			request.setAttribute("repl", list);
			request.setAttribute("product", ProductList);
			page= "detail";
			
		}else if(pathInfo.equals("/addForm")) {//추가양식
			
			page = "addForm";
			
		}else if(pathInfo.equals("/add")) {//물건 추가
			
			Map<String, String> req = upload.getMultipartRequest(request);
			String image = req.get("imageFileName");
			
			if(image==null) { 
				response.sendRedirect(contextPath+"/product/addForm");
				return;
			}
			
			ProductVO vo = ProductVO.builder().class1(req.get("class1")).name(req.get("name")).like1(0).
					sellmount(0).discount(0).image(image).
					currentmount(Integer.parseInt(req.get("currentmount"))).price(Integer.parseInt(req.get("price")))
					.content(req.get("content")).writer(req.get("writer")).build();
			
			int pno = service.addProduct(vo);
			if(image!=null && image.length()>0) {
				upload.uploadImage(pno, image);
			}
			
			response.sendRedirect(contextPath+"/main/");//등록후 메인으로
			return;
			
		}else if(pathInfo.equals("/del")){//삭제
			
			int pno = Integer.parseInt(request.getParameter("pno"));
			
			service.delProduct(pno);
			upload.deletAllImage(pno);
			
			response.sendRedirect(contextPath+"/main");
			return;
			
		}else if(pathInfo.equals("/modForm")){//수정모드
			
			int pno = Integer.parseInt(request.getParameter("pno"));

			ProductVO ProductVO =service.detail(pno);//수정 모드로 갈때 정보 담아보내기
			
			request.setAttribute("product", ProductVO);
			page= "modForm";
			
		}else if(pathInfo.equals("/mod")){ // 수정처리 
			
			Map<String, String> req = upload.getMultipartRequest(request);//이미지 포함돼있으니 multipart로 보냈음
			String paramPno = req.get("pno");
			int pno = Integer.parseInt(paramPno);
			String image = req.get("imageFileName"); // 새 이미지 
			
			Integer.parseInt(req.get("price"));
			ProductVO vo = ProductVO.builder().pno(pno).name(req.get("name")).
					image(image).
					currentmount(Integer.parseInt(req.get("currentmount"))).price(Integer.parseInt(req.get("price")))
					.content(req.get("content")).build();
			
			if(image!=null && image.length()>0) { // 새 이미지가 있을 때 
				String origin = req.get("origin");
				if(origin!=null) {
					upload.deleteOriginImage(pno, origin);
				}
				upload.uploadImage(pno, image);
				service.modProduct(vo);
			}else {//없을때
				service.modProduct(vo);
			}
			
			
			response.sendRedirect(contextPath+"/main");
			return;
			
		}else if(pathInfo.equals("/search")) {//검색
			
			String class1 = request.getParameter("class1");
			
			List<ProductVO> list = service.findList(class1);
			request.setAttribute("product", list);
			
			page= "searchProduct";
			
		}else if(pathInfo.equals("/like")) {
			String pnoparam = request.getParameter("pno");
			int pno = Integer.parseInt(pnoparam);
			
			//연속 클릭 방지
			long currentTime = System.currentTimeMillis();
			HttpSession session =  request.getSession(false);
			if(session.getAttribute("lastWrting")!=null) {
				long lastWrting = (long) session.getAttribute("lastWrting");
				if(currentTime-lastWrting<10000) {
					String result = gson.toJson("광클금지");
					out.print(result);
					return;
				}
			}
			session.setAttribute("lastWrting", currentTime);
			service.likeThis(pno);
			
			String result = gson.toJson("좋아요!");
			out.print(result);
			return;
			
		}else if(pathInfo.equals("/buy")) {
			/*String paramList = request.getParameter("paramList"); 껍대기 이름 */
			String id = request.getParameter("id");
			String cnt = request.getParameter("array");
			String pno = request.getParameter("check");
			
			if(cnt.length()>3) {// 문자열을 다듬어서 맞추기
				id = id.substring(2,id.length()-2);
				String[] ids = id.split("\",\"");

				cnt = cnt.substring(2,cnt.length()-2);
				String[] cnts = cnt.split("\",\"");
				
				pno = pno.substring(2,pno.length()-2);
				String[] pnos = pno.split("\",\"");
				
				String result = service.buyTry(ids,cnts,pnos);
				
				service.bill(ids,cnts,pnos);
				
				result = gson.toJson(result);
				out.print(result);
				
			}else {//잘 못 입력 시 반응
			String result = "정확한 값을 입력하세요";
			result = gson.toJson(result);
			out.print(result);
			}
			return;
			
			
		}else if(pathInfo.equals("/buyResult")) {
			
			page = "buyResult";
			
		}else if(pathInfo.equals("/cal")) {//달력?
			
			page = "calendar";
			
		}
		else if(pathInfo.equals("/searchDetail")) {//검색 시 리시트
			
			String search = request.getParameter("search");
			List<HashVO> searchList = service.getHashName(search);
			HashVO vo = HashVO.builder().name(search).build();
			searchList.add(vo);
			
			List<ProductVO> ProductList = service.findSearch(searchList);
		
			request.setAttribute("product", ProductList);
			page= "searchProduct";
			
		}else if(pathInfo.equals("/val")) {
			
			String hash = request.getParameter("val");
			List<String> hashList = new Gson().fromJson(hash, List.class);//g손 객체로 보내서g손으로 풀어야함
			String pnoS = request.getParameter("pno"); 
			int pno =Integer.parseInt(pnoS);
			String name = request.getParameter("name");
			service.addHash(pno,name,hashList);
			
			String result = "해시 등록완료";
			result = gson.toJson(result);
			out.print(result);
			
			return;
			
		}else if(pathInfo.equals("/bill")) {
			List<productBill> billList = service.getBill();
			request.setAttribute("b", billList);
			page= "bill";
		}else if(pathInfo.equals("/billCanlcel")) {
			String pbnoS = request.getParameter("pbno");
			String id = request.getParameter("id");
			String cnt = request.getParameter("cnt");
			String priceS = request.getParameter("price");
			int price = Integer.parseInt(priceS);
			int pbno = Integer.parseInt(pbnoS);
			
			service.billCancle(pbno);
			service.feedbackPoint(id,cnt,price);
			response.sendRedirect(contextPath+"/product/bill");//등록후 메인으로
			return;
		}else if(pathInfo.equals("/myBill")) {
			String id = request.getParameter("id");
			List<productBill> billList =service.ownBill(id);
			request.setAttribute("b", billList);
			page= "bill";
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
