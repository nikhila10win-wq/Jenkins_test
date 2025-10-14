package com.mhdsys.common.service.masters;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.common.api.masters.AllMasterCommonApi;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.SportsMaster;
import com.mhdsys.schema.model.SportsTypeMaster;
import com.mhdsys.schema.model.TalukaMaster;
import com.mhdsys.schema.service.DistrictMasterLocalService;
import com.mhdsys.schema.service.DivisionMasterLocalService;
import com.mhdsys.schema.service.SportsMasterLocalService;
import com.mhdsys.schema.service.SportsTypeMasterLocalService;
import com.mhdsys.schema.service.TalukaMasterLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author yetish
 */
@Component(immediate = true, service = AllMasterCommonApi.class)
public class AllMasterCommonService implements AllMasterCommonApi{
    private static Log LOGGER = LogFactoryUtil.getLog(AllMasterCommonService.class);
	@Override
	public JSONObject getAllMasterData(String userId,String source) {
		JSONObject masterDataJSONObject = JSONFactoryUtil.createJSONObject();
		masterDataJSONObject.put("dataStatus", true);
		JSONArray districtMasterJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray divisionMasterJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray sportsMasterJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray sportsTypeMasterJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray talukaMasterJSONArray = JSONFactoryUtil.createJSONArray();
		
		/* All Active District Master Data */
		List<DistrictMaster> districtMasterList = null;
		try {
			districtMasterList = districtLocalService.getByActiveState(true);
			if (Validator.isNotNull(districtMasterList)) {
				for (DistrictMaster districtMaster : districtMasterList) {
					districtMasterJSONArray.put(JSONFactoryUtil.createJSONObject(districtMaster.toString()));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			masterDataJSONObject.put("dataStatus", false);
			masterDataJSONObject.put("dataErrorMsg","Error in getting District Master List- " + e.getMessage());
		}
		masterDataJSONObject.put("districtMasterData", districtMasterJSONArray);
         
		/* All Active Division Master Data */
		List<DivisionMaster> divisionMasterList = null;
		try {
			divisionMasterList = divisionLocalService.getByActiveState(true);
			if(Validator.isNotNull(divisionMasterList)) {
				for(DivisionMaster divisionMaster : divisionMasterList) {
					divisionMasterJSONArray.put(JSONFactoryUtil.createJSONObject(divisionMaster.toString()));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			masterDataJSONObject.put("dataStatus", false);
			masterDataJSONObject.put("dataErrorMsg", "Error in getting Division Master List- " + e.getMessage());
		}
		masterDataJSONObject.put("divisionMasterData", divisionMasterJSONArray);
		
		/* All Active Sports Master Data */
		List<SportsMaster> sportsMasterList = null;
		try {
			sportsMasterList = sportsMasterLocalService.getByActiveState(true);
			if(Validator.isNotNull(sportsMasterList)) {
				for(SportsMaster sportsMaster : sportsMasterList) {
					sportsMasterJSONArray.put(JSONFactoryUtil.createJSONObject(sportsMaster.toString()));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			masterDataJSONObject.put("dataStatus", false);
			masterDataJSONObject.put("dataErrorMsg", "Error in getting Sports Master List- " + e.getMessage());
		}
		masterDataJSONObject.put("sportsMasterData", sportsMasterJSONArray);
		
		/* All Active Sports Type Master Data */
		List<SportsTypeMaster> sportsTypeMasterList = null;
		try {
			sportsTypeMasterList = sportsTypeMasterLocalService.getByActiveState(true);
			if(Validator.isNotNull(sportsTypeMasterList)) {
				for(SportsTypeMaster sportsTypeMaster : sportsTypeMasterList) {
					sportsTypeMasterJSONArray.put(JSONFactoryUtil.createJSONObject(sportsTypeMaster.toString()));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			masterDataJSONObject.put("dataStatus", false);
			masterDataJSONObject.put("dataErrorMsg", "Error in getting Sports Type Master List- " + e.getMessage());
		}
		masterDataJSONObject.put("sportsTypeMasterData", sportsTypeMasterJSONArray);
		
		/* All Active Taluka Master Data */
		List<TalukaMaster> talukaMasterList = null;
		try {
			talukaMasterList = talukaMasterLocalService.getByActiveState(true);
			if(Validator.isNotNull(talukaMasterList)) {
				for(TalukaMaster talukaMaster: talukaMasterList) {
					talukaMasterJSONArray.put(JSONFactoryUtil.createJSONObject(talukaMaster.toString()));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			masterDataJSONObject.put("dataStatus", false);
			masterDataJSONObject.put("dataErrorMsg", "Error in getting Taluka Master List- " + e.getMessage());
		}
		masterDataJSONObject.put("talukaMasterData", talukaMasterJSONArray);
		
		return masterDataJSONObject;
	}
	
	@Reference private DistrictMasterLocalService districtLocalService;
	@Reference private DivisionMasterLocalService divisionLocalService;
	@Reference private SportsMasterLocalService sportsMasterLocalService;
	@Reference private SportsTypeMasterLocalService sportsTypeMasterLocalService;
	@Reference private TalukaMasterLocalService talukaMasterLocalService;
}

