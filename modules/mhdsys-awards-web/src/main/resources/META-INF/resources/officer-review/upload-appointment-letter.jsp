<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<portlet:resourceURL id="<%=AwardsWebPortletKeys.APPOINTMENT_LETTER_UPLOAD%>"  var="uploadAppointmentLetterURL" />

<portlet:renderURL var="awardsRedirectURL">
    <portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.AWARDS %>" />
</portlet:renderURL>

<form id="sports_application" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="upload-appointment-letter" /></h5>
						<div>
							<c:if test="${isAssociation}">
								<a href="/group/guest/verify-award-applications" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a>
							</c:if>
						</div>
					</div>
					
					<div class="card-body">
						<div id="dynamicFields">
							<div class="row dynamic-row">
									
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="association-name"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="associationName" id="associationName" value="${associationName}" readonly="readonly"/>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="name"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="name" id="name" />
									</div>
								</div>
								
								<div class="col-md-6">
								    <div class="form-group">
								        <label>
								            <liferay-ui:message key="appointment-letter" />
								            <sup class="text-danger">*</sup>
								            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file" />"></em>
								        </label>
								            <%-- View Mode --%>
								                <c:if test="${not empty application.appointmentLetterUrl}">
								                    <div>
								                        <a href="${application.appointmentLetterUrl}" target="_blank" style="flex-grow: 1; text-decoration: none;">
								                            ${application.appointmentLetterName}
								                        </a>
								                    </div>
								                </c:if>
								            <%-- Edit Mode --%>
								            <%-- <c:if test="${mode eq 'edit'}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="appointmentLetter" name="appointmentLetter"
								                        onchange="handleFileUpload(event, 'appointmentLetter', 'appointmentLetterPreviewContainer', 'appointmentLetterPreviewLink', 'appointmentLetterdeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                Show existing file with delete option if available
								                <c:if test="${not empty application.dprDocumentURL}">
								                    <div class="ownerProofid d-flex mt-3" id="appointmentLetterPreviewContainer">
								                        <a class="appointmentLetterProofCls text-truncate" id="appointmentLetterPreviewLink" 
								                            href="${application.dprDocumentURL}" target="_blank"
								                            style="flex-grow: 1; text-decoration: none;">
								                            ${application.dprDocumentName}
								                        </a>
								                        <button type="button" id="appointmentLetterdeleteButton" class="dltappointmentLetterBtn close"
								                            aria-label="Close" onclick="deleteFile('appointmentLetterPreviewContainer', 'appointmentLetter')">
								                            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                        </button>
								                    </div>
								                </c:if>
								                Empty container for new file upload preview
								                <div class="ownerProofid d-none mt-3" id="appointmentLetterPreviewContainer">
								                    <a class="appointmentLetterProofCls text-truncate"  id="appointmentLetterNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="appointmentLetterNewDeleteButton" class="dltappointmentLetterBtn close"
								                        aria-label="Close" onclick="deleteFile('appointmentLetterPreviewContainer', 'appointmentLetter')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if> --%>
								            <%-- Add/Other Modes --%>
								            <c:if test="${cmd eq 'edit' && empty application.appointmentLetterUrl}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="appointmentLetter" name="appointmentLetter"
								                        onchange="handleFileUpload(event, 'appointmentLetter', 'appointmentLetterNewPreviewContainer', 'appointmentLetterNewPreviewLink', 'appointmentLetterNewDeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                <%-- Empty container for new file upload preview --%>
								                <div class="ownerProofid d-none mt-3" id="appointmentLetterNewPreviewContainer">
								                    <a class="appointmentLetterProofCls text-truncate" id="appointmentLetterNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="appointmentLetterNewDeleteButton" class="dltappointmentLetterBtn close"
								                        aria-label="Close" onclick="deleteFile('appointmentLetterNewPreviewContainer', 'appointmentLetter')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if>
								    </div>
								</div>
								
								
								<!-- Mobile Number -->
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Mobile Number<sup class="text-danger">*</sup></label>
                                        <input type="text" class="form-control" id="mobileNo" name="mobileNo" maxlength="10" placeholder="Enter mobile number" />
                                        <button type="button" class="btn btn-secondary mt-2" onclick="sendOtp()">Get OTP</button>
                                    </div>
                                </div>

                                <!-- OTP Verification Section (Initially Hidden) -->
                                <div class="col-md-6" id="otpSection" style="display:none;">
                                    <div class="form-group">
                                        <label>Enter OTP<sup class="text-danger">*</sup></label>
                                        <input type="text" class="form-control" id="otpInput" maxlength="4" placeholder="Enter OTP" />
                                        <button type="button" class="btn btn-success mt-2" onclick="verifyOtp()">Verify OTP</button>
                                        <input type="hidden" id="fixedOtp" value="6279" />
                                    </div>
                                </div>
								
								
							</div>
						</div>
					</div>
					
					<div class="card-footer bg-transparent text-right p-4">
							
						<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards';">
                        	<liferay-ui:message key="cancel" />
                    	</button>
                            		
                         <button class="btn btn-primary" type="button" onclick="location.reload();">
							<liferay-ui:message key="reset" />
						</button>
					
						<button class="btn btn-primary submitBtn" type="button" disabled
							onclick="addSportsApplication(event)"> <liferay-ui:message key="submit" />
						</button>
						
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
</form>


<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="saveAwardsApplication" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="appointment-letter"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
                                    	<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/verify-award-applications" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for awards application for sports person -->


<div class="modal fade" id="verifyOTP" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="OTP Verified"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
                                    	<p id="otp-msg"><liferay-ui:message key="otp-verified-successfully"/></p>
									</div>
							</div>
						</div>
					</div>
					<!-- <div class="modal-footer d-flex justify-content-end">
       					 <a type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeVerifyOTPModal()"><liferay-ui:message key="close"/></a>
					</div> -->
					<div class="modal-footer d-flex justify-content-end">
					    <button type="button" id="closeModal" class="btn btn-secondary maha-save-btn" 
					        data-bs-dismiss="modal" onclick="closeVerifyOTPModal()">
					        <liferay-ui:message key="close"/>
					    </button>
					</div>
				</div>
			</div>
		</div>
		
		
<div class="modal fade" id="sendOTP" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="OTP"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
                                    	<p id="otp-msg"><liferay-ui:message key="otp-sent-successfully"/></p>
									</div>
							</div>
						</div>
					</div>
					<!-- <div class="modal-footer d-flex justify-content-end">
       					 <a type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeOTPModal()"><liferay-ui:message key="close"/></a>
					</div> -->
					<div class="modal-footer d-flex justify-content-end">
					    <button type="button" id="closeModal" class="btn btn-secondary maha-save-btn" 
					        data-bs-dismiss="modal" onclick="closeOTPModal()">
					        <liferay-ui:message key="close"/>
					    </button>
					</div>
				</div>
			</div>
		</div>


<script>

$(document).ready(function () {
	
	 // jQuery validation start
    $('#sports_application').validate({
        onkeyup: function (element){
            $(element).valid();
        },
        onchange: function (element){
            $(element).valid();
        },
        rules:{
        	associationName: {
                required: true,
                minlength: 3,
                maxlength: 75,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
                pattern: /^[A-Za-z0-9]+(?: [A-Za-z0-9]+)*$/
            },
            name: {
                required: true,
                minlength: 3,
                maxlength: 75,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
                validateName: true,
            },
            appointmentLetter: {
            	required: true,
            	 pdfOnly: true,
                 maxFileSize: true,
            }
        },
        messages:{
        	associationName: {
                required: "<liferay-ui:message key='please-enter-player-name'/>",
                minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern: '<liferay-ui:message key="only-alphabets-numbers-and-space-are-allowed"/>'
            },
            name: {
                required: "<liferay-ui:message key='please-enter-name'/>",
                minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
            },
            appointmentLetter: {
            	required: "<liferay-ui:message key='please-upload-appointment-letter' />"
            }
           
        },
        
    });
    
    
    $.validator.addMethod("startEndSpace", function(value, element) {
        return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
    }, "<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");
    
    $.validator.addMethod("validateName", function(value, element) {
	    // Allow only letters (A-Z, a-z) and spaces
	    return this.optional(element) || /^[A-Za-z]+(?: [A-Za-z]+)*$/.test(value);
	}, "<liferay-ui:message key='only-alphabets-and-space-are-allowed'/>");
    
    $.validator.addMethod("validateRemarks", function(value, element) {
 	   const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
 	    return this.optional(element) || regex.test(value);
 	}, "<liferay-ui:message key='please-enter-valid-place-details' />");
    
    $.validator.addMethod("singleSpaceBetweenWords", function(value) {
        return !/ {2,}/.test(value);
    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');
    
    $.validator.addMethod("placeValidation", function(value) {
        return /^[A-Za-z0-9\-\.\/#, ]*$/.test(value);
    }, '<liferay-ui:message key="please-enter-valid-details"/>');

    $.validator.addMethod("validateEmail", function(value, element) {
 		const regex =  /^(?!.*\.\.)(?!.*__)(?!.*[._][._])(?![_\.])[a-zA-Z0-9._]*[a-zA-Z][a-zA-Z0-9._]*(?<![_\.])@[a-zA-Z0-9][a-zA-Z0-9-]*\.[a-zA-Z]{2,}$/
	    return this.optional(element) || regex.test(value);
	}, "<liferay-ui:message key='please-enter-valid-email-address'/>");
    
    $.validator.addMethod("validAddress", function (value, element) {
        value = $.trim(value); 
        return this.optional(element) || (/^(?!.*\s{2,})[a-zA-Z0-9\s,.\-/#]{3,250}$/.test(value));
    }, "<liferay-ui:message key='please-enter-valid-address' />");
    
    // Validate PDF extension
    $.validator.addMethod("pdfOnly", function (value, element) {
        if (element.files.length === 0) return true; // Don't block other required validation
        const fileName = element.files[0].name;
        const ext = fileName.split('.').pop().toLowerCase();
        return ext === "pdf";
    }, "<liferay-ui:message key='allowed-only-pdf-file' />");

    // Validate file size (max 2MB)
    $.validator.addMethod("maxFileSize", function (value, element) {
        if (element.files.length === 0) return true;
        return element.files[0].size <= 2 * 1024 * 1024; // 2 MB
    }, "<liferay-ui:message key='file-size-limit' />");
    
    $.validator.addMethod("generalFieldsValidation", function(value, element) {
  	   const regex = /^(?!0+$)(?!.* {2})(?!.*(\s[\/-]|[\/-]\s))(?!.*([,.\-/])\2)[A-Za-z0-9]+(?:[ ]?[,\.]?[ ]?[A-Za-z0-9]+|[\/-][A-Za-z0-9]+)*$/i;
  	    return this.optional(element) || regex.test(value);
  	}, "<liferay-ui:message key='only-letters-numbers-symbols-allowed' />");

	 $.validator.addMethod("noConsecutiveSpecialChars", function(value, element) {
	    var hasLetterOrDigit = /[A-Za-z0-9]/.test(value);

	    // Disallow repeated special characters like "..", "--", "//", "  "
	    var repeatedSpecials = /([.,\/\-])\1+/;

	    // Disallow invalid consecutive combinations like ".,", ",.", ",-", "./", etc.
	    var mixedSpecials = /[.,\/\-]{2,}/;

	    if (hasLetterOrDigit && (repeatedSpecials.test(value) || mixedSpecials.test(value))) {
	        return false;
	    }
	    return true;
	}, "<liferay-ui:message key='no-consecutive-special-characters-allowed' />");

	$.validator.addMethod("noSpaceAroundDashSlash", function (value, element) {
	    // Check for space before or after "-" or "/"
	    const regex = /(\s[-]|[-]\s|\/\s|\s\/)/;
	    return this.optional(element) || !regex.test(value);
	}, "<liferay-ui:message key='no-space-around-hyphen-or-slash-allowed' />");

	$.validator.addMethod("noStartEndSpecialChar", function (value, element) {
	    return this.optional(element) || !/^[.,\/\-]|[.,\/\-]$/.test(value);
	}, "<liferay-ui:message key='should-not-start-or-end-with-special-characters' />");
    
	
	
	
});

function addSportsApplication(event){debugger
		var form = $("#sports_application")[0];
		var formData = new FormData(form);
		
		if (event) {
	        event.preventDefault();
	    } 
		if($("#sports_application").valid()){
	 $.ajax({
	        type: "POST",
	        url: '${uploadAppointmentLetterURL}' ,
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
	        	if(data.uploaded == true){
	        		var msg = "<liferay-ui:message key="appoinment-letter-uploaded-successfully"/>";
	        	    $('#success-application').html(msg);
	        		 $("#saveAwardsApplication").modal('show');
	        	}else{
	        		var msg = "<liferay-ui:message key="failed-to-upload-appointment-letter"/>";
	        	    $('#success-application').html(msg);
	        		 $("#saveAwardsApplication").modal('show'); 
	        	}
	    	 }
	    });
	}
};
	
function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {debugger;
    
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
            //wordWrap: "break-word",
           // whiteSpace: "normal",
            //overflow: "hidden",
            //maxWidth: "200px"
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


function sendOtp() {
    var mobile = $("#mobileNo").val().trim();
    if (mobile === "") {
        alert("Please enter your mobile number");
        return;
    }
    if (!/^[6-9]\d{9}$/.test(mobile)) {
        alert("Please enter a valid 10-digit mobile number");
        return;
    }
    $("#otpSection").show();
    $("#sendOTP").modal('show');
}

function verifyOtp() {
    var enteredOtp = $("#otpInput").val().trim();
    var fixedOtp = $("#fixedOtp").val();
    if (enteredOtp === "") {
        alert("Please enter the OTP");
        return;
    }
    if (enteredOtp === fixedOtp) {
    	$("#verifyOTP").modal('show');
    	$(".submitBtn").prop("disabled", false);
        
    } else {
        alert("Invalid OTP. Please try again.");
    }
}

function closeOTPModal() {debugger
	 event.preventDefault(); 
	 $("#sendOTP").modal('hide');
}

function closeVerifyOTPModal() {debugger
	event.preventDefault(); 
	$("#verifyOTP").modal('hide');
}
</script>