package com.edu.smartpersionsys.controller;

import com.edu.smartpersionsys.pojo.Suggestions;
import com.edu.smartpersionsys.pojo.Symptoms;
import com.edu.smartpersionsys.service.OlderService;
import com.edu.smartpersionsys.service.SymptomsService;
import com.edu.smartpersionsys.utils.DataFromBackend;
import com.edu.smartpersionsys.utils.OlderIncrease;
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
    @Autowired
    private OlderService olderService;

    //访问首页
    @GetMapping(value = "/index" )
    public String index(Model model){
        System.out.println("welcome");

        //获取所有意见反馈
        List<Suggestions> allSuggestions = olderService.getAllSuggestions();
        allSuggestions.forEach(System.out::println);

        model.addAttribute("suggestions",allSuggestions);

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

    /**
     * 获取新入住老人的信息
     * @param timeframe
     * @return
     */
    @ResponseBody
    @RequestMapping("/old/data")
    public String getNewOlderData(String timeframe) throws JsonProcessingException {
        System.out.println(timeframe);
        //判断查询的是 7days,30days,3months;
        OlderIncrease olderIncrease = new OlderIncrease();
        if(timeframe.equals("7days")){
            olderIncrease =  olderService.getIncrease(7);
        }else if(timeframe.equals("30days")){
            olderIncrease =  olderService.getIncrease(30);
        }else{
            olderIncrease =  olderService.getIncrease(90);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(olderIncrease);
        System.out.println("jsonData:"+jsonData);
        return jsonData;
    }

    @GetMapping("/message")
    public String message(){
        return "message";
    }

}

