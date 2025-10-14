<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp"%>
<portlet:resourceURL id="<%=MhdsysAdministrativeWebPortletKeys.SAVE_NCC_GRANT_REQUEST_MVC_RESOURCE_COMMAND%>"
var="saveGrantRequestURL" />
<form id="nccGrantRequestForm" method="POST" enctype="multipart/form-data">
	<div class="common-forms-div">
		<div class="container my-5 newTabs">
			<div class="row">
			  <div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="request-for-grants" />
						</h5>
							<portlet:renderURL var="grantRequestBackURL">
								<portlet:param name="mvcRenderCommandName"
									value="<%=MhdsysAdministrativeWebPortletKeys.NCC_GRANT_REQUEST_MVC_RENDER_COMMAND%>" />
							</portlet:renderURL>
							<portlet:renderURL var="grantRequestListBackURL">
								<portlet:param name="mvcRenderCommandName"
									value="<%=MhdsysAdministrativeWebPortletKeys.NCC_GRANT_REQUEST_LIST_MVC_RENDER_COMMAND%>" />
							</portlet:renderURL>
						
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
								href="${grantRequestListBackURL }"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
						</div>
					</div>
						<input type="hidden" class="form-control" name="nccGrantRequestId" value="${nccGrantRequestId}" />
					<div class="card-body">
						<ul class="justify-content-around nav nav-pills mb-4 p-2 shadow-sm"
							id="grantTab" role="tablist">
							<li class="nav-item " role="presentation">
								<button class="nav-link active border-0" id="unitDetails-tab"
									type="button" role="tab" data-bs-toggle="tab"
									data-bs-target="#unitDetails">
									<liferay-ui:message key="unit-details" />
								</button>
							</li>
							<li class="nav-item " role="presentation">
								<button class="nav-link border-0" id="salaryTab1-tab"
									type="button" role="tab" data-bs-toggle="tab"
									data-bs-target="#salaryTab1">
									<liferay-ui:message key="salary-and-allowance-i" />
								</button>
							</li>
							<li class="nav-item " role="presentation">
								<button class="nav-link border-0" id="salaryTab2-tab"
									type="button" role="tab" data-bs-toggle="tab"
									data-bs-target="#salaryTab2">
									<liferay-ui:message key="salary-and-allowance-ii" />
								</button>
							</li>
						</ul>

						<div class="tab-content" id="grantTabContent">
							<!-- Tab 1: Unit Details -->
							<div class="tab-pane fade show active" id="unitDetails"
								role="tabpanel" aria-labelledby="unitDetails-tab">
								<div class="card card-background p-0">
									<div class="card-header header-card">
										<liferay-ui:message key="unit-details" />
									</div>
									<div class="card-body">
										<div class="row">
											<!-- Headquarter Name -->
											<div class="col-md-6 form-group">
												<label> <liferay-ui:message key="headquarter-name" />
													<sup class="text-danger">*</sup>
												</label> <select class="form-control" name="headquarterName"
													<c:if test="${mode eq 'view'}">disabled</c:if>>
													<option value=""><liferay-ui:message key="select" /></option>
													<option value="headquarter1"
														<c:if test="${nccGrant.headquarterName == 'headquarter1'}">selected</c:if>>
														headquarter-1</option>
													<option value="headquarter2"
														<c:if test="${nccGrant.headquarterName == 'headquarter2'}">selected</c:if>>
														headquarter-2</option>
												</select>
											</div>

											<!-- Unit Type -->
											<div class="col-md-6 form-group">
												<label> <liferay-ui:message key="unit-type" /> <sup
													class="text-danger">*</sup>
												</label> <select class="form-control" name="unitType"
													<c:if test="${mode eq 'view'}">disabled</c:if>>
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="unitReg" items="${unitReg}">
														<option value="${unitReg.unitType}"
															<c:if test="${unitReg.unitType == nccGrant.unitType}">selected</c:if>>
															${unitReg.unitType}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>
								</div>
								<!-- Submit Buttons -->
								<div class="card-footer bg-transparent text-right p-4">
									<c:if test="${mode ne 'view' }">
										<button type="button" class="btn btn-primary"
											onclick="location.href='/group/guest/scout-guide-ncc';">
											<liferay-ui:message key="cancel" />
										</button>
									</c:if>
									<%-- <c:if test="${mode eq 'view' }">
										<a href="${grantRequestBackURL}" type="button"
											class="btn btn-primary">
											<liferay-ui:message key="cancel" />
										</a>
									</c:if> --%>
									<c:if test="${mode ne 'view'}">
										<button class="btn btn-primary" type="button"
											onclick="location.reload();">
											<liferay-ui:message key="reset" />
										</button>
										<button type="button" class="btn btn-primary"
											onclick="saveGrantRequestForm('Draft')">
											<liferay-ui:message key="save-as-draft" />
										</button>
										<button type="button" class="btn btn-primary" onclick="validateTabFields('salaryTab1')">
							                <liferay-ui:message key="next" />
							            </button>
									</c:if>
								</div>
							</div>

							<!-- Tab 2: Salary and Allowance - I -->
							<%@ include file="/ncc/salary-and-allowance-I.jsp"%>
							<%@ include file="/ncc/salary-and-allowance-II.jsp"%>

							<!-- Tab 3: Salary and Allowance - II -->
							
							<!-- End of Tab 3 -->
						</div>

						

					</div>
					<!-- card-body -->
				</div>
				</div>
				<!-- card -->
			</div>
			<!-- row -->
		</div>
		<!-- container -->
	</div>
	<!-- common-forms-div -->
</form>
<!-- Modal popup for NCC Grant Request Save -->
<div class="modal fade" id="grantRequestSaveModal" tabindex="-1" role="dialog" aria-labelledby="grantRequestModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content modal-bg">
            <div class="modal-header justify-content-center align-items-center">
                <h5 class="modal-title" id="grantRequestModalTitle">
                    <liferay-ui:message key="ncc-grant-request-submission" />
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
                            <p id="successGrantRequestSave"></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer d-flex justify-content-end">
                <a href="${grantRequestListBackURL}" type="button" class="btn btn-secondary maha-save-btn"
                   data-bs-dismiss="modal" onclick="closeModal('grantRequestSaveModal')">
                    <liferay-ui:message key="close" />
                </a>
            </div>
        </div>
    </div>
</div>


<script>

$(document).ready(function() {
	let reloadCount = parseInt(localStorage.getItem('reloadCount')) || 0;
    console.log("reloadCount"+reloadCount);
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
	$("#nccGrantRequestForm").validate({
		rules: {
			headquarterName: { required: true },
			unitType: { required: true },
			caretakerAllowance: { required: true },
			refreshmentAllowance: { required: true },

			salaries: { required: true, pattern: /^\d*\.?\d+$/ },
			overTimeAllowance: { required: true, pattern: /^\d*\.?\d+$/ },
			telephone: { required: true, pattern: /^\d*\.?\d+$/ },
			electricity: { required: true, pattern: /^\d*\.?\d+$/ },
			waterCharges: { required: true, pattern: /^\d*\.?\d+$/ },
			contractualServices: { required: true, pattern: /^\d*\.?\d+$/ },
			domesticTravellingExpenses: { required: true, pattern: /^\d*\.?\d+$/ },
			officeExpenses: { required: true, pattern: /^\d*\.?\d+$/ },

			rent: { required: true, pattern: /^\d*\.?\d+$/ },
			rates: { required: true, pattern: /^\d*\.?\d+$/ },
			taxes: { required: true, pattern: /^\d*\.?\d+$/ },
			trainingActivities: { required: true, pattern: /^\d*\.?\d+$/ },
			atg: { required: true, pattern: /^\d*\.?\d+$/ },
			washingAndPolishingAllowance: { required: true, pattern: /^\d*\.?\d+$/ },
			petrolOilLubricants: { required: true, pattern: /^\d*\.?\d+$/ },
			minorWork: { required: true, pattern: /^\d*\.?\d+$/ },
			honorariumToAno: { required: true, pattern: /^\d*\.?\d+$/ },
			outfitMaintenanceAllowance: { required: true, pattern: /^\d*\.?\d+$/ },
			grantsInAid: { required: true, pattern: /^\d*\.?\d+$/ },
			scholarshipOrStipend: { required: true, pattern: /^\d*\.?\d+$/ }
		},
		messages: {
			headquarterName: {
				required: "<liferay-ui:message key='headquarter-name-required' />"
			},
			unitType: {
				required: "<liferay-ui:message key='unit-type-required' />"
			},
			caretakerAllowance: {
				required: "<liferay-ui:message key='caretaker-allowance-required' />"
			},
			refreshmentAllowance: {
				required: "<liferay-ui:message key='refreshement-allowance-required' />"
			},
			salaries: {
				required: "<liferay-ui:message key='salaries-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			overTimeAllowance: {
				required: "<liferay-ui:message key='overtime-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			telephone: {
				required: "<liferay-ui:message key='telephone-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			electricity: {
				required: "<liferay-ui:message key='electricity-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			waterCharges: {
				required: "<liferay-ui:message key='water-charges-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			contractualServices: {
				required: "<liferay-ui:message key='contractual-services-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			domesticTravellingExpenses: {
				required: "<liferay-ui:message key='domestic-travel-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			officeExpenses: {
				required: "<liferay-ui:message key='office-expenses-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			rent: {
				required: "<liferay-ui:message key='rent-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			rates: {
				required: "<liferay-ui:message key='rates-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			taxes: {
				required: "<liferay-ui:message key='taxes-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			trainingActivities: {
				required: "<liferay-ui:message key='training-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			atg: {
				required: "<liferay-ui:message key='atg-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			washingAndPolishingAllowance: {
				required: "<liferay-ui:message key='washing-polishing-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			petrolOilLubricants: {
				required: "<liferay-ui:message key='pol-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			minorWork: {
				required: "<liferay-ui:message key='minor-work-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			honorariumToAno: {
				required: "<liferay-ui:message key='honorarium-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			outfitMaintenanceAllowance: {
				required: "<liferay-ui:message key='outfit-maintenance-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			grantsInAid: {
				required: "<liferay-ui:message key='grants-in-aid-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			},
			scholarshipOrStipend: {
				required: "<liferay-ui:message key='scholarship-required' />",
				pattern: "<liferay-ui:message key='decimal-only' />"
			}
		},
		errorElement: "div",
		errorPlacement: function (error, element) {
			error.addClass("text-danger");
			element.closest(".form-group").append(error);
		},
		highlight: function (element) {
			$(element).addClass("is-invalid");
		},
		unhighlight: function (element) {
			$(element).removeClass("is-invalid");
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
           
            case 'salaryTab1-tab':
	            if (!validateTabFields('salaryTab1')) return e.preventDefault();
	            break;
	        case 'salaryTab2-tab':
	            if (!validateTabFields('salaryTab1') || !validateTabFields('salaryTab2')) return e.preventDefault();
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
	
	 function calculateTotal() {
	        let total = 0;
	        $(".grant-amount").each(function () {
	            const val = parseFloat($(this).val().replace(/,/g, '')) || 0;
	            total += val;
	        });
	        // Update both total fields (Salary Tab I and II)
	        $("input[name='total']").val(total.toFixed(2));
	        $("input[name='totalexp']").val(total.toFixed(2));
	    }

	    // Trigger calculation on keyup, change, or blur
	    $(document).on("input", ".grant-amount", function () {
	        calculateTotal();
	    });
});
function saveGrantRequestForm(status) { debugger
    event.preventDefault();

    var form = $('#nccGrantRequestForm')[0];
    var formData = new FormData(form);
    formData.append("status", status);
    if ($('#nccGrantRequestForm').valid()) {
    $.ajax({
        url: "${saveGrantRequestURL}", // Liferay resource URL injected via JSP
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) { debugger
            var data = typeof response === "string" ? JSON.parse(response) : response;

            if (data.grantRequestSaved) {
                $('#successGrantRequestSave').html('<div>Grant Request saved successfully</div>');
                $('#grantRequestSaveModal').modal('show');
            } else {
                $('#successGrantRequestSave').html('<div>Grant Request save failed. Please try again.</div>');
                $('#grantRequestSaveModal').modal('show');
            }
        },
        error: function (xhr) {
            console.error("Something went wrong. Please try again.");
            $('#successGrantRequestSave').html('<div>Something went wrong while saving.</div>');
            $('#grantRequestSaveModal').modal('show');
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
	    // First tab validation fields (General Info)
	    salaryTab1: [
	        "[name='unitType']",
	        "[name='headquarterName']"
	    ],

	    // Second tab validation fields (Grant & Expense Details)
	    salaryTab2: [
	        "[name='caretakerAllowance']",
	        "[name='refreshmentAllowance']",
	        "[name='salaries']",
	        "[name='overTimeAllowance']",
	        "[name='telephone']",
	        "[name='electricity']",
	        "[name='waterCharges']",
	        "[name='contractualServices']",
	        "[name='domesticTravellingExpenses']",
	        "[name='officeExpenses']",
	    ]
	};
function validateTabFields(tabId) {
    $('#nccGrantRequestForm').validate().resetForm();
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