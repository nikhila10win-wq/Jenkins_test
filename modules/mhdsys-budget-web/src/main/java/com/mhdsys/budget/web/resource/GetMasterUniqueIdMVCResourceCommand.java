package com.mhdsys.budget.web.resource;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys;
import com.mhdsys.schema.model.CommittedMaster;
import com.mhdsys.schema.model.SchemeMaster;
import com.mhdsys.schema.service.CommittedMasterLocalServiceUtil;
import com.mhdsys.schema.service.SchemeMasterLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETWEB,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETLIST,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETADDITION,
		"mvc.command.name=" + MhdsysBudgetWebPortletKeys.GETMASTERSUNIQUEID }, service = MVCResourceCommand.class)
public class GetMasterUniqueIdMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(GetMasterUniqueIdMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Get Masters Inique Id By Master  ::: ");
		String category = ParamUtil.getString(resourceRequest, "category");
		long master = ParamUtil.getLong(resourceRequest, "master");
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		try {

			LOGGER.info("###################################33");
			LOGGER.info("Category :::   " + category);
			LOGGER.info("Master Master ::::   " + master);

			if (category.equalsIgnoreCase("Schemes")) {
				SchemeMaster schemeMasters = SchemeMasterLocalServiceUtil.getSchemeMaster(master);

				jsonObject.put("uniqueId", schemeMasters.getUniqueId());

			} else if (category.equalsIgnoreCase("Committed")) {
				CommittedMaster committedMasters = CommittedMasterLocalServiceUtil.getCommittedMaster(master);

				jsonObject.put("uniqueId", committedMasters.getUniqueId());

			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e);
		}

		LOGGER.info("#############################");

		return true;
	}

}
