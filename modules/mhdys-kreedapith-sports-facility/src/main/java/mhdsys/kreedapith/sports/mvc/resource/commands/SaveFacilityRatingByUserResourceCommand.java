package mhdsys.kreedapith.sports.mvc.resource.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.facility.rating.FacilityRatingCommonApi;
import com.mhdsys.common.api.sportsFacilityBooking.SportsFacilityBookingCommonApi;
import com.mhdsys.common.pojo.FacilityRatingCommonDTO;
import com.mhdsys.schema.model.FacilityRating;
import com.mhdsys.schema.service.FacilityRatingLocalServiceUtil;

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
		"mvc.command.name="+ MhdysKreedapithSportsFacilityPortletKeys.SAVE_SPORTSFACILITY_RATING_RESOURCECOMMAND}, service = MVCResourceCommand.class)
public class SaveFacilityRatingByUserResourceCommand implements MVCResourceCommand{
	private Log _log = LogFactoryUtil.getLog(SaveFacilityRatingByUserResourceCommand.class);
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		_log.info("SaveFacilityRatingByUserResourceCommand Starts ---------------------------------- ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String actionType = ParamUtil.getString(resourceRequest, "actionType");
		
		try {
			
			if(Validator.isNotNull(actionType) && "adminReply".equalsIgnoreCase(actionType)) {
				long facilityRatingId = ParamUtil.getLong(resourceRequest, "facilityRatingId");
				String replyToReview = ParamUtil.getString(resourceRequest, "replyToReview");
				_log.info("facilityRatingId:: "+facilityRatingId);
				if(Validator.isNotNull(facilityRatingId) && facilityRatingId>0) {
					FacilityRating facilityRating = FacilityRatingLocalServiceUtil.getFacilityRating(facilityRatingId);
					facilityRating.setHoReplyToReview(replyToReview);
					facilityRating.setModifiedDate(new Date());
					FacilityRatingLocalServiceUtil.updateFacilityRating(facilityRating);
					resourceResponse.getWriter().write("success"); 
				}
				
			}else {
				FacilityRatingCommonDTO FacilityRatingDTO = _SportsFacilityCommonUtil.setFacilityRatingDTO(themeDisplay,resourceRequest);
				
				_log.info("FacilityRatingDTO::::::: "+FacilityRatingDTO);
				if(Validator.isNotNull(FacilityRatingDTO)) {
					FacilityRating FacilityRating = _FacilityRatingCommonApi.saveFacilityRating(FacilityRatingDTO);
					_log.info("facility rating Saved:::::: "+FacilityRating);
				}
				if(Validator.isNotNull(FacilityRatingDTO)) {
					resourceResponse.getWriter().write("success"); 
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		_log.info("SaveFacilityRatingByUserResourceCommand Ends ---------------------------------- ");
		
		return false;
	}

	@Reference private FacilityRatingCommonApi _FacilityRatingCommonApi;
	@Reference private SportsFacilityCommonUtil _SportsFacilityCommonUtil;
}
