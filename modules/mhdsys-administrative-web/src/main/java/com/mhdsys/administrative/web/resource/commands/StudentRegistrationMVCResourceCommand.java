package com.mhdsys.administrative.web.resource.commands;

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
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.api.administrative.AdministrativeCommonApi;
import com.mhdsys.common.pojo.StudentRegistrationCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.StudentRegistration;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTREGWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTLISTWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.STUDENT_REGISTRATION_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class StudentRegistrationMVCResourceCommand implements MVCResourceCommand {
	private Log LOGGER = LogFactoryUtil.getLog(ScoutAndGuideRegistrationMVCResourceCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;
	@Reference
	AdministrativeCommonApi administrativeCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Create Student Registration Resource Command ::: ");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long studentRegistrationId = ParamUtil.getLong(resourceRequest, "studentRegistrationId");
		String mode = ParamUtil.getString(resourceRequest, "mode");
		String transfer = ParamUtil.getString(resourceRequest, "transfer");
		String unitType = ParamUtil.getString(resourceRequest, "unitType");
		String register = ParamUtil.getString(resourceRequest, "register");

		LOGGER.info("STUDENT REGISTRATION ID ::: " + studentRegistrationId);

//		try {
//			StudentRegistrationCommonDTO studentDTO;
//			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
//			if (transfer.equalsIgnoreCase("transfer")) {
//				// Set DTO from resource request
//				studentDTO = administrativeUtil.setStudentTransfer(resourceRequest, themeDisplay,
//						studentRegistrationId);
//			}
//			else if (mode.equalsIgnoreCase("view")&& !transfer.equalsIgnoreCase("transfer")) {
//				// Set DTO from resource request
//				studentDTO = administrativeUtil.setStudentRegister(resourceRequest, themeDisplay,
//						studentRegistrationId);
//			} else {
//				studentDTO = administrativeUtil.setStudentRegistration(resourceRequest, themeDisplay,
//						studentRegistrationId);
//			}
//			// Save Student
//			StudentRegistration savedStudent = administrativeCommonApi.saveStudentRegistration(studentDTO);
//			LOGGER.info("Saved Student Registration: " + savedStudent);
//
//			if (Validator.isNotNull(savedStudent)) {
//				jsonObject.put("createStudent", true);
//			} else {
//				jsonObject.put("createStudent", false);
//			}
//
//			resourceResponse.getWriter().write(jsonObject.toString());
//
//		}
		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			 boolean commandingOfficer = RoleConstant.isCommandingOfficer(user, themeDisplay.getCompanyId());

		    StudentRegistrationCommonDTO studentDTO;
		    JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		    LOGGER.info("transfer: " + transfer);
		    LOGGER.info("mode: " + mode);
		    LOGGER.info("studentRegistrationId: " + studentRegistrationId);

		    if ("transfer".equalsIgnoreCase(transfer)) {
		        // 1. Transfer case
		        studentDTO = administrativeUtil.setStudentTransfer(resourceRequest, themeDisplay, studentRegistrationId,commandingOfficer);

		    } else if ("view".equalsIgnoreCase(mode) && register.equalsIgnoreCase("register")) {
		        // 2. Set registerTo (e.g., Scout or Guide), after initial registration
		        studentDTO = administrativeUtil.setStudentRegister(resourceRequest, themeDisplay, studentRegistrationId,commandingOfficer);

		    } else if (studentRegistrationId > 0 && !unitType.isEmpty()) {
		        // 3. Only update unit type for existing student
		        studentDTO = administrativeUtil.setStudentUnitType(resourceRequest, themeDisplay, studentRegistrationId);

		    } else {
		        // 4. First time full registration
		        studentDTO = administrativeUtil.setStudentRegistration(resourceRequest, themeDisplay, studentRegistrationId);
		    }

		    // Save the student registration
		    StudentRegistration savedStudent = administrativeCommonApi.saveStudentRegistration(studentDTO);
		    LOGGER.info("Saved Student Registration: " + savedStudent);

		    if (Validator.isNotNull(savedStudent)) {
		        jsonObject.put("createStudent", true);
		    } else {
		        jsonObject.put("createStudent", false);
		    }

		    resourceResponse.getWriter().write(jsonObject.toString());

		}
		catch (Exception e) {
			LOGGER.error("Error while saving student registration: ", e);
		}

		return false;
	}

}
