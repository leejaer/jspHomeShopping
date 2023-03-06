package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class FileUpload {
	private static final String REPO_PATH="c:/file_upload/";
	
	private String serviecPathName;
	
	public FileUpload(String serviecPathName) {
		this.serviecPathName = serviecPathName;
	}

	public Map<String, String> getMultipartRequest(HttpServletRequest request) {
		Map<String, String> map = new HashMap();
		File currentPath = new File(REPO_PATH + serviecPathName +"temp");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload rep = new ServletFileUpload(factory);
		rep.setFileSizeMax(1024*1024*10);
		
		try {
			List<FileItem> items = rep.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) map.put(item.getFieldName(), item.getString("utf-8"));
				else {
					if(item.getSize()>0) {
						String fileName = item.getName();
						map.put(item.getFieldName(), fileName);
						File uploadFile = new File(currentPath,fileName);
						item.write(uploadFile);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public void uploadImage(int no, String image) {
		File srcFile = new File(REPO_PATH+serviecPathName+"temp",image);
		File destFile = new File(REPO_PATH+serviecPathName+ no);
		destFile.mkdir();
		try {
			FileUtils.moveFileToDirectory(srcFile, destFile, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteOriginImage(int no, String originFileName ) {
		File oldFile =new File(REPO_PATH+serviecPathName+no+"/"+originFileName);
		oldFile.delete();
	}
	public void deletAllImage(int no) throws IOException {
		File targetDir = new File(REPO_PATH+serviecPathName+no);
		if(targetDir.exists()) {
			FileUtils.deleteDirectory(targetDir);
		}
	}

}
