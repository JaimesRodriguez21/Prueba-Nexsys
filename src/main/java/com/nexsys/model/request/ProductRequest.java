package com.nexsys.model.request;

import java.util.ArrayList;
import java.util.List;

public class ProductRequest {
    private Long id;
    private String name;
    private double priceFinal;
    private String description;
    private Long categoryId;
    private List<String> images;
    private String imageUrl;

    public ProductRequest() {
        this.images = new ArrayList<>();
    }

    public ProductRequest(Long id, String name, double priceFinal, String description, Long categoryId, String imageUrl) {
        this.id = id;
        this.name = name;
        this.priceFinal = priceFinal;
        this.description = description;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.images = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceFinal() {
        return priceFinal;
    }

    public void setPriceFinal(double priceFinal) {
        this.priceFinal = priceFinal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addImagen(String imageUrl) {
        this.images.add(imageUrl);
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceFinal=" + priceFinal +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", images=" + images +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
