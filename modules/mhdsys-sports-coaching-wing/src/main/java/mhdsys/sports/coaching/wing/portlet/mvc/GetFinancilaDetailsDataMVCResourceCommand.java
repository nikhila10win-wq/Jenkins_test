package mhdsys.sports.coaching.wing.portlet.mvc;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mhdsys.schema.model.TrainingCentreFinancialDetail;
import com.mhdsys.schema.service.TrainingCentreFinancialDetailLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;

@Component(immediate = true, property = {
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name=" + SportsCoachinWingMvcCommand.GET_FINANCIAL_DETAILS_DATA_URL }, service = MVCResourceCommand.class)
public class GetFinancilaDetailsDataMVCResourceCommand implements MVCResourceCommand {

	private static final Log _log = LogFactoryUtil.getLog(GetFinancilaDetailsDataMVCResourceCommand.class);

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		/*
		 * double coachAmount=50000; double finalCoachAmount=0;
		 * 
		 * double sportsPersonAmount=1500; double finalSportsPersonAmount=0;
		 * 
		 * double servanAmount=6000; double finalServantAmount=0;
		 * 
		 * double honorariumAmount=10000;
		 * 
		 * double snaksAmount=(10*50*2);
		 */
		
		_log.info("Entry into GetFinancilaDetailsDataMVCResourceCommand ::  serveResource ::");
		
		long trainingCenterId = ParamUtil.getLong(resourceRequest, "trainingCenterId");
		_log.info("trainingCenterId :::" + trainingCenterId);
		
		
		List<TrainingCentreFinancialDetail> financialDetails = null;
		
		try {
			financialDetails=TrainingCentreFinancialDetailLocalServiceUtil.findByTrainingCentreId(trainingCenterId);
		}catch(Exception e) {
			_log.error("Error in code :::" +e);
		}
		JSONArray jsonArray=JSONFactoryUtil.createJSONArray();
		if(Validator.isNotNull(financialDetails) && financialDetails.size()>0) {
			for(TrainingCentreFinancialDetail financialDetail:financialDetails) {
				JSONObject obj=JSONFactoryUtil.createJSONObject();
				obj.put("desc", financialDetail.getDescription());
				obj.put("id", financialDetail.getFinancialDetailId());
				obj.put("requestedAmount", financialDetail.getRequestedAmount());
				obj.put("grAmount", financialDetail.getGrAmount());
				jsonArray.put(obj);
			}
		}
		
		/*
		 * List<Coach> coachList = null; try {
		 * coachList=CoachLocalServiceUtil.findByTrainingCentreId(trainingCenterId); }
		 * catch (Exception e) { _log.error("Error in code ::" +e); }
		 * 
		 * _log.info("coachList ::" +coachList); if(Validator.isNotNull(coachList) &&
		 * coachList.size()>0) { finalCoachAmount=coachList.size() * 50000; }
		 * 
		 * List<SportsPerson> sportsPersons = null;
		 * 
		 * try { sportsPersons=SportsPersonLocalServiceUtil.findByTrainingCentreId(
		 * trainingCenterId); } catch (Exception e) { _log.error("Error in code ::" +e);
		 * }
		 * 
		 * _log.info("sportsPersons ::" +sportsPersons);
		 * if(Validator.isNotNull(sportsPersons) && sportsPersons.size()>0) {
		 * finalSportsPersonAmount=((sportsPersons.size()+ coachList.size())*1500);
		 * snaksAmount=(snaksAmount*sportsPersons.size()); }
		 * 
		 * 
		 * List<Servant> servantList = null; try {
		 * servantList=ServantLocalServiceUtil.getByTrainingCenterId(trainingCenterId);
		 * } catch (Exception e) { _log.error("Error in code ::" +e); }
		 * 
		 * _log.info("servantList ::" +servantList);
		 * 
		 * if(Validator.isNotNull(servantList) && servantList.size()>0) {
		 * finalServantAmount=(servantList.size() * 12 * 6000); }
		 * 
		 * 
		 * _log.info("finalCoachAmount ::"+finalCoachAmount);
		 * _log.info("finalServantAmount ::"+finalServantAmount);
		 * _log.info("snaksAmount ::"+snaksAmount);
		 */
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		
		try {
			resourceResponse.getWriter().print(jsonArray.toJSONString());
		} catch (IOException e) {
			_log.error("Error in code :::" + e);
		}
		return false;
	}

}
