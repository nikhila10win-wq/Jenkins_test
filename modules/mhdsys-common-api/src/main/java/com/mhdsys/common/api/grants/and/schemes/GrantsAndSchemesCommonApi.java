package com.mhdsys.common.api.grants.and.schemes;

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

public interface GrantsAndSchemesCommonApi {

	DistrictGrantScheme saveDistrictLevelGrantsAndSchemes(DistrictGrantSchemeCommonDTO grantSchemeCommonDTO);

	FinancialAssistance saveStateLevelFinancialAssistance(FinancialAssistanceCommonDTO financialAssistanceCommonDTO);

	AwardWinner saveStateLevelAwardWinner(AwardWinnerCommonDTO awardWinnerCommonDTO);

	IntSportsComp saveIntSportsComp(IntSportsCompCommonDTO intSportsCompCommonDTO);
	CouncilSportCompetitionDetails saveCouncilCompetitionDetails(CouncilSportCompetitionDetailsCommonDTO dto);
	
	AspiringAthlete saveAspiringAthlete(AspiringAthleteCommonDTO aspiringAthleteCommonDTO);

}
