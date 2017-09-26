package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.StringUtil;
import Token.TokenInfo;
import Token.TokenUtil;

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
		String returnUrl = request.getParameter("returnUrl");
		
		StringBuffer loginUrl = new StringBuffer("/SsoServer/login.html");
		StringBuffer indexUrl = new StringBuffer("/SsoServer/index.html");
	    //有url，就是应用发来的登录认证    
		String username = request.getParameter("pwd");
		String password = request.getParameter("user"); 
	    if(username.equals(password)){
	        //在cookie中存放标识，用以判断是否已经登录
	        Cookie cookie = new Cookie("sso", username);
	        //设置cookie的作用域
	        cookie.setPath("/SsoServer");
            response.addCookie(cookie);
            //cookie.setPath("/");// 同服共享
            
	        //生成令牌
	        if(StringUtil.isUnEmpty(returnUrl)){
	            String tokenId = UUID.randomUUID().toString();
    	        TokenInfo ti = new TokenInfo();
    	        ti.setGlobalId(request.getSession().getId());
    	        ti.setSsoClientId(returnUrl);
    	        ti.setUserName(username);
    	        TokenUtil.setToken(tokenId, ti);
    	        //将令牌发送给客户端
    	        response.sendRedirect(returnUrl+"?"+"tokenId=" + tokenId);
	        }else{
	            response.sendRedirect(indexUrl.toString()); 
	        }
	    }else{
            response.sendRedirect(loginUrl.toString());
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
