package com.mhdsys.budget.web.resource;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys;
import com.mhdsys.budget.web.util.BudgetUtil;
import com.mhdsys.common.api.budget.BudgetCommonApi;
import com.mhdsys.common.pojo.FundDistributionCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.FundDistribution;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETWEB,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSFUNDSDISTRIBUTION,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSFUNDSDISTRIBUTIONLIST,
		"mvc.command.name=" + MhdsysBudgetWebPortletKeys.FUNDDISTRIBUTION }, service = MVCResourceCommand.class)

public class SaveFundDistributionMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveFundDistributionMVCResourceCommand.class);

	@Reference
	BudgetCommonApi budgetCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Fund Distribution Resource Command ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long fundDistributionId = ParamUtil.getLong(resourceRequest, "fundDistributionId");
		String mode = ParamUtil.getString(resourceRequest, "mode");

		LOGGER.info("Fund Id PRIMARY KEY ::: " + fundDistributionId);

		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			FundDistributionCommonDTO distributionCommonDTO = BudgetUtil.setFundDistributionCommonDTO(resourceRequest,
					themeDisplay, fundDistributionId);
			FundDistribution fundDistribution = budgetCommonApi.saveFundDistribution(distributionCommonDTO);
			LOGGER.info("Fund  : " + fundDistribution);

			if (Validator.isNotNull(fundDistribution)) {
				jsonObject.put("fund", true);
				jsonObject.put("mode", mode);
				jsonObject.put("isHoAdmin", RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));
			} else {
				jsonObject.put("fund", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Fund Distribution Details Added");
		return false;
	}

}
