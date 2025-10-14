package com.mhdsys.grants.and.schemes.resource;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.grants.and.schemes.GrantsAndSchemesCommonApi;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.CompetitionLevelMaster;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;

import java.text.SimpleDateFormat;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSSTATELEVELGRANTSANDSCHEMES, "mvc.command.name="
				+ MhdsysGrantsAndSchemesPortletKeys.GET_COMPETITION_DETAILS_MVC_RESOURCE }, service = MVCResourceCommand.class)

public class GetCompetitionDetailsMVCResourceCommand implements MVCResourceCommand {
	private Log LOGGER = LogFactoryUtil.getLog(GetCompetitionDetailsMVCResourceCommand.class);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Reference
	GrantsAndSchemesCommonApi grantsAndSchemesCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			String competitionName = ParamUtil.getString(resourceRequest, "competitionName");
			String competitionNameAsp = ParamUtil.getString(resourceRequest, "competitionNameAsp");
			String competitionNameA = ParamUtil.getString(resourceRequest, "competitionNameA");
			String councilCompetitionName = ParamUtil.getString(resourceRequest, "councilCompetitionName");

			LOGGER.info("competitionName:" + competitionName);
			LOGGER.info("competitionNameAsp:" + competitionNameAsp);
			LOGGER.info("competitionNameA:" + competitionNameA);
			LOGGER.info("councilCompetitionName:" + councilCompetitionName);

			AwardApplication awardApplication = null;

			if (!competitionName.isEmpty()) {
				awardApplication = AwardApplicationLocalServiceUtil.findByCompetitionName(competitionName);

			} else if (!competitionNameAsp.isEmpty()) {
				awardApplication = AwardApplicationLocalServiceUtil.findByCompetitionName(competitionNameAsp);

			} else if (!competitionNameA.isEmpty()) {
				awardApplication = AwardApplicationLocalServiceUtil.findByCompetitionName(competitionNameA);

			} else if (!councilCompetitionName.isEmpty()) {
				awardApplication = AwardApplicationLocalServiceUtil.findByCompetitionName(councilCompetitionName);

			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if (Validator.isNotNull(awardApplication)) {
				CompetitionLevelMaster competitionLevelMaster = CompetitionLevelMasterLocalServiceUtil
						.getCompetitionLevelMaster(awardApplication.getCompetitionLevelId());
				jsonObject.put("competitionLevel",
						Validator.isNotNull(competitionLevelMaster) ? competitionLevelMaster.getName_en() : "");
				jsonObject.put("sportName",
						Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(awardApplication.getSportId())
										.getName_en()
								: "");

				jsonObject.put("sportType",
						Validator.isNotNull(
								SportsTypeMasterLocalServiceUtil.getSportsTypeMaster(awardApplication.getCategory()))
										? SportsTypeMasterLocalServiceUtil
												.getSportsTypeMaster(awardApplication.getCategory()).getName_en()
										: "");
				if (Validator.isNotNull(awardApplication.getCompetitionStartDate())) {
					LOGGER.info("COMPETITION START DATE :::;  " + awardApplication.getCompetitionStartDate());
					jsonObject.put("competitionStartDate",
							formatter.format(awardApplication.getCompetitionStartDate()));
				}
			}
			jsonObject.put("awardApplication", awardApplication);
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

}
