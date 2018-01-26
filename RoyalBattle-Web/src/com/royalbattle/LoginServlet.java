package com.royalbattle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@EJB
    DatabaseBean databaseBean;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		response.setContentType("text/html;charset=\"UTF-8\"");
		
		PrintWriter out = response.getWriter();
		
		StringBuilder sbResponseMessage = new StringBuilder();
		
		try {
			databaseBean.createTable();
			
			sbResponseMessage.append("Connected successfull!");
			
		} catch (SQLException e) {
			e.printStackTrace();
			sbResponseMessage.append("SQL exception! ").append(e.getLocalizedMessage());
		}
		
		out.println("<br><H1>" + sbResponseMessage.toString() + "</H1>");
	
	}
}
