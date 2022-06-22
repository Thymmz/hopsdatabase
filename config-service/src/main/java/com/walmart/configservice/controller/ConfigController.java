package com.walmart.configservice.controller;

import com.walmart.configservice.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping
    public List<Object> getConfig(@RequestParam("INT_ID") String INT_ID, @RequestParam("INT_CC") String INT_CC, @RequestParam("INT_INST") Integer INT_INST){
        return configService.getConfigFromDb(INT_ID, INT_CC, INT_INST);
    }
}
