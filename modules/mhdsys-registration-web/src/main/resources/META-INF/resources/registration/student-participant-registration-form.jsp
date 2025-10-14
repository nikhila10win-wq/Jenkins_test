<%@page import="com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.captcha.CaptchaException"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://liferay.com/tld/captcha" prefix="liferay-captcha" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<input type="hidden" id="regEmailExist" value="<liferay-ui:message key="email-id-already-exist"/>">
<input type="hidden" id="validAadhaarMsg" value="<liferay-ui:message key="valid-aadhaar-msg"/>">
<input type="hidden" id="validMobileMsg" value="<liferay-ui:message key="valid-mobile-msg"/>">
<input type="hidden" id="validEmailMsg" value="<liferay-ui:message key="valid-email-msg"/>">

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
						<h5><liferay-ui:message key="school-college-education-institutions"/></h5>						
					</div> --%>
					
				 <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="school-college-education-institutions" />
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
					<input type="hidden" class="form-control" id="regType" name="regType" value="SchoolCollege" />					
						
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
									<select class="form-control" name="division">
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
							        <label for="ageGroupUnder14"><liferay-ui:message key="school-name" /><sup class="text-danger">*</sup></label>
							        <input type="text" class="form-control" id="firstName" name="firstName" value="" />
							    </div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="name-of-proncipal-head-master"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="principalName" name="principalName" value="" />
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label><liferay-ui:message key="name-of-sports-physical-teacher"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="sportsTeacher" name="sportsTeacher" value=""/>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="school-registration-no"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="schoolRegNo" id="schoolRegNo" value=""/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="aadhar-no-of-principal"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="aadharNo" id="aadharNo" value=""/>
								</div>
							</div>
						</div>
						
						<div class="row">
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
						
						
						<div class="form-group">
																<div class="d-flex align-items-center">
						                <canvas id="captchaCanvas" class="captcha-image"></canvas>
						                <button type="button" id="refreshCaptcha" class="btn btn-secondary btn-sm">Refresh</button>
						            </div>
						            <label><liferay-ui:message key="text-verification-code"/><sup class="text-danger">*</sup></label>
						            <input type="text" class="form-control mt-2" id="captchaInput" name="captchaInput" placeholder="Enter Captcha">
						        <div id="captchaError" class="error"></div>
								</div>
						</div>
						
						<div class="card-footer bg-transparent text-right p-4">
							<button class="btn btn-primary" onclick="saveSchoolCollegeRegistration()"><liferay-ui:message key="register" /></button>
							<button class="btn btn-primary" onclick="location.href='/home';"><liferay-ui:message key="cancel" /></button>
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
                                    <p><liferay-ui:message key="registered-successfully"/></p>
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
		firstName: {
			required:true,
		},
		principalName: {
			required:true,
		},
		sportsTeacher: {
			required:true,
		},
		schoolRegNo: {
			required:true,
		},
		aadharNo: {
			required:true,
			aadhaarValidation:true
		},
		mobileNo: {
			required:true,
			mobileNumberValidation:true
		},
		landlineNo: {
			required:true,
		},
		email: {
			required:true,
			noEdgeSpaces:true,
			validateEmail:true,
			existingEmail:true
		},
		website: {
			required:true,
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
		firstName: {
			required:"<liferay-ui:message key="please-enter-school-name"/>",
		},
		principalName: {
			required:"<liferay-ui:message key="please-enter-principal-name"/>"
		},
		sportsTeacher: {
			required:"<liferay-ui:message key="please-enter-sports-teacher-name"/>"
		},
		schoolRegNo: {
			required:"<liferay-ui:message key="please-enter-school-reg-no"/>"
		},
		aadharNo: {
			required:"<liferay-ui:message key="please-select-sport-type"/>"
		},
		mobileNo: {
			required:"<liferay-ui:message key="please-enter-mobile-no"/>",
		},
		landlineNo: {
			required:"<liferay-ui:message key="please-enter-landline-no"/>"
		},
		email: {
			required:"<liferay-ui:message key="please-enter-email-id"/>"
		},
		website: {
			required:"<liferay-ui:message key="please-enter-website"/>"
		}
		
	},
});
	
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
	    }, $('#validMobileMsg').val());
	}
	
	if(!findProperty($.validator.methods, 'validateEmail')){
		$.validator.addMethod("validateEmail", function(value, element) {
			const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			if (!regex.test(value)) {
	            $.validator.messages.validateEmail = '<liferay-ui:message key="valid-email-msg"/>';
	            return false;
	        }
		    return this.optional(element) || regex.test(value);
		}, $('#validEmailMsg').val());
	}
	
	if(!findProperty($.validator.methods, 'aadhaarValidation')){
		$.validator.addMethod("aadhaarValidation", function(value, element) {
			const regex = /^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$/;
			if (!regex.test(value)) {
	            return false;
	        }
		    return this.optional(element) || regex.test(value);
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
					console.log("Data ::: "+data);
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




function saveSchoolCollegeRegistration(){
	console.log("registration");
	var form = $("#register_school_college")[0];
	var formData = new FormData(form);
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

</script> 