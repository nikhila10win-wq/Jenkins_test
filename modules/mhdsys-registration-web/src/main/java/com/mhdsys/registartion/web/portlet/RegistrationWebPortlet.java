package com.mhdsys.registartion.web.portlet;

import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author DELL
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=MHDSYS.Registration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=MhdsysRegistrationWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",
		//"com.liferay.portlet.header-portal-css=/o/mhdsys-guest-theme/css/plugins/bootstrap.min.css",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-guest-theme/js/plugins/jquery.min.js",
		//"com.liferay.portlet.header-portal-javascript=/o/mhdsys-guest-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-guest-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-guest-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RegistrationWebPortlet extends MVCPortlet {
}