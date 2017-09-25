package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/auth/login")
public class AuthLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthLogin() {
        super();
    }

	/**
	 * ��¼�˺�����У��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object clientUrl = request.getSession().getAttribute("clientUrl");
		//������У��
	        
	    //��url������Ӧ�÷����ĵ�¼��֤    
	    if(clientUrl != null){
	        
	    }
	    if(request.getParameter("user").equals(request.getParameter("pwd"))){
	        request.getSession().setAttribute("username", request.getParameter("user"));
	        response.sendRedirect("/SsoServer/index.html");
	    }else{
            response.sendRedirect("/SsoServer/login.html");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
