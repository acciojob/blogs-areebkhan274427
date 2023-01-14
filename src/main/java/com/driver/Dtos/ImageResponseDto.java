package com.driver.Dtos;

public class ImageResponseDto {

    private String description;

    private String dimensions;

    private int blogId;

    public ImageResponseDto(String description, String dimensions, int blogId) {
        this.description = description;
        this.dimensions = dimensions;
        this.blogId = blogId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}
