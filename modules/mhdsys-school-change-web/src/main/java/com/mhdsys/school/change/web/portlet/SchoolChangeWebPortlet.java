package com.mhdsys.school.change.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.mhdsys.school.change.web.constants.SchoolChangeCommonConstants;
import com.mhdsys.school.change.web.constants.SchoolChangeWebPortletKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Epiphany
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysSchoolChangeWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template="+SchoolChangeCommonConstants.SCHOOL_CHANGE_JSP,
		"javax.portlet.name=" + SchoolChangeWebPortletKeys.MHDSYSSCHOOLCHANGEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SchoolChangeWebPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		
		super.render(renderRequest, renderResponse);
	}
}