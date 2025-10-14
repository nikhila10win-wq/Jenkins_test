<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="uc-cc-status"/></div>
		<div class="card-body">
			<div class="row">
			
				<div class="col-md-12">
				    <div class="form-group">
				        <label><liferay-ui:message key="UCCC-Status"/> <sup class="text-danger">*</sup></label>
				        <div class="d-flex mt-2">
						    <div class="radio-text">
						      <input type="radio" class="radio-btn" id="UCCCStatusYes" name="UCCCStatus" value="Yes"
						        ${empty constructionTrackerDTO.UCCCStatus || constructionTrackerDTO.UCCCStatus == 'Yes' ? 'checked' : ''}
				          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
						      <label for="UCCCStatusYes"><liferay-ui:message key="yes"/></label>
						    </div>
						    <div class="radio-text ms-3">
						      <input type="radio" class="radio-btn" id="UCCCStatusNo" name="UCCCStatus" value="No"
						      ${empty constructionTrackerDTO.UCCCStatus || constructionTrackerDTO.UCCCStatus == 'No' ? 'checked' : ''}
				          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
						      <label for="UCCCStatusNo"><liferay-ui:message key="no"/></label>
						    </div>
					  </div>
				    </div>
				</div>
				</div>
				<!-- submitted block starts ----------------------------------------------------------------------------------------------------- -->
				 <div class="submitted_block">
				 <div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label><liferay-ui:message key="UCCC-Amount"/><sup class="text-danger">*</sup></label>
							<input type="text" class="form-control" name="UCCCAmount" id="UCCCAmount" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> value="${constructionTrackerDTO.UCCCAmount }">
						</div>
					</div>
					
					<div class="col-md-6">
					    <div class="form-group">
					        <label><liferay-ui:message key="extension"/> </label>
					        <div class="d-flex mt-2">
							    <div class="radio-text">
							      <input type="radio" class="radio-btn" id="UCCCExtension" name="UCCCExtension" value="Yes"
							        ${empty constructionTrackerDTO.UCCCExtension || constructionTrackerDTO.UCCCExtension == 'Yes' ? 'checked' : ''}
					          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
							      <label for="UCCCExtension"><liferay-ui:message key="yes"/></label>
							    </div>
							    <div class="radio-text ms-3">
							      <input type="radio" class="radio-btn" id="UCCCExtension" name="UCCCExtension" value="No"
							      ${empty constructionTrackerDTO.UCCCExtension || constructionTrackerDTO.UCCCExtension == 'No' ? 'checked' : ''}
					          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
							      <label for="UCCCExtension"><liferay-ui:message key="no"/></label>
							    </div>
						  </div>
					    </div>
					</div>
					<!-- If expendicture yes then it should visible -->
					<div class="col-md-6">
						<div class="form-group">
							<label><liferay-ui:message key="UCCC-Revised-Completion-Date"/></label>
							<input type="date" class="form-control" name="UCCCRevisedCompletionDate" id="UCCCRevisedCompletionDate" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>
							 value="<fmt:formatDate value='${constructionTrackerDTO.UCCCRevisedCompletionDate}' pattern='yyyy-MM-dd'/>">
						</div>
					</div>
					
					<div class="col-md-6">
					    <div class="form-group">
					        <label><liferay-ui:message key="work-Completed-In-Timeline"/> </label>
					        <div class="d-flex mt-2">
							    <div class="radio-text">
							      <input type="radio" class="radio-btn" id="workCompletedInTimeline" name="workCompletedInTimeline" value="Yes"
							        ${empty constructionTrackerDTO.workCompletedInTimeline || constructionTrackerDTO.workCompletedInTimeline == 'Yes' ? 'checked' : ''}
					          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
							      <label for="workCompletedInTimeline"><liferay-ui:message key="yes"/></label>
							    </div>
							    <div class="radio-text ms-3">
							      <input type="radio" class="radio-btn" id="workCompletedInTimeline" name="workCompletedInTimeline" value="No"
							      ${empty constructionTrackerDTO.workCompletedInTimeline || constructionTrackerDTO.workCompletedInTimeline == 'No' ? 'checked' : ''}
					          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
							      <label for="workCompletedInTimeline"><liferay-ui:message key="no"/></label>
							    </div>
						  	</div>
					    </div>
					</div>
					</div>
				
				 </div>
				<!-- submitted block Ends ----------------------------------------------------------------------------------------------------- -->
				
				<!-- Not submitted block Starts ----------------------------------------------------------------------------------------------------- -->
				
				<div class="not_submitted_block">
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group">
							<label><liferay-ui:message key="UCCC-Reason"/><sup class="text-danger">*</sup></label>
							<input type="text" class="form-control" name="UCCCReason" id="UCCCReason" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> value="${constructionTrackerDTO.UCCCReason }">
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label><liferay-ui:message key="panal-Action"/><sup class="text-danger">*</sup></label>
							<input type="text" class="form-control" name="panalAction" id="panalAction" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> value="${constructionTrackerDTO.panalAction }">
						</div>
					</div>
					
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label><liferay-ui:message key="panal-Action-Doc"/><sup class="text-danger">*</sup>
								<em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file" />"></em>
							</label>
							
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="panalActionDoc" name="panalActionDoc"
									onchange="handleFileUpload(event, 'medicalCertificate', 'medicalCertificateNewPreviewContainer', 'medicalCertificateNewPreviewLink', 'medicalCertificateNewDeleteButton')"> 
								<label class="custom-file-label" for="customFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
										
						</div>
					</div> --%>
					
					<div class="col-md-6">
					  <div class="form-group">
					    <label>
					      <liferay-ui:message key="panal-Action-Doc" />
					      <sup class="text-danger">*</sup>
					      <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-under-2mb' />"></em>
					    </label>
					    <div class="custom-file">
					      <input type="file" class="custom-file-input" id="panalActionDoc" name="panalActionDoc"
					        onchange="handleSingleImageUpload(this,'panalActionDoc','panalActionDocPreviewContainer','panalActionDocPreviewLink','panalActionDocDeleteButton','panalActionDocError','panalActionDocHiddenInput')"
					        <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> >
					      <label class="custom-file-label" for="panalActionDoc"><liferay-ui:message key="choose-file" /></label>
					    </div>
					    <span id="panalActionDocError" class="text-danger mt-2"></span>
					    <input type="hidden" id="panalActionDocHiddenInput" name="panalActionDocHiddenInput" value="${constructionTrackerDTO.panalActionDoc }" readonly="readonly"/>
					    <input type="hidden" name="hiddenPanelActionDoc" value="${constructionTrackerDTO.panalActionDoc }" readonly="readonly">
					   <div class="mt-3" id="panalActionDocPreviewContainer" style="${not empty panalActionDocURL and panalActionDocURL != '' ? '' : 'display:none;'}">
					      <div class="d-flex">
					      <a href="${panalActionDocURL}" target="_blank" id="panalActionDocPreviewLink" class="text-truncate">View document</a>
					         <c:if test="${mode ne 'view' && mode ne 'edit'}">
							      <button type="button" class="btn btn-sm btn-danger" id="panalActionDocDeleteButton"
							        onclick="removeSingleImageFile(this,'panalActionDoc','panalActionDocPreviewContainer','panalActionDocPreviewLink','panalActionDocHiddenInput')">
							        	<i class="bi bi-x-circle-fill"></i>
							      </button>
					         </c:if>
							</div>
					    </div>
					  </div>
					</div>
					
				
				</div>
				</div>
				<!-- Not submitted block Ends ----------------------------------------------------------------------------------------------------- -->
				
			</div>
		</div>
	</div>


<c:if test="${constructionTrackerDTO.applicationStatus == 1 || constructionTrackerDTO.applicationStatus == 2}">
<div class="card card-background p-0 w-100">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="review-by-deputy-director"/></div>
		<div class="card-body">
			<div class="row">
		      <div class="col-md-12">
		     	 <div class="form-group">
			        <label><liferay-ui:message key="review" /><sup class="text-danger">*</sup></label>
			        <input type="text" class="form-control" name="review" id="review" value="${constructionTrackerDTO.dddReview }" readonly="readonly"/>
			   	 </div>
		      </div>
		      <div class="col-md-12">
		      	<div class="form-group">
		      		<label for=""> <liferay-ui:message key="review-document" /></label>
				
				    <div class="custom-file mt-2">
				    <c:choose>
				        <c:when test="${not empty dddDocURL}">
				            <a href="${hoDocURL}" target="_blank">
				                <liferay-ui:message key="view-document" />
				            </a>
				        </c:when>
				        <c:otherwise>
				            <span><liferay-ui:message key="no-document-uploaded" /></span>
				        </c:otherwise>
				    </c:choose>
				</div>
				
		      	</div>
		      </div>
		    </div>
		</div>
	</div>
</div>
</c:if>

<c:if test="${constructionTrackerDTO.applicationStatus == 2}">
<div class="card card-background p-0 w-100">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="review-by-ho"/></div>
		<div class="card-body">
			<div class="row">
		      <div class="col-md-12">
		     	 <div class="form-group">
			        <label><liferay-ui:message key="review" /><sup class="text-danger">*</sup></label>
			        <input type="text" class="form-control" name="review" id="review" value="${constructionTrackerDTO.hoReview }" readonly="readonly"/>
			   	 </div>
		      </div>
		      <div class="col-md-12">
		      	<div class="form-group">
		      		<label for="">  <liferay-ui:message key="review-document" /> </label>
				
				  <div class="custom-file mt-2">
				    <c:choose>
				        <c:when test="${not empty hoDocURL}">
				            <a href="${hoDocURL}" target="_blank">
				                <liferay-ui:message key="view-document" />
				            </a>
				        </c:when>
				        <c:otherwise>
				            <span><liferay-ui:message key="no-document-uploaded" /></span>
				        </c:otherwise>
				    </c:choose>
				</div>

		      	</div>
		      </div>
		    </div>
		</div>
	</div>
</div>
</c:if>

<script>
$(document).ready(function () {
	// Initialize form validation
	
	/* let panalActionDocURL = '${panalActionDocURL}';
	if(panalActionDocURL){
		$('#panalActionDocPreviewLink').text('View document').attr('href', panalActionDocURL);
		$('#panalActionDocPreviewContainer').show();
	} */
	
	$('#constructionTracker').validate({
		ignore: ':hidden',
		errorClass: 'text-danger',
		errorElement: 'div'
	});

	// Initial evaluation
	toggleUCCBlocks();
	toggleExtensionField();

	// Event listeners
	$('input[name="UCCCStatus"]').on('change', function () {
		toggleUCCBlocks();
	});

	$('input[name="UCCCExtension"]').on('change', function () {
		toggleExtensionField();
	});

	// Toggle UC/CC blocks
	function toggleUCCBlocks() {
		const isSubmitted = $('input[name="UCCCStatus"]:checked').val() === 'Yes';

		if (isSubmitted) {
			$('.submitted_block').show();
			$('.not_submitted_block').hide();

			// Reset not-submitted values
			$('#UCCCReason, #panalAction').val('');
			$('#panalActionDoc').val('');
			$('#medicalCertificateNewPreviewContainer').hide();
			$('#medicalCertificateNewPreviewLink').text('').attr('href', '#');
			$('#medicalCertificateNewDeleteButton').hide();

			// Add validation
			$('#UCCCAmount').rules('add', {
				required: true,
				number: true,
				min:1,
				maxlength: 10,
				messages: {
					required: '<liferay-ui:message key="please-enter-amount" />',
					number: '<liferay-ui:message key="amount-must-be-number" />',
					 min: '<liferay-ui:message key="amount-must-be-positive-or-greater-than-0" />',
					 maxlength: '<liferay-ui:message key="maximum-10-digits-should-allow" />',
					 
				}
			});
			
			$('#UCCCRevisedCompletionDate').rules('add', {
				pastOrToday: true,
			});

			// Remove validations from not-submitted
			$('#UCCCReason, #panalAction, #panalActionDoc').each(function () {
				$(this).rules('remove');
				$(this).removeClass('error');
				$(this).siblings('div.text-danger').remove();
			});
			
		} else {
			$('.submitted_block').hide();
			$('.not_submitted_block').show();

			// Reset submitted values
			$('#UCCCAmount').val('');
			$('#UCCCRevisedCompletionDate').val('');
			$('input[name="UCCCExtension"][value="Yes"]').prop('checked', true);
			$('input[name="workCompletedInTimeline"][value="Yes"]').prop('checked', true);

			// Remove validations from submitted
			$('#UCCCAmount').rules('remove');
			$('#UCCCAmount').removeClass('error');
			$('#UCCCAmount').siblings('div.text-danger').remove();

			// Add validations for not-submitted
			/* $('#UCCCReason, #panalAction').each(function () {
				$(this).rules('add', {
					 required: true,
 	       	        minlength: 3,
	   	       	        maxlength: 75,
	   	       	  alphanumericWithPeroidAndHyphen: true,
	   		             noEdgeSpaces: true,
	   		             singleSpaceOnly: true,
	   		             noConsecutiveSpecials:true,
					messages: {
						required: '<liferay-ui:message key="please-enter-reason" />',
						required: '<liferay-ui:message key="please-enter-panel-action" />',
		 	        	  minlength: "<liferay-ui:message key='please-enter-min-3-characters' />",
		     	          maxlength: "<liferay-ui:message key='please-enter-max-75-characters' />",
					}
				});
			}); */
			
			// Validation for UCCCReason
			$('#UCCCReason').rules('add', {
			    required: true,
			    minlength: 3,
			    maxlength: 75,
			    alphanumericWithPeroidAndHyphen: true,
			    noEdgeSpaces: true,
			    singleSpaceOnly: true,
			    noConsecutiveSpecials: true,
			    messages: {
			        required: '<liferay-ui:message key="please-enter-reason" />',
			        minlength: '<liferay-ui:message key="please-enter-min-3-characters" />',
			        maxlength: '<liferay-ui:message key="please-enter-max-75-characters" />'
			    }
			});

			// Validation for panalAction
			$('#panalAction').rules('add', {
			    required: true,
			    minlength: 3,
			    maxlength: 75,
			    alphanumericWithPeroidAndHyphen: true,
			    noEdgeSpaces: true,
			    singleSpaceOnly: true,
			    noConsecutiveSpecials: true,
			    messages: {
			        required: '<liferay-ui:message key="please-enter-panel-action" />',
			        minlength: '<liferay-ui:message key="please-enter-min-3-characters" />',
			        maxlength: '<liferay-ui:message key="please-enter-max-75-characters" />'
			    }
			});


			/* $('#panalActionDoc').rules('add', {
				required: true,
				accept: 'application/pdf',
				messages: {
					required: '<liferay-ui:message key="please-upload-pdf" />',
					accept: '<liferay-ui:message key="only-pdf-allowed" />'
				}
			}); */
			
		}
	}

	// Toggle Revised Completion Date field
	function toggleExtensionField() {
		const isYes = $('input[name="UCCCExtension"]:checked').val() === 'Yes';
		const $field = $('#UCCCRevisedCompletionDate').closest('.col-md-6');

		if (isYes) {
			$field.show();
		} else {
			$field.hide();
			$('#UCCCRevisedCompletionDate').val('');
		}
	}
	
	 function formatDateForInput(dateStr) {
         if (!dateStr) return '';
         const date = new Date(dateStr);
         return date.toISOString().split('T')[0];
     }
	 
	
});

function reset_uc_cc_status() {

	 if(!isEditMode){
	   	 resetAllFields();
	   	const previewContainer = document.getElementById("panalActionDocPreviewContainer");
	    const previewLink = document.getElementById("panalActionDocPreviewLink");
	    const errorSpan = document.getElementById("panalActionDocError");
	    const hiddenInput = document.getElementById("hiddenPanelActionDoc");
	    const hiddenInput2 = document.getElementById("panalActionDocHiddenInput");
	    const fileInput = document.getElementById("panalActionDoc");
	    const fileLabel = document.querySelector("label[for='panalActionDoc']");

	    if (previewContainer) previewContainer.style.display = "none";
	    if (previewLink) {
	        previewLink.href = "#";
	        previewLink.textContent = "";
	    }
	    if (errorSpan) {
	        errorSpan.textContent = "";
	        errorSpan.style.display = "none";
	    }
	    if (hiddenInput) hiddenInput.value = "";
	    if (hiddenInput2) hiddenInput2.value = "";
	    if (fileInput) fileInput.value = "";
	    if (fileLabel) fileLabel.textContent = "Choose file"; // Adjust for localization if needed

	    clearInputFile("panalActionDoc");
	 }
    
}


</script>
