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
import com.mhdsys.schema.model.CoachingWingMonthlyReport;
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
import com.mhdsys.schema.service.CoachingWingMonthlyReportLocalServiceUtil;
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
import java.util.Arrays;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;
import mhdsys.sports.coaching.wing.dto.CoachDTO;
import mhdsys.sports.coaching.wing.dto.CoachingWingFinancialDetailsReportsDTO;
import mhdsys.sports.coaching.wing.dto.CoachingWingMonthlyReportDTO;
import mhdsys.sports.coaching.wing.dto.FinancialDetailsDTO;
import mhdsys.sports.coaching.wing.dto.ReportsAttachmentDTO;
import mhdsys.sports.coaching.wing.dto.ServantDTO;
import mhdsys.sports.coaching.wing.dto.SportsPersonDTO;
import mhdsys.sports.coaching.wing.dto.TrainingCenterDto;
import mhdsys.sports.coaching.wing.util.SportsCoachingWingUtil;

@Component(immediate = true, 
property = {
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name="+SportsCoachinWingMvcCommand.VIEW_FINANCIAL_REPORT
		},
service = MVCRenderCommand.class)
public class ViewFinancialReportMvcRenderCommand implements MVCRenderCommand {
	
	public static final Log _log=LogFactoryUtil.getLog(ViewFinancialReportMvcRenderCommand.class.getName());
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.info("ViewFinancialReportMvcRenderCommand  render ::");
		 String redirectPage="/coachingwing-financial-report-view.jsp";
		 ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		 
		 boolean isDSO=roleUtil.hasDSORole(themeDisplay.getUser());
		 boolean isdistdeputydirector=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.DDD,themeDisplay.getCompanyId());
		 boolean ishoadmin=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.HO_ADMIN,themeDisplay.getCompanyId());
		 boolean isCoach=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.COACH,themeDisplay.getCompanyId());
		 String centerType= ParamUtil.getString(renderRequest, "centerType");
		 String mode= ParamUtil.getString(renderRequest, "mode");
		 long coachingWingMonthlyReportId= ParamUtil.getLong(renderRequest, "coachingWingMonthlyReportId");
		 long trainingCentreId= ParamUtil.getLong(renderRequest, "trainingCentreId");
		 
		 
		 _log.info("ViewFinancialReportMvcRenderCommand :: coachingWingMonthlyReportId :::"+coachingWingMonthlyReportId);
		 _log.info("mode :::" +mode);
		 _log.info("centerType :::" +centerType);
		 _log.info("trainingCentreId :::" +trainingCentreId);
		 
		 
		 List<DistrictMaster> districtList = DistrictMasterLocalServiceUtil.getByActiveState(Boolean.TRUE);
			renderRequest.setAttribute("districts", districtList);
			//District Sport Talent Development Centre
			CoachingWingFinancialDetailsReportsDTO type1Report = getTrainingCenterFinancialReportByTrainingCenterType(centerType,trainingCentreId);
			
			
			
			
			_log.info("type1Report :::" +type1Report);
			//_log.info("districts :::" +districtList);
			renderRequest.setAttribute("centerType",centerType);
			 renderRequest.setAttribute("talentDev",type1Report);
			 renderRequest.setAttribute("districts",districtList);
		 
		 
		 
			/* renderRequest.setAttribute("servantDTOs",servantDTOs);
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
		 */
		 
		if(isCoach){
			_log.info("inside view report ni coach ::");
			 CoachingWingMonthlyReport report=null;
			 try {
				 report = CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReport(coachingWingMonthlyReportId);
			} catch (Exception e) {
				_log.error("Error in code ::" +e.getMessage());
			}
			 CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
			if(Validator.isNotNull(report)) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				_log.info("report.getTimestamp() ::"+ report.getTimestamp());
				String format = sdf.format(report.getTimestamp());
				_log.info("format ::"+ format);
				_log.info("report.getTrainingCenterPlace() ::"+ report.getTrainingCenterPlace());
				_log.info("report ::"+ report);
				dto.setReportId(report.getCoachingWingMonthlyReportId());
				dto.setTimestamp(report.getTimestamp());
				dto.setTimestampStr(format);
				dto.setCoachName(report.getCoachName());
				dto.setMonth(report.getReportMonth());
				dto.setDistrict(report.getDistrict());
				dto.setEducationalQualification(report.getEducationalQualification());
				dto.setOtherCourse(report.getOtherCourse());
				dto.setSportsName(report.getSportsName());
				dto.setTrainingCenterPlace(report.getTrainingCenterPlace());
				dto.setNoOfPlayers(report.getNoOfPlayers());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setTrainingProgram(report.getTrainingProgram());
				dto.setIdea(report.getIdea());
				dto.setPlayerAnalysisMethod(report.getPlayerAnalysisMethod());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setPlanningForBestPlayers(report.getPlanningForBestPlayers());
				dto.setAssistanceAndEffort(report.getAssistanceAndEffort());
				dto.setDsoDirUserid(report.getDsoDirUserid());
				dto.setDsoDecision(report.getDsoDecision());
				dto.setDsoRemarks(report.getDsoRemarks());
				
				if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
					dto.setStatus("Pending With DSO");
				}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
					dto.setStatus("Approved By DSO");
				}
			} 
			List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
			
			renderRequest.setAttribute("reportMonths",months);
			renderRequest.setAttribute("mode",mode);
			renderRequest.setAttribute("manthlyReport",dto);
			 renderRequest.setAttribute("isCoach",true);
		}else if(isDSO) {
		
			 renderRequest.setAttribute("mode","view");
			 renderRequest.setAttribute("isDSO",true);
			 CoachingWingMonthlyReport report=null;
			 try {
				 report = CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReport(coachingWingMonthlyReportId);
			} catch (Exception e) {
				_log.error("Error in code ::" +e.getMessage());
			}
			 CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
			if(Validator.isNotNull(report)) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				_log.info("report.getTimestamp() ::"+ report.getTimestamp());
				//2025-08-10 00:00:00.0
				String format = sdf.format(report.getTimestamp());
				
				
				_log.info("format ::"+ format);
				_log.info("report.getTrainingCenterPlace() ::"+ report.getTrainingCenterPlace());
				_log.info("report ::"+ report);
				dto.setReportId(report.getCoachingWingMonthlyReportId());
				dto.setTimestamp(report.getTimestamp());
				dto.setTimestampStr(format);
				dto.setCoachName(report.getCoachName());
				dto.setMonth(report.getReportMonth());
				dto.setDistrict(report.getDistrict());
				dto.setEducationalQualification(report.getEducationalQualification());
				dto.setOtherCourse(report.getOtherCourse());
				dto.setSportsName(report.getSportsName());
				dto.setTrainingCenterPlace(report.getTrainingCenterPlace());
				dto.setNoOfPlayers(report.getNoOfPlayers());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setTrainingProgram(report.getTrainingProgram());
				dto.setIdea(report.getIdea());
				dto.setPlayerAnalysisMethod(report.getPlayerAnalysisMethod());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setPlanningForBestPlayers(report.getPlanningForBestPlayers());
				dto.setAssistanceAndEffort(report.getAssistanceAndEffort());
				dto.setDsoDirUserid(report.getDsoDirUserid());
				dto.setDsoDecision(report.getDsoDecision());
				dto.setDsoRemarks(report.getDsoRemarks());
				
				if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
					dto.setStatus("Pending With DSO");
				}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
					dto.setStatus("Approved By DSO");
				}
			} 
			renderRequest.setAttribute("manthlyReport",dto);
		 }else if(isdistdeputydirector) {
			 renderRequest.setAttribute("mode","view");
			 renderRequest.setAttribute("isDDD",true);
			 CoachingWingMonthlyReport report=null;
			 try {
				 report = CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReport(coachingWingMonthlyReportId);
			} catch (Exception e) {
				_log.error("Error in code ::" +e.getMessage());
			}
			 CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
			if(Validator.isNotNull(report)) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				_log.info("report.getTimestamp() ::"+ report.getTimestamp());
				//2025-08-10 00:00:00.0
				String format = sdf.format(report.getTimestamp());
				
				
				_log.info("format ::"+ format);
				_log.info("report.getTrainingCenterPlace() ::"+ report.getTrainingCenterPlace());
				_log.info("report ::"+ report);
				dto.setReportId(report.getCoachingWingMonthlyReportId());
				dto.setTimestamp(report.getTimestamp());
				dto.setTimestampStr(format);
				dto.setCoachName(report.getCoachName());
				dto.setMonth(report.getReportMonth());
				dto.setDistrict(report.getDistrict());
				dto.setEducationalQualification(report.getEducationalQualification());
				dto.setOtherCourse(report.getOtherCourse());
				dto.setSportsName(report.getSportsName());
				dto.setTrainingCenterPlace(report.getTrainingCenterPlace());
				dto.setNoOfPlayers(report.getNoOfPlayers());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setTrainingProgram(report.getTrainingProgram());
				dto.setIdea(report.getIdea());
				dto.setPlayerAnalysisMethod(report.getPlayerAnalysisMethod());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setPlanningForBestPlayers(report.getPlanningForBestPlayers());
				dto.setAssistanceAndEffort(report.getAssistanceAndEffort());
				dto.setDsoDirUserid(report.getDsoDirUserid());
				dto.setDsoDecision(report.getDsoDecision());
				dto.setDsoRemarks(report.getDsoRemarks());
				
				if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
					dto.setStatus("Pending With DSO");
				}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
					dto.setStatus("Approved By DSO");
				}
			} 
			renderRequest.setAttribute("manthlyReport",dto);
		 }else if(ishoadmin) {
			 renderRequest.setAttribute("mode","view");
			 CoachingWingMonthlyReport report=null;
			 try {
				 report = CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReport(coachingWingMonthlyReportId);
			} catch (Exception e) {
				_log.error("Error in code ::" +e.getMessage());
			}
			 CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
			if(Validator.isNotNull(report)) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				_log.info("report.getTimestamp() ::"+ report.getTimestamp());
				//2025-08-10 00:00:00.0
				String format = sdf.format(report.getTimestamp());
				
				
				_log.info("format ::"+ format);
				_log.info("report.getTrainingCenterPlace() ::"+ report.getTrainingCenterPlace());
				_log.info("report ::"+ report);
				dto.setReportId(report.getCoachingWingMonthlyReportId());
				dto.setTimestamp(report.getTimestamp());
				dto.setTimestampStr(format);
				dto.setCoachName(report.getCoachName());
				dto.setMonth(report.getReportMonth());
				dto.setDistrict(report.getDistrict());
				dto.setEducationalQualification(report.getEducationalQualification());
				dto.setOtherCourse(report.getOtherCourse());
				dto.setSportsName(report.getSportsName());
				dto.setTrainingCenterPlace(report.getTrainingCenterPlace());
				dto.setNoOfPlayers(report.getNoOfPlayers());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setTrainingProgram(report.getTrainingProgram());
				dto.setIdea(report.getIdea());
				dto.setPlayerAnalysisMethod(report.getPlayerAnalysisMethod());
				dto.setPerformanceOfThePlayers(report.getPerformanceOfThePlayers());
				dto.setPlanningForBestPlayers(report.getPlanningForBestPlayers());
				dto.setAssistanceAndEffort(report.getAssistanceAndEffort());
				dto.setDsoDirUserid(report.getDsoDirUserid());
				dto.setDsoDecision(report.getDsoDecision());
				dto.setDsoRemarks(report.getDsoRemarks());
				
				if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
					dto.setStatus("Pending With DSO");
				}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
					dto.setStatus("Approved By DSO");
				}
			} 
			renderRequest.setAttribute("manthlyReport",dto);
			 renderRequest.setAttribute("ishoadmin",true);
		 }
		 renderRequest.setAttribute("coachingWingMonthlyReportId",coachingWingMonthlyReportId);
		_log.info("redirectPage :::" +redirectPage);
		return redirectPage;
	}
	
	
	
	
	/**
	 * @param centerType
	 * @return
	 */
	private CoachingWingFinancialDetailsReportsDTO getTrainingCenterFinancialReportByTrainingCenterType(
			String centerType, long trainingCenterId) {
		List<FinancialDetailsDTO> financialDetailsDTO = new ArrayList<>();
		CoachingWingFinancialDetailsReportsDTO reportDTO = new CoachingWingFinancialDetailsReportsDTO();
		TrainingCentre trainingCentre = null;
		try {
			trainingCentre = TrainingCentreLocalServiceUtil.getTrainingCentre(trainingCenterId);
		} catch (Exception e) {
			_log.error("Error in code::" + e);
		}

		if (Validator.isNotNull(trainingCentre)) {
			List<CoachingWingFinancialDetailsReportsDTO> type1Report = new ArrayList<>();

			reportDTO.setTrainingCenterId(trainingCentre.getTrainingCentreId());

			if (Validator.isNotNull(trainingCentre.getDistrict())) {
				DistrictMaster districtMaster = null;
				try {
					districtMaster = DistrictMasterLocalServiceUtil
							.getDistrictMaster(Long.parseLong(trainingCentre.getDistrict()));
				} catch (Exception e) {
					_log.error("Error in code :::" + e.getMessage());
				}
				if (Validator.isNotNull(districtMaster)) {
					reportDTO.setDistrictname(districtMaster.getDistrictName_en());
				}
			}

			try {
				reportDTO.setDistrictId(Long.parseLong(trainingCentre.getDistrict()));
			} catch (Exception e) {
				_log.error("Error in code ::" + e);
			}

			List<TrainingCentreFinancialDetail> financialDetailsList = null;
			try {
				financialDetailsList = TrainingCentreFinancialDetailLocalServiceUtil
						.findByTrainingCentreId(trainingCenterId);
			} catch (Exception e) {
				_log.error("Error in code ::" + e);
			}
			if (Validator.isNotNull(financialDetailsList)) {
				List<FinancialDetailsDTO> fdDTOList = new ArrayList<>();
				double finalTolal = 0.0;
				for (TrainingCentreFinancialDetail tfd : financialDetailsList) {
					FinancialDetailsDTO fd = new FinancialDetailsDTO();
					fd.setDescription(tfd.getDescription());
					fd.setGrAmount(tfd.getGrAmount());
					fd.setRequestedAmount(tfd.getRequestedAmount());
					fd.setTotal(finalTolal + tfd.getRequestedAmount());
					fdDTOList.add(fd);
					finalTolal = finalTolal + tfd.getRequestedAmount();
				}
				reportDTO.setTotal(finalTolal);
				reportDTO.setFinancialTraingData(fdDTOList);
			}
		}

		return reportDTO;
	}
	
	private List<TrainingCentre> getTrainingCenterList(String formstatus) {
		DynamicQuery dynamicQuery = TrainingCentreLocalServiceUtil.dynamicQuery();
	    dynamicQuery .add(RestrictionsFactoryUtil.eq("formstatus", formstatus));
	    return TrainingCentreLocalServiceUtil.dynamicQuery(dynamicQuery);
	}
	
	

	private void getReportsDTOList(ThemeDisplay themeDisplay, List<ReportsAttachmentDTO> reportsAttachments,
			TrainingCentre trainingCentre) {
		List<TrainingCentreReport> reportsList = TrainingCentreReportLocalServiceUtil.findByTrainingCentreId(trainingCentre.getTrainingCentreId());
		if(Validator.isNotNull(reportsList) && reportsList.size()>0) {
				for(TrainingCentreReport trainingCentreReport :reportsList ) {
					ReportsAttachmentDTO reports=new ReportsAttachmentDTO();
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
								_log.info("File Name : " + fileName);
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
	}

	private void getFinancialDetailsDTOList(List<FinancialDetailsDTO> financialDetailsDtoList,
			TrainingCentre trainingCentre) {
		List<TrainingCentreFinancialDetail> trainingCenterFinalcialDetails = TrainingCentreFinancialDetailLocalServiceUtil.findByTrainingCentreId(trainingCentre.getTrainingCentreId());
		if(Validator.isNotNull(trainingCenterFinalcialDetails) && trainingCenterFinalcialDetails.size()>0) {
			for(TrainingCentreFinancialDetail fDetail:trainingCenterFinalcialDetails) {
				FinancialDetailsDTO financialDetails=new FinancialDetailsDTO();
				financialDetails.setFinancialDetailId(fDetail.getFinancialDetailId());
				financialDetails.setDescription(fDetail.getDescription());
				financialDetails.setTrainingCentreId(fDetail.getTrainingCentreId());
				financialDetails.setGrAmount(fDetail.getGrAmount());
				financialDetails.setRequestedAmount(fDetail.getRequestedAmount());
				financialDetailsDtoList.add(financialDetails);
			}
		}
	}

	private void getServantDetails(List<ServantDTO> servantDTOs, TrainingCentre trainingCentre, SimpleDateFormat sdf) {
		List<Servant> servants = ServantLocalServiceUtil.getByTrainingCenterId(trainingCentre.getTrainingCentreId());
		if(Validator.isNotNull(servants) && servants.size()>0) {
			for(Servant servant :servants){
				ServantDTO  servantdto=new ServantDTO();
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
		
		_log.info("sportsPersons :::" +sportsPersons);
		//2000-12-21
		
		if(Validator.isNotNull(sportsPersons) && sportsPersons.size()>0) {
			for(SportsPerson sportsPerson:sportsPersons) {
				SportsPersonDTO sportsPersonDTO=new SportsPersonDTO();
				sportsPersonDTO.setTrainingCentreId(trainingCentre.getTrainingCentreId());
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

	private void getCoachDTOList(List<CoachDTO> coachDTO, List<Coach> coachs, TrainingCentre trainingCentre) {
		try {
			coachs=CoachLocalServiceUtil.findByTrainingCentreId(trainingCentre.getTrainingCentreId());
		}catch(Exception e) {
			_log.error("Error in code :::" +e);
		}
		_log.info("coachs ::::" +coachs);
		if(Validator.isNotNull(coachs) && coachs.size()>0) {
			for(Coach coach:coachs) {
				CoachDTO c=new CoachDTO();
				c.setTrainingCentreId(coach.getTrainingCentreId());
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
