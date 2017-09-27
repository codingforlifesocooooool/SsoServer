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
	 * �ж��Ƿ�������
	 */
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter(Commons.token);
		String returnUrl = request.getParameter(Commons.returnUrl);
		String loaclsessionId = request.getParameter(Commons.loaclSessionId);
		//String localSessionId = request.getParameter(Commons.loaclSessionId);
		
		//������У��
	    if(token == null){
	        System.out.println("���Ʋ����ڣ�");
	    //û������У�飬������url������Ӧ�÷����ĵ�¼��֤    
	    }else {
	        if(returnUrl == null){
	            System.out.println("�ͻ��˵�ַ������");
	        }else{
	            TokenInfo ti = TokenUtil.getToken(token);
	            TokenUtil.deleteToke(token);
	            if(ti == null){
	                //��Դ������
	                response.setStatus(404);
	            }else{
	                //��������ֵ
	                response.setStatus(200);
    	            String sessionId = request.getParameter(Commons.globalSessionId);
    	            
                    //��Ҫ�ǳ���Ӧ����Ϣ�������֤���ĵ�session��
    	            Map<String,String> loginoutMap = null;
                    if(request.getSession().getAttribute(Commons.loginout) != null){
                        if(request.getSession().getAttribute(Commons.loginout) instanceof List){
                            loginoutMap = (Map<String,String>)request.getSession().getAttribute(Commons.loginout) ;
                        }
                    }else{
                        loginoutMap = new HashMap<String,String>();
                    }
                    //�Ѳ���·���ĵ�ַ��ŵ�Ҫ�ǳ���list��
                    String url = StringUtil.getUrl(returnUrl);
                    loginoutMap.put(loaclsessionId,url);
                    
                    HttpSession session = GlobalSessions.getSession(sessionId);
                    session.setAttribute(Commons.loginout, loginoutMap);
    	            System.out.println("AuthYerify = "+session.getId());
    	            
    	            //ת����json�������
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
