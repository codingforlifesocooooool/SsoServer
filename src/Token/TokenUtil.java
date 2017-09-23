package Token;

import redis.clients.jedis.Jedis;


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
    void setToken(String tokenId,TokenInfo tokenInfo){
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
    TokenInfo getToken(String tokenId){
        //从redis数据库中取,并删除令牌
    	TokenInfo ti = (TokenInfo) SerializeUtil.unSerialize(jedis.get(tokenId.getBytes()));
        return ti;
    }
    
    /**
     * 删除已经校验的令牌
     * @author hek
     * @date 2017年9月22日下午3:07:42
     */
    void deleteToke(String tokenId){
    	jedis.del(tokenId.getBytes());
    }
    
}
