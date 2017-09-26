package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {
    public static String getVal(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName().equals(name)){
                return c.getValue();
            }
        }
        return name;
    }
    
    public static void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals(name)){
                    c.setMaxAge(0);
                    c.setValue(null);
                    response.addCookie(c);
                    return;
                }
            }
        }
    }
}
