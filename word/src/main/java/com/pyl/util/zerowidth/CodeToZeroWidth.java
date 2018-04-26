package com.pyl.util.zerowidth;

import org.apache.commons.lang3.StringUtils;

/**
 * 零宽字符
 * @author pyl
 *
 */
public class CodeToZeroWidth {
	
	public String saveZeroWidthCode(String code){
		String text2binary = text2binary(code);
		String binaryToZeroWidth = binaryToZeroWidth(text2binary);
		return binaryToZeroWidth;
	}
	
	private String binaryToZeroWidth(String binaryCode){
		char[] binaryChars = binaryCode.toCharArray();
		String[] convertStrs = new String[binaryChars.length];
		for (int i = 0; i < binaryChars.length; i++) {
			char binary = binaryChars[i];
			String binary2ZeroWidthCode = binary2ZeroWidthCode(binary);
			convertStrs[i] = binary2ZeroWidthCode;
		}
		String result = StringUtils.join(convertStrs, "\ufeff");
		System.out.println(result);
		return result;
	}
	
	private String binary2ZeroWidthCode(char binary){
		if(String.valueOf(binary).equals(" ")){
			return "\u200d";
		}
		int num = Integer.parseInt(String.valueOf(binary));
		if (num == 1) {
	      return "\u200b"; // invisible &#8203;
	    } else if (num == 0) {
	      return "\u200c"; // invisible &#8204;
	    }
	    return "\u200d"; 
	}
	
	private String text2binary(String code){
//		code.split('').map(char => zeroPad(char.charCodeAt(0).toString(2))).join(' ')
		char[] strChar=code.toCharArray();
		String[] newStrs = new String[strChar.length];
		for (int i=0;i<strChar.length;i++) {
			String binaryStr = Integer.toBinaryString(strChar[i]);
			String zeroPad = zeroPad(binaryStr);
			newStrs[i] = zeroPad;
		}
		String binaryCode = StringUtils.join(newStrs, " ");
		System.out.println(binaryCode);
		return binaryCode;
	}
	
	private String zeroPad(String binaryStr){
//		const zeroPad = num => '00000000'.slice(String(num).length) + num;
		binaryStr = new String("00000000").substring(binaryStr.length()) + binaryStr;
		return binaryStr;
	}

	public static void main(String[] args) {
		CodeToZeroWidth zeroWidthCode = new CodeToZeroWidth();
		zeroWidthCode.saveZeroWidthCode("pyl1164");
	}
}
