<%@page import="mhdsys.construction.tracker.web.constants.MhdsysConstructionTrackerWebPortletKeys"%>
<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
<div class="personal_details">
	<div class="card-header header-card"><liferay-ui:message key="name-of-sports-complex"/></div>
	<div class="card-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label><liferay-ui:message key="division"/> <sup class="text-danger">*</sup></label>
					<select class="form-control" name="division" id="division" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
						<option value=""><liferay-ui:message key="select"/></option>
						<c:forEach var="division" items="${divisions}">
						<option value="${division.divisionId}" <c:if test="${constructionTrackerDTO.division == division.divisionId}">selected</c:if> >${division.divisionName_en}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label><liferay-ui:message key="district"/> <sup class="text-danger">*</sup></label>
			<select class="form-control" name="district" id="district" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
			    <option value=""><liferay-ui:message key="select" /></option>
			</select>

			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label><liferay-ui:message key="taluka"/> <sup class="text-danger">*</sup></label>
				<select class="form-control" name="taluka" id="taluka" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
					<option value=""><liferay-ui:message key="select"/></option>
				</select>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label><liferay-ui:message key="status-of-complex"/> <sup class="text-danger">*</sup>
				<em class="bi bi-info-circle-fill" title="<liferay-ui:message key="status-of-complex-definition" />"></em>
						</label>
						<select class="form-control" name="statusOfComplex" id="statusOfComplex" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
						    <option value=""><liferay-ui:message key="select"/></option>
						    <option value="A" <c:if test="${constructionTrackerDTO.statusOfComplex eq 'A'}">selected</c:if>><liferay-ui:message key="A"/></option>
						    <option value="B" <c:if test="${constructionTrackerDTO.statusOfComplex eq 'B'}">selected</c:if>><liferay-ui:message key="B"/></option>
						    <option value="C" <c:if test="${constructionTrackerDTO.statusOfComplex eq 'C'}">selected</c:if>><liferay-ui:message key="C"/></option>
						    <option value="D" <c:if test="${constructionTrackerDTO.statusOfComplex eq 'D'}">selected</c:if>><liferay-ui:message key="D"/></option>
						</select>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>

function reset_name_of_sports_complex(){
	if(!isEditMode){
		resetAllFields();
	}
}

window.validate_name_of_sports_complex = function () {
    console.log("In validate_name_of_sports_complex ------ ");
    let isValid = true;

    const messages = {
        division: '<liferay-ui:message key="please-select-division" />',
        district: '<liferay-ui:message key="please-select-district" />',
        taluka: '<liferay-ui:message key="please-select-taluka" />',
        statusOfComplex: '<liferay-ui:message key="please-select-status-of-complex" />'
    };

    $.each(messages, function (field, message) {
        $form.find("#" + field).rules("add", {
            required: true,
            messages: { required: message }
        });
    });

    $("#name-of-sports-complex")
        .find("input, select, textarea")
        .filter(":visible")
        .each(function () {
            if (!$form.validate().element(this)) {
                isValid = false;
            }
        });

     if(isValid){
    	 callGeoLocation();
    } 
    return isValid;
};

function callGeoLocation(){
	 var latitudeStr = "${constructionTrackerDTO.latitude}";
	 var longitudeStr = "${constructionTrackerDTO.longitude}";

	 var latitude = parseFloat(latitudeStr);
	 var longitude = parseFloat(longitudeStr);

	 // Validate coordinates first
	 var isValidCoordinates = !isNaN(latitude) && !isNaN(longitude);

	 // Fallback values
	 var defaultLat = 19.7515;
	 var defaultLon = 75.7139;

	 // Use valid coordinates if available
	 var lat = isValidCoordinates ? latitude : defaultLat;
	 var lon = isValidCoordinates ? longitude : defaultLon;

	 // Determine mode
	 var isCreateMode = mode === "" || mode === null || mode === undefined || mode ==="draft" ;
	 var isEditOrView = mode === "edit" || mode === "view";

	 console.log("lat:", latitudeStr);
	 console.log("lon:", longitudeStr);

	 // Show map and initialize
	 $("#map").removeClass("d-none");
	 initializeMap(lat, lon, isCreateMode ? true : false); 
}
$(document).ready(function () {
    const selectedDivision = '${constructionTrackerDTO.division}';
    const selectedDistrict = '${constructionTrackerDTO.district}';
    const selectedTaluka = '${constructionTrackerDTO.taluka}';
    
    if (selectedDivision) {
        $('#division').val(selectedDivision);

        // Fetch and populate districts
        $.ajax({
            url: '${getDistricts}',
            type: 'GET',
            data: { divisionId: selectedDivision },
            success: function (districtData) {
                const $district = $('#district');
                $district.empty().append('<option value="">Select</option>');

                districtData = typeof districtData === 'string' ? JSON.parse(districtData) : districtData;

                $.each(districtData, function (i, d) {
                    const selected = d.districtId == selectedDistrict ? 'selected' : '';
                    $district.append('<option value="' + d.districtId + '" ' + selected + '>' + d.districtName + '</option>');
                });

                if (selectedDistrict) {
                    // Fetch and populate talukas
                    $.ajax({
                        url: '${getTalukas}',
                        type: 'GET',
                        data: { districtId: selectedDistrict },
                        success: function (talukaData) {
                            const $taluka = $('#taluka');
                            $taluka.empty().append('<option value="">Select</option>');

                            talukaData = typeof talukaData === 'string' ? JSON.parse(talukaData) : talukaData;

                            $.each(talukaData, function (i, t) {
                                const selected = t.talukaId == selectedTaluka ? 'selected' : '';
                                $taluka.append('<option value="' + t.talukaId + '" ' + selected + '>' + t.talukaName + '</option>');
                            });
                        }
                    });
                }
            }
        });
    }
    
});


$('select[name="division"]').on('change', function () {
    const divisionId = $(this).val();
    $("#district").val('');
    $("#taluka").val('');
    if (divisionId) {
        $.ajax({
            url: '${getDistricts}',
            type: 'GET',
            data: {
                divisionId: divisionId
            },
            success: function (data) {
                const $districtSelect = $("#district");
                $districtSelect.empty();
				console.log("Data ::: "+data);
                let districts = typeof data === "string" ? JSON.parse(data) : data;

                if (districts && districts.length > 0) {
                    $districtSelect.append('<option value="">Select</option>');

                    $.each(districts, function (index, value) {
                    	$districtSelect.append('<option value="' + value.districtId + '">' + value.districtName + '</option>');
                    });
                } else {
                    $districtSelect.append('<option value="">No districts available</option>');
                }
            }
            
        });
    }
}); 
 

$('select[name="district"]').on('change', function () {
    const districtId = $(this).val();
    $("#taluka").val('');
    if (districtId) {
        $.ajax({
            url: '${getTalukas}',
            type: 'GET',
            data: {
                districtId: districtId
            },
            
            success: function (data) {
                const $talukaSelect = $("#taluka");
                $talukaSelect.empty();

                let talukas = typeof data === "string" ? JSON.parse(data) : data;

                if (talukas && talukas.length > 0) {
                    $talukaSelect.append('<option value="">Select</option>');

                    $.each(talukas, function (index, value) {
                    	$talukaSelect.append('<option value="' + value.talukaId + '">' + value.talukaName + '</option>');
                    });
                } else {
                    $talukaSelect.append('<option value="">No talukas available</option>');
                }
            }
            
        });
    }
});

</script>




