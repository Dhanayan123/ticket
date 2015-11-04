package com.ajira.candy.service.impl;


import java.util.List;

import com.ajira.candy.models.Property;
import com.ajira.candy.models.Tickets;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

@RestController
@RequestMapping("/docs/ticket")
public class TicketServiceImpl {
    public enum status {
        CANCELLED, COMPLETED
    }

    public enum cancelledReason {
        ENDUSER, OTHERS
    }


    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Tickets")
    public JSONObject addNewTicket(@RequestBody Tickets ticket) {
        JSONObject jsonObject = new JSONObject();
        if(ticket.getCreatedBy() == null) {
            Property p = new Property();
            p.setType("String");
            ticket.setCreatedBy(p);
        }
        if(ticket.getDescription() == null) {
            Property p = new Property();
            p.setType("String");
            ticket.setDescription(p);
        }
        if(ticket.getSeverity() == null) {
            Property p = new Property();
            p.setType("String");
            ticket.setSeverity(p);
        }
        if(ticket.getStatus() == null) {
            Property p = new Property();
            p.setType("enum");
            ticket.setStatus(p);
        }


        if (ticket.getStatus().getValue() == null) {
            jsonObject.put("createdBy", ticket.getCreatedBy());
            jsonObject.put("description", ticket.getDescription());
            jsonObject.put("severity", ticket.getSeverity());
            jsonObject.put("status", ticket.getStatus());
        } else if (ticket.getStatus().getValue().equalsIgnoreCase("completed")) {
            if(ticket.getComments() == null) {
                Property p = new Property();
                p.setType("String");
                ticket.setComments(p);
            }
            jsonObject.put("createdBy", ticket.getCreatedBy());
            jsonObject.put("description", ticket.getDescription());
            jsonObject.put("severity", ticket.getSeverity());
            jsonObject.put("status", ticket.getStatus());
            ticket.getComments().setType("String");
            jsonObject.put("comment", ticket.getComments());
        } else if (ticket.getStatus().getValue().equalsIgnoreCase("cancelled")) {

            if(ticket.getCancelledReason() == null) {
                Property p = new Property();
                p.setType("enum");
                ticket.setCancelledReason(p);
            }

            if (ticket.getCancelledReason().getValue() == null) {

                jsonObject.put("createdBy", ticket.getCreatedBy());
                jsonObject.put("description", ticket.getDescription());
                jsonObject.put("severity", ticket.getSeverity());
                jsonObject.put("status", ticket.getStatus());
                ticket.getCancelledReason().setType("enum");
                jsonObject.put("cancelledReason", ticket.getCancelledReason());

            }else if (!ticket.getCancelledReason().getValue().equalsIgnoreCase("others")) {

                jsonObject.put("createdBy", ticket.getCreatedBy());
                jsonObject.put("description", ticket.getDescription());
                jsonObject.put("severity", ticket.getSeverity());
                jsonObject.put("status", ticket.getStatus());
                ticket.getCancelledReason().setType("enum");
                jsonObject.put("cancelledReason", ticket.getCancelledReason());

            } else {
                if(ticket.getCancelledOtherDescription() == null) {
                    Property p = new Property();
                    p.setType("String");
                    ticket.setCancelledOtherDescription(p);
                }
                jsonObject.put("createdBy", ticket.getCreatedBy());
                jsonObject.put("description", ticket.getDescription());
                jsonObject.put("severity", ticket.getSeverity());
                jsonObject.put("status", ticket.getStatus());
                ticket.getCancelledReason().setType("enum");

                jsonObject.put("cancelledReason", ticket.getCancelledReason());
                ticket.getCancelledOtherDescription().setType("String");
                jsonObject.put("cancelledOtherDescription", ticket.getCancelledOtherDescription());

            }
        }
        return jsonObject;
    }

}
