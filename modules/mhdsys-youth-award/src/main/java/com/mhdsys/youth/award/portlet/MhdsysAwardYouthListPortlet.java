package com.mhdsys.youth.award.portlet;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.service.AwardYouthLocalServiceUtil;
import com.mhdsys.schema.service.ConstructionTrackerLocalServiceUtil;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;
import com.mhdsys.youth.award.constants.YouthAwardConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"com.liferay.portlet.display-category=MHDSYS.YOUTH",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=MhdsysAwardYouthList",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=" + YouthAwardConstants.AWARD_YOUTH_LIST,
			"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHLIST,
			"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)

public class MhdsysAwardYouthListPortlet extends MVCPortlet{
	private Log LOGGER=LogFactoryUtil.getLog(MhdsysAwardYouthListPortlet.class);
	
	@Override
	
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
	        throws IOException, PortletException {
	    try {
	        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	        User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

	        boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
	        boolean isAssistantDirector = RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
	        boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());

	        List<AwardYouth> awardYouthsList = new ArrayList<>(); // Always start with a modifiable list

	        if (isDeskOfficer) {
	            awardYouthsList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(0));
	            awardYouthsList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(1));
	            awardYouthsList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(2));
	            renderRequest.setAttribute("isDeskOfficer", true);
	        } else if (isAssistantDirector) {
	            awardYouthsList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(1));
	            awardYouthsList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(2));
	            renderRequest.setAttribute("isAssistantDirector", true);
	        } else if (isHOAdmin) {
	            awardYouthsList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(2));
	            renderRequest.setAttribute("isHOAdmin", true); // maybe should be "isHOAdmin"
	        }

	        // Sort descending by AwardYouthId
	        List<AwardYouth> sortedList = awardYouthsList.stream()
	                .sorted(Comparator.comparingLong(AwardYouth::getAwardYouthId).reversed())
	                .collect(Collectors.toList());

	        LOGGER.info("awardYouthsList::::::::::::::::::::::::::::" + sortedList);
	        renderRequest.setAttribute("awardYouthsList", sortedList);
	    } catch (Exception e) {
	        LOGGER.error("Error in MhdsysAwardYouthListPortlet:::::::", e);
	    }

	    super.render(renderRequest, renderResponse);
	}

	
	private List<AwardYouth> getAwardYouthListBaseOnFormStatusAndStatus(int status) {
		DynamicQuery dynamicQuery = AwardYouthLocalServiceUtil.dynamicQuery();
	    dynamicQuery
	        .add(RestrictionsFactoryUtil.eq("formStatus", "Submitted"))
	        .add(RestrictionsFactoryUtil.eq("status", status));
 
	    return AwardYouthLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

}
