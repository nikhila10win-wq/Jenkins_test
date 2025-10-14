<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="agency-details"/></div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-12">
				    <div class="form-group">
				        <label><liferay-ui:message key="architect-Appointed"/> <sup class="text-danger">*</sup></label>
				        <div class="d-flex mt-2">
						    <div class="radio-text">
						        <input type="radio" class="radio-btn" id="architectAppointedYes" name="architectAppointed" value="Yes"
						            <c:if test="${constructionTrackerDTO.architectAppointed == 'Yes'}">checked</c:if>
						            <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> checked />
						        <label for="architectAppointedYes"><liferay-ui:message key="yes"/></label>
						    </div>
						    <div class="radio-text ms-3">
						        <input type="radio" class="radio-btn" id="architectAppointedNo" name="architectAppointed" value="No"
						            <c:if test="${constructionTrackerDTO.architectAppointed == 'No'}">checked</c:if>
						            <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> />
						        <label for="architectAppointedNo"><liferay-ui:message key="no"/></label>
						    </div>
						</div>
				    </div>
				</div>
				<div class="col-md-6 agency-details-div">
					<div class="form-group">
						<label><liferay-ui:message key="executing-Agency"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="executingAgency" id="executingAgency" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> value="${constructionTrackerDTO.executingAgency }">
					</div>
				</div>
				
				<div class="col-md-6 agency-details-div">
					<div class="form-group">
						<label><liferay-ui:message key="name-Of-The-Firm"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" name="nameOfTheFirm" id="nameOfTheFirm" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> value="${constructionTrackerDTO.nameOfTheFirm }" >
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>

function reset_agency_details(){
	if(!isEditMode){
		resetAllFields();
	 }
}

window.validate_agency_details = function () {
    console.log("Inside validate_agency_details ------ ");
    let isValid = true;

    const messages = {
			  architectAppointed: '<liferay-ui:message key="please-select-architect-appointed" />',
			  executingAgency: '<liferay-ui:message key="please-enter-executing-agency" />',
			  nameOfTheFirm: '<liferay-ui:message key="please-enter-name-of-the-firm" />',
    };

    $.each(messages, function (field, message) {
    	
    	if(field === "executingAgency" || field === "nameOfTheFirm" ){
       	 $form.find("#" + field).rules("add", {
       	        required: true,
       	        minlength: 3,
       	        maxlength: 75,
       	     	alphanumericWithPeroidAndHyphen: true,
	             noEdgeSpaces: true,
	             singleSpaceOnly: true,
	             noConsecutiveSpecials:true,
       	        messages: {
       	            required: message,
	       	        minlength: "<liferay-ui:message key='please-enter-min-3-characters' />",
	    	        maxlength: "<liferay-ui:message key='please-enter-max-75-characters' />",
       	        }
       	    });
       }else{
    	   $form.find("#" + field).rules("add", {
               required: true,
               messages: { required: message }
           });
       }
       
    }); 

    $("#agency-details")
        .find("input, select, textarea")
        .filter(":visible")
        .each(function () {
            if (!$form.validate().element(this)) {
                isValid = false;
            }
        });

    return isValid;
};

$(document).ready(function () {

	let architectAppointed = '${constructionTrackerDTO.architectAppointed}';
	if(architectAppointed == "Yes" || architectAppointed == ""){
		$('.agency-details-div').show();
	}else if(architectAppointed == "No"){
		 $('.agency-details-div').hide();
         $('.agency-details-div input').val('');
	}
	$('input[name="architectAppointed"]').on('change', function () {
	        const value = $(this).val();
	        if (value === 'No') {
	            $('.agency-details-div').hide();
	            $('.agency-details-div input').val('');
	        } else {
	            $('.agency-details-div').show();
	        }
	    });
	
	$.validator.addMethod("alphanumericOnly", function(value, element) {
	    return this.optional(element) || /^[A-Za-z0-9 ]+$/.test(value);
	}, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
	$.validator.addMethod("noEdgeSpaces", function(value, element) {
		  return this.optional(element) || value === value.trim();
	}, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

	$.validator.addMethod("singleSpaceOnly", function(value, element) {
	  return this.optional(element) || !/\s{2,}/.test(value);
	}, "<liferay-ui:message key='only-one-space-between-words-allowed' />");
	
	$.validator.addMethod("noConsecutiveSpecials", function(value, element) {
		  return this.optional(element) || !/[.,/#-]{2,}/.test(value);
	}, "<liferay-ui:message key='no-consecutive-specials-allowed' />");
	
	 const messages = {
		        executingAgency: '<liferay-ui:message key="please-enter-executing-agency" />',
		        nameOfTheFirm: '<liferay-ui:message key="please-enter-name-of-the-firm" />',
		        architectAppointed: '<liferay-ui:message key="please-select-architect-appointed" />'
		    };

		    $.each(messages, function (field, message) {
		        if (field === "executingAgency" || field === "nameOfTheFirm") {
		            $("#" + field).rules("add", {
		            	  required: true,
		         	        minlength: 3,
		         	        maxlength: 75,
		         	     	alphanumericWithPeroidAndHyphen: true,
		  	             noEdgeSpaces: true,
		  	             singleSpaceOnly: true,
		  	             noConsecutiveSpecials:true,
		                messages: {
		                    required: message,
		                    minlength: "<liferay-ui:message key='please-enter-min-3-characters' />",
			    	        maxlength: "<liferay-ui:message key='please-enter-max-75-characters' />",
		                }
		            });
		        } else {
		            $("input[name='architectAppointed']").rules("add", {
		                required: true,
		                messages: { required: message }
		            });
		        }
		    });
		    
});

</script>