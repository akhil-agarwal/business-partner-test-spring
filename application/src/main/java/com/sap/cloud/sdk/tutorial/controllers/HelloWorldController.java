package com.sap.cloud.sdk.tutorial.controllers;

import org.slf4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;

import com.sap.cloud.sdk.s4hana.datamodel.odata.helper.Order;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultBusinessPartnerService;

@RestController
@RequestMapping( "/hello" )
public class HelloWorldController
{
    private static final Logger logger = CloudLoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<List<BusinessPartner>> getHello(@RequestParam( defaultValue = "world" ) final String name ) throws ODataException {
        logger.info("I am running!");

        final List<BusinessPartner> result;

        result = new DefaultBusinessPartnerService()
                .getAllBusinessPartner()
                .select(BusinessPartner.BUSINESS_PARTNER,
                        BusinessPartner.LAST_NAME,
                        BusinessPartner.FIRST_NAME)
                .orderBy(BusinessPartner.LAST_NAME, Order.ASC)
                .execute();

        return ResponseEntity.ok(result);
    }
}
