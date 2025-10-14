package com.mhdsys.budget.web.resource;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.util.DLURLHelperUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys;
import com.mhdsys.schema.model.Budget;
import com.mhdsys.schema.service.BudgetLocalServiceUtil;
import com.mhdsys.schema.service.CommittedMasterLocalServiceUtil;
import com.mhdsys.schema.service.SchemeMasterLocalServiceUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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

@Component(immediate = true, property = { "javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSBUDGETWEB,
		"javax.portlet.name=" + MhdsysBudgetWebPortletKeys.MHDSYSDOWNLOADREPORTS,
		"mvc.command.name=" + MhdsysBudgetWebPortletKeys.DOWNLOADREPORT }, service = MVCResourceCommand.class)

public class DownloadReportMVCResourceCommand implements MVCResourceCommand {

	private Log LOGGER = LogFactoryUtil.getLog(DownloadReportMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		LOGGER.info("Generating Report PDF...");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String financialYear = ParamUtil.getString(resourceRequest, "financialYear");
		String category = ParamUtil.getString(resourceRequest, "category");

		LOGGER.info("YEAR and Catgeory :::   " + category + " : " + financialYear);

		List<Budget> budgets = new ArrayList();

		if (category.equalsIgnoreCase("Consolidate")) {
			budgets = BudgetLocalServiceUtil.getByFinancialYear(financialYear);
		} else if (category.equalsIgnoreCase("Schemes")) {
			LOGGER.info("CAtegory CAtegory ::::   " + " Schemes " + category);
			budgets = BudgetLocalServiceUtil.getByFinancialYearAndCategory(financialYear, category);

			LOGGER.info("SIZE :::   " + budgets.size());
		} else if (category.equalsIgnoreCase("Committed")) {
			LOGGER.info("CAtegory CAtegory ::::   " + " Committed " + category);
			budgets = BudgetLocalServiceUtil.getByFinancialYearAndCategory(financialYear, category);
			LOGGER.info("SIZE :::   " + budgets.size());
		}

		PDDocument document = new PDDocument();
		PDPage page = new PDPage(PDRectangle.A4);
		document.addPage(page);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try (PDPageContentStream contentStream = new PDPageContentStream(document, page,
				PDPageContentStream.AppendMode.APPEND, true, true)) {

			float margin = 40;
			float startX = margin;
			float startY = page.getMediaBox().getHeight() - margin;
			float rowHeight = 20;
			float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

			startY -= 40;

			if (category.equalsIgnoreCase("Schemes") || category.equalsIgnoreCase("Committed")) {

				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

				float pageWidth = page.getMediaBox().getWidth();
				float rightMargin = 40;

				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Sports Department 2204 - Sports and Youth Services");
				contentStream.endText();

				String dateText = "Date: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				float dateWidth = (PDType1Font.HELVETICA_BOLD).getStringWidth(dateText) / 1000 * 12;
				contentStream.beginText();
				contentStream.newLineAtOffset(pageWidth - rightMargin - dateWidth, startY);
				contentStream.showText(dateText);
				contentStream.endText();

				startY -= 15;
				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Programme Expenditure Plan Summary for the year 2025-26");
				contentStream.endText();

				String rupeesText = "Rupees in lakhs";
				float rupeesWidth = (PDType1Font.HELVETICA_BOLD).getStringWidth(rupeesText) / 1000 * 12;
				contentStream.beginText();
				contentStream.newLineAtOffset(pageWidth - rightMargin - rupeesWidth, startY);
				contentStream.showText(rupeesText);
				contentStream.endText();

				startY -= 30;

				float[] columnWidths = { 140, 80, 100, 80, 80, 80 };
				String[] headers = { "Scheme Name", "Sanction Fund", "Supplementary Demand", "Total Fund",
						"Fund Received", "Expense" };

				contentStream.setNonStrokingColor(200, 200, 200);
				contentStream.addRect(startX, startY - rowHeight, tableWidth, rowHeight);
				contentStream.fill();
				contentStream.setNonStrokingColor(0, 0, 0);

				float textX = startX + 5;
				float textY = startY - (rowHeight / 1.5f);
				for (int i = 0; i < headers.length; i++) {
					contentStream.beginText();
					contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
					contentStream.newLineAtOffset(textX, textY);
					contentStream.showText(headers[i]);
					contentStream.endText();
					textX += columnWidths[i];
				}

				startY -= rowHeight + 10;

				contentStream.setFont(PDType1Font.HELVETICA, 8);
				int srNo = 1;
				for (Budget budget : budgets) {

					String master = "";

					if (category.equalsIgnoreCase("Schemes")) {
						master = SchemeMasterLocalServiceUtil.getSchemeMaster(Long.valueOf(budget.getMaster()))
								.getSchemeName_en();
					} else if (category.equalsIgnoreCase("Committed")) {
						master = CommittedMasterLocalServiceUtil.getCommittedMaster(Long.valueOf(budget.getMaster()))
								.getCommittedName_en();
					}

					String[] rowData = { master, budget.getApprovedBudget(), budget.getSupplementaryDemand(),
							budget.getTotalFund(), budget.getRecievedAmount(), budget.getTotalSpend() };

					float dataX = startX + 2;
//					for (int i = 0; i < rowData.length; i++) {
////						contentStream.beginText();
////						contentStream.newLineAtOffset(dataX, startY);
////						contentStream.showText(rowData[i] != null ? rowData[i] : "0.00");
////						contentStream.endText();
//						
//						if (i == 0) { 
//						    drawWrappedText(contentStream, rowData[i] != null ? rowData[i] : "", dataX, startY, columnWidths[i] - 5);
//						} else {
//						    contentStream.beginText();
//						    contentStream.newLineAtOffset(dataX, startY);
//						    contentStream.showText(rowData[i] != null ? rowData[i] : "0.00");
//						    contentStream.endText();
//						}
//
//						
//						
//						dataX += columnWidths[i];
//					}
//
//					startY -= rowHeight;
					
					int maxLines = 1; // track the tallest cell in the row
					float cellStartY = startY;

					for (int i = 0; i < rowData.length; i++) {
					    if (i == 0) { // first column (Scheme/Committed name)
					        int lines = drawWrappedText(contentStream, rowData[i] != null ? rowData[i] : "", dataX, cellStartY, columnWidths[i] - 5);
					        maxLines = Math.max(maxLines, lines);
					    } else {
					        contentStream.beginText();
					        contentStream.setFont(PDType1Font.HELVETICA, 8);
					        contentStream.newLineAtOffset(dataX, cellStartY);
					        contentStream.showText(rowData[i] != null ? rowData[i] : "0.00");
					        contentStream.endText();
					    }
					    dataX += columnWidths[i];
					}

					// after drawing row, reduce Y by row height * maxLines
					startY -= (rowHeight * maxLines);

				}

			} else if (category.equalsIgnoreCase("Consolidate")) {

				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

				float pageWidth = page.getMediaBox().getWidth();
				float rightMargin = 40;

				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Sports Department 2204 - Sports and Youth Services");
				contentStream.endText();

				String dateText = "Date: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date());
				float dateWidth = (PDType1Font.HELVETICA_BOLD).getStringWidth(dateText) / 1000 * 12;
				contentStream.beginText();
				contentStream.newLineAtOffset(pageWidth - rightMargin - dateWidth, startY);
				contentStream.showText(dateText);
				contentStream.endText();

				startY -= 15;
				contentStream.beginText();
				contentStream.newLineAtOffset(startX, startY);
				contentStream.showText("Programme Expenditure Plan Summary for the year 2025-26");
				contentStream.endText();

				String rupeesText = "Rupees in lakhs";
				float rupeesWidth = (PDType1Font.HELVETICA_BOLD).getStringWidth(rupeesText) / 1000 * 12;
				contentStream.beginText();
				contentStream.newLineAtOffset(pageWidth - rightMargin - rupeesWidth, startY);
				contentStream.showText(rupeesText);
				contentStream.endText();

				startY -= 30;
				float[] columnWidths = { 140, 80, 100, 80, 80, 80 };
				String[] headers = { "Type of Scheme", "Sanction Fund", "Supplementary Demand", "Overall Approved",
						"Fund Received", "Expense" };

				contentStream.setNonStrokingColor(200, 200, 200);
				contentStream.addRect(startX, startY - rowHeight, tableWidth, rowHeight);
				contentStream.fill();
				contentStream.setNonStrokingColor(0, 0, 0);

				float textX = startX + 5;
				float textY = startY - (rowHeight / 1.5f);
				for (int i = 0; i < headers.length; i++) {
					contentStream.beginText();
					contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
					contentStream.newLineAtOffset(textX, textY);
					contentStream.showText(headers[i]);
					contentStream.endText();
					textX += columnWidths[i];
				}
				startY -= rowHeight + 10;

//				String schemesSanctioned = "0", schemesSupp = "0", schemesApproved = "0", schemesReceived = "0",
//						schemesExpense = "0";
//				String committedSanctioned = "0", committedSupp = "0", committedApproved = "0", committedReceived = "0",
//						committedExpense = "0";
//
//				for (Budget budget : budgets) {
//					if ("Schemes".equalsIgnoreCase(budget.getCategory())) {
//					 	schemesSanctioned += budget.getApprovedBudget();
//						schemesSupp += budget.getSupplementaryDemand();
//						schemesApproved += budget.getTotalFund();
//						schemesReceived += budget.getRecievedAmount();
//						schemesExpense += budget.getTotalSpend();
//					} else if ("Committed".equalsIgnoreCase(budget.getCategory())) {
//						committedSanctioned += budget.getApprovedBudget();
//						committedSupp += budget.getSupplementaryDemand();
//						committedApproved += budget.getTotalFund();
//						committedReceived += budget.getRecievedAmount();
//						committedExpense += budget.getTotalSpend();
//					}
//				}

				double schemesSanctioned = 0, schemesSupp = 0, schemesApproved = 0, schemesReceived = 0,
						schemesExpense = 0;
				double committedSanctioned = 0, committedSupp = 0, committedApproved = 0, committedReceived = 0,
						committedExpense = 0;

				for (Budget budget : budgets) {
					try {

						double suppTotal = 0;
						double supplementaryDemand = 0;
						if (Validator.isNotNull(budget.getSupplementaryDemand())) {
							String suppStr = budget.getSupplementaryDemand();

							String[] parts = suppStr.split(",");
							for (String p : parts) {
								try {
									suppTotal += Double.parseDouble(p.trim());
								} catch (NumberFormatException e) {
									LOGGER.error("Invalid number in supplementaryDemand: " + p);
								}
							}
							supplementaryDemand = +suppTotal;
						}

						double approvedBudget = Double.parseDouble(budget.getApprovedBudget());
						double totalFund = Double.parseDouble(budget.getTotalFund());
						double recievedAmount = Double.parseDouble(budget.getRecievedAmount());
						double totalSpend = Double.parseDouble(budget.getTotalSpend());

						LOGGER.info("APPROVED BUDGET / FUNDS   ::::   " + approvedBudget);

						if ("Schemes".equalsIgnoreCase(budget.getCategory())) {
							schemesSanctioned += approvedBudget;
							schemesSupp += supplementaryDemand;
							schemesApproved += totalFund;
							schemesReceived += recievedAmount;
							schemesExpense += totalSpend;
							LOGGER.info("##########################################");
							LOGGER.info("SANCTIONED SCHEMES :::::   " + schemesSanctioned);
						} else if ("Committed".equalsIgnoreCase(budget.getCategory())) {
							committedSanctioned += approvedBudget;
							committedSupp += supplementaryDemand;
							committedApproved += totalFund;
							committedReceived += recievedAmount;
							committedExpense += totalSpend;
						}
					} catch (NumberFormatException e) {
						LOGGER.error("Invalid number in budget: " + e.getMessage());
					}
				}

				String[][] rows = {
						{ "Mandatory spending plan total", String.valueOf(schemesSanctioned),
								String.valueOf(schemesSupp), String.valueOf(schemesApproved),
								String.valueOf(schemesReceived), String.valueOf(schemesExpense) },

						{ "Program Expenditure Plan Total", String.valueOf(committedSanctioned),
								String.valueOf(committedSupp), String.valueOf(committedApproved),
								String.valueOf(committedReceived), String.valueOf(committedExpense) },

						{ "Sports and Youth Services Total :", String.valueOf(schemesSanctioned + committedSanctioned),
								String.valueOf(schemesSupp + committedSupp),
								String.valueOf(schemesApproved + committedApproved),
								String.valueOf(schemesReceived + committedReceived),
								String.valueOf(schemesExpense + committedExpense) } };

				contentStream.setFont(PDType1Font.HELVETICA, 8);
				for (String[] rowData : rows) {
					float dataX = startX + 2;
					for (int i = 0; i < rowData.length; i++) {
						contentStream.beginText();
						contentStream.newLineAtOffset(dataX, startY);
						contentStream.showText(rowData[i] != null ? rowData[i] : "0.00");
						contentStream.endText();
						dataX += columnWidths[i];
					}
					startY -= rowHeight;
				}
			}

			contentStream.close();
			document.save(outputStream);

			byte[] pdfBytes = outputStream.toByteArray();
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = "Report_" + timestamp + ".pdf";

			File tempFile = File.createTempFile("report_", ".pdf");
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
			LOGGER.error("Error generating table in PDF", e);
		}

		return true;
	}

	private String formatNumber(Double value) {
		if (value == null)
			return "0.00";
		return new DecimalFormat("#,##0.00").format(value);
	}

	private static long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

	public Long getFolder(Long groupId) {
		Folder folder = null;
		try {
			folder = DLAppServiceUtil.getFolder(groupId, PARENT_FOLDER_ID, "Report");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return folder.getFolderId();
	}

	@SuppressWarnings("deprecation")
	public Long fileUpload(java.io.File file, String filename, long groupId) {
		String mimeType = "application/pdf";
		String title = filename + ".pdf";
		String description = "This file is added programmatically";
		Long folderId = getFolder(groupId);
		try {
			FileEntry entry = DLAppServiceUtil.addFileEntry(groupId, folderId, title, mimeType, title, description, "",
					file, new ServiceContext());
			return entry.getFileEntryId();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return 0L;
	}

	public static String formatDate(Date date) {
		if (date == null) {
			return "N/A";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}

//	private void drawWrappedText(PDPageContentStream contentStream, String text, float x, float y, float maxWidth)
//			throws IOException {
//		List<String> lines = new ArrayList<>();
//		String[] words = text.split(" ");
//		StringBuilder line = new StringBuilder();
//
//		for (String word : words) {
//			String testLine = line.length() == 0 ? word : line + " " + word;
//			float size = PDType1Font.HELVETICA.getStringWidth(testLine) / 1000 * 8; // 8 is font size
//			if (size > maxWidth) {
//				lines.add(line.toString());
//				line = new StringBuilder(word);
//			} else {
//				line = new StringBuilder(testLine);
//			}
//		}
//		lines.add(line.toString());
//
//		for (String l : lines) {
//			contentStream.beginText();
//			contentStream.setFont(PDType1Font.HELVETICA, 8);
//			contentStream.newLineAtOffset(x, y);
//			contentStream.showText(l);
//			contentStream.endText();
//			y -= 10; // line spacing
//		}
//	}
	
	private int drawWrappedText(PDPageContentStream contentStream, String text, float x, float y, float maxWidth) throws IOException {
	    List<String> lines = new ArrayList<>();
	    String[] words = text.split(" ");
	    StringBuilder line = new StringBuilder();

	    for (String word : words) {
	        String testLine = line.length() == 0 ? word : line + " " + word;
	        float size = PDType1Font.HELVETICA.getStringWidth(testLine) / 1000 * 8; // font size = 8
	        if (size > maxWidth) {
	            lines.add(line.toString());
	            line = new StringBuilder(word);
	        } else {
	            line = new StringBuilder(testLine);
	        }
	    }
	    lines.add(line.toString());

	    for (String l : lines) {
	        contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA, 8);
	        contentStream.newLineAtOffset(x, y);
	        contentStream.showText(l);
	        contentStream.endText();
	        y -= 10; // line spacing
	    }

	    return lines.size(); // return how many lines were written
	}


}
