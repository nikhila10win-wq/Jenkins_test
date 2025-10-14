<%@page import="mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<script src="<%=request.getContextPath() %>/plugins/jquery.validate.min.js"></script> 

<portlet:resourceURL id="<%=MhdsysConstructionTrackerWebPortletKeys.GET_DISTRICTS%>"
var="getDistricts" />

<portlet:resourceURL id="<%=MhdsysConstructionTrackerWebPortletKeys.GET_TALUKAS%>"
var="getTalukas" />


<script>
$.validator.addMethod("pastOrToday", function(value, element) {
    if (this.optional(element)) return true;
    const inputDate = new Date(value);
    const today = new Date();
    inputDate.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);
    return inputDate <= today;
}, "<liferay-ui:message key='future-date-not-allowed' />");

    $.validator.addMethod("containsLetters", function(value, element) {
        return this.optional(element) || /[A-Za-z]/.test(value);
    }, "<liferay-ui:message key='field-must-contain-characters' />");

    $.validator.addMethod("validContact", function(value, element) {
        return this.optional(element) || /^[6-9]\d{9}$/.test(value);
    }, "<liferay-ui:message key='contact-number-invalid-pattern' />");

    $.validator.addMethod("alphanumericOnly", function(value, element) {
        return this.optional(element) || /^[A-Za-z0-9 ]+$/.test(value);
    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");

    $.validator.addMethod("alphanumericWithSpecialChars", function(value, element) {
 		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
 		    return this.optional(element) || /^[A-Za-z0-9.,\- ]+$/.test(value);
 	    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
    
    $.validator.addMethod("alphanumericWithPeroidAndHyphen", function(value, element) {
 		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
 		   return this.optional(element) || /^[A-Za-z0-9.\- ]+$/.test(value);
 	    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
    
    $.validator.addMethod("validSpaces", function(value, element) {
        return this.optional(element) || /^[^\s]+(?:\s[^\s]+)*$/.test(value);
    }, "<liferay-ui:message key='Spaces-are-allowed-only-between-words-and-No-leading-or-trailing-spaces' />");

    $.validator.addMethod("noEdgeSpaces", function(value, element) {
        return this.optional(element) || value === value.trim();
    }, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

    $.validator.addMethod("singleSpaceOnly", function(value, element) {
        return this.optional(element) || !/\s{2,}/.test(value);
    }, "<liferay-ui:message key='only-one-space-between-words-allowed' />");

    $.validator.addMethod("validCharacters", function(value, element) {
        return this.optional(element) || /^[A-Za-z0-9\s.,/#-]*$/.test(value);
    }, "<liferay-ui:message key='please-enter-valid-characters' />");

    $.validator.addMethod("noConsecutiveSpecials", function(value, element) {
        return this.optional(element) || !/([.,/#-]\s*){2,}/.test(value);
    }, "<liferay-ui:message key='no-consecutive-specials-allowed' />");

    $.validator.addMethod("onlyDotAtEnd", function(value, element) {
        return this.optional(element) || /\.$/.test(value) || /[A-Za-z0-9]$/.test(value);
    }, "<liferay-ui:message key='only-dot-at-end-allowed' />");

    $.validator.addMethod("validPersonName", function(value, element) {
        return this.optional(element) || /^[A-Za-z. ]+$/.test(value.trim());
    }, "<liferay-ui:message key='please-enter-valid-name' />");
</script>


<liferay-theme:defineObjects />

<portlet:defineObjects />