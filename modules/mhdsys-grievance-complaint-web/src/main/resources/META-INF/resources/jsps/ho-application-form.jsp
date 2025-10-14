<%@page import="com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysGrievanceComplaintWebPortletKeys.SAVE_HO_APPLICATION_MVC_RESOURCE_COMMAND%>"
var="transferApplicationSubmitURL" />
<div class="common-forms-div">
    <div class="container">
        <div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5>
							<c:if test="${isHOAdmin}">
								<liferay-ui:message key="transfer-of-application-from-ho" />
						</h5>
						</c:if>
						<c:if test="${isDSO}">
							<liferay-ui:message key="transfer-of-application-from-dso" />
							</h5>
						</c:if>
						<c:if test="${isTSO}">
							<liferay-ui:message key="transfer-of-application-from-tso" />
							</h5>
						</c:if>
						<span class="text-right text-white d-none"><liferay-ui:message
								key="indicates-mandatory-fields" /></span>
						<div>
							<c:if test="${!HoList}">

								<a href="/group/guest/grievance-list"
									class="btn btn-primary btn-sm rounded-pill back-btn-cn"
									style="background-color: #26268E; border-color: #fff;"><i
									class="bi bi-arrow-left-circle"></i>
								<liferay-ui:message key="back" /></a>
							</c:if>
							<c:if test="${HoList}">

								<a href="/group/guest/ho-application-list"
									class="btn btn-primary btn-sm rounded-pill back-btn-cn"
									style="background-color: #26268E; border-color: #fff;"><i
									class="bi bi-arrow-left-circle"></i>
								<liferay-ui:message key="back" /></a>
							</c:if>
						</div>
					</div>
					<form id="applicationTransferForm" method="POST">
						<div class="card-body">
							<input type="hidden" class="form-control" id=grievanceId
								name="grievanceId" value="${grievance.grievanceId}" /> <input
								type="hidden" class="form-control" id=transferApplicationId
								name="transferApplicationId" value="${transferApplicationId}" />
							<input type="hidden" class="form-control" id=isHOAdmin
								name="isHOAdmin" value="${isHOAdmin}" /> <input type="hidden"
								class="form-control" id=isDSO name="isDSO" value="${isDSO}" />
							<input type="hidden" class="form-control" id=isTSO name="isTSO"
								value="${isTSO}" />
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
													disabled/>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label><liferay-ui:message key="type-of-complaint" />
													<sup class="text-danger">*</sup></label> <select
													class="form-control" id="complaintType"
													name="complaintType"
													disabled>
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
												disabled>${grievance.complaintText}</textarea>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- Review Section -->
							<div class="card card-background p-0 mt-4">
								<div class="card-header header-card">
									<liferay-ui:message key="review" />
								</div>
								<div class="card-body">
									<div class="row">
										<c:if
											test="${(empty transferApplication.reviewByHo && isHOAdmin) or (empty transferApplication.reviewByDSO && isDSO)
									or (empty transferApplication.reviewByTSO && isTSO)}">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="solution" /> <sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="solution" name="solution"
														placeholder="Solution" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="review" /> <sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="reviewText" name="review"
														placeholder="Review" />
												</div>
											</div>
										</c:if>
										<c:if test="${not empty transferApplication.reviewByHo}">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="solution-by-ho" />
														<sup class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="solutionByHO" name="solutionByHO"
														value="${transferApplication.solutionByHo }"
														placeholder="Solution" disabled />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="review-by-ho" /> <sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="reviewTextByHO" name="reviewByHO"
														value="${transferApplication.reviewByHo }"
														placeholder="Review" disabled />
												</div>
											</div>
										</c:if>
										<c:if test="${not empty transferApplication.reviewByDSO}">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="solution-by-dso" />
														<sup class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="solutionByDSO" name="solutionByDSO"
														value="${transferApplication.solutionByDSO }"
														placeholder="Solution" disabled />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="review-by-dso" />
														<sup class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="reviewTextByDSO" name="reviewByDSO"
														value="${transferApplication.reviewByDSO }"
														placeholder="Review" disabled />
												</div>
											</div>
										</c:if>
										<c:if test="${not empty transferApplication.reviewByTSO}">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="solution-by-tso" />
														<sup class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="solutionByTSO" name="solutionByTSO"
														value="${transferApplication.solutionByTSO }"
														placeholder="Solution" disabled />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="review-by-tso" />
														<sup class="text-danger">*</sup></label> <input type="text"
														class="form-control" id="reviewTextByTSO" name="reviewByTSO"
														value="${transferApplication.reviewByTSO }"
														placeholder="Review" disabled />
												</div>
											</div>
										</c:if>
									</div>
								</div>
							</div>
							<c:if test="${transferApplication.transfer ne 'HO-Admin' }">
								<!-- Transfer Details -->
								<div class="card card-background p-0 mt-4">
									<div class="card-header header-card">
										<liferay-ui:message key="transfer-details" />
									</div>
									<%-- <div class="card-body">
									<div class="form-group">
										<label><liferay-ui:message
												key="transfer-application-to" /> <sup class="text-danger">*</sup></label>
										<div>
											<c:if test="${isDSO or isHOAdmin}">
												<div class="form-check form-check-inline">
													<input class="form-check-input" type="radio"
														name="transferTo" id="tso" value="0" 
														<c:if test="${transferApplication.transferApplication == 0}">checked</c:if><c:if test="${mode eq 'view'}">disabled</c:if>/> <label
														class="form-check-label" for="tso"><liferay-ui:message
															key="tso" /></label>
												</div>
											</c:if>
											<c:if test="${isHOAdmin}">
												<div class="form-check form-check-inline">
													<input class="form-check-input" type="radio"
														name="transferTo" id="dso" value="1"
														<c:if test="${transferApplication.transferApplication == 1}">checked</c:if>
														<c:if test="${mode eq 'view'}">disabled</c:if> /> <label
														class="form-check-label" for="dso"><liferay-ui:message
															key="dso" /></label>
												</div>
											</c:if>
											<c:if test="${isTSO or isDSO}">
												<div class="form-check form-check-inline">
													<input class="form-check-input" type="radio"
														name="transferTo" id="ho" value="2"
														<c:if test="${transferApplication.transferApplication == 2}">checked</c:if>
														<c:if test="${mode eq 'view'}">disabled</c:if> /> <label
														class="form-check-label" for="tso"><liferay-ui:message
															key="ho" /></label>
												</div>
											</c:if>
										</div>
									</div>
								</div> --%>
									<div class="card-body">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label> <liferay-ui:message
															key="transfer-application-to" /> <sup
														class="text-danger">*</sup>
													</label> <select class="form-control" name="transferTo"
														id="transferTo"
														<c:if test="${mode eq 'view' && not empty transferApplication.reviewByHo}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>

														<c:if test="${isDSO or isHOAdmin}">
															<option value="0"
																<c:if test="${transferApplication.transferApplication == 0}">selected</c:if>>
																<liferay-ui:message key="tso" />
															</option>
														</c:if>

														<c:if test="${isHOAdmin}">
															<option value="1"
																<c:if test="${transferApplication.transferApplication == 1}">selected</c:if>>
																<liferay-ui:message key="dso" />
															</option>
														</c:if>

														<c:if test="${isTSO or isDSO}">
															<option value="2"
																<c:if test="${transferApplication.transferApplication == 2}">selected</c:if>>
																<liferay-ui:message key="ho" />
															</option>
														</c:if>
													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</div>
						<!-- Footer -->
						<div class="card-footer bg-transparent text-right p-4">
							<c:if test="${(isHOAdmin && empty transferApplication.reviewByHo) or (isDSO && empty transferApplication.reviewByDSO) or (isTSO && empty transferApplication.reviewByTSO) }">
								<input type="hidden" class="form-control" id="status"
									name="status" value="Transfer" />
								<%-- <c:if test="${empty transferApplication.reviewByHo }">
									<button type="button" class="btn btn-primary"
										onclick="location.href='/group/guest/grievance-list';">
										<liferay-ui:message key="cancel" />
									</button>
								</c:if>
								<c:if test="${not empty transferApplication.reviewByHo }">
									<button type="button" class="btn btn-primary"
										onclick="location.href='/group/guest/ho-application-list';">
										<liferay-ui:message key="cancel" />
									</button>
								</c:if> --%>
								<button type="button" class="btn btn-primary"
										onclick="location.href='/group/guest/grievance-dashboard';">
										<liferay-ui:message key="cancel" />
								</button>
								<button class="btn btn-primary" type="button"
									onclick="location.reload();">
									<liferay-ui:message key="reset" />
								</button>
								<button type="button" class="btn btn-primary maha-save-btn"
									onclick="createTransferApplication(event)">
									<liferay-ui:message key="transfer" />
								</button>
							</c:if>
							<c:if test="${transferApplication.transfer eq 'HO-Admin' and grievance.status ne 'Publish'}">
								<input type="hidden" class="form-control" id="status"
									name="status" value="Publish" />
								<button type="button" class="btn btn-primary maha-save-btn"
									onclick="createTransferApplication(event)">
									<liferay-ui:message key="publish" />
								</button>
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
        </div>
    </div>
<!-- Modal Popup for Transfer of Application Save Status -->
<div class="modal fade" id="saveTransferModal" tabindex="-1" role="dialog" aria-labelledby="transferModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="transferModalTitle">
					<liferay-ui:message key="transfer-status" />
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
							<p id="transfer-save-message" ></p>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer d-flex justify-content-end">
				<c:if test="${empty transferApplication.reviewByHo }">
					<a href="/group/guest/grievance-list" type="button" id="closeTransferModal"
						class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveTransferModal')">
						<liferay-ui:message key="close" />
					</a>
				</c:if>
				<c:if test="${not empty transferApplication.reviewByHo }">
					<a href="/group/guest/ho-application-list" type="button" id="closeTransferModal"
						class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveTransferModal')">
						<liferay-ui:message key="close" />
					</a>
				</c:if>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
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
	var transfer = '${transferApplication.transfer}'
		$("#applicationTransferForm").validate({
			 onkeyup: function (element) {
			    $(element).valid();
			    },
			 onchange: function (element) {
			       $(element).valid();
			    },
				    
				rules : {
					solution : {
						required : true,
						startEndSpace:true,
			            singleSpaceBetweenWords:true,
			            customNamePattern:true,
						minlength: 2,
						maxlength: 250
					},
					review : {
						required : true,
						startEndSpace:true,
			            singleSpaceBetweenWords:true,
			            customNamePattern:true,
						minlength: 2,
						maxlength: 250
					},
					transferTo : {
						required: {
			                depends: function () {
			                    return transfer !== 'HO-Admin';
			                }
					}
				  }
				},
				messages : {
					solution : {
						required : '<liferay-ui:message key="enter-solution" />',
						minlength: '<liferay-ui:message key="minimum-length-is-2" />',
		                maxlength: '<liferay-ui:message key="please-enter-max-250-characters" />'
					},
					review : {
						required : '<liferay-ui:message key="enter-review" />',
						minlength: '<liferay-ui:message key="minimum-length-is-2" />',
		                maxlength: '<liferay-ui:message key="please-enter-max-250-characters" />'

					},
					transferTo : {
						required : '<liferay-ui:message key="select-transfer-destination" />'
					}
				},
				errorClass : "text-danger",
				errorPlacement : function(error,
						element) {
					if (element.attr("type") === "radio") {
						error.insertAfter(element.closest(".form-check-inline").parent());
					} else {
						error.insertAfter(element);
					}
				},
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

});

function createTransferApplication(event) {
		debugger;

var form = $("#applicationTransferForm")[0]; // Your form ID
var formData = new FormData(form);

if (event) {
	event.preventDefault(); // Prevent default form submission
}

if ($('#applicationTransferForm').valid()) { // Assuming form uses jQuery Validation
			$.ajax({
				type : "POST",
				url : '${transferApplicationSubmitURL}', // Liferay action URL from JSP
				data : formData,
				contentType : false,
				cache : false,
				processData : false,
				success : function(data) {
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

					if (data.createTransfer === true) {
						$('#transfer-save-message')
								.html(
										"<liferay-ui:message key='application-transferred-successfully' />");
					}else if (data.grievance === true) {
						$('#transfer-save-message')
						.html("<liferay-ui:message key='application-is-published' />");
					}
					else {
						$('#transfer-save-message')
								.html(
										"<liferay-ui:message key='application-transfer-failed' />");
					}

					$('#saveTransferModal').modal('show');
				},
				error : function(xhr, status, error) {
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

