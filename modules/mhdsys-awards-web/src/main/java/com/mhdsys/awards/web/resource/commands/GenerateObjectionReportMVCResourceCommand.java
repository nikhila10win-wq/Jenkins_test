package com.mhdsys.awards.web.resource.commands;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.schema.model.Objection;
import com.mhdsys.schema.service.ObjectionLocalServiceUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name=" + AwardsWebPortletKeys.MHDSYSAWARDSWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.SUGGESTION_OBJECTION_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.OBJECTION_LIST_MANAGEMENTWEB,
		"mvc.command.name=" + AwardsWebPortletKeys.GENERATE_OBJECTION_REPORT }, service = MVCResourceCommand.class)

public class GenerateObjectionReportMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(GenerateObjectionReportMVCResourceCommand.class);

	@Reference
	DateConversionUtil dateConversionUtil;

//	@Override
//	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
//			throws PortletException {
//		LOGGER.info("Generate Report Resource Command ::: ");
//		try {
//
//			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
//
//			long objectionId = ParamUtil.getLong(resourceRequest, "objectionId");
//			LOGGER.info("Objection ID ::: " + objectionId);
//
//			Objection objection = ObjectionLocalServiceUtil.getObjection(objectionId);
//
//			PDDocument document = new PDDocument();
//			PDPage page = new PDPage(PDRectangle.A4);
//			document.addPage(page);
//
//			try (PDPageContentStream contentStream = new PDPageContentStream(document, page,
//					PDPageContentStream.AppendMode.APPEND, true, true)) {
//
//				LOGGER.info("PDF Generation  ::: ");
//
//				User user = UserLocalServiceUtil.getUser(objection.getUserId());
//
//				float margin = 40;
//				float startX = margin;
//				float startY = page.getMediaBox().getHeight() - margin;
//				float rowHeight = 20;
//				float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
//				float[] columnWidths = { 40, 50, 50, 130, 50, 40, 40, 50, 40, 80 };
//
//				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
//				contentStream.beginText();
//				contentStream.newLineAtOffset(startX, startY);
//				contentStream.showText("Shiv Chhatrapati State Sports Department, 2024-25");
//				contentStream.endText();
//
//				startY -= 30;
//				contentStream.beginText();
//				contentStream.newLineAtOffset(startX, startY);
//				contentStream.showText("Marking Chart");
//				contentStream.endText();
//
//				startY -= 40;
//				contentStream.setFont(PDType1Font.HELVETICA, 12);
//				contentStream.beginText();
//				contentStream.newLineAtOffset(startX, startY);
//				contentStream.showText("Name of Player/Coach: " + user.getFirstName());
//				contentStream.newLineAtOffset(0, -rowHeight);
//				contentStream.showText("User ID: " + user.getUserId());
//				contentStream.endText();
//
//				startY -= 40;
//				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 5);
//				float xPosition = startX;
//				String[] headers = { "Player Name", "Award Year", "Objector Name", "DO Verification",
//						"DD Verification" };
//
//				contentStream.setNonStrokingColor(200, 200, 200);
//				contentStream.addRect(startX, startY - rowHeight, tableWidth, rowHeight);
//				contentStream.fill();
//				contentStream.setNonStrokingColor(0, 0, 0);
//
//				float textX = startX + 5;
//				float textY = startY - (rowHeight / 1.5f);
//				float columnX = startX;
//
//				contentStream.setLineWidth(1f);
//
//				contentStream.moveTo(startX, startY);
//				contentStream.lineTo(startX + tableWidth, startY);
//				contentStream.stroke();
//
//				for (int i = 0; i < headers.length; i++) {
//					contentStream.moveTo(columnX, startY);
//					contentStream.lineTo(columnX, startY - rowHeight);
//					contentStream.stroke();
//
//					contentStream.beginText();
//					contentStream.newLineAtOffset(textX, textY);
//					contentStream.showText(headers[i]);
//					contentStream.endText();
//
//					columnX += columnWidths[i];
//					textX += columnWidths[i];
//				}
//
//				contentStream.moveTo(columnX, startY);
//				contentStream.lineTo(columnX, startY - rowHeight);
//				contentStream.stroke();
//
//				contentStream.moveTo(startX, startY - rowHeight);
//				contentStream.lineTo(startX + tableWidth, startY - rowHeight);
//				contentStream.stroke();
//
//				startY -= rowHeight + 10;
//				contentStream.setFont(PDType1Font.HELVETICA, 5);
//
//				float tableBottomY = startY - (1 * rowHeight);
//				float tableRightX = startX + tableWidth;
//
//			} catch (Exception e) {
//				LOGGER.error("Error Message  ::: " + e.getMessage());
//				LOGGER.error("Error  ::: " + e);
//			}
//
//			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
//				document.save(baos);
//
//				String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//
//				String fileName = "Report_" + timestamp + ".pdf";
//
//				InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
//
//				OutputStream stream = null;
//				File tempFile = null;
//				try {
//					tempFile = File.createTempFile("report", "pdf");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				stream = new FileOutputStream(tempFile);
//				document.save(stream);
//				long fileEntryId = fileUpload(tempFile, fileName, themeDisplay.getScopeGroupId());
//
//				FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
//
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
//
//				stream.close();
//				document.close();
//				tempFile.deleteOnExit();
//			} catch (Exception e) {
//				throw new PortletException("Error generating PDF", e);
//			}
//
//			return true;
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//		return false;
//	}

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Generate Report Resource Command ::: ");
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long objectionId = ParamUtil.getLong(resourceRequest, "objectionId");
			LOGGER.info("Objection ID ::: " + objectionId);

			Objection objection = ObjectionLocalServiceUtil.getObjection(objectionId);
			User objectorUser = UserLocalServiceUtil.getUser(objection.getUserId());

			PDDocument document = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true, true)) {

				LOGGER.info("PDF Generation  ::: ");

				float margin = 40;
				float startX = margin;
				float startY = page.getMediaBox().getHeight() - margin;
				float rowHeight = 20;
				float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
				float[] columnWidths = { 150, 100, 150, 150, 150 }; // Adjusted column widths

				// Header
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Shiv Chhatrapati State Sports Department, 2024-25");
				contentStream.endText();

				startY -= 30;
				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Objection Report");
				contentStream.endText();

				// Basic Information
				startY -= 40;
				contentStream.setFont(PDType1Font.HELVETICA, 12);
				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Player/Coach Name: " + objection.getPlayerName());
				contentStream.newLineAtOffset(0, -rowHeight);
				contentStream.showText("Objector Name: " + objectorUser.getFullName());
				contentStream.newLineAtOffset(0, -rowHeight);
				contentStream.showText("Award Year: " + objection.getAwardYear());
				contentStream.newLineAtOffset(0, -rowHeight);
				contentStream.showText("Objection Date: " + "");
				contentStream.endText();

				// Table Header
				startY -= 60;
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
				String[] headers = { "Player Name", "Award Year", "Objector Name", "DO Verification",
						"DD Verification" };

				// Draw header background
				contentStream.setNonStrokingColor(200, 200, 200);
				contentStream.addRect(startX, startY - rowHeight, tableWidth, rowHeight);
				contentStream.fill();
				contentStream.setNonStrokingColor(0, 0, 0);

				// Draw header text and lines
				float textX = startX + 5;
				float textY = startY - (rowHeight / 1.5f);
				float columnX = startX;

				contentStream.setLineWidth(1f);

				// Draw top line
				contentStream.moveTo(startX, startY);
				contentStream.lineTo(startX + tableWidth, startY);
				contentStream.stroke();

				// Draw columns and header text
				for (int i = 0; i < headers.length; i++) {
					contentStream.moveTo(columnX, startY);
					contentStream.lineTo(columnX, startY - rowHeight);
					contentStream.stroke();

					contentStream.beginText();
					contentStream.newLineAtOffset(textX, textY);
					contentStream.showText(headers[i]);
					contentStream.endText();

					columnX += columnWidths[i];
					textX += columnWidths[i];
				}

				// Draw right border
				contentStream.moveTo(columnX, startY);
				contentStream.lineTo(columnX, startY - rowHeight);
				contentStream.stroke();

				// Draw bottom line
				contentStream.moveTo(startX, startY - rowHeight);
				contentStream.lineTo(startX + tableWidth, startY - rowHeight);
				contentStream.stroke();

				// Table Data Row
				startY -= rowHeight;
				contentStream.setFont(PDType1Font.HELVETICA, 10);

				// Prepare data values
				String playerName = objection.getPlayerName();
				String awardYear = objection.getAwardYear();
				String objectorName = objectorUser.getFullName();
				String doVerification = objection.getDoVerification() != null ? objection.getDoVerification()
						: "Pending";
				String ddVerification = objection.getDdVerification() != null ? objection.getDdVerification()
						: "Pending";

				String[] dataValues = { playerName, awardYear, objectorName, doVerification, ddVerification };

				// Reset positions for data row
				textX = startX + 5;
				textY = startY - (rowHeight / 1.5f);
				columnX = startX;

				// Draw left border
				contentStream.moveTo(startX, startY);
				contentStream.lineTo(startX, startY - rowHeight);
				contentStream.stroke();

				// Draw data cells
				for (int i = 0; i < dataValues.length; i++) {
					contentStream.beginText();
					contentStream.newLineAtOffset(textX, textY);
					contentStream.showText(dataValues[i]);
					contentStream.endText();

					columnX += columnWidths[i];
					textX += columnWidths[i];

					// Draw right border
					contentStream.moveTo(columnX, startY);
					contentStream.lineTo(columnX, startY - rowHeight);
					contentStream.stroke();
				}

				// Draw bottom line for data row
				contentStream.moveTo(startX, startY - rowHeight);
				contentStream.lineTo(startX + tableWidth, startY - rowHeight);
				contentStream.stroke();

				// Add objection remarks if available
				if (objection.getDoRemarks() != null && !objection.getDoRemarks().isEmpty()) {
					startY -= 40;
					contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
					contentStream.beginText();
					contentStream.newLineAtOffset(startX, startY);
					contentStream.showText("Objection Remarks:");
					contentStream.endText();

					startY -= 20;
					contentStream.setFont(PDType1Font.HELVETICA, 10);
					// Split long text into multiple lines
					String remarks = objection.getDoRemarks();
					List<String> lines = splitStringIntoLines(remarks, 100);
					for (String line : lines) {
						contentStream.beginText();
						contentStream.newLineAtOffset(startX, startY);
						contentStream.showText(line);
						contentStream.endText();
						startY -= 15;
					}
				}

			} catch (Exception e) {
				LOGGER.error("Error Message  ::: " + e.getMessage());
				LOGGER.error("Error  ::: " + e);
			}

			// Rest of your file saving code remains the same...
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				document.save(baos);

				String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				String fileName = "Objection_Report_" + timestamp + ".pdf";
				InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

				OutputStream stream = null;
				File tempFile = null;
				try {
					tempFile = File.createTempFile("report", "pdf");
				} catch (Exception e) {
					e.printStackTrace();
				}
				stream = new FileOutputStream(tempFile);
				document.save(stream);
				long fileEntryId = fileUpload(tempFile, fileName, themeDisplay.getScopeGroupId());

				FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileEntryId);
				String downloadURL = DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay,
						"");

				LOGGER.info("Download URL : " + downloadURL);

				JSONObject responseJson = JSONFactoryUtil.createJSONObject();
				responseJson.put("downloadURL", downloadURL);

				resourceResponse.setContentType("application/json");
				PrintWriter outPrint = resourceResponse.getWriter();
				outPrint.print(responseJson.toString());
				outPrint.flush();

				stream.close();
				document.close();
				tempFile.deleteOnExit();
			} catch (Exception e) {
				throw new PortletException("Error generating PDF", e);
			}

			return true;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return false;
	}

	// Helper method to split long text into multiple lines
	private List<String> splitStringIntoLines(String text, int maxLineLength) {
		List<String> lines = new ArrayList<>();
		if (text == null || text.isEmpty()) {
			return lines;
		}

		String[] words = text.split(" ");
		StringBuilder currentLine = new StringBuilder();

		for (String word : words) {
			if (currentLine.length() + word.length() + 1 > maxLineLength) {
				lines.add(currentLine.toString());
				currentLine = new StringBuilder(word);
			} else {
				if (currentLine.length() > 0) {
					currentLine.append(" ");
				}
				currentLine.append(word);
			}
		}

		if (currentLine.length() > 0) {
			lines.add(currentLine.toString());
		}

		return lines;
	}

	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

	public Long getFolder(Long groupId) {

		Folder folder = null;
		try {
			folder = DLAppServiceUtil.getFolder(groupId, PARENT_FOLDER_ID, "Reports");
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

	public static String formatDate(Date date) {
		if (date == null) {
			return "N/A";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}

}
