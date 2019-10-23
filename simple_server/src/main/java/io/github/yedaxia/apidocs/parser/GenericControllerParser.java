package io.github.yedaxia.apidocs.parser;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import io.github.yedaxia.apidocs.Utils;

/**
 * 由于原解析器不支持blade-mvc的方式
 * 覆盖原解析器，增加自定义必填项支持
 * @author pyl
 */
public class GenericControllerParser extends AbsControllerParser {

	private String IsRequired = "[required]";
	
    @Override
    protected void afterHandleMethod(RequestNode requestNode, MethodDeclaration md) {
        md.getAnnotationByName("ApiDoc").ifPresent(an -> {
            if(an instanceof NormalAnnotationExpr){
                ((NormalAnnotationExpr)an).getPairs().forEach(p -> {
                    String n = p.getNameAsString();
                    if(n.equals("url")){
                        requestNode.setUrl(Utils.removeQuotations(p.getValue().toString()));
                    }else if(n.equals("method")){
                        requestNode.addMethod(Utils.removeQuotations(p.getValue().toString()));
                    }
                });
            }
        });
        requestNode.getParamNodes().forEach(p ->{
        	if(p.getDescription().indexOf(IsRequired)>0){
        		p.setRequired(true);
        		p.setDescription(p.getDescription().replace(IsRequired, ""));
        	}
        	
        });
    }

}
