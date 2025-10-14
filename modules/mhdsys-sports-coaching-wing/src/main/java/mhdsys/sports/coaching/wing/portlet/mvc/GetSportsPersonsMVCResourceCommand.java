package mhdsys.sports.coaching.wing.portlet.mvc;

import com.liferay.petra.string.StringPool;
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
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.exception.NoSuchSchoolCollegeOfficerRegistrationException;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;
import mhdsys.sports.coaching.wing.util.SportsCoachingWingUtil;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name=" + SportsCoachinWingMvcCommand.GET_SPORTS_PERSONS }, service = MVCResourceCommand.class)
public class GetSportsPersonsMVCResourceCommand implements MVCResourceCommand {

	private static final Log _log = LogFactoryUtil.getLog(GetSportsPersonsMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		_log.info("Entry into GetSportsPersonsMVCResourceCommand ::  serveResource ::");
		
		long id = ParamUtil.getLong(resourceRequest, "id");
		_log.info("id :::" + id);
		SchoolCollegeOfficerRegistration sportsPerson = null;
		try {
			sportsPerson = SchoolCollegeOfficerRegistrationLocalServiceUtil.findByUserId(id);
		} catch (Exception e) {
			_log.error("Error in code :::" + e);
		}
		
		 Profile profile =null;
		 try {
			profile= ProfileLocalServiceUtil.findByUserId(id);
		} catch (Exception e) {
			_log.error("Error in code :::" +e);
		}
		
		 _log.info("profile :::" +profile);
		if(Validator.isNotNull(profile)) {
			 _log.info("profile :::" +profile.getDateOfBirth());
		}

		JSONObject sportsPersonJson = JSONFactoryUtil.createJSONObject();
		// Coach Deatails
		if (Validator.isNotNull(sportsPerson)) {

			sportsPersonJson.put("id", sportsPerson.getUserId());
			sportsPersonJson.put("firstName", sportsPerson.getFirstName());
			sportsPersonJson.put("lastName", sportsPerson.getLastName());
			sportsPersonJson.put("name",
					sportsPerson.getFirstName().concat(StringPool.SPACE).concat(sportsPerson.getLastName()));
			sportsPersonJson.put("schoolName", sportsPerson.getSchoolOrCollegeName());
			sportsPersonJson.put("email", sportsPerson.getEmail());
			sportsPersonJson.put("mobile", sportsPerson.getMobileNumber());
			sportsPersonJson.put("dob", Validator.isNotNull(profile) ?	new SimpleDateFormat("yyyy-MM-dd").format(profile.getDateOfBirth()) : "");
		}
		try {
			resourceResponse.getWriter().print(sportsPersonJson.toJSONString());
		} catch (IOException e) {
			_log.error("Error in code :::" + e);
		}
		return false;
	}

}
