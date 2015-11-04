package com.ajira.candy.models;

import lombok.Data;

@Data
public class Tickets {
    private Property createdBy;


    private Property description;

    private Property severity;

    private Property cancelledOtherDescription;

    private Property comments;

    private Property status;

    private Property cancelledReason;

}
