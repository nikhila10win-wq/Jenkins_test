package com.mhdsys.administrative.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.pojo.EventCertificateCommonDTO;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.service.EventCertificateLocalServiceUtil;

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
@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Adiministrative",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysEventListWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/scout-and-guide/event-list.jsp",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSEVENTLISTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MhdsysEventListWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(MhdsysEventListWebPortlet.class);
	@Reference
	AdministrativeUtil administrativeUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String localLang = themeDisplay.getLocale().toString();
			List<EventCertificateCommonDTO> eventCertificates = new ArrayList<>();
			List<EventCertificate> eventList = EventCertificateLocalServiceUtil.findByUserId(themeDisplay.getUserId());
			for (EventCertificate eventCertificate : eventList) {
				eventCertificates.add(administrativeUtil.setEventCertificateCommonDTO(eventCertificate, themeDisplay));

			}
			renderRequest.setAttribute("eventCertificates",	eventCertificates);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}

}