package com.mhdsys.registration.web.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.reg.RegistrationCommonApi;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.registartion.web.constants.RegistrationCommonConstants;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.registration.web.util.RegistrationUtil;
import com.mhdsys.schema.model.SportPersonCoachRegistration;
import com.mhdsys.schema.service.SportPersonCoachRegistrationLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONSPORTPERSONWEBPORTLET,
		"mvc.command.name="
				+ RegistrationWebPortletKeys.SAVE_SPORT_PERSON_REGISTRATION_MVC_RESOURCE }, service = MVCResourceCommand.class)

public class SaveSportPersonRegistrationMVCResourceCommand implements MVCResourceCommand {
	@Reference
	RegistrationUtil registrationUtil;
	@Reference
	RegistrationCommonApi registrationCommonApi;

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Registration Started");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			AwardApplicationCommonDTO awardApplicationCommonDTO = registrationUtil
					.setAwardApplicationCommonDTO(resourceRequest, themeDisplay);

			UserInformationModel userInformationModel = registrationUtil.setUserInformationModule(resourceRequest,
					awardApplicationCommonDTO.getUserType());
			
			SportPersonCoachRegistration sportPersonCoachRegistration = registrationCommonApi
					.saveSportPersonRegistration(awardApplicationCommonDTO, userInformationModel,
							awardApplicationCommonDTO.getUserType());
			LOGGER.info("sportPersonCoachRegistration: " + sportPersonCoachRegistration);
			List<SportPersonCoachRegistration> sportPersonCoachRegistrations = SportPersonCoachRegistrationLocalServiceUtil
					.getSportPersonCoachRegistrations(-1, -1);
			if (Validator.isNotNull(sportPersonCoachRegistrations)) {
				resourceRequest.setAttribute("sportPersonCoachRegistrations", sportPersonCoachRegistrations);

				PortletRequestDispatcher dispatcher = resourceRequest.getPortletSession().getPortletContext()
						.getRequestDispatcher(RegistrationCommonConstants.SPORT_PERSON_COACH_REGISTRATION_LIST_JSP);
				dispatcher.include(resourceRequest, resourceResponse);
			}
			resourceResponse.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Registration Ended");
		return false;
	}

}
