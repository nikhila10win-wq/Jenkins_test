package com.mhdsys.common.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author yetish
 */
@Component(immediate = true, service = RoleUtil.class)
public class RoleUtil {

	private static Log LOGGER = LogFactoryUtil.getLog(RoleUtil.class);

	public Role getRole(User user, String roleName) {
		try {
			if (Validator.isNotNull(user) && Validator.isNotNull(roleName)) {
				return RoleLocalServiceUtil.getRole(user.getCompanyId(), roleName);
			}
		} catch (PortalException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public long getAdminRoleId(long companyId) {
		try {
			if (Validator.isNotNull(companyId)) {
				Role role = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ADMINISTRATOR);
				return role.getRoleId();
			}
		} catch (PortalException e) {
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public boolean addUserRole(String roleName, long companyId, long userId) {
		try {
			if (Validator.isNotNull(roleName) && Validator.isNotNull(companyId) && Validator.isNotNull(userId)) {
				Role role = RoleLocalServiceUtil.getRole(companyId, roleName.trim());
				return RoleLocalServiceUtil.addUserRole(userId, role);
			}
		} catch (PortalException e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	public static boolean hasRole(User user, String roleName, long companyId) {
		try {
			if (Validator.isNotNull(user) && Validator.isNotNull(roleName) && Validator.isNotNull(companyId)) {
				Role role = RoleLocalServiceUtil.getRole(companyId, roleName.trim());
				return RoleLocalServiceUtil.hasUserRole(user.getUserId(), role.getRoleId());
			}
		} catch (PortalException e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	public boolean hasDSORole(User user) {
		try {
			List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			boolean hasDSORole = userRoles.stream().map(Role::getName).anyMatch(roleName -> roleName.contains("-DSO"));

			if (hasDSORole) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	public boolean hasTSORole(User user) {
		try {
			List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
			boolean hasTSORole = userRoles.stream().map(Role::getName).anyMatch(roleName -> roleName.contains("-TSO") ||  roleName.contains("TSO-"));
			
			if (hasTSORole) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
}
