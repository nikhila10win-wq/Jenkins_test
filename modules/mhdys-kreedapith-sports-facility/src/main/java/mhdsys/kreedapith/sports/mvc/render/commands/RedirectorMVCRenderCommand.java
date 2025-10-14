package mhdsys.kreedapith.sports.mvc.render.commands;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.FacilityRating;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.FacilityRatingLocalServiceUtil;
import com.mhdsys.schema.service.SportsFacilityMasterLocalServiceUtil;
import com.mhdsys.schema.service.sportsFacilityBookingLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.facility.web.util.SportsFacilityCommonUtil;
import mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + MhdysKreedapithSportsFacilityPortletKeys.MHDYSKREEDAPITHSPORTSFACILITY,
	        "mvc.command.name=" + MhdysKreedapithSportsFacilityPortletKeys.REDIRECT
	    },
	    service = MVCRenderCommand.class
	)
public class RedirectorMVCRenderCommand implements MVCRenderCommand{
	private Log _log=LogFactoryUtil.getLog(RedirectorMVCRenderCommand.class);
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.info("*********************** RedirectorMVCRenderCommand Starts **********************");
		
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			String type = ParamUtil.getString(renderRequest, "type");
			String mode = ParamUtil.getString(renderRequest, "mode");

			_log.info("type: "+ type);
			_log.info("mode In render: "+ mode);
			
//			ROLES
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
		    _log.info("isHOAdmin: "+isHOAdmin +", isDSO: "+isDSO +", isTSO: "+isTSO);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			renderRequest.setAttribute("isDSO", isDSO);
			renderRequest.setAttribute("isTSO", isTSO);
			renderRequest.setAttribute("mode", mode);
			renderRequest.setAttribute("type", type);
			
//			ADD AVAILABILITY FORM HO/DSO ********************************************************************************************************************************
			if(Validator.isNotNull(type) && "initiateForm".equalsIgnoreCase(type) && (isDSO || isTSO)) {
				List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
					    .getSportsFacilityMasters(-1, -1)
					    .stream()
					    .filter(sfm -> sfm.getIsUpdatedByHO() && !sfm.getIsUpdatedByDSO())
					    .sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate()))
					    .collect(Collectors.toList());
				renderRequest.setAttribute("filteredList", filteredList);
				
				return "/facility/add-dso-tso-facility.jsp";
			}
			
			if(Validator.isNotNull(type) && "initiateForm".equalsIgnoreCase(type) && isHOAdmin) {
				return "/facility/add-ho-facility.jsp";
			}
			
//			DSO/TSO FACILITY LIST ********************************************************************************************************************************
			if(Validator.isNotNull(type) && "dsoTsoFacilityList".equalsIgnoreCase(type) && (isDSO || isTSO)) {
				List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
					    .getSportsFacilityMasters(-1, -1)
					    .stream()
					    .filter(sfm -> sfm.getIsUpdatedByDSO())
					    .sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate()))
					    .collect(Collectors.toList());
				renderRequest.setAttribute("filteredList", filteredList);
				
				return "/facility/common-facility-list.jsp";
			}
//			HO FACILITY LIST ********************************************************************************************************************************
			if(Validator.isNotNull(type) && "hoFacilityList".equalsIgnoreCase(type) && isHOAdmin) {
				List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
					    .getSportsFacilityMasters(-1, -1)
					    .stream()
					    .filter(sfm -> sfm.getIsUpdatedByHO())
					    .sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate()))
					    .collect(Collectors.toList());
				renderRequest.setAttribute("filteredList", filteredList);
				
				return "/facility/common-facility-list.jsp";
			}
			
			
//			MODE: VIEW AND EDIT - HO ********************************************************************************************************************************
			if(("edit".equalsIgnoreCase(mode) ||"view".equalsIgnoreCase(mode))  && isHOAdmin && !(type.equalsIgnoreCase("adminAction") || type.equalsIgnoreCase("facilityBookingView"))) {
				String facilityId = ParamUtil.getString(renderRequest, "facilityId");
				if(Validator.isNotNull(facilityId) && Long.valueOf(facilityId)>0) {
					SportsFacilityMaster sportsFacilityMaster = SportsFacilityMasterLocalServiceUtil.getSportsFacilityMaster(Long.valueOf(facilityId));
					renderRequest.setAttribute("sportsFacilityMaster", sportsFacilityMaster);
				}
				return "/facility/add-ho-facility.jsp";
			}
			
//			MODE: VIEW AND EDIT - DSO/TSO  ********************************************************************************************************************************
			if(("edit".equalsIgnoreCase(mode) ||"view".equalsIgnoreCase(mode)) && (isDSO || isTSO)) {
				String facilityId = ParamUtil.getString(renderRequest, "facilityId");
				if(Validator.isNotNull(facilityId) && Long.valueOf(facilityId)>0) {
					SportsFacilityMaster sportsFacilityMaster = SportsFacilityMasterLocalServiceUtil.getSportsFacilityMaster(Long.valueOf(facilityId));
					
//					Geo Tag Photos
					String geoTagPhotoJson = sportsFacilityMaster.getGeotagPhotos();
					if (Validator.isNotNull(geoTagPhotoJson)) {
					    JSONArray arr = JSONFactoryUtil.createJSONArray(geoTagPhotoJson);

					 for (int i = 0; i < arr.length(); i++) {
					        JSONObject obj = arr.getJSONObject(i);
					        if (obj.has("fileEntryId")) {
					            try {
					                long fileId = GetterUtil.getLong(obj.getString("fileEntryId"));
					                FileEntry fe = DLAppLocalServiceUtil.getFileEntry(fileId);
					                String url = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
					                obj.put("url", url);
					            } catch (Exception e) {
					                obj.put("url", "#");
					            }
					        }
					    }
					 _log.info("geoTagPhotoJsonArray: "+arr.toString());
					 renderRequest.setAttribute("geoTagPhoto", arr.toString());
					 sportsFacilityMaster.setGeotagPhotos("");
					}
					renderRequest.setAttribute("sportsFacilityMaster", sportsFacilityMaster);
				}
				return "/facility/add-dso-tso-facility.jsp";
			}
			
			
			
//			--------------------------- FACILITY  BOOKING -------------------------------------------------------------
			
			renderRequest.setAttribute("isMonthlyBooking", false);
			renderRequest.setAttribute("isRentalBooking", false);
//			MONTHLY FACILITY LIST ********************************
			if(Validator.isNotNull(type) && "monthlyFacility".equalsIgnoreCase(type)) {
//				List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
//					    .getSportsFacilityMasters(-1, -1)
//					    .stream()
//					    .filter(sfm -> sfm.getIsUpdatedByDSO())
//					    .filter(sfm -> sfm.getType() != null && sfm.getType().trim().toLowerCase().contains("monthly pass"))
//					    .peek(sfm -> sfm.setGeotagPhotos("[]")) // <-- clear geotagPhotos
//					    .sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
//					    .collect(Collectors.toList());
				
				List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
				        .getSportsFacilityMasters(-1, -1)
				        .stream()
				        .filter(sfm -> sfm.getIsUpdatedByDSO())
				        .filter(sfm -> sfm.getType() != null && sfm.getType().trim().toLowerCase().contains("monthly pass"))
				        .peek(sfm -> {
				            String json = sfm.getGeotagPhotos();
				            if (Validator.isNotNull(json)) {
				                sfm.setGeotagPhotos(getGeoTagPhotosByJson(json, themeDisplay)); // <-- now sets processed photo JSON
				            }
				        })
				        .sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
				        .collect(Collectors.toList());
				
				
				_log.info("monthlyFacility List ------- "+filteredList.toString());
				renderRequest.setAttribute("filteredList", filteredList);
				renderRequest.setAttribute("isMonthlyBooking", true);
				return "/facility-booking/user-facility-booking.jsp";
			}
			
//			RENTAL FACILITY LIST ********************************
			if(Validator.isNotNull(type) && "rentalFacility".equalsIgnoreCase(type)) {
				List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
						.getSportsFacilityMasters(-1, -1)
						.stream()
						.filter(sfm -> sfm.getIsUpdatedByDSO())
						.filter(sfm -> sfm.getType() != null && sfm.getType().trim().toLowerCase().contains("rent booking"))
						 .peek(sfm -> {
					            String json = sfm.getGeotagPhotos();
					            if (Validator.isNotNull(json)) {
					                sfm.setGeotagPhotos(getGeoTagPhotosByJson(json, themeDisplay)); // <-- now sets processed photo JSON
					            }
					        })
						.sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
						.collect(Collectors.toList());
				
				_log.info("Rental List ------- "+filteredList.toString());
				renderRequest.setAttribute("filteredList", filteredList);
				renderRequest.setAttribute("isRentalBooking", true);
				return "/facility-booking/user-facility-booking.jsp";
			}
			
//			USER BOOKING LIST ******************************** 
			if(Validator.isNotNull(type) && "userBokingList".equalsIgnoreCase(type)) {
				  renderRequest.setAttribute("facilityMaster", _SportsFacilityCommonUtil.getFacilityMaster());
				  List<sportsFacilityBooking> filteredList = sportsFacilityBookingLocalServiceUtil
						    .getByCreatorUserId(themeDisplay.getUserId())
						    .stream()
						    .sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate())) // descending
						    .collect(Collectors.toList());
				_log.info("Booking List ------- "+filteredList.toString());
				renderRequest.setAttribute("filteredList", filteredList);
				renderRequest.setAttribute("isRentalBooking", true);
				return "/facility-booking/facility-booking-list.jsp";
			}
			
//			ADMIN BOOKING LIST ***************************
			if(Validator.isNotNull(type) && "adminBokingList".equalsIgnoreCase(type)) {
				renderRequest.setAttribute("facilityMaster", _SportsFacilityCommonUtil.getFacilityMaster());
				List<sportsFacilityBooking> filteredList = sportsFacilityBookingLocalServiceUtil
						.getsportsFacilityBookings(-1, -1)
						.stream()
						.filter(sfm -> sfm.getIsApproved() || sfm.getBookingStatus() == 0) // Approved and Pending List
						.sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate()))
						.collect(Collectors.toList());
				_log.info("Booking List ------- "+filteredList.toString());
				renderRequest.setAttribute("filteredList", filteredList);
				return "/facility-booking/facility-booking-list.jsp";
			}
			
//			FACILITY BOOKING VIEW - USER AND ADMIN ************************************
			_log.info("");
			if(Validator.isNotNull(type) && ("facilityBookingView".equalsIgnoreCase(type) || "adminAction".equalsIgnoreCase(type))) {
				
				String redirectUrl = "adminAction".equalsIgnoreCase(type) ? "/facility-booking/facility-booking-approval.jsp" : "/facility-booking/user-facility-booking.jsp";
				_log.info("redirectUrl:: "+redirectUrl);
				long facilityBookingId = ParamUtil.getLong(renderRequest, "facilityBookingId");
				_log.info("facilityBookingId in facilityBookingView: "+ facilityBookingId);
				sportsFacilityBooking bookingDetails = null;
				List<SportsFacilityMaster> filteredList = null;
				if(Validator.isNotNull(facilityBookingId) && facilityBookingId>0) {
					bookingDetails = sportsFacilityBookingLocalServiceUtil.getsportsFacilityBooking(facilityBookingId);
					
				    filteredList = SportsFacilityMasterLocalServiceUtil
						    .getSportsFacilityMasters(-1, -1)
						    .stream()
						    .filter(sfm -> sfm.getIsUpdatedByDSO())
						    .peek(sfm -> {
					            String json = sfm.getGeotagPhotos();
					            if (Validator.isNotNull(json)) {
					                sfm.setGeotagPhotos(getGeoTagPhotosByJson(json, themeDisplay)); // <-- now sets processed photo JSON
					            }
					        })
						    .sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
						    .collect(Collectors.toList());
				    
				    long PanalActionDoc = bookingDetails.getMedicalCertificate();
					String PanalActionDocURL="";
					if(Validator.isNotNull(PanalActionDoc) && PanalActionDoc>0) {
						FileEntry fe = DLAppLocalServiceUtil.getFileEntry(PanalActionDoc);
						 PanalActionDocURL = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
					}
					renderRequest.setAttribute("medicalDocUrl", PanalActionDocURL);
				}
				renderRequest.setAttribute("filteredList", filteredList);
				renderRequest.setAttribute("bookingDetails", bookingDetails);
				_log.info("facilityBookingView Details:  "+bookingDetails);
				return redirectUrl;
			}
			
			
//			USER RATING
			if(Validator.isNotNull(type) && "userRating".equalsIgnoreCase(type)) {
				
				List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
					    .getSportsFacilityMasters(-1, -1)
					    .stream()
					    .filter(sfm -> sfm.getIsUpdatedByDSO())
					    .sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
					    .collect(Collectors.toList());

					// Get facilityMasterIds already rated by user
					List<Long> ratedFacilityIds = FacilityRatingLocalServiceUtil
					    .getByBycreatorUserId(themeDisplay.getUserId())
					    .stream()
					    .map(FacilityRating::getFacilityMasterId)
					    .collect(Collectors.toList());

					// Filter out the ones already rated
					List<SportsFacilityMaster> unratedList = filteredList
					    .stream()
					    .filter(sfm -> !ratedFacilityIds.contains(sfm.getSportsFacilityId()))
					    .collect(Collectors.toList());

					renderRequest.setAttribute("filteredList", unratedList);
					
					
//					User Submitted rating List
					renderRequest.setAttribute("facilityMaster", _SportsFacilityCommonUtil.getFacilityMaster());
					List<FacilityRating> userRatingList = FacilityRatingLocalServiceUtil.getByBycreatorUserId(themeDisplay.getUserId())
							.stream()
						    .sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate())) // descending
						    .collect(Collectors.toList());
					renderRequest.setAttribute("userRatingList", userRatingList);
				return "/facility-booking/user-facility-rating.jsp";
			}
			
//			ADMIN RATING REVIEW
			if(Validator.isNotNull(type) && "adminRatingReview".equalsIgnoreCase(type)) {
				renderRequest.setAttribute("facilityMaster", _SportsFacilityCommonUtil.getFacilityMaster());
				List<FacilityRating> allRatings = FacilityRatingLocalServiceUtil.getFacilityRatings(-1, -1)
						.stream()
					    .sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate())) // descending
					    .collect(Collectors.toList());

				List<FacilityRating> facilitRatingList = allRatings.stream()
				    .filter(rating -> {
				        String reply = rating.getHoReplyToReview();
				        return reply == null || reply.trim().isEmpty();
				    })
				    .sorted((a, b) -> b.getModifiedDate().compareTo(a.getModifiedDate())) // descending
				    .collect(Collectors.toList());

				  Map<Long, Integer> ratingIdToSerialNo = new HashMap<>();
				    for (int i = 0; i < allRatings.size(); i++) {
				        ratingIdToSerialNo.put(allRatings.get(i).getFacilityRatingId(), i + 1);
				    }
				    
				renderRequest.setAttribute("facilitRatingList", facilitRatingList);
				renderRequest.setAttribute("userRatingList", allRatings);
				renderRequest.setAttribute("ratingIdToSerialNo", ratingIdToSerialNo);
				
				return "/facility-booking/admin-rating-review.jsp";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		_log.info("*********************** RedirectorMVCRenderCommand Ends **********************");
		return null;
	}
	@Reference private SportsFacilityCommonUtil _SportsFacilityCommonUtil;
	
	public String getGeoTagPhotosByJson(String rawJson, ThemeDisplay themeDisplay) {
	    StringBuilder photoUrls = new StringBuilder();

	    try {
	        JSONArray arr = JSONFactoryUtil.createJSONArray(rawJson);
	        for (int i = 0; i < arr.length(); i++) {
	            JSONObject obj = arr.getJSONObject(i);
	            if (obj.has("fileEntryId")) {
	                try {
	                    long fileId = GetterUtil.getLong(obj.getString("fileEntryId"));
	                    FileEntry fe = DLAppLocalServiceUtil.getFileEntry(fileId);
	                    String url = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");

	                    if (photoUrls.length() > 0) {
	                        photoUrls.append(",");
	                    }
	                    photoUrls.append(url);
	                } catch (Exception e) {
	                    // You can skip or log the error for individual entries
	                }
	            }
	        }
	    } catch (Exception e) {
	        _log.error("Error parsing geotag photo JSON: " + rawJson, e);
	    }

	    return photoUrls.length() > 0 ? photoUrls.toString() : "#";
	}



}
