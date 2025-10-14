<%@page import="com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysGrievanceComplaintWebPortletKeys.GET_DISTRICTS%>"
var="getDistricts" />

<portlet:resourceURL id="<%=MhdsysGrievanceComplaintWebPortletKeys.SAVE_GRIEVANCE_MVC_RESOURCE_COMMANDS%>"
var="createGrievanceURL" />
<portlet:resourceURL id="<%=MhdsysGrievanceComplaintWebPortletKeys.GET_TALUKAS%>"
var="getTalukas" />
<style>
    /* Force checkbox label to remain black regardless of validation */
    .form-check-label.text-dark {
        color: #212529 !important; /* Bootstrap's default text-dark */
        font-weight: 700;
    }
</style>
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                  <!--   <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="grievance-complaint-form" /></h5>
                        <span class="text-danger font-weight-bold">
                            *<liferay-ui:message key="indicates-mandatory-fields" />
                        </span>
                    </div> -->
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"><liferay-ui:message key="grievance-complaint-form" /></h5>
						<span class="text-danger font-weight-bold d-none">*<liferay-ui:message key="indicates-mandatory-fields" />
                        </span>
						<div>
						<c:if test="${isPublished eq 'Published'}">
							<a href="/group/guest/grievance-published-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn" style="background-color: #26268E;
	   				 		border-color: #fff;"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a>
	   				 	</c:if>
						<c:if test="${isPublished ne 'Published'  and !isGuestUser}">
							<a href="/group/guest/grievance-dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn" style="background-color: #26268E;
	   				 		border-color: #fff;"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a>
	   				 	</c:if>
						<c:if test="${isPublished ne 'Published' and isGuestUser}">
							<a href="/home" class="btn btn-primary btn-sm rounded-pill back-btn-cn" style="background-color: #26268E;
	   				 		border-color: #fff;"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a>
	   				 	</c:if>
						</div>
					</div>
                    <form id="grievance_form" method="POST">
						<div class="card-body">

							<!-- Administration Section -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="administration" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label><liferay-ui:message key="division" /><sup
													class="text-danger">*</sup></label> <select class="form-control"
													name="division" id="division"
													<c:if test="${mode eq 'view'}">disabled</c:if>>
													<option value="">Select</option>
													<c:forEach var="division" items="${divisions}">
														<option value="${division.divisionId}"
															<c:if test="${division.divisionId == grievance.division}">selected</c:if>>
															${division.divisionName_en}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><liferay-ui:message key="district" /><sup
													class="text-danger">*</sup></label> <select class="form-control"
													name="district" id="district"
													<c:if test="${mode eq 'view'}">disabled</c:if>>
													<option value="">Select</option>
												</select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label><liferay-ui:message key="taluka" /><sup
													class="text-danger">*</sup></label> <select class="form-control"
													name="taluka" id="taluka"
													<c:if test="${mode eq 'view'}">disabled</c:if>>
													<option value="">Select</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Complaint Details Section -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="complaint-details" />
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="date" /> <sup
													class="text-danger">*</sup></label> <input type="date"
													class="form-control" id="complaintDate"
													name="complaintDate" value="${grievance.complaintDateStr}"
													<c:if test="${mode eq 'view'}">disabled</c:if> />
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="type-of-complaint" />
													<sup class="text-danger">*</sup></label> <select
													class="form-control" id="complaintType"
													name="complaintType"
													<c:if test="${mode eq 'view'}">disabled</c:if>>
													<option value="">Select</option>
													<option value="Grievance"
														<c:if test="${grievance.complaintType eq 'Grievance'}">selected</c:if>>Grievance</option>
													<option value="Complaints"
														<c:if test="${grievance.complaintType eq 'Complaints'}">selected</c:if>>Complaints</option>

												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label><liferay-ui:message key="complaint" /> <sup
													class="text-danger">*</sup></label>
												<textarea class="form-control" id="complaintText" name="complaintText" 
												<c:if test="${mode eq 'view'}">disabled</c:if>>${grievance.complaintText}</textarea>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- Self-Declaration Section -->
							<div class="card card-background p-0 mb-4">
								<div class="card-header header-card">
									<liferay-ui:message key="complaint-details" />
								</div>
								<div class="card-body">
									<div class="form-check">
										<input type="checkbox" class="form-check-input"
											id="selfDeclaration" name="selfDeclaration"
											<c:if test="${grievance.selfDeclaration}">checked disabled</c:if> />
										<label class="form-check-label fw-bold text-dark" for="selfDeclaration">
											<liferay-ui:message key="self-declaration" /><sup
													class="text-danger">*</sup>
										</label>
									</div>
								</div>
							</div>
							<c:if test="${mode eq 'view'}">
								<div class="card card-background p-0 mb-4">
									<div class="card-header header-card">
										<liferay-ui:message key="review-details" />
									</div>
									<div class="card-body">
										<div class="row">
											<div class="col-md-4">
												<strong><liferay-ui:message key="review-by-ho" />:</strong>
												<p><c:out value="${empty transferApplication.reviewByHo ? 'N/A' : transferApplication.reviewByHo}" /></p>

												<strong><liferay-ui:message key="solution-by-ho" />:</strong>
												<p><c:out value="${empty transferApplication.solutionByHo ? 'N/A' : transferApplication.solutionByHo}" /></p>
											</div>

											<div class="col-md-4">
												<strong><liferay-ui:message key="review-by-dso" />:</strong>
												<p><c:out value="${empty transferApplication.reviewByDSO ? 'N/A' : transferApplication.reviewByDSO}" /></p>

												<strong><liferay-ui:message key="solution-by-dso" />:</strong>
												<p><c:out value="${empty transferApplication.solutionByDSO ? 'N/A' : transferApplication.solutionByDSO}" /></p>
											</div>

											<div class="col-md-4">
												<strong><liferay-ui:message key="review-by-tso" />:</strong>
												<p><c:out value="${empty transferApplication.reviewByTSO ? 'N/A' : transferApplication.reviewByTSO}" /></p>

												<strong><liferay-ui:message key="solution-by-tso" />:</strong>
												<p><c:out value="${empty transferApplication.solutionByTSO ? 'N/A' : transferApplication.solutionByTSO}" /></p>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</div>
						<div class="card-footer bg-transparent text-right p-4">
							<c:if test="${mode ne 'view'}">
								<button type="button" class="btn btn-primary"
									onclick="location.href='/group/guest/grievance-dashboard';">
									<liferay-ui:message key="cancel" />
								</button>
								<button class="btn btn-primary" type="button"
									onclick="location.reload();">
									<liferay-ui:message key="reset" />
								</button>
								<button type="submit" class="btn btn-primary"
									onclick="createGrievance(event)">
									<liferay-ui:message key="submit" />
								</button>
							</c:if>
							<!-- <button type="button" class="btn btn-secondary" onclick="location.href='/group/guest/grievance-dashboard';">
                                <liferay-ui:message key="cancel" />
                            </button> -->
						</div>
					</form>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Popup for Grievance Save Success -->
<div class="modal fade" id="saveGrievanceModal" tabindex="-1" role="dialog" aria-labelledby="grievanceModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="grievanceModalTitle">
					<liferay-ui:message key="grievance-save-status" />
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
							<p id="grievance-save-message" ></p>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer d-flex justify-content-end">
				<c:if test="${!isGuestUser}">
					<a href="/group/guest/grievance-dashboard" type="button" id="closeGrievanceModal"
						class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveGrievanceModal')">
						<liferay-ui:message key="close" />
					</a>
				</c:if>
				<c:if test="${isGuestUser}">
					<a href="/home" type="button" id="closeGrievanceModal"
						class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveGrievanceModal')">
						<liferay-ui:message key="close" />
					</a>
				</c:if>
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
	jQuery.noConflict();
    $('#grievance_form').validate({
    	 onkeyup: function (element) {
 		    $(element).valid();
 		    },
 		  onchange: function (element) {
 		       $(element).valid();
 		    },
 		    
        rules: {
            division: {
                required: true
            },
            district: {
                required: true
            },
            taluka: {
                required: true
            },
            complaintDate: {
                required: true,
                date: true,
                validatePastOrTodayDate:true
            },
            complaintType: {
                required: true
            },
            complaintText: {
                required: true,
                minlength: 2,
                maxlength: 5000,
                startEndSpace:true,
                singleSpaceBetweenWords:true,
                customNamePattern:true,
                //validateRemarks:true
            },
            selfDeclaration: {
                required: true
            }
        },
        messages: {
            division: {
                required: '<liferay-ui:message key="select-division" />'
            },
            district: {
                required: '<liferay-ui:message key="select-district" />'
            },
            taluka: {
                required: '<liferay-ui:message key="select-taluka" />'
            },
            complaintDate: {
                required: '<liferay-ui:message key="enter-complaint-date" />',
                date: '<liferay-ui:message key="enter-valid-date" />',
            },
            complaintType: {
                required: '<liferay-ui:message key="select-complaint-type" />'
            },
            complaintText: {
                required: '<liferay-ui:message key="enter-complaint-details" />',
                minlength: '<liferay-ui:message key="minimum-length-is-2" />',
                maxlength: '<liferay-ui:message key="maximum-length-is-5000" />',
            },
            selfDeclaration: {
                required: '<liferay-ui:message key="accept-self-declaration" />'
            }
        },

        errorElement: 'div',
        errorPlacement: function (error, element) {
            error.addClass('text-danger');

            if (element.attr('name') === 'selfDeclaration') {
                // Show error below the label
                error.insertAfter(element.closest('.form-check').find('label'));
            } else {
                error.insertAfter(element);
            }
        },

        highlight: function (element) {
            $(element).addClass('is-invalid');
            // Do NOT make the label red
        },

        unhighlight: function (element) {
            $(element).removeClass('is-invalid');
            // No need to remove red label class
        }
    });
    
	if (!findProperty($.validator.methods, 'startEndSpace')) {
	    $.validator.addMethod("startEndSpace", function(value) {
	        return value === value.trim();
	    }, '<liferay-ui:message key="no-leading-and-trailing-space-allowed"/>');
	}
	
	// Only one space between words
	if (!findProperty($.validator.methods, 'singleSpaceBetweenWords')) {
	    $.validator.addMethod("singleSpaceBetweenWords", function(value) {
	        return !/ {2,}/.test(value);
	    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');
	}
	

	// Custom name validation: alphabets, numbers, space, hyphen, slash, dot
	if (!findProperty($.validator.methods, 'customNamePattern')) {
	    $.validator.addMethod("customNamePattern", function(value) {
	        return /^[A-Za-z0-9\/.,\- ]+$/.test(value);
	    }, '<liferay-ui:message key="only-alphabets-numbers-single-spaces-hyphen-slash-dot-allowed"/>');
	}

    
/*     if (!findProperty($.validator.methods, 'validateRemarks')) {
	    $.validator.addMethod("validateRemarks", function(value, element) {
	 	   const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
	 	    return this.optional(element) || regex.test(value);
	 	},"<liferay-ui:message key='enter-valid-complaint' />");
    } */
    
    $.validator.addMethod("validatePastOrTodayDate", function(value, element) {
        if (!value) return true; // allow empty if optional

        const inputDate = new Date(value);
        const today = new Date();

        // Remove time portion from both dates for accurate comparison
        inputDate.setHours(0, 0, 0, 0);
        today.setHours(0, 0, 0, 0);

        return inputDate <= today;
    }, "<liferay-ui:message key='future-dates-are-not-allowed' />");

 var mode = '${mode}'
 console.log("mode:",mode)
 if(mode == 'view'){
	 getDistricts();
	 getTaluka();
 }
    
});

$('select[name="division"]').on('change', function () {
	getDistricts();
}); 
function getDistricts() {debugger
    const divisionId = $('#division').val();
    const selectedDistrictId = '${grievance.district}'; // from server-side
        $.ajax({
            url: '${getDistricts}',
            type: 'GET',
            data: {
                divisionId: divisionId
            },
            success: function (data) {debugger 
                const $districtSelect = $("#district");
                $districtSelect.empty();
                console.log("Data ::: ", data);

                let districts = typeof data === "string" ? JSON.parse(data) : data;

                if (districts && districts.length > 0) {
                    $districtSelect.append('<option value="">Select</option>');

                    $.each(districts, function (index, value) {
                        const selected = (value.districtId == selectedDistrictId) ? 'selected' : '';
                        $districtSelect.append('<option value="' + value.districtId + '" ' + selected + '>' + value.districtName + '</option>');
                    });
                } else {
                    $districtSelect.append('<option value="">No districts available</option>');
                }
            }
        });
}

 

$('select[name="district"]').on('change', function () {
	getTaluka();
});
function getTaluka() {debugger
	var mode = '${mode}'
    let districtId = $('#district').val();
    const selectedTalukaId = '${grievance.taluka}'; // server-side value
	if(mode == 'view'){
		districtId = '${grievance.district}';
	}
    if (districtId) {
        $.ajax({
            url: '${getTalukas}',
            type: 'GET',
            data: {
                districtId: districtId
            },
            success: function (data) {debugger
                const $talukaSelect = $("#taluka");
                $talukaSelect.empty();

                let talukas = typeof data === "string" ? JSON.parse(data) : data;

                if (talukas && talukas.length > 0) {
                    $talukaSelect.append('<option value="">Select</option>');

                    $.each(talukas, function (index, value) {
                        const selected = (value.talukaId == selectedTalukaId) ? 'selected' : '';
                        $talukaSelect.append('<option value="' + value.talukaId + '" ' + selected + '>' + value.talukaName + '</option>');
                    });
                } else {
                    $talukaSelect.append('<option value="">No talukas available</option>');
                }
            }
        });
    }
}

function createGrievance(event) {
    debugger;

    var form = $("#grievance_form")[0]; // Your form ID
    var formData = new FormData(form);

    if (event) {
        event.preventDefault(); // Prevents default form submission
    }

    if ($('#grievance_form').valid()) { // Assuming form uses jQuery Validation
        $.ajax({
            type: "POST",
            url: '${createGrievanceURL}', // Liferay URL from JSP
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success: function (data) {debugger
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

                if (data.createGrievance === true) {debugger
                    $('#grievance-save-message').html("<liferay-ui:message key='grievance-created-successfully' />");
                } else {
                    $('#grievance-save-message').html("<liferay-ui:message key='grievance-creation-failed' />");
                }
                $('#saveGrievanceModal').modal('show');

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