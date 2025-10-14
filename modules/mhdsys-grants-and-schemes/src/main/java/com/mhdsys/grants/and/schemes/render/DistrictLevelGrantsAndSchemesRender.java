package com.mhdsys.grants.and.schemes.render;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemeConstant;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.grants.and.schemes.util.GrantsAndSchemesUtil;
import com.mhdsys.schema.service.DistrictGrantSchemeLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSDISTRICTLEVELGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSAPPLYDISTRICTLEVELGRANTSANDSCHEMES,
		"mvc.command.name="
				+ MhdsysGrantsAndSchemesPortletKeys.DISTRICT_LEVEL_GRANTS_AND_SCHEMES_RENDER }, service = MVCRenderCommand.class)

public class DistrictLevelGrantsAndSchemesRender implements MVCRenderCommand {

	private Log LOGGER = LogFactoryUtil.getLog(DistrictLevelGrantsAndSchemesRender.class);
	@Reference
	GrantsAndSchemesUtil grantsAndSchemesUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		try {
			long districtGrantSchemeId = ParamUtil.getLong(renderRequest, "districtGrantSchemeId");
			String mode = ParamUtil.getString(renderRequest, "mode");

			LOGGER.info(" District Grant Scheme ID  :" + districtGrantSchemeId);
			LOGGER.info("mode:" + mode);

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

			renderRequest.setAttribute("application", grantsAndSchemesUtil.setDistrictGrantSchemeCommonDTO(
					DistrictGrantSchemeLocalServiceUtil.getDistrictGrantScheme(districtGrantSchemeId), themeDisplay));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return MhdsysGrantsAndSchemeConstant.APPLY_GRANTS_AND_SCHEMES_JSP;
	}

}
