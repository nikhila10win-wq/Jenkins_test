<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="/init.jsp" %>
<input type="hidden" id="validCaptchaMsg" value="<liferay-ui:message key="valid-captcha-msg"/>">

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		String signedInAs = HtmlUtil.escape(user.getFullName());

		if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
			String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());
			String dashboardURL = "/group/guest/dashboard";
			signedInAs = "<a class=\"signed-in\" href=\"" +dashboardURL + "\">" + signedInAs + "</a>";
		}
		%>

		<liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" />
	</c:when>
	<c:otherwise>

		<%
		String formName = "loginForm";

		if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
			formName += "Modal";
		}

		String redirect = ParamUtil.getString(request, "redirect");

		String login = (String)SessionErrors.get(renderRequest, "login");

		if (Validator.isNull(login)) {
			login = LoginUtil.getLogin(request, "login", company);
		}

		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}
		%>
		
		
		<div class="common-forms-div my-5">
			<div class="container">
				<div class="row">
				<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="card shadow border-0">
							<div class="card-header d-flex align-item-center justify-content-between">
								<h5><liferay-ui:message key="login"/></h5>						
							</div>
						 
							<div class="card-body"> 
								<div class="row">
									<div class="col-md-12">
										<div class="login-container">
											<portlet:actionURL name="/login/login" secure="<%= request.isSecure() %>" var="loginURL">
												<portlet:param name="mvcRenderCommandName" value="/login/login" />
											</portlet:actionURL>
								
											<aui:form  action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' cssClass="sign-in-form" method="post" name="<%= formName %>" onSubmit="event.preventDefault();" validateOnBlur="<%= false %>">
												<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
												<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
												<aui:input name="doActionAfterLogin" type="hidden" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />
								
												<div class="inline-alert-container lfr-alert-container"></div>
								
												<liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPre" />
								
												<c:choose>
													<c:when test='<%= SessionMessages.contains(request, "forgotPasswordSent") %>'>
														<div class="alert alert-success">
															<liferay-ui:message key="your-request-completed-successfully" />
														</div>
													</c:when>
													<c:when test='<%= SessionMessages.contains(request, "userAdded") %>'>
								
														<%
														String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
														%>
								
														<div class="alert alert-success">
															<liferay-ui:message key="thank-you-for-creating-an-account" />
								
															<c:if test="<%= company.isStrangersVerify() %>">
																<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="your-email-verification-code-was-sent-to-x" translateArguments="<%= false %>" />
															</c:if>
								
															<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
																<c:choose>
																	<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD, PropsValues.LOGIN_CREATE_ACCOUNT_ALLOW_CUSTOM_PASSWORD) %>">
																		<liferay-ui:message key="use-your-password-to-login" />
																	</c:when>
																	<c:otherwise>
																		<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="you-can-set-your-password-following-instructions-sent-to-x" translateArguments="<%= false %>" />
																	</c:otherwise>
																</c:choose>
															</c:if>
														</div>
													</c:when>
													<c:when test='<%= SessionMessages.contains(request, "userPending") %>'>
								
														<%
														String userEmailAddress = (String)SessionMessages.get(request, "userPending");
														%>
								
														<div class="alert alert-success">
															<liferay-ui:message arguments="<%= HtmlUtil.escape(userEmailAddress) %>" key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved" translateArguments="<%= false %>" />
														</div>
													</c:when>
												</c:choose>
								
												<c:if test="<%= PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES && PropsValues.SESSION_TEST_COOKIE_SUPPORT %>">
													<div class="alert alert-danger" id="<portlet:namespace />cookieDisabled" style="display: none;">
														<liferay-ui:message key="authentication-failed-please-enable-browser-cookies" />
													</div>
												</c:if>
								
												<c:choose>
													<c:when test="<%= company.isSendPasswordResetLink() %>">
														<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed-due-to-incorrect-credentials-or-account-lockout" />
													</c:when>
													<c:otherwise>
														<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
													</c:otherwise>
												</c:choose>
								
												<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
												<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
												<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
												<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
												<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
												<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />
								
												<c:choose>
													<c:when test="<%= company.isSendPasswordResetLink() %>">
														<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>" message="authentication-failed-due-to-incorrect-credentials-or-account-lockout" />
													</c:when>
													<c:otherwise>
														<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>" message="authentication-failed" />
													</c:otherwise>
												</c:choose>
								
												<liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
												<liferay-ui:error exception="<%= UserScreenNameException.MustNotBeNull.class %>" message="the-screen-name-cannot-be-blank" />
								
												<liferay-util:dynamic-include key="com.liferay.login.web#/login.jsp#alertPost" />
								
												<aui:fieldset>
								
													<%
													String loginLabel = null;
								
													if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
														loginLabel = "email-address";
													}
													else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
														loginLabel = "user-name";
													}
													else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
														loginLabel = "id";
													}
													%>
								
													<aui:input cssClass="clearable" label="<%= loginLabel %>" name="login" required="<%= true %>" showRequiredLabel="<%= false %>" type="text" placeholder="Please enter your User Name">
														<c:if test="<%= authType.equals(CompanyConstants.AUTH_TYPE_EA) %>">
															<aui:validator name="email" />
														</c:if>
													</aui:input>
													
													
													<div class="position-relative">
														<aui:input name="password" label="<%= LanguageUtil.get(request, "password-label")%>" required="<%= true %>" showRequiredLabel="<%= false %>" type="password" id="password" placeholder="Please enter your Password" />
														<i class="bi bi-eye eye-icon" id="togglePassword" style="cursor: pointer;"></i>
													</div>
												
													<div class="form-group">
													<label><liferay-ui:message key="text-verification-code"/><sup class="text-danger">*</sup></label>
														<div class="d-flex align-items-center">
												                <canvas id="captchaCanvas" class="captcha-image"></canvas>
												                <button type="button" id="refreshCaptcha" class="btn btn-refresh btn-sm" aria-label="Refresh Captcha"><i class="bi bi-repeat"></i></button>
												            <input type="text" class="form-control" id="captchaInput" name="captchaInput" placeholder="<%= LanguageUtil.get(request, "enter-captcha") %>">
												        </div>
												     <div id="captchaError" class="error"></div>
													</div>
													
													<p class="text-2 text-right pointer">
														<a class="pointer text-decoration-none" href="/login?p_p_id=com_liferay_login_web_portlet_LoginPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_login_web_portlet_LoginPortlet_mvcRenderCommandName=%2Flogin%2Fforgot_password&saveLastPath=false">
															<liferay-ui:message key="forgot-password" />
														</a>
													</p>
													
													<%--data-toggle="modal" data-target="#forgotPsdmodal" --%>
								
													<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>
								
													<%--
														<c:if test="<%= company.isAutoLogin() %>">
															<aui:input checked="<%= rememberMe %>" name="rememberMe" type="checkbox" />
														</c:if>
													--%>
												</aui:fieldset>
								
												<aui:button-row>
													<aui:button type="button"  value="<%= LanguageUtil.get(request, "login-label")%>" onclick="validateForm()"/>
													<p class="mt-3 text-3">
														<span class="span-text"><liferay-ui:message key="dont-have-an-account" /></span> <a href="/registration" class="text-signup"><liferay-ui:message key="register-here" /></a>
													</p>
												</aui:button-row>
											</aui:form>
											<%-- <%@ include file="/navigation.jspf" %>  --%>
										</div> 
									</div> 
								</div> 
							</div> 
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div> 
		
		<!-- Forgot Password Module -->
		<div class="modal fade" id="forgotPsdmodal" tabindex="-1" aria-labelledby="#forgotPsdmodalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<h5 class="modal-title" id="forgotPsdmodalLabel"><liferay-ui:message key="Forgot Password"/></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				  <span aria-hidden="true"><i class="bi bi-x-circle-fill text-danger"></i></span>
				</button>
			  </div>
			  <div class="modal-body">
			  
			  </div> 
			</div>
		  </div>
		</div>	
		
		<!-- Forgot Password Module -->
		
		

		<aui:script sandbox="<%= true %>">
			var form = document.getElementById('<portlet:namespace /><%= formName %>');

			if (form) {
				form.addEventListener('submit', (event) => {
					<c:if test="<%= PropsValues.SESSION_ENABLE_PERSISTENT_COOKIES && PropsValues.SESSION_TEST_COOKIE_SUPPORT %>">
						if (!navigator.cookieEnabled) {
							document.getElementById(
								'<portlet:namespace />cookieDisabled'
							).style.display = '';

							return;
						}
					</c:if>

					<c:if test="<%= Validator.isNotNull(redirect) %>">
						var redirect = form.querySelector('#<portlet:namespace />redirect');

						if (redirect) {
							var redirectVal = redirect.getAttribute('value');

							redirect.setAttribute('value', redirectVal + window.location.hash);
						}
					</c:if>

					submitForm(form);
				});

				var password = form.querySelector('#<portlet:namespace />password');

				if (password) {
					password.addEventListener('keypress', (event) => {
						Liferay.Util.showCapsLock(
							event,
							'<portlet:namespace />passwordCapsLockSpan'
						);
					});
				}
			}
		</aui:script>
	</c:otherwise>
</c:choose>


<script>

function findProperty(obj, key) {
    if (typeof obj === "object") {
        if (key in obj) {
            return true;
        } else {
            return false;
        }
    }
    return false;
}

$(document).ready(function () {
	
	var formId = document.getElementById('<portlet:namespace />loginForm');
	$('#<portlet:namespace />loginForm').validate({
	rules:{
		'<portlet:namespace />login':{
			required:true,
		},
		'<portlet:namespace />password':{
			required:true,
		},
		captchaInput: {
			required:true,
			validateCaptcha:true
		}
		
	},messages:{
		'<portlet:namespace />login':{
			required:"<liferay-ui:message key="enter-login-name"/>"
		},
		'<portlet:namespace />password':{
			required:"<liferay-ui:message key="password-required"/>"
		},
		captchaInput: {
			required:"<liferay-ui:message key="please-enter-verification-code"/>"
		}
		
	}
});
	if(!findProperty($.validator.methods, 'validateCaptcha')){
		$.validator.addMethod("validateCaptcha", function(value, element) {
			const userCaptchaInput = $("#captchaInput").val();
		    if (userCaptchaInput !== currentCaptcha)
		    {
		       return false;
		    } 
		    else 
		    {
		       return true;
		    }
		}, $('#validCaptchaMsg').val());
	}
	
	  $('#togglePassword').on('click', function () {
          var input = $('#<portlet:namespace />password');  // Use namespaced ID
          var type = input.attr('type') === 'password' ? 'text' : 'password';
          input.attr('type', type);

          // Toggle icon
          $(this).toggleClass('bi-eye bi-eye-slash');
      });
	
	 
});

//Generate CAPTCHA
function generateCaptcha() {
  const characters = "0123456789";
  let captcha = "";
  for (let i = 0; i < 4; i++) {
      captcha += characters.charAt(Math.floor(Math.random() * characters.length));
  }
  return captcha;
}

//Draw CAPTCHA on canvas
function drawCaptcha(captchaText) 
{
  const canvas = document.getElementById('captchaCanvas');
  const ctx = canvas.getContext('2d');
  canvas.width = 200;
  canvas.height = 50;

  // Clear canvas
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  // Draw background
  ctx.fillStyle = "#f0f0f0";
  ctx.fillRect(0, 0, canvas.width, canvas.height);

  // Add noise
  for (let i = 0; i < 50; i++) {
      ctx.fillStyle = `rgb(${Math.random() * 255}, ${Math.random() * 255}, ${Math.random() * 255})`;
      ctx.fillRect(Math.random() * canvas.width, Math.random() * canvas.height, 2, 2);
  }

  // Draw CAPTCHA text
  ctx.font = "bold 30px Arial";
  ctx.fillStyle = "#333";
  ctx.textAlign = "center";
  ctx.fillText(captchaText, canvas.width / 2, canvas.height / 2 + 10);
}

//Initialize CAPTCHA
let currentCaptcha = generateCaptcha();
drawCaptcha(currentCaptcha);

//Refresh CAPTCHA
$("#refreshCaptcha").on("click", function () {
  currentCaptcha = generateCaptcha();
  drawCaptcha(currentCaptcha);
  $("#captchaInput").val("");
  $("#captchaError").text(""); // Clear error
  $("#captchaInput-error").text("");
});


function validateForm(){
	if($('#<portlet:namespace />loginForm').valid()){
		$('#<portlet:namespace />loginForm').submit();
	
	}
}


$(document).ready(function () {
  $(".toggle-password").click(function () {
    $(this).toggleClass("bi-eye bi-eye-slash");
    const input = $(this).closest(".input-group").find("input");
    const type = input.attr("type") === "password" ? "text" : "password";
    input.attr("type", type);
  });
});

</script>

<style>
.eye-icon {
	position: absolute;
    right: 15px;
    top: 35px;
    z-index: 9;
}
.pointer {
	color: #26268E;
}
</style>