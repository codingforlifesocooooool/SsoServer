package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import constant.Commons;
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
		String localSessionId = request.getParameter(Commons.loaclSessionId);
		
		//������У��
	    if(token == null){
	        System.out.println("���Ʋ����ڣ�");
	    //û������У�飬������url������Ӧ�÷����ĵ�¼��֤    
	    }else {
	        if(returnUrl == null){
	            System.out.println("�ͻ��˵�ַ������");
	        }else{
	            TokenInfo ti = TokenUtil.getToken(token);
	            //ת����json�������
	            Map<String,String> loginoutMap = null;
	            if(request.getSession().getAttribute(Commons.loginout) != null)
	                loginoutMap = (Map<String, String>) request.getSession().getAttribute(Commons.loginout);
	            else
	                loginoutMap = new HashMap<String, String>();
	            loginoutMap.put(localSessionId, returnUrl);
	            if(ti != null){
	                //��Դ������
	                response.setStatus(404);
	            }else{
	                //��������ֵ
	                response.setStatus(200);
    	            request.getSession().setAttribute(Commons.loginout, loginoutMap);
    	            response.getWriter().write(new Gson().toJson(ti));
	            }
	        }
	    }
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
