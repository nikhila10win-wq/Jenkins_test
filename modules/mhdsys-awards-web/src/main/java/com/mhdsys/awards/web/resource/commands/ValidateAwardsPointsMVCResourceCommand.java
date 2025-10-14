package com.mhdsys.awards.web.resource.commands;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.schema.model.AwardPoints;
import com.mhdsys.schema.service.AwardPointsLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.VALIDATE_AWARDS_POINTS }, service = MVCResourceCommand.class)

public class ValidateAwardsPointsMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(ValidateAwardsPointsMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Validate Awards Points Resource Commands ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long awardName = ParamUtil.getLong(resourceRequest, "awardName");
		String awardYear = ParamUtil.getString(resourceRequest, "awardYear");
		long sportsCompetitionLevel = ParamUtil.getLong(resourceRequest, "sportsCompetitionLevel");
		long sportsType = ParamUtil.getLong(resourceRequest, "sportsType");
		long category = ParamUtil.getLong(resourceRequest, "category");
		long sportsName = ParamUtil.getLong(resourceRequest, "sportsName");
		long competitionLevel = ParamUtil.getLong(resourceRequest, "competitionLevel");

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			AwardPoints awardPoints = null;

			LOGGER.info("Dynamic Query Started ::: ");

			DynamicQuery dynamicQuery = AwardPointsLocalServiceUtil.dynamicQuery();

			dynamicQuery.add(RestrictionsFactoryUtil.eq("awardNameId", awardName));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("awardYear", awardYear));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("sportsCompetitionLevelId", sportsCompetitionLevel));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("sportsTypeId", sportsType));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("category", category));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("sportsNameId", sportsName));
			dynamicQuery.add(RestrictionsFactoryUtil.eq("competitionLevelId", competitionLevel));

			List<AwardPoints> results = AwardPointsLocalServiceUtil.dynamicQuery(dynamicQuery);

			LOGGER.info("AWARD POINTS SIZE  : " + results);

			if (results.size() > 0) {
				LOGGER.info("Awards Points Available : ");
				awardPoints = results.get(0);
			}

			if (Validator.isNotNull(awardPoints)) {
				LOGGER.info("TRUE ::: ");
				jsonObject.put("awardsPoints", true);

			} else {
				jsonObject.put("awardsPoints", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("awards point validated :");
		return false;
	}

}
