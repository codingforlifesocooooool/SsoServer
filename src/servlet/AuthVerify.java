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
	 * �ж��Ƿ�������
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		String clientUrl = request.getParameter("clientUrl");
		//������У��
	    if(token != null){
	        
	        
	    //û������У�飬������url������Ӧ�÷����ĵ�¼��֤    
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
