package com.mhdsys.profile.management.web.action;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.common.api.profile.ProfileAPI;
import com.mhdsys.common.api.profile.UserIdentifiable;
import com.mhdsys.common.pojo.profile.CoachAndSportPersonDTO;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.common.util.RoleUtil;
import com.mhdsys.common.utility.constants.RoleConstant;
import com.mhdsys.profile.management.web.constants.MhdsysProfileManagementWebPortletKeys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		property = {
			"javax.portlet.name=" + MhdsysProfileManagementWebPortletKeys.MHDSYSPROFILEMANAGEMENTWEB,
			"mvc.command.name=addSportsPersonAndCoachEntry"
		},
		service = MVCActionCommand.class
	)
public class UpdateProfileInformationEntryMVCAction extends BaseMVCActionCommand{
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Reference private FileUploadUtil fileUploadUtil;
	@Reference private ProfileAPI profileAPI;

	@SuppressWarnings("deprecation")
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("In UpdateProfileInformationEntryMVCAction ");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		if(Validator.isNull(user)) throw new Exception("User object is null.");
		boolean IsProfileUpdated = (boolean)user.getExpandoBridge().getAttribute("IsProfileUpdated");
		boolean isSportsPerson = RoleUtil.hasRole(user, RoleConstant.SPORTS_PERSON, themeDisplay.getCompanyId());
		boolean isSportsCoach = RoleUtil.hasRole(user, RoleConstant.COACH, themeDisplay.getCompanyId());
		
		if(!isSportsPerson && !isSportsCoach) {
			SessionErrors.add(actionRequest, PortalException.class);
			actionResponse.setRenderParameter("mvcPath", "/view.jsp");
			return;
		}
		UserIdentifiable dto = null;
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(),uploadPortletRequest);
		HashSet<Long> oldFiles = new HashSet<Long>();
		if(isSportsPerson || isSportsCoach) {
			CoachAndSportPersonDTO oldDTO = profileAPI.getByUserId(new CoachAndSportPersonDTO(), themeDisplay.getUserId());
			Date dateOfBirth = ParamUtil.getDate(actionRequest, "dateOfBirth",dateFormat);
			long birthCertificateEntryId = Validator.isNotNull(uploadPortletRequest.getFileName("birthCertificateFile")) 
				    ? fileUploadUtil.uploadFile(uploadPortletRequest, "birthCertificateFile", "Documents Related to profiles", serviceContext) 
				    : Validator.isNotNull(oldDTO) && oldDTO.getBirthCertificateEntryId() != 0
				        ? oldDTO.getBirthCertificateEntryId() 
				        : 0;
			if(Validator.isNotNull(oldDTO)) if(birthCertificateEntryId != oldDTO.getBirthCertificateEntryId() && oldDTO.getBirthCertificateEntryId() != 0) oldFiles.add(oldDTO.getBirthCertificateEntryId());

			
			Date[] coachingDateFrom = ParamUtil.getDateValues(actionRequest, "coachingDateFrom[]",dateFormat);
			Date[] coachingDateTo = ParamUtil.getDateValues(actionRequest, "coachingDateTo[]",dateFormat);
			JSONArray coachingDatesArray = JSONFactoryUtil.createJSONArray();
			for(int i=0; i<coachingDateFrom.length; i++) {
				try {
					Date fromDate = Validator.isNotNull(coachingDateFrom[i]) ? coachingDateFrom[i] : null;
					Date toDate = Validator.isNotNull(coachingDateTo[i]) ? coachingDateTo[i] : null;
					if(fromDate != null && toDate != null) {
						JSONObject coachingDate = JSONFactoryUtil.createJSONObject();
						coachingDate.put("from", dateFormat.format(fromDate));
						coachingDate.put("to", dateFormat.format(toDate));
						coachingDatesArray.put(coachingDate);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			String postalAddress = ParamUtil.getString(actionRequest, "postalAddress");
			String pinCode = ParamUtil.getString(actionRequest, "pinCode");
			
			long photoEntryId = Validator.isNotNull(uploadPortletRequest.getFileName("photoFile")) 
				    ? fileUploadUtil.uploadFile(uploadPortletRequest, "photoFile", "Profile Photo", serviceContext) 
				    : Validator.isNotNull(oldDTO) && oldDTO.getPhotoEntryId() != 0
				        ? oldDTO.getPhotoEntryId() 
				        : 0;
			if(Validator.isNotNull(oldDTO)) if(birthCertificateEntryId != oldDTO.getBirthCertificateEntryId() && oldDTO.getBirthCertificateEntryId() != 0) oldFiles.add(oldDTO.getBirthCertificateEntryId());
			long nationalityCertificateEntryId = Validator.isNotNull(uploadPortletRequest.getFileName("nationalityCertificateFile")) 
					    ? fileUploadUtil.uploadFile(uploadPortletRequest, "nationalityCertificateFile", "Documents Related to profiles", serviceContext) 
					    : Validator.isNotNull(oldDTO) && oldDTO.getNationalityCertificateEntryId() != 0
					        ? oldDTO.getNationalityCertificateEntryId() 
					        : 0;
			if(Validator.isNotNull(oldDTO)) if(nationalityCertificateEntryId != oldDTO.getNationalityCertificateEntryId() && oldDTO.getNationalityCertificateEntryId() != 0) oldFiles.add(oldDTO.getNationalityCertificateEntryId());
			boolean disabilityApplicable = Validator.isNotNull(ParamUtil.getString(actionRequest, "disabilityApplicable",StringPool.BLANK)) ? ParamUtil.getString(actionRequest, "disabilityApplicable",StringPool.BLANK).trim().equalsIgnoreCase("yes") ? true : false : false;
			
			long disabilityCertificateEntryId = Validator.isNotNull(uploadPortletRequest.getFileName("disabilityCertificateFile")) 
					    ? fileUploadUtil.uploadFile(uploadPortletRequest, "disabilityCertificateFile", "Documents Related to profiles", serviceContext) 
					    : Validator.isNotNull(oldDTO) && oldDTO.getDisabilityCertificateEntryId() != 0
					        ? oldDTO.getDisabilityCertificateEntryId() 
					        : 0;
			if(Validator.isNotNull(oldDTO)) if(disabilityCertificateEntryId != oldDTO.getDisabilityCertificateEntryId() && oldDTO.getDisabilityCertificateEntryId() != 0) oldFiles.add(oldDTO.getDisabilityCertificateEntryId());
			String qualification = ParamUtil.getString(actionRequest, "qualification");
			String typeofJobBusiness = ParamUtil.getString(actionRequest, "typeofJobBusiness");
			String Designation = ParamUtil.getString(actionRequest, "Designation");
			String officeAddress = ParamUtil.getString(actionRequest, "officeAddress");
			
			boolean isAnyCrimeRegistered = Validator.isNotNull(ParamUtil.getString(actionRequest, "isAnyCrimeRegistered",StringPool.BLANK)) ? ParamUtil.getString(actionRequest, "isAnyCrimeRegistered",StringPool.BLANK).trim().equalsIgnoreCase("yes") ? true : false : false;
			String policeStationName = ParamUtil.getString(actionRequest, "policeStationName");
			String policeStationAddress = ParamUtil.getString(actionRequest, "policeStationAddress");
			Date dateOfRegistration = ParamUtil.getDate(actionRequest, "dateOfRegistration",dateFormat);
			long characterCertificateEntryId = Validator.isNotNull(uploadPortletRequest.getFileName("characterCertificateFile")) 
					    ? fileUploadUtil.uploadFile(uploadPortletRequest, "characterCertificateFile", "Documents Related to profiles", serviceContext) 
					    : Validator.isNotNull(oldDTO) && oldDTO.getCharacterCertificateEntryId() != 0
					        ? oldDTO.getCharacterCertificateEntryId() 
					        : 0;
			if(Validator.isNotNull(oldDTO)) if(characterCertificateEntryId != oldDTO.getCharacterCertificateEntryId() && oldDTO.getCharacterCertificateEntryId() != 0) oldFiles.add(oldDTO.getCharacterCertificateEntryId());
			String bankName = ParamUtil.getString(actionRequest, "bankName");
			String branchName = ParamUtil.getString(actionRequest, "branchName");
			String accountNumber = ParamUtil.getString(actionRequest, "accountNumber");
			String IFSCcode = ParamUtil.getString(actionRequest, "IFSCcode");
			boolean acknowledgement = ParamUtil.getBoolean(actionRequest, "acknowledgement",false);
			String roleName = ParamUtil.getString(actionRequest, "roleName");
			
			long cancelledChequeEntryId = Validator.isNotNull(uploadPortletRequest.getFileName("cancelledChequeFile")) 
				    ? fileUploadUtil.uploadFile(uploadPortletRequest, "cancelledChequeFile", "Documents Related to profiles", serviceContext) 
				    : Validator.isNotNull(oldDTO) && oldDTO.getCancelledChequeEntryId() != 0
				        ? oldDTO.getCancelledChequeEntryId() 
				        : 0;
			if(Validator.isNotNull(oldDTO)) if(cancelledChequeEntryId != oldDTO.getCancelledChequeEntryId() && oldDTO.getCancelledChequeEntryId() != 0) oldFiles.add(oldDTO.getCancelledChequeEntryId());
			dto = new CoachAndSportPersonDTO();
			((CoachAndSportPersonDTO) dto).setDateOfBirth(dateOfBirth);
			((CoachAndSportPersonDTO) dto).setBirthCertificateEntryId(birthCertificateEntryId);
			((CoachAndSportPersonDTO) dto).setCoachingDates(coachingDatesArray.toString());
			((CoachAndSportPersonDTO) dto).setPostalAddress(postalAddress);
			((CoachAndSportPersonDTO) dto).setPinCode(pinCode);
			((CoachAndSportPersonDTO) dto).setPhotoEntryId(photoEntryId);
			((CoachAndSportPersonDTO) dto).setNationalityCertificateEntryId(nationalityCertificateEntryId);
			((CoachAndSportPersonDTO) dto).setDisabilityApplicable(disabilityApplicable);
			((CoachAndSportPersonDTO) dto).setDisabilityCertificateEntryId(disabilityCertificateEntryId);
			((CoachAndSportPersonDTO) dto).setQualification(qualification);
			((CoachAndSportPersonDTO) dto).setTypeofJobBusiness(typeofJobBusiness);
			((CoachAndSportPersonDTO) dto).setDesignation(Designation);
			((CoachAndSportPersonDTO) dto).setOfficeAddress(officeAddress);
			((CoachAndSportPersonDTO) dto).setAnyCrimeRegistered(isAnyCrimeRegistered);
			((CoachAndSportPersonDTO) dto).setPoliceStationName(policeStationName);
			((CoachAndSportPersonDTO) dto).setPoliceStationAddress(policeStationAddress);
			((CoachAndSportPersonDTO) dto).setDateOfRegistration(dateOfRegistration);
			((CoachAndSportPersonDTO) dto).setCharacterCertificateEntryId(characterCertificateEntryId);
			((CoachAndSportPersonDTO) dto).setBankName(bankName);
			((CoachAndSportPersonDTO) dto).setBranchName(branchName);
			((CoachAndSportPersonDTO) dto).setAccountNumber(accountNumber);
			((CoachAndSportPersonDTO) dto).setIFSCcode(IFSCcode);
			((CoachAndSportPersonDTO) dto).setAcknowledgement(acknowledgement);
			((CoachAndSportPersonDTO) dto).setUserId(user.getUserId());
			((CoachAndSportPersonDTO) dto).setRoleName(roleName);
			((CoachAndSportPersonDTO) dto).setVideoLink(roleName.equalsIgnoreCase("Adventure Person") ? ParamUtil.getString(actionRequest, "videoLink",StringPool.BLANK) : StringPool.BLANK);
			((CoachAndSportPersonDTO) dto).setCancelledChequeEntryId(cancelledChequeEntryId);
		}
		
		if(Validator.isNotNull(dto)) {
			CoachAndSportPersonDTO validatedDTO = ((CoachAndSportPersonDTO) dto).validateFields();
			if(Validator.isNotNull(validatedDTO)) {
				UserIdentifiable updateProfileInformation = profileAPI.updateProfileInformation(validatedDTO, user.getUserId());
				System.out.println("updateProfileInformation : "+updateProfileInformation.toString());
				if(!IsProfileUpdated) {
					if(Validator.isNotNull(((CoachAndSportPersonDTO) updateProfileInformation).validateFields())) {
						user.getExpandoBridge().setAttribute("IsProfileUpdated", true);
						UserLocalServiceUtil.updateUser(user);
						actionResponse.sendRedirect("/group/guest/dashboard");
						return;
					}
				}
//				if(Validator.isNotNull(updateProfileInformation)) {
//					if(oldFiles.size() > 0) {
//						try {
//							System.out.println("oldFiles : "+oldFiles.toString());
//							fileUploadUtil.deleteFileEntries(oldFiles);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}
			}else {
				SessionErrors.add(actionRequest, PortalException.class);
			}
		}else {
			SessionErrors.add(actionRequest, PortalException.class);
		}
		
		actionResponse.setRenderParameter("mvcPath", "/profiles/sportPersonAndCoach.jsp");
		return;
	}

}
