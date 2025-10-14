package com.mhdsys.competition.management.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.ResultUploadCommonDTO;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionResultUpload;
import com.mhdsys.schema.service.CompetitionResultUploadLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionScheduleLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name="
				+ CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PT_TEACHER_REQUEST_LIST_MANAGEMENTWEB,
		"javax.portlet.name="
				+ CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PRINCIPAL_APPROVED_LIST_MANAGEMENTWEB,
				"javax.portlet.name="
						+ CompetitionManagementWebPortletKeys.MHDSYS_UPLOADED_RESULT_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_SCHEDULED_LIST_MANAGEMENTWEB,
		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.RESULT_UPLOAD_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)
public class ResultUploadMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	CompetitionUtil competitionMasterUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long competitionResultUploadId = ParamUtil.getLong(renderRequest, "competitionResultUploadId");
			long competitionScheduledId = ParamUtil.getLong(renderRequest, "competitionScheduledId");
			renderRequest.setAttribute("competitionScheduledId", competitionScheduledId);
			renderRequest.setAttribute("competitionInitiationId",
					ParamUtil.getLong(renderRequest, "competitionInitiationId"));
			renderRequest.setAttribute("ptTeacherApplicationId",
					ParamUtil.getLong(renderRequest, "ptTeacherApplicationId"));
			String mode = ParamUtil.getString(renderRequest, "cmd");
			LOGGER.info("mode: " + mode);
			if (competitionResultUploadId > 0) {
				CompetitionResultUpload competitionResultUpload = CompetitionResultUploadLocalServiceUtil
						.getCompetitionResultUpload(competitionResultUploadId);
				if (competitionResultUpload.getCompetitionScheduledId() > 0) {
					ResultUploadCommonDTO resultUploadCommonDTO = competitionMasterUtil
							.setCompetitionResultUploadDTO(competitionResultUpload, CompetitionScheduleLocalServiceUtil
									.getCompetitionSchedule(competitionResultUpload.getCompetitionScheduledId()),themeDisplay);
					renderRequest.setAttribute("resultUpload", resultUploadCommonDTO);

				}
			}
			renderRequest.setAttribute("mode", mode);
			renderRequest.setAttribute("competitionSchedule",
					CompetitionScheduleLocalServiceUtil.getCompetitionSchedule(competitionScheduledId));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return CompetitionCommonConstant.RESULT_UPLOAD_JSP;
	}
}
