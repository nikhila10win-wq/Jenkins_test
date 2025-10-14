package com.mhdsys.competition.management.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.pojo.CompetitionInitiationCommonDTO;
import com.mhdsys.common.pojo.CompetitionMasterCommonDTO;
import com.mhdsys.competition.management.util.CompetitionUtil;
import com.mhdsys.competition.management.web.constants.CompetitionCommonConstant;
import com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionMasterLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.TalukaMasterLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_INITIATION_MANAGEMENTWEB,
		"javax.portlet.name=" + CompetitionManagementWebPortletKeys.MHDSYS_COMPETITION_INITIATION_LIST_MANAGEMENTWEB,
		"mvc.command.name="
				+ CompetitionManagementWebPortletKeys.COMPETITION_INITIATION_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)
public class CompetitionInitiationMVCRenderCommand implements MVCRenderCommand {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	@Reference
	CompetitionUtil competitionMasterUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			long competitionMasterId = ParamUtil.getLong(renderRequest, "competitionMasterId");
			long competitionInitiationId = ParamUtil.getLong(renderRequest, "competitionInitiationId");
			String mode = ParamUtil.getString(renderRequest, "cmd");
			LOGGER.info("competitionMasterId:"+competitionMasterId);
			LOGGER.info("mode:"+mode);
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(themeDisplay.getUserId());
			if (Validator.isNotNull(userRoles)) {
				String districtName = userRoles.stream().map(Role::getName)
						.filter(roleName -> roleName.contains("-DSO")).findFirst()
						.map(roleName -> roleName.replace("-DSO", "")).orElse(null);

				renderRequest.setAttribute("districtName", districtName);
				DistrictMaster district = DistrictMasterLocalServiceUtil.findByname(districtName);
				if (Validator.isNotNull(district)) {
					renderRequest.setAttribute("talukas",
							TalukaMasterLocalServiceUtil.getTalukaByDistrictId(district.getDistrictId()));
				}
			}
			renderRequest.setAttribute("competitionMasterId", competitionMasterId);
			if (competitionMasterId > 0 && Validator
					.isNotNull(CompetitionMasterLocalServiceUtil.getCompetitionMaster(competitionMasterId))) {
				LOGGER.info("competitionMasterId: "+competitionMasterId);
				 CompetitionMasterCommonDTO competitionMasterDTO = competitionMasterUtil.setCompetitionMasterDTO(
							CompetitionMasterLocalServiceUtil.getCompetitionMaster(competitionMasterId));
				 LOGGER.info("competitionMasterDTO: "+competitionMasterDTO.isUnderForteen());
				renderRequest.setAttribute("competitionMaster", competitionMasterUtil.setCompetitionMasterDTO(
						CompetitionMasterLocalServiceUtil.getCompetitionMaster(competitionMasterId)));

			}
			if(competitionInitiationId > 0) {
				CompetitionInitiation competitionInitiation = CompetitionInitiationLocalServiceUtil.getCompetitionInitiation(competitionInitiationId);
				if(Validator.isNotNull(competitionInitiation)) {
					CompetitionInitiationCommonDTO competitionInitiationDTO = competitionMasterUtil.setCompetitionInitiationDTO(competitionInitiation);
					LOGGER.info("competitionInitiationDTO: "+competitionInitiationDTO);
					renderRequest.setAttribute("competitionInitiation",competitionMasterUtil.setCompetitionInitiationDTO(competitionInitiation));
				}
			}
			renderRequest.setAttribute("competitionMasterId", competitionMasterId);
			renderRequest.setAttribute("mode", mode);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return CompetitionCommonConstant.COMPETITION_INITIATION_JSP;
	}

}
