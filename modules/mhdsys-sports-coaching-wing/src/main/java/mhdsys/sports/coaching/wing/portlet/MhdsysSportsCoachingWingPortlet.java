package mhdsys.sports.coaching.wing.portlet;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author USER
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysSportsCoachingWing",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MhdsysSportsCoachingWingPortlet extends MVCPortlet {
	
	
	
}