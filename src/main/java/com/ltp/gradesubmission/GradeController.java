package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    List<Grade> studenGrades = new ArrayList<>();
    @GetMapping("/grades")
    public String getGrades(Model model)
    {   
        model.addAttribute("grade", studenGrades);
        return "grades";
    }

    @GetMapping("/")
    public String gradeForm(Model model,@RequestParam(required = false) String name)
    {
        
         model.addAttribute("grade", getGradeIndex(name)==-1000?  new Grade():studenGrades.get(getGradeIndex(name)));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade)
    {
        int index = getGradeIndex(grade.getName());
        if(index==-1000)
            studenGrades.add(grade);
        else
            studenGrades.set(index, grade);
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String names)
    {   
        for(int i=0;i<studenGrades.size();i++)
        {
            if(studenGrades.get(i).getName().equals(names))
                return i;
        }
        return -1000;

    }
    
}
 