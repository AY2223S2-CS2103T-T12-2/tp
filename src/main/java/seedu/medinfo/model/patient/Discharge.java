package seedu.medinfo.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 * Represents a Patient's discharge date in MedInfo.
 */
public class Discharge {

    public static final String MESSAGE_CONSTRAINTS = "Discharge date-time should be a valid future date-time" +
            " of the form dd/MM/yyyy HHmm";
    public static final String DEFAULT_DISCHARGE = "To Be Confirmed";

    public final String value;

    final static String DATE_FORMAT = "dd/MM/yyyy HHmm";

    /**
     * Constructs a {@code Discharge}.
     *
     * @param discharge A valid discharge.
     */
    public Discharge(String discharge) {
        requireNonNull(discharge);
        checkArgument(isValidDischarge(discharge), MESSAGE_CONSTRAINTS);
        value = discharge;
    }


    /**
     * Returns true if a given discharge date-time is valid.
     */

    public static boolean isValidDischarge(String date) {
        if (date.equals(DEFAULT_DISCHARGE)) {
            return true;
        }
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    /**
     * Returns true if a given discharge date-time is valid and in the future.
     */

    public static boolean isValidFutureDischarge(String date) {
        if (date.equals(DEFAULT_DISCHARGE)) {
            return true;
        }
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            return df.parse(date).compareTo(new Date()) > 0;
        } catch (ParseException e) {
            return false;
        }

    }

    /**
     * Returns the dateTime.
     * @return LocalDateTime
     */
    public LocalDateTime getDateTime() {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(value, format);
        } catch (DateTimeParseException e) {
            return LocalDateTime.MIN;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Discharge // instanceof handles nulls
                && value.equals(((Discharge) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

