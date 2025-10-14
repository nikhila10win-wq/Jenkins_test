package com.mhdsys.awards.web.resource.commands;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.SPORTS_APPLICATION }, service = MVCResourceCommand.class)
public class SaveSportsApplicationMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveSportsApplicationMVCResourceCommand.class);

	@Reference
	AwardsUtil awardsUtil;

	@Reference
	AwardsCommonApi commonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		LOGGER.info("Save Award Application Resource");

		String status = ParamUtil.getString(resourceRequest, "status");
		LOGGER.info("Status: " + status);
		String userType = ParamUtil.getString(resourceRequest, "userType");
		String roleName = ParamUtil.getString(resourceRequest, "roleName");
		LOGGER.info("user type: " + userType + "role name: " + roleName);
		String applicationIdsJson = ParamUtil.getString(resourceRequest, "applicationIds");
		JSONArray applicationIdsArray;
		try {
			applicationIdsArray = JSONFactoryUtil.createJSONArray(applicationIdsJson);
			LOGGER.info("Application IDs:");

			for (int i = 0; i < applicationIdsArray.length(); i++) {
				LOGGER.info(applicationIdsArray.getString(i));
				AwardApplication awardApplication = AwardApplicationLocalServiceUtil
						.fetchAwardApplication(Long.valueOf(applicationIdsArray.getString(i)));
				/*
				 * if
				 * (awardApplication.getStatus().equalsIgnoreCase(AwardsCommonConstants.PENDING)
				 * ) {
				 */
				AwardApplication updateDraftStatus = awardsUtil
						.updateDraftStatus(Long.valueOf(applicationIdsArray.getString(i)), status);
				LOGGER.info("UPDATED STATUS ::: " + updateDraftStatus.getStatus());
				/* } */
			}

		} catch (JSONException e) {
			LOGGER.info(e);
		}

		if (ParamUtil.getLong(resourceRequest, "competitionLevel") > 0) {

			long applicationId = ParamUtil.getLong(resourceRequest, "applicationId");
			LOGGER.info("Application ID :: " + applicationId);
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

			try {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				AwardApplicationCommonDTO awardApplicationDTO = awardsUtil.setAwardApplicationCommonDTO(resourceRequest,
						themeDisplay, applicationId);

				jsonObject.put("status", status);

//      //  AwardApplicationCommonDTO awardApplicationDTO = awardsUtil.setApplicationDTO(resourceRequest, themeDisplay,
//                  applicationId);

				LOGGER.info("Application DTO : " + awardApplicationDTO);

				AwardApplication awardApplicationModal = commonApi.saveAwardsApplications(awardApplicationDTO);
				LOGGER.info("awards application : " + awardApplicationModal);
				List<AwardApplicationCommonDTO> awardApplicationCommonDTOs = new ArrayList<>();
				List<AwardApplication> awardApplications = AwardApplicationLocalServiceUtil
						.getAwardApplicationsByuserId(themeDisplay.getUserId());
				if (Validator.isNotNull(awardApplications)) {
					for (AwardApplication awardApplication : awardApplications) {
						awardApplicationCommonDTOs.add(awardsUtil.setAwardApplicationDTO(awardApplication));
					}
					resourceRequest.setAttribute("awardApplications", awardApplicationCommonDTOs);
					resourceRequest.setAttribute("userType", userType);
					resourceRequest.setAttribute("roleName", roleName);
					
					PortletRequestDispatcher dispatcher = resourceRequest.getPortletSession().getPortletContext()
							.getRequestDispatcher("/awards/award-application-list.jsp");
					dispatcher.include(resourceRequest, resourceResponse);
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}

		}

		LOGGER.info("awards application reviewd");
		return false;

	}

	@Reference
	FileUploadUtil fileUploadUtil;

	@Reference
	AwardsCommonApi awardsCommonApi;

}
