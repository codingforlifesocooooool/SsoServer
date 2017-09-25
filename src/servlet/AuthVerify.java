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
	 * 判断是否有令牌
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		String returnUrl = request.getParameter("returnUrl");
		//做令牌校验
	    if(token == null){
	        System.out.println("令牌不存在！");
	    //没有令牌校验，但是有url，就是应用发来的登录认证    
	    }else {
	        if(returnUrl == null){
	            System.out.println("客户端地址不存在");
	        }else{
	            TokenInfo ti = TokenUtil.getToken(token);
	            //转换成json处理对象
	        }
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
