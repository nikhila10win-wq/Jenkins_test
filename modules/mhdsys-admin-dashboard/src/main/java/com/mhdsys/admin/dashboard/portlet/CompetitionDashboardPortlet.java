package com.mhdsys.admin.dashboard.portlet;

import com.mhdsys.admin.dashboard.constants.AdminDashboardPortletKeys;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;

import java.io.IOException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Epiphany
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=MHDSYS.DASHBOARD",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CompetitionDashboard",
		"javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/dashboard/competition-dashboard.jsp",
		"javax.portlet.name=" + AdminDashboardPortletKeys.COMPETITION_DASHBOARD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CompetitionDashboardPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	RoleUtil roleUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user;
		try {
			user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isPtTeacher = roleUtil.hasRole(user, RoleConstant.PT_TEACHER, themeDisplay.getCompanyId());
			boolean isPrincipal = roleUtil.hasRole(user, RoleConstant.PRINCIPAL, themeDisplay.getCompanyId());
			boolean isHOAdmin = roleUtil.hasRole(user, RoleConstant.HO_ADMIN, themeDisplay.getCompanyId());
			boolean isDSORole = roleUtil.hasDSORole(user);
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			
			renderRequest.setAttribute("isPtTeacher", isPtTeacher);
			renderRequest.setAttribute("isPrincipal", isPrincipal);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			renderRequest.setAttribute("isDSORole", isDSORole);
			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			
		} catch (PortalException e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}
}