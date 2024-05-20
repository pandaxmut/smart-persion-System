package com.edu.smartpersionsys.controller;

import com.edu.smartpersionsys.pojo.Symptoms;
import com.edu.smartpersionsys.service.SymptomsService;
import com.edu.smartpersionsys.utils.DataFromBackend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class homeController {

    @Autowired
    private SymptomsService symptomsService;//用于老人症状的图表统计

    //访问首页
    @GetMapping(value = "/index" )
    public String index(Model model){
        System.out.println("welcome");

        return "index";
    }

    //使用ajax 生成图表；
    // 问题：冗余，可以提升的地方：AOP、泛型
    @ResponseBody
    @RequestMapping("/getData")
    public String getData() throws JsonProcessingException {
        //老人症状饼图分析
        List<Symptoms> list = symptomsService.getAllSymptoms();
        DataFromBackend dataFromBackend = new DataFromBackend();
        List<String> label = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (Symptoms s:list){
            label.add(s.getSymptomName());
            data.add(s.getCount());
        }
        dataFromBackend.setLabels(label);
        dataFromBackend.setValues(data);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(dataFromBackend);
        return jsonData;
    }


}
