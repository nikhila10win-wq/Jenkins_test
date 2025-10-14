package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.model.PTTeacherApplication;
import com.mhdsys.schema.model.impl.PTTeacherApplicationImpl;
import com.mhdsys.schema.service.persistence.PTTeacherApplicationFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = PTTeacherApplicationFinder.class)
public class PTTeacherApplicationFinderImpl extends PTTeacherApplicationFinderBaseImpl
		implements PTTeacherApplicationFinder {
	public static final String GET_PT_TEACHER_FORM_BY_STATUS = PTTeacherApplicationFinder.class.getName()
			+ ".getPtTeacherFormByStatus";
	public static final String GETPARTICIPANTNAME = PTTeacherApplicationFinder.class.getName()
			+ ".getParticipantName";
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	private CustomSQL customSQL;

	public List<PTTeacherApplication> getPtTeacherFormByStatus(List<Long> statuses) {
		Session session = null;

		try {
			session = openSession();
			String sql = customSQL.get(getClass(), GET_PT_TEACHER_FORM_BY_STATUS);
			String inClause = statuses.stream().map(id -> "?").collect(Collectors.joining(", "));
			sql = sql.replace("$PARAMS$", inClause);
			LOGGER.info("Dynamic SQL Query: " + sql);

			SQLQuery query = session.createSQLQuery(sql);
			query.setCacheable(false);
			query.addEntity("PTTeacherApplication", PTTeacherApplicationImpl.class);
			QueryPos qPos = QueryPos.getInstance(query);
			for (Long status : statuses) {
				qPos.add(status);
			}
			return query.list();
		} catch (Exception e) {
			LOGGER.error("Error fetching PT Teacher forms by status: ", e);
		} finally {
			closeSession(session);
		}

		return Collections.emptyList(); 
	}
	
	public List<String> getParticipantNames(String searchParticipant) {
	    List<String> participantNames = new ArrayList<>();
	    Session session = null;

	    try {
	        session = openSession();
	        String sql = customSQL.get(getClass(), GETPARTICIPANTNAME);

	        SQLQuery query = session.createSQLQuery(sql);
	        query.addScalar("participantName", Type.STRING); 
	        QueryPos qPos = QueryPos.getInstance(query);
	        qPos.add("%" + searchParticipant + "%"); // Bind first placeholder
	        qPos.add("%" + searchParticipant + "%"); // Bind second placeholder

	        participantNames = query.list(); 
	    } catch (Exception e) {
	        LOGGER.error("Error fetching participant names: ", e);
	    } finally {
	        closeSession(session);
	    }

	    return participantNames;
	}


}
