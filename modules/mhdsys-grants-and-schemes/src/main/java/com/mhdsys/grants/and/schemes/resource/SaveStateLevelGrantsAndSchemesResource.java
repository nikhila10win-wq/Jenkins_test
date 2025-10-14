package com.mhdsys.grants.and.schemes.resource;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.grants.and.schemes.GrantsAndSchemesCommonApi;
import com.mhdsys.common.pojo.AspiringAthleteCommonDTO;
import com.mhdsys.common.pojo.AwardWinnerCommonDTO;
import com.mhdsys.common.pojo.CouncilSportCompetitionDetailsCommonDTO;
import com.mhdsys.common.pojo.FinancialAssistanceCommonDTO;
import com.mhdsys.common.pojo.IntSportsCompCommonDTO;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.grants.and.schemes.util.StateLevelGrantsAndSchemesUtil;
import com.mhdsys.schema.model.AspiringAthlete;
import com.mhdsys.schema.model.AwardWinner;
import com.mhdsys.schema.model.CouncilSportCompetitionDetails;
import com.mhdsys.schema.model.FinancialAssistance;
import com.mhdsys.schema.model.IntSportsComp;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELGRANTSANDSCHEMES,
		
		"mvc.command.name="
				+ MhdsysGrantsAndSchemesPortletKeys.SAVE_STATE_LEVEL_GRANTS_AND_SCHEMES_APPLICATION }, service = MVCResourceCommand.class)

public class SaveStateLevelGrantsAndSchemesResource implements MVCResourceCommand{
	private Log LOGGER = LogFactoryUtil.getLog(SaveStateLevelGrantsAndSchemesResource.class);
	
	@Reference
	StateLevelGrantsAndSchemesUtil stateLevelGrantsAndSchemesUtil;
	
	@Reference
	GrantsAndSchemesCommonApi grantsAndSchemesCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String typeOfScheme=ParamUtil.getString(resourceRequest,"typeOfScheme");
		LOGGER.info("typeOfScheme::::::::::" + typeOfScheme);
		
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			switch (typeOfScheme) {
			case "Financial Assistance":
				long financialAssistanceId = ParamUtil.getLong(resourceRequest, "financialAssistanceId");
				FinancialAssistanceCommonDTO financialAssistanceCommonDTO=stateLevelGrantsAndSchemesUtil.setFinancialAssistanceCommonDTO(resourceRequest, themeDisplay, financialAssistanceId);
				FinancialAssistance financialAssistance=grantsAndSchemesCommonApi.saveStateLevelFinancialAssistance(financialAssistanceCommonDTO);
				jsonObject.put("status", Validator.isNotNull(financialAssistance));
				break;
			
			case "International Sports Competition":
				long intSportsCompId = ParamUtil.getLong(resourceRequest, "intSportsCompId");
				IntSportsCompCommonDTO intSportsCompCommonDTO=stateLevelGrantsAndSchemesUtil.setIntSportsCompCommonDTO(resourceRequest, themeDisplay, intSportsCompId);
				IntSportsComp intSportsComp=grantsAndSchemesCommonApi.saveIntSportsComp(intSportsCompCommonDTO);
				jsonObject.put("status", Validator.isNotNull(intSportsComp));
				break;
			case "Award Winners":
				long awardWinnerId = ParamUtil.getLong(resourceRequest, "awardWinnerId");
				AwardWinnerCommonDTO awardWinnerCommonDTO=stateLevelGrantsAndSchemesUtil.setAwardWinnerCommonDTO(resourceRequest, themeDisplay, awardWinnerId);
				LOGGER.info(awardWinnerCommonDTO);
				AwardWinner awardWinner=grantsAndSchemesCommonApi.saveStateLevelAwardWinner(awardWinnerCommonDTO);
				jsonObject.put("status", Validator.isNotNull(awardWinner));
				break;
			case "Sports Council":
				long councilSportCompetitionDetailsId = ParamUtil.getLong(resourceRequest, "councilSportCompetitionDetailsId");
				CouncilSportCompetitionDetailsCommonDTO councilSportCompetitionDetailsCommonDTO =stateLevelGrantsAndSchemesUtil.setCouncilSportCompetitionDetails(resourceRequest, themeDisplay);
				LOGGER.info(councilSportCompetitionDetailsCommonDTO);
				CouncilSportCompetitionDetails saveCouncilCompetitionDetails = grantsAndSchemesCommonApi.saveCouncilCompetitionDetails(councilSportCompetitionDetailsCommonDTO);
				jsonObject.put("status", Validator.isNotNull(saveCouncilCompetitionDetails));
				break;
			case "International Competition Aspiring Athetes":	
				long aspiringAthleteId = ParamUtil.getLong(resourceRequest, "aspiringAthleteId");
				AspiringAthleteCommonDTO aspiringAthleteCommonDTO=stateLevelGrantsAndSchemesUtil.setAspiringAthleteCommonDTO(resourceRequest, themeDisplay, aspiringAthleteId);
				LOGGER.info("AspiringAthleteCommonDTO:::::::::::"+aspiringAthleteCommonDTO);
	            AspiringAthlete aspiringAthlete=grantsAndSchemesCommonApi.saveAspiringAthlete(aspiringAthleteCommonDTO);
	            jsonObject.put("status", Validator.isNotNull(aspiringAthlete));

			default:
				LOGGER.warn("Unknown typeOfScheme: " + typeOfScheme);
				jsonObject.put("error", "Invalid typeOfRecord");
			}
			resourceResponse.setContentType("application/json");
			resourceResponse.getWriter().write(jsonObject.toString());
			
		} catch (Exception e) {
			LOGGER.error("Exception in serveResource: ", e);
			try {
				JSONObject errorObject = JSONFactoryUtil.createJSONObject();
				errorObject.put("error", "Server error occurred");
				resourceResponse.getWriter().write(errorObject.toString());
			} catch (Exception exception) {
				LOGGER.error("Failed to write error response: ", exception);
			}
		}
		return true;
	}

}
