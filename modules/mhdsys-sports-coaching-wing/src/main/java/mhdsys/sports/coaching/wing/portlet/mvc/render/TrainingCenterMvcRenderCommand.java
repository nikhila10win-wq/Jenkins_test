package mhdsys.sports.coaching.wing.portlet.mvc.render;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.Coach;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.model.Servant;
import com.mhdsys.schema.model.SportsPerson;
import com.mhdsys.schema.model.SportsTypeMaster;
import com.mhdsys.schema.model.TrainingCentre;
import com.mhdsys.schema.model.TrainingCentreFinancialDetail;
import com.mhdsys.schema.model.TrainingCentreReport;
import com.mhdsys.schema.service.CoachLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.ServantLocalServiceUtil;
import com.mhdsys.schema.service.SportsPersonLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreFinancialDetailLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreLocalServiceUtil;
import com.mhdsys.schema.service.TrainingCentreReportLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;
import mhdsys.sports.coaching.wing.dto.CoachDTO;
import mhdsys.sports.coaching.wing.dto.FinancialDetailsDTO;
import mhdsys.sports.coaching.wing.dto.ReportsAttachmentDTO;
import mhdsys.sports.coaching.wing.dto.ServantDTO;
import mhdsys.sports.coaching.wing.dto.SportsPersonDTO;
import mhdsys.sports.coaching.wing.dto.TrainingCenterDto;
import mhdsys.sports.coaching.wing.util.SportsCoachingWingUtil;

@Component(immediate = true, 
property = {
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name="+SportsCoachinWingMvcCommand.TRAINING_CENTER_MVC_RENDER_COMMAND
		},
service = MVCRenderCommand.class)
public class TrainingCenterMvcRenderCommand implements MVCRenderCommand {
	
	public static final Log _log=LogFactoryUtil.getLog(TrainingCenterMvcRenderCommand.class.getName());
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.info("TrainingCenterMvcRenderCommand  render ::");
		 String redirectPage="/coaching_wing_form.jsp";
		 
		 ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		 
		 boolean isDSO=roleUtil.hasDSORole(themeDisplay.getUser());
		 boolean isdistdeputydirector=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.DDD,themeDisplay.getCompanyId());
		 boolean ishoadmin=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.HO_ADMIN,themeDisplay.getCompanyId());
		 
		 long trainingCentreId= ParamUtil.getLong(renderRequest, "trainingCentreId");
		 String mode= ParamUtil.getString(renderRequest, "mode");
		 double totalRequestedAmount=0.0;
		 
		 _log.info("isDSO ::::" +isDSO);
		 _log.info("isdistdeputydirector ::::" +isdistdeputydirector);
		 _log.info("ishoadmin ::::" +ishoadmin);
		 _log.info("mode ::::" +mode);
		 _log.info("trainingCentreId ::::" +trainingCentreId);
		 
		 TrainingCenterDto trainingCenterDTO=new TrainingCenterDto();
		 List<SportsPersonDTO> sportsPersonDTOs =new ArrayList<>();
		 List<CoachDTO> coachDTO=new ArrayList<>();
		 List<FinancialDetailsDTO> financialDetailsDtoList=new ArrayList<>();
		 List<ReportsAttachmentDTO> reportsAttachments=new ArrayList<>();
		 List<ServantDTO> servantDTOs=new ArrayList<>();
		 
		 List<DivisionMaster> divisions = getDivision();
		 List<DistrictMaster> districts = getDistricts();
		 List<Coach> coachs =new ArrayList<>();
		 List<SportsPerson> sportsPersons =new ArrayList<>();
		 
		 List<SportsTypeMaster> sportsTypes =null;
		 //_log.debug("divisions :::" +divisions);
		 TrainingCentre trainingCentre = null;

			try {
				sportsTypes=SportsTypeMasterLocalServiceUtil.getByActiveState(Boolean.TRUE);
			}catch(Exception e) {
				_log.error("Error in code ::" +e);
			}
		//	_log.debug("sportsTypes :::" +sportsTypes);
	  //List Coach Deatails
	  List<SchoolCollegeOfficerRegistration> coachList = SportsCoachingWingUtil.SchoolCollegeOfficersByDesignation(themeDisplay.getCompanyId(),RoleConstant.COACH);
	  List<SchoolCollegeOfficerRegistration> sportsPersonsList = SportsCoachingWingUtil.SchoolCollegeOfficersByDesignation(themeDisplay.getCompanyId(),RoleConstant.SPORTS_PERSON);
		 //Later get the role 
	  	//ishoadmin=true;
		 
		 if(isdistdeputydirector) {
			  mode = "view"; // default
	 			try {
	 				trainingCentre=TrainingCentreLocalServiceUtil.getTrainingCentre(trainingCentreId);
				} catch (Exception e) {
					_log.error("Error in code ::"+e);
				}
	 			_log.info("trainingCentre :::" +trainingCentre);
	 			if(Validator.isNotNull(trainingCentre)) {
	 				
	 				List<Long> coachIds=new ArrayList<>();
	 				
	 				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	 				
	 				//Getting Training CenterDTO
		 			getTrainingCenterDTO(themeDisplay, trainingCenterDTO, trainingCentre);
		 			
	 				//Getting coach details for edit
					getCoachDTOList(coachIds,coachDTO, coachs, trainingCentre);
					
					//Getting sports person details 
					getSportsPersonDTOList(sportsPersonDTOs, sportsPersons, trainingCentre, sdf);
					
					//Get servant
					getServantDetails(servantDTOs, trainingCentre, sdf);
					
					//get financial details 
					totalRequestedAmount=getFinancialDetailsDTOList(financialDetailsDtoList, trainingCentre,totalRequestedAmount);
					 	
					//get reports and attachments
					getReportsDTOList(themeDisplay, reportsAttachments, trainingCentre);
	 			}
	 			 renderRequest.setAttribute("totalRequestedAmount",totalRequestedAmount);
		 		 renderRequest.setAttribute("servantDTOs",servantDTOs);
		 		 renderRequest.setAttribute("financialDetailsList",financialDetailsDtoList);
		 		 renderRequest.setAttribute("reportsAttachments",reportsAttachments);
		 		 renderRequest.setAttribute("sportsPersonDTOs",sportsPersonDTOs);
		 		 renderRequest.setAttribute("coachDTO",coachDTO);
		 		 renderRequest.setAttribute("coachList",coachList);
		 		 renderRequest.setAttribute("sportsPersonsList",sportsPersonsList);
		 		 renderRequest.setAttribute("trainingCenterDTO",trainingCenterDTO);
		 		 renderRequest.setAttribute("sportsTypes",sportsTypes);
		 		 renderRequest.setAttribute("trainingCentre",trainingCentre);
		 		 renderRequest.setAttribute("districts",districts);
		 		 renderRequest.setAttribute("divisions",divisions);
		 		 renderRequest.setAttribute("mode", mode);
				 renderRequest.setAttribute("isdistdeputydirector",true);
				 redirectPage="/coaching_wing_form.jsp";
		 }else if(ishoadmin){
			  mode = "view"; // default
	 			try {
	 				trainingCentre=TrainingCentreLocalServiceUtil.getTrainingCentre(trainingCentreId);
				} catch (Exception e) {
					_log.error("Error in code ::"+e);
				}
	 			_log.info("trainingCentre :::" +trainingCentre);
	 			if(Validator.isNotNull(trainingCentre)) {
	 				
	 				List<Long> coachIds=new ArrayList();
	 				
	 				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	 				
	 				//Getting Training CenterDTO
		 			getTrainingCenterDTO(themeDisplay, trainingCenterDTO, trainingCentre);
		 			
	 				//Getting coach details for edit
					getCoachDTOList(coachIds,coachDTO, coachs, trainingCentre);
					
					//Getting sports person details 
					getSportsPersonDTOList(sportsPersonDTOs, sportsPersons, trainingCentre, sdf);
					
					//Get servant
					getServantDetails(servantDTOs, trainingCentre, sdf);
					
					//get financial details 
					totalRequestedAmount=getFinancialDetailsDTOList(financialDetailsDtoList, trainingCentre,totalRequestedAmount);
					 	
					//get reports and attachments
					getReportsDTOList(themeDisplay, reportsAttachments, trainingCentre);
	 			}
	 			 renderRequest.setAttribute("totalRequestedAmount",totalRequestedAmount);
	 			renderRequest.setAttribute("sportsPersonsList",sportsPersonsList);
		 		 renderRequest.setAttribute("servantDTOs",servantDTOs);
		 		 renderRequest.setAttribute("financialDetailsList",financialDetailsDtoList);
		 		 renderRequest.setAttribute("reportsAttachments",reportsAttachments);
		 		 renderRequest.setAttribute("sportsPersonDTOs",sportsPersonDTOs);
		 		 renderRequest.setAttribute("coachDTO",coachDTO);
		 		 renderRequest.setAttribute("coachList",coachList);
		 		 renderRequest.setAttribute("trainingCenterDTO",trainingCenterDTO);
		 		 renderRequest.setAttribute("sportsTypes",sportsTypes);
		 		 renderRequest.setAttribute("trainingCentre",trainingCentre);
		 		 renderRequest.setAttribute("districts",districts);
		 		 renderRequest.setAttribute("divisions",divisions);
		 		 renderRequest.setAttribute("mode", mode);
				 renderRequest.setAttribute("ishoadmin",true);
				 redirectPage="/coaching_wing_form.jsp";
		 }else if(isDSO) {
			 List<Long> coachIDs=new ArrayList<>();
			 try {
	 				trainingCentre=TrainingCentreLocalServiceUtil.getTrainingCentre(trainingCentreId);
				} catch (Exception e) {
					_log.error("Error in code ::"+e);
				}
			 	//_log.info("trainingCentre :::" +trainingCentre);
	 			if(Validator.isNotNull(trainingCentre)) {
	 				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	 				
	 				//Getting Training CenterDTO
		 			getTrainingCenterDTO(themeDisplay, trainingCenterDTO, trainingCentre);
		 			
	 				//Getting coach details for edit
					getCoachDTOList(coachIDs,coachDTO, coachs, trainingCentre);
					
					//Getting sports person details 
					getSportsPersonDTOList(sportsPersonDTOs, sportsPersons, trainingCentre, sdf);
					
					//Get servant
					getServantDetails(servantDTOs, trainingCentre, sdf);
					
					//get financial details 
					totalRequestedAmount=getFinancialDetailsDTOList(financialDetailsDtoList, trainingCentre,totalRequestedAmount);
					 	
					//get reports and attachments
					getReportsDTOList(themeDisplay, reportsAttachments, trainingCentre);
	 			}
	 			//_log.info("trainingCenterDTO :::" +trainingCenterDTO);
	 			//_log.info("coachList in render :::" +coachList);
	 			//_log.info("totalRequestedAmount:::" +totalRequestedAmount);
	 			
	 		 renderRequest.setAttribute("sportsPersoncount",sportsPersonDTOs.size());
	 		 renderRequest.setAttribute("servantDTOCount",servantDTOs.size());
	 		 renderRequest.setAttribute("totalRequestedAmount",totalRequestedAmount);
	 		 renderRequest.setAttribute("sportsPersonsList",sportsPersonsList);
	 		 renderRequest.setAttribute("coachIDs",coachIDs);
			 renderRequest.setAttribute("servantDTOs",servantDTOs);
	 		 renderRequest.setAttribute("financialDetailsList",financialDetailsDtoList);
	 		 renderRequest.setAttribute("reportsAttachments",reportsAttachments);
	 		 renderRequest.setAttribute("sportsPersonDTOs",sportsPersonDTOs);
	 		 renderRequest.setAttribute("coachDTO",coachDTO);
	 		 renderRequest.setAttribute("coachList",coachList);
	 		 renderRequest.setAttribute("trainingCenterDTO",trainingCenterDTO);
	 		 renderRequest.setAttribute("sportsTypes",sportsTypes);
	 		 renderRequest.setAttribute("trainingCentre",trainingCentre);
	 		 renderRequest.setAttribute("districts",districts);
	 		 renderRequest.setAttribute("divisions",divisions);
	 		 renderRequest.setAttribute("isDSO",true);
			 renderRequest.setAttribute("mode",mode);
			 redirectPage="/coaching_wing_form.jsp";
		 }
		_log.info("redirectPage :::" +redirectPage);
		return redirectPage;
	}
	
	private List<TrainingCentre> getTrainingCenterList(String formstatus) {
		DynamicQuery dynamicQuery = TrainingCentreLocalServiceUtil.dynamicQuery();
	    dynamicQuery .add(RestrictionsFactoryUtil.eq("formstatus", formstatus));
	    return TrainingCentreLocalServiceUtil.dynamicQuery(dynamicQuery);
	}
	
	

	private void getReportsDTOList(ThemeDisplay themeDisplay, List<ReportsAttachmentDTO> reportsAttachments,
			TrainingCentre trainingCentre) {
		List<TrainingCentreReport> reportsList = TrainingCentreReportLocalServiceUtil.findByTrainingCentreId(trainingCentre.getTrainingCentreId());
		//_log.info("reportsList size in reportsList :::: "+reportsList.size());
		if(Validator.isNotNull(reportsList) && reportsList.size()>0) {
				for(TrainingCentreReport trainingCentreReport :reportsList ) {
					ReportsAttachmentDTO reports=new ReportsAttachmentDTO();
					reports.setReportId(trainingCentreReport.getReportId());
					reports.setTrainingCentreId(trainingCentreReport.getTrainingCentreId());
					reports.setDescription(trainingCentreReport.getDescription());
					reports.setRemarks(trainingCentreReport.getRemarks());
					reports.setAttachmentPath(trainingCentreReport.getAttachmentPath());
					if(Validator.isNotNull(trainingCentreReport.getAttachmentPath())){
						long fileEntryId=	Long.parseLong(trainingCentreReport.getAttachmentPath());
						try {
							DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileEntryId);
							if (Validator.isNotNull(fileEntry)) {
								String fileName = fileEntry.getFileName();
								//_log.info("File Name : " + fileName);
								if (fileName.contains("_")) {
									int underscoreIndex = fileName.indexOf('_');
									fileName = fileName.substring(underscoreIndex + 1);
								}
								reports.setAttachmentName(fileName);
								reports.setAttachmenturl(getPreviewURL(fileEntryId, themeDisplay));
							}
							
						} catch (Exception e) {
							_log.error("Error in code:" +e);
						}
					}
					reportsAttachments.add(reports);
				}
		}
		//_log.info("reportsAttachments ::::" +reportsAttachments);
	}

	private double getFinancialDetailsDTOList(List<FinancialDetailsDTO> financialDetailsDtoList,
			TrainingCentre trainingCentre,double totalRequestedAmount) {
		List<TrainingCentreFinancialDetail> trainingCenterFinalcialDetails = TrainingCentreFinancialDetailLocalServiceUtil.findByTrainingCentreId(trainingCentre.getTrainingCentreId());
		if(Validator.isNotNull(trainingCenterFinalcialDetails) && trainingCenterFinalcialDetails.size()>0) {
			for(TrainingCentreFinancialDetail fDetail:trainingCenterFinalcialDetails) {
				FinancialDetailsDTO financialDetails=new FinancialDetailsDTO();
				financialDetails.setFinancialDetailId(fDetail.getFinancialDetailId());
				financialDetails.setDescription(fDetail.getDescription());
				financialDetails.setTrainingCentreId(fDetail.getTrainingCentreId());
				financialDetails.setGrAmount(fDetail.getGrAmount());
				financialDetails.setRequestedAmount(fDetail.getRequestedAmount());
				totalRequestedAmount=totalRequestedAmount+fDetail.getRequestedAmount();
				financialDetailsDtoList.add(financialDetails);
			}
			_log.info("getFinancialDetailsDTOList :::totalRequestedAmount ::: " +totalRequestedAmount);
		}
		return totalRequestedAmount;
	}

	private void getServantDetails(List<ServantDTO> servantDTOs, TrainingCentre trainingCentre, SimpleDateFormat sdf) {
		List<Servant> servants = ServantLocalServiceUtil.getByTrainingCenterId(trainingCentre.getTrainingCentreId());
		if(Validator.isNotNull(servants) && servants.size()>0) {
			for(Servant servant :servants){
				ServantDTO  servantdto=new ServantDTO();
				servantdto.setServantId(servant.getServantId());
				servantdto.setTrainingCentreId(servant.getTrainingCentreId());
				servantdto.setName(servant.getName());
				servantdto.setSurname(servant.getSurname());
				servantdto.setDob(servant.getDob());
				servantdto.setDobstr(sdf.format(servant.getDob()));
				servantdto.setJoiningDate(servant.getJoiningDate());
				servantdto.setDojstr(sdf.format(servant.getJoiningDate()));
				servantdto.setAddress(servant.getAddress());
				servantDTOs.add(servantdto);
			}
		}
	}

	private void getSportsPersonDTOList(List<SportsPersonDTO> sportsPersonDTOs, List<SportsPerson> sportsPersons,
			TrainingCentre trainingCentre, SimpleDateFormat sdf) {
		try {
			sportsPersons=SportsPersonLocalServiceUtil.findByTrainingCentreId(trainingCentre.getTrainingCentreId());
		}catch(Exception e) {
			_log.error("Error in code :::" +e);
		}
		if(Validator.isNotNull(sportsPersons) && sportsPersons.size()>0) {
			for(SportsPerson sportsPerson:sportsPersons) {
				SportsPersonDTO sportsPersonDTO=new SportsPersonDTO();
				sportsPersonDTO.setTrainingCentreId(trainingCentre.getTrainingCentreId());
				sportsPersonDTO.setSportsPersonId(sportsPerson.getSportsPersonId());
				sportsPersonDTO.setFullName(sportsPerson.getFullName());
				sportsPersonDTO.setMobileNumber(sportsPerson.getMobileNumber());
				sportsPersonDTO.setSchoolName(sportsPerson.getSchoolName());
				sportsPersonDTO.setAddress(sportsPerson.getAddress());
				sportsPersonDTO.setDateOfBirthString(sdf.format(sportsPerson.getDateOfBirth()));
				sportsPersonDTO.setEntryDateStr(sdf.format(sportsPerson.getEntryDate()));
				sportsPersonDTO.setRemarks(sportsPerson.getRemarks());
				sportsPersonDTO.setAchievementLevel(sportsPerson.getAchievementLevel());
				sportsPersonDTO.setRanking(sportsPerson.getRanking());
				sportsPersonDTOs.add(sportsPersonDTO);
			}
		}
	}

	private void getCoachDTOList(List<Long> coachIds,List<CoachDTO> coachDTO, List<Coach> coachs, TrainingCentre trainingCentre) {
		try {
			coachs=CoachLocalServiceUtil.findByTrainingCentreId(trainingCentre.getTrainingCentreId());
		}catch(Exception e) {
			_log.error("Error in code :::" +e);
		}
		if(Validator.isNotNull(coachs) && coachs.size()>0) {
			for(Coach coach:coachs) {
				CoachDTO c=new CoachDTO();
				c.setTrainingCentreId(coach.getTrainingCentreId());
				coachIds.add(coach.getCoachId());
				c.setCoachId(coach.getCoachId());
				c.setFullName(coach.getFullName());
				c.setEmail(coach.getEmail());
				c.setAddress(coach.getAddress());
				c.setRemarks(coach.getRemarks());
				c.setSportsName(coach.getSportsName());
				c.setMobileNumber(coach.getMobileNumber());
				c.setTracksuitSize(coach.getTracksuitSize());
				c.setTshirtSize(coach.getTshirtSize());
				c.setShortsSize(coach.getShortsSize());
				c.setShoesSize(coach.getShoesSize());
				c.setCoachType(coach.getCoachType());
				c.setDistrict(coach.getDistrict());
				coachDTO.add(c);
			}
		}
	}

	private void getTrainingCenterDTO(ThemeDisplay themeDisplay, TrainingCenterDto trainingCenterDTO,
			TrainingCentre trainingCentre) {
		
		trainingCenterDTO.setTrainingCentreId(trainingCentre.getTrainingCentreId());
		trainingCenterDTO.setDivision(trainingCentre.getDivision());
		
		DistrictMaster districtMaster=null;
		try {
			districtMaster = DistrictMasterLocalServiceUtil.getDistrictMaster(Long.parseLong(trainingCentre.getDistrict()));
		}catch(Exception e){
			_log.error("Error in code ::"+e);
		}
		trainingCenterDTO.setDistrict(trainingCentre.getDistrict());
		trainingCenterDTO.setDistrictName(Validator.isNotNull(districtMaster) ?districtMaster.getDistrictName_en():trainingCentre.getDistrict());
		
		trainingCenterDTO.setSportsType(trainingCentre.getSportsType());
		trainingCenterDTO.setCentreType(trainingCentre.getCentreType());
		trainingCenterDTO.setDprDocPath(trainingCentre.getDprDocPath());
		trainingCenterDTO.setGeoTag(trainingCentre.getGeoTag());
		trainingCenterDTO.setLatitude(trainingCentre.getLatitude());
		trainingCenterDTO.setLongitude(trainingCentre.getLongitude());
		trainingCenterDTO.setDivdepDirDecision(trainingCentre.getDivdepDirDecision());
		trainingCenterDTO.setDivdepDirRemarks(trainingCentre.getDivdepDirRemarks());
		trainingCenterDTO.setDivdepDirUserid(trainingCentre.getDivdepDirUserid());
		trainingCenterDTO.setHoDecision(trainingCentre.getHoDecision());
		trainingCenterDTO.setHoRemarks(trainingCentre.getHoRemarks());
		trainingCenterDTO.setHoDirUserid(trainingCentre.getHoDirUserid());
		trainingCenterDTO.setStatus(trainingCentre.getStatus());
		
		if(Validator.isNotNull(trainingCentre.getStatus()) && trainingCentre.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DD)) {
			trainingCenterDTO.setStatusValue("Pending At Divisional  Deputy Director");
		}else if(Validator.isNotNull(trainingCentre.getStatus()) && trainingCentre.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_HO)) {
			trainingCenterDTO.setStatusValue("Pending At HO");
		}else if(Validator.isNotNull(trainingCentre.getStatus()) && trainingCentre.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_HO)) {
			trainingCenterDTO.setStatusValue("Approved By HO");
		}else if(Validator.isNotNull(trainingCentre.getStatus()) && trainingCentre.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DD)) {
			trainingCenterDTO.setStatusValue("Approved By Divisional  Deputy Director");
		}else if(Validator.isNotNull(trainingCentre.getStatus()) && trainingCentre.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.REJECED_BY_DD)) {
			trainingCenterDTO.setStatusValue("Rejected By Divisional  Deputy Director");
		}else if(Validator.isNotNull(trainingCentre.getStatus()) && trainingCentre.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.REJECED_BY_HO)) {
			trainingCenterDTO.setStatusValue("Rejected By HO");
		}else if(trainingCentre.getFormstatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT)) {
			trainingCenterDTO.setStatusValue(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT);
		}
		// dpr document
		if (Validator.isNotNull(trainingCenterDTO.getDprDocPath())) {
			DLFileEntry recieptFileEntry=null;
			try {
				recieptFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(Long.parseLong(trainingCenterDTO.getDprDocPath()));
			} catch (Exception e) {
				_log.error("Error in code ::"+e);
			}
			if (Validator.isNotNull(recieptFileEntry)) {
				String fileName = recieptFileEntry.getFileName();
				if (fileName.contains("_")) {
					int underscoreIndex = fileName.indexOf('_');
					String trimmedFilename = fileName.substring(underscoreIndex + 1);
					trainingCenterDTO.setDprDocName(trimmedFilename);
				}
				try {
					trainingCenterDTO.setDprDocurl(getPreviewURL(Long.parseLong(trainingCenterDTO.getDprDocPath()), themeDisplay));
				} catch (Exception e) {
					_log.error("Error in code ::"+e);
				} 
			}
		}
		// geo tag photos
		if (Validator.isNotNull(trainingCenterDTO.getGeoTag()) && trainingCenterDTO.getGeoTag().length()>0) {
			String getGeoTagPhotosStr = trainingCenterDTO.getGeoTag();
			getGeoTagPhotosStr = getGeoTagPhotosStr.replaceAll("[\\[\\] ]", "");
			String[] getGeoTagPhotosIds = getGeoTagPhotosStr.split(",");

			List<String> geoTagPhotosURLs = new ArrayList<>();
			List<String> geoTagPhotosNames = new ArrayList<>();
			List<String> geoTagPhotosEntryIds = new ArrayList<>(); // List for file entry IDs as Strings
			if(Validator.isNotNull(getGeoTagPhotosIds) && getGeoTagPhotosIds.length>0) {
				for (String getGeoTagPhotosIdStr : getGeoTagPhotosIds) {
					try {
						long getGeoTagPhotoId = Long.parseLong(getGeoTagPhotosIdStr.trim());
						_log.info("Geo Tag Photo Id ::: " + getGeoTagPhotoId);
						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(getGeoTagPhotoId);

						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							_log.info("File Name : " + fileName);
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								fileName = fileName.substring(underscoreIndex + 1);
							}
							geoTagPhotosNames.add(fileName);
							geoTagPhotosURLs.add(getPreviewURL(getGeoTagPhotoId, themeDisplay));
							geoTagPhotosEntryIds.add(String.valueOf(getGeoTagPhotoId)); // Add ID as String
							_log.info("URL : " + geoTagPhotosURLs.size());
						}
					} catch (Exception e) {
						_log.error("Error processing Photo ID: " + getGeoTagPhotosIdStr, e);
					}
				}
		}
			trainingCenterDTO.setGeoTagPhotosNames(geoTagPhotosNames);
			trainingCenterDTO.setGeoTagPhotosURLs(geoTagPhotosURLs);
			trainingCenterDTO.setGeoTagPhotosIds(geoTagPhotosEntryIds);
		}
	}

	private List<DistrictMaster> getDistricts() {
		List<DistrictMaster> districts = null;
		 try {
			districts=DistrictMasterLocalServiceUtil.getByActiveState(Boolean.TRUE);
		} catch (Exception e) {
			_log.error("Error in code ::"+e);
		}
		 return districts;
	}

	private List<DivisionMaster> getDivision() {
		List<DivisionMaster> divisions = null;
		 try {
			 divisions=DivisionMasterLocalServiceUtil.getByActiveState(Boolean.TRUE);
			} catch (Exception e) {
				_log.error("Error in code ::" +e);
			}
		return divisions;
	} 
	
	/**
	 * 
	 * @param fileEntryId
	 * @param themeDisplay
	 * @return
	 * @throws PortalException
	 */
	public String getPreviewURL(long fileEntryId, ThemeDisplay themeDisplay) throws PortalException {
		FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
		FileVersion fileVersion = fileEntry.getLatestFileVersion();
		return DLURLHelperUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, StringPool.BLANK);
	}
	
	@Reference
	RoleUtil roleUtil;

	@Reference
    FileUploadUtil fileUploadUtil;
}
