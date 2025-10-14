package mhdsys.sports.coaching.wing.portlet.mvc;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.mhdsys.schema.model.Coach;
import com.mhdsys.schema.model.SportsPerson;
import com.mhdsys.schema.model.TrainingCentre;
import com.mhdsys.schema.model.TrainingCentreFinancialDetail;
import com.mhdsys.schema.model.TrainingCentreReport;
import com.mhdsys.schema.service.CoachLocalServiceUtil;
import com.mhdsys.schema.service.SportsPersonLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreFinancialDetailLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreReportLocalServiceUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import aQute.service.reporter.Report;
import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name="+MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
	        "mvc.command.name=/getTrainingCentre"
	    },
	    service = MVCResourceCommand.class
	)
public class GetSportCoachingWingMVCResourceCommand implements MVCResourceCommand {

    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
        JSONObject responseJson = JSONFactoryUtil.createJSONObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            long trainingCentreId = ParamUtil.getLong(resourceRequest, "trainingCentreId");

            if (trainingCentreId <= 0) {
                throw new IllegalArgumentException("Invalid training centre ID");
            }

            // Fetch Training Centre
            TrainingCentre trainingCentre = TrainingCentreLocalServiceUtil.getTrainingCentre(trainingCentreId);
            JSONObject trainingCentreJson = JSONFactoryUtil.createJSONObject();
            trainingCentreJson.put("division", trainingCentre.getDivision());
            trainingCentreJson.put("district", trainingCentre.getDistrict());
            trainingCentreJson.put("centreType", trainingCentre.getCentreType());
            trainingCentreJson.put("sportsType", trainingCentre.getSportsType());
            trainingCentreJson.put("geoTag", trainingCentre.getGeoTag());
            trainingCentreJson.put("gisMap", trainingCentre.getGisMap());
            trainingCentreJson.put("dprDocPath", trainingCentre.getDprDocPath());

            // Fetch Coaches
            List<Coach> coaches = CoachLocalServiceUtil.findByTrainingCentreId(trainingCentreId);
            JSONArray coachesJson = JSONFactoryUtil.createJSONArray();
            for (Coach coach : coaches) {
                JSONObject coachJson = JSONFactoryUtil.createJSONObject();
                coachJson.put("coachId", coach.getCoachId());
                coachJson.put("coachType", coach.getCoachType());
                coachJson.put("fullName", coach.getFullName());
                coachJson.put("mobileNumber", coach.getMobileNumber());
                coachJson.put("email", coach.getEmail());
                coachJson.put("sportsName", coach.getSportsName());
                coachJson.put("address", coach.getAddress());
                coachJson.put("tracksuitSize", coach.getTracksuitSize());
                coachJson.put("tshirtSize", coach.getTshirtSize());
                coachJson.put("shortsSize", coach.getShortsSize());
                coachJson.put("shoesSize", coach.getShoesSize());
                coachJson.put("remarks", coach.getRemarks());
                coachesJson.put(coachJson);
            }

            // Fetch Sports Persons
            List<SportsPerson> sportsPersons = SportsPersonLocalServiceUtil.findByTrainingCentreId(trainingCentreId);
            JSONArray sportsPersonsJson = JSONFactoryUtil.createJSONArray();
            for (SportsPerson sp : sportsPersons) {
                JSONObject spJson = JSONFactoryUtil.createJSONObject();
                spJson.put("id", sp.getSportsPersonId());
                spJson.put("fullName", sp.getFullName());
                spJson.put("dateOfBirth", sp.getDateOfBirth() != null ? dateFormat.format(sp.getDateOfBirth()) : "");
                spJson.put("schoolName", sp.getSchoolName());
                spJson.put("mobileNumber", sp.getMobileNumber());
                spJson.put("address", sp.getAddress());
                spJson.put("entryDate", sp.getEntryDate() != null ? dateFormat.format(sp.getEntryDate()) : "");
                spJson.put("ranking", sp.getRanking());
                spJson.put("achievementLevel", sp.getAchievementLevel());
                spJson.put("remarks", sp.getRemarks());
                sportsPersonsJson.put(spJson);
            }

            // Fetch Financial Details
            List<TrainingCentreFinancialDetail> TrainingCentreFinancialDetails = TrainingCentreFinancialDetailLocalServiceUtil.findByTrainingCentreId(trainingCentreId);
            JSONArray financialDetailsJson = JSONFactoryUtil.createJSONArray();
            for (TrainingCentreFinancialDetail fd : TrainingCentreFinancialDetails) {
                JSONObject fdJson = JSONFactoryUtil.createJSONObject();
                fdJson.put("financialDetailId", fd.getFinancialDetailId());
                fdJson.put("description", fd.getDescription());
                fdJson.put("grAmount", fd.getGrAmount());
                fdJson.put("requestedAmount", fd.getRequestedAmount());
                financialDetailsJson.put(fdJson);
            }

            // Fetch Reports
            List<TrainingCentreReport> reports = TrainingCentreReportLocalServiceUtil.findByTrainingCentreId(trainingCentreId);
            JSONArray reportsJson = JSONFactoryUtil.createJSONArray();
            for (TrainingCentreReport report : reports) {
                JSONObject reportJson = JSONFactoryUtil.createJSONObject();
                reportJson.put("reportId", report.getReportId());
                reportJson.put("description", report.getDescription());
                reportJson.put("remarks", report.getRemarks());
                reportJson.put("attachmentPath", report.getAttachmentPath());
                reportsJson.put(reportJson);
            }

            // Build response
            responseJson.put("status", "success");
            responseJson.put("trainingCentre", trainingCentreJson);
            responseJson.put("coaches", coachesJson);
            responseJson.put("sportsPersons", sportsPersonsJson);
            responseJson.put("financialDetails", financialDetailsJson);
            responseJson.put("reports", reportsJson);

        } catch (Exception e) {
            responseJson.put("status", "error");
            responseJson.put("message", "Error retrieving form data: " + e.getMessage());
            LOG.error(e);
        }

        try {
			resourceResponse.getWriter().write(responseJson.toString());
		} catch (IOException e) {
			LOG.error(e);
		}
        return true;
    }
    private static Log LOG = LogFactoryUtil.getLog(GetSportCoachingWingMVCResourceCommand.class);
}
