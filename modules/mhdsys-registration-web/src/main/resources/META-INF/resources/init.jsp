<%@page import="com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:resourceURL id="<%=RegistrationWebPortletKeys.SAVE_SCHOOL_COLLEGE_OFFICER_REGISTRATION_MVC_RESOURCE%>"
var="schoolCollegeOfficerFormURL" />

<portlet:resourceURL id="<%=RegistrationWebPortletKeys.SAVE_SPORT_PERSON_REGISTRATION_MVC_RESOURCE%>"
var="saveSportPersonRegistrationURL" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>