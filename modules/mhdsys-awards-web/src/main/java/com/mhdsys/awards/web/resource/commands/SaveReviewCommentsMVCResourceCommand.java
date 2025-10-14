package com.mhdsys.awards.web.resource.commands;

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
import com.mhdsys.schema.model.AwardApplication;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,
		
		"javax.portlet.name=" + AwardsWebPortletKeys.SPORTS_PERSON_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.UPLOAD_APPOINTMENT_LETTER_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB,
		
		"mvc.command.name=" + AwardsWebPortletKeys.APPLICATION_VERIFICATION }, service = MVCResourceCommand.class)

public class SaveReviewCommentsMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveSportsApplicationMVCResourceCommand.class);

	@Reference
	AwardsUtil awardsUtil;

	@Reference
	AwardsCommonApi commonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		LOGGER.info("Save Application Review Resource");

		long applicationId = ParamUtil.getLong(resourceRequest, "applicationId");
		LOGGER.info("Application ID :: " + applicationId);
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			AwardApplicationCommonDTO awardApplicationDTO = awardsUtil.setApplicationDTO(resourceRequest, themeDisplay,
					applicationId);

			LOGGER.info("Application DTO : " + awardApplicationDTO);

			AwardApplication awardApplicationModal = commonApi.saveAwardsApplications(awardApplicationDTO);
			LOGGER.info("awards application : " + awardApplicationModal);

			if (Validator.isNotNull(awardApplicationModal)) {
				jsonObject.put("reviewApplication", true);
			} else {
				jsonObject.put("reviewApplication", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.info("application reviewd");
		return false;

	}

}
