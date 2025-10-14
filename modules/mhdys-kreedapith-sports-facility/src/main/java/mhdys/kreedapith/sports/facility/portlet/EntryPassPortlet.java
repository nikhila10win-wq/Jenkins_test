package mhdys.kreedapith.sports.facility.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.sportsFacilityBookingLocalServiceUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import mhdsys.sports.facility.web.util.SportsFacilityCommonUtil;
import mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys;

/**
 * @author VENUGOPAL
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sportsFacility",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=mhdys_MhdysEntryPassPortlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/entrypass/entrypass-list.jsp",
		"javax.portlet.name=" + MhdysKreedapithSportsFacilityPortletKeys.MHDYSFACILITYENTRYPASSPORTLET,
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
public class EntryPassPortlet  extends MVCPortlet{
	private Log _log=LogFactoryUtil.getLog(EntryPassPortlet.class);
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			
			renderRequest.setAttribute("facilityMaster", _SportsFacilityCommonUtil.getFacilityMaster());
			List<sportsFacilityBooking> filteredList = sportsFacilityBookingLocalServiceUtil
					.getsportsFacilityBookings(-1, -1)
					.stream()
					.filter(sfm -> sfm.getIsApproved() && sfm.getBookingStatus() == 1 &&  sfm.getCreatorUserId() == themeDisplay.getUserId()) // Approved List
					.sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
					.collect(Collectors.toList());
			_log.info("Approved Booking List ------- "+filteredList.toString());
			renderRequest.setAttribute("filteredList", filteredList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.render(renderRequest, renderResponse);
	}
	@Reference private SportsFacilityCommonUtil _SportsFacilityCommonUtil;
}
