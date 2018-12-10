package com.cafe24.websample.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 파일 util
 * @author 최은혁
 * @date 2018.4.21
 */
public class FileUtil {

	/**
	 * 파일 복사
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static boolean copy(File source , File target) throws Exception{
		FileInputStream io = null;
		FileOutputStream out = null;
		
		try{
			io = new FileInputStream (source);
			if (!target.isFile()){
				File fParent = new File (target.getParent());
				if (!fParent.exists()){
					fParent.mkdirs();
				}
				target.createNewFile();
			}
			out = new FileOutputStream (target);
			byte[] bBuffer = new  byte[ 1024 * 8];

			int nRead;
			while ((nRead = io.read( bBuffer)) != -1){
				out.write( bBuffer, 0, nRead);
			}
			out.flush();
		}catch(Exception ex){
			throw ex;
		}finally{
			if(io	!= null)	io.close();
			if(out	!= null)	out.close();
		}		
		return true;
	}
	
}
