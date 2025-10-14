package com.mhdsys.awards.web.resource.commands;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;

import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,

		"javax.portlet.name=" + AwardsWebPortletKeys.SPORTS_PERSON_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.UPLOAD_APPOINTMENT_LETTER_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB,

		"mvc.command.name=" + AwardsWebPortletKeys.APPOINTMENT_LETTER_UPLOAD }, service = MVCResourceCommand.class)

public class UploadAppointmentLetterMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveSportsApplicationMVCResourceCommand.class);

	@Reference
	AwardsUtil awardsUtil;

	@Reference
	AwardsCommonApi commonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		LOGGER.info("Upload Appointment Letter Resource");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			boolean isUploaded = false;

			List<AwardApplication> applications = AwardApplicationLocalServiceUtil.getAwardApplications(-1, -1);

			for (AwardApplication application : applications) {
				LOGGER.info("Application DTO : " + application);

				if (application.getDirectorRemarks().equalsIgnoreCase("Approve")) {
					isUploaded = true;
					
					if(application.getAssociationName().isEmpty()) {
						LOGGER.info("###################################");
						application.setAssociationName(ParamUtil.getString(resourceRequest, "associationName"));
						application.setName(ParamUtil.getString(resourceRequest, "name"));
						application.setAssociationUserId(themeDisplay.getUserId());

						// files upload
						UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
						ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),
								uploadPortletRequest);

						if (uploadPortletRequest != null && uploadPortletRequest.getFile("appointmentLetter") != null) {
							long appointmentLetter = AwardsUtil.uploadFile(uploadPortletRequest, "appointmentLetter",
									CommonUtilityConstant.AWARD_FOLDER, serviceContext);
							LOGGER.info("appointmentLetter Document file: " + appointmentLetter);
							application.setAppointmentLetter(appointmentLetter);
						}
						
					}

					AwardApplicationLocalServiceUtil.updateAwardApplication(application);
				}
			}

			if (isUploaded) {
				jsonObject.put("uploaded", true);
			} else {
				jsonObject.put("uploaded", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.info("application reviewd");
		return false;

	}

}
