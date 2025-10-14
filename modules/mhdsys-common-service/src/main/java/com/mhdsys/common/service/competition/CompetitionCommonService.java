package com.mhdsys.common.service.competition;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.competition.CompetitionCommonApi;
import com.mhdsys.common.pojo.CompetitionInitiationCommonDTO;
import com.mhdsys.common.pojo.CompetitionMasterCommonDTO;
import com.mhdsys.common.pojo.CompetitionScheduleCommonDTO;
import com.mhdsys.common.pojo.PTTeacherApplicationCommonDTO;
import com.mhdsys.common.pojo.ResultUploadCommonDTO;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.CompetitionMaster;
import com.mhdsys.schema.model.CompetitionResultUpload;
import com.mhdsys.schema.model.CompetitionSchedule;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.service.CompetitionInitiationLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionMasterLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionResultUploadLocalServiceUtil;
import com.mhdsys.schema.service.CompetitionScheduleLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.PTTeacherApplicationLocalServiceUtil;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;

/**
 * @author Tanusree
 */
@Component(immediate = true, service = CompetitionCommonApi.class)

public class CompetitionCommonService implements CompetitionCommonApi {
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	public CompetitionMaster saveCompetitionMaster(CompetitionMasterCommonDTO competitionMasterDTO) {
		try {
			CompetitionMaster competitionMaster = CompetitionMasterLocalServiceUtil
					.createCompetitionMaster(CounterLocalServiceUtil.increment(CompetitionMaster.class.getName()));
			long competitionMasterId = competitionMaster.getCompetitionMasterId();
			LOGGER.info("competition master id: " + competitionMaster.getCompetitionMasterId());
			BeanPropertiesUtil.copyProperties(competitionMasterDTO, competitionMaster);
			competitionMaster.setCompetitionMasterId(competitionMasterId);
			LOGGER.info(competitionMaster.getFees() + "competition id: " + competitionMasterId);

			return CompetitionMasterLocalServiceUtil.addCompetitionMaster(competitionMaster);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;

	}

	@Override
	public CompetitionInitiation saveCompetitionInitiation(CompetitionInitiationCommonDTO competitionInitiationDTO) {
		try {
			CompetitionInitiation competitionInitiation = CompetitionInitiationLocalServiceUtil
					.createCompetitionInitiation(
							CounterLocalServiceUtil.increment(CompetitionInitiation.class.getName()));
			long competitionInitiationId = competitionInitiation.getCompetitionInitiationId();
			LOGGER.info("competition initiation id: " + competitionInitiationId);
			BeanPropertiesUtil.copyProperties(competitionInitiationDTO, competitionInitiation);
			competitionInitiation.setCompetitionInitiationId(competitionInitiationId);
			LOGGER.info(competitionInitiation.getFees() + "competition id: " + competitionInitiationId);

			return CompetitionInitiationLocalServiceUtil.addCompetitionInitiation(competitionInitiation);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public PTTeacherApplication savePTTeacherApplication(PTTeacherApplicationCommonDTO ptTeacherApplicationDTO) {
		try {
			long ptTeacherApplicationId = ptTeacherApplicationDTO.getPtTeacherApplicationId();
			PTTeacherApplication pttTeacherApplication;
			if (ptTeacherApplicationDTO.getPtTeacherApplicationId() > 0) {
				ptTeacherApplicationId = ptTeacherApplicationDTO.getPtTeacherApplicationId();
				pttTeacherApplication = PTTeacherApplicationLocalServiceUtil
						.getPTTeacherApplication(ptTeacherApplicationId);
			} else {
				pttTeacherApplication = PTTeacherApplicationLocalServiceUtil.createPTTeacherApplication(
						CounterLocalServiceUtil.increment(PTTeacherApplication.class.getName()));
				ptTeacherApplicationId = pttTeacherApplication.getPtTeacherApplicationId();
			}
			LOGGER.info("pttTeacherApplicationId: " + ptTeacherApplicationId);
			BeanPropertiesUtil.copyProperties(ptTeacherApplicationDTO, pttTeacherApplication);
			pttTeacherApplication.setPtTeacherApplicationId(ptTeacherApplicationId);
			pttTeacherApplication.setApplicationNumber(createApplicationNumber(ptTeacherApplicationId));
			LOGGER.info(pttTeacherApplication.getStatus() + "pttTeacherApplication id: " + ptTeacherApplicationId);
			if (ptTeacherApplicationDTO.getPtTeacherApplicationId() > 0) {
				return PTTeacherApplicationLocalServiceUtil.updatePTTeacherApplication(pttTeacherApplication);
			}
			return PTTeacherApplicationLocalServiceUtil.addPTTeacherApplication(pttTeacherApplication);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public PTTeacherApplication updatePtTeacherApplicationStatus(PTTeacherApplication ptTeacherApplication) {
		try {
			return PTTeacherApplicationLocalServiceUtil.updatePTTeacherApplication(ptTeacherApplication);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

//	public void createRoles() {
//		try {
//			// Fetch all PoliceStationMaster records
//			List<DistrictMaster> districts = DistrictMasterLocalServiceUtil.getByActiveState(true);
//
//			// Get the default companyId
//			long companyId = PortalUtil.getDefaultCompanyId();
//
//			// Fetch service context for role creation
//			ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
//
//			// Use Streams to process records
//			districts.stream().map(DistrictMaster::getDistrictName_en) // Extract the names of police stations
//					.filter(Validator::isNotNull) // Ensure the name is not null
//					.map(String::trim) // Trim spaces from names
//					.distinct() // Avoid duplicate names within the list
//					.filter(roleName -> RoleLocalServiceUtil.fetchRole(companyId, roleName) == null) // Check if role
//																										// doesn't exist
//					.collect(Collectors.toList()) // Collect valid role names
//					.forEach(roleName -> { // Process each role name
//						try {
//							RoleLocalServiceUtil.addRole(roleName, serviceContext.getUserId(), // Creator UserId
//									null, // Class name (not used here)
//									0L, // Class PK
//									roleName+"-DSO", // Role name
//									Collections.singletonMap(Locale.getDefault(), roleName), // Title map
//									Collections.singletonMap(Locale.getDefault(), "Role for District: " + roleName), // Description
//																														// map
//									RoleConstants.TYPE_REGULAR, // Role type (Regular role)
//									null, // Role permissions
//									serviceContext // Service Context
//							);
//							LOGGER.info("Created Role: " + roleName);
//						} catch (PortalException | SystemException e) {
//							LOGGER.error("Error creating role for: " + roleName);
//							e.printStackTrace();
//						}
//					});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public String createApplicationNumber(long ptTeacherApplicationId) {

		try {
			return "MAHASPORT" + ptTeacherApplicationId;

		} catch (Exception e) {
			return String.valueOf(ptTeacherApplicationId);
		}

	}

	@Override
	public CompetitionSchedule SaveCompetitionSchedule(CompetitionScheduleCommonDTO competitionScheduleCommonDTO) {
		try {
			long ptTeacherApplicationId = competitionScheduleCommonDTO.getPtTeacherApplicationId();
			LOGGER.info("pttTeacherApplicationId: " + ptTeacherApplicationId);
			CompetitionSchedule competitionSchedule = CompetitionScheduleLocalServiceUtil
					.createCompetitionSchedule(CounterLocalServiceUtil.increment(CompetitionSchedule.class.getName()));

			BeanPropertiesUtil.copyProperties(competitionScheduleCommonDTO, competitionSchedule);
			competitionSchedule.setCompetitionScheduleId(competitionSchedule.getCompetitionScheduleId());
			competitionSchedule = CompetitionScheduleLocalServiceUtil.addCompetitionSchedule(competitionSchedule);
			if (Validator.isNotNull(competitionSchedule)) {
				PTTeacherApplication pttTeacherApplication = PTTeacherApplicationLocalServiceUtil
						.getPTTeacherApplication(ptTeacherApplicationId);
				pttTeacherApplication.setScheduled(true);
				PTTeacherApplicationLocalServiceUtil.updatePTTeacherApplication(pttTeacherApplication);
			}
			return competitionSchedule;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public CompetitionResultUpload SaveResultUpload(ResultUploadCommonDTO resultUploadCommonDTO) {
		try {
			LOGGER.info("getCompetitionScheduledId: " + resultUploadCommonDTO.getCompetitionScheduledId());
			CompetitionResultUpload competitionResultUpload = CompetitionResultUploadLocalServiceUtil
					.createCompetitionResultUpload(
							CounterLocalServiceUtil.increment(CompetitionResultUpload.class.getName()));
			long resultUploadId = competitionResultUpload.getCompetitionResultUploadId();
			LOGGER.info("resultUploadId  id: " + resultUploadId);
			BeanPropertiesUtil.copyProperties(resultUploadCommonDTO, competitionResultUpload);
			competitionResultUpload.setCompetitionResultUploadId(resultUploadId);
			competitionResultUpload = CompetitionResultUploadLocalServiceUtil.addCompetitionResultUpload(competitionResultUpload);
			if(Validator.isNotNull(competitionResultUpload)) {
				CompetitionSchedule competitionSchedule = CompetitionScheduleLocalServiceUtil.getCompetitionSchedule(competitionResultUpload.getCompetitionScheduledId());
				competitionSchedule.setResultUpload(true);
				CompetitionScheduleLocalServiceUtil.updateCompetitionSchedule(competitionSchedule);
			}
			return competitionResultUpload;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
