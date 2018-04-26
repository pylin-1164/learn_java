package com.pyl.util.zerowidth;

import org.apache.commons.lang3.StringUtils;

import com.pyl.util.StrBinaryTurn;

public class ZeroWidthToCode {
	
	public String zeroWidth2Text(String zeroStr){
		String binaryStr = zeroWdith2Binary(zeroStr);
		String text = binary2Text(binaryStr);
		return text;
	}

	private String zeroWdith2Binary(String zeroStr){
		String[] zeroWidthChars = StringUtils.split(zeroStr, "\ufeff");
		String[] binaryChars = new String[zeroWidthChars.length];
		for (int i = 0; i < binaryChars.length; i++) {
			if(zeroWidthChars[i].equals("\u200b")){
				binaryChars[i] = "1";
			}else if(zeroWidthChars[i].equals("\u200c")){
				binaryChars[i] = "0";
			}else if(zeroWidthChars[i].equals("\u200d")){
				binaryChars[i] = " ";
			}
		}
		String binaryStr = StringUtils.join(binaryChars,"");
		return binaryStr;
	}
	
	private String binary2Text(String binaryStr){
		String[] binaryArr = StringUtils.split(binaryStr," ");
		String text = new String("");
		StrBinaryTurn strBinaryTurn = new StrBinaryTurn();
		for (String binary : binaryArr) {
			String charCode = strBinaryTurn.binstrToStr(binary);
			text += charCode;
		}
		return text;
	}
	
	public static void main(String[] args) {
		CodeToZeroWidth codeToZeroWidth = new CodeToZeroWidth();
		String zeroWidthCdoe = codeToZeroWidth.saveZeroWidthCode("pyl1164aaa");
		ZeroWidthToCode zeroWidthToCode = new ZeroWidthToCode();
		String text = zeroWidthToCode.zeroWidth2Text(zeroWidthCdoe);
		System.out.println(text);
		
	}
}
