package com.mhdsys.awards.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.pojo.ObjectionCommonDTO;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.Objection;
import com.mhdsys.schema.service.ObjectionLocalServiceUtil;

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
		"javax.portlet.display-name=MhdsysObjectionListWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.template-path=/", "com.liferay.portlet.requires-namespaced-parameters=false",

		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",

		"javax.portlet.init-param.view-template=" + AwardsCommonConstants.OBJECTION_LIST_JSP,
		"javax.portlet.name=" + AwardsWebPortletKeys.OBJECTION_LIST_MANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class SuggestionObjectionListWebPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(AwardsApplicationListWebPortlet.class);

	@Reference
	RoleUtil roleUtil;

	@Reference
	AwardsUtil awardsUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isAssistantDirector = RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
			boolean isSportsCoach = RoleConstant.isSportsCoach(user, themeDisplay.getCompanyId());
			boolean isSportsPerson = RoleConstant.isSportsPerson(user, themeDisplay.getCompanyId());
			boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			boolean isPtTeacher = RoleConstant.isPtTeacher(user, themeDisplay.getCompanyId());

			List<Objection> objections = new ArrayList<>();
			if (isDeskOfficer || isDeputyDirector) {
				objections = ObjectionLocalServiceUtil.getObjections(-1, -1);
			} else {
				objections = ObjectionLocalServiceUtil.getByUserId(user.getUserId());
			}

			List<ObjectionCommonDTO> commonDTOs = new ArrayList<>();
			for (Objection objection : objections) {
				commonDTOs.add(
						awardsUtil.setObjectionDTO(ObjectionLocalServiceUtil.getObjection(objection.getObjectionId())));
			}
			renderRequest.setAttribute("objList", commonDTOs);

			renderRequest.setAttribute("isDeputyDirector", isDeputyDirector);
			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			renderRequest.setAttribute("isSportsPerson", isSportsPerson);
			renderRequest.setAttribute("isSportsCoach", isSportsCoach);
			renderRequest.setAttribute("isAssistantDirector", isAssistantDirector);
			renderRequest.setAttribute("isPtTeacher", isPtTeacher);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}

}
