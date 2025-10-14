package com.mhdys.establishment.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.establishment.util.EstablishmentUtil;
import com.mhdsys.schema.service.EstablishmentDetailsLocalServiceUtil;
import com.mhdsys.schema.service.GPFDetailsLocalServiceUtil;
import com.mhdsys.schema.service.NPSDetailsLocalServiceUtil;
import com.mhdsys.schema.service.PersonalDetailsLocalServiceUtil;
import com.mhdsys.schema.service.PostingStatusLocalServiceUtil;
import com.mhdsys.schema.service.RoasterStatusLocalServiceUtil;
import com.mhdsys.schema.service.ServiceDetailsLocalServiceUtil;
import com.mhdsys.schema.service.TrainingDetailsLocalServiceUtil;
import com.mhdys.establishment.constants.EstablishmentWebPortletKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author DELL
 */
@Component(property = { "com.liferay.portlet.display-category=category.establishment",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysEstablishmentWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsps/personal-details.jsp",
		"javax.portlet.name=" + EstablishmentWebPortletKeys.MHDSYSESTABLISHMENTWEB,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",

		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MhdsysEstablishmentWebPortlet extends MVCPortlet {
	@Reference
	EstablishmentUtil establishmentUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		renderRequest.setAttribute("mode", "add");
		renderRequest.setAttribute("establishmentDetails", EstablishmentDetailsLocalServiceUtil.findByUserId(themeDisplay.getUserId()));
		renderRequest.setAttribute("personalDetails", establishmentUtil.setPersonalDetailsDTO(PersonalDetailsLocalServiceUtil.findByUserId(themeDisplay.getUserId()), themeDisplay));
		renderRequest.setAttribute("serviceDetails", establishmentUtil.setServiceDetailsDTO(ServiceDetailsLocalServiceUtil.findByUserId(themeDisplay.getUserId()), themeDisplay));
		renderRequest.setAttribute("gpfDetails", establishmentUtil.setGPFDetailsDTO(GPFDetailsLocalServiceUtil.findByUserId(themeDisplay.getUserId()), themeDisplay));
		renderRequest.setAttribute("npsDetails", establishmentUtil.setNPSDetailsDTO(NPSDetailsLocalServiceUtil.findByUserId(themeDisplay.getUserId()), themeDisplay));
		renderRequest.setAttribute("trainingDetails", establishmentUtil.setTrainingDetailsDTO(TrainingDetailsLocalServiceUtil.findByUserId(themeDisplay.getUserId()), themeDisplay));
		renderRequest.setAttribute("postingStatus", PostingStatusLocalServiceUtil.findByUserId(themeDisplay.getUserId()));
		renderRequest.setAttribute("roasterDetails", RoasterStatusLocalServiceUtil.findByUserId(themeDisplay.getUserId()));

		super.render(renderRequest, renderResponse);
	}
}