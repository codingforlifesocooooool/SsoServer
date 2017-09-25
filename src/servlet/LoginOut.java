package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Session.GlobalSessions;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/auth/loginOut")
public class LoginOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOut() {
        super();
    }

	/**
	 * µÇ³öÏµÍ³
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    GlobalSessions.delSession(request.getSession().getId());
	    request.getSession().invalidate();
		response.sendRedirect("/SsoServer/login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
