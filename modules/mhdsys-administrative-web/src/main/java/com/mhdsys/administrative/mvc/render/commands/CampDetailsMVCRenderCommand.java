package com.mhdsys.administrative.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.schema.service.UnitRegistrationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSADMINISTRATIVEWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTLISTWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.CAMP_DETAILS_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class CampDetailsMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(CampDetailsMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			renderRequest.setAttribute("unitReg", UnitRegistrationLocalServiceUtil.getUnitRegistrations(-1, -1));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "/ncc/camp-details.jsp";
	}

}
