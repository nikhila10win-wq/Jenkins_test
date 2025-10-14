<%@ include file="/init.jsp"%>

    <div class="card card-background p-0">
        <div class="personal_details">
        <input type="hidden" id="totalGovtReceivedAmount" name="totalGovtReceivedAmount" />
		<input type="hidden" id="totalGovtExpenditureAmount" name="totalGovtExpenditureAmount" />
		<input type="hidden" id="totalGovtOverallAmount" name="totalGovtOverallAmount" />
        
            <div class="card-header header-card">
                <liferay-ui:message key="govt-grants"/>
            </div>
            <div class="card-body">
                <div id="govtGrantsContainer"></div>

			<c:if test="${mode ne 'view'}">
                <div class="buttonsDiv mt-2">
                    <button type="button" class="btn btn-primary" id="addGovtRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if> >
                        <i class="bi bi-plus-circle"></i>
                    </button>
                    <button type="button" class="btn btn-danger" id="removeGovtRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if> >
                        <i class="bi bi-dash-circle"></i>
                    </button>
                </div>
                </c:if>
            </div>
        </div>
    </div>


<script>

let allGovtFieldsValid = true;
let govRowIndex = 0;
let minGovtBlockLength = 1;

function reset_govt_grants(){
	if(!isEditMode){
		resetAllFields();
	 }else{
		 resetOnlyActiveFields();
	 }
}

function validate_govt_grants() {
	allGovtFieldsValid = validateGovRow();
	console.log("allGovtFieldsValid ---------------------------------------" +allGovtFieldsValid );
	return allGovtFieldsValid;
}

$(document).ready(function () {
	
    let testform = $("#constructionTracker"); // Ensure this is your form ID
    

    testform.validate({
        ignore: ':hidden',
        errorClass: "text-danger",
        errorElement: "div"
    });

    let govtGrantsJSON = ${not empty constructionTrackerDTO.govtGrantsJSON ? constructionTrackerDTO.govtGrantsJSON : '[]'};
    if (govtGrantsJSON && govtGrantsJSON.length > 0) {
    	govRowIndex = govtGrantsJSON.length;
    	minGovtBlockLength = Math.max(govtGrantsJSON.length, 1);
    	console.log("govRowIndex ------------ "+govRowIndex)
    	populateExistingGovtGant(govtGrantsJSON);
    } else {
    	addGovtBlock();
    }
    calculateGovtGrantTotals();
    calculateGovtFacilityAndExpendictureAmount();
    
    // Add new block if all current fields are valid
    $('#addGovtRowBtn').on('click', function () {
        let isValid = true;

        $('.govt-block').each(function () {
            const inputs = $(this).find('select, input');
            inputs.each(function () {
                if (!$(this).valid()) {
                    isValid = false;
                }
            });
        });

        if (isValid) {
            addGovtBlock();
        }
    });

    // Remove last block if more than 1
    $('#removeGovtRowBtn').on('click', function () {
        const blocks = $('.govt-block');
        if (blocks.length > minGovtBlockLength) {
            blocks.last().remove();
            calculateGovtGrantTotals();
            calculateGovtFacilityAndExpendictureAmount();
        }
    });

});
    // Function to add a new block
    function addGovtBlock() {
        const block = $(
            '<div class="row govt-block mb-3 border p-3 rounded" data-index="' + govRowIndex + '">' +
                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="govt-Grants"/> <sup class="text-danger">*</sup></label>' +
                        '<select class="form-control govtGrants" name="govtGrants_' + govRowIndex + '">' +
                            '<option value=""><liferay-ui:message key="select"/></option>' +
                            '<option value="A">A</option>' +
                            '<option value="B">B</option>' +
                            '<option value="C">C</option>' +
                            '<option value="D">D</option>' +
                        '</select>' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Received-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control govtReceivedAmount" name="govtReceivedAmount_' + govRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="date"/><sup class="text-danger">*</sup></label>' +
                        '<input type="date" class="form-control govtDate" name="govtDate_' + govRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Facility-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control govtFacilityAmount" name="govtFacilityAmount_' + govRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Expenditure-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control govtExpenditureAmount" name="govtExpenditureAmount_' + govRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Total-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control govtTotalAmount" name="govtTotalAmount_' + govRowIndex + '" readonly>' +
                    '</div>' +
                '</div>' +
            '</div>'
        );

        $('#govtGrantsContainer').append(block);
        applyGovtValidationRules(block,govRowIndex);
        govRowIndex++;
    }



function calculateGovtGrantTotals() {
    let totalReceived = 0;
    let totalExpenditure = 0;
    let totalOverall = 0;

    $('.govtReceivedAmount').each(function () {
        const val = parseFloat($(this).val());
        if (!isNaN(val)) totalReceived += val;
    });

    $('.govtExpenditureAmount').each(function () {
        const val = parseFloat($(this).val());
        if (!isNaN(val)) totalExpenditure += val;
    });

    $('.govtTotalAmount').each(function () {
        const val = parseFloat($(this).val());
        if (!isNaN(val)) totalOverall += val;
    });

    $('#totalGovtReceivedAmount').val(totalReceived.toFixed(2));
    $('#totalGovtExpenditureAmount').val(totalExpenditure.toFixed(2));
    $('#totalGovtOverallAmount').val(totalOverall.toFixed(2));
}

function calculateGovtFacilityAndExpendictureAmount() {
    $('.govt-block').each(function () {
        let facility = parseFloat($(this).find('.govtFacilityAmount').val()) || 0;
        let expenditure = parseFloat($(this).find('.govtExpenditureAmount').val()) || 0;
        let total = facility + expenditure;

        $(this).find('.govtTotalAmount').val(total.toFixed(2));
    });
}

function validateGovRow(){
	 let isValid = true;

     $('.govt-block').each(function () {
         const inputs = $(this).find('select, input');
         inputs.each(function () {
             if (!$(this).valid()) {
                 isValid = false;
             }
         });
     });

    return isValid;
}


/* PRE POPULATION ---------------------------------------------------- */

function populateExistingGovtGant(govtGrantsJSON){
	let mode = '${mode}';
	const isDisabled = (mode === 'view' || mode === 'edit') ? 'disabled' : '';
	const isReadOnly = (mode === 'view' || mode === 'edit') ? 'readonly' : '';
	govtGrantsJSON.forEach(function(item, index) {
		  const readOnlyRow =  
			  '<div class="row govt-block mb-3 border p-3 rounded" data-index="' + index + '">' +
		          '<div class="col-md-6">' +
		          '<div class="form-group">' +
		              '<label><liferay-ui:message key="govt-Grants"/> <sup class="text-danger">*</sup></label>' +
		              '<select class="form-control govtGrants" name="govtGrants_' + index + '" '+isDisabled+'>' +
		                  '<option value=""><liferay-ui:message key="select"/></option>' +
		                  '<option value="A" ' + (item.grantType == 'A' ? 'selected' : '') + '>A</option>' +
		                  '<option value="B" ' + (item.grantType == 'B' ? 'selected' : '') + '>B</option>' +
		                  '<option value="C" ' + (item.grantType == 'C' ? 'selected' : '') + '>C</option>' +
		                  '<option value="D" ' + (item.grantType == 'D' ? 'selected' : '') + '>D</option>' +
		              '</select>' +
		          '</div>' +
		      '</div>' +
		
		      '<div class="col-md-6">' +
		          '<div class="form-group">' +
		              '<label><liferay-ui:message key="Received-Amount"/><sup class="text-danger">*</sup></label>' +
		              '<input type="number" step="0.01" class="form-control govtReceivedAmount" name="govtReceivedAmount_' + index + '" value="' + item.receivedAmount + '" '+isReadOnly+'>' +
		          '</div>' +
		      '</div>' +
		
		      '<div class="col-md-6">' +
		          '<div class="form-group">' +
		              '<label><liferay-ui:message key="date"/><sup class="text-danger">*</sup></label>' +
		              '<input type="date" class="form-control govtDate" name="govtDate_' + index + '" value="' + formatDateForInput(item.date) + '" '+isReadOnly+'>' +
		          '</div>' +
		      '</div>' +
		
		      '<div class="col-md-6">' +
		          '<div class="form-group">' +
		              '<label><liferay-ui:message key="Facility-Amount"/><sup class="text-danger">*</sup></label>' +
		              '<input type="number" step="0.01" class="form-control govtFacilityAmount" name="govtFacilityAmount_' + index + '" value="' + item.facilityAmount + '" '+isReadOnly+' >' +
		          '</div>' +
		      '</div>' +
		
		      '<div class="col-md-6">' +
		          '<div class="form-group">' +
		              '<label><liferay-ui:message key="Expenditure-Amount"/><sup class="text-danger">*</sup></label>' +
		              '<input type="number" step="0.01" class="form-control govtExpenditureAmount" name="govtExpenditureAmount_' + index + '" value="' + item.expenditureAmount + '" '+isReadOnly+'>' +
		          '</div>' +
		      '</div>' +
		
		      '<div class="col-md-6">' +
		          '<div class="form-group">' +
		              '<label><liferay-ui:message key="Total-Amount"/><sup class="text-danger">*</sup></label>' +
		              '<input type="number" step="0.01" class="form-control govtTotalAmount" name="govtTotalAmount_' + index + '" value="' + item.totalAmount + '" readonly>' +
		          '</div>' +
		      '</div>' +
		  '</div>'
		  
		  
		      const $readOnlyRow = $($.parseHTML(readOnlyRow)); 
				$('#govtGrantsContainer').append($readOnlyRow);
				if (mode !== 'view' && mode !== 'edit') {
					applyGovtValidationRules($readOnlyRow, index);
				}
				
	});
}


function applyGovtValidationRules(block,govRowIndex){
	 // Add validation rules per input
	 
	  $.validator.addMethod("pastOrToday", function(value, element) {
    		    if (this.optional(element)) return true;
    		    const inputDate = new Date(value);
    		    const today = new Date();
    		    inputDate.setHours(0, 0, 0, 0);
    		    today.setHours(0, 0, 0, 0);
    		    return inputDate <= today;
    		}, "<liferay-ui:message key='future-date-not-allowed' />");
	 
    block.find('[name="govtGrants_' + govRowIndex + '"]').rules('add', {
        required: true,
        messages: {
            required: '<liferay-ui:message key="please-select-grant" />'
        }
    });

    block.find('[name="govtReceivedAmount_' + govRowIndex + '"]').rules('add', {
        required: true,
        number: true,
        min: 1,
        maxlength: 10,
        messages: {
            required: '<liferay-ui:message key="please-enter-received-amount" />',
            number: '<liferay-ui:message key="approval-amount-must-be-number" />',
            min: '<liferay-ui:message key="amount-must-be-positive-or-greater-than-0" />',
            maxlength: '<liferay-ui:message key="maximum-10-digits-should-allow" />',
        }
    });

    block.find('[name="govtDate_' + govRowIndex + '"]').rules('add', {
        required: true, pastOrToday:true,
        messages: {
            required: '<liferay-ui:message key="please-select-date" />'
        }
    });

    block.find('[name="govtFacilityAmount_' + govRowIndex + '"]').rules('add', {
        required: true,
        number: true,
        min: 1,
        maxlength: 10,
        messages: {
            required: '<liferay-ui:message key="please-enter-facility-amount" />',
            number: '<liferay-ui:message key="approval-amount-must-be-number" />',
            min: '<liferay-ui:message key="amount-must-be-positive-or-greater-than-0" />',
            maxlength: '<liferay-ui:message key="maximum-10-digits-should-allow" />',
        }
    });

    block.find('[name="govtExpenditureAmount_' + govRowIndex + '"]').rules('add', {
        required: true,
        number: true,
        min: 1,
        maxlength: 10,
        messages: {
            required: '<liferay-ui:message key="please-enter-expenditure-amount" />',
            number: '<liferay-ui:message key="approval-amount-must-be-number" />',
            min: '<liferay-ui:message key="amount-must-be-positive-or-greater-than-0" />',
            maxlength: '<liferay-ui:message key="maximum-10-digits-should-allow" />',
        }
    });

    block.find('[name="govtTotalAmount_' + govRowIndex + '"]').rules('add', {
        required: true,
        number: true,
        min: 1,
        maxlength: 10,
        messages: {
            required: '<liferay-ui:message key="please-enter-total-amount" />',
            number: '<liferay-ui:message key="approval-amount-must-be-number" />',
            min: '<liferay-ui:message key="amount-must-be-positive-or-greater-than-0" />',
            maxlength: '<liferay-ui:message key="maximum-10-digits-should-allow" />',
        }
    });
    
    block.find('input').on('input change', function () {
        calculateGovtGrantTotals();
        calculateGovtFacilityAndExpendictureAmount();
    });

}
function formatDateForInput(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toISOString().split('T')[0];
}

</script>
