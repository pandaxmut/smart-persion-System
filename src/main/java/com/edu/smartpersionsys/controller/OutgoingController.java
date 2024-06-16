package com.edu.smartpersionsys.controller;

import com.edu.smartpersionsys.pojo.Older;
import com.edu.smartpersionsys.pojo.Outgoing;
import com.edu.smartpersionsys.service.OlderService;
import com.edu.smartpersionsys.service.OutgoingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OutgoingController {
    @Autowired
    private OutgoingService outgoingService;
//查询outgoing表信息
    @RequestMapping("/outgoing")
    public ModelAndView outgoing(){
        ModelAndView modelAndView=new ModelAndView();
        List<Outgoing> outgoings = outgoingService.getOutgoings();
        modelAndView.addObject("outgoings", outgoings);
        modelAndView.setViewName("outgoing_list");
        return modelAndView;
    }
//    public String outgoing( Model model) {
//        List<Outgoing> outgoings = outgoingService.getOutgoings();
//        model.addAttribute("outgoings", outgoings);
//        return "outgoing_list";
//    }
     @RequestMapping("/del")
    public ModelAndView del(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        outgoingService.Del(id);
        return new ModelAndView("redirect:/outgoing");
     }
     @GetMapping("/addPage")
    public ModelAndView addPage(Model model){
        model.addAttribute("outgoing",new Outgoing());
        return  new ModelAndView("add","outgoing1",model);
    }
    @PostMapping("/add")
    public ModelAndView add(Outgoing outgoing){
        outgoingService.Add(outgoing);
        return new ModelAndView("redirect:/outgoing");
    }
    @PostMapping("/outgoingSearch")
    public String search(@RequestParam("name") String name, Model model){
        List<Outgoing> outgoings = outgoingService.findByName(name);
        if (outgoings.size() == 0){
            model.addAttribute("msg","0");
        }else{
            model.addAttribute("outgoings", outgoings);
        }
        return "outgoing_list";
    }
}
