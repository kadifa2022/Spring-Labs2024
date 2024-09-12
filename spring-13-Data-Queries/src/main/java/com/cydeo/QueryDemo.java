package com.cydeo;

import com.cydeo.repository.DepartmentRepository;
import com.cydeo.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QueryDemo implements CommandLineRunner {
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;

    public QueryDemo(RegionRepository regionRepository, DepartmentRepository departmentRepository) {
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("--------------REGIONS-------------------------");
        System.out.println("RegionRepository.findByCountry(\"Canada\") = " + regionRepository.findByCountry("Canada"));
        System.out.println("RegionRepository.findByCountry(\"Asia\") = " + regionRepository.findByCountry("Asia"));
        System.out.println("RegionRepository.findByCountryContains(\"United\") = " + regionRepository.findByCountryContains("United"));
        System.out.println("RegionRepository.findByCountryContainsOrderByRegion(\"United\") = "
                + regionRepository.findByCountryContainingOrderByRegion("United"));
        System.out.println(regionRepository.findTop2ByCountry("Asia"));
        System.out.println(regionRepository.findTopByCountry("Canada"));
        System.out.println("RegionRepository.findFirst2ByCountry(\"United States\") = " + regionRepository.findFirst5ByCountry("United State"));
        System.out.println(regionRepository.findTopByCountryContainsOrderByRegion("United"));

        System.out.println("------------DEPARTMENTS---------------------------");


    }

}
