package com.example.agecalculator.service; 
public class AgeDetails { 
    private final int years; 
    private final int months; 
    private final int days; 
    private final int totalMonths; 
    private final long totalWeeks; 
    private final long remainingDays; 
    private final long totalDays; 
    private final long totalHours; 
    private final long totalMinutes; 
    private final long totalSeconds; 
    public AgeDetails(int years, int months, int days, int totalMonths, long totalWeeks, long remainingDays, long totalDays, long totalHours, long totalMinutes, long totalSeconds) { 
        this.years = years; 
        this.months = months; 
        this.days = days; 
        this.totalMonths = totalMonths; 
        this.totalWeeks = totalWeeks; 
        this.remainingDays = remainingDays; 
        this.totalDays = totalDays; 
        this.totalHours = totalHours; 
        this.totalMinutes = totalMinutes; 
        this.totalSeconds = totalSeconds; 
    } 
    // Getters for all fields 
    public int getYears() { return years; } 
    public int getMonths() { return months; } 
    public int getDays() { return days; } 
    public int getTotalMonths() { return totalMonths; } 
    public long getTotalWeeks() { return totalWeeks; } 
    public long getRemainingDays() { return remainingDays; } 
    public long getTotalDays() { return totalDays; } 
    public long getTotalHours() { return totalHours; } 
    public long getTotalMinutes() { return totalMinutes; } 
    public long getTotalSeconds() { return totalSeconds; } 
} 