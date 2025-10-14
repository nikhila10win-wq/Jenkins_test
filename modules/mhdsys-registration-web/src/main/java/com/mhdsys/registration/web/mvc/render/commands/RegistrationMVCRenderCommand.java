package com.mhdsys.registration.web.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.mhdsys.registartion.web.constants.RegistrationCommonConstants;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONWEB,
		"mvc.command.name=" + RegistrationWebPortletKeys.SPORTS_REGISTRATION }, service = MVCRenderCommand.class)

public class RegistrationMVCRenderCommand implements MVCRenderCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			LOGGER.info("Registration Render Command Started");

			String type = ParamUtil.getString(renderRequest, "type");
			List<DivisionMaster> divisions = DivisionMasterLocalServiceUtil.getByActiveState(true);
			LOGGER.info("Size Of the Division Lists :::  " + divisions.size());
			renderRequest.setAttribute("divisions", divisions);

			switch (type.toLowerCase()) {
			case RegistrationCommonConstants.SCHOOL_COLLEGE:
				return RegistrationCommonConstants.SCHOOL_COLLEGE_REGISTRATION_JSP;
			case RegistrationCommonConstants.SPORTS_ASSOCIATION:
				return RegistrationCommonConstants.SPORTS_ASSOCIATION_REGISTRATION_JSP;
			case RegistrationCommonConstants.SPORTS_COMPLEX:
				return RegistrationCommonConstants.SPORTS_COMPLEX_REGISTRATION_JSP;
			case RegistrationCommonConstants.YOUTH_INSTITUTES:
				return RegistrationCommonConstants.YOUTH_INSTITUTES_REGISTRATION_JSP;
			case RegistrationCommonConstants.SPORTS_DESK_OFFICER:
				return RegistrationCommonConstants.SPORTS_DESK_OFFICER_JSP;
			case RegistrationCommonConstants.LOCAL_SELF_GOV:
				return RegistrationCommonConstants.LOCAL_SELF_GOV_JSP;
			default:
				return "";
			}

		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.info("Registration Render Command ended");
		return RegistrationCommonConstants.SCHOOL_COLLEGE_REGISTRATION_JSP;
	}

}
