package HospitalDb;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

public final class Utils {
    private Utils() {}
    
    public static java.util.Date sqlDateToDate(final java.sql.Date sqlDate) {
        return sqlDate == null ? null : new java.util.Date(sqlDate.getTime());
    }
    
    public static java.sql.Date dateToSqlDate(final java.util.Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }
    public static java.util.Date sqlDateToDateTime(final java.sql.Timestamp sqlDate) {
        return sqlDate == null ? null : new java.util.Date(sqlDate.getTime());
    }
    
    public static java.sql.Timestamp dateToSqlDateTime(final java.util.Date date) {
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }
    
    public static Optional<java.util.Date> buildDate(final int day, final int month, final int year) {
        try {
            final String dateFormatString = "dd/MM/yyyy";
            final String dateString = day + "/" + month + "/" + year;
            final java.util.Date date = new SimpleDateFormat(dateFormatString, Locale.ITALIAN).parse(dateString);
            return Optional.of(date);
        } catch (final ParseException e) {
            return Optional.empty();
        }
    }
    public static Optional<java.util.Date> buildDateTime(String dateTime) {
        try {
            final String dateFormatString = "dd-MM-yyyy";
            final String timeFormatString = "HH:mm:ss";
            final java.util.Date date = new SimpleDateFormat(dateFormatString + " " + timeFormatString, Locale.ITALIAN).parse(dateTime);
            return Optional.of(date);
        } catch (final ParseException e) {
            return Optional.empty();
        }
    }
    
    public static Optional<java.util.Date> buildDate(String dateString) {
        try {
            final String dateFormatString = "dd-MM-yyyy";
            final java.util.Date date = new SimpleDateFormat(dateFormatString, Locale.ITALIAN).parse(dateString);
            return Optional.of(date);
        } catch (final ParseException e) {
            return Optional.empty();
        }
    }
    public static String stringSql(String str) {
    	if(!str.equals("")) {
    		return "'" + str + "'";
    	}else {
    		return "null";
    	}
     
    }
    
}