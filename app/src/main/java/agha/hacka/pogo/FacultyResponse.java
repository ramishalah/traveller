package agha.hacka.pogo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacultyResponse {

    @SerializedName("id_faculty")
    @Expose
    private Integer idFaculty;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("short_biography")
    @Expose
    private String shortBiography;
    @SerializedName("office_location")
    @Expose
    private String officeLocation;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("website_url")
    @Expose
    private String websiteUrl;
    @SerializedName("approved")
    @Expose
    private Integer approved;
    @SerializedName("photo_url")
    @Expose
    private String photoUrl;
    @SerializedName("is_first_name_visible")
    @Expose
    private Integer isFirstNameVisible;
    @SerializedName("is_last_name_visible")
    @Expose
    private Integer isLastNameVisible;
    @SerializedName("is_short_biography_visible")
    @Expose
    private Integer isShortBiographyVisible;
    @SerializedName("is_office_location_visible")
    @Expose
    private Integer isOfficeLocationVisible;
    @SerializedName("is_email_visible")
    @Expose
    private Integer isEmailVisible;
    @SerializedName("is_department_visible")
    @Expose
    private Integer isDepartmentVisible;
    @SerializedName("is_website_url_visible")
    @Expose
    private Integer isWebsiteUrlVisible;
    @SerializedName("is_photo_visible")
    @Expose
    private Integer isPhotoVisible;

    public Integer getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Integer idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShortBiography() {
        return shortBiography;
    }

    public void setShortBiography(String shortBiography) {
        this.shortBiography = shortBiography;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getIsFirstNameVisible() {
        return isFirstNameVisible;
    }

    public void setIsFirstNameVisible(Integer isFirstNameVisible) {
        this.isFirstNameVisible = isFirstNameVisible;
    }

    public Integer getIsLastNameVisible() {
        return isLastNameVisible;
    }

    public void setIsLastNameVisible(Integer isLastNameVisible) {
        this.isLastNameVisible = isLastNameVisible;
    }

    public Integer getIsShortBiographyVisible() {
        return isShortBiographyVisible;
    }

    public void setIsShortBiographyVisible(Integer isShortBiographyVisible) {
        this.isShortBiographyVisible = isShortBiographyVisible;
    }

    public Integer getIsOfficeLocationVisible() {
        return isOfficeLocationVisible;
    }

    public void setIsOfficeLocationVisible(Integer isOfficeLocationVisible) {
        this.isOfficeLocationVisible = isOfficeLocationVisible;
    }

    public Integer getIsEmailVisible() {
        return isEmailVisible;
    }

    public void setIsEmailVisible(Integer isEmailVisible) {
        this.isEmailVisible = isEmailVisible;
    }

    public Integer getIsDepartmentVisible() {
        return isDepartmentVisible;
    }

    public void setIsDepartmentVisible(Integer isDepartmentVisible) {
        this.isDepartmentVisible = isDepartmentVisible;
    }

    public Integer getIsWebsiteUrlVisible() {
        return isWebsiteUrlVisible;
    }

    public void setIsWebsiteUrlVisible(Integer isWebsiteUrlVisible) {
        this.isWebsiteUrlVisible = isWebsiteUrlVisible;
    }

    public Integer getIsPhotoVisible() {
        return isPhotoVisible;
    }

    public void setIsPhotoVisible(Integer isPhotoVisible) {
        this.isPhotoVisible = isPhotoVisible;
    }

    @Override
    public String toString() {
        return "FacultyResponse{" +
                "idFaculty=" + idFaculty +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", shortBiography='" + shortBiography + '\'' +
                ", officeLocation='" + officeLocation + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", approved=" + approved +
                ", photoUrl='" + photoUrl + '\'' +
                ", isFirstNameVisible=" + isFirstNameVisible +
                ", isLastNameVisible=" + isLastNameVisible +
                ", isShortBiographyVisible=" + isShortBiographyVisible +
                ", isOfficeLocationVisible=" + isOfficeLocationVisible +
                ", isEmailVisible=" + isEmailVisible +
                ", isDepartmentVisible=" + isDepartmentVisible +
                ", isWebsiteUrlVisible=" + isWebsiteUrlVisible +
                ", isPhotoVisible=" + isPhotoVisible +
                '}';
    }
}
