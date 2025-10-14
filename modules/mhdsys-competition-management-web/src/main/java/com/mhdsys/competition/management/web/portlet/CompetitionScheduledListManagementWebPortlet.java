package com.mhdsys.competition.management.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.CompetitionScheduleCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.CompetitionSchedule;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionScheduleLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author DELL
 */
@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Competition",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysCompetitionScheduledListManagementWeb",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-css=/o/mhdsys-dashboard-theme/css/plugins/bootstrap.min.css",

		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.init-param.view-template=" + CompetitionCommonConstant.COMPETITION_SCHEDULED_LIST_JSP,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_SCHEDULED_LIST_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CompetitionScheduledListManagementWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	CompetitionUtil competitionMasterUtil;
	@Reference
	com.mhdsys.common.util.RoleUtil roleUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			List<CompetitionSchedule> competitionSchedules = CompetitionScheduleLocalServiceUtil
					.getCompetitionSchedules(-1, -1);
			List<CompetitionScheduleCommonDTO> compeList = new ArrayList<>();
			for (CompetitionSchedule competitionSchedule : competitionSchedules) {
				if (competitionSchedule.getCompetitionInitiationId() > 0) {
					CompetitionInitiation competitionInitiation = CompetitionInitiationLocalServiceUtil
							.getCompetitionInitiation(competitionSchedule.getCompetitionInitiationId());
					compeList.add(competitionMasterUtil.setCompetitionScheduledDTO(competitionSchedule,
							competitionInitiation));
				}
			}
			renderRequest.setAttribute("competitionSchedules", compeList);
			renderRequest.setAttribute("isPtTeacher", roleUtil.hasRole(user, RoleConstant.PT_TEACHER, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isPrincipal", roleUtil.hasRole(user, RoleConstant.PRINCIPAL, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isDSORole", roleUtil.hasDSORole(user));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}
}