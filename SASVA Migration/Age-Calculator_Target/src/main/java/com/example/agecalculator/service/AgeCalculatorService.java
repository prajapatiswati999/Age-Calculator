package com.example.agecalculator.service; 
import org.springframework.stereotype.Service; 
import java.time.LocalDate; 
import java.time.Period; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
@Service 
public class AgeCalculatorService { 
    private static final Logger logger = Logger.getLogger(AgeCalculatorService.class.getName()); 
    public AgeDetails calculateAge(LocalDate dob, LocalDate targetDate) { 
        logger.info("Calculating age from DOB: " + dob + " to target date: " + targetDate); 
        if (dob.isAfter(targetDate)) { 
            logger.severe("Date of Birth cannot be after the target date."); 
            throw new IllegalArgumentException("Date of Birth cannot be after the target date."); 
        } 
        Period period = Period.between(dob, targetDate); 
        int years = period.getYears(); 
        int months = period.getMonths(); 
        int days = period.getDays(); 
        long totalDays = dob.until(targetDate).getDays(); 
        long totalWeeks = totalDays / 7; 
        long remainingDays = totalDays % 7; 
        long totalHours = totalDays * 24; 
        long totalMinutes = totalHours * 60; 
        long totalSeconds = totalMinutes * 60; 
        logger.info("Age calculated: " + years + " years, " + months + " months, " + days + " days"); 
        logger.info("Total months: " + (years * 12 + months) + ", Total weeks: " + totalWeeks + ", Remaining days: " + remainingDays); 
        logger.info("Total days: " + totalDays + ", Total hours: " + totalHours + ", Total minutes: " + totalMinutes + ", Total seconds: " + totalSeconds); 
        return new AgeDetails(years, months, days, years * 12 + months, totalWeeks, remainingDays, totalDays, totalHours, totalMinutes, totalSeconds); 
    } 
}