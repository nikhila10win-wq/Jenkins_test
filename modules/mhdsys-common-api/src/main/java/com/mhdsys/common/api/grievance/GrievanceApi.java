package com.mhdsys.common.api.grievance;

import com.mhdsys.common.pojo.GrievanceCommonDTO;
import com.mhdsys.common.pojo.TransferApplicationCommonDTO;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.model.TransferApplication;

public interface GrievanceApi {
	Grievance saveGrievance(GrievanceCommonDTO grievanceCommonDTO);
	TransferApplication saveTransferApplication(TransferApplicationCommonDTO dto);

}
