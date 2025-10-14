<%@ include file="/init.jsp" %>
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5><liferay-ui:message key="result-upload"/></h5>						
						<div><a href="/group/guest/competition-dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>s
					</div>
				<form id="result_upload_form" enctype="multipart/form-data"> 
						<input type="hidden" class="form-control"  name="competitionInitiationId"  value="${competitionInitiationId }" readonly/>
						<input type="hidden" class="form-control"  name="ptTeacherApplicationId"  value="${ptTeacherApplicationId }" readonly/>
						<input type="hidden" class="form-control"  name="competitionScheduledId"  value="${competitionScheduledId }" readonly/>
					
					<div class="card-body">
						<div class="row">
							<div class="col-md-4">
									<div class="form-group ">
							            <label><liferay-ui:message key="winner-name"/><sup class="text-danger">*</sup></label>
							            <input type="text" class="form-control" readonly value="${competitionSchedule.competitionName}"/>
							        </div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
								    <label for="genderYes">
								        <liferay-ui:message key="is-participant-reached-ground" />
								        <sup class="text-danger">*</sup>
								    </label>
								    <div class="d-inline-block mr-3">
								        <input type="radio" id="reachedGroundYes" name="reachedGround" value="1" 
								        <c:if test="${mode == 'view' && resultUpload.reachedGround}">checked</c:if> 
								        <c:if test="${mode == 'view'}">disabled</c:if>/>
								        <label for="reachedGroundYes"><liferay-ui:message key="yes"/></label>
								    </div>
								    <div class="d-inline-block">
								        <input type="radio" id="reachedGroundNo" name="reachedGround" value="2" 
								        <c:if test="${mode == 'view' && !resultUpload.reachedGround}">checked</c:if>
								        <c:if test="${mode == 'view'}">disabled</c:if>/>
								        <label for="reachedGroundNo"><liferay-ui:message key="no"/></label>
								    </div>
								</div>

							</div>
							<div class="col-md-4">
									<div class="form-group position-relative">
							            <label><liferay-ui:message key="winner-name"/><sup class="text-danger">*</sup></label>
							            <input type="text" class="form-control" id="winnerName" 
							                   name="winnerName" oninput="searchParticipant(this.value,'winnerNameSuggestion','winnerName')" value="${resultUpload.winnerName}" 
							                   <c:if test="${mode == 'view'}">readonly</c:if> autocomplete="off"/>
							
							            <!-- Autocomplete suggestions dropdown -->
							            <div id="winnerNameSuggestion" class="winnerNameSuggestionCls"></div>
							        </div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
									<div class="form-group position-relative">
							            <label><liferay-ui:message key="first-runner-up-name"/><sup class="text-danger">*</sup></label>
							            <input type="text" class="form-control" id="firstRunnerUpName" 
							                   name="firstRunnerUpName" oninput="searchParticipant(this.value,'firstRunnerUpNameSuggestion','firstRunnerUpName')" 
							                   value="${resultUpload.firstRunnerUpName}" autocomplete="off" <c:if test="${mode == 'view'}">readonly</c:if>/>
							
							            <!-- Autocomplete suggestions dropdown -->
							            <div id="firstRunnerUpNameSuggestion" class="firstRunnerUpNameSuggestionCls"></div>
							        </div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="first-runner-up-result-score-sheet-pdf-only-1-mb"/><sup class="text-danger">*</sup></label>
									<c:if test="${mode eq 'add'}">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="firstRunnerUpScoreSheet" name="firstRunnerUpScoreSheet" 
										onchange="handleFileUpload(event,'firstRunnerUpScoreSheet','firstfilePreviewContainer','firstfilePreviewLink','firstdeleteButton')" accept=".pdf">
										<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
									</div>
									
								 <!-- Preview and Delete Section -->
								    <div class="firstScoreSheetId d-none mt-3" id="firstfilePreviewContainer">
								        <a class="firstScoreSheetCls" id="firstfilePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								        <button type="button" id="firstdeleteButton"  class="dltfirstScoreSheetBtn close" aria-label="Close"  onclick="deleteFile('firstfilePreviewContainer','firstRunnerUpScoreSheet')" >
								       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								   		</button>
								    </div>
									 </c:if>
									 <div style="margin-top: 10px;">
										 <c:if test="${mode eq 'view'}">
											 <a  href="${resultUpload.firstFileURL }" target="_blank" style="flex-grow: 1; text-decoration: none;">${resultUpload.firstFileEntryName }</a>
											 <input type="hidden" class="form-control"  name="firstScoreSheetFileEntryId"  value=""/>
										 </c:if>
										 
									 </div>
								 </div>
							</div>
							<div class="col-md-4">
									<div class="form-group position-relative">
							            <label><liferay-ui:message key="second-runner-up-name"/><sup class="text-danger">*</sup></label>
							            <input type="text" class="form-control" id="secondRunnerUpName" 
							                   name="secondRunnerUpName" oninput="searchParticipant(this.value,'secondRunnerUpNameSuggestion','secondRunnerUpName')" 
							                   value="${resultUpload.secondRunnerUpName}"  autocomplete="off" <c:if test="${mode == 'view'}">readonly</c:if>/>
							
							            <!-- Autocomplete suggestions dropdown -->
							            <div id="secondRunnerUpNameSuggestion" class="secondRunnerUpNameSuggestionCls"></div>
							        </div>
							</div>
						</div>
							
						<div class="row">
							<div class="col-md-4">
									<div class="form-group position-relative">
							            <label><liferay-ui:message key="third-runner-up-name"/><sup class="text-danger">*</sup></label>
							            <input type="text" class="form-control" id="thirdRunnerUpName" name="thirdRunnerUpName"
							             oninput="searchParticipant(this.value,'thirdRunnerUpNameSuggestion','thirdRunnerUpName')" 
							             value="${resultUpload.thirdRunnerUpName}" autocomplete="off" <c:if test="${mode == 'view'}">readonly</c:if>/>
							
							            <!-- Autocomplete suggestions dropdown -->
							            <div id="thirdRunnerUpNameSuggestion" class="thirdRunnerUpNameSuggestionCls"></div>
							        </div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="second-and-third-runner-up-result-score-sheet-pdf-only-1-mb"/><sup class="text-danger">*</sup></label>
									<c:if test="${mode eq 'add'}">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="secondAndThirdRunnerUpScoreSheet" name="secondAndThirdRunnerUpScoreSheet" onchange="handleFileUpload(event,'secondRunnerUpScoreSheet','secondfilePreviewContainer','secondfilePreviewLink','seconddeleteButton')" accept=".pdf">
										<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
									</div>
									
								 <!-- Preview and Delete Section -->
								    <div class="secondAndThirdScoreSheetId d-none mt-3" id="secondfilePreviewContainer">
								        <a class="secondAndThirdScoreSheetCls" id="secondfilePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								        <button type="button" id="seconddeleteButton"  class="dltsecondAndThirdScoreSheetBtn close" aria-label="Close"  onclick="deleteFile('secondfilePreviewContainer','secondRunnerUpScoreSheet'	)" >
								       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								   		</button>
								    </div>
									 </c:if>
									 <div style="margin-top: 10px;">
										 <c:if test="${mode eq 'view'}">
											 <a  href="${resultUpload.secondFileURL }" target="_blank" style="flex-grow: 1; text-decoration: none;">${resultUpload.secondFileEntryName }</a>
											 <input type="hidden" class="form-control"  name="secondAndThirdScoreSheetFileEntryId"  value=""/>
										 </c:if>
										 
									 </div>
								 </div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
							    <div class="form-group">
							        <label for="selfDeclaration"><liferay-ui:message key="self-declaration" /><sup class="text-danger">*</sup></label>
							        <input type="checkbox" id="self_Declaration" name="selfDeclaration" 
							         <c:if test="${resultUpload.selfDeclaration && mode eq 'view'}">checked disabled</c:if> class="selfDeclaration" />
							    </div>
							</div>
						</div>
						
					</div>
					
					<div class="card-footer bg-transparent text-right p-4">
						<c:if test="${mode eq 'view' }">
							<a href="/group/guest/uploaded-result-list" type="button"  class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
						</c:if>
						<c:if test="${mode eq 'add' }">
							<a href="/group/guest/scheduled-competition-list" type="button"  class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
							<button class="btn btn-primary" onclick="saveResultUpload(event)"><liferay-ui:message key="save" /></button>
						</c:if>
					</div>
				</form>
					
				</div>
			</div>
		</div>
	</div>
</div>


<!-- modal popup for add competition -->
<div class="modal fade" id="saveResultUploadModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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
                                    <liferay-ui:message key="result-is-uploaded"/></p>
									<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/scheduled-competition-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
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
 $('#result_upload_form').validate({
     onkeyup: function (element) {
         $(element).valid();
     },
     onchange: function (element) {
         $(element).valid();
     },
     rules: {
         winnerName: {
             required: true,
             pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/, // Allows alphabets and spaces, but ensures there is at least one alphabet
         },
         firstRunnerUpName: {
             required: true,
             pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/, // Same for firstRunnerUpName
         },
         secondRunnerUpName: {
             required: true,
             pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/, // Same for secondRunnerUpName
         },
         thirdRunnerUpName: {
             required: true,
             pattern: /^[A-Za-z]+([A-Za-z ]*[A-Za-z])?$/, // Same for thirdRunnerUpName
         },
         reachedGround: {
             required: true,
         },
         firstRunnerUpScoreSheet: { 
        	required: true,
			accept: "application/pdf",
			filesize: 1 * 1024 * 1024,
			filexssFilter: true,
		},
		secondAndThirdRunnerUpScoreSheet: { 
			required: true,
			accept: "application/pdf",
			filesize: 1 * 1024 * 1024,
			filexssFilter: true,
		},
         selfDeclaration: {
             required: true,
        }
     },
     messages: {
         winnerName: {
             required: "<liferay-ui:message key='please-enter-winner-name'/>",
             pattern: "<liferay-ui:message key='please-enter-valid-name'/>"
         },
         firstRunnerUpName: {
             required: "<liferay-ui:message key='please-enter-first-runner-up-name'/>",
             pattern: "<liferay-ui:message key='please-enter-valid-name'/>"
         },
         secondRunnerUpName: {
             required: "<liferay-ui:message key='please-enter-second-runner-up-name'/>",
             pattern: "<liferay-ui:message key='please-enter-valid-name'/>"
         },
         thirdRunnerUpName: {
             required: "<liferay-ui:message key='please-enter-third-runner-up-name'/>",
             pattern: "<liferay-ui:message key='please-enter-valid-name'/>"
         },
         reachedGround: {
        	 required: "<liferay-ui:message key='please-select-reached-ground'/>",
         },
         firstRunnerUpScoreSheet: { 
        	 required: "<liferay-ui:message key='please-choose-a-file'/>",
        	 accept: "<liferay-ui:message key="please-choose-valid-file"/>",
			 filesize: "<liferay-ui:message key="maximum-file-size-is-1-mb"/>"
		},
		secondAndThirdRunnerUpScoreSheet: { 	
			required: "<liferay-ui:message key='please-choose-a-file'/>",
			accept: "<liferay-ui:message key="please-choose-valid-file"/>",
			filesize: "<liferay-ui:message key="maximum-file-size-is-1-mb"/>"
		},
         selfDeclaration: {
        	 required: "<liferay-ui:message key='please-check-self-declaration'/>",
        },
     }
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
}

function saveResultUpload(event){debugger
	var form = $("#result_upload_form")[0];
	var formData = new FormData(form);
	var mode = '${mode}';
		if (event) {
	        event.preventDefault(); // Stops the default form submission behavior
	    }
		if($("#result_upload_form").valid()){
 $.ajax({
        type: "POST",
        url: '${ResultUploadURL}' ,
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
        console.log("Parsed data: ", data,"data.competitionSchedule: ",data.competitionSchedule);
        	if(data.competitionResultUpload == true){
        		var competitionResultUpload = data.competitionResultUpload;
       			 $("#saveResultUploadModel").modal('show'); 

        	}else{
        		var msg = "<liferay-ui:message key="the-result-upload-is-unsucessfull"/>";
        	    //$('#success-application').html(msg);
        		$("#saveResultUploadModel").modal('show'); 
        	}
    	 }
       
    });
};
};
function closeModal() {debugger
    $("#saveResultUploadModel").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}


function handleFileUpload(event,id,filePreviewContainer,filePreviewLink,deleteBtn) {debugger
    const fileInput = event.target;
    const file = fileInput.files[0]; // Get the uploaded file
    const previewContainer = document.getElementById(filePreviewContainer);
    const previewLink = document.getElementById(filePreviewLink);
    const deleteButton = document.getElementById(deleteBtn);

    if (file && $('#'+id).val() != '' ) {   
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
function deleteFile(filePreviewContainer,id) {
    const previewContainer = document.getElementById(filePreviewContainer);
    const fileInput = document.getElementById(id);

    // Reset file input
    fileInput.value = "";
	$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("choose-file"); 
    // Hide the preview container
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}

//search participant

function searchParticipant(query,id,participantName) {debugger
    if (query.length < 2 ) {
        $('#'+id).hide();
        return;
    }
	var form = $("#result_upload_form")[0];
	var formData = new FormData(form);
	formData.append('participantName',$('#'+participantName).val());
	if( $('#'+participantName).valid()){
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
                        selectParticipant(participant,participantName,id); 
                    };
                    fragment.appendChild(div);
                    });
                $('#'+id).html('').append(fragment).show();

                var inputPos = $('#'+participantName).offset(); 
                $('#'+id).css({
                    top: inputPos.top + $('#'+participantName).outerHeight(), 
                    left: inputPos.left, 
                    width: $('#'+participantName).outerWidth() 
                });
            } else {
                $('#'+id).html('<div>No results found</div>').show();
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
      
	}
			});
		}
	}

	function selectParticipant(name, participantName, id) {
		$('#' + participantName).val(name);
		$('#' + id).hide();
	}
</script>