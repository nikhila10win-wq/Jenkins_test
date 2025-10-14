package com.mhdsys.awards.web.render.commands;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsCommonConstants;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.CommonUtilityConstant;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.Profile;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.AwardNameMasterLocalServiceUtil;
import com.mhdsys.schema.service.CategoryMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.ProfileLocalServiceUtil;
import com.mhdsys.schema.service.SportsCompLvlMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsTypeMasterLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_POINTS_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.OBJECTION_LIST_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.SUGGESTION_OBJECTION_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.AWARDS }, service = MVCRenderCommand.class)
public class AwardsMVCRenderCommand implements MVCRenderCommand {
	@Reference
	AwardsUtil awardsUtil;
	@Reference
	FileUploadUtil fileUploadUtil;

	@Reference
	RoleUtil roleUtil;

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			LOGGER.info("Awards Render Command Started");
			String type = ParamUtil.getString(renderRequest, "type");
			LOGGER.info("Awards Redirection ::: " + type);

			renderRequest.setAttribute("competitionLevels",
					CompetitionLevelMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsCompLevels", SportsCompLvlMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("awardsNames", AwardNameMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsMaster", SportsMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("sportsTypes", SportsTypeMasterLocalServiceUtil.getByActiveState(true));

			renderRequest.setAttribute("categories", CategoryMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("districts", DistrictMasterLocalServiceUtil.getByActiveState(true));
			renderRequest.setAttribute("userType", type);
			List<AwardApplicationCommonDTO> awardApplicationCommonDTOs = new ArrayList<>();
			List<AwardApplication> awardApplications = AwardApplicationLocalServiceUtil
					.getAwardApplicationsByuserId(themeDisplay.getUserId());

			LOGGER.info("awardApplications: " + awardApplications.size());
			String roleName = "";
			Optional<Profile> profileOptional = ProfileLocalServiceUtil.getByUserId(themeDisplay.getUserId());
			if (profileOptional.isPresent()) {
				Profile profile = profileOptional.get();
				roleName = profile.getRoleName();
			}
			renderRequest.setAttribute("roleName", roleName);

			DynamicQuery dynamicQuery = AwardApplicationLocalServiceUtil.dynamicQuery();
			dynamicQuery.addOrder(OrderFactoryUtil.asc("participationYear"));
			List<AwardApplication> application = AwardApplicationLocalServiceUtil.dynamicQuery(dynamicQuery);

			List<AwardApplicationCommonDTO> applicationsList = new ArrayList<>();
			for (AwardApplication appli : application) {
				AwardApplication awardApplication = AwardApplicationLocalServiceUtil.getAwardApplication(appli.getAwardApplicationId());
				if (awardApplication.getUserId() > 0 && awardApplication.getSportId() > 0) {

					LOGGER.info("Only Not Distributed Award Application Should Display");
					LOGGER.info("   " + awardApplication.getAssignToDeskOff());
					if (!awardApplication.getAssignToDeskOff()
							&& awardApplication.getStatus().equalsIgnoreCase(AwardsCommonConstants.APPLIED)) {
						LOGGER.info("111111111111111111111111111");
						applicationsList.add(awardsUtil.setAwardApplicationDTO(awardApplication));
					}
				}
			}

			renderRequest.setAttribute("isDeskOfficer", roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.DESK_OFFICER, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isAssociation", roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.ASSOCIATION, themeDisplay.getCompanyId()));
			renderRequest.setAttribute("isHO", roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.HO_ADMIN, themeDisplay.getCompanyId()));

			LOGGER.info("Desk : " + roleUtil.hasRole(themeDisplay.getUser(), RoleConstant.DESK_OFFICER, themeDisplay.getCompanyId()));

			try {
				Folder folder = fileUploadUtil.getFolder(themeDisplay.getScopeGroupId(), CommonUtilityConstant.AWARD_FOLDER);
				DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntryByFileName(
						themeDisplay.getScopeGroupId(), folder.getFolderId(), "selfundertaking.pdf");
				LOGGER.info("fileEntryByFileName: " + fileEntry.getFileName() + "title: " + fileEntry.getTitle());
				renderRequest.setAttribute("fileUrl", fileUploadUtil.getPreviewURL(fileEntry.getFileEntryId(), themeDisplay));
				renderRequest.setAttribute("title", fileEntry.getTitle());
			} catch (Exception e) {
				LOGGER.error(e);
			}
			if (Validator.isNotNull(awardApplications)) {
				for (AwardApplication awardApplication : awardApplications) {
					awardApplicationCommonDTOs.add(awardsUtil.setAwardApplicationDTO(awardApplication));
				}
				renderRequest.setAttribute("awardApplications", awardApplicationCommonDTOs);
			}
			
			switch (type.toLowerCase()) {
			case AwardsCommonConstants.CREATE_AWARDS_POINTS:
				return AwardsCommonConstants.CREATE_AWARDS_POINTS_JSP;
			case AwardsCommonConstants.SPORTS_APPLICATION_FOR_COACH:
				return AwardsCommonConstants.AWARDS_APPLICATION_FOR_SPORTS_PERSON;
			case AwardsCommonConstants.SPORTS_APPLICATION_FOR_SPORTS_PERSON:
				return AwardsCommonConstants.AWARDS_APPLICATION_FOR_SPORTS_PERSON;
			case AwardsCommonConstants.REVIEW_SPORTS_APPLICATION:
				renderRequest.setAttribute("applList", applicationsList);
				return AwardsCommonConstants.AWARDS_APPLICATION_VERIFICATION;
			case AwardsCommonConstants.OBJECTION:
				User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
				renderRequest.setAttribute("objector", user.getFirstName());
				renderRequest.setAttribute("email", user.getEmailAddress());
				renderRequest.setAttribute("mobileNo", "7741936646");
				renderRequest.setAttribute("cmd", "add");
				renderRequest.setAttribute("sportsNames", SportsMasterLocalServiceUtil.getByActiveState(true));
				return AwardsCommonConstants.SUGGESTION_OBJECTION_JSP;
			default:
				return "";
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		LOGGER.info("Registration Render Command ended");
		return AwardsCommonConstants.CREATE_AWARDS_POINTS_JSP;
	}
}
