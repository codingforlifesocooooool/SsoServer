package Token;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import redis.clients.jedis.Jedis;
import util.CookieUtil;


public class TokenUtil {
	private static Jedis jedis;
    
    static{
        jedis = new Jedis("127.0.0.1",6379);
    }
    
    /**
     * 生成一个令牌
     * @author hek
     * @date 2017年9月22日下午3:08:12
     */
    public static void setToken(String tokenId,TokenInfo tokenInfo){
        //存放在redis数据库中，并且数据生命周期60秒
        jedis.set(tokenId.getBytes(), SerializeUtil.ObjTOSerialize(tokenInfo));
        jedis.expire(tokenId.getBytes(), 60);
        
    }
    
    /**
     * 通过客户Id得到对应令牌做验证
     * @author hek
     * @date 2017年9月22日下午3:08:04
     * @param cilent
     * @return
     */
    public static TokenInfo getToken(String tokenId){
        //从redis数据库中取,并删除令牌
    	TokenInfo ti = (TokenInfo) SerializeUtil.unSerialize(jedis.get(tokenId.getBytes()));
        return ti;
    }
    
    /**
     * 删除已经校验的令牌
     * @author hek
     * @date 2017年9月22日下午3:07:42
     */
    public static void deleteToke(String tokenId){
    	jedis.del(tokenId.getBytes());
    }
    public static void create(HttpServletRequest request,String returnUrl){
      create(request,returnUrl,null);
    }
    
    /**
     * 生成令牌和令牌对应信息
     * @author hek
     * @date 2017年9月26日上午11:58:51
     * @param request
     * @param returnUrl
     * @param username 用户名，如果没有的话就从cookie里获取
     */
    public static void create(HttpServletRequest request,String returnUrl,String username){
        String tokenId = UUID.randomUUID().toString();
        TokenInfo ti = new TokenInfo();
        ti.setGlobalId(request.getSession().getId());
        ti.setSsoClientId(returnUrl);
        if(username != null)
            ti.setUserName(username);
        else
            ti.setUserName( CookieUtil.getVal(request, "sso"));
        TokenUtil.setToken(tokenId, ti);
    }
    
}
