package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;

import com.google.gson.Gson;

import constant.Commons;
import Session.GlobalSessions;
import Token.TokenInfo;
import Token.TokenUtil;

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
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter(Commons.token);
		String returnUrl = request.getParameter(Commons.returnUrl);
		String loaclsessionId = request.getParameter(Commons.loaclSessionId);
		//String localSessionId = request.getParameter(Commons.loaclSessionId);
		
		//做令牌校验
	    if(token == null){
	        System.out.println("令牌不存在！");
	    //没有令牌校验，但是有url，就是应用发来的登录认证    
	    }else {
	        if(returnUrl == null){
	            System.out.println("客户端地址不存在");
	        }else{
	            TokenInfo ti = TokenUtil.getToken(token);
	            TokenUtil.deleteToke(token);
	            if(ti == null){
	                //资源不存在
	                response.setStatus(404);
	            }else{
	                //返回正常值
	                response.setStatus(200);
    	            String sessionId = request.getParameter(Commons.globalSessionId);
    	            
                    //把要登出的应用信息存放在认证中心的session中
    	            Map<String,String> loginoutMap = null;
                    if(request.getSession().getAttribute(Commons.loginout) != null){
                        if(request.getSession().getAttribute(Commons.loginout) instanceof List){
                            loginoutMap = (Map<String,String>)request.getSession().getAttribute(Commons.loginout) ;
                        }
                    }else{
                        loginoutMap = new HashMap<String,String>();
                    }
                    //把不带路径的地址存放到要登出的list中
                    String url = StringUtil.getUrl(returnUrl);
                    loginoutMap.put(loaclsessionId,url);
                    
                    HttpSession session = GlobalSessions.getSession(sessionId);
                    session.setAttribute(Commons.loginout, loginoutMap);
    	            System.out.println("AuthYerify = "+session.getId());
    	            
    	            //转换成json处理对象
    	            String tokenGson = new Gson().toJson(ti);
    	            response.getWriter().write(tokenGson);
	            }
	        }
	    }
	}
    
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
