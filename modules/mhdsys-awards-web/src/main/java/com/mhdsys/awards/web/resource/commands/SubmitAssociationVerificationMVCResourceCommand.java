package com.mhdsys.awards.web.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,

		"javax.portlet.name=" + AwardsWebPortletKeys.ASSOCIATION_VERIFICATION_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.SPORTS_PERSON_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.UPLOAD_APPOINTMENT_LETTER_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB,

		"mvc.command.name="
				+ AwardsWebPortletKeys.SAVE_ASSOCIATION_VERIFICATION_BY_DESK_OFFICER }, service = MVCResourceCommand.class)

public class SubmitAssociationVerificationMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveSportsApplicationMVCResourceCommand.class);

	@Reference
	AwardsUtil awardsUtil;

	@Reference
	AwardsCommonApi commonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		LOGGER.info("Submit Association Verification Review Resource");

		long applicationId = ParamUtil.getLong(resourceRequest, "applicationId");
		long associationUserId = ParamUtil.getLong(resourceRequest, "associationUserId");
		String verification = ParamUtil.getString(resourceRequest, "verification");
		String remarks = ParamUtil.getString(resourceRequest, "remarks");
		LOGGER.info("Application ID :: " + applicationId + " Verification : " + verification + " Remarks : " + remarks
				+ " Association UserId " + associationUserId);
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			List<AwardApplication> applications = AwardApplicationLocalServiceUtil
					.getByAssociationUserId(associationUserId);

			boolean verified = false;

			for (AwardApplication awardApplication : applications) {

				verified = true;

				awardApplication.setMainDeskOffRemarks(remarks);
				awardApplication.setMainDeskOffVerification(verification);
				
				AwardApplicationLocalServiceUtil.updateAwardApplication(awardApplication);
			}

			jsonObject.put("verified", verified);
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.info("association verification");
		return false;

	}

}
