package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CookieUtil;
import constant.Commons;
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
	 * 登出系统
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    HttpSession session = request.getSession();
	    //删除cookie标识
	    CookieUtil.delCookie(request,response,"sso");
	    
	    if(session.getAttribute(Commons.loginout) != null){
	        Map<String,String> map = (Map<String, String>) session.getAttribute(Commons.loginout);
	    }else{
	        
	    };
	    
	    GlobalSessions.delSession(request.getSession().getId());
	    request.getSession().invalidate();
		response.sendRedirect("/SsoServer/login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
