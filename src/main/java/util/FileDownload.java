package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/filedownload")
public class FileDownload extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileRepo = "c:/file_upload";//파일이 저장된 디렉토리
		String fileName = request.getParameter("image");//이름
		String path = request.getParameter("path");
		String no = request.getParameter("no");
		File downFile = new File(fileRepo+"/"+path+"/"+no,fileName);//다운로드 대상 파일
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-disposition", "attachment; fileName=" + fileName);
		
		try(
			OutputStream out = response.getOutputStream();//출력 스트림
			InputStream in = new FileInputStream(downFile);//입력스트림
			) {
			//ㅅ.txt >> 입력스트림>>다운로드 요청>> 출력 스트림>>사용자 한테 전달
			byte[] buffer = new byte[1024*8];
			int cnt =0;
			while((cnt =in.read(buffer))!=-1) {//읽어올 값이 있다면
				out.write(buffer,0,cnt);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
