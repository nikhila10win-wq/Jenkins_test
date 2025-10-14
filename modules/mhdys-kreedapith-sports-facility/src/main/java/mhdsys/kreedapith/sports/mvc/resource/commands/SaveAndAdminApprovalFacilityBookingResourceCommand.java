package mhdsys.kreedapith.sports.mvc.resource.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.sportsFacilityBooking.SportsFacilityBookingCommonApi;
import com.mhdsys.common.api.sportsFacilityMaster.SportsFacilityMasterCommonApi;
import com.mhdsys.common.pojo.SportsFacilityBookingDTO;
import com.mhdsys.common.pojo.SportsFacilityMasterDTO;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.sportsFacilityBookingLocalServiceUtil;

import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.facility.web.util.SportsFacilityCommonUtil;
import mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys;

@Component
(immediate = true, property = { "javax.portlet.name=" + MhdysKreedapithSportsFacilityPortletKeys.MHDYSKREEDAPITHSPORTSFACILITY,
		"mvc.command.name="+ MhdysKreedapithSportsFacilityPortletKeys.SAVE_FACILITYBOOKING_RESOURCECOMMAND}, service = MVCResourceCommand.class)
public class SaveAndAdminApprovalFacilityBookingResourceCommand implements MVCResourceCommand{
	private Log _log = LogFactoryUtil.getLog(SaveAndAdminApprovalFacilityBookingResourceCommand.class);
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		_log.info("SaveFacilityBookingResourceCommand Starts ---------------");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String mode= ParamUtil.getString(resourceRequest, "mode");
		String actionType= ParamUtil.getString(resourceRequest, "actionType");
		
		try {
			
			if(!("view".equalsIgnoreCase(mode)) && !("adminApproval".equalsIgnoreCase(actionType))) {
				SportsFacilityBookingDTO SportsFacilityBookingDTO = _SportsFacilityCommonUtil.setSportsFacilityBookingDTO(resourceRequest,themeDisplay);
				if(Validator.isNotNull(SportsFacilityBookingDTO)) {
					sportsFacilityBooking sportsFacilityBooking = _sportsFacilityBookingCommonApi.saveSportsFacilityBooking(SportsFacilityBookingDTO);
					resourceResponse.getWriter().write("success");
				}else {
					resourceResponse.getWriter().write("failed");
				}
					
			}else if("adminApproval".equalsIgnoreCase(actionType)){
				_log.info("Admin Approval Block ----------- ");
				long facilityBookingId = ParamUtil.getLong(resourceRequest, "facilityBookingId");
				String adminAction = ParamUtil.getString(resourceRequest, "adminAction");
				String remarks = ParamUtil.getString(resourceRequest, "remarks");
				boolean approve_reject = adminAction.equalsIgnoreCase("approved")?true: false;
				long bookingStatus = adminAction.equalsIgnoreCase("approved") ? 1 : 9;
				sportsFacilityBooking sportsFacilityBooking = sportsFacilityBookingLocalServiceUtil.getsportsFacilityBooking(facilityBookingId);
				sportsFacilityBooking.setIsApproved(approve_reject);
				sportsFacilityBooking.setRemarks(remarks);
				sportsFacilityBooking.setActionTakenByUserId(themeDisplay.getUserId());
				sportsFacilityBooking.setBookingStatus(bookingStatus);
				sportsFacilityBooking.setModifiedDate(new Date());
				sportsFacilityBookingLocalServiceUtil.updatesportsFacilityBooking(sportsFacilityBooking);
				_log.info("adminAction: "+adminAction + " approve_reject: "+approve_reject +" remarks: "+remarks );
				resourceResponse.getWriter().write("success");
			}else {
				resourceResponse.getWriter().write("failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		_log.info("SaveFacilityBookingResourceCommand Ends ---------------");
		
		return false;
	}

	@Reference private SportsFacilityBookingCommonApi _sportsFacilityBookingCommonApi;
	@Reference private SportsFacilityCommonUtil _SportsFacilityCommonUtil;
}
