package com.restful.demo.doc;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

public class ApiDocRunner {
	public static void main(String[] args) {
		DocsConfig config = new DocsConfig();
	    config.setProjectPath("F:\\work\\code\\bsg\\simple_server");
	    Docs.buildHtmlDocs(config);
	}
}
