package com.mhdsys.competition.management.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;
import com.mhdsys.schema.service.PTTeacherApplicationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_INITIATION_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PT_TEACHER_REQUEST_LIST_MANAGEMENTWEB,
		"javax.portlet.name="
				+ CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PRINCIPAL_APPROVED_LIST_MANAGEMENTWEB,
		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.PT_TEACHER_APPLICATION_FORM_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)
public class PTTeacherApplicationMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	CompetitionUtil competitionMasterUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long competitionInitiationId = ParamUtil.getLong(renderRequest, "competitionInitiationId");
			long ptTeacherApplicationId = ParamUtil.getLong(renderRequest, "ptTeacherApplicationId");
			String mode = ParamUtil.getString(renderRequest, "cmd");
			LOGGER.info("mode: " + mode);
			LOGGER.info("competitionInitiationId: " + competitionInitiationId);
			CompetitionInitiation competitionInitiation = CompetitionInitiationLocalServiceUtil
					.getCompetitionInitiation(competitionInitiationId);
			renderRequest.setAttribute("competitionInitiation",
					competitionMasterUtil.setCompetitionInitiationDTO(competitionInitiation));
			if (mode.equalsIgnoreCase("view") || mode.equalsIgnoreCase("editParticipant")) {
				LOGGER.info("ptTeacherApplicationId: " + ptTeacherApplicationId);
				PTTeacherApplication ptTeacherApplication = PTTeacherApplicationLocalServiceUtil
						.getPTTeacherApplication(ptTeacherApplicationId);
				renderRequest.setAttribute("ptTeacherApplication",
						competitionMasterUtil.setPtTeacherApplicationDTO(ptTeacherApplication, themeDisplay));
			}
			renderRequest.setAttribute("mode", ParamUtil.getString(renderRequest, "cmd"));
			renderRequest.setAttribute("isPtTeacher", ParamUtil.getString(renderRequest, "isPtTeacher"));
			renderRequest.setAttribute("isPrincipal", ParamUtil.getString(renderRequest, "isPrincipal"));
			renderRequest.setAttribute("isDSORole", ParamUtil.getString(renderRequest, "isDSORole"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return CompetitionCommonConstant.PT_TEACHER_FORM_JSP;
	}

}
