package com.mhdsys.common.service.grievance;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.grievance.GrievanceApi;
import com.mhdsys.common.pojo.GrievanceCommonDTO;
import com.mhdsys.common.pojo.TransferApplicationCommonDTO;
import com.mhdsys.schema.model.Grievance;
import com.mhdsys.schema.model.TransferApplication;
import com.mhdsys.schema.service.GrievanceLocalServiceUtil;
import com.mhdsys.schema.service.TransferApplicationLocalServiceUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = GrievanceApi.class)

public class GrievanceCommonService implements GrievanceApi {
	private static Log LOGGER = LogFactoryUtil.getLog(GrievanceCommonService.class);

	@Override
	public Grievance saveGrievance(GrievanceCommonDTO grievanceDTO) {
		try {
			LOGGER.info("GRIEVANCE DTO ::: " + grievanceDTO);

			if (grievanceDTO.getGrievanceId() > 0) {
				LOGGER.info("UPDATE MODE");

				Grievance grievance = GrievanceLocalServiceUtil.getGrievance(grievanceDTO.getGrievanceId());
				long grievanceId = grievance.getGrievanceId();
				LOGGER.info("Grievance ID : " + grievanceId);

				BeanPropertiesUtil.copyProperties(grievanceDTO, grievance);
				grievance.setGrievanceId(grievanceId);
				grievance.setModifiedDate(new Date());

				return GrievanceLocalServiceUtil.updateGrievance(grievance);
			} else {
				Grievance grievance = GrievanceLocalServiceUtil
						.createGrievance(CounterLocalServiceUtil.increment(Grievance.class.getName()));

				long grievanceId = grievance.getGrievanceId();
				LOGGER.info("Grievance ID : " + grievanceId);

				BeanPropertiesUtil.copyProperties(grievanceDTO, grievance);
				grievance.setGrievanceId(grievanceId);
				grievance.setCreateDate(new Date());
				grievance.setModifiedDate(new Date());

				return GrievanceLocalServiceUtil.addGrievance(grievance);
			}

		} catch (Exception e) {
			LOGGER.error("Error while saving grievance: ", e);
		}

		return null;
	}

	@Override
	public TransferApplication saveTransferApplication(TransferApplicationCommonDTO dto) {
		try {
			LOGGER.info("TRANSFER APPLICATION DTO ::: " + dto.getTransferApplicationId());

			if (dto.getTransferApplicationId() > 0) {
				LOGGER.info("UPDATE MODE");

				TransferApplication entity = TransferApplicationLocalServiceUtil
						.getTransferApplication(dto.getTransferApplicationId());

				long transferApplicationId = entity.getTransferApplicationId();
				LOGGER.info("Transfer Application ID : " + transferApplicationId);

				BeanPropertiesUtil.copyProperties(dto, entity);
				entity.setTransferApplicationId(transferApplicationId);
				entity.setModifiedDate(new Date());

				return TransferApplicationLocalServiceUtil.updateTransferApplication(entity);

			} else {
				LOGGER.info("CREATE MODE");

				TransferApplication entity = TransferApplicationLocalServiceUtil.createTransferApplication(
						CounterLocalServiceUtil.increment(TransferApplication.class.getName()));

				long transferApplicationId = entity.getTransferApplicationId();
				LOGGER.info("Transfer Application ID : " + transferApplicationId);

				BeanPropertiesUtil.copyProperties(dto, entity);
				entity.setTransferApplicationId(transferApplicationId);
				entity.setCreateDate(new Date());
				entity.setModifiedDate(new Date());

				return TransferApplicationLocalServiceUtil.addTransferApplication(entity);
			}
		} catch (Exception e) {
			LOGGER.error("Error while saving transfer application: ", e);
		}

		return null;
	}

}
