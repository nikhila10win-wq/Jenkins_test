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
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Awards",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=MhdsysApproveDeskOfficersAssignmentWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",

		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",

		"javax.portlet.init-param.view-template=" + AwardsCommonConstants.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_JSP,
		"javax.portlet.name=" + AwardsWebPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class ApproveSportsDeskOfficersAssignmentPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(ApproveSportsDeskOfficersAssignmentPortlet.class);

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

			List<AwardApplication> awardApplications = AwardApplicationLocalServiceUtil.getAwardApplications(-1, -1);
			List<AwardApplicationCommonDTO> applicationsList = new ArrayList<>();

			for (AwardApplication application : awardApplications) {
				AwardApplication awardApplication = AwardApplicationLocalServiceUtil.getAwardApplication(application.getAwardApplicationId());
				if(awardApplication.getAssignToDeskOff() && !awardApplication.getDirectoryApproval()) {
					applicationsList.add(awardsUtil.setAwardApplicationDTO(awardApplication));
				}
			}

			renderRequest.setAttribute("applList", applicationsList);
			renderRequest.setAttribute("isDeskOfficer",
					roleUtil.hasRole(user, RoleConstant.DESK_OFFICER, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isAssociation",
					roleUtil.hasRole(user, RoleConstant.ASSOCIATION, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isHO",
					roleUtil.hasRole(user, RoleConstant.HO_ADMIN, themeDisplay.getCompanyId()));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}

}
