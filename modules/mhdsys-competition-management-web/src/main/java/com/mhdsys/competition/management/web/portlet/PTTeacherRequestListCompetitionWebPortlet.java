package com.mhdsys.competition.management.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.PTTeacherApplicationCommonDTO;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.PTTeacherApplicationLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
		"javax.portlet.display-name=MhdsysPtTeacherRequestListManagementWeb",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-css=/o/mhdsys-dashboard-theme/css/plugins/bootstrap.min.css",

		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.init-param.view-template=" + CompetitionCommonConstant.PT_TEACHER_REQUEST_LIST_JSP,
		"javax.portlet.name="
				+ CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_PT_TEACHER_REQUEST_LIST_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class PTTeacherRequestListCompetitionWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	CompetitionUtil competitionMasterUtil;
	@Reference
	RoleUtil roleUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isPtTeacher = roleUtil.hasRole(user, RoleConstant.PT_TEACHER, themeDisplay.getCompanyId());
			boolean isPrincipal = roleUtil.hasRole(user, RoleConstant.PRINCIPAL, themeDisplay.getCompanyId());
			boolean isDSORole = roleUtil.hasDSORole(user);
			List<PTTeacherApplication> ptTeacherApplications = PTTeacherApplicationLocalServiceUtil.getPTTeacherApplications(-1, -1);
			List<Long> statuses;
			if (isPtTeacher) {
				ptTeacherApplications =PTTeacherApplicationLocalServiceUtil.getPTTeacherApplications(-1, -1);
			} 
			if (isPrincipal) {
				statuses = Arrays.asList(CompetitionCommonConstant.PROCESS1_PENDING,CompetitionCommonConstant.PROCESS2_PENDING);

				ptTeacherApplications = PTTeacherApplicationLocalServiceUtil
						.getPtTeacherFormByStatus(statuses);
			}
//				else if (isDSORole) {
//				ptTeacherApplications = PTTeacherApplicationLocalServiceUtil
//						.getPtteacherListByStatus(CompetitionCommonConstant.PROCESS1_FORWARDED_TO_DSO);
//			}
			List<PTTeacherApplicationCommonDTO> ptTeacherApplicationCommonDTOs = new ArrayList<>();
			for (PTTeacherApplication ptTeacherApplication : ptTeacherApplications) {
				LOGGER.info("desc id: " + ptTeacherApplication.getPtTeacherApplicationId());
				ptTeacherApplicationCommonDTOs
						.add(competitionMasterUtil.setPtTeacherApplicationDTO(ptTeacherApplication, themeDisplay));
			}
			renderRequest.setAttribute("ptTeacherApplications", ptTeacherApplicationCommonDTOs);
			renderRequest.setAttribute("isPtTeacher", isPtTeacher);
			renderRequest.setAttribute("isPrincipal", isPrincipal);
			renderRequest.setAttribute("isDSORole", isDSORole);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}
}
