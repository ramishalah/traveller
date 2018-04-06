
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getFullName() {
        return fullName;
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
        return email;
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
