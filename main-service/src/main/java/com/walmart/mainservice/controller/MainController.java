package com.walmart.mainservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.walmart.mainservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/main")
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping
    public Map<String, Object> mainMethod(@RequestParam("INT_ID") String INT_ID, @RequestParam("INT_CC") String INT_CC, @RequestParam("INT_INST") Integer INT_INST) throws JsonProcessingException {
        return mainService.callConfigService(INT_ID, INT_CC, INT_INST);
    }
}










//                "{\n" +
//                "    _id: '{{objectId()}}',\n" +
//                "    index: '{{index()}}',\n" +
//                "    guid: '{{guid()}}',\n" +
//                "    isActive: '{{bool()}}',}";
