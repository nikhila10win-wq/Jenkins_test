package com.mdsys.youth.award.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.mhdsys.common.api.youth.AwardYouthOrgCommonApi;
import com.mhdsys.common.pojo.AwardYouthCommonDTO;
import com.mhdsys.common.pojo.AwardYouthOrgCommonDTO;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.schema.model.AwardYouthOrg;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;
import com.mhdsys.youth.award.util.YouthAwardCommonUtil;
@Component(
		immediate = true, property = { 
		
		"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHORGWEB,
		"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHORGLIST,
		"mvc.command.name=" + MhdsysAwardYouthWebPortletKeys.SAVEAWARDYOUTHORG 
		
				}, 
service = MVCResourceCommand.class)

public class SaveAwardYouthOrgMVCResourceCommand extends BaseMVCResourceCommand{
	private Log LOGGER=LogFactoryUtil.getLog(SaveAwardYouthMVCResourceCommand.class);
	
	@Reference
	YouthAwardCommonUtil youthAwardCommonUtil;
	
	@Reference
	AwardYouthOrgCommonApi awardYouthOrgCommonApi;
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		ThemeDisplay themeDisplay=(ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		LOGGER.info("SaveAwardYouthOrgMVCResourceCommand is Started ::::::::::::::::::::::::");
       try {
    	   JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			AwardYouthOrgCommonDTO awardYouthOrgCommonDTO=youthAwardCommonUtil.setAwardYouthOrgCommonDTO(resourceRequest, themeDisplay);
			LOGGER.info("AwardYouthOrgCommonDTO ::::::::::" + awardYouthOrgCommonDTO);
			AwardYouthOrg awardYouthOrg=awardYouthOrgCommonApi.saveAwardYouthOrg(awardYouthOrgCommonDTO);
			LOGGER.info("AwardYouthOrgCommonDTO ::::::::::" + awardYouthOrg);
			 boolean status = Validator.isNotNull(awardYouthOrg);
		        jsonObject.put("status", status);
		        if(status)
		        jsonObject.put("awardYouthOrgId", awardYouthOrg.getAwardYouthOrgId());

		        
		        resourceResponse.setContentType("application/json");
		        resourceResponse.getWriter().write(jsonObject.toString());
			
		}
		catch (Exception e) {
			LOGGER.info("Error in SaveAwardYouthOrgMVCResourceCommand ::::::::" + e.getMessage());
			 try {
		            JSONObject errorObject = JSONFactoryUtil.createJSONObject();
		            errorObject.put("status", false);
		            errorObject.put("error", "Server error occurred");
		            resourceResponse.setContentType("application/json");
		            resourceResponse.getWriter().write(errorObject.toString());
		        } catch (Exception exception) {
		            LOGGER.error("Failed to write error response: ", exception);
		        }
		}
		//return true;
	}

}
