package com.mhdsys.registration.web.mvc.resource.commands;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.util.CompanyUtil;
import com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONWEB,"javax.portlet.name=" + RegistrationWebPortletKeys.MHDSYSREGISTRATIONSCHOOLCOLLEGEOFFICERWEB,
		"mvc.command.name=" + RegistrationWebPortletKeys.EMAIL_EXIST_URL }, service = MVCResourceCommand.class)
public class CheckEmailExistMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("CheckEmailExistMVCResourceCommand");
		String email = ParamUtil.getString(resourceRequest, "email");
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		User user = null;
		try {
			user = UserLocalServiceUtil.getUserByEmailAddress(companyUtil.getDefaultCompanyId(), email);
            if(Validator.isNotNull(user)) {
            	jsonObject.put("emailExist", true);
            }else {
            	jsonObject.put("emailExist", false);
            }
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			jsonObject.put("emailExist", false);
		}
		try {
			resourceResponse.getWriter().write(jsonObject.toString());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return true;
	}
@Reference CompanyUtil companyUtil;
}
