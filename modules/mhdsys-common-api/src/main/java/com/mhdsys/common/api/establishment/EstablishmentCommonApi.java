package com.mhdsys.common.api.establishment;

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

public interface EstablishmentCommonApi {
	PersonalDetails savePersonalDetails(PersonalDetailsCommonDTO personalDetailsCommonDTO);
	ServiceDetails saveServiceDetails(ServiceDetailsCommonDTO serviceDetailsCommonDTO);
	GPFDetails saveGPFDetails(GPFDetailsCommonDTO gPFDetailsCommonDTO);
	NPSDetails saveNPSDetails(NPSDetailsCommonDTO nPSDetailsCommonDTO);
	TrainingDetails saveTrainingDetails(TrainingDetailsCommonDTO trainingDetailsCommonDTO);
	PostingStatus savePostingStatus(PostingStatusCommonDTO postingStatusCommonDTO);
	RoasterStatus saveRoasterStatus(RoasterStatusCommonDTO roasterStatusCommonDTO);
	EstablishmentDetails saveEstablishmentDetails(EstablishmentCommonDTO establishmentCommonDTO);
}
