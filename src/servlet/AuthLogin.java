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
	 * 登录账号密码校验
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object clientUrl = request.getSession().getAttribute("clientUrl");
		//做令牌校验
	        
	    //有url，就是应用发来的登录认证    
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
