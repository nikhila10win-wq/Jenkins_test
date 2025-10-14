package com.mhdsys.grants.and.schemes.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.schema.service.DistrictGrantSchemeLocalServiceUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Grants And Schemes",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysDownloadApplications", "javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/district-level/download-applications.jsp",
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSDOWNLOADAPPLICATIONS,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MhdsysDownloadApplicationPortlet extends MVCPortlet {

	private static Log LOGGER = LogFactoryUtil.getLog(MhdsysDownloadApplicationPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isAssociation = RoleConstant.isAssociation(user, themeDisplay.getCompanyId());
			boolean isHoAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			boolean isAssistantDirector = RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());

			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isHoAdmin", isHoAdmin);
			renderRequest.setAttribute("isAssociation", isAssociation);
			renderRequest.setAttribute("isDeputyDirector", isDeputyDirector);
			renderRequest.setAttribute("isAssistantDirector", isAssistantDirector);

			renderRequest.setAttribute("applicationList",
					DistrictGrantSchemeLocalServiceUtil.getDistrictGrantSchemes(-1, -1));
		} catch (Exception e) {
			LOGGER.error(e);
		}
		super.render(renderRequest, renderResponse);
	}

}
