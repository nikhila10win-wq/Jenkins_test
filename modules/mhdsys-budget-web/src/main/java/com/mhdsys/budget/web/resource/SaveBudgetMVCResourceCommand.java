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
import com.mhdsys.common.pojo.BudgetCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.Budget;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETWEB,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETADDITION,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETLIST,
		"mvc.command.name=" + MhdsysBudgetWebPortletKeys.BUDGETADDITION }, service = MVCResourceCommand.class)

public class SaveBudgetMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SaveBudgetMVCResourceCommand.class);

	@Reference
	BudgetCommonApi budgetCommonApi;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Budget Addition Resource Command ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long budgetId = ParamUtil.getLong(resourceRequest, "budgetId");
		String mode = ParamUtil.getString(resourceRequest, "mode");

		LOGGER.info("Budget Id PRIMARY KEY ::: " + budgetId);

		try {
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			BudgetCommonDTO budgetCommonDTO = BudgetUtil.setBudgetCommonDTO(resourceRequest, themeDisplay, budgetId);
			Budget budget = budgetCommonApi.saveBudget(budgetCommonDTO);
			LOGGER.info("Budget : " + budget);

			if (Validator.isNotNull(budget)) {
				jsonObject.put("budget", true);
				jsonObject.put("mode", mode);
				jsonObject.put("isHoAdmin", RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId()));

			} else {
				jsonObject.put("budget", false);
			}
			resourceResponse.getWriter().write(jsonObject.toString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Budget Details Added");
		return false;
	}

}
