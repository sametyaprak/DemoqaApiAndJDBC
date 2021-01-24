package TESTNG.pojos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "collectionOfIsbns"
})
public class DemoqaBooksPojo {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("collectionOfIsbns")
    private List<CollectionOfIsbnPojo> collectionOfIsbns;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("collectionOfIsbns")
    public List<CollectionOfIsbnPojo> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    @JsonProperty("collectionOfIsbns")
    public void setCollectionOfIsbns(List<CollectionOfIsbnPojo> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public DemoqaBooksPojo(){

    }
    public DemoqaBooksPojo(String userId, CollectionOfIsbnPojo collectionOfIsbnPojo){
        this.userId = userId;
        collectionOfIsbns.add(collectionOfIsbnPojo);
    }

    @Override
    public String toString() {
        return "DemoqaBooksPojo{" +
                "userId='" + userId + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}