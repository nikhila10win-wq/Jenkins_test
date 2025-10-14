package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.service.persistence.SchoolCollegeOfficerRegistrationFinder;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true,service = SchoolCollegeOfficerRegistrationFinder.class)
public class SchoolCollegeOfficerRegistrationFinderImpl extends SchoolCollegeOfficerRegistrationFinderBaseImpl implements SchoolCollegeOfficerRegistrationFinder{

	public static final String GETPARTICIPANTNAME = SchoolCollegeOfficerRegistrationFinder.class.getName()
			+ ".findByParticipantName";
	
	
	@Reference private CustomSQL customSQL;
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
	
	public List<Object[]> fetchParticipantNameAndUserId(String searchKeyword) {
	    List<Object[]> results = new ArrayList<>();
	    Session session = null;

	    try {
	        session = openSession();
	        String sql = customSQL.get(getClass(), GETPARTICIPANTNAME); // use correct constant ID

	        SQLQuery query = session.createSQLQuery(sql);
	        query.addScalar("fullname", Type.STRING);
	        query.addScalar("userid", Type.LONG);

	        QueryPos qPos = QueryPos.getInstance(query);
	        qPos.add("%" + searchKeyword + "%"); // for firstname
	        qPos.add("%" + searchKeyword + "%"); // for lastname

	        results = query.list(); // Each row is an Object[]: [fullname, userid]

	    } catch (Exception e) {
	        LOGGER.error("Error fetching participant data: ", e);
	    } finally {
	        closeSession(session);
	    }

	    return results;
	}
}
