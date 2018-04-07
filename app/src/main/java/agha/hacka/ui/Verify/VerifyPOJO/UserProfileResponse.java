package agha.hacka.ui.Verify.VerifyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileResponse {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("url")
    @Expose
    private String url;
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