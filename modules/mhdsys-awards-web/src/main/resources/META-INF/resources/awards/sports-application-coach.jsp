
<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<portlet:resourceURL id="<%=AwardsWebPortletKeys.SPORTS_APPLICATION%>" 
var="saveSportsApplicationURL" />

<form id="sports_application" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					
					<!-- <div class="card-header d-flex align-item-center justify-content-between">
						<h5><liferay-ui:message key="sports-application-for-coach"/></h5>						
					</div> -->
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="sports-application-for-coach" /></h5>
						<div><a href="/group/guest/awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
					
					<div class="card-body">
					<input type="hidden" class="form-control" name="applicant" id="applicant" value="coach" />					
						<div id="dynamicFields">
							<div class="row dynamic-row">
								<!-- Competition Level -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="competition-level"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name="competitionLevel[]" id="competitionLevel">
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="competitionLevel" items="${competitionLevels}">
												<option value="${competitionLevel.competitionLevelMasterId}">${competitionLevel.name_en}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<!-- Competition Name -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="competition-name"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="competitionName[]" id="competitionName" />
									</div>
								</div>

								<!-- Competition Place -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="competition-place"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="competitionPlace[]" id="competitionPlace" />
									</div>
								</div>

								<!-- Participation Year -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="participation-year"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name="participationYear[]" id="participationYear" >
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="2009-10" >2009-10</option>
											<option value="2010-11" >2010-11</option>
											<option value="2011-12" >2011-12</option>
											<option value="2012-13" >2012-13</option>
											<option value="2013-14" >2013-14</option>
											<option value="2014-15" >2014-15</option>
											<option value="2015-16" >2015-16</option>
											<option value="2016-17" >2016-17</option>
											<option value="2017-18" >2017-18</option>
											<option value="2018-19" >2018-19</option>
											<option value="2019-20" >2019-20</option>
											<option value="2020-21" >2020-21</option>
											<option value="2021-22" >2021-22</option>
											<option value="2022-23" >2022-23</option>
											<option value="2023-24" >2023-24</option>
										</select>
									</div>
								</div>

								<!-- Sport Name -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="sport-name"/></label>
										<input type="text" class="form-control" name="sportsName[]" id="sportsName" />
									</div>
								</div>

								<!-- Medal Received -->
								<!-- <div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="medal-recieved"/></label>
										<input type="text" class="form-control" name="medalRecieved[]" id="medalRecieved" />
									</div>
								</div> -->

								<!-- Category -->
								<!-- <div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="category"/></label>
										<input type="text" class="form-control" name="category[]" id="category"/>
									</div>
								</div> -->

								<!-- Country of Competition -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="country-of-competition"/></label>
										<input type="text" class="form-control" name="countryOfCompetition[]" id="countryOfCompetition" />
										<!-- <select class="form-control" name="countryOfCompetition[]" id="countryOfCompetition">
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="India">India</option>
										</select> -->
									</div>
								</div>

								<!-- City of Competition -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="city-of-competition"/></label>
										<select class="form-control" name="cityOfCompetition[]" id="cityOfCompetition">
											<option value="">Select</option>
											<c:forEach var="district" items="${districts}">
												<option value="${district.districtId}">${district.districtName_en}</option>
											</c:forEach>
										</select>
										<!-- <select class="form-control" name="cityOfCompetition[]" id="cityOfCompetition">
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="Pune">Pune</option>
											<option value="Mumbai">Mumbai</option>
											<option value="Ahilyanagar">Ahilyanagar</option>
										</select> -->
									</div>
								</div>

								<!-- Competition Start Date -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="competition-start-date"/></label>
										<input type="date" class="form-control" name="competitionStartDate[]" id="competitionStartDate"/>
									</div>
								</div>

								<!-- Competition End Date -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="competition-end-date"/></label>
										<input type="date" class="form-control" name="competitionEndDate[]" id="competitionEndDate"/>
									</div>
								</div>

								<!-- Highest Performance -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-sports-person-participated"/></label>
										<input type="text" class="form-control" name="sportsPersons[]" id="highestPerformance"/>
									</div>
								</div>
								
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-medals-recieved-to-sports-person"/></label>
										<input type="text" class="form-control" name="noOfMedals[]" id="noOfMedals"/>
									</div>
								</div>
								

								<!-- Competition Certificate -->
								<div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="competition-certificate"/></label>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="competitionCertificate" name="competitionCertificate[]" onchange="handleFileUpload(event, 'competitionCertificate', 'filePreviewContainer', 'filePreviewLink', 'deleteButton')" accept=".pdf">
										<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
									</div>
									
								 <!-- Preview and Delete Section -->
								    <div class="competitionCertificateid d-none mt-3" id="filePreviewContainer">
								        <a class="competitionCertificateCls text-truncate" id="filePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								        <button type="button" id="deleteButton"  class="dltcompetitionCertificateBtn close" aria-label="Close"  onclick="deleteFile('filePreviewContainer', 'competitionCertificate')" >
								       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								   		</button>
								    </div>
									 
								 </div>
							</div>
								
								
								<!-- <div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="competition-certificate"/></label>
										<input type="file" class="form-control" name="competitionCertificate[]" id="competitionCertificate"/>
									</div>
								</div> -->

								<!-- Name of Coach -->
								
								
								<div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="separate-undertaking"/></label>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="separateUndertaking" name="separateUndertaking[]" onchange="handleFileUpload(event, 'separateUndertaking', 'separateUndertakingfilePreviewContainer', 'separateUndertakingfilePreviewLink', 'separateUndertakingdeleteButton')" accept=".pdf">
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
								
								
								<!-- <div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="separate-undertaking"/></label>
										<input type="file" class="form-control" name="separateUndertaking[]" id="separateUndertaking"/>
									</div>
								</div> -->
							</div>

							<!-- Add/Remove Buttons -->
							<div class="text-right mt-3">
								<button type="button" class="btn btn-primary add-more"><liferay-ui:message key="to-add-more-competition-details"/></button>
								<!-- <button type="button" class="btn btn-primary remove" style="display: none;">Remove</button> -->
							</div>
						</div>
					</div>
					<div class="card-footer bg-transparent text-right p-4">
						
						<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards';">
                        	<liferay-ui:message key="cancel" />
                    	</button>	
                            		
                         <button class="btn btn-primary" type="button" onclick="location.reload();">
							<liferay-ui:message key="reset" />
						</button>
					
						<button class="btn btn-primary" type="button" onclick="addSportsApplication(event)">
							<liferay-ui:message key="save"/>
						</button>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</form>


<!-- modal popup for awards application for coach -->
<div class="modal fade" id="saveAwardsApplication" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="awards-application"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <liferay-ui:message key="awards-application-sent-successfully"/></p>
                                    <p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for awards application for coach -->





<script>
	$(document).ready(function() {
		
		addValidationMethods();
		
		// jQuery validation start
		$('#sports_application').validate({
		    
		    onkeyup: function (element) {
		        $(element).valid();
		    },
		    onchange: function (element) {
		        $(element).valid();
		    },
		    
		    rules: {
		        "competitionLevel[]": {
		            required: true,
		        },
		        "competitionName[]": {
		            required: true,
		        },
		        "competitionPlace[]": {
		            required: true,
		        },
		        "participationYear[]": {
		            required: true,
		        },
		        "sportsName[]": {
		            required: true,
		            maxlength: 100
		        },
		        "countryOfCompetition[]": {
		            required: true,
		        },
		        "cityOfCompetition[]": {
		            required: true,
		        },
		        "competitionStartDate[]": {
		            required: true,
		        },
		        "competitionEndDate[]": {
		            required: true,
		        },
		        "competitionCertificate[]": {
		        	accept: "application/pdf",
					filesize: 2 * 1024 * 1024,
					filexssFilter: true,
					required: true,
		        },
		        "separateUndertaking[]": {
		        	accept: "application/pdf",
					filesize: 2 * 1024 * 1024,
					filexssFilter: true,
					required: true,
		        }
		    },
		    messages: {
		        "competitionLevel[]": {
		            required: "<liferay-ui:message key='please-select-competition-level' />",
		        },
		        "competitionName[]": {
		            required: "<liferay-ui:message key='please-enter-competition-name' />",
		        },
		        "competitionPlace[]": {
		            required: "<liferay-ui:message key='please-enter-competition-place' />",
		        },
		        "participationYear[]": {
		            required: "<liferay-ui:message key='please-select-participation-year' />",
		        },
		        "sportsName[]": {
		            required: "<liferay-ui:message key='please-enter-sports-name' />",
		            maxlength: "<liferay-ui:message key='sports-name-max-length-exceeded' />"
		        },
		        "countryOfCompetition[]": {
		            required: "<liferay-ui:message key='please-select-country-of-competition' />",
		        },
		        "cityOfCompetition[]": {
		            required: "<liferay-ui:message key='please-select-city-of-competition' />",
		        },
		        "competitionStartDate[]": {
		            required: "<liferay-ui:message key='please-enter-competition-start-date' />",
		        },
		        "competitionEndDate[]": {
		            required: "<liferay-ui:message key='please-enter-competition-end-date' />",
		        },
		        "competitionCertificate[]": {
		        	accept: "<liferay-ui:message key="please-choose-valid-file"/>",
					filesize: "<liferay-ui:message key="maximum-file-size-is-2-mb"/>",
					required: "<liferay-ui:message key='please-choose-a-file'/>"
					
		        },
		        "separateUndertaking[]": {
		        	accept: "<liferay-ui:message key="please-choose-valid-file"/>",
					filesize: "<liferay-ui:message key="maximum-file-size-is-2-mb"/>",
					required: "<liferay-ui:message key='please-choose-a-file'/>"
		        }
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
		
		
		

		$(".add-more").click(function() {
			let dynamicRow = $(".dynamic-row").first().clone();
			dynamicRow.find("input, select").val("");
			dynamicRow.appendTo("#dynamicFields");
			$(".remove").show();
		});

		/* $(document).on("click", ".remove", function() {
			$(this).closest(".dynamic-row").remove();
			if ($(".dynamic-row").length === 1) {
				$(".remove").hide();
			}
		}); */
	});
	
	
	function addSportsApplication(event){debugger
        var formData = new FormData();
        var formArray = [];

        if (event) {
	        event.preventDefault(); // Stops the default form submission behavior
	    }
        if($('#sports_application').valid()){
        $(".dynamic-row").each(function() {
            var formObj = {};
            $(this).find(":input").each(function() {
                var fieldName = $(this).attr("name");
                var fieldValue = $(this).val();

                if (fieldValue !== undefined && fieldValue !== '') {
                    formObj[fieldName] = fieldValue;
                }
            });
            formArray.push(formObj);
        });

        formData.append("formData", JSON.stringify(formArray));

        $.ajax({
            type: "POST",
            url: '${saveSportsApplicationURL}',
            data: formData,
            contentType: false,
            processData: false,
            success: function(data) {
                
            	if(data.awardApplication == true){
              		 $("#saveAwardsApplication").modal('show');
              	}else{
              		var msg = "<liferay-ui:message key="awards-application-unsucessfull"/>";
              		 $("#saveAwardsApplication").modal('show'); 
              	}
            	
            },
            error: function(xhr, status, error) {
                console.error("Error saving sports application: " + error);
                alert("An error occurred while saving the sports application. Please try again.");
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