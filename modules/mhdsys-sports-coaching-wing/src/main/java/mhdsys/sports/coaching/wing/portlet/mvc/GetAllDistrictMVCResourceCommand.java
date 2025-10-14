package mhdsys.sports.coaching.wing.portlet.mvc;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;

@Component(
		immediate=true,
	    property = { 
	    		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
	    		"mvc.command.name="+SportsCoachinWingMvcCommand.GET_ALL_DISTRICT_URL
	    }, 
	    service = MVCResourceCommand.class
)
public class GetAllDistrictMVCResourceCommand  implements MVCResourceCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(GetAllDistrictMVCResourceCommand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
			_log.info("Entry into GetAllDistrictMVCResourceCommand ::  serveResource ::");
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			JSONArray districtsArray =JSONFactoryUtil.createJSONArray();
			
			//Coach Deatails
				List<DistrictMaster> districts = getDistricts();
 			if(Validator.isNotNull(districts) && districts.size()>0){
 				for(DistrictMaster district:districts) {
 					JSONObject sportsPersonJson = JSONFactoryUtil.createJSONObject();
 					sportsPersonJson.put("id", district.getDistrictId());
 					sportsPersonJson.put("name", district.getDistrictName_en());
 					districtsArray.put(sportsPersonJson);
 				}
 			}
			try {
				resourceResponse.getWriter().print(districtsArray.toJSONString());
			} catch (IOException e) {
				_log.error("Error in code :::" +e);
			}
		return false;
	}
	
	private List<DistrictMaster> getDistricts() {
		List<DistrictMaster> districts = null;
		 try {
			districts=DistrictMasterLocalServiceUtil.getByActiveState(Boolean.TRUE);
		} catch (Exception e) {
			_log.error("Error in code ::"+e);
		}
		 return districts;
	}
	
}

