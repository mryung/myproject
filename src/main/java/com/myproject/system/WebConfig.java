package com.myproject.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.web.context.ContextLoader;

public class WebConfig {
	static public String hostName;
	static public String serverPath;
	static public String cssPath;
	static public String jsPath;
	static public String imgPath;
	
	static public String host = "host";
	static public String server = "serverpath";
	static public String js = "js";
	static public String css = "css";
	static public String img = "img";
	
	static{
		Properties properties = new Properties();
		try {
			 InputStream intputstream =ContextLoader
					.getCurrentWebApplicationContext()
					.getServletContext()
					.getResourceAsStream("/WEB-INF/classes/applicationServlet.properties");
			 properties.load(intputstream);
			 hostName = properties.getProperty(host);
			 serverPath = properties.getProperty(server);
			 jsPath = properties.getProperty(js);
			 cssPath = properties.getProperty(css);
			 imgPath = properties.getProperty(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
