package com.mhdsys.admin.dashboard.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.admin.dashboard.constants.AdminDashboardPortletKeys;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Epiphany
 */
@Component(property = { "com.liferay.portlet.display-category=MHDSYS.DASHBOARD",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AdminDashboard", "javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/dashboard/admin-dashboard-page.jsp",
		"javax.portlet.name=" + AdminDashboardPortletKeys.ADMINDASHBOARD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class AdminDashboardPortlet extends MVCPortlet {
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

			boolean isSchoolCollege = roleUtil.hasRole(user, RoleConstant.SCHOOLCOLLEGE, themeDisplay.getCompanyId());
			renderRequest.setAttribute("isSchoolCollege", isSchoolCollege);
			renderRequest.setAttribute("isDeskOfficer", RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isHOAdmin", RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isAssociation", RoleConstant.isAssociation(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isDDD", RoleConstant.isDDD(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isSportsPerson",  RoleConstant.isSportsPerson(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isCoach",  RoleConstant.isSportsCoach(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isDeputyDirector",  RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isSportsDeskOfficer",  RoleConstant.isSportsDeskOfficer(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isPtTeacher",  RoleConstant.isPtTeacher(user, themeDisplay.getCompanyId()));
			
	        boolean isDSO = false;
	        boolean isTSO = false;
	        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
	        for (Role role : userRoles) {
	        	 if (role.getName().endsWith("-DSO")) {
		            	isDSO = true;
		            }
		            if (role.getName().endsWith("-TSO") || role.getName().startsWith("TSO-")) {
		            	isTSO = true;
		            }
	        }
	        renderRequest.setAttribute("isDSO", isDSO);
	        renderRequest.setAttribute("isTSO", isTSO);
	        LOGGER.info("isDSO: "+isDSO +", isTSO: "+isTSO);
			
		} catch (PortalException e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}
	
}