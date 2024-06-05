package com.edu.smartpersionsys.controller;


import com.edu.smartpersionsys.pojo.CaseFile;
import com.edu.smartpersionsys.service.CaseFileService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class CaseController {
    @Autowired
    private CaseFileService caseFileService;

    //查询全部操作
    @GetMapping("/case")
    public String getAllCase(Model model) {
        System.out.println("welcome to case");
        List<CaseFile> allCase = caseFileService.getAllCase();
        model.addAttribute("casefile", allCase);

        System.out.println("allcase:");
        allCase.forEach(System.out::println);
        return "CaseFile";
    }

    //删除操作
    @GetMapping("/delete/{olderId}")
    public String deleteCase(@PathVariable int olderId, Model model, MultipartFile photoFile) {
        int caseFiles = caseFileService.deleteCaseById(olderId);
        return "redirect:/case";
    }

    //新增页面
    @RequestMapping("/AddCaseFile")
    public String insert(Model model) {
        return "AddCaseFile";
    }

    //添加操作
    @RequestMapping("/insertCaseFile")
    public String addCaseFile(CaseFile caseFile) {
        System.out.println(caseFile);
        caseFileService.add(caseFile);
        return "CaseFile";
    }


    //修改
    @PostMapping("/caseFile/edit")
    public String edit(CaseFile caseFile, Model model) {
        caseFileService.updateById(caseFile);

        return "redirect:/case";
    }

    @RequestMapping("/getById")
    public String getCaseFileById(int olderId) {
        caseFileService.findById(olderId);
        return "redirect:/case";
    }
    @RequestMapping("/page")
    public String queryAllByPage(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize,Model model){
    PageInfo<CaseFile> caseFilePageInfo = caseFileService.queryAllByPage(pageNum, pageSize);
    model.addAttribute(" caseFilePageInfo", caseFilePageInfo);
    return "redirect:/case";
}

}
