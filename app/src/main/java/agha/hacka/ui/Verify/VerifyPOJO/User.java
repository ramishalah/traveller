
package agha.hacka.ui.Verify.VerifyPOJO;

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
    private String mobile;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("email_activated")
    @Expose
    private Boolean emailActivated;
    @SerializedName("mobile_activated")
    @Expose
    private Boolean mobileActivated;
    @SerializedName("role")
    @Expose
    private Role role;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
