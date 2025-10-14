package com.mhdsys.common.service.sports.facility.booking;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.sportsFacilityBooking.SportsFacilityBookingCommonApi;
import com.mhdsys.common.pojo.SportsFacilityBookingDTO;
import com.mhdsys.schema.model.sportsFacilityBooking;
import com.mhdsys.schema.service.sportsFacilityBookingLocalServiceUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = SportsFacilityBookingCommonApi.class)
public class SportsFacilityBookingCommonService implements SportsFacilityBookingCommonApi{
	private Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	@Override
	public sportsFacilityBooking saveSportsFacilityBooking(SportsFacilityBookingDTO sportsFacilityBookingDTO) {
		
		try {

			_log.info("SPORTS FACILITY BOOKING SERVICE :::::: ");

			if (sportsFacilityBookingDTO.getFacilityBookingId() > 0) {

				_log.info("UPDATE / EDIT ::::::::::::: ");
				sportsFacilityBooking sportsFacilityBooking = sportsFacilityBookingLocalServiceUtil.getsportsFacilityBooking(sportsFacilityBookingDTO.getFacilityBookingId());

				long sportsFacilityBookingId = sportsFacilityBookingDTO.getFacilityBookingId();
				Date existingCreatedDate = sportsFacilityBooking.getCreateDate();
				
				_log.info("sportsFacilityBookingId : " + sportsFacilityBookingId);
				BeanPropertiesUtil.copyProperties(sportsFacilityBookingDTO, sportsFacilityBooking);
				sportsFacilityBooking.setFacilityBookingId(sportsFacilityBookingId);
				sportsFacilityBooking.setCreateDate(existingCreatedDate);
				
				return sportsFacilityBookingLocalServiceUtil.updatesportsFacilityBooking(sportsFacilityBooking);
			} else {
				_log.info("SAVE ::::::::::::: ");
				sportsFacilityBooking sportsFacilityBooking = sportsFacilityBookingLocalServiceUtil
						.createsportsFacilityBooking(CounterLocalServiceUtil.increment(sportsFacilityBooking.class.getName()));
				long sportsFacilityBookingId = sportsFacilityBooking.getFacilityBookingId();
				_log.info("sportsFacilityBookingId Id : " + sportsFacilityBookingId);
				BeanPropertiesUtil.copyProperties(sportsFacilityBookingDTO, sportsFacilityBooking);
				sportsFacilityBooking.setFacilityBookingId(sportsFacilityBookingId);
				sportsFacilityBooking.setCreateDate(new Date());
				return sportsFacilityBookingLocalServiceUtil.addsportsFacilityBooking(sportsFacilityBooking);
			}

		} catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
		return null;
	}

}
