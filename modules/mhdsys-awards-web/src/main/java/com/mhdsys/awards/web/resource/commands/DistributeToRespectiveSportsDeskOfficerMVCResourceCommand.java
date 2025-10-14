package com.mhdsys.awards.web.resource.commands;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.SPORTS_PERSON_LIST_MANAGEMENTWEB, "mvc.command.name="
				+ AwardsWebPortletKeys.SEND_TO_RESPECTIVE_SPORTS_DESK_OFFICERS }, service = MVCResourceCommand.class)

public class DistributeToRespectiveSportsDeskOfficerMVCResourceCommand implements MVCResourceCommand {

	@Reference
	AwardsCommonApi awardsApi;
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		LOGGER.info("Distribute To Desk Officer Resource Commands ::: ");
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String selectedIdsJson = ParamUtil.getString(resourceRequest, "selectedIds");
			
			JSONArray selectedIdsArray = JSONFactoryUtil.createJSONArray(selectedIdsJson);
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			
			for (int i = 0; i < selectedIdsArray.length(); i++) {
				long awardApplicationId = selectedIdsArray.getLong(i);
				LOGGER.info("awardApplicationId:" + awardApplicationId);
				AwardApplication awardApplication = AwardApplicationLocalServiceUtil.getAwardApplication(awardApplicationId);
				
				if (Validator.isNotNull(awardApplication)) {
					LOGGER.info("UPDATED ::::::     ");
					awardApplication.setAssignToDeskOff(true);
					awardApplication.setDeskOffSport("Desk Officer-"
							+ SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId()).getName_en());
					
				    Role role = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), "Desk Officer "
							+ SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId()).getName_en());
				    
				    List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());
				    
				    if (!users.isEmpty()) {
				        User sportsDeskOfficer = users.get(0);
				        
				        awardApplication.setSportsDeskOffUserId(sportsDeskOfficer.getUserId());
				        awardApplication.setSportsDeskOffName(sportsDeskOfficer.getFirstName());
				    }
					
					LOGGER.info("VALUES VALUES :::::::    "+awardApplication.getAssignToDeskOff()+"     ////////     "+awardApplication.getDeskOffSport());
					AwardApplicationLocalServiceUtil.updateAwardApplication(awardApplication);
				}
			}

			jsonObject.put("applied", true);
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Distributed saved");
		return false;
	}

}
