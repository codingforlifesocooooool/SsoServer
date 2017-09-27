package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Commons;
import Session.GlobalSessions;
import Token.TokenInfo;
import Token.TokenUtil;
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
	 * ���ʵ�¼����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    System.out.println("LOGINSESSION = "+ request.getSession().getId());
	    String returnUrl = request.getParameter("returnUrl");
	    System.out.println("returnUrl = " + returnUrl);
        String loginUrl = "/SsoServer/login.html";
        String indexUrl = "/SsoServer/index.html";
	    //�ж�cookie���Ƿ���ڵ�¼��¼
	    Cookie[] cookies = request.getCookies();
	    if(cookies != null){
    	    for(Cookie c : cookies){
    	        if(c.getName().equals("sso")){
    	            if(StringUtil.isUnEmpty(returnUrl)){
    	                String tokenId =  TokenUtil.create(request, returnUrl);
    	                response.sendRedirect(returnUrl+"?"+Commons.token+"="+tokenId+"&"
    	                                    +Commons.globalSessionId+"="+request.getSession().getId());
    	            }else{
    	                response.sendRedirect(indexUrl);
    	            }
    	            return;
    	        }
    	    }
	    }
	    if(StringUtil.isUnEmpty(returnUrl)){
	        loginUrl = loginUrl + "?" +"returnUrl=" + returnUrl;
	        indexUrl = indexUrl + "?" +"returnUrl=" + returnUrl;
	    }
	    response.sendRedirect(loginUrl);
	    
	    //���session���иò�����˵���û��Ѿ���¼
	   /* if(request.getSession().getAttribute("username") != null){
	        response.sendRedirect(indexUrl);
	    }else{
	        response.sendRedirect(loginUrl);
	    }*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
