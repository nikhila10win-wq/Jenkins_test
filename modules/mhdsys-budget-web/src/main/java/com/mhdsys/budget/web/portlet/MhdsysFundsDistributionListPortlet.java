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
import com.mhdsys.common.pojo.FundDistributionCommonDTO;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.schema.model.FundDistribution;
import com.mhdsys.schema.service.CategoryMasterLocalServiceUtil;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.FundDistributionLocalServiceUtil;

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
		"javax.portlet.display-name=MhdsysFundsDistributionList", "javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/budget/fund-distribution-list.jsp",
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSFUNDSDISTRIBUTIONLIST,
		"javax.portlet.resource-bundle=content.Language", "com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)

public class MhdsysFundsDistributionListPortlet extends MVCPortlet {

	private Log LOGGER = LogFactoryUtil.getLog(MhdsysFundsDistributionListPortlet.class);

	@Reference
	BudgetUtil budgetUtil;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			LOGGER.info("Fund Distribution List Portlet");
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean isDeskOfficer = RoleConstant.isDeskOfficer(user, themeDisplay.getCompanyId());
			boolean isAssociation = RoleConstant.isAssociation(user, themeDisplay.getCompanyId());
			boolean isHoAdmin = RoleConstant.isHOAdmin(user, themeDisplay.getCompanyId());

			renderRequest.setAttribute("isDeskOfficer", isDeskOfficer);
			renderRequest.setAttribute("isHoAdmin", isHoAdmin);
			renderRequest.setAttribute("isAssociation", isAssociation);

			List<FundDistributionCommonDTO> fundDistributionCommonDTOs = new ArrayList<>();

			List<FundDistribution> distributions = FundDistributionLocalServiceUtil.getFundDistributions(-1, -1);

			for (FundDistribution distribution : distributions) {

				fundDistributionCommonDTOs.add(budgetUtil.setFundDistributionCommonDTO(distribution));

			}

			renderRequest.setAttribute("fundList", fundDistributionCommonDTOs);
			
			renderRequest.setAttribute("divisions", DivisionMasterLocalServiceUtil.getDivisionMasters(-1, -1));
			renderRequest.setAttribute("categories", CategoryMasterLocalServiceUtil.getCategoryMasters(-1, -1));

			super.render(renderRequest, renderResponse);

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

}
