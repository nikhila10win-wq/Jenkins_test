package com.mhdsys.security.xss.filter.custom;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.ResourceFilter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.mhdsys.xss.filter.constants.MhdsysXssFilterPortletKeys;


@Component(
		immediate = true, 
		property = { 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.ADMIN_DASHBOARD, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.COMPETITION_DASHBOARD,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.GRIEVANCE_DASHBOARD, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.PT_TEACHER_DASHBOARD,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.SCHOOL_COLLEGE_DASHBOARD, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSGRANTREQUESTWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSADDGRANTWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSADMINISTRATIVEWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSEVENTDETAILSWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSEVENTLISTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSGRANTLISTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSNCCSTUDENTLISTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSCOUTGUIDEREGWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSTUDENTLISTWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSTUDENTREGWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSUNITREGWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAPPLICATIONCERTIFICATEVERIFICATIONLIST, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAPPLICATIONCERTIFICATEVERIFICATION, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSATHLETEWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.APPROVE_SPORTS_DESK_OFFICERS_ASSIGNMENT_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.AWARDS_APPLICATION_LIST_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.AWARDS_POINTS_LIST_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAWARDSWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.OBJECTION_LIST_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.SUGGESTION_OBJECTION_MANAGEMENTWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.VERIFY_AWARDS_APPLICATIONS_SPORTS_DESK_OFFICERS_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSBUDGETADDITION,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSDOWNLOADREPORTS, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSBUDGETLIST, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSBUDGETWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSFUNDSDISTRIBUTIONLIST, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSFUNDSDISTRIBUTION,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYS_COMPETITION_INITIATION_LIST_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYS_COMPETITION_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYS_COMPETITION_SCHEDULED_LIST_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYS_COMPETITION_INITIATION_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYS_COMPETITION_PRINCIPAL_APPROVED_LIST_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYS_COMPETITION_PT_TEACHER_REQUEST_LIST_MANAGEMENTWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYS_UPLOADED_RESULT_LIST_MANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSCONSTRUCTIONTRACKERWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSESTABLISHMENTLISTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSESTABLISHMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAPPLYDISTRICTLEVELGRANTSANDSCHEMES, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSDISTRICTLEVELGRANTSANDSCHEMES, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSDOWNLOADAPPLICATIONS,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSFUNDDISTRIBUTION, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSGRANTSANDSCHEMES, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSTATELEVELDASHBOARD, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSTATELEVELGRANTSANDSCHEMES, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSTATELEVELLIST, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSGRIEVANCECOMPLAINTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSGRIEVANCELISTWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSHOAPPLICATIONLISTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSPROFILEMANAGEMENTWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSREGISTRATIONSCHOOLCOLLEGEOFFICERWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSREGISTRATIONSPORTPERSONWEBPORTLET, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSREGISTRATIONWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSCHOOLCHANGEWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSSPORTSCOACHINGWING, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAWARDYOUTHLIST, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAWARDYOUTHORGLIST, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAWARDYOUTHORGWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSAWARDYOUTHWEB, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDSYSYOUTHAWARDWEB,
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDYSFACILITYENTRYPASSPORTLET, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.ENTRYPASS_QR_VIEWPORTELT, 
				 "javax.portlet.name=" + MhdsysXssFilterPortletKeys.MHDYSKREEDAPITHSPORTSFACILITY, 
		}, 
		service = PortletFilter.class
)
public class MhdsysCustomPortletFilter implements ActionFilter, RenderFilter, ResourceFilter {

	private static final Log LOGGER = LogFactoryUtil.getLog(MhdsysCustomPortletFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain filterChain)
			throws IOException, PortletException {
		LOGGER.debug("RenderFilter :::: doFilter ::::::: ");
		String currentCompleteURL = PortalUtil.getCurrentURL(request);
		LOGGER.debug("currentUrl:::::::" + currentCompleteURL);
		boolean foundXSS = false;
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			String value = request.getParameter(param);
			if(param.contains("redirect")) {
				if(value.contains("www.google.com") || value.contains("owasp.org")) {
					LOGGER.debug("inside param and value" + value);
					foundXSS = true;
				}
			}
			if (foundXSS) {
				break;
			}
			foundXSS = containsJS(value);
		}
		
		
		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
		LOGGER.debug("foundXSS ::::::"+foundXSS);
		if (!foundXSS) {
			filterChain.doFilter(request, response);
		} else {
			LOGGER.debug("Render Request is more prone to XSS attack");
			//SessionErrors.add(request, "xss.error");
			String accessDeniedReason = "New Invalid REFERER  request headers are both not same so we block the request !";
			//httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedReason);
			 try {
				PortalUtil.sendError(403, new PrincipalException("Access denied."), httpRequest, httpResponse);
			} catch (ServletException e) {
				LOGGER.debug("Error in principal exception in message::::::::::" + e.getMessage());
			}
			//return;
		}
	}
	
	@Override
	public void doFilter(ResourceRequest request, ResourceResponse response, FilterChain filterChain)
			throws IOException, PortletException {
		LOGGER.debug("ResourceFilter :::: doFilter ::::::: ");
		boolean foundXSS = false;
		Enumeration<String> params = request.getParameterNames();
		
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			String value = request.getParameter(param);
			LOGGER.debug("Value of param:::::::"+value+":::::::::::Param:::::::::::::"+param);
			if (foundXSS) {
				break;
			}
			foundXSS = containsJS(value);
		}
		

		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
		
		LOGGER.debug("foundXSS ::::::"+foundXSS);
		if (!foundXSS) {
			filterChain.doFilter(request, response);
		} else {
			LOGGER.debug("Resource Request is more prone to XSS attack");
			SessionErrors.add(request, "xss.error");
			String accessDeniedReason = "Please provide data in valid format";
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedReason);
			 try {
				PortalUtil.sendError(403, new PrincipalException("Access denied."), httpRequest, httpResponse);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void doFilter(ActionRequest request, ActionResponse response, FilterChain filterChain)
			throws IOException, PortletException {
		LOGGER.debug("ActionFilter :::: doFilter ::::::: ");
		boolean foundXSS = false;
		
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			String value = request.getParameter(param);
			if(param.equalsIgnoreCase("login") || param.equalsIgnoreCase("rememberMe")) {
				if(value.contains("home")) {
					foundXSS = true;
				}
			}
			if(param.equalsIgnoreCase("g-recaptcha-response")) {
				if(value.contains("login-verify")) {
					foundXSS = true;
				}
			}
			if (foundXSS) {
				break;
			}
			foundXSS = containsJS(value);
		}
		
		String contentType = request.getContentType();
		if(contentType != null && contentType.equalsIgnoreCase("multipart/form-data")) {
			UploadPortletRequest ur = PortalUtil.getUploadPortletRequest(request);
			Enumeration<String> params1 = ur.getParameterNames();
			while (params1.hasMoreElements()) {
				String param = params1.nextElement();
				String value = ur.getParameter(param);
				if(param.equalsIgnoreCase("login") || param.equalsIgnoreCase("rememberMe")) {
					if(value.contains("home")) {
						foundXSS = true;
					}
				}
				if(param.equalsIgnoreCase("g-recaptcha-response")) {
					if(value.contains("login-verify")) {
						foundXSS = true;
					}
				}
				if (foundXSS) {
					break;
				}
				foundXSS = containsJS(value);
			}
		}
		

		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(response);
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
		
		if (!foundXSS) {
			filterChain.doFilter(request, response);
		} else {
			String accessDeniedReason = "New Invalid REFERER  request headers are both not same so we block the request !";
			 try {
				PortalUtil.sendError(403, new PrincipalException("Access denied."), httpRequest, httpResponse);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}

	}
	
	public boolean containsJS(String value) {
		boolean foundXSS = false;
		if (value == null || value.isEmpty()) {
			return false;
		}
		
		for (Pattern scriptPattern : patterns){
			Matcher patternMatcher = scriptPattern.matcher(value);
			if(patternMatcher.find()) {
				foundXSS = true;
				 break;
			}
           
        }
		return foundXSS;
	}
	
	 private static Pattern[] patterns = new Pattern[]{
			 	Pattern.compile("www.google.com", Pattern.CASE_INSENSITIVE),
			 	Pattern.compile("owasp.org", Pattern.CASE_INSENSITIVE),
			 	Pattern.compile("alert", Pattern.CASE_INSENSITIVE),
			 	 // For all script fragments
		        Pattern.compile("</?\\w+((\\s+\\w+(\\s*=\\s*(?:\".*?\"|'.*?'|[\\^'\">\\s]+))?)+\\s*|\\s*)/?>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<event-source>(.*?)</event-source>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<?>(.*?)</?>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<!>(.*?)</!>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<%>(.*?)</%>", Pattern.CASE_INSENSITIVE),
		        // Script fragments
		        Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
		        // src='...'
		        Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        // lonely script tags
		        Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("</event-source>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<event-source(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("</?>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<?(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("</!>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<!(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("</%>", Pattern.CASE_INSENSITIVE),
		        Pattern.compile("<%(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        // eval(...)
		        Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        // expression(...)
		        Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        // javascript:...
		        Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
		        // vbscript:...
		        Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
		        // onload(...)=...
		        Pattern.compile("onafterprint(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onbeforeprint(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onbeforeunload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onhashchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onmessage(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onoffline(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ononline(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onpagehide(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onpageshow(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onpopstate(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onresize(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onstorage(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onunload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onblur(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oncontextmenu(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onfocus(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oninput(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oninvalid(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onreset(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onsearch(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onselect(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onsubmit(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onkeydown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onkeypress(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onkeyup(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondblclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onmousedown(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onmousemove(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onmouseout(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onmouseover(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onmouseup(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onmousewheel(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onwheel(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondrag(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondragend(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondragenter(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondragleave(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondragover(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondragstart(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondrop(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onscroll(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oncopy(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oncut(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onpaste(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onabort(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oncanplay(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oncanplaythrough(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("oncuechange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ondurationchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onemptied(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onended(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onloadeddata(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onloadedmetadata(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onloadstart(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onpause(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onplay(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onplaying(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onprogress(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onratechange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onseeked(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onseeking(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onstalled(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onsuspend(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ontimeupdate(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onvolumechange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("onwaiting(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
		        Pattern.compile("ontoggle(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
		    };
  }
