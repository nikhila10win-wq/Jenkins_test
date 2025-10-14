package com.mhdsys.awards.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

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
		"javax.portlet.display-name=MhdsysVerifyAwardsApplicationSportsDeskOfficer",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.init-param.view-template="
				+ AwardsCommonConstants.VERIFY_AWARDS_APPLICATION_BY_SPORTS_DESK_OFFICERS,
		"javax.portlet.name=" + AwardsWebPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class VerifyAwardsApplicationPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(VerifyAwardsApplicationPortlet.class);

	@Reference
	RoleUtil roleUtil;

	@Reference
	AwardsUtil awardsUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			List<AwardApplicationCommonDTO> applicationsList = new ArrayList<>();
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isSportsDeskOfficer = RoleConstant.isSportsDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isAssistantDirector = RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			boolean isAssociation = RoleConstant.isAssociation(user, themeDisplay.getCompanyId());
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());

			List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			List<AwardApplication> awardApplications = AwardApplicationLocalServiceUtil.getAwardApplications(-1, -1);
			List<AwardApplication> associationList = AwardApplicationLocalServiceUtil
					.getByAssociationUserId(user.getUserId());

			for (AwardApplication application : awardApplications) {

				if (application.getAssociationUserId() == user.getUserId()
						|| Validator.isNull(application.getAssociationUserId()) || isDeskOfficer || isAssistantDirector
						|| isDeputyDirector | isSportsDeskOfficer) {

					AwardApplication awardApplication = AwardApplicationLocalServiceUtil
							.getAwardApplication(application.getAwardApplicationId());

					String sportName = SportsMasterLocalServiceUtil.getSportsMaster(application.getSportId())
							.getName_en();

					String expectedRoleName = RoleConstant.DESK_OFFICER_PREFIX + sportName;

					boolean hasDeskOfficerRoleForSport = userRoles.stream()
							.anyMatch(role -> role.getName().equalsIgnoreCase(expectedRoleName));

					if (awardApplication.getDirectoryApproval() && hasDeskOfficerRoleForSport) {
						applicationsList.add(awardsUtil.setAwardApplicationDTO(awardApplication));
					} else if (isDeputyDirector) {
						applicationsList.add(awardsUtil.setAwardApplicationDTO(awardApplication));
					} else if (isAssociation) {
						if (application.getDirectorRemarks().equalsIgnoreCase("Approve")) {
							renderRequest.setAttribute("isUploaded", application.getAppointmentLetter() > 0
									&& application.getAssociationUserId() == themeDisplay.getUserId());
						}
						applicationsList.add(awardsUtil.setAwardApplicationDTO(awardApplication));
					} else if (isDeskOfficer) {
						applicationsList.add(awardsUtil.setAwardApplicationDTO(awardApplication));
					}

				}

			}

			boolean userExistsInList = awardApplications.stream()
					.anyMatch(app -> app.getAssociationUserId() == user.getUserId());
			boolean isRejected = associationList.stream()
					.anyMatch(app -> "Reject".equalsIgnoreCase(app.getMainDeskOffVerification()));
			boolean isApproved = associationList.stream()
					.anyMatch(app -> "Approve".equalsIgnoreCase(app.getMainDeskOffVerification()));
			boolean result = userExistsInList && isAssociation;
			boolean isNewApplication = awardApplications.stream().anyMatch(
					app -> app.getAssociationUserId() == 0L && "Approve".equalsIgnoreCase(app.getDirectorRemarks()));

			renderRequest.setAttribute("isNewApplication", isNewApplication);
			renderRequest.setAttribute("result", result);
			renderRequest.setAttribute("isApproved", isApproved);
			renderRequest.setAttribute("isRejected", isRejected);
			renderRequest.setAttribute("isSportsDeskOfficer", isSportsDeskOfficer);
			renderRequest.setAttribute("isAssistantDirector", isAssistantDirector);
			renderRequest.setAttribute("isDeputyDirector", isDeputyDirector);
			renderRequest.setAttribute("sportsMaster", SportsMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("applList", applicationsList);
			renderRequest.setAttribute("isDeskOfficer", RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isAssociation", RoleConstant.isAssociation(user, themeDisplay.getCompanyId()));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}

}
