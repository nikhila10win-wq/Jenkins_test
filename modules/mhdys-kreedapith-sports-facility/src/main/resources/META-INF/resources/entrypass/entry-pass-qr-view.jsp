<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<portlet:resourceURL id="<%=MhdysKreedapithSportsFacilityPortletKeys.SAVE_FACILITYBOOKING_RESOURCECOMMAND %>" var="SaveFacilityBookingUrl" />

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="card shadow border-0 w-100">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                        <liferay-ui:message key="entry-pass" />
                    </h5>
                    
                </div>
                <div class="card-body">
                
               <c:if test="${!noDataFound}">
                
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="facility-details" />
                            </div>
                            <div class="card-body">
                               <div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="facility-Name"/><sup class="text-danger">*</sup></label>
											<select class="form-control" name="selectedFacility" id="selectedFacility" disabled >
													<option value=""><liferay-ui:message key="select"/></option>
													<c:forEach var="facility" items="${filteredList}">
													    <option value="${facility.sportsFacilityId}" <c:if test="${bookingDetails.selectedFacility==facility.sportsFacilityId}">selected</c:if>>${facility.facilityName}</option>
													</c:forEach>
											</select>
				
										</div>
									</div>
						 
									<div class="col-md-6">
									<div class="form-group">
								        <label><liferay-ui:message key="type"/> <sup class="text-danger">*</sup></label>
								        
								       <c:if test="${empty bookingDetails}"> 
									        <c:choose>
										        <c:when test="${isMonthlyBooking}">
										            <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="montlyRadio" name="monthlyRentalRadio" value="monthly" checked disabled="disabled"/>
												        <label for="architectAppointedYes"><liferay-ui:message key="monthly"/></label>
												    </div>
												</div>
										        </c:when>
										        <c:otherwise>
										          <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="rentalRadio" name="monthlyRentalRadio" value="rental" checked  disabled="disabled"/>
												        <label for="architectAppointedYes"><liferay-ui:message key="rental"/></label>
												    </div>
												</div>
										        </c:otherwise>
									    	</c:choose>
								   		</c:if>
								   		<c:if test="${!empty bookingDetails}"> 
								   				<c:choose>
										        <c:when test="${bookingDetails.type eq 'monthly'}">
										            <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="montlyRadio" name="monthlyRentalRadio" value="monthly" checked disabled="disabled"/>
												        <label for="architectAppointedYes"><liferay-ui:message key="monthly"/></label>
												    </div>
												</div>
										        </c:when>
										        <c:otherwise>
										          <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="rentalRadio" name="monthlyRentalRadio" value="rental" checked disabled="disabled"/>
												        <label for="architectAppointedYes"><liferay-ui:message key="rental"/></label>
												    </div>
												</div>
										        </c:otherwise>
									    	</c:choose>
								   		</c:if>
								   	
							    	</div>
							    	</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="date"/><sup class="text-danger">*</sup></label>
											<input type="date" class="form-control" name="Date" id="Date" disabled
											value="<fmt:formatDate value='${bookingDetails.createDate}' pattern='yyyy-MM-dd'/>">
										</div>
									</div>
									
									<div class="col-md-6">
									    <div class="form-group">
									        <label>
									            <liferay-ui:message key="batch" /> 
									            <sup class="text-danger">*</sup>
									        </label>
									        <select class="form-control" name="batch" id="batch" 
									                disabled>
									            <option value=""><liferay-ui:message key="select" /></option>
									       		<option value="6AM to 10AM" <c:if test="${bookingDetails.batch eq '6AM to 10AM'}">selected</c:if>>6AM to 10AM</option>     
									            <option value="10AM to 11AM" <c:if test="${bookingDetails.batch eq '10AM to 11AM'}">selected</c:if>>10AM to 11AM</option>
									            <option value="11AM to 12PM" <c:if test="${bookingDetails.batch eq '11AM to 12PM'}">selected</c:if>>11AM to 12PM</option>
									            <option value="12PM to 1PM" <c:if test="${bookingDetails.batch eq '12PM to 1PM'}">selected</c:if>>12PM to 1PM</option>
									            <option value="1PM to 2PM" <c:if test="${bookingDetails.batch eq '1PM to 2PM'}">selected</c:if>>1PM to 2PM</option>
									            <option value="2PM to 3PM" <c:if test="${bookingDetails.batch eq '2PM to 3PM'}">selected</c:if>>2PM to 3PM</option>
									            <option value="3PM to 4PM" <c:if test="${bookingDetails.batch eq '3PM to 4PM'}">selected</c:if>>3PM to 4PM</option>
									            <option value="4PM to 5PM" <c:if test="${bookingDetails.batch eq '4PM to 5PM'}">selected</c:if>>4PM to 5PM</option>
									            <option value="5PM to 6PM" <c:if test="${bookingDetails.batch eq '5PM to 6PM'}">selected</c:if>>5PM to 6PM</option>
									            <option value="6PM to 7PM" <c:if test="${bookingDetails.batch eq '6PM to 7PM'}">selected</c:if>>6PM to 7PM</option>
									            <option value="7PM to 8PM" <c:if test="${bookingDetails.batch eq '7PM to 8PM'}">selected</c:if>>7PM to 8PM</option>
									
									        </select>
									    </div>
									</div>
									
									<div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="time-from" /> <sup class="text-danger">*</sup></label>
									        <select class="form-control" name="timeFrom" id="timeFrom" disabled>
											    <option value="${bookingDetails.timeFrom}">${bookingDetails.timeFrom}</option>
											</select>
									    </div>
									</div>
									<div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="time-to" /> <sup class="text-danger">*</sup></label>
									        <select class="form-control" name="timeTo" id="timeTo" disabled>
											    <option value="${bookingDetails.timeTo}">${bookingDetails.timeTo}</option>
											</select>
									    </div>
									</div>

									<div class="col-md-6">
									    <div class="form-group">
									        <label>
									            <liferay-ui:message key="sport-Court" /> 
									            <sup class="text-danger">*</sup>
									        </label>
									        <select class="form-control" name="sportCourt" id="sportCourt" disabled>
									            <option value=""><liferay-ui:message key="select" /></option>
									            <option value="swimming" <c:if test="${bookingDetails.sportCourt eq 'swimming'}">selected</c:if>>swimming</option>
									            <option value="snooker" <c:if test="${bookingDetails.sportCourt eq 'snooker'}">selected</c:if>>snooker</option>
									            <option value="basketball" <c:if test="${bookingDetails.sportCourt eq 'basketball'}">selected</c:if>>basketball</option>
									            <option value="football" <c:if test="${bookingDetails.sportCourt eq 'football'}">selected</c:if>>football</option>
									            <option value="cricket" <c:if test="${bookingDetails.sportCourt eq 'cricket'}">selected</c:if>>cricket</option>
									        </select>
									    </div>
									</div>
							</div>
							
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                    
                    
                    <!-- 2nd User Details -------------- -->
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="user-details" />
                            </div>
                            <div class="card-body">
                               <div class="row"> 
                                <div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="name"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="name" id="name" value="${bookingDetails.name }" disabled>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="contact"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="contact" id="contact" value="${bookingDetails.contact }" disabled>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="purpose"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="purpose" id="purpose" value="${bookingDetails.purpose }" disabled>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="fees"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="fees" id="fees" value="${bookingDetails.fees }" disabled>
										</div>
									</div>
									
									
									<div class="col-md-6">
									  <div class="form-group">
									    <label>
									      <liferay-ui:message key="medical-certificate" />
									      <sup class="text-danger">*</sup>
									      <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-under-2mb' />"></em>
									    </label>
										<div class="mt-3" id="medicalCertificatePreviewContainer" <c:if test="${empty bookingDetails or bookingDetails.medicalCertificate <= 0}">style="display:none;"</c:if>>
									      <a href="${medicalDocUrl }" target="_blank" id="medicalCertificatePreviewLink" class="text-decoration-none"><liferay-ui:message key="view-document" /></a>
									    </div>  
									  
									  </div>
									</div>
                                </div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                    
                
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="review" />
                            </div>
                            <div class="card-body">
                                
                                <div class="col-md-12">
								  <div class="form-group">
								    <div class="d-flex mt-2">
								      <div class="radio-text me-3">
								        <input type="radio" class="radio-btn" id="approve" name="adminAction" value="approved" checked="checked"
								        disabled
								        <c:if test="${bookingDetails.isApproved}">checked</c:if> >
								        <label for="genderYes">Approve</label>
								      </div>
								      <div class="radio-text">
								        <input type="radio" class="radio-btn" id="reject" name="adminAction" value="rejected"
								        disabled
								        <c:if test="${!bookingDetails.isApproved}">checked</c:if> >
								        <label for="genderNo">Reject</label>
								      </div>
								    </div>
								  </div>
								</div>
								
									<div class="col-md-12">
										<div class="form-group">
											<label><liferay-ui:message key="remarks"/><sup class="text-danger">*</sup></label>
											 <textarea class="form-control" name="remarks" id="remarks" rows="3" disabled>${bookingDetails.remarks}</textarea>
										</div>
									</div>
									
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                    
                  </c:if>  
                    
                   <c:if test="${noDataFound}">  
                    <!-- If No EntryId Data -->
                     <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card"><liferay-ui:message key="no-data-found" /></div>
                            <div class="card-body">
                                <div class="col-md-12">
								 	<p class="text-center"> No Entry Details Found With ID: ${facilityBookingId }</p>
								</div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                 </c:if>   
                    
                    
                </div> <!-- .card-body (outer) -->
            </div> <!-- .card -->
        </div> <!-- .row -->
    </div> <!-- .container -->
</div> <!-- .common-forms-div -->




<script>

var $form = $("#facilityBookingForm");
$(document).ready(function () {
	setTimeFunction("timeFrom");
	setTimeFunction("timeTo");
	
	 $form.validate({
		  rules: { remarks: { required: true } },
		  messages: { remarks: { required: "<liferay-ui:message key='please-enter-remarks' />" } }
		});

});


$("#saveAdminAction").on("click", function () {
    if ($form.valid()) {
        saveForm("adminApproval");
    }
});

function backOrClearUrl() {
	var mode = "${mode}";
	var isCreateMode = mode === "" || null;
	if(isCreateMode){
    	window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
	}else{
		window.location.href = "${redirectUrl}&type=dsoTsoFacilityList&clear=true";
	}
}

function closeModal(){
	 $("#saveConstructionModal").modal('hide');
	 var mode = "${mode}";
		var isCreateMode = mode === "" || null;
		if(isCreateMode){
	    	window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
		}else{
			window.location.href = "${redirectUrl}&type=dsoTsoFacilityList&clear=true";
		}
}

function saveForm(actionType){
	 console.log("All validations are passed and saving Form ------ ");
	 console.log("actionType: "+actionType);
	 let facilityBookingId = $('#facilityBookingId').val();
	 console.log("facilityBookingId: "+facilityBookingId);
	 let mode = '${mode}';
	 
		 const form = document.getElementById("facilityBookingForm");
		

		    const formData = new FormData(form); 
		    formData.append("actionType", actionType);
		    formData.append("mode", mode);
		    if(facilityBookingId){
		    	formData.append("facilityBookingId", facilityBookingId);
		    }
		 
		    $.ajax({
		        type: "POST",
		        url: "${SaveFacilityBookingUrl}",
		        data: formData,
		        contentType : false,
				cache : false,
				processData : false,
		        success: function(data){
		        console.log("data:: "+data)
		        if (data === "success") {
		            var msg = '<span class="text-success"><liferay-ui:message key="the-details-are-saved-successfully"/></span>';
		            $('#success-message').html(msg);
		            $('#submitBtn').prop('disabled', true);
		            $("#saveConstructionModal").modal('show');
		        } else {
		            var msg = '<span class="text-danger"><liferay-ui:message key="the-details-are-failed-to-submit"/></span>';
		            $('#success-message').html(msg);
		            $("#saveConstructionModal").modal('show');
		        }
		    	 }
		    });
	
}

function setTimeFunction(id){
	var select = $('#'+id);
    for (var hour = 0; hour < 23; hour++) {
        var start = formatHour(hour);
        var end = formatHour(hour + 1);
        var timeSlot = start + ' to ' + end;
        select.append('<option value=\'' + timeSlot + '\'>' + timeSlot + '</option>');
    }

    // Optional: preselect existing value from server
  	var selectedTime = (id === "timeFrom") ? '${bookingDetails.timeFrom}' : '${bookingDetails.timeTo}';
    if (selectedTime) {
        select.val(selectedTime);
    }
}

function formatHour(hour) {
    var period = hour >= 12 ? 'PM' : 'AM';
    var hr = hour % 12;
    hr = hr === 0 ? 12 : hr;
    return hr + period;
}

</script>
