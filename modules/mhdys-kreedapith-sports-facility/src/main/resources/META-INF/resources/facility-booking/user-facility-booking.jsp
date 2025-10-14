<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<portlet:resourceURL id="<%=MhdysKreedapithSportsFacilityPortletKeys.SAVE_FACILITYBOOKING_RESOURCECOMMAND %>" var="SaveFacilityBookingUrl" />
<portlet:resourceURL id="<%=MhdysKreedapithSportsFacilityPortletKeys.GETBOOKINGDETAILSFORSLOTSRESOURCECOMMAND %>" var="getBookingDetailsForSlots" />

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.REDIRECT %>" />
</portlet:renderURL>
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.10.0/css/bootstrap-datepicker.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.10.0/js/bootstrap-datepicker.min.js"></script> -->

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>



<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="card shadow border-0">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                       <liferay-ui:message key="facility-booking" />
                    </h5>
                    <div>
                        <a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backOrClearUrl()"><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back" /></a>
                    </div>
                </div>
                <form id="facilityBookingForm" method="POST" enctype="multipart/form-data">
                
               <input type="hidden" id="facilityBookingId" value="${bookingDetails.facilityBookingId }">
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
											<label><liferay-ui:message key="select-facility"/><sup class="text-danger">*</sup></label>
											<select class="form-control" name="selectedFacility" id="selectedFacility" <c:if test="${mode eq 'view'|| mode eq 'edit'}">disabled</c:if> >
													<option value=""><liferay-ui:message key="select"/></option>
													<c:forEach var="facility" items="${filteredList}" varStatus="status">
												        <c:if test="${status.first}">
												            <option value="${facility.sportsFacilityId}"
												                <c:if test="${bookingDetails.selectedFacility == facility.sportsFacilityId}">selected</c:if>>
												                ${facility.facilityName}
												            </option>
												        </c:if>
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
									        <label>
									            <liferay-ui:message key="select-booking-type" /> 
									            <sup class="text-danger">*</sup>
									            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='daily-or-datewise' />"></em>
									        </label>
									        <div class="d-flex mt-2">
									            <!-- Daily -->
									            <div class="radio-text">
									                <input type="radio" class="radio-btn dailyOrDatewise" id="daily" name="dailyOrDatewise" value="daily"
									                    <c:if test="${bookingDetails.dailyOrDate eq 'daily'}">checked</c:if>
									                    <c:if test="${mode eq 'view'}">disabled</c:if> />
									                <label for="daily"><liferay-ui:message key="daily"/></label>
									            </div>
									
									            <!-- Datewise -->
									            <div class="radio-text ms-3">
									                <input type="radio" class="radio-btn dailyOrDatewise" id="datewise" name="dailyOrDatewise" value="datewise"
									                    <c:if test="${bookingDetails.dailyOrDate eq 'datewise' or empty bookingDetails.dailyOrDate}">checked</c:if>
									                    <c:if test="${mode eq 'view'}">disabled</c:if> />
									                <label for="datewise"><liferay-ui:message key="date"/></label>
									            </div>
									        </div>
									    </div>
									</div>

							    	
							    	<div class="col-md-6">
									    <div class="form-group">
									        <label>
									            <liferay-ui:message key="sport-Court" /> 
									            <sup class="text-danger">*</sup>
									        </label>
									        <select class="form-control" name="sportCourt" id="sportCourt" 
									                <c:if test="${mode eq 'view'}">disabled</c:if>>
									            <option value=""><liferay-ui:message key="select" /></option>
									            <option value="swimming" <c:if test="${bookingDetails.sportCourt eq 'swimming'}">selected</c:if>>swimming</option>
									            <option value="shooting" <c:if test="${bookingDetails.sportCourt eq 'shooting'}">selected</c:if>>shooting</option>
									            <option value="snooker" <c:if test="${bookingDetails.sportCourt eq 'snooker'}">selected</c:if>>snooker</option>
									            <option value="basketball" <c:if test="${bookingDetails.sportCourt eq 'basketball'}">selected</c:if>>basketball</option>
									            <option value="football" <c:if test="${bookingDetails.sportCourt eq 'football'}">selected</c:if>>football</option>
									            <option value="cricket" <c:if test="${bookingDetails.sportCourt eq 'cricket'}">selected</c:if>>cricket</option>
									        </select>
									    </div>
									</div>
									
									<div class="col-md-6">
									
											<div class="form-group dPicker">	
											 	<label><liferay-ui:message key="date"/><sup class="text-danger dateMandatoryLabel" id="dateMandatoryLabel">*</sup></label>									
<!-- 											 	<label><liferay-ui:message key="date"/><sup class="text-danger dateMandatoryLabel" id="dateMandatoryLabel"></sup></label>									 -->
												<input type="text" class="form-control" id="Date" name="Date"  <c:if test="${mode eq 'view'}">disabled</c:if>
														value="<fmt:formatDate value='${bookingDetails.date}' pattern='yyyy-MM-dd'/>" onchange="getBookingDetails('Date')" autocomplete="off">														
														<i class="bi bi-calendar-week"></i>
											</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="number-of-months"/><sup class="text-danger">*</sup><em class="bi bi-info-circle-fill" title="<liferay-ui:message key="no-of-months-title" />"></em></label>
											<input type="text" class="form-control" name="numberOfMonths" id="numberOfMonths" value="${bookingDetails.numberOfMonths }" <c:if test="${mode eq 'view'}">disabled</c:if>>
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
									        </select>
									    </div>
									</div>
									
									<div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="time-from" /> <sup class="text-danger">*</sup></label>
									        <%-- <select class="form-control" name="timeFrom" id="timeFrom" onclick="this.showPicker()"
											    <c:if test="${mode eq 'view'}">disabled</c:if>>
											    <option value=""><liferay-ui:message key="select" /></option>
											</select> --%>
											<input type="time" class="form-control" name="timeFrom" id="timeFrom" onclick="this.showPicker()" onchange="roundTimeToNearest30(this)"
											<c:if test="${mode eq 'view'}">disabled</c:if> value="${bookingDetails.timeFrom }">
									    </div>
									</div>
									<div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="time-to" /> <sup class="text-danger">*</sup></label>
									        <%-- <select class="form-control" name="timeTo" id="timeTo"
											    <c:if test="${mode eq 'view'}">disabled</c:if>>
											    <option value=""><liferay-ui:message key="select" /></option>
											</select> --%>
											<input type="time" class="form-control" name="timeTo" id="timeTo" onclick="this.showPicker()" onchange="roundTimeToNearest30(this)"
											<c:if test="${mode eq 'view'}">disabled</c:if> value="${bookingDetails.timeTo }">
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
										<!-- Abhishek -->
											<label><liferay-ui:message key="fees"/><sup class="text-danger">*</sup></label>
											<%--  <input type="text" class="form-control" name="fees"  id="fees" value="${bookingDetails.fees }" <c:if test="${mode eq 'view'}">disabled</c:if>>  --%>
										 <input type="text" class="form-control" name="fees" readonly="readonly" id="fees" value="${bookingDetails.fees}"/> 
										</div>
									</div>
									
									<div class="col-md-6">
									  <div class="form-group">
									    <label>
									      <liferay-ui:message key="medical-certificate" />
									      <sup class="text-danger" id="medicalDocMandatoryLabel"></sup>
									      <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-jpg-png-files-under-2mb' />"></em>
									    </label>
									    <div class="custom-file">
									      <input type="file" class="custom-file-input mb-2" id="medicalCertificate" name="medicalCertificate"
									        onchange="handleSingleImageUpload(this,'medicalCertificate','medicalCertificatePreviewContainer','medicalCertificatePreviewLink','medicalCertificateDeleteButton','medicalCertificateError','medicalCertificateHiddenInput')"
									        <c:if test="${mode eq 'view'}">disabled</c:if> >
									      <label class="custom-file-label" for="medicalCertificate"><liferay-ui:message key="choose-file" /></label>
									    </div>
									    <span id="medicalCertificateError" class="text-danger mt-2"></span>
									    <input type="hidden" id="medicalCertificateHiddenInput" name="medicalCertificateHiddenInput" />
									     <div class="mt-3" id="medicalCertificatePreviewContainer"  <c:if test="${empty bookingDetails or bookingDetails.medicalCertificate <= 0}">style="display:none;"</c:if>>
										      <div class="d-flex">
										      <a href="${medicalDocUrl }" target="_blank" id="medicalCertificatePreviewLink" class="text-decoration-none text-truncate"><liferay-ui:message key="view-document" /></a>
										        <c:if test="${mode ne 'view'}">
										      <button type="button" class="medicalCertificateDeleteButton btn btn-primary" id="medicalCertificateDeleteButton"
										        onclick="removeSingleImageFile(this,'medicalCertificate','medicalCertificatePreviewContainer','medicalCertificatePreviewLink','medicalCertificateHiddenInput')">
										       <em class="bi bi-x-circle-fill"></em>
										      </button>
										      </c:if>
										      </div>
										    </div> 
									    <input type="hidden" name="hiddenMedicalDoc" id="hiddenMedicalDoc" value="${bookingDetails.medicalCertificate }">
									  </div>
									</div>
                                </div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                    
                    
                    <!-- If approved / rejected by admin -->
                  <c:if test="${bookingDetails.bookingStatus == 1 || bookingDetails.bookingStatus == 9}">
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
								        <input type="radio" class="radio-btn" id="approve" name="adminAction" value="approved" checked="checked" disabled="disabled"
								        <c:if test="${bookingDetails.isApproved}">checked</c:if> >
								        <label for="genderYes">Approve</label>
								      </div>
								      <div class="radio-text">
								        <input type="radio" class="radio-btn" id="reject" name="adminAction" value="rejected" disabled="disabled"
								        <c:if test="${!bookingDetails.isApproved}">checked</c:if> >
								        <label for="genderNo">Reject</label>
								      </div>
								    </div>
								  </div>
								</div>
									<div class="col-md-12">
										<div class="form-group">
											<label><liferay-ui:message key="remarks"/><sup class="text-danger">*</sup></label>
											 <textarea class="form-control" name="remarks" id="remarks" rows="3" disabled="disabled">${bookingDetails.remarks}</textarea>
										</div>
									</div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                   </c:if> 
                   
                </div> <!-- .card-body (outer) -->
                 <c:if test="${mode ne 'view'}">
                <div class="card-footer bg-transparent text-right p-4">
					 <div class="d-flex justify-content-end">
						     <a href="#" class="btn btn-secondary" id="modalCloseBtn" onclick="window.location.href='<%= MhdysKreedapithSportsFacilityPortletKeys.HOMEURL %>?clear=true';">
				                <liferay-ui:message key="cancel"/>
				            </a>

						     <button type="button" class="btn btn-primary reset-btn" id="reset-btn">
						      <liferay-ui:message key="reset" />
						    </button> 
					    		
					    <button type="button" class="btn btn-primary submit-btn" id="submitBtn">
							<c:choose>
								<c:when test="${mode eq 'edit'}">
									<liferay-ui:message key="update" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="book" />
								</c:otherwise>
							</c:choose>
						</button>

					</div>
				</div>
				</c:if>
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
				
				<div class="modal fade" id="timingsModal" role="dialog" aria-labelledby="timingsModal" aria-hidden="true" tabindex="-1" data-backdrop="static" data-keyboard="false">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content modal-bg">
				      <div class="modal-header justify-content-center align-items-center">
				        <h5 class="modal-title"><liferay-ui:message key="Message" /></h5>
				      </div>
				      <div class="modal-body">
				        <div class="text-center">
				          <p id="timing-message">
				           <!--  <liferay-ui:message key="please-fill-all-required-fields-before-proceeding" /> -->
				          </p>
				        </div>
				      </div>
				      <div class="card-footer bg-transparent text-right p-4">
					      	<div class="d-flex justify-content-end">
							    <button  class="btn btn-secondary maha-save-btn" data-dismiss="modal"> <liferay-ui:message key="close"/> </button>
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
let medicalCertificate = Number("${bookingDetails.medicalCertificate}");
var uploadedFilesGeoTagPhoto = [];
let fullyBookedDates = [];
let grouped = {};
let bookedHours =[];
var allBatches = [
	  "6AM to 10AM", "10AM to 2PM",  "2PM to 6PM", "6PM to 10PM"
	];


let datepicker; // global reference

function initDatepicker(fullyBookedDates) {
    if (datepicker) {
        datepicker.destroy(); // destroy old one before re-init
    }

    datepicker = flatpickr("#Date", {
        dateFormat: "Y-m-d",
        minDate: "today",
        disable: fullyBookedDates,
        allowInput: true,
    });
}


$(document).ready(function () {

	// set batches Intially
	setBatchSlots("batch");
	initDatepicker([]);
	
    var facilitiesListStr = '${empty filteredList ? "[]" : filteredList}';
    var facilitiesList = JSON.parse(facilitiesListStr);
    
    let selectedFacility = $('#selectedFacility').val();
    if(selectedFacility==""){
    	$('#facilityPhotosBtn').prop('disabled', true);
    }else{
    	$('#facilityPhotosBtn').prop('disabled', false);
    }
    
    $('#selectedFacility').on('change', function () {
        getBookingDetails('selectedFacility');
        const selectedId = $(this).val();
        $('#facilityPhotoPreview').html('');
        $('#view_all_photos_msg').html('');
        if (!selectedId) {
        	$('#facilityPhotosBtn').prop('disabled', true);
            $('#facilityName, #fees').val('');
            return;
        }else {
            $('#facilityPhotosBtn').prop('disabled', false);
        }
        
        const selectedFacility = facilitiesList.find(f => f.sportsFacilityId == selectedId);
        if (selectedFacility) {
            $('#fees').val(selectedFacility.fees);
        }
    }); 
    
 // Handle View Facility Photos button click
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

        $('#facilityPhotoPreview').html(photoHtml);
    });
 
    
	/* --------------------------------------- */
    let validationRules = {
    	selectedFacility: { required: true},
        facilityName: { required: true, alphanumericOnly:true, minlength: 3, maxlength: 75 },
        fees: { required: true, number: true, min: 1, max: 9999999999},
        monthlyRentalRadio: { required: true},
        Date: { required: true},
        batch: { required: true},
        timeFrom: { required: true},
        timeTo: { required: true},
        sportCourt: { required: true},
        name: { required: true,alphabetsOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75},
        contact: {  required: true, digits: true, minlength: 10, maxlength: 10,validContact: true},
        purpose: { required: true,alphanumericOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75},
        numberOfMonths:{required: true, min: 1, max: 99},
        dailyOrDatewise:{required: true},
    };	
	
    /* validationRules.medicalCertificate = {
    	    required: {
    	        depends: function () {
    	            const hiddenDocVal = $('#hiddenMedicalDoc').val();
    	            const selectedFile = $('#medicalCertificate').val();
    	            return !hiddenDocVal && !selectedFile;
    	        }
    	    }
    	}; */
    	
    	/* validationRules.Date = {
        	    required: {
        	        depends: function () {
        	           // if datewise selected
        	        	return $("input[name='dailyOrDatewise']:checked").val() === "datewise";
        	        }
        	    }
        	}; */

    let validationMessages = {
        facilityName: {
            required: "<liferay-ui:message key='please-enter-facility-name' />",
            minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
            maxlength: "<liferay-ui:message key='please-enter-max-75-chars' />"
        },
        fees: {
            required: "<liferay-ui:message key='please-enter-fees' />",
            number: "<liferay-ui:message key='fees-must-be-number' />",
            min: '<liferay-ui:message key="it-must-be-positive-or-greater-than-0" />',
            max: "<liferay-ui:message key='please-enter-max-10-digits' />"
        },
        Date: { 
        	required: "<liferay-ui:message key='please-enter-date' />"
        },
        monthlyRentalRadio: {
            required: "<liferay-ui:message key='radio-option-required' />",
        },
        batch: {
            required: "<liferay-ui:message key='please-select-batch' />",
        },
        selectedFacility: {
            required: "<liferay-ui:message key='please-select-facility-type' />",
        },
        timeFrom: {
            required: "<liferay-ui:message key='please-select-time-from' />",
        },
        timeTo: {
            required: "<liferay-ui:message key='please-select-time-to' />",
        },
        sportCourt: {
            required: "<liferay-ui:message key='please-select-sport-court' />",
        },
        name: {
        	  required: "<liferay-ui:message key='please-enter-name' />",
              minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
              maxlength: "<liferay-ui:message key='please-enter-max-75-chars' />"
        },
        contact: {
        	 required: '<liferay-ui:message key="please-enter-contact-number" />',
             digits: '<liferay-ui:message key="contact-number-digits-only" />',
             minlength: '<liferay-ui:message key="contact-number-should-be-10-digits" />',
             maxlength: '<liferay-ui:message key="contact-number-should-be-10-digits" />'
        },
        purpose: {
        	  required: "<liferay-ui:message key='please-enter-purpose' />",
              minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
              maxlength: "<liferay-ui:message key='please-enter-max-75-chars' />"
        },
        numberOfMonths: {
        	required: "<liferay-ui:message key='please-enter-number-of-months' />",
            min: '<liferay-ui:message key="it-must-be-positive-or-greater-than-0" />',
            max: "<liferay-ui:message key='months-should-not-be-greater-than-two-digits' />"
        },
        dailyOrDatewise: {
        	required: "<liferay-ui:message key='please-enter-booking-type' />",
        }
    };

   $("#facilityBookingForm").validate({
    	onkeyup: function (element) {
   	        $(element).valid();
   	    },
   	    onchange: function (element) {
   	        $(element).valid();
   	    },
        rules: validationRules,
        messages: validationMessages,
        errorElement: 'span',
        errorClass: 'text-danger',
        errorPlacement: function (error, element) {
            if (element.attr("type") == "radio") {
                error.insertAfter(element.parent().parent());
            } else if (element.attr("type") == "checkbox" && element.hasClass("form-check-input")) {
                error.insertAfter(element.closest('.form-check'));
            } else if(element.attr("id") == "Date"){
            	 error.insertAfter(element.closest('.form-control'));
            }else {
                error.insertAfter(element);
            }
        }
    });
    

    $.validator.addMethod("validContact", function(value, element) {
        return this.optional(element) || /^[6-9]\d{9}$/.test(value);
    }, '<liferay-ui:message key="contact-number-invalid-pattern" />');

    $.validator.addMethod("containsLetters", function(value, element) {
        return this.optional(element) || /[A-Za-z]/.test(value);
    }, "<liferay-ui:message key='field-must-contain-characters' />");

    $.validator.addMethod("validSpaces", function(value, element) {
        // Checks if there are no leading/trailing spaces and only single space between words
        return this.optional(element) || /^[^\s]+(?:\s[^\s]+)*$/.test(value);
    }, "<liferay-ui:message key='Spaces-are-allowed-only-between-words-and-No-leading-or-trailing-spaces' />");

    $.validator.addMethod("alphabetsOnly", function(value, element) {
        return this.optional(element) || /^[A-Za-z ]+$/.test(value);
    }, "<liferay-ui:message key='please-enter-alphabets' />");

    $.validator.addMethod("alphanumericOnly", function(value, element) {
        return this.optional(element) || /^[A-Za-z0-9 ]+$/.test(value);
    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
    
    $.validator.addMethod("validPersonName", function(value, element) {
    	// It Should accept alphabets space .
        return this.optional(element) || /^[A-Za-z. ]+$/.test(value.trim());
    }, "<liferay-ui:message key='please-enter-valid-name' />");

    $.validator.addMethod("noEdgeSpaces", function(value, element) {
    	  return this.optional(element) || value === value.trim();
    	}, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

   	$.validator.addMethod("singleSpaceOnly", function(value, element) {
   	  return this.optional(element) || !/\s{2,}/.test(value);
   	}, "<liferay-ui:message key='only-one-space-between-words-allowed' />");
    	

});

/* document.ready ends ----------------- */

$('#sportCourt').on('change', function(){
	getBookingDetails('sportCourt');
	let sportCourtVal = $('#sportCourt').val();
	 if(sportCourtVal == "swimming" || sportCourtVal == "shooting"){
		 $('#medicalDocMandatoryLabel').html('*');
	 }else{
		 $('#medicalDocMandatoryLabel').html('');
	 }
});

 $(".dailyOrDatewise").on("change", function() {
	 getIfAnyBookingsAreOverriding();
    /* if ($("#datewise").is(":checked")) {
    	$('#dateMandatoryLabel').html('*');
    } else {
    	$('#dateMandatoryLabel').html('');
    } */
}); 

 $('#batch').on('change', function(){
		resetSlots('batch');
		populateTimeSlotsBasedOnBatch(bookedHours);
	});

$('#numberOfMonths').on('change', function(){
	 getIfAnyBookingsAreOverriding();
});

$('#timeFrom, #timeTo').on('change', validateTimeSelection);

function setBatchSlots(id){
	let select = $('#' + id);
	allBatches.forEach(batch => {
	     select.append('<option value="' + batch + '">' + batch + '</option>');
	 });
	 
	 // preselect existing value from server
	 var batchSelection = '${bookingDetails.batch}';
	 console.log("batchSelection:::: ", batchSelection);
	 if(batchSelection){
	 	select.val(batchSelection);
	 }
}

function roundTimeToNearest30(timeInput) {
    const [hours, minutes] = timeInput.value.split(':').map(Number);

    if (!isNaN(hours) && !isNaN(minutes)) {
        // Round minutes to nearest 30 (00 or 30)
        const roundedMinutes = minutes < 15 ? '00' : minutes < 45 ? '30' : '00';
        const nextHour = minutes >= 45 ? (hours + 1) % 24 : hours;

        // Format time (HH:mm)
        const formattedTime = nextHour.toString().padStart(2, '0') + ':' + roundedMinutes;
        timeInput.value = formattedTime;
    }
}


//Time From and To conditions starts ---------------------------------------------
function validateTimeSelection() {
	 const timeFrom = $('#timeFrom').val();
	 const timeTo = $('#timeTo').val();

	 console.log("timeFrom:: "+timeFrom +" , timeTo:: "+timeTo);
	 
	 if (!timeFrom || !timeTo) {
	     return; // skip if one is not selected
	 }

	 const fromHour = parseTimeTo24Hour(timeFrom);
	 const toHour = parseTimeTo24Hour(timeTo);

	 console.log("fromHour:: "+fromHour +" , toHour:: "+toHour);
	 let batchVal = $('#batch').val();
	 
	// case 1: already booked check
	console.log("bookedHours :: "+ bookedHours);
	 if (bookedHours && (isBookedHour(fromHour) || isBookedHour(toHour))) {
			showTimingError("<liferay-ui:message key='this-time-slot-is-already-booked' />");
			return;
		}
	 
	// case 1: invalid time range
		if (fromHour >= toHour) {
			showTimingError("<liferay-ui:message key='please-select-valid-timings' />");
			return;
		}
		
	// case 2: outside batch range
		if(batchVal!=""){
			// get batch range in 24 hr format
			let [batchStart, batchEnd] = batchVal.split(" to ");
			const batchStartHour = parseTimeTo24Hour(batchStart);
			const batchEndHour = parseTimeTo24Hour(batchEnd);
			if (fromHour < batchStartHour || toHour > batchEndHour) {
				showTimingError("<liferay-ui:message key='please-select-timings-within' /> " + batchVal);
				return;
			}
		}
	

		// valid
		$('#timing-message').html("");
		$("#timingsModal").modal('hide');
		getIfAnyBookingsAreOverriding();
	 
}

function populateTimeSlotsBasedOnBatch(bookedHours = []) {
    let batchVal = $('#batch').val(); // e.g., "6AM to 10AM"
    if (!batchVal) return;

    let [batchStartStr, batchEndStr] = batchVal.split('to').map(s => s.trim());
    let batchStart = parseTimeTo24Hour(batchStartStr);
    let batchEnd = parseTimeTo24Hour(batchEndStr);

    let slotOptions = [];
    for (let h = batchStart; h <= batchEnd; h += 0.5) {
        let slot = formatHour(h);
        if (!bookedHours.includes(slot)) {
            slotOptions.push(slot);
        }
    }

    let msg = slotOptions.length > 0 
    ? '<liferay-ui:message key="available-time-slots" /> ' + slotOptions.join(', ') 
    : '<liferay-ui:message key="no-available-slots-in-this-batch" />';

	// Show available times on modal popup
	$('#timing-message').html('<span>' + msg + '</span>');
	$("#timingsModal").modal('show');
    
    // Fill the datalists
    /* setDatalistOptions('timeFrom-options', slotOptions);
    setDatalistOptions('timeTo-options', slotOptions); */
}

function setDatalistOptions(datalistId, options) {
    let datalist = document.getElementById(datalistId);
    datalist.innerHTML = '';
    options.forEach(opt => {
        let optionEl = document.createElement('option');
        optionEl.value = opt;
        datalist.appendChild(optionEl);
    });
}


function parseTimeTo24Hour(timeStr) {
    timeStr = timeStr.trim().toUpperCase();

    // Handle formats like "13:30", "01:30 PM", "1PM"
    let match = timeStr.match(/^(\d{1,2})(?::(\d{2}))?\s*(AM|PM)?$/);
    if (!match) {
        console.warn("Invalid time format:", timeStr);
        return null;
    }

    let hour = parseInt(match[1], 10);
    let minutes = match[2] ? parseInt(match[2], 10) : 0;
    let period = match[3] || "";

    if (period === "PM" && hour !== 12) hour += 12;
    if (period === "AM" && hour === 12) hour = 0;

    // Return fractional hour (e.g., 13.5 for 13:30)
    return hour + (minutes / 60);
}

function isBookedHour(hour24) {
	let formatted = formatHour(hour24); // convert 13 -> "1PM"
	return bookedHours && bookedHours.includes(formatted);
}

function showTimingError(msg) {
	$('#timing-message').html('<span class="">' + msg + '</span>');
	$("#timingsModal").modal('show');
	$('#timeFrom').val('');
	$('#timeTo').val('');
}
//Time From and To conditions ends ---------------------------------------------	
	
/* Fetch Details for already booking slots------------------------------- */
function getBookingDetails(id){
	resetSlots(id);
	var selectedFacilityVal = $('#selectedFacility').val();
	var sportCourtVal = $('#sportCourt').val();
	if(selectedFacilityVal!="" && sportCourtVal!=""){
		var selectedType = $("input[name='monthlyRentalRadio']:checked").val();
		var date = $('#Date').val();
		var dataToSend = {
				"selectedFacilityVal": selectedFacilityVal,	
				"sportCourtVal": sportCourtVal,	
				"selectedType": selectedType,	
				"Date": date,
		}
		$.ajax({
	        type: "POST",
	        url: "${getBookingDetailsForSlots}",
	        data: dataToSend,
	        success: function(data){
		        if (!data || data.length === 0) {
		            // If no bookings, everything is available
		            populateBatches([]);
		            populateTimeSlots([]);
		            return;
		        }
		      
		        let bookings = data.bookings;
		        fullyBookedDates = data.fullyBookedDates || [];
		        // Refresh datepicker after updating disabled dates
		        console.log("fullyBookedDates:: "+ fullyBookedDates)
		         // Re-initialize flatpickr with new disabled dates
      		  initDatepicker(fullyBookedDates);
		        
		        if(date){
		        	
		        	 let dayBookings = bookings.filter(d => {
		        	        let bookingDate = new Date(d.startDate);
		        	        let bookingStr = bookingDate.toISOString().split('T')[0]; // yyyy-MM-dd
		        	        return bookingStr === date;
		        	    });
		        	 
		        	console.log("dayBookings::: "+dayBookings);
			        populateBatches(dayBookings);
			        populateTimeSlots(dayBookings);
		        }
		    
	    	 }
	    });
		
	}
}

function getIfAnyBookingsAreOverriding(){
	
	var selectedFacilityVal = $('#selectedFacility').val();
	var sportCourtVal = $('#sportCourt').val();
	var selectedType = $("input[name='monthlyRentalRadio']:checked").val();
	var date = $('#Date').val();
	var numberOfMonths = $('#numberOfMonths').val();
	var batch = $('#batch').val();
	var timeFrom = $('#timeFrom').val();
	var timeTo = $('#timeTo').val();
	var dailyOrDatewise = $("input[name='dailyOrDatewise']:checked").val();
	var facilityBookingId = '${bookingDetails.facilityBookingId}'
	// Validate required fields
	if (!selectedFacilityVal || !sportCourtVal || !selectedType || !date || !numberOfMonths || !batch || !timeFrom || !timeTo || !dailyOrDatewise) {
	    console.log("Please fill in all required fields.");
	    return; 
	}
	
		var dataToSend = {
				"selectedFacilityVal": selectedFacilityVal,	
				"sportCourtVal": sportCourtVal,	
				"selectedType": selectedType,	
				"Date": date,
				"numberOfMonths": numberOfMonths,
				"batch": batch,
				"timeFrom": timeFrom,
				"timeTo": timeTo,
				"dailyOrDatewise": dailyOrDatewise,
				"bookingsCheck": "bookingsCheck",
				"facilityBookingId": facilityBookingId,
		}
		$.ajax({
	        type: "POST",
	        url: "${getBookingDetailsForSlots}",
	        data: dataToSend,
	        success: function(data){
		        if (!data || data.length === 0) {
		            return;
		        }
		      
		        let overlappingBookings = data.overlappingBookings;
		        let overlapDetails = data.details || [];

		        console.log('overlappingBookings:: ', overlappingBookings);
		        console.log('overlapDetails:: ', overlapDetails);

		        if (overlappingBookings === true || overlappingBookings === 'true') {
		            let msg = '<liferay-ui:message key="this-time-slot-is-already-booked-on-the-following-dates" /> <br><br>';

		            overlapDetails.forEach(function(d) {
		            	 let shortDate = d.date.split('00:00:00')[0].trim();
		                msg +=  shortDate + '  ' + d.timeFrom + ' - ' + d.timeTo + '<br>';
		            });

		            $('#timing-message').html('<span>' + msg + '</span>');
		            $('#timingsModal').modal('show');
		            resetSlots('Date');
		        }

		       /*  if (overlappingBookings.length > 0) {
		            let dates = overlappingBookings.map(b => b.date).join(", ");
		            alert("Error: Overlapping bookings found on " + dates);
		        } */
		       
	    	 }
	    });
		
}

/*Slots hanlde form ajax starts ------------------------------------   */

//1------

function populateBatches(dayBookings) {
    let batchSelect = $("#batch");
    batchSelect.empty().append('<option value=""><liferay-ui:message key="select" /></option>');

    allBatches.forEach(batch => {
        let [batchStart, batchEnd] = batch.split(" to ");
        let batchStartHour = parseTimeTo24Hour(batchStart);
        let batchEndHour = parseTimeTo24Hour(batchEnd);

        // get bookings only for this batch
        let batchBookings = dayBookings.filter(d => d.batch === batch);

        // find booked hours in this batch
        let bookedHours = getBookedHours(batchBookings);

        // full set of hours for this batch
        let batchHours = [];
        for (let h = batchStartHour; h < batchEndHour; h += 0.5) {
            batchHours.push(formatHour(h));
        }

        // remaining hours = batchHours - bookedHours
        let available = batchHours.filter(h => !bookedHours.includes(h));

        if (available.length > 0) {
            // batch is still available
            batchSelect.append('<option value="'+batch+'">'+batch+'</option>');
        }
    });
}

//3---------------

function populateTimeSlots(data) { 
    let allHours = generateHours();
    bookedHours = getBookedHours(data);
}

function generateHours() {
 let hours = [];
 for (let h = 0; h < 24; h++) {
     hours.push(formatHour(h));
 }
 return hours;
}

/* function getBookedHours(data) {
 let booked = [];
 data.forEach(d => {
     let from = parseTimeTo24Hour(d.timeFrom);
     let to = parseTimeTo24Hour(d.timeTo);
     for (let h = from; h < to; h++) {
         booked.push(formatHour(h));
     }
 });
 return booked;
} */

/* function formatHour(hour) {
	 var period = hour >= 12 ? 'PM' : 'AM';
	 var hr = hour % 12;
	 hr = hr === 0 ? 12 : hr;
	 return hr + period;
} */

function getBookedHours(data) {
    let booked = [];
    data.forEach(d => {
        let from = parseTimeTo24Hour(d.timeFrom);
        let to = parseTimeTo24Hour(d.timeTo);
        for (let h = from; h < to; h += 0.5) {
            booked.push(formatHour(h));
        }
    });
    return booked;
}

function formatHour(hour) {
    let baseHour = Math.floor(hour);
    let minutes = (hour - baseHour) * 60;
    let period = baseHour >= 12 ? 'PM' : 'AM';
    let hr = baseHour % 12;
    hr = hr === 0 ? 12 : hr;
    let minStr = minutes === 0 ? "" : ":30";
    return hr + minStr + period; // e.g. "1PM", "1:30PM"
}
	
/* Slots hanlde form ajax ends ------------------------------------- */

function resetSlots(id) {
	if (id !== "Date" && id !== "batch") {
	    $('#Date').val('');
	    bookedHours = [];
	}
	if(id !="batch"){
 		$('#batch').empty().append('<option value=""><liferay-ui:message key="select" /></option>');
		setBatchSlots("batch");
	}
	 $('#timeFrom').val('');
	 $('#timeTo').val('');
}

// Save Form Starts ----------------------

$("#submitBtn").on("click", function () {
	 const hiddenDocVal = $('#hiddenMedicalDoc').val();
    const selectedFile = $('#medicalCertificate').val();
	 let isFormValid = $("#facilityBookingForm").valid();
	 let isFileValid = true;
	 
	 let sportCourtVal = $('#sportCourt').val();
	 if(sportCourtVal == "swimming" || sportCourtVal == "shooting"){
		 if(!hiddenDocVal && !selectedFile){
		     const errorSpan = document.getElementById("medicalCertificateError");
		     	errorSpan.textContent = "<liferay-ui:message key='please-upload-medical-certificate' />";
			    errorSpan.style.display = "block";
			    isFileValid = false;
		     }
	 }
	 
    if(isFileValid && isFormValid ){
        saveForm("save"); 
    }
});

function saveForm(actionType){
	 console.log("All validations are passed and saving Form ------ ");
	 console.log("actionType: "+actionType);
	 let facilityBookingId = $('#facilityBookingId').val();
	 console.log("facilityBookingId: "+facilityBookingId);
	 let mode = '${mode}';
	 
	 if(mode != "view"){
		 
		 const form = document.getElementById("facilityBookingForm");
		 if(mode == "edit" || actionType == "edit"){
		    form.querySelectorAll("[disabled]").forEach(function (el) {
		        el.disabled = false;
		    });
		 }

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
		        if (data === "success") {
		            var msg = '<span class="text-success"><liferay-ui:message key="details-submitted-successfully"/></span>';
		            if(mode == "edit"){
		            	msg = '<span class="text-success"><liferay-ui:message key="the-details-are-updated-successfully"/></span>';
		            }
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
	 }else{
		var msg = "<liferay-ui:message key='you-canot-submit-details-in-view-mode'/>";
	    $('#success-message').html(msg);
		$("#saveConstructionModal").modal('show');
	 }
	 
}

// save Form Ends -------------------------------------------------

/* Redirect conditions starts ----------------------------------- */

function handleRedirect() {
	var mode = "${mode}";
	var type = "${type}";
	var isHOAdmin = "${isHOAdmin}" === "true"; // ensures it's a boolean
	var isCreateMode = mode === "" || mode === null;

	if (isCreateMode) {
		window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
	} else if (!isHOAdmin && type === "facilityBookingView") {
		window.location.href = "${redirectUrl}&type=userBokingList&clear=true";
	} else if (isHOAdmin && type === "facilityBookingView") {
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
/* Redirect conditions ends -------------------------------------------- */

$("#reset-btn").on("click", function () {
	let mode = '${mode}';
	if(mode == "edit"){
		$("#facilityBookingForm").find("input, select, textarea").each(function () {
		    const $field = $(this);
		    if ($field.prop("disabled") || $field.prop("readonly")) {
		        return; // skip this field
		    }
		    const type = $field.attr("type");
		    if (type === "checkbox" || type === "radio") {
		        $field.prop("checked", false);
		    } else if (type === "file") {
		        $field.val("");
		    } else {
		        $field.val("");
		    }
		});

		// Clear validation
		$("#facilityBookingForm").validate().resetForm();
		$("#facilityBookingForm").validate().resetForm();
	    $('#facilityPhotoPreview, #view_all_photos_msg').empty();
	    $('#facilityPhotos, #facilityPhotosHiddenInput').val('');
	   /*  $('#medicalCertificate').next('.custom-file-label').text($('#chooseFileLabelText').text().trim() || 'Choose file'); */
	   $('#medicalCertificateDeleteButton').click();
	}else{
		$("#facilityBookingForm")[0].reset();
		$("#facilityBookingForm").validate().resetForm();
	    $('#facilityPhotoPreview, #view_all_photos_msg').empty();
	    $('#facilityPhotos, #facilityPhotosHiddenInput').val('');
	   /*  $('#medicalCertificate').next('.custom-file-label').text($('#chooseFileLabelText').text().trim() || 'Choose file'); */
	   $('#medicalCertificateDeleteButton').click();
	}
	
});


/* File Handle */
	const uploadedSingleFiles = {};
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
		
			
			if (!['jpg', 'jpeg', 'png', 'pdf'].includes(ext)) {
				errorSpan.textContent = "<liferay-ui:message key='allowed-file-types-jpg-jpeg-png-pdf' />";
				errorSpan.style.display = 'block';
				fileInput.value = '';
				return;
			}

		if (file.size > 2 * 1024 * 1024) {
			errorSpan.textContent = "<liferay-ui:message key='file-size-less-than-2mb' />";
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

	}

 function removeSingleImageFile(button, inputId, previewContainerId, previewLinkId, hiddenInputId) {
	    delete uploadedSingleFiles[inputId];

	    $('#hiddenMedicalDoc').val('');
	    medicalCertificate = 0;
	    // Hide preview section
	    document.getElementById(previewContainerId).style.display = 'none';

	    // Reset preview link
	    const previewLink = document.getElementById(previewLinkId);
	    previewLink.textContent = '';
	    previewLink.href = '#';

	    // Reset hidden input
	    document.getElementById(hiddenInputId).value = '';

	    // Reset file input value and validation
	    const fileInput = document.getElementById(inputId);
	    if (fileInput) {
	        fileInput.value = '';
	        fileInput.dispatchEvent(new Event('change')); // Notify validators

	        // Revalidate if using jQuery Validate
	        if ($(fileInput).closest("form").length) {
	            $(fileInput).closest("form").validate().element(fileInput);
	        }

	        // Reset file label
	        const label = fileInput.nextElementSibling;
	        if (label && label.classList.contains('custom-file-label')) {
	            const chooseText = document.getElementById('chooseFileLabelText')?.textContent?.trim() || '<liferay-ui:message key="choose-file" />';
	            label.textContent = chooseText;
	        }
	    }
	}
	
</script>
