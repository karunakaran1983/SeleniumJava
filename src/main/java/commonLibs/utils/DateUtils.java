package commonLibs.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	
	public static String getCurrentDateAndTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		
		String dateTimeFormat = dateTime.format(formatter);
		return dateTimeFormat;
	}
}
