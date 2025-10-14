package com.mhdsys.grievance.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.pojo.GrievanceCommonDTO;
import com.mhdsys.common.pojo.TransferApplicationCommonDTO;
import com.mhdsys.common.util.DateConversionUtil;
import com.mhdsys.common.util.FileUploadUtil;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.model.TransferApplication;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.GrievanceLocalServiceUtil;
import com.mhdsys.schema.service.TransferApplicationLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.ResourceRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = GrievanceUtil.class)

public class GrievanceUtil {
	@Reference
	DateConversionUtil dateConversionUtil;
	@Reference
	FileUploadUtil fileUploadUtil;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private Log LOGGER = LogFactoryUtil.getLog(GrievanceUtil.class);

	public GrievanceCommonDTO setGrievanceDTO(ResourceRequest resourceRequest, ThemeDisplay themeDisplay,
			long grievanceId) {
		GrievanceCommonDTO grievance = new GrievanceCommonDTO();

		try {
			grievance.setGrievanceId(grievanceId);

			// Administration Section
			grievance.setDivision(ParamUtil.getLong(resourceRequest, "division"));
			grievance.setDistrict(ParamUtil.getLong(resourceRequest, "district"));
			grievance.setTaluka(ParamUtil.getLong(resourceRequest, "taluka"));

			// Complaint Details
			String complaintDateStr = ParamUtil.getString(resourceRequest, "complaintDate");
			if (Validator.isNotNull(complaintDateStr)) {
				grievance.setComplaintDate(dateConversionUtil.convertStringToDateFormat(complaintDateStr));
			}

			grievance.setComplaintType(ParamUtil.getString(resourceRequest, "complaintType"));
			grievance.setComplaintText(ParamUtil.getString(resourceRequest, "complaintText"));

			// Self-Declaration
			grievance.setSelfDeclaration(ParamUtil.getBoolean(resourceRequest, "selfDeclaration"));

			// Audit Fields
			grievance.setUserId(themeDisplay.getUserId());
			grievance.setCreateDate(new Date());
			grievance.setModifiedDate(new Date());

		} catch (Exception e) {
			LOGGER.error("Error while setting Grievance DTO: ", e);
		}

		return grievance;
	}

//	public TransferApplicationCommonDTO setTransferApplication(ResourceRequest resourceRequest,
//			ThemeDisplay themeDisplay, long transferApplicationId) {
//		TransferApplicationCommonDTO dto = new TransferApplicationCommonDTO();
//
//		try {
//			dto.setTransferApplicationId(transferApplicationId);
//			dto.setGrievanceId(ParamUtil.getLong(resourceRequest, "grievanceId"));
//			Grievance grievance = GrievanceLocalServiceUtil.getGrievance(dto.getGrievanceId());
//			LOGGER.info("grievanceId: " + dto.getGrievanceId());
//			LOGGER.info("dso or tso: " + ParamUtil.getString(resourceRequest, "transferTo"));
//			boolean transferTo = ParamUtil.getBoolean(resourceRequest, "transferTo");
//
//			boolean isHOAdmin = ParamUtil.getBoolean(resourceRequest, "isHOAdmin");
//			boolean isDSO = ParamUtil.getBoolean(resourceRequest, "isDSO");
//			boolean isTSO = ParamUtil.getBoolean(resourceRequest, "isTSO");
//			DistrictMaster districtMaster = DistrictMasterLocalServiceUtil.getDistrictMaster(grievance.getDistrict());
//			districtMaster.getDistrictName_en();
//			if (!transferTo) {
//				dto.setTransferTo(grievance.getTaluka());
//				dto.setTransfer("TSO-"+grievance.getTaluka());
//			} else {
//				dto.setTransferTo(grievance.getDistrict());
//				dto.setTransfer(districtMaster.getDistrictName_en()+"-DSO");
//			}
//			dto.setDistrict(grievance.getDistrict());
//			dto.setTaluka(grievance.getTaluka());
//			String solution = ParamUtil.getString(resourceRequest, "solution");
//			String review = ParamUtil.getString(resourceRequest, "review");
//			// Set solution/review based on role
//			if (isHOAdmin) {
//				dto.setSolutionByHo(solution);
//				dto.setReviewByHo(review);
//			} else if (isDSO) {
//				dto.setSolutionByDSO(solution);
//				dto.setReviewByDSO(review);
//			} else if (isTSO) {
//				dto.setSolutionByTSO(solution);
//				dto.setReviewByTSO(review);
//			}
//			dto.setUserId(themeDisplay.getUserId());
//			dto.setCreateDate(new Date());
//			dto.setModifiedDate(new Date());
//
//		} catch (Exception e) {
//			LOGGER.error("Error while setting TransferApplication DTO: ", e);
//		}
//
//		return dto;
//	}
//	public TransferApplicationCommonDTO setTransferApplication(ResourceRequest resourceRequest,
//			ThemeDisplay themeDisplay, long transferApplicationId, String mode) {
//
//		TransferApplicationCommonDTO dto = new TransferApplicationCommonDTO();
//
//		try {
//			dto.setTransferApplicationId(transferApplicationId);
//			dto.setGrievanceId(ParamUtil.getLong(resourceRequest, "grievanceId"));
//			Grievance grievance = GrievanceLocalServiceUtil.getGrievance(dto.getGrievanceId());
//			int transferTo = ParamUtil.getInteger(resourceRequest, "transferTo");
//			LOGGER.info("transferTo: " + transferTo);
//			boolean isHOAdmin = ParamUtil.getBoolean(resourceRequest, "isHOAdmin");
//			boolean isDSO = ParamUtil.getBoolean(resourceRequest, "isDSO");
//			boolean isTSO = ParamUtil.getBoolean(resourceRequest, "isTSO");
//
//			if (transferApplicationId > 0) {
//				TransferApplication existing = TransferApplicationLocalServiceUtil
//						.getTransferApplication(transferApplicationId);
//
//				dto.setSolutionByHo(existing.getSolutionByHo());
//				dto.setReviewByHo(existing.getReviewByHo());
//				dto.setSolutionByDSO(existing.getSolutionByDSO());
//				dto.setReviewByDSO(existing.getReviewByDSO());
//				dto.setSolutionByTSO(existing.getSolutionByTSO());
//				dto.setReviewByTSO(existing.getReviewByTSO());
//			}
//			DistrictMaster districtMaster = DistrictMasterLocalServiceUtil.getDistrictMaster(grievance.getDistrict());
//			if ("view".equalsIgnoreCase(mode)) {
//				dto.setTransfer("Published");
//			} else {
//				if (transferTo == 0) {
//					dto.setTransferTo(grievance.getTaluka());
//					dto.setTransfer("TSO-" + grievance.getTaluka());
//				} else if (transferTo == 1) {
//					dto.setTransferTo(grievance.getDistrict());
//					dto.setTransfer(districtMaster.getDistrictName_en() + "-DSO");
//				} else if (transferTo == 2) {
//					dto.setTransferTo(42682);
//					dto.setTransfer("HO-Admin");
//				}
//
//				dto.setDistrict(grievance.getDistrict());
//				dto.setTaluka(grievance.getTaluka());
//
//				String solution = ParamUtil.getString(resourceRequest, "solution");
//				String review = ParamUtil.getString(resourceRequest, "review");
//
//				if (isHOAdmin) {
//					dto.setSolutionByHo(solution);
//					dto.setReviewByHo(review);
//				} else if (isDSO) {
//					dto.setSolutionByDSO(solution);
//					dto.setReviewByDSO(review);
//				} else if (isTSO) {
//					dto.setSolutionByTSO(solution);
//					dto.setReviewByTSO(review);
//				}
//
//				dto.setUserId(themeDisplay.getUserId());
//				dto.setCreateDate(new Date());
//				dto.setModifiedDate(new Date());
//			}
//		} catch (Exception e) {
//			LOGGER.error("Error while setting TransferApplication DTO: ", e);
//		}
//
//		return dto;
//	}
	public TransferApplicationCommonDTO setTransferApplication(ResourceRequest resourceRequest,
			ThemeDisplay themeDisplay, long transferApplicationId, String mode) {

		TransferApplicationCommonDTO dto = new TransferApplicationCommonDTO();

		try {
			LOGGER.info("transferApplicationId: " + transferApplicationId);

			dto.setTransferApplicationId(transferApplicationId);
			dto.setGrievanceId(ParamUtil.getLong(resourceRequest, "grievanceId"));

			Grievance grievance = GrievanceLocalServiceUtil.getGrievance(dto.getGrievanceId());
			int transferTo = ParamUtil.getInteger(resourceRequest, "transferTo");
			dto.setTransferApplication(transferTo);
			LOGGER.info("transferTo: " + transferTo);

			boolean isHOAdmin = ParamUtil.getBoolean(resourceRequest, "isHOAdmin");
			boolean isDSO = ParamUtil.getBoolean(resourceRequest, "isDSO");
			boolean isTSO = ParamUtil.getBoolean(resourceRequest, "isTSO");

			// Populate existing values
			if (transferApplicationId > 0) {
				TransferApplication existing = TransferApplicationLocalServiceUtil
						.getTransferApplication(transferApplicationId);

				dto.setSolutionByHo(existing.getSolutionByHo());
				dto.setReviewByHo(existing.getReviewByHo());
				dto.setSolutionByDSO(existing.getSolutionByDSO());
				dto.setReviewByDSO(existing.getReviewByDSO());
				dto.setSolutionByTSO(existing.getSolutionByTSO());
				dto.setReviewByTSO(existing.getReviewByTSO());
			}

			DistrictMaster districtMaster = DistrictMasterLocalServiceUtil.getDistrictMaster(grievance.getDistrict());

//			if ("view".equalsIgnoreCase(mode)) {
//				dto.setTransfer("Published");
//			} else {
				// Set transfer level
				if (transferTo == 0) {
					dto.setTransferTo(grievance.getTaluka());
					dto.setTransfer("TSO-" + grievance.getTaluka());
				} else if (transferTo == 1) {
					dto.setTransferTo(grievance.getDistrict());
					dto.setTransfer(districtMaster.getDistrictName_en() + "-DSO");
				} else if (transferTo == 2) {
					dto.setTransferTo(42682);
					dto.setTransfer("HO-Admin");
				}

				dto.setDistrict(grievance.getDistrict());
				dto.setTaluka(grievance.getTaluka());

				// Get new solution/review inputs
				String solution = ParamUtil.getString(resourceRequest, "solution");
				String review = ParamUtil.getString(resourceRequest, "review");
				LOGGER.info("solution: "+solution);
				LOGGER.info("review: "+review);
				// Only update if non-empty
				if (isHOAdmin) {
					if (Validator.isNotNull(solution)) {
						dto.setSolutionByHo(solution);
					}
					if (Validator.isNotNull(review)) {
						dto.setReviewByHo(review);
					}
				} else if (isDSO) {
					if (Validator.isNotNull(solution)) {
						dto.setSolutionByDSO(solution);
					}
					if (Validator.isNotNull(review)) {
						dto.setReviewByDSO(review);
					}
				} else if (isTSO) {
					if (Validator.isNotNull(solution)) {
						dto.setSolutionByTSO(solution);
					}
					if (Validator.isNotNull(review)) {
						dto.setReviewByTSO(review);
					}
				}

				dto.setUserId(themeDisplay.getUserId());
				dto.setCreateDate(new Date());
				dto.setModifiedDate(new Date());
//			}
		} catch (Exception e) {
			LOGGER.error("Error while setting TransferApplication DTO: ", e);
		}

		return dto;
	}

public GrievanceCommonDTO setGrievanceDTO(Grievance grievance) {
	try {
		GrievanceCommonDTO grievanceCommonDTO = new GrievanceCommonDTO();
		BeanPropertiesUtil.copyProperties(grievance, grievanceCommonDTO);
	 grievanceCommonDTO.setComplaintDateStr(formatter.format(grievance.getComplaintDate()));
	 return grievanceCommonDTO;
	}catch (Exception e) {
		LOGGER.error(e.getMessage(), e);	}
	return null;
}

}
