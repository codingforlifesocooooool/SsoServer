package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.StringUtil;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * 访问登录界面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String returnUrl = request.getParameter("returnUrl");
	    String loginUrl = "/SsoServer/login.html";
	    String indexUrl = "/SsoServer/index.html";
	    
	    if(StringUtil.isUnEmpty(returnUrl)){
	        loginUrl = indexUrl + "?" +"returnUrl=" + returnUrl;
	        indexUrl = loginUrl + "?" +"returnUrl=" + returnUrl;
	    }
	    //如果session中有该参数，说明用户已经登录
	    if(request.getSession().getAttribute("username") != null){
	        response.sendRedirect(loginUrl);
	    }else{
	        response.sendRedirect(indexUrl);
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
