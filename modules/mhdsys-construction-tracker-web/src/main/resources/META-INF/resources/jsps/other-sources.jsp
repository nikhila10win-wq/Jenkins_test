<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
    <div class="personal_details">
    <input type="hidden" id="totalReceivedAmount" name="totalReceivedAmount">
	<input type="hidden" id="totalExpenditureAmount" name="totalExpenditureAmount">
	<input type="hidden" id="totalOverallAmount" name="totalOverallAmount">
    
    
        <div class="card-header header-card">
            <liferay-ui:message key="other-sources"/>
        </div>
        <div class="card-body">

            <!-- Container for dynamic blocks -->
            <div id="otherSourcesContainer"></div>
			<c:if test="${mode ne 'view'}">
            <div class="buttonsDiv mt-2">
                <button type="button" class="btn btn-primary" id="addOtherSourcesRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if>>
                    <i class="bi bi-plus-circle"></i>
                </button>
                <button type="button" class="btn btn-danger" id="removeOtherSourcesRowBtn" <c:if test="${mode eq 'view'}">disabled</c:if>>
                    <i class="bi bi-dash-circle"></i>
                </button>
            </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Scripts -->
<script>

let allOtherSourcesFieldsValid = true;
let otherSourcesRowIndex = 0;
let minOtherSourceBlockLength = 1;

function reset_other_sources(){
	if(!isEditMode){
		resetAllFields();
	 }else{
		 resetOnlyActiveFields();
	 }
}

function validate_other_sources() {
	allOtherSourcesFieldsValid = validateOtherSourcesRow();
	console.log("allOtherSourcesFieldsValid ---------------------------------------" +allOtherSourcesFieldsValid );
	 validate_other();
	return allOtherSourcesFieldsValid;
}

$(document).ready(function () {
	 
    const form = $("#constructionTracker"); 

    form.validate({
        ignore: ':hidden',
        errorClass: "text-danger",
        errorElement: "div"
    });

    let otherSourcesJSON = ${not empty constructionTrackerDTO.otherSourcesJSON ? constructionTrackerDTO.otherSourcesJSON : '[]'};
    if (otherSourcesJSON && otherSourcesJSON.length > 0) {
    	otherSourcesRowIndex = otherSourcesJSON.length;
    	minOtherSourceBlockLength = Math.max(otherSourcesJSON.length, 1);
    	console.log("govRowIndex ------------ "+govRowIndex)
    	populateExistingOtherSources(otherSourcesJSON);
    } else {
    	addOtherSourcesBlock();
    }
    calculateOtherSourcesTotals();
    calculateOtherFacilityAndExpendictureAmount();

    $('#addOtherSourcesRowBtn').on('click', function () {
        let isValid = true;

        $('.other-sources-block').each(function () {
            $(this).find('select, input').each(function () {
                if (!$(this).valid()) {
                    isValid = false;
                }
            });
        });

        if (isValid) {
            addOtherSourcesBlock();
        }
    });

    $('#removeOtherSourcesRowBtn').on('click', function () {
        const blocks = $('.other-sources-block');
        if (blocks.length > minOtherSourceBlockLength) {
            blocks.last().remove();
            calculateOtherSourcesTotals();
            calculateOtherFacilityAndExpendictureAmount();
        }
    });

});

    function addOtherSourcesBlock() {
        const block = $(
            '<div class="row other-sources-block mb-3 border p-3 rounded" data-index="' + otherSourcesRowIndex + '">' +

               /*  '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Other-Sources"/> <sup class="text-danger">*</sup></label>' +
                        '<div class="form-check">' +
                            '<input type="radio" class="form-check-input" name="otherSources_' + otherSourcesRowIndex + '" value="Yes" checked>' +
                            '<label class="form-check-label"><liferay-ui:message key="yes"/></label>' +
                        '</div>' +
                        '<div class="form-check">' +
                            '<input type="radio" class="form-check-input" name="otherSources_' + otherSourcesRowIndex + '" value="No">' +
                            '<label class="form-check-label"><liferay-ui:message key="no"/></label>' +
                        '</div>' +
                    '</div>' +
                '</div>' + */
                
                '<div class="col-md-6">' +
                '<div class="form-group">' +
                    '<label><liferay-ui:message key="other-sources"/> <sup class="text-danger">*</sup></label>' +
                    '<div class="d-flex gap-3 mt-2">' +
                        '<div class="form-check">' +
                            '<input type="radio" class="form-check-input" name="otherSources_' + otherSourcesRowIndex + '" value="Yes" checked>' +
                            '<label class="form-check-label mr-5"><liferay-ui:message key="yes"/></label>' +
                        '</div>' +
                        '<div class="form-check">' +
                            '<input type="radio" class="form-check-input" name="otherSources_' + otherSourcesRowIndex + '" value="No">' +
                            '<label class="form-check-label"><liferay-ui:message key="no"/></label>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</div>'+


                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Received-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control otherSourcesReceivedAmount" name="otherSourcesReceivedAmount_' + otherSourcesRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="date"/><sup class="text-danger">*</sup></label>' +
                        '<input type="date" class="form-control otherSourcesDate" name="otherSourcesDate_' + otherSourcesRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Facility-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control otherSourcesFacilityAmount" name="otherSourcesFacilityAmount_' + otherSourcesRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Expenditure-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control otherSourcesExpenditureAmount" name="otherSourcesExpenditureAmount_' + otherSourcesRowIndex + '">' +
                    '</div>' +
                '</div>' +

                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="Total-Amount"/><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control otherSourcesTotalAmount" name="otherSourcesTotalAmount_' + otherSourcesRowIndex + '" readonly>' +
                    '</div>' +
                '</div>' +
            '</div>'
        );

        $('#otherSourcesContainer').append(block);

        applyValidationRules(block, otherSourcesRowIndex);
        
     // Radio toggle logic
        block.find('input[name="otherSources_' + otherSourcesRowIndex + '"]').on('change', function () {
            const value = $(this).val();
            const container = $(this).closest('.other-sources-block');

            const received = container.find('.otherSourcesReceivedAmount');
            const date = container.find('.otherSourcesDate');

            if (value === 'No') {
                received.val('').prop('disabled', true).rules('remove');
                date.val('').prop('disabled', true).rules('remove');
            } else {
                received.prop('disabled', false).rules('add', {
                    required: true,
                    number: true,
                    min: 0.01,
                    messages: {
                        required: '<liferay-ui:message key="please-enter-received-amount" />',
                        number: '<liferay-ui:message key="approval-amount-must-be-number" />',
                        min: '<liferay-ui:message key="approval-amount-must-be-positive" />'
                    }
                });

                date.prop('disabled', false).rules('add', {
                    required: true,
                    messages: {
                        required: '<liferay-ui:message key="please-select-date" />'
                    }
                });
            }
        });
        block.find('input').on('input change', function () {
            calculateOtherSourcesTotals();
            calculateOtherFacilityAndExpendictureAmount();
        });

        // Trigger toggle initially
        block.find('input[name="otherSources_' + otherSourcesRowIndex + '"]:checked').trigger('change');

        otherSourcesRowIndex++;
    }
    
function calculateOtherSourcesTotals() {
    let totalReceived = 0;
    let totalExpenditure = 0;
    let totalOverall = 0;

    $('.otherSourcesReceivedAmount:enabled').each(function () {
        const val = parseFloat($(this).val());
        if (!isNaN(val)) totalReceived += val;
    });

    $('.otherSourcesExpenditureAmount:enabled').each(function () {
        const val = parseFloat($(this).val());
        if (!isNaN(val)) totalExpenditure += val;
    });

    $('.otherSourcesTotalAmount:enabled').each(function () {
        const val = parseFloat($(this).val());
        if (!isNaN(val)) totalOverall += val;
    });

    $('#totalReceivedAmount').val(totalReceived.toFixed(2));
    $('#totalExpenditureAmount').val(totalExpenditure.toFixed(2));
    $('#totalOverallAmount').val(totalOverall.toFixed(2));
}

function calculateOtherFacilityAndExpendictureAmount() {
    $('.other-sources-block').each(function () {
        let facility = parseFloat($(this).find('.otherSourcesFacilityAmount').val()) || 0;
        let expenditure = parseFloat($(this).find('.otherSourcesExpenditureAmount').val()) || 0;
        let total = facility + expenditure;

        $(this).find('.otherSourcesTotalAmount').val(total.toFixed(2));
    });
}

function validateOtherSourcesRow(){
	 let isValid = true;
     $('.other-sources-block').each(function () {
         $(this).find('select, input').each(function () {
             if (!$(this).valid()) {
                 isValid = false;
             }
         });
     });
     return isValid;
}


/* PRE POPULATION */
 
function populateExistingOtherSources(otherSourcesJSON){
	let mode= '${mode}';
	const isDisabled = (mode === 'view' || mode === 'edit') ? 'disabled' : '';
	const isReadOnly = (mode === 'view' || mode === 'edit') ? 'readonly' : '';
	otherSourcesJSON.forEach(function(item, index) {
	let isNo = item.otherSources === 'No' ? 'disabled' : '';
		
		const readOnlyRow = 
			
			 '<div class="row other-sources-block mb-3 border p-3 rounded" data-index="' + index + '">' +

             '<div class="col-md-6">' +
                 '<div class="form-group">' +
                 '<label><liferay-ui:message key="other-sources"/> <sup class="text-danger">*</sup></label>' +
                 '<div class="d-flex mt-2">' +
                     '<div class="radio-text">' +
                        /*  '<input type="radio" class="radio-btn" id="otherSourcesYes_' + index + '" name="otherSources_' + index + '" value="Yes" ' + (item.otherSources === 'Yes' ? 'checked' : '') + 'disabled>' + */
                        '<input type="radio" class="radio-btn" id="otherSourcesYes_' + index + '" name="otherSources_' + index + '" value="Yes" ' + (item.otherSources === 'Yes' ? 'checked' : '') + ' '+isDisabled+'>' +
                         '<label for="otherSourcesYes_' + index + '"><liferay-ui:message key="yes"/></label>' +
                     '</div>' +
                     '<div class="radio-text ms-3">' +
                         /* '<input type="radio" class="radio-btn" id="otherSourcesNo_' + index + '" name="otherSources_' + index + '" value="No" ' + (item.otherSources === 'No' ? 'checked' : '') + 'disabled>' + */
                         '<input type="radio" class="radio-btn" id="otherSourcesNo_' + index + '" name="otherSources_' + index + '" value="No" ' + (item.otherSources === 'No' ? 'checked' : '') + ' '+isDisabled+'>' +
                         '<label for="otherSourcesNo_' + index + '"><liferay-ui:message key="no"/></label>' +
                     '</div>' +
                 '</div>' +
                 '</div>' +
             '</div>' +

             '<div class="col-md-6">' +
                 '<div class="form-group">' +
                     '<label><liferay-ui:message key="Received-Amount"/><sup class="text-danger">*</sup></label>' +
                     '<input type="number" step="0.01" class="form-control otherSourcesReceivedAmount" name="otherSourcesReceivedAmount_' + index + '" value="' + item.receivedAmount + '" '+isReadOnly+' '+isNo+'>' +
                 '</div>' +
             '</div>' +

             '<div class="col-md-6">' +
                 '<div class="form-group">' +
                     '<label><liferay-ui:message key="date"/><sup class="text-danger">*</sup></label>' +
                     '<input type="date" class="form-control otherSourcesDate" name="otherSourcesDate_' + index + '" value="' + formatDateForInput(item.date) + '" '+isReadOnly+' '+isNo+'>' +
                 '</div>' +
             '</div>' +

             '<div class="col-md-6">' +
                 '<div class="form-group">' +
                     '<label><liferay-ui:message key="Facility-Amount"/><sup class="text-danger">*</sup></label>' +
                     '<input type="number" step="0.01" class="form-control otherSourcesFacilityAmount" name="otherSourcesFacilityAmount_' + index + '" value="' + item.totalAmount + '" '+isReadOnly+'>' +
                 '</div>' +
             '</div>' +

             '<div class="col-md-6">' +
                 '<div class="form-group">' +
                     '<label><liferay-ui:message key="Expenditure-Amount"/><sup class="text-danger">*</sup></label>' +
                     '<input type="number" step="0.01" class="form-control otherSourcesExpenditureAmount" name="otherSourcesExpenditureAmount_' + index + '" value="' + item.expenditureAmount + '" '+isReadOnly+'>' +
                 '</div>' +
             '</div>' +

             '<div class="col-md-6">' +
                 '<div class="form-group">' +
                     '<label><liferay-ui:message key="Total-Amount"/><sup class="text-danger">*</sup></label>' +
                     '<input type="number" step="0.01" class="form-control otherSourcesTotalAmount" name="otherSourcesTotalAmount_' + index + '" value="' + item.totalAmount + '" readonly>' +
                 '</div>' +
             '</div>' +
         '</div>'
		
         const $readOnlyRow = $($.parseHTML(readOnlyRow)); // Wrap the string as jQuery object
		$('#otherSourcesContainer').append($readOnlyRow);
		if (mode !== 'view' && mode !== 'edit') {
			 applyValidationRules($readOnlyRow, index);
		}
		
	})
	
}

function formatDateForInput(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toISOString().split('T')[0];
}

function applyValidationRules(block, index) {
	
	 $.validator.addMethod("pastOrToday", function(value, element) {
		    if (this.optional(element)) return true;
		    const inputDate = new Date(value);
		    const today = new Date();
		    inputDate.setHours(0, 0, 0, 0);
		    today.setHours(0, 0, 0, 0);
		    return inputDate <= today;
		}, "<liferay-ui:message key='future-date-not-allowed' />");
	 
    block.find('[name="otherSources_' + index + '"]').rules('add', {
        required: true,
        messages: {
            required: '<liferay-ui:message key="please-choose-option" />',
        }
    });

    block.find('[name="otherSourcesReceivedAmount_' + index + '"]').rules('add', {
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

    block.find('[name="otherSourcesDate_' + index + '"]').rules('add', {
        required: true, pastOrToday:true,
        messages: {
            required: '<liferay-ui:message key="please-select-date" />'
        }
    });

    block.find('[name="otherSourcesFacilityAmount_' + index + '"]').rules('add', {
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

    block.find('[name="otherSourcesExpenditureAmount_' + index + '"]').rules('add', {
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

    block.find('[name="otherSourcesTotalAmount_' + index + '"]').rules('add', {
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
    
    // Radio toggle logic
    block.find('input[name="otherSources_' + index + '"]').on('change', function () {
        const value = $(this).val();
        const container = $(this).closest('.other-sources-block');

        const received = container.find('.otherSourcesReceivedAmount');
        const date = container.find('.otherSourcesDate');

        if (value === 'No') {
            received.val('').prop('disabled', true).rules('remove');
            date.val('').prop('disabled', true).rules('remove');
        } else {
            received.prop('disabled', false).rules('add', {
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

            date.prop('disabled', false).rules('add', {
                required: true,
                messages: {
                    required: '<liferay-ui:message key="please-select-date" />'
                }
            });
        }
    });
    block.find('input').on('input change', function () {
        calculateOtherSourcesTotals();
        calculateOtherFacilityAndExpendictureAmount();
    });

    // Trigger toggle initially
    block.find('input[name="otherSources_' + index + '"]:checked').trigger('change');
    
}

</script>
