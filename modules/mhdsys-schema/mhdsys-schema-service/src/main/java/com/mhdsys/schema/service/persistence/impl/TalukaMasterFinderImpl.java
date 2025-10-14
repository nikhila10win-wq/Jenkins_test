package com.mhdsys.schema.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.schema.service.persistence.TalukaMasterFinder;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true,service = TalukaMasterFinder.class)
public class TalukaMasterFinderImpl extends TalukaMasterFinderBaseImpl implements TalukaMasterFinder{

	private static final String GET_ALL_TALUKAS_BY_DIVISION_ID_AND_DISCTRIC_ID = TalukaMasterFinder.class.getName()
			+ ".fetchTalukasByDivisionIdAndDistrictId";
	
	private static final String GET_TALUKA_BY_TALUKA_ID = TalukaMasterFinder.class.getName()
			+ ".fetchTalukaByTalukaId";
	
	private static final String GET_DDT_BY_DDT_IDS = TalukaMasterFinder.class.getName()
			+ ".fetchDDTbyDDTids";
	
	private static final String GET_TALUKA_BY_TALUKA_ID_AND_LANG = TalukaMasterFinder.class.getName()
			+ ".fetchTalukaByTalukaIdAndLang";
	
	@SuppressWarnings("unchecked")
	public List<Object> fetchAllTalukasByDivisionIdAndDistrictId(String language, boolean isActive, long divisionId,  long districtId){
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
	    String TalukaColumn = switch (language.toLowerCase()) {
	        case "hi" -> "talukaName_hi";
	        case "mr" -> "talukaName_mr";
	        default -> "talukaName_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_ALL_TALUKAS_BY_DIVISION_ID_AND_DISCTRIC_ID).replace("${divisionNameColumn}", DivisionColumn)
					.replace("${districtNameColumn}", DistrictColumn).replace("${talukaNameColumn}", TalukaColumn);
			SQLQuery query = session.createSQLQuery(baseQuery);
		    query.addScalar("divisionid", Type.LONG);
		    query.addScalar(DivisionColumn, Type.STRING);
		    query.addScalar("districtId", Type.LONG);
		    query.addScalar(DistrictColumn, Type.STRING);
		    query.addScalar("talukaId", Type.LONG);
		    query.addScalar(TalukaColumn, Type.STRING);
		    
		    if(divisionId != 0 && districtId != 0) {
			    QueryPos queryPos = QueryPos.getInstance(query);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
		        queryPos.add(divisionId);
		        queryPos.add(districtId);
				return query.list();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new ArrayList<>();
	}
	
	public Object fetchTalukaByTalukaId(String language, boolean isActive, long takulaId){
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
	    String TalukaColumn = switch (language.toLowerCase()) {
	        case "hi" -> "talukaName_hi";
	        case "mr" -> "talukaName_mr";
	        default -> "talukaName_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_TALUKA_BY_TALUKA_ID).replace("${divisionNameColumn}", DivisionColumn)
					.replace("${districtNameColumn}", DistrictColumn).replace("${talukaNameColumn}", TalukaColumn);
			SQLQuery query = session.createSQLQuery(baseQuery);
			query.addScalar("divisionid", Type.LONG);
		    query.addScalar(DivisionColumn, Type.STRING);
		    query.addScalar("districtId", Type.LONG);
		    query.addScalar(DistrictColumn, Type.STRING);
		    query.addScalar("talukaId", Type.LONG);
		    query.addScalar(TalukaColumn, Type.STRING);
		    
		    if(takulaId != 0) {
			    QueryPos queryPos = QueryPos.getInstance(query);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
		        queryPos.add(takulaId);
				return query.uniqueResult();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public Object fetchDDTbyDDTids(String language, boolean isActive, long divisionId,  long districtId, long takulaId){
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
	    String TalukaColumn = switch (language.toLowerCase()) {
	        case "hi" -> "talukaName_hi";
	        case "mr" -> "talukaName_mr";
	        default -> "talukaName_en";
	    };
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_DDT_BY_DDT_IDS).replace("${divisionNameColumn}", DivisionColumn)
					.replace("${districtNameColumn}", DistrictColumn).replace("${talukaNameColumn}", TalukaColumn);
			SQLQuery query = session.createSQLQuery(baseQuery);
			query.addScalar("divisionid", Type.LONG);
		    query.addScalar(DivisionColumn, Type.STRING);
		    query.addScalar("districtId", Type.LONG);
		    query.addScalar(DistrictColumn, Type.STRING);
		    query.addScalar("talukaId", Type.LONG);
		    query.addScalar(TalukaColumn, Type.STRING);
		    
		    if(divisionId != 0 && districtId != 0 && takulaId != 0) {
			    QueryPos queryPos = QueryPos.getInstance(query);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
			    queryPos.add(isActive);
		        queryPos.add(divisionId);
		        queryPos.add(districtId);
		        queryPos.add(takulaId);
				return query.uniqueResult();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	
	public Object[] fetchNewTalukaByTalukaId(String language, boolean isActive, long takulaId){
		String TalukaColumn = switch (language.toLowerCase()) {
		case "mr_in" -> "talukaName_mr";
		default -> "talukaName_en";
		};
	    Session session = null;
		try {
			session = openSession();
			String baseQuery = customSQL.get(getClass(), GET_TALUKA_BY_TALUKA_ID_AND_LANG).replace("${talukaNameColumn}", TalukaColumn);
			SQLQuery query = session.createSQLQuery(baseQuery);
			 query.addScalar("talukaid", Type.LONG);
			 query.addScalar(TalukaColumn, Type.STRING);
		    
		    if(takulaId != 0) {
			    QueryPos queryPos = QueryPos.getInstance(query);
				queryPos.add(isActive);
		        queryPos.add(takulaId);
				return (Object[]) query.uniqueResult();
		    }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}finally {
			closeSession(session);
		}
		return null;
	}
	
	@Reference private CustomSQL customSQL;
	private Log LOGGER = LogFactoryUtil.getLog(this.getClass().getName());
}
