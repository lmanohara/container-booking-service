package com.maersk.containerbookingservice;

import com.maersk.containerbookingservice.model.AvailableSpacesResponse;
import com.maersk.containerbookingservice.model.BookingsSpec;
import com.maersk.containerbookingservice.model.ContainerType;
import com.maersk.containerbookingservice.model.ExternalAPIResponse;

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
