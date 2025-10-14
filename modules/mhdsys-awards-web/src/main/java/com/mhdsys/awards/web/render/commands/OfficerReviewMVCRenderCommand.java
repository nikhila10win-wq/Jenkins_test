package com.mhdsys.awards.web.render.commands;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,
		
		"javax.portlet.name=" + AwardsWebPortletKeys.SPORTS_PERSON_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB,
		
		"mvc.command.name=" + AwardsWebPortletKeys.OFFICER_REVIEW_APPLICATION }, service = MVCRenderCommand.class)
public class OfficerReviewMVCRenderCommand implements MVCRenderCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	AwardsUtil awardsUtil;
	@Reference
	RoleUtil roleUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user;
		try {
			user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			long applicationId = ParamUtil.getLong(renderRequest, "applicationId");
			LOGGER.info("applicationId:" + applicationId);
			String cmd = ParamUtil.getString(renderRequest, "cmd");

			renderRequest.setAttribute("application", awardsUtil
					.setAwardsApplicationsDTO(AwardApplicationLocalServiceUtil.getAwardApplication(applicationId), themeDisplay));

			boolean isDeskOfficer = roleUtil.hasRole(user, RoleConstant.DESK_OFFICER, themeDisplay.getCompanyId());
			boolean isAssociation = roleUtil.hasRole(user, RoleConstant.ASSOCIATION, themeDisplay.getCompanyId());
			boolean isHOAdmin = roleUtil.hasRole(user, RoleConstant.HO_ADMIN, themeDisplay.getCompanyId());
			boolean isSportsDeskOfficer = RoleConstant.isSportsDeskOfficer(user, themeDisplay.getCompanyId());
			
			boolean isAssistantDirector = RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			
			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
		//	renderRequest.setAttribute("isAssociation", isAssociation);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			renderRequest.setAttribute("isAssociation", RoleConstant.isAssociation(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isSportsDeskOfficer", isSportsDeskOfficer);
			renderRequest.setAttribute("isAssistantDirector", isAssistantDirector);
			renderRequest.setAttribute("isDeputyDirector", isDeputyDirector);
			renderRequest.setAttribute("cmd", cmd);
		
		} catch (PortalException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return AwardsCommonConstants.AWARD_APPLICATION_VERIFICATION_JSP;
	}

}
