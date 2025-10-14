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
            <div class="card shadow border-0">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                        <liferay-ui:message key="facility-booking" />
                    </h5>
                    <div>
                        <a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="handleRedirect()"><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back" /></a>
                    </div>
                </div>
                <div class="card-body">
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="facility-details" />
                            </div>
                            <div class="card-body">
                               <div class="row">
								<%-- <c:if test="${!empty filteredList}">	 --%>
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="facility-Name"/><sup class="text-danger">*</sup></label>
											<select class="form-control" name="selectedFacility" id="selectedFacility" <c:if test="${mode eq 'view'}">disabled</c:if> >
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
												        <input type="radio" class="radio-btn" id="montlyRadio" name="monthlyRentalRadio" value="monthly" checked disabled="disabled" />
												        <label for="architectAppointedYes"><liferay-ui:message key="monthly"/></label>
												    </div>
												</div>
										        </c:when>
										        <c:otherwise>
										          <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="rentalRadio" name="monthlyRentalRadio" value="rental" checked disabled="disabled" />
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
												        <input type="radio" class="radio-btn" id="montlyRadio" name="monthlyRentalRadio" value="monthly" checked disabled="disabled" />
												        <label for="architectAppointedYes"><liferay-ui:message key="monthly"/></label>
												    </div>
												</div>
										        </c:when>
										        <c:otherwise>
										          <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="rentalRadio" name="monthlyRentalRadio" value="rental" checked disabled="disabled" />
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
											<input type="date" class="form-control" name="Date" id="Date" <c:if test="${mode eq 'view'}">disabled</c:if>
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
									                <c:if test="${mode eq 'view'}">disabled</c:if>>
									            <option value=""><liferay-ui:message key="select" /></option>
									            
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
									        <select class="form-control" name="timeFrom" id="timeFrom"
											    <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
											    <option value=""><liferay-ui:message key="select" /></option>
											</select>
									    </div>
									</div>
									<div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="time-to" /> <sup class="text-danger">*</sup></label>
									        <select class="form-control" name="timeTo" id="timeTo"
											    <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
											    <option value=""><liferay-ui:message key="select" /></option>
											</select>
									    </div>
									</div>

									<div class="col-md-6">
									    <div class="form-group">
									        <label>
									            <liferay-ui:message key="sport-Court" /> 
									            <sup class="text-danger">*</sup>
									        </label>
									        <select class="form-control" name="sportCourt" id="sportCourt" 
									                <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
									            <option value=""><liferay-ui:message key="select" /></option>
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
											<input type="text" class="form-control" name="name" id="name" value="${bookingDetails.name }" <c:if test="${mode eq 'view'}">disabled</c:if>>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="contact"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="contact" id="contact" value="${bookingDetails.contact }" <c:if test="${mode eq 'view'}">disabled</c:if>>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="purpose"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="purpose" id="purpose" value="${bookingDetails.purpose }" <c:if test="${mode eq 'view'}">disabled</c:if>>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="fees"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="fees" id="fees" value="${bookingDetails.fees }" <c:if test="${mode eq 'view'}">disabled</c:if>>
										</div>
									</div>
									
									
									<div class="col-md-6">
									  <div class="form-group">
									    <label>
									      <liferay-ui:message key="medical-certificate" />
									      <sup class="text-danger">*</sup>
									      <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-under-2mb' />"></em>
									    </label>
									    <div class="custom-file">
									      <input type="file" class="custom-file-input" id="medicalCertificate" name="medicalCertificate"
									        onchange="handleSingleImageUpload(this,'medicalCertificate','medicalCertificatePreviewContainer','medicalCertificatePreviewLink','medicalCertificateDeleteButton','medicalCertificateError','medicalCertificateHiddenInput')"
									        <c:if test="${mode eq 'view'}">disabled</c:if> >
									      <label class="custom-file-label" for="medicalCertificate"><liferay-ui:message key="choose-file" /></label>
									    </div>
									    <span id="medicalCertificateError" class="text-danger"></span>
									    <input type="hidden" id="medicalCertificateHiddenInput" name="medicalCertificateHiddenInput" />
										<div class="mt-3" id="medicalCertificatePreviewContainer" <c:if test="${empty bookingDetails or bookingDetails.medicalCertificate <= 0}">style="display:none;"</c:if>>
									      <a href="${medicalDocUrl }" target="_blank" id="medicalCertificatePreviewLink" class="text-decoration-none"><liferay-ui:message key="view-document" /></a>
									         <c:if test="${mode ne 'view'}">
									         <button type="button" id="medicalCertificateDeleteButton"
													class="medicalCertificateDeleteButton close" aria-label="Close"
													onclick="removeSingleImageFile(this,'medicalCertificate','medicalCertificatePreviewContainer','medicalCertificatePreviewLink','medicalCertificateHiddenInput')">
													<span aria-hidden="true" class="text-danger">
														<em class="bi bi-x-circle-fill"></em>
													</span>
												</button>
									         </c:if>
									    </div>  
									    <%-- <div class="mt-3" id="medicalCertificatePreviewContainer" style="display:none;">
										      <a href="#" target="_blank" id="medicalCertificatePreviewLink" class="text-decoration-none"></a>
										        <c:if test="${mode ne 'view'}">
										      <button type="button" class="medicalCertificateDeleteButton close text-danger" id="medicalCertificateDeleteButton"
										        onclick="removeSingleImageFile(this,'medicalCertificate','medicalCertificatePreviewContainer','medicalCertificatePreviewLink','medicalCertificateHiddenInput')">
										       <em class="bi bi-x-circle-fill"></em>
										      </button>
										      </c:if>
										    </div> --%>
									    <input type="hidden" name="hiddenMedicalDoc" value="${bookingDetails.medicalCertificate }">
									  </div>
									</div>
                                </div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                    
                    <!-- Thisfor ADMIN for approve/reject -->
                    
          <form id="facilityBookingForm" method="POST" enctype="multipart/form-data">
                
               <input type="hidden" id="facilityBookingId" value="${bookingDetails.facilityBookingId }">
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
								        <c:if test="${bookingDetails.isApproved}">disabled</c:if>
								        <c:if test="${bookingDetails.isApproved}">checked</c:if> >
								        <label for="genderYes">Approve</label>
								      </div>
								      <div class="radio-text">
								        <input type="radio" class="radio-btn" id="reject" name="adminAction" value="rejected"
								        <c:if test="${bookingDetails.isApproved}">disabled</c:if>
								        <c:if test="${!bookingDetails.isApproved}">checked</c:if> >
								        <label for="genderNo">Reject</label>
								      </div>
								    </div>
								  </div>
								</div>
								
									<div class="col-md-12">
										<div class="form-group">
											<label><liferay-ui:message key="remarks"/><sup class="text-danger">*</sup></label>
											 <textarea class="form-control" name="remarks" id="remarks" rows="3" <c:if test="${bookingDetails.isApproved}">disabled</c:if>>${bookingDetails.remarks}</textarea>
										</div>
									</div>
                              <c:if test="${bookingDetails.bookingStatus == 0}">
                                <div class="card-footer bg-transparent text-right p-4">
									 <div class="d-flex justify-content-end">
									     <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="window.location.href='<%= MhdysKreedapithSportsFacilityPortletKeys.HOMEURL %>?clear=true';">
							                <liferay-ui:message key="cancel"/>
							            </a>
							              
							              <button type="button" class="btn btn-primary reset-btn" id="reset-btn">
									      <liferay-ui:message key="reset" />
									    </button>

									    <button type="button" class="btn btn-primary saveAdminAction" id="saveAdminAction" >
									      <liferay-ui:message key="submit" />
									    </button>
									</div>
								</div>
							</c:if>   
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                </div> <!-- .card-body (outer) -->
           </form>     
           
           <!-- Validation modal -->
				<div class="modal fade" id="saveConstructionModal" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" tabindex="-1" data-backdrop="static" data-keyboard="false">
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
				      <div class="card-footer bg-transparent text-right p-4">
					      	<div class="d-flex justify-content-end">
							    <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
							     <liferay-ui:message key="close"/>
							    </a>
							</div>
						</div>
				    </div>
				  </div>
				</div>

            </div> <!-- .card -->
        </div> <!-- .row -->
    </div> <!-- .container -->
</div> <!-- .common-forms-div -->




<script>

var $form = $("#facilityBookingForm");
$(document).ready(function () {
	setTimeFunction("timeFrom");
	setTimeFunction("timeTo");
	
	 $("#facilityBookingForm").validate({
		 onkeyup: function (element) {
	   	        $(element).valid();
	   	    },
	   	    onchange: function (element) {
	   	        $(element).valid();
	   	    },
		  rules: { remarks: { required: true, 
			  minlength: 3,
		      maxlength: 250,
		      alphanumericWithSpecialChars:true,
		      singleSpaceOnly:true, noEdgeSpaces:true,noConsecutiveSpecials:true,} },
		  messages: { remarks: { required: "<liferay-ui:message key='please-enter-remarks' />",
			  minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
              maxlength: "<liferay-ui:message key='please-enter-max-250-chars' />",
              } }
		});
	 

		 $.validator.addMethod("alphanumericOnly", function(value, element) {
		    	// alpha numeric with .,
		        return this.optional(element) || /^[A-Za-z0-9., ]+$/.test(value);
		    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
		    
		    $.validator.addMethod("noEdgeSpaces", function(value, element) {
	    	  return this.optional(element) || value === value.trim();
	    	}, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

	    	$.validator.addMethod("singleSpaceOnly", function(value, element) {
	    	  return this.optional(element) || !/\s{2,}/.test(value);
	    	}, "<liferay-ui:message key='only-one-space-between-words-allowed' />");
	    	
	    	$.validator.addMethod("noConsecutiveSpecials", function(value, element) {
	    		return this.optional(element) || !/([.,/#-]\s*){2,}/.test(value);
	      	}, "<liferay-ui:message key='no-consecutive-specials-allowed' />");

	    	 $.validator.addMethod("alphanumericWithSpecialChars", function(value, element) {
	    		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
	    		    return this.optional(element) || /^[A-Za-z0-9.,\- ]+$/.test(value);
	    	    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
	    	 
		$("#saveAdminAction").on("click", function () {
		    if ( $("#facilityBookingForm").valid()) {
		        saveForm("adminApproval");
		    }
		});
		
		$("#reset-btn").on("click", function () {
		    $form[0].reset(); // Reset all fields
		    $form.validate().resetForm(); // Clear validation
		});

});



function handleRedirect() {
	var mode = "${mode}";
	var type = "${type}";
	var isHOAdmin = "${isHOAdmin}" === "true"; 
	var isCreateMode = mode === "" || mode === null;

	if (isCreateMode) {
		window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
	} else if (isHOAdmin) {
		window.location.href = "${redirectUrl}&type=adminBokingList&clear=true";
	}
}

function backOrClearUrl() {
	handleRedirect();
}

function closeModal() {
	$("#saveConstructionModal").modal('hide');
	handleRedirect();
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
		            var msg = '<span class="text-success"><liferay-ui:message key="details-submitted-successfully"/></span>';
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
