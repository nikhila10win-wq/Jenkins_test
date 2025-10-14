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
import com.mhdsys.schema.model.SubCategoryMaster;
import com.mhdsys.schema.service.SubCategoryMasterLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETWEB,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSFUNDSDISTRIBUTION,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSDOWNLOADREPORTS,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSFUNDSDISTRIBUTIONLIST,
		"mvc.command.name=" + MhdsysBudgetWebPortletKeys.GETSUBCATEGORIES }, service = MVCResourceCommand.class)

public class GetSubCategoryMastersMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(GetSubCategoryMastersMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Get Sub Categories By Category Id  ::: ");
		long categoryId = ParamUtil.getLong(resourceRequest, "categoryId");
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		try {
			List<SubCategoryMaster> subCategoryMasters = getSubCategoryByCategoryId(categoryId);
			if (Validator.isNotNull(subCategoryMasters)) {
				for (SubCategoryMaster subCategoryMaster : subCategoryMasters) {

					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
					jsonObject.put("subCategoryId", subCategoryMaster.getSubCategoryId());
					jsonObject.put("subCategoryName", subCategoryMaster.getSubCategoryName_en());
					jsonArray.put(jsonObject);
				}
			}
			resourceResponse.getWriter().write(jsonArray.toString());

		} catch (Exception e) {
			LOGGER.error(e);
		}

		LOGGER.info("Category Id ::: " + categoryId);

		return true;
	}

	private List<SubCategoryMaster> getSubCategoryByCategoryId(long categoryId) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		try {
			List<SubCategoryMaster> subCategoryMasters = SubCategoryMasterLocalServiceUtil
					.getSubCategoryByCategoryId(categoryId);

			LOGGER.info("Size Of Sub Category ::: " + subCategoryMasters.size());

			return subCategoryMasters;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
