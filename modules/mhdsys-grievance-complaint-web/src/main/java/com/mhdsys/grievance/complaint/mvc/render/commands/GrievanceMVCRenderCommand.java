package com.mhdsys.grievance.complaint.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys;
import com.mhdsys.grievance.util.GrievanceUtil;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.model.TransferApplication;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.GrievanceLocalServiceUtil;
import com.mhdsys.schema.service.TransferApplicationLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrievanceComplaintWebPortletKeys.MHDSYSGRIEVANCECOMPLAINTWEB,
		"javax.portlet.name=" + MhdsysGrievanceComplaintWebPortletKeys.MHDSYSGRIEVANCELISTWEB,
		"javax.portlet.name=" + MhdsysGrievanceComplaintWebPortletKeys.MHDSYSGRIEVANCEPUBLISHEDLISTWEB,
		"mvc.command.name="
				+ MhdsysGrievanceComplaintWebPortletKeys.GRIEVANCE_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class GrievanceMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(GrievanceMVCRenderCommand.class);
@Reference
GrievanceUtil grievanceUtil;
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long grievanceId = ParamUtil.getLong(renderRequest, "grievanceId");
			long transferApplicationId = ParamUtil.getLong(renderRequest, "transferApplicationId");
			String mode = ParamUtil.getString(renderRequest, "mode");
			String isPublished = ParamUtil.getString(renderRequest, "isPublished");
			LOGGER.info("grievanceId: " + grievanceId+"transferApplicationId:"+transferApplicationId);
			Grievance grievance = GrievanceLocalServiceUtil.getGrievance(grievanceId);
			renderRequest.setAttribute("grievance", grievanceUtil.setGrievanceDTO(grievance));
			TransferApplication transferApplication = TransferApplicationLocalServiceUtil.findByGrievanceId(grievanceId);
			renderRequest.setAttribute("transferApplication", transferApplication);
			renderRequest.setAttribute("isPublished", isPublished);
			LOGGER.info("transferApplication: "+transferApplication);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDSO = false;
			List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			for (Role role : userRoles) {
				if (role.getName().endsWith("-DSO")) {
					isDSO = true;
					break;
				}
			}
			boolean isTSO = false;
			List<Role> tsoUserRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			for (Role role : tsoUserRoles) {
				if (role.getName().startsWith("TSO")) {
					isTSO = true;
					break;
				}
			}
			renderRequest.setAttribute("mode", mode);
			renderRequest.setAttribute("isDSO", isDSO);
			renderRequest.setAttribute("isHOAdmin", RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isTSO", isTSO);
			LOGGER.info("ho admin: "+RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));
			LOGGER.info("isDSO: "+isDSO);
			LOGGER.info("isTSO: "+isTSO);
			List<DivisionMaster> divisions = DivisionMasterLocalServiceUtil.getByActiveState(true);
			LOGGER.info("Size Of the Division Lists :::  " + divisions.size());
			renderRequest.setAttribute("divisions", divisions);
			if(mode.equalsIgnoreCase("view")) {
			renderRequest.setAttribute("application", TransferApplicationLocalServiceUtil.getTransferApplication(transferApplicationId));
			return "/jsps/grievance-complaint-register.jsp";
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return "/jsps/grievance-complaint-register.jsp";
	}

}
