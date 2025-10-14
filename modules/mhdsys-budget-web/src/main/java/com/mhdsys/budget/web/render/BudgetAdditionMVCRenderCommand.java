package com.mhdsys.budget.web.render;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.budget.web.constants.MhdsysBudgetConstant;
import com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys;
import com.mhdsys.budget.web.util.BudgetUtil;
import com.mhdsys.common.pojo.BudgetCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.service.BudgetLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETWEB,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETADDITION,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETLIST,
		"mvc.command.name=" + MhdsysBudgetWebPortletKeys.BUDGETADDITIONVIEW }, service = MVCRenderCommand.class)

public class BudgetAdditionMVCRenderCommand implements MVCRenderCommand {

	private Log LOGGER = LogFactoryUtil.getLog(BudgetAdditionMVCRenderCommand.class);
	@Reference
	BudgetUtil budgetUtil;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		try {
			long budgetId = ParamUtil.getLong(renderRequest, "budgetId");
			String mode = ParamUtil.getString(renderRequest, "mode");
			LOGGER.info(" Budget ID  :" + budgetId);
			LOGGER.info("mode:" + mode);

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isAssociation = RoleConstant.isAssociation(user, themeDisplay.getCompanyId());
			boolean isHoAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());

			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isHoAdmin", isHoAdmin);
			renderRequest.setAttribute("isAssociation", isAssociation);

			renderRequest.setAttribute("budget",
					budgetUtil.setBudgetCommonDTO(BudgetLocalServiceUtil.getBudget(budgetId), themeDisplay));
			
			BudgetCommonDTO budgetCommonDTO = budgetUtil.setBudgetCommonDTO(BudgetLocalServiceUtil.getBudget(budgetId), themeDisplay);
			
			String supplementaryDemandStr = budgetCommonDTO.getSupplementaryDemand();

			if (supplementaryDemandStr != null && !supplementaryDemandStr.isEmpty()) {
			    String[] supplementaryDemands = supplementaryDemandStr.split(",");
			    renderRequest.setAttribute("supplementaryDemands", supplementaryDemands);
			}
			
			renderRequest.setAttribute("mode", mode);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return MhdsysBudgetConstant.VIEW_BUDGET;
	}

}
