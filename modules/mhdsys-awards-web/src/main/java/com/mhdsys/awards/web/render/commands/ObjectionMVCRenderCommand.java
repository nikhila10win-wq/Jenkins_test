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
import com.mhdsys.schema.service.AwardNameMasterLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.ObjectionLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.OBJECTION_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.SUGGESTION_OBJECTION_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.OBJECTION }, service = MVCRenderCommand.class)
public class ObjectionMVCRenderCommand implements MVCRenderCommand {

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
			long objectionId = ParamUtil.getLong(renderRequest, "objectionId");
			String cmd = ParamUtil.getString(renderRequest, "cmd");
			LOGGER.info("objectionId:" + objectionId);

			renderRequest.setAttribute("objection",
					awardsUtil.setObjectionDTO(ObjectionLocalServiceUtil.getObjection(objectionId)));

			renderRequest.setAttribute("cmd", cmd);
			renderRequest.setAttribute("objectionId", objectionId);

			renderRequest.setAttribute("sportsNames", SportsMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("awardsNames", AwardNameMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("districts", DistrictMasterLocalServiceUtil.getByActiveState(true));

			boolean isDeskOfficer = roleUtil.hasRole(user, RoleConstant.DESK_OFFICER, themeDisplay.getCompanyId());
			boolean isHOAdmin = roleUtil.hasRole(user, RoleConstant.HO_ADMIN, themeDisplay.getCompanyId());

			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			renderRequest.setAttribute("isDeputyDirector",
					RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId()));
		} catch (PortalException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return AwardsCommonConstants.SUGGESTION_OBJECTION_JSP;
	}

}
