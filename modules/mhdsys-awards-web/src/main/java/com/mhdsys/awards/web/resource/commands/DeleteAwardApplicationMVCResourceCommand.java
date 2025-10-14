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
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB, "mvc.command.name="
				+ AwardsWebPortletKeys.DELETEAWARDAPPLICATIONMVCRESOURCECOMMAND }, service = MVCResourceCommand.class)
public class DeleteAwardApplicationMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(DeleteAwardApplicationMVCResourceCommand.class);

	@Reference
	AwardsCommonApi commonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		LOGGER.info("delete Award Application Resource");
		long awardApplicationId = ParamUtil.getLong(resourceRequest, "awardApplicationId");
		LOGGER.info("awardApplicationId: " + awardApplicationId);
		JSONObject requestObject = JSONFactoryUtil.createJSONObject();

		try {
			AwardApplication awardApplication = AwardApplicationLocalServiceUtil
					.getAwardApplication(awardApplicationId);
			AwardApplication awApplication = AwardApplicationLocalServiceUtil.deleteAwardApplication(awardApplication);
			if (Validator.isNotNull(awApplication)) {
				LOGGER.info("deleted");
				requestObject.put("deletedAward", true);
			}
			resourceResponse.getWriter().write(requestObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}

		LOGGER.info("awards application delete ended");
		return false;

	}

}
