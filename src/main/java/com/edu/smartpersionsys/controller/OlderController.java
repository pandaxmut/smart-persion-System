package com.edu.smartpersionsys.controller;

import com.edu.smartpersionsys.pojo.BloodPressure;
import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Suggestions;
import com.edu.smartpersionsys.pojo.User;
import com.edu.smartpersionsys.service.OlderService;
import com.edu.smartpersionsys.utils.DataFromBackend;
import com.edu.smartpersionsys.utils.VitalSignsData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OlderController {

    @Autowired
    private OlderService olderService;

    @GetMapping("/{olderId}/olderPage")
    public String olderPage(@PathVariable int olderId,Model model,HttpSession session){
        Older older = olderService.getOlderByOlderId(olderId);
        session.setAttribute("older",older);
        model.addAttribute("older",older);
        return "olderpage";
    }


    //http://localhost:8080/old
    //访问老人管理页
    @RequestMapping("/old")
    public String getAllAndSearch(Model model,
                         @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "findOlderName", required =false,defaultValue = "无") String findOlderName){
        PageInfo<Older> all = new PageInfo<>();
        if(findOlderName.equals("无")){
            all = olderService.findAll(pageIndex, pageSize);
        }else {
            all = olderService.findByOlderName(pageIndex, pageSize,findOlderName);
            System.out.println(all);
            if (all.getSize()==0){
                model.addAttribute("msg","查无此人");
            }
        }
        model.addAttribute("page", all);
        model.addAttribute("path", "/old?pageIndex=");
        return "olderManage";
    }

    //修改老人信息
    @PostMapping(value = "/editOlderInfo")
    public String editOlderInfo(Model model,
                                RedirectAttributes redirect,
                                @Validated Older older,
                                @RequestParam("photoFile") MultipartFile photoFile) throws IOException {
        System.out.println(older.toString());
        //判断用户是否上传了文件 ：区别在于需不需要 更新头像地址
        int flag = 0;
        if (!photoFile.isEmpty()) { //存在
            //文件上传的地址
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/media/photo";
            String realPath = path.replace('/', '\\').substring(1, path.length());
            //获取文件的名称
            final String fileName = photoFile.getOriginalFilename();
            //限制文件上传的类型

            //文件上传
            File file = new File(realPath, fileName);
            //完成文件上传
            photoFile.transferTo(file);
            System.out.println("文件上传成功");
            //设置头像路径
            older.setOlderPhoto("../media/photo/"+fileName);
            //更新老人身份信息
            flag = olderService.updateOlderInfo(older);
        }else{
            flag = olderService.updateOlderInfoExcludeOlderPhoto(older);
        }

        String msg;
        if (flag==1){
            msg="更新成功";
            redirect.addFlashAttribute("msg",msg);
        }else{
            msg="更新失败";
            redirect.addFlashAttribute("msg",msg);

        }
        // 将消息添加到Flash属性中
        return "redirect:/old";
    }


    //制作一个老人图表、信息包括老人的：三十天内：最高最低血糖、体温；
    @GetMapping("/getVitalSigns")
    @ResponseBody
    public String getVitalSigns(HttpSession session) throws JsonProcessingException {

        Older older = (Older)session.getAttribute("older");
        System.out.println("olderId:"+older.getOlderId());
        List<BloodPressure> vitalSigns = olderService.getBloodPressureByTime(older.getOlderId(),30);

        List<Integer> lowBPS = new ArrayList<>();
        List<Integer> maxBPS = new ArrayList<>();
        List<Float> temperatures = new ArrayList<>();
        List<String> days = new ArrayList<>();

        for (BloodPressure data : vitalSigns){
            lowBPS.add(data.getLowBP());
            maxBPS.add(data.getMaxBP());
            temperatures.add(data.getTemperature());
            days.add(data.getDate());
        }
        VitalSignsData vitalSignsData = new VitalSignsData(maxBPS,lowBPS,temperatures,days);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(vitalSignsData);
        return jsonData;
    }

    /**
     * 老人提交建议信息
     */
    @PostMapping("/submitSuggestion")
    public String submitSuggestionByOlder( Suggestions suggestion,HttpSession session, RedirectAttributes redirect){

        Older older = (Older)session.getAttribute("older");
        //追加 当前时间、userId
        suggestion.setOlderId(older.getOlderId());
        String date = LocalDate.now().toString();
        suggestion.setDate(date);
        //添加一条老人建议
        int flag = olderService.addSuggestionByOlder(suggestion);
        //判断是否添加成功
        if(flag==1){
            redirect.addFlashAttribute("insertMsg","反馈成功!");
        }else{
            redirect.addFlashAttribute("insertMsg","反馈失败!");
        }
        return "redirect:/"+older.getOlderId()+"/olderPage";
    }
}
