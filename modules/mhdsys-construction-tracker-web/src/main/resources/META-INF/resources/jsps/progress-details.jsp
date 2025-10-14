<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card">
			<liferay-ui:message key="progress-details" />
		</div>
		<div class="card-body">
				<div id="progressContainer"></div>
			<c:if test="${mode ne 'view'}">
				<div class="buttonsDiv mt-2">
					<button type="button" class="btn btn-primary" id="addProgressRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if>><i class="bi bi-plus-circle"></i></button>
					<button type="button" class="btn btn-danger" id="removeProgressRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if> ><i class="bi bi-dash-circle"></i></button>
				</div>
			</c:if>
		</div>
	</div>
</div>

<script>

let allProgressFieldsValid = true;
let progressIndex = 0;
let minProgressBlockLength = 1;

function reset_progress_details() {
	
	if(!isEditMode){
		resetAllFields();
		 // Reset all progress blocks
	    $('.progress-block').each(function () {
	        const $block = $(this);
	        const index = $block.data('index');
	        // Clear file inputs
	      /*   $block.find('#addGanttChart_' + index).val('');
	        $block.find('#GISPhoto_' + index).val(''); */
	        clearInputFile('addGanttChart_' + index);
	        clearInputFile('GISPhoto_' + index);

	        // Clear preview containers
	        $block.find('#addGanttChartPreviewContainer_' + index).hide();
	        $block.find('#GISPhotoPreviewContainer_' + index).hide();

	        // Clear preview links
	        $block.find('#addGanttChartPreviewLink_' + index).attr('href', '').text('');
	        $block.find('#GISPhotoPreviewLink_' + index).attr('href', '').text('');

	        // Clear hidden inputs for prepopulated file IDs
	        $block.find('#addGanttChartHiddenInput_' + index).val('');
	        $block.find('#GISPhotoHiddenInput_' + index).val('');

	        // Clear any error messages
	        $block.find('#addGanttChartError_' + index).text('').hide();
	        $block.find('#GISPhotoError_' + index).text('').hide();
	    });
	 }else{
		 resetOnlyActiveFields();

	        const $activeTab = $('.tab-pane.active');
	        $activeTab.find('.progress-block').each(function () {
	            const $block = $(this);
	            const index = $block.data('index');

	            // Check if file input is NOT disabled/readonly before clearing
	            const $ganttInput = $block.find('#addGanttChart_' + index);
	            if (!$ganttInput.prop('disabled') && !$ganttInput.prop('readonly')) {
	                clearInputFile('addGanttChart_' + index);
	                $block.find('#addGanttChartPreviewContainer_' + index).hide();
	                $block.find('#addGanttChartPreviewLink_' + index).attr('href', '').text('');
	                $block.find('#addGanttChartHiddenInput_' + index).val('');
	                $block.find('#addGanttChartError_' + index).text('').hide();
	            }

	            const $gisInput = $block.find('#GISPhoto_' + index);
	            if (!$gisInput.prop('disabled') && !$gisInput.prop('readonly')) {
	                clearInputFile('GISPhoto_' + index);
	                $block.find('#GISPhotoPreviewContainer_' + index).hide();
	                $block.find('#GISPhotoPreviewLink_' + index).attr('href', '').text('');
	                $block.find('#GISPhotoHiddenInput_' + index).val('');
	                $block.find('#GISPhotoError_' + index).text('').hide();
	            }
	        });

	        $activeTab.find('.custom-preview').hide().text('');
	    }
   
}


function validate_progress_details() {
	allProgressFieldsValid = validateProgressRow();
	console.log("allProgressFieldsValid --------------------------" +allProgressFieldsValid );
	validate_other();
	return allProgressFieldsValid;
}

const uploadedSingleFiles = {};

$(document).ready(function () {
	let form = $('#constructionTracker');

	form.validate({
		ignore: ':hidden',
		errorClass: 'text-danger',
		errorElement: 'div'
	});

	let progressDetailsJSON = ${not empty constructionTrackerDTO.progressDetailsJSON ? constructionTrackerDTO.progressDetailsJSON : '[]'};
	if (progressDetailsJSON && progressDetailsJSON.length > 0) {
		progressIndex = progressDetailsJSON.length;
		minProgressBlockLength = Math.max(progressDetailsJSON.length, 1);
		console.log("progressIndex ------------ "+progressIndex);
		populateExistingProgressDetails(progressDetailsJSON);
	} else {
		addProgressBlock();
	}
	
	$('#addProgressRowBtn').on('click', function () {
		let isValid = true;
		console.log("addProgressRowBtn clicked -------- ");
		/* $('.progress-block').each(function () {
			$(this).find('input, select').each(function () {
				if (!$(this).valid()) {
					isValid = false;
				}
			});
		}); 
		if (isValid) {
			addProgressBlock();
		}*/
		
		if (validateProgressRow()) {
			addProgressBlock();
		}
		
		
	});

	 $('#removeProgressRowBtn').on('click', function () {
		const blocks = $('.progress-block');
		if (blocks.length > minProgressBlockLength) {
			blocks.last().remove();
			progressIndex = progressIndex-1;
		}
	}); 
	
	
});

function addProgressBlock() {
	const index = progressIndex;
	const block = $(
		'<div class="row progress-block mb-3 border p-3 rounded" data-index="' + index + '">' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Work In Hand <sup class="text-danger">*</sup></label>' +
					/* '<input type="number" step="0.01" class="form-control workInHand" name="workInHand_' + index + '" id="workInHand_' + index + '">' + */
					'<input type="text" class="form-control workInHand" name="workInHand_' + index + '" id="workInHand_' + index + '">' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Date of Tender <sup class="text-danger">*</sup></label>' +
					'<input type="date" class="form-control dateOfTender" name="dateOfTender_' + index + '" id="dateOfTender_' + index + '">' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Date of Work Order <sup class="text-danger">*</sup></label>' +
					'<input type="date" class="form-control dateOfWorkOrder" name="dateOfWorkOrder_' + index + '" id="dateOfWorkOrder_' + index + '">' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Expected Date of Completion <sup class="text-danger">*</sup></label>' +
					'<input type="date" class="form-control expectedDateOfCompletion" name="expectedDateOfCompletion_' + index + '" id="expectedDateOfCompletion_' + index + '">' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Add Gantt Chart <sup class="text-danger">*</sup>' +
						'<em class="bi bi-info-circle-fill" title="Allowed only JPG, JPEG, PNG and PDF files under 2MB."></em>' +
					'</label>' +
					'<div class="custom-file">' +
						'<input type="file" class="custom-file-input" id="addGanttChart_' + index + '" name="addGanttChart_' + index + '" ' +
						'onchange="handleSingleImageUpload(this, \'addGanttChart_' + index + '\', \'addGanttChartPreviewContainer_' + index + '\', \'addGanttChartPreviewLink_' + index + '\', \'addGanttChartDeleteButton_' + index + '\', \'addGanttChartError_' + index + '\', \'addGanttChartHiddenInput_' + index + '\')">' +
						'<label class="custom-file-label" for="addGanttChart_' + index + '">Choose file</label>' +
					'</div>' +
					'<span id="addGanttChartError_' + index + '" class="text-danger mt-2"></span>' +
					'<input type="hidden" id="addGanttChartHiddenInput_' + index + '" name="addGanttChartHiddenInput_' + index + '" />' +
					'<div class="mt-3" id="addGanttChartPreviewContainer_' + index + '" style="display:none;">' +
						'<div class="d-flex"> <a href="#" target="_blank" id="addGanttChartPreviewLink_' + index + '" class="text-truncate"></a>' +
						'<button type="button" class="btn btn-sm btn-danger" id="addGanttChartDeleteButton_' + index + '"' +
						'onclick="removeSingleImageFile(this, \'addGanttChart_' + index + '\', \'addGanttChartPreviewContainer_' + index + '\', \'addGanttChartPreviewLink_' + index + '\', \'addGanttChartHiddenInput_' + index + '\')">' +
						'<i class="bi bi-x-circle-fill"></i>' +
						'</button> </div>' +
					'</div>' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>GIS Photo <sup class="text-danger">*</sup>' +
						'<em class="bi bi-info-circle-fill" title="Allowed only JPG, JPEG, PNG and PDF files under 2MB."></em>' +
					'</label>' +
					'<div class="custom-file">' +
						'<input type="file" class="custom-file-input" id="GISPhoto_' + index + '" name="GISPhoto_' + index + '" ' +
						'onchange="handleSingleImageUpload(this, \'GISPhoto_' + index + '\', \'GISPhotoPreviewContainer_' + index + '\', \'GISPhotoPreviewLink_' + index + '\', \'GISPhotoDeleteButton_' + index + '\', \'GISPhotoError_' + index + '\', \'GISPhotoHiddenInput_' + index + '\')">' +
						'<label class="custom-file-label" for="GISPhoto_' + index + '">Choose file</label>' +
					'</div>' +
					'<span id="GISPhotoError_' + index + '" class="text-danger mt-2"></span>' +
					'<input type="hidden" id="GISPhotoHiddenInput_' + index + '" name="GISPhotoHiddenInput_' + index + '" />' +
					'<div class="mt-3" id="GISPhotoPreviewContainer_' + index + '" style="display:none;">' +
						'<div class="d-flex"><a href="#" target="_blank" id="GISPhotoPreviewLink_' + index + '" class="text-truncate"></a>' +
						'<button type="button" class="btn btn-sm btn-danger" id="GISPhotoDeleteButton_' + index + '"' +
						'onclick="removeSingleImageFile(this, \'GISPhoto_' + index + '\', \'GISPhotoPreviewContainer_' + index + '\', \'GISPhotoPreviewLink_' + index + '\', \'GISPhotoHiddenInput_' + index + '\')">' +
						'<i class="bi bi-x-circle-fill"></i>' +
						'</button></div>' +
					'</div>' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Progress Work % <sup class="text-danger">*</sup></label>' +
					'<input type="text" class="form-control progressWorkPercentage" name="progressWorkPercentage_' + index + '" id="progressWorkPercentage_' + index + '">' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Expenditure <sup class="text-danger">*</sup></label>' +
					'<input type="text" class="form-control progressExpenditure" name="progressExpenditure_' + index + '" id="progressExpenditure_' + index + '">' +
				'</div>' +
			'</div>' +
		'</div>'
	);

	$('#progressContainer').append(block);
	applyProgressValidationRules(block,index);

	progressIndex++;
}



function validateProgressRow(){
	let isValid = true;
	$('.progress-block').each(function () {
		$(this).find('input, select').each(function () {
			if (!$(this).valid()) {
				isValid = false;
			}
		});
	});
	console.log(" Checking custom file validations ----------- progressIndex : ", progressIndex);
	  var msg_uploadGantt = "<liferay-ui:message key='please-upload-gantt-chart' />";
	  var msg_uploadGIS = "<liferay-ui:message key='please-upload-gis-photo' />";
	/* for (let i = 0; i < progressIndex; i++) {
	    const index = i;
	    console.log("index:: ", index)
	    const addGantt = document.getElementById("addGanttChart_" + index);
	    const ganttErr = document.getElementById("addGanttChartError_" + index);
	    const gisPhoto = document.getElementById("GISPhoto_" + index);
	    const gisErr = document.getElementById("GISPhotoError_" + index);

	    if (ganttErr) ganttErr.textContent = "", ganttErr.style.display = "none";
	    if (gisErr) gisErr.textContent = "", gisErr.style.display = "none";

	    if (!addGantt?.files?.length) {
	        if (ganttErr) ganttErr.textContent = msg_uploadGantt, ganttErr.style.display = "block";
	        isValid = false;
	    }
	    if (!gisPhoto?.files?.length) {
	        if (gisErr) gisErr.textContent = msg_uploadGIS, gisErr.style.display = "block";
	        isValid = false;
	    }
	} */
	
	for (let i = 0; i < progressIndex; i++) {
	    const index = i;

	    const ganttErr = document.getElementById("addGanttChartError_" + index);
	    const gisErr = document.getElementById("GISPhotoError_" + index);

	    if (ganttErr) ganttErr.textContent = "", ganttErr.style.display = "none";
	    if (gisErr) gisErr.textContent = "", gisErr.style.display = "none";

	    if (!hasFileOrHidden("addGanttChart_" + index, "addGanttChartHiddenInput_" + index)) {
	        if (ganttErr) ganttErr.textContent = msg_uploadGantt, ganttErr.style.display = "block";
	        isValid = false;
	    }

	    if (!hasFileOrHidden("GISPhoto_" + index, "GISPhotoHiddenInput_" + index)) {
	        if (gisErr) gisErr.textContent = msg_uploadGIS, gisErr.style.display = "block";
	        isValid = false;
	    }
	}
	
	console.log("isValid:: "+isValid)
	return isValid;
}

function hasFileOrHidden(fileInputId, hiddenInputId) {
    const file = document.getElementById(fileInputId);
    const hidden = document.getElementById(hiddenInputId);
    return file?.files?.length || hidden?.value;
}


/* PRE POPULATION */

function populateExistingProgressDetails(progressDetailsJSON){
	let mode= '${mode}';
	const isReadOnly = (mode === 'view' || mode === 'edit') ? 'readonly' : '';
	const isDisabled = (mode === 'view' || mode === 'edit') ? 'disabled' : '';

	progressDetailsJSON.forEach(function(item, index) {

		const ganttUrl = item.gisPhotoUrl || '#';
		const ganttFileId = item.addGanttChart || '';
		const gisUrl = item.ganttChartUrl || '#';
		const gisFileId = item.GISPhoto || '';

		
		const existingRow = 
			'<div class="row progress-block mb-3 border p-3 rounded" data-index="' + index + '">' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Work In Hand <sup class="text-danger">*</sup></label>' +
					/* '<input type="number" step="0.01" class="form-control workInHand" name="workInHand_' + index + '" id="workInHand_' + index + '" value="' + item.workInHand + '" '+isReadOnly+'>' + */
					'<input type="text" class="form-control workInHand" name="workInHand_' + index + '" id="workInHand_' + index + '" value="' + item.workInHand + '" '+isReadOnly+'>' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Date of Tender <sup class="text-danger">*</sup></label>' +
					'<input type="date" class="form-control" name="dateOfTender_' + index + '" id="dateOfTender_' + index + '" value="' + formatDateForInput(item.dateOfTender) + '" '+isReadOnly+'>' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Date of Work Order <sup class="text-danger">*</sup></label>' +
					'<input type="date" class="form-control" name="dateOfWorkOrder_' + index + '" id="dateOfWorkOrder_' + index + '" value="' + formatDateForInput(item.dateOfWorkOrder) + '" '+isReadOnly+'>' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Expected Date of Completion <sup class="text-danger">*</sup></label>' +
					'<input type="date" class="form-control" name="expectedDateOfCompletion_' + index + '" id="expectedDateOfCompletion_' + index + '" value="' + formatDateForInput(item.expectedDateOfCompletion) + '" '+isReadOnly+'>' +
				'</div>' +
			'</div>' +

			/*  '<div class="col-md-6">' +
		    '<div class="form-group">' +
		        '<label>Add Gantt Chart <sup class="text-danger">*</sup>' +
		            '<em class="bi bi-info-circle-fill" title="Allowed only JPG, JPEG, PNG files under 2MB."></em>' +
		        '</label>' +
		        '<div>' +
		            '<a href="' + item.ganttChartUrl + '" target="_blank" class="text-truncate">View Gantt Chart</a>' +
		        '</div>' +
		        '<input type="hidden" name="addGanttChartHiddenInput_' + index + '" id="addGanttChartHiddenInput_' + index + '" value="' + item.addGanttChart + '" />' +
		    '</div>' +
			'</div>' +


			'<div class="col-md-6">' +
		    '<div class="form-group">' +
		        '<label>GIS Photo <sup class="text-danger">*</sup>' +
		            '<em class="bi bi-info-circle-fill" title="Allowed only JPG, JPEG, PNG files under 2MB."></em>' +
		        '</label>' +
		        '<div>' +
		            '<a href="' + item.gisPhotoUrl + '" target="_blank" class="text-truncate">View GIS Photo</a>' +
		        '</div>' +
		        '<input type="hidden" name="GISPhotoHiddenInput_' + index + '" id="GISPhotoHiddenInput_' + index + '" value="' + item.GISPhoto + '" />' +
		    '</div>' +
			'</div>' +  */
			
			 '<div class="col-md-6">' +
			'<div class="form-group">' +
				'<label>Add Gantt Chart <sup class="text-danger">*</sup>' +
					'<em class="bi bi-info-circle-fill" title="Allowed only JPG, JPEG, PNG and PDF files under 2MB."></em>' +
				'</label>' +
				'<div class="custom-file">' +
					'<input type="file" class="custom-file-input" id="addGanttChart_' + index + '" name="addGanttChart_' + index + '" ' + 
					'onchange="handleSingleImageUpload(this, \'addGanttChart_' + index + '\', \'addGanttChartPreviewContainer_' + index + '\', \'addGanttChartPreviewLink_' + index + '\', \'addGanttChartDeleteButton_' + index + '\', \'addGanttChartError_' + index + '\', \'addGanttChartHiddenInput_' + index + '\') " '+isDisabled+'>' +
					'<label class="custom-file-label" for="addGanttChart_' + index + '">Choose file</label>' +
				'</div>' +
				'<span id="addGanttChartError_' + index + '" class="text-danger mt-2"></span>' +
				'<div class="mt-3" id="addGanttChartPreviewContainer_' + index + '" style="">' +
					'<div class="d-flex"> <a href="' + item.ganttChartUrl + '" target="_blank" id="addGanttChartPreviewLink_' + index + '" class="text-truncate">View document</a>' +
					'<button type="button" class="btn btn-sm btn-danger" style="' + (isReadOnly ? 'display:none;' : '') + '" id="addGanttChartDeleteButton_' + index + '"' +
					'onclick="removeSingleImageFile(this, \'addGanttChart_' + index + '\', \'addGanttChartPreviewContainer_' + index + '\', \'addGanttChartPreviewLink_' + index + '\', \'addGanttChartHiddenInput_' + index + '\')">' +
					'<i class="bi bi-x-circle-fill"></i>' +
					'</button> </div>' +
				'</div>' +
					 '<input type="hidden" name="addGanttChartHiddenInput_' + index + '" id="addGanttChartHiddenInput_' + index + '" value="' + item.addGanttChart + '" '+isReadOnly+' />' +
			'</div>' +
		'</div>' +

		'<div class="col-md-6">' +
			'<div class="form-group">' +
				'<label>GIS Photo <sup class="text-danger">*</sup>' +
					'<em class="bi bi-info-circle-fill" title="Allowed only JPG, JPEG, PNG and PDF files under 2MB."></em>' +
				'</label>' +
				'<div class="custom-file">' +
					'<input type="file" class="custom-file-input" id="GISPhoto_' + index + '" name="GISPhoto_' + index + '" ' +
					'onchange="handleSingleImageUpload(this, \'GISPhoto_' + index + '\', \'GISPhotoPreviewContainer_' + index + '\', \'GISPhotoPreviewLink_' + index + '\', \'GISPhotoDeleteButton_' + index + '\', \'GISPhotoError_' + index + '\', \'GISPhotoHiddenInput_' + index + '\')  " '+isDisabled+'>' +
					'<label class="custom-file-label" for="GISPhoto_' + index + '">Choose file</label>' +
				'</div>' +
				'<span id="GISPhotoError_' + index + '" class="text-danger mt-2"></span>' +
				'<div class="mt-3" id="GISPhotoPreviewContainer_' + index + '" style="">' +
					'<div class="d-flex"><a href="' + item.gisPhotoUrl + '" target="_blank" id="GISPhotoPreviewLink_' + index + '" class="text-truncate">View document</a>' +
					'<button type="button" class="btn btn-sm btn-danger" style="' + (isReadOnly ? 'display:none;' : '') + '" id="GISPhotoDeleteButton_' + index + '"' +
					'onclick="removeSingleImageFile(this, \'GISPhoto_' + index + '\', \'GISPhotoPreviewContainer_' + index + '\', \'GISPhotoPreviewLink_' + index + '\', \'GISPhotoHiddenInput_' + index + '\')">' +
					'<i class="bi bi-x-circle-fill"></i>' +
					'</button></div>' +
				'</div>' +
				'<input type="hidden" name="GISPhotoHiddenInput_' + index + '" id="GISPhotoHiddenInput_' + index + '" value="' + item.GISPhoto + '" '+isReadOnly+' />' +
			'</div>' +
		'</div>' + 


			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Progress Work % <sup class="text-danger">*</sup></label>' +
					'<input type="text" class="form-control progressWorkPercentage" name="progressWorkPercentage_' + index + '" id="progressWorkPercentage_' + index + '" value="' + item.progressWorkPercentage + '" '+isReadOnly+'>' +
				'</div>' +
			'</div>' +

			'<div class="col-md-6">' +
				'<div class="form-group">' +
					'<label>Expenditure <sup class="text-danger">*</sup></label>' +
					'<input type="text" class="form-control" name="progressExpenditure_' + index + '" id="progressExpenditure_' + index + '" value="' + item.progressExpenditure + '" '+isReadOnly+'>' +
				'</div>' +
			'</div>' +
		'</div>'
		
		  const $readOnlyRow = $($.parseHTML(existingRow)); // Wrap the string as jQuery object
			$('#progressContainer').append($readOnlyRow);
			if (mode !== 'view' && mode !== 'edit') {
				applyProgressValidationRules($readOnlyRow, index);
			}
				
	});
}

function applyProgressValidationRules(block,index){
	
	 $.validator.addMethod("pastOrToday", function(value, element) {
		    if (this.optional(element)) return true;
		    const inputDate = new Date(value);
		    const today = new Date();
		    inputDate.setHours(0, 0, 0, 0);
		    today.setHours(0, 0, 0, 0);
		    return inputDate <= today;
		}, "<liferay-ui:message key='future-date-not-allowed' />");
	 $.validator.addMethod("futureOnly", function(value, element) {
		    if (this.optional(element)) return true;
		    const inputDate = new Date(value);
		    const today = new Date();
		    inputDate.setHours(0, 0, 0, 0);
		    today.setHours(0, 0, 0, 0);
		    return inputDate > today;
		}, "<liferay-ui:message key='only-future-date-allowed' />");

	 $.validator.addMethod("validProgressPercentage", function(value, element) {
		    // Allow number or number with a single % at the end
		    return /^[0-9]+(\.[0-9]+)?%?$/.test(value);
		}, "<liferay-ui:message key='please-enter-valid-progress-percentage' />");
	 
	 $.validator.addMethod("minStrict", function(value, element) {
		    const numeric = parseFloat(value.replace('%', ''));
		    return !isNaN(numeric) && numeric >= 1;
		}, "<liferay-ui:message key='amount-must-be-positive-or-greater-than-0' />");

		$.validator.addMethod("maxStrict", function(value, element) {
		    const numeric = parseFloat(value.replace('%', ''));
		    return !isNaN(numeric) && numeric <= 100;
		}, "<liferay-ui:message key='progress-percentage-max' />");

		$.validator.addMethod("alphanumericWithPeroidAndHyphen", function(value, element) {
	 		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
	 		   return this.optional(element) || /^[A-Za-z0-9.\- ]+$/.test(value);
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
		
	 
	/* block.find('[name="workInHand_' + index + '"]').rules('add', {
		required: true , min: 1, maxlength: 10,
		messages: { 
				required: '<liferay-ui:message key="please-enter-work-in-hand" />',
				min: '<liferay-ui:message key="amount-must-be-positive-or-greater-than-0" />',
				maxlength: '<liferay-ui:message key="maximum-10-digits-should-allow" />',
			}
	}); */
	
	block.find('[name="workInHand_' + index + '"]').rules('add', {
		  required: true,
		    minlength: 3,
		    maxlength: 75,
		    alphanumericWithPeroidAndHyphen: true,
		    noEdgeSpaces: true,
		    singleSpaceOnly: true,
		    noConsecutiveSpecials: true,
		    messages: {
		        required: '<liferay-ui:message key="please-enter-work-in-hand" />',
		        minlength: '<liferay-ui:message key="please-enter-min-3-characters" />',
		        maxlength: '<liferay-ui:message key="please-enter-max-75-characters" />'
		    }
	});

	block.find('[name="dateOfTender_' + index + '"]').rules('add', {
		required: true,pastOrToday:true,
		messages: { required: '<liferay-ui:message key="please-select-date-of-tender" />' }
	});

	block.find('[name="dateOfWorkOrder_' + index + '"]').rules('add', {
		required: true,pastOrToday:true,
		messages: { required: '<liferay-ui:message key="please-select-date-of-work-order" />' }
	});

	block.find('[name="expectedDateOfCompletion_' + index + '"]').rules('add', {
		required: true,futureOnly:true,
		messages: { required: '<liferay-ui:message key="please-select-expected-date-of-completion" />' }
	});

	block.find('[name="progressWorkPercentage_' + index + '"]').rules('add', {
		required: true,
		validProgressPercentage:true,
		  minStrict: true,
		    maxStrict: true,
		messages: {
			required: '<liferay-ui:message key="please-enter-progress-percentage" />',
		}
	});

	block.find('[name="progressExpenditure_' + index + '"]').rules('add', {
		required: true,
		number: true,
		min: 1,
	    maxlength: 10,
		messages: {
			required: '<liferay-ui:message key="please-enter-expenditure" />',
			number: '<liferay-ui:message key="expenditure-must-be-number" />',
			min: '<liferay-ui:message key="amount-must-be-positive-or-greater-than-0" />',
			maxlength: '<liferay-ui:message key="maximum-10-digits-should-allow" />',
		}
	});
	
		
	/*   block.find('[name="addGanttChart_' + index + '"]').rules('add', {
		required: true,
		accept: 'image/jpeg, image/jpg, image/png',
		messages: {
			required: '<liferay-ui:message key="please-upload-gantt-chart" />',
			accept: '<liferay-ui:message key="only-jpg-jpeg-png-allowed" />'
		}
	});

	block.find('[name="GISPhoto_' + index + '"]').rules('add', {
		required: true,
		accept: 'image/jpeg, image/jpg, image/png',
		messages: {
			required: '<liferay-ui:message key="please-upload-gis-photo" />',
			accept: '<liferay-ui:message key="only-jpg-jpeg-png-allowed" />'
		}
	});   */
	
 	/* const addGanttChart = document.getElementById("addGanttChart_" + index);
	const addGanttChartError = document.getElementById("addGanttChartError_" + index);
	const GISPhoto = document.getElementById("GISPhoto_" + index);
	const GISPhotoError = document.getElementById("GISPhotoError_" + index);

	[addGanttChartError, GISPhotoError].forEach(e => { e.textContent = ""; e.style.display = "none"; });

	if (!addGanttChart?.files?.length) {
	    addGanttChartError.textContent = '<liferay-ui:message key="please-upload-gantt-chart" />';
	    addGanttChartError.style.display = "block";
	    isValid = false;
	}
	if (!GISPhoto?.files?.length) {
	    GISPhotoError.textContent = '<liferay-ui:message key="please-upload-gis-photo" />';
	    GISPhotoError.style.display = "block";
	    isValid = false;
	}  */
	
}
function formatDateForInput(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toISOString().split('T')[0];
}

</script>
