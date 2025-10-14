package com.mhdsys.awards.web.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.schema.service.AwardNameMasterLocalServiceUtil;
import com.mhdsys.schema.service.AwardPointsLocalServiceUtil;
import com.mhdsys.schema.service.CategoryMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsCompLvlMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_POINTS_LIST_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.VIEW_AWARDS_POINTS }, service = MVCRenderCommand.class)

public class AwardsPointsMVCRenderCommand implements MVCRenderCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	AwardsUtil awardsUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			long awardsPointsId = ParamUtil.getLong(renderRequest, "awardsPointsId");
			String mode = ParamUtil.getString(renderRequest, "cmd");
			LOGGER.info("awardsPointsId:" + awardsPointsId);
			LOGGER.info("mode:" + mode);

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			renderRequest.setAttribute("competitionLevels", CompetitionLevelMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsCompLevels", SportsCompLvlMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("awardsNames", AwardNameMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsMaster", SportsMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsTypes", SportsTypeMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("categories", CategoryMasterLocalServiceUtil.getByActiveState(true));
			
			LOGGER.info("Call UTIL Class To set dto :::::   ");
			renderRequest.setAttribute("awardsPoints",
					awardsUtil.setAwardsPointsDTO(AwardPointsLocalServiceUtil.getAwardPoints(awardsPointsId)));
			renderRequest.setAttribute("awardsPointsId", awardsPointsId);
			renderRequest.setAttribute("mode", mode);
		} catch (Exception e) {
			LOGGER.info(e);
			LOGGER.error(e.getMessage(), e);
		}

		return AwardsCommonConstants.VIEW_AWARDS_POINTS;
	}

}
