package com.mhdsys.registration.web.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.reg.RegistrationCommonApi;
import com.mhdsys.common.pojo.RegistrationDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.registration.web.util.RegistrationUtil;
import com.mhdsys.schema.model.Registration;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONWEB,
		"mvc.command.name=" + RegistrationWebPortletKeys.REGISTER_SCHOOL_COLLEGE }, service = MVCResourceCommand.class)
public class RegisterSchoolCollegeMVCResourceCommand implements MVCResourceCommand {

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
			String regType = ParamUtil.getString(resourceRequest, "regType");

			UserInformationModel userInformationModel = registrationUtil.setUserInformationModule(resourceRequest,
					regType);
			User user = registrationCommonApi.createUserInternal(userInformationModel, regType);
			RegistrationDTO registrationDTO = registrationUtil.setRegistrationDTO(resourceRequest, themeDisplay);
			LOGGER.info("REGISTRATION DTO : " + registrationDTO.getAadharNo());

			Registration registration = registrationCommonApi.saveRegistration(registrationDTO, userInformationModel,
					regType);
			LOGGER.info("registration: " + registration);

			if (Validator.isNotNull(registration)) {
				jsonObject.put("registration", true);
				if (Validator.isNotNull(user)) {
					jsonObject.put("screenName", user.getScreenName());
					LOGGER.info("user.getPasswordUnencrypted():" + user.getGoogleUserId());
					jsonObject.put("password", user.getGoogleUserId());
					
					Map<String, String> emailContent = new HashMap<>();
					emailContent.put("initiatorName", user.getFullName());
					emailContent.put("userLogin", user.getScreenName());
					emailContent.put("userPassword", user.getGoogleUserId());
					emailContent.put("link", themeDisplay.getPortalURL() + "/login");
					Message message = new Message();
					message.put("emailContent", emailContent);
					message.put("companyId", themeDisplay.getCompanyId());
					message.put("recieverEmail", user.getEmailAddress());
					
					if(regType.equalsIgnoreCase("Desk Officer")) {
						try {
							message.put("templateName", RegistrationWebPortletKeys.SPORTS_DEPARTMENT_DESK_OFFICER_REGISTRATION_EMAIL_TEMPLATE);
							MessageBusUtil.sendMessage("mhdsys/email-parallel-message", message);
						} catch (Exception e) {
							LOGGER.error("Exception in sending email::" + e.getMessage());
						}
					}else if(regType.equalsIgnoreCase("LocalSelfGov")) {
						try {
							message.put("templateName", RegistrationWebPortletKeys.LOCAL_SELF_GOVERNMENT_EMAIL_TEMPLATE);
							MessageBusUtil.sendMessage("mhdsys/email-parallel-message", message);
						} catch (Exception e) {
							LOGGER.error("Exception in sending email::" + e.getMessage());
						}
					}else if(regType.equalsIgnoreCase("SchoolCollege")) {
						try {
							message.put("templateName", RegistrationWebPortletKeys.SCHOOL_COLLEGE_REGISTRATION_EMAIL_TEMPLATE);
							MessageBusUtil.sendMessage("mhdsys/email-parallel-message", message);
						} catch (Exception e) {
							LOGGER.error("Exception in sending email::" + e.getMessage());
						}
					}else if(regType.equalsIgnoreCase("Association")) {
						try {
							message.put("templateName", RegistrationWebPortletKeys.SPORTS_ASSOCIATIONS_REGISTRATION);
							MessageBusUtil.sendMessage("mhdsys/email-parallel-message", message);
						} catch (Exception e) {
							LOGGER.error("Exception in sending email::" + e.getMessage());
						}
					}else if(regType.equalsIgnoreCase("SportsComplex")) {
						try {
							message.put("templateName", RegistrationWebPortletKeys.SPORTS_COMPLEX_REGISTRATION);
							MessageBusUtil.sendMessage("mhdsys/email-parallel-message", message);
						} catch (Exception e) {
							LOGGER.error("Exception in sending email::" + e.getMessage());
						}
					}else if(regType.equalsIgnoreCase("SportsComplex")) {
						try {
							message.put("templateName", RegistrationWebPortletKeys.YOUTH_INSTITUTION_REGISTRATION);
							MessageBusUtil.sendMessage("mhdsys/email-parallel-message", message);
						} catch (Exception e) {
							LOGGER.error("Exception in sending email::" + e.getMessage());
						}
					}
				}
			} else {
				jsonObject.put("registration", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Registration Ended");
		return false;
	}

}
