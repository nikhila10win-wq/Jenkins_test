package com.mhdsys.common.service.establishment;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.establishment.EstablishmentCommonApi;
import com.mhdsys.common.pojo.EstablishmentCommonDTO;
import com.mhdsys.common.pojo.GPFDetailsCommonDTO;
import com.mhdsys.common.pojo.NPSDetailsCommonDTO;
import com.mhdsys.common.pojo.PersonalDetailsCommonDTO;
import com.mhdsys.common.pojo.PostingStatusCommonDTO;
import com.mhdsys.common.pojo.RoasterStatusCommonDTO;
import com.mhdsys.common.pojo.ServiceDetailsCommonDTO;
import com.mhdsys.common.pojo.TrainingDetailsCommonDTO;
import com.mhdsys.schema.model.EstablishmentDetails;
import com.mhdsys.schema.model.GPFDetails;
import com.mhdsys.schema.model.NPSDetails;
import com.mhdsys.schema.model.PersonalDetails;
import com.mhdsys.schema.model.PostingStatus;
import com.mhdsys.schema.model.RoasterStatus;
import com.mhdsys.schema.model.ServiceDetails;
import com.mhdsys.schema.model.TrainingDetails;
import com.mhdsys.schema.service.EstablishmentDetailsLocalServiceUtil;
import com.mhdsys.schema.service.GPFDetailsLocalServiceUtil;
import com.mhdsys.schema.service.NPSDetailsLocalServiceUtil;
import com.mhdsys.schema.service.PersonalDetailsLocalServiceUtil;
import com.mhdsys.schema.service.PostingStatusLocalServiceUtil;
import com.mhdsys.schema.service.RoasterStatusLocalServiceUtil;
import com.mhdsys.schema.service.ServiceDetailsLocalServiceUtil;
import com.mhdsys.schema.service.TrainingDetailsLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = EstablishmentCommonApi.class)

public class EstablishmentCommonService implements EstablishmentCommonApi {
	private static Log LOGGER = LogFactoryUtil.getLog(EstablishmentCommonService.class);

	@Override
	public PersonalDetails savePersonalDetails(PersonalDetailsCommonDTO personalDetailsCommonDTO) {
		try {
			LOGGER.info("PERSONAL DETAILS DTO ::: " + personalDetailsCommonDTO);

			if (personalDetailsCommonDTO.getPersonalDetailsId() > 0) {
				LOGGER.info("UPDATE EDIT");

				PersonalDetails personalDetails = PersonalDetailsLocalServiceUtil
						.getPersonalDetails(personalDetailsCommonDTO.getPersonalDetailsId());

				long personalId = personalDetails.getPersonalDetailsId();
				LOGGER.info("Personal Details Id : " + personalId);

				BeanPropertiesUtil.copyProperties(personalDetailsCommonDTO, personalDetails);
				personalDetails.setPersonalDetailsId(personalId);

				return PersonalDetailsLocalServiceUtil.updatePersonalDetails(personalDetails);
			} else {
				PersonalDetails personalDetails = PersonalDetailsLocalServiceUtil
						.createPersonalDetails(CounterLocalServiceUtil.increment(PersonalDetails.class.getName()));

				long personalId = personalDetails.getPersonalDetailsId();
				LOGGER.info("Personal Details Id : " + personalId);

				BeanPropertiesUtil.copyProperties(personalDetailsCommonDTO, personalDetails);
				personalDetails.setPersonalDetailsId(personalId);

				return PersonalDetailsLocalServiceUtil.addPersonalDetails(personalDetails);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public ServiceDetails saveServiceDetails(ServiceDetailsCommonDTO serviceDetailsCommonDTO) {
		try {
			LOGGER.info("SERVICE DETAILS DTO ::: " + serviceDetailsCommonDTO);

			if (serviceDetailsCommonDTO.getServiceDetailsId() > 0) {
				LOGGER.info("UPDATE EDIT");
				ServiceDetails serviceDetails = ServiceDetailsLocalServiceUtil
						.getServiceDetails(serviceDetailsCommonDTO.getServiceDetailsId());

				long id = serviceDetails.getServiceDetailsId();
				LOGGER.info("Service Details Id : " + id);

				BeanPropertiesUtil.copyProperties(serviceDetailsCommonDTO, serviceDetails);
				serviceDetails.setServiceDetailsId(id);

				return ServiceDetailsLocalServiceUtil.updateServiceDetails(serviceDetails);
			} else {
				ServiceDetails serviceDetails = ServiceDetailsLocalServiceUtil
						.createServiceDetails(CounterLocalServiceUtil.increment(ServiceDetails.class.getName()));

				long id = serviceDetails.getServiceDetailsId();
				LOGGER.info("Service Details Id : " + id);

				BeanPropertiesUtil.copyProperties(serviceDetailsCommonDTO, serviceDetails);
				serviceDetails.setServiceDetailsId(id);

				return ServiceDetailsLocalServiceUtil.addServiceDetails(serviceDetails);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public GPFDetails saveGPFDetails(GPFDetailsCommonDTO gPFDetailsCommonDTO) {
		try {
			LOGGER.info("GPF DETAILS DTO ::: " + gPFDetailsCommonDTO);

			if (gPFDetailsCommonDTO.getgPFDetailsId() > 0) {
				LOGGER.info("UPDATE EDIT");
				GPFDetails gpfDetails = GPFDetailsLocalServiceUtil.getGPFDetails(gPFDetailsCommonDTO.getgPFDetailsId());

				long id = gpfDetails.getGPFDetailsId();
				LOGGER.info("GPF Details Id : " + id);

				BeanPropertiesUtil.copyProperties(gPFDetailsCommonDTO, gpfDetails);
				gpfDetails.setGPFDetailsId(id);

				return GPFDetailsLocalServiceUtil.updateGPFDetails(gpfDetails);
			} else {
				GPFDetails gpfDetails = GPFDetailsLocalServiceUtil
						.createGPFDetails(CounterLocalServiceUtil.increment(GPFDetails.class.getName()));

				long id = gpfDetails.getGPFDetailsId();
				LOGGER.info("GPF Details Id : " + id);

				BeanPropertiesUtil.copyProperties(gPFDetailsCommonDTO, gpfDetails);
				gpfDetails.setGPFDetailsId(id);

				return GPFDetailsLocalServiceUtil.addGPFDetails(gpfDetails);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public NPSDetails saveNPSDetails(NPSDetailsCommonDTO nPSDetailsCommonDTO) {
		try {
			LOGGER.info("NPS DETAILS DTO ::: " + nPSDetailsCommonDTO);

			if (nPSDetailsCommonDTO.getnPSDetailsId() > 0) {
				LOGGER.info("UPDATE EDIT");
				NPSDetails npsDetails = NPSDetailsLocalServiceUtil.getNPSDetails(nPSDetailsCommonDTO.getnPSDetailsId());

				long id = npsDetails.getNPSDetailsId();
				LOGGER.info("NPS Details Id : " + id);

				BeanPropertiesUtil.copyProperties(nPSDetailsCommonDTO, npsDetails);
				npsDetails.setNPSDetailsId(id);

				return NPSDetailsLocalServiceUtil.updateNPSDetails(npsDetails);
			} else {
				NPSDetails npsDetails = NPSDetailsLocalServiceUtil
						.createNPSDetails(CounterLocalServiceUtil.increment(NPSDetails.class.getName()));

				long id = npsDetails.getNPSDetailsId();
				LOGGER.info("NPS Details Id : " + id);

				BeanPropertiesUtil.copyProperties(nPSDetailsCommonDTO, npsDetails);
				npsDetails.setNPSDetailsId(id);

				return NPSDetailsLocalServiceUtil.addNPSDetails(npsDetails);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public TrainingDetails saveTrainingDetails(TrainingDetailsCommonDTO trainingDetailsCommonDTO) {
		try {
			LOGGER.info("TRAINING DETAILS DTO ::: " + trainingDetailsCommonDTO);

			if (trainingDetailsCommonDTO.getTrainingDetailsId() > 0) {
				LOGGER.info("UPDATE EDIT");
				TrainingDetails trainingDetails = TrainingDetailsLocalServiceUtil
						.getTrainingDetails(trainingDetailsCommonDTO.getTrainingDetailsId());

				long id = trainingDetails.getTrainingDetailsId();
				LOGGER.info("Training Details Id : " + id);

				BeanPropertiesUtil.copyProperties(trainingDetailsCommonDTO, trainingDetails);
				trainingDetails.setTrainingDetailsId(id);

				return TrainingDetailsLocalServiceUtil.updateTrainingDetails(trainingDetails);
			} else {
				TrainingDetails trainingDetails = TrainingDetailsLocalServiceUtil
						.createTrainingDetails(CounterLocalServiceUtil.increment(TrainingDetails.class.getName()));

				long id = trainingDetails.getTrainingDetailsId();
				LOGGER.info("Training Details Id : " + id);

				BeanPropertiesUtil.copyProperties(trainingDetailsCommonDTO, trainingDetails);
				trainingDetails.setTrainingDetailsId(id);

				return TrainingDetailsLocalServiceUtil.addTrainingDetails(trainingDetails);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public PostingStatus savePostingStatus(PostingStatusCommonDTO postingStatusCommonDTO) {
		try {
			LOGGER.info("POSTING STATUS DTO ::: " + postingStatusCommonDTO);

			if (postingStatusCommonDTO.getPostingStatusId() > 0) {
				LOGGER.info("UPDATE EDIT");
				PostingStatus postingStatus = PostingStatusLocalServiceUtil
						.getPostingStatus(postingStatusCommonDTO.getPostingStatusId());

				long id = postingStatus.getPostingStatusId();
				LOGGER.info("Posting Status Id : " + id);

				BeanPropertiesUtil.copyProperties(postingStatusCommonDTO, postingStatus);
				postingStatus.setPostingStatusId(id);

				return PostingStatusLocalServiceUtil.updatePostingStatus(postingStatus);
			} else {
				PostingStatus postingStatus = PostingStatusLocalServiceUtil
						.createPostingStatus(CounterLocalServiceUtil.increment(PostingStatus.class.getName()));

				long id = postingStatus.getPostingStatusId();
				LOGGER.info("Posting Status Id : " + id);

				BeanPropertiesUtil.copyProperties(postingStatusCommonDTO, postingStatus);
				postingStatus.setPostingStatusId(id);

				return PostingStatusLocalServiceUtil.addPostingStatus(postingStatus);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public RoasterStatus saveRoasterStatus(RoasterStatusCommonDTO roasterStatusCommonDTO) {
		try {
			LOGGER.info("ROASTER STATUS DTO ::: " + roasterStatusCommonDTO);

			if (roasterStatusCommonDTO.getRoasterStatusId() > 0) {
				LOGGER.info("UPDATE EDIT");
				RoasterStatus roasterStatus = RoasterStatusLocalServiceUtil
						.getRoasterStatus(roasterStatusCommonDTO.getRoasterStatusId());

				long id = roasterStatus.getRoasterStatusId();
				LOGGER.info("Roaster Status Id : " + id);

				BeanPropertiesUtil.copyProperties(roasterStatusCommonDTO, roasterStatus);
				roasterStatus.setRoasterStatusId(id);

				return RoasterStatusLocalServiceUtil.updateRoasterStatus(roasterStatus);
			} else {
				RoasterStatus roasterStatus = RoasterStatusLocalServiceUtil
						.createRoasterStatus(CounterLocalServiceUtil.increment(RoasterStatus.class.getName()));

				long id = roasterStatus.getRoasterStatusId();
				LOGGER.info("Roaster Status Id : " + id);

				BeanPropertiesUtil.copyProperties(roasterStatusCommonDTO, roasterStatus);
				roasterStatus.setRoasterStatusId(id);

				return RoasterStatusLocalServiceUtil.addRoasterStatus(roasterStatus);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	@Override
	public EstablishmentDetails saveEstablishmentDetails(EstablishmentCommonDTO establishmentCommonDTO) {
	    try {
	        LOGGER.info("ESTABLISHMENT DETAILS DTO ::: " + establishmentCommonDTO);

	        if (establishmentCommonDTO.getEstablishmentDetailId() > 0) {
	            LOGGER.info("UPDATE EDIT");
	            EstablishmentDetails establishmentDetails = EstablishmentDetailsLocalServiceUtil
	                    .getEstablishmentDetails(establishmentCommonDTO.getEstablishmentDetailId());

	            long id = establishmentDetails.getEstablishmentDetailId();
	            LOGGER.info("Establishment Detail Id : " + id);

	            BeanPropertiesUtil.copyProperties(establishmentCommonDTO, establishmentDetails);
	            establishmentDetails.setEstablishmentDetailId(id);

	            return EstablishmentDetailsLocalServiceUtil.updateEstablishmentDetails(establishmentDetails);
	        } else {
	            EstablishmentDetails establishmentDetails = EstablishmentDetailsLocalServiceUtil
	                    .createEstablishmentDetails(CounterLocalServiceUtil.increment(EstablishmentDetails.class.getName()));

	            long id = establishmentDetails.getEstablishmentDetailId();
	            LOGGER.info("Establishment Detail Id : " + id);

	            BeanPropertiesUtil.copyProperties(establishmentCommonDTO, establishmentDetails);
	            establishmentDetails.setEstablishmentDetailId(id);

	            return EstablishmentDetailsLocalServiceUtil.addEstablishmentDetails(establishmentDetails);
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error while saving establishment details: " + e.getMessage(), e);
	    }
	    return null;
	}


}
