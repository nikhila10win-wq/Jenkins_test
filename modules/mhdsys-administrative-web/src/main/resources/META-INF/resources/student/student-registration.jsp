<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.STUDENT_REGISTRATION_MVC_RESOURCE_COMMAND%>"
var="saveStudentRegistrationURL" />
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="student-registration" />
						</h5>
						<div>
							<c:if test="${mode ne 'view'}">
								<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
									href="/group/guest/student-list"
									style="background-color: #26268E; border-color: #fff;"> <i
									class="bi bi-arrow-left-circle"></i> <liferay-ui:message
										key="back" />
								</a>
							</c:if>
							<c:if test="${mode eq 'view'}">
								<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
									href="/group/guest/student-list"
									style="background-color: #26268E; border-color: #fff;"> <i
									class="bi bi-arrow-left-circle"></i> <liferay-ui:message
										key="back" />
								</a>
							</c:if>
						</div>
					</div>

					<form id="studentRegistrationForm" method="POST">
						<div class="card-body">

							<!-- Personal Details Section -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="personal-details" />
								</div>
								<input type="hidden" class="form-control" name="mode"
									value="${mode}" /> <input type="hidden" class="form-control"
									name="studentRegistrationId"
									value="${studentDetails.studentRegistrationId}" />
								<div class="card-body">
									<div class="row">
										<!-- First Name -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message key="first-name" /> <sup
													class="text-danger">*</sup>
												</label> <input type="text" class="form-control" name="firstName"
													placeholder="First Name"
													value="${studentDetails.firstName}"
													<c:if test="${mode eq 'view'}">readonly</c:if> />
											</div>
										</div>
										<!-- Last Name -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message key="last-name" /> <sup
													class="text-danger">*</sup>
												</label> <input type="text" class="form-control" name="lastName"
													placeholder="Last Name" value="${studentDetails.lastName}"
													<c:if test="${mode eq 'view'}">readonly</c:if> />
											</div>
										</div>
									</div>

									<div class="row">
										<!-- Mother's Name -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message key="mothers-name" /> <sup
													class="text-danger">*</sup>
												</label> <input type="text" class="form-control" name="motherName"
													placeholder="Mother Name"
													value="${studentDetails.mothersName}"
													<c:if test="${mode eq 'view'}">readonly</c:if> />
											</div>
										</div>
										<!-- Father's Name -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message key="fathers-name" /> <sup
													class="text-danger">*</sup>
												</label> <input type="text" class="form-control" name="fatherName"
													placeholder="Father Name"
													value="${studentDetails.fathersName}"
													<c:if test="${mode eq 'view'}">readonly</c:if> />
											</div>
										</div>
									</div>

									<div class="row">
										<!-- Gender -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message key="gender" /> <sup
													class="text-danger">*</sup>
												</label>
												<div class="gender-errors">
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio" name="gender"
															value="Male"
															<c:if test="${studentDetails.gender eq 'Male'}">checked</c:if>
															<c:if test="${mode eq 'view'}">disabled</c:if> /> <label
															class="form-check-label"> <liferay-ui:message
																key="male" />
														</label>
													</div>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio" name="gender"
															value="Female"
															<c:if test="${studentDetails.gender eq 'Female'}">checked</c:if>
															<c:if test="${mode eq 'view'}">disabled</c:if> /> <label
															class="form-check-label"> <liferay-ui:message
																key="female" />
														</label>
													</div>
												</div>
											</div>
										</div>
										<!-- Aadhar Number -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message key="aadhar-number" />
													<sup class="text-danger">*</sup>
												</label> <input type="text" class="form-control" name="aadharNumber"
													placeholder="Aadhar Number"
													value="${studentDetails.aadharNumber}"
													<c:if test="${mode eq 'view'}">readonly</c:if> />
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- School / College Details Section -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="school-college-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<!-- Standard -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message key="standard" /> <sup
													class="text-danger">*</sup>
												</label> <input type="text" class="form-control" name="standard"
													placeholder="Standard" value="${studentDetails.standard}"
													<c:if test="${mode eq 'view'}">readonly</c:if> />
											</div>
										</div>
										<!-- School/College Name -->
										<div class="col-md-6">
											<div class="form-group">
												<label> <liferay-ui:message
														key="school-college-name" /> <sup class="text-danger">*</sup>
												</label> <input type="text" class="form-control"
													name="schoolCollegeName" placeholder="School/College Name"
													value="${studentDetails.schoolCollegeName}"
													<c:if test="${mode eq 'view'}">readonly</c:if> />
											</div>
										</div>
									</div>
								</div>
								<c:if test="${not empty studentDetails}">
									<div class="card-body">
										<div class="row">
											<!-- unit type -->
											<div class="col-md-6">
												<div class="form-group">
													<label> <liferay-ui:message key="unit-type" /> <sup
														class="text-danger">*</sup>
													</label> <select name="unitType" class="form-control"
													<c:if test="${not empty studentDetails.unitType}">readonly</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<c:forEach var="unitReg" items="${unitReg}">
															<option value="${unitReg.unitType}"
																<c:if test="${unitReg.unitType eq studentDetails.unitType}">selected</c:if>>
																${unitReg.unitType}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
								</c:if>

							</div>

						</div>


						<div class="card-footer bg-transparent text-right p-4">
							<c:if test="${mode ne 'view'}">
								<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/student-list';">
                              		<liferay-ui:message key="cancel" />
                          		</button>
                          		<button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
							</c:if>
							<c:if test="${mode eq 'view'}">
								<button type="button" class="btn btn-primary"
									onclick="location.href='/group/guest/student-list';">
									<liferay-ui:message key="cancel" />
								</button>
							</c:if>


							

							<c:if
								test="${(empty studentDetails or (empty studentDetails.registerTo and not empty studentDetails.unitType and empty studentDetails.transferTo)
								or (empty studentDetails.registerTo and  studentDetails.transferTo eq 'Guide' and guideMaster)
								or (empty studentDetails.registerTo and  studentDetails.transferTo eq 'Scout' and scoutMaster)
								or (empty studentDetails.registerTo and  studentDetails.transferTo eq 'NCC' and commandingOfficers))
								and !isHOAdmin}">
								<input type="hidden" name="register" value="register" />
								<button type="button" class="btn btn-primary"
									onclick="saveStudentRegistration(event)">
									<liferay-ui:message key="register" />
								</button>
							</c:if>


							<c:if
								test="${not empty studentDetails and empty studentDetails.unitType and mode eq 'view'}">
								<button type="button" class="btn btn-primary"
									onclick="saveStudentRegistration(event)">
									<liferay-ui:message key="submit" />
								</button>
							</c:if>

							<c:if
								test="${not empty studentDetails.registerTo and not empty studentDetails.unitType and  empty studentDetails.transferTo}">
								<input type="hidden" name="transfer" value="transfer" />
								<button type="button" class="btn btn-primary"
									onclick="saveStudentRegistration(event)">
									<c:if test="${guideMaster || scoutMaster }">
									<liferay-ui:message key="transfer-to-commanding-officer" />
									</c:if>
									<c:if test="${commandingOfficer }">
									<liferay-ui:message key="transfer-to-scout-and-guide" />
									</c:if>
								</button>
							</c:if>

						</div>

					</form>

				</div>
			</div>
		</div>
	</div>
</div>
<!-- Student Save Result Modal -->
<div class="modal fade" id="saveStudentModal" tabindex="-1"
	role="dialog" aria-labelledby="saveStudentModalTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="saveStudentModalTitle">
					<liferay-ui:message key="student-registration" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<img src="<%=request.getContextPath()%>/images/check.png" alt=""
								width="50px" class="my-3">
							<p id="student-save-message" class="text-primary"></p>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer d-flex justify-content-end">
				<a href="/group/guest/student-list" type="button"
					id="closeGrievanceModal" class="btn btn-secondary maha-save-btn"
					data-bs-dismiss="modal" onclick="closeModal('saveStudentModal')">
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
     $("#studentRegistrationForm").validate({
     	onkeyup: function (element) { $(element).valid(); },
         onchange: function (element) { $(element).valid(); },
         rules: {
             firstName: {
                 required: true,
                 minlength: 2
             },
             lastName: {
                 required: true,
                 minlength: 2
             },
             motherName: {
                 required: true,
                 minlength: 2
             },
             fatherName: {
                 required: true,
                 minlength: 2
             },
             gender: {
                 required: true
             },
             aadharNumber: {
                 required: true,
                 aadhaarValidation: true                },
             standard: {
                 required: true,
                 digits: true
             },
             schoolCollegeName: {
                 required: true
             },
             unitType: {
                 required: {
                     depends: function () {
                         return $("select[name='unitType']").is(":visible");
                     }
                 }
             }
         },
         messages: {
             firstName: {
                 required: "<liferay-ui:message key='please-enter-first-name' />",
                 minlength: "<liferay-ui:message key='first-name-minlength' />"
             },
             lastName: {
                 required: "<liferay-ui:message key='please-enter-last-name' />",
                 minlength: "<liferay-ui:message key='last-name-minlength' />"
             },
             motherName: {
                 required: "<liferay-ui:message key='please-enter-mother-name' />",
                 minlength: "<liferay-ui:message key='mother-name-minlength' />"
             },
             fatherName: {
                 required: "<liferay-ui:message key='please-enter-father-name' />",
                 minlength: "<liferay-ui:message key='father-name-minlength' />"
             },
             gender: {
                 required: "<liferay-ui:message key='please-select-gender' />"
             },
             aadharNumber: {
                 required: "<liferay-ui:message key='please-enter-aadhaar-number' />",
                 aadhaarValidation: "<liferay-ui:message key='enter-valid-aadhaar-number' />"
             },
             standard: {
                 required: "<liferay-ui:message key='please-enter-standard' />",
                 digit: "<liferay-ui:message key='enter-only-integer-value' />"
             },
             schoolCollegeName: {
                 required: "<liferay-ui:message key='please-enter-school-college-name' />"
             },
             unitType: {
                 required: "<liferay-ui:message key='please-select-unit-type' />"
             }
         },
         errorElement: "div",
         errorClass: "text-danger",
         highlight: function (element) {
             $(element).closest(".form-group").addClass("has-error");
         },
         unhighlight: function (element) {
             $(element).closest(".form-group").removeClass("has-error");
         },
         errorPlacement: function (error, element) {
             if (element.attr("name") === "gender") {
                 $(".gender-errors").after(error);
             } else {
                 error.insertAfter(element);
             }
         }
     });
        function addValidationMethods() {
            if(!findProperty($.validator.methods, 'aadhaarValidation')){
        		$.validator.addMethod("aadhaarValidation", function(value, element) {
        			const regex = /^[0-9]{4}[ -]?[0-9]{4}[ -]?[0-9]{4}$/;
        			if (!regex.test(value)) {
        	            return false;
        	        }
        		    return this.optional(element) || regex.test(value);
        		});
        	}
        }
    });
    function saveStudentRegistration(event) {
        debugger;

        var form = $("#studentRegistrationForm")[0];
        var formData = new FormData(form);

        if (event) {
            event.preventDefault(); // Prevent default submission
        }

        if ($('#studentRegistrationForm').valid()) {
            $.ajax({
                type: "POST",
                url: '${saveStudentRegistrationURL}', // Define this URL in your JSP using Liferay tag
                data: formData,
                contentType: false,
                cache: false,
                processData: false,
                success: function (data) {
                    console.log("Raw response: ", data);

                    if (typeof data === 'string') {
                        try {
                            data = JSON.parse(data);
                        } catch (e) {
                            console.error("JSON parse error: ", e);
                            return;
                        }
                    }

                    console.log("Parsed response: ", data);
                    var isTransfer = $("input[name='transfer']").val() === "transfer";

                    if (data.createStudent === true) {
                    	if (isTransfer) {
                            $('#student-save-message').text("<liferay-ui:message key='student-transfer-success' />");
                        } else{
                        $('#student-save-message').html("<liferay-ui:message key='details-are-submitted-successfully' />");
                        }
                    } else {
                        $('#student-save-message').html("<liferay-ui:message key='failed-to-submit-form' />");
                    }

                    $('#saveStudentModal').modal('show');
                },
                error: function (xhr, status, error) {
                    console.error("AJAX Error: ", error);
                }
            });
        }
    }
    function closeModal(id) {
		debugger
		$("#" + id).modal('hide');
		$(".modal-backdrop").remove();
		$("body").removeClass("modal-open");
	}
</script>
