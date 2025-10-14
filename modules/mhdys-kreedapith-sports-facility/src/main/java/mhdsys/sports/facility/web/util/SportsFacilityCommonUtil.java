package mhdsys.sports.facility.web.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.FacilityRatingCommonDTO;
import com.mhdsys.common.pojo.SportsFacilityBookingDTO;
import com.mhdsys.common.pojo.SportsFacilityMasterDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.ConstructionTrackerLocalServiceUtil;
import com.mhdsys.schema.service.SportsFacilityMasterLocalServiceUtil;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(immediate = true, service = SportsFacilityCommonUtil.class)
public class SportsFacilityCommonUtil {
	private final Log _log=LogFactoryUtil.getLog(SportsFacilityCommonUtil.class);
	@Reference FileUploadUtil fileUploadUtil;
	DateFormat daterFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public SportsFacilityMasterDTO setSportsFacilityMasterDTO(ResourceRequest resourceRequest,ThemeDisplay themeDisplay) {
		
		
		SportsFacilityMasterDTO SportsFacilityMasterDTO = new SportsFacilityMasterDTO(); 
		
		try {
			_log.info("Inside Set DTO --------------------- ");
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			boolean isDSO = false;
			boolean isTSO = false;
		        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
		        for (Role role : userRoles) {
		            if (role.getName().endsWith("-DSO")) {
		            	isDSO = true;
		            }
		            if (role.getName().endsWith("-TSO") || role.getName().startsWith("TSO-")) {
		            	isTSO = true;
		            }
		        }
		        
//		        SET ATTRIBUTES
		        String mode= ParamUtil.getString(resourceRequest, "mode");
				String actionType = ParamUtil.getString(resourceRequest, "actionType");
				
				long sportsFacilityFormId = ParamUtil.getLong(resourceRequest, "sportsFacilityFormId");
				_log.info("sportsFacilityFormId while setting -- "+sportsFacilityFormId);
				SportsFacilityMasterDTO.setSportsFacilityId(sportsFacilityFormId);
				
//					If HO - These should save if the mode is save, If mode is edit DSO/TSO fieds shoun't efffect (because that data is already stored if edit)

					SportsFacilityMasterDTO.setFacilityName(ParamUtil.getString(resourceRequest, "facilityName"));
					SportsFacilityMasterDTO.setFacilityType(ParamUtil.getString(resourceRequest, "facilityType"));
					SportsFacilityMasterDTO.setFacilityArea(ParamUtil.getString(resourceRequest, "facilityArea"));
					SportsFacilityMasterDTO.setLongitude(ParamUtil.getString(resourceRequest, "longitude"));
					SportsFacilityMasterDTO.setLatitude(ParamUtil.getString(resourceRequest, "latitude"));
					SportsFacilityMasterDTO.setFees(ParamUtil.getString(resourceRequest, "fees"));
					String[] selectedTypes = ParamUtil.getParameterValues(resourceRequest, "typeCheckBox");
					String joinedTypes = String.join(", ", selectedTypes);
					SportsFacilityMasterDTO.setType(joinedTypes);
					SportsFacilityMasterDTO.setIsUpdatedByHO(true);
					SportsFacilityMasterDTO.setModifiedDate(new Date());
					if("save".equalsIgnoreCase(actionType)) {
						SportsFacilityMasterDTO.setCreatorUserId(themeDisplay.getUserId()); // While update directly setting in service class
					}
					SportsFacilityMasterDTO.setHoAction(isHOAdmin);
					
//				IF DSO/TSO - All data including HO fields should save if the mode is save
				if((isDSO || isTSO) && "edit".equalsIgnoreCase(actionType)) {
					
					SportsFacilityMasterDTO.setBookingUrl(ParamUtil.getString(resourceRequest, "bookingUrl"));
					SportsFacilityMasterDTO.setContactPersonName(ParamUtil.getString(resourceRequest, "contactPersonName"));
					SportsFacilityMasterDTO.setContactPersonNumber(ParamUtil.getString(resourceRequest, "contactPersonNumber"));
					SportsFacilityMasterDTO.setIsUpdatedByDSO(true);
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
					                    uploadRequest, "GeoTagPhoto", "SportsFacilityMaster", serviceContext, fileName, file
					                );
					                resultJson.put("fileEntryId", fileEntryId);
					                resultJson.put("fileName", fileName);
					                fileUploadIndex++;
					            }
					        }
					        geoTagFileArray.put(resultJson);
					    }
					}
					
					SportsFacilityMasterDTO.setGeotagPhotos(geoTagFileArray.toString());
				}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SportsFacilityMasterDTO;
	}


	public SportsFacilityBookingDTO setSportsFacilityBookingDTO(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay) {
		
		SportsFacilityBookingDTO SportsFacilityBookingDTO = new SportsFacilityBookingDTO();
		
		try {
			long facilityBookingId = ParamUtil.getLong(resourceRequest, "facilityBookingId");
			long selectedFacilityId = ParamUtil.getLong(resourceRequest, "selectedFacility");
			
			String dateStr = ParamUtil.getString(resourceRequest, "Date");
			Date date = null;
			if(Validator.isNotNull(dateStr)) {
				date =	 Validator.isNotNull(daterFormatter.parse(dateStr)) ? daterFormatter.parse(dateStr): null;
			}
			 
			SportsFacilityBookingDTO.setFacilityBookingId(facilityBookingId);
			SportsFacilityBookingDTO.setSelectedFacility(selectedFacilityId);
			SportsFacilityBookingDTO.setType(ParamUtil.getString(resourceRequest, "monthlyRentalRadio"));
			SportsFacilityBookingDTO.setDate(date);
			SportsFacilityBookingDTO.setBatch(ParamUtil.getString(resourceRequest, "batch"));
			SportsFacilityBookingDTO.setTimeFrom(ParamUtil.getString(resourceRequest, "timeFrom"));
			SportsFacilityBookingDTO.setTimeTo(ParamUtil.getString(resourceRequest, "timeTo"));
			SportsFacilityBookingDTO.setSportCourt(ParamUtil.getString(resourceRequest, "sportCourt"));
			SportsFacilityBookingDTO.setName(ParamUtil.getString(resourceRequest, "name"));
			SportsFacilityBookingDTO.setContact(ParamUtil.getString(resourceRequest, "contact"));
			SportsFacilityBookingDTO.setPurpose(ParamUtil.getString(resourceRequest, "purpose"));
			SportsFacilityBookingDTO.setFees(ParamUtil.getString(resourceRequest, "fees"));
			SportsFacilityBookingDTO.setNumberOfMonths(ParamUtil.getString(resourceRequest, "numberOfMonths"));
			SportsFacilityBookingDTO.setDailyOrDate(ParamUtil.getString(resourceRequest, "dailyOrDatewise"));
			SportsFacilityBookingDTO.setCreatorUserId(themeDisplay.getUserId());
			SportsFacilityBookingDTO.setModifiedDate(new Date());
			SportsFacilityBookingDTO.setBookingStatus(0);
			
			long hiddenMedicalDoc = ParamUtil.getLong(resourceRequest, "hiddenMedicalDoc");
			_log.info("hiddenMedicalDoc:: "+hiddenMedicalDoc);
			if(Validator.isNotNull(hiddenMedicalDoc) && hiddenMedicalDoc>0) {
				SportsFacilityBookingDTO.setMedicalCertificate(hiddenMedicalDoc);
			}else {
				UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadRequest);
				File medicalCertificate = uploadRequest.getFile("medicalCertificate");
				if(Validator.isNotNull(medicalCertificate)) {
					long medicalDocFileId = fileUploadUtil.multipleFileUpload(uploadRequest, "medicalCertificate", "SportsFacilityBooking", serviceContext, medicalCertificate.getName(), medicalCertificate);
					SportsFacilityBookingDTO.setMedicalCertificate(medicalDocFileId);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SportsFacilityBookingDTO;
	}

	public Map<Long, String> getFacilityMaster() {
	    List<SportsFacilityMaster> facilities = SportsFacilityMasterLocalServiceUtil.getSportsFacilityMasters(-1, -1);
	    Map<Long, String> map = new HashMap<>();
	    for (SportsFacilityMaster facility : facilities) {
	        map.put(facility.getSportsFacilityId(), facility.getFacilityName());
	    }
	    _log.info("getFacilityMaster =============== " + map);
	    return map;
	}


	public FacilityRatingCommonDTO setFacilityRatingDTO(ThemeDisplay themeDisplay, ResourceRequest resourceRequest) {
		
		FacilityRatingCommonDTO facilityRatingCommonDTO = new FacilityRatingCommonDTO();
		try {
			facilityRatingCommonDTO.setFacilityRatingId( ParamUtil.getLong(resourceRequest, "facilityRatingId"));
			facilityRatingCommonDTO.setFacilityMasterId(ParamUtil.getLong(resourceRequest, "facilityMasterId"));
			facilityRatingCommonDTO.setRating(ParamUtil.getString(resourceRequest, "rating"));
			facilityRatingCommonDTO.setComment(ParamUtil.getString(resourceRequest, "comment"));
			facilityRatingCommonDTO.setCreatorUserId(themeDisplay.getUserId());
			facilityRatingCommonDTO.setUserName(UserLocalServiceUtil.getUser(themeDisplay.getUserId()).getFullName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return facilityRatingCommonDTO;
	}

	
}
