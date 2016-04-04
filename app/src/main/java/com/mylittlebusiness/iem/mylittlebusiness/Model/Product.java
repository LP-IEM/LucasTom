package com.mylittlebusiness.iem.mylittlebusiness.Model;

public class Product {
    private String name, description;
    private Double price;
    private int imageResource;
    private int number;

    public Product(String name, String description, Double price, int imageResource) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResource = imageResource;
        this.number = 0;
    }

    public int getNumber() {
        return number;
    }

    public void incrementNumber() {
        this.number += 1;
    }

    public void decrementNumber() {
        this.number -= 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getImage() {
        return imageResource;
    }

    public void setImage(int imageResource) {
        this.imageResource = imageResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (imageResource != product.imageResource) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null)
            return false;
        return price != null ? price.equals(product.price) : product.price == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + imageResource;
        return result;
    }
}
