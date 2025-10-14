package com.mhdsys.grievance.complaint.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.GrievanceCommonDTO;
import com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.GrievanceLocalServiceUtil;
import com.mhdsys.schema.service.TalukaMasterLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author DELL
 */
@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Grievance",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysGrievancePublishedListComplaintWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsps/publish-grievance-list.jsp",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.name=" + MhdsysGrievanceComplaintWebPortletKeys.MHDSYSGRIEVANCEPUBLISHEDLISTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MhdsysGrievancePublishedListWebPortlet extends MVCPortlet {
	private Log LOGGER = LogFactoryUtil.getLog(MhdsysGrievancePublishedListWebPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			String localLang = themeDisplay.getLocale().toString();
			boolean isDSO = false;
			boolean isTSO = false;
			String roleName = "";
			List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			for (Role role : userRoles) {
				if (role.getName().endsWith("-DSO")) {
					isDSO = true;
					roleName = role.getName(); // Save DSO role name
					break;
				} else if (role.getName().startsWith("TSO")) {
					isTSO = true;
					roleName = role.getName(); // Save TSO role name
					break;
				} else if (role.getName().equalsIgnoreCase("HO-Admin")) {
					roleName = "HO-Admin";
				}
			}
			List<Grievance> grievances;
			if (roleName.isEmpty()) {
				grievances = GrievanceLocalServiceUtil.findByStatusAndUserId("Publish", themeDisplay.getUserId());
			} else {
				grievances = GrievanceLocalServiceUtil.findByStatus("Publish");

			}
			LOGGER.info("Size Of the grievances Lists :::  " + grievances.size());
			List<String> divisionNames = new ArrayList<>();
			List<String> districtNames = new ArrayList<>();
			List<String> talukaNames = new ArrayList<>();
			for (Grievance grievance : grievances) {
				Object[] division = DivisionMasterLocalServiceUtil.getNewDivisionByDivisionId(localLang, true,
						grievance.getDivision());
				Object district = DistrictMasterLocalServiceUtil.getNewDistrictByDistrictId(localLang, true,
						grievance.getDistrict());
				Object taluka = TalukaMasterLocalServiceUtil.getNewTalukaByTalukaId(localLang, true,
						grievance.getTaluka());
				LOGGER.info("division name: " + division[1]);
				divisionNames.add(division[1].toString());
				Object[] districtData = (Object[]) district;
				Object[] talukaData = (Object[]) taluka;
				LOGGER.info("district name: " + districtData[1]);
				districtNames.add(districtData[1].toString());
				talukaNames.add(talukaData[1].toString());

			}
			renderRequest.setAttribute("divisionNames", divisionNames);
			renderRequest.setAttribute("districtNames", districtNames);
			renderRequest.setAttribute("talukaNames", talukaNames);
			renderRequest.setAttribute("grievanceList", grievances);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		super.render(renderRequest, renderResponse);
	}

}