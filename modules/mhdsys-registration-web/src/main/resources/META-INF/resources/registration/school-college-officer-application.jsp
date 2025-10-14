<%@page import="com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.captcha.CaptchaException"%>
<%@ taglib uri="http://liferay.com/tld/captcha" prefix="liferay-captcha" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=RegistrationWebPortletKeys.SMART_SEARCH_SCHOOL_MVC_RESOURCE_COMMAND%>"
var="searchSchoolURL" />
<portlet:resourceURL id="<%=RegistrationWebPortletKeys.EMAIL_EXIST_URL%>"
var="emailExist" />
<input type="hidden" id="regEmailExist" value="<liferay-ui:message key="email-id-already-exist"/>">

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5><liferay-ui:message key="sports-person-coach-registration"/></h5>						
						<%-- <h5>*<liferay-ui:message key="indicates-mandotory-fields"/></h5> --%>						
						<h5><div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn" href="/registration"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
							</div></a>
						</h5>						
					</div>
				<form id="school-college-officer-form" enctype="multipart/form-data"> 
					<div class="card-body">
					<div class="card card-background p-0">
					 <div class="card-header header-card d-flex align-item-center justify-content-between">
					 		<h5><liferay-ui:message key="sports-person-coach-registration"/></h5>
					 		<h5><sup class="text-danger">*</sup><liferay-ui:message key="indicates-mandotory-fields"/></h5>
					 </div>
					 <div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="first-name"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="firstName"  value=""/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="last-name"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="lastName"  value=""/>
								</div>
							</div>
	
						</div>
						
						<div class="row">
									<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="mothers-name"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="mothersName"  value=""/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="fathers-name"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="fathersName"  value=""/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								    <label for="genderYes">
								        <liferay-ui:message key="gender" />
								        <sup class="text-danger">*</sup>
								    </label>
								    <div class="d-flex  mt-2">
									<div class="radio-text">
								        <input type="radio" class="radio-btn" id="genderYes" name="gender" value="1" />
								        <label for="genderYes"><liferay-ui:message key="male"/></label>
								    </div>
								    <div>
								        <input type="radio" class="radio-btn" id="genderNo" name="gender" value="2" />
								        <label for="genderNo"><liferay-ui:message key="female"/></label>
								    </div>
								    </div>
								</div>

							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="current-role"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="currentDesignation" id= "currentDesignation" >
										<option value=""><liferay-ui:message key="select"/></option>
										<c:forEach var="designation" items="${designations}">
											<option value="${designation.officerDesignationId}">${designation.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="school-or-college-or-company-institute-name"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="schoolOrCollegeName"   oninput="searchSchool(this.value)"  id="schoolOrCollegeName" value=""/>
									<div id="schoolSuggestions" class=""></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="aadhar-number"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="aadharNumber" name="aadharNumber"  value="" />
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="email-id"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="email"  name="email" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="mobile-number"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="mobileNumber"  name="mobileNumber" />
								</div>
							</div>
							</div>
							<!-- <div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="email-id-verification-code"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="emailIdVerificationCode"  name="emailIdVerificationCode" />
								</div>
							</div> -->
							
							<!-- <div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="aadhar-verification-code"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="aadharVerificationCode" name="aadharVerificationCode"  value="" />
								</div>
							</div> -->
							
						
						<!-- <div class="row">
							
						</div> -->
						<div class="row">
							<!-- <div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="mobile-number-verification-code"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="mobileNumberVerificationCode"  name="mobileNumberVerificationCode" />
								</div>
							</div> -->
							
							<div class="col-md-6">
								<div class="form-group">
									<label ><liferay-ui:message key="aadhar-card-pdf-only-2-mb"/><sup class="text-danger">*</sup></label>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="aadharCardReciept" name="aadharCardReciept" onchange="handleFileUpload(event)" accept=".pdf">
										<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
									</div>
									
								 <!-- Preview and Delete Section -->
								    <div class="aadharCardid d-none mt-3" id="filePreviewContainer">
								        <a class="aadharCardCls" id="filePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								        <button type="button" id="deleteButton"  class="dltFeesReceiptBtn close" aria-label="Close"  onclick="deleteFile()" >
								       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								   		</button>
								    </div>
								 </div>
							</div>
							<div class="col-md-6">
								 <%-- <div class="form-group">
								<label><liferay-ui:message key="captcha-code"/><sup class="text-danger">*</sup></label> 
									<liferay-captcha:captcha /><liferay-ui:error exception="<%= CaptchaException.class %>" message='<%= LanguageUtil.get(request, "captcha-verification-failed") %>' />
									<input type="text" class="form-control" id="captchaCode" name="captchaCode"  value="" />
								</div>  --%>
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
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="type"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="type" id= "type" >
										<option value=""><liferay-ui:message key="select"/></option>
											<option value="Private"><liferay-ui:message key="private"/></option>
											<option value="Government"><liferay-ui:message key="government"/></option>
									</select>
								</div>
							</div>
						</div>
						</div>
			
					</div>
				</div>
					<div class="card-footer bg-transparent text-right p-4">
							<a href="javascript:void(0)" type="button"  class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
							<button class="btn btn-primary" onclick="saveSchoolCollegeOfficerForm(event)"><liferay-ui:message key="save" /></button>
					</div>
				</form>
					
				</div>
			</div>
		</div>
	</div>
</div>


<!-- modal popup for add competition -->
<div class="modal fade" id="schoolCollegeOfficerModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <liferay-ui:message key="registration-is-successful"/></p>
									<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="javascript:void(0" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for add competition -->

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
    $('#school-college-officer-form').validate({
        onkeyup: function (element) {
            $(element).valid();
        },
        onchange: function (element) {
            $(element).valid();
        },
        rules: {
            firstName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                noEdgeSpaces:true
            },
            lastName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                noEdgeSpaces:true,
            },
            mothersName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                noEdgeSpaces:true,
            },
            fathersName: {
                required: true,
                pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/,
                noEdgeSpaces:true,
            },
            currentDesignation: {
                required: true,
            },
            gender: {
                required: true,
            },
            type: {
              required: function(element) {
                  return $("#currentDesignation").val() === "2";
              }
            },
            schoolOrCollegeName: {
                required: true,
                maxlength: 100
            },
            aadharNumber: {
                required: true,
                digits: true,
                minlength: 12,
                maxlength: 12,
            },
            email: {
                required: true,
                noEdgeSpaces:true,
                email: true,
                existingEmail:true
            },
            mobileNumber: {
                required: true,
                digits: true,
                minlength: 10,
                maxlength: 10,
            },
            aadharCardReciept: {
            	required: true,
            	accept: "application/pdf",
				filesize: 2 * 1024 * 1024,
				filexssFilter: true,
                
            },
        },
        messages: {
            firstName: {
                required: "<liferay-ui:message key='please-enter-first-name' />",
                pattern: "<liferay-ui:message key='only-alphabates-are-allowed' />"
            },
            lastName: {
                required: "<liferay-ui:message key='please-enter-last-name' />",
                pattern: "<liferay-ui:message key='only-alphabates-are-allowed' />"
            },
            mothersName: {
                required: "<liferay-ui:message key='please-enter-mothers-name' />",
                pattern: "<liferay-ui:message key='only-alphabates-are-allowed' />"
            },
            fathersName: {
                required: "<liferay-ui:message key='please-enter-fathers-name' />",
                pattern: "<liferay-ui:message key='only-alphabates-are-allowed' />"
            },
            currentDesignation: {
                required: "<liferay-ui:message key='please-select-designation' />",
            },
            gender: {
                required: "<liferay-ui:message key='please-select-gender' />",
            },
             type: {
                required: "<liferay-ui:message key='please-select-type' />",
            },
            schoolOrCollegeName: {
                required: "<liferay-ui:message key='please-enter-school-or-college-name' />",
                maxlength: "<liferay-ui:message key='maximum-length-is-100-characters' />"
            },
            aadharNumber: {
                required: "<liferay-ui:message key='please-enter-aadhar-number' />",
                digits: "<liferay-ui:message key='please-enter-only-numeric-values' />",
                minlength: "<liferay-ui:message key='aadhar-number-must-be-12-digits' />",
                maxlength: "<liferay-ui:message key='aadhar-number-must-be-12-digits' />",
            },
            email: {
                required: "<liferay-ui:message key='please-enter-email-id' />",
                email: "<liferay-ui:message key='please-enter-valid-email' />",
            },
            mobileNumber: {
                required: "<liferay-ui:message key='please-enter-mobile-number' />",
                digits: "<liferay-ui:message key='please-enter-only-numeric-values' />",
                minlength: "<liferay-ui:message key='mobile-number-must-be-10-digits' />",
                maxlength: "<liferay-ui:message key='mobile-number-must-be-10-digits' />",
            },
            aadharCardReciept: {
            	accept: "<liferay-ui:message key='please-choose-valid-file'/>",
				filesize: "<liferay-ui:message key='maximum-file-size-is-2-mb'/>",
				required: "<liferay-ui:message key='please-choose-a-file' />"
            },
        },
    });
});

function addValidationMethods(){
	$.validator.addMethod("filesize", function(value, element, param) {
	    return this.optional(element) || (element.files[0].size <= param);
	});
	$.validator.addMethod("filexssFilter", function(value, element, param) {
		if (element.files.length === 0) {
	        console.log("No file selected, skipping type validation.");
	        return true; // If no file is selected, don't validate the file type.
	    }
		let validFileRegex = new RegExp(/^[a-zA-Z0-9 ._-]+$/); 
	    if(!validFileRegex.test(element.files[0].name)){
	    	$.validator.messages.filexssFilter = "Special charecters not allowed.Please select a different file.";
	    	return false;
	    }
	    return true;
	});
	 if(!findProperty($.validator.methods, 'existingEmail')){
		$.validator.addMethod("existingEmail", function(value, element) {
			var email = $("#email").val();
			return this.optional(element) || checkEmailExist(email);
		}, $('#regEmailExist').val());
	} 
	 if (!findProperty($.validator.methods, 'noEdgeSpaces')) {
		    $.validator.addMethod("noEdgeSpaces", function(value) {
		        return value === value.trim();
		    }, '<liferay-ui:message key="no-leading-and-trailing-space-allowed"/>');
		}
	 if (!findProperty($.validator.methods, 'noEdgeSpaces')) {
		    $.validator.addMethod("noEdgeSpaces", function(value) {
		        return value === value.trim();
		    }, '<liferay-ui:message key="no-leading-and-trailing-space-allowed"/>');
		}

}
function saveSchoolCollegeOfficerForm(event){debugger
	var form = $("#school-college-officer-form")[0];
	var formData = new FormData(form);
	var fileInput = document.getElementById("aadharCardReciept");
	if (fileInput) {
	    if (fileInput.files && fileInput.files.length > 0) {
	 	var aadharCardReciept = document.getElementById("aadharCardReciept").files[0];
			if (aadharCardReciept) {
		        console.log("Selected adhar file: " + aadharCardReciept.name);
		        formData.append('aadharCardReciept', aadharCardReciept);
		    	}
		  	}
		}
		if (event) {
	        event.preventDefault(); // Stops the default form submission behavior
	    }
		if( $('#school-college-officer-form').valid()){
		 
			
			$.ajax({
		        type: "POST",
		        url: '${schoolCollegeOfficerFormURL}' ,
		        data:  formData, 
		        enctype: 'multipart/form-data',
		        contentType : false,
				cache : false,
				processData : false,
		        success: function(data){ 
		        	console.log("data: ", typeof data);
		        if (typeof data === 'string') {debugger
		            try {
		                data = JSON.parse(data);
		            } catch (e) {
		                console.error("Failed to parse JSON response: ", e);
		                return; 
		            }
		        }
		        console.log("Parsed data: ", data);
		        	if(data.schoolCollegeOfficerReg == true){
		        		var screenName = data.screenName;
		        		var password = data.password;
		        		console.log('screenname, '+screenName+'password: ',password)
		        		var msg = '<div style="color: red;">' +
		                '<liferay-ui:message key="note-your-credential-to-login" />:<br/>' +
		                '<b><liferay-ui:message key="username-is" /></b>: ' + screenName + '<br/>' +
		                '<b><liferay-ui:message key="password-is" /></b>: ' + password +
		            '</div>';
		        		$('#success-application').html(msg);
		        		  $("#schoolCollegeOfficerModel").modal('show');  
		        	}else{
		        		 $("#schoolCollegeOfficerModel").modal('show'); 
		        	}
		    	 }
		       
		    });
		};
	}
function closeModal() {debugger
    $("#schoolCollegeOfficerModel").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
	window.location.href = "/login";
}

function handleFileUpload(event) {debugger
    const fileInput = event.target;
    const file = fileInput.files[0]; // Get the uploaded file
    const previewContainer = document.getElementById('filePreviewContainer');
    const previewLink = document.getElementById('filePreviewLink');
    const deleteButton = document.getElementById('deleteButton');

    if (file && $('#aadharCardReciept').val() != '' && $('#aadharCardReciept').valid()) {   
    	const fileName = file.name;

        // Show the preview container
        previewContainer.classList.remove('d-none');
        previewContainer.classList.add('d-flex');

        // Set the link text and href
        previewLink.textContent = fileName;
        previewLink.href = URL.createObjectURL(file); // Generate a temporary object URL for preview/download
        previewLink.target = "_blank";

        // Attach the delete functionality
        deleteButton.dataset.filename = fileName; // Store file name in button dataset
    }
}

/**
 * Delete the uploaded file from the frontend.
 */
function deleteFile() {
    const previewContainer = document.getElementById('filePreviewContainer');
    const fileInput = document.getElementById('aadharCardReciept');

    // Reset file input
    fileInput.value = "";
	$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("choose-file"); 
    // Hide the preview container
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}

//search participant
/* 
function searchParticipant(query) {debugger
    if (query.length < 2) {
        $('#participantSuggestions').hide();
        return;
    }
	var form = $("#pt-teacher")[0];
	var formData = new FormData(form);
    $.ajax({
    	type: "POST",
        data:  formData, 
        contentType : false,
		cache : false,
		processData : false,
        url: '${searchParticipantURL}',
        success: function (response) {
        	console.log('response: ',response)
            response =JSON.parse(response);
            if (response && response.length > 0) {debugger
                let fragment = document.createDocumentFragment(); 
                let suggestionsHtml = '';
                response.forEach(function (participant) {
                	console.log("participant, ",participant)
                	var div = document.createElement('div');
                    div.textContent = participant;  
                    div.onclick = function() {
                        selectParticipant(participant); 
                    };
                    fragment.appendChild(div);
                    });
                $('#participantSuggestions').html('').append(fragment).show();

                var inputPos = $('#participantName').offset(); 
                $('#participantSuggestions').css({
                    top: inputPos.top + $('#participantName').outerHeight(), 
                    left: inputPos.left, 
                    width: $('#participantName').outerWidth() 
                });
            } else {
                $('#participantSuggestions').html('<div>No results found</div>').show();
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function selectParticipant(name) {
    $('#participantName').val(name);
    $('#participantSuggestions').hide();
}
 */
 
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
	document.getElementById('currentDesignation').addEventListener('change', function () {debugger
	    var currentDesignation = $('#currentDesignation').val();
	    var modalTitle = document.getElementById('exampleModalLongTitle');

	    if (currentDesignation == '1' ) {
	        modalTitle.innerHTML = '<liferay-ui:message key="sport-person-registration"/>';
	    } else if (currentDesignation == '2') {
	        modalTitle.innerHTML = '<liferay-ui:message key="sport-coach-registration"/>';
	    } else {
	        modalTitle.innerHTML = '<liferay-ui:message key="sport-person-coach-registration"/>';
	    }
	});

	function searchSchool(query) {debugger
	    if (query.length < 1) {
	        $('#schoolSuggestions').hide();
	        return;
	    }
		var form = $("#school-college-officer-form")[0];
		var formData = new FormData(form);
	    $.ajax({
	    	type: "POST",
	        data:  formData, 
	        contentType : false,
			cache : false,
			processData : false,
	        url: '${searchSchoolURL}',
	        success: function (response) {
	        	console.log('response: ',response)
	            response =JSON.parse(response);
	            if (response && response.length > 0) {debugger
	                let fragment = document.createDocumentFragment(); 
	                let suggestionsHtml = '';
	                response.forEach(function (school) {
	                	console.log("school, ",school.schoolName)
	                	var div = document.createElement('div');
	                    div.textContent = school.schoolName;  
	                    div.onclick = function() {
	                        selectSchool(school.schoolName); 
	                    };
	                    fragment.appendChild(div);
	                    });
	                $('#schoolSuggestions').html('').append(fragment).show();

	                var inputPos = $('#schoolOrCollegeName').offset(); 
	                $('#schoolSuggestions').css({
	                    top: inputPos.top + $('#schoolOrCollegeName').outerHeight(), 
	                    left: inputPos.left, 
	                    width: $('#schoolOrCollegeName').outerWidth() 
	                });
	            } else {
	                $('#schoolSuggestions').html('<div>No results found</div>').show();
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error("Error:", error);
	        }
	    });
	}
	function selectSchool(name) {
	    $('#schoolOrCollegeName').val(name);
	    $('#schoolSuggestions').hide();
	}

</script>