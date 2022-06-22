package com.walmart.serviceservice.controller;

import com.walmart.serviceservice.service.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/service")
@Slf4j
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public Object getService(@RequestParam("INT_ID") String INT_ID, @RequestParam("INT_CC") String INT_CC, @RequestParam("INT_INST") Integer INT_INST, @RequestParam("INT_SERVICE_ID") String INT_SERVICE_ID){
//        log.info(INT_ID);
//        log.info(INT_CC);
//        log.info(String.valueOf(INT_INST));
//        log.info(INT_SERVICE_ID);
        return serviceService.getServiceFromDb(INT_ID, INT_CC, INT_INST, INT_SERVICE_ID);

    }
}
