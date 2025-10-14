package mhdsys.construction.tracker.web.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.ConstructionTrackerCommonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.service.ConstructionTrackerLocalServiceUtil;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ConstructionTrackerUtil.class)
public class ConstructionTrackerUtil {
	private final Log LOGGER=LogFactoryUtil.getLog(ConstructionTrackerUtil.class);
	@Reference FileUploadUtil fileUploadUtil;
	DateFormat daterFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public ConstructionTrackerCommonDTO setConstructionTrackerDTO(ConstructionTracker constructionTracker, ThemeDisplay themeDisplay) {
	    ConstructionTrackerCommonDTO commonDTO = new ConstructionTrackerCommonDTO();
	    String language=themeDisplay.getLocale().toString();

	    try {
	        // Auto copy matching properties
	        BeanPropertiesUtil.copyProperties(constructionTracker, commonDTO);

	        // Primary Key
	        commonDTO.setConstructionTrackerId(constructionTracker.getConstructionTrackerId());

	        // Name of Sports Complex
	        
            commonDTO.setDivisionId(constructionTracker.getDivision());
            commonDTO.setDistrictId(constructionTracker.getDistrict());
            commonDTO.setTalukaId(constructionTracker.getTaluka());
            commonDTO.setStatusOfComplex(constructionTracker.getStatusOfComplex());
	        
//	        Object [] divisionObj=DivisionMasterLocalServiceUtil.getNewDivisionByDivisionId(language, true, Long.valueOf(constructionTracker.getDivision()));
//	        Object [] districtObj=DistrictMasterLocalServiceUtil.getNewDistrictByDistrictId(language, true, Long.valueOf(constructionTracker.getDistrict()));
//	        Object [] talukaObj=TalukaMasterLocalServiceUtil.getNewTalukaByTalukaId(language, true, Long.valueOf(constructionTracker.getTaluka()));
//	        commonDTO.setDivision(divisionObj[1].toString());
//	        commonDTO.setDistrict(districtObj[1].toString());
//	        commonDTO.setTaluka(talukaObj[1].toString());

	        // Address Details
	        commonDTO.setAddress(constructionTracker.getAddress());
	        commonDTO.setLatitude(constructionTracker.getLatitude());
	        commonDTO.setLongitude(constructionTracker.getLongitude());
	        commonDTO.setGeoTagPhoto(constructionTracker.getGeoTagPhoto());

	        // Officer Details
	        commonDTO.setOfficerName(constructionTracker.getOfficerName());
	        commonDTO.setContactNumber(constructionTracker.getContactNumber());

	        // Land Details
	        commonDTO.setLandAvailable(constructionTracker.getLandAvailable());
	        commonDTO.setTypeOfLand(constructionTracker.getTypeOfLand());
	        commonDTO.setArea(constructionTracker.getArea());
	        commonDTO.setOwnership(constructionTracker.getOwnership());

	        // Administration
	        commonDTO.setAdministrationJSON(constructionTracker.getAdministrationJSON());

	        // Funds
	        commonDTO.setFundsJSON(constructionTracker.getFundsJSON());

	        // Govt. Grants
	        commonDTO.setGovtGrantsJSON(constructionTracker.getGovtGrantsJSON());

	        // Other Sources
	        commonDTO.setOtherSourcesJSON(constructionTracker.getOtherSourcesJSON());

	        // Other Amounts
	        commonDTO.setOtherTotalReceivedAmount(constructionTracker.getOtherTotalReceivedAmount());
	        commonDTO.setOtherTotalExpenditureAmount(constructionTracker.getOtherTotalExpenditureAmount());
	        commonDTO.setOtherTotalBalanceAmount(constructionTracker.getOtherTotalBalanceAmount());

	        // Agency Details
	        commonDTO.setArchitectAppointed(constructionTracker.getArchitectAppointed());
	        commonDTO.setExecutingAgency(constructionTracker.getExecutingAgency());
	        commonDTO.setNameOfTheFirm(constructionTracker.getNameOfTheFirm());

	        // Progress Details
	        commonDTO.setProgressDetailsJSON(constructionTracker.getProgressDetailsJSON());

	        // UC/CC Status
	        commonDTO.setUCCCStatus(constructionTracker.getUCCCStatus());
	        commonDTO.setUCCCAmount(constructionTracker.getUCCCAmount());
	        commonDTO.setUCCCExtension(constructionTracker.getUCCCExtension());
	        commonDTO.setUCCCRevisedCompletionDate(constructionTracker.getUCCCRevisedCompletionDate());
	        commonDTO.setWorkCompletedInTimeline(constructionTracker.getWorkCompletedInTimeline());
	        commonDTO.setUCCCReason(constructionTracker.getUCCCReason());
	        commonDTO.setPanalAction(constructionTracker.getPanalAction());
	        commonDTO.setPanalActionDoc(constructionTracker.getPanalActionDoc());

	        // HO Review
	        commonDTO.setHoReview(constructionTracker.getHoReview());
	        commonDTO.setHoReviewDoc(constructionTracker.getHoReviewDoc());

	        // Status
	        commonDTO.setApplicationStatus(constructionTracker.getApplicationStatus());
	        commonDTO.setHoldWithUserId(constructionTracker.getHoldWithUserId());
	        commonDTO.setIsSaveAsDraft(constructionTracker.getIsSaveAsDraft());

	    } catch (Exception e) {
	        LOGGER.error("Error setting DTO from entity: " + e.getMessage(), e);
	    }
	    return commonDTO;
	}
	public ConstructionTrackerCommonDTO setConstructionTrackerCommonDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay) {
		
		ConstructionTrackerCommonDTO constructionTrackerDTO = new ConstructionTrackerCommonDTO();
		
		try {
			String mode= ParamUtil.getString(resourceRequest, "mode");
			String actionType = ParamUtil.getString(resourceRequest, "actionType");

//		Primary Key
		long constructionTrackerId = ParamUtil.getLong(resourceRequest, "constructionTrackerId");
		LOGGER.info("constructionTrackerId while setting -- "+constructionTrackerId);
		if(Validator.isNotNull(constructionTrackerId)) {
			constructionTrackerDTO.setConstructionTrackerId(constructionTrackerId);
			long applicationStatus = ConstructionTrackerLocalServiceUtil.getConstructionTracker(constructionTrackerId).getApplicationStatus();
			if (applicationStatus == 1 || applicationStatus == 2) {
			    constructionTrackerDTO.setApplicationStatus(applicationStatus);
			} else {
			    constructionTrackerDTO.setApplicationStatus(0);
			}
		}
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadRequest);

		JSONArray geoTagFileArray = JSONFactoryUtil.createJSONArray();
		String geoTagPhotoJson = ParamUtil.getString(uploadRequest, "geoTagPhotoHiddenInput");

		if (Validator.isNotNull(geoTagPhotoJson)) {
		    JSONArray inputArray = JSONFactoryUtil.createJSONArray(geoTagPhotoJson);
		    File[] uploadedFiles = uploadRequest.getFiles("GeoTagPhoto");
		    String[] uploadedFileNames = uploadRequest.getFileNames("GeoTagPhoto");
		    int fileUploadIndex = 0;

		    for (int i = 0; i < inputArray.length(); i++) {
		        JSONObject fileJson = inputArray.getJSONObject(i);
		        if (fileJson.has("markedForDelete") && fileJson.getBoolean("markedForDelete")) {
		            continue;
		        }

		        JSONObject resultJson = JSONFactoryUtil.createJSONObject();

		        if (fileJson.has("isExisting") && fileJson.getBoolean("isExisting")) {
		            resultJson.put("fileEntryId", fileJson.getLong("fileEntryId"));
		            resultJson.put("fileName", fileJson.getString("name"));
		        } else {
		            if (fileUploadIndex < uploadedFiles.length) {
		                File file = uploadedFiles[fileUploadIndex];
		                String fileName = uploadedFileNames[fileUploadIndex];
		                long fileEntryId = fileUploadUtil.multipleFileUpload(
		                    uploadRequest, "GeoTagPhoto", "ConstructionTracker", serviceContext, fileName, file
		                );
		                resultJson.put("fileEntryId", fileEntryId);
		                resultJson.put("fileName", fileName);
		                fileUploadIndex++;
		            }
		        }
		        geoTagFileArray.put(resultJson);
		    }
		}
		constructionTrackerDTO.setGeoTagPhoto(geoTagFileArray.toString());

		
				
//		OFFICER-DETAILS
		constructionTrackerDTO.setOfficerName(ParamUtil.getString(resourceRequest, "officerName"));
		constructionTrackerDTO.setContactNumber(ParamUtil.getString(resourceRequest, "contactNumber"));

//		Administration JSON
		JSONArray administrationJSONArray = JSONFactoryUtil.createJSONArray();
		String administrativeApproval = uploadRequest.getParameter("administrativeApproval");
		int index = 0;
		if ("Yes".equalsIgnoreCase(administrativeApproval)) {
		    while (true) {
		        String amountStr = uploadRequest.getParameter("approvalAmount_" + index);
		        String dateStr = uploadRequest.getParameter("dateOfApproval_" + index);

		        // If both amount and date are null, break the loop (no more rows)
		        if (Validator.isNull(amountStr) && Validator.isNull(dateStr)) {
		            break;
		        }

		        try {
		            JSONObject approvalObj = JSONFactoryUtil.createJSONObject();
		            approvalObj.put("administrativeApproval", administrativeApproval);
		            approvalObj.put("approvalAmount", Validator.isNotNull(amountStr) ? amountStr.trim() : "");
		            approvalObj.put("dateOfApproval", Validator.isNotNull(dateStr) ? daterFormatter.parse(dateStr) : "");
		            administrationJSONArray.put(approvalObj);
		        } catch (Exception e) {
		            LOGGER.error("Error processing approval row " + index, e);
		        }

		        index++;
		    }
		} else {
		    JSONObject approvalObj = JSONFactoryUtil.createJSONObject();
		    approvalObj.put("administrativeApproval", administrativeApproval);
		    administrationJSONArray.put(approvalObj);
		}
		LOGGER.info("Administration Index-------- "+index);
	
//		//	FUNDS
		JSONArray fundsJSONArray = JSONFactoryUtil.createJSONArray();
		int fundsIndex = 0;
		while (true) {
		    String divisionStr = ParamUtil.getString(resourceRequest, "fundsDivision_" + fundsIndex);
		    String districtStr = ParamUtil.getString(resourceRequest, "fundsDistrict_" + fundsIndex);
		    String talukaStr = ParamUtil.getString(resourceRequest, "fundsTaluka_" + fundsIndex);
		    // Stop when all 3 fields are empty (end of rows)
		    if (Validator.isNull(divisionStr) && Validator.isNull(districtStr) && Validator.isNull(talukaStr)) {
		        break;
		    }
		    try {
		        long divisionId = Long.parseLong(divisionStr);
		        long districtId = Long.parseLong(districtStr);
		        long talukaId = Long.parseLong(talukaStr);

		        JSONObject rowObj = JSONFactoryUtil.createJSONObject();
		        rowObj.put("divisionId", divisionId);
		        rowObj.put("districtId", districtId);
		        rowObj.put("talukaId", talukaId);

		        fundsJSONArray.put(rowObj);

		    } catch (NumberFormatException e) {
		        LOGGER.warn("Invalid ID format at row " + fundsIndex);
		    }
		    fundsIndex++;
		}
		
		JSONArray govtGrantsJSONArray = JSONFactoryUtil.createJSONArray();
		int govtIndex = 0;
		while (true) {
		    String grantType = ParamUtil.getString(resourceRequest, "govtGrants_" + govtIndex);
		    String receivedAmountStr = ParamUtil.getString(resourceRequest, "govtReceivedAmount_" + govtIndex);
		    String dateStr = ParamUtil.getString(resourceRequest, "govtDate_" + govtIndex);
		    String facilityAmountStr = ParamUtil.getString(resourceRequest, "govtFacilityAmount_" + govtIndex);
		    String expenditureAmountStr = ParamUtil.getString(resourceRequest, "govtExpenditureAmount_" + govtIndex);
		    String totalAmountStr = ParamUtil.getString(resourceRequest, "govtTotalAmount_" + govtIndex);

		    // If all fields are empty, break the loop
		    if (Validator.isNull(grantType) &&
		        Validator.isNull(receivedAmountStr) &&
		        Validator.isNull(dateStr) &&
		        Validator.isNull(facilityAmountStr) &&
		        Validator.isNull(expenditureAmountStr) &&
		        Validator.isNull(totalAmountStr)) {
		        break;
		    }

		    try {
		        JSONObject grantJson = JSONFactoryUtil.createJSONObject();
		         Date date = daterFormatter.parse(dateStr); 
		        grantJson.put("grantType", grantType);
		        grantJson.put("receivedAmount", receivedAmountStr);
		        grantJson.put("date", date);  
		        grantJson.put("facilityAmount", facilityAmountStr);
		        grantJson.put("expenditureAmount", expenditureAmountStr);
		        grantJson.put("totalAmount", totalAmountStr);

		        govtGrantsJSONArray.put(grantJson);

		    } catch (Exception e) {
		        LOGGER.info("Error parsing Govt Grant row " + govtIndex + ": " + e.getMessage());
		    }
		    govtIndex++;
		}
		
		JSONArray otherSourcesJSONArray = JSONFactoryUtil.createJSONArray();
		int otherSourcesIndex = 0;

		while (true) {
		    String otherSources = ParamUtil.getString(resourceRequest, "otherSources_" + otherSourcesIndex);
		    String receivedAmountStr = ParamUtil.getString(resourceRequest, "otherSourcesReceivedAmount_" + otherSourcesIndex);
		    String dateStr = ParamUtil.getString(resourceRequest, "otherSourcesDate_" + otherSourcesIndex);
		    String facilityAmountStr = ParamUtil.getString(resourceRequest, "otherSourcesFacilityAmount_" + otherSourcesIndex);
		    String expenditureAmountStr = ParamUtil.getString(resourceRequest, "otherSourcesExpenditureAmount_" + otherSourcesIndex);
		    String totalAmountStr = ParamUtil.getString(resourceRequest, "otherSourcesTotalAmount_" + otherSourcesIndex);

		    // Break if all fields are empty
		    if (Validator.isNull(otherSources) &&
		        Validator.isNull(facilityAmountStr) &&
		        Validator.isNull(expenditureAmountStr) &&
		        Validator.isNull(totalAmountStr)) {
		        break;
		    }
		    try {
		        JSONObject obj = JSONFactoryUtil.createJSONObject();
		        obj.put("otherSources", otherSources);

		        if ("Yes".equalsIgnoreCase(otherSources)) {
		            obj.put("receivedAmount", Validator.isNotNull(receivedAmountStr) ? receivedAmountStr : "");
		            obj.put("date", Validator.isNotNull(dateStr) ? daterFormatter.parse(dateStr) : "");
		        } else {
		            obj.put("receivedAmount", "");
		            obj.put("date", "");
		        }

		        obj.put("facilityAmount", Validator.isNotNull(facilityAmountStr) ? facilityAmountStr : "");
		        obj.put("expenditureAmount", Validator.isNotNull(expenditureAmountStr) ? expenditureAmountStr : "");
		        obj.put("totalAmount", Validator.isNotNull(totalAmountStr) ? totalAmountStr : "");

		        otherSourcesJSONArray.put(obj);

		    } catch (Exception e) {
		        LOGGER.error("Error parsing other sources row " + otherSourcesIndex, e);
		    }

		    otherSourcesIndex++;
		}
		
		JSONArray progressDetailsJSONArray = JSONFactoryUtil.createJSONArray();
		int progressIndex = 0;

		while (true) {
		    String workInHandStr = ParamUtil.getString(resourceRequest, "workInHand_" + progressIndex);
		    String dateOfTender = ParamUtil.getString(resourceRequest, "dateOfTender_" + progressIndex);
		    String dateOfWorkOrder = ParamUtil.getString(resourceRequest, "dateOfWorkOrder_" + progressIndex);
		    String expectedDateOfCompletion = ParamUtil.getString(resourceRequest, "expectedDateOfCompletion_" + progressIndex);
		    String progressWorkPercentage = ParamUtil.getString(resourceRequest, "progressWorkPercentage_" + progressIndex);
		    progressWorkPercentage = progressWorkPercentage.replace("%", "").trim();
		    String progressExpenditure = ParamUtil.getString(resourceRequest, "progressExpenditure_" + progressIndex);
		    
		    
		    long existingAddGanttChartHiddenInput_ = ParamUtil.getLong(resourceRequest, "addGanttChartHiddenInput_" + progressIndex);
		    long existingGISPhotoHiddenInput_ = ParamUtil.getLong(resourceRequest, "GISPhotoHiddenInput_" + progressIndex);

		    if (Validator.isNull(workInHandStr)&& Validator.isNull(dateOfTender)
		    	    && Validator.isNull(dateOfWorkOrder)
		    	    && Validator.isNull(expectedDateOfCompletion)
		    	    && Validator.isNull(progressWorkPercentage)
		    	    && Validator.isNull(progressExpenditure)) {
		    	    progressIndex++;
		    	    break;
		    	}

		    try {
		        JSONObject progressObj = JSONFactoryUtil.createJSONObject();

		        progressObj.put("workInHand", workInHandStr);
		        progressObj.put("dateOfTender", Validator.isNotNull(dateOfTender) ? daterFormatter.parse(dateOfTender) : "");
		        progressObj.put("dateOfWorkOrder", Validator.isNotNull(dateOfWorkOrder) ? daterFormatter.parse(dateOfWorkOrder) : "");
		        progressObj.put("expectedDateOfCompletion", Validator.isNotNull(expectedDateOfCompletion) ? daterFormatter.parse(expectedDateOfCompletion) : "");
		        progressObj.put("progressWorkPercentage", Double.parseDouble(progressWorkPercentage));
		        progressObj.put("progressExpenditure", progressExpenditure);

		        // These are the actual input names used in JSP
			    String addGanttChartField = "addGanttChart_" + progressIndex;
			    String gisPhotoField = "GISPhoto_" + progressIndex;
			    
		        // Correct way to fetch files using the input field names
		        File addGanttChartFile = uploadRequest.getFile(addGanttChartField);
		        File gisPhotoFile = uploadRequest.getFile(gisPhotoField);

		        if (Validator.isNotNull(existingAddGanttChartHiddenInput_) && existingAddGanttChartHiddenInput_ > 0) {
		            progressObj.put("addGanttChart", existingAddGanttChartHiddenInput_);
		        } else if (Validator.isNotNull(addGanttChartFile)) {
		            LOGGER.info("Adding new Gantt file...");
		            long ganttId = fileUploadUtil.multipleFileUpload(
		                uploadRequest, addGanttChartField, "ConstructionTracker", serviceContext,
		                addGanttChartFile.getName(), addGanttChartFile
		            );
		            progressObj.put("addGanttChart", ganttId);
		        } else {
		            LOGGER.info("No Gantt file provided for index " + progressIndex);
		        }
		        
		        if (Validator.isNotNull(existingGISPhotoHiddenInput_) && existingGISPhotoHiddenInput_ > 0) {
		            progressObj.put("GISPhoto", existingGISPhotoHiddenInput_);
		        } else if (Validator.isNotNull(gisPhotoFile)) {
		            LOGGER.info("Adding new GIS photo...");
		            long gisId = fileUploadUtil.multipleFileUpload(
		                uploadRequest, gisPhotoField, "ConstructionTracker", serviceContext,
		                gisPhotoFile.getName(), gisPhotoFile
		            );
		            progressObj.put("GISPhoto", gisId);
		        } else {
		            LOGGER.info("No GIS photo provided for index " + progressIndex);
		        }
		        
		        LOGGER.info("Progress JSON at index " + progressIndex + ": " + progressObj.toString());
		        progressDetailsJSONArray.put(progressObj);
		    } catch (Exception e) {
		        LOGGER.error("Error parsing progress details at progressIndex " + progressIndex, e);
		    }

		    progressIndex++;
		}

		constructionTrackerDTO.setAdministrationJSON(administrationJSONArray.toString());
		constructionTrackerDTO.setFundsJSON(fundsJSONArray.toString());
		constructionTrackerDTO.setGovtGrantsJSON(govtGrantsJSONArray.toString());
		constructionTrackerDTO.setOtherSourcesJSON(otherSourcesJSONArray.toString());
		constructionTrackerDTO.setProgressDetailsJSON(progressDetailsJSONArray.toString());

		constructionTrackerDTO.setOtherTotalReceivedAmount(ParamUtil.getString(resourceRequest, "otherTotalReceivedAmount"));
		constructionTrackerDTO.setOtherTotalExpenditureAmount(ParamUtil.getString(resourceRequest, "otherTotalExpenditureAmount"));
		constructionTrackerDTO.setOtherTotalBalanceAmount(ParamUtil.getString(resourceRequest, "otherTotalBalanceAmount"));

//			SPORTS-COMPLEX
			constructionTrackerDTO.setDivision(ParamUtil.getString(resourceRequest, "division"));
			constructionTrackerDTO.setDistrict(ParamUtil.getString(resourceRequest, "district"));
			constructionTrackerDTO.setTaluka(ParamUtil.getString(resourceRequest, "taluka"));
			constructionTrackerDTO.setStatusOfComplex(ParamUtil.getString(resourceRequest, "statusOfComplex"));
//			ADDRESS-DETAILS
			constructionTrackerDTO.setAddress(ParamUtil.getString(resourceRequest, "address"));
			constructionTrackerDTO.setLatitude(ParamUtil.getString(resourceRequest, "latitude"));
			constructionTrackerDTO.setLongitude(ParamUtil.getString(resourceRequest, "longitude"));
			
//			LAND-DETAILS
			constructionTrackerDTO.setLandAvailable(ParamUtil.getString(resourceRequest, "landAvailable"));
			constructionTrackerDTO.setTypeOfLand(ParamUtil.getString(resourceRequest, "typeOfLand"));
			String areaStr = ParamUtil.getString(resourceRequest, "area");
			long area = 0;
			if(Validator.isNotNull(areaStr) && areaStr!="") {
				area = (long) Double.parseDouble(areaStr);
			}
			constructionTrackerDTO.setArea(area);
			constructionTrackerDTO.setOwnership(ParamUtil.getString(resourceRequest, "ownership"));
			
//			AGENCY-DETAILS

			constructionTrackerDTO.setArchitectAppointed(ParamUtil.getString(resourceRequest, "architectAppointed"));
			constructionTrackerDTO.setExecutingAgency(ParamUtil.getString(resourceRequest, "executingAgency"));
			constructionTrackerDTO.setNameOfTheFirm(ParamUtil.getString(resourceRequest, "nameOfTheFirm")); 

			
//			UCC BLOCK
			constructionTrackerDTO.setUCCCStatus(ParamUtil.getString(resourceRequest, "UCCCStatus"));
			constructionTrackerDTO.setUCCCAmount(ParamUtil.getString(resourceRequest, "UCCCAmount"));
			constructionTrackerDTO.setUCCCExtension(ParamUtil.getString(resourceRequest, "UCCCExtension"));

			String ucccRevisedCompletionDate = ParamUtil.getString(resourceRequest, "UCCCRevisedCompletionDate");
			constructionTrackerDTO.setUCCCRevisedCompletionDate(Validator.isNotNull(ucccRevisedCompletionDate) ? daterFormatter.parse(ucccRevisedCompletionDate) : null);

			constructionTrackerDTO.setWorkCompletedInTimeline(ParamUtil.getString(resourceRequest, "workCompletedInTimeline"));
			constructionTrackerDTO.setUCCCReason(ParamUtil.getString(resourceRequest, "UCCCReason"));
			constructionTrackerDTO.setPanalAction(ParamUtil.getString(resourceRequest, "panalAction"));

			long hiddenPanelActionDoc = ParamUtil.getLong(resourceRequest, "hiddenPanelActionDoc");
			LOGGER.info("hiddenPanelActionDoc:: "+hiddenPanelActionDoc);
			if(Validator.isNotNull(hiddenPanelActionDoc) && hiddenPanelActionDoc>0) {
				constructionTrackerDTO.setPanalActionDoc(hiddenPanelActionDoc);
			}else {
				File panalActionDocFile = uploadRequest.getFile("panalActionDoc");
				if(Validator.isNotNull(panalActionDocFile)) {
					long panalActionDocFileId = fileUploadUtil.multipleFileUpload(uploadRequest, "panalActionDoc", "ConstructionTracker", serviceContext, panalActionDocFile.getName(), panalActionDocFile);
					constructionTrackerDTO.setPanalActionDoc(panalActionDocFileId);
				}
			}
			
		constructionTrackerDTO.setHoldWithUserId(themeDisplay.getUserId());
		constructionTrackerDTO.setIsSaveAsDraft(false);
		constructionTrackerDTO.setIntiatorUserId(themeDisplay.getUserId());
		constructionTrackerDTO.setModifiedDate(new Date());
//		If Draft
		if("draft".equalsIgnoreCase(actionType)) {
			constructionTrackerDTO.setIsSaveAsDraft(true);
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return constructionTrackerDTO;
	}
	public Map<String, String> getDivisionMap(List<DivisionMaster> divisions) {
	    Map<String, String> map = new HashMap<>();
	    for (DivisionMaster division : divisions) {
	        map.put(String.valueOf(division.getDivisionId()), division.getDivisionName_en());
	    }
//	    LOGGER.info("getDivisionMap::::: "+map);
	    return map;
	}

	public Map<String, String> getDistrictMap(List<DistrictMaster> districts) {
	    Map<String, String> map = new HashMap<>();
	    for (DistrictMaster district : districts) {
	        map.put(String.valueOf(district.getDistrictId()), district.getDistrictName_en());
	    }
//	    LOGGER.info("getDistrictMap::::: "+map);
	    return map;
	}

	public Map<String, String> getTalukaMap(List<TalukaMaster> talukas) {
	    Map<String, String> map = new HashMap<>();
	    for (TalukaMaster taluka : talukas) {
	        map.put(String.valueOf(taluka.getTalukaId()), taluka.getTalukaName_en());
	    }
//	    LOGGER.info("getDistrictMap::::: "+map);
	    return map;
	}


}
