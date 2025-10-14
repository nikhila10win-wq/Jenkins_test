package mhdsys.sports.coaching.wing.portlet.mvc.render;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.mhdsys.schema.model.Coach;
import com.mhdsys.schema.model.DistrictMaster;
import com.mhdsys.schema.model.DivisionMaster;
import com.mhdsys.schema.service.CoachLocalServiceUtil;
import com.mhdsys.schema.service.DistrictMasterLocalServiceUtil;
import com.mhdsys.schema.service.DivisionMasterLocalServiceUtil;
import java.util.Arrays;
import java.util.List;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import mhdsys.sports.coaching.wing.constants.MhdsysSportsCoachingWingPortletKeys;
import mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand;
import mhdsys.sports.coaching.wing.dto.CoachDTO;

@Component(immediate = true, 
property = {
		"javax.portlet.name=" + MhdsysSportsCoachingWingPortletKeys.MHDSYSSPORTSCOACHINGWING,
		"mvc.command.name="+SportsCoachinWingMvcCommand.ADD_MONTHLY_REPORT
		},
service = MVCRenderCommand.class)
public class AddMonthlyReportMvcRenderCommand implements MVCRenderCommand {
	
	public static final Log _log=LogFactoryUtil.getLog(AddMonthlyReportMvcRenderCommand.class.getName());
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.info("AddMonthlyReportMvcRenderCommand ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String mode = ParamUtil.getString(renderRequest, "mode");
		
		DivisionMaster divisionMaster=null;
		DistrictMaster districtMaster=null;
		CoachDTO coachDTO=new CoachDTO();
		Coach coach=null;
		List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
		try {
			coach = CoachLocalServiceUtil.findCoachByEmail(themeDisplay.getUser().getEmailAddress());
		} catch (Exception e) {
			_log.error("Error in code :::" +e);
		}
		if(Validator.isNotNull(coach)) {
			coachDTO.setCoachId(coach.getCoachId());
			coachDTO.setDistrict(coach.getDistrict());
			coachDTO.setSportsName(coach.getSportsName());
			coachDTO.setFullName(coach.getFullName());
			if(Validator.isNotNull(coach.getCoachId())) {
			
				try {
					districtMaster = DistrictMasterLocalServiceUtil.getDistrictMaster(Long.parseLong(coach.getDistrict()));
				} catch (Exception e) {
					_log.error("Error in code :::" +e);
				} 
				if(Validator.isNotNull(districtMaster)) {
					try {
						divisionMaster = DivisionMasterLocalServiceUtil.getDivisionMaster(districtMaster.getDivisionId());
					} catch (Exception e) {
						_log.error("Error in code ::" +e);
					}
				}
				if(Validator.isNotNull(divisionMaster)) {
					coachDTO.setDivisionName(divisionMaster.getDivisionName_en());
				}
				if(Validator.isNotNull(districtMaster)) {
					coachDTO.setDistrictName(districtMaster.getDistrictName_en());
				}
			}
			renderRequest.setAttribute("coach", coachDTO);
		}
		renderRequest.setAttribute("reportMonths", months);
		renderRequest.setAttribute("isCoach", true);
		renderRequest.setAttribute("mode", mode);
		//return "/coachingwing-monthly-report.jsp";
		return "/coachingwing-monthly-report.jsp";
	}
}
