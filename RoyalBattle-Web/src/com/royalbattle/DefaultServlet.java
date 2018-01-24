package com.royalbattle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DefaultServlet
 */
@WebServlet("/login")
public class DefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DefaultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String parameter = request.getParameter("parameter");
        		
		response.setContentType("text/html;charset=\"UTF-8\"");
		
		PrintWriter out = response.getWriter();
		
		out.println("<h2 align=\"center\">\"Королевская Битва\" - многопользовательская браузерная онлайн-RPG</h2>");
		out.println("<br>");
		out.println("<h3 align=\"right\"><i>\"Восхитительный дизайн и инновационный подход к геймплею\"</i></h3>");
		out.println("<p align=\"right\">\"Страна Ир\"</p>");
		out.println("<h3 align=\"right\"><i>\"The most breath-taking adventure you've ever seen\"</i></h3>");
		out.println("<p align=\"right\">\"Weird\"</p>");
		out.println("<br>");
		out.println("<br>");
		
		out.println("<form name=\"loginForm\" method=\"post\" action=\"loginServlet\">");
		out.println("Имя пользователя: <br/><input type=\"text\" name=\"username\"/> <br/>");
		out.println("Пароль: <br/><input type=\"password\" name=\"password\"/> <br/>");
		out.println("<input type=\"submit\" value=\"Вход/регистрация\" />");
		out.println("</form>");
	}

}
