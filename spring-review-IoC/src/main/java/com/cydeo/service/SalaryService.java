package com.cydeo.service;

import com.cydeo.repository.EmployeeRepository;
import com.cydeo.repository.HoursRepository;
import com.cydeo.repository.OverTimeHours;
import com.cydeo.repository.RegularHours;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SalaryService  {

    private final EmployeeRepository employeeRepository;
   private final HoursRepository hoursRepository;


    public SalaryService(EmployeeRepository employeeRepository, @Qualifier("overTimeHours") HoursRepository hoursRepository ) {
        this.employeeRepository = employeeRepository;
        this.hoursRepository = hoursRepository;

    }

    public void calculateRegularSalary(){
        System.out.println(employeeRepository.getHourlyRate() * hoursRepository.getHours());

    }






}
