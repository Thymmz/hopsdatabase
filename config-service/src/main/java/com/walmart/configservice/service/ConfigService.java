package com.walmart.configservice.service;

import com.walmart.configservice.dao.ConfigDao;
import com.walmart.configservice.model.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private RestTemplate restTemplate;

    public ConfigService(ConfigDao configDao) {
        this.configDao = configDao;
    }

    public List<Object> getConfigFromDb(String int_id, String int_cc, Integer int_inst){
        Config configResult = configDao.findByIntidAndIntccAndIntinst(int_id, int_cc, int_inst);
        String configServiceId = configResult.getIntserviceid();
        List<String> listOfServiceId = jsonToList(configServiceId);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:9022/api/v1/service")
                .queryParam("INT_ID", "{INT_ID}")
                .queryParam("INT_CC", "{INT_CC}")
                .queryParam("INT_INST", "{INT_INST}")
                .queryParam("INT_SERVICE_ID", "{INT_SERVICE_ID}")
                .encode()
                .toUriString();
        List<Object> result = new ArrayList<Object>();
        for (int i=0; i<listOfServiceId.size(); i++){
            Map<String, Object> params = queryResult(int_id, int_cc, int_inst, listOfServiceId.get(i));

            result.add(restTemplate.getForObject(urlTemplate, Object.class, params));
            //log.info(String.valueOf(params),"Params");
//            System.out.println("Params:");
//            System.out.println(params);
//            log.info(urlTemplate);
//            log.info(String.valueOf(result));
        }
        return result;
    }

    public List<String> jsonToList(String configServiceId){
        String[] strArray = null;
        //splitting the string with delimiter as \"
        String patternStr = "\"";
        Pattern ptr = Pattern.compile(patternStr);
        //storing the string elements in list after splitting
        strArray = ptr.split(configServiceId);
        List<String> list = new ArrayList<String>(Arrays.asList(strArray));
        list.remove("[");
        list.remove(", ");
        list.remove("]");
        //printing the converted string array
        return list;
    }

    public Map<String, Object> queryResult(String int_id, String int_cc, Integer int_inst, String int_service_id){
        Map<String, Object> params = new HashMap<>();
        params.put("INT_ID", int_id);
        params.put("INT_CC", int_cc);
        params.put("INT_INST", int_inst);
        params.put("INT_SERVICE_ID", int_service_id);
        //System.out.println(params);
        return params;
    }

}
