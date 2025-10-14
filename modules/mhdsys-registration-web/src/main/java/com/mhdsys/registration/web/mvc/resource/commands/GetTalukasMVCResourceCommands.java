package com.mhdsys.registration.web.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.service.TalukaMasterLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONWEB,
		"mvc.command.name=" + RegistrationWebPortletKeys.GET_TALUKAS }, service = MVCResourceCommand.class)
public class GetTalukasMVCResourceCommands implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Get Talukas By District Id  ::: ");
		long districtId = ParamUtil.getLong(resourceRequest, "districtId");
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		try {
			List<TalukaMaster> talukaMasters = getTalukasByDivisionId(districtId);
			if (Validator.isNotNull(talukaMasters)) {
				for (TalukaMaster talukaMaster : talukaMasters) {

					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("talukaId", talukaMaster.getTalukaId());
					jsonObject.put("talukaName", talukaMaster.getTalukaName_en());
					jsonArray.put(jsonObject);
				}
			}
			resourceResponse.getWriter().write(jsonArray.toString());

		} catch (Exception e) {
			LOGGER.error(e);
		}

		LOGGER.info("District Id ::: " + districtId);

		return true;
	}

	private List<TalukaMaster> getTalukasByDivisionId(long districtId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		try {
			List<TalukaMaster> talukaMasters = TalukaMasterLocalServiceUtil.getTalukaByDistrictId(districtId);

			LOGGER.info("Size Of Talukas ::: " + talukaMasters.size());

			return talukaMasters;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
