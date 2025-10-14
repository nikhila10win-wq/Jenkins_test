package mhdsys.sports.coaching.wing.portlet.mvc.action;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.CoachingWingMonthlyReport;
import com.mhdsys.schema.service.CoachingWingMonthlyReportLocalServiceUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;

@Component(
		immediate=true,
	    property = { 
	    		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
	    		"mvc.command.name="+SportsCoachinWingMvcCommand.SUBMIT_TRAINING_CENTER_MONTHLYREPORT
	    }, 
	    service = MVCActionCommand.class
)
public class SubmitMonthlyReportMVCActionCommand  extends BaseMVCActionCommand {
	
	private static final Log _log = LogFactoryUtil.getLog(SubmitMonthlyReportMVCActionCommand.class);
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		_log.info("Entry into SubmitMonthlyReportMVCActionCommand::::");
		 
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		 boolean isDSO=roleUtil.hasDSORole(themeDisplay.getUser());
		 boolean isCoach=roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.COACH,themeDisplay.getCompanyId());
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadRequest);
			
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf2=new SimpleDateFormat();
		long reportId=ParamUtil.getLong(actionRequest, "wingMonthlyreportId");
		Date timestamp=ParamUtil.getDate(actionRequest, "timestamp",formatter);
		String timestampStr=ParamUtil.getString(actionRequest, "timestamp");
		String reportMonth=ParamUtil.getString(actionRequest, "reportMonth");
		String coachName=ParamUtil.getString(actionRequest, "coachName");
		String district=ParamUtil.getString(actionRequest, "district");
		String qualification=ParamUtil.getString(actionRequest, "qualification");
		String otherCourse=ParamUtil.getString(actionRequest, "otherCourse");
		String sportsName=ParamUtil.getString(actionRequest, "sportsName");
		String place=ParamUtil.getString(actionRequest, "place");
		String noOfPlayers=ParamUtil.getString(actionRequest, "noOfPlayers");
		String performance=ParamUtil.getString(actionRequest, "performance");
		String trainingProgram=ParamUtil.getString(actionRequest, "trainingProgram");
		String methods=ParamUtil.getString(actionRequest, "methods");
		String planning=ParamUtil.getString(actionRequest, "planning");
		String idea=ParamUtil.getString(actionRequest, "idea");
		String assistance=ParamUtil.getString(actionRequest, "assistance");
		boolean dsoDecision=ParamUtil.getBoolean(actionRequest, "dsoDecision");
		String dsoRemarks=ParamUtil.getString(actionRequest, "dsoRemarks");
		String mode=ParamUtil.getString(actionRequest, "mode");
		String digitalSignature=ParamUtil.getString(actionRequest, "digitalSignature");
		String status=ParamUtil.getString(actionRequest, "status");
		
		_log.info("status :::" +status);
		_log.info("mode :::" +mode);
		_log.info("dsoDecision :::" +dsoDecision);
		_log.info("dsoRemarks :::" +dsoRemarks);
		_log.info("isDSO :::" +isDSO);
		_log.info("reportId :::" +reportId);
		_log.info("timestampStr :::" +timestampStr);
		_log.info("reportMonth :::" +reportMonth);
		_log.info("coachName :::" +coachName);
		_log.info("district :::" +district);
		_log.info("qualification :::" +qualification);
		_log.info("otherCourse :::" +otherCourse);
		_log.info("sportsName :::" +sportsName);
		_log.info("place :::" +place);
		_log.info("noOfPlayers :::" +noOfPlayers);
		_log.info("performance :::" +reportMonth);
		_log.info("reportMonth :::" +reportMonth);
		
		CoachingWingMonthlyReport report=null;
		if(isDSO) {
				_log.info("dso from view mode and aprrove or reject wing monthly report");
				
				//Upload Digital Signature
				long fileEntryId =0l;
				try {
					File digitalSignatureFile = uploadRequest.getFile("digitalSignature");
					
					if (digitalSignatureFile != null && digitalSignatureFile.exists()) {
						 fileEntryId = fileUploadUtil.uploadFile(uploadRequest, "digitalSignature", "SPORTS_COACHING_WING", serviceContext);
					}
				}catch(Exception e) {
					_log.error("Error in code ::" +e);
				}
				
				_log.info("fileEntryId :::" +fileEntryId);
				
				
				if(Validator.isNotNull(reportId) && reportId>0) {
					try {
						report = CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReport(reportId);
					} catch (Exception e) {
						_log.error("Error in code :::" +e);
					}
					if(Validator.isNotNull(report)) {
						report.setDigitalSignature(fileEntryId);
						report.setDsoDecision(dsoDecision);
						report.setDsoRemarks(dsoRemarks);
						report.setDsoDirUserid(themeDisplay.getUserId());
						if(dsoDecision) {
							report.setStatus(MhdsysSportsCoachingWingPortletKeys.APPROVED_BY_DSO);
						}else {
							report.setStatus(MhdsysSportsCoachingWingPortletKeys.REJECED_BY_DSO);
						}
						CoachingWingMonthlyReportLocalServiceUtil.updateCoachingWingMonthlyReport(report);
					}
					_log.info("report befire save :::" +report);
				}
		}else if(isCoach){
			if(Validator.isNotNull(reportId) && reportId>0) {
				try {
					report = CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReport(reportId);
				} catch (Exception e) {
					_log.error("Error in code :::" +e);
				}
			}else {
				report=CoachingWingMonthlyReportLocalServiceUtil.createCoachingWingMonthlyReport(CounterLocalServiceUtil.increment(CoachingWingMonthlyReport.class.getName()));
			}
			report.setTimestamp(sdf1.parse(timestampStr));
			report.setReportMonth(reportMonth);
			report.setCoachName(coachName);
			report.setDistrict(district);
			report.setEducationalQualification(qualification);
			report.setOtherCourse(otherCourse);
			report.setSportsName(sportsName);
			report.setTrainingCenterPlace(place);
			report.setNoOfPlayers(noOfPlayers);
			report.setPerformanceOfThePlayers(performance);
			report.setTrainingProgram(trainingProgram);
			report.setPlayerAnalysisMethod(methods);
			report.setPlanningForBestPlayers(planning);
			report.setIdea(idea);
			report.setAssistanceAndEffort(assistance);
			report.setUserId(themeDisplay.getUserId());
			if(status.equalsIgnoreCase("add")) {
				report.setReportStatus(MhdsysSportsCoachingWingPortletKeys.STATUS_SUBMIT);
				report.setStatus(MhdsysSportsCoachingWingPortletKeys.PENDING_WITH_DSO);
			}else if(status.equalsIgnoreCase("draft")) {
				report.setReportStatus(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT);
				report.setStatus(MhdsysSportsCoachingWingPortletKeys.STATUS_DRAFT);
			}
			try {
				CoachingWingMonthlyReportLocalServiceUtil.updateCoachingWingMonthlyReport(report);
			} catch (Exception e) {
				_log.error("Error in code :::" +e);
			}
		}
		actionResponse.getRenderParameters().setValue("mvcRenderCommandName",SportsCoachinWingMvcCommand.COACHING_WING_MONTHLY_REPORT_LIST);
		//actionResponse.sendRedirect("/group/guest/sports-coaching-wing");
	}
	    
    @Reference
    FileUploadUtil fileUploadUtil;

    @Reference
	RoleUtil roleUtil;

}

