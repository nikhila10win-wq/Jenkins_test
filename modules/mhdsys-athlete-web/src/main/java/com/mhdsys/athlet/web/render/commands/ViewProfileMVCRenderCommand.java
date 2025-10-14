package com.mhdsys.athlet.web.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.athelet.web.com.constants.MhdsysAthleteWebPortletKeys;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAthleteWebPortletKeys.MHDSYSATHLETEWEB, "mvc.command.name="
				+ MhdsysAthleteWebPortletKeys.VIEW_PROFILE_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class ViewProfileMVCRenderCommand implements MVCRenderCommand{
	private Log LOGGER = LogFactoryUtil.getLog(ViewProfileMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long participantUserId = ParamUtil.getLong(renderRequest, "participantUserId");
			User user = UserLocalServiceUtil.getUser(participantUserId);
			SchoolCollegeOfficerRegistration schoolCollege =SchoolCollegeOfficerRegistrationLocalServiceUtil.findByUserId(participantUserId);
			renderRequest.setAttribute("user", user);
			String portraitURL;
			if (Validator.isNotNull(user) && user.getPortraitId() > 0) {
		        portraitURL = user.getPortraitURL(themeDisplay);
		    } else {
		        portraitURL = themeDisplay.getPathImage(); // Default avatar
		    }
			LOGGER.info("portalurl : "+portraitURL);
			renderRequest.setAttribute("schoolCollege", schoolCollege);
			renderRequest.setAttribute("portraitURL", portraitURL);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		return "/jsps/view-profile.jsp";
	}

}
