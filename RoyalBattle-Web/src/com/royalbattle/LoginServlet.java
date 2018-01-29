package com.royalbattle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.IncorrectPasswordException;
import exceptions.PasswordIsNullException;
import exceptions.UsernameOutOfBoundsException;
import exceptions.UsersLimitReachedException;
import utils.Credentials;
import utils.SettingsConst;

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
		
		response.setContentType("text/html;charset=\"UTF-8\"");
		
		PrintWriter out = response.getWriter();
		
		StringBuilder responseMessage = new StringBuilder();
		
		GameChar authorizedChar = null;
		
		try {
			Credentials credentials = new Credentials(request.getParameter("username"), request.getParameter("password"));
			
			authorizedChar = databaseBean.doLogin(credentials);
			
			responseMessage.append("Добро пожаловать! <br>");
			responseMessage.append(authorizedChar.toString());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			responseMessage.append("SQL exception! ");
			responseMessage.append(e.getLocalizedMessage());
		} catch (UsernameOutOfBoundsException e1) {

			e1.printStackTrace();
			responseMessage.append("Имя пользователя должно быть не менее 1 и не более ");
			responseMessage.append(SettingsConst.MAX_NAME_LENGTH);
			responseMessage.append(" символов");
		} catch (PasswordIsNullException e2) {
			
			e2.printStackTrace();
			responseMessage.append("Пароль не должен быть пустым");
		} catch (IncorrectPasswordException e3) {

			e3.printStackTrace();
			responseMessage.append("Неверный пароль");
		} catch (UsersLimitReachedException e4) {

			e4.printStackTrace();
			responseMessage.append("Достигнут лимит в ");
			responseMessage.append(SettingsConst.MAX_USERS_COUNT);
			responseMessage.append(" пользователей, регистрация невозможна");
		}
		
		if(authorizedChar == null) {	
			responseMessage.append("<br><form name=\"backToLoginForm\" method=\"get\" action=\"login\">");
			responseMessage.append("<input type=\"submit\" value=\"Назад\" />");
			responseMessage.append("</form>");
		} else {

			response.sendRedirect("game");
		}
		
		out.println("<br>" + responseMessage.toString());
	}
}
