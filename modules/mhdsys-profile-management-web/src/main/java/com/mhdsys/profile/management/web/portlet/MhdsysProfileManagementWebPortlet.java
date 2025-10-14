package com.mhdsys.profile.management.web.portlet;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletMode;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.profile.ProfileAPI;
import com.mhdsys.common.pojo.ScoutAndGuideRegistrationCommonDTO;
import com.mhdsys.common.pojo.profile.CoachAndSportPersonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.profile.management.web.constants.MhdsysProfileManagementWebPortletKeys;
import com.mhdsys.schema.model.ScoutAndGuideRegistration;
import com.mhdsys.schema.service.ScoutAndGuideRegistrationLocalServiceUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author yetish
 */
@Component(property = { "javax.portlet.version=3.0", "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.display-name=MhdsysProfileManagementWeb", "javax.portlet.init-param.template-path=/",
		
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
		"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MhdsysProfileManagementWebPortletKeys.MHDSYSPROFILEMANAGEMENTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MhdsysProfileManagementWebPortlet extends MVCPortlet {
	private static Log LOGGER = LogFactoryUtil.getLog(MhdsysProfileManagementWebPortlet.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Reference
	private ProfileAPI profileAPI;
	@Reference
	private FileUploadUtil fileUtil;

	@SuppressWarnings("deprecation")
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		boolean isSportsPerson = RoleUtil.hasRole(user, RoleConstant.SPORTS_PERSON, themeDisplay.getCompanyId());
		boolean isSportsCoach = RoleUtil.hasRole(user, RoleConstant.COACH, themeDisplay.getCompanyId());
		boolean scoutMaster = RoleConstant.isScoutMaster(user, themeDisplay.getCompanyId());
		boolean guideMaster = RoleConstant.isGuideMaster(user, themeDisplay.getCompanyId());
		LOGGER.info("guide master: " + guideMaster);
		String mvcPath = renderRequest.getRenderParameters().getValue("mvcPath");
		LOGGER.info("mvcPath: " + mvcPath);

		if ((!isSportsPerson && !isSportsCoach && !scoutMaster && !guideMaster) && mvcPath == null) {
			super.render(renderRequest, renderResponse);
			return;
		}
		if ((isSportsPerson || isSportsCoach) && mvcPath == null) {
			LiferayPortletURL renderUrl = PortletURLFactoryUtil.create(renderRequest,
					themeDisplay.getPortletDisplay().getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			renderUrl.setWindowState(LiferayWindowState.NORMAL);
			renderUrl.setPortletMode(LiferayPortletMode.VIEW);
			renderUrl.setParameter("mvcPath", "/profiles/sportPersonAndCoach.jsp");
			PortalUtil.getHttpServletResponse(renderResponse).sendRedirect(renderUrl.toString());
			return;
		} else if (Validator.isNotNull(mvcPath) && mvcPath.equalsIgnoreCase("/profiles/sportPersonAndCoach.jsp")
				&& (isSportsPerson || isSportsCoach)) {
			renderRequest.setAttribute("dto", JSONFactoryUtil.createJSONObject());
			renderRequest.setAttribute("roleNames",
					isSportsPerson ? Arrays.asList("Athlete", "Para Athlete", "Adventure Person")
							: Arrays.asList("Coach", "Coach of Para"));
			renderRequest.setAttribute("dateOfBirth", StringPool.BLANK);
			renderRequest.setAttribute("dateOfRegistration", StringPool.BLANK);
			renderRequest.setAttribute("CoachingDates", StringPool.BLANK);

			CoachAndSportPersonDTO dto = profileAPI.getByUserId(new CoachAndSportPersonDTO(), themeDisplay.getUserId());
			if (Validator.isNotNull(dto)) {
				try {
					System.out.println("dto : " + JSONFactoryUtil.createJSONObject(dto.toString()));
					renderRequest.setAttribute("dto", JSONFactoryUtil.createJSONObject(dto.toString()));

					// Dates
					renderRequest.setAttribute("dateOfBirth",
							Validator.isNotNull(dto.getDateOfBirth()) ? dateFormat.format(dto.getDateOfBirth())
									: StringPool.BLANK);
					renderRequest.setAttribute("dateOfRegistration",
							Validator.isNotNull(dto.getDateOfBirth()) ? dateFormat.format(dto.getDateOfRegistration())
									: StringPool.BLANK);
					JSONArray CoachingDatesArray = JSONFactoryUtil.createJSONArray(dto.getCoachingDates());
					renderRequest.setAttribute("CoachingDates",
							CoachingDatesArray.length() > 0 ? CoachingDatesArray : StringPool.BLANK);

					// Files
					renderRequest.setAttribute("birthCertificateFile",
							dto.getBirthCertificateEntryId() != 0
									? fileUtil.getUploadedFileDetails(themeDisplay, dto.getBirthCertificateEntryId())
									: StringPool.BLANK);
					renderRequest.setAttribute("photoFile",
							dto.getPhotoEntryId() != 0
									? fileUtil.getUploadedFileDetails(themeDisplay, dto.getPhotoEntryId())
									: StringPool.BLANK);
					renderRequest.setAttribute("nationalityCertificateFile",
							dto.getNationalityCertificateEntryId() != 0
									? fileUtil.getUploadedFileDetails(themeDisplay,
											dto.getNationalityCertificateEntryId())
									: StringPool.BLANK);
					renderRequest.setAttribute("disabilityCertificateFile",
							dto.getDisabilityCertificateEntryId() != 0
									? fileUtil.getUploadedFileDetails(themeDisplay,
											dto.getDisabilityCertificateEntryId())
									: StringPool.BLANK);
					renderRequest.setAttribute("characterCertificateFile",
							dto.getCharacterCertificateEntryId() != 0
									? fileUtil.getUploadedFileDetails(themeDisplay,
											dto.getCharacterCertificateEntryId())
									: StringPool.BLANK);
					renderRequest.setAttribute("cancelledChequeFile",
							dto.getCancelledChequeEntryId() != 0
									? fileUtil.getUploadedFileDetails(themeDisplay, dto.getCancelledChequeEntryId())
									: StringPool.BLANK);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		LOGGER.info("mvcPath: " + mvcPath);
		if ((scoutMaster || guideMaster) && Validator.isNull(mvcPath)) {
			LOGGER.info("inside scout and guide");
			LiferayPortletURL renderUrl = PortletURLFactoryUtil.create(renderRequest,
					themeDisplay.getPortletDisplay().getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
			renderUrl.setWindowState(LiferayWindowState.NORMAL);
			renderUrl.setPortletMode(LiferayPortletMode.VIEW);
			renderUrl.setParameter("mvcPath", "/profiles/scout-guide-profile.jsp");
			PortalUtil.getHttpServletResponse(renderResponse).sendRedirect(renderUrl.toString());
			return;
		} else if (Validator.isNotNull(mvcPath) && mvcPath.equalsIgnoreCase("/profiles/scout-guide-profile.jsp")
				&& (scoutMaster || guideMaster)) {
			LOGGER.info("inside scout and guide");
			ScoutAndGuideRegistrationCommonDTO dto = new ScoutAndGuideRegistrationCommonDTO();
			ScoutAndGuideRegistration scoutAndGuideRegistration = ScoutAndGuideRegistrationLocalServiceUtil
					.findByUserId(themeDisplay.getUserId());
			LOGGER.info("ScoutAndGuide: " + scoutAndGuideRegistration);
			BeanPropertiesUtil.copyProperties(scoutAndGuideRegistration, dto);
			LOGGER.info("dto : " + dto);

			if (Validator.isNotNull(scoutAndGuideRegistration)) {
				try {
//					LOGGER.info("ScoutAndGuideDTO : " + JSONFactoryUtil.createJSONObject(dto.toString()));
					renderRequest.setAttribute("dto", scoutAndGuideRegistration);
					if (scoutAndGuideRegistration.getAadharCardFileEntryId() > 0) {
						dto.setAadharCardFileEntryId(scoutAndGuideRegistration.getAadharCardFileEntryId());

						DLFileEntry fileEntry = DLFileEntryLocalServiceUtil
								.getFileEntry(scoutAndGuideRegistration.getAadharCardFileEntryId());
						if (Validator.isNotNull(fileEntry)) {
							String fileName = fileEntry.getFileName();
							if (fileName.contains("_")) {
								int underscoreIndex = fileName.indexOf('_');
								String trimmedFilename = fileName.substring(underscoreIndex + 1);
								dto.setAadharCardFileName(trimmedFilename);
							} else {
								dto.setAadharCardFileName(fileName);
							}
							dto.setAadharCardFileURL(fileUtil
									.getPreviewURL(scoutAndGuideRegistration.getAadharCardFileEntryId(), themeDisplay));
						}
					}
					renderRequest.setAttribute("dto", dto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		super.render(renderRequest, renderResponse);
	}
}