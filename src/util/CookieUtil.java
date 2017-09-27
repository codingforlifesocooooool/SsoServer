package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {
    /**
     * 得到对应cookie的值
     * @author hek
     * @date 2017年9月27日上午9:41:31
     * @param request
     * @param name
     * @return
     */
    public static String getVal(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals(name)){
                return c.getValue();
            }
        }
        return name;
    }
    
    /**
     * 删除cookie，但是要注意cookie作用域和被删除的要相同
     * @author hek
     * @date 2017年9月27日上午9:41:08
     * @param request
     * @param response
     * @param name
     */
    public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals(name)){
                    c.setMaxAge(0);
                    c.setValue(null);
                    c.setPath("/SsoServer");
                    response.addCookie(c);
                    return;
                }
            }
        }
    }
}
