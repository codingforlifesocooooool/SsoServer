package Token;

import redis.clients.jedis.Jedis;


public class TokenUtil {
    
    static{
        Jedis jedis = new Jedis("127.0.0.1",6379);
    }
    
    /**
     * ����һ������
     * @author hek
     * @date 2017��9��22������3:08:12
     */
    void setToken(String clientId){
        //�����redis���ݿ��У�����������������60��
        
        
    }
    
    /**
     * ͨ���ͻ�Id�õ���Ӧ��������֤
     * @author hek
     * @date 2017��9��22������3:08:04
     * @param cilent
     * @return
     */
    TokenInfo getToken(String clientId){
        //��redis���ݿ���ȡ
        return null;
    }
    
    /**
     * ɾ���Ѿ�У�������
     * @author hek
     * @date 2017��9��22������3:07:42
     */
    void deleteToke(){
        
        
    }
    
}
