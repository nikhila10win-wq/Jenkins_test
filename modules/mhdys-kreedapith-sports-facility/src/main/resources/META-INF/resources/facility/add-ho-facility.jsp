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
            <div class="card shadow border-0">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                        <liferay-ui:message key="facility-initiation-by-ho" />
                    </h5>
                    <div>
                        <a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backOrClearUrl()"><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back" /></a>
                    </div>
                </div>
                <form id="sportsFacilityForm" method="POST" enctype="multipart/form-data">
                	<input type="hidden" id="sportsFacilityFormId" value="${sportsFacilityMaster.sportsFacilityId}">
                <div class="card-body">
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="facility-initiation" />
                            </div>
                            <div class="card-body">
                               <div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="facility-Name"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="facilityName" id="facilityName" value="${sportsFacilityMaster.facilityName }" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="facility-Type"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="facilityType" id="facilityType" value="${sportsFacilityMaster.facilityType }" <c:if test="${mode eq 'view'}">disabled</c:if>>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="facility-Area"/><sup class="text-danger">*</sup></label>
											<input type="text" class="form-control" name="facilityArea" id="facilityArea" value="${sportsFacilityMaster.facilityArea }" <c:if test="${mode eq 'view'}">disabled</c:if>>
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
									        <label><liferay-ui:message key="latitude" /><sup class="text-danger">*</sup></label>
									        <input type="hidden" class="form-control" name="latitude" id="latitude"
									            placeholder="<liferay-ui:message key='latitude' />"
									            value="${sportsFacilityMaster.latitude}" readonly />
									    </div>
									</div>
									
									<div class="col-md-6 d-none">
									    <div class="form-group">
									        <label><liferay-ui:message key="longitude" /><sup class="text-danger">*</sup></label>
									        <input type="hidden" class="form-control" name="longitude" id="longitude"
									            placeholder="<liferay-ui:message key='longitude' />"
									            value="${sportsFacilityMaster.longitude}" readonly />
									    </div>
									</div>
								  <!-- Map Container Ends -->
								
								<div class="col-md-6 mt-4">
									<div class="form-group">
										<label for="type"><liferay-ui:message key="type" /><sup class="text-danger">*</sup></label>
										<div class="d-flex mt-2">
										<div class="radio-text mx-4">
											<input type="checkbox" class="form-check-input typeCheckBox" name="typeCheckBox" id="type_monthly" value="Monthly Pass"
											                <c:if test="${mode eq 'view'}">disabled</c:if>>
											<label class="form-check-label mr-3" for="type_monthly"><liferay-ui:message key="monthly-pass" /></label>
										</div>
										<div>
											<input type="checkbox" class="form-check-input typeCheckBox" name="typeCheckBox" id="type_rent" value="Rent Booking"
										                <c:if test="${mode eq 'view'}">disabled</c:if>>
											<label class="form-check-label" for=type_rent><liferay-ui:message key="rent-booking" /></label>
											</div>
										</div>
									</div>
								</div>
									
								  	<div class="col-md-6 mt-4">
										<div class="form-group">
											<label><liferay-ui:message key="fees"/><sup class="text-danger">*</sup></label>
											<input type="number" step="0.01" class="form-control" name="fees" id="fees" value="${sportsFacilityMaster.fees }" <c:if test="${mode eq 'view'}">disabled</c:if>>
										</div>
									</div>
							</div>
							
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                </div> <!-- .card-body (outer) -->
            
             <c:if test="${mode ne 'view'}">    
                <div class="card-footer bg-transparent text-right p-4">
					 <div class="d-flex justify-content-end">
					   <!--  <a href="#" type="button" class="btn btn-secondary cancel-btn me-2" onclick="backOrClearUrl()">
					      <liferay-ui:message key="cancel" />
					    </a> -->
					     <a href="javascript:void(0);" onclick="window.location.href='<%= MhdysKreedapithSportsFacilityPortletKeys.HOMEURL %>?clear=true';" class="btn btn-secondary maha-save-btn" id="modalCloseBtn">
						    <liferay-ui:message key="cancel"/>
						</a>
					    <c:if test="${mode ne 'view'}">
						    <button type="button" class="btn btn-primary reset-btn" id="reset-btn">
						      <liferay-ui:message key="reset" />
						    </button>
						 </c:if>
					    <button type="button" class="btn btn-primary submit-btn" id="submitBtn">
					       <c:if test="${mode eq 'edit'}"><liferay-ui:message key="update" /></c:if> 
					       <c:if test="${mode ne 'edit'}"><liferay-ui:message key="submit" /></c:if> 
					    </button>
					</div>
				</div>
			</c:if>
           </form>     
            </div> <!-- .card -->
        </div> <!-- .row -->
    </div> <!-- .container -->
</div> <!-- .common-forms-div -->


<!-- Validation modal -->
<div class="modal fade" id="saveConstructionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
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

function backOrClearUrl() {
	var mode = "${mode}";
	var isCreateMode = mode === "" || null;
	if(isCreateMode){
    	window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
	}else{
		window.location.href = "${redirectUrl}&type=hoFacilityList&clear=true";
	}
}

	
var uploadedFilesGeoTagPhoto = [];
var $form = $("#sportsFacilityForm");

$.validator.addMethod("containsLetters", function(value, element) {
    return this.optional(element) || /[A-Za-z]/.test(value);
}, "<liferay-ui:message key='field-must-contain-characters' />");

/* $("#reset-btn").on("click", function () {
	 let mode = '${mode}';
	 console.log("mode:: ", mode)

	$("#sportsFacilityForm")[0].reset(); // Reset all fields
    $("input[type='checkbox']").prop('checked', false);
	$("#sportsFacilityForm").validate().resetForm(); // Clear validation
}); */

$("#reset-btn").on("click", function () {
    let mode = '${mode}';
    if (mode === "edit") {
    	// Clear only editable inputs & textareas
        $("#sportsFacilityForm")
            .find("input[type=text]:not(:disabled):not([readonly]), input[type=number]:not(:disabled):not([readonly]), textarea:not(:disabled):not([readonly])")
            .val("");
        // Clear checkboxes and radios (but only if not disabled)
        $("#sportsFacilityForm")
            .find("input[type=checkbox]:not(:disabled), input[type=radio]:not(:disabled)")
            .prop("checked", false);
        // Clear validation errors
        $("#sportsFacilityForm").validate().resetForm();
    } else {
        // Normal reset in create mode
        $("#sportsFacilityForm")[0].reset();
        $("#sportsFacilityForm").validate().resetForm();
        $("input[type='checkbox']").prop('checked', false);
    }
});



$('.typeCheckBox').on('change', function () {
    if (this.checked) {
        $('.typeCheckBox').not(this).prop('checked', false);
    }
});

function closeModal(){
	 $("#saveConstructionModal").modal('hide');
	 var mode = "${mode}";
		var isCreateMode = mode === "" || null;
		if(isCreateMode){
	    	window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
		}else{
			window.location.href = "${redirectUrl}&type=hoFacilityList&clear=true";
		}
}

$(document).on('keypress', '#fees', function (e) {
    const char = String.fromCharCode(e.which);
    if (!/[0-9.]/.test(char)) {
        e.preventDefault();
    }
});

$(document).ready(function () {
	
	var mode = "${mode}";
	var latitudeStr = "${sportsFacilityMaster.latitude}";
	var longitudeStr = "${sportsFacilityMaster.longitude}";

	var latitude = parseFloat(latitudeStr);
	var longitude = parseFloat(longitudeStr);
	var isValidCoordinates = !isNaN(latitude) && !isNaN(longitude);
	var isCreateMode = mode === "" || null;
	var isEditMode = mode === "edit";
	var isViewMode = mode === "view";

	if (( (isEditMode || isViewMode) && isValidCoordinates) || isCreateMode) {
	    $("#map").removeClass("d-none");
	    var defaultLat = 19.7515;
	    var defaultLon = 75.7139;
	    var lat = isValidCoordinates ? latitude : defaultLat;
	    var lon = isValidCoordinates ? longitude : defaultLon;
	    console.log("lat:", lat);
	    console.log("lon:", lon);
	    initializeMap(lat, lon, isViewMode ? false : true); 
	}

    
    console.log("Mode:", mode);
    console.log("Latitude:", latitude);
    console.log("Longitude:", longitude);
    
	
	let typeValue = "${sportsFacilityMaster.type}";
    if (typeValue) {
        const types = typeValue.split(",").map(t => t.trim());

        types.forEach(type => {
            if (type === "Monthly Pass") {
                $("#type_monthly").prop("checked", true);
            } else if (type === "Rent Booking") {
                $("#type_rent").prop("checked", true);
            }
        });
    }
    
    const uploadedFilesGeoTagPhoto = [];

    let validationRules = {
        facilityName: { required: true,alphanumericOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75 },
        facilityType: { required: true, alphanumericOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75 },
        facilityArea: { required: true,alphanumericOnly:true,singleSpaceOnly:true, noEdgeSpaces:true, minlength: 3, maxlength: 75 },
        typeCheckBox: { required: true },
        fees: { required: true, number: true, min: 1, max: 9999999999}
    };

    let validationMessages = {
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
        }
    };

    /* $form.validate({
        rules: validationRules,
        messages: validationMessages,
        errorElement: 'span',
        errorClass: 'text-danger'
    }); */
    
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

    $.validator.addMethod("containsLetters", function(value, element) {
        return this.optional(element) || /[A-Za-z]/.test(value);
    }, "<liferay-ui:message key='field-must-contain-characters' />");

    $.validator.addMethod("validContact", function(value, element) {
        return this.optional(element) || /^[6-9]\d{9}$/.test(value);
    }, '<liferay-ui:message key="contact-number-invalid-pattern" />');

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
        if ($("#sportsFacilityForm").valid()) {
            saveForm("save");
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
		 if(mode == "edit"){
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
		            	$('#submitBtn').prop('disabled', true);
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
		var msg = "<liferay-ui:message key="you-canot-submit-details-in-view-mode"/>";
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
 
/* var isViewMode = typeof mode !== "undefined" && (mode === "view");
var isEditMode = typeof mode !== "undefined" && (mode === "edit");
 
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
            $("#locationNotFound").html("We are facing a technical issue, please select a location on the map.");
        });
} */

</script>
