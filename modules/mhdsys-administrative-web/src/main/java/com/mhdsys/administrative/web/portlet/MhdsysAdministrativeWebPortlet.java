package com.mhdsys.administrative.web.portlet;

import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.service.StudentRegistrationLocalServiceUtil;

import java.io.IOException;
import java.util.List;

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

/**
 * @author DELL
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=MHDSYS.Adiministrative",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysAdministrativeWeb",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/scount-guide-dashboard.jsp",
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSADMINISTRATIVEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MhdsysAdministrativeWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(MhdsysAdministrativeWebPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			renderRequest.setAttribute("scoutMaster", RoleConstant.isScoutMaster(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("guideMaster", RoleConstant.isGuideMaster(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("commandingOfficer", RoleConstant.isCommandingOfficer(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isHoAdmin", RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));
		}catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		super.render(renderRequest, renderResponse);
	}
}