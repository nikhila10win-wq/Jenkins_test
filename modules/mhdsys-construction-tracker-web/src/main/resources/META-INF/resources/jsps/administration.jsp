<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
    <div class="personal_details">
        <div class="card-header header-card">
            <liferay-ui:message key="administration" />
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label><liferay-ui:message key="administrative-Approval" /> <sup class="text-danger">*</sup></label>
                        <div class="d-flex mt-2">
						    <div class="radio-text">
						      <input type="radio" class="radio-btn administrativeApproval" id="administrativeApprovalYes" name="administrativeApproval" value="Yes"
						        <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> />
						      <label for="administrativeApprovalYes"><liferay-ui:message key="yes"/></label>
						    </div>
						    <div class="radio-text ms-3">
						      <input type="radio" class="radio-btn administrativeApproval" id="administrativeApprovalNo" name="administrativeApproval" value="No"
						        <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> />
						      <label for="administrativeApprovalNo"><liferay-ui:message key="no"/></label>
						    </div>
					  </div>
					  
                    </div>
                </div>
					  
            </div>

		<div id="approvalBlock">
		    <div id="approvalContainer"></div>
		    
		    <c:if test="${mode ne 'view'}">
		        <div class="buttonsDiv mt-2">
		            <button type="button" class="btn btn-primary" id="addAdministrationRowBtn"> <i class="bi bi-plus-circle"></i></button>
		            <button type="button" class="btn btn-danger" id="removeAdministrationRowBtn"><i class="bi bi-dash-circle"></i></button>
		        </div>
		    </c:if>
		</div>

        </div>
    </div>
</div>

<script>
 let approvalRowCount = 0;
 let minBlockLength = 1;
 
 $(document).ready(function () {
 	
     const form = $('#constructionTracker');
     form.validate({
         ignore: ':hidden',
         errorClass: 'text-danger',
         errorElement: 'div',
         rules: {
             administrativeApproval: { required: true }
         },
         messages: {
             administrativeApproval: '<liferay-ui:message key="please-enter-administrative-approval" />'
         }
     });
     
     let administrationJSONArray = ${not empty constructionTrackerDTO.administrationJSON ? constructionTrackerDTO.administrationJSON : '[]'};
	    if (administrationJSONArray && administrationJSONArray.length > 0) {
	    	let administrativeApprovalYes = administrationJSONArray[0].administrativeApproval;
	    	if(administrativeApprovalYes=="Yes"){
	    		$('#administrativeApprovalYes').prop('checked', true);
	   	        $('#approvalBlock').show();
	    	}else{
	    		$('#administrativeApprovalNo').prop('checked', true);
	    		 $('#approvalBlock').hide();
	    	}
	        minBlockLength = Math.max(administrationJSONArray.length, 1);
	        approvalRowCount = administrationJSONArray.length; // Setting row count if records exist
	        console.log("approvalRowCount:: "+approvalRowCount);
	        populateExistingApprovals(administrationJSONArray);
	    } else {
	        // Default to "Yes" if field is empty (fresh form)
	        $('#administrativeApprovalYes').prop('checked', true);
	        $('#approvalBlock').show();
	        addApprovalRow();
	        console.log("Added New Row for Administration ---------- ")
	    }

     $('input[name="administrativeApproval"]').on('change', function () {
         const value = $(this).val();
         if (value === 'Yes') {
             $('#approvalBlock').show();
             if ($('#approvalContainer').children().length === 0 && (!administrationJSONArray || administrationJSONArray.length === 0)) {
                 addApprovalRow();
             }
         } else {
             $('#approvalBlock').hide();
             /* $('#approvalContainer').empty(); */
         }
     });

     $('#addAdministrationRowBtn').on('click', function () {
     	if (validateAndAddRow()) {
     		addApprovalRow();
		}
     });
     
     $('#removeAdministrationRowBtn').on('click', function () {
      const blocks = $('.approval-row');
      if (blocks.length > minBlockLength) {
          blocks.last().remove();
      }	
  });
    
 });
    
 function reset_administration(){
	 if(!isEditMode){
			resetAllFields();
		 }else{
			 resetOnlyActiveFields();
		 }
	}
 
    function validate_administration() {
    	allAdministrationFieldsValid = validateAndAddRow();
    	console.log("allAdministrationFieldsValid ---------------------------------------" +allAdministrationFieldsValid );
    	return allAdministrationFieldsValid;
    }

    function validateAndAddRow() {
        let isValid = true;
        let administrativeApprovalRadio = $('[name="administrativeApproval"]').val();
        if (administrativeApprovalRadio === "Yes") {
            $('.approvalAmount, .dateOfApproval').each(function () {
                if ($(this).is(':visible') && !$(this).valid()) {
                    isValid = false;
                }
            });
        }
     
        return isValid;
    }
    
    
    function addApprovalRow() {
       
        const row =
            '<div class="row approval-row mb-2">' +
                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="approval-Amount" /><sup class="text-danger">*</sup></label>' +
                        '<input type="number" step="0.01" class="form-control approvalAmount" name="approvalAmount_' + approvalRowCount + '">' +
                    '</div>' +
                '</div>' +
                '<div class="col-md-6">' +
                    '<div class="form-group">' +
                        '<label><liferay-ui:message key="date-Of-Approval" /><sup class="text-danger">*</sup></label>' +
                        '<input type="date" class="form-control dateOfApproval" name="dateOfApproval_' + approvalRowCount + '">' +
                    '</div>' +
                '</div>' +
            '</div>';
      
            const $row = $($.parseHTML(row)); // convert to jQuery object
            $('#approvalContainer').append($row);

            applyAdministrationValidationRules($row, approvalRowCount);
            
        approvalRowCount++;
    }
    
    /* PRE POPULATION ---------------------------------------------------------------------  */
  
     function populateExistingApprovals(administrationJSONArray) {
    	let mode = '${mode}';
    	const isReadOnly = (mode === 'view' || mode === 'edit') ? 'readonly' : '';
        administrationJSONArray.forEach(function(item, index) {
            const readOnlyRow =
                '<div class="row approval-row mb-2">' +
                    '<div class="col-md-6">' +
                        '<div class="form-group">' +
                            '<label><liferay-ui:message key="approval-Amount" /></label>' +
                            '<input type="number" step="0.01" class="form-control approvalAmount" name="approvalAmount_' + index + '" value="' + item.approvalAmount + '" '+isReadOnly+'>' +
                        '</div>' +
                    '</div>' +
                    '<div class="col-md-6">' +
                        '<div class="form-group">' +
                            '<label><liferay-ui:message key="date-Of-Approval" /></label>' +
                            '<input type="date" class="form-control dateOfApproval" name="dateOfApproval_' + index + '" value="' + formatDateForInput(item.dateOfApproval) + '" '+isReadOnly+'>' +
                        '</div>' +
                    '</div>' +
                '</div>';
            const $readOnlyRow = $($.parseHTML(readOnlyRow)); 
			$('#approvalContainer').append($readOnlyRow);
			if (mode !== 'view' && mode !== 'edit') {
				applyAdministrationValidationRules($readOnlyRow, index);
			}
        });
    }
    
  // Force immediate validation on keyup/input
   /*  $(document).on('input keyup', '.approvalAmount, .dateOfApproval', function () {
	    $(this).valid(); // Trigger validation immediately for any of the fields
	}); */
  
     function applyAdministrationValidationRules(row, approvalRowCount) {
    	 
    	 $.validator.addMethod("pastOrToday", function(value, element) {
    		    if (this.optional(element)) return true;
    		    const inputDate = new Date(value);
    		    const today = new Date();
    		    inputDate.setHours(0, 0, 0, 0);
    		    today.setHours(0, 0, 0, 0);
    		    return inputDate <= today;
    		}, "<liferay-ui:message key='future-date-not-allowed' />");

    
    	    row.find('.approvalAmount').rules('add', {
    	        required: true,
    	        number: true,
    	        min: 1,
    	        maxlength:10,
    	        messages: {
    	            required: '<liferay-ui:message key="please-enter-approval-amount" />',
    	            number: '<liferay-ui:message key="approval-amount-must-be-number" />',
    	            min: '<liferay-ui:message key="it-must-be-positive-or-greater-than-0" />',
    	            maxlength: '<liferay-ui:message key="approval-amount-must-be-less-than-10-digits" />',
    	        }
    	    });

    	    row.find('.dateOfApproval').rules('add', {
    	        required: true,
    	        pastOrToday: true,
    	        messages: {
    	            required: '<liferay-ui:message key="please-select-date-of-approval" />'
    	        }
    	    });
    	    
    	}

     function formatDateForInput(dateStr) {
         if (!dateStr) return '';
         const date = new Date(dateStr);
         return date.toISOString().split('T')[0];
     }
     
</script>
