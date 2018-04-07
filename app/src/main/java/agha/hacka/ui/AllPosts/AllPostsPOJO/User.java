
package agha.hacka.ui.AllPosts.AllPostsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("full_name")
    @Expose
    private Object fullName;
    @SerializedName("mobile")
    @Expose
    private Object mobile;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("email_activated")
    @Expose
    private Boolean emailActivated;
    @SerializedName("mobile_activated")
    @Expose
    private Boolean mobileActivated;
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        if (url != null)
            return url;
        return "null";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getFullName() {
        if (fullName != null)
            return fullName;
        return "Unknown";
    }

    public void setFullName(Object fullName) {
        this.fullName = fullName;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getEmail() {
        if (email != null)
            return email;
        return "Unknown";
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Boolean getEmailActivated() {
        return emailActivated;
    }

    public void setEmailActivated(Boolean emailActivated) {
        this.emailActivated = emailActivated;
    }

    public Boolean getMobileActivated() {
        return mobileActivated;
    }

    public void setMobileActivated(Boolean mobileActivated) {
        this.mobileActivated = mobileActivated;
    }

}
