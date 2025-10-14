package com.mhdsys.admin.dashboard.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.mhdsys.admin.dashboard.constants.AdminDashboardPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Epiphany
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=MHDSYS.DASHBOARD",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PTTeacherDashboard",
		"javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/dashboard",
		"javax.portlet.name=" + AdminDashboardPortletKeys.PT_TEACHER_DASHBOARD_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PTTeacherApplicationDashboardPortlet extends MVCPortlet {
}