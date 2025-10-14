package mhdsys.sports.coaching.wing.portlet.mvc;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.Coach;
import com.mhdsys.schema.model.Servant;
import com.mhdsys.schema.model.SportsPerson;
import com.mhdsys.schema.model.TrainingCentre;
import com.mhdsys.schema.model.TrainingCentreFinancialDetail;
import com.mhdsys.schema.model.TrainingCentreReport;
import com.mhdsys.schema.service.CoachLocalServiceUtil;
import com.mhdsys.schema.service.ServantLocalServiceUtil;
import com.mhdsys.schema.service.SportsPersonLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreFinancialDetailLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreReportLocalServiceUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;

@Component(
		immediate=true,
	    property = { 
	    		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
	    		"mvc.command.name="+SportsCoachinWingMvcCommand.SAVE_CAOCHIN_WING_RESOURCE_URL
	    }, 
	    service = MVCResourceCommand.class
)
public class SaveSportCoachingWingMVCResourceCommand  implements MVCResourceCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(SaveSportCoachingWingMVCResourceCommand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		_log.info("Entry into :: SaveSportCoachingWingMVCResourceCommand ::: serveResource() :: ");
		 
		 ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		 boolean isdistdeputydirector=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.DDD,themeDisplay.getCompanyId());
		 boolean ishoadmin=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.HO_ADMIN,themeDisplay.getCompanyId());
		 boolean isDSO=roleUtil.hasDSORole(themeDisplay.getUser());
		 	
		 UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
	      JSONObject responseJson = JSONFactoryUtil.createJSONObject();
	        
	        String testname =ParamUtil.getString(resourceRequest, "testname");
	        String currentTab =ParamUtil.getString(resourceRequest, "currentTab");
	        String formStatus =ParamUtil.getString(resourceRequest, "formStatus");
	        String formStatusInput =ParamUtil.getString(resourceRequest, "formStatusInput");
	        String currentStatus =ParamUtil.getString(resourceRequest, "currentStatus");
	        
	        String ddRemarks =ParamUtil.getString(resourceRequest, "ddRemarks");
	        boolean ddDecision =ParamUtil.getBoolean(resourceRequest, "ddDecision");
	        
	        boolean ddHoDecision =ParamUtil.getBoolean(resourceRequest, "ddHoDecision");
	        String ddHoRemarks =ParamUtil.getString(resourceRequest, "ddHoRemarks");
	        
	        //String formStatus =ParamUtil.getString(resourceRequest, "formStatus");
	        long trainingcenterId=ParamUtil.getLong(resourceRequest, "trainingcenterId");
	        
	        _log.info("serveResource formStatusInput :::" +formStatusInput);
	        _log.info("serveResource testname :::" +testname);
	        _log.info("serveResource currentTab :::" +currentTab);
	        _log.info("serveResource currentStatus :::" +currentStatus);
	        _log.info("serveResource formStatus :::" +formStatus);
	        _log.info("serveResource trainingcenterId :::" +trainingcenterId);
	        _log.info("serveResource ddDecision :::" +ddDecision);
	        _log.info("serveResource ddRemarks :::" +ddRemarks);
	        
	        _log.info("saveTrainingCenterDetails trainingcenterId :::"+trainingcenterId);
	        if(isdistdeputydirector){
	        	  _log.info("inside  isdistdeputydirector::: trainingcenterId ::::" +trainingcenterId);
	        	  if(Validator.isNotNull(trainingcenterId) && trainingcenterId>0) {
	        		  TrainingCentre trainingCentre=null;
	        		  try {
	        			  trainingCentre = TrainingCentreLocalServiceUtil.getTrainingCentre(trainingcenterId);
					} catch (Exception e) {
						_log.error("Error in code :::" +e);
					}
	        		  
	        		 _log.info("trainingCentre in isdistdeputydirector :: " +trainingCentre); 
	        		if(Validator.isNotNull(trainingCentre)){
	        			trainingCentre.setDivdepDirUserid(themeDisplay.getUserId());
	        			trainingCentre.setDivdepDirDecision(ddDecision);
	        			trainingCentre.setDivdepDirRemarks(ddRemarks);
	        			trainingCentre.setStatus(ddDecision?"pendingwithHO":"rejectedByDD");
	        			TrainingCentreLocalServiceUtil.updateTrainingCentre(trainingCentre);
	        		}  
	        	  }
	        }if(ishoadmin){
	        	  _log.info("inside  ishoadmin::: trainingcenterId ::::" +trainingcenterId);
	        	  if(Validator.isNotNull(trainingcenterId) && trainingcenterId>0) {
	        		  TrainingCentre trainingCentre=null;
	        		  try {
	        			  trainingCentre = TrainingCentreLocalServiceUtil.getTrainingCentre(trainingcenterId);
					} catch (Exception e) {
						_log.error("Error in code :::" +e);
					}
	        		 _log.info("trainingCentre in isdistdeputydirector :: " +trainingCentre); 
	        		if(Validator.isNotNull(trainingCentre)){
	        			trainingCentre.setHoDirUserid(themeDisplay.getUserId());
	        			trainingCentre.setHoDecision(ddHoDecision);
	        			trainingCentre.setHoRemarks(ddHoRemarks);
	        			TrainingCentreLocalServiceUtil.updateTrainingCentre(trainingCentre);
	        		}  
	        	  }
	        }else if(isDSO){
		        	_log.info("Entry into save as draft functionality :::");
		        	try {
						  // Save Training Centre 
						 TrainingCentre trainingCentre =  saveTrainingCentre(resourceRequest,uploadRequest, themeDisplay);
						  _log.info("trainingCentre :::" +trainingCentre); 
						  // long trainingCentreId = trainingCentre.getTrainingCentreId();
						  
						  // Save Coaches
						 List<Coach> saveCoaches = saveCoaches(resourceRequest,uploadRequest,trainingCentre.getTrainingCentreId());
						  _log.info("saveCoaches :::"+saveCoaches); 
						  //saveCoaches(uploadRequest, trainingCentreId);
						  
						  // Save Sports Persons 
						  //saveSportsPersons(resourceRequest,uploadRequest, 0);
						 saveSportsPersons(resourceRequest,uploadRequest,trainingCentre.getTrainingCentreId());
						  
						  
						  //save servant
						  saveServant(resourceRequest,uploadRequest,trainingCentre.getTrainingCentreId());
						  //saveServant(resourceRequest,uploadRequest,trainingCentre.getTrainingCentreId());
						  
						  // Save Financial Details
						  //saveFinancialDetails(resourceRequest,uploadRequest, 0);
						  saveFinancialDetails(resourceRequest,uploadRequest,trainingCentre.getTrainingCentreId());
						  
						  // Save Reports
						  //saveReports(resourceRequest,themeDisplay, uploadRequest,0); 
						  saveReports(resourceRequest,themeDisplay, uploadRequest,trainingCentre.getTrainingCentreId());
						  
						  responseJson.put("status", "success"); 
						  responseJson.put("message","Form data saved successfully"); 
						  responseJson.put("trainingCentreId",trainingCentre.getTrainingCentreId());
						  //responseJson.put("trainingCentreId",trainingCentre.getTrainingCentreId());
						 
		        } catch (Exception e) {
		            responseJson.put("status", "error");
		            responseJson.put("message", "Error saving form data: " + e.getMessage());
		            _log.error("Error in code :::"+e);
		        }
	        }
		try {
			resourceResponse.getWriter().write(responseJson.toString());
			return Boolean.FALSE;
		} catch (IOException e) {	
			_log.error("Error, "+e.getMessage());
			return Boolean.TRUE;
		}
	}
	
	private void saveServant(ResourceRequest resourceRequest, UploadPortletRequest uploadRequest,
			long trainingCentreId) {
		_log.info("inside addin servant:::::");
		
		int servantcount = ParamUtil.getInteger(resourceRequest, "servantcount");
		_log.info("servantcount :::" +servantcount);
		if(Validator.isNotNull(servantcount)) {
			
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			
			for(int i=1;i<=servantcount;i++) {
				
				long servantId = ParamUtil.getLong(resourceRequest, "servantId_" + i);
				String name=ParamUtil.getString(resourceRequest, "servantname_"+i);
				String surname=ParamUtil.getString(resourceRequest, "servantsurname_"+i);
				String dateOfBirthStr=ParamUtil.getString(resourceRequest, "servantdob_"+i);
				Date dateOfBirth=ParamUtil.getDate(resourceRequest, "servantdob_"+i,sf);
				String joiningDateStr=ParamUtil.getString(resourceRequest, "servantdoj_"+i);
				Date joiningDate=ParamUtil.getDate(resourceRequest, "servantdoj_"+i,sf);
				String address=ParamUtil.getString(resourceRequest, "servantaddress_"+i);
				
				//Date dateOfBirth=ParamUtil.getDate(resourceRequest, "servantname_"+i);
				_log.info("servantId :::" +servantId);
				_log.info("name :::" +name);
				_log.info("dateOfBirthStr :::" +dateOfBirthStr);
				_log.info("joiningDateStr :::" +joiningDateStr);
				if(Validator.isNotNull(name) && Validator.isNotNull(surname) && Validator.isNotNull(address)) {
					Servant servant=null;
					if(Validator.isNotNull(servantId)) {
						try {
							servant=	ServantLocalServiceUtil.getServant(servantId);
						} catch (Exception e) {
							_log.error("Error in code :::" +e);
						}
					}else {
						servant = ServantLocalServiceUtil.createServant(CounterLocalServiceUtil.increment(Servant.class.getName()));
					}
					try {
						servant.setTrainingCentreId(trainingCentreId);
						servant.setName(name);
						servant.setSurname(surname);
						servant.setDob(dateOfBirth);
						servant.setAddress(address);
						servant.setJoiningDate(joiningDate);
						servant.setCreateDate(new Date());
						servant.setModifiedDate(new Date());
						ServantLocalServiceUtil.updateServant(servant);
						_log.info("servant :::: " +servant);
					}catch(Exception e) {
						_log.error("Error in code::" +e);
					}
				}
			}
		}
		//String name = ParamUtil.getString(resourceRequest, "division");
	}

	//Save training center	
	private TrainingCentre saveTrainingCentre(ResourceRequest resourceRequest, UploadPortletRequest uploadRequest,
			ThemeDisplay themeDisplay) throws PortalException, IOException {
		long trainingcenterId = ParamUtil.getLong(resourceRequest, "trainingcenterId");
		
		String division = ParamUtil.getString(uploadRequest, "division");
		String district = ParamUtil.getString(uploadRequest, "district");
		String centreType = ParamUtil.getString(uploadRequest, "centre_type");
		String sportsType = ParamUtil.getString(uploadRequest, "sports_type");
		String gisMap = ParamUtil.getString(uploadRequest, "gis_map");
		String latitude = ParamUtil.getString(resourceRequest, "latitude");
		String longitude = ParamUtil.getString(resourceRequest, "longitude");
		String currentStatus=ParamUtil.getString(uploadRequest, "currentStatus");
		long dprDocId=ParamUtil.getLong(uploadRequest, "dprDocPath");
		 String formStatusInput =ParamUtil.getString(resourceRequest, "formStatusInput");
		 
		 
		_log.info("district ::::" +district);
		_log.debug("trainingcenterId ::::" +trainingcenterId);
		_log.debug("saveTrainingCentre currentStatus ::::" +currentStatus);
		_log.debug("saveTrainingCentre formStatusInput ::::" +formStatusInput);
		
		// Validate required fields
		if (Validator.isNull(division) || Validator.isNull(district) || Validator.isNull(centreType)
				|| Validator.isNull(sportsType)) {
			throw new IllegalArgumentException("Required fields are missing");
		}
		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadRequest);
		// Handle DPR document upload
		String dprDocPath = "";
		File dprDoc = uploadRequest.getFile("dpr_doc");
		List<Long> actualGeoTagPhotosIds = new ArrayList<>();
		List<Long> actualSupportedDocumentsIds = new ArrayList<>();
		_log.info("dprDoc:::" + dprDoc);

		long fileEntryId = 0l;
		
		
		if (dprDoc != null && dprDoc.exists()) {
			fileEntryId = fileUploadUtil.uploadFile(uploadRequest, "dpr_doc", "SPORTS_COACHING_WING", serviceContext);
		}
		
		// Process existing files (kept in the UI)
		String[] existingGeoTagPhotos = ParamUtil.getParameterValues(resourceRequest, "existingGeoTagPhotos");
		String[] existingGeoTagPhotosIds = ParamUtil.getParameterValues(resourceRequest,"existingGeoTagPhotosIds");
		
		_log.debug("existingGeoTagPhotos :::" +existingGeoTagPhotos.length);
		_log.debug("existingGeoTagPhotosIds :::" +existingGeoTagPhotosIds.length);
		
		if (existingGeoTagPhotos != null && existingGeoTagPhotos.length > 0) {
			if (existingGeoTagPhotos.length == existingGeoTagPhotosIds.length) {
				for (int i = 0; i < existingGeoTagPhotos.length; i++) {
					String fileIdStr = existingGeoTagPhotosIds[i];
					_log.info("Existing File - Name: " + ", ID: " + fileIdStr);
					try {
						long fileId = Long.parseLong(fileIdStr);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileId);
						actualGeoTagPhotosIds.add(fileEntry.getFileEntryId());
					} catch (Exception e) {
						_log.error("Error processing existing file with ID: " + fileIdStr, e);
					}
				}
			}
		}
		
		// Process new actual geo tag photos
		if (uploadRequest.getFile("actualGeoTagPhotos") != null) {
			File[] actualStorageFiles = uploadRequest.getFiles("actualGeoTagPhotos");
			_log.debug("SIZE ::::   " + actualStorageFiles.length);
			String[] actualStorageFileNames = ParamUtil.getParameterValues(resourceRequest, "actualGeoTagPhotosNames");

			for (int i = 0; i < actualStorageFiles.length; i++) {
				File file = actualStorageFiles[i];
				String fileName = actualStorageFileNames.length > i ? actualStorageFileNames[i] : "unknown";
				_log.info("File Name ::::  " + fileName);
				long geofileEntryId = multipleFileUpload(themeDisplay, uploadRequest, "actualGeoTagPhotos",
						"SPORTS_COACHING_WING", serviceContext, fileName, file);
				actualGeoTagPhotosIds.add(geofileEntryId);
			}
			_log.info("Geo Tag Photos photos: " + actualGeoTagPhotosIds.toString());
		}
		
		
		TrainingCentre trainingCentre =null;
		if(Validator.isNotNull(trainingcenterId)) {
			trainingCentre=TrainingCentreLocalServiceUtil.getTrainingCentre(trainingcenterId);
		}else {
			trainingCentre= TrainingCentreLocalServiceUtil
					.createTrainingCentre(CounterLocalServiceUtil.increment(TrainingCentre.class.getName()));
		}
		
		trainingCentre.setDivision(division);
		trainingCentre.setDistrict(district);
		trainingCentre.setCentreType(centreType);
		trainingCentre.setSportsType(sportsType);
		trainingCentre.setLatitude(latitude);
		trainingCentre.setLongitude(longitude);
		
		//Geo Tag photos
		if(Validator.isNotNull(trainingcenterId) && trainingcenterId>0){
			if(actualGeoTagPhotosIds.size()>0) {
				trainingCentre.setGeoTag(actualGeoTagPhotosIds.toString());
			}
		}else {
			trainingCentre.setGeoTag(actualGeoTagPhotosIds.toString());
		}
		trainingCentre.setGisMap(gisMap);
		if(dprDocId>0) {
			trainingCentre.setDprDocPath(String.valueOf(dprDocId));
		}else {
			trainingCentre.setDprDocPath(String.valueOf(fileEntryId));
		}
		trainingCentre.setCreateDate(new Date());
		trainingCentre.setModifiedDate(new Date());
		trainingCentre.setUserid(themeDisplay.getUserId());
		/*if(currentStatus.equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT)) {
			trainingCentre.setFormstatus(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT);
		}else if(currentStatus.equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.STATUS_SUBMIT)) {
			trainingCentre.setFormstatus(MhdsysSportsCoachingWingPortletKeys.STATUS_SUBMIT);
		}*/
		
		if(currentStatus.equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT)) {
			_log.info("inside if ");
			trainingCentre.setFormstatus(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT);
		}else if(currentStatus.equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.STATUS_SUBMIT)) {
			_log.info("inside else ");
			trainingCentre.setFormstatus(MhdsysSportsCoachingWingPortletKeys.STATUS_SUBMIT);
			trainingCentre.setStatus(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DD);
		}
		
		return TrainingCentreLocalServiceUtil.updateTrainingCentre(trainingCentre);
	}
	
	
	//Add Coach
    private List<Coach> saveCoaches(ResourceRequest resourceRequest, UploadPortletRequest uploadRequest, long trainingCentreId) throws PortalException {
    		_log.info("Entry into saveCoaches ::::+++++++ ");
    		List<Coach> coachList=new ArrayList<>();
	        int coachCount = ParamUtil.getInteger(resourceRequest, "coachCount", 0);
	        _log.info("coachCount  :::" +coachCount);
	        	String formStatus=ParamUtil.getString(uploadRequest, "formStatus");
	        
	        	for (int i = 1; i <= coachCount; i++) { 
	        		long coachexistingId = ParamUtil.getLong(uploadRequest, "coachId_" + i);
		            String coachType = ParamUtil.getString(uploadRequest, "coach_type_" + i);
		            String fullName = ParamUtil.getString(uploadRequest, "coach_name_" + i);
		            String mobileNumber = ParamUtil.getString(uploadRequest, "coach_mobile_" + i);
		            String email = ParamUtil.getString(uploadRequest, "coach_email_" + i);
		            String sportsName=ParamUtil.getString(uploadRequest, "coach_sports_name_" + i);
		            String coachDistrict =ParamUtil.getString(uploadRequest, "coachdistrictselect_" + i);
		            String coachRemarks = ParamUtil.getString(uploadRequest, "coach_remarks_" + i);
	            _log.info("coachexistingId ::" +coachexistingId);
	            _log.info("coachType ::" +coachType);
	            _log.info("fullName ::" +fullName);
	            _log.info("mobileNumber ::" +mobileNumber);
	            _log.info("email ::" +email);
	            _log.info("sportsName ::" +sportsName);
	            _log.info("coachDistrict ::" +coachDistrict);
	            _log.info("coachRemarks =================::" +coachRemarks);
	            
	            if (Validator.isNull(coachType) || Validator.isNull(fullName) || Validator.isNull(mobileNumber) || Validator.isNull(email)) {
	                continue; // Skip incomplete entries
	            }
	            Coach coach=null;
	            
	            if(Validator.isNotNull(coachexistingId) && coachexistingId>0) {
	            	try {
						coach=CoachLocalServiceUtil.getCoach(coachexistingId);
					} catch (Exception e) {
						_log.error("Error in code :::"+e);
					}
	            }else {
	            	coach = CoachLocalServiceUtil.createCoach(CounterLocalServiceUtil.increment(Coach.class.getName()));
	            }
	            coach.setTrainingCentreId(trainingCentreId);
	            coach.setFormStatus(formStatus);
	            coach.setCoachType(coachType);
	            coach.setFullName(fullName);
	            coach.setMobileNumber(mobileNumber);
	            coach.setEmail(email);
	            coach.setSportsName(ParamUtil.getString(uploadRequest, "coach_sports_name_" + i));
	            coach.setAddress(ParamUtil.getString(uploadRequest, "coach_address_" + i));
	            coach.setTracksuitSize(ParamUtil.getString(uploadRequest, "tracksuit_size_" + i));
	            coach.setTshirtSize(ParamUtil.getString(uploadRequest, "tshirt_size_" + i));
	            coach.setShortsSize(ParamUtil.getString(uploadRequest, "shorts_size_" + i));
	            coach.setShoesSize(ParamUtil.getString(uploadRequest, "shoes_size_" + i));
	            coach.setRemarks(ParamUtil.getString(uploadRequest, "coach_remarks_" + i));
	            coach.setCreateDate(new Date());
	            coach.setModifiedDate(new Date());
	            coach.setDistrict(ParamUtil.getString(uploadRequest, "coachdistrictselect_" + i));
	            coach=CoachLocalServiceUtil.updateCoach(coach);
	            _log.info("coach :::"+i+"::::" +coach);
	            coachList.add(coach);
	        }
	        return coachList;
	    }

	    private void saveSportsPersons(ResourceRequest resourceRequest, UploadPortletRequest uploadRequest, long trainingCentreId) throws PortalException {
	    	_log.info("Entry into saveSportsPersons :::");
	    	
	        int sportsPersonCount = ParamUtil.getInteger(uploadRequest, "sportsPersonCount", 0);
	        String formStatus=ParamUtil.getString(uploadRequest, "formStatus");
	        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	        _log.info("sportsPersonCount" +sportsPersonCount);
	        _log.info("formStatus :::" +formStatus);
	        
	        for (int i = 1; i <= sportsPersonCount; i++) {
	        	long sportsPersonId = ParamUtil.getLong(resourceRequest, "sportsPersonId_" + i);
	        	String fullName = ParamUtil.getString(uploadRequest, "sp_name_" + i);
	            String dateOfBirth = ParamUtil.getString(uploadRequest, "sp_dob_" + i);
	            String mobileNumber = ParamUtil.getString(uploadRequest, "sp_mobile_" + i);
	            String achievementLevel=ParamUtil.getString(uploadRequest, "sp_achievement_" + i);
	            String entryDate =ParamUtil.getString(uploadRequest, "sp_entry_date_" + i);
	            String ranking =ParamUtil.getString(uploadRequest, "sp_ranking_" + i);
	            _log.info("sportsPersonId :::" +sportsPersonId);
	            _log.info("fullName :::" +fullName);
	            _log.info("dateOfBirth :::" +dateOfBirth);
	            _log.info("mobileNumber :::" +mobileNumber);
	            _log.info("achievementLevel :::" +achievementLevel);
	            _log.info("entryDate :::" +entryDate);
	            _log.info("ranking :::" +ranking);
	            
	            if (Validator.isNull(fullName) || Validator.isNull(dateOfBirth) || Validator.isNull(mobileNumber)) {
	                continue; // Skip incomplete entries
	            }
	            SportsPerson sportsPerson=null;
	            
	            if(Validator.isNotNull(sportsPersonId) && sportsPersonId>0) {
	            	try {
						sportsPerson=SportsPersonLocalServiceUtil.getSportsPerson(sportsPersonId);
					} catch (Exception e) {
						_log.error("Error in code ::" +e);
					}
	            }else {
	            	 sportsPerson = SportsPersonLocalServiceUtil.createSportsPerson(CounterLocalServiceUtil.increment(SportsPerson.class.getName()));
	            }
	            
	            sportsPerson.setTrainingCentreId(trainingCentreId);
	            sportsPerson.setFullName(fullName);
	            sportsPerson.setDateOfBirth(ParamUtil.getDate(uploadRequest, "sp_dob_" + i, sf));
	            sportsPerson.setSchoolName(ParamUtil.getString(uploadRequest, "sp_school_" + i));
	            sportsPerson.setMobileNumber(mobileNumber);
	            sportsPerson.setAddress(ParamUtil.getString(uploadRequest, "sp_address_" + i));
	            sportsPerson.setEntryDate(ParamUtil.getDate(uploadRequest, "sp_entry_date_" + i, sf));
	            sportsPerson.setRanking(ParamUtil.getString(uploadRequest, "sp_ranking_" + i));
	            sportsPerson.setAchievementLevel(ParamUtil.getString(uploadRequest, "sp_achievement_" + i));
	            sportsPerson.setRemarks(ParamUtil.getString(uploadRequest, "sp_remarks_" + i));
	            sportsPerson.setCreateDate(new Date());
	            sportsPerson.setModifiedDate(new Date());
	            sportsPerson.setFormStatus(formStatus);
	            SportsPersonLocalServiceUtil.updateSportsPerson(sportsPerson);
	        }
	    }

	    private void saveFinancialDetails(ResourceRequest resourceRequest,UploadPortletRequest uploadRequest, long trainingCentreId) throws PortalException {
	    	double coachAmount=50000;
			double finalCoachAmount=0;
			
			double sportsPersonAmount=1500;
			double finalSportsPersonAmount=0;
			
			double servanAmount=6000;
			double finalServantAmount=0;
			
			double honorariumAmount=2*10000;
			double finalHonorariumAmount=2*10000;
			
			double snaksAmount=(10*50*2);
			double finalSnaksAmount=0;
	    	
	    	
			List<Coach> coachList = null;
			try {
				coachList=CoachLocalServiceUtil.findByTrainingCentreId(trainingCentreId);
			} catch (Exception e) {
				_log.error("Error in code ::" +e);
			}
			
			_log.info("coachList ::" +coachList);
			if(Validator.isNotNull(coachList)  && coachList.size()>0) {
				finalCoachAmount=coachList.size() * 50000;
				finalHonorariumAmount=(honorariumAmount*coachList.size());
			}
			
			List<SportsPerson> sportsPersons = null;
			
			try {
				sportsPersons=SportsPersonLocalServiceUtil.findByTrainingCentreId(trainingCentreId);
			} catch (Exception e) {
				_log.error("Error in code ::" +e);
			}
			
			_log.info("sportsPersons ::" +sportsPersons);
			if(Validator.isNotNull(sportsPersons)  && sportsPersons.size()>0) {
				finalSportsPersonAmount=((sportsPersons.size()+ coachList.size())*1500);
				finalSnaksAmount=(snaksAmount*sportsPersons.size());
			}
			
			
			List<Servant> servantList = null;
			try {
				servantList=ServantLocalServiceUtil.getByTrainingCenterId(trainingCentreId);
			} catch (Exception e) {
				_log.error("Error in code ::" +e);
			}
			
			_log.info("servantList ::" +servantList);
			
			if(Validator.isNotNull(servantList) && servantList.size()>0) {
				finalServantAmount=(servantList.size() * 12 * 6000);
			}
	    	
			_log.info("finalCoachAmount ::::::" +finalCoachAmount);
	    	_log.info("finalServantAmount ::::::" +finalServantAmount);
	    	_log.info("snaksAmount ::::::" +snaksAmount);
	    	_log.info("finalSportsPersonAmount ::::::" +finalSportsPersonAmount);
	    	
	    	
	    	
	    	
	    	List<String> descriptions = new ArrayList<>();
	        String formStatus=ParamUtil.getString(resourceRequest, "formStatus");
	        // Add financial item descriptions (as per form)
	        descriptions.add("Sports material and sports training equipment (5 sports Coaches at Rs. 50,000/- each)");
	        descriptions.add("5 sports Coaches will provide track suits, tshirts, shorts, shoes etc. to 50 players each who practice regularly at the center (55 x Rs. 1500/-)");
	        descriptions.add("Honorarium of 3 part time maidan servants (Rs. 6000/- x 12 x 3)");
	        descriptions.add("Sports literature, CDs, Books, periodicals, Monthly etc");
	        descriptions.add("Office/ General expenses");
	        descriptions.add("Honorarium to private trainers, 10 Trainers for 5 sports for 2 months (Rs. 10,000 x 10 x 2)");
	        descriptions.add("Coach's travel Expense");
	        descriptions.add("Playground maintenance and repair");
	        descriptions.add("Seminar/ Comradeship/ Revision class (at least twice a year)");
	        descriptions.add("Counselling");
	        descriptions.add("Preparation of brochures, flex boards, posters, local newspaper/ cable channels for publicity");
	        descriptions.add("Training camp 2 times a year (100 players for 10 days, Rs. 50 per snack)");
	        
	        
	        _log.info("descriptions :::" +descriptions.size());
	        
	        for (int i = 1; i <= descriptions.size(); i++) {
	        	long fdId=ParamUtil.getLong(uploadRequest, "fdId_"+i);
	        	double requestedAmount = ParamUtil.getDouble(uploadRequest, "req_amount_" + i, 0.0);
	            double grAmount = ParamUtil.getDouble(uploadRequest, "gr_amount_" + i, 0.0);
	            
	            _log.info("descriptions value  :::" +descriptions.get(i-1));
	            
	            
	            TrainingCentreFinancialDetail financialDetail=null;
	            if(Validator.isNotNull(fdId) && fdId>0) {
	            	try {
						financialDetail=TrainingCentreFinancialDetailLocalServiceUtil.getTrainingCentreFinancialDetail(fdId);
					} catch (Exception e) {
						_log.error("Error in code ::" +e);
					}
	            }else {
	            	financialDetail=TrainingCentreFinancialDetailLocalServiceUtil.createTrainingCentreFinancialDetail(CounterLocalServiceUtil.increment(TrainingCentreFinancialDetail.class.getName()));
	            }
	            try {
	            	financialDetail.setTrainingCentreId(trainingCentreId);
	 	            financialDetail.setDescription(descriptions.get(i - 1));
	 	            financialDetail.setGrAmount(grAmount);
	 	            
	 	            
	 	            if(descriptions.get(i-1).equalsIgnoreCase("Sports material and sports training equipment (5 sports Coaches at Rs. 50,000/- each)")) {
	 	            	if(Validator.isNotNull(requestedAmount) && requestedAmount>0) {
	 	            		financialDetail.setRequestedAmount(requestedAmount);
	 	            	}else {
	 	            		financialDetail.setRequestedAmount(finalCoachAmount);
	 	            	}
	 	            }else if(descriptions.get(i-1).equalsIgnoreCase("5 sports Coaches will provide track suits, tshirts, shorts, shoes etc. to 50 players each who practice regularly at the center (55 x Rs. 1500/-)")) {
	 	            	if(Validator.isNotNull(requestedAmount) && requestedAmount>0) {
	 	            		financialDetail.setRequestedAmount(requestedAmount);
	 	            	}else {
	 	            		financialDetail.setRequestedAmount(finalSportsPersonAmount);
	 	            	}
	 	            }else if(descriptions.get(i-1).equalsIgnoreCase("Honorarium of 3 part time maidan servants (Rs. 6000/- x 12 x 3)")) {
	 	            	if(Validator.isNotNull(requestedAmount) && requestedAmount>0) {
	 	            		financialDetail.setRequestedAmount(requestedAmount);
	 	            	}else {
	 	            		financialDetail.setRequestedAmount(finalServantAmount);
	 	            	}
	 	            }else if(descriptions.get(i-1).equalsIgnoreCase("Honorarium to private trainers, 10 Trainers for 5 sports for 2 months (Rs. 10,000 x 10 x 2)")) {
	 	            	if(Validator.isNotNull(requestedAmount) && requestedAmount>0) {
	 	            		financialDetail.setRequestedAmount(requestedAmount);
	 	            	}else {
	 	            		financialDetail.setRequestedAmount(finalHonorariumAmount);
	 	            	}
	 	            }else if(descriptions.get(i-1).equalsIgnoreCase("Training camp 2 times a year (100 players for 10 days, Rs. 50 per snack)")) {
	 	            	if(Validator.isNotNull(requestedAmount) && requestedAmount>0) {
	 	            		financialDetail.setRequestedAmount(requestedAmount);
	 	            	}else {
	 	            		financialDetail.setRequestedAmount(finalSnaksAmount);
	 	            	}
	 	            }else {
	 	            	financialDetail.setRequestedAmount(requestedAmount);
	 	            }
	 	            financialDetail.setCreateDate(new Date());
	 	            financialDetail.setModifiedDate(new Date());
	 	            financialDetail.setFormStatus(formStatus);
	 	            TrainingCentreFinancialDetailLocalServiceUtil.updateTrainingCentreFinancialDetail(financialDetail);
	            }catch (Exception e) {
	            	_log.error("Error in code ::" +e);
				}
	        }
	    }

	    private void saveReports(ResourceRequest resourceRequest,ThemeDisplay themeDisplay, UploadPortletRequest uploadRequest, long trainingCentreId) throws PortalException, IOException {
	    	_log.info("Entry into saveReports ::"); 
	    	String formStatus=ParamUtil.getString(resourceRequest, "formStatus");
	    	
	    	List<String> reportDescriptions = new ArrayList<>();
	        // Add report descriptions (as per form)
	        reportDescriptions.add("1. Detailed report of the training program organised last year...");
	        reportDescriptions.add("a. Sports wise list of participating government/ private/ honorary sports guides who guided in the training program organized");
	        reportDescriptions.add("b. Number of players who participated in the training program organized by district last year");
	        reportDescriptions.add("2. Year wise list of sports materials (durable/ non-durable) and training equipment purchased for the training centre in last 3 years with price and feedback on the current status of the materials.");
	        reportDescriptions.add("a. List of furniture and office supplies purchased in the last 3 years with year wise prices");
	        reportDescriptions.add("b. List of sports equipment and magazines etc. purchased in last 3 years with their prices");
	        reportDescriptions.add("c. Details of total income and expenditure for the last 2 years");
	        reportDescriptions.add("d. Project report of the schemes implemented in the training centre in last year (seminars/revision classes/ counselling/ two sports training camps in a year)");
	        reportDescriptions.add("3. List of new sports equipment (durable/non durable) and training equipment required by sport to be purchased for district sports training centre for this year with price");
	        reportDescriptions.add("4. List of new furniture and office supplies etc. to be purchased for this year with their cost (office/general expenses)");
	        reportDescriptions.add("5. List of new sports equipment, periodicals, etc. to be purchased for this year along with their prices");
	        reportDescriptions.add("6. Details of the space required to safely store available sports material and training equipments");
	        reportDescriptions.add("7. Feedback from district sports officer regarding the proper utilization of the available sports equipment and facilities for training");
	        reportDescriptions.add("8. Feedback on the need for new materials, taking into account the currently available sports material, training equipment, sports literature, magzines, and fumiture");
	        reportDescriptions.add("9. Action taken in the current year to promote the training centre by preparing brochures, flex boards. posters, local newspapers/cable channels");
	        reportDescriptions.add("10. Project report of the plans to be implemented in the training centre in the current year (seminar/ symposium/revision class/ counselling/ teo sports camps in the year)");
	        reportDescriptions.add("11. List of players regularly attending the current training center as per Annexure-A attached");
	        reportDescriptions.add("12. Complete personal information of the government State/ Honorary Sports Guides at the training centre during the current year as per Annexure-B");
	        reportDescriptions.add("13. Complete personal information of the Private trainers appointed for training (duration of 12 months) in the current year as per Annexure-c");
	        reportDescriptions.add("14. The Appropriation certificate of the grant given to district sports training ceriter in the previous year should be attached");
	        reportDescriptions.add("15. Audited accounts of the grants spent last year.");
	        reportDescriptions.add("a. Reasons for not fully utilizing the grant given to the district sports traing centre last year");
	        reportDescriptions.add("16. Names and dates of the appointments of groundskeepers appointed in 17 district sports training centres (if any, certified copy of appointment order)");
	        reportDescriptions.add("17. General Remarks");
	        reportDescriptions.add("18. Photos of flexes, name boards, brochures, and boards with information about the district and training centre's accomplished athletes, training centre room, materials, and office supply");
	        reportDescriptions.add("19. Your office's bank account number, branch code number, MICR and IFSC code number etc, detailed information should be given separately along with the proposal");
	        reportDescriptions.add("20. The training centre should ensure that there is a need for new materials by considering the sports materials, training equipments, sports literature, magazines, furniture available in the training centre.");
	        reportDescriptions.add("21. Others");
	        
	        // Add other descriptions...
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadRequest);
	      
	        for (int i = 1; i <=reportDescriptions.size(); i++) {
	        	try {
	            String remarks = ParamUtil.getString(uploadRequest, "remarks_" + i);
	            String attachmentPath = "";
	            File attachment = uploadRequest.getFile("attachment_" + i);
	            long attachmentPathId=ParamUtil.getLong(resourceRequest,"attachmentPathId_"+i);
	            
	            long reportAtcId = ParamUtil.getLong(uploadRequest, "reportAtcId_"+i);
	            TrainingCentreReport report =null;
	            
	            _log.info("attachmentPathId :::"+"  i value is :::"+i  +"::"+attachmentPathId);
	            _log.info("reportAtcId :::"+"  i value is :::"+i  +"::"+reportAtcId);
	          
	            
	            long fileEntryId = 0l;
	            if (attachment != null && attachment.exists()) {
	            	fileEntryId = fileUploadUtil.uploadFile(uploadRequest, "attachment_" + i, "SPORTS_COACHING_WING", serviceContext);
	            }
	            
	            if(Validator.isNotNull(reportAtcId) && reportAtcId>0) {
	            	try {
						report=	TrainingCentreReportLocalServiceUtil.getTrainingCentreReport(reportAtcId);
					} catch (Exception e) {
						_log.error("Error in code :::" +e);
					}
	            }else {
	            	 report = TrainingCentreReportLocalServiceUtil.createTrainingCentreReport(CounterLocalServiceUtil.increment(TrainingCentreReport.class.getName()));
	            }
	           // _log.debug("report ::::" +report);
	            report.setTrainingCentreId(trainingCentreId);
	            report.setDescription(reportDescriptions.get(i-1));
	            report.setRemarks(remarks);
	            
	            if(Validator.isNotNull(attachmentPathId) && attachmentPathId>0) {
	            	report.setAttachmentPath(String.valueOf(attachmentPathId));
	            }else {
	            	report.setAttachmentPath(Validator.isNotNull(fileEntryId)&& fileEntryId>0 ? String.valueOf(fileEntryId):String.valueOf(0));
	            }
	            report.setCreateDate(new Date());
	            report.setModifiedDate(new Date());
	            report.setFormStatus(formStatus);	
	            TrainingCentreReportLocalServiceUtil.updateTrainingCentreReport(report);
	            
	        	}catch(Exception e) {
	        		_log.error("Error in code :::" +e);
	        	}
	        }
	        _log.info("Exit from saveReports::::");
	    }
	    
	    //File Upload
		public  long multipleFileUpload(ThemeDisplay themeDisplay, UploadPortletRequest uploadPortletRequest, String fileParamName,
				String folderName, ServiceContext serviceContext, String fileName, File file) {
			try {
				_log.info("fileName:" + fileName);
				fileName = HtmlUtil.stripHtml(fileName);
				fileName = HtmlParserUtil.extractText(fileName);
				_log.info(fileName);
				_log.info("File ::  " + file);
				Date date = new Date();
				if (Validator.isNotNull(file) && Validator.isNotNull(fileName)) {
					String description = fileName;
					fileName = date.getTime() + "_" + fileName;
					String mimeType = uploadPortletRequest.getContentType(fileParamName);
					String title = fileName;
					if (validatePDFFileType(fileName, mimeType) || validateImageFileType(fileName, mimeType)) {
						_log.debug("file" + file + "  fileName " + fileName);
						long repositoryId = themeDisplay.getScopeGroupId();
						_log.info("repositoryid: " + repositoryId);
						try {
							Folder folder =   fileUploadUtil.getFolder(themeDisplay.getScopeGroupId(),"SPORTS_COACHING_WING");
							if (Validator.isNotNull(folder)) {
								Role adminRole = RoleLocalServiceUtil.getRole(CompanyThreadLocal.getCompanyId(),
										RoleConstants.ADMINISTRATOR);
								List<User> adminUsers = UserLocalServiceUtil.getRoleUsers(adminRole.getRoleId());
								long userId = 20122;
								if (adminUsers != null && !adminUsers.isEmpty()) {
									userId = adminUsers.get(0).getUserId();
								}
								User user1 = UserLocalServiceUtil.getUser(userId);
								PermissionChecker checker = PermissionCheckerFactoryUtil.create(user1);
								PermissionThreadLocal.setPermissionChecker(checker);
								_log.info("folder:" + folder.getFolderId());
								FileEntry fileEntry = DLAppServiceUtil.addFileEntry(repositoryId, folder.getFolderId(),
										fileName, mimeType, title, description, StringPool.BLANK, file, serviceContext);
								_log.info("fileEntry.getFileEntryId() :" + fileEntry.getFileEntryId());
								return fileEntry.getFileEntryId();
							}
						} catch (Exception e) {
							_log.error(
									"Exception in uploading file in FileUpload :: uploadClaimFile ::" + e.getMessage());
							_log.error(e);
						}
					}
				}
			} catch (Exception e) {
				_log.error(e.getMessage(), e);
			}
			return 0;
		}

		public void setFilePermissionByRoleName(long fileEntryId, ServiceContext serviceContext, ThemeDisplay themeDisplay,
				String roleName) {
			try {
				Role role = RoleLocalServiceUtil.fetchRole(themeDisplay.getCompanyId(), roleName);
				ResourcePermissionLocalServiceUtil.setResourcePermissions(serviceContext.getCompanyId(),
						DLFileEntry.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(fileEntryId),
						role.getRoleId(), new String[] { ActionKeys.VIEW, ActionKeys.DOWNLOAD, ActionKeys.ADD_DOCUMENT,
								ActionKeys.ADD_FILE, ActionKeys.ADD_ATTACHMENT });
			} catch (PortalException e) {
				_log.error(e);
			}
		}
	    
		
		public static boolean validatePDFFileType(String fileName, String mimeType) {
			List<String> validMime = new ArrayList<String>();
			validMime.add("application/pdf");
			List<String> validExt = new ArrayList<String>();
			validExt.add("pdf");
			boolean isValidMime = validMime.contains(mimeType);
			String extension = StringPool.BLANK;
			int i = fileName.lastIndexOf('.');
			if (i > 0) {
				extension = fileName.substring(i + 1);
			}
			boolean isValidExt = validExt.contains(extension);
			_log.debug("isValidMime " + isValidMime + "isValidExt" + isValidExt);
			return isValidMime && isValidExt;
		}
	    
		
		/**
		 * Validate Image file
		 */
		public static boolean validateImageFileType(String fileName, String mimeType) {
			List<String> validMime = new ArrayList<String>();
			validMime.add("image/jpeg");
			validMime.add("image/png");
			List<String> validExt = new ArrayList<String>();
			validExt.add("jpg");
			validExt.add("jpeg");
			validExt.add("png");
			boolean isValidMime = validMime.contains(mimeType);
			String extension = StringPool.BLANK;
			int i = fileName.lastIndexOf('.');
			if (i > 0) {
				extension = fileName.substring(i + 1);
			}
			boolean isValidExt = validExt.contains(extension);
			return isValidMime && isValidExt;
		}
	    
	    @Reference
	    FileUploadUtil fileUploadUtil;
	
	    @Reference
		RoleUtil roleUtil;
}