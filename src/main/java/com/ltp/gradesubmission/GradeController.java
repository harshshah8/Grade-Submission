package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.LinkLoopException;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.boot.devtools.tunnel.payload.HttpTunnelPayload;
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
    public String gradeForm(Model model,@RequestParam(required = false) String id)
    {
        
         model.addAttribute("grade", getGradeIndex(id)==Constant.NOT_FOUND?  new Grade():studenGrades.get(getGradeIndex(id)));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade)
    {
        int index = getGradeIndex(grade.getId());
        if(index==Constant.NOT_FOUND)
            studenGrades.add(grade);
        else
            studenGrades.set(index, grade);
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id)
    {   
        for(int i=0;i<studenGrades.size();i++)
        {
            if(studenGrades.get(i).getId().equals(id))
                return i;
        }
        return Constant.NOT_FOUND;

    }
    
}
 