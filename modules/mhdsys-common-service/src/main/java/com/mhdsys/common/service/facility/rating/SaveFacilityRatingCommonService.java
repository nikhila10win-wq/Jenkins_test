package com.mhdsys.common.service.facility.rating;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.facility.rating.FacilityRatingCommonApi;
import com.mhdsys.common.pojo.FacilityRatingCommonDTO;
import com.mhdsys.schema.model.FacilityRating;
import com.mhdsys.schema.service.FacilityRatingLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;

import org.osgi.service.component.annotations.Component;
@Component(immediate = true, service = FacilityRatingCommonApi.class)
public class SaveFacilityRatingCommonService implements FacilityRatingCommonApi {
	private Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	@Override
	public FacilityRating saveFacilityRating(FacilityRatingCommonDTO facilityRatingCommonDTO) {
		try {
			_log.info("FACILITY RATING SERVICE ::::::");

			if (facilityRatingCommonDTO.getFacilityRatingId() > 0) {
				_log.info("UPDATE / EDIT :::::::::::::");

				FacilityRating facilityRating = FacilityRatingLocalServiceUtil.getFacilityRating(facilityRatingCommonDTO.getFacilityRatingId());

				long facilityRatingId = facilityRatingCommonDTO.getFacilityRatingId();
				Date existingCreateDate = facilityRating.getCreateDate();
				String existingUniqueId = facilityRating.getRatingUniqueId();

				_log.info("facilityRatingId : " + facilityRatingId);

				BeanPropertiesUtil.copyProperties(facilityRatingCommonDTO, facilityRating);
				facilityRating.setFacilityRatingId(facilityRatingId);
				facilityRating.setRatingUniqueId(existingUniqueId);
				facilityRating.setCreateDate(existingCreateDate);

				return FacilityRatingLocalServiceUtil.updateFacilityRating(facilityRating);

			} else {
				FacilityRating facilityRating = FacilityRatingLocalServiceUtil .createFacilityRating(CounterLocalServiceUtil.increment(FacilityRating.class.getName()));

				long facilityRatingId = facilityRating.getFacilityRatingId();
				_log.info("New facilityRatingId: " + facilityRatingId);

				BeanPropertiesUtil.copyProperties(facilityRatingCommonDTO, facilityRating);
				facilityRating.setFacilityRatingId(facilityRatingId);
				facilityRating.setRatingUniqueId(generateUniqueId(facilityRatingId));
				facilityRating.setCreateDate(new Date());
				facilityRating.setModifiedDate(new Date());
					_log.info("facilityRating:: "+facilityRating);
				return FacilityRatingLocalServiceUtil.addFacilityRating(facilityRating);
			}

		} catch (Exception e) {
			_log.error("Error in saveFacilityRating: " + e.getMessage(), e);
		}

		return null;
	}

	private String generateUniqueId(long facilityRatingId) {
		Calendar calendar = Calendar.getInstance();
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		int currentMonth = calendar.get(Calendar.MONTH) + 1; // MONTH is 0-based
		int currentYear = calendar.get(Calendar.YEAR);
		String formattedDay = String.format("%02d", currentDay);
		String formattedMonth = String.format("%02d", currentMonth);
		return  currentYear + formattedMonth + formattedDay + facilityRatingId;
	}
}
