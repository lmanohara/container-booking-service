package com.shperev.containerbookingservice.model;


/**
 * The model class for checking available spaces
 */
public class AvailableSpacesResponse {
    private Boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
