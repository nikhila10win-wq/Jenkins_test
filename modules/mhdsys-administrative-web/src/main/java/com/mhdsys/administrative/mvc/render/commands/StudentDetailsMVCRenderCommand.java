package com.mhdsys.administrative.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.service.StudentRegistrationLocalServiceUtil;
import com.mhdsys.schema.service.UnitRegistrationLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTREGWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTLISTWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.STUDENT_DETAILS_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class StudentDetailsMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(StudentDetailsMVCRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			long studentRegistrationId = ParamUtil.getLong(renderRequest, "studentRegistrationId");
			String mode = ParamUtil.getString(renderRequest, "mode");
			renderRequest.setAttribute("mode", mode);
			renderRequest.setAttribute("studentDetails", StudentRegistrationLocalServiceUtil.getStudentRegistration(studentRegistrationId));
			renderRequest.setAttribute("unitReg", UnitRegistrationLocalServiceUtil.getUnitRegistrations(-1, -1));
			boolean scoutMaster = RoleConstant.isScoutMaster(user, themeDisplay.getCompanyId());
			boolean guideMaster = RoleConstant.isGuideMaster(user, themeDisplay.getCompanyId());
			boolean commandingOfficer = RoleConstant.isCommandingOfficer(user, themeDisplay.getCompanyId());
			renderRequest.setAttribute("scoutMaster", scoutMaster);
			renderRequest.setAttribute("guideMaster", guideMaster);
			renderRequest.setAttribute("commandingOfficer", commandingOfficer);
		} catch (Exception e) {
		}
		return "/student/student-registration.jsp";
	}

}
