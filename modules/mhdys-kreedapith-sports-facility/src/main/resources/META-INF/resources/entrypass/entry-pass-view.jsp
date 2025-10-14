<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>


<portlet:resourceURL id="<%=MhdysKreedapithSportsFacilityPortletKeys.SAVE_FACILITYBOOKING_RESOURCECOMMAND %>" var="SaveFacilityBookingUrl" />

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.ENTRYPASSREDIRECT %>" />
</portlet:renderURL>

<%
    String qrCodeBase64 = (String) request.getAttribute("qrCodeBase64");
%>

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="card shadow border-0">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                        <liferay-ui:message key="download-entry-pass" />
                    </h5>
                    <div>
                        <a href="<%=MhdysKreedapithSportsFacilityPortletKeys.FACILITYHOMEURL %>" class="btn btn-primary btn-sm rounded-pill back-btn-cn""><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back" /></a>
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
												<button type="button" class="btn btn-primary facilityPhotosBtn d-block" id="facilityPhotosBtn" ><liferay-ui:message key="view-photos-of-facility" /></button>
					    					<div><p id="view_all_photos_msg"></p></div>
					    					<div id="facilityPhotoPreview" class="mt-3"></div>
					    				</div>
									</div>
									
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
												        <input type="radio" class="radio-btn" id="montlyRadio" name="monthlyRentalRadio" value="monthly" checked />
												        <label for="architectAppointedYes"><liferay-ui:message key="monthly"/></label>
												    </div>
												</div>
										        </c:when>
										        <c:otherwise>
										          <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="rentalRadio" name="monthlyRentalRadio" value="rental" checked />
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
												        <input type="radio" class="radio-btn" id="montlyRadio" name="monthlyRentalRadio" value="monthly" checked />
												        <label for="architectAppointedYes"><liferay-ui:message key="monthly"/></label>
												    </div>
												</div>
										        </c:when>
										        <c:otherwise>
										          <div class="d-flex mt-2">
												    <div class="radio-text">
												        <input type="radio" class="radio-btn" id="rentalRadio" name="monthlyRentalRadio" value="rental" checked />
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
											<label><liferay-ui:message key="date-of-booking"/><sup class="text-danger">*</sup></label>
											<input type="date" class="form-control" name="Date" id="Date" disabled
											value="<fmt:formatDate value='${bookingDetails.date}' pattern='yyyy-MM-dd'/>">
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
								
								<!--  QR CODE -->
								<div class="col-md-6">
								    <div class="form-group">
								        <label><liferay-ui:message key="qr-code"/></label><br/>
								        <img src="data:image/png;base64,<%= qrCodeBase64 %>" alt="QR Code" class="img-fluid mt-2"/>
								    </div>
								</div>
								
                                </div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                    
									
               	<div class="card-footer bg-transparent text-right p-4">
					 <div class="d-flex justify-content-end">
					    <button type="button" class="btn btn-primary saveAdminAction" id="downloadBtn" >
					      <liferay-ui:message key="download" />
					    </button>
					    <button type="button" class="btn btn-primary saveAdminAction" id="printBtn" >
					      <liferay-ui:message key="print" />
					    </button>
					</div>
				</div>
								
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
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

var facilitiesListStr = '${empty filteredList ? "[]" : filteredList}';
var facilitiesList = JSON.parse(facilitiesListStr);
console.log("facilitiesList:: "+facilitiesList);

//Handle View Facility Photos button click
$('#facilityPhotosBtn').on('click', function () {
    const selectedId = $('#selectedFacility').val();
    if (!selectedId) {
        $('#view_all_photos_msg').html("<liferay-ui:message key='please-select-a-facility' />")
        return;
    }

    const selectedFacility = facilitiesList.find(f => f.sportsFacilityId == selectedId);
    if (!selectedFacility || !selectedFacility.geotagPhotos) {
        $('#view_all_photos_msg').html("<liferay-ui:message key='no-photos-available-for-this-facility' />")
        return;
    }

    const photoUrls = selectedFacility.geotagPhotos.split(',');
    let photoHtml = '';

    	 $('#view_all_photos_msg').html('');
    photoUrls.forEach(function(url, index) {
        if (!url.startsWith('http')) {
            url = 'https://' + url; // Optional fallback fix
        }
        photoHtml += '<a href="' + url + '" target="_blank" class="text-decoration-none m-3">Photo ' + (index + 1) + '</a>';
    });

    console.log("Generated HTML: ", photoHtml);
    $('#facilityPhotoPreview').html(photoHtml);
});

$("#saveAdminAction").on("click", function () {
    if ($form.valid()) {
        saveForm("adminApproval");
    }
});

/* function backOrClearUrl() {
	window.location.href = "${redirectUrl}&type=entryPassList&clear=true";
} */

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

$('#printBtn').on('click', function () {
	  generateEntryPassPdf('print');
	});

$('#downloadBtn').on('click', function () {
  generateEntryPassPdf('download');
});

  
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

function generateEntryPassPdf(action) {
	  var bookingDetailsStr = '${empty bookingDetails ? "[]" : bookingDetails}';
	  var bookingDetails = JSON.parse(bookingDetailsStr);
	  var facilityName = $('#selectedFacility option:selected').text();
	  var monthlyRentalValue = $('input[name="monthlyRentalRadio"]:checked').val();

	  var qrText = window.location.origin + '/view-entry-pass?facilityBookingId=' + bookingDetails.facilityBookingId;

	  const docDefinition = {
	    content: [
	      { text: 'Entry Pass', style: 'header' },
	      { text: '\n' },
	      {
	        table: {
	          widths: ['*', '*'],
	          body: [
	            ['Facility Name:', facilityName || ''],
	            ['Type:', monthlyRentalValue || ''],
	            ['Date of booking:', bookingDetails.Date || ''],
	            ['Booking Time :', bookingDetails.timeFrom + ' To ' + bookingDetails.timeTo|| ''],
	            ['Name:', bookingDetails.name || ''],
	            ['Contact:', bookingDetails.contact || ''],
	            ['Purpose:', bookingDetails.purpose || ''],
	            ['Fees:', bookingDetails.fees || ''],
	          ],
	        },
	        margin: [0, 0, 0, 20]
	      },
	      {
	        text: 'QR Code:',
	        style: 'subheader'
	      },
	      {
	        qr: qrText,
	        fit: 100,
	        alignment: 'left',
	        margin: [0, 10, 0, 0]
	      }
	    ],
	    styles: {
	      header: {
	        fontSize: 18,
	        bold: true,
	        alignment: 'center'
	      },
	      subheader: {
	        fontSize: 14,
	        bold: true,
	        margin: [0, 10, 0, 5]
	      }
	    }
	  };

	  const pdfDoc = pdfMake.createPdf(docDefinition);

	  if (action === 'print') {
	    pdfDoc.print();
	  } else if (action === 'download') {
	    const fileName = (bookingDetails.facilityName || 'EntryPass') + '_Entrypass.pdf';
	    pdfDoc.download(fileName);
	  }
	}


</script>
