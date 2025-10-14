package com.mhdsys.common.api.awards;

import com.mhdsys.common.pojo.AwardApplicationCommonDTO;
import com.mhdsys.common.pojo.AwardPointsCommonDTO;
import com.mhdsys.common.pojo.ObjectionCommonDTO;
import com.mhdsys.schema.model.AwardApplication;
import com.mhdsys.schema.model.AwardPoints;
import com.mhdsys.schema.model.Objection;

public interface AwardsCommonApi {

	AwardPoints saveAwardsPoint(AwardPointsCommonDTO awardPointsDTO);

	AwardApplication saveAwardsApplications(AwardApplicationCommonDTO applicationDTO);

	Objection saveObjection(ObjectionCommonDTO objectionCommonDTO);
}
