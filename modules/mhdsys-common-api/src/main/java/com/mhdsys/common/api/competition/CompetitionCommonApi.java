package com.mhdsys.common.api.competition;

import com.mhdsys.common.pojo.CompetitionInitiationCommonDTO;
import com.mhdsys.common.pojo.CompetitionMasterCommonDTO;
import com.mhdsys.common.pojo.CompetitionScheduleCommonDTO;
import com.mhdsys.common.pojo.PTTeacherApplicationCommonDTO;
import com.mhdsys.common.pojo.ResultUploadCommonDTO;
import com.mhdsys.schema.model.CompetitionInitiation;
import com.mhdsys.schema.model.CompetitionMaster;
import com.mhdsys.schema.model.CompetitionResultUpload;
import com.mhdsys.schema.model.CompetitionSchedule;
import com.mhdsys.schema.model.PTTeacherApplication;

public interface CompetitionCommonApi {
	CompetitionMaster saveCompetitionMaster(CompetitionMasterCommonDTO competitionMasterDTO);

	CompetitionInitiation saveCompetitionInitiation(CompetitionInitiationCommonDTO competitionInitiationDTO);

	PTTeacherApplication savePTTeacherApplication(PTTeacherApplicationCommonDTO ptTeacherApplicationDTO);

	PTTeacherApplication updatePtTeacherApplicationStatus(PTTeacherApplication ptTeacherApplication);

	CompetitionSchedule SaveCompetitionSchedule(CompetitionScheduleCommonDTO competitionScheduleCommonDTO);

	CompetitionResultUpload SaveResultUpload(ResultUploadCommonDTO resultUploadCommonDTO);

}
