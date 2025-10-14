package com.mhdsys.common.service.grants.and.schemes;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.grants.and.schemes.GrantsAndSchemesCommonApi;
import com.mhdsys.common.pojo.AspiringAthleteCommonDTO;
import com.mhdsys.common.pojo.AwardWinnerCommonDTO;
import com.mhdsys.common.pojo.CouncilSportCompetitionDetailsCommonDTO;
import com.mhdsys.common.pojo.DistrictGrantSchemeCommonDTO;
import com.mhdsys.common.pojo.FinancialAssistanceCommonDTO;
import com.mhdsys.common.pojo.IntSportsCompCommonDTO;
import com.mhdsys.schema.model.AspiringAthlete;
import com.mhdsys.schema.model.AwardWinner;
import com.mhdsys.schema.model.CouncilSportCompetitionDetails;
import com.mhdsys.schema.model.DistrictGrantScheme;
import com.mhdsys.schema.model.FinancialAssistance;
import com.mhdsys.schema.model.IntSportsComp;
import com.mhdsys.schema.service.AspiringAthleteLocalServiceUtil;
import com.mhdsys.schema.service.AwardWinnerLocalServiceUtil;
import com.mhdsys.schema.service.CouncilSportCompetitionDetailsLocalServiceUtil;
import com.mhdsys.schema.service.DistrictGrantSchemeLocalServiceUtil;
import com.mhdsys.schema.service.FinancialAssistanceLocalServiceUtil;
import com.mhdsys.schema.service.IntSportsCompLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = GrantsAndSchemesCommonApi.class)
public class GrantsAndSchemesCommonService implements GrantsAndSchemesCommonApi {

	private Log LOGGER = LogFactoryUtil.getLog(GrantsAndSchemesCommonService.class);

	@Override
	public DistrictGrantScheme saveDistrictLevelGrantsAndSchemes(DistrictGrantSchemeCommonDTO grantSchemeCommonDTO) {

		try {
			LOGGER.info("Grants and Schemes DTO ::: " + grantSchemeCommonDTO);

			if (grantSchemeCommonDTO.getDistrictGrantSchemeId() > 0) {

				LOGGER.info("UPDATE EDIT ");
				DistrictGrantScheme districtGrantScheme = DistrictGrantSchemeLocalServiceUtil
						.getDistrictGrantScheme(grantSchemeCommonDTO.getDistrictGrantSchemeId());

				long districtGrantSchemeId = districtGrantScheme.getDistrictGrantSchemeId();
				LOGGER.info("District Grant Scheme Id : " + districtGrantSchemeId);
				BeanPropertiesUtil.copyProperties(grantSchemeCommonDTO, districtGrantScheme);
				districtGrantScheme.setDistrictGrantSchemeId(districtGrantSchemeId);
				LOGGER.info(districtGrantScheme.getCategory() + "Primary Key Id : " + districtGrantSchemeId);

				return DistrictGrantSchemeLocalServiceUtil.updateDistrictGrantScheme(districtGrantScheme);
			} else {
				DistrictGrantScheme districtGrantScheme = DistrictGrantSchemeLocalServiceUtil.createDistrictGrantScheme(
						CounterLocalServiceUtil.increment(DistrictGrantScheme.class.getName()));

				long districtGrantSchemeId = districtGrantScheme.getDistrictGrantSchemeId();
				LOGGER.info("District Grant Scheme Id : " + districtGrantSchemeId);
				BeanPropertiesUtil.copyProperties(grantSchemeCommonDTO, districtGrantScheme);
				districtGrantScheme.setDistrictGrantSchemeId(districtGrantSchemeId);
				LOGGER.info(districtGrantScheme.getCategory() + "Primary Key Id : " + districtGrantSchemeId);

				return DistrictGrantSchemeLocalServiceUtil.addDistrictGrantScheme(districtGrantScheme);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public FinancialAssistance saveStateLevelFinancialAssistance(
			FinancialAssistanceCommonDTO financialAssistanceCommonDTO) {

		try {
			LOGGER.info("Financial Assistance DTO ::: " + financialAssistanceCommonDTO);

			if (financialAssistanceCommonDTO.getFinancialAssistanceId() > 0) {
				// UPDATE flow
				LOGGER.info("UPDATE EDIT State-Level Financial Assistance");

				FinancialAssistance financialAssistance = FinancialAssistanceLocalServiceUtil
						.getFinancialAssistance(financialAssistanceCommonDTO.getFinancialAssistanceId());

				long financialAssistanceId = financialAssistance.getFinancialAssistanceId();
				LOGGER.info("Financial Assistance ID: " + financialAssistanceId);

				BeanPropertiesUtil.copyProperties(financialAssistanceCommonDTO, financialAssistance);
				financialAssistance.setFinancialAssistanceId(financialAssistanceId); // optional

				return FinancialAssistanceLocalServiceUtil.updateFinancialAssistance(financialAssistance);

			} else {
				// CREATE flow
				long financialAssistanceId = CounterLocalServiceUtil.increment(FinancialAssistance.class.getName());
				FinancialAssistance financialAssistance = FinancialAssistanceLocalServiceUtil
						.createFinancialAssistance(financialAssistanceId);

				LOGGER.info("Creating new Financial Assistance ID: " + financialAssistanceId);

				BeanPropertiesUtil.copyProperties(financialAssistanceCommonDTO, financialAssistance);
				financialAssistance.setFinancialAssistanceId(financialAssistanceId); // optional

				return FinancialAssistanceLocalServiceUtil.addFinancialAssistance(financialAssistance);
			}

		} catch (Exception e) {
			LOGGER.error("Error saving Financial Assistance: " + e.getMessage(), e);
		}

		return null;
	}

	@Override
	public AwardWinner saveStateLevelAwardWinner(AwardWinnerCommonDTO awardWinnerCommonDTO) {

		try {
			LOGGER.info("Award Winner DTO ::: " + awardWinnerCommonDTO);

			if (awardWinnerCommonDTO.getAwardWinnerId() > 0) {
				// UPDATE flow
				LOGGER.info("UPDATE EDIT State-Level Award Winner");

				AwardWinner awardWinner = AwardWinnerLocalServiceUtil
						.getAwardWinner(awardWinnerCommonDTO.getAwardWinnerId());

				long awardWinnerId = awardWinner.getAwardWinnerId();
				LOGGER.info("Award Winner ID: " + awardWinnerId);

				BeanPropertiesUtil.copyProperties(awardWinnerCommonDTO, awardWinner);
				awardWinner.setAwardWinnerId(awardWinnerId); // optional

				return AwardWinnerLocalServiceUtil.updateAwardWinner(awardWinner);

			} else {
				// CREATE flow
				long awardWinnerId = CounterLocalServiceUtil.increment(AwardWinner.class.getName());
				AwardWinner awardWinner = AwardWinnerLocalServiceUtil.createAwardWinner(awardWinnerId);

				LOGGER.info("Creating new Award Winner ID: " + awardWinnerId);

				BeanPropertiesUtil.copyProperties(awardWinnerCommonDTO, awardWinner);
				awardWinner.setAwardWinnerId(awardWinnerId); // optional

				return AwardWinnerLocalServiceUtil.addAwardWinner(awardWinner);
			}

		} catch (Exception e) {
			LOGGER.error("Error saving Award Winner: " + e.getMessage(), e);
		}

		return null;
	}

	@Override
	public IntSportsComp saveIntSportsComp(IntSportsCompCommonDTO intSportsCompCommonDTO) {
		try {
			LOGGER.info("Grants and Schemes DTO ::: " + intSportsCompCommonDTO);

			if (intSportsCompCommonDTO.getIntSportsCompId() > 0) {

				LOGGER.info("UPDATE EDIT ");
				IntSportsComp intSportsComp = IntSportsCompLocalServiceUtil
						.getIntSportsComp(intSportsCompCommonDTO.getIntSportsCompId());

				long intSportsCompId = intSportsComp.getIntSportsCompId();
				LOGGER.info("State Grant Scheme Id : " + intSportsCompId);
				BeanPropertiesUtil.copyProperties(intSportsCompCommonDTO, intSportsComp);
				intSportsComp.setIntSportsCompId(intSportsCompId);
				LOGGER.info(intSportsComp.getDob() + "Primary Key Id : " + intSportsCompId);

				return IntSportsCompLocalServiceUtil.updateIntSportsComp(intSportsComp);
			} else {
				IntSportsComp intSportsComp = IntSportsCompLocalServiceUtil
						.createIntSportsComp(CounterLocalServiceUtil.increment(IntSportsComp.class.getName()));

				long intSportsCompId = intSportsComp.getIntSportsCompId();
				LOGGER.info("State Grant Scheme Id : " + intSportsCompId);
				BeanPropertiesUtil.copyProperties(intSportsCompCommonDTO, intSportsComp);
				intSportsComp.setIntSportsCompId(intSportsCompId);
				LOGGER.info(intSportsComp.getDob() + "Primary Key Id : " + intSportsCompId);

				return IntSportsCompLocalServiceUtil.addIntSportsComp(intSportsComp);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	@Override
	public CouncilSportCompetitionDetails saveCouncilCompetitionDetails(CouncilSportCompetitionDetailsCommonDTO dto) {
	    try {
	        LOGGER.info("Council Sport Competition DTO ::: " + dto);

	        if (dto.getCouncilSportCompetitionDetailsId() > 0) {
	            // UPDATE
	            LOGGER.info("UPDATE operation");

	            CouncilSportCompetitionDetails existingEntity =
	                CouncilSportCompetitionDetailsLocalServiceUtil.getCouncilSportCompetitionDetails(
	                    dto.getCouncilSportCompetitionDetailsId());

	            long primaryKey = existingEntity.getCouncilSportCompetitionDetailsId();
	            LOGGER.info("Council Competition Primary Key ID: " + primaryKey);

	            BeanPropertiesUtil.copyProperties(dto, existingEntity);
	            existingEntity.setCouncilSportCompetitionDetailsId(primaryKey); // ensure ID is retained

	            return CouncilSportCompetitionDetailsLocalServiceUtil.updateCouncilSportCompetitionDetails(existingEntity);
	        } else {
	            // CREATE
	            CouncilSportCompetitionDetails newEntity =
	                CouncilSportCompetitionDetailsLocalServiceUtil.createCouncilSportCompetitionDetails(
	                    CounterLocalServiceUtil.increment(CouncilSportCompetitionDetails.class.getName()));

	            long newPrimaryKey = newEntity.getCouncilSportCompetitionDetailsId();
	            LOGGER.info("New Council Competition ID: " + newPrimaryKey);

	            BeanPropertiesUtil.copyProperties(dto, newEntity);
	            newEntity.setCouncilSportCompetitionDetailsId(newPrimaryKey);

	            return CouncilSportCompetitionDetailsLocalServiceUtil.addCouncilSportCompetitionDetails(newEntity);
	        }

	    } catch (Exception e) {
	        LOGGER.error("Error saving Council Sport Competition Details: " + e.getMessage(), e);
	    }
	    return null;
	}

	@Override
	public AspiringAthlete saveAspiringAthlete(AspiringAthleteCommonDTO aspiringAthleteCommonDTO) {
		try {
			LOGGER.info("Aspiring Athlete DTO ::: " + aspiringAthleteCommonDTO);

			if (aspiringAthleteCommonDTO.getAspiringAthleteId() > 0) {
				// UPDATE
				LOGGER.info("UPDATE EDIT ");
				AspiringAthlete aspiringAthlete = AspiringAthleteLocalServiceUtil
						.getAspiringAthlete(aspiringAthleteCommonDTO.getAspiringAthleteId());

				long aspiringAthleteId = aspiringAthlete.getAspiringAthleteId();
				LOGGER.info("Aspiring Athlete Id : " + aspiringAthleteId);

				BeanPropertiesUtil.copyProperties(aspiringAthleteCommonDTO, aspiringAthlete);
				aspiringAthlete.setAspiringAthleteId(aspiringAthleteId);

				LOGGER.info("Primary Key Id : " + aspiringAthleteId);

				return AspiringAthleteLocalServiceUtil.updateAspiringAthlete(aspiringAthlete);
			} else {
				// CREATE
				AspiringAthlete aspiringAthlete = AspiringAthleteLocalServiceUtil
						.createAspiringAthlete(CounterLocalServiceUtil.increment(AspiringAthlete.class.getName()));

				long aspiringAthleteId = aspiringAthlete.getAspiringAthleteId();
				LOGGER.info("Aspiring Athlete Id : " + aspiringAthleteId);

				BeanPropertiesUtil.copyProperties(aspiringAthleteCommonDTO, aspiringAthlete);
				aspiringAthlete.setAspiringAthleteId(aspiringAthleteId);

				LOGGER.info("Primary Key Id : " + aspiringAthleteId);

				return AspiringAthleteLocalServiceUtil.addAspiringAthlete(aspiringAthlete);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}


}
