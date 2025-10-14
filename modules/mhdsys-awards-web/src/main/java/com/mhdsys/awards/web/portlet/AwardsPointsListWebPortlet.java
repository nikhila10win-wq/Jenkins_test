package com.mhdsys.awards.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.pojo.AwardPointsCommonDTO;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.schema.model.AwardPoints;
import com.mhdsys.schema.service.AwardPointsLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Awards",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=MhdsysAwardsPointsListWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",

		"javax.portlet.init-param.view-template=/awards/awards-points-list.jsp",
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_POINTS_LIST_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class AwardsPointsListWebPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(AwardsPointsListWebPortlet.class);
	@Reference
	AwardsUtil awardsUtil;
	@Reference
	RoleUtil roleUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			LOGGER.info("Awards Points List Portlet ::::::   ");
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			List<AwardPoints> awardPoints = AwardPointsLocalServiceUtil.getAwardPointses(-1, -1);
			List<AwardPointsCommonDTO> awardsList = new ArrayList<>();

			for (AwardPoints points : awardPoints) {
				awardsList.add(awardsUtil.setAwardsPointsDTO(points));
			}

			renderRequest.setAttribute("awardsList", awardsList);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}
}
