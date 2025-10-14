package com.mhdsys.administrative.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administartive.util.AdministrativeUtil;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.schema.model.EventCertificate;
import com.mhdsys.schema.model.NCCGrant;
import com.mhdsys.schema.service.EventCertificateLocalServiceUtil;
import com.mhdsys.schema.service.NCCGrantLocalServiceUtil;
import com.mhdsys.schema.service.NccGrantRequestLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSGRANTREQUESTWEB, "mvc.command.name=",
				"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSADMINISTRATIVEWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.NCC_GRANT_REQUEST_LIST_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class NCCGrantRequestListMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(NCCGrantRequestListMVCRenderCommand.class);
	@Reference
	AdministrativeUtil administrativeUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			renderRequest.setAttribute("nccGrantRequest",NccGrantRequestLocalServiceUtil.getNccGrantRequests(-1, -1));

		} catch (Exception e) {
		}
		return "/ncc/grant-request-list.jsp";
	}

}
