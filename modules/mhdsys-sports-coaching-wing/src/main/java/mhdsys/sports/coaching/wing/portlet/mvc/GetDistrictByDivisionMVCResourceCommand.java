package mhdsys.sports.coaching.wing.portlet.mvc;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
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
	    		"mvc.command.name="+SportsCoachinWingMvcCommand.GET_DISTRICT_BY_DIVISION
	    }, 
	    service = MVCResourceCommand.class
)
public class GetDistrictByDivisionMVCResourceCommand  implements MVCResourceCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(GetDistrictByDivisionMVCResourceCommand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
			_log.info("Entry into GetDistrictByDivisionMVCResourceCommand ::  serveResource ::");
			JSONArray districtArray =JSONFactoryUtil.createJSONArray();
			List<DistrictMaster> districts =null;
			long divisionId =ParamUtil.getLong(resourceRequest,"divisionId");
			_log.info("divisionId :::"+divisionId);
			if(Validator.isNotNull(divisionId) && divisionId>0){
					 districts = DistrictMasterLocalServiceUtil.getDistrictByDivisionId(divisionId);
			}
			_log.info("districts :::"+ districts);
			if(Validator.isNotNull(districts)){
				for(DistrictMaster district :districts){
							JSONObject districtJson = JSONFactoryUtil.createJSONObject();
							districtJson.put("name", district.getDistrictName_en());
							districtJson.put("id", district.getDistrictId());
							districtArray.put(districtJson);
				}
			}
			_log.info("districtArray :::" +districtArray);
			try {
				resourceResponse.getWriter().print(districtArray.toJSONString());
			} catch (IOException e) {
				_log.error("Error in code :::" +e);
			}
		return false;
	}
	
}

