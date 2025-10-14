<%@ include file="/html/portal/init.jsp" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<%
String currentURL = PortalUtil.getCurrentURL(request);

String referer = ParamUtil.getString(request, WebKeys.REFERER, currentURL);

Ticket ticket = (Ticket)request.getAttribute(WebKeys.TICKET);

String ticketId = ParamUtil.getString(request, "ticketId");

String ticketKey = ParamUtil.getString(request, "ticketKey");

if (referer.startsWith(themeDisplay.getPathMain() + "/portal/update_password") && Validator.isNotNull(ticketKey)) {
	referer = themeDisplay.getPathMain();
}

String titlePage = (String)request.getAttribute(WebKeys.TITLE_SET_PASSWORD);
boolean showCancelButton = false;

if (Validator.isNull(titlePage)) {
	titlePage = "change-password";
	showCancelButton = true;
}
%>
<style>
    .toggle-password {
        position: absolute;
        right: 13px;
        top: 60%;
        transform: translateY(-20%);
        cursor: pointer;
        font-size: 27px;
    }
    .button-holder .btn-cancel{
    margin-right:30px;
    margin-left:20px;
    } 
    
    .position-relative .form-feedback-item + .toggle-password {
    top: 45%;
}

.dropdown.open > .dropdown-menu, .overlay-content .open > .dropdown-menu {
    display: block;
    font-size: 15px;
}
</style>
<div class="mt-4 sheet sheet-lg">
	<div class="sheet-header">
		<div class="autofit-padded-no-gutters-x autofit-row">
			<div class="autofit-col autofit-col-expand">
				<h2 class="sheet-title">
					<liferay-ui:message key="<%= titlePage %>" />
				</h2>
			</div>

			<div class="autofit-col">
				<%@ include file="/html/portal/select_language.jspf" %>
			</div>
		</div>
	</div>

	<div class="sheet-text">
		<c:choose>
			<c:when test="<%= !themeDisplay.isSignedIn() && (ticket == null) %>">
				<div class="alert alert-warning">
					<c:choose>
						<c:when test="<%= (ticket == null) && (ticketKey != null) && Validator.isNull(ticketId) %>">
							<liferay-ui:message key="this-link-format-is-no-longer-recognized-please-request-a-new-link" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="your-password-reset-link-is-no-longer-valid" />
						</c:otherwise>
					</c:choose>

					<%
					PortletURL portletURL = PortletURLFactoryUtil.create(request, PortletKeys.LOGIN, PortletRequest.RENDER_PHASE);

					portletURL.setParameter("mvcRenderCommandName", "/login/forgot_password");
					portletURL.setWindowState(WindowState.MAXIMIZED);
					%>

					<div class="reset-link-contaner">
						<aui:a href="<%= portletURL.toString() %>" label="request-a-new-password-reset-link"></aui:a>
					</div>
				</div>
			</c:when>
			<c:when test="<%= SessionErrors.contains(request, UserLockoutException.LDAPLockout.class.getName()) %>">
				<div class="alert alert-danger">
					<liferay-ui:message key="this-account-is-locked" />
				</div>
			</c:when>
			<c:when test="<%= SessionErrors.contains(request, UserLockoutException.PasswordPolicyLockout.class.getName()) %>">
				<div class="alert alert-danger">

					<%
					UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout)SessionErrors.get(request, UserLockoutException.PasswordPolicyLockout.class.getName());

					Format dateFormat = FastDateFormatFactoryUtil.getDateTime(FastDateFormatConstants.SHORT, FastDateFormatConstants.LONG, locale, TimeZone.getTimeZone(ule.user.getTimeZoneId()));
					%>

					<liferay-ui:message arguments="<%= dateFormat.format(ule.user.getUnlockDate()) %>" key="this-account-is-locked-until-x" translateArguments="<%= false %>" />
				</div>
			</c:when>
			<c:otherwise>
				<aui:form action='<%= themeDisplay.getPathMain() + "/portal/update_password" %>' method="post" name="fm">
					<aui:input name="p_l_id" type="hidden" value="<%= layout.getPlid() %>" />
					<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
					<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
					<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
					<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= referer %>" />
					<aui:input name="ticketId" type="hidden" value="<%= ticketId %>" />
					<aui:input name="ticketKey" type="hidden" value="<%= ticketKey %>" />

					<c:if test="<%= !SessionErrors.isEmpty(request) %>">
							<c:choose>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustBeLonger.class.getName()) %>">
								  <div class="alert alert-danger">
									<%
									UserPasswordException.MustBeLonger upe = (UserPasswordException.MustBeLonger)SessionErrors.get(request, UserPasswordException.MustBeLonger.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minLength) %>" key="that-password-is-too-short" translateArguments="<%= false %>" />
								  </div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustComplyWithModelListeners.class.getName()) %>">
									<div class="alert alert-danger">
									<liferay-ui:message key="that-password-is-invalid-please-enter-a-different-password" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustComplyWithRegex.class.getName()) %>">
									<div class="alert alert-danger">
									<%
									UserPasswordException.MustComplyWithRegex upe = (UserPasswordException.MustComplyWithRegex)SessionErrors.get(request, UserPasswordException.MustComplyWithRegex.class.getName());
									%>

									<liferay-ui:message arguments="<%= upe.regex %>" key="that-password-does-not-comply-with-the-regular-expression" translateArguments="<%= false %>" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreAlphanumeric.class.getName()) %>">
									<div class="alert alert-danger">
									<%
									UserPasswordException.MustHaveMoreAlphanumeric upe = (UserPasswordException.MustHaveMoreAlphanumeric)SessionErrors.get(request, UserPasswordException.MustHaveMoreAlphanumeric.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minAlphanumeric) %>" key="that-password-must-contain-at-least-x-alphanumeric-characters" translateArguments="<%= false %>" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreLowercase.class.getName()) %>">
									<div class="alert alert-danger">
									<%
									UserPasswordException.MustHaveMoreLowercase upe = (UserPasswordException.MustHaveMoreLowercase)SessionErrors.get(request, UserPasswordException.MustHaveMoreLowercase.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minLowercase) %>" key="that-password-must-contain-at-least-x-lowercase-characters" translateArguments="<%= false %>" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreNumbers.class.getName()) %>">
									<div class="alert alert-danger">
									<%
									UserPasswordException.MustHaveMoreNumbers upe = (UserPasswordException.MustHaveMoreNumbers)SessionErrors.get(request, UserPasswordException.MustHaveMoreNumbers.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minNumbers) %>" key="that-password-must-contain-at-least-x-numbers" translateArguments="<%= false %>" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreSymbols.class.getName()) %>">
									<div class="alert alert-danger">
									<%
									UserPasswordException.MustHaveMoreSymbols upe = (UserPasswordException.MustHaveMoreSymbols)SessionErrors.get(request, UserPasswordException.MustHaveMoreSymbols.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minSymbols) %>" key="that-password-must-contain-at-least-x-symbols" translateArguments="<%= false %>" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustHaveMoreUppercase.class.getName()) %>">
									<div class="alert alert-danger">
									<%
									UserPasswordException.MustHaveMoreUppercase upe = (UserPasswordException.MustHaveMoreUppercase)SessionErrors.get(request, UserPasswordException.MustHaveMoreUppercase.class.getName());
									%>

									<liferay-ui:message arguments="<%= String.valueOf(upe.minUppercase) %>" key="that-password-must-contain-at-least-x-uppercase-characters" translateArguments="<%= false %>" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustMatch.class.getName()) %>">
									<div class="alert alert-danger">
										<liferay-ui:message key="the-passwords-you-entered-do-not-match" />
									</div>	
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeEqualToCurrent.class.getName()) %>">
									<div class="alert alert-danger">
										<liferay-ui:message key="your-new-password-cannot-be-the-same-as-your-old-password-please-enter-a-different-password" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeNull.class.getName()) %>">
									<div class="alert alert-danger">
										<liferay-ui:message key="the-password-cannot-be-blank" />
									</div>
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeRecentlyUsed.class.getName()) %>">
									<div class="alert alert-danger">
										<liferay-ui:message key="that-password-has-already-been-used-please-enter-a-different-password" />
									</div>	
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotBeTrivial.class.getName()) %>">
									<div class="alert alert-danger">
										<liferay-ui:message key="that-password-uses-common-words-please-enter-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
									</div>	
								</c:when>
								<c:when test="<%= SessionErrors.contains(request, UserPasswordException.MustNotContainDictionaryWords.class.getName()) %>">
									<div class="alert alert-danger">
										<liferay-ui:message key="that-password-uses-common-dictionary-words" />
									</div>	
								</c:when>
								<c:otherwise>
									<!-- Otherwise -->
								</c:otherwise>
							</c:choose>
						
					</c:if>

<aui:fieldset>
    <!-- Password Field -->
    <div class="position-relative">
        <aui:input name="password1" required="<%= true %>" label='<%= LanguageUtil.get(request, "password-required") %>' showRequiredLabel="<%= false %>" type="password" id="password1" cssClass="form-control pe-5" />
        <span class="position-absolute end-0 top-50 translate-middle-y me-3 cursor-pointer toggle-password" data-target="password1">
            <i class="bi bi-eye fs-5"></i>
        </span>
    </div>

    <!-- Re-enter Password Field -->
    <div class="position-relative mt-3">
        <aui:input name="password2" required="<%= true %>" label='<%= LanguageUtil.get(request, "re-renter-password-required") %>' showRequiredLabel="<%= false %>" type="password" id="password2" cssClass="form-control pe-5">
            <aui:validator name="equalTo">
                '#<portlet:namespace />password1'
            </aui:validator>
        </aui:input>
        <span class="position-absolute end-0 top-50 translate-middle-y me-3 cursor-pointer toggle-password" data-target="password2">
            <i class="bi bi-eye fs-5"></i>
        </span>
    </div>
</aui:fieldset>

					<aui:button-row>
					
					<c:if test="<%= showCancelButton %>">
							<aui:button href='<%= themeDisplay.getPathMain() + "/portal/logout" %>' type="cancel" />
						</c:if>
						<aui:button type="submit" />
					</aui:button-row>
				</aui:form>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script>
    $(document).ready(function() {
        $(".toggle-password").click(function() {
            var inputField = $("#" + $(this).attr("data-target"));
            var icon = $(this).find("i");

            if (inputField.attr("type") === "password") {
                inputField.attr("type", "text");
                icon.removeClass("bi-eye").addClass("bi-eye-slash");
            } else {
                inputField.attr("type", "password");
                icon.removeClass("bi-eye-slash").addClass("bi-eye");
            }
        });
    });
    
    $(window).on('load', function() {
        $('label[for="password1"]').after('<sup class="text-danger">*</sup>');
         $('label[for="password2"]').after('<sup class="text-danger">*</sup>');
    });

    $(document).ready(function () {
        // Function to update the position of the eye icon
        function updateEyeIconPosition() {
            $(".position-relative").each(function () {
                var parentDiv = $(this);
                var eyeIcon = parentDiv.find(".toggle-password");

                if (parentDiv.find(".form-feedback-item").length > 0) {
                    eyeIcon.css("top", "45%");
                } else {
                    eyeIcon.css("top", "56%");
                }
            });
        }

        // Run on input or blur
        $("input").on("input blur", function () {
            updateEyeIconPosition();
        });

        // Use MutationObserver to detect when validation messages appear
        var observer = new MutationObserver(function (mutations) {
            mutations.forEach(function (mutation) {
                if (mutation.addedNodes.length || mutation.removedNodes.length) {
                    updateEyeIconPosition();
                }
            });
        });

        // Observe the body or specific form for changes
        observer.observe(document.body, { childList: true, subtree: true });
    });

</script>