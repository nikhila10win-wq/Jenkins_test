package com.mhdsys.budget.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys;
import com.mhdsys.budget.web.util.BudgetUtil;
import com.mhdsys.common.pojo.BudgetCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.Budget;
import com.mhdsys.schema.service.BudgetLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { "com.liferay.portlet.display-category=MHDSYS.Budget",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysBudgetList", "javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/budget/budget-list.jsp",
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETLIST,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MhdsysBudgetListPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(MhdsysBudgetListPortlet.class);

	@Reference
	BudgetUtil budgetUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			LOGGER.info("Budget List Portlet");
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isAssociation = RoleConstant.isAssociation(user, themeDisplay.getCompanyId());
			boolean isHoAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());
			boolean isDeputyDirector = RoleConstant.isDDD(user, themeDisplay.getCompanyId());
			boolean isDDD = RoleConstant.isDeputyDirector(user, themeDisplay.getCompanyId());

			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isHoAdmin", isHoAdmin);
			renderRequest.setAttribute("isAssociation", isAssociation);
			renderRequest.setAttribute("isDDD", isDDD);
			renderRequest.setAttribute("isDeputyDirector", isDeputyDirector);

			List<BudgetCommonDTO> budgetCommonDTOs = new ArrayList<>();

			List<Budget> budgets = BudgetLocalServiceUtil.getBudgets(-1, -1);

			for (Budget budget : budgets) {

				budgetCommonDTOs.add(budgetUtil.setBudgetCommonDTO(budget, themeDisplay));

			}

			renderRequest.setAttribute("budgetList", budgetCommonDTOs);

			super.render(renderRequest, renderResponse);

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

}
