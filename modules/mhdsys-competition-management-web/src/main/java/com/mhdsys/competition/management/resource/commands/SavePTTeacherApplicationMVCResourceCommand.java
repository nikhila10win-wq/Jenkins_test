package com.mhdsys.competition.management.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.competition.CompetitionCommonApi;
import com.mhdsys.common.pojo.CompetitionInitiationCommonDTO;
import com.mhdsys.common.pojo.PTTeacherApplicationCommonDTO;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.PTTeacherApplication;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_INITIATION_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PT_TEACHER_REQUEST_LIST_MANAGEMENTWEB,

		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.SAVE_COMPETITION_PT_TEACHER_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class SavePTTeacherApplicationMVCResourceCommand implements MVCResourceCommand{
	@Reference
	CompetitionUtil competitionMasterUtil;
	@Reference
	CompetitionCommonApi competitionApi;

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("save comp initiation started");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			long competitionInitiationId = ParamUtil.getLong(resourceRequest, "competitionInitiationId");
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			PTTeacherApplicationCommonDTO ptTeacherApplicationCommonDTO = competitionMasterUtil.setPtTeacherApplication(resourceRequest,
					themeDisplay,competitionInitiationId);
			PTTeacherApplication pTTeacherApplication = competitionApi.savePTTeacherApplication(ptTeacherApplicationCommonDTO);
			LOGGER.info("pTTeacherApplication: " + pTTeacherApplication);
			if (Validator.isNotNull(pTTeacherApplication)) {
				jsonObject.put("pTTeacherApplication", true);	
				jsonObject.put("applicationNumber", pTTeacherApplication.getApplicationNumber());	
			} else {
				jsonObject.put("pTTeacherApplication", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("save comp pTTeacherApplication ended");
		return false;
	}

}
