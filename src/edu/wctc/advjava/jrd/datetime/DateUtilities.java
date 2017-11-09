package edu.wctc.advjava.jrd.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * This class is responsible for providing common conversion functionality between string and
 * LocalDate and LocalDateTime objects in Java 8+. 
 * 
 * @author Jenna
 * @version 1.0
 */
public class DateUtilities {
    private DateTimeFormatter formatter;
    private DateValidator dateValidator;
    private String pattern = "d MMM uuuu";
    
    public DateUtilities(DateValidator dv) {
        setFormatter(DateTimeFormatter.ofPattern(this.getPattern()));
        setDateValidator(dv);
    }
       
    public DateUtilities(String pattern, DateValidator dv) {
        setFormatter(DateTimeFormatter.ofPattern(pattern));
        setDateValidator(dv);
    }

    public final DateValidator getDateValidator() {
        return dateValidator;
    }

    public final void setDateValidator(DateValidator dateValidator) {
        if (dateValidator == null) {
            throw new IllegalArgumentException("A date validator object must be provided!");
        }
        this.dateValidator = dateValidator;
    }
    
    public final String getPattern() {
        return pattern;
    }

    public final void setPattern(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new IllegalArgumentException("You must provide a formatting pattern!");
        }
        this.pattern = pattern;
    }
    
    public final DateTimeFormatter getFormatter() {
        return formatter;
    }
    
    public final void setFormatter(DateTimeFormatter formatter) throws NullPointerException {
        if (formatter == null) {
            throw new NullPointerException("Formatter object cannot be null!");
        }
        this.formatter = formatter;
    }
    
    /**
     * Convert a LocalDate object into a String, according to the pattern specified by 
     * the format object.
     * 
     * @param localDate - a LocalDate object
     * @return a String formatted according to the pattern implemented by the formatter
     * @throws IllegalArgumentException if parameter is not LocalDate object or is null
     */
    public final String toString(LocalDate localDate) {
        return this.getFormatter().format(localDate);
    }
    
    /**
     * Converts a LocalDateTime object to a string
     * 
     * @param localDateTime
     * @return String formatted according to pattern set in the formatter. 
     * @throws IllegalArgumentException if localDateTime parameter is null.
     */
    public final String toString(LocalDateTime localDateTime) {
        return this.getFormatter().format(localDateTime);
    }
    
    /**
     * Converts a LocalDate object to a string according to the given pattern. 
     * 
     * @param date
     * @param pattern
     * @return
     * @throws IllegalArgumentException 
     */
    public final String toString(LocalDate date, String pattern) throws IllegalArgumentException {
        return this.getFormatter().format(date);
    }
    
    /**
     * Converts a LocalDateTime object into a LocalDate object.
     * 
     * @param localDateTime - a LocalDateTime object
     * @return a LocalDate object
     * @throws IllegalArgumentException if LocalDateTime object is null.
     */
    public final LocalDate convertLocalDateTimeToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }
    
    /**
     * Converts a LocalDate object into a LocalDateTime object.
     * 
     * @param localDate - a LocalDate object
     * @return a LocalDateTime object
     * @throws IllegalArgumentException if LocalDate parameter is null.
     */
    public final LocalDateTime convertLocalDateToLocalDateTime(LocalDate localDate) {
        Month month = localDate.getMonth();
        int year = localDate.getYear();
        int dayOfMonth = localDate.getDayOfMonth();
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
        return localDateTime;
    }
    
    /**
     * Converts a string into a LocalDate object by validating its format and, if valid, 
     * parsing it into a LocalDate object. 
     * 
     * @param date as String
     * @return LocalDate object
     * @throws IllegalArgumentException if date parameter is null or empty.
     */
    public final LocalDate convertToLocalDate(String date) {
        if(!this.getDateValidator().isDateValid(date)) {
            throw new IllegalArgumentException("The date provided is not valid!");
        }
        LocalDate localDate = LocalDate.parse(date, this.getFormatter());
        return localDate;
    }
    
    /**
     * Returns the period of time between one LocalDateTime object and another.
     * 
     * @param dateOne - first LocalDateTime object
     * @param dateTwo - another LocalDateTime object to find the difference between the first
     * @return Period object specifying the time difference between the two parameters
     */
    public final LocalDateTime getDateDifference(LocalDateTime dateOne, LocalDateTime dateTwo) {
        long diffInDays = ChronoUnit.DAYS.between(dateOne, dateTwo);
        long diffInHours = ChronoUnit.HOURS.between(dateOne, dateTwo);
        long diffInMinutes = ChronoUnit.MINUTES.between(dateOne, dateTwo);
        long diffInSeconds = ChronoUnit.SECONDS.between(dateOne, dateTwo);
        LocalDateTime ldt = LocalDateTime.of(0, 0, 0, (int)diffInHours, (int)diffInMinutes, (int)diffInSeconds);
        return ldt;
    }
    
    
}
