package com.mhdsys.common.service.construction.tracker;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.constructionTracker.ConstructionTrackerCommonApi;
import com.mhdsys.common.pojo.ConstructionTrackerCommonDTO;
import com.mhdsys.schema.model.ConstructionTracker;
import com.mhdsys.schema.service.ConstructionTrackerLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ConstructionTrackerCommonApi.class)

public class ConstructionTrackerCommonService implements ConstructionTrackerCommonApi {
	private Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	@Override
	public ConstructionTracker saveConstructionTracker(ConstructionTrackerCommonDTO constructionTrackerCommonDTO) {
		
		try {

			_log.info("CONSTRUCTION TRACKER SERVICE :::::: ");

			if (constructionTrackerCommonDTO.getConstructionTrackerId() > 0) {

				_log.info("UPDATE / EDIT ::::::::::::: ");
				ConstructionTracker constructionTracker = ConstructionTrackerLocalServiceUtil.getConstructionTracker(constructionTrackerCommonDTO.getConstructionTrackerId());

				long constructionTrackerId = constructionTrackerCommonDTO.getConstructionTrackerId();
				Date existingCreatedDate = constructionTracker.getCreatedDate();
				String existingUniqueId = constructionTracker.getConstructionTrackerUniqueId();
				
				_log.info("constructionTrackerId : " + constructionTrackerId);
				BeanPropertiesUtil.copyProperties(constructionTrackerCommonDTO, constructionTracker);
				constructionTracker.setConstructionTrackerId(constructionTrackerId);
				constructionTracker.setConstructionTrackerUniqueId(existingUniqueId);
				constructionTracker.setCreatedDate(existingCreatedDate);
				
				return ConstructionTrackerLocalServiceUtil.updateConstructionTracker(constructionTracker);
			} else {
				ConstructionTracker constructionTracker = ConstructionTrackerLocalServiceUtil
						.createConstructionTracker(CounterLocalServiceUtil.increment(ConstructionTracker.class.getName()));
				long constructionTrackerId = constructionTracker.getConstructionTrackerId();
				_log.info("constructionTrackerId Id : " + constructionTrackerId);
				BeanPropertiesUtil.copyProperties(constructionTrackerCommonDTO, constructionTracker);
				constructionTracker.setConstructionTrackerId(constructionTrackerId);
				constructionTracker.setConstructionTrackerUniqueId(generateUniqueId());
				constructionTracker.setCreatedDate(new Date());
				return ConstructionTrackerLocalServiceUtil.addConstructionTracker(constructionTracker);
			}

		} catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
		
		return null;
	}

	private String generateUniqueId() {
		Calendar calendar = Calendar.getInstance();
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		int currentMonth = calendar.get(Calendar.MONTH) + 1; // MONTH is 0-based
		int currentYear = calendar.get(Calendar.YEAR);
		String formattedDay = String.format("%02d", currentDay);
		String formattedMonth = String.format("%02d", currentMonth);
		// Generate a 4-digit random number
		int randomNumber = (int) (Math.random() * 9000) + 1000;
		// Return in the format: ddMMyyyy#### (e.g., 190720251234)
		return currentYear + formattedMonth + formattedDay + randomNumber;
	}

	
}
