package com.mhdsys.competition.management.resource.commands;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.competition.CompetitionCommonApi;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.PTTeacherApplicationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name="
				+ CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PT_TEACHER_REQUEST_LIST_MANAGEMENTWEB,
		"javax.portlet.name="
				+ CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PRINCIPAL_APPROVED_LIST_MANAGEMENTWEB,
		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.SAVE_PRINCIPAL_APPROVE_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class SavePrincipalApproveMVCResourceCommand implements MVCResourceCommand {
	@Reference
	CompetitionUtil competitionMasterUtil;
	@Reference
	CompetitionCommonApi competitionApi;
	@Reference
	RoleUtil roleUtil;
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("save comp initiation started");
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String selectedIdsJson = ParamUtil.getString(resourceRequest, "selectedIds");
			String sendToDSO = ParamUtil.getString(resourceRequest, "sendToDSO");
			boolean dsoApproval = ParamUtil.getBoolean(resourceRequest, "dsoApproval");
			JSONArray selectedIdsArray = JSONFactoryUtil.createJSONArray(selectedIdsJson);
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isPrincipal = roleUtil.hasRole(user, RoleConstant.PRINCIPAL, themeDisplay.getCompanyId());
			boolean isDSORole = roleUtil.hasDSORole(user);
			for (int i = 0; i < selectedIdsArray.length(); i++) {
				long ptTeacherApplicationId = selectedIdsArray.getLong(i);

				PTTeacherApplication ptTeacherApplication = PTTeacherApplicationLocalServiceUtil
						.getPTTeacherApplication(ptTeacherApplicationId);
				if (ptTeacherApplication != null) {
					if (isPrincipal && ptTeacherApplication.getStatus() == CompetitionCommonConstant.PROCESS1_PENDING) {
						ptTeacherApplication.setStatus(CompetitionCommonConstant.PROCESS1_APPROVED_BY_PRINCIPAL);
					}
					if (isPrincipal && ptTeacherApplication.getStatus() == CompetitionCommonConstant.PROCESS2_PENDING) {
						ptTeacherApplication.setStatus(CompetitionCommonConstant.PROCESS2_APPROVED_BY_PRINCIPAL);
					}
					if (isPrincipal && sendToDSO.equalsIgnoreCase("forwardToDSO") && ptTeacherApplication.getStatus() == CompetitionCommonConstant.PROCESS1_APPROVED_BY_PRINCIPAL){
						ptTeacherApplication.setStatus(CompetitionCommonConstant.PROCESS1_FORWARDED_TO_DSO);
					}
					if (isDSORole && dsoApproval) {
						ptTeacherApplication.setStatus(CompetitionCommonConstant.PROCESS1_APPROVED_BY_DSO);
					}
					competitionApi.updatePtTeacherApplicationStatus(ptTeacherApplication);
					LOGGER.info("Updated PTTeacherApplication with ID: "
							+ ptTeacherApplication.getPtTeacherApplicationId());
				}
			}

			// After the update, return a success response
			jsonObject.put("principalApproval", true);

			// Write the response back to the client
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("save comp pTTeacherApplication ended");
		return false;
	}
}
