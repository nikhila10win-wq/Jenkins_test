package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.service.persistence.DivisionMasterFinder;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true,service = DivisionMasterFinder.class)
public class DivisionMasterFinderImpl extends DivisionMasterFinderBaseImpl implements DivisionMasterFinder{
    private Log lOGGER=LogFactoryUtil.getLog(DivisionMasterFinderImpl.class);
	private static final String GET_ALL_DIVISIONS_BY_LANGUAGE = DivisionMasterFinder.class.getName()
			+ ".fetchAllDivisions";

	private static final String GET_DIVISION_BY_LANGUAGE = DivisionMasterFinder.class.getName()
			+ ".fetchDivisionByDivisionId";
	
	

	
	@SuppressWarnings("unchecked")
	public List<Object> fetchAllDivisions(String language, boolean isActive){
	    String column = switch (language.toLowerCase()) {
	        case "hi" -> "divisionname_hi";
	        case "mr" -> "divisionname_mr";
	        default -> "divisionname_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_ALL_DIVISIONS_BY_LANGUAGE).replace("${divisionNameColumn}", column);
			SQLQuery query = session.createSQLQuery(baseQuery);
		    query.addScalar("divisionid", Type.LONG);
		    query.addScalar(column, Type.STRING);
		    
		    QueryPos queryPos = QueryPos.getInstance(query);
	        queryPos.add(isActive);
			return query.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}
	
	public Object fetchDivisionByDivisionId(String language, boolean isActive, long divisionId){
	    String column = switch (language.toLowerCase()) {
	        case "hi" -> "divisionname_hi";
	        case "mr" -> "divisionname_mr";
	        default -> "divisionname_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_DIVISION_BY_LANGUAGE).replace("${divisionNameColumn}", column);
			SQLQuery query = session.createSQLQuery(baseQuery);
		    query.addScalar("divisionid", Type.LONG);
		    query.addScalar(column, Type.STRING);
		    
		    if(divisionId != 0) {
				QueryPos queryPos = QueryPos.getInstance(query);
				queryPos.add(isActive);
		        queryPos.add(divisionId);
				return query.uniqueResult();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public List<Object[]> findNewActiveDivisionsByLanguage(String languageCode,boolean isActive) {
		 String column = switch (languageCode.toLowerCase()) {
	        case "mr_in" -> "divisionname_mr";
	        default -> "divisionname_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_ALL_DIVISIONS_BY_LANGUAGE).replace("${divisionNameColumn}", column);
			SQLQuery query = session.createSQLQuery(baseQuery);
		    query.addScalar("divisionMasterid", Type.LONG);
		    query.addScalar(column, Type.STRING);
		    
		    QueryPos queryPos = QueryPos.getInstance(query);
	        queryPos.add(isActive);
			return query.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new ArrayList<>();
   }
	
	
	public Object[] fetchNewDivisionByDivisionId(String language, boolean isActive, long divisionId) {
	    String column = switch (language.toLowerCase()) {
	        case "mr_in" -> "divisionname_mr";
	        default -> "divisionname_en";
	    };

	 
	    Session session = null;
	    try {
	        session = openSession();
	        String baseQuery = customSQL.get(getClass(), GET_DIVISION_BY_LANGUAGE)
	                                    .replace("${divisionNameColumn}", column);

	      
	        SQLQuery query = session.createSQLQuery(baseQuery);
	        query.addScalar("divisionid", Type.LONG);
	        query.addScalar(column, Type.STRING);

	        if (divisionId != 0) {
	            QueryPos queryPos = QueryPos.getInstance(query);
	            queryPos.add(isActive);
	            queryPos.add(divisionId);
	            return (Object[]) query.uniqueResult();
	        }
	    } catch (Exception e) {
	        LOGGER.error("Error fetching division by ID", e);
	    }
	    return null;
	}

	
	
	@Reference private CustomSQL customSQL;
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
}
