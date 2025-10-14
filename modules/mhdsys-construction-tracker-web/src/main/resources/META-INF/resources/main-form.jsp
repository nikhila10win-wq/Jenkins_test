<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys"%>
<%@ include file="/init.jsp"%>


<style>
#receiptInvoiceUpload-error {
	position: absolute;
	width: 100%;
	left: 0;
	bottom: -27px;
	font-size: 12px;
}

.newTabs .nav-pills {
	background: #fff;
	padding: 0px;
	border-radius: 10px;
	overflow-x: auto;
	white-space: nowrap;
}

.newTabs .nav-pills li.nav-item {
	margin: 5px;
	display: inline-block;
}

.newTabs .nav-pills .nav-link {
	width: 100%;
	cursor: pointer;
}

.newTabs .nav-link.active {
	color: #fff;
	background-color: #26268e;
}

.nav-link {
	cursor: default !important;
}

.nav-link:not(.active) {
	cursor: pointer !important;
}
</style>

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdsysConstructionTrackerWebPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<portlet:resourceURL id="<%=MhdsysConstructionTrackerWebPortletKeys.SAVE_CONSTRUCTIONTRACKER_RESOURCECOMMAND %>" var="SaveConstructionTrackerUrl" />

<c:choose>
	<c:when test="${!empty mode}">
		<c:choose>
			<c:when test="${isDSO || isTSO}">
				<c:set var="backUrl" value="${redirectUrl}&type=dsoView" />
			</c:when>
			<c:otherwise>
				<c:set var="backUrl" value="${redirectUrl}&type=dddHoView" />
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:set var="backUrl" value="/group/guest/construction-tracker" />
	</c:otherwise>
</c:choose>

<div class="common-forms-div">
<div class="container my-5 newTabs">
	<div class="row">
	<div class="border-0 card shadow">
	<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
		<h5 class="mb-0"> <liferay-ui:message key="construction-tracker" /></h5>
			
		<div><a href="${backUrl}" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
	</div>
	<div class="card-body">
	<form id="constructionTracker" method="POST" enctype="multipart/form-data">
	
	<input type="hidden" id="constructionTrackerId" value="${constructionTrackerDTO.constructionTrackerId}">
		<div class="col-md-12">
			<ul class="nav nav-pills nav-justified shadow-sm" id="myTab" role="tablist">
				<li class="nav-item"><button class="nav-link active border-0" id="name-of-sports-complex-tab" data-target="name-of-sports-complex" type="button"><liferay-ui:message key="name-of-sports-complex" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="address-details-tab" data-target="address-details" type="button"><liferay-ui:message key="address-details" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="officer-details-tab" data-target="officer-details" type="button"><liferay-ui:message key="officer-details" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="land-details-tab" data-target="land-details" type="button"><liferay-ui:message key="land-details" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="administration-tab" data-target="administration" type="button"><liferay-ui:message key="administration" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="funds-tab" data-target="funds" type="button"><liferay-ui:message key="funds" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="govt-grants-tab" data-target="govt-grants" type="button"><liferay-ui:message key="govt-grants" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="other-sources-tab" data-target="other-sources" type="button"><liferay-ui:message key="other-sources" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="other-tab" data-target="other" type="button"><liferay-ui:message key="other" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="agency-details-tab" data-target="agency-details" type="button"><liferay-ui:message key="agency-details" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="progress-details-tab" data-target="progress-details" type="button"><liferay-ui:message key="progress-details" /></button></li>
				<li class="nav-item"><button class="nav-link border-0" id="uc-cc-status-tab" data-target="uc-cc-status" type="button"><liferay-ui:message key="uc-cc-status" /></button></li>
			</ul>
			
				<div class="tab-content mt-5" id="myTabContent">
				
					<div class="tab-pane fade show active" id="name-of-sports-complex" role="tabpanel"><jsp:include page="/jsps/sports-complex.jsp"/>
						<div class="card-footer bg-transparent text-right p-4">
						<!-- <div class="d-flex justify-content-between"> -->
						<div class="d-flex justify-content-end">
						 <c:if test="${mode ne 'view'}">
							 <button type="button" class="btn btn-outline-danger cancel-tab-btn">
						        <liferay-ui:message key="cancel" />
						    </button>
					   
						    <button type="button" class="btn btn-warning reset-tab-btn">
						        <liferay-ui:message key="reset" />
						    </button> 
					     </c:if> 
					     
							  <button type="button" class="btn btn-outline-secondary save-draft-btn" onclick="saveAsDraft()" <c:if test="${mode eq 'view' || mode eq 'edit'}">style="display:none;"</c:if>>
							    <liferay-ui:message key="save-as-draft" />
							  </button>
						  <button type="button" class="btn btn-primary next-tab-btn">
						    <liferay-ui:message key="next" />
						  </button>
						</div>
						</div>
					</div>
					
					<div class="tab-pane fade" id="address-details" role="tabpanel" ><jsp:include page="/jsps/address-details.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/> </div>
					<div class="tab-pane fade" id="officer-details" role="tabpanel"><jsp:include page="/jsps/officer-details.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="land-details" role="tabpanel"><jsp:include page="/jsps/land-details.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="administration" role="tabpanel"><jsp:include page="/jsps/administration.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="funds" role="tabpanel"><jsp:include page="/jsps/funds.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="govt-grants" role="tabpanel"><jsp:include page="/jsps/govt-grants.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="other-sources" role="tabpanel"><jsp:include page="/jsps/other-sources.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="other" role="tabpanel" data-loaded="false"><jsp:include page="/jsps/other.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="agency-details" role="tabpanel"><jsp:include page="/jsps/agency-details.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="progress-details" role="tabpanel"><jsp:include page="/jsps/progress-details.jsp"/> <jsp:include page="/jsps/prev-next-draft.jsp"/></div>
					<div class="tab-pane fade" id="uc-cc-status" role="tabpanel"><jsp:include page="/jsps/uc-cc-status.jsp"/>
						<div class="card-footer bg-transparent text-right p-4">
							<div class="d-flex justify-content-end">
							 <c:if test="${mode ne 'view'}">
								 <button type="button" class="btn btn-outline-danger cancel-tab-btn">
							        <liferay-ui:message key="cancel" />
							    </button>
							    <button type="button" class="btn btn-warning reset-tab-btn">
							        <liferay-ui:message key="reset" />
							    </button>
					    	</c:if> 
								 <button type="button" class="btn btn-outline-secondary save-draft-btn" onclick="saveAsDraft()" <c:if test="${mode eq 'view' || mode eq 'edit'}">style="display:none;"</c:if>>
							    <liferay-ui:message key="save-as-draft" />
							  </button>
								  <div>
								    <button type="button" class="btn btn-secondary prev-tab-btn me-2">
								      <liferay-ui:message key="previous" />
								    </button>
								   <c:if test="${mode ne 'view'}">
								    <button type="button" class="btn btn-success" id="saveBtn">
								      <liferay-ui:message key="submit" />
								    </button>
								   </c:if> 
								  </div>
							</div>
					</div>
				</div>
				</div>
			</form>
		</div>
	<!--	<div class="card-footer bg-transparent text-right p-4">
							<div class="d-flex justify-content-end">
 							<div class="d-flex justify-content-between"> 
								  <button type="button" class="btn btn-outline-secondary save-draft-btn" onclick="saveAsDraft()" <c:if test="${mode eq 'view' || mode eq 'edit'}">style="display:none;"</c:if>>
								    <liferay-ui:message key="save-as-draft" />
								  </button>
								  <div>
								    <button type="button" class="btn btn-secondary prev-tab-btn me-2">
								      <liferay-ui:message key="previous" />
								    </button>
								   <c:if test="${mode ne 'view'}">
								    <button type="button" class="btn btn-success" id="saveBtn">
								      <liferay-ui:message key="submit" />
								    </button>
								   </c:if> 
								  </div>
							</div>
						</div> -->
						
						<!-- Validation modal -->
					<div class="modal fade" id="saveConstructionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
					  <div class="modal-dialog modal-dialog-centered" role="document">
					    <div class="modal-content modal-bg">
					      <div class="modal-header justify-content-center align-items-center">
					        <h5 class="modal-title"><liferay-ui:message key="form" /></h5>
					      </div>
					      <div class="modal-body">
					        <div class="text-center">
					          <p id="success-message">
					           <!--  <liferay-ui:message key="please-fill-all-required-fields-before-proceeding" /> -->
					          </p>
					        </div>
					      </div>
					      <div class="modal-footer d-flex justify-content-end">
							    <c:choose>
							        <c:when test="${mode eq 'edit'}">
							        <!--     <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
							                <liferay-ui:message key="close"/>
							            </a> -->
							            <button type="button" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
										    <liferay-ui:message key="close"/>
										</button>
							        </c:when>
							        <c:when test="${mode eq '' || mode eq null }">
							           <!--  <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
							                <liferay-ui:message key="close"/>
							            </a> -->
							            <button type="button" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
										    <liferay-ui:message key="close"/>
										</button>
							        </c:when>
							        <c:otherwise>
							          <!--   <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn"  onclick="closeModal()">
							                <liferay-ui:message key="close"/>
							            </a> -->
							            
							            <button type="button" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
										    <liferay-ui:message key="close"/>
										</button>
							        </c:otherwise>
							    </c:choose>
							</div>
					
					    </div>
					  </div>
					</div>

	</div>
	</div>
</div>
</div>

<%-- <c:if test="${constructionTrackerDTO.applicationStatus == 1 || constructionTrackerDTO.applicationStatus == 2}">
	<jsp:include page="/jsps/admins-approval-blocks.jsp"/> 
</c:if> --%>

<script>

var $form = $("#constructionTracker");
let mode = '${mode}';
var isEditMode = mode == "edit";
console.log("isEditMode:: ", isEditMode)
console.log("mode:: "+mode);
	
$(document).ready(function () {
	$form.validate({
		ignore: ':hidden',
	    errorClass: "text-danger",
	    errorElement: "div",
	    errorPlacement: function (error, element) {
            if (element.attr("type") == "radio") {
                error.insertAfter(element.parent().parent());
            } else if (element.attr("type") == "checkbox" && element.hasClass("form-check-input")) {
                error.insertAfter(element.closest('.form-check'));
            } else {
                error.insertAfter(element);
            }
        }
	 
	  });
	validate_other();
	
	$.validator.addMethod("containsLetters", function(value, element) {
	    return this.optional(element) || /[A-Za-z]/.test(value);
	}, "<liferay-ui:message key='field-must-contain-characters' />");

	$.validator.addMethod("validContact", function(value, element) {
	    return this.optional(element) || /^[6-9]\d{9}$/.test(value);
	}, '<liferay-ui:message key="contact-number-invalid-pattern" />');

	$.validator.addMethod("alphanumericOnly", function(value, element) {
	    return this.optional(element) || /^[A-Za-z0-9 ]+$/.test(value);
	}, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");

	 $.validator.addMethod("alphanumericWithSpecialChars", function(value, element) {
	 		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
	 		    return this.optional(element) || /^[A-Za-z0-9.,\- ]+$/.test(value);
	 	    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
	 
	 $.validator.addMethod("alphanumericWithPeroidAndHyphen", function(value, element) {
	 		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
	 		   return this.optional(element) || /^[A-Za-z0-9.\- ]+$/.test(value);
	 	    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
	 
	$.validator.addMethod("validSpaces", function(value, element) {
	    // Checks if there are no leading/trailing spaces and only single space between words
	    return this.optional(element) || /^[^\s]+(?:\s[^\s]+)*$/.test(value);
	}, "<liferay-ui:message key='Spaces-are-allowed-only-between-words-and-No-leading-or-trailing-spaces' />");

	$.validator.addMethod("noEdgeSpaces", function(value, element) {
		  return this.optional(element) || value === value.trim();
		}, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

		$.validator.addMethod("singleSpaceOnly", function(value, element) {
		  return this.optional(element) || !/\s{2,}/.test(value);
		}, "<liferay-ui:message key='only-one-space-between-words-allowed' />");

		$.validator.addMethod("validCharacters", function(value, element) {
		  return this.optional(element) || /^[A-Za-z0-9\s.,/#-]*$/.test(value);
		}, "<liferay-ui:message key='please-enter-valid-characters' />");

		$.validator.addMethod("noConsecutiveSpecials", function(value, element) {
			return this.optional(element) || !/([.,/#-]\s*){2,}/.test(value);
		}, "<liferay-ui:message key='no-consecutive-specials-allowed' />");

		$.validator.addMethod("onlyDotAtEnd", function(value, element) {
		  return this.optional(element) || /\.$/.test(value) || /[A-Za-z0-9]$/.test(value);
		}, "<liferay-ui:message key='only-dot-at-end-allowed' />");
		
		$.validator.addMethod("validPersonName", function(value, element) {
			// It Should accept alphabets space .
		    return this.optional(element) || /^[A-Za-z. ]+$/.test(value.trim());
		}, "<liferay-ui:message key='please-enter-valid-name' />");
		
		 $.validator.addMethod("pastOrToday", function(value, element) {
			    if (this.optional(element)) return true;
			    const inputDate = new Date(value);
			    const today = new Date();
			    inputDate.setHours(0, 0, 0, 0);
			    today.setHours(0, 0, 0, 0);
			    return inputDate <= today;
			}, "<liferay-ui:message key='future-date-not-allowed' />");
		
});

$("#constructionTracker input, #constructionTracker select, #constructionTracker textarea").on('change blur', function() {
    $(this).valid(); // triggers validation on field
});

function closeModal(){
	 $("#saveConstructionModal").modal('hide');
	    window.location.href = "/group/guest/construction-tracker?clear=true";
}

$(document).on('click', '.reset-tab-btn', function () {
    const currentTabId = $(".tab-pane.active").attr("id");
    const normalizedTabId = currentTabId.replace(/-/g, "_");

    // Call the corresponding reset function dynamically
    const resetFn = window['reset_' + normalizedTabId];
    if (typeof resetFn === "function") {
        resetFn();
    }
});

function resetAllFields(){
	const $activeTab = $('.tab-pane.active');
	$activeTab.find('input, select, textarea').each(function () {
       const type = $(this).attr('type');
       if (type === 'checkbox' || type === 'radio') {
           //$(this).prop('checked', false); 
       } else if (type === 'file') {
           $(this).val('');
       } else {
           $(this).val('');
       }
       $(this).removeClass('is-invalid');
       $(this).next('.text-danger').remove();
   });
   $activeTab.find('.custom-preview').hide().text('');
}

function resetOnlyActiveFields() {
    const $activeTab = $('.tab-pane.active');
    $activeTab.find('input, select, textarea').each(function () {
        if ($(this).prop('readonly') || $(this).prop('disabled')) {
            return; // Skip readonly/disabled fields
        }

        const type = $(this).attr('type');
        if (type === 'checkbox' || type === 'radio') {
            // Optionally clear if needed
            // $(this).prop('checked', false);
        } else if (type === 'file') {
            $(this).val('');
        } else {
            $(this).val('');
        }

        $(this).removeClass('is-invalid');
        $(this).next('.text-danger').remove();
    });

    $activeTab.find('.custom-preview').hide().text('');
}


$(document).on('click', '.cancel-tab-btn', function () {
  /*   window.location.href = "${backUrl}"; */
	window.location.href = "/group/guest/construction-tracker?clear=true";
});

// Triggered when "Next" button is clicked
$(document).on("click", ".next-tab-btn", function () {
    const currentTabId = $(".tab-pane.active").attr("id");
    const normalizedTabId = currentTabId.replace(/-/g, "_");
	console.log("normalizedTabId --- "+normalizedTabId);
    // Call specific tab's validation method
    const validatorFn = window['validate_' + normalizedTabId];
    const isValid = typeof validatorFn === "function" ? validatorFn() : true;

    if (!isValid) {
        return;
    }
    goToNextTab(currentTabId);
});

//Triggered when tab clicked manually
$('.nav-link').on('click', function () {
	
	 if (mode === 'view') {
	        const targetTabId = $(this).data('target');
	        if(targetTabId == "address-details"){
	        	callGeoLocation();
	        }
	        switchToTab(targetTabId);
	        return;
	    }
	
    const targetTabId = $(this).data('target');
    const currentTabId = $(".tab-pane.active").attr("id");

    if (targetTabId === currentTabId) return;

    const $tabs = $('.nav-link');
    const currentIndex = $tabs.index($('.nav-link[data-target="' + currentTabId + '"]'));
    const targetIndex = $tabs.index($(this));

    // Allow moving to any previous tab without validation
    if (targetIndex <= currentIndex) {
        switchToTab(targetTabId);
        return;
    }

    // Block skipping more than one tab ahead
    if (targetIndex > currentIndex + 1) {
        return;
    }

    // Validate current tab before moving forward
    const normalizedTabId = currentTabId.replace(/-/g, "_");
    const validatorFn = window['validate_' + normalizedTabId];
    const isValid = typeof validatorFn === "function" ? validatorFn() : true;

    if (!isValid) {
        return;
    }
    // Proceed to next tab
    switchToTab(targetTabId);
});

 
/*  $('.nav-link').click(function () {
	$('.nav-link').removeClass('active');
	$('.tab-pane').removeClass('show active');

	$(this).addClass('active');

	let targetId = $(this).attr('id').replace('-tab', '');
	$('#' + targetId).addClass('show active');
});  */

// Logic to switch to next/prev tab and load via AJAX
function goToNextTab(currentTabId) {
    const $tabs = $('.nav-link');
    const currentIndex = $tabs.index($('.nav-link[data-target="' + currentTabId + '"]'));
    if (currentIndex + 1 < $tabs.length) {
        const nextTabId = $tabs.eq(currentIndex + 1).data("target");
        switchToTab(nextTabId);
    }
}

function switchToTab(tabId) {
    $(".nav-link").removeClass("active");
    $('.nav-link[data-target="' + tabId + '"]').addClass("active");
    $(".tab-pane").removeClass("show active");
    const $targetPane = $("#" + tabId);
    $targetPane.addClass("show active");
}

//Triggered when "Previous" button is clicked
$(document).on("click", ".prev-tab-btn", function () {
    const currentTabId = $(".tab-pane.active").attr("id");
    goToPreviousTab(currentTabId);
});

// Function to switch to previous tab
function goToPreviousTab(currentTabId) {
    const $tabs = $('.nav-link');
    const currentIndex = $tabs.index($('.nav-link[data-target="' + currentTabId + '"]'));
    if (currentIndex > 0) {
        const prevTabId = $tabs.eq(currentIndex - 1).data("target");
        switchToTab(prevTabId);
    }
}


// PREVENT ENTERING +/- SYMBOLS FOR AMOUNT FIELDS
 $(document).on('keypress', '#area, .progressExpenditure, #UCCCAmount, .approvalAmount, .govtReceivedAmount, .govtFacilityAmount, .govtExpenditureAmount, .govtTotalAmount, .otherSourcesReceivedAmount, .otherSourcesFacilityAmount, .otherSourcesExpenditureAmount, .otherSourcesTotalAmount', function (e) {
    const char = String.fromCharCode(e.which);
    if (!/[0-9.]/.test(char)) {
        e.preventDefault();
    }
    const input = $(this);
    setTimeout(() => {
        const value = input.val();
        if (/[^0-9.]/.test(value)) {
            input.val(value.replace(/[^0-9.]/g, '')); // Remove invalid characters
            input.valid(); // Trigger validation
        }
    }, 0);
}); 

$(document).on('input', '#area,.progressExpenditure, #UCCCAmount, .approvalAmount, .govtReceivedAmount, .govtFacilityAmount, .govtExpenditureAmount, .govtTotalAmount, .otherSourcesReceivedAmount, .otherSourcesFacilityAmount, .otherSourcesExpenditureAmount, .otherSourcesTotalAmount', function (e) {
    const input = $(this);
    let value = input.val();
    value = value.replace(/[^0-9.]/g, '');
    // Allow only one dot
    const parts = value.split('.');
    if (parts.length > 2) {
        value = parts[0] + '.' + parts[1]; // remove extra dots
    }
    // Limit to two decimal places
    if (parts[1]) {
        parts[1] = parts[1].substring(0, 2);
        value = parts[0] + '.' + parts[1];
    }
    // Update only if the value has changed
    if (input.val() !== value) {
        const cursorPos = input[0].selectionStart;
        input.val(value);
        // Try to restore cursor position
        input[0].setSelectionRange(cursorPos - 1, cursorPos - 1);
    }

    input.valid(); // Optional: trigger validation
});




$(document).on('input keyup', '#UCCCReason, #panalAction, #UCCCRevisedCompletionDate, #UCCCAmount, #address, #officerName, #contactNumber, #area, #executingAgency, #nameOfTheFirm, .approvalAmount, .dateOfApproval, .govtReceivedAmount, .govtFacilityAmount, .govtDate, .govtExpenditureAmount, .govtTotalAmount, .otherSourcesReceivedAmount, .otherSourcesFacilityAmount, .otherSourcesDate, .otherSourcesExpenditureAmount, .otherSourcesTotalAmount, .workInHand, .dateOfTender, .dateOfWorkOrder, .expectedDateOfCompletion, .progressWorkPercentage, .progressExpenditure', function () {
    $(this).valid(); // Trigger validation immediately for any of the fields
});

/* SAVE FORM */
 $('#saveBtn').on('click', function () {
	 	let isFileValid = true;
	 	const isFormValid = $('#constructionTracker').valid();
	 	let isNotSubmitted = $('input[name="UCCCStatus"]:checked').val() === 'No';
	 	
	 	if(isNotSubmitted){
 		   const panalActionDoc = document.getElementById("panalActionDoc");
 		   const panalActionDocHiddenInput = document.getElementById("panalActionDocHiddenInput");
		   const errorSpan = document.getElementById("panalActionDocError");
			errorSpan.textContent = "";
			errorSpan.style.display = "none";
			if(panalActionDocHiddenInput && panalActionDocHiddenInput.value.trim() !== "" && Number(panalActionDocHiddenInput.value)>0 ){
 				isFileValid = true;
 			}
 			else if (!panalActionDoc || !panalActionDocHiddenInput || Number(panalActionDocHiddenInput.value)==0 ||panalActionDocHiddenInput.value.trim() == ""||!panalActionDoc.files || panalActionDoc.files.length === 0) {
 			    errorSpan.textContent = "<liferay-ui:message key='please-upload-pdf' />";
 			    errorSpan.style.display = "block";
 			    isFileValid = false;
 			} 
	 	}
		   
		if(isFileValid && isFormValid){
			saveForm("save");
		}
});

/* SAVE AS Draft */
function saveAsDraft(){
	   const currentTabId = $(".tab-pane.active").attr("id");
	    const normalizedTabId = currentTabId.replace(/-/g, "_");
		console.log("normalizedTabId --- "+normalizedTabId);
	    // Call specific tab's validation method
	    const validatorFn = window['validate_' + normalizedTabId];
	    const isValid = typeof validatorFn === "function" ? validatorFn() : true;
	    let idUcCCTabValid = true;
	    let isFileValid = true;
	    if(normalizedTabId === "uc_cc_status"){
	    	idUcCCTabValid = $('#constructionTracker').valid();
	    	
	    	let isNotSubmitted = $('input[name="UCCCStatus"]:checked').val() === 'No';
	    	console.log("isNotSubmitted:: ", isNotSubmitted);
	    	if(isNotSubmitted){
	  		   const panalActionDoc = document.getElementById("panalActionDoc");
	  		   const panalActionDocHiddenInput = document.getElementById("panalActionDocHiddenInput");
	 		   const errorSpan = document.getElementById("panalActionDocError");
	 			errorSpan.textContent = "";
	 			errorSpan.style.display = "none";
	 			
	 			//console.log("panalActionDoc::", panalActionDoc, ", panalActionDocHiddenInput::", panalActionDocHiddenInput);
	 			if(panalActionDocHiddenInput && panalActionDocHiddenInput.value.trim() !== "" && Number(panalActionDocHiddenInput.value)>0 ){
	 				isFileValid = true;
	 			}
	 			else if (!panalActionDoc || !panalActionDocHiddenInput || Number(panalActionDocHiddenInput.value)==0 ||panalActionDocHiddenInput.value.trim() == ""||!panalActionDoc.files || panalActionDoc.files.length === 0) {
	 			    errorSpan.textContent = "<liferay-ui:message key='please-upload-pdf' />";
	 			    errorSpan.style.display = "block";
	 			    isFileValid = false;
	 			} 
	 			
	 	 	}
	    }
	    
	    if (!isValid || !idUcCCTabValid || !isFileValid) {
	        return;
	    }
	    saveForm('draft');
}
	
 function saveForm(actionType){
	 console.log("All validations are passed and saving Form ------ ");
	 console.log("actionType: "+actionType);
	 let constructionTrackerId = $('#constructionTrackerId').val();
	 console.log("constructionTrackerId: "+constructionTrackerId);
/* 	 let constructionTrackerId = '${constructionTrackerDTO.constructionTrackerId}'; */
	 let mode = '${mode}';
	 
	 if(mode != "view"){
		 
		 const form = document.getElementById("constructionTracker");
		 if(mode == "edit"){
		    form.querySelectorAll("[disabled]").forEach(function (el) {
		        el.disabled = false;
		    });
		 }

		    const formData = new FormData(form); 
		    formData.append("actionType", actionType);
		    formData.append("mode", mode);
		    if(constructionTrackerId){
		    	formData.append("constructionTrackerId", constructionTrackerId);
		    }
		 
		 $.ajax({
		        type: "POST",
		        url: "${SaveConstructionTrackerUrl}" ,
		        data: formData,
		        contentType : false,
				cache : false,
				processData : false,
		        success: function(response){
		        var data = JSON.parse(response);
		        console.log("response:: "+response)
		        console.log("data:: "+data)
		        if (data.status === "success") {
		        	/* $('#saveBtn').prop('disabled', true);
		        	$('.save-draft-btn').prop('disabled', true); */
		        	$('#constructionTrackerId').val(data.conTrackerId); 
		        	 console.log("constructionTrackerId After Save as Draft: "+$('#constructionTrackerId').val());
		            var msg = '<span class="text-success"><liferay-ui:message key="details-submitted-successfully"/></span>';
		             if(actionType == "draft"){
		            	msg = '<span class="text-success"><liferay-ui:message key="the-details-are-saved-successfully"/></span>';
		            } 
		            $('#success-message').html(msg);
		            $('#saveBtn').prop('disabled', true);
		            $("#saveConstructionModal").modal('show');
		        } else {
		            var msg = '<span class="text-danger"><liferay-ui:message key="the-details-are-failed-to-submit"/></span>';
		            $('#success-message').html(msg);
		            $("#saveConstructionModal").modal('show');
		        }
		    	 }
		    });
	 }else{
		var msg = "<liferay-ui:message key="you-canot-submit-details-in-view-mode"/>";
 	    $('#success-message').html(msg);
 		 $("#saveConstructionModal").modal('show');
	 }
	 
}

 function handleSingleImageUpload(fileInput, inputId, previewContainerId, previewLinkId, deleteButtonId, errorSpanId, hiddenInputId) {
 	const file = fileInput.files[0];
 	const previewContainer = document.getElementById(previewContainerId);
 	const previewLink = document.getElementById(previewLinkId);
 	const errorSpan = document.getElementById(errorSpanId);
 	const hiddenInput = document.getElementById(hiddenInputId);

 	errorSpan.textContent = '';
 	errorSpan.style.display = 'none';

 	if (!file) return;

 	let ext = file.name.split('.').pop().toLowerCase();
 	
 	if (inputId.startsWith("addGanttChart_") || inputId.startsWith("GISPhoto_")) {
 	    // Allow both PDF and JPG/JPEG/PNG files
 	 if (!['pdf', 'jpg', 'jpeg', 'png'].includes(ext)) {
 	        errorSpan.textContent = 'Allowed only PDF, JPG, JPEG, PNG files.';
 	        errorSpan.style.display = 'block';
 	        fileInput.value = '';
 	        return;
 	    } 
 	}else if(inputId == "panalActionDoc"){
 		  if (ext !== 'pdf') {
 		        errorSpan.textContent = 'Allowed only PDF files.';
 		        errorSpan.style.display = 'block';
 		        fileInput.value = '';
 		        return;
 		    }	
	 	}else{
	 		
 		if (!['jpg', 'jpeg', 'png'].includes(ext)) {
 			errorSpan.textContent =  "<liferay-ui:message key='allowed-only-jpg-jpeg-png-files' />";
 			errorSpan.style.display = 'block';
 			fileInput.value = '';
 			return;
 		}
 	}
 	

 	if (file.size > 2 * 1024 * 1024) {
 		errorSpan.textContent = "<liferay-ui:message key='file-size-must-be-less-than-2mb' />";
 		errorSpan.style.display = 'block';
 		fileInput.value = '';
 		return;
 	}

 	uploadedSingleFiles[inputId] = {
 		file: file,
 		name: file.name,
 		url: URL.createObjectURL(file)
 	};

 	previewLink.textContent = file.name;
 	previewLink.href = uploadedSingleFiles[inputId].url;
 	previewContainer.style.display = 'block';
 	hiddenInput.value = file.name;

 	/* fileInput.value = ''; */
 }

 function removeSingleImageFile(fileInput, inputId, previewContainerId, previewLinkId, hiddenInputId) {
 	delete uploadedSingleFiles[inputId];
 	
 	 const fileInputElement = document.getElementById(inputId);
 	  if (fileInputElement) {
	        fileInputElement.value = '';
	        fileInputElement.dispatchEvent(new Event('change')); // Notify validators

	        // Revalidate if using jQuery Validate
	        if ($(fileInputElement).closest("form").length) {
	            $(fileInputElement).closest("form").validate().element(fileInputElement);
	        }

	        // Reset file label
	        const label = fileInputElement.nextElementSibling;
	        if (label && label.classList.contains('custom-file-label')) {
	            const chooseText = document.getElementById('chooseFileLabelText')?.textContent?.trim() || 'Choose file';
	            label.textContent = chooseText;
	        }
	    }
 	  
 	document.getElementById(previewContainerId).style.display = 'none';
 	document.getElementById(previewLinkId).textContent = '';
 	document.getElementById(previewLinkId).href = '#';
 	document.getElementById(hiddenInputId).value = '';
 }
 
 
</script>