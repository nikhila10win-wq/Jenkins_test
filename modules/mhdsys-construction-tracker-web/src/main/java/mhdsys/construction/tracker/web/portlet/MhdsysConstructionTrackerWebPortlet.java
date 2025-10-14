package mhdsys.construction.tracker.web.portlet;

import mhdsys.construction.tracker.mvc.render.commands.RedirectorMVCRender;
import mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.service.ConstructionTrackerLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.TalukaMasterLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author USER
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysConstructionTrackerWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MhdsysConstructionTrackerWebPortletKeys.MHDSYSCONSTRUCTIONTRACKERWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
	},
	service = Portlet.class
)
public class MhdsysConstructionTrackerWebPortlet extends MVCPortlet {
	 private Log _log=LogFactoryUtil.getLog(MhdsysConstructionTrackerWebPortlet.class);
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			
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
			
			boolean isAlreadySubmitted = false;
			ConstructionTracker trackers = null;
			try {
				trackers = ConstructionTrackerLocalServiceUtil.getByIntiatorUserId(user.getUserId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			if(Validator.isNotNull(trackers)) {
				isAlreadySubmitted = true;
			}
			renderRequest.setAttribute("isAlreadySubmitted", isAlreadySubmitted);
			_log.info("isAlreadySubmitted:::::::::::: "+isAlreadySubmitted);
			_log.info("isHOAdmin: "+isHOAdmin +", isDDD: "+isDDD +", isDSO: "+isDSO );
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.render(renderRequest, renderResponse);
	}
}