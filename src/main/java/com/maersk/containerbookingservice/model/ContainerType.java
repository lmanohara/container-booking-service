package com.maersk.containerbookingservice.model;

public enum ContainerType {
    DRY("DRY"), REEFER("REEFER");

    public final String value;

    ContainerType(String value) {
        this.value = value;
    }
}
