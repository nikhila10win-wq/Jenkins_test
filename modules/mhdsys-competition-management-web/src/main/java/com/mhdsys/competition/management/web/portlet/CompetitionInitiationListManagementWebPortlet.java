package com.mhdsys.competition.management.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.RoleUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.CompetitionInitiationCommonDTO;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author DELL
 */
@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Competition",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysCompetitionInitiatedListManagementWeb",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-css=/o/mhdsys-dashboard-theme/css/plugins/bootstrap.min.css",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.init-param.view-template=" + CompetitionCommonConstant.COMPETITION_INITIATED_LIST_JSP,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_INITIATION_LIST_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CompetitionInitiationListManagementWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	CompetitionUtil competitionMasterUtil;
@Reference
com.mhdsys.common.util.RoleUtil roleUtil;
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			List<CompetitionInitiation> competitionInitiaionList;
			boolean isDSORole = roleUtil.hasDSORole(user);
			if(isDSORole) {
			competitionInitiaionList = CompetitionInitiationLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());
			}else {
//				change the condition based on roleid mapping
				competitionInitiaionList = CompetitionInitiationLocalServiceUtil.findAll();
			}
			List<CompetitionInitiationCommonDTO> compeList = new ArrayList<>();
			for (CompetitionInitiation competitionInitiaion : competitionInitiaionList) {
				compeList.add(competitionMasterUtil.setCompetitionInitiationDTO(competitionInitiaion));
			}
			renderRequest.setAttribute("competitionInitiationList", compeList);
			renderRequest.setAttribute("isDSORole", isDSORole);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}
}