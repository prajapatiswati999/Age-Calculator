package com.example.agecalculator.controller; 
import com.example.agecalculator.service.AgeCalculatorService; 
import com.example.agecalculator.service.AgeDetails; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 
import java.time.format.DateTimeParseException; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
@Controller 
public class AgeCalculatorController { 
    private static final Logger logger = Logger.getLogger(AgeCalculatorController.class.getName()); 
    @Autowired 
    private AgeCalculatorService ageCalculatorService; 
    @RequestMapping("/") 
    public String index() { 
        logger.info("Accessed index page."); 
        return "index"; 
    } 
    @PostMapping("/calculate") 
    public String calculateAge(@RequestParam("dob") String dob, @RequestParam(value = "target_date", required = false) String targetDate, Model model) { 
        logger.info("Received request to calculate age with DOB: " + dob + " and target date: " + targetDate); 
        try { 
            LocalDate dobDate = LocalDate.parse(dob, DateTimeFormatter.ISO_DATE); 
            LocalDate targetDateObj = targetDate != null ? LocalDate.parse(targetDate, DateTimeFormatter.ISO_DATE) : LocalDate.now(); 
            if (dobDate.isAfter(targetDateObj)) { 
                throw new IllegalArgumentException("Date of Birth cannot be after the target date."); 
            } 
            AgeDetails ageDetails = ageCalculatorService.calculateAge(dobDate, targetDateObj); 
            model.addAttribute("ageDetails", ageDetails); 
            logger.info("Age calculation successful."); 
        } catch (DateTimeParseException e) { 
            String errorMessage = "Invalid date format. Please use YYYY-MM-DD."; 
            model.addAttribute("errorMessage", errorMessage); 
            logger.log(Level.SEVERE, errorMessage, e); 
        } catch (IllegalArgumentException e) { 
            String errorMessage = e.getMessage(); 
            model.addAttribute("errorMessage", errorMessage); 
            logger.log(Level.SEVERE, errorMessage, e); 
        } 
        model.addAttribute("dob", dob); 
        model.addAttribute("targetDate", targetDate); 
        return "index"; 
    } 
}