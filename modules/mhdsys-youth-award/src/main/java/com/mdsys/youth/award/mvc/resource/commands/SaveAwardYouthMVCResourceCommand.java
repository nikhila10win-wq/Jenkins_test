package com.mdsys.youth.award.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.youth.AwardYouthCommonApi;
import com.mhdsys.common.pojo.AwardYouthCommonDTO;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys;
import com.mhdsys.youth.award.util.YouthAwardCommonUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true, property = { 
		"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHWEB,
		"javax.portlet.name=" + MhdsysAwardYouthWebPortletKeys.MHDSYSAWARDYOUTHLIST,
		"mvc.command.name=" + MhdsysAwardYouthWebPortletKeys.SAVEAWARDYOUTH 
		}, 
service = MVCResourceCommand.class)

public class SaveAwardYouthMVCResourceCommand implements MVCResourceCommand{
   private Log LOGGER=LogFactoryUtil.getLog(SaveAwardYouthMVCResourceCommand.class);
   
   @Reference
	YouthAwardCommonUtil youthAwardCommonUtil;
   @Reference
   AwardYouthCommonApi awardYouthCommonApi;
   
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		ThemeDisplay themeDisplay=(ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		LOGGER.info("SaveAwardYouthMVCResourceCommand Called ::::::::::::::::");
		try {
			 JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			AwardYouthCommonDTO awardYouthCommonDTO=youthAwardCommonUtil.setAwardYouthCommonDTO(resourceRequest, themeDisplay);
			LOGGER.info("AwardYouthCommonDTO :::::::::::::::::" + awardYouthCommonDTO);
			AwardYouth awardYouth=awardYouthCommonApi.saveAwardYouth(awardYouthCommonDTO);
			LOGGER.info("awardYouth :::::::::::::::::" + awardYouth);
			 boolean status = Validator.isNotNull(awardYouth);
		        jsonObject.put("status", status);
		        if(status)
		        jsonObject.put("awardYouthId", awardYouth.getAwardYouthId());

		        
		        resourceResponse.setContentType("application/json");
		        resourceResponse.getWriter().write(jsonObject.toString());
		}
		catch (Exception e) {
			LOGGER.info("Error in SaveAwardYouthMVCResourceCommand ::::::::" + e.getMessage());
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
		return true;
	}

}
