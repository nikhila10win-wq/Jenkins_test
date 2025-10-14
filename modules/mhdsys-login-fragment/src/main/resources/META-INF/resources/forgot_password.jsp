<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>
<input type="hidden" id="validCaptchaMsg" value="<liferay-ui:message key="valid-captcha-msg"/>">

<%
User user2 = (User)request.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_USER);

if (Validator.isNull(authType)) {
	authType = company.getAuthType();
}

String login = (String)portletSession.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_USER_EMAIL_ADDRESS);

Integer reminderAttempts = (Integer)portletSession.getAttribute(WebKeys.FORGOT_PASSWORD_REMINDER_ATTEMPTS);

if (reminderAttempts == null) {
	reminderAttempts = 0;
}

renderResponse.setTitle(LanguageUtil.get(request, "forgot-password"));
%>

<portlet:actionURL name="/login/forgot_password" var="forgotPasswordURL">
	<portlet:param name="mvcRenderCommandName" value="/login/forgot_password" />
</portlet:actionURL>

<div class="common-forms-div my-5">
			<div class="container">
				<div class="row">
				<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="card shadow border-0">
							<div class="card-header d-flex align-item-center justify-content-between">
								<h5><liferay-ui:message key="forgot-password" /></h5>						
							</div>
						 
							<div class="card-body"> 
								<div class="row">
									<div class="col-md-12">
										<div class="login-container">
											<aui:form action="<%= forgotPasswordURL %>" method="post" name="fm" onSubmit="event.preventDefault();" validateOnBlur="<%= false %>">
												<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
										
												<liferay-ui:error exception="<%= CaptchaConfigurationException.class %>" message="a-captcha-error-occurred-please-contact-an-administrator" />
												<liferay-ui:error exception="<%= CaptchaException.class %>" message="captcha-verification-failed" />
												<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
												<liferay-ui:error exception="<%= NoSuchUserException.class %>" message='<%= "the-" + TextFormatter.format(HtmlUtil.escape(authType), TextFormatter.K) + "-you-requested-is-not-registered-in-our-database" %>' />
												<liferay-ui:error exception="<%= RequiredReminderQueryException.class %>" message="you-have-not-configured-a-reminder-query" />
												<liferay-ui:error exception="<%= SendPasswordException.MustBeEnabled.class %>" message="password-recovery-is-disabled" />
												<liferay-ui:error exception="<%= UserActiveException.class %>" message="your-account-is-not-active" />
												<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
												<liferay-ui:error exception="<%= UserEmailAddressException.MustValidate.class %>" message="please-enter-a-valid-email-address" />
												<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />
												<liferay-ui:error exception="<%= UserReminderQueryException.class %>" message="your-answer-does-not-match-what-is-in-our-database" />
										
												<aui:fieldset>
													<c:choose>
														<c:when test="<%= user2 == null %>">
										
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
										
															<aui:input name="step" type="hidden" value="1" />
										
															<c:if test="<%= !PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_ENABLED, PropsValues.USERS_REMINDER_QUERIES_ENABLED) %>">
																<portlet:renderURL var="redirectURL">
																	<portlet:param name="mvcRenderCommandName" value="/login/login" />
																</portlet:renderURL>
										
																<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
															</c:if>
										
															<aui:input label="<%= loginLabel %>" name="login" required="<%= true %>" showRequiredLabel="<%= true %>" size="30" type="text" value="<%= login %>" />
										
															<%-- <c:if test="<%= captchaConfiguration.sendPasswordCaptchaEnabled() %>"> --%>
																				<div class="form-group">
																					<label><liferay-ui:message key="text-verification-code"/><sup class="text-danger">*</sup></label>
																						<div class="d-flex align-items-center">
																				                <canvas id="captchaCanvas" class="captcha-image"></canvas>
																				                <button type="button" id="refreshCaptcha" class="btn btn-refresh btn-sm" aria-label="Refresh Captcha"><i class="bi bi-repeat"></i></button>
																				            <input type="text" class="form-control" id="captchaInput" name="captchaInput" placeholder="<%= LanguageUtil.get(request, "enter-captcha") %>">
																				        </div>
																				     <div id="captchaError" class="error"></div>
																				</div>
																				
															<%-- </c:if> --%>
																			<p class="text-2 text-right pointer">
														<a href="/login" class="text-decoration-none pointer">
															<liferay-ui:message key="login" />
														</a>
													</p>
															<aui:button-row>
																<aui:button type="submit" value='<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_ENABLED, PropsValues.USERS_REMINDER_QUERIES_ENABLED) ? "next" : "send-new-password" %>'  onclick="validateForm()"/>
															</aui:button-row>
														</c:when>
														<c:when test="<%= user2 != null %>">
															<aui:input name="step" type="hidden" value="2" />
										
															<portlet:renderURL var="redirectURL">
																<portlet:param name="mvcRenderCommandName" value="/login/login" />
															</portlet:renderURL>
										
															<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />
										
															<c:if test="<%= Validator.isNotNull(user2.getReminderQueryQuestion()) && Validator.isNotNull(user2.getReminderQueryAnswer()) %>">
																<div class="alert alert-info">
																	<liferay-ui:message arguments="<%= HtmlUtil.escape(login) %>" key="an-email-will-be-sent-to-x-if-you-can-correctly-answer-the-following-question" translateArguments="<%= false %>" />
																</div>
										
																<aui:input label="<%= HtmlUtil.escape(LanguageUtil.get(request, user2.getReminderQueryQuestion())) %>" name="answer" type='<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_DISPLAY_IN_PLAIN_TEXT, PropsValues.USERS_REMINDER_QUERIES_DISPLAY_IN_PLAIN_TEXT) ? "text" : "password" %>' />
															</c:if>
										
															<c:choose>
																<c:when test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.USERS_REMINDER_QUERIES_REQUIRED, PropsValues.USERS_REMINDER_QUERIES_REQUIRED) && !user2.hasReminderQuery() %>">
																	<div class="alert alert-info">
																		<liferay-ui:message key="the-password-cannot-be-reset-because-you-have-not-configured-a-reminder-query" />
																	</div>
																</c:when>
																<c:otherwise>
																	<c:if test="<%= reminderAttempts >= 3 %>">
																		<div class="form-group">
																			<label><liferay-ui:message key="text-verification-code"/><sup class="text-danger">*</sup></label>
																				<div class="d-flex align-items-center">
																		                <canvas id="captchaCanvas" class="captcha-image"></canvas>
																		                <button type="button" id="refreshCaptcha" class="btn btn-refresh btn-sm" aria-label="Refresh Captcha"><i class="bi bi-repeat"></i></button>
																		            <input type="text" class="form-control" id="captchaInput" name="captchaInput" placeholder="<%= LanguageUtil.get(request, "enter-captcha") %>">
																		        </div>
																		     <div id="captchaError" class="error"></div>
																		</div>
																		
																	</c:if>
																			<p class="text-2 text-right pointer">
														<a href="/login" class="pointer text-decoration-none">
															<liferay-ui:message key="login" />
														</a>
													</p>
																	<aui:button-row>
																		<aui:button type="submit" value='<%= company.isSendPasswordResetLink() ? "send-password-reset-link" : "send-new-password" %>' />
																	</aui:button-row>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:otherwise>
															<div class="alert alert-warning">
																<liferay-ui:message key="the-system-cannot-send-you-a-new-password-because-you-have-not-provided-an-email-address" />
															</div>
														</c:otherwise>
													</c:choose>
												</aui:fieldset>
											</aui:form>
										
											<%-- <%@ include file="/navigation.jspf" %> --%>
										</div>
										
										</div> 
								</div> 
							</div> 
						</div>
					</div>
				</div>
			</div>
		</div> 
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
	
	var formId = document.getElementById('<portlet:namespace />');
	$('#<portlet:namespace />fm').validate({
	rules:{
		captchaInput: {
			required:true,
			validateCaptcha:true
		},
		'<portlet:namespace />login':{
			required:true,
		}
	},messages:{
		captchaInput: {
			required:"<liferay-ui:message key="please-enter-verification-code"/>"
		},
		'<portlet:namespace />login':{
			required:"<liferay-ui:message key="enter-login-name"/>"
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


function validateForm(){debugger
	if($('#<portlet:namespace />fm').valid()){
		$('#<portlet:namespace />fm').submit();
	}
}


</script>
<style>
.c-inner .lexicon-icon-angle-left {
	display:none
}
.form-group .text-warning {
 color: #ff0000 !important;
 font-size: 6px;
}
.pointer {
	color: #26268E !important;
}
#_com_liferay_login_web_portlet_LoginPortlet_loginHelper {
		display: none !important;
} 
</style>		