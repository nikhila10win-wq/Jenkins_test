package com.mhdsys.awards.web.resource.commands;

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
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.common.pojo.AwardPointsCommonDTO;
import com.mhdsys.schema.model.AwardPoints;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_POINTS_LIST_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.CREATE_AWARDS_POINTS }, service = MVCResourceCommand.class)
public class SaveAwardPointsMVCResourceCommands implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Create Awards Points Resource Commands ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long awardsPointsId = ParamUtil.getLong(resourceRequest, "awardsPointsId");
		String mode = ParamUtil.getString(resourceRequest, "mode");
		
		LOGGER.info("AWARDS POINT ID PRIMARY KEY ::: "+awardsPointsId);
		
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			AwardPointsCommonDTO awardPointsDTO = AwardsUtil.setAwardsPointDTO(resourceRequest, themeDisplay, awardsPointsId);
			AwardPoints awardPoints = awardsApi.saveAwardsPoint(awardPointsDTO);
			LOGGER.info("awardPoints: " + awardPoints);

			if (Validator.isNotNull(awardPoints)) {
				jsonObject.put("createAwardPoints", true);
				
				if(mode.equalsIgnoreCase("edit")) {
					jsonObject.put("mode", "edit");
				}
			} else {
				jsonObject.put("createAwardPoints", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("awards point saved");
		LOGGER.info("awards point saved");
		return false;
	}
	
	@Reference
	AwardsCommonApi awardsApi;
}
