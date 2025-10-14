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
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB, "mvc.command.name="
				+ AwardsWebPortletKeys.EDITAWARDAPPLICATIONMVCRESOURCECOMMAND }, service = MVCResourceCommand.class)
public class EditAwardApplicationMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(EditAwardApplicationMVCResourceCommand.class);

	@Reference
	AwardsUtil awardsUtil;

	@Reference
	AwardsCommonApi commonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		LOGGER.info("edit Award Application Resource");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long awardApplicationId = ParamUtil.getLong(resourceRequest, "awardApplicationId");
		LOGGER.info("awardApplicationId: " + awardApplicationId);
		JSONObject requestObject = JSONFactoryUtil.createJSONObject();

		try {
			AwardApplication awardApplication = AwardApplicationLocalServiceUtil
					.getAwardApplication(awardApplicationId);
			AwardApplicationCommonDTO awardApplicationCommonDTO = awardsUtil.setAwardsApplicationsDTO(awardApplication, themeDisplay);
			requestObject.put("awardApplication", awardApplication);
			requestObject.put("competitionStartDate", awardApplicationCommonDTO.getCompetitionStartDateStr());
			requestObject.put("competitionEndDate", awardApplicationCommonDTO.getCompetitionEndDateStr());
			requestObject.put("coachFromDate", awardApplicationCommonDTO.getCoachFromDateStr());
			requestObject.put("coachToDate", awardApplicationCommonDTO.getCoachToDateStr());
			requestObject.put("competitionCertificateURL", awardApplicationCommonDTO.getCertificateFileURL());
			requestObject.put("competitionCertificateName", awardApplicationCommonDTO.getCertificateFileEntryName());
			requestObject.put("undertakingURL", awardApplicationCommonDTO.getUndertakingFileURL());
			requestObject.put("undertakingName", awardApplicationCommonDTO.getUndertakingFileEntryName());
			resourceResponse.getWriter().write(requestObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}

		LOGGER.info("awards application edit ended");
		return false;

	}

	@Reference
	FileUploadUtil fileUploadUtil;

	@Reference
	AwardsCommonApi awardsCommonApi;

}
