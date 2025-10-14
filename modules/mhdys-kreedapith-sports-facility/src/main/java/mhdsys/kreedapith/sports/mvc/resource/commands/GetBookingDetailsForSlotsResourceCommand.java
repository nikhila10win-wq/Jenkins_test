package mhdsys.kreedapith.sports.mvc.resource.commands;

import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.model.sportsFacilityBookingTable;
import com.mhdsys.schema.service.sportsFacilityBookingLocalServiceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys;

@Component(
    immediate = true, 
    property = { 
        "javax.portlet.name=" + MhdysKreedapithSportsFacilityPortletKeys.MHDYSKREEDAPITHSPORTSFACILITY,
        "mvc.command.name=" + MhdysKreedapithSportsFacilityPortletKeys.GETBOOKINGDETAILSFORSLOTSRESOURCECOMMAND
    }, 
    service = MVCResourceCommand.class
)
public class GetBookingDetailsForSlotsResourceCommand implements MVCResourceCommand {
    private Log _log = LogFactoryUtil.getLog(GetBookingDetailsForSlotsResourceCommand.class);

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
            throws PortletException {
        _log.info("GetBookingDetailsForSlotsResourceCommand Starts---------");

        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long selectedFacilityVal = ParamUtil.getLong(resourceRequest, "selectedFacilityVal");
        String sportCourtVal = ParamUtil.getString(resourceRequest, "sportCourtVal");
        String selectedType = ParamUtil.getString(resourceRequest, "selectedType");

        int numberOfMonths = ParamUtil.getInteger(resourceRequest, "numberOfMonths");
        String batch = ParamUtil.getString(resourceRequest, "batch");
        String timeFrom = ParamUtil.getString(resourceRequest, "timeFrom");
        String timeTo = ParamUtil.getString(resourceRequest, "timeTo");
        String dailyOrDatewise = ParamUtil.getString(resourceRequest, "dailyOrDatewise");
        String bookingsCheck = ParamUtil.getString(resourceRequest, "bookingsCheck");
        // If editing the booking should not overlap for that particular booking
        long facilityBookingId = ParamUtil.getLong(resourceRequest, "facilityBookingId");

        String dateStr = ParamUtil.getString(resourceRequest, "Date");
        Date date = null;
        LocalDate selectedLocalDate = null;
        try {
            DateFormat daterFormatter = new SimpleDateFormat("yyyy-MM-dd");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (Validator.isNotNull(dateStr)) {
                date = daterFormatter.parse(dateStr);
                selectedLocalDate = LocalDate.parse(dateStr, formatter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        _log.info("selectedLocalDate:: " + selectedLocalDate);

        // ---------------- Overlap Checking ----------------
        if ("bookingsCheck".equalsIgnoreCase(bookingsCheck) && selectedFacilityVal > 0
                && Validator.isNotNull(sportCourtVal) && Validator.isNotNull(selectedType) && numberOfMonths > 0
                && Validator.isNotNull(batch) && Validator.isNotNull(timeFrom) && Validator.isNotNull(timeTo)
                && Validator.isNotNull(dailyOrDatewise)) {

            try {
                List<sportsFacilityBooking> overlappingBookings = new ArrayList<>();

                // Fetch all bookings of same facility/court/type/batch
//                DSLQuery baseQuery = DSLQueryFactoryUtil
//                        .select(sportsFacilityBookingTable.INSTANCE)
//                        .from(sportsFacilityBookingTable.INSTANCE)
//                        .where(
//                            sportsFacilityBookingTable.INSTANCE.selectedFacility.eq(selectedFacilityVal)
//                            .and(sportsFacilityBookingTable.INSTANCE.sportCourt.eq(sportCourtVal))
//                            .and(sportsFacilityBookingTable.INSTANCE.type.eq(selectedType))
//                            .and(sportsFacilityBookingTable.INSTANCE.batch.eq(batch))
//                        );
                
                Predicate wherePredicate =
                	    sportsFacilityBookingTable.INSTANCE.selectedFacility.eq(selectedFacilityVal)
                	    .and(sportsFacilityBookingTable.INSTANCE.sportCourt.eq(sportCourtVal))
                	    .and(sportsFacilityBookingTable.INSTANCE.type.eq(selectedType))
                	    .and(sportsFacilityBookingTable.INSTANCE.batch.eq(batch));

                	if (facilityBookingId > 0) {
                	    wherePredicate = wherePredicate.and(
                	        sportsFacilityBookingTable.INSTANCE.facilityBookingId.neq(facilityBookingId)
                	    );
                	}

                	DSLQuery baseQuery = DSLQueryFactoryUtil
                	    .select(sportsFacilityBookingTable.INSTANCE)
                	    .from(sportsFacilityBookingTable.INSTANCE)
                	    .where(wherePredicate);
                	

                List<sportsFacilityBooking> existingBookings = sportsFacilityBookingLocalServiceUtil.dslQuery(baseQuery);

                // Compute new booking range
                LocalDate bookingStart = selectedLocalDate;
                LocalDate bookingEnd = null;

                if ("daily".equalsIgnoreCase(dailyOrDatewise)) {
                    bookingEnd = selectedLocalDate.plusMonths(numberOfMonths - 1)
                            .with(TemporalAdjusters.lastDayOfMonth());
                } else {
                    bookingEnd = selectedLocalDate.plusMonths(numberOfMonths - 1);
                }

//                Date utilStart = Date.from(bookingStart.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                Date utilEnd = Date.from(bookingEnd.atStartOfDay(ZoneId.systemDefault()).toInstant());
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                for (sportsFacilityBooking existing : existingBookings) {
                    LocalDate existStart = existing.getDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate existEnd = getEndDateBasedOnNoOfMonths(existing.getDate(),
                            Integer.valueOf(existing.getNumberOfMonths()));

                    boolean overlaps = !(bookingEnd.isBefore(existStart) || bookingStart.isAfter(existEnd));

                    if (overlaps) {
                        LocalTime existingFrom = LocalTime.parse(existing.getTimeFrom(), timeFormatter);
                        LocalTime existingTo = LocalTime.parse(existing.getTimeTo(), timeFormatter);
                        LocalTime newFrom = LocalTime.parse(timeFrom, timeFormatter);
                        LocalTime newTo = LocalTime.parse(timeTo, timeFormatter);

                        boolean timeOverlap = existingFrom.isBefore(newTo) && existingTo.isAfter(newFrom);

                        if (timeOverlap) {
                            overlappingBookings.add(existing);
                        }
                    }
                }
                _log.info("overlappingBookings:: "+ overlappingBookings);
                JSONObject finalResponse = JSONFactoryUtil.createJSONObject();
                JSONArray overlapArray = JSONFactoryUtil.createJSONArray();

                for (sportsFacilityBooking existing : overlappingBookings) {
                    JSONObject obj = JSONFactoryUtil.createJSONObject();
                    obj.put("date", existing.getDate().toString());  // or format to yyyy-MM-dd
                    obj.put("timeFrom", existing.getTimeFrom());
                    obj.put("timeTo", existing.getTimeTo());
                    overlapArray.put(obj);
                }

                finalResponse.put("overlappingBookings", !overlappingBookings.isEmpty());
                finalResponse.put("details", overlapArray);

                resourceResponse.setContentType("application/json");
                resourceResponse.getWriter().write(finalResponse.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // ---------------- Fetch bookings for UI ----------------
        else {
            try {
                DSLQuery allBookingsQuery = DSLQueryFactoryUtil
                        .select(sportsFacilityBookingTable.INSTANCE)
                        .from(sportsFacilityBookingTable.INSTANCE)
                        .where(sportsFacilityBookingTable.INSTANCE.selectedFacility.eq(selectedFacilityVal)
                                .and(sportsFacilityBookingTable.INSTANCE.sportCourt.eq(sportCourtVal))
                                .and(sportsFacilityBookingTable.INSTANCE.type.eq(selectedType)));

                List<sportsFacilityBooking> allResults = sportsFacilityBookingLocalServiceUtil.dslQuery(allBookingsQuery);

                // Expand into fullyBookedDates
                Map<String, Set<String>> dateToBatches = new HashMap<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                for (sportsFacilityBooking booking : allResults) {
                    LocalDate s = booking.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate e = getEndDateBasedOnNoOfMonths(booking.getDate(),  Integer.valueOf(booking.getNumberOfMonths()));
                    LocalDate current = s;

                    while (!current.isAfter(e)) {
                        boolean add = false;
                        if ("daily".equalsIgnoreCase(booking.getDailyOrDate())) {
                            add = true;
                        } else if ("datewise".equalsIgnoreCase(booking.getDailyOrDate())) {
                            if (current.getDayOfMonth() == s.getDayOfMonth()) {
                                add = true;
                            }
                        }
                        if (add) {
                            String dateKey = current.format(formatter);
                            dateToBatches.computeIfAbsent(dateKey, k -> new HashSet<>()).add(booking.getBatch());
                        }
                        current = current.plusDays(1);
                    }
                }

                Set<String> allBatches = new HashSet<>(Arrays.asList(
                        "6AM to 10AM", "10AM to 2PM", "2PM to 6PM", "6PM to 10PM"
                ));

                JSONArray fullyBookedDates = JSONFactoryUtil.createJSONArray();
                for (Map.Entry<String, Set<String>> entry : dateToBatches.entrySet()) {
                    if (entry.getValue().containsAll(allBatches)) {
                        fullyBookedDates.put(entry.getKey());
                    }
                }

                // Date-specific bookings
                JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
                if (Validator.isNotNull(date)) {
                    LocalDate queryDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    for (sportsFacilityBooking booking : allResults) {
                        LocalDate s = booking.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate e = getEndDateBasedOnNoOfMonths(booking.getDate(),  Integer.valueOf(booking.getNumberOfMonths()));

                        if (!queryDate.isBefore(s) && !queryDate.isAfter(e)) {
                            boolean matches = false;
                            if ("daily".equalsIgnoreCase(booking.getDailyOrDate())) {
                                matches = true;
                            } else if ("datewise".equalsIgnoreCase(booking.getDailyOrDate())) {
                                if (queryDate.getDayOfMonth() == s.getDayOfMonth()) {
                                    matches = true;
                                }
                            }

                            if (matches) {
                                JSONObject obj = JSONFactoryUtil.createJSONObject();
                                obj.put("startDate", booking.getDate());
                                obj.put("endDate", getEndDateBasedOnNoOfMonths(booking.getDate(),  Integer.valueOf(booking.getNumberOfMonths())));
                                obj.put("dailOrDaywise", booking.getDailyOrDate());
                                obj.put("batch", booking.getBatch());
                                obj.put("timeFrom", booking.getTimeFrom());
                                obj.put("timeTo", booking.getTimeTo());
                                jsonArray.put(obj);
                            }
                        }
                    }
                }

                JSONObject finalResponse = JSONFactoryUtil.createJSONObject();
                finalResponse.put("bookings", jsonArray);
                finalResponse.put("fullyBookedDates", fullyBookedDates);
                _log.info("finalResponse:: "+finalResponse.toJSONString());
                resourceResponse.setContentType("application/json");
                resourceResponse.getWriter().write(finalResponse.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        _log.info("GetBookingDetailsForSlotsResourceCommand Ends---------");
        return false;
    }
    
    private LocalDate getEndDateBasedOnNoOfMonths(Date startDate, int noOfMonths) {
        LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // Add (noOfMonths - 1) months and keep same day-of-month
        LocalDate end = start.plusMonths(noOfMonths - 1);

        // Handle cases where the target month has fewer days
        if (end.getDayOfMonth() != start.getDayOfMonth()) {
            // fallback to last valid day of that month
            end = end.withDayOfMonth(Math.min(start.getDayOfMonth(), end.lengthOfMonth()));
        }

        return end;
    }
}
