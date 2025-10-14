package com.mhdsys.common.service.sports.facility.master;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.mhdsys.common.api.sportsFacilityMaster.SportsFacilityMasterCommonApi;
import com.mhdsys.common.pojo.SportsFacilityMasterDTO;
import com.mhdsys.schema.model.SportsFacilityMaster;
import com.mhdsys.schema.service.SportsFacilityMasterLocalServiceUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = SportsFacilityMasterCommonApi.class)
public class SportsFacilityMasterCommonService implements SportsFacilityMasterCommonApi{
	private Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	@Override
	public SportsFacilityMaster saveSportsFacilityMaster(SportsFacilityMasterDTO sportsFacilityMasterDTO) {
		
		try {

			_log.info("Sports Facility Master SERVICE :::::: ");

			if (sportsFacilityMasterDTO.getSportsFacilityId() > 0) {
			    _log.info("UPDATE / EDIT ::::::::::::: ");
			    SportsFacilityMaster existingFacility = SportsFacilityMasterLocalServiceUtil.getSportsFacilityMaster(sportsFacilityMasterDTO.getSportsFacilityId());
			    long sportsFacilityId = sportsFacilityMasterDTO.getSportsFacilityId();
			    Date existingCreatedDate = existingFacility.getCreateDate();

			    // Backup DSO/TSO fields
			    String bookingUrl = existingFacility.getBookingUrl();
			    String contactPersonName = existingFacility.getContactPersonName();
			    String contactPersonNumber = existingFacility.getContactPersonNumber();
			    boolean isUpdatedByDSO = existingFacility.getIsUpdatedByDSO();
			    String geotagPhotos = existingFacility.getGeotagPhotos();

			    BeanPropertiesUtil.copyProperties(sportsFacilityMasterDTO, existingFacility);

			    // Restore DSO/TSO values for HO update
			    if (sportsFacilityMasterDTO.isHoAction()) {
			        existingFacility.setBookingUrl(bookingUrl);
			        existingFacility.setContactPersonName(contactPersonName);
			        existingFacility.setContactPersonNumber(contactPersonNumber);
			        existingFacility.setIsUpdatedByDSO(isUpdatedByDSO);
			        existingFacility.setGeotagPhotos(geotagPhotos);
			    }

			    existingFacility.setSportsFacilityId(sportsFacilityId);
			    existingFacility.setCreateDate(existingCreatedDate);
			    existingFacility.setCreatorUserId(existingFacility.getCreatorUserId());

			    return SportsFacilityMasterLocalServiceUtil.updateSportsFacilityMaster(existingFacility);
			}
			else {
				_log.info("SAVE ::::::::::::: ");
				SportsFacilityMaster sportsFacilityMaster = SportsFacilityMasterLocalServiceUtil
						.createSportsFacilityMaster(CounterLocalServiceUtil.increment(SportsFacilityMaster.class.getName()));
				long sportsFacilityId = sportsFacilityMaster.getSportsFacilityId();
				_log.info("sportsFacilityId Id : " + sportsFacilityId);
				BeanPropertiesUtil.copyProperties(sportsFacilityMasterDTO, sportsFacilityMaster);
				sportsFacilityMaster.setSportsFacilityId(sportsFacilityId);
				sportsFacilityMaster.setCreateDate(new Date());
				return SportsFacilityMasterLocalServiceUtil.addSportsFacilityMaster(sportsFacilityMaster);
			}

		} catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
		
		return null;
	}

}
