

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Utility")
public class Utility
{

	   static Properties prop = new Properties();

	   public static void loadProperty(ServletContext servletContext) throws Exception {
	      String filePath = "/WEB-INF/config.properties";
	      InputStream is = servletContext.getResourceAsStream(filePath);

	      System.out.println("[DBG] Loaded: " + new File(filePath).getAbsolutePath());
	      prop.load(is);
	   }

	   public static String getProp(String key) {
	      return prop.getProperty(key).trim();
	   }
}

