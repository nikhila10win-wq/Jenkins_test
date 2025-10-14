package com.mhdsys.registration.web.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.reg.RegistrationCommonApi;
import com.mhdsys.common.pojo.SchoolCollegeOfficerRegistrationCommonDTO;
import com.mhdsys.common.pojo.UserInformationModel;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.registration.web.util.RegistrationUtil;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONSCHOOLCOLLEGEOFFICERWEB,
		"mvc.command.name="
				+ RegistrationWebPortletKeys.SAVE_SCHOOL_COLLEGE_OFFICER_REGISTRATION_MVC_RESOURCE }, service = MVCResourceCommand.class)

public class SaveSchoolCollegeOfficerRegistrationMVCResourceCommand implements MVCResourceCommand {
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

			SchoolCollegeOfficerRegistrationCommonDTO schoolCollegeOfficerDTO = registrationUtil
					.setSchoolCollegeOfficerReg(resourceRequest, themeDisplay);
			String regType="";
			if(schoolCollegeOfficerDTO.getCurrentDesignation() ==1 ) {
				 regType = RoleConstant.SPORTS_PERSON;
			}else if(schoolCollegeOfficerDTO.getCurrentDesignation() ==2) {
				regType = RoleConstant.COACH;
			}
			UserInformationModel userInformationModel = registrationUtil.setUserInformationModule(resourceRequest,regType);
			User user = registrationCommonApi.createUserInternal(userInformationModel,regType);
			SchoolCollegeOfficerRegistration schoolCollegeOfficerRegistration = registrationCommonApi
					.saveSchoolCollegeOfficerRegistration(schoolCollegeOfficerDTO, userInformationModel, user);
			LOGGER.info("schoolCollegeOfficerRegistration: " + schoolCollegeOfficerRegistration);

			if (Validator.isNotNull(schoolCollegeOfficerRegistration)) {
				jsonObject.put("schoolCollegeOfficerReg", true);
				if (Validator.isNotNull(user)) {
					jsonObject.put("screenName", user.getScreenName());
					LOGGER.info("user.getPasswordUnencrypted():"+user.getGoogleUserId());
					jsonObject.put("password",user.getGoogleUserId());
				}
			} else {
				jsonObject.put("schoolCollegeOfficerReg", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Registration Ended");
		return false;
	}

}
