package Token;

import java.io.Serializable;


/**
 * token对应的资源
 * @author hk
 *
 */
public class TokenInfo implements Serializable{
    private static final long serialVersionUID = 1730135296052999548L;
    
    private String UserName;
    private String ssoClientId;
    private String globalId;
    
    public String getUserName() {
    
        return UserName;
    }
    
    public void setUserName(String userName) {
    
        UserName = userName;
    }
    
    public String getSsoClientId() {
    
        return ssoClientId;
    }
    
    public void setSsoClientId(String ssoClientId) {
    
        this.ssoClientId = ssoClientId;
    }
    
    public String getGlobalId() {
    
        return globalId;
    }
    
    public void setGlobalId(String globalId) {
    
        this.globalId = globalId;
    }

}
