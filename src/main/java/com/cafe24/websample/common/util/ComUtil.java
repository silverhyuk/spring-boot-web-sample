package com.cafe24.websample.common.util;


import com.cafe24.websample.web.auth.CustomUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 공통 util
 * @author 최은혁
 * @date 2018.4.21
 */
public class ComUtil {
	
	public static final int MY_DOMIN	= 1;
	public static final int DEV_DOMIN	= 2;
	public static final int STG_DOMIN	= 3;
	public static final int REA_DOMIN	= 4;
			
	
	/**
	 * 리퍼러 체크하기. 전 도메인이 es3가 이 아닌경우.
	 * @author 최은혁
	 * @date 2018.4.21
	 */
/*	public static boolean isReferer(HttpServletRequest req)  throws Exception{
		boolean isReferer = false;
		String url = req.getHeader("Referer");
		
		//- TODO 리퍼러 수정 HTTPS 가 될수도 있고 해당 사이트는 관리자이니 확인 요망.
		if(url.startsWith(Config.get("http.url")) || url.startsWith(Config.get("https.url")) || url.startsWith("http://" + getDomain(req)) ){
			isReferer = true;
		}else {
			isReferer = false;
		}
		return isReferer;
	}*/
	

	
	/**
	 * 현재 도메인 정보 가져오기
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String getDomain(HttpServletRequest req)  throws Exception{
		String domain = req.getServerName();
		return domain;
	}
	
	/**
	 * 현재 도메인중심으로 로컬 도메인주소
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String getFrontDomain(HttpServletRequest req )  throws Exception{
		return getFrontDomain(req, false);
	}
	
	/**
	 * 현재 도메인중심으로 로컬 도메인주소
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String getFrontDomain(HttpServletRequest req , boolean isSsl)  throws Exception{
		String domain = req.getServerName();
		String domain2 = domain.substring(domain.indexOf("."));
		String domain1 = "";
		String http = "http://";
		
		if(isSsl){
			http= "https://";
		}
		if(domain.startsWith("my.")){
			domain1 = "my";
		}
		if(domain.startsWith("dev.")){
			domain1 = "dev";
		}
		if(domain.startsWith("stg.")){
			domain1 = "stg";
		}
		if(domain.startsWith("www.")){
			domain1 = "www";
		}
		return http + domain1 + domain2;
	}
	
	/**
	 * 로그인 정보 가져오기.
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static CustomUser getLoginInfo()  throws Exception{
		//SecurityAuthManagerDvo loginDvo = (SecurityAuthManagerDvo)req.getSession().getAttribute("userLoginInfo");
		CustomUser loginDvo = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		return loginDvo;
	}
	
	/**
	 * 로그인 여부 판단.
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static boolean isLogin(HttpServletRequest req)  throws Exception{
		boolean isLogin = false;
		CustomUser loginDvo = getLoginInfo();
		if (loginDvo != null) {
			isLogin = true;
		}
		return isLogin;
	}
	
	/**
	 * 로그아웃
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static void myLogoff(HttpServletRequest request, HttpServletResponse response) {
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		cookieClearingLogoutHandler.logout(request, response, null);
		securityContextLogoutHandler.logout(request, response, null);
	}
		
	/**
	 * 아이피 보안 체크.
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static boolean isIpCheck(HttpServletRequest req)  throws Exception{
		boolean isIpCheck = false;
		String ip = (String)req.getSession().getAttribute("IP_SEC_CHECK_SESSION");
		
		if(req.getRemoteAddr().equals(ip)){
			isIpCheck = true; //- 정상 사용자.
		}else {
			isIpCheck = false; //- 부저사용자
		}
		return isIpCheck;
	}
	
	/**
	 * 인증번호 난수 발생.
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String getCertiNum(int length){
		Random rand = new Random(System.currentTimeMillis());
		
		String num = String.valueOf(Math.abs(rand.nextGaussian()));
		String tempNum = num.substring(num.length() - length , num.length());
		
		return tempNum;
	}
	
	/**
	 * 임시 비밀번호 생성
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String getTempPwd(){
		char[] charSet = new char[]{'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','h','i','j',
				'k','l','m','n','0','p','q','r','s','t',
				'u','v','w','x','y','z'}; 
		int idx = 0;
		StringBuffer sb = new StringBuffer(); 
		
		for(int i=0; i<12; i++){
			idx = (int)(charSet.length*Math.random());
			sb.append(charSet[idx]);
		}
		
		return sb.toString();
	}


	
	/**
	 * 쿠키정보 가져오기.
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String getCookie(HttpServletRequest req , String key)  throws Exception{
		Cookie[] cookies = req.getCookies();
		String value = null;
		for (int cookieCnt = 0; cookieCnt < cookies.length; cookieCnt++) {
			if(key.equals(cookies[cookieCnt].getName())){
				value = cookies[cookieCnt].getValue() ;
				break;				
			}
		}
		return value;
	}
	
	/**
	 * 엑셀 다운로드 xls 셋팅
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static void setDownXls(HttpServletResponse res , String fileName) throws Exception{
		setDownXlsResponse(res , fileName , ".xls");
	}
	
	/**
	 * 엑셀 다운로드 xlsx 확인
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static void setDownXlsx(HttpServletResponse res , String fileName) throws Exception{
		setDownXlsResponse(res , fileName , ".xlsx");
	}
	
	/**
	 * 엑셀 다운로드 셋팅
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	private static void setDownXlsResponse(HttpServletResponse res , String fileName , String ext) throws Exception{
		res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8") + ext);  
		res.setHeader("Content-Description", "JSP Generated Data");  
		res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");  
	}
	
	/**
	 * 문자열 1,2,3 -> 숫자로 변환 후 sum
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String makeBitData(String data,String gubun) {
		int returnData = 0;
		if("".equals(data)){
			return "";
		}
		String[] datas = data.split(gubun);
		for (int i=0;i<datas.length;i++) {
			returnData += Integer.parseInt(datas[i]);
		}
		return Integer.toString(returnData);
	}
	
	/**
	 * 숫자를 bit data로 변환
	 * @author 최은혁
	 * @date 2018.4.21
	 */
	public static String getBitData(String data,Integer totalBit) {
		int num = Integer.parseInt(data);
		String bitData = Integer.toBinaryString(num);
		//System.out.println("bitData : "+bitData);
		//System.out.println("bitData : "+String.format("%0"+totalBit+"d",Long.parseLong(bitData)));
		bitData = String.format("%0"+totalBit+"d",Long.parseLong(bitData));
		String reverseBitData = new StringBuffer(bitData).reverse().toString();
		//System.out.println("reverseBitData : "+reverseBitData);
		return reverseBitData;
	}
	
	/**
     * 숫자 staNum부터 endNum까지 SELECT OPTION태그 만들기
     */
    public static String renderOptions(int staNum, int endNum, int selectNum) {
        StringBuffer options = new StringBuffer();
        DecimalFormat df = new DecimalFormat("00");
        for (int i = staNum; i <= endNum; i++) {
            if (i == selectNum) {
                options.append("<option value='" + df.format(i)
                        + "' selected='selected'> " + df.format(i) + " </option>\n");
            } else {
                options.append("<option value='" + df.format(i) + "'> " + df.format(i)
                        + " </option>\n");
            }
        }
        return options.toString();
    }
    
    /**
     * 숫자 staNum부터 endNum까지 SELECT OPTION태그 만들기
     */
    public static String renderOptionsPow(int staNum, int endNum) {
        StringBuffer options = new StringBuffer();
        DecimalFormat df = new DecimalFormat("00");
        for (int i = staNum; i <= endNum; i++) {
        	options.append("<option value='" + (int)Math.pow(2, i)
                    + "' selected='selected'> " + df.format(i) +"~"+ df.format(i+1) + " </option>\n");
        }
        return options.toString();
    }
}
