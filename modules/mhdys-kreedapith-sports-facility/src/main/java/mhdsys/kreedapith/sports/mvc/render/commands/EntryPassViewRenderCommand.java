package mhdsys.kreedapith.sports.mvc.render.commands;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.SportsFacilityMasterLocalServiceUtil;
import com.mhdsys.schema.service.sportsFacilityBookingLocalServiceUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name=" + MhdysKreedapithSportsFacilityPortletKeys.MHDYSFACILITYENTRYPASSPORTLET,
	        "mvc.command.name=" + MhdysKreedapithSportsFacilityPortletKeys.ENTRYPASSREDIRECT
	    },
	    service = MVCRenderCommand.class
	)

public class EntryPassViewRenderCommand implements MVCRenderCommand{
	private Log _log=LogFactoryUtil.getLog(EntryPassViewRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			
			long facilityBookingId = ParamUtil.getLong(renderRequest, "facilityBookingId");
			_log.info("facilityBookingId in EntryPassViewRenderCommand: "+ facilityBookingId);
			sportsFacilityBooking bookingDetails = null;
			List<SportsFacilityMaster> filteredList = null;
			if(Validator.isNotNull(facilityBookingId) && facilityBookingId>0) {
				bookingDetails = sportsFacilityBookingLocalServiceUtil.getsportsFacilityBooking(facilityBookingId);
				
//			    filteredList = SportsFacilityMasterLocalServiceUtil
//					    .getSportsFacilityMasters(-1, -1)
//					    .stream()
//					    .filter(sfm -> sfm.getIsUpdatedByDSO())
//					    .peek(sfm -> sfm.setGeotagPhotos("[]")) // <-- clear geotagPhotos
//					    .sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
//					    .collect(Collectors.toList());
			    
			    filteredList = SportsFacilityMasterLocalServiceUtil
					    .getSportsFacilityMasters(-1, -1)
					    .stream()
					    .filter(sfm -> sfm.getIsUpdatedByDSO())
					    .peek(sfm -> {
				            String json = sfm.getGeotagPhotos();
				            if (Validator.isNotNull(json)) {
				                sfm.setGeotagPhotos(getGeoTagPhotosByJson(json, themeDisplay)); // <-- now sets processed photo JSON
				            }
				        })
					    .sorted((a, b) -> b.getCreateDate().compareTo(a.getCreateDate()))
					    .collect(Collectors.toList());
			    
			    long PanalActionDoc = bookingDetails.getMedicalCertificate();
				String PanalActionDocURL="";
				if(Validator.isNotNull(PanalActionDoc)) {
					FileEntry fe = DLAppLocalServiceUtil.getFileEntry(PanalActionDoc);
					 PanalActionDocURL = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");
				}
				
				
//				QR CODE:
//				String qrCodeURL = "/web/guest/product-details?productId=" ;
//
//			    QRCodeWriter qrCodeWriter = new QRCodeWriter();
//			    BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeURL, BarcodeFormat.QR_CODE, 300, 300);
//			    BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
				String baseURL = PortalUtil.getPortalURL(renderRequest);
				String qrCodeURL = baseURL+"/view-entry-pass?facilityBookingId="+facilityBookingId;

				QRCodeWriter qrCodeWriter = new QRCodeWriter();
				BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeURL, BarcodeFormat.QR_CODE, 300, 300);
				BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

				// Convert BufferedImage to Base64 String
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(barcodeImage, "png", baos);
				String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

				renderRequest.setAttribute("qrCodeBase64", base64Image);
				
			    
				renderRequest.setAttribute("medicalDocUrl", PanalActionDocURL);
			}
			renderRequest.setAttribute("filteredList", filteredList);
			renderRequest.setAttribute("bookingDetails", bookingDetails);
			_log.info("facilityBookingView Details:  "+bookingDetails);
			
			return "/entrypass/entry-pass-view.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getGeoTagPhotosByJson(String rawJson, ThemeDisplay themeDisplay) {
	    StringBuilder photoUrls = new StringBuilder();

	    try {
	        JSONArray arr = JSONFactoryUtil.createJSONArray(rawJson);
	        for (int i = 0; i < arr.length(); i++) {
	            JSONObject obj = arr.getJSONObject(i);
	            if (obj.has("fileEntryId")) {
	                try {
	                    long fileId = GetterUtil.getLong(obj.getString("fileEntryId"));
	                    FileEntry fe = DLAppLocalServiceUtil.getFileEntry(fileId);
	                    String url = DLURLHelperUtil.getPreviewURL(fe, fe.getFileVersion(), themeDisplay, "");

	                    if (photoUrls.length() > 0) {
	                        photoUrls.append(",");
	                    }
	                    photoUrls.append(url);
	                } catch (Exception e) {
	                    // You can skip or log the error for individual entries
	                }
	            }
	        }
	    } catch (Exception e) {
	        _log.error("Error parsing geotag photo JSON: " + rawJson, e);
	    }

	    return photoUrls.length() > 0 ? photoUrls.toString() : "#";
	}
	
}
