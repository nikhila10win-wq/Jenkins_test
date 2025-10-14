package com.mhdsys.athelet.web.com.portlet;

import com.mhdsys.athelet.web.com.constants.MhdsysAthleteWebPortletKeys;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;
import com.mhdsys.schema.service.PTTeacherApplicationLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author DELL
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MhdsysAthleteWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsps/athelet-list.jsp",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.name=" + MhdsysAthleteWebPortletKeys.MHDSYSATHLETEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MhdsysAthleteWebPortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			List<PTTeacherApplication> ptTeacherApplications = PTTeacherApplicationLocalServiceUtil.getPTTeacherApplications(-1, -1);
			List<String> sportNames = new ArrayList<>();
			for(PTTeacherApplication ptTeacherApplication: ptTeacherApplications) {
				CompetitionInitiation competitionInitiation = CompetitionInitiationLocalServiceUtil.getCompetitionInitiation(ptTeacherApplication.getCompetitionInitiationId());
				sportNames.add(Validator
						.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(competitionInitiation.getSportId())
										.getName_en()
								: "");
			}
			renderRequest.setAttribute("sportNames", sportNames);
			renderRequest.setAttribute("atheletList", ptTeacherApplications);
		}catch (Exception e) {
			
		}
		super.render(renderRequest, renderResponse);
	}
}