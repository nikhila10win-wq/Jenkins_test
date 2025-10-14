package com.mhdsys.common.service.awards;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.awards.AwardsCommonApi;
import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.pojo.AwardPointsCommonDTO;
import com.mhdsys.common.pojo.ObjectionCommonDTO;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.AwardPoints;
import com.mhdsys.schema.model.Objection;
import com.mhdsys.schema.service.AwardApplicationLocalServiceUtil;
import com.mhdsys.schema.service.AwardPointsLocalServiceUtil;
import com.mhdsys.schema.service.ObjectionLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = AwardsCommonApi.class)
public class AwardsCommonService implements AwardsCommonApi {

	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public AwardPoints saveAwardsPoint(AwardPointsCommonDTO awardPointsDTO) {

		try {

			LOGGER.info("AWARD POINT DTO ::: " + awardPointsDTO);

			if (awardPointsDTO.getAwardPointsId() > 0) {

				LOGGER.info("UPDATE EDIT ");
				AwardPoints awardPoint = AwardPointsLocalServiceUtil.getAwardPoints(awardPointsDTO.getAwardPointsId());

				long awardId = awardPoint.getAwardPointsId();
				LOGGER.info("Award Point Id : " + awardId);
				BeanPropertiesUtil.copyProperties(awardPointsDTO, awardPoint);
				awardPoint.setAwardPointsId(awardId);
				LOGGER.info(awardPoint.getFirstRunnerUp() + "Award Id : " + awardId);

				return AwardPointsLocalServiceUtil.updateAwardPoints(awardPoint);
			} else {
				AwardPoints awardPoint = AwardPointsLocalServiceUtil
						.createAwardPoints(CounterLocalServiceUtil.increment(AwardPoints.class.getName()));

				long awardId = awardPoint.getAwardPointsId();
				LOGGER.info("Award Point Id : " + awardId);
				BeanPropertiesUtil.copyProperties(awardPointsDTO, awardPoint);
				awardPoint.setAwardPointsId(awardId);
				LOGGER.info(awardPoint.getFirstRunnerUp() + "Award Id : " + awardId);

				return AwardPointsLocalServiceUtil.addAwardPoints(awardPoint);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;

	}

	@Override
	public AwardApplication saveAwardsApplications(AwardApplicationCommonDTO applicationDTO) {

		try {

			LOGGER.info("AWARD APPLICATION DTO ::: " + applicationDTO);

			if (applicationDTO.getAwardApplicationId() > 0) {

				LOGGER.info("UPDATE EDIT ");
				AwardApplication application = AwardApplicationLocalServiceUtil
						.getAwardApplication(applicationDTO.getAwardApplicationId());

				long applicationId = application.getAwardApplicationId();
				LOGGER.info("Award Application Id : " + applicationId);
				BeanPropertiesUtil.copyProperties(applicationDTO, application);
				application.setAwardApplicationId(applicationId);
				LOGGER.info(application.getCityOfCompetition() + "Award Application Id : " + applicationId);

				return AwardApplicationLocalServiceUtil.updateAwardApplication(application);
			} else {
				AwardApplication awardApplication = AwardApplicationLocalServiceUtil
						.createAwardApplication(CounterLocalServiceUtil.increment(AwardApplication.class.getName()));

				long applId = awardApplication.getAwardApplicationId();
				LOGGER.info("Award Application Id : " + applId);
				BeanPropertiesUtil.copyProperties(applicationDTO, awardApplication);
				awardApplication.setAwardApplicationId(applId);
				LOGGER.info(awardApplication.getCityOfCompetition() + "Award Appl ID : " + applId);

				return AwardApplicationLocalServiceUtil.addAwardApplication(awardApplication);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;

	}

	@Override
	public Objection saveObjection(ObjectionCommonDTO objectionCommonDTO) {
		try {

			LOGGER.info("Objection DTO ::: " + objectionCommonDTO);

			if (objectionCommonDTO.getObjectionId() > 0) {

				LOGGER.info("UPDATE EDIT ");
				Objection objection = ObjectionLocalServiceUtil.getObjection(objectionCommonDTO.getObjectionId());

				long objectionId = objection.getObjectionId();
				LOGGER.info("Object Id : " + objectionId);
				BeanPropertiesUtil.copyProperties(objectionCommonDTO, objection);
				objection.setObjectionId(objectionId);
				LOGGER.info(objection.getPlayerName() + "Objection Id : " + objectionId);

				return ObjectionLocalServiceUtil.updateObjection(objection);
			} else {
				Objection objection = ObjectionLocalServiceUtil
						.createObjection(CounterLocalServiceUtil.increment(Objection.class.getName()));

				long objId = objection.getObjectionId();
				LOGGER.info("Objection Id : " + objId);
				BeanPropertiesUtil.copyProperties(objectionCommonDTO, objection);
				objection.setObjectionId(objId);
				LOGGER.info(objection.getPlayerName() + "Objection ID : " + objId);

				return ObjectionLocalServiceUtil.addObjection(objection);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

}
