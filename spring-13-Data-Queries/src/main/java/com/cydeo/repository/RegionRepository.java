package com.cydeo.repository;

import com.cydeo.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    //Display all regions in Canada
    //List<Region> findRegionsByCountry(String country);
    List<Region> findByCountry(String country);
    List<Region> getByCountry(String country);

    // Display all regions with country name includes 'Unite'
    List <Region> findByCountryContains(String country);

    //Display all regions with country name includes 'United' in order(region)ASC
    List<Region> findByCountryContainingOrderByRegion(String country);

    //Display top 2 regions in United States
    List<Region> findTop2ByCountry(String country);
    List<Region> findTopByCountry(String country);
    List<Region> findFirst5ByCountry(String country);
    List<Region> findTopByCountryContainsOrderByRegion(String country);



}
