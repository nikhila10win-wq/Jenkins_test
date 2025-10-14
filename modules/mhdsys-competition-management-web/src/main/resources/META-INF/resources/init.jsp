<%@page import="com.mhdsys.competition.management.web.constants.CompetitionManagementWebPortletKeys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SAVE_COMPETITION_MASTER_MVC_RESOURCE_COMMAND%>"
var="saveCompetitionMasterURL" />
<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SAVE_COMPETITION_INITIATION_MVC_RESOURCE_COMMAND%>"
var="saveCompetitionInitiationURL" />
<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SAVE_COMPETITION_PT_TEACHER_MVC_RESOURCE_COMMAND%>"
var="ptTeacherURL" />
<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SAVE_PRINCIPAL_APPROVE_MVC_RESOURCE_COMMAND%>"
var="principalApproveFormURL" />
<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SAVE_PRINCIPAL_APPROVE_MVC_RESOURCE_COMMAND%>"
var="sendToDSOFormURL" >
 <portlet:param name="sendToDSO" value="forwardToDSO" />
</portlet:resourceURL>
<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SMART_SEARCH_PARTICIPANT_MVC_RESOURCE_COMMAND%>"
var="searchParticipantURL" />
<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SAVE_COMPETITION_SCHEDULE_MVC_RESOURCE_COMMAND%>"
var="competitionScheduleURL" />
<portlet:resourceURL id="<%=CompetitionManagementWebPortletKeys.SAVE_RESULT_UPLOAD_MVC_RESOURCE_COMMAND%>"
var="ResultUploadURL" />
<%@ page import="com.mhdsys.competition.management.web.constants.CompetitionCommonConstant" %>
