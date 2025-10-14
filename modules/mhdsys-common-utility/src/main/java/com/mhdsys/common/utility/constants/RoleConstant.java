package com.mhdsys.common.utility.constants;

import com.liferay.portal.kernel.model.User;
import com.mhdsys.common.util.RoleUtil;

import java.util.Arrays;
import java.util.List;

public class RoleConstant {
	public static final String PT_TEACHER = "PT-Teacher";
	public static final String PRINCIPAL = "Principal";
	public static final String HO_ADMIN = "HO-Admin";
	public static final String COMMANDING_OFFICER = "CommandingOfficer";
	public static final String SCHOOLCOLLEGE = "SchoolCollege";
	public static final String DESK_OFFICER = "Desk Officer";
	public static final String ASSOCIATION = "Association";
	public static final String SPORTS_DEPARTMENT_DESK_OFFICER = "SportsDepartmentDeskOfficer";
	public static final String LOCAL_SELF_GOV = "LocalSelfGov";
	public static final String SPORTS_ASSOCIATION = "SportsAssociation";
	public static final String SPORTS_COMPLEX = "SportsComplex";
	public static final String YOUTH_INSTITUTE = "YouthInstitute";
	public static final String SPORTS_PERSON = "Sport Person";
	public static final String COACH = "Coach";
	public static final String GUEST = "Guest";
	public static final String DDD = "DDD";
	public static final String SCOUT_MASTER = "Scout Master";
	public static final String GUIDE_MASTER = "Guide Master";
	public static final String DEPUTY_DIRECTOR = "Deputy Director";
	public static final String ASSISTANT_DIRECTOR = "Assitant Director";
	
	
	
	public static final String DESK_OFFICER_PREFIX = "Desk Officer ";

	public static final List<String> SPORTS_NAMES = Arrays.asList(
		"Cricket", "Badminton", "Kho Kho", "Hockey", "Kabaddi", 
		"Volleyball", "Athletics"
	);

	public static boolean isSportsDeskOfficer(User user, long companyId) {
		for (String sport : SPORTS_NAMES) {
			String roleName = DESK_OFFICER_PREFIX + sport;
			if (RoleUtil.hasRole(user, roleName, companyId)) {
				return true;
			}
		}
		return false;
	}


	
	
	public static boolean isPtTeacher(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.PT_TEACHER, companyId);
	}

	public static boolean isPrincipal(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.PRINCIPAL, companyId);
	}

	public static boolean isHOAdmin(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.HO_ADMIN, companyId);
	}

	public static boolean isSportsPerson(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.SPORTS_PERSON, companyId);
	}

	public static boolean isSportsCoach(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.COACH, companyId);
	}

	public static boolean isAssociation(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.ASSOCIATION, companyId);
	}

	public static boolean isDeskOfficer(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.DESK_OFFICER, companyId);
	}

	public static boolean isDDD(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.DDD, companyId);
	}

	public static boolean isScoutMaster(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.SCOUT_MASTER, companyId);
	}

	public static boolean isGuideMaster(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.GUIDE_MASTER, companyId);
	}

	public static boolean isDeputyDirector(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.DEPUTY_DIRECTOR, companyId);
	}

	public static boolean isAssistantDirector(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.ASSISTANT_DIRECTOR, companyId);
	}
	public static boolean isCommandingOfficer(User user, long companyId) {
		return RoleUtil.hasRole(user, RoleConstant.COMMANDING_OFFICER, companyId);
	}
}
