package com.mhdsys.common.api.facility.rating;

import com.mhdsys.common.pojo.FacilityRatingCommonDTO;
import com.mhdsys.schema.model.FacilityRating;

public interface FacilityRatingCommonApi {
	FacilityRating saveFacilityRating(FacilityRatingCommonDTO  facilityRatingCommonDTO);
}
