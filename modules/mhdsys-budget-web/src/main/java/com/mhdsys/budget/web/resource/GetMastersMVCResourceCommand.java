package com.mhdsys.budget.web.resource;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys;
import com.mhdsys.schema.model.CommittedMaster;
import com.mhdsys.schema.model.SchemeMaster;
import com.mhdsys.schema.service.CommittedMasterLocalServiceUtil;
import com.mhdsys.schema.service.SchemeMasterLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETWEB,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETLIST,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETADDITION,
		"mvc.command.name=" + MhdsysBudgetWebPortletKeys.GETMASTERSDATA }, service = MVCResourceCommand.class)

public class GetMastersMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(GetMastersMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Get Masters By Category  ::: ");
		String category = ParamUtil.getString(resourceRequest, "category");
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		try {

			LOGGER.info("###################################33");
			LOGGER.info("Category Category ::::   " + category);

			if (category.equalsIgnoreCase("Schemes")) {
				List<SchemeMaster> schemeMasters = SchemeMasterLocalServiceUtil.getByActiveState(true);

				if (Validator.isNotNull(schemeMasters)) {
					for (SchemeMaster schemeMaster : schemeMasters) {

						JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", schemeMaster.getSchemeMasterId());
						jsonObject.put("name", schemeMaster.getSchemeName_en());
						jsonArray.put(jsonObject);
					}
				}

			} else if (category.equalsIgnoreCase("Committed")) {
				List<CommittedMaster> committedMasters = CommittedMasterLocalServiceUtil.getByActiveState(true);

				if (Validator.isNotNull(committedMasters)) {
					for (CommittedMaster committedMaster : committedMasters) {

						JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("id", committedMaster.getCommittedMasterId());
						jsonObject.put("name", committedMaster.getCommittedName_en());
						jsonArray.put(jsonObject);
					}
				}

			}
			resourceResponse.getWriter().write(jsonArray.toString());

		} catch (Exception e) {
			LOGGER.error(e);
		}

		LOGGER.info("#############################");

		return true;
	}

}
