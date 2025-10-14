package com.mhdsys.youth.award.portlet;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardYouthOrg;
import com.mhdsys.schema.service.AwardYouthOrgLocalServiceUtil;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;
import com.mhdsys.youth.award.constants.YouthAwardConstants;

import java.io.IOException;
import java.util.ArrayList;
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
			"javax.portlet.display-name=MhdsysAwardYouthOrgList",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=" + YouthAwardConstants.AWARD_YOUTH_ORG_LIST,
			"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHORGLIST,
			"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)

public class MhdsysAwardYouthOrgListPortlet extends MVCPortlet{
	private Log LOGGER=LogFactoryUtil.getLog(MhdsysAwardYouthOrgListPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
	        throws IOException, PortletException {
	    try {
	        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	        User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

	        boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
	        boolean isAssistantDirector = RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
	        boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());

	        List<AwardYouthOrg> awardYouthsOrgList = new ArrayList<>();

	        if (isDeskOfficer) {
	            awardYouthsOrgList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(0));
	            awardYouthsOrgList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(1));
	            awardYouthsOrgList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(2));
	            renderRequest.setAttribute("isDeskOfficer", true);
	        } else if (isAssistantDirector) {
	            awardYouthsOrgList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(1));
	            awardYouthsOrgList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(2));
	            renderRequest.setAttribute("isAssistantDirector", true);
	        } else if (isHOAdmin) {
	            awardYouthsOrgList.addAll(getAwardYouthListBaseOnFormStatusAndStatus(2));
	            renderRequest.setAttribute("isHOAdmin", true);
	        }

	        List<AwardYouthOrg> sortedAwardYouthsOrgList = awardYouthsOrgList.stream()
	                .sorted(Comparator.comparingLong(AwardYouthOrg::getAwardYouthOrgId).reversed())
	                .collect(Collectors.toList());

	        LOGGER.info("awardYouthsOrgList ::::::::::::::::::::::::::::::: " + sortedAwardYouthsOrgList);
	        renderRequest.setAttribute("awardYouthsOrgList", sortedAwardYouthsOrgList);

	    } catch (Exception e) {
	        LOGGER.error("Error in MhdsysAwardYouthListPortlet ::::::::::: ", e);
	    }

	    super.render(renderRequest, renderResponse);
	}

	
	private List<AwardYouthOrg> getAwardYouthListBaseOnFormStatusAndStatus(int status) {
		DynamicQuery dynamicQuery = AwardYouthOrgLocalServiceUtil.dynamicQuery();
	    dynamicQuery
	        .add(RestrictionsFactoryUtil.eq("formStatus", "Submitted"))
	        .add(RestrictionsFactoryUtil.eq("status", status));
 
	    return AwardYouthOrgLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

}
