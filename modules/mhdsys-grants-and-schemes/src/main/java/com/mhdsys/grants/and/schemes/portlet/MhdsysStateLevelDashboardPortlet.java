package com.mhdsys.grants.and.schemes.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Grants And Schemes",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysStateLevelDashboard", "javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/state-level/state-level-dashboard.jsp",
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELDASHBOARD,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MhdsysStateLevelDashboardPortlet extends MVCPortlet {

	private static Log LOGGER = LogFactoryUtil.getLog(MhdsysStateLevelDashboardPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		LOGGER.info("MhdsysStateLevelDashboardPortlet :::  ");
		renderRequest.setAttribute("mode", "add");
		super.render(renderRequest, renderResponse);
	}

}
