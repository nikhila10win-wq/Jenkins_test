package com.mhdsys.common.api.youth;

import com.mhdsys.common.pojo.AwardYouthCommonDTO;
import com.mhdsys.schema.model.AwardYouth;

public interface AwardYouthCommonApi {
   AwardYouth saveAwardYouth(AwardYouthCommonDTO awardYouthCommonDTO);
}
