<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%@page import="com.mhdsys.schema.model.DivisionMaster"%>
<%@page import="java.util.List"%>
<%@page import="mhdsys.sports.coaching.wing.util.SportsCoachingWingUtil"%>

<!-- Coaching-wing-form imports -->
<%@page import="mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand"%>
<%@page import="mhdsys.sports.coaching.wing.portlet.mvc.SaveSportCoachingWingMVCResourceCommand"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%-- <%@page import="mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand"%> --%>
<!-- Coaching-wing-form imports -->

<!-- coaching-wing-fiancial-detail-reports.jsp imports -->
<%-- <%@page import="mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand"%> --%>
<!-- coaching-wing-fiancial-detail-reports.jsp imports  -->

<!-- financial-details.jsp imports -->
<%-- <%@page import="mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand"%> --%>
<!-- financial-details.jsp imports -->

<!-- view.jsp imports -->
<%-- <%@page import="mhdsys.sports.coaching.wing.constants.SportsCoachinWingMvcCommand"%> --%>
<!-- view.jsp imports -->

<!--Resource URL -->
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.SAVE_CAOCHIN_WING_RESOURCE_URL%>" var="saveCoachingWingURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_DISTRICT_BY_DIVISION%>" var="getDistrictByDivisionURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_ALL_SPORTS_PRESONS%>" var="getallSportsPersonsURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_ALL_COACH%>" var="getallCoachURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_SPORTS_PERSONS%>" var="getSportsPersonsURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_COACH%>" var="getCoachURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_ALL_DISTRICT_URL%>" var="getallDistrictURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GENERATE_REPORT%>" var="generateReportURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GENERATE_MONTHLY_REPORT%>" var="generateMonathlyReportURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GENERATE_FINAL_REPORT%>" var="generateFinalReportURL" />
<portlet:resourceURL id="<%=SportsCoachinWingMvcCommand.GET_FINANCIAL_DETAILS_DATA_URL%>" var="getFinancialDetailsDataURL" />
<!--Resource URL -->

<!--Action URL -->
 <liferay-portlet:actionURL name="<%=SportsCoachinWingMvcCommand.SAVE_TRAINING_CENTER%>" var="saveTrainingCenterActionCommand"></liferay-portlet:actionURL>
 <liferay-portlet:actionURL name="<%=SportsCoachinWingMvcCommand.SUBMIT_TRAINING_CENTER_MONTHLYREPORT%>" var="submitMonthlyReportActionCommand"></liferay-portlet:actionURL>
 <!--Action URL -->