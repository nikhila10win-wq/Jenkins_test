package com.mhdsys.login.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;

/**
 * @author adhik
 */
@Component(immediate = true, property = { "key=login.events.post" }, service = LifecycleAction.class)
public class PostLoginEvent implements LifecycleAction {
	private static Log LOGGER = LogFactoryUtil.getLog(PostLoginEvent.class);

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		// TODO Auto-generated method stub
		HttpServletRequest request = lifecycleEvent.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(WebKeys.USER);
		String referer = request.getHeader("Referer");
		try {
			PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
			PermissionThreadLocal.setPermissionChecker(checker);
			boolean IsProfileUpdated = (boolean) user.getExpandoBridge().getAttribute("IsProfileUpdated");

			boolean sportPerson = RoleUtil.hasRole(user, RoleConstant.SPORTS_PERSON, user.getCompanyId());
			boolean coach = RoleUtil.hasRole(user, RoleConstant.COACH, user.getCompanyId());

			if (!IsProfileUpdated && (sportPerson || coach)) {
				lifecycleEvent.getResponse().sendRedirect("/group/guest/profile");
			} else {
				lifecycleEvent.getResponse().sendRedirect("/group/guest/dashboard");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}