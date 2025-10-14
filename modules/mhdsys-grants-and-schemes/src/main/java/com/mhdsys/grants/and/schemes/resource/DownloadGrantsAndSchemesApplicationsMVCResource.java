package com.mhdsys.grants.and.schemes.resource;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys;
import com.mhdsys.schema.model.DistrictGrantScheme;
import com.mhdsys.schema.service.DistrictGrantSchemeLocalServiceUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSAPPLYDISTRICTLEVELGRANTSANDSCHEMES,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSDOWNLOADAPPLICATIONS,
		"javax.portlet.name=" + MhdsysGrantsAndSchemesPortletKeys.MHDSYSDISTRICTLEVELGRANTSANDSCHEMES,
		"mvc.command.name="
				+ MhdsysGrantsAndSchemesPortletKeys.DOWNLOAD_APPLICATIONS }, service = MVCResourceCommand.class)

public class DownloadGrantsAndSchemesApplicationsMVCResource implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(DownloadGrantsAndSchemesApplicationsMVCResource.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info(" District Level Grants and Schemes Application Download Resource Command  ::: ");
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

			try {
				ByteArrayOutputStream outputStream = downloadReportPDF(resourceRequest, resourceResponse, themeDisplay);

				resourceResponse.setContentType("application/pdf");
				resourceResponse.addProperty(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=Applications_" + ".pdf");

			} catch (Exception e) {
				LOGGER.error("Error generating PDF", e);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

//	private ByteArrayOutputStream downloadReportPDF(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
//			ThemeDisplay themeDisplay) throws IOException {
//		try {
//
//			String selectedIdsJson = ParamUtil.getString(resourceRequest, "selectedIds");
//
//			JSONArray selectedIdsArray = JSONFactoryUtil.createJSONArray(selectedIdsJson);
//			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
//			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
//
//			for (int i = 0; i < selectedIdsArray.length(); i++) {
//				long applicationId = selectedIdsArray.getLong(i);
//				LOGGER.info("awardApplicationId:" + applicationId);
//				
//				DistrictGrantScheme districtGrantScheme = DistrictGrantSchemeLocalServiceUtil.getDistrictGrantScheme(applicationId);
//
//			}
//
//			PDDocument document = new PDDocument();
//			PDPage page = new PDPage(PDRectangle.A4);
//			document.addPage(page);
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//
//				contentStream.close();
//				document.save(outputStream);
//
//				byte[] pdfBytes = outputStream.toByteArray();
//				String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//				String fileName = "Applications_" + timestamp + ".pdf";
//
//				File tempFile = File.createTempFile("applications_", ".pdf");
//				try (FileOutputStream fos = new FileOutputStream(tempFile)) {
//					fos.write(pdfBytes);
//				}
//
//				long fileEntryId = fileUpload(tempFile, fileName, themeDisplay.getScopeGroupId());
//				FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
//				String downloadURL = DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay,
//						"");
//
//				LOGGER.info("Download URL : " + downloadURL);
//
//				JSONObject responseJson = JSONFactoryUtil.createJSONObject();
//				responseJson.put("downloadURL", downloadURL);
//
//				resourceResponse.setContentType("application/json");
//				PrintWriter outPrint = resourceResponse.getWriter();
//				outPrint.print(responseJson.toString());
//				outPrint.flush();
//				outPrint.close();
//
//				tempFile.deleteOnExit();
//
//			} catch (Exception e) {
//				LOGGER.error("Error while generating PDF", e);
//			} finally {
//				document.close();
//			}
//
//			return outputStream;
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//		return null;
//	}

	private ByteArrayOutputStream downloadReportPDF(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			ThemeDisplay themeDisplay) throws IOException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PDDocument document = new PDDocument();
		PDPageContentStream contentStream = null;

		try {
			String selectedIdsJson = ParamUtil.getString(resourceRequest, "selectedIds");
			JSONArray selectedIdsArray = JSONFactoryUtil.createJSONArray(selectedIdsJson);

			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);

			contentStream = new PDPageContentStream(document, page);

			float margin = 50;
			float yStart = page.getMediaBox().getHeight() - margin;
			float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
			float yPosition = yStart;
			float rowHeight = 20;
			float cellMargin = 5;
			float[] columnWidths = { tableWidth * 0.25f, tableWidth * 0.25f, tableWidth * 0.25f, tableWidth * 0.25f };

			// Header
			String[] headers = { "Category", "Subcategory", "Project", "Type" };

			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			float x = margin;
			for (int i = 0; i < headers.length; i++) {
				contentStream.beginText();
				contentStream.newLineAtOffset(x + cellMargin, yPosition - 15);
				contentStream.showText(headers[i]);
				contentStream.endText();
				x += columnWidths[i];
			}

			yPosition -= rowHeight;

			// Data rows
			contentStream.setFont(PDType1Font.HELVETICA, 11);
			for (int i = 0; i < selectedIdsArray.length(); i++) {
				long applicationId = selectedIdsArray.getLong(i);
				DistrictGrantScheme districtGrantScheme = DistrictGrantSchemeLocalServiceUtil
						.getDistrictGrantScheme(applicationId);

				String[] rowData = { districtGrantScheme.getCategory(), districtGrantScheme.getSubCategory(),
						districtGrantScheme.getProject(), districtGrantScheme.getType() };

				x = margin;
				for (int j = 0; j < rowData.length; j++) {
					contentStream.beginText();
					contentStream.newLineAtOffset(x + cellMargin, yPosition - 15);
					contentStream.showText(rowData[j] != null ? rowData[j] : "-");
					contentStream.endText();
					x += columnWidths[j];
				}

				yPosition -= rowHeight;

				if (yPosition <= margin) {
					contentStream.close();
					page = new PDPage(PDRectangle.A4);
					document.addPage(page);
					contentStream = new PDPageContentStream(document, page);
					yPosition = yStart;
				}
			}

			contentStream.close();
			document.save(outputStream);

			// Save file
			byte[] pdfBytes = outputStream.toByteArray();
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = "Applications_" + timestamp + ".pdf";

			File tempFile = File.createTempFile("applications_", ".pdf");
			try (FileOutputStream fos = new FileOutputStream(tempFile)) {
				fos.write(pdfBytes);
			}

			long fileEntryId = fileUpload(tempFile, fileName, themeDisplay.getScopeGroupId());
			FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
			String downloadURL = DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, "");

			LOGGER.info("Download URL : " + downloadURL);

			JSONObject responseJson = JSONFactoryUtil.createJSONObject();
			responseJson.put("downloadURL", downloadURL);

			resourceResponse.setContentType("application/json");
			PrintWriter outPrint = resourceResponse.getWriter();
			outPrint.print(responseJson.toString());
			outPrint.flush();
			outPrint.close();

			tempFile.deleteOnExit();

		} catch (Exception e) {
			LOGGER.error("Error while generating PDF", e);
		} finally {
			try {
				if (contentStream != null) {
					contentStream.close();
				}
				document.close();
			} catch (IOException e) {
				LOGGER.error("Failed to close PDF resources", e);
			}
		}

		return outputStream;
	}

	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

	public Long getFolder(Long groupId) {

		Folder folder = null;
		try {
			folder = DLAppServiceUtil.getFolder(groupId, PARENT_FOLDER_ID, "Grants And Schemes");
			LOGGER.info(folder);

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return folder.getFolderId();

	}

	@SuppressWarnings("deprecation")
	public Long fileUpload(java.io.File file, String filename, long groupId) {

		String mimeType = "application/pdf";
		String title = filename + ".pdf";
		String description = "This file is added via programatically";
		Long folderId = getFolder(groupId);
		try {

			FileEntry entry = DLAppServiceUtil.addFileEntry(groupId, folderId, title, mimeType, title, description, "",
					file, new ServiceContext());
			return entry.getFileEntryId();

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			LOGGER.error(e);
		}
		return 0l;
	}

}
