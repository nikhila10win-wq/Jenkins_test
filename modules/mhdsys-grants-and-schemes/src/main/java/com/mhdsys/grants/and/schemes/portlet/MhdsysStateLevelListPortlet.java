package com.mhdsys.grants.and.schemes.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.schema.service.AspiringAthleteLocalServiceUtil;
import com.mhdsys.schema.service.AwardWinnerLocalServiceUtil;
import com.mhdsys.schema.service.CouncilSportCompetitionDetailsLocalService;
import com.mhdsys.schema.service.CouncilSportCompetitionDetailsLocalServiceUtil;
import com.mhdsys.schema.service.FinancialAssistanceLocalServiceUtil;
import com.mhdsys.schema.service.IntSportsCompLocalServiceUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Grants And Schemes",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysStateLevelGrantsAndSchemeList", "javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/state-level/state-level-list.jsp",
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELLIST,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MhdsysStateLevelListPortlet extends MVCPortlet {

	private static Log LOGGER = LogFactoryUtil.getLog(MhdsysStateLevelListPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		LOGGER.info("MhdsysStateLevelListPortlet  :::   ");

		try {
			LOGGER.info("############");
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isAssociation = RoleConstant.isAssociation(user, themeDisplay.getCompanyId());
			boolean isHoAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			
			if(isDeskOfficer || isAssociation || isHoAdmin || isDeputyDirector) {
				renderRequest.setAttribute("awardWinnerList", AwardWinnerLocalServiceUtil.getAwardWinners(-1, -1));
				renderRequest.setAttribute("financeAssistanceList",
						FinancialAssistanceLocalServiceUtil.getFinancialAssistances(-1, -1));
				renderRequest.setAttribute("intSportsCompList", IntSportsCompLocalServiceUtil.getIntSportsComps(-1, -1));
				renderRequest.setAttribute("councilSportList", CouncilSportCompetitionDetailsLocalServiceUtil.getCouncilSportCompetitionDetailses(-1, -1));
				renderRequest.setAttribute("aspiringAthlete", AspiringAthleteLocalServiceUtil.getAspiringAthletes(-1, -1));
				
			}else {
				renderRequest.setAttribute("awardWinnerList", Validator.isNotNull(AwardWinnerLocalServiceUtil.getAwardWinnerListByUserId(themeDisplay.getUserId()))
						? AwardWinnerLocalServiceUtil.getAwardWinnerListByUserId(themeDisplay.getUserId()):"");
				renderRequest.setAttribute("financeAssistanceList",Validator.isNotNull(FinancialAssistanceLocalServiceUtil.getFinancialAssistanceListByUserId(themeDisplay.getUserId()))
						? FinancialAssistanceLocalServiceUtil.getFinancialAssistanceListByUserId(themeDisplay.getUserId()):"");
				renderRequest.setAttribute("intSportsCompList",Validator.isNotNull(IntSportsCompLocalServiceUtil.getIntSportsCompListByUserId(themeDisplay.getUserId()))
						? IntSportsCompLocalServiceUtil.getIntSportsCompListByUserId(themeDisplay.getUserId()):"");
				renderRequest.setAttribute("councilSportList", Validator.isNotNull(CouncilSportCompetitionDetailsLocalServiceUtil.getCouncilSportCompetitionDetailsListByUserId(themeDisplay.getUserId()))
						? CouncilSportCompetitionDetailsLocalServiceUtil.getCouncilSportCompetitionDetailsListByUserId(themeDisplay.getUserId()):"");
				renderRequest.setAttribute("aspiringAthlete", Validator.isNotNull(AspiringAthleteLocalServiceUtil.getAspiringAthleteByUserId(themeDisplay.getUserId()))
						? AspiringAthleteLocalServiceUtil.getAspiringAthleteByUserId(themeDisplay.getUserId()): "");
				
			}

			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isHoAdmin", isHoAdmin);
			renderRequest.setAttribute("isAssociation", isAssociation);
			renderRequest.setAttribute("isDeputyDirector", isDeputyDirector);

		

			super.render(renderRequest, renderResponse);

		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

}
