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
	 * �ǳ�ϵͳ
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    HttpSession session = request.getSession();
	    System.out.println("loginout-sessionId = "+session.getId());
	    String unOutlocalSessionId = request.getParameter(Commons.loaclSessionId);
	    //ɾ��cookie��ʶ
	    CookieUtil.delCookie(request,response,"sso");
	    if(session.getAttribute(Commons.loginout) != null){
	        //��ÿ����½��ϵͳ���͵ǳ�����
	        Map<String,String> loginoutMap  = null;
	        if(session.getAttribute(Commons.loginout) != null){
	            loginoutMap = (Map<String,String>) session.getAttribute(Commons.loginout);
	            if(unOutlocalSessionId != null){
	                loginoutMap.remove(unOutlocalSessionId);
	            }
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
	    
	    if(unOutlocalSessionId == null)
	        response.sendRedirect("/SsoServer/login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
