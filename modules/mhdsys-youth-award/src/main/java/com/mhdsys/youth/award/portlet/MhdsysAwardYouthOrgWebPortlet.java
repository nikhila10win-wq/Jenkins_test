package com.mhdsys.youth.award.portlet;
import com.mhdsys.youth.award.constants.YouthAwardConstants;
import com.mhdsys.youth.award.util.YouthAwardCommonUtil;

import java.io.IOException;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.AwardYouthCommonDTO;
import com.mhdsys.common.pojo.AwardYouthOrgCommonDTO;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.schema.model.AwardYouthOrg;
import com.mhdsys.schema.service.AwardYouthLocalServiceUtil;
import com.mhdsys.schema.service.AwardYouthOrgLocalServiceUtil;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		property = {
			"com.liferay.portlet.display-category=MHDSYS.YOUTH",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=MhdsysAwardYouthOrgWeb",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=" + YouthAwardConstants.AWARD_YOUTH_ORG,
			"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHORGWEB,
			"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)

public class MhdsysAwardYouthOrgWebPortlet extends MVCPortlet{
	private Log LOGGER=LogFactoryUtil.getLog(MhdsysAwardYouthWebPortlet.class);
	 @Reference
    YouthAwardCommonUtil youthAwardCommonUtil;
	 @Override
	 public void render(RenderRequest renderRequest, RenderResponse renderResponse)
	         throws IOException, PortletException {
	     try {
	         ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	         long userId = themeDisplay.getUserId();

	         LOGGER.info("User Id::::" + userId);

	         AwardYouthOrgCommonDTO awardYouthOrgCommonDTO = null;
	         String mode = "add"; // default

	         AwardYouthOrg draftEntry = fetchAwardYouthBaseOnFormStatusAndUserId(userId, "Draft");
	         if (Validator.isNotNull(draftEntry)) {
	             awardYouthOrgCommonDTO = youthAwardCommonUtil.setAwardYouthOrgCommonDTO(draftEntry, themeDisplay);
	             mode = "edit";
	         } else {
	             AwardYouthOrg submittedEntry = fetchAwardYouthBaseOnFormStatusAndUserId(userId, "Submitted");
	             if (Validator.isNotNull(submittedEntry)) {
	                 awardYouthOrgCommonDTO = youthAwardCommonUtil.setAwardYouthOrgCommonDTO(submittedEntry, themeDisplay);
	                 mode = "view";
	             }
	         }

	         if (Validator.isNotNull(awardYouthOrgCommonDTO)) {
	             renderRequest.setAttribute("awardYouthOrgCommonDTO", awardYouthOrgCommonDTO);
	         }

	         renderRequest.setAttribute("mode", mode);

	     } catch (Exception e) {
	         LOGGER.info("Error in MhdsysAwardYouthWebPortlet::::::::::::"+ e.getMessage());
	         renderRequest.setAttribute("mode", "add");
	     }

	     super.render(renderRequest, renderResponse);
	 }

	private AwardYouthOrg fetchAwardYouthBaseOnFormStatusAndUserId(long userId,String formStatus) {
		DynamicQuery dynamicQuery = AwardYouthOrgLocalServiceUtil.dynamicQuery();
		dynamicQuery
		    .add(RestrictionsFactoryUtil.eq("formStatus", formStatus))
		    .add(RestrictionsFactoryUtil.eq("userId", userId));

		List<AwardYouthOrg> results = AwardYouthOrgLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (!results.isEmpty()) {
			return results.get(0); 
		}
		return null;
	}
}
