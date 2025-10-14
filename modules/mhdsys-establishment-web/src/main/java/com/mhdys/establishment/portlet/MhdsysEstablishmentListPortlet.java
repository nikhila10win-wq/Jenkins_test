package com.mhdys.establishment.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.mhdsys.schema.model.EstablishmentDetails;
import com.mhdsys.schema.service.EstablishmentDetailsLocalServiceUtil;
import com.mhdys.establishment.constants.EstablishmentWebPortletKeys;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(property = { "com.liferay.portlet.display-category=category.establishment",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysEstablishmentDetailsListWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsps/establishment-details-list.jsp",
		"javax.portlet.name=" + EstablishmentWebPortletKeys.MHDSYSESTABLISHMENTLISTWEB,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",

		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MhdsysEstablishmentListPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		List<EstablishmentDetails> establishmentDetails = EstablishmentDetailsLocalServiceUtil.getEstablishmentDetailses(-1, -1);

		super.render(renderRequest, renderResponse);
	}
}
