package com.cydeo.repository;

import com.cydeo.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class DBEmployeeRepository implements EmployeeRepository{



    // assume we are getting this data from DB
    @Override
    public int getHourlyRate() {
        Employee employee = new Employee("John Finch", "IT",55);
        return employee.getHourlyRate();
    }
}
