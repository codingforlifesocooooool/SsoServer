package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * ��¼�˺�����У��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String returnUrl = request.getParameter("returnUrl");
		
		StringBuffer loginUrl = new StringBuffer("/SsoServer/login.html");
		StringBuffer indexUrl = new StringBuffer("/SsoServer/index.html");
	    //��url������Ӧ�÷����ĵ�¼��֤    
		String username = request.getParameter("pwd");
		String password = request.getParameter("user"); 
	    if(username.equals(password)){
	        //��������
	        String tokenId = UUID.randomUUID().toString();
	        TokenInfo ti = new TokenInfo();
	        ti.setGlobalId(request.getSession().getId());
	        ti.setSsoClientId(returnUrl);
	        ti.setUserName(username);
	        TokenUtil.setToken(tokenId, ti);
	        response.sendRedirect(returnUrl+"?"+"tokenId=" + tokenId);
	    }else{
            response.sendRedirect("/SsoServer/login.html");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
