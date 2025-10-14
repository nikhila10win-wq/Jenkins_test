package com.mhdsys.common.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = DateConversionUtil.class)

public class DateConversionUtil {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public Date convertStringToDateFormat(String date) {
		try {
			if (Validator.isNotNull(date)) {
				LOGGER.debug("Date string :: " + date);
				return new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD).parse(date);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public Date convertStringToTimeFormat(String time) {
		try {
			if (Validator.isNotNull(time)) {
				LOGGER.debug("Time string :: " + time);

				// Use SimpleDateFormat with "HH:mm" for time only (hours and minutes)
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
				return timeFormat.parse(time); // Parse the time string to Date
			}
		} catch (Exception e) {
			LOGGER.error("Error parsing time: " + e.getMessage(), e);
		}
		return null;
	}

	public String formatTimeForView(Date time) {
	    try {
	        if (time != null) {
	            // Format the time in HH:mm format (24-hour format)
	            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	            return sdf.format(time);
	        }
	    } catch (Exception e) {
	        LOGGER.error("Error formatting time: " + e.getMessage(), e);
	    }
	    return null;
	}

}
