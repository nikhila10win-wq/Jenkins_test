package com.mhdsys.competition.management.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.CompetitionSchedule;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionScheduleLocalServiceUtil;
import com.mhdsys.schema.service.PTTeacherApplicationLocalServiceUtil;

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
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_SCHEDULED_LIST_MANAGEMENTWEB,
		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.ADD_COMPETITION_SCHEDULE_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)
public class AddCompetitionScheduleMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	CompetitionUtil competitionMasterUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long competitionScheduledId = ParamUtil.getLong(renderRequest, "competitionScheduledId");
			long competitionInitiationId = ParamUtil.getLong(renderRequest, "competitionInitiationId");
			long ptTeacherApplicationId = ParamUtil.getLong(renderRequest, "ptTeacherApplicationId");
			String mode = ParamUtil.getString(renderRequest, "cmd");
			LOGGER.info("mode: " + mode);
			LOGGER.info("competitionInitiationId: " + competitionInitiationId);
			CompetitionInitiation competitionInitiation = CompetitionInitiationLocalServiceUtil
					.getCompetitionInitiation(competitionInitiationId);
			renderRequest.setAttribute("competitionInitiation",
					competitionMasterUtil.setCompetitionInitiationDTO(competitionInitiation));
			PTTeacherApplication ptTeacherApplication = PTTeacherApplicationLocalServiceUtil
					.getPTTeacherApplication(ptTeacherApplicationId);
			renderRequest.setAttribute("ptTeacherApplication",
					competitionMasterUtil.setPtTeacherApplicationDTO(ptTeacherApplication, themeDisplay));
			if (competitionScheduledId > 0) {
				renderRequest.setAttribute("competitionSchedule", competitionMasterUtil.setCompetitionScheduledDTO(
						CompetitionScheduleLocalServiceUtil.getCompetitionSchedule(competitionScheduledId),competitionInitiation));
			}
			int competitionScheduleSize = 0;
			if (Validator.isNotNull(CompetitionScheduleLocalServiceUtil.getCompetitionSchedules(-1, -1))) {
				competitionScheduleSize = CompetitionScheduleLocalServiceUtil.getCompetitionSchedules(-1, -1).size();
			}
			LOGGER.info("size: " + competitionScheduleSize);
			renderRequest.setAttribute("competitionScheduleSize", competitionScheduleSize);
			renderRequest.setAttribute("mode", ParamUtil.getString(renderRequest, "cmd"));
			renderRequest.setAttribute("isPtTeacher", ParamUtil.getString(renderRequest, "isPtTeacher"));
			renderRequest.setAttribute("isPrincipal", ParamUtil.getString(renderRequest, "isPrincipal"));
			renderRequest.setAttribute("isDSORole", ParamUtil.getString(renderRequest, "isDSORole"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return CompetitionCommonConstant.ADD_COMPETITION_SCHEDULE_JSP;
	}

}
