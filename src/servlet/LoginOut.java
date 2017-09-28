package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CookieUtil;
import util.HttpUtil;
import util.StringUtil;
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
	    //如果从客户端传过来，所以无法通过cookieId来得到对应的session,需要客户端穿GlobalSessionId得到对应的cookie
	  /*  if(request.getParameter(Commons.globalSessionId) != null){
	        String globalSessionId = request.getParameter(Commons.globalSessionId);
	        session = GlobalSessions.getSession(globalSessionId);
	    }else{
	        session = request.getSession();
	    }*/
	    //session不是原来的，因为从客户端传过来，所以无法通过cookieId来得到对应的session,需要客户端穿GlobalSessionId得到对应的cookie
	    System.out.println("loginout-sessionId = "+session.getId());
	    String returnUrl = request.getParameter(Commons.returnUrl);
	    //String unOutlocalSessionId = request.getParameter(Commons.loaclSessionId);
	    //删除cookie标识,但是如果是从应用端发来的http通信，response可能不是相同的，无法删除cookie，所以应用端应该重定向到这里
	   // CookieUtil.delCookie(request,response,"sso");
	    Cookie cookie = new Cookie("sso", null);
        cookie.setPath("/SsoServer");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
	    
	    if(session.getAttribute(Commons.loginout) != null){
	        //向每个登陆的系统发送登出请求
	        Map<String,String> loginoutMap  = null;
	        if(session.getAttribute(Commons.loginout) != null){
	            loginoutMap = (Map<String,String>) session.getAttribute(Commons.loginout);
	           /* if(unOutlocalSessionId != null){
	                loginoutMap.remove(unOutlocalSessionId);
	            }*/
	            for(String localSessionId : loginoutMap.keySet()){
	                String loginStr = loginoutMap.get(localSessionId);
	                String url = loginStr+"/loginOut";
                    Map<String,String> params = new HashMap<String, String>();
                    params.put(Commons.isGobal,"Yes");
                    params.put(Commons.loaclSessionId, localSessionId);
                    HttpUtil.http(url, params);
	            }
	        }
	    }
	    
	    request.getSession().invalidate();
	    
	    if(StringUtil.isEmpty(returnUrl))
	        response.sendRedirect("/SsoServer/login");
	    else
	        response.sendRedirect(returnUrl);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
