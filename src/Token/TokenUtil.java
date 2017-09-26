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
     * ����һ������
     * @author hek
     * @date 2017��9��22������3:08:12
     */
    public static void setToken(String tokenId,TokenInfo tokenInfo){
        //�����redis���ݿ��У�����������������60��
        jedis.set(tokenId.getBytes(), SerializeUtil.ObjTOSerialize(tokenInfo));
        jedis.expire(tokenId.getBytes(), 60);
        
    }
    
    /**
     * ͨ���ͻ�Id�õ���Ӧ��������֤
     * @author hek
     * @date 2017��9��22������3:08:04
     * @param cilent
     * @return
     */
    public static TokenInfo getToken(String tokenId){
        //��redis���ݿ���ȡ,��ɾ������
    	TokenInfo ti = (TokenInfo) SerializeUtil.unSerialize(jedis.get(tokenId.getBytes()));
        return ti;
    }
    
    /**
     * ɾ���Ѿ�У�������
     * @author hek
     * @date 2017��9��22������3:07:42
     */
    public static void deleteToke(String tokenId){
    	jedis.del(tokenId.getBytes());
    }
    public static void create(HttpServletRequest request,String returnUrl){
      create(request,returnUrl,null);
    }
    
    /**
     * �������ƺ����ƶ�Ӧ��Ϣ
     * @author hek
     * @date 2017��9��26������11:58:51
     * @param request
     * @param returnUrl
     * @param username �û��������û�еĻ��ʹ�cookie���ȡ
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
