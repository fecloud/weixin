package com.jd.samples;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setStatus(200);

		String filePath = this.getServletConfig().getServletContext().getRealPath("/");
		filePath += "log.log";
		FileWriter writer = new FileWriter(filePath, true);
		writer.append("==============================================");
		Enumeration<String> headnames = request.getHeaderNames();
		while (headnames.hasMoreElements()) {
			String string = (String) headnames.nextElement();
			writer.append(string).append(":").append(request.getHeader(string)).append("\r\n");
		}

		writer.append("content size:" + request.getContentLength()).append("\r\n");
		writer.append("CharacterEncoding:" + request.getCharacterEncoding()).append("\r\n");
		InputStream in = request.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while (null != (line = reader.readLine())) {
			writer.append(line).append("\r\n");
		}
		writer.flush();
		reader.close();

	}

}
