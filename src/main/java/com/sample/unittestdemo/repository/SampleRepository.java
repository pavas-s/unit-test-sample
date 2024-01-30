package com.sample.unittestdemo.repository;

import com.sample.unittestdemo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SampleRepository extends JpaRepository<Country, Integer> {
    Set<Country> findCountriesByYearSinceActiveBefore(int year);
    Set<Country> findCountriesByYearSinceActiveAfter(int year);
}
