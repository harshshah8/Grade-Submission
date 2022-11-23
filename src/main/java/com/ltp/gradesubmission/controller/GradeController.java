package com.ltp.gradesubmission.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Constant;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

@Controller
public class GradeController {

    GradeRepository gradeRepository = new GradeRepository();

    @GetMapping("/grades")
    public String getGrades(Model model)
    {   
        model.addAttribute("grade", gradeRepository.getGrades());
        return "grades";
    }

    @GetMapping("/")
    public String gradeForm(Model model,@RequestParam(required = false) String id)
    {
        
         model.addAttribute("grade", getGradeIndex(id)==Constant.NOT_FOUND?  new Grade():gradeRepository.getGrade(getGradeIndex(id)));
        return "form";
    }

    @PostMapping("/handleSubmit") 
    public String submitForm(@Valid Grade grade, BindingResult result)
    {
        if(result.hasErrors())
            return "form";
        int index = getGradeIndex(grade.getId());
        if(index==Constant.NOT_FOUND)
            gradeRepository.addGrade(grade);
        else
            gradeRepository.updateGrade(index, grade);
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id)
    {   
        for(int i=0;i<gradeRepository.getGrades().size();i++)
        {
            if(gradeRepository.getGrades().get(i).getId().equals(id))
                return i;
        }
        return Constant.NOT_FOUND;

    }
    
}
 