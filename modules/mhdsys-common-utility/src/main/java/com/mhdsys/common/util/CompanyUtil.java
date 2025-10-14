package com.mhdsys.common.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author yetish
 */
@Component(immediate = true, service = CompanyUtil.class)
public class CompanyUtil {
	
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	
	/* Get default user to add other users */
	public User getDefaultUser() {
		User defaultUser = null;
		Company company = null;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			defaultUser = userLocalService.getDefaultUser(company.getCompanyId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return defaultUser;
	}
	
	public User getCompanyAdminUser(long companyId, long adminRoleId) {
		User adminUser = null;
		List<User> adminUsersList = userLocalService.getRoleUsers(adminRoleId); //get it from role util
		if (Validator.isNotNull(adminUsersList) && adminUsersList.size() > 0) {
			adminUser = adminUsersList.get(0);
		}
		return adminUser;
	}

	public long getDefaultCompanyId() {
		Company company = null;
		long companyId = 0l;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			companyId = company.getCompanyId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return companyId;
	}

	public long getDefaultScopeGroupId() {
		Company company = null;
		long groupId = 0l;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			groupId = company.getGroup().getGroupId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return groupId;
	}
	
	public long getSiteGroupId(long companyId) {
		try {
			//return groupLocalService.fetchFriendlyURLGroup(companyId, "/maharashtra-sports").getGroupId();
			return groupLocalService.getGroup(companyId, "Guest").getGroupId();
		}catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public long getUsercompanyId(long userId) {
		User user = null;
		try {
			user = userLocalService.getUser(userId);
			return user.getCompanyId();
		} catch (PortalException e) {
			LOGGER.error(e.getMessage());
		}
		return 0;
		
	}
	
	public long getGlobalCompanyGroupId() {
		long groupId = 0l;
		Company company = null;
		try {
			company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			groupId = company.getGroup().getGroupId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return groupId;
	}
	
	public long getDefaultSiteGroupId() {
	    try {
	        // Fetch the default company
	        Company company = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
	        long companyId = company.getCompanyId();

	        // Get the default site's group
	        Group group = GroupLocalServiceUtil.getGroup(companyId, "Guest");
	        return group.getGroupId();
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage());
	        return 0;
	    }
	}
	
	public long[] getRoleId(String regType) {
		long[] roleIds= new long[1];
		Role role = null;
		try {
			role = roleLocalService.getRole(getDefaultCompanyId(), regType);
			roleIds[0] = role.getRoleId();
		} catch (Exception e) {
			roleIds[0] = 0l;
			LOGGER.error(e.getMessage());
		}
		return roleIds;
	}
	
	@Reference private CompanyLocalService companyLocalService;
	@Reference private UserLocalService userLocalService;
	@Reference private GroupLocalService groupLocalService;
	@Reference private RoleLocalService roleLocalService;
}
