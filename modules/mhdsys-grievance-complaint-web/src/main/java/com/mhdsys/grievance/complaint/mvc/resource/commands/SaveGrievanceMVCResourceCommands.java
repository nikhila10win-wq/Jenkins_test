package com.mhdsys.grievance.complaint.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.grievance.GrievanceApi;
import com.mhdsys.common.pojo.GrievanceCommonDTO;
import com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys;
import com.mhdsys.grievance.util.GrievanceUtil;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.service.TalukaMasterLocalServiceUtil;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrievanceComplaintWebPortletKeys.MHDSYSGRIEVANCECOMPLAINTWEB, "mvc.command.name="
				+ MhdsysGrievanceComplaintWebPortletKeys.SAVE_GRIEVANCE_MVC_RESOURCE_COMMANDS }, service = MVCResourceCommand.class)
public class SaveGrievanceMVCResourceCommands implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	GrievanceApi grievanceApi;
	@Reference
	GrievanceUtil grievanceUtil;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Create Grievance Resource Command ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long grievanceId = ParamUtil.getLong(resourceRequest, "grievanceId");

		LOGGER.info("GRIEVANCE PRIMARY KEY ::: " + grievanceId);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			// Set DTO from resource request
			GrievanceCommonDTO grievanceDTO = grievanceUtil.setGrievanceDTO(resourceRequest, themeDisplay, grievanceId);

			// Save Grievance
			Grievance savedGrievance = grievanceApi.saveGrievance(grievanceDTO);
			LOGGER.info("Saved Grievance: " + savedGrievance);
//			createRoles();
			if (Validator.isNotNull(savedGrievance)) {
				jsonObject.put("createGrievance", true);
			} else {
				jsonObject.put("createGrievance", false);
			}

			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error("Error while saving grievance: ", e);
		}

		LOGGER.info("Grievance saved");
		return false;
	}

	public void createRoles() {
		try {
			// Fetch all PoliceStationMaster records
			List<TalukaMaster> talukas = TalukaMasterLocalServiceUtil.getByActiveState(true);

			// Get the default companyId
			long companyId = PortalUtil.getDefaultCompanyId();

			// Fetch service context for role creation
			ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

			// Use Streams to process records
			talukas.stream().filter(Validator::isNotNull) // Ensure TalukaMaster is not null
					.distinct() // Avoid duplicate TalukaMaster objects
					.map(TalukaMaster::getTalukaId) // Get TalukaMaster IDs
					.distinct() // Ensure unique IDs
					.filter(talukaId -> {
						String roleName = "TSO-" + talukaId;
						return RoleLocalServiceUtil.fetchRole(companyId, roleName) == null; // Check if role doesn't
																							// exist
					}).forEach(talukaId -> {
						String roleName = "TSO-" + talukaId;
						try {
							RoleLocalServiceUtil.addRole("",serviceContext.getUserId(), // Creator UserId
									null, // Class name (not used here)
									0L, // Class PK
									"TSO-"+talukaId, // Role name
									Collections.singletonMap(Locale.getDefault(), roleName), // Title map
									Collections.singletonMap(Locale.getDefault(), "Role for taluka: " + roleName), // Description
									RoleConstants.TYPE_REGULAR, // Role type (Regular role)
									null, // Role permissions
									serviceContext // Service Context
							);							
							LOGGER.info("Created Role: " + roleName);
						} catch (Exception e) {
							LOGGER.error("Error creating role for Taluka ID: " + talukaId, e);
						}
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
