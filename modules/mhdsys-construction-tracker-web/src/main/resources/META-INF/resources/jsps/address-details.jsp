<%@ include file="/init.jsp"%>
<!-- Include Leaflet CSS & JS -->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

<c:set var="latitude" value="${not empty constructionTrackerDTO.latitude ? constructionTrackerDTO.latitude : '19.7515'}" />
<c:set var="longitude" value="${not empty constructionTrackerDTO.longitude ? constructionTrackerDTO.longitude : '75.7139'}" />

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="address-details"/></div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label><liferay-ui:message key="address"/><sup class="text-danger">*</sup></label>
						<textarea class="form-control" name="address" id="address" rows="3" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>${constructionTrackerDTO.address}</textarea>
					</div>
				</div>
				
				<div class="col-md-12">
			    <div class="form-group">
			        <label><liferay-ui:message key="GIS-Location" /><sup class="text-danger">*</sup></label><br/>
			        
			         <div class="input-group">
				        <input type="text" id="locationSearch" class="form-control" placeholder="Enter Area or City Name" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
				        <button type="button" class="btn btn-primary" onclick="searchLocation()" <c:if test="${mode eq 'view'|| mode eq 'edit'}">disabled</c:if>>Search</button>
				    </div>
				    <span class="text-danger d-block" id="locationNotFound"></span>
									    
			        <button type="button" class="btn btn-primary d-none" id="getGisLocation" onclick="getLocation()" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> >
			            <liferay-ui:message key="get-GIS-location" />
			        </button>
			    </div>
			    <!-- Map Container -->
                 <div id="map" class="d-none" style="height: 300px; border: 1px solid #ccc;z-index: 1;"></div>
			   </div>
				
				<!-- <div class="col-md-6">
				    <div class="form-group">
				        <label><liferay-ui:message key="GIS-Location" /><sup class="text-danger">*</sup></label>
				        <input type="text" class="form-control" id="GISLocation" name="GISLocation" readonly />
				    </div>
				</div> -->
				
			  <div class="col-md-6 d-none">
				    <div class="form-group">
				        <label><liferay-ui:message key="latitude" /><sup class="text-danger">*</sup></label>
				        <input type="text" class="form-control" name="latitude" id="latitude"
				            placeholder="<liferay-ui:message key='latitude' />"
				            value="${latitude}" readonly />
				    </div>
				</div>
				
				<div class="col-md-6 d-none">
				    <div class="form-group">
				        <label><liferay-ui:message key="longitude" /><sup class="text-danger">*</sup></label>
				        <input type="text" class="form-control" name="longitude" id="longitude"
				            placeholder="<liferay-ui:message key='longitude' />"
				            value="${longitude}" readonly />
				    </div>
				</div>
			
				<div class="col-md-6 mt-4">
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
		</div>
	</div>
</div>

<script>

var uploadedFilesGeoTagPhoto = [];
$(document).ready(function () {
	let geoTagPhotoJson = ${not empty constructionTrackerDTO.geoTagPhoto ? constructionTrackerDTO.geoTagPhoto : '[]'};
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
	
	/* Applying Jquey validation on keyup for address and officer details */
	 const form = $('#constructionTracker');
     form.validate({
         ignore: ':hidden',
         errorClass: 'text-danger',
         errorElement: 'div',
         rules: {
        	 address: { required: true, minlength: 10, maxlength: 250,containsLetters: true,
     	        noEdgeSpaces: true,
    	        singleSpaceOnly: true,
    	        validCharacters: true,
    	        noConsecutiveSpecials: true,
    	        onlyDotAtEnd: true, },
    	        officerName: {
    	            required: true,
    	            minlength: 3,
    	            maxlength: 75,
    	            validPersonName: true,
    	            noEdgeSpaces: true,
    	            singleSpaceOnly: true,
    	            noConsecutiveSpecials: true
    	        },
    	        contactNumber: {
    	            required: true,
    	            digits: true,
    	            minlength: 10,
    	            maxlength: 10,
    	            validContact: true
    	        },
    	        area: {
    	        	 required: true, number: true, min: 1, maxlength: 10,
    	        },
    	        executingAgency:{
    	        	 required: true,
    	       	        minlength: 3,
    	       	        maxlength: 75,
    	       	     alphanumericWithPeroidAndHyphen: true,
    		             noEdgeSpaces: true,
    		             singleSpaceOnly: true,
    		             noConsecutiveSpecials:true,
    	        },
    	        nameOfTheFirm:{
    	        	 required: true,
    	       	        minlength: 3,
	   	       	        maxlength: 75,
	   	       	  alphanumericWithPeroidAndHyphen: true,
	   		             noEdgeSpaces: true,
	   		             singleSpaceOnly: true,
	   		             noConsecutiveSpecials:true,
    	        },
         },
         messages: {
        	 address:{
        	 required: '<liferay-ui:message key="please-enter-address" />',
        	 minlength: "<liferay-ui:message key='please-enter-min-10-characters' />",
	         maxlength: "<liferay-ui:message key='please-enter-max-250-characters' />",
        	 },
        	 officerName: {
 	            required: '<liferay-ui:message key="please-enter-officer-name" />',
 	            minlength: '<liferay-ui:message key="please-enter-min-3-characters" />',
 	            maxlength: '<liferay-ui:message key="please-enter-max-75-characters" />'
 	        },
 	        contactNumber: {
 	            required: '<liferay-ui:message key="please-enter-contact-number" />',
 	            digits: '<liferay-ui:message key="contact-number-digits-only" />',
 	            minlength: '<liferay-ui:message key="contact-number-should-be-10-digits" />',
 	            maxlength: '<liferay-ui:message key="contact-number-should-be-10-digits" />'
 	        },
 	        area: {
 	        	required: '<liferay-ui:message key="please-enter-area" />',
 	            number: '<liferay-ui:message key="area-should-be-number" />',
 	            min: '<liferay-ui:message key="area-should-be-positive-or-greater-than-0" />',
 	            maxlength: '<liferay-ui:message key="area-should-not-exceed-10-digits" />',
 	        },
 	       executingAgency: {
 	        	required: '<liferay-ui:message key="please-enter-executing-agency" />',
 	        	  minlength: "<liferay-ui:message key='please-enter-min-3-characters' />",
     	          maxlength: "<liferay-ui:message key='please-enter-max-75-characters' />",
 	        },
 	       nameOfTheFirm: {
 	        	required: '<liferay-ui:message key="please-enter-name-of-the-firm" />',
 	        	  minlength: "<liferay-ui:message key='please-enter-min-3-characters' />",
     	          maxlength: "<liferay-ui:message key='please-enter-max-75-characters' />",
 	        },
         },
         errorPlacement: function (error, element) {
             if (element.attr("type") == "radio") {
                 error.insertAfter(element.parent().parent());
             } else if (element.attr("type") == "checkbox" && element.hasClass("form-check-input")) {
                 error.insertAfter(element.closest('.form-check'));
             } else {
                 error.insertAfter(element);
             }
         }
     });
     
});

function reset_address_details() {
    console.log("resetting address details ------- ");
  
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
    
    if(!isEditMode){
   	 resetAllFields();
    }
}


window.validate_address_details = function () {
    console.log("In validate_address_details ------ ");
    let isValid = true;

    const messages = {
    	address: '<liferay-ui:message key="please-enter-address" />',
    	longitude: '<liferay-ui:message key="please-enter-longitude" />',
    	latitude: '<liferay-ui:message key="please-enter-latitude" />',
    	/* GeoTagPhoto: '<liferay-ui:message key="please-upload-geo-tag-photo" />', */
    };
    
    $.each(messages, function (field, message) {
        	if(field === "address"){
        	 $form.find("#" + field).rules("add", {
        	        required: true,
        	        minlength: 10,
        	        maxlength: 250,
        	        containsLetters: true,
        	        noEdgeSpaces: true,
        	        singleSpaceOnly: true,
        	        validCharacters: true,
        	        noConsecutiveSpecials: true,
        	        onlyDotAtEnd: true,
        	        messages: {
        	            required: message,
        	            minlength: "<liferay-ui:message key='please-enter-min-10-characters' />",
        	            maxlength: "<liferay-ui:message key='please-enter-max-250-characters' />",
        	        }
        	    });
        }else {
            // Apply normal validation
            $form.find("#" + field).rules("add", {
                required: true,
                messages: { required: message }
            });
        }
    });

    $("#address-details")
        .find("input, select, textarea")
        .filter(":visible")
        .each(function () {
            if (!$form.validate().element(this)) {
                isValid = false;
            }
        });

    /* custom validation for file */
	 const activeFiles = uploadedFilesGeoTagPhoto.filter(f => !f.markedForDelete);
	 let isFileValid = true;
    const errorSpan = document.getElementById("geoTagPhotoError");
	if (activeFiles.length == 0) {
	    errorSpan.textContent = "<liferay-ui:message key='please-upload-geo-tag-photo' />";
	    errorSpan.style.display = "block";
	    isFileValid = false;
	}else {
        errorSpan.innerHTML = "";
        errorSpan.style.display = "none";
    }
    if(isFileValid){
    	console.log("Validations passed  ------------- ")
    }
    
    if(isValid && isFileValid){
    	document.getElementById("geoTagPhotoHiddenInput").value = JSON.stringify(uploadedFilesGeoTagPhoto.filter(f => !f.markedForDelete));
    	console.log("geoTagPhotoHiddenInput -- "+ $('#geoTagPhotoHiddenInput').val())
    }
    return isValid && isFileValid;
};


function handleMultipleFileUpload(fileInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    let uploadedFiles = getUploadedFileArray(inputId);

    const newFiles = Array.from(fileInput.files);
    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
    const totalFiles = activeFilesCount + newFiles.length;
    
    if (totalFiles > 4) {
        errorSpan.textContent = "<liferay-ui:message key='You-can-upload-a-maximum-of-4-files' />";
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
    
    clearInputFile(inputId);

    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);

    const hasActiveFiles = uploadedFiles.some(f => !f.markedForDelete);
    errorSpan.style.display = hasActiveFiles ? "none" : "block";
    errorSpan.textContent = hasActiveFiles ? "" : "<liferay-ui:message key='Please-upload-at-least-one-file' />";
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
        link.className = "text-truncate";
        //link.style.cssText = "flex-grow: 1; text-decoration: none; white-space: nowrap; overflow: hidden;";
        li.appendChild(link);
        let mode = '${mode}';
        if(mode!="view"){
	        const btn = document.createElement("button");
	        btn.type = "button";
	        btn.className = "btn btn-danger btn-sm";
	        btn.innerHTML = '<i class="bi bi-x-circle-fill"></i>';
	        btn.onclick = () => removeFile(index, previewContainer.id, previewList.id, errorSpan.id, hiddenInput.id, inputId);
	        li.appendChild(btn);
        }
        li.style.backgroundColor = "#fff";
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


// Map
let map;
let marker;
let latElement = document.getElementById('latitude');
let lonElement = document.getElementById('longitude');
/* let isViewMode = typeof mode !== "undefined" && (mode === "view");
let isEditMode = typeof mode !== "undefined" && (mode === "edit"); */

// Retrieve stored coordinates
let storedLat = parseFloat(latElement.value);
let storedLon = parseFloat(lonElement.value);
 
 
/*  if ((isViewMode||isEditMode) && !isNaN(storedLat) && !isNaN(storedLon)) {
    $("#map").removeClass("d-none");
    initializeMap(storedLat, storedLon, false);
} */
 
function getLocation() {
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
 
function initializeMap(lat, lon, isEditable) {
	console.log("Initialize map called ----------- ")
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
    setTimeout(() => map.invalidateSize(), 200);
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
            alert('Error finding location.');
        });
}
</script>
