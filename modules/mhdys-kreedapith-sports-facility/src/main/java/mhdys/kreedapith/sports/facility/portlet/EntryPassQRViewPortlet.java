package mhdys.kreedapith.sports.facility.portlet;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.SportsFacilityMasterLocalServiceUtil;
import com.mhdsys.schema.service.sportsFacilityBookingLocalServiceUtil;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys;

@Component(
		property = {
			"com.liferay.portlet.display-category=category.sportsFacility",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=mhdys_MhdysEntryPassQRViewPortlet",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/entrypass/entry-pass-qr-view.jsp",
			"javax.portlet.name=" + MhdysKreedapithSportsFacilityPortletKeys.ENTRYPASS_QR_VIEWPORTELT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user",
			"com.liferay.portlet.requires-namespaced-parameters=false",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/bootstrap.bundle.min.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/jquery.validate.js",
			"com.liferay.portlet.header-portal-javascript=/o/mhdsys-dashboard-theme/js/plugins/additional-methods.js",
		},
		service = Portlet.class
	)

public class EntryPassQRViewPortlet extends MVCPortlet{
	private Log _log=LogFactoryUtil.getLog(EntryPassQRViewPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		_log.info("------------- EntryPassQRViewPortlet -------------------- ");
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		HttpServletResponse httpRes = PortalUtil.getHttpServletResponse(renderResponse);

		long facilityBookingId = 0;
		String facilityBookingIdStr = httpReq.getParameter("facilityBookingId");

		if (Validator.isNotNull(facilityBookingIdStr)) {
		    try {
		        facilityBookingIdStr = URLDecoder.decode(facilityBookingIdStr, StandardCharsets.UTF_8.name());
		        facilityBookingId = Long.parseLong(facilityBookingIdStr);
		    } catch (Exception e) {
		        _log.error("Invalid facilityBookingId: " + facilityBookingIdStr, e);
		    }
		}

		_log.info("facilityBookingId:: " + facilityBookingId);
		renderRequest.setAttribute("facilityBookingId", facilityBookingId);

		//Redirect to error page if missing or invalid
		if (facilityBookingId <= 0) {
		    try {
		        httpRes.sendRedirect("/error");
		        return;
		    } catch (IOException e) {
		        _log.error("Redirection to error page failed", e);
		    }
		}

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
		    sportsFacilityBooking bookingDetails = sportsFacilityBookingLocalServiceUtil.getsportsFacilityBooking(facilityBookingId);

		    List<SportsFacilityMaster> filteredList = SportsFacilityMasterLocalServiceUtil
		            .getSportsFacilityMasters(-1, -1)
		            .stream()
		            .filter(sfm -> sfm.getIsUpdatedByDSO())
		            .peek(sfm -> sfm.setGeotagPhotos("[]"))
		            .sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
		            .collect(Collectors.toList());

		    // Handle medical certificate
		    long PanalActionDoc = bookingDetails.getMedicalCertificate();
		    String PanalActionDocURL = "";
		    if (Validator.isNotNull(PanalActionDoc)) {
		        FileEntry fe = DLAppLocalServiceUtil.getFileEntry(PanalActionDoc);
		        PanalActionDocURL = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
		    }

		    // Set attributes
		    renderRequest.setAttribute("bookingDetails", bookingDetails);
		    renderRequest.setAttribute("filteredList", filteredList);
		    renderRequest.setAttribute("medicalDocUrl", PanalActionDocURL);
		    renderRequest.setAttribute("noDataFound", false);

		    _log.info("facilityBookingView Details: " + bookingDetails);
		} catch (Exception e) {
		    _log.error("Error retrieving facility booking or master list", e);
		    renderRequest.setAttribute("noDataFound", true);
		}

		
		
		super.render(renderRequest, renderResponse);
	}
}
