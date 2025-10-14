package mhdsys.kreedapith.sports.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.sportsFacilityMaster.SportsFacilityMasterCommonApi;
import com.mhdsys.common.pojo.ConstructionTrackerCommonDTO;
import com.mhdsys.common.pojo.SportsFacilityMasterDTO;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.model.SportsFacilityMaster;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.facility.web.util.SportsFacilityCommonUtil;
import mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys;

@Component
(immediate = true, property = { "javax.portlet.name=" + MhdysKreedapithSportsFacilityPortletKeys.MHDYSKREEDAPITHSPORTSFACILITY,
		"mvc.command.name="+ MhdysKreedapithSportsFacilityPortletKeys.SAVE_SPORTSFACILITY_RESOURCECOMMAND}, service = MVCResourceCommand.class)
				
public class SaveSportsFacilityResourceCommand implements MVCResourceCommand{
	private Log _log = LogFactoryUtil.getLog(SaveSportsFacilityResourceCommand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		_log.info("SaveSportsFacilityResourceCommand Starts------------------");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String mode= ParamUtil.getString(resourceRequest, "mode");
		try {
			
			if(!("view".equalsIgnoreCase(mode))) {
				
				SportsFacilityMasterDTO SportsFacilityMasterDTO = _SportsFacilityCommonUtil.setSportsFacilityMasterDTO(resourceRequest,themeDisplay);
				_log.info("SportsFacilityMasterDTO::::::::::  " + SportsFacilityMasterDTO);
				_log.info("*********************************************************************************************");
				if(Validator.isNotNull(SportsFacilityMasterDTO)) {
					SportsFacilityMaster sportsFacilityMasterModal = _SportsFacilityMasterCommonApi.saveSportsFacilityMaster(SportsFacilityMasterDTO);
					_log.info("Saved sportsFacilityMasterModal::::::::::  " + sportsFacilityMasterModal);
					resourceResponse.getWriter().write("success");
				}else {
					resourceResponse.getWriter().write("failed");
				}
				
		}else {
			resourceResponse.getWriter().write("failed");
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		_log.info("SaveSportsFacilityResourceCommand Ends------------------");
		
		return false;
	}

	@Reference private SportsFacilityMasterCommonApi _SportsFacilityMasterCommonApi;
	@Reference private SportsFacilityCommonUtil _SportsFacilityCommonUtil;
}
