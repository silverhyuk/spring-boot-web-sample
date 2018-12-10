package com.cafe24.websample.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.text.StrBuilder;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String util
 * @author 최은혁
 * @date 2014.4.21
 */
public class StringUtil {
	
	private static String lowEng = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ";
	private static String upEng = "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ";
	
	public static void main(String[] args){		
		System.out.println(printf(" %3s  %2s" , new String[]{"3","3"}));
	}
	
	/**
	 * 2자리 수의 1 들어갈경우 01 이 리턴되어진다.
	 * @author 최은혁
	 * @date 2014.4.21
	*/
	public static String get0Fill(String value){
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat("00",symbol);
        
		return df.format(Integer.parseInt(value));
	}
	
	/**
	 * 2자리 수의 1 들어갈경우 01 이 리턴되어진다.
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String get0Fill(int value){
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat("00",symbol);
        
		return df.format(value);
	}
	
	/**
	 * 패턴을 받아 해당 패턴으로 리턴
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String get0FillPattern(String pattern , String value){
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat(pattern,symbol);
        
		return df.format(Integer.parseInt(value));
	}
	
	/**
	 * 패턴을 받아 해당 패턴으로 리턴
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String get0FillPattern(String pattern , int value){
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat(pattern,symbol);
        
		return df.format(value);
	}
	
	/**
	 * 3개의 인자를 받아 예) 1,2,"-" => 01-02 
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String get0Fill(String value1 , String value2 , String flag){
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat("00",symbol);
        
		return df.format(Integer.parseInt(value1)) + flag +  df.format(Integer.parseInt(value2));
	}
	
	/**
	 * 3개의 인자를 받아 예) 1,2,"-" => 01-02 
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String get0Fill(int value1 , int value2 , String flag){
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat("00",symbol);
        
		return df.format(value1) + flag +  df.format(value2);
	}
	
	/**
	 * value - 123 , flag = "-" , idx 2 ==> 12-03
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String get0Fill(String value , String flag , int idx){
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		DecimalFormat df = new DecimalFormat("00",symbol);
        
		String tmpStr1 = value.substring(0,idx);
		String tmpStr2 = value.substring(idx);
		//System.out.println(tmpStr1);		
		//System.out.println(tmpStr2);
		
		//System.out.println(df.format(value.substring(0,idx)) + flag +  df.format(value.substring(idx)) + "");		
		
		return df.format(Integer.parseInt(tmpStr1)) + flag +  df.format(Integer.parseInt(tmpStr2));
	}
	
	/**
	 * pattern - "%3s - %2s"  , value = 1,2 => 001 - 02  숫 자의 0을 채워함. 
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String printf(String pattern , String[] values){
		pattern = pattern.replaceAll("%s" , "%1s");		
		String[] patternTypes = getPattern(pattern);
		DecimalFormatSymbols symbol;
		DecimalFormat df;
		int typeCnt = 0;
		for(String type : patternTypes){
			
			String tempType = type.substring(1, type.length()-1);
			int formatCount = Integer.parseInt(tempType);
			String formatString = "";
			for(int fCnt = 0 ; fCnt < formatCount ; fCnt++){
				formatString += "0";
			}				
			symbol = new DecimalFormatSymbols();
			df = new DecimalFormat(formatString,symbol);
			
			values[typeCnt] = df.format(Integer.parseInt(values[typeCnt]));
			pattern = pattern.replaceFirst(type,"{" + typeCnt + "}");
			
			typeCnt++;
		}
		
		return MessageFormat.format(pattern, (Object[])values);
	}
	
	/**
	 * 해당 패턴을 불러옴
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	protected static String[] getPattern(String pattern){
		List<String> list = new ArrayList<String>();
		String regex = "%[\\d]+s";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(pattern);
		
		while (m.find()){
			list.add(m.group(0));			
		}
		
		return list.toArray(new String[0]);
	}
	
	/**
	 * str에서 sub의 반복 횟수를 반환 합니다.
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static int countMatches(String str, String sub) {
		return StringUtils.countMatches(str, sub);
	}

	/**
	* 문자열이 비었거나 null인지 확인 합니다.
	* NOTE: white space에 대한 검사는 하지 않습니다.
	* @author 최은혁
	* @date 2014.4.21
	*/
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	/**
	 * String Array를 한 문자열로 합침니다.
	 * 처음과 끝은 separator가 추가 되지 않습니다.
	 * separator {@code null} 인 경우 빈문자열과 같이 취급 됩니다.
	 * Array의 null은 빈문자열로 취급 됩니다.
	 * @author 최은혁
	 * @date 2014.4.21 
	 */
	public static String combinationArr(String[] array, String separator) {
		return StringUtils.join(array, separator);
	}

	/**
	* 왼쪽에 문자열 길이가 length가 될때 까지 repChar를 채웁니다.
	* @author 최은혁
	* @date 2014.4.21 
	*/
	public static String lpad(String str, int length, String repChar) {
		return StringUtils.leftPad(str, length, repChar);
	}

	/**
	 * 오른쪽에 문자열 길이가 length가 될때 까지 repChar를 채웁니다.
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String rpad(String str, int length, String repChar) {
		return StringUtils.rightPad(str, length, repChar);
	}

	// parseValue??, WiseString.parse, WiseString.StrToArray
	/**
	 * str을 separatorChars 문자열로 잘라 문자열 배열을 만듭니다.
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String[] strToArray(String str, String separatorChars) {
		return StringUtils.split(str, separatorChars);
	}

	/**
	 * compare String
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static boolean equals(String foo, String bar) {
		return StringUtils.equals(foo, bar);
	}

	/**
	 * String foo의 값이 "true" 이면 fos "false" 이면 neg 둘다 아니면 foo를 반환
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String repToStr(String foo, String pos, String neg) {
		if (StringUtils.isEmpty(foo) || StringUtils.equals(foo, "null")) {
			return "";
		}
		if (StringUtils.equals(foo, "true")) {
			return pos;
		}
		if (StringUtils.equals(foo, "false")) {
			return neg;
		}
		return foo;
	}

	/**
	 * 무작위 문자열을 randomStringLength 만큼 type으로 생성
	 * @author 최은혁
	 * @date 2014.4.21 
	 */
	public static String getRandomString(int randomStringLength, String type) {
		if ("N".equals(type)) {
			return RandomStringUtils.randomNumeric(randomStringLength);
		}
		if ("S".equals(type)) {
			return RandomStringUtils.randomAlphabetic(randomStringLength);
		}
		if ("NS".equals(type)) {
			return RandomStringUtils.randomAlphanumeric(randomStringLength);
		}
		return null;
	}

	// wise.util.JSPUtil 
	/**
	 * 문자열 중에 del문자열 제거
	 * @author 최은혁
	 * @date 2014.4.21 
	 */
	public static String ignoreSeparator(String str, String del) {
		return StringUtils.replace(str, del, "");
	}

	
	/**
	 * exception 정보를 String 형태로 return
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String getStackTrace(Throwable throwable) {
		return ExceptionUtils.getStackTrace(throwable);
	}

	public static String CheckInjection(String str) {
		//return StringEscapeUtils.escapeHtml4(str);
		if (str instanceof String) {

			if (!isEmpty(str)) {
				String message = " /*Catched by SQL Injection*/";
				if (str.toUpperCase().indexOf("<") != -1)
					str = replaceAll(str, "<", "＜") + message;
				if (str.toUpperCase().indexOf(">") != -1)
					str = replaceAll(str, ">", "＞") + message;
				if (str.toUpperCase().indexOf("&") != -1)
					str = replaceAll(str, "&", "＆") + message;
				if (str.toUpperCase().indexOf("--") != -1)
					str = replaceAll(str, "--", "－－") + message;
				if (str.toUpperCase().indexOf("'") != -1)
					str = replaceAll(str, "'", "\u2019") + message;
				if (str.toUpperCase().indexOf(";") != -1)
					str = replaceAll(str, ";", "；") + message;
			}
		}
		return str;
	}
	
	public static String toEmpty(String str) {

		String rtnVal = "";
		byte[] byteRes = null;

		if (str == null) {
			rtnVal = "";
		} else {
			try {
				//rtnVal = URLDecoder.decode(str, "UTF-8");
				byteRes = str.getBytes("UTF-8");
				rtnVal = new String(byteRes);
			} catch (UnsupportedEncodingException unsupportedencodingexception) {
				unsupportedencodingexception.printStackTrace();
			}
		}
		return rtnVal;
	}

	/**
	 * 널 문자열이 들어오면 빈 문자열을 return 
	 * 아닐 경우는 입력 값을 return
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String nullToEmptyString(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str;
	}

	public static String replace(String text, String searchString, String replacement) {
		return StringUtils.replace(text, searchString, replacement);
	}

	public static String replaceAll(String text, String searchString, String replacement) {

		for (int i = 0; i < text.length(); i++) {
			if (String.valueOf(text.charAt(i)).equals(searchString)) {
				text = text.replace(searchString, replacement);
			}
		}
		return text;
	}

	/**
	 * 2차원 문자열 배열에서 해당 문자열의 인덱스를 반환(return searchString index in Dimension String array )
	 * from wiseframework getIndex 
	 * difference: 첫번째 인덱스를 넘기고 두번째 인덱스만 리턴 받음 
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static int[] getIndexFromDimensionStringArray(String[][] strComplexArray, String searchString) {
		for (int i = 0; i < strComplexArray.length; i++) {
			for (int j = 0; j < strComplexArray.length; j++) {
				if (strComplexArray[i][j].equals(searchString)) {
					return new int[] {i, j};
				}
			}
		}
		return new int[] {-1, -1};
	}

	/**
	 * convert \n to &lt;br/&gt;
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String nToBr(String str) {
		return StringUtils.replace(str, "\n", "<br/>");
	}

	/**
	 * convert &lt;br/&gt; to \n
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String brToN(String str) {
		return StringUtils.replace(str, "<br/>", "\n");
	}

	/**
	 * 문자열 내에 delimeter 로 구분된 항목 들을 sql in 절에 맞는 형태로 변형(for in query)
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String forInQuery(String str, String delimeter) {
		String[] split = StringUtils.split(str, delimeter);
		String join = StringUtils.join(split, "', '");
		return String.format("('%s')", join);
	}

	/**
	 * Url encoding
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String encodeUrl(String str) {
		/* @formatter:off */
		String[][] replaceMapArray = { {"%", "%25"}, {"#", "%23"}, {"&", "%26"}, {"'", "%27"}, {"+", "%2B"}, {":", "%3A"}, {";", "%3B"}, {"=", "%3D"}, {"\"", "%22"}, {" ", "+"}};
		/* @formatter:on */

		StrBuilder strBuilder = new StrBuilder(str);
		for (String[] replaceMap : replaceMapArray) {
			strBuilder.replaceAll(replaceMap[0], replaceMap[1]);
		}
		return strBuilder.toString();
	}

	/**
	 * 문자열 덧셈
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String add(String value1, String value2) {
		return new BigDecimal(value1).add(new BigDecimal(value2)).toString();
	}

	/**
	 * 문자열 뺄셈
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String subtract(String value1, String value2) {
		return new BigDecimal(value1).subtract(new BigDecimal(value2)).toString();
	}

	/**
	 * 문자열 곱셈
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String multiply(String value1, String value2) {
		return new BigDecimal(value1).multiply(new BigDecimal(value2)).toString();
	}

	/**
	 * 문자열 나눗셈 rounding 모드와 자릿수 지정
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String divide(String value1, String value2, int scale, int roundMode) {
		return new BigDecimal(value1).divide(new BigDecimal(value2), scale, roundMode).toString();
	}

	/**
	 * 문자열 나눗셈 반올림 할 자리수 지정
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String divide(String value1, String value2, int scale) {
		return divide(value1, value2, scale, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 문자열 나눗셈 소숫점 둘째 자리 까지
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String divide(String value1, String value2) {
		return divide(value1, value2, 2);
	}

	/**
	 * 문자열 나머지(mod, %)
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String remainder(String value1, String value2) {
		return new BigDecimal(value1).remainder(new BigDecimal(value2)).toString();
	}

	/**
	 * 문자열을 srcEncodingType에서 targetEncodingType으로 변환
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String convertEncoding(String str, String srcEncodingType, String targetEncodingType) throws UnsupportedEncodingException {
		return StringUtils.toString(str.getBytes(srcEncodingType), targetEncodingType);
	}

	/**
	 * 8859_1문자열을 KSC5601로 변환
	 * 변환에 실패하면 null을 반환
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String e2k(String str) {
		try {
			return convertEncoding(str, "8859_1", "KSC5601");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * KSC5601문자열을 8859_1로 변환
	 * 변환에 실패하면 null을 반환
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String k2e(String str) {
		try {
			return convertEncoding(str, "KSC5601", "8859_1");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/**
	 * 오른쪽부터 length 만큼. masking
	 * 2013. 3. 18.
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String rMask(String str, int length) {
		return StringUtils.rightPad(str.substring(0,str.length() - length), str.length(), "*");
	}
	
	/**
	 * COOKIE에서 JSON 읽어 올때 UTF-16으로 인코딩 변경
	 * @author 최은혁
	 * @date 2014.4.21
	 */
	public static String unescape(String escaped) throws UnsupportedEncodingException
    {
        String str = escaped.replaceAll("%0", "%u000");
        str = str.replaceAll("%1", "%u001");
        str = str.replaceAll("%2", "%u002");
        str = str.replaceAll("%3", "%u003");
        str = str.replaceAll("%4", "%u004");
        str = str.replaceAll("%5", "%u005");
        str = str.replaceAll("%6", "%u006");
        str = str.replaceAll("%7", "%u007");
        str = str.replaceAll("%8", "%u008");
        str = str.replaceAll("%9", "%u009");
        str = str.replaceAll("%A", "%u00A");
        str = str.replaceAll("%B", "%u00B");
        str = str.replaceAll("%C", "%u00C");
        str = str.replaceAll("%D", "%u00D");
        str = str.replaceAll("%E", "%u00E");
        str = str.replaceAll("%F", "%u00F");

        String [] arr = str.split("%u");
        Vector<String> vec = new Vector<String>();
        if(arr[0]!=null&&!arr[0].equals(""))
        {
            vec.add(arr[0]);
        }
        for (int i = 1 ; i < arr.length  ; i++) {
            if(arr[i]!=null&&!arr[i].equals(""))
            {
                vec.add("%"+arr[i].substring(0, 2));
                vec.add("%"+arr[i].substring(2));
            }
        }
        str = "";
        for (String string : vec) {
            str += string;
        }
        
        return URLDecoder.decode(str,"UTF-16");
    }
	
	/**
	 * 문자가 길경우에 특정 바이트 단위 길이로 자른다
	 * @author 최은혁
	 * @date 2014.6.3
	 */
	public static String getStringOfByte(String str,int byteSize) throws Exception {
		int rSize = 0;
		int len = 0;

		if( str.getBytes().length > byteSize ) {
			for( ; rSize < str.length(); rSize++ ) {
				if( str.charAt( rSize ) > 0x007F )
					len += 2;
				else
					len++;

				if( len > byteSize ) break;
			}
			str = str.substring( 0, rSize );
		}

		return str;

	}	
	public static String stringToHex(String s) {
	    String result = "";

	    for (int i = 0; i < s.length(); i++) {
	      result += String.format("%02X", (int) s.charAt(i));
	    }

	    return result;
	  }
    /**
     * JSON (String)을 문자열로 변환하여 클라이언트에 전송.
     * AJax 용. 
     */
    public static void responseJsonValue(HttpServletResponse response, Object value) {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        
        try {
            response.getWriter().print( mapper.writeValueAsString(value));
        } catch (IOException ex) {
            //LOGGER.error("responseJsonValue");
        }        
    }
    
    public static String text2Html(String txt) {
        txt = txt.replaceAll(" ", "&nbsp");
        return txt.replaceAll("\n", "<br>");
    } 
}
