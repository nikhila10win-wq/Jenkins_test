package com.mhdsys.grievance.complaint.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.grievance.GrievanceApi;
import com.mhdsys.common.pojo.TransferApplicationCommonDTO;
import com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys;
import com.mhdsys.grievance.util.GrievanceUtil;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.model.TransferApplication;
import com.mhdsys.schema.service.GrievanceLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrievanceComplaintWebPortletKeys.MHDSYSGRIEVANCELISTWEB,
		"javax.portlet.name=" + MhdsysGrievanceComplaintWebPortletKeys.MHDSYSHOAPPLICATIONLISTWEB, "mvc.command.name="
				+ MhdsysGrievanceComplaintWebPortletKeys.SAVE_HO_APPLICATION_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class SaveHOApplicationMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	GrievanceApi grievanceApi;
	@Reference
	GrievanceUtil grievanceUtil;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Create Transfer Application Resource Command ::: ");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long transferApplicationId = ParamUtil.getLong(resourceRequest, "transferApplicationId");
		String mode = ParamUtil.getString(resourceRequest, "mode");

		LOGGER.info("TRANSFER APPLICATION PRIMARY KEY ::: " + transferApplicationId);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			// Set DTO from request
			TransferApplicationCommonDTO transferDTO = grievanceUtil.setTransferApplication(resourceRequest,
					themeDisplay, transferApplicationId, mode);
			long grievanceId = ParamUtil.getLong(resourceRequest, "grievanceId");

			Grievance grievance = GrievanceLocalServiceUtil.getGrievance(grievanceId);
			if (Validator.isNotNull(grievance)) {
				String status = ParamUtil.getString(resourceRequest, "status");
				LOGGER.info("status: " + status);
				grievance.setStatus(ParamUtil.getString(resourceRequest, "status"));
				grievance = GrievanceLocalServiceUtil.updateGrievance(grievance);

				TransferApplication transferApplication = grievanceApi.saveTransferApplication(transferDTO);
				LOGGER.info("Saved Transfer Application: " + transferApplication);
				if (status.equalsIgnoreCase("Publish")) {
					if (Validator.isNotNull(grievance)) {
						jsonObject.put("grievance", true);
					} else {
						jsonObject.put("grievance", false);
					}
				} else {
					if (Validator.isNotNull(transferApplication)) {
						jsonObject.put("createTransfer", true);
					} else {
						jsonObject.put("createTransfer", false);
					}
				}
			}
			// Save Transfer Application

			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error("Error while saving transfer application: ", e);
		}

		LOGGER.info("Transfer Application saved");

		return false;
	}

}
