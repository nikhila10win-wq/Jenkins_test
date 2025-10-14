package mhdsys.sports.coaching.wing.util;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.model.SchoolCollegeOfficerRegistration;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import com.mhdsys.schema.service.SchoolCollegeOfficerRegistrationLocalServiceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SportsCoachingWingUtil {
		
	public static final Log _log=LogFactoryUtil.getLog(SportsCoachingWingUtil.class);
	
	public static List<DivisionMaster> getDivision(){
		return DivisionMasterLocalServiceUtil.getByActiveState(true);
	} 
	
	public static List<SchoolCollegeOfficerRegistration> SchoolCollegeOfficersByDesignation(long comapnyId,String designation) {
		_log.info("Calling  getCoachList::");
		Role coachRole = null;
		List<Long> coachs = null;
		long[] coachss=null;
		try {
			//coachRole = RoleLocalServiceUtil.getRole(comapnyId, RoleConstant.COACH);
			coachRole = RoleLocalServiceUtil.getRole(comapnyId, designation);
		} catch (Exception e) {
			_log.error("Error in code ::" + e);
		}
		if (Validator.isNotNull(coachRole)) {
			coachss = UserLocalServiceUtil.getRoleUserIds(coachRole.getRoleId());
		}
		List<Long> coachIdList=new ArrayList<>();
		if(Validator.isNotNull(coachss) && coachss.length>0) {
			for(long coach:coachss) {
				coachIdList.add(coach);
			}
			
		}
		
		
		
		List<SchoolCollegeOfficerRegistration> coachList=new ArrayList<>();
		//_log.info("coachs size:::" +coachss);
		//for(long uid:coachss) {
			//_log.info("Uid ::" +uid);
			try {
				//SchoolCollegeOfficerRegistration schoolCollegeOfficer = SchoolCollegeOfficerRegistrationLocalServiceUtil.findByUserId(uid);
				DynamicQuery dq = SchoolCollegeOfficerRegistrationLocalServiceUtil.dynamicQuery();
				dq.add(RestrictionsFactoryUtil.in("userId",coachIdList));
				List<SchoolCollegeOfficerRegistration> SchoolCollegeOfficerRegistrationList = SchoolCollegeOfficerRegistrationLocalServiceUtil.dynamicQuery(dq);
				
				//_log.info("schoolCollegeOfficer :::" + schoolCollegeOfficer);
				
				if(Validator.isNotNull(SchoolCollegeOfficerRegistrationList) && SchoolCollegeOfficerRegistrationList.size()>0){
					for(SchoolCollegeOfficerRegistration scr:SchoolCollegeOfficerRegistrationList) {
						if(Validator.isNotNull(scr)){
							coachList.add(scr);
						}
					}
				}
				
			} catch (Exception e) {
				_log.error("Error in code :::" + e);
			}
		//}
		//_log.info("Final CoachList :::" +coachList);
		return coachList;
	}

}
