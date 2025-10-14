<%@ include file="/init.jsp" %>
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.js"></script>
 -->
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5><liferay-ui:message key="competition-application"/></h5>	
						<div>
						  <a href="/group/guest/competition-initiated-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn">  <i class="bi bi-arrow-left-circle mr-1"></i> <liferay-ui:message key="back" /> </a>
						</div>					
					</div>
					
				<form id="pt-teacher" enctype="multipart/form-data"> 
					<div class="card-body">
					<div class="card card-background p-0">
					 <div class="card-header header-card"><liferay-ui:message key="competition-application"/></div>
					 <div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="sports-name"/></label>
									<input type="text" class="form-control"  name="sportName"  value="${competitionInitiation.sportName }" readonly/>
									<input type="hidden" class="form-control"  name="competitionInitiationId"  value="${competitionInitiation.competitionInitiationId }" readonly/>
									<input type="hidden" class="form-control"  name="ptTeacherApplicationId"  value="${ptTeacherApplication.ptTeacherApplicationId }" readonly/>
									<input type="hidden" class="form-control"  name="participantUserId"  id="participantUserId" value="" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="category"/></label>
									<input type="text" class="form-control"  name="category"  value="${competitionInitiation.categoryName }" readonly/>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-md-6">
							   <div class="form-group">
									<label><liferay-ui:message key="age-group" /></label>
									<div class="d-flex mt-2">
									<div class="radio-text">
										<input type="radio" class="radio-btn" name="currentYear" id="mandatoryYearYes"
											value="1"  /> <label for="mandatoryYearYes"><liferay-ui:message key="Under 14" /></label>
									</div>
									
									
									<div class="radio-text">
										<input type="radio" class="radio-btn" name="currentYear" id="mandatoryYearNo"
											value="0" /> <label for="mandatoryYearNo"><liferay-ui:message key="Under 17" /></label>
									</div>
										
									<div>
										<input type="radio" class="radio-btn" name="currentYear" id="mandatoryYearNo"
											value="0" /> <label for="mandatoryYearNo"><liferay-ui:message key="Under 19" /></label>
									</div>
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="fees-for-competition"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="feesForCompetition" name="feesForCompetition"  value="${competitionInitiation.fees}" readonly />
								</div>
							</div>
							
							
						</div>
						
						<%-- <div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees-category-as-per-count-of-total-student"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  id="affiliationFeesByTotalCount" name="affiliationFeesByTotalCount"  value="${competitionInitiation.affiliationFeesByTotalCount}" readonly />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees-category-as-per-category"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  id="affiliationFeesByCategory" name="affiliationFeesByCategory"  value="${competitionInitiation.affiliationFeesByCategory }" readonly />
								</div>
							</div>
						</div> --%>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="count-of-team-or-ndividual"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="countOfTeamOrIndividual"  name="countOfTeamOrIndividual" 
									<c:if test="${mode == 'view'}">readonly</c:if> value="${ptTeacherApplication.countOfTeamOrIndividual }"  />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="fees"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="fees" name="fees"  value="${ptTeacherApplication.fees }"  readonly/>
								</div>
							</div>
							
							
						</div>
						<div class="row">
						<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="affiliationFees" name="affiliationFees"  value="${ptTeacherApplication.affiliationFees }"  readonly/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="total-fees"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"   id="totalFees" name="totalFees"  value="${ptTeacherApplication.totalFees }" readonly />
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
								 <c:if test="${mode eq 'add'}">
									<label ><liferay-ui:message key="fees-payment-receipt-pdf-only-2-mb"/></label>
									
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="feesPaymentReciept" name="feesPaymentReciept" onchange="handleFileUpload(event)" accept=".pdf,.jpeg,.jpg,.png,.bmp">
										<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
									</div>
									
									
									
								 <!-- Preview and Delete Section -->
								    <div class="feesReceiptid d-none mt-3" id="filePreviewContainer">
								        <a class="feesReceiptCls text-truncate" id="filePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								        <button type="button" id="deleteButton"  class="dltFeesReceiptBtn close" aria-label="Close"  onclick="deleteFile()" >
								       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								   		</button>
								    </div>
									 </c:if>
									 <div style="margin-top: 10px;">
										 <c:if test="${mode eq 'view' or mode eq 'editParticipant'}">
											 <a  href="${ptTeacherApplication.fileURL }" target="_blank" style="flex-grow: 1; text-decoration: none;">${ptTeacherApplication.fileEntryName}</a>
											 <input type="hidden" class="form-control"  name="feesRecieptFileEntryId"  value="${ptTeacherApplication.feesRecieptFileEntryId }"/>
										 </c:if>
										 
									 </div>
								 </div>
							</div>
							</div>
							 <c:if test="${(mode eq 'view' and ptTeacherApplication.status == 5) || (mode eq 'editParticipant' and ptTeacherApplication.status == 4)}">
								<div class="col-md-4">
									<div class="form-group position-relative">
							            <label><liferay-ui:message key="participant-student"/><sup class="text-danger">*</sup></label>
							            <input type="text" 
							                   class="form-control" 
							                   id="participantName" 
							                   name="participantName" 
							                   oninput="searchParticipant(this.value)" 
							                   value="${ptTeacherApplication.participantName}" 
							                   autocomplete="off"
							                   <c:if test="${mode == 'view' and (ptTeacherApplication.status == 5|| ptTeacherApplication.status == 7 || ptTeacherApplication.status == 8)}">readonly</c:if> />
							
							            <!-- Autocomplete suggestions dropdown -->
							            <div id="participantSuggestions" class=""></div>
							        </div>
								</div>
							</c:if>
						</div>
						</div>
					</div>
					
				<%-- 	<div class="card-footer bg-transparent text-right p-4">
						<c:if test="${mode eq 'view'}">
							<a href="/group/guest/pt-teacher-requested-list" type="button"  class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
						</c:if>
						<c:if test="${mode eq 'add' or mode eq 'editParticipant'}">
							<a href="/group/guest/competition-initiated-list" type="button"  class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
							<button class="btn btn-primary" onclick="savePTTeacherApplicationForm(event)"><liferay-ui:message key="save" /></button>
						</c:if>
					</div> --%>
					
					<c:if test="${mode ne 'view'}">
					<div class="card-footer bg-transparent text-right p-4">
						 <div class="d-flex justify-content-end">
							   <a href="/group/guest/competition-dashboard" class="btn btn-secondary maha-save-btn" id="modalCloseBtn"> <liferay-ui:message key="cancel"/></a>
					            
					           <c:if test="${mode eq 'add' or mode eq 'editParticipant'}"> <button type="button" class="btn btn-primary reset-btn" id="reset-btn"> <liferay-ui:message key="reset" /></button> </c:if>
							    
							   <c:if test="${mode eq 'add' or mode eq 'editParticipant'}"> <button class="btn btn-primary" onclick="savePTTeacherApplicationForm(event)"><liferay-ui:message key="save" /></button> </c:if>
						</div>
					</div>
				</c:if>
				</form>
					
				</div>
			</div>
		</div>
	</div>
</div>


<!-- modal popup for add competition -->
<div class="modal fade" id="savePTTeacerFormModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="pt-teacher-application-form"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <liferay-ui:message key="pt-teacher-application-has-been-successfully-submitted"/></p>
									<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/pt-teacher-requested-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for add competition -->

<script>

$(document).ready(function () {
	addValidationMethods();
    // Add a change event to all checkboxes with the class "ageGroupCheckbox"
    $('.selfDeclaration').on('change', function () {
        if ($(this).is(':checked')) {
            $(this).val('true'); // Set value to true if checked
        } else {
            $(this).val('false'); // Set value to false if unchecked
        }
    });
    
 // Get references to the input fields
    const $countOfTeamOrIndividual = $("#countOfTeamOrIndividual");
    const $fees = $("#fees");
    const $affiliationFees = $("#affiliationFees");
    const $totalFees = $("#totalFees");

    // Read-only values
    const feesForCompetition = parseFloat($("#feesForCompetition").val()) || 0;
    const affiliationFeesByCategory = parseFloat($("#affiliationFeesByCategory").val()) || 0;

    // Event listener for input changes
    $countOfTeamOrIndividual.on("input", function () {
        const countOfTeam = parseFloat($(this).val()) || 0;

        // Calculate fees and affiliation fees
        const calculatedFees = countOfTeam * feesForCompetition;
        const calculatedAffiliationFees = countOfTeam * affiliationFeesByCategory;

        // Calculate total fees
        const calculatedTotalFees = calculatedFees + calculatedAffiliationFees;

        // Update the values in the respective fields
        $fees.val(calculatedFees.toFixed(2));
        $affiliationFees.val(calculatedAffiliationFees.toFixed(2));
        $totalFees.val(calculatedTotalFees.toFixed(2));
    });
    
 // validation
      $('#pt-teacher').validate({
 		onkeyup: function (element) {
 			if (element.type !== "file") {
 				$(element).valid();
 			}
 		  },
 		onchange: function (element) {
 			    $(element).valid();
 		  },
 		rules:{
 			countOfTeamOrIndividual: {
 				required:true,
 				pattern: /^\d+$/,
 				minlength:1,
 				maxlength:5,
 			},
 			feesPaymentReciept: { 						
				accept: "application/pdf",
				filesize: 2 * 1024 * 1024,
				filexssFilter: true,
			}
 			
 		},messages:{
 			countOfTeamOrIndividual: {
 				required:"<liferay-ui:message key="please-enter-count-of-team-or-individual"/>",
 				pattern: "<liferay-ui:message key="please-enter-valid-count"/>",
 				minlength:"<liferay-ui:message key="minimum-length-is-1"/>",
 				maxlength:"<liferay-ui:message key="maximum-length-is-5"/>"
 			},
 			feesPaymentReciept: { 						
				accept: "<liferay-ui:message key="please-choose-valid-file"/>",
				filesize: "<liferay-ui:message key="maximum-file-size-is-2-mb"/>"
			}
 			
 		},
 	}); 
//validation end
});

$("#reset-btn").on("click", function () {
    // Reset only enabled and non-readonly fields
    $('#pt-teacher')
        .find(':input')
        .not(':disabled, [readonly]')
        .each(function () {
            if (this.type === 'checkbox' || this.type === 'radio') {
                this.checked = false;
        	}else {
                $(this).val('');
            }
        });

    // Reset validation
    $('#pt-teacher').validate().resetForm();
    deleteFile();
});


function savePTTeacherApplicationForm(event){debugger
	
	event.preventDefault();
	if (!$("#pt-teacher").valid()) {
	    return false; 
	}
	
	var form = $("#pt-teacher")[0];
	var formData = new FormData(form);
	var mode = '${mode}';
	var fileInput = document.getElementById("feesPaymentReciept");
	if (fileInput) {
	    if (fileInput.files && fileInput.files.length > 0) {
	 	var feesPaymentReciept = document.getElementById("feesPaymentReciept").files[0];
			if (feesPaymentReciept) {
		        console.log("Selected photo file: " + feesPaymentReciept.name);
		        formData.append('feesPaymentReciept', feesPaymentReciept);
		    	}
		  	}
		}
		if (event) {
	        event.preventDefault(); // Stops the default form submission behavior
	    }
		
  $.ajax({
        type: "POST",
        url: '${ptTeacherURL}' ,
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
        	if(data.pTTeacherApplication == true){
        		var applicationNumber = data.applicationNumber;
        		var msg = '<liferay-ui:message key="application-number-is"/> ' + applicationNumber;
        	    $('#success-application').html(msg);
        		  $("#savePTTeacerFormModel").modal('show');  
        	}else{
        		var msg = '<liferay-ui:message key="the-pt-teacher-application-is-unsucessfull"/>';
        		 $("#savePTTeacerFormModel").modal('show'); 
        	}
    	 }
       
    }); 
};

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
}

function closeModal() {debugger
    $("#savePTTeacerFormModel").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}

function handleFileUpload(event) {debugger
    const fileInput = event.target;
    const file = fileInput.files[0]; // Get the uploaded file
    const previewContainer = document.getElementById('filePreviewContainer');
    const previewLink = document.getElementById('filePreviewLink');
    const deleteButton = document.getElementById('deleteButton');

    if (file && $('#feesPaymentReciept').val() != '' && $('#feesPaymentReciept').valid()) {   
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
    const fileInput = document.getElementById('feesPaymentReciept');

    // Reset file input
    fileInput.value = "";
	$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("choose-file"); 
    // Hide the preview container
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}

//search participant

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
                	console.log("participant, ",participant.name)
                	var div = document.createElement('div');
                    div.textContent = participant.name;  
                    $('#participantUserId').val(participant.userId)
                    div.onclick = function() {
                        selectParticipant(participant.name); 
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



</script>