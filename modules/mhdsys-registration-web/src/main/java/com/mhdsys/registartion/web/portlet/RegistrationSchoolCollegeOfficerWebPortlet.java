package com.mhdsys.registartion.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.mhdsys.registartion.web.constants.RegistrationCommonConstants;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.schema.service.OfficerDesignationLocalServiceUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author DELL
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=MHDSYS.Registration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysRegistrationSchoolCollegeOfficerWeb",
		"javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.requires-namespaced-parameters=false",
//		"com.liferay.portlet.header-portal-css=/o/mhdsys-dashboard-theme/css/plugins/bootstrap.min.css",
//		
//		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
//		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
//		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
//		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-guest-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-guest-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-guest-theme/js/plugins/additional-methods.js",
		"javax.portlet.init-param.view-template="+RegistrationCommonConstants.SCHOOL_COLLEGE_OFFICER_APPLICATION_JSP,
		"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONSCHOOLCOLLEGEOFFICERWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RegistrationSchoolCollegeOfficerWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
			try {
				renderRequest.setAttribute("designations", OfficerDesignationLocalServiceUtil.getOfficerDesignations(-1, -1));

			}catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		super.render(renderRequest, renderResponse);
	}
}