<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<portlet:resourceURL id="<%=AwardsWebPortletKeys.SPORTS_APPLICATION%>" var="saveSportsApplicationURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.APPLY_AWARD_APPLICATION_MVC_RESOURCE_COMMAND%>" var="sendToDeskOfficerFormURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.SEND_TO_RESPECTIVE_SPORTS_DESK_OFFICERS%>" var="sendToSportsDeskOfficerFormURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.SAVE_ASSOCIATION_VERIFICATION_BY_DESK_OFFICER%>" var="associationVerificationURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.APPROVE_DESK_OFFICERS_ASSINMENT%>" var="approveDeskOfficerAssignmentFormURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.EDITAWARDAPPLICATIONMVCRESOURCECOMMAND%>" var="editAwardApplicationURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.DELETEAWARDAPPLICATIONMVCRESOURCECOMMAND%>" var="deleteAwardApplicationURL" />
<portlet:resourceURL id="<%=AwardsWebPortletKeys.VALIDATE_AWARDS_POINTS%>" var="validateAwardsPointsURL" />