<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp"%>
<!-- Include Leaflet CSS & JS -->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

<portlet:resourceURL id="<%=MhdysKreedapithSportsFacilityPortletKeys.SAVE_SPORTSFACILITY_RESOURCECOMMAND %>" var="SaveSportsFacilityUrl" />

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="card shadow border-0 w-100">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                        <liferay-ui:message key="facility-initiation-by-dso/tso" />
                    </h5>
                    <div>
                        <a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backOrClearUrl()"><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back" /></a>
                    </div>
                </div>
                <form id="sportsFacilityForm" method="POST" enctype="multipart/form-data">
                
               <input type="hidden" id="sportsFacilityFormId" value="">
                <div class="card-body">
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="facility-initiation-by-dso/tso" />
                            </div>
                            <div class="card-body">
                            
                           			 <c:choose>
								        <c:when test="${mode eq 'edit' || mode eq 'view' || !empty filteredList}">
								         	<div class="row">
												<c:if test="${!empty filteredList}">	
													<div class="col-md-6">
														<div class="form-group">
															<label><liferay-ui:message key="facility-Name"/><sup class="text-danger">*</sup></label>
															<select class="form-control" name="facilityNameDropdown" id="facilityNameDropdown">
																	<option value=""><liferay-ui:message key="select"/></option>
																	<c:forEach var="facility" items="${filteredList}">
																	    <option value="${facility.sportsFacilityId}">${facility.facilityName}</option>
																	</c:forEach>
															</select>
								
														</div>
													</div>
												</c:if>
												
													<c:choose>
												        <c:when test="${mode eq 'edit' || mode eq 'view'}">
												           <div class="col-md-6">
																<div class="form-group">
																	<label><liferay-ui:message key="facility-Name"/><sup class="text-danger">*</sup></label>
																	<input type="text" class="form-control" name="facilityName" id="facilityName" readonly="readonly">
																</div>
															</div>
												        </c:when>
												        <c:otherwise>
												           <div class="col-md-6">
																<div class="form-group d-none">
																	<label><liferay-ui:message key="facility-Name"/><sup class="text-danger">*</sup></label>
																	<input type="text" class="form-control" name="facilityName" id="facilityName" readonly="readonly">
																</div>
															</div>
												        </c:otherwise>
												    </c:choose>
						    
													<div class="col-md-6">
														<div class="form-group">
															<label><liferay-ui:message key="facility-Type"/><sup class="text-danger">*</sup></label>
															<input type="text" class="form-control" name="facilityType" id="facilityType" <c:if test="${mode eq 'view'}">disabled</c:if>>
														</div>
													</div>
													
													<div class="col-md-6">
														<div class="form-group">
															<label><liferay-ui:message key="facility-Area"/><sup class="text-danger">*</sup></label>
															<input type="text" class="form-control" name="facilityArea" id="facilityArea" <c:if test="${mode eq 'view'}">disabled</c:if>>
														</div>
													</div>
													
													<div class="col-md-12">
												    <div class="form-group">
												        <label><liferay-ui:message key="Facility-Location" /><sup class="text-danger">*</sup></label><br/>
												        
												        <div class="input-group">
													        <input type="text" id="locationSearch" class="form-control" placeholder="Enter Area or City Name" <c:if test="${mode eq 'view'}">disabled</c:if>>
													        <button type="button" class="btn btn-primary" onclick="searchLocation()" <c:if test="${mode eq 'view'}">disabled</c:if>>Search</button>
													    </div>
													    <span class="text-danger d-block" id="locationNotFound"></span>
													    
												        <button type="button" class="btn btn-primary d-none" id="getGisLocation" onclick="getLocation()" <c:if test="${mode eq 'view'}">disabled</c:if> >
												            <liferay-ui:message key="get-GIS-location" />
												        </button>
												    </div>
												    <!-- Map Container -->
									                 <div id="map" class="d-none" style="height: 300px; border: 1px solid #ccc; z-index: 1;"></div>
												   </div>
													
												  <div class="col-md-6 d-none">
													    <div class="form-group">
													        <label><liferay-ui:message key="latitude" /></label>
													        <input type="hidden" class="form-control" name="latitude" id="latitude"
													            placeholder="<liferay-ui:message key='latitude' />"
													            value="${latitude}" readonly />
													    </div>
													</div>
													
													<div class="col-md-6 d-none">
													    <div class="form-group">
													        <label><liferay-ui:message key="longitude" /></label>
													        <input type="hidden" class="form-control" name="longitude" id="longitude"
													            placeholder="<liferay-ui:message key='longitude' />"
													            value="${longitude}" readonly />
													    </div>
													</div>
												  <!-- Map Container Ends -->
												
												<div class="col-md-6 mt-4">
													<div class="form-group">
														<label for="type"><liferay-ui:message key="type" /><sup class="text-danger">*</sup></label>
														<div class="d-flex mt-2">
														<div class="radio-text mx-4">
															<input type="checkbox" class="form-check-input typeCheckBox" name="typeCheckBox" id="type_monthly" value="Monthly Pass" <c:if test="${mode eq 'view'}">disabled</c:if>>
															<label class="form-check-label mr-3" for="type_monthly"><liferay-ui:message key="monthly-pass" /></label>
														</div>
														<div class="radio-text mx-4">
															<input type="checkbox" class="form-check-input typeCheckBox" name="typeCheckBox" id="type_rent" value="Rent Booking" <c:if test="${mode eq 'view'}">disabled</c:if>>
															<label class="form-check-label" for=type_rent><liferay-ui:message key="rent-booking" /></label>
															</div>
														</div>
													</div>
												</div>
				
													
												  <div class="col-md-6 mt-4">
														<div class="form-group">
															<label><liferay-ui:message key="fees"/><sup class="text-danger">*</sup></label>
															<input type="number" step="0.01" class="form-control" name="fees" id="fees" <c:if test="${mode eq 'view'}">disabled</c:if>>
														</div>
													</div>
													
													<!-- Extra/additional fields -->
													
													<div class="col-md-6">
														<div class="form-group">
															<label><liferay-ui:message key="booking-Url"/><sup class="text-danger">*</sup></label>
															<input type="text" class="form-control" name="bookingUrl" id="bookingUrl" <c:if test="${mode eq 'view'}">readonly</c:if>>
														</div>
													</div>
													
												     <div class="col-md-6">
														<div class="form-group">
															<label><liferay-ui:message key="contact-Person-Name"/><sup class="text-danger">*</sup></label>
															<input type="text" class="form-control" name="contactPersonName" id="contactPersonName" <c:if test="${mode eq 'view'}">readonly</c:if>>
														</div>
													</div>
													
													<div class="col-md-6">
														<div class="form-group">
															<label><liferay-ui:message key="contact-Person-Number"/><sup class="text-danger">*</sup></label>
															<input type="text" class="form-control" name="contactPersonNumber" id="contactPersonNumber" <c:if test="${mode eq 'view'}">readonly</c:if>>
														</div>
													</div>
													
												
													  <div class="col-md-6">
														  <div class="form-group">
														    <label for="GeoTagPhoto">
														      <liferay-ui:message key="geo-tag-photo" />
														      <sup class="text-danger">*</sup>
														      <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file-of-size-2mb' />"></em>
														    </label>
														
														    <div class="custom-file">
														      <input type="file" class="custom-file-input geoTagPhoto" id="GeoTagPhoto" name="GeoTagPhoto" multiple
														        onchange="handleMultipleFileUpload(this, 'GeoTagPhoto', 'geoTagPhotoPreviewContainer', 'geoTagPhotoPreviewList', 'geoTagPhotoError', 'geoTagPhotoHiddenInput')"
														        <c:if test="${mode eq 'view'}">disabled</c:if> />
														      <label class="custom-file-label" for="GeoTagPhoto"><liferay-ui:message key="choose-file" /></label>
														    </div>
														
														    <span id="geoTagPhotoError" class="text-danger mt-2 d-block"></span>
														    
														    <input type="hidden" id="geoTagPhotoHiddenInput" name="geoTagPhotoHiddenInput" />
														
														    <div class="mt-3" id="geoTagPhotoPreviewContainer" style="display: none;">
														      <ul id="geoTagPhotoPreviewList" class="list-group"></ul>
														    </div>
														  </div>
														</div>
												</div>
									        </c:when>
									        <c:otherwise>
									           <div class="text-center"><p><liferay-ui:message key="no-facilities-are-available-right-now" /></p></div>
									        </c:otherwise>
									    </c:choose>
								    
                               
							
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                </div> <!-- .card-body (outer) -->
                
                
                <c:choose>
			        <c:when test="${mode eq 'view' || (type eq 'initiateForm' && empty filteredList)}">
			           
			        </c:when>
			        <c:otherwise>
						  <div class="card-footer bg-transparent text-right p-4">
							 <div class="d-flex justify-content-end">
					            <a href="javascript:void(0);" onclick="window.location.href='<%= MhdysKreedapithSportsFacilityPortletKeys.HOMEURL %>?clear=true';" class="btn btn-secondary maha-save-btn" id="modalCloseBtn">
								    <liferay-ui:message key="cancel"/>
								</a>
							    <button type="button" class="btn btn-primary reset-btn" id="reset-btn">
							      <liferay-ui:message key="reset" />
							    </button>
							    <button type="button" class="btn btn-primary submit-btn" id="submitBtn">
							       <c:if test="${mode eq 'edit'}"><liferay-ui:message key="update" /></c:if> 
							       <c:if test="${mode ne 'edit'}"><liferay-ui:message key="submit" /></c:if> 
							    </button>
								</div>
							</div>
			        </c:otherwise>
			    </c:choose>
												    
            <%--     <c:if test="${mode ne 'view'}">
	                <div class="card-footer bg-transparent text-right p-4">
						 <div class="d-flex justify-content-end">
				            <a href="javascript:void(0);" onclick="window.location.href='<%= MhdysKreedapithSportsFacilityPortletKeys.HOMEURL %>?clear=true';" class="btn btn-secondary maha-save-btn" id="modalCloseBtn">
							    <liferay-ui:message key="cancel"/>
							</a>
						    <button type="button" class="btn btn-primary reset-btn" id="reset-btn">
						      <liferay-ui:message key="reset" />
						    </button>
						    <button type="button" class="btn btn-primary submit-btn" id="submitBtn">
						       <c:if test="${mode eq 'edit'}"><liferay-ui:message key="update" /></c:if> 
						       <c:if test="${mode ne 'edit'}"><liferay-ui:message key="submit" /></c:if> 
						    </button>
						    
					</div>
				</div>
				</c:if> --%>
           </form>     
            </div> <!-- .card -->
        </div> <!-- .row -->
    </div> <!-- .container -->
</div> <!-- .common-forms-div -->


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
      <div class="modal-footer d-flex justify-content-end">
		    <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
		                <liferay-ui:message key="close"/>
		    </a>
		</div>

    </div>
  </div>
</div>

<script>

$('.typeCheckBox').on('change', function () {
    if (this.checked) {
        $('.typeCheckBox').not(this).prop('checked', false);
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

var uploadedFilesGeoTagPhoto = [];
var $form = $("#sportsFacilityForm");


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

$("#reset-btn").on("click", function () {
	let mode = '${mode}';
	if(mode == "edit"){
		//$('#bookingUrl, #contactPersonName, #contactPersonNumber').val('');
		resetFiles();
		var $form = $("#sportsFacilityForm");
		$form.find("input, select, textarea").each(function(){
		  $(this).data("val", $(this).val());
		});
		$form[0].reset();
		$form.find(":input:disabled, :input[readonly]").each(function(){
		  $(this).val($(this).data("val"));
		});
		 $("#sportsFacilityForm").validate().resetForm(); // Clear validation
	}else{
		resetFiles();
		$("#sportsFacilityForm")[0].reset(); // Reset all fields
		$("#sportsFacilityForm").validate().resetForm(); // Clear validation
	}
  
});
function resetFiles(){

	 uploadedFilesGeoTagPhoto = [];
	    // 2. Reset DOM elements
	    const previewContainer = document.getElementById("geoTagPhotoPreviewContainer");
	    const previewList = document.getElementById("geoTagPhotoPreviewList");
	    const errorSpan = document.getElementById("geoTagPhotoErrorSpan");
	    const hiddenInput = document.getElementById("geoTagPhotoHiddenInput");
	    const fileInput = document.getElementById("GeoTagPhoto");

	    if (previewList) previewList.innerHTML = "";
	    if (previewContainer) previewContainer.style.display = "none";
	    if (errorSpan) {
	        errorSpan.textContent = "";
	        errorSpan.style.display = "none";
	    }
	    if (hiddenInput) hiddenInput.value = "";
	    if (fileInput) fileInput.value = "";
	    clearInputFile("GeoTagPhoto");
}

$(document).ready(function () {
     /* $('#getGisLocation').click();  */

    var facilitiesListStr = '${empty filteredList ? "[]" : filteredList}';
    var facilitiesList = JSON.parse(facilitiesListStr);
    console.log("facilitiesList:: "+facilitiesList);
    $('#facilityNameDropdown').on('change', function () {
        const selectedId = $(this).val();
        if (!selectedId) {
            // Clear all fields
            $('#facilityName, #facilityType, #facilityArea, #fees').val('');
            $('input[type=checkbox][name=typeCheckBox]').prop('checked', false);
            return;
        }

        const selectedFacility = facilitiesList.find(f => f.sportsFacilityId == selectedId);
        if (selectedFacility) {
        	$("#sportsFacilityForm").validate().resetForm(); // clear validation errors
            $('#sportsFacilityFormId').val(selectedFacility.sportsFacilityId);
            $('#facilityName').val(selectedFacility.facilityName);
            $('#facilityType').val(selectedFacility.facilityType);
            $('#facilityArea').val(selectedFacility.facilityArea);
            $('#fees').val(selectedFacility.fees);
            /* $('#bookingUrl').val(selectedFacility.bookingUrl || '');
            $('#contactPersonName').val(selectedFacility.contactPersonName || '');
            $('#contactPersonNumber').val(selectedFacility.contactPersonNumber || ''); */

            // Handle checkbox values
            $('input[type=checkbox][name=typeCheckBox]').each(function () {
                const val = $(this).val();
                $(this).prop('checked', selectedFacility.type?.includes(val));
            });

            // Latitude/Longitude
            $('#latitude').val(selectedFacility.latitude);
            $('#longitude').val(selectedFacility.longitude);

            // Optional: Auto-show map
            if (selectedFacility.latitude && selectedFacility.longitude) {
                $("#map").removeClass("d-none");
                initializeMap(parseFloat(selectedFacility.latitude), parseFloat(selectedFacility.longitude), true);
            }
        }
    });
    
    
    /* If Editing after Availablity */
    var sportsFacilityMasterStr = '${empty sportsFacilityMaster ? "[]" : sportsFacilityMaster}';
    var sportsFacilityMaster = JSON.parse(sportsFacilityMasterStr);
    console.log("sportsFacilityMaster -- "+sportsFacilityMaster);
    
    if(sportsFacilityMaster){
    	  $('#sportsFacilityFormId').val(sportsFacilityMaster.sportsFacilityId);
          $('#facilityName').val(sportsFacilityMaster.facilityName);
          $('#facilityType').val(sportsFacilityMaster.facilityType);
          $('#facilityArea').val(sportsFacilityMaster.facilityArea);
          $('#fees').val(sportsFacilityMaster.fees);
          $('#bookingUrl').val(sportsFacilityMaster.bookingUrl || '');
          $('#contactPersonName').val(sportsFacilityMaster.contactPersonName || '');
          $('#contactPersonNumber').val(sportsFacilityMaster.contactPersonNumber || '');

          // Handle checkbox values
          $('input[type=checkbox][name=typeCheckBox]').each(function () {
              const val = $(this).val();
              $(this).prop('checked', sportsFacilityMaster.type?.includes(val));
          });

          // Latitude/Longitude
          $('#latitude').val(sportsFacilityMaster.latitude);
          $('#longitude').val(sportsFacilityMaster.longitude);

          // Optional: Auto-show map
          if (sportsFacilityMaster.latitude && sportsFacilityMaster.longitude) {
              $("#map").removeClass("d-none");
              initializeMap(parseFloat(sportsFacilityMaster.latitude), parseFloat(sportsFacilityMaster.longitude), true);
          }
    }
    
    let geoTagPhotoJson = ${not empty geoTagPhoto ? geoTagPhoto : '[]'};
	console.log("geoTagPhotoJson:: "+geoTagPhotoJson);

	if (Array.isArray(geoTagPhotoJson)) {
	    uploadedFilesGeoTagPhoto = geoTagPhotoJson.map(item => ({
	        name: item.fileName,
	        fileEntryId: item.fileEntryId,
	        url: item.url || '#',
	        isExisting: true,
	        markedForDelete: false
	    }));
	    renderFilePreviews("GeoTagPhoto", document.getElementById("geoTagPhotoPreviewContainer"), document.getElementById("geoTagPhotoPreviewList"), document.getElementById("geoTagPhotoError"), document.getElementById("geoTagPhotoHiddenInput"));
	}
	
	/* --------------------------------------- */
    let validationRules = {
    	facilityNameDropdown: { required: true },
    	 facilityName: { required: true,alphanumericOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75 },
         facilityType: { required: true, alphanumericOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75 },
         facilityArea: { required: true,alphanumericOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75 },
         typeCheckBox: { required: true },
         fees: { required: true, number: true, min: 1, max: 9999999999},
        bookingUrl: { required: true,validBookingURL:true, minlength: 10, maxlength: 2083 },
        contactPersonName: { required: true,alphabetsOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75 },
        contactPersonNumber: { required: true, number: true, validContact:true, minlength: 10, maxlength: 10 },
    };

    let validationMessages = {
    	
    	facilityNameDropdown: {
    		 required: "<liferay-ui:message key='please-enter-facility-name' />",
    	},
    	  facilityName: {
              required: "<liferay-ui:message key='please-enter-facility-name' />",
              minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
              maxlength: "<liferay-ui:message key='please-enter-max-75-chars' />"
          },
          facilityType: {
              required: "<liferay-ui:message key='please-enter-facility-type' />",
              minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
              maxlength: "<liferay-ui:message key='please-enter-max-75-chars' />"
          },
          facilityArea: {
              required: "<liferay-ui:message key='please-enter-facility-area' />",
              minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
              maxlength: "<liferay-ui:message key='please-enter-max-75-chars' />"
          },
          typeCheckBox: {
              required: "<liferay-ui:message key='please-enter-type' />",
          },
          fees: {
              required: "<liferay-ui:message key='please-enter-fees' />",
              number: "<liferay-ui:message key='fees-must-be-number' />",
              min: '<liferay-ui:message key="it-must-be-positive-or-greater-than-0" />',
              max: "<liferay-ui:message key='please-enter-max-10-digits' />"
          },
        bookingUrl: {
            required: "<liferay-ui:message key='booking-url-required' />",
            minlength: "<liferay-ui:message key='please-enter-min-10-characters' />",
            maxlength: "<liferay-ui:message key='max-2083-chars' />"
        },
        contactPersonName: {
            required: "<liferay-ui:message key='contact-person-name-required' />",
            minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
            maxlength: "<liferay-ui:message key='please-enter-max-75-chars' />"
        },
        contactPersonNumber: {
            required: "<liferay-ui:message key='contact-person-number-required' />",
            number: "<liferay-ui:message key='only-numbers' />",
            minlength: "<liferay-ui:message key='contact-number-invalid-pattern' />",
            maxlength: "<liferay-ui:message key='contact-number-invalid-pattern' />"
        }
    };

    $("#sportsFacilityForm").validate({
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
            if (element.attr("name") === "typeCheckBox") {
                $('.d-flex.mt-2').next('span.text-danger').remove();
                $('.d-flex.mt-2').last().after(error);
            } else {
                error.insertAfter(element);
            }
        }
    });
    
 // Add validation manually for GeoTagPhoto
   /*  $form.find("#GeoTagPhoto").rules("add", {
        required: function () {
            return uploadedFilesGeoTagPhoto.length === 0;
        },
        messages: {
            required: '<liferay-ui:message key="please-upload-geo-tag-photo" />'
        }
    }); */
 
    $.validator.addMethod("validContact", function(value, element) {
        return this.optional(element) || /^[6-9]\d{9}$/.test(value);
    }, '<liferay-ui:message key="contact-number-invalid-pattern" />');

    $.validator.addMethod("validBookingURL", function(value, element) {
        if (this.optional(element)) return true;

        // Basic URL pattern (accepts http, https, optional www, domain, etc.)
        var urlPattern = /^(https?:\/\/)?(www\.)?[a-zA-Z0-9-]+\.[a-z]{2,}([\/\w\-\.]*)*\/?$/;

        return urlPattern.test(value);
    }, "<liferay-ui:message key='please-enter-valid-booking-url' />");

    $.validator.addMethod("alphabetsOnly", function(value, element) {
        return this.optional(element) || /^[A-Za-z ]+$/.test(value);
    }, "<liferay-ui:message key='please-enter-alphabets' />");
    
    $.validator.addMethod("alphanumericOnly", function(value, element) {
        return this.optional(element) || /^[A-Za-z0-9 ]+$/.test(value);
    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");

    $.validator.addMethod("validSpaces", function(value, element) {
        // Checks if there are no leading/trailing spaces and only single space between words
        return this.optional(element) || /^[^\s]+(?:\s[^\s]+)*$/.test(value);
    }, "<liferay-ui:message key='Spaces-are-allowed-only-between-words-and-No-leading-or-trailing-spaces' />");

    $.validator.addMethod("noEdgeSpaces", function(value, element) {
    	  return this.optional(element) || value === value.trim();
    }, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

    $.validator.addMethod("singleSpaceOnly", function(value, element) {
      return this.optional(element) || !/\s{2,}/.test(value);
    }, "<liferay-ui:message key='only-one-space-between-words-allowed' />");

    $.validator.addMethod("validCharacters", function(value, element) {
      return this.optional(element) || /^[A-Za-z0-9\s.,/#-]*$/.test(value);
    }, "<liferay-ui:message key='please-enter-valid-characters' />");

    $.validator.addMethod("noConsecutiveSpecials", function(value, element) {
    	return this.optional(element) || !/([.,/#-]\s*){2,}/.test(value);
    }, "<liferay-ui:message key='no-consecutive-specials-allowed' />");

    $.validator.addMethod("onlyDotAtEnd", function(value, element) {
      return this.optional(element) || /\.$/.test(value) || /[A-Za-z0-9]$/.test(value);
    }, "<liferay-ui:message key='only-dot-at-end-allowed' />");

    $.validator.addMethod("validPersonName", function(value, element) {
    	// It Should accept alphabets space .
        return this.optional(element) || /^[A-Za-z. ]+$/.test(value.trim());
    }, "<liferay-ui:message key='please-enter-valid-name' />");
    
    	$("#submitBtn").on("click", function () {
    		console.log("uploadedFilesGeoTagPhoto -- "+ uploadedFilesGeoTagPhoto.length);
    		 const activeFiles = uploadedFilesGeoTagPhoto.filter(f => !f.markedForDelete);
    		 let isFileValid = true;
    		 const isFormValid = $("#sportsFacilityForm").valid();
    	    const errorSpan = document.getElementById("geoTagPhotoError");
    		if (activeFiles.length == 0) {
    		    errorSpan.textContent = "<liferay-ui:message key='please-upload-geo-tag-photo' />";
    		    errorSpan.style.display = "block";
    		    isFileValid = false;
    		}else {
    	        errorSpan.innerHTML = "";
    	        errorSpan.style.display = "none";
    	    }
    	    if(isFileValid && isFormValid){
    	    	console.log("Validations passed  ------------- ")
    	        saveForm("edit");  
    	    }
    	    
    	});
    	
});




function saveForm(actionType){
	 console.log("All validations are passed and saving Form ------ ");
	 console.log("actionType: "+actionType);
	 let sportsFacilityFormId = $('#sportsFacilityFormId').val();
	 console.log("sportsFacilityFormId: "+sportsFacilityFormId);
	 let mode = '${mode}';
	 
	 if(mode != "view"){
		 
		 const form = document.getElementById("sportsFacilityForm");
		 if(mode == "edit" || actionType == "edit"){
		    form.querySelectorAll("[disabled]").forEach(function (el) {
		        el.disabled = false;
		    });
		 }

		    const formData = new FormData(form); 
		    formData.append("actionType", actionType);
		    formData.append("mode", mode);
		    if(sportsFacilityFormId){
		    	formData.append("sportsFacilityFormId", sportsFacilityFormId);
		    }
		 
		    $.ajax({
		        type: "POST",
		        url: "${SaveSportsFacilityUrl}",
		        data: formData,
		        contentType : false,
				cache : false,
				processData : false,
		        success: function(data){
		        console.log("data:: "+data)
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

// Map
var map;
var marker;
var latElement = document.getElementById('latitude');
var lonElement = document.getElementById('longitude');

// Retrieve stored coordinates
var storedLat = parseFloat(latElement.value);
var storedLon = parseFloat(lonElement.value);
 
/* let isViewMode = typeof mode !== "undefined" && (mode === "view");
let isEditMode = typeof mode !== "undefined" && (mode === "edit");
 
 if ((isViewMode||isEditMode) && !isNaN(storedLat) && !isNaN(storedLon)) {
    $("#map").removeClass("d-none");
    initializeMap(storedLat, storedLon, false);
} */
 
function getLocation() {debugger
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;
            $("#map").removeClass("d-none");
            initializeMap(lat, lon, true); // Edit mode
        }, showError);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}
 
function initializeMap(lat, lon, isEditable) {debugger
    if (!map) {
        map = L.map('map').setView([lat, lon], 13);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);
        latElement.value = lat.toFixed(6);
        lonElement.value = lon.toFixed(6);
	    } else {
        map.setView([lat, lon], 13);
    }
 
    // Remove existing marker
    if (marker) {
        map.removeLayer(marker);
    }
 
    // Add marker with conditional interactivity
    marker = L.marker([lat, lon], { draggable: isEditable }).addTo(map);
 
    if (isEditable) {
        map.on('click', function (e) {
            updateCoordinates(e.latlng.lat, e.latlng.lng);
        });
 
        marker.on('dragend', function (event) {
            let updatedLatLng = event.target.getLatLng();
            updateCoordinates(updatedLatLng.lat, updatedLatLng.lng);
        });
    }
}
 
function updateCoordinates(lat, lon) {debugger
    latElement.value = lat.toFixed(6);
    lonElement.value = lon.toFixed(6);
    marker.setLatLng([lat, lon]);
}
 
function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            alert("User denied the request for Geolocation.");
            break;
        case error.POSITION_UNAVAILABLE:
            alert("Location information is unavailable.");
            break;
        case error.TIMEOUT:
            alert("The request to get user location timed out.");
            break;
        case error.UNKNOWN_ERROR:
            alert("An unknown error occurred.");
            break;
    }
}

/* Multiple Documents */


function handleMultipleFileUpload(fileInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    let uploadedFiles = getUploadedFileArray(inputId);

    const newFiles = Array.from(fileInput.files);
    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
    const totalFiles = activeFilesCount + newFiles.length;

    if (totalFiles > 10) {
        errorSpan.textContent = "You can upload a maximum of 10 files.";
        errorSpan.style.display = "block";
        fileInput.value = "";
        return;
    }

    for (let file of newFiles) {
        const ext = file.name.split('.').pop().toLowerCase();
        if (!['jpg', 'jpeg', 'png'].includes(ext)) {
            errorSpan.textContent = "<liferay-ui:message key='allowed-only-jpg-jpeg-png-files' />";
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        if (file.size >= 2 * 1024 * 1024) {
            errorSpan.textContent = "<liferay-ui:message key='file-size-must-be-less-than-2mb' />";
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        if (uploadedFiles.some(f => f.name === file.name && !f.markedForDelete)) {
            errorSpan.textContent = "<liferay-ui:message key='this-file-is-already-uploaded' />";
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }
    }

    errorSpan.textContent = "";
    errorSpan.style.display = "none";

    newFiles.forEach(file => {
        uploadedFiles.push({
            file: file,
            name: file.name,
            isExisting: false,
            markedForDelete: false
        });
    });

    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
     /* fileInput.value = "";  */
}

function removeFile(index, previewContainerId, previewListId, errorSpanId, hiddenInputId, inputId) {
    let uploadedFiles = getUploadedFileArray(inputId);

    if (index < 0 || index >= uploadedFiles.length) return;

    if (uploadedFiles[index].isExisting) {
        uploadedFiles[index].markedForDelete = true;
    } else {
        uploadedFiles.splice(index, 1);
    }

    const previewContainer = document.getElementById(previewContainerId);
    const fileInput = document.getElementById(inputId);
    const label = fileInput.closest('.custom-file').querySelector('.custom-file-label');
    // Reset input and label
    fileInput.value = '';
    label.classList.remove('selected');
    label.innerHTML = 'Choose file';
    // Hide preview
    previewContainer.style.display = 'none';
    previewContainer.querySelector('ul').innerHTML = '';
    
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
    $form.find("#" + inputId).valid();
    const hasActiveFiles = uploadedFiles.some(f => !f.markedForDelete);
    errorSpan.style.display = hasActiveFiles ? "none" : "block";
    errorSpan.textContent = hasActiveFiles ? "" : "Please upload at least one file.";
}

function renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput) {
    let uploadedFiles = getUploadedFileArray(inputId);

    previewList.innerHTML = "";

    uploadedFiles.forEach((fileObj, index) => {
        if (fileObj.markedForDelete) return;

        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";

        const link = document.createElement("a");
        link.href = fileObj.isExisting ? (fileObj.url || "#") : URL.createObjectURL(fileObj.file);
        link.textContent = fileObj.name;
        link.target = "_blank";
        link.style.cssText = "flex-grow: 1; text-decoration: none; white-space: nowrap; overflow: hidden; max-width: 200px;";
        li.appendChild(link);
        let mode = '${mode}';
        if(mode!="view"){
	        const btn = document.createElement("button");
	        btn.type = "button";
	        btn.className = "btn btn-primary";
	        btn.innerHTML = '<i class="bi bi-x-circle-fill"></i>';
	        btn.onclick = () => removeFile(index, previewContainer.id, previewList.id, errorSpan.id, hiddenInput.id, inputId);
        	btn.style.border = "none";
	        li.appendChild(btn);
        }
        	li.style.backgroundColor = "transparent";
        	li.style.border = "none";
        previewList.appendChild(li);
    });

    previewContainer.style.display = uploadedFiles.some(f => !f.markedForDelete) ? "block" : "none";
    /* hiddenInput.value = uploadedFiles.filter(f => !f.markedForDelete).map(f => f.name).join(','); */
    hiddenInput.value = JSON.stringify(uploadedFiles.filter(f => !f.markedForDelete));
}


function getUploadedFileArray(inputId) {
    const map = {
        'GeoTagPhoto': uploadedFilesGeoTagPhoto
    };
    return map[inputId];
}

function clearInputFile(inputId){
	const fileInputElement = document.getElementById(inputId);
	  if (fileInputElement) {
	        fileInputElement.value = '';
	        fileInputElement.dispatchEvent(new Event('change')); // Notify validators

	        // Revalidate if using jQuery Validate
	        if ($(fileInputElement).closest("form").length) {
	            $(fileInputElement).closest("form").validate().element(fileInputElement);
	        }

	        // Reset file label
	        const label = fileInputElement.nextElementSibling;
	        if (label && label.classList.contains('custom-file-label')) {
	            const chooseText = document.getElementById('chooseFileLabelText')?.textContent?.trim() || 'Choose file';
	            label.textContent = chooseText;
	        }
	    }
}

function searchLocation() {
    var location = document.getElementById('locationSearch').value;

    if (!location.trim()) {
        $("#locationNotFound").html("Please enter a location name.");
        return;
    }

    fetch('https://us1.locationiq.com/v1/search?key=pk.3fedb4a22572cf3542da5b4400f972e0&q=' 
          + encodeURIComponent(location) + '&format=json')
        .then(response => response.json())
        .then(function(data) {
            if (data.length > 0) {
                var lat = parseFloat(data[0].lat);
                var lon = parseFloat(data[0].lon);
                $('#map').removeClass('d-none');
                initializeMap(lat, lon, true);
                updateCoordinates(lat, lon);
                $("#locationNotFound").html('');
            } else {
                $("#locationNotFound").html("Location not found. Please try again.")
            }
        })
        .catch(function(err) {
            console.error('Geocoding error: ', err);
            $("#locationNotFound").html("We are facing a technical issue, please select a location on the map.");
        });
}

/* function searchLocation() {
    var location = document.getElementById('locationSearch').value;

    if (!location.trim()) {
    	$("#locationNotFound").html("Please enter a location name.")
        return;
    }

    fetch('https://nominatim.openstreetmap.org/search?format=json&q=' + encodeURIComponent(location))
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            if (data.length > 0) {
                var lat = parseFloat(data[0].lat);
                var lon = parseFloat(data[0].lon);
                $('#map').removeClass('d-none');
                initializeMap(lat, lon, true); // allow editing
                updateCoordinates(lat, lon);   // update hidden fields
                $("#locationNotFound").html('');
            } else {
            	$("#locationNotFound").html("Location not found. Please try again.")
            }
        })
        .catch(function(err) {
            console.error('Geocoding error: ', err);
            $("#locationNotFound").html("We are facing a technical issue, please select a location on the map.")
        });
} */

</script>
