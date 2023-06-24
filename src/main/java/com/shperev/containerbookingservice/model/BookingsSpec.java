package com.shperev.containerbookingservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * The model for container booking specification
 */
@Table("container_bookings")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingsSpec {

    @PrimaryKey("booking_ref")
    private String bookingRef;
    @Column("container_type")
    private ContainerType containerType;
    @Column("container_size")
    @Pattern(regexp = "20|40")
    private int containerSize;
    @Column("origin")
    @Min(5)
    @Max(20)
    private String origin;
    @Column("destination")
    @Min(5)
    @Max(20)
    private String destination;
    @Column("quantity")
    @Min(1)
    @Max(1000)
    private int quantity;
    @Column("timestamp")
    private String timestamp;

    public BookingsSpec(ContainerType containerType, int containerSize, String origin, String destination, int quantity) {
        this.containerType = containerType;
        this.containerSize = containerSize;
        this.origin = origin;
        this.destination = destination;
        this.quantity = quantity;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }

    public int getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(int containerSize) {
        this.containerSize = containerSize;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
