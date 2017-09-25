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
@WebServlet("/auth/verify")
public class AuthVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthVerify() {
        super();
    }

	/**
	 * 判断是否有令牌
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		String clientUrl = request.getParameter("clientUrl");
		//做令牌校验
	    if(token != null){
	        
	        
	    //没有令牌校验，但是有url，就是应用发来的登录认证    
	    }else if(clientUrl != null){
	        
	    }else{
	        response.sendRedirect("/SsoServer/Login.html");
	    }
	    
		System.out.println(request.getParameter("user"));
		System.out.println(request.getParameter("pwd"));
		response.sendRedirect("/SsoServer/index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
