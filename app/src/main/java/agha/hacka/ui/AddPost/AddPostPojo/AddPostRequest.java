package agha.hacka.ui.AddPost.AddPostPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPostRequest {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("metadata_key")
    @Expose
    private String metadataKey;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetadataKey() {
        return metadataKey;
    }

    public void setMetadataKey(String metadataKey) {
        this.metadataKey = metadataKey;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
