package com.mypackage.servesmart.repository;

import com.mypackage.servesmart.model.MealDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<MealDetails, Long> {
    List<MealDetails> findByDate(LocalDate date);
}
