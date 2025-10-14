package mhdsys.construction.tracker.mvc.render.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.AdministrativeApprovalDTO;
import com.mhdsys.common.pojo.ConstructionTrackerCommonDTO;
import com.mhdsys.common.pojo.GovtGrantDTO;
import com.mhdsys.common.pojo.OtherSourceDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.service.ConstructionTrackerLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.TalukaMasterLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.construction.tracker.web.constants.ConstructionTrackerConstants;
import mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys;
import mhdsys.construction.tracker.web.util.ConstructionTrackerUtil;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + MhdsysConstructionTrackerWebPortletKeys.MHDSYSCONSTRUCTIONTRACKERWEB,
	        "mvc.command.name=" + MhdsysConstructionTrackerWebPortletKeys.REDIRECT
	    },
	    service = MVCRenderCommand.class
	)
public class RedirectorMVCRender implements MVCRenderCommand {
	 private Log _log=LogFactoryUtil.getLog(RedirectorMVCRender.class);
	 @Reference ConstructionTrackerUtil constructionTrackerUtil;
	 
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.info("RedirectorMVCRender starts------------------------------");
		
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
//			String type = renderRequest.getRenderParameters().getValue("type");
			String type = renderRequest.getParameter("type");
			String mode = renderRequest.getParameter("mode");
			String constructionTrackerId = renderRequest.getParameter("constructionTrackerId");
			_log.info("type: "+ type);
			_log.info("mode In render: "+ mode);
			_log.info("constructionTrackerId: "+ constructionTrackerId);
			
//			Getting Roles********************************************************************************************************************************
			boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			boolean isDDD = RoleConstant.isDDD(user, themeDisplay.getCompanyId());
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
	        renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			renderRequest.setAttribute("isDDD", isDDD);
			renderRequest.setAttribute("isDSO", isDSO);
			renderRequest.setAttribute("isTSO", isTSO);
			
			_log.info("isHOAdmin: "+isHOAdmin +", isDDD: "+isDDD +", isDSO: "+isDSO +", isTSO: "+isTSO);
//			-------------------------------------------------------------------------------------------
		        
//	       Divisions,Districts,Taluka Map ********************************************************************************************************************************
		        List<DivisionMaster> divisions = DivisionMasterLocalServiceUtil.getByActiveState(true);
		        List<DistrictMaster> districts = DistrictMasterLocalServiceUtil.getByActiveState(true);
		        List<TalukaMaster> talukas = TalukaMasterLocalServiceUtil.getByActiveState(true);
		        ConstructionTrackerUtil _util = new ConstructionTrackerUtil();
		        // Set maps for JSP
		        renderRequest.setAttribute("divisionMap", _util.getDivisionMap(divisions));
		        renderRequest.setAttribute("districtMap", _util.getDistrictMap(districts));
		        renderRequest.setAttribute("talukaMap", _util.getTalukaMap(talukas));
		        
		        renderRequest.setAttribute("divisions", DivisionMasterLocalServiceUtil.getByActiveState(true));
				renderRequest.setAttribute("districts", DistrictMasterLocalServiceUtil.getByActiveState(true));
				renderRequest.setAttribute("talukas", TalukaMasterLocalServiceUtil.getByActiveState(true));
//		        Ends-----------
			
			
//			INITIATE FORM ********************************************************************************************************************************
			if(Validator.isNotNull(type) && "initiateForm".equalsIgnoreCase(type) && (isDSO || isTSO)) {
				return "/main-form.jsp";
			}
			
//			DSO VIEW ********************************************************************************************************************************
			if(Validator.isNotNull(type) && "dsoView".equalsIgnoreCase(type) && (isDSO || isTSO)) {
				ConstructionTracker tracker = ConstructionTrackerLocalServiceUtil.getByIntiatorUserId(themeDisplay.getUserId());
				List<ConstructionTracker> dsoViewList = new ArrayList<>();
			    if (tracker != null) {dsoViewList.add(tracker); }
				renderRequest.setAttribute("List", dsoViewList);
				return ConstructionTrackerConstants.CONSTRUCTION_TRACKER_LIST;
			}
			
//			DDD / HO VIEW LIST ********************************************************************************************************************************
			if(Validator.isNotNull(type) && "dddHoView".equalsIgnoreCase(type) && (isHOAdmin || isDDD)) {
				List<ConstructionTracker> constructionTrackerList=null;
				 if (isDDD) {
			        	constructionTrackerList = getAllconstructionTrackerWithApplicationStatus(0,1,2); // 0 - initiated, 1- approved by DDD, 2- Approved By HO
		        } else if (isHOAdmin) {
		        	constructionTrackerList = getAllconstructionTrackerWithApplicationStatus(1,2,2);
		        }
				 renderRequest.setAttribute("List", constructionTrackerList);
				return ConstructionTrackerConstants.CONSTRUCTION_TRACKER_LIST;
			}

			
//			FOR PREPOPULATION 
			ConstructionTracker constructionTracker=ConstructionTrackerLocalServiceUtil.getConstructionTracker(Long.valueOf(constructionTrackerId));
			ConstructionTrackerCommonDTO constructionTrackerDTO=constructionTrackerUtil.setConstructionTrackerDTO(constructionTracker, themeDisplay);
			
			_log.info("constructionTrackerDTO::: "+ constructionTrackerDTO);
			
//			Geo Tag Photos
			String geoTagPhotoJson = constructionTrackerDTO.getGeoTagPhoto();
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
			    constructionTrackerDTO.setGeoTagPhoto(arr.toString());
			}
			
//			ProgressionDetails Docs Formatting
			String progressJson = constructionTrackerDTO.getProgressDetailsJSON();
			if (Validator.isNotNull(progressJson)) {
			    JSONArray arr = JSONFactoryUtil.createJSONArray(progressJson);
			    for (int i = 0; i < arr.length(); i++) {
			        JSONObject obj = arr.getJSONObject(i);
			        
			        // Handle GIS Photo
			        if (obj.has("GISPhoto") && Validator.isNotNull(obj.get("GISPhoto"))) {
			            try {
			                long fileId = obj.getLong("GISPhoto");
			                FileEntry fe = DLAppLocalServiceUtil.getFileEntry(fileId);
			                String url = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
			                obj.put("gisPhotoUrl", url);
			            } catch (Exception e) {
			                obj.put("gisPhotoUrl", "#");
			            }
			        }

			        // Handle Gantt Chart
			        if (obj.has("addGanttChart") && Validator.isNotNull(obj.get("addGanttChart"))) {
			            try {
			                long fileId = obj.getLong("addGanttChart");
			                FileEntry fe = DLAppLocalServiceUtil.getFileEntry(fileId);
			                String url = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
			                obj.put("ganttChartUrl", url);
			            } catch (Exception e) {
			                obj.put("ganttChartUrl", "#");
			            }
			        }
			    }
			    _log.info("ProgressionDetails:: " + arr);
			    constructionTrackerDTO.setProgressDetailsJSON(arr.toString());
			}

			
//			UCC CC doc Formatting
			long PanalActionDoc = constructionTrackerDTO.getPanalActionDoc();
			String PanalActionDocURL="";
			if(Validator.isNotNull(PanalActionDoc)) {
				FileEntry fe = DLAppLocalServiceUtil.getFileEntry(PanalActionDoc);
				 PanalActionDocURL = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
			}
			
			
			renderRequest.setAttribute("constructionTrackerDTO", constructionTrackerDTO);
			renderRequest.setAttribute("mode", mode);
			renderRequest.setAttribute("panalActionDocURL", PanalActionDocURL);
			
			long dddReviewDoc = constructionTrackerDTO.getDddReviewDoc();
			String dddDocURL="";
			if(Validator.isNotNull(dddReviewDoc)) {
				FileEntry fe = DLAppLocalServiceUtil.getFileEntry(dddReviewDoc);
				dddDocURL = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
			}
			renderRequest.setAttribute("dddDocURL", dddDocURL);
			
			long hoReviewDoc = constructionTrackerDTO.getHoReviewDoc();
			String hoDocURL="";
			if(Validator.isNotNull(hoReviewDoc)) {
				FileEntry fe = DLAppLocalServiceUtil.getFileEntry(hoReviewDoc);
				hoDocURL = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
			}
			renderRequest.setAttribute("hoDocURL", hoDocURL);
		
			  return ConstructionTrackerConstants.CONSTRUCTION_TRACKER; 
			  
//			PREPOPULATION ENDS -------------------------------------------------------------------------------------------------------
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		_log.info("RedirectorMVCRender Ends ------------------------------");
		return null;
	}
	
	private List<ConstructionTracker> getAllconstructionTrackerWithApplicationStatus(long status1, long status2, long status3) {
	    DynamicQuery dynamicQuery = ConstructionTrackerLocalServiceUtil.dynamicQuery();
	    Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
	    disjunction.add(RestrictionsFactoryUtil.eq("applicationStatus", status1));
	    disjunction.add(RestrictionsFactoryUtil.eq("applicationStatus", status2));
	    disjunction.add(RestrictionsFactoryUtil.eq("applicationStatus", status3));
	    dynamicQuery.add(disjunction);
	    dynamicQuery.add(RestrictionsFactoryUtil.eq("isSaveAsDraft", false));
	    dynamicQuery.addOrder(OrderFactoryUtil.desc("createdDate"));
	    return ConstructionTrackerLocalServiceUtil.dynamicQuery(dynamicQuery);
	}
	
//	private List<ConstructionTracker> getAllconstructionTrackerWithApplicationStatus(long status1, long status2) {
//		DynamicQuery dynamicQuery = ConstructionTrackerLocalServiceUtil.dynamicQuery();
//	    dynamicQuery
//	        .add(RestrictionsFactoryUtil.eq("applicationStatus", status1))
//	        .add(RestrictionsFactoryUtil.eq("applicationStatus", status2))
//	        .add(RestrictionsFactoryUtil.eq("isSaveAsDraft", false));
//	    return ConstructionTrackerLocalServiceUtil.dynamicQuery(dynamicQuery);
//	}

	
}
