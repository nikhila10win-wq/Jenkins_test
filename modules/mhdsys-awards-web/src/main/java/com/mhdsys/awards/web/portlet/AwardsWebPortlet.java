package com.mhdsys.awards.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.AwardNameMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;
import com.mhdsys.schema.service.SportsCompLvlMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Epiphany
 */
@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Awards",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=MhdsysAwardsWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",

		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",

		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class AwardsWebPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(AwardsWebPortlet.class);

	@Reference
	RoleUtil roleUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			LOGGER.info("Awards Portlet ::: ");
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			LOGGER.info("Awards Portlet ::: ");

			boolean sportsPerson = RoleConstant.isSportsPerson(user, themeDisplay.getCompanyId());
			boolean sportsCoach = RoleConstant.isSportsCoach(user, themeDisplay.getCompanyId());

			if (sportsPerson || sportsCoach) {

				SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration = SchoolCollegeOfficerRegistrationLocalServiceUtil
						.findByUserId(user.getUserId());

				renderRequest.setAttribute("gender", schoolCollegeOfficerRegistration.getGender());

			}

			renderRequest.setAttribute("awardNames", AwardNameMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("competitionLevels",
					CompetitionLevelMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsNames", SportsMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsCompLevels", SportsCompLvlMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsTypes", SportsTypeMasterLocalServiceUtil.getByActiveState(true));

			renderRequest.setAttribute("isAssistantDirector",
					RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isDeputyDirector",
					RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isAssociation", RoleConstant.isAssociation(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isDeskOfficer", RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId()));
			LOGGER.info("RoleConstant.isSportsPerson(user, themeDisplay.getCompanyId(), :"
					+ RoleConstant.isSportsPerson(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isSportsPerson",
					RoleConstant.isSportsPerson(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isSportsCoach", RoleConstant.isSportsCoach(user, themeDisplay.getCompanyId()));
			LOGGER.info("11111     " + RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isSportsDeskOfficer",
					RoleConstant.isSportsDeskOfficer(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isHOAdmin", RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));

			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
}