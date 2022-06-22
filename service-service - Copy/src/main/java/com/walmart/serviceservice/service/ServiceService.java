package com.walmart.serviceservice.service;

import com.walmart.serviceservice.dao.ServiceDao;
import com.walmart.serviceservice.model.ServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private RestTemplate restTemplate;

    public ServiceService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public Object getServiceFromDb(String int_id, String int_cc, Integer int_inst, String intserid){
        ServiceModel serviceResult = serviceDao.findByIntidAndIntccAndIntinstAndIntserid(int_id, int_cc, int_inst, intserid);
        String serviceHopsequence = serviceResult.getHopsequence();
        List<String> listOfHopSequence =jsonToList(serviceHopsequence);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:9023/api/v1/hops")
                .queryParam("INT_ID", "{INT_ID}")
                .queryParam("INT_CC", "{INT_CC}")
                .queryParam("INT_INST", "{INT_INST}")
                .queryParam("INT_SERVICE_ID", "{INT_SERVICE_ID}")
                .queryParam("HOP_SEQ", "{HOP_SEQ}")
                .encode()
                .toUriString();
        List<Object> result = new ArrayList<Object>();
        for (int i=0; i<listOfHopSequence.size(); i++){
            Map<String, Object> params = queryResult(int_id, int_cc, int_inst, intserid, listOfHopSequence.get(i));

            result.add(restTemplate.getForObject(urlTemplate, Object.class, params));
            //log.info(String.valueOf(params),"Params");
//            System.out.println("Params:");
//            System.out.println(params);
//            log.info(urlTemplate);
//            log.info(String.valueOf(result));
        }
        return (Object) result;

    }

    public List<String> jsonToList(String serviceHopsequence){
        String[] strArray = null;
        //splitting the string with delimiter as \"
        String patternStr = "\"";
        Pattern ptr = Pattern.compile(patternStr);
        //storing the string elements in list after splitting
        strArray = ptr.split(serviceHopsequence);
        List<String> list = new ArrayList<String>(Arrays.asList(strArray));
        list.remove("[");
        list.remove(", ");
        list.remove("]");
        //printing the converted string array
        return list;
    }

    public Map<String, Object> queryResult(String int_id, String int_cc, Integer int_inst, String int_service_id, String hopsequence){
        Map<String, Object> params = new HashMap<>();
        params.put("INT_ID", int_id);
        params.put("INT_CC", int_cc);
        params.put("INT_INST", int_inst);
        params.put("INT_SERVICE_ID", int_service_id);
        params.put("HOP_SEQ", hopsequence);
        //System.out.println(params);
        return params;
    }
}
