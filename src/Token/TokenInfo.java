package Token;


public class TokenInfo {
    private int userId;
    private int UserName;
    private String ssoClientId;
    private String globalId;
    
    public int getUserId() {
    
        return userId;
    }
    
    public void setUserId(int userId) {
    
        this.userId = userId;
    }
    
    public int getUserName() {
    
        return UserName;
    }
    
    public void setUserName(int userName) {
    
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
