package com.mhdsys.registartion.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.mhdsys.registartion.web.constants.RegistrationCommonConstants;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.schema.service.CategoryMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author DELL
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=MHDSYS.Registration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysRegistrationSportPersonWeb",
		"javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-css=/o/mhdsys-dashboard-theme/css/plugins/bootstrap.min.css",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.init-param.view-template="+RegistrationCommonConstants.SPORT_PERSON_COACH_REGISTRATION_JSP,
		"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONSPORTPERSONWEBPORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RegistrationSportPersonWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
			try {
				String type = ParamUtil.getString(renderRequest, "type");
				LOGGER.info("type: "+type);
				renderRequest.setAttribute("competitionLevels", CompetitionLevelMasterLocalServiceUtil.getByActiveState(true));
				renderRequest.setAttribute("sportsMaster", SportsMasterLocalServiceUtil.getByActiveState(true));
				renderRequest.setAttribute("userType", type);
				renderRequest.setAttribute("categories", CategoryMasterLocalServiceUtil.getByActiveState(true));

			}catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		super.render(renderRequest, renderResponse);
	}
}