package com.mdsys.establishment.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.mhdys.establishment.constants.EstablishmentWebPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
@Component(immediate = true, property = {
		"javax.portlet.name="
				+ EstablishmentWebPortletKeys.MHDSYSESTABLISHMENTWEB,
		"mvc.command.name="
				+ EstablishmentWebPortletKeys.ESTABLISHMENT_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)
public class EstablishmentMVCRenderCommand implements MVCRenderCommand{
	private static Log LOGGER = LogFactoryUtil.getLog(EstablishmentMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			renderRequest.setAttribute("primaryId", ParamUtil.getString(renderRequest, "primaryId"));
			renderRequest.setAttribute("mode", ParamUtil.getString(renderRequest, "mode"));
			
		}catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return null;
	}

}
