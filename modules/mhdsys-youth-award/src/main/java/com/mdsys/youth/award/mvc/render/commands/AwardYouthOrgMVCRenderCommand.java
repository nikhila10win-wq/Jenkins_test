package com.mdsys.youth.award.mvc.render.commands;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.youth.AwardYouthCommonApi;
import com.mhdsys.common.pojo.AwardYouthOrgCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardYouthOrg;
import com.mhdsys.schema.service.AwardYouthOrgLocalServiceUtil;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;
import com.mhdsys.youth.award.constants.YouthAwardConstants;
import com.mhdsys.youth.award.portlet.MhdsysAwardYouthListPortlet;
import com.mhdsys.youth.award.util.YouthAwardCommonUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(immediate = true, property = {
		"javax.portlet.name="
				+ MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHORGLIST,
		"mvc.command.name="
				+ MhdsysAwardYouthWebPortletKeys.AWARD_YOUTH_ORG_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)


public class AwardYouthOrgMVCRenderCommand implements MVCRenderCommand{
	private Log LOGGER=LogFactoryUtil.getLog(MhdsysAwardYouthListPortlet.class);
	 @Reference
     YouthAwardCommonUtil youthAwardCommonUtil;
	 
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		LOGGER.info("AwardYouthOrgMVCRenderCommand Start::::::");
		ThemeDisplay themeDisplay=(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			long awardYouthOrgId=ParamUtil.getLong(renderRequest,"awardYouthOrgId");
			AwardYouthOrg awardYouthOrg=AwardYouthOrgLocalServiceUtil.getAwardYouthOrg(awardYouthOrgId);
			AwardYouthOrgCommonDTO awardYouthOrgCommonDTO=youthAwardCommonUtil.setAwardYouthOrgCommonDTO(awardYouthOrg, themeDisplay);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean  isDeskOfficer= RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean  isAssistantDirector= RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
			boolean  isHOAdmin= RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			//boolean  isDeputyDirector= RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());
			
			LOGGER.info("AwardYouthOrgCommonDTO:::::::::::::"+awardYouthOrgCommonDTO);
			LOGGER.info("awardYouthOrg:::::::::::::"+awardYouthOrg);
			LOGGER.info("AwardYouthOrgId:::::::::::::::"+awardYouthOrgId);
			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isAssistantDirector", isAssistantDirector);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			
			renderRequest.setAttribute("awardYouthOrgCommonDTO", awardYouthOrgCommonDTO);
			renderRequest.setAttribute("mode", ParamUtil.getString(renderRequest, "mode"));
		}
		catch (Exception e) {
			LOGGER.info("Error AwardYouthOrgMVCRenderCommand ::::::"+e.getMessage());
		}
		
		return YouthAwardConstants.AWARD_YOUTH_ORG;
	}

}
