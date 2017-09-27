package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {
    /**
     * �õ���Ӧcookie��ֵ
     * @author hek
     * @date 2017��9��27������9:41:31
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
     * ɾ��cookie������Ҫע��cookie������ͱ�ɾ����Ҫ��ͬ
     * @author hek
     * @date 2017��9��27������9:41:08
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
