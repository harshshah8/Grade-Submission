package com.ltp.gradesubmission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ltp.gradesubmission.Grade;

@Component
public class GradeRepository {
    
    private List<Grade> studenGrades = new ArrayList<>();
    
    public Grade getGrade(int index)
    {
        return studenGrades.get(index);
    }

    public void addGrade(Grade grade)
    {
        studenGrades.add(grade);
    }

    public void updateGrade(int index, Grade grade)
    {
        studenGrades.set(index, grade);
    }

    public List<Grade> getGrades()
    {
        return studenGrades;
    }
}
