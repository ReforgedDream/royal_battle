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
		
		String responseMessage = null;
		
		try {
			Credentials credentials = new Credentials(request.getParameter("username"), request.getParameter("password"));
			
			GameChar authorizedChar = databaseBean.doLogin(credentials);
			
			responseMessage = "Добро пожаловать! <br>" + authorizedChar.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
			responseMessage = "SQL exception! " + e.getLocalizedMessage();
		} catch (UsernameOutOfBoundsException e1) {

			e1.printStackTrace();
			responseMessage = "Имя пользователя должно быть не менее 1 и не более " + 
					Integer.toString(SettingsConst.MAX_NAME_LENGTH) +
					" символов";
		} catch (PasswordIsNullException e2) {
			
			e2.printStackTrace();
			responseMessage = "Пароль не должен быть пустым";
		} catch (IncorrectPasswordException e3) {

			e3.printStackTrace();
			responseMessage = "Неверный пароль";
		} catch (UsersLimitReachedException e4) {

			e4.printStackTrace();
			responseMessage = "Достигнут лимит в " +
					Integer.toString(SettingsConst.MAX_USERS_COUNT) +
					" пользователей, регистрация невозможна";
		}
		
		out.println("<br><H1>" + responseMessage + "</H1>");

	}
}
