package com.mhdsys.grants.and.schemes.resource;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.grants.and.schemes.GrantsAndSchemesCommonApi;
import com.mhdsys.common.pojo.DistrictGrantSchemeCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.grants.and.schemes.util.GrantsAndSchemesUtil;
import com.mhdsys.schema.model.DistrictGrantScheme;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSAPPLYDISTRICTLEVELGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSDISTRICTLEVELGRANTSANDSCHEMES,
		"mvc.command.name="
				+ MhdsysGrantsAndSchemesPortletKeys.SAVE_DISTRICT_LEVEL_GRANTS_AND_SCHEMES_APPLICATION }, service = MVCResourceCommand.class)

public class SaveDistrictLevelGrantsAndSchemesResource implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveDistrictLevelGrantsAndSchemesResource.class);

	@Reference
	GrantsAndSchemesCommonApi grantsAndSchemesCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
			LOGGER.info("Grants and Schemes Addition Resource Command ::: ");
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long districtGrantSchemeId = ParamUtil.getLong(resourceRequest, "districtGrantSchemeId");
			String mode = ParamUtil.getString(resourceRequest, "mode");
			LOGGER.info("Grants and Schemes Id PRIMARY KEY ::: " + districtGrantSchemeId);

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			DistrictGrantSchemeCommonDTO grantSchemeCommonDTO = GrantsAndSchemesUtil
					.setDistrictGrantSchemeCommonDTO(resourceRequest, themeDisplay, districtGrantSchemeId);

			DistrictGrantScheme districtGrantScheme = grantsAndSchemesCommonApi
					.saveDistrictLevelGrantsAndSchemes(grantSchemeCommonDTO);
			LOGGER.info("Grant and Scheme : " + districtGrantScheme);

			if (Validator.isNotNull(districtGrantScheme)) {
				jsonObject.put("grant", true);
				jsonObject.put("mode", mode);
				if (isDeskOfficer || isHOAdmin) {
					jsonObject.put("isDeskOfficer", isDeskOfficer);
					jsonObject.put("isHoAdmin", isHOAdmin);
					jsonObject.put("mode", "");
				} else if (isDeputyDirector) {
					jsonObject.put("isDeputyDirector", isDeputyDirector);
					jsonObject.put("mode", "");
				}

			} else {
				jsonObject.put("grant", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Grants and Schemes Added");
		return false;

	}

}
