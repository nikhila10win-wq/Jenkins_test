<%@page import="com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.captcha.CaptchaException"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/captcha" prefix="liferay-captcha" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<input type="hidden" id="regEmailExist" value="<liferay-ui:message key="email-id-already-exist"/>">
<input type="hidden" id="validAadhaarMsg" value="<liferay-ui:message key="valid-aadhaar-msg"/>">
<input type="hidden" id="validMobileMsg" value="<liferay-ui:message key="valid-mobile-msg"/>">
<input type="hidden" id="validLandlineMsg" value="<liferay-ui:message key="valid-landline-msg"/>">
<input type="hidden" id="validEmailMsg" value="<liferay-ui:message key="valid-email-msg"/>">
<input type="hidden" id="emptyCaptcha" value="<liferay-ui:message key="please-enter-captcha"/>">
<input type="hidden" id="incorrectCaptcha" value="<liferay-ui:message key="incorrect-captcha"/>">

<portlet:resourceURL id="<%=RegistrationWebPortletKeys.GET_DISTRICTS%>"
var="getDistricts" />

<portlet:resourceURL id="<%=RegistrationWebPortletKeys.GET_TALUKAS%>"
var="getTalukas" />

<portlet:resourceURL id="<%=RegistrationWebPortletKeys.REGISTER_SCHOOL_COLLEGE%>"
var="saveSchoolCollegeRegistrationURL" />

<portlet:resourceURL id="<%=RegistrationWebPortletKeys.EMAIL_EXIST_URL%>"
var="emailExist" />


<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<%-- <div class="card-header d-flex align-item-center justify-content-between">
						<h5><liferay-ui:message key="sports-complex-government-department-registration"/></h5>						
					</div> --%>
					
				  <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="sports-complex-government-department-registration" />
						</h5>
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn" href="/registration"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
						</div>
				  </div>
					
				<form id="register_school_college"> 
					<div class="card-body">
					<div class="card card-background p-0">
					 <div class="card-header header-card"><liferay-ui:message key="sports-complex-government-department-registration"/></div>
					<input type="hidden" class="form-control" id="regType" name="regType" value="SportsComplex" />
					<div class="card-body">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label><liferay-ui:message key="state"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="state">
										<option value="1">Maharashtra</option>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><liferay-ui:message key="division"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name="division" id="division">
										<option value="">Select</option>
										<c:forEach var="division" items="${divisions}">
											<option value="${division.divisionId}">${division.divisionName_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><liferay-ui:message key="district"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="district" id="district">
										<option value="">Select</option>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><liferay-ui:message key="taluka"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="taluka" id="taluka">
										<option value="">Select</option>
									</select>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-6">
							    <div class="form-group">
							        <label for="ageGroupUnder14"><liferay-ui:message key="name-of-department" /><sup class="text-danger">*</sup></label>
							        <input type="text" class="form-control" id="departmentName" name="departmentName" value="" />
							    </div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="sub-department"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="subDepartment" name="subDepartment" value="" />
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="name-of-official"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="officialName" name="officialName" value=""/>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="designation"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="designation" id="designation">
										<option value=""><liferay-ui:message key="select"/></option>
										<option value="designation1"><liferay-ui:message key="Designation1"/></option>
										<option value="designation2"><liferay-ui:message key="Designation2"/></option>
									</select>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="name-of-secretary-commissioner"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="secretaryName" id="secretaryName" value=""/>
								</div>
							</div>
							<%-- <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="designation"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="designation" id="designation">
										<option value=""><liferay-ui:message key="select"/></option>
										<option value="designation1"><liferay-ui:message key="Designation1"/></option>
										<option value="designation2"><liferay-ui:message key="Designation2"/></option>
									</select>
								</div>
							</div> --%>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="aadhar-no-of-employee"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="aadharNo" id="aadharNo" value=""/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<%-- <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="aadhar-no-of-employee"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="aadharNo" id="aadharNo" value=""/>
								</div>
							</div> --%>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="email"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="email" id="email" value=""/>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="website"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="website" id="website" value=""/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<%-- <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="website"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="website" id="website" value=""/>
								</div>
							</div> --%>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="mobile-no"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="mobileNo" id="mobileNo" value=""/>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="landline-no"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="landlineNo" id="landlineNo" value=""/>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<%-- <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="landline-no"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="landlineNo" id="landlineNo" value=""/>
								</div>
							</div> --%>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="name-of-secretary"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="secretaryOrgName" id="secretaryOrgName" value=""/>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="text-verification-code"/><sup class="text-danger">*</sup></label>
									  <div class="d-flex align-items-center mb-2">
									    <canvas id="captchaCanvas" class="captcha-image me-2"></canvas>
									    <button type="button" id="refreshCaptcha" class="btn btn-refresh btn-sm" aria-label="Refresh Captcha">
									      <i class="bi bi-repeat"></i>
									    </button>
									    	  <input type="text" class="form-control mb-1" id="captchaInput" name="captchaInput" placeholder="Enter Captcha">
								  <div id="captchaError" class="error text-danger small"></div>
								  </div>
						
								</div>
							</div>
						</div>
						
						
						<%-- <div class="row">
						   <div class="col-md-6">
								<div class="form-group">
													<label><liferay-ui:message key="text-verification-code"/><sup class="text-danger">*</sup></label>
													  <div class="d-flex align-items-center mb-2">
													    <canvas id="captchaCanvas" class="captcha-image me-2"></canvas>
													    <button type="button" id="refreshCaptcha" class="btn btn-refresh btn-sm" aria-label="Refresh Captcha">
													      <i class="bi bi-repeat"></i>
													    </button>
													    	  <input type="text" class="form-control mb-1" id="captchaInput" name="captchaInput" placeholder="Enter Captcha">
													  <div id="captchaError" class="error text-danger small"></div>
													  </div>
												
													</div>
													</div>
							</div> --%>
						</div>
						</div>
						</div>
						<div class="card-footer bg-transparent text-right p-4">
							<button type="button" class="btn btn-primary" onclick="location.href='/home';"><liferay-ui:message key="cancel" /></button>
							<button type="button" class="btn btn-primary" onclick="window.location.reload();"><liferay-ui:message key="reset" /></button>
							<button class="btn btn-primary" onclick="saveSchoolCollegeRegistration(event)"><liferay-ui:message key="register" /></button>
						</div>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</div>



<!-- modal popup for registration -->
<div class="modal fade" id="registrationForSports" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="sports-registration"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <%-- <p><liferay-ui:message key="registered-successfully"/></p> --%>
                                    <p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for registration -->

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
	
	$('#register_school_college').validate({
	onkeyup: function (element) {
		$(element).valid();
	  },
	onchange: function (element) {
		    $(element).valid();
	  },
	rules:{
		division: {
			required:true,
		},
		district: {
			required:true,
		},
		taluka: {
			required:true,
		},
		departmentName: {
			required:true,
			noEdgeSpaces:true,
			singleSpaceBetweenWords:true,
			noConsecutiveSpecialChars:true,
			customNamePattern: true,
			nameContainsAlphabet: true,
			validName: true,
			minlength: 3,
		    maxlength: 75,
		},
		subDepartment: {
			required:true,
			noEdgeSpaces:true,
			singleSpaceBetweenWords:true,
			noConsecutiveSpecialChars:true,
			customNamePattern:true,
			nameContainsAlphabet: true,
			validName: true,
			minlength: 3,
		    maxlength: 75,
		},
		officialName: {
			required:true,
			noEdgeSpaces:true,
			singleSpaceBetweenWords:true,
			noConsecutiveSpecialCharsForName:true,
			customNamingPattern:true,
			nameContainsAlphabet: true,
			validName: true,
			minlength: 3,
		    maxlength: 75,
		},
		secretaryName: {
			required:true,
			noEdgeSpaces:true,
			singleSpaceBetweenWords:true,
			noConsecutiveSpecialCharsForName:true,
			customNamingPattern:true,
			nameContainsAlphabet: true,
			validName: true,
			minlength: 3,
		    maxlength: 75,
		},
		designation: {
			required:true,
			/* noEdgeSpaces:true,
			singleSpaceBetweenWords:true,
			noConsecutiveSpecialChars:true,
			customNamePattern: true,
			nameContainsAlphabet: true, 
			validName: true,
			minlength: 3,
		    maxlength: 75,*/
		},
		aadharNo: {
			required:true,
			notAllZeros:true,
			noEdgeSpaces:true,
			aadhaarValidation:true
		},
		email: {
			required:true,
			noEdgeSpaces:true,
			validateEmail:true,
			existingEmail:true,
		},
		website: {
			required:true,
			noEdgeSpaces:true,
			customWebsitePattern:true,
			minlength: 3,
		    maxlength: 75,
		},
		mobileNo: {
			required:true,
			mobileNumberValidation:true
		},
		landlineNo: {
			required:true,
			minlength: 6,
		    maxlength: 20,
		    landlineValidation:true
		},
		secretaryOrgName: {
			required:true,
			required:true,
			noEdgeSpaces:true,
			singleSpaceBetweenWords:true,
			noConsecutiveSpecialCharsForName:true,
			customNamingPattern:true,
			nameContainsAlphabet: true,
			validName: true,
			minlength: 3,
		    maxlength: 75,
		},
		captchaInput: {
		    required: true,
		    captchaMatch: true
		}
		
	},messages:{
		division: {
			required:"<liferay-ui:message key="please-select-division"/>"
		},
		district: {
			required:"<liferay-ui:message key="please-select-district"/>"
		},
		taluka: {
			required:"<liferay-ui:message key="please-select-taluka"/>"
		},
		departmentName: {
			required:"<liferay-ui:message key="please-enter-department-name"/>",
			minlength:"<liferay-ui:message key="please-enter-at-least-3-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-75-characters"/>",
		},
		subDepartment: {
			required:"<liferay-ui:message key="please-enter-subdepartment-name"/>",
			minlength:"<liferay-ui:message key="please-enter-at-least-3-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-75-characters"/>",
		},
		officialName: {
			required:"<liferay-ui:message key="please-enter-official-name"/>",
			minlength:"<liferay-ui:message key="please-enter-at-least-3-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-75-characters"/>",
		},
		secretaryName: {
			required:"<liferay-ui:message key="please-enter-secretary-name"/>",
			minlength:"<liferay-ui:message key="please-enter-at-least-3-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-75-characters"/>",
		},
		designation: {
			required:"<liferay-ui:message key="please-select-designation"/>",
			/* minlength:"<liferay-ui:message key="please-enter-at-least-3-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-75-characters"/>", */
		},
		aadharNo: {
			required:"<liferay-ui:message key="please-enter-aadhar-no"/>",
		},
		email: {
			required:"<liferay-ui:message key="please-enter-email-id"/>"
		},
		website: {
			required:"<liferay-ui:message key="please-enter-website"/>",
			minlength:"<liferay-ui:message key="please-enter-at-least-3-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-75-characters"/>",
		},
		mobileNo: {
			required:"<liferay-ui:message key="please-enter-mobile-no"/>"
		},
		landlineNo: {
			required:"<liferay-ui:message key="please-enter-landline-no"/>",
			minlength:"<liferay-ui:message key="please-enter-at-least-6-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-20-characters"/>",
		},
		secretaryOrgName: {
			required:"<liferay-ui:message key="please-enter-secreatary-chairman"/>",
			minlength:"<liferay-ui:message key="please-enter-at-least-3-characters"/>",
			maxlength:"<liferay-ui:message key="please-enter-no-more-than-75-characters"/>",
		},
	    captchaInput: {
	        required: "<liferay-ui:message key="please-enter-captcha"/>",
	    }
		
	},
});
	
	if (!findProperty($.validator.methods, 'mobileNumberValidation')) {
	    $.validator.addMethod("mobileNumberValidation", function(value, element) {
	        var basicRegex = /^[6-9]\d{9}$/; // 10 or 13 digits, starting with 6-9
	        var sameDigitsRegex = /^(\d)\1{9}$/; // All digits are the same for 10 or 13 digits
	        if (value === "") {
	            return true; // Skip validation if the field is empty
	        }
	        if (!basicRegex.test(value)) {
	            $.validator.messages.mobileNumberValidation = '<liferay-ui:message key="valid-mobile-msg"/>';
	            return false;
	        } else if (sameDigitsRegex.test(value)) {
	            $.validator.messages.mobileNumberValidation = '<liferay-ui:message key="all-same-digits-msg"/>';
	            return false;
	        }
	        return true;
	    }, $('#validMobileMsg').val());
	}
	
	if (!findProperty($.validator.methods, 'landlineValidation')) {
	    $.validator.addMethod("landlineValidation", function(value) {
	        value = value.trim();

	        // Check for allowed characters
	        if (!/^[0-9()\-\s]+$/.test(value)) {
	            $.validator.messages.landlineValidation = '<liferay-ui:message key="valid-landline-msg"/>';
	            return false;
	        }

	        // Disallow consecutive hyphens
	        if (/--/.test(value)) {
	            $.validator.messages.landlineValidation = '<liferay-ui:message key="valid-landline-msg"/>';
	            return false;
	        }

	        // Check for valid use of parentheses: only once and at the beginning
	        if (value.includes('(') || value.includes(')')) {
	            if (!/^\(\d{2,5}\)[\s\-]?\d{3,}$/.test(value)) {
	                $.validator.messages.landlineValidation = '<liferay-ui:message key="valid-landline-msg"/>';
	                return false;
	            }
	        }

	        // Should not start or end with hyphen or parenthesis
	        if (/^[-()]/.test(value) || /[-()]$/.test(value)) {
	            $.validator.messages.landlineValidation = '<liferay-ui:message key="valid-landline-msg"/>';
	            return false;
	        }

	        // Check if all digits are the same
	        const digits = value.replace(/\D/g, '');
	        if (/^(\d)\1{5,}$/.test(digits)) { // e.g., 0000000000
	            $.validator.messages.landlineValidation = '<liferay-ui:message key="valid-landline-msg"/>';
	            return false;
	        }

	        return true;
	    }, '');
	}
	
	if (!findProperty($.validator.methods, 'captchaMatch')) {
	    $.validator.addMethod("captchaMatch", function(value, element) {
	    	return this.optional(element) || value.trim().toUpperCase() === currentCaptcha.toUpperCase();
		  }, $('#incorrectCaptcha').val());
	}
	
	// No leading or trailing spaces
	if (!findProperty($.validator.methods, 'noEdgeSpaces')) {
	    $.validator.addMethod("noEdgeSpaces", function(value) {
	        return value === value.trim();
	    }, '<liferay-ui:message key="no-leading-and-trailing-space-allowed"/>');
	}

	// Only one space between words
	if (!findProperty($.validator.methods, 'singleSpaceBetweenWords')) {
	    $.validator.addMethod("singleSpaceBetweenWords", function(value) {
	        return !/ {2,}/.test(value);
	    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');
	}

	// No consecutive special characters
	if (!findProperty($.validator.methods, 'noConsecutiveSpecialChars')) {
    $.validator.addMethod("noConsecutiveSpecialChars", function(value) {
        value = value.trim();

        var allowedRegex = /^[A-Za-z0-9\/.\- ]+$/;
        var hasOnlyAllowedChars = allowedRegex.test(value);

        if (!hasOnlyAllowedChars) {
            $.validator.messages.noConsecutiveSpecialChars = '<liferay-ui:message key="only-alphabets-numbers-single-spaces-hyphen-slash-dot-allowed"/>';
            return false;
        }

        var hasLetter = /[A-Za-z]/.test(value);
        var hasConsecutiveSpecials = /([./\- ])\1+/.test(value);  // catches "..", "--", "//", "  "

        if (hasLetter && hasConsecutiveSpecials) {
            $.validator.messages.noConsecutiveSpecialChars = '<liferay-ui:message key="no-consecutive-special-characters-allowed"/>';
            return false;
        }

        return true;
    }, '');
	}
	
	
	// No consecutive special characters for name
	if (!findProperty($.validator.methods, 'noConsecutiveSpecialCharsForName')) {
	    $.validator.addMethod("noConsecutiveSpecialCharsForName", function(value) {
	        value = value.trim();
	
	        var allowedRegex = /^[A-Za-z0-9\/.\- '\u2019]+$/;
	        var hasOnlyAllowedChars = allowedRegex.test(value);
	
	        if (!hasOnlyAllowedChars) {
	            $.validator.messages.noConsecutiveSpecialCharsForName = '<liferay-ui:message key="only-alphabets-hyphen-dot-apostrophe-allowed"/>';
	            return false;
	        }
	
	        var hasLetter = /[A-Za-z]/.test(value);
	        var hasConsecutiveSpecials = /([./\-' \u2019])\1+/.test(value);  
	
	        if (hasLetter && hasConsecutiveSpecials) {
	            $.validator.messages.noConsecutiveSpecialCharsForName = '<liferay-ui:message key="no-consecutive-special-characters-allowed"/>';
	            return false;
	        }
	
	        return true;
	    }, '');
	}


	// Custom name validation: alphabets, numbers, space, hyphen, slash, dot
	if (!findProperty($.validator.methods, 'customNamePattern')) {
	    $.validator.addMethod("customNamePattern", function(value) {
	        return /^[A-Za-z0-9\/.\- ]+$/.test(value);
	    }, '<liferay-ui:message key="only-alphabets-numbers-single-spaces-hyphen-slash-dot-allowed"/>');
	}

	// Custom naming convention: alphabets, hyphen, dot, apostrophe, space
	if (!findProperty($.validator.methods, 'customNamingPattern')) {
	    $.validator.addMethod("customNamingPattern", function(value) {
	        return /^[A-Za-z.\-'\s]+$/.test(value);
	    }, "<liferay-ui:message key='only-alphabets-hyphen-dot-apostrophe-allowed'/>");
	}

	// Website validation
	if (!findProperty($.validator.methods, 'customWebsitePattern')) {
	    $.validator.addMethod("customWebsitePattern", function(value, element) {
	    	const regex = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/;
	        return this.optional(element) || regex.test(value.trim());
	    }, '<liferay-ui:message key="please-enter-valid-website-url"/>');
	}
	
	// Alphanumeric only
	if (!findProperty($.validator.methods, 'alphanumericOnly')) {
	    $.validator.addMethod("alphanumericOnly", function(value) {
	        return /^[A-Za-z0-9]+$/.test(value.trim());
	    }, '<liferay-ui:message key="only-alphanumeric-characters-allowed"/>');
	}
	
	// Not all zeros for ID
	if (!findProperty($.validator.methods, 'notAllZeros')) {
	    $.validator.addMethod("notAllZeros", function(value) {
	        // Remove all whitespace from the value before testing
	        var cleanedValue = value.replace(/\s+/g, '');
	        return !/^0+$/.test(cleanedValue);
	    }, '<liferay-ui:message key="employee-id-cannot-be-all-zeros"/>');
	}
	
	//Allow only names
	if (!findProperty($.validator.methods, 'nameContainsAlphabet')) {
	    $.validator.addMethod("nameContainsAlphabet", function(value) {
	        return /[A-Za-z]/.test(value);
	    }, '<liferay-ui:message key="name-cannot-be-numeric-only"/>' );
	}
	
	//Valid names check
	if (!findProperty($.validator.methods, 'validName')) {
	    $.validator.addMethod("validName", function(value) {
	        return /^(?:[A-Za-z]\.|[A-Za-z][A-Za-z0-9]*(?:['/-][A-Za-z0-9]+)?)(?:\.|\s[A-Za-z](?:\.[A-Za-z])?|\s[A-Za-z0-9][A-Za-z0-9]*(?:['/-][A-Za-z0-9]+)?|\s[0-9]+)*$/.test(value.trim());
	    }, '<liferay-ui:message key="please-enter-a-valid-name"/>');
	}
	
	//Email Validation
	if (!findProperty($.validator.methods, 'validateEmail')) {
	    $.validator.addMethod("validateEmail", function(value, element) {
	        const regex = /^[a-zA-Z0-9]+(?:[._][a-zA-Z0-9]+)*@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z]{2,})(?:\.[a-zA-Z]{2})?$/;
	        const trimmedValue = value.trim();
	        if (!regex.test(trimmedValue)) {
	            $.validator.messages.validateEmail = '<liferay-ui:message key="valid-email-msg"/>';
	            return false;
	        }
	        // Extract local part (before @)
	        const localPart = trimmedValue.split('@')[0];
	        // Check for at least one letter in the local part
	        if (!/[a-zA-Z]/.test(localPart)) {
	            $.validator.messages.validateEmail = '<liferay-ui:message key="valid-email-msg"/>';
	            return false;
	        }
	        return this.optional(element) || regex.test(trimmedValue);
	    }, $('#validEmailMsg').val());
	}
	
	if (!findProperty($.validator.methods, 'aadhaarValidation')) {
	    $.validator.addMethod("aadhaarValidation", function(value, element) {
	        const regex = /^(?:\d{12}|\d{4} \d{4} \d{4})$/;
	        return this.optional(element) || regex.test(value.trim());
	    }, $('#validAadhaarMsg').val());
	}
	
	if(!findProperty($.validator.methods, 'existingEmail')){
		$.validator.addMethod("existingEmail", function(value, element) {
			var email = $("#email").val();
			return this.optional(element) || checkEmailExist(email);
		}, $('#regEmailExist').val());
	}
	
	function checkEmailExist(email){
		var flag = false;
		$.ajax({
            url: '${emailExist}',
            type: 'GET',
            data: {
            	email: email
            },
            async: false, 
            success: function (response) {
            	var data = JSON.parse(response);
            	console.log('emailExist:',data.emailExist);
            	if(data.emailExist == true){
            		flag = false;
            	}
            	if(data.emailExist == false){
            		flag = true;
            	}
            }
        });
		return flag;
	}
	
	
	console.log("Division Changed ::: ");
	
	$('select[name="division"]').on('change', function () {
        const divisionId = $(this).val();
        if (divisionId) {
            $.ajax({
                url: '${getDistricts}',
                type: 'GET',
                data: {
                    divisionId: divisionId
                },
                success: function (data) {
                    const $districtSelect = $("#district");
                    $districtSelect.empty();

                    let districts = typeof data === "string" ? JSON.parse(data) : data;

                    if (districts && districts.length > 0) {
                        $districtSelect.append('<option value="">Select</option>');

                        $.each(districts, function (index, value) {
                        	$districtSelect.append('<option value="' + value.districtId + '">' + value.districtName + '</option>');
                        });
                    } else {
                        $districtSelect.append('<option value="">No districts available</option>');
                    }
                }
                
            });
        }
    }); 
     

    $('select[name="district"]').on('change', function () {
        const districtId = $(this).val();
        if (districtId) {
            $.ajax({
                url: '${getTalukas}',
                type: 'GET',
                data: {
                    districtId: districtId
                },
                
                success: function (data) {
                    const $talukaSelect = $("#taluka");
                    $talukaSelect.empty();

                    let talukas = typeof data === "string" ? JSON.parse(data) : data;

                    if (talukas && talukas.length > 0) {
                        $talukaSelect.append('<option value="">Select</option>');

                        $.each(talukas, function (index, value) {
                        	$talukaSelect.append('<option value="' + value.talukaId + '">' + value.talukaName + '</option>');
                        });
                    } else {
                        $talukaSelect.append('<option value="">No talukas available</option>');
                    }
                }
                
            });
        }
    });
});




function saveSchoolCollegeRegistration(event){
	console.log("registration");
	var form = $("#register_school_college")[0];
	var formData = new FormData(form);
	if (event) {
        event.preventDefault(); // Stops the default form submission behavior
    }
	if($('#register_school_college').valid()){	
	 $.ajax({
        type: "POST",
        url: '${saveSchoolCollegeRegistrationURL}' ,
        data:  formData, 
        contentType : false,
		cache : false,
		processData : false,
        success: function(data){ 
        	console.log("data: ", typeof data);
        if (typeof data === 'string') {
            try {
                data = JSON.parse(data);
            } catch (e) {
                console.error("Failed to parse JSON response: ", e);
                return; 
            }
        }
        console.log("Parsed data: ", data);
        if(data.registration == true){
    		var msg = '<liferay-ui:message key="registered-successfully"/> ';
    	    $('#success-application').html(msg);
    		$("#registrationForSports").modal('show');
    	}else{
    		var msg = '<liferay-ui:message key="registration-is-unsucessfull"/>';
    		 $("#registrationForSports").modal('show'); 
    	}
    	 }
       
    });
	}
};


//Generate CAPTCHA
function generateCaptcha() {
    const characters = "0123456789";
    let captcha = "";
    for (let i = 0; i < 4; i++) {
        captcha += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    return captcha;
}

// Draw CAPTCHA on canvas
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

// Initialize CAPTCHA
let currentCaptcha = generateCaptcha();
drawCaptcha(currentCaptcha);

// Refresh CAPTCHA
$("#refreshCaptcha").on("click", function () {
    currentCaptcha = generateCaptcha();
    drawCaptcha(currentCaptcha);
    $("#captchaInput").val("");
    $("#captchaError").text(""); // Clear error
    $("#captchaInput-error").text("");
    
});

function closeModal() {
    $("#registrationForSports").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
	window.location.href = "/login";
}

</script> 