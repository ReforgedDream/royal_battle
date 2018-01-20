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
@WebServlet("/DefaultServlet")
public class DefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DefaultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //@EJB
    //TestBean testBean;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String parameter = request.getParameter("parameter");
        
		System.out.println("Executing doGet method from DefaultServlet");
		
		response.setContentType("text/html;charset=\"UTF-8\"");
		
		PrintWriter out = response.getWriter();
		//out.println(testBean.testThisBean());
		
		out.println("Served at: " + request.getContextPath() + " <--- request.getContextPath()");
		out.println("<br>");
		out.println("Parameter: " + parameter);
		out.println("<br>");
		out.println("Character encoding: " + response.getCharacterEncoding());
		out.println("<br>");
		out.println("Cyrillic text: ну наконец-то");
	}

}
