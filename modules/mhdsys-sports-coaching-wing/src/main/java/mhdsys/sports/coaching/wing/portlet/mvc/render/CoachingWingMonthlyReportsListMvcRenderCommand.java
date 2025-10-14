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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.Coach;
import com.mhdsys.schema.model.CoachingWingMonthlyReport;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.Servant;
import com.mhdsys.schema.model.SportsPerson;
import com.mhdsys.schema.model.TrainingCentre;
import com.mhdsys.schema.model.TrainingCentreFinancialDetail;
import com.mhdsys.schema.model.TrainingCentreReport;
import com.mhdsys.schema.service.CoachLocalServiceUtil;
import com.mhdsys.schema.service.CoachingWingMonthlyReportLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.ServantLocalServiceUtil;
import com.mhdsys.schema.service.SportsPersonLocalServiceUtil;
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
import mhdsys.sports.coaching.wing.dto.CoachingWingMonthlyReportDTO;
import mhdsys.sports.coaching.wing.dto.FinancialDetailsDTO;
import mhdsys.sports.coaching.wing.dto.ReportsAttachmentDTO;
import mhdsys.sports.coaching.wing.dto.ServantDTO;
import mhdsys.sports.coaching.wing.dto.SportsPersonDTO;
import mhdsys.sports.coaching.wing.dto.TrainingCenterDto;

@Component(immediate = true, 
property = {
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name="+SportsCoachinWingMvcCommand.COACHING_WING_MONTHLY_REPORT_LIST
		},
service = MVCRenderCommand.class)
public class CoachingWingMonthlyReportsListMvcRenderCommand implements MVCRenderCommand {
	
	public static final Log _log=LogFactoryUtil.getLog(CoachingWingMonthlyReportsListMvcRenderCommand.class.getName());
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.info("TrainingCenterMvcRenderCommand  render ::");
		 String redirectPage="/coaching-wing-monthly-report-list.jsp";
		 ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		 
		 boolean isDSO=roleUtil.hasDSORole(themeDisplay.getUser());
		 boolean isdistdeputydirector=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.DDD,themeDisplay.getCompanyId());
		 boolean ishoadmin=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.HO_ADMIN,themeDisplay.getCompanyId());
		 boolean isCoach=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.COACH,themeDisplay.getCompanyId());

			_log.info("Inside here");
			if(isDSO) {// DSO Will approve reports
				/**
				 * 			DSO Will have pending monthly report list 
				 **/
				List<CoachingWingMonthlyReportDTO> reportDTOList=new ArrayList<>();
				List<CoachingWingMonthlyReport> coachingWingMonthlyReportsList =null;
				
				// DSO Will approve reports
				//Get the pending coaching wing reports list 
				_log.info("inside dso rrport list screen will view next");
				getMonthlyReportList(reportDTOList, coachingWingMonthlyReportsList);
				renderRequest.setAttribute("coachingWingMonthlyReportsList",reportDTOList);
				renderRequest.setAttribute("isDSO",true);
				return "/coaching-wing-monthly-report-list.jsp";
				
			}else if(isdistdeputydirector) {// DDD will view and download the reports
					
				// DDD will view and download the reports
				//Get the Approved coaching wing reports list 
				_log.info("inside ddd reports login ::::");
				_log.info("Get the Approved coaching wing reports list ");
				List<CoachingWingMonthlyReport> coachingWingMonthlyReportsList =null;
				List<CoachingWingMonthlyReportDTO> reportDTOList=new ArrayList<>();
				
				try {
					DynamicQuery dq = CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery();
					dq.add(RestrictionsFactoryUtil.eq("status",MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO));
					coachingWingMonthlyReportsList=CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery(dq);
				} catch (Exception e) {
					_log.error("Error in code :::" +e);
				}
				if(Validator.isNotNull(coachingWingMonthlyReportsList) && coachingWingMonthlyReportsList.size()>0) {
					for(CoachingWingMonthlyReport report :coachingWingMonthlyReportsList) {
						CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						_log.info("report.getTimestamp() ::"+ report.getTimestamp());
						//2025-08-10 00:00:00.0
						String format = sdf.format(report.getTimestamp());
						
						_log.info("format ::"+ format);
						_log.info("report.getTrainingCenterPlace() ::"+ report.getTrainingCenterPlace());
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
						if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
							dto.setStatus("Pending With DSO");
						}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
							dto.setStatus("Approved By DSO");
						}
						reportDTOList.add(dto);
					}
				}
				
				renderRequest.setAttribute("coachingWingMonthlyReportsList",reportDTOList);
				renderRequest.setAttribute("isDDD",true);
				return "/coaching-wing-monthly-report-approved-list.jsp";
				
			}else if(ishoadmin) {
				
				_log.info("inside HOD Admin  reports login ::::");
				
				// DDD will view and download the reports
				//Get the Approved coaching wing reports list 
				_log.info("inside ddd reports login ::::");
				_log.info("Get the Approved coaching wing reports list ");
				List<CoachingWingMonthlyReport> coachingWingMonthlyReportsList =null;
				List<CoachingWingMonthlyReportDTO> reportDTOList=new ArrayList<>();
				
				try {
					DynamicQuery dq = CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery();
					dq.add(RestrictionsFactoryUtil.eq("status",MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO));
					coachingWingMonthlyReportsList=CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery(dq);
				} catch (Exception e) {
					_log.error("Error in code :::" +e);
				}
				if(Validator.isNotNull(coachingWingMonthlyReportsList) && coachingWingMonthlyReportsList.size()>0) {
					for(CoachingWingMonthlyReport report :coachingWingMonthlyReportsList) {
						CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						_log.info("report.getTimestamp() ::"+ report.getTimestamp());
						//2025-08-10 00:00:00.0
						String format = sdf.format(report.getTimestamp());
						
						_log.info("format ::"+ format);
						_log.info("report.getTrainingCenterPlace() ::"+ report.getTrainingCenterPlace());
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
						if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
							dto.setStatus("Pending With DSO");
						}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
							dto.setStatus("Approved By DSO");
						}
						reportDTOList.add(dto);
					}
				}
				
				renderRequest.setAttribute("coachingWingMonthlyReportsList",reportDTOList);
				renderRequest.setAttribute("isDDD",true);
				return "/coaching-wing-monthly-report-approved-list.jsp";
				
			}else if(isCoach) {
				List<CoachingWingMonthlyReport> coachingWingMonthlyReportsList =null;
				List<CoachingWingMonthlyReportDTO> reportDTOList=new ArrayList<>();
				DivisionMaster divisionMaster=null;
				DistrictMaster districtMaster=null;
				CoachDTO coachDTO=new CoachDTO();
				Coach coach=null;
				String coachEmail=themeDisplay.getUser().getEmailAddress();
				
				_log.info("inside isCoach monthly  reports login ::::");
				List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
				
				try {
					coach = CoachLocalServiceUtil.findCoachByEmail(coachEmail);
				} catch (Exception e) {
					_log.error("Error in code :::" +e);
				}
				if(Validator.isNotNull(coach)) {
					coachDTO.setCoachId(coach.getCoachId());
					coachDTO.setDistrict(coach.getDistrict());
					coachDTO.setSportsName(coach.getSportsName());
					coachDTO.setFullName(coach.getFullName());
					if(Validator.isNotNull(coach.getCoachId())) {
					
						try {
							districtMaster = DistrictMasterLocalServiceUtil.getDistrictMaster(Long.parseLong(coach.getDistrict()));
						} catch (Exception e) {
							_log.error("Error in code :::" +e);
						} 
						if(Validator.isNotNull(districtMaster)) {
							try {
								divisionMaster = DivisionMasterLocalServiceUtil.getDivisionMaster(districtMaster.getDivisionId());
							} catch (Exception e) {
								_log.error("Error in code ::" +e);
							}
						}
						if(Validator.isNotNull(divisionMaster)) {
							coachDTO.setDivisionName(divisionMaster.getDivisionName_en());
						}
						if(Validator.isNotNull(districtMaster)) {
							coachDTO.setDistrictName(districtMaster.getDistrictName_en());
						}
					}
					renderRequest.setAttribute("coach", coachDTO);
					
					try {
						DynamicQuery dq = CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery();
						dq.add(RestrictionsFactoryUtil.eq("userId",themeDisplay.getUserId()));
						coachingWingMonthlyReportsList=CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery(dq);
					} catch (Exception e) {
						_log.error("Error in code :::" +e);
					}
					
					_log.info("coachingWingMonthlyReportsList in coach :::"+coachingWingMonthlyReportsList);
					
					if(Validator.isNotNull(coachingWingMonthlyReportsList) && coachingWingMonthlyReportsList.size()>0) {
						for(CoachingWingMonthlyReport report :coachingWingMonthlyReportsList) {
							CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							_log.info("report.getTimestamp() ::"+ report.getTimestamp());
							//2025-08-10 00:00:00.0
							String format = sdf.format(report.getTimestamp());
							
							_log.info("format ::"+ format);
							_log.info("report.getTrainingCenterPlace() ::"+ report.getTrainingCenterPlace());
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
							dto.setCurrentstatus(report.getStatus());
							if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
								dto.setStatus("Pending With DSO");
							}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
								dto.setStatus("Approved By DSO");
							}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT)) {
								dto.setStatus(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT);
							}
							reportDTOList.add(dto);
						}
					}
				}
				
				_log.info("reportDTOList for coach :::" +reportDTOList);
				renderRequest.setAttribute("mode", "add");
				renderRequest.setAttribute("isCoach", true);
				renderRequest.setAttribute("reportMonths", months);
				renderRequest.setAttribute("coachingWingMonthlyReportsList",reportDTOList);
				return "/coaching-wing-monthly-report-list.jsp";
			}
			return	redirectPage;
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
	 * @param reportDTOList
	 * @param coachingWingMonthlyReportsList
	 */
	private void getMonthlyReportList(List<CoachingWingMonthlyReportDTO> reportDTOList,
			List<CoachingWingMonthlyReport> coachingWingMonthlyReportsList) {
		List<String> statusList=new ArrayList<>();
		statusList.add(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO);
		statusList.add(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO);
		try {
			DynamicQuery dq = CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery();
			dq.add(RestrictionsFactoryUtil.in("status",statusList));
			coachingWingMonthlyReportsList=CoachingWingMonthlyReportLocalServiceUtil.dynamicQuery(dq);
		} catch (Exception e) {
			_log.error("Error in code :::" +e);
		}
		
		_log.info("coachingWingMonthlyReportsList :::" +coachingWingMonthlyReportsList);
		
		//List<CoachingWingMonthlyReport> coachingWingMonthlyReportsList = CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReports(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
		if(Validator.isNotNull(coachingWingMonthlyReportsList) && coachingWingMonthlyReportsList.size()>0) {
			for(CoachingWingMonthlyReport report :coachingWingMonthlyReportsList) {
				CoachingWingMonthlyReportDTO dto=new CoachingWingMonthlyReportDTO();
				dto.setReportId(report.getCoachingWingMonthlyReportId());
				dto.setCoachName(report.getCoachName());
				dto.setMonth(report.getReportMonth());
				dto.setDistrict(report.getDistrict());
				if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO)) {
					dto.setStatus("Pending With DSO");
				}else if(report.getStatus().equalsIgnoreCase(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO)) {
					dto.setStatus("Approved By DSO");
				}
				reportDTOList.add(dto);
			}
		}
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
