package com.cafe24.websample.common.util;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

public class FileDownloadView extends AbstractView {
 
    public FileDownloadView() {
        // 객체가 생성될 때 Content Type을 다음과 같이 변경 
        setContentType("application/download; charset=utf-8");
    }
 
    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> model, 
            HttpServletRequest request,
            HttpServletResponse response
            ) throws Exception {
         
        Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile"); // 넘겨받은 모델(파일 정보)
         
        String fileUploadPath = (String) fileInfo.get("fileUploadPath");    // 파일 업로드 경로
        String fileLogicName = (String) fileInfo.get("fileLogicName");      // 파일 논리명(화면에 표시될 파일 이름)
        String filePhysicName = (String) fileInfo.get("filePhysicName");    // 파일 물리명(실제 저장된 파일 이름)
         
        File file = new File(fileUploadPath, filePhysicName);
         
        response.setContentType(getContentType());
        response.setContentLength((int) file.length());
        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1;
        String fileName = null;
 
        if(ie) {
            fileName = URLEncoder.encode(fileLogicName, "utf-8");
        } else {
            fileName = new String(fileLogicName.getBytes("utf-8"), "iso-8859-1");
        }
 
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
 
        FileInputStream fis = null;
 
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } finally {
            if(fis != null){
            	 try {
                     fis.close();
                 } catch(IOException ex) {
                	 
                 }
            }
               
        }
        out.flush();
    }
}
