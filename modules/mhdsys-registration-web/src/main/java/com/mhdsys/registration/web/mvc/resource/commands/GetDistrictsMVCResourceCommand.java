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
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONWEB,
		"mvc.command.name=" + RegistrationWebPortletKeys.GET_DISTRICTS }, service = MVCResourceCommand.class)
public class GetDistrictsMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Get Districts By Division Id  ::: ");
		long divisionId = ParamUtil.getLong(resourceRequest, "divisionId");
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		try {
			List<DistrictMaster> districtMasters = getDistrictsByDivisionId(divisionId);
			if (Validator.isNotNull(districtMasters)) {
				for (DistrictMaster districtMaster : districtMasters) {

					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("districtId", districtMaster.getDistrictId());
					jsonObject.put("districtName", districtMaster.getDistrictName_en());
					jsonArray.put(jsonObject);
				}
			}
			resourceResponse.getWriter().write(jsonArray.toString());

		} catch (Exception e) {
			LOGGER.error(e);
		}

		LOGGER.info("Division Id ::: " + divisionId);

		return true;
	}

	private List<DistrictMaster> getDistrictsByDivisionId(long divisionId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		try {
			List<DistrictMaster> districtMasters = DistrictMasterLocalServiceUtil.getDistrictByDivisionId(divisionId);

			LOGGER.info("Size Of Distrcist ::: " + districtMasters.size());

			return districtMasters;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
