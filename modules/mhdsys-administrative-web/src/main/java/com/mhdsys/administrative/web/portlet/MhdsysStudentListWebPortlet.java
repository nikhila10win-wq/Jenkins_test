package com.mhdsys.administrative.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.service.StudentRegistrationLocalServiceUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author DELL
 */
@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Adiministrative",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysStudentListWeb", "javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.init-param.view-template=/student/student-list.jsp",
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTLISTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MhdsysStudentListWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(MhdsysStudentListWebPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean scoutMaster = RoleConstant.isScoutMaster(user, themeDisplay.getCompanyId());
			boolean guideMaster = RoleConstant.isGuideMaster(user, themeDisplay.getCompanyId());
			boolean commandingOfficer = RoleConstant.isCommandingOfficer(user, themeDisplay.getCompanyId());
			boolean isPtTeacher = RoleConstant.isPtTeacher(user, themeDisplay.getCompanyId());
			boolean isHOAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());

			List<StudentRegistration> studentList = null;
//			if (scoutMaster) {
//				studentList = StudentRegistrationLocalServiceUtil.findByGender("Male");
//			} else if (guideMaster) {
//				studentList = StudentRegistrationLocalServiceUtil.findByGender("Female");
//			} else {
//				studentList = StudentRegistrationLocalServiceUtil.getStudentRegistrations(-1, -1);
//			}
			if (scoutMaster) {
			    List<StudentRegistration> maleStudents = StudentRegistrationLocalServiceUtil.findByGender("Male");
			    studentList = maleStudents.stream()
			        .filter(student -> {
			            String registerTo = student.getRegisterTo();
			            String transferTo = student.getTransferTo();

			            // Exclude those registered to NCC, unless transferTo is Scout or Guide
			            return !("NCC".equalsIgnoreCase(registerTo)) ||
			                   ("Scout".equalsIgnoreCase(transferTo));
			        })
			        .collect(Collectors.toList());

			} else if (guideMaster) {
			    List<StudentRegistration> femaleStudents = StudentRegistrationLocalServiceUtil.findByGender("Female");
			    studentList = femaleStudents.stream()
			        .filter(student -> {
			            String registerTo = student.getRegisterTo();
			            String transferTo = student.getTransferTo();

			            // Exclude those registered to NCC, unless transferTo is Scout or Guide
			            return !("NCC".equalsIgnoreCase(registerTo)) ||
			                   ( "Guide".equalsIgnoreCase(transferTo));
			        })
			        .collect(Collectors.toList());

			} else {
			    List<StudentRegistration> allStudents = StudentRegistrationLocalServiceUtil.getStudentRegistrations(-1, -1);
			    studentList = allStudents.stream()
			        .filter(student -> {
			            String registerTo = student.getRegisterTo();
			            String transferTo = student.getTransferTo();

			            // Exclude those registered to Scout or Guide, unless transferTo is Commanding Officer
			            return !( "Scout".equalsIgnoreCase(registerTo) || "Guide".equalsIgnoreCase(registerTo) ) ||
			                   "Commanding Officer".equalsIgnoreCase(transferTo);
			        })
			        .collect(Collectors.toList());
			}

			LOGGER.info("student size: " + studentList.size());
			renderRequest.setAttribute("studentList", studentList);
			renderRequest.setAttribute("scoutMaster", scoutMaster);
			renderRequest.setAttribute("guideMaster", guideMaster);
			renderRequest.setAttribute("commandingOfficer", commandingOfficer);
			renderRequest.setAttribute("isPtTeacher", isPtTeacher);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}
}