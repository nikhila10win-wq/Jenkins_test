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
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;
import mhdsys.sports.coaching.wing.util.SportsCoachingWingUtil;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name=" + SportsCoachinWingMvcCommand.GET_COACH }, service = MVCResourceCommand.class)
public class GetCoachMVCResourceCommand implements MVCResourceCommand {

	private static final Log _log = LogFactoryUtil.getLog(GetCoachMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		_log.info("Entry into GetCoachMVCResourceCommand ::  serveResource ::");
		
		long id = ParamUtil.getLong(resourceRequest, "id");
		_log.info("id :::" + id);
		SchoolCollegeOfficerRegistration coach = null;
		try {
			coach = SchoolCollegeOfficerRegistrationLocalServiceUtil.findByUserId(id);
		} catch (Exception e) {
			_log.error("Error in code :::" + e);
		}

		JSONObject coachJson = JSONFactoryUtil.createJSONObject();
		// Coach Deatails
		if (Validator.isNotNull(coach)) {

			coachJson.put("id", coach.getUserId());
			coachJson.put("firstName", coach.getFirstName());
			coachJson.put("lastName", coach.getLastName());
			coachJson.put("name",
					coach.getFirstName().concat(StringPool.SPACE).concat(coach.getLastName()));
			coachJson.put("schoolName", coach.getSchoolOrCollegeName());
			coachJson.put("email", coach.getEmail());
			coachJson.put("mobile", coach.getMobileNumber());
			coachJson.put("type",Validator.isNotNull(coach.getType())?coach.getType():"Private");
		}
		try {
			resourceResponse.getWriter().print(coachJson.toJSONString());
		} catch (IOException e) {
			_log.error("Error in code :::" + e);
		}
		return false;
	}

}
