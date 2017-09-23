package Token;

import redis.clients.jedis.Jedis;


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
    void setToken(String tokenId,TokenInfo tokenInfo){
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
    TokenInfo getToken(String tokenId){
        //��redis���ݿ���ȡ,��ɾ������
    	TokenInfo ti = (TokenInfo) SerializeUtil.unSerialize(jedis.get(tokenId.getBytes()));
        return ti;
    }
    
    /**
     * ɾ���Ѿ�У�������
     * @author hek
     * @date 2017��9��22������3:07:42
     */
    void deleteToke(String tokenId){
    	jedis.del(tokenId.getBytes());
    }
    
}
