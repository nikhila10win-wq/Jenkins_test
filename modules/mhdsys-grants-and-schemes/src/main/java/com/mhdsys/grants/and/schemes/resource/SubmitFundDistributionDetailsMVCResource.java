package com.mhdsys.grants.and.schemes.resource;

import com.liferay.portal.kernel.json.JSONArray;
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
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.schema.model.DistrictGrantScheme;
import com.mhdsys.schema.service.DistrictGrantSchemeLocalServiceUtil;

import java.math.BigDecimal;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSFUNDDISTRIBUTION,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSAPPLYDISTRICTLEVELGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSDISTRICTLEVELGRANTSANDSCHEMES,
		"mvc.command.name=" + MhdsysGrantsAndSchemesPortletKeys.FUND_DISTRIBUTION }, service = MVCResourceCommand.class)
public class SubmitFundDistributionDetailsMVCResource implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(SubmitFundDistributionDetailsMVCResource.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Fund Distribution Resource Command ::: ");

		JSONObject responseJson = JSONFactoryUtil.createJSONObject();
		responseJson.put("success", false);

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String applicationsDataJson = ParamUtil.getString(resourceRequest, "applicationsData");

			JSONArray applicationsArray = JSONFactoryUtil.createJSONArray(applicationsDataJson);
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

			boolean allUpdated = true;

			for (int i = 0; i < applicationsArray.length(); i++) {
				JSONObject appData = applicationsArray.getJSONObject(i);
				long applicationId = appData.getLong("id");
				String amountStr = appData.getString("amount"); // Get as string

				LOGGER.info("Processing application ID: " + applicationId + " with amount: " + amountStr);

				// Validate the amount string
				if (!Validator.isNotNull(amountStr)) {
					LOGGER.error("Empty amount for application ID: " + applicationId);
					allUpdated = false;
					continue;
				}

				try {
					// Convert to BigDecimal for precise decimal handling if needed
					BigDecimal amount = new BigDecimal(amountStr);

					DistrictGrantScheme districtGrantScheme = DistrictGrantSchemeLocalServiceUtil
							.getDistrictGrantScheme(applicationId);

					if (Validator.isNotNull(districtGrantScheme)) {
						// Update the scheme with the distributed amount as string
						districtGrantScheme.setDistributedFund(amountStr); // Storing as string
						districtGrantScheme.setStatus("FUND_DISTRIBUTED");
						districtGrantScheme.setUserId(user.getUserId());

						DistrictGrantSchemeLocalServiceUtil.updateDistrictGrantScheme(districtGrantScheme);
					} else {
						LOGGER.error("No DistrictGrantScheme found for ID: " + applicationId);
						allUpdated = false;
					}
				} catch (NumberFormatException e) {
					LOGGER.error("Invalid amount format for application ID: " + applicationId + ": " + amountStr);
					allUpdated = false;
				}
			}

			if (allUpdated) {
				responseJson.put("success", true);
				responseJson.put("message", "Fund distribution completed successfully");
			} else {
				responseJson.put("message", "Some applications couldn't be updated");
			}

		} catch (Exception e) {
			LOGGER.error("Error in fund distribution: " + e.getMessage(), e);
			responseJson.put("message", "Error processing fund distribution: " + e.getMessage());
		}

		try {
			resourceResponse.getWriter().write(responseJson.toString());
		} catch (Exception e) {
			LOGGER.error("Error writing response: " + e.getMessage(), e);
		}

		return false;
	}
}
