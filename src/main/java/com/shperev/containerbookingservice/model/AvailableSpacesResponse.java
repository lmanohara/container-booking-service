package com.shperev.containerbookingservice.model;


import lombok.Builder;
import lombok.Value;

/**
 * The model class for checking available spaces
 */

@Value
@Builder
public class AvailableSpacesResponse {
    Boolean availability;
}
