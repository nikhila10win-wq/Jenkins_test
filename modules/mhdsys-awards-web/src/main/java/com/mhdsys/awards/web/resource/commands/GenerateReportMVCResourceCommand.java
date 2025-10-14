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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.awards.web.constants.AwardsWebPortletKeys;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.CategoryMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionLevelMasterLocalServiceUtil;
import com.mhdsys.schema.service.SportsMasterLocalServiceUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
		"javax.portlet.name=" + AwardsWebPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB,
		
		"javax.portlet.name=" + AwardsWebPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB,
		"javax.portlet.name=" + AwardsWebPortletKeys.SPORTS_PERSON_LIST_MANAGEMENTWEB,
		
		"mvc.command.name=" + AwardsWebPortletKeys.GENERATE_REPORT }, service = MVCResourceCommand.class)
public class GenerateReportMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(GenerateReportMVCResourceCommand.class);

	@Reference
	DateConversionUtil dateConversionUtil;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Generate Report Resource Command ::: ");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(resourceRequest, "userId");
		long applicationId = ParamUtil.getLong(resourceRequest, "applicationId");
		LOGGER.info("USER ID  ::: " + userId);
		LOGGER.info("Application ID ::: " + applicationId);

		List<AwardApplication> applications = AwardApplicationLocalServiceUtil.getAwardApplicationsByuserId(userId);
		LOGGER.info("Application Counts ::: " + applications.size());

		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A4);
		document.addPage(page);

		try (PDPageContentStream contentStream = new PDPageContentStream(document, page,
				PDPageContentStream.AppendMode.APPEND, true, true)) {

			LOGGER.info("PDF Generation  ::: ");

			User user = UserLocalServiceUtil.getUser(userId);
			AwardApplication applicatAwardApplication = AwardApplicationLocalServiceUtil
					.getAwardApplication(applicationId);
			Map<Integer, String> genderMap = Map.of(1, "Male", 2, "Female");
			String genderName = genderMap.getOrDefault(applicatAwardApplication.getGender(), "N/A");

			String category = Validator.isNotNull(applicatAwardApplication.getCategory()) && Validator
					.isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(applicatAwardApplication.getCategory()))
							? CategoryMasterLocalServiceUtil.getCategoryMaster(applicatAwardApplication.getCategory())
									.getName()
							: "N/A";

			float margin = 40;
			float startX = margin;
			float startY = page.getMediaBox().getHeight() - margin;
			float rowHeight = 20;
			float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
			float[] columnWidths = { 40, 50, 50, 130, 50, 40, 40, 50, 40, 80 };

			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
			contentStream.beginText();
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText("Shiv Chhatrapati State Sports Awards, 2023-2024");
			contentStream.endText();

			startY -= 30;
			contentStream.beginText();
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText("Marking Chart");
			contentStream.endText();

			startY -= 40;
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.beginText();
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText("Name of Player/Coach: " + user.getFirstName());
			contentStream.newLineAtOffset(0, -rowHeight);
			contentStream.showText("Player/Coach ID: " + userId);
			contentStream.newLineAtOffset(300, rowHeight);
			contentStream.showText("Category: " + category);
			contentStream.newLineAtOffset(0, -rowHeight);
			contentStream.showText("Gender: " + genderName);
			contentStream.endText();

			startY -= 40;
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 5);
			float xPosition = startX;
			String[] headers = { "Comp Date", "Sport Name", "Comp Level", "Comp Name", "Medal Received", "Award Year",
					"DSO Marks", "DSO Remark"};

			contentStream.setNonStrokingColor(200, 200, 200);
			contentStream.addRect(startX, startY - rowHeight, tableWidth, rowHeight);
			contentStream.fill();
			contentStream.setNonStrokingColor(0, 0, 0);

			float textX = startX + 5;
			float textY = startY - (rowHeight / 1.5f);
			float columnX = startX;

			contentStream.setLineWidth(1f);

			contentStream.moveTo(startX, startY);
			contentStream.lineTo(startX + tableWidth, startY);
			contentStream.stroke();

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

			contentStream.moveTo(columnX, startY);
			contentStream.lineTo(columnX, startY - rowHeight);
			contentStream.stroke();

			contentStream.moveTo(startX, startY - rowHeight);
			contentStream.lineTo(startX + tableWidth, startY - rowHeight);
			contentStream.stroke();

			startY -= rowHeight + 10;
			contentStream.setFont(PDType1Font.HELVETICA, 5);

			float tableBottomY = startY - (applications.size() * rowHeight);
			float tableRightX = startX + tableWidth;

			for (AwardApplication application : applications) {
				if (startY < margin + rowHeight) {
					page = new PDPage(PDRectangle.A4);
					document.addPage(page);
					startY = page.getMediaBox().getHeight() - margin;
				}

				LOGGER.info("For Loop  ::: ");

				xPosition = startX;
				String sportName = Validator.isNotNull(application.getSportId())
						&& Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(application.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(application.getSportId()).getName_en()
								: "N/A";

				String compLevel = Validator.isNotNull(application.getCompetitionLevelId())
						&& Validator.isNotNull(CompetitionLevelMasterLocalServiceUtil
								.getCompetitionLevelMaster(application.getCompetitionLevelId()))
										? CompetitionLevelMasterLocalServiceUtil
												.getCompetitionLevelMaster(application.getCompetitionLevelId())
												.getName_en()
										: "N/A";

				LOGGER.info("Date : " + application.getCompetitionStartDate());
				LOGGER.info("String Date : "+formatDate(application.getCompetitionStartDate()));

				String[] rowData = { formatDate(application.getCompetitionStartDate()), sportName, compLevel,
						application.getCompetitionName() != null ? application.getCompetitionName() : "N/A",
						application.getMedalRecieved() != null ? application.getMedalRecieved() : "N/A",
						application.getParticipationYear() != null ? application.getParticipationYear() : "N/A",
						application.getAwardPointByDeskOff() != null ? application.getAwardPointByDeskOff() : "N/A",
						application.getOverallDeskOffRemarks() != null ? application.getOverallDeskOffRemarks() : "N/A" };

				try (PDPageContentStream contentStream1 = new PDPageContentStream(document, page,
						PDPageContentStream.AppendMode.APPEND, true)) {
					for (int i = 0; i < rowData.length; i++) {
						String cellText = rowData[i] != null ? rowData[i] : "";
						contentStream1.setFont(PDType1Font.HELVETICA, 5);
						contentStream1.beginText();
						contentStream1.newLineAtOffset(xPosition, startY);
						contentStream1.showText(cellText);
						contentStream1.endText();
						xPosition += columnWidths[i];
					}

					contentStream1.moveTo(startX, startY - 5);
					contentStream1.lineTo(startX + tableWidth, startY - 5);
					contentStream1.stroke();
				} catch (IOException e) {
					LOGGER.error("Error while writing to PDF: ", e);
				}

				startY -= rowHeight;
			}

//			float colX = startX;
//			for (float columnWidth : columnWidths) {
//				contentStream.moveTo(colX, startY + (applications.size() * rowHeight) + 10);
//				contentStream.lineTo(colX, tableBottomY);
//				contentStream.stroke();
//				colX += columnWidth;
//			}
//
//			contentStream.moveTo(tableRightX, startY + (applications.size() * rowHeight) + 10);
//			contentStream.lineTo(tableRightX, tableBottomY);
//			contentStream.stroke();

		} catch (Exception e) {
			LOGGER.error("Error Message  ::: " + e.getMessage());
			LOGGER.error("Error  ::: " + e);
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			document.save(baos);

			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

			String fileName = "Report_" + timestamp + ".pdf";

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

			String downloadURL = DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, "");

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
