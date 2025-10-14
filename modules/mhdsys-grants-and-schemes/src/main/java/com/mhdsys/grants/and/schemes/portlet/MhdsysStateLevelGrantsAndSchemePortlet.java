
package com.mhdsys.grants.and.schemes.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.grants.and.schemes.util.StateLevelGrantsAndSchemesUtil;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.CompetitionLevelMaster;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Grants And Schemes",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysStateLevelGrantsAndScheme", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/state-level/apply-state-level-grants-and-scheme.jsp",
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELGRANTSANDSCHEMES,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MhdsysStateLevelGrantsAndSchemePortlet extends MVCPortlet {
	private static Log LOGGER = LogFactoryUtil.getLog(MhdsysStateLevelGrantsAndSchemePortlet.class);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Reference
	StateLevelGrantsAndSchemesUtil stateLevelGrantsAndSchemesUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			 
			renderRequest.setAttribute("mode", "add");
			SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration = SchoolCollegeOfficerRegistrationLocalServiceUtil
					.findByUserIdAndDesignation(themeDisplay.getUserId(), 1);
			Profile profile = ProfileLocalServiceUtil.findByUserId(themeDisplay.getUserId());

			List<AwardApplication> awardApplications = AwardApplicationLocalServiceUtil
					.getAwardApplicationsByuserId(themeDisplay.getUserId());
			AwardApplication awardApplication = null;
			if (Validator.isNotNull(awardApplications) && !awardApplications.isEmpty()) {
				awardApplication = awardApplications.get(0);
			}
			// CompetitionMaster
			// competitionMaster=CompetitionMasterLocalServiceUtil.getUserId(themeDisplay.getUserId());

			// CompetitionInitiation
			// competitionInitiation=CompetitionInitiationLocalServiceUtil.findByUserId(themeDisplay.getUserId()).get(0);

			if (Validator.isNotNull(profile)) {
				if (Validator.isNotNull(profile.getDateOfBirth())) {
					renderRequest.setAttribute("dob", formatter.format(profile.getDateOfBirth()));
				}
			}
			/* renderRequest.setAttribute("profile", profile); */
			LOGGER.info("inside grant and scheme portlet");
			if (Validator.isNotNull(schoolCollegeOfficerRegistration)) {
				LOGGER.info("schoolCollegeOfficerRegistration: " + schoolCollegeOfficerRegistration.getFirstName());
			}
			if (Validator.isNotNull(awardApplication)) {
				CompetitionLevelMaster competitionLevelMaster = CompetitionLevelMasterLocalServiceUtil
						.getCompetitionLevelMaster(awardApplication.getCompetitionLevelId());
				renderRequest.setAttribute("competitionLevel",
						Validator.isNotNull(competitionLevelMaster) ? competitionLevelMaster.getName_en() : "");
				renderRequest.setAttribute("sportName",
						Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId())
										.getName_en()
								: "");

				renderRequest.setAttribute("sportType",
						Validator.isNotNull(
								SportsTypeMasterLocalServiceUtil.getSportsTypeMaster(awardApplication.getCategory()))
										? SportsTypeMasterLocalServiceUtil
												.getSportsTypeMaster(awardApplication.getCategory()).getName_en()
										: "");
				if (Validator.isNotNull(awardApplication.getCompetitionStartDate())) {
					renderRequest.setAttribute("competitionDate",
							formatter.format(awardApplication.getCompetitionStartDate()));
				}
			}
			renderRequest.setAttribute("schoolCollegeOfficerRegistration", schoolCollegeOfficerRegistration);
			renderRequest.setAttribute("awardApplication", awardApplication);
			renderRequest.setAttribute("awardApplications", awardApplications);
			
			// renderRequest.setAttribute("competitionMaster", competitionMaster);
			// renderRequest.setAttribute("competitionInitiation", competitionInitiation);
			renderRequest.setAttribute("profile", profile);

		} catch (Exception e) {
			LOGGER.info("Error in MhdsysStateLevelGrantsAndSchemePortlet::::::::::::", e);
		}
		super.render(renderRequest, renderResponse);
	}

}
