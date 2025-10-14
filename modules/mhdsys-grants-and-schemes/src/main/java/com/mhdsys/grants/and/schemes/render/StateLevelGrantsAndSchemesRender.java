package com.mhdsys.grants.and.schemes.render;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.AwardWinnerCommonDTO;
import com.mhdsys.common.pojo.FinancialAssistanceCommonDTO;
import com.mhdsys.common.pojo.IntSportsCompCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemeConstant;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.grants.and.schemes.portlet.MhdsysStateLevelGrantsAndSchemePortlet;
import com.mhdsys.grants.and.schemes.util.StateLevelGrantsAndSchemesUtil;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.CompetitionLevelMaster;
import com.mhdsys.schema.model.FinancialAssistance;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.AwardWinnerLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.FinancialAssistanceLocalServiceUtil;
import com.mhdsys.schema.service.IntSportsCompLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSDISTRICTLEVELGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELLIST,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELDASHBOARD,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSAPPLYDISTRICTLEVELGRANTSANDSCHEMES,
		"mvc.command.name="
				+ MhdsysGrantsAndSchemesPortletKeys.STATE_LEVEL_GRANTS_AND_SCHEMES_RENDER }, service = MVCRenderCommand.class)

public class StateLevelGrantsAndSchemesRender implements MVCRenderCommand {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private Log LOGGER = LogFactoryUtil.getLog(StateLevelGrantsAndSchemesRender.class);

	@Reference
	private StateLevelGrantsAndSchemesUtil stateLevelGrantsAndSchemesUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		try {
			long awardWinnerId = ParamUtil.getLong(renderRequest, "awardWinnerId");
			long financialAssistanceId = ParamUtil.getLong(renderRequest, "financialAssistanceId");
			long intSportsCompId = ParamUtil.getLong(renderRequest, "intSportsCompId");
			String mode = ParamUtil.getString(renderRequest, "mode");
			String application = ParamUtil.getString(renderRequest, "application");
			
			long userId=0;

			LOGGER.info("Grant Scheme ID  :" + awardWinnerId);
			LOGGER.info("mode:" + mode);
			LOGGER.info("Application :::   " + application);
			LOGGER.info("financialAssistanceId :::   " + financialAssistanceId);

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
			renderRequest.setAttribute("mode", mode);

			if (awardWinnerId > 0) {
				AwardWinnerCommonDTO awardWinnerCommonDTO=stateLevelGrantsAndSchemesUtil.getAwardWinnerCommonDTO(
						AwardWinnerLocalServiceUtil.getAwardWinner(awardWinnerId), themeDisplay);
				userId=awardWinnerCommonDTO.getUserId();
				renderRequest.setAttribute("awardWinner", awardWinnerCommonDTO);
			} else {
				renderRequest.setAttribute("awardWinner", "");
			}

			if (financialAssistanceId > 0) {
				FinancialAssistanceCommonDTO financialAssistanceCommonDTO=stateLevelGrantsAndSchemesUtil.getFinancialAssistanceCommonDTO(
						FinancialAssistanceLocalServiceUtil.getFinancialAssistance(financialAssistanceId),
						themeDisplay);
				userId=financialAssistanceCommonDTO.getUserId();
				renderRequest.setAttribute("financeAssistance",financialAssistanceCommonDTO);
						
			} else {
				renderRequest.setAttribute("financeAssistance", "");
			}

			if (intSportsCompId > 0) {
				IntSportsCompCommonDTO intSportsCompCommonDTO=stateLevelGrantsAndSchemesUtil.getIntSportsCompCommonDTO(
						IntSportsCompLocalServiceUtil.getIntSportsComp(intSportsCompId), themeDisplay);
				userId=intSportsCompCommonDTO.getUserId();
				renderRequest.setAttribute("intSportsComp", intSportsCompCommonDTO);
			} else {
				renderRequest.setAttribute("intSportsComp", "");
			}
			
			if(userId>0) {
			SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration = SchoolCollegeOfficerRegistrationLocalServiceUtil
					.findByUserIdAndDesignation(userId, 1);
			Profile profile = ProfileLocalServiceUtil.findByUserId(userId);

			List<AwardApplication> awardApplications = AwardApplicationLocalServiceUtil
					.getAwardApplicationsByuserId(userId);
			AwardApplication awardApplication = null;
			if (Validator.isNotNull(awardApplications) && !awardApplications.isEmpty()) {
				awardApplication = awardApplications.get(0);
			}
			
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
			// renderRequest.setAttribute("competitionMaster", competitionMaster);
			// renderRequest.setAttribute("competitionInitiation", competitionInitiation);
			renderRequest.setAttribute("profile", profile);
			}
			
			
			

			if (application.equalsIgnoreCase("award")) {
				return MhdsysGrantsAndSchemeConstant.AWARD_WINNER_JSP;
			} else if (application.equalsIgnoreCase("financeAssistance")) {
				return MhdsysGrantsAndSchemeConstant.FINANCE_ASSISTANCE_JSP;
			} else if (application.equalsIgnoreCase("intSportsComp")) {
				return MhdsysGrantsAndSchemeConstant.INT_SPORTS_COMP_JSP;
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;

	}

}
