<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.captcha.CaptchaException"%>

<%@ taglib uri="http://liferay.com/tld/captcha" prefix="liferay-captcha" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.EMAIL_EXIST_URL%>"
var="emailExist" />
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.SCOUT_AND_GUIDE_REGISTRATION_MVC_RESOURCE%>"
var="saveScoutAndGuideRegURL" />
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="scout-and-guide-master-registration" />
						</h5>
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn" href="/group/guest/scout-guide-ncc"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
						</div>
					</div>
					<form id="scoutMasterForm">
						<div class="card-body">

							<!-- Personal Details -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="personal-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="first-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="firstName" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="last-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="lastName" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="mother-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="motherName" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="father-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="fatherName" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group mb-1">
												<label><liferay-ui:message key="gender" /><sup
													class="text-danger">*</sup></label><br> 
													<input type="radio"
													name="gender" value="Male" />
												<liferay-ui:message key="male" />
												<input type="radio" name="gender" value="Female"
													class="ms-3" />
												<liferay-ui:message key="female" />
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Employee Details -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="employee-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="current-designation" /><sup
													class="text-danger">*</sup></label> <select class="form-control"
													name="designation" id="designation">
													<option value=""><liferay-ui:message key="select" /></option>
													<option value="Scout Master">Scout Master</option>
													<option value="Guide Master">Guide Master</option>
												</select>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message
														key="school-name-or-college-name" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="schoolName" />
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Verification -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="verification" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="aadhar-number" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="aadharNumber" />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message
														key="text-verification-code" /> <sup class="text-danger">*</sup>
												</label>
												<div class="d-flex align-items-center">
													<canvas id="captchaCanvas" class="captcha-image me-2"></canvas>
													<button type="button" id="refreshCaptcha"
														class="btn btn-refresh btn-sm"
														aria-label="Refresh Captcha">
														<i class="bi bi-repeat"></i>
													</button>
													<input type="text" class="form-control mb-1"
														id="captchaInput" name="captchaInput"
														placeholder="Enter Captcha">
												</div>
												<div id="captchaInput-error" class="error text-danger small"></div>
												
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="email-id" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="emailId" id="emailId" />
											</div>
										</div>

										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="mobile-number" /><sup
													class="text-danger">*</sup></label> <input type="text"
													class="form-control" name="mobileNumber" />
											</div>
										</div>
									</div>
                           		<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label> <liferay-ui:message key="aadhar-card-upload" />
												<sup class="text-danger">*</sup> <em
												class="bi bi-info-circle-fill"
												title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
											</label>

											<!-- File Input (Shared for Add/Edit) -->
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="aadharCard"
													name="aadharCard" accept="application/pdf"
													onchange="handleFileUpload(event, 'aadharCard', 'aadharCardPreviewContainer', 'aadharCardPreviewLink', 'aadharCardDeleteButton')">
												<label class="custom-file-label" for="aadharCard"> <liferay-ui:message
														key="choose-file" />
												</label>
											</div>

											<!-- File Preview Container (Shared for Add/Edit) -->
											<div
												class="ownerProofid mt-3 ${empty personalDetails.aadharCardURL ? 'd-none' : 'd-flex'}"
												id="aadharCardPreviewContainer">
												<a class="aadharCardProofCls text-truncate" id="aadharCardPreviewLink"
													href="${empty personalDetails.aadharCardURL ? '' : personalDetails.aadharCardURL}"
													target="_blank"
													style="flex-grow: 1; text-decoration: none;"> ${empty personalDetails.aadharCardName ? '' : personalDetails.aadharCardName}
												</a>
												<button type="button" id="aadharCardDeleteButton"
													class="dltaadharCardBtn close" aria-label="Close"
													onclick="deleteFile('aadharCardPreviewContainer', 'aadharCard')">
													<span aria-hidden="true" class="text-danger"> <em
														class="bi bi-x-circle-fill"></em>
													</span>
												</button>
											</div>
										</div>
									</div>
                           		  </div>
								</div>
							</div>
						</div>
						
						<div class="card-footer bg-transparent text-right p-4">
									
									<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/scout-guide-ncc';">
                                		<liferay-ui:message key="cancel" />
                            		</button>	
                            		
                            		<button class="btn btn-primary" type="button" onclick="location.reload();">
										<liferay-ui:message key="reset" />
									</button>	
								
									<button type="button" class="btn btn-primary"
										onclick="saveScoutMasterForm(event)">
										<liferay-ui:message key="register" />
									</button>
								</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal Popup for Grievance Save Success -->
<div class="modal fade" id="scoutGuideMasterRegModal" tabindex="-1" role="dialog" aria-labelledby="grievanceModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="grievanceModalTitle">
					<liferay-ui:message key="scout-and-guide-registration" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3">
							<p id="successScoutGuideMasterReg" ></p>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer d-flex justify-content-end">
				<a href="/group/guest/scout-guide-ncc" type="button" id="closescoutGuideMasterRegModal"
					class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('scoutGuideMasterRegModal')">
					<liferay-ui:message key="close" />
				</a>
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
    addValidationMethods();

    $('#scoutMasterForm').validate({
        onkeyup: function (element) { $(element).valid(); },
        onchange: function (element) { $(element).valid(); },
        rules: {
            firstName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                minlength: 2,
                maxlength: 75
            },
            lastName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                minlength: 2,
                maxlength: 75
            },
            motherName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                minlength: 2,
                maxlength: 75
            },
            fatherName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                minlength: 2,
                maxlength: 75
            },
            gender: {
                required: true
            },
            designation: {
                required: true
            },
            schoolName: {
                required: true,
                minlength: 2,
                maxlength: 100
            },
            aadharNumber: {
                required: true,
                digits: true,
                minlength: 12,
                maxlength: 12
            },
            emailId: {
                required: true,
                email: true,
                existingEmail: true
            },
            mobileNumber: {
                required: true,
                mobileNumberValidation: true,
                minlength: 10,
                maxlength: 10
            },
            aadharCard: {
                required: true,
                accept: "application/pdf",
                filesize: 2 * 1024 * 1024,
            },
            captchaInput: {
                required: true,
                equalToCaptcha: true
            }
        },
        messages: {
            firstName: {
                required: "<liferay-ui:message key='please-enter-first-name' />",
                pattern: "<liferay-ui:message key='only-alphabets-allowed' />",
                minlength: "<liferay-ui:message key='minimum-length-is-2' />",
                maxlength: "<liferay-ui:message key='address-maxlength' />"
            },
            lastName: {
                required: "<liferay-ui:message key='please-enter-last-name' />",
                pattern: "<liferay-ui:message key='only-alphabets-allowed' />",
                minlength: "<liferay-ui:message key='minimum-length-is-2' />",
                maxlength: "<liferay-ui:message key='address-maxlength' />"
            },
            motherName: {
                required: "<liferay-ui:message key='please-enter-mother-name' />",
                pattern: "<liferay-ui:message key='only-alphabets-allowed' />",
                minlength: "<liferay-ui:message key='minimum-length-is-2' />",
                maxlength: "<liferay-ui:message key='address-maxlength' />"
            },
            fatherName: {
                required: "<liferay-ui:message key='please-enter-father-name' />",
                pattern: "<liferay-ui:message key='only-alphabets-allowed' />",
                minlength: "<liferay-ui:message key='minimum-length-is-2' />",
                maxlength: "<liferay-ui:message key='address-maxlength' />"
            },
            gender: {
                required: "<liferay-ui:message key='please-select-gender' />"
            },
            designation: {
                required: "<liferay-ui:message key='please-enter-designation' />"
            },
            schoolName: {
                required: "<liferay-ui:message key='please-enter-school-or-college-name' />",
                minlength: "<liferay-ui:message key='minimum-length-is-2' />",
                maxlength: "<liferay-ui:message key='max-100-characters-allowed' />"
            },
            aadharNumber: {
                required: "<liferay-ui:message key='please-enter-aadhaar-number' />",
                digits: "<liferay-ui:message key='only-digits-allowed' />",
                minlength: "<liferay-ui:message key='aadhaar-must-be-12-digits' />",
                maxlength: "<liferay-ui:message key='aadhaar-must-be-12-digits' />",
                aadhaarValidation: "<liferay-ui:message key='enter-valid-aadhaar-number' />"
            },
            emailId: {
                required: "<liferay-ui:message key='please-enter-email' />",
                email: "<liferay-ui:message key='enter-valid-email' />"
            },
            mobileNumber: {
                required: "<liferay-ui:message key='please-enter-mobile-number' />",
                mobileNumberValidation: "<liferay-ui:message key='enter-valid-mobile-number' />",
                minlength: "<liferay-ui:message key='mobile-must-be-10-digits' />",
                maxlength: "<liferay-ui:message key='mobile-must-be-10-digits' />"
            },
            aadharCard: {
                required: "<liferay-ui:message key='please-upload-aadhaar-card' />",
                accept: "<liferay-ui:message key='only-pdf-allowed' />",
                filesize: "<liferay-ui:message key='file-under-2mb' />"
            },
            captchaInput: {
                required: "<liferay-ui:message key='enter-captcha' />",
                equalToCaptcha: "<liferay-ui:message key='invalid-captcha' />"
            }
        },
        errorPlacement: function (error, element) {
            if (element.attr("name") === "gender" || element.attr("name") === "captchaInput") {
                element.closest('.form-group').after(error);
            } else {
                error.insertAfter(element);
            }
        },

    });
    
});

// Custom methods
function addValidationMethods() {
    $.validator.addMethod("filesize", function (value, element, param) {
        return this.optional(element) || (element.files[0].size <= param);
    });

    $.validator.addMethod("filexssFilter", function (value, element) {
        if (element.files.length === 0) return true;
        let validRegex = /^[a-zA-Z0-9 ._-]+$/;
        return validRegex.test(element.files[0].name);
    }, "Invalid file name");

    $.validator.addMethod("equalToCaptcha", function (value) {
        return value === currentCaptcha;
    }, "Captcha does not match");

    $.validator.addMethod("existingEmail", function (value, element) {debugger
        let isValid = false;
        $.ajax({
            url: "${emailExist}",
            type: "GET",
            data: { email: value },
            async: false,
            success: function (res) {
                let json = JSON.parse(res);
                isValid = !json.emailExist;
            }
        });
        return isValid;
    }, "Email already exists");
    if(!findProperty($.validator.methods, 'aadhaarValidation')){
		$.validator.addMethod("aadhaarValidation", function(value, element) {
			const regex = /^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$/;
			if (!regex.test(value)) {
	            return false;
	        }
		    return this.optional(element) || regex.test(value);
		});
	}
    if (!findProperty($.validator.methods, 'mobileNumberValidation')) {
	    $.validator.addMethod("mobileNumberValidation", function(value, element) {
	        var basicRegex = /^[6-9]\d{9}(\d{3})?$/; // 10 or 13 digits, starting with 6-9
	        var sameDigitsRegex = /^(\d)\1{9,12}$/; // All digits are the same for 10 or 13 digits
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
	    });
	}
}
function saveScoutMasterForm(event) {debugger
    event.preventDefault();

    if ($('#scoutMasterForm').valid()) {
        var form = $('#scoutMasterForm')[0];
        var formData = new FormData(form);

        $.ajax({
            url: "${saveScoutAndGuideRegURL}",
            method: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {debugger
                var data = typeof response === "string" ? JSON.parse(response) : response;
                if (data.scoutGuideReg === true) {debugger
                    $('#successScoutGuideMasterReg').html("<liferay-ui:message key='registration-is-successful' />");
                } else {
                    $('#successScoutGuideMasterReg').html("<liferay-ui:message key='registration-is-unsucessfull' />");
                }
                $('#scoutGuideMasterRegModal').modal('show');
            },
            error: function (xhr) {
                console.log("Something went wrong. Please try again.");
            }
        });
    }
}
let currentCaptcha = generateCaptcha();
drawCaptcha(currentCaptcha);

function generateCaptcha() {
    const chars = "0123456789";
    return Array.from({ length: 5 }, () => chars[Math.floor(Math.random() * chars.length)]).join('');
}

function drawCaptcha(captchaText) {
    const canvas = document.getElementById("captchaCanvas");
    const ctx = canvas.getContext("2d");
    canvas.width = 200;
    canvas.height = 50;

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = "#eaeaea";
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // Draw noise
    for (let i = 0; i < 30; i++) {
        ctx.fillStyle = `rgb(${Math.random()*255}, ${Math.random()*255}, ${Math.random()*255})`;
        ctx.fillRect(Math.random()*canvas.width, Math.random()*canvas.height, 2, 2);
    }

    // Draw text
    ctx.font = "bold 30px Arial";
    ctx.fillStyle = "#000";
    ctx.textAlign = "center";
    ctx.fillText(captchaText, canvas.width / 2, canvas.height / 2 + 10);
}

$("#refreshCaptcha").click(function () {
    currentCaptcha = generateCaptcha();
    drawCaptcha(currentCaptcha);
    $("#captchaInput").val('');
    $("#captchaError").text('');
});
function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {debugger
    
    const fileInput = event.target;
    const file = fileInput.files[0];
    const previewContainer = document.getElementById(filePreviewContainer);
    const previewLink = document.getElementById(filePreviewLink);
    const deleteButton = document.getElementById(deleteBtn);

    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
    previewLink.textContent = '';
    previewLink.href = '';
    deleteButton.dataset.filename = '';
    
    if (file && $('#' + id).val() !== '' && (typeof $('#' + id).valid === 'function' ? $('#' + id).valid() : true)) {   
        const fileName = file.name;

        previewContainer.classList.remove('d-none');
        previewContainer.classList.add('d-flex');

        previewLink.textContent = fileName;
        previewLink.href = URL.createObjectURL(file); 
        previewLink.target = "_blank";
        
        Object.assign(previewLink.style, {
            textDecoration: "none",
           // wordWrap: "break-word",
           // whiteSpace: "normal",
           // overflow: "hidden",
           // maxWidth: "200px"
        });

        deleteButton.dataset.filename = fileName;
    }
}

function deleteFile(filePreviewContainer,id) {
    const previewContainer = document.getElementById(filePreviewContainer);
    const fileInput = document.getElementById(id);

    fileInput.value = "";
 $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("choose-file"); 
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}
function closeModal() {debugger
 	$("#scoutGuideMasterRegModal").modal('hide');
 	$(".modal-backdrop").remove();
 	$("body").removeClass("modal-open");
}
</script>