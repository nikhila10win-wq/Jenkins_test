<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="funds"/></div>
		<div class="card-body">
			<div id="fundsContainer"></div>

			<c:if test="${mode ne 'view'}">
				<div class="buttonsDiv mt-2">
					<button type="button" class="btn btn-primary" id="addFundsRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if> > <i class="bi bi-plus-circle"></i></button>
					<button type="button" class="btn btn-danger" id="removeFundsRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if> > <i class="bi bi-dash-circle"></i></button>
				</div>
			</c:if>
		</div>
	</div>
</div>

<script>
	let allFundsFieldsValid = true;
	let fundsRowIndex = 0;
	let minFundsBlockLength =1;

	$(document).ready(function () {
		const form = $('#constructionTracker');

		form.validate({
			ignore: ':hidden',
			errorClass: 'text-danger',
			errorElement: 'div'
		});

		let fundsJSONArray = ${not empty constructionTrackerDTO.fundsJSON ? constructionTrackerDTO.fundsJSON : '[]'};
		if (fundsJSONArray && fundsJSONArray.length > 0) {
			fundsRowIndex = fundsJSONArray.length;
			minFundsBlockLength = Math.max(fundsJSONArray.length, 1);
			console.log("fundsRowIndex ------------ "+fundsRowIndex)
			populateExistingFunds(fundsJSONArray);
		} else {
			addFundsRow(); // fresh form - add default row
		}

		$('#addFundsRowBtn').on('click', function () {
			if (validateFundsRow()) {
				addFundsRow();
			}
		});

		 $('#removeFundsRowBtn').on('click', function () {
		        const blocks = $('.funds-row');
		        if (blocks.length > minFundsBlockLength) {
		            blocks.last().remove();
		        }
		    });
		 
	});
	
	 function reset_funds(){
			 if(!isEditMode){
				resetAllFields();
			 }else{
				 resetOnlyActiveFields();
			 }
		}
	 
	function validate_funds() {
		allFundsFieldsValid = validateFundsRow();
		return allFundsFieldsValid;
	}

	/* function populateExistingFunds(fundsJSONArray) {
		fundsJSONArray.forEach(function(item, index) {
			const row =
				'<div class="row funds-row mb-2">' +
					'<div class="col-md-6">' +
						'<div class="form-group">' +
							'<label><liferay-ui:message key="division"/> <sup class="text-danger">*</sup></label>' +
							'<select class="form-control fundsDivision" name="fundsDivision_' + index + '"' + (mode === 'view' || mode === 'edit' ? ' disabled' : '') + '>' +
								'<option value=""><liferay-ui:message key="select"/></option>' +
								'<c:forEach var="division" items="${divisions}">' +
									'<option value="${division.divisionId}" <c:if test="${constructionTrackerDTO.division == division.divisionId}">selected</c:if> >${division.divisionName_en}</option>' +
								'</c:forEach>' +
							'</select>' +
						'</div>' +
					'</div>' +

					'<div class="col-md-6">' +
						'<div class="form-group">' +
							'<label><liferay-ui:message key="district"/> <sup class="text-danger">*</sup></label>' +
							'<select class="form-control fundsDistrict" name="fundsDistrict_' + index + '"' + (mode === 'view' || mode === 'edit' ? ' disabled' : '') + '>' +
								'<option value=""><liferay-ui:message key="select"/></option>' +
								'<c:forEach var="district" items="${districts}">' +
									'<option value="${district.districtId}" <c:if test="${constructionTrackerDTO.district == district.districtId}">selected</c:if> >${district.districtName_en}</option>' +
								'</c:forEach>' +
							'</select>' +
						'</div>' +
					'</div>' +

					'<div class="col-md-6">' +
						'<div class="form-group">' +
							'<label><liferay-ui:message key="taluka"/> <sup class="text-danger">*</sup></label>' +
							'<select class="form-control fundsTaluka" name="fundsTaluka_' + index + '"' + (mode === 'view' || mode === 'edit' ? ' disabled' : '') + '>' +
								'<option value=""><liferay-ui:message key="select"/></option>' +
								'<c:forEach var="taluka" items="${talukas}">' +
									'<option value="${taluka.talukaId}" <c:if test="${constructionTrackerDTO.taluka == taluka.talukaId}">selected</c:if> >${taluka.talukaName_en}</option>' +
								'</c:forEach>' +
							'</select>' +
						'</div>' +
					'</div>' +
				'</div>';

			$('#fundsContainer').append(row);
		});
	} */
	
	function populateExistingFunds(fundsJSONArray) {
		let mode = '${mode}';
		const isReadOnly = (mode === 'view' || mode === 'edit') ? 'disabled' : '';

		fundsJSONArray.forEach(function(item, index) {
			const row =
				'<div class="row funds-row mb-2" data-index="' + index + '">' +
					'<div class="col-md-6">' +
						'<div class="form-group">' +
							'<label><liferay-ui:message key="division"/> <sup class="text-danger">*</sup></label>' +
							'<select class="form-control fundsDivision" name="fundsDivision_' + index + '" ' + isReadOnly + '>' +
								'<option value=""><liferay-ui:message key="select"/></option>' +
								'<c:forEach var="division" items="${divisions}">' +
									'<option value="${division.divisionId}">${division.divisionName_en}</option>' +
								'</c:forEach>' +
							'</select>' +
						'</div>' +
					'</div>' +

					'<div class="col-md-6">' +
						'<div class="form-group">' +
							'<label><liferay-ui:message key="district"/> <sup class="text-danger">*</sup></label>' +
							'<select class="form-control fundsDistrict" name="fundsDistrict_' + index + '" ' + isReadOnly + '>' +
								'<option value=""><liferay-ui:message key="select"/></option>' +
							'</select>' +
						'</div>' +
					'</div>' +

					'<div class="col-md-6">' +
						'<div class="form-group">' +
							'<label><liferay-ui:message key="taluka"/> <sup class="text-danger">*</sup></label>' +
							'<select class="form-control fundsTaluka" name="fundsTaluka_' + index + '" ' + isReadOnly + '>' +
								'<option value=""><liferay-ui:message key="select"/></option>' +
							'</select>' +
						'</div>' +
					'</div>' +
				'</div>';

			const $row = $($.parseHTML(row));
			$('#fundsContainer').append($row);

			// Set division value
			const $division = $row.find('[name="fundsDivision_' + index + '"]');
			$division.val(item.divisionId);

			// Fetch and populate districts
			if (item.divisionId) {
				$.ajax({
					url: '${getDistricts}',
					type: 'GET',
					data: { divisionId: item.divisionId },
					success: function (districts) {
						districts = typeof districts === 'string' ? JSON.parse(districts) : districts;
						const $district = $row.find('[name="fundsDistrict_' + index + '"]');
						$district.empty().append('<option value="">Select</option>');
						$.each(districts, function (i, dist) {
							const selected = dist.districtId == item.districtId ? 'selected' : '';
							$district.append('<option value="' + dist.districtId + '" ' + selected + '>' + dist.districtName + '</option>');
						});

						// Fetch and populate talukas if district is present
						if (item.districtId) {
							$.ajax({
								url: '${getTalukas}',
								type: 'GET',
								data: { districtId: item.districtId },
								success: function (talukas) {
									talukas = typeof talukas === 'string' ? JSON.parse(talukas) : talukas;
									const $taluka = $row.find('[name="fundsTaluka_' + index + '"]');
									$taluka.empty().append('<option value="">Select</option>');
									$.each(talukas, function (i, tal) {
										const selected = tal.talukaId == item.talukaId ? 'selected' : '';
										$taluka.append('<option value="' + tal.talukaId + '" ' + selected + '>' + tal.talukaName + '</option>');
									});
								}
							});
						}
					}
				});
			}

			if (mode !== 'view' && mode !== 'edit') {
				applyFundsValidationRules($row, index);
			}
		});
	}



	function addFundsRow() {
		const row =
			'<div class="row funds-row mb-2" data-index="' + fundsRowIndex + '">' +
				'<div class="col-md-6">' +
					'<div class="form-group">' +
						'<label><liferay-ui:message key="division"/> <sup class="text-danger">*</sup></label>' +
						'<select class="form-control fundsDivision" name="fundsDivision_' + fundsRowIndex + '">' +
							'<option value=""><liferay-ui:message key="select"/></option>' +
							'<c:forEach var="division" items="${divisions}">' +
								'<option value="${division.divisionId}">${division.divisionName_en}</option>' +
							'</c:forEach>' +
						'</select>' +
					'</div>' +
				'</div>' +

				'<div class="col-md-6">' +
					'<div class="form-group">' +
						'<label><liferay-ui:message key="district"/> <sup class="text-danger">*</sup></label>' +
						'<select class="form-control fundsDistrict" name="fundsDistrict_' + fundsRowIndex + '">' +
							'<option value=""><liferay-ui:message key="select"/></option>' +
							/* '<c:forEach var="district" items="${districts}">' +
								'<option value="${district.districtId}">${district.districtName_en}</option>' +
							'</c:forEach>' + */
						'</select>' +
					'</div>' +
				'</div>' +

				'<div class="col-md-6">' +
					'<div class="form-group">' +
						'<label><liferay-ui:message key="taluka"/> <sup class="text-danger">*</sup></label>' +
						'<select class="form-control fundsTaluka" name="fundsTaluka_' + fundsRowIndex + '">' +
							'<option value=""><liferay-ui:message key="select"/></option>' +
							/* '<c:forEach var="taluka" items="${talukas}">' +
								'<option value="${taluka.talukaId}">${taluka.talukaName_en}</option>' +
							'</c:forEach>' + */
						'</select>' +
					'</div>' +
				'</div>' +
			'</div>';

			const $row = $($.parseHTML(row)); 
			$('#fundsContainer').append($row);
			applyFundsValidationRules($row, fundsRowIndex);

		fundsRowIndex++;
	}

	function applyFundsValidationRules(block, fundsRowIndex) {
	    block.find('[name="fundsDivision_' + fundsRowIndex + '"]').rules('add', {
	        required: true,
	        messages: {
	            required: '<liferay-ui:message key="please-select-division" />'
	        }
	    });

	    block.find('[name="fundsDistrict_' + fundsRowIndex + '"]').rules('add', {
	        required: true,
	        messages: {
	            required: '<liferay-ui:message key="please-select-district" />'
	        }
	    });

	    block.find('[name="fundsTaluka_' + fundsRowIndex + '"]').rules('add', {
	        required: true,
	        messages: {
	            required: '<liferay-ui:message key="please-select-taluka" />'
	        }
	    });
	}

	function validateFundsRow() {
		let isValid = true;
		$('.fundsDivision, .fundsDistrict, .fundsTaluka').each(function () {
			if (!$(this).valid()) isValid = false;
		});
		return isValid;
	}
	
	
	$(document).on('change', '.fundsDivision', function () {
	    const $divisionSelect = $(this);
	    const $row = $divisionSelect.closest('.funds-row');
	    const index = $row.data('index');

	    const divisionId = $divisionSelect.val();
	    const $districtSelect = $row.find('.fundsDistrict');
	    const $talukaSelect = $row.find('.fundsTaluka');

	    $districtSelect.empty().append('<option value="">Select</option>');
	    $talukaSelect.empty().append('<option value="">Select</option>');

	    if (divisionId) {
	        $.ajax({
	            url: '${getDistricts}',
	            type: 'GET',
	            data: { divisionId: divisionId },
	            success: function (data) {
	                const districts = typeof data === 'string' ? JSON.parse(data) : data;
	                if (districts?.length) {
	                    $.each(districts, function (i, value) {
	                        $districtSelect.append('<option value="' + value.districtId + '">' + value.districtName + '</option>');
	                    });
	                } else {
	                    $districtSelect.append('<option value="">No districts available</option>');
	                }
	            }
	        });
	    }
	});

	$(document).on('change', '.fundsDistrict', function () {
	    const $districtSelect = $(this);
	    const $row = $districtSelect.closest('.funds-row');
	    const districtId = $districtSelect.val();
	    const $talukaSelect = $row.find('.fundsTaluka');

	    $talukaSelect.empty().append('<option value="">Select</option>');

	    if (districtId) {
	        $.ajax({
	            url: '${getTalukas}',
	            type: 'GET',
	            data: { districtId: districtId },
	            success: function (data) {
	                const talukas = typeof data === 'string' ? JSON.parse(data) : data;
	                if (talukas?.length) {
	                    $.each(talukas, function (i, value) {
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
