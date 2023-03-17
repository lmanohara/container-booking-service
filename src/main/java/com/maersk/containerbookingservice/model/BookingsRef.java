package com.maersk.containerbookingservice.model;


/**
 * The model class for booking reference
 */
public class BookingsRef {
    private String bookingRef;

    public BookingsRef() {
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public BookingsRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }
}
