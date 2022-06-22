package com.walmart.hopsservice.controller;

import com.walmart.hopsservice.model.Hops;
import com.walmart.hopsservice.model.HopsInfo;
import com.walmart.hopsservice.service.HopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hops")
public class HopsController {

    @Autowired
    private HopsService hopsService;

    public HopsController(HopsService hopsService) {
        this.hopsService = hopsService;
    }

    @GetMapping
    public Hops getHops(@RequestParam("INT_ID") String INT_ID, @RequestParam("INT_CC") String INT_CC, @RequestParam("INT_INST") Integer INT_INST, @RequestParam("INT_SERVICE_ID") String INT_SERVICE_ID, @RequestParam("HOP_SEQ") String HOP_SEQ){
        return hopsService.getHopsFromDB(INT_ID, INT_CC, INT_INST, INT_SERVICE_ID, HOP_SEQ);
    }
}
