package com.mhdsys.awards.web.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"mvc.command.name="
				+ AwardsWebPortletKeys.AWARD_APPLICATION_REGISTERED_LIST_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)
public class AwardApplicationRegisteredListMVCRenderCommand implements MVCRenderCommand {
	@Reference
	AwardsUtil awardsUtil;
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			List<AwardApplicationCommonDTO> awardApplicationCommonDTOs = new ArrayList<>();
			// List<AwardApplication> awardApplications =
			// AwardApplicationLocalServiceUtil.getByStatus(AwardsCommonConstants.SAVE);
			List<AwardApplication> awardApplications = AwardApplicationLocalServiceUtil
					.getByStatusAndUserId(themeDisplay.getUserId(), AwardsCommonConstants.SAVE);
			if (Validator.isNotNull(awardApplications)) {
				for (AwardApplication awardApplication : awardApplications) {
					awardApplicationCommonDTOs.add(awardsUtil.setAwardApplicationDTO(awardApplication));
				}
				renderRequest.setAttribute("awardApplications", awardApplicationCommonDTOs);

				renderRequest.setAttribute("competitionLevels",
						CompetitionLevelMasterLocalServiceUtil.getByActiveState(true));

				renderRequest.setAttribute("sportsMaster", SportsMasterLocalServiceUtil.getByActiveState(true));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return AwardsCommonConstants.AWARD_APPLICATION_REGISTERED_LIST_JSP;
	}

}
