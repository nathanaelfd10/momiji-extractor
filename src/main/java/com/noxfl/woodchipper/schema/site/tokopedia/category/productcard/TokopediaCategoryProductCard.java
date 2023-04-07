
package com.noxfl.woodchipper.schema.site.tokopedia.category.productcard;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "url",
    "imageUrl",
    "imageUrlLarge",
    "catId",
    "gaKey",
    "countReview",
    "discountPercentage",
    "preorder",
    "name",
    "price",
    "priceInt",
    "original_price",
    "rating",
    "wishlist",
    "labels",
    "badges",
    "shop",
    "labelGroups",
    "__typename"
})
@Generated("jsonschema2pojo")
public class TokopediaCategoryProductCard {

    @JsonProperty("id")
    private long id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("imageUrlLarge")
    private String imageUrlLarge;
    @JsonProperty("catId")
    private long catId;
    @JsonProperty("gaKey")
    private String gaKey;
    @JsonProperty("countReview")
    private long countReview;
    @JsonProperty("discountPercentage")
    private long discountPercentage;
    @JsonProperty("preorder")
    private boolean preorder;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private String price;
    @JsonProperty("priceInt")
    private long priceInt;
    @JsonProperty("original_price")
    private String originalPrice;
    @JsonProperty("rating")
    private long rating;
    @JsonProperty("wishlist")
    private boolean wishlist;
    @JsonProperty("labels")
    private List<Object> labels;
    @JsonProperty("badges")
    private List<Badge> badges;
    @JsonProperty("shop")
    private Shop shop;
    @JsonProperty("labelGroups")
    private List<LabelGroup> labelGroups;
    @JsonProperty("__typename")
    private String typename;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TokopediaCategoryProductCard() {
    }

    /**
     * 
     * @param imageUrlLarge
     * @param shop
     * @param originalPrice
     * @param wishlist
     * @param rating
     * @param url
     * @param labels
     * @param countReview
     * @param discountPercentage
     * @param badges
     * @param catId
     * @param labelGroups
     * @param price
     * @param imageUrl
     * @param name
     * @param id
     * @param gaKey
     * @param preorder
     * @param priceInt
     * @param typename
     */
    public TokopediaCategoryProductCard(long id, String url, String imageUrl, String imageUrlLarge, long catId, String gaKey, long countReview, long discountPercentage, boolean preorder, String name, String price, long priceInt, String originalPrice, long rating, boolean wishlist, List<Object> labels, List<Badge> badges, Shop shop, List<LabelGroup> labelGroups, String typename) {
        super();
        this.id = id;
        this.url = url;
        this.imageUrl = imageUrl;
        this.imageUrlLarge = imageUrlLarge;
        this.catId = catId;
        this.gaKey = gaKey;
        this.countReview = countReview;
        this.discountPercentage = discountPercentage;
        this.preorder = preorder;
        this.name = name;
        this.price = price;
        this.priceInt = priceInt;
        this.originalPrice = originalPrice;
        this.rating = rating;
        this.wishlist = wishlist;
        this.labels = labels;
        this.badges = badges;
        this.shop = shop;
        this.labelGroups = labelGroups;
        this.typename = typename;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("imageUrlLarge")
    public String getImageUrlLarge() {
        return imageUrlLarge;
    }

    @JsonProperty("imageUrlLarge")
    public void setImageUrlLarge(String imageUrlLarge) {
        this.imageUrlLarge = imageUrlLarge;
    }

    @JsonProperty("catId")
    public long getCatId() {
        return catId;
    }

    @JsonProperty("catId")
    public void setCatId(long catId) {
        this.catId = catId;
    }

    @JsonProperty("gaKey")
    public String getGaKey() {
        return gaKey;
    }

    @JsonProperty("gaKey")
    public void setGaKey(String gaKey) {
        this.gaKey = gaKey;
    }

    @JsonProperty("countReview")
    public long getCountReview() {
        return countReview;
    }

    @JsonProperty("countReview")
    public void setCountReview(long countReview) {
        this.countReview = countReview;
    }

    @JsonProperty("discountPercentage")
    public long getDiscountPercentage() {
        return discountPercentage;
    }

    @JsonProperty("discountPercentage")
    public void setDiscountPercentage(long discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @JsonProperty("preorder")
    public boolean isPreorder() {
        return preorder;
    }

    @JsonProperty("preorder")
    public void setPreorder(boolean preorder) {
        this.preorder = preorder;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("priceInt")
    public long getPriceInt() {
        return priceInt;
    }

    @JsonProperty("priceInt")
    public void setPriceInt(long priceInt) {
        this.priceInt = priceInt;
    }

    @JsonProperty("original_price")
    public String getOriginalPrice() {
        return originalPrice;
    }

    @JsonProperty("original_price")
    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    @JsonProperty("rating")
    public long getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(long rating) {
        this.rating = rating;
    }

    @JsonProperty("wishlist")
    public boolean isWishlist() {
        return wishlist;
    }

    @JsonProperty("wishlist")
    public void setWishlist(boolean wishlist) {
        this.wishlist = wishlist;
    }

    @JsonProperty("labels")
    public List<Object> getLabels() {
        return labels;
    }

    @JsonProperty("labels")
    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    @JsonProperty("badges")
    public List<Badge> getBadges() {
        return badges;
    }

    @JsonProperty("badges")
    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    @JsonProperty("shop")
    public Shop getShop() {
        return shop;
    }

    @JsonProperty("shop")
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @JsonProperty("labelGroups")
    public List<LabelGroup> getLabelGroups() {
        return labelGroups;
    }

    @JsonProperty("labelGroups")
    public void setLabelGroups(List<LabelGroup> labelGroups) {
        this.labelGroups = labelGroups;
    }

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("__typename")
    public void setTypename(String typename) {
        this.typename = typename;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
