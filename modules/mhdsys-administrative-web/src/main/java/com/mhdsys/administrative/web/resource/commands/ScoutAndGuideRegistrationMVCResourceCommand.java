package com.mhdsys.administrative.web.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.api.administrative.AdministrativeCommonApi;
import com.mhdsys.common.pojo.ScoutAndGuideRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.notification.events.util.EmailNotificationUtil;
import com.mhdsys.schema.model.ScoutAndGuideRegistration;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSCOUTGUIDEREGWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.SCOUT_AND_GUIDE_REGISTRATION_MVC_RESOURCE }, service = MVCResourceCommand.class)
public class ScoutAndGuideRegistrationMVCResourceCommand implements MVCResourceCommand {
	private Log LOGGER = LogFactoryUtil.getLog(ScoutAndGuideRegistrationMVCResourceCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;
	@Reference
	AdministrativeCommonApi administrativeCommonApi;
	@Reference
	EmailNotificationUtil emailNotificationUtil;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Scout & Guide Registration Started");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			// Set the DTO object from resource request
			ScoutAndGuideRegistrationCommonDTO scoutDTO = administrativeUtil
					.setScoutAndGuideRegistration(resourceRequest, themeDisplay);

			// Role assignment
			String regType = "";
			if (scoutDTO.getDesignation().equals("Scout Master")) {
				regType = RoleConstant.SCOUT_MASTER;
			} else if (scoutDTO.getDesignation().equals("Guide Master")) {
				regType = RoleConstant.GUIDE_MASTER;
			}
			// Prepare user info
			UserInformationModel userInformationModel = administrativeUtil.setUserInformationModule(resourceRequest,
					regType);

			// Create user
			User user = administrativeCommonApi.createUserInternal(userInformationModel, regType);

			// Save registration
			ScoutAndGuideRegistration scoutAndGuideRegistration = administrativeCommonApi
					.saveScoutAndGuideRegistration(scoutDTO, userInformationModel, user);

			LOGGER.info("scoutAndGuideRegistration: " + scoutAndGuideRegistration);

			// Prepare JSON response
			if (Validator.isNotNull(scoutAndGuideRegistration)) {
				Map<String, String> notificationContent = new HashMap<>();
				notificationContent.put("screenName", user.getScreenName());
				notificationContent.put("password", user.getGoogleUserId());
				notificationContent.put("regType", regType);
				emailNotificationUtil.sendEmailNotification(notificationContent, themeDisplay.getCompanyId(),
						"scout-master-reg", user.getEmailAddress());
				jsonObject.put("scoutGuideReg", true);
			} else {
				jsonObject.put("scoutGuideReg", false);
			}

			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error("Error in Scout & Guide Registration", e);
		}

		LOGGER.info("Scout & Guide Registration Ended");
		return false;
	}

}
