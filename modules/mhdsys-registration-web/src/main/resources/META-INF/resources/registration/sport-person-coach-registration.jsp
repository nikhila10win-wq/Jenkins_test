<%@ include file="/init.jsp" %>

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div
						class="card-header d-flex align-item-center justify-content-between">
						<h5>
							<liferay-ui:message key="sport-person-coach-application" />
						</h5>
						<h5>*<liferay-ui:message key="indicates-mandotory-fields" />
						</h5>
					</div>
					<form id="sport-person-coach-reg" enctype="multipart/form-data">
						<input type="hidden" class="form-control" name="userType"
							id="userType" value="${userType}" />
						<div class="card-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-level" /><sup
											class="text-danger">*</sup></label> <select class="form-control"
											name="competitionLevel" id="competitionLevel">
											<option value=""><liferay-ui:message key="select" /></option>
											<c:forEach var="competitionLevel"
												items="${competitionLevels}">
												<option value="${competitionLevel.competitionLevelMasterId}">${competitionLevel.name_en}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<!-- Competition Name -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-name" /><sup
											class="text-danger">*</sup></label> <input type="text"
											class="form-control" name="competitionName"
											id="competitionName" />
									</div>
								</div>

								<!-- Competition Place -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-place" /><sup
											class="text-danger">*</sup></label> <input type="text"
											class="form-control" name="competitionPlace"
											id="competitionPlace" />
									</div>
								</div>
							</div>
							<div class="row">
								<!-- Participation Year -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="participation-year" /><sup
											class="text-danger">*</sup></label> <select class="form-control"
											name="participationYear" id="participationYear">
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="2019-20" >2019-20</option>
											<option value="2020-21" >2020-21</option>
											<option value="2021-22" >2021-22</option>
											<option value="2022-23" >2022-23</option>
											<option value="2023-24" >2023-24</option>
										</select>
									</div>
								</div>

								<!-- Sport Name -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="sport-name" /><sup
											class="text-danger">*</sup></label> 
											<select class="form-control" name ="sportsName" id="sportsName" >
												<option value="">Select</option>
												<c:forEach var="sportsMaster" items="${sportsMaster}">
													<option value="${sportsMaster.sportMasterId}">${sportsMaster.name_en}</option>
												</c:forEach>
									</select>
									</div>
								</div>

								<!-- Medal Received -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="medal-recieved" /><sup
											class="text-danger">*</sup></label> 
											<select class="form-control" name="medalRecieved"
												id="medalRecieved">
												<option value=""><liferay-ui:message key="select" /></option>
												<option value="Gold">Gold</option>
												<option value="Silver">Silver</option>
												<option value="Bronze">Bronze</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<!-- Category -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="category" /><sup
											class="text-danger">*</sup></label> 
										<select class="form-control" name="category">
										<option value="">Select</option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.categoryMasterId}">${category.name}</option>
										</c:forEach>
									</select>
									</div>
								</div>

								<!-- Country of Competition -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message
												key="country-of-competition" /><sup
											class="text-danger">*</sup></label> <select
											class="form-control" name="countryOfCompetition"
											id="countryOfCompetition">
											<option value="India">India</option>
										</select>
									</div>
								</div>

								<!-- City of Competition -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="city-of-competition" /><sup
											class="text-danger">*</sup></label>
										<select class="form-control" name="cityOfCompetition"
											id="cityOfCompetition">
											<option value=""><liferay-ui:message key="select" /></option>
										<c:forEach var="district" items="${districts}">
											<option value="${district.districtId}">${district.districtName_en}</option>
										</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<!-- Competition Start Date -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message
												key="competition-start-date" /><sup
											class="text-danger">*</sup></label> <input type="date"
											class="form-control" name="competitionStartDate"
											id="competitionStartDate" />
									</div>
								</div>

								<!-- Competition End Date -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-end-date" /></label>
										<input type="date" class="form-control"
											name="competitionEndDate" id="competitionEndDate" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message
												key="competition-certificate" /></label>
										<div class="custom-file">
											<input type="file" class="custom-file-input"
												id="competitionCertificate" name="competitionCertificate"
												onchange="handleFileUpload(event, 'competitionCertificate', 'filePreviewContainer', 'filePreviewLink', 'deleteButton')"
												accept=".pdf"> <label class="custom-file-label"
												for="customFile"><liferay-ui:message
													key="choose-file" /></label>
										</div>

										<!-- Preview and Delete Section -->
										<div class="competitionCertificateid d-none mt-3"
											id="filePreviewContainer">
											<a class="competitionCertificateCls" id="filePreviewLink"
												href="" target="_blank"
												style="flex-grow: 1; text-decoration: none;"></a>
											<button type="button" id="deleteButton"
												class="dltcompetitionCertificateBtn close"
												aria-label="Close"
												onclick="deleteFile('filePreviewContainer', 'competitionCertificate')">
												<span aria-hidden="true" class="text-danger"><em
													class="bi bi-x-circle-fill"></em></span>
											</button>
										</div>

									</div>
								</div>
								
							</div>
							<c:if test="${userType eq 'sportsPerson'}">
								<div class="row">
									<!-- Highest Performance -->
									<div class="col-md-4">
										<div class="form-group">
											<label><liferay-ui:message key="highest-performance" /><sup
												class="text-danger">*</sup></label>
											<input type="text" class="form-control"
												name="highestPerformance" id="highestPerformance" />
										</div>
									</div>
									<!-- Name of Coach -->
									<div class="col-md-4">
										<div class="form-group">
											<label><liferay-ui:message
													key="name-of-coach-during-competition" /><sup
												class="text-danger">*</sup></label> <input
												type="text" class="form-control" name="coachName"
												id="coachName" />
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${userType eq 'coach'}">	
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-sports-person-participated"/></label>
										<input type="text" class="form-control" name="sportsPersons" id="highestPerformance"/>
									</div>
								</div>
								
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-medals-recieved-to-sports-person"/></label>
										<input type="text" class="form-control" name="noOfMedals" id="noOfMedals"/>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label ><liferay-ui:message key="separate-undertaking"/></label>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="separateUndertaking" name="separateUndertaking" onchange="handleFileUpload(event, 'separateUndertaking', 'separateUndertakingfilePreviewContainer', 'separateUndertakingfilePreviewLink', 'separateUndertakingdeleteButton')" accept=".pdf">
											<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
										</div>
										
									 <!-- Preview and Delete Section -->
									    <div class="separateUndertakingid d-none mt-3" id="separateUndertakingfilePreviewContainer">
									        <a class="separateUndertakingCls" id="separateUndertakingfilePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
									        <button type="button" id="separateUndertakingdeleteButton"  class="dltseparateUndertakingBtn close" aria-label="Close"  onclick="deleteFile('separateUndertakingfilePreviewContainer', 'separateUndertaking')" >
									       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									   		</button>
									    </div>
										 
									 </div>
								</div>
								
							</c:if>
							<!-- Add/Remove Buttons -->
							<div class="text-right mt-3">
								<button type="button" class="btn btn-primary add-more" onclick="addSportCoachRegistration(event)" title="<liferay-ui:message key="to-add-more-competition-details"/>">
									<liferay-ui:message key="add-more" />
								</button>
							</div>
							<!--datatable append here  -->
								<div class="universal-table"  id="sport-person-coach-div"></div>
							<!--datatable  -->
							<div class="card-footer bg-transparent text-right p-4">
								<button class="btn btn-primary" type="button" >
									<liferay-ui:message key="save" />
								</button>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="saveAwardsApplication" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="exampleModalLongTitle">
					<liferay-ui:message key="awards-application" />
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
								width="50px" class="my-3"> <span class="text-primary"></span><sup><em
								class="bi bi-copy mx-2"></em></sup>
							<liferay-ui:message key="awards-application-sent-successfully" />
							</p>
							<p id="success-application"></p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<a href="" type="button" id="closeModal"
					class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal"
					onclick="closeModal()"><liferay-ui:message key="close" /></a>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for awards application for sports person -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>

<script>
	$(document).ready(function() {
		
		addValidationMethods();
		
		// jQuery validation start
		$('#sport-person-coach-reg').validate({
		    
		    onkeyup: function (element) {
		        $(element).valid();
		    },
		    onchange: function (element) {
		        $(element).valid();
		    },
		    
		    rules: {
		        "competitionLevel": {
		            required: true,
		        },
		        "competitionName": {
		            required: true,
		        },
		        "competitionPlace": {
		            required: true,
		        },
		        "participationYear": {
		            required: true,
		        },
		        "sportsName": {
		            required: true,
		            maxlength: 100
		        },
		        "medalRecieved": {
		            required: true,
		            maxlength: 50
		        },
		        "category": {
		            required: true,
		            maxlength: 100
		        },
		        "countryOfCompetition": {
		            required: true,
		        },
		        "cityOfCompetition": {
		            required: true,
		        },
		        "competitionStartDate": {
		            required: true,
		        },
		        "competitionEndDate": {
		            required: true,
		        },
		        "competitionCertificate": {
		        	accept: "application/pdf",
					filesize: 2 * 1024 * 1024,
					filexssFilter: true,
					required: true,
		        },
		        "coachName":{
		        	required: true,
		        }
		    },
		    messages: {
		        "competitionLevel": {
		            required: "<liferay-ui:message key='please-select-competition-level' />",
		        },
		        "competitionName": {
		            required: "<liferay-ui:message key='please-enter-competition-name' />",
		        },
		        "competitionPlace": {
		            required: "<liferay-ui:message key='please-enter-competition-place' />",
		        },
		        "participationYear": {
		            required: "<liferay-ui:message key='please-select-participation-year' />",
		        },
		        "sportsName": {
		            required: "<liferay-ui:message key='please-enter-sports-name' />",
		            maxlength: "<liferay-ui:message key='sports-name-max-length-exceeded' />"
		        },
		        "medalRecieved": {
		            required: "<liferay-ui:message key='please-enter-medal-received' />",
		            maxlength: "<liferay-ui:message key='medal-max-length-exceeded' />"
		        },
		        "category": {
		            required: "<liferay-ui:message key='please-enter-category' />",
		            maxlength: "<liferay-ui:message key='category-max-length-exceeded' />"
		        },
		        "countryOfCompetition": {
		            required: "<liferay-ui:message key='please-select-country-of-competition' />",
		        },
		        "cityOfCompetition": {
		            required: "<liferay-ui:message key='please-select-city-of-competition' />",
		        },
		        "competitionStartDate": {
		            required: "<liferay-ui:message key='please-enter-competition-start-date' />",
		        },
		        "competitionEndDate": {
		            required: "<liferay-ui:message key='please-enter-competition-end-date' />",
		        },
		        "competitionCertificate": {
		        	accept: "<liferay-ui:message key="please-choose-valid-file"/>",
					filesize: "<liferay-ui:message key="maximum-file-size-is-2-mb"/>",
					required: "<liferay-ui:message key='please-choose-a-file'/>"
					
		        },
		        "coachName": {
		            required: "<liferay-ui:message key='please-enter-coach-name' />",
		        },
		    },
		});
		// jQuery validation end

		
	
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
	
});
	
	
function addSportCoachRegistration(event){debugger
	var form = $("#sport-person-coach-reg")[0];
       var formData = new FormData(form);
       if (event) {
        event.preventDefault(); // Stops the default form submission behavior
    }
        
	   if($('#sport-person-coach-reg').valid()){
        $.ajax({
            type: "POST",
            url: '${saveSportPersonRegistrationURL}',
            data: formData,
            enctype: 'multipart/form-data',
            contentType : false,
    		cache : false,
    		processData : false,
            success: function(data) {
                console.log("data: " + data);
            },
            complete: function(data) {debugger
                console.log("data: " + data.responseText);
                $("#sport-person-coach-div").html(data.responseText.trim());

                // Initialize DataTable
                $('#sport-person-coach-list').DataTable({
                    "sPaginationType": "full_numbers",
                    "ordering": false,
                    "bLengthChange": true,
                    "pageLength": 10,
                    "destroy": true,
                    "bInfo": true,
                    "searching": false,
                    "responsive": true,
        			"autoWidth": false,
                });
            }
        });
        
	}
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
	
</script>