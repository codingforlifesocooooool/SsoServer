package servlet;

import java.io.IOException;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		String returnUrl = request.getParameter("returnUrl");
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
	        }
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
