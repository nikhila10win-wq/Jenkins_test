<%@ include file="/init.jsp" %>


<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5><liferay-ui:message key="competition-scheduling"/></h5>						
						<div><a href="/group/guest/competition-dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
				<form id="competition-schedule_form" enctype="multipart/form-data"> 
					<div class="card-body">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="sports-name"/></label>
									<input type="text" class="form-control"  name="sportName"  value="${competitionInitiation.sportName }" readonly/>
									<input type="hidden" class="form-control"  name="competitionInitiationId"  value="${competitionInitiation.competitionInitiationId }" readonly/>
									<input type="hidden" class="form-control"  name="ptTeacherApplicationId"  value="${ptTeacherApplication.ptTeacherApplicationId }" readonly/>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="category"/></label>
									<input type="text" class="form-control"  name="category"  value="${competitionInitiation.categoryName }" readonly/>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-md-4">
							    <div class="form-group">
							        <label for="ageGroupUnder14"><liferay-ui:message key="age-group-under-14" /></label>
							        <input type="checkbox" id="ageGroupUnder14" name="underForteen" disabled
							         <c:if test="${competitionInitiation.underForteen}">checked </c:if> class="ageGroupCheckbox" />
							    </div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="age-group-under-17"/></label>
									<input type="checkbox"  name="underSeventeen" disabled
									 <c:if test="${competitionInitiation.underSeventeen}">checked </c:if> class="ageGroupCheckbox" />
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="age-group-under-19"/></label>
									<input type="checkbox" name="underNineteen" disabled
									<c:if test="${competitionInitiation.underNineteen}">checked </c:if> id="underNineteen" class="ageGroupCheckbox"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="fees-for-competition"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="feesForCompetition" name="feesForCompetition"  value="${competitionInitiation.fees}" readonly />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees-category-as-per-count-of-total-student"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  id="affiliationFeesByTotalCount" name="affiliationFeesByTotalCount"  value="${competitionInitiation.affiliationFeesByTotalCount}" readonly />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees-category-as-per-category"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  id="affiliationFeesByCategory" name="affiliationFeesByCategory"  value="${competitionInitiation.affiliationFeesByCategory }" readonly />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="competition-name"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  id="competitionName" name="competitionName" 
									 value="${competitionSchedule.competitionName}"  <c:if test="${mode == 'view'}">readonly</c:if>/>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
								    <label for="genderYes">
								        <liferay-ui:message key="gender" />
								        <sup class="text-danger">*</sup>
								    </label>
								    <div>
								        <input type="radio" id="genderYes" name="gender" value="1" <c:if test="${mode == 'view' && competitionSchedule.gender}">checked</c:if>
								        <c:if test="${mode == 'view'}">disabled</c:if> />
								        <label for="genderYes"><liferay-ui:message key="male"/></label>
								    </div>
								    <div>
								        <input type="radio" id="genderNo" name="gender" value="2" <c:if test="${mode == 'view' && !competitionSchedule.gender}">checked</c:if>
								        <c:if test="${mode == 'view'}">disabled</c:if> />
								        <label for="genderNo"><liferay-ui:message key="female"/></label>
								    </div>
								</div>

							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="district-taluka"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="districtOrTaluka" id= "districtOrTaluka" <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value="1" <c:if test="${competitionSchedule.districtOrTaluka == '1'}">selected</c:if>>District</option>
										<option value="2" <c:if test="${competitionSchedule.districtOrTaluka == '2'}">selected</c:if>>Taluka</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="registration-start-date"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control"  id="registartionStartDate" name="registartionStartDate" 
									 value="${competitionSchedule.registrationStartDateStr}"  <c:if test="${mode == 'view'}">readonly</c:if>/>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="registration-end-date"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control"  id="registartionEndDate" name="registartionEndDate"
									  value="${competitionSchedule.registrationEndDateStr}"  <c:if test="${mode == 'view'}">readonly</c:if>/>

								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="player-details-submission-last-date"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control"  id="lastDateOfSubmission" name="lastDateOfSubmission" 
									 value="${competitionSchedule.lastSubmissionDateStr}"  <c:if test="${mode == 'view'}">readonly</c:if>/>
								</div>
							</div>
						</div>
						<div class="row">
						<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="competition-organization-name"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  id="competitionOrganizerName" name="competitionOrganizerName"
									  value="${competitionSchedule.competitionOrganizerName}" <c:if test="${mode == 'view'}">readonly</c:if> />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="competition-organizer-contact-details"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  id="competitionOrganizerContact" name="competitionOrganizerContact"
									  value="${competitionSchedule.competitionOrganizerContact}"  <c:if test="${mode == 'view'}">readonly</c:if>/>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="count-of-team-or-ndividual"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="countOfTeamOrIndividual"  name="countOfTeamOrIndividual" 
									<c:if test="${mode == 'view'}">readonly</c:if> value="${ptTeacherApplication.countOfTeamOrIndividual }"  />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="fees"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="fees" name="fees"  value="${ptTeacherApplication.fees }"  readonly/>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="affiliationFees" name="affiliationFees"  value="${ptTeacherApplication.affiliationFees }"  readonly/>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="total-fees"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"   id="totalFees" name="totalFees"  value="${ptTeacherApplication.totalFees }" readonly />
								</div>
							</div>
							
							
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="fees-payment-receipt"/></label>
									 <div style="margin-top: 10px;">
										 <a  href="${ptTeacherApplication.fileURL }" target="_blank" style="flex-grow: 1; text-decoration: none;">${ptTeacherApplication.fileEntryName}</a>
										 <input type="hidden" class="form-control"  name="feesRecieptFileEntryId"  value="${ptTeacherApplication.feesRecieptFileEntryId }"/>
										 
									 </div>
								 </div>
							</div>
							<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="participant-student"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" id="participantName" name="participantName" 
										readonly
										value="${ptTeacherApplication.participantName }" />
									</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
								
									<label><liferay-ui:message key="sr-no"/><sup class="text-danger">*</sup></label>
									<c:if test="${mode == 'view'}">
										<input type="text" class="form-control"  id="srNumber" name="srNumber" value="${competitionSchedule.srNumber}" readonly>
									</c:if>
									<c:if test="${mode == 'add'}">
										<input type="text" class="form-control"  id="srNumber" name="srNumber" value="${competitionScheduleSize}" readonly>
									</c:if>
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="start-time"/><sup class="text-danger">*</sup></label>
									<input type="time" class="form-control"  id="startTime" name="startTime" oninput="adjustReportingTime()"
									 value="${competitionSchedule.formattedStartTime}" <c:if test="${mode == 'view'}">readonly</c:if> />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="reporting-time"/><sup class="text-danger">*</sup></label>
									<input type="time" class="form-control"  id="reportingTime" name="reportingTime" 
									 value="${competitionSchedule.formattedReportingTime}"  readonly/>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer bg-transparent text-right p-4">
						<c:if test="${mode == 'view'}">
							<a href="/group/guest/scheduled-competition-list" type="button" class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
						</c:if>
						<c:if test="${mode == 'add'}">
							<a href="/group/guest/principal-approved-list" type="button" class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
						</c:if>
						<button class="btn btn-primary" onclick="saveCompetitionScheduleForm(event)"><liferay-ui:message key="save" /></button>
					</div>
				</form>
					
				</div>
			</div>
		</div>
	</div>
</div>


<!-- modal popup for add competition -->
<div class="modal fade" id="saveCompetitionScheduleModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="competition-schedule-form"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <liferay-ui:message key="competition-is-scheduled-successfuly"/></p>
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

$(document).ready(function() {
    // Apply jQuery Validation to the form
    $("#competition-schedule_form").validate({
    	onkeyup: function (element) {
			$(element).valid();
		  },
		onchange: function (element) {
			    $(element).valid();
		  },
        rules: {
            competitionName: {
                required: true
            },
            gender: {
                required: true
            },
            registartionStartDate: {
                required: true
            },
            registartionEndDate: {
                required: true
            },
            lastDateOfSubmission: {
                required: true
            },
            competitionOrganizerName: {
                required: true
            },
            competitionOrganizerContact: {
            	required: true,
            	digits: true,
                minlength: 10,
                maxlength: 10,
            },
            countOfTeamOrIndividual: {
                required: true,
                digits: true,
                maxlength: 5,
            },
            startTime: {
                required: true
            }
        },
        messages: {
            competitionName: {
                required: "<liferay-ui:message key='enter-competition-name'/>"
            },
            gender: {
                required: "<liferay-ui:message key='select-gender'/>"
            },
            registartionStartDate: {
                required: "<liferay-ui:message key='select-registration-start-date'/>"
            },
            registartionEndDate: {
                required: "<liferay-ui:message key='select-registration-end-date'/>"
            },
            lastDateOfSubmission: {
                required: "<liferay-ui:message key='select-last-date-of-submission-date'/>"
            },
            competitionOrganizerName: {
                required: "<liferay-ui:message key='enter-competition-organizer-name'/>"
            },
            competitionOrganizerContact: {
            	required: "<liferay-ui:message key='enter-competition-organizer-contact'/>",
            	 digits: "<liferay-ui:message key='please-enter-only-numeric-values' />",
                 minlength: "<liferay-ui:message key='mobile-number-must-be-10-digits' />",
                 maxlength: "<liferay-ui:message key='mobile-number-must-be-10-digits' />"
            },
            countOfTeamOrIndividual: {
                required: "<liferay-ui:message key='enter-count-of-team-or-individual'/>",
                digits: "<liferay-ui:message key='please-enter-only-numeric-values' />",
                maxlength: "<liferay-ui:message key='maximum-5-digits-are-allowed' />",
            },
            startTime: {
                required: "<liferay-ui:message key='select-start-time'/>"
            }
        },
    });
});

    

function saveCompetitionScheduleForm(event){debugger
	var form = $("#competition-schedule_form")[0];
	var formData = new FormData(form);
	var mode = '${mode}';
		if (event) {
	        event.preventDefault(); // Stops the default form submission behavior
	    }
		if($("#competition-schedule_form").valid()){
 $.ajax({
        type: "POST",
        url: '${competitionScheduleURL}' ,
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
        	if(data.competitionSchedule == true){
        		var competitionSchedule = data.competitionSchedule;
       			 $("#saveCompetitionScheduleModel").modal('show'); 

        	}else{
        		var msg = '<liferay-ui:message key="the-competition-schedule-is-unsucessful"/>';
        	    //$('#success-application').html(msg);
        		$("#saveCompetitionScheduleModel").modal('show'); 
        	}
    	 }
       
    });
	}
};
function adjustReportingTime() {debugger
    // Get the value of the startTime input field
    var startTime = document.getElementById('startTime').value;
    
    if (startTime) {
        // Split the startTime into hours and minutes
        var timeParts = startTime.split(':');
        var hours = parseInt(timeParts[0], 10);
        var minutes = parseInt(timeParts[1], 10);
        
        // Create a Date object with the start time
        var startDate = new Date();
        startDate.setHours(hours);
        startDate.setMinutes(minutes);
        startDate.setSeconds(0);
        
        // Subtract 1 hour to get reportingTime
        startDate.setHours(startDate.getHours() - 1);
        
        // Get the reporting time in HH:mm format
        var reportingHours = startDate.getHours();
        var reportingMinutes = startDate.getMinutes();
        
        // Ensure the hours and minutes are in two digits
        if (reportingHours < 10) {
            reportingHours = '0' + reportingHours;
        }
        if (reportingMinutes < 10) {
            reportingMinutes = '0' + reportingMinutes;
        }
        
        // Update the reportingTime input field
        document.getElementById('reportingTime').value = reportingHours + ':' + reportingMinutes;
    }
}
function closeModal() {debugger
    $("#saveCompetitionScheduleModel").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}

</script>