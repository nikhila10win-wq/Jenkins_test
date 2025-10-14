package mhdsys.construction.tracker.web.resource.commands;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.service.ConstructionTrackerLocalServiceUtil;

import java.io.File;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysConstructionTrackerWebPortletKeys.MHDSYSCONSTRUCTIONTRACKERWEB,
		"mvc.command.name="
				+ MhdsysConstructionTrackerWebPortletKeys.ADMINREVIEWRESOURCECOMMAND}, service = MVCResourceCommand.class)
public class AdminReviewResourceCommand  implements MVCResourceCommand{
	private Log _log = LogFactoryUtil.getLog(AdminReviewResourceCommand.class);
	@Reference FileUploadUtil fileUploadUtil;
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		_log.info("AdminReviewResourceCommand starts********************************************");
		
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), uploadRequest);
			long constructionTrackerId = ParamUtil.getLong(resourceRequest, "constructionTrackerId");
			_log.info("constructionTrackerId:: "+constructionTrackerId);
			ConstructionTracker constructionTrackerDetailsById = ConstructionTrackerLocalServiceUtil.getConstructionTracker(constructionTrackerId);
			if(Validator.isNotNull(constructionTrackerDetailsById)) {
				String review = ParamUtil.getString(resourceRequest, "review");
				String action = ParamUtil.getString(resourceRequest, "action");
				File reviewDoc = uploadRequest.getFile("reviewDoc");
				
				long fileEntryId = 0;
				if(Validator.isNotNull(reviewDoc)) {
					 fileEntryId = fileUploadUtil.multipleFileUpload(uploadRequest, "reviewDoc", "ConstructionTracker", serviceContext, reviewDoc.getName(), reviewDoc);
				}
				boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
				boolean isDDD = RoleConstant.isDDD(user, themeDisplay.getCompanyId());
				long applicationStatus = ("approve".equalsIgnoreCase(action) && isDDD) ? 1: ("approve".equalsIgnoreCase(action) && isHOAdmin) ? 2 : 9 ;
				long holdWithuserId = "reject".equalsIgnoreCase(action) ? constructionTrackerDetailsById.getIntiatorUserId() : themeDisplay.getUserId();
				_log.info("review:: "+review);
				_log.info("action:: "+action);
				_log.info("applicationStatus:: "+applicationStatus);
				_log.info("holdWithuserId:: "+holdWithuserId);
				
				constructionTrackerDetailsById.setHoldWithUserId(holdWithuserId);
				constructionTrackerDetailsById.setApplicationStatus(applicationStatus);
				if(isDDD) {
					constructionTrackerDetailsById.setDddReview(review);
					constructionTrackerDetailsById.setDddReviewDoc(fileEntryId);
				}
				if(isHOAdmin) {
					constructionTrackerDetailsById.setHoReview(review);
					constructionTrackerDetailsById.setHoReviewDoc(fileEntryId);
				}
				ConstructionTrackerLocalServiceUtil.updateConstructionTracker(constructionTrackerDetailsById);
				String redirectUrl = getRedirectUrlByResourceCommand(resourceRequest,themeDisplay.getPlid(),"dddHoView");
				_log.info("Construction Tracker redirectUrl ---------- "+ redirectUrl);
				
				resourceResponse.setContentType("application/json");
				PrintWriter writer = resourceResponse.getWriter();
				JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
				jsonResponse.put("redirectUrl", redirectUrl);
				writer.write(jsonResponse.toString());
//				include redirectUrl
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		_log.info("AdminReviewResourceCommand ends********************************************");
		
		return false;
	}
	
	public String getRedirectUrlByResourceCommand(ResourceRequest resourceRequest, long plId, String type) {
	    try {
	        LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
	            resourceRequest,
	            MhdsysConstructionTrackerWebPortletKeys.MHDSYSCONSTRUCTIONTRACKERWEB,
	            plId,
	            PortletRequest.RENDER_PHASE
	        );

	        liferayPortletURL.setWindowState(LiferayWindowState.NORMAL);
	        liferayPortletURL.setPortletMode(LiferayPortletMode.VIEW);

	        liferayPortletURL.setParameter("mvcRenderCommandName", MhdsysConstructionTrackerWebPortletKeys.REDIRECT);
	        liferayPortletURL.setParameter("type", type);

	        _log.info("Generated redirect URL: " + liferayPortletURL.toString());

	        return liferayPortletURL.toString();

	    } catch (Exception e) {
	        _log.error("Error generating redirect URL", e);
	    }
	    return null;
	}


}
