package agha.hacka.ui.UserProfile;

public class UserSingleton {
    private static final UserSingleton ourInstance = new UserSingleton();

    private String userId;
    private String fullName;
    private String mobile;
    private String email;
    private String url;

    static UserSingleton getInstance() {
        return ourInstance;
    }

    private UserSingleton() {
    }

    public static UserSingleton getOurInstance() {
        return ourInstance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
