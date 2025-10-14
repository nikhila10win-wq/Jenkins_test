package com.mhdsys.awards.web.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.awards.web.util.AwardsUtil;
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.common.pojo.ObjectionCommonDTO;
import com.mhdsys.schema.model.Objection;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.SUGGESTION_OBJECTION_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.OBJECTION_LIST_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.SUGGESTION_OBJECTION }, service = MVCResourceCommand.class)
public class SaveObjectionMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveObjectionMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Objection Resource Commands ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long objectionId = ParamUtil.getLong(resourceRequest, "objectionId");

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			ObjectionCommonDTO commonDTO = awardsUtil.setObjectionDTO(resourceRequest, themeDisplay, objectionId);
			Objection objection = awardsApi.saveObjection(commonDTO);
			LOGGER.info("objection: " + objection);

			if (Validator.isNotNull(objection)) {
				jsonObject.put("objection", true);
			} else {
				jsonObject.put("objection", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("objection submitted");
		return false;
	}

	@Reference
	AwardsCommonApi awardsApi;

	@Reference
	AwardsUtil awardsUtil;

}
