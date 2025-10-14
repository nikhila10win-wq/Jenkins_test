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
import com.mhdsys.common.pojo.AwardYouthCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.schema.service.AwardYouthLocalServiceUtil;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;
import com.mhdsys.youth.award.portlet.MhdsysAwardYouthListPortlet;
import com.mhdsys.youth.award.util.YouthAwardCommonUtil;
import com.mhdsys.youth.award.constants.YouthAwardConstants;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
		"javax.portlet.name="
				+ MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHLIST,
		"mvc.command.name="
				+ MhdsysAwardYouthWebPortletKeys.AWARD_YOUTH_MVC_RENDER_COMMAND }, service = MVCRenderCommand.class)

public class AwardYouthMVCRenderCommand implements MVCRenderCommand{
	private Log LOGGER=LogFactoryUtil.getLog(MhdsysAwardYouthListPortlet.class);
	 @Reference
	 YouthAwardCommonUtil youthAwardCommonUtil;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		LOGGER.info("AwardYouthMVCRenderCommand Start::::::");
		ThemeDisplay themeDisplay=(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			long awardYouthId=ParamUtil.getLong(renderRequest,"awardYouthId");
			AwardYouth awardYouth=AwardYouthLocalServiceUtil.getAwardYouth(awardYouthId);
			AwardYouthCommonDTO awardYouthCommonDTO=youthAwardCommonUtil.setAwardYouthCommonDTO(awardYouth, themeDisplay);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			boolean  isDeskOfficer= RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean  isAssistantDirector= RoleConstant.isAssistantDirector(user, themeDisplay.getCompanyId());
			boolean  isHOAdmin= RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			//boolean  isDeputyDirector= RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());

			LOGGER.info("isHOAdmin:::::::::::::::"+isHOAdmin);
			LOGGER.info("AwardYouthId:::::::::::::::"+awardYouthId);
			LOGGER.info("AwardYouthCommonDTO:::::::::::::"+awardYouthCommonDTO);
			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isAssistantDirector", isAssistantDirector);
			renderRequest.setAttribute("isHOAdmin", isHOAdmin);
			renderRequest.setAttribute("awardYouthCommonDTO", awardYouthCommonDTO);
			renderRequest.setAttribute("mode", ParamUtil.getString(renderRequest, "mode"));
		}
		catch (Exception e) {
			LOGGER.info("Error AwardYouthMVCRenderCommand ::::::"+e.getMessage());
		}
		return YouthAwardConstants.AWARD_YOUTH;
	}

}
