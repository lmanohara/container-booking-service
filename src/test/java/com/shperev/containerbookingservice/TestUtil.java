package com.shperev.containerbookingservice;

import com.shperev.containerbookingservice.model.AvailableSpacesResponse;
import com.shperev.containerbookingservice.model.BookingsSpec;
import com.shperev.containerbookingservice.model.ContainerType;
import com.shperev.containerbookingservice.model.ExternalAPIResponse;

public class TestUtil {

    public static BookingsSpec buildBookingSpec(String bookingRef, String timestamp) {
        BookingsSpec bookingsSpec = new BookingsSpec(ContainerType.DRY, 10,
                "Australia", "Singapore", 5);
        bookingsSpec.setBookingRef(bookingRef);
        bookingsSpec.setTimestamp(timestamp);

        return bookingsSpec;
    }

    public static AvailableSpacesResponse buildAvailableSpaces(Boolean available) {

        AvailableSpacesResponse availableSpacesResponse = new AvailableSpacesResponse();
        availableSpacesResponse.setAvailable(available);

        return availableSpacesResponse;
    }

    public static ExternalAPIResponse buildExternalAPIResponse(Integer availableSpaces) {
        ExternalAPIResponse externalAPIResponse = new ExternalAPIResponse();
        externalAPIResponse.setAvailableSpace(availableSpaces);
        return externalAPIResponse;
    }

}
