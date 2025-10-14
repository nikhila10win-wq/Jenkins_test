package com.mhdsys.competition.management.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.competition.CompetitionCommonApi;
import com.mhdsys.common.pojo.CompetitionMasterCommonDTO;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionMaster;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_MANAGEMENTWEB,
		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.SAVE_COMPETITION_MASTER_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class SaveCompetitionMasterMVCResourceCommand implements MVCResourceCommand {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	CompetitionUtil competitionMasterUtil;
	@Reference
	CompetitionCommonApi competitionApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		LOGGER.info("save comp master started");
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			CompetitionMasterCommonDTO setCompetitionMaster = competitionMasterUtil.setCompetitionMaster(resourceRequest,
					themeDisplay);
			CompetitionMaster competitionMaster = competitionApi.saveCompetitionMaster(setCompetitionMaster);
			LOGGER.info("competitionMaster: " +competitionMaster);
			if (Validator.isNotNull(competitionMaster)) {
				jsonObject.put("createCompetition", true);
			} else {
				jsonObject.put("createCompetition", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("save comp master ended");
		return false;
	}

}
