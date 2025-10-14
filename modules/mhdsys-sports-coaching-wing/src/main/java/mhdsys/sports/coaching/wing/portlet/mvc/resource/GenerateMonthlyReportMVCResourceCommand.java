package mhdsys.sports.coaching.wing.portlet.mvc.resource;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.CoachingWingMonthlyReport;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.CoachingWingMonthlyReportLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;

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

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;
@Component(immediate = true, property = { 
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name=" + SportsCoachinWingMvcCommand.GENERATE_MONTHLY_REPORT }, service = MVCResourceCommand.class)
public class GenerateMonthlyReportMVCResourceCommand implements MVCResourceCommand {

	public static final Log _log = LogFactoryUtil.getLog(GenerateMonthlyReportMVCResourceCommand.class.getName());

	@Reference
	DateConversionUtil dateConversionUtil;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		_log.info("Generate Report Resource Command ::: ::: ");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long reportId = ParamUtil.getLong(resourceRequest, "reportId");
		//long applicationId = ParamUtil.getLong(resourceRequest, "applicationId");
		_log.info("reportId  ::: " + reportId);
		//_log.info("Application ID ::: " + applicationId);

		CoachingWingMonthlyReport wingReport =null;
		try {
			wingReport=CoachingWingMonthlyReportLocalServiceUtil.getCoachingWingMonthlyReport(reportId);
		} catch (Exception e) {
			_log.error("Error in code :::" +e);
		}
		_log.info("wingReport::: " + wingReport);
		
		
		//Code change for the financial details reports
		
		//List<DistrictMaster> districtList = DistrictMasterLocalServiceUtil.getByActiveState(Boolean.TRUE);
		
		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A4);
		document.addPage(page);

		try (PDPageContentStream contentStream = new PDPageContentStream(document, page,
				PDPageContentStream.AppendMode.APPEND, true, true)) {

			_log.info("PDF Generation  ::: ");

			User user = UserLocalServiceUtil.getUser(43286);
			//AwardApplication applicatAwardApplication = AwardApplicationLocalServiceUtil
			//		.getAwardApplication(applicationId);
			Map<Integer, String> genderMap = Map.of(1, "Male", 2, "Female");
			//String genderName = genderMap.getOrDefault(applicatAwardApplication.getGender(), "N/A");
			String genderName = genderMap.getOrDefault("male", "N/A");

			/*
			 * String category = Validator.isNotNull(applicatAwardApplication.getCategory())
			 * && Validator .isNotNull(CategoryMasterLocalServiceUtil.getCategoryMaster(
			 * applicatAwardApplication.getCategory())) ?
			 * CategoryMasterLocalServiceUtil.getCategoryMaster(applicatAwardApplication.
			 * getCategory()) .getName() : "N/A";
			 */
			
			String category = "test category";

			float margin = 50;
			float startX = margin;
			float startY = page.getMediaBox().getHeight() - margin;
			float rowHeight = 200;
			float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
			//float[] columnWidths = { 40, 50, 50, 150, 50, 40, 40, 96 };
			
			
			_log.info("tableWidth ::::" +tableWidth);
			
			
			//float[] columnWidths = {30,200, 200, 150, 200, 200, 200, 200 ,200,200,200,200,200,200 };
			float[] columnWidths = {40,40,45,30, 45, 45, 45, 45 ,45,45,45,45,45,45 };

			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
			contentStream.beginText();
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText("Sports Coaching Wing Monthly Report, 2025");
			contentStream.endText();

			startY -= 30;
			contentStream.beginText();
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText("Report Chart");
			contentStream.endText();

			startY -= 40;
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.beginText();
			contentStream.newLineAtOffset(startX, startY);
			contentStream.showText("Name of Coach: " + wingReport.getCoachName());
			contentStream.newLineAtOffset(0, -rowHeight);
			
			//contentStream.showText("Player/Coach ID: " + reportId);
			//contentStream.newLineAtOffset(300, rowHeight);
			//contentStream.showText("Category: " + category);
			//contentStream.newLineAtOffset(0, -rowHeight);
			//contentStream.showText("Gender: " + genderName);
			contentStream.endText();

			startY -= 40;
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 5);
			float xPosition = startX;
			String[] headers = { 
					"Timestamp",
					"Report month",
					"Name of coach",
					"District Name",
					"Educational qualifications",
					"Sports Name",
					"Training center location/place",
					"Number of players at the training center",
					"Performance of players - International/National/State/Division/District",
					"Training Program (Annual/Quarterly/Monthly)",
					"Has the analysis of the players' performance been done, and if so, what are the methods - physical/mental/medical?",
					"What planning have you done to increase the performance of the best players at the training center?",
					"Your idea about the ideal sports training center",
					"Have you received any kind of assistance from the Sports Science Center at your training center, and what efforts have you made about this?"
				};

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
			 float lineHeight = 15;
			for (int i = 0; i < headers.length; i++) {
				
				contentStream.moveTo(columnX, startY);
				contentStream.lineTo(columnX, startY - rowHeight);
				contentStream.stroke();

				contentStream.beginText();
				contentStream.newLineAtOffset(textX, textY);
				
				//new changes
				 if(headers[i].length()>60){
					String firstLine=headers[i].substring(0,11);
					String secondLine = headers[i].substring(11,25);
					String thirdLine = headers[i].substring(25,40);
					String forthLine = headers[i].substring(40,headers[i].length());
					 contentStream.showText(firstLine);
					 contentStream.newLineAtOffset(0, -lineHeight);
					 contentStream.showText(secondLine);
					 contentStream.newLineAtOffset(0, -lineHeight);
					 contentStream.showText(thirdLine);
					 contentStream.newLineAtOffset(0, -lineHeight);
					 contentStream.showText(forthLine);
				}if(headers[i].length()>20){
					String firstLine=headers[i].substring(0,11);
					String secondLine = headers[i].substring(11);
					 contentStream.showText(firstLine);
					 contentStream.newLineAtOffset(0, -lineHeight);
					 contentStream.showText(secondLine);
				}else {
					contentStream.showText(headers[i]);
				}
				
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

			float tableBottomY = startY - (1 * rowHeight);
			float tableRightX = startX + tableWidth;

			//for (DistrictMaster district : districtList) {
				if (startY < margin + rowHeight) {
					page = new PDPage(PDRectangle.A4);
					document.addPage(page);
					startY = page.getMediaBox().getHeight() - margin;
				}

				_log.info("For Loop  ::: ");

				xPosition = startX;
				/*String sportName = Validator.isNotNull(application.getSportId())
						&& Validator.isNotNull(SportsMasterLocalServiceUtil.getSportsMaster(application.getSportId()))
								? SportsMasterLocalServiceUtil.getSportsMaster(application.getSportId()).getName_en()
								: "N/A";*/
				
				String sportName = "test";

				String compLevel = "test";
				
				/*String compLevel = Validator.isNotNull(application.getCompetitionLevelId())
						&& Validator.isNotNull(CompetitionLevelMasterLocalServiceUtil
								.getCompetitionLevelMaster(application.getCompetitionLevelId()))
										? CompetitionLevelMasterLocalServiceUtil
												.getCompetitionLevelMaster(application.getCompetitionLevelId())
												.getName_en()
										: "N/A";*/

				//_log.info("String Date : "+formatDate(new Date()));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String[] rowData = { sdf.format(wingReport.getTimestamp()),
									wingReport.getReportMonth(),
									wingReport.getCoachName(),
									wingReport.getDistrict(),
									wingReport.getEducationalQualification(),
									wingReport.getSportsName(),
									wingReport.getTrainingCenterPlace(),
									wingReport.getNoOfPlayers(),
									wingReport.getPerformanceOfThePlayers(),
									wingReport.getTrainingProgram(),
									wingReport.getPlayerAnalysisMethod(),
									wingReport.getPlanningForBestPlayers(),
									wingReport.getIdea(),
									wingReport.getAssistanceAndEffort()
									
				};

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
					_log.error("Error while writing to PDF: ", e);
				}

				startY -= rowHeight;
			//}

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
			_log.error("Error Message  ::: " + e.getMessage());
			_log.error("Error  ::: " + e);
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

			_log.info("Download URL : " + downloadURL);

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
			_log.info(folder);

		} catch (Exception e) {
			_log.info(e.getMessage());
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
			_log.info(e.getMessage());
			_log.error(e);
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
