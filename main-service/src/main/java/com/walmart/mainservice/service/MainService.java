package com.walmart.mainservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.walmart.mainservice.model.Hops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainService {

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> callConfigService(String INT_ID, String INT_CC, Integer INT_INST) throws JsonProcessingException {

        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:9021/api/v1/config")
                .queryParam("INT_ID", "{INT_ID}")
                .queryParam("INT_CC", "{INT_CC}")
                .queryParam("INT_INST", "{INT_INST}")
                .encode()
                .toUriString();

        Map<String, Object> params = new HashMap<>();
        params.put("INT_ID", INT_ID);
        params.put("INT_CC", INT_CC);
        params.put("INT_INST", INT_INST);


        Object result = restTemplate.getForObject(urlTemplate, Object.class, params);
        List<List<Object>> res = (List<List<Object>>) result;
        List<Object> flat =
                res.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
        flat.remove(null);

        Map<String, Object> hopsResult = stringToJson(flat);

        return hopsResult;
    }

    public Map<String, Object> stringToJson(List<Object> flat) throws JsonProcessingException {
        String resJson = new Gson().toJson(flat);
        ObjectMapper mapper = new ObjectMapper();
        List<Hops> participantJsonList = mapper.readValue(resJson, new TypeReference<List<Hops>>(){});

        if (participantJsonList == null || participantJsonList.isEmpty()) {
            //return Collections.emptyMap();
        }

        Hops first = participantJsonList.get(0);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("intid", first.getIntid());
        result.put("intcc", first.getIntcc());
        result.put("intinst", first.getIntinst());

        Map<String, Object> inserid = new LinkedHashMap<>();

        Map<String, List<Hops>> grouped = participantJsonList.stream().collect(Collectors.groupingBy(Hops::getIntserid));

        grouped.forEach((k, v) -> {
            inserid.put(k, v.stream().map(inputData -> {
                Map<String, Object> childResult = new HashMap<>();
                childResult.put("hopsequence", inputData.getHopsequence());
                childResult.put("hoplink", inputData.getHoplink());
                childResult.put("hopname", inputData.getHopname());
                childResult.put("hopdest", inputData.getHopdest());
                childResult.put("hopservice", inputData.getHopservice());
                childResult.put("hopmeta", inputData.getHopmeta());
                return childResult;
            }).collect(Collectors.toList()));
        });

        List<Object> inseridlist = inserid.entrySet().stream().collect(Collectors.toList());
        result.put("intserid", inseridlist);
        System.out.println(inseridlist);
        return result;
    }
}
