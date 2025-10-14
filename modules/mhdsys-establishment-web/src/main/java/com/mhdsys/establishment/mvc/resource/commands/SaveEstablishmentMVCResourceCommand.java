package com.mhdsys.establishment.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.establishment.EstablishmentCommonApi;
import com.mhdsys.common.pojo.EstablishmentCommonDTO;
import com.mhdsys.common.pojo.GPFDetailsCommonDTO;
import com.mhdsys.common.pojo.NPSDetailsCommonDTO;
import com.mhdsys.common.pojo.PersonalDetailsCommonDTO;
import com.mhdsys.common.pojo.PostingStatusCommonDTO;
import com.mhdsys.common.pojo.RoasterStatusCommonDTO;
import com.mhdsys.common.pojo.ServiceDetailsCommonDTO;
import com.mhdsys.common.pojo.TrainingDetailsCommonDTO;
import com.mhdsys.establishment.util.EstablishmentUtil;
import com.mhdsys.schema.model.EstablishmentDetails;
import com.mhdsys.schema.model.GPFDetails;
import com.mhdsys.schema.model.NPSDetails;
import com.mhdsys.schema.model.PersonalDetails;
import com.mhdsys.schema.model.PostingStatus;
import com.mhdsys.schema.model.RoasterStatus;
import com.mhdsys.schema.model.ServiceDetails;
import com.mhdsys.schema.model.TrainingDetails;
import com.mhdys.establishment.constants.EstablishmentWebPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + EstablishmentWebPortletKeys.MHDSYSESTABLISHMENTWEB,
		"mvc.command.name="
				+ EstablishmentWebPortletKeys.SAVEESTABLISHMENTMVCRESOURCECOMMAND }, service = MVCResourceCommand.class)
public class SaveEstablishmentMVCResourceCommand implements MVCResourceCommand {
	private Log LOGGER = LogFactoryUtil.getLog(SaveEstablishmentMVCResourceCommand.class);
	@Reference
	EstablishmentUtil establishmentUtil;
	@Reference
	EstablishmentCommonApi commonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Save Establishment Resource");

		long establishmentDetailId = ParamUtil.getLong(resourceRequest, "establishmentDetailId");
		long personalDetailsId = ParamUtil.getLong(resourceRequest, "personalDetailsId");
		long gPFDetailsId = ParamUtil.getLong(resourceRequest, "gPFDetailsId");
		long nPSDetailsId = ParamUtil.getLong(resourceRequest, "nPSDetailsId");
		long serviceDetailsId = ParamUtil.getLong(resourceRequest, "serviceDetailsId");
		long trainingDetailsId = ParamUtil.getLong(resourceRequest, "trainingDetailsId");
		long postingStatusId = ParamUtil.getLong(resourceRequest, "postingStatusId");
		long roasterStatusId = ParamUtil.getLong(resourceRequest, "roasterStatusId");
		String typeOfRecord = ParamUtil.getString(resourceRequest, "typeOfRecord");

		LOGGER.info("establishmentDetailId: " + establishmentDetailId + ", personalDetailsId: " + personalDetailsId + 
	            ", gPFDetailsId: " + gPFDetailsId + ", nPSDetailsId: " + nPSDetailsId + 
	            ", serviceDetailsId: " + serviceDetailsId + ", trainingDetailsId: " + trainingDetailsId + 
	            ", postingStatusId: " + postingStatusId + ", roasterStatusId: " + roasterStatusId + 
	            ", typeOfRecord: " + typeOfRecord );

		LOGGER.info("establishmentDetailId :: " + establishmentDetailId);
		LOGGER.info("Type of Record :: " + typeOfRecord);

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		EstablishmentDetails establishment = establishmentUtil.findByUserId(themeDisplay.getUserId());
		long establishementId =0;
		EstablishmentCommonDTO establishmentDTO = establishmentUtil.setEstablishmentDetails(establishmentDetailId,
				typeOfRecord, themeDisplay);
			EstablishmentDetails establishmentDetails = commonApi.saveEstablishmentDetails(establishmentDTO);
			if(Validator.isNotNull(establishmentDetails)) {
				establishementId = establishmentDetails.getEstablishmentDetailId();
			}
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			switch (typeOfRecord) {
			case "Personal Details":

				PersonalDetailsCommonDTO personalDTO = establishmentUtil.setPersonalDetailsDTO(resourceRequest,
						themeDisplay, personalDetailsId,establishementId);
				LOGGER.info("PersonalDetails DTO: " + personalDTO);
				PersonalDetails personal = commonApi.savePersonalDetails(personalDTO);
				jsonObject.put("status", Validator.isNotNull(personal));
				break;

			case "Service Details":
				ServiceDetailsCommonDTO serviceDTO = establishmentUtil.setServiceDetailsDTO(resourceRequest,
						themeDisplay, serviceDetailsId,establishementId);
				LOGGER.info("ServiceDetails DTO: " + serviceDTO);
				ServiceDetails service = commonApi.saveServiceDetails(serviceDTO);
				jsonObject.put("status", Validator.isNotNull(service));
				break;

			case "GPF Details":
				GPFDetailsCommonDTO gpfDTO = establishmentUtil.setGPFDetailsDTO(resourceRequest, themeDisplay,
						gPFDetailsId,establishementId);
				LOGGER.info("GPFDetails DTO: " + gpfDTO);
				GPFDetails gpf = commonApi.saveGPFDetails(gpfDTO);
				jsonObject.put("status", Validator.isNotNull(gpf));
				break;

			case "NPS Details":
				NPSDetailsCommonDTO npsDTO = establishmentUtil.setNPSDetailsDTO(resourceRequest, themeDisplay,
						nPSDetailsId,establishementId);
				LOGGER.info("NPSDetails DTO: " + npsDTO);
				NPSDetails nps = commonApi.saveNPSDetails(npsDTO);
				jsonObject.put("status", Validator.isNotNull(nps));
				break;

			case "Training Details":
				TrainingDetailsCommonDTO trainingDTO = establishmentUtil.setTrainingDetailsDTO(resourceRequest,
						themeDisplay, trainingDetailsId,establishementId);
				LOGGER.info("TrainingDetails DTO: " + trainingDTO);
				TrainingDetails training = commonApi.saveTrainingDetails(trainingDTO);
				jsonObject.put("status", Validator.isNotNull(training));
				break;

			case "Posting Status":
				PostingStatusCommonDTO postingDTO = establishmentUtil.setPostingStatusDTO(resourceRequest, themeDisplay,
						postingStatusId,establishementId);
				LOGGER.info("PostingStatus DTO: " + postingDTO);
				PostingStatus posting = commonApi.savePostingStatus(postingDTO);
				jsonObject.put("status", Validator.isNotNull(posting));
				break;

			case "Roaster Status":
				RoasterStatusCommonDTO roasterDTO = establishmentUtil.setRoasterStatusDTO(resourceRequest, themeDisplay,
						roasterStatusId,establishementId);
				LOGGER.info("RoasterStatus DTO: " + roasterDTO);
				RoasterStatus roaster = commonApi.saveRoasterStatus(roasterDTO);
				jsonObject.put("status", Validator.isNotNull(roaster));
				break;

			default:
				LOGGER.warn("Unknown typeOfRecord: " + typeOfRecord);
				jsonObject.put("error", "Invalid typeOfRecord");
			}

			resourceResponse.setContentType("application/json");
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error("Exception in serveResource: ", e);
			try {
				JSONObject errorObject = JSONFactoryUtil.createJSONObject();
				errorObject.put("error", "Server error occurred");
				resourceResponse.getWriter().write(errorObject.toString());
			} catch (Exception exception) {
				LOGGER.error("Failed to write error response: ", exception);
			}
		}

		return true;
	}

}
