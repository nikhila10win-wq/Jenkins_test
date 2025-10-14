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
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
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

@Component(
		immediate=true,
	    property = { 
	    		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
	    		"mvc.command.name="+SportsCoachinWingMvcCommand.GET_ALL_SPORTS_PRESONS
	    }, 
	    service = MVCResourceCommand.class
)
public class GetAllSportsPersonsMVCResourceCommand  implements MVCResourceCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(GetAllSportsPersonsMVCResourceCommand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
			_log.info("Entry into GetAllSportsPersonsMVCResourceCommand ::  serveResource ::");
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			JSONArray sportsPersonJSonArray =JSONFactoryUtil.createJSONArray();
			
			//Coach Deatails
 			List<SchoolCollegeOfficerRegistration> sportsPersons = SportsCoachingWingUtil.SchoolCollegeOfficersByDesignation(themeDisplay.getCompanyId(),RoleConstant.SPORTS_PERSON);
 			if(Validator.isNotNull(sportsPersons) && sportsPersons.size()>0){
 				for(SchoolCollegeOfficerRegistration sportsperson:sportsPersons) {
 					JSONObject sportsPersonJson = JSONFactoryUtil.createJSONObject();
 					sportsPersonJson.put("id", sportsperson.getUserId());
 					sportsPersonJson.put("firstName", sportsperson.getFirstName());
 					sportsPersonJson.put("lastName", sportsperson.getLastName());
 					sportsPersonJson.put("name", sportsperson.getFirstName().concat(StringPool.SPACE).concat(sportsperson.getLastName()));
 					sportsPersonJson.put("schoolName", sportsperson.getSchoolOrCollegeName());
 					sportsPersonJson.put("email", sportsperson.getEmail());
 					sportsPersonJson.put("mobile", sportsperson.getMobileNumber());
 					sportsPersonJson.put("dob","2007-12-21");
 					sportsPersonJSonArray.put(sportsPersonJson);
 				}
 			}
			try {
				resourceResponse.getWriter().print(sportsPersonJSonArray.toJSONString());
			} catch (IOException e) {
				_log.error("Error in code :::" +e);
			}
		return false;
	}
	
}

