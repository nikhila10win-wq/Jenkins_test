package com.mhdsys.awards.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.service.AwardNameMasterLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Awards",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=MhdsysSuggestionObjectionWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",

		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",

		"javax.portlet.init-param.view-template=" + AwardsCommonConstants.SUGGESTION_OBJECTION_JSP,
		"javax.portlet.name=" + AwardsWebPortletKeys.SUGGESTION_OBJECTION_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class SuggestionObjectionWebPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(AwardsApplicationListWebPortlet.class);

	@Reference
	RoleUtil roleUtil;

	@Reference
	AwardsUtil awardsUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isPtTeacher = RoleConstant.isPtTeacher(user, themeDisplay.getCompanyId());

			renderRequest.setAttribute("objector", user.getFirstName());
			renderRequest.setAttribute("email", user.getEmailAddress());
			renderRequest.setAttribute("mobileNo", "7741936646");

			renderRequest.setAttribute("sportsNames", SportsMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("awardsNames", AwardNameMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("districts", DistrictMasterLocalServiceUtil.getByActiveState(true));

			renderRequest.setAttribute("isDeskOfficer",
					roleUtil.hasRole(user, RoleConstant.DESK_OFFICER, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isAssociation",
					roleUtil.hasRole(user, RoleConstant.ASSOCIATION, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isHO",
					roleUtil.hasRole(user, RoleConstant.HO_ADMIN, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isDeputyDirector",
					RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isPtTeacher", isPtTeacher);
			renderRequest.setAttribute("cmd", "add");

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}

}
