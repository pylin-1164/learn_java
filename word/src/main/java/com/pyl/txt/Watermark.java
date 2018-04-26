package com.pyl.txt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.pyl.util.zerowidth.CodeToZeroWidth;
import com.pyl.util.zerowidth.ZeroWidthToCode;

/**
 * 通过零宽字符方式给txt文件添加水印
 * 校验字符中的水印地址：https://umpox.github.io/zero-width-detection/
 * @author pyl
 *
 */
public class Watermark {
	
	String fileName = System.getProperty("user.dir") + "/watermark.txt";
	
	public void addWaterMark() throws IOException{
		File file = new File(fileName);
		if(!file.exists()){
			FileUtils.write(file, "init txt data ...", "UTF-8");;
		}
		
		//创建水印文字
		CodeToZeroWidth codeToZeroWidth = new CodeToZeroWidth();
		String watermark = codeToZeroWidth.saveZeroWidthCode("WATERMARK");
		
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		List<String> newLines = new ArrayList<String>();
		for (String line : lines) {
			boolean hasWatermark = checkWaterMark(line);
			if(!hasWatermark){
				line = joinWatermark(line, watermark);
			}
			newLines.add(line);
		}
		FileUtils.writeLines(file, newLines, false);
	}
	
	private String joinWatermark(String content,String watermark){
		if(StringUtils.isBlank(content)){
			return content;
		}
		int randomNum = new Random().nextInt(content.length());
		String beforeStr = content.substring(0,randomNum);
		String endStr = content.substring(randomNum);
		String[] splits = new String[]{beforeStr,watermark,endStr};
		content = StringUtils.join(splits,"");
		return content;
	}
	
	
	private boolean checkWaterMark(String content){
		//匹配零宽字符
		String regEx = "(\\u200d|\\u200b|\\u200c|\\ufeff){1,}";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher matcher = pattern.matcher(content);
	    // 字符串是否与正则表达式相匹配
	    boolean rs = matcher.find();
	    if(rs){
	    	String code = matcher.group();
	    	String text = new ZeroWidthToCode().zeroWidth2Text(code);
	    	System.out.println(text);
	    	return true;
	    }
	    return false;
	}
	
	public static void main(String[] args) {
		Watermark watermark = new Watermark();
		try {
			watermark.addWaterMark();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
