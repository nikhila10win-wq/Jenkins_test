<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.SAVE_NCC_GRANT_MVC_RESOURCE_COMMAND%>"
var="saveGrantURL" />
<form id="nccGrantForm" method="POST" enctype="multipart/form-data">
	<div class="common-forms-div">
		<div class="container my-5 newTabs">
			<div class="row">
			   <div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="add-grants" />
						</h5>
						<div>
							<portlet:renderURL var="grantBackURL">
								<portlet:param name="mvcRenderCommandName"
									value="<%=MhdsysAdministrativeWebPortletKeys.NCC_GRANT_MVC_RENDER_COMMAND%>" />
							</portlet:renderURL>
							<%-- <c:if test="${mode ne 'view' }">
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
								href="/group/guest/grant-list"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
							</c:if> --%>
								<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
									href="/group/guest/grant-list"
									style="background-color: #26268E; border-color: #fff;"> <i
									class="bi bi-arrow-left-circle"></i> <liferay-ui:message
										key="back" />
								</a>
 						</div>
					</div>

					<div class="card-body">
						<ul class="justify-content-around nav nav-pills mb-4 p-2 shadow-sm"
							id="grantTab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active border-0" id="unitDetails-tab"
									type="button" role="tab" data-bs-toggle="tab"
									data-bs-target="#unitDetails">
									<liferay-ui:message key="unit-details" />
								</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link border-0" id="grantsDetails-tab"
									type="button" role="tab" data-bs-toggle="tab"
									data-bs-target="#grantsDetails">
									<liferay-ui:message key="grants-details" />
								</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link border-0" id="remarkDetails-tab"
									type="button" role="tab" data-bs-toggle="tab"
									data-bs-target="#remarkDetails">
									<liferay-ui:message key="remark-details" />
								</button>
							</li>
						</ul>
						<input type="hidden" class="form-control" name="nccGrantId" value="${nccGrant.nccGrantId}" />
						<div class="tab-content" id="grantTabContent">
							<!-- Unit Details -->
							<div class="tab-pane fade show active" id="unitDetails"
								role="tabpanel" aria-labelledby="unitDetails-tab">
								<div class="card card-background p-0">
									<div class="card-header header-card">
										<liferay-ui:message key="unit-details" />
									</div>
										<div class="card-body">
											<div class="row">
												<!-- Financial Year -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="financial-year" /><sup
														class="text-danger">*</sup></label> <select class="form-control"
														name="financialYear"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="2022-2023"
															${nccGrant.financialYear == '2022-2023' ? 'selected' : ''}>2022-2023</option>
														<option value="2023-2024"
															${nccGrant.financialYear == '2023-2024' ? 'selected' : ''}>2023-2024</option>
														<option value="2024-2025"
															${nccGrant.financialYear == '2024-2025' ? 'selected' : ''}>2024-2025</option>
													</select>
												</div>
	
												<!-- Department -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="department" /><sup
														class="text-danger">*</sup></label> <select class="form-control"
														name="department"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="department1"
															${nccGrant.department == 'department1' ? 'selected' : ''}>Department
															1</option>
														<option value="department2"
															${nccGrant.department == 'department2' ? 'selected' : ''}>Department
															2</option>
													</select>
												</div>
	
												<!-- Group Name -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="group-name" /><sup
														class="text-danger">*</sup></label> <select class="form-control"
														name="groupName"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="group1"
															${nccGrant.groupName == 'group1' ? 'selected' : ''}>Group
															1</option>
														<option value="group2"
															${nccGrant.groupName == 'group2' ? 'selected' : ''}>Group
															2</option>
													</select>
												</div>
	
												<!-- Unit Type -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="unit-type" /><sup
														class="text-danger">*</sup></label> <select class="form-control"
														name="unitType"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<c:forEach var="unitReg" items="${unitReg}">
															<option value="${unitReg.unitType}"
																${unitReg.unitType == nccGrant.unitType ? 'selected' : ''}>${unitReg.unitType}</option>
														</c:forEach>
													</select>
												</div>
	
												<!-- Commanding Officer -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="commanding-officer" /><sup
														class="text-danger">*</sup></label> <select class="form-control"
														name="commandingOfficer"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="officer1"
															${nccGrant.commandingOfficer == 'officer1' ? 'selected' : ''}>Officer
															1</option>
														<option value="officer2"
															${nccGrant.commandingOfficer == 'officer2' ? 'selected' : ''}>Officer
															2</option>
													</select>
												</div>
	
												<!-- Demanded Number -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="demanded-number" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="demandedNumber"
														value="${nccGrant.demandedNumber}"
														<c:if test="${mode eq 'view'}">disabled</c:if> />
												</div>
	
												<!-- Scheme Number -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="scheme-number" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="schemeNumber"
														value="${nccGrant.schemeNumber}"
														<c:if test="${mode eq 'view'}">disabled</c:if> />
												</div>
	
												<!-- Detail Head -->
												<div class="col-md-6 form-group">
													<label><liferay-ui:message key="detail-head" /></label> <input type="text"
														class="form-control" name="detailHead"
														value="${nccGrant.detailHead}"
														<c:if test="${mode eq 'view'}">disabled</c:if> />
												</div>
	
												<!-- Utilization Certificate Upload -->
												<div class="col-md-6">
													<div class="form-group">
														<label><liferay-ui:message
																key="utilization-certificate-upload" /> <sup
															class="text-danger">*</sup> <em
															class="bi bi-info-circle-fill"
															title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
														</label>
	
														<div class="custom-file">
															<input type="file" class="custom-file-input"
																id="utilizationCertificate" name="utilizationCertificate"
																accept="application/pdf"
																onchange="handleFileUpload(event, 'utilizationCertificate', 'utilizationCertificatePreviewContainer', 'utilizationCertificatePreviewLink', 'utilizationCertificateDeleteButton')"
																<c:if test="${mode eq 'view'}">disabled</c:if>> <label
																class="custom-file-label" for="utilizationCertificate"><liferay-ui:message
																	key="choose-file" /></label>
														</div>
	
														<!-- File Preview -->
														<div
															class="ownerProofid mt-3 ${empty nccGrant.utilizationCertificateURL ? 'd-none' : 'd-flex'}"
															id="utilizationCertificatePreviewContainer">
															<a class="utilizationCertificateProofCls"
																id="utilizationCertificatePreviewLink"
																href="${empty nccGrant.utilizationCertificateURL ? '' : nccGrant.utilizationCertificateURL}"
																target="_blank"
																style="flex-grow: 1; text-decoration: none;"> ${empty nccGrant.utilizationCertificateName ? '' : nccGrant.utilizationCertificateName}
															</a>
	
															<c:if test="${mode ne 'view'}">
																<button type="button"
																	id="utilizationCertificateDeleteButton"
																	class="dltUtilizationCertificateBtn close"
																	aria-label="Close"
																	onclick="deleteFile('utilizationCertificatePreviewContainer', 'utilizationCertificate')">
																	<span aria-hidden="true" class="text-danger"> <em
																		class="bi bi-x-circle-fill"></em>
																	</span>
																</button>
															</c:if>
														</div>
													</div>
												</div>
											</div>
	
	
										</div>
									</div>
									<div class="card-footer bg-transparent text-right p-4">
										
										<c:if test="${mode ne 'view'}">
											<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/scout-guide-ncc';">
		                                		<liferay-ui:message key="cancel" />
		                            		</button>	
											<button class="btn btn-primary" type="button"
												onclick="location.reload();">
												<liferay-ui:message key="reset" />
											</button>
											<button type="button" class="btn btn-primary"
												onclick="saveGrantForm('Draft')">
												<liferay-ui:message key="save-as-draft" />
											</button>
											
											<button type="button" class="btn btn-primary" onclick="validateTabFields('grantsDetails')">
			                                    <liferay-ui:message key="next" />
			                                </button>
										</c:if>
									</div>
									
								</div>

							<%@ include file="/ncc/grant-details.jsp"%>
							<%@ include file="/ncc/other-details.jsp"%>
							<!-- tab-content -->
						</div>
						<!-- card-body -->
					</div>
                    
				</div>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- Modal popup for NCC Grant Save -->
<div class="modal fade" id="grantSaveModal" tabindex="-1" role="dialog" aria-labelledby="grantModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content modal-bg">
            <div class="modal-header justify-content-center align-items-center">
                <h5 class="modal-title" id="grantModalTitle">
                    <liferay-ui:message key="ncc-grant-submission" />
                </h5>
                <button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div>
                            <%-- Optional success icon/image can go here --%>
                            <p id="successGrantSave"></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer d-flex justify-content-end">
                <a href="/group/guest/grant-list" type="button" class="btn btn-secondary maha-save-btn"
                   data-bs-dismiss="modal" onclick="closeModal('grantSaveModal')">
                    <liferay-ui:message key="close" />
                </a>
            </div>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
	debugger;
	let reloadCount = parseInt(localStorage.getItem('reloadCount')) || 0;
    console.log("reloadCount "+reloadCount);
    // Check if we should reload (count is 0)
    if (reloadCount === 0) {
        // Increment the counter
        console.log("increment ");
        localStorage.setItem('reloadCount', 1);
        // Perform the reload
        window.location.reload();
    } else {
     console.log("----------------------");
        // Reset counter for future visits
        localStorage.setItem('reloadCount', 0);
    }
		$("#nccGrantForm").validate({
			rules: {
			    // === General Fields ===
			    financialYear: { required: true },
			    department: { required: true },
			    groupName: { required: true },
			    unitType: { required: true },
			    commandingOfficer: { required: true },
			    demandedNumber: { required: true, number: true },
			    schemeNumber: { required: true, number: true },
			    utilizationCertificate: { required: true, extension: "pdf" },

			    // === Grants Details Fields ===
			    grantsReceived: { required: true,  pattern: /^[1-9]\d*$/ },
			    grantSurrenderReapp: { required: true, pattern: /^[1-9]\d*$/ },
			    grantsWithdrawn: { required: true },
			    grantsWithdrawnBy: { required: true },
			    grantsSurrender: { required: true },
			    grantsSurrenderTo: { required: true },
			    grantsAllowed: { required: true, number: true },
			    grantRecievedReapp: { required: true, number: true },
			    balance: { required: true,  pattern: /^\d*\.?\d+$/ },
			    expenses: { required: true,  pattern: /^\d*\.?\d+$/ },
			    actualExpense: { required: true,  pattern: /^\d*\.?\d+$/ },
			    balanceWithDdo: { required: true,  pattern: /^\d*\.?\d+$/ }
			},

			messages: {
				// === General Field Messages ===
				financialYear: { required: "<liferay-ui:message key='please-select-financial-year' />" },
				department: { required: "<liferay-ui:message key='please-select-department' />" },
				groupName: { required: "<liferay-ui:message key='please-select-group-name' />" },
				unitType: { required: "<liferay-ui:message key='please-select-unit-type' />" },
				commandingOfficer: { required: "<liferay-ui:message key='please-select-commanding-officer' />" },
				demandedNumber: {
					required: "<liferay-ui:message key='please-enter-demanded-number' />",
					digits: "<liferay-ui:message key='please-enter-valid-number' />"
				},
				schemeNumber: {
					required: "<liferay-ui:message key='please-enter-scheme-number' />",
					digits: "<liferay-ui:message key='please-enter-valid-number' />"
				},
				utilizationCertificate: {
					required: "<liferay-ui:message key='please-upload-utilization-certificate' />",
					extension: "<liferay-ui:message key='only-pdf-files-are-allowed' />"
				},

				// === Grants Details Messages ===
				grantsReceived: {
					required: "<liferay-ui:message key='please-enter-grants-received' />",
					pattern: "<liferay-ui:message key='please-enter-valid-number' />"
				},
				grantSurrenderReapp: {
					required: "<liferay-ui:message key='please-enter-surrender-reapp' />",
					pattern: "<liferay-ui:message key='please-enter-valid-number' />"
				},
				grantsWithdrawn: { required: "<liferay-ui:message key='please-enter-grants-withdrawn' />" },
				grantsWithdrawnBy: { required: "<liferay-ui:message key='please-enter-grants-withdrawn-by' />" },
				grantsSurrender: { required: "<liferay-ui:message key='please-enter-grants-surrender' />" },
				grantsSurrenderTo: { required: "<liferay-ui:message key='please-enter-grants-surrender-to' />" },
				grantsAllowed: {
					required: "<liferay-ui:message key='please-enter-grants-allowed' />",
					number: "<liferay-ui:message key='please-enter-valid-number' />"
				},
				grantRecievedReapp: {
					required: "<liferay-ui:message key='please-enter-grant-received-reapp' />",
					number: "<liferay-ui:message key='please-enter-valid-number' />"
				},
				balance: {
					required: "<liferay-ui:message key='please-enter-balance' />",
					pattern: "<liferay-ui:message key='please-enter-valid-decimal' />"
				},
				expenses: {
					required: "<liferay-ui:message key='please-enter-expenses' />",
					pattern: "<liferay-ui:message key='please-enter-valid-decimal' />"
				},
				actualExpense: {
					required: "<liferay-ui:message key='please-enter-actual-expense' />",
					pattern: "<liferay-ui:message key='please-enter-valid-decimal' />"
				},
				balanceWithDdo: {
					required: "<liferay-ui:message key='please-enter-balance-with-ddo' />",
					pattern: "<liferay-ui:message key='please-enter-valid-decimal' />"
				}
			},
			errorElement: 'div',
			errorPlacement: function (error, element) {
				error.addClass('text-danger mt-1');
				if (element.closest('.form-group').length) {
					error.appendTo(element.closest('.form-group'));
				} else {
					error.insertAfter(element);
				}
			}
		});

	var mode = '${mode}';
	if(mode=='view'){
		$('.nav-link').click(function(e) {
			e.preventDefault(); // prevent default anchor behavior
	
			var targetTabId = $(this).attr('id'); // e.g. unitDetails-tab
			console.log("Tab clicked:", targetTabId);
	
			// Remove existing active states
			$('.nav-link').removeClass('active');
			$('.tab-pane').removeClass('show active');
	
			// Activate clicked tab
			$(this).addClass('active');
			$('#' + targetTabId.replace('-tab', '')).addClass('show active');
	
			if (typeof scrollToTop === 'function') {
				scrollToTop(); // optional: define this function if needed
			}
		});
	}else {
	    $('.nav-link').click(function(e) {debugger
	        var targetTabId = $(this).attr('id');
	        console.log(targetTabId);

	        // Perform validation based on tab clicked
	        switch (targetTabId) {
	            case 'grantsDetails-tab':
	                if (!validateTabFields('grantsDetails') || !validateTabFields('grantsDetails')) {
	                    e.preventDefault();
	                    return false;
	                }
	                break;
	            case 'remarkDetails-tab':
	                if (!validateTabFields('remarkDetails')) {
	                    e.preventDefault();
	                    return false;
	                }
	                break;
	        }

	        // Activate the clicked tab
	        var targetPaneId = targetTabId.replace('-tab', '');
	        if ($('#' + targetPaneId).length) {
	            $('.nav-link').removeClass('active');
	            $('.tab-pane').removeClass('show active');

	            $(this).addClass('active');
	            $('#' + targetPaneId).addClass('show active');

	            if (typeof scrollToTop === "function") {
	                scrollToTop();
	            }
	        } else {
	            e.preventDefault();
	        }
	    });
	}
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
            //overflow: "hidden",
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
function saveGrantForm(status) { debugger
    event.preventDefault();

     if ($('#nccGrantForm').valid()) {
      var form = $('#nccGrantForm')[0];
        var formData = new FormData(form);
        formData.append("status", status);
        $.ajax({
            url: "${saveGrantURL}", // Replace with your actual Liferay action URL
            method: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) { debugger
                var data = typeof response === "string" ? JSON.parse(response) : response;

                if (data.grantSaved) {
                    $('#successGrantSave').html('<div>Grant saved successfully</div>');
                    $('#grantSaveModal').modal('show');
                } else {
                    $('#successGrantSave').html('<div >Grant save failed. Please try again.</div>');
                    $('#grantSaveModal').modal('show');
                }
            },
            error: function (xhr) {
                console.error("Something went wrong. Please try again.");
                $('#successGrantSave').html('<div >Something went wrong while saving.</div>');
                $('#grantSaveModal').modal('show');
            }
        });
     } 
}
function closeModal(id) {debugger
 	$("#"+id).modal('hide');
 	$(".modal-backdrop").remove();
 	$("body").removeClass("modal-open");
}
const tabValidationRules = {
	    // First tab validation fields
	    grantsDetails: [
	        "[name='financialYear']",
	        "[name='department']",
	        "[name='groupName']",
	        "[name='unitType']",
	        "[name='commandingOfficer']",
	        "[name='demandedNumber']",
	        "[name='schemeNumber']",
	        "#utilizationCertificate" // file input with id
	    ],

	    // Second tab validation fields
	    remarkDetails: [
	        "[name='grantsReceived']",
	        "[name='grantSurrenderReapp']",
	        "[name='grantsWithdrawn']",
	        "[name='grantsWithdrawnBy']",
	        "[name='grantsSurrender']",
	        "[name='grantsSurrenderTo']",
	        "[name='grantsAllowed']",
	        "[name='grantRecievedReapp']",
	        "[name='balance']",
	        "[name='expenses']",
	        "[name='actualExpense']",
	        "[name='balanceWithDdo']"
	    ]
	};
function validateTabFields(tabId) {
    $('#nccGrantForm').validate().resetForm();
    let isValid = true;

    const fields = tabValidationRules[tabId] || [];

    fields.forEach((selector) => {
        if (!$(selector).valid()) {
            isValid = false;
        }
    });

    return handleValidationResult(isValid, tabId);
}
function handleValidationResult(isValid, nextTabId) {
    if (isValid) {
        setActiveTab(nextTabId);
        return true;
    } else {
        $('html, body').animate({
            scrollTop: $(".error:visible").first().offset().top - 100
        }, 500);
        return false;
    }
}
function setActiveTab(tabId) {
    $('.nav-link').removeClass('active');
    $('.tab-pane').removeClass('show active');

    $('#' + tabId + '-tab').addClass('active');
    $('#' + tabId).addClass('show active');

    if (typeof scrollToTop === "function") {
        scrollToTop();
    }
}

</script>