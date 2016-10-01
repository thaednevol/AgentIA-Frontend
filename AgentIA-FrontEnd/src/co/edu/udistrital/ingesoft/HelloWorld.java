package co.edu.udistrital.ingesoft;

import java.io.BufferedReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5519800618253913908L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		
		String str;
		StringBuffer strBuffer = new StringBuffer();
		BufferedReader reader = req.getReader();
		while ((str = reader.readLine()) != null){
			strBuffer.append(str);
		}
		  
		System.out.println(strBuffer.toString());
		
		System.out.println("doPost");
		res.setContentType("text/html");
		res.getWriter().write("Te envío desde JAVA: "+strBuffer.toString());
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		
		System.out.println("doGet");
		doPost(req, res);
	}
}