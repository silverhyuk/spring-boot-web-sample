package com.cafe24.websample.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * FileDownload Util
 * @author 최은혁
 * @date 2018.4.21
 */
public class FileDownloadUtil
{
	 /** 다운로드 버퍼 크기 */
	  private static final int BUFFER_SIZE = 8192; // 8kb

	  /** 문자 인코딩 */
	  private static final String CHARSET = "euc-kr";

	  /**
	   * 지정된 파일을 다운로드 한다.
	   * @author 최은혁
	   * @date 2018.4.21
	  */
	  public static void download(HttpServletRequest request, HttpServletResponse response, File file, String realName) throws Exception {
		  	InputStream is = null;
		    BufferedInputStream fin = null;
		    BufferedOutputStream outs = null;
		  	try {
			    String mimetype = request.getSession().getServletContext().getMimeType(file.getName());
			    /*
			     프로그램 파일 또는 실행파일일 경우 에러처리
			     */
			    if (file == null) {
			    	throw new Exception("filedownload.null.error");
			    }if (!file.exists()) {
			    	throw new Exception("filedownload.nodata.error");
			    }if (file.length() <= 0) {
			    	throw new Exception("filedownload.nosize.error");
			    }if (file.isDirectory()) {
			    	throw new Exception("filedownload.nofile.error");
			    }
			    
			    String mime = "binary/octet-stream;";
			    
			    byte[] buffer = new byte[BUFFER_SIZE];
		
			    response.setContentType(mime + "; charset=" + CHARSET);
		
			    //String userAgent = request.getHeader("User-Agent");
			    response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(realName, "UTF-8").replaceAll("\\+", "%20") + ";");
			    
			    long filesize = file.length(); 
			    
			    // 파일 사이즈가 정확하지 않을때는 아예 지정하지 않는다.
			    if (filesize > 0) {
			      response.setHeader("Content-Length", "" + filesize);
			    }
			    
			    //파일 다운로드 작업
			    is = new FileInputStream(file);
			    fin = new BufferedInputStream(is);
			    outs = new BufferedOutputStream(response.getOutputStream());
			    int read = 0;
			    while ((read = fin.read(buffer)) != -1) {
			        outs.write(buffer, 0, read);
			    }
	  		}catch(Exception e){
	        	e.printStackTrace();
	        	throw new Exception("filedownload.error");
		    }finally {
		    	if(is!=null){
		    		is.close();
		    	}
		    	if(outs!=null){
		    		outs.close();
		    	}
		    	if(fin!=null){
		    		fin.close();
		    	}
		    } // end of try/catch
	  } 	    
}
