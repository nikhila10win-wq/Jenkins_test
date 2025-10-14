package com.mhdsys.administrative.web.resource.commands;

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
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys;
import com.mhdsys.schema.model.StudentRegistration;
import com.mhdsys.schema.service.StudentRegistrationLocalServiceUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTREGWEB,
		"javax.portlet.name=" + MhdsysAdministrativeWebPortletKeys.MHDSYSSTUDENTLISTWEB, "mvc.command.name="
				+ MhdsysAdministrativeWebPortletKeys.GENERATE_ID_CARD_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)
public class GenerateIdCardMVCResourceCommand implements MVCResourceCommand {
	private static final Log LOGGER = LogFactoryUtil.getLog(GenerateIdCardMVCResourceCommand.class);
	private static final long PARENT_FOLDER_ID = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		LOGGER.info("Generating Student ID Card PDF...");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			long studentRegistrationId = ParamUtil.getLong(resourceRequest, "studentRegistrationId");
			LOGGER.info("studentRegistrationId: " + studentRegistrationId);

			StudentRegistration student = StudentRegistrationLocalServiceUtil
					.getStudentRegistration(studentRegistrationId);

			PDDocument document = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A6);
			document.addPage(page);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
				int y = 380;
				int gap = 20;
				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

				contentStream.beginText();
				contentStream.newLineAtOffset(50, y);
				contentStream.showText("Student ID Card");
				contentStream.endText();

				contentStream.beginText();
				contentStream.newLineAtOffset(40, y -= gap);
				contentStream.showText("First Name: " + student.getFirstName());
				contentStream.endText();

				contentStream.beginText();
				contentStream.newLineAtOffset(40, y -= gap);
				contentStream.showText("Last Name: " + student.getLastName());
				contentStream.endText();

				contentStream.beginText();
				contentStream.newLineAtOffset(40, y -= gap);
				contentStream.showText("Gender: " + student.getGender());
				contentStream.endText();

				contentStream.beginText();
				contentStream.newLineAtOffset(40, y -= gap);
				contentStream.showText("Standard: " + student.getStandard());
				contentStream.endText();

				contentStream.beginText();
				contentStream.newLineAtOffset(40, y -= gap);
				contentStream.showText("School: " + student.getSchoolCollegeName());
				contentStream.endText();

				contentStream.close();
			}

			document.save(outputStream);
			document.close();

			byte[] pdfBytes = outputStream.toByteArray();
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String fileName = "Student_ID_Card_" + timestamp + ".pdf";

			File tempFile = File.createTempFile("id_card_", ".pdf");
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
			LOGGER.error("Error generating student ID card PDF", e);
		}

		return true;
	}

	private Long getFolder(Long groupId) {
		try {
			Folder folder = DLAppServiceUtil.getFolder(groupId, PARENT_FOLDER_ID, "IDCards");
			return folder.getFolderId();
		} catch (Exception e) {
			LOGGER.info("Folder not found, defaulting to parent");
			return PARENT_FOLDER_ID;
		}
	}

	public Long fileUpload(File file, String filename, long groupId) {
		String mimeType = "application/pdf";
		String title = filename;
		String description = "Student ID Card PDF";
		Long folderId = getFolder(groupId);

		try {
			FileEntry entry = DLAppServiceUtil.addFileEntry(groupId, folderId, title, mimeType, title, description, "",
					file, new ServiceContext());
			return entry.getFileEntryId();
		} catch (Exception e) {
			LOGGER.error("Error uploading file", e);
		}
		return 0L;
	}
}
