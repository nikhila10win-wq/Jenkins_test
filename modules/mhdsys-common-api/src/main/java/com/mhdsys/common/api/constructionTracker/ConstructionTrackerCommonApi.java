package com.mhdsys.common.api.constructionTracker;

import com.mhdsys.common.pojo.ConstructionTrackerCommonDTO;
import com.mhdsys.schema.model.ConstructionTracker;

public interface ConstructionTrackerCommonApi {
	ConstructionTracker saveConstructionTracker(ConstructionTrackerCommonDTO constructionTrackerCommonDTO);
}
