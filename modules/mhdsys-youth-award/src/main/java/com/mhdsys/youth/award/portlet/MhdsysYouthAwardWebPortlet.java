package com.mhdsys.youth.award.portlet;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;
import com.mhdsys.youth.award.constants.YouthAwardConstants;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"com.liferay.portlet.display-category=MHDSYS.YOUTH",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=MhdsysYouthAwardWeb",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=" + YouthAwardConstants.YOUTH_AWARD,
			"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSYOUTHAWARDWEB,
			"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)

public class MhdsysYouthAwardWebPortlet extends MVCPortlet{
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			renderRequest.setAttribute("isDeskOfficer", RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isAssistantDirector", RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isHOAdmin", RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));
			//renderRequest.setAttribute("isDeputyDirector", RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId()));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		super.render(renderRequest, renderResponse);
	}

}
