
package edu.wctc.advjava.jrd.datetime;

import java.text.ParseException;

/**
 *
 * @author Jenna
 */
public class DateValidator {
    
    public final boolean isDateValid(String date) {
        boolean isValid = true;
        
        if (date == null || date.isEmpty()) {
            isValid = false;
        }
        
        if (!date.matches("^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$")) {
            isValid = false;
        }
        
        return isValid;
    }
   
}
