package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.service.persistence.DistrictMasterFinder;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true,service = DistrictMasterFinder.class)
public class DistrictMasterFinderImpl extends DistrictMasterFinderBaseImpl implements DistrictMasterFinder{

	private static final String GET_ALL_DISTRICTS_BY_DIVISION_ID = DistrictMasterFinder.class.getName()
			+ ".fetchAllDistrictsByDivisionId";
	
	private static final String GET_DISTRICT_BY_DISTRICT_ID = DistrictMasterFinder.class.getName()
			+ ".fetchDistrictByDistrictId";
	
	private static final String GET_DISTRICT_BY_DISTRICT_ID_AND_LANG = DistrictMasterFinder.class.getName()
			+ ".fetchDistrictByDistrictIdAndLang";
	
	@SuppressWarnings("unchecked")
	public List<Object> fetchAllDistrictsByDivisionId(String language, boolean isActive, long divisionId){
	    String DivisionColumn = switch (language.toLowerCase()) {
	        case "hi" -> "divisionname_hi";
	        case "mr" -> "divisionname_mr";
	        default -> "divisionname_en";
	    };
	    String DistrictColumn = switch (language.toLowerCase()) {
	        case "hi" -> "districtname_hi";
	        case "mr" -> "districtname_mr";
	        default -> "districtname_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_ALL_DISTRICTS_BY_DIVISION_ID).replace("${districtNameColumn}", DistrictColumn).replace("${divisionNameColumn}", DivisionColumn);
			SQLQuery query = session.createSQLQuery(baseQuery);
		    query.addScalar("divisionid", Type.LONG);
		    query.addScalar(DivisionColumn, Type.STRING);
		    query.addScalar("districtId", Type.LONG);
		    query.addScalar(DistrictColumn, Type.STRING);
		    
		    if(divisionId != 0) {
			    QueryPos queryPos = QueryPos.getInstance(query);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
		        queryPos.add(divisionId);
				return query.list();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}
	
	public Object fetchDistrictByDistrictId(String language, boolean isActive, long districtId){
	    String DivisionColumn = switch (language.toLowerCase()) {
	        case "hi" -> "divisionname_hi";
	        case "mr" -> "divisionname_mr";
	        default -> "divisionname_en";
	    };
	    String DistrictColumn = switch (language.toLowerCase()) {
	        case "hi" -> "districtname_hi";
	        case "mr" -> "districtname_mr";
	        default -> "districtname_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_DISTRICT_BY_DISTRICT_ID).replace("${districtNameColumn}", DistrictColumn).replace("${divisionNameColumn}", DivisionColumn);
			SQLQuery query = session.createSQLQuery(baseQuery);
		    query.addScalar("divisionid", Type.LONG);
		    query.addScalar(DivisionColumn, Type.STRING);
		    query.addScalar("districtId", Type.LONG);
		    query.addScalar(DistrictColumn, Type.STRING);
		    
		    if(districtId != 0) {
			    QueryPos queryPos = QueryPos.getInstance(query);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
		        queryPos.add(districtId);
				return query.uniqueResult();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	
	public Object[] fetchNewDistrictByDistrictId(String language, boolean isActive, long districtId){
	    String column = switch (language.toLowerCase()) {
	    case "mr_in" -> "districtname_mr";
	        default -> "districtname_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_DISTRICT_BY_DISTRICT_ID_AND_LANG).replace("${districtNameColumn}", column);
			SQLQuery query = session.createSQLQuery(baseQuery);
		    query.addScalar("districtid", Type.LONG);
		    query.addScalar(column, Type.STRING);
		    
		    if(districtId != 0) {
				QueryPos queryPos = QueryPos.getInstance(query);
				queryPos.add(isActive);
		        queryPos.add(districtId);
				return (Object[]) query.uniqueResult();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	
	
	@Reference private CustomSQL customSQL;
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
}
