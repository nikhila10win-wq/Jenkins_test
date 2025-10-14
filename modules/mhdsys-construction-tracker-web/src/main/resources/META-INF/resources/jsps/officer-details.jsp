<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="officer-details"/></div>
		<div class="card-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="officer-Name"/><sup class="text-danger">*</sup></label>
						<input class="form-control" name="officerName" id="officerName" <c:if test="${mode eq 'view'}">disabled</c:if> value="${constructionTrackerDTO.officerName }">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="contact-Number"/><sup class="text-danger">*</sup></label>
						<input class="form-control" name="contactNumber" id="contactNumber" <c:if test="${mode eq 'view'}">disabled</c:if> value="${constructionTrackerDTO.contactNumber }">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>

function reset_officer_details(){
	resetAllFields();
}

window.validate_officer_details = function () {
    console.log("In validate_officer_details ------ ");
    let isValid = true;

    $form.find("#officerName").rules("add", {
        required: true,
        minlength: 3,
        maxlength: 75,
        validPersonName: true,
        noEdgeSpaces: true,
        singleSpaceOnly: true,
        noConsecutiveSpecials:true,
        messages: { 
        	required: '<liferay-ui:message key="please-enter-officer-name" />' ,
        	minlength: "<liferay-ui:message key='please-enter-min-3-characters' />",
            maxlength: "<liferay-ui:message key='please-enter-max-75-characters' />",
        	}
    });

    $form.find("#contactNumber").rules("add", {
        required: true, digits: true, minlength: 10, maxlength: 10,validContact: true,
        messages: {
            required: '<liferay-ui:message key="please-enter-contact-number" />',
            digits: '<liferay-ui:message key="contact-number-digits-only" />',
            minlength: '<liferay-ui:message key="contact-number-should-be-10-digits" />',
            maxlength: '<liferay-ui:message key="contact-number-should-be-10-digits" />'
        }
    });

    $("#officer-details").find("input, select, textarea").filter(":visible").each(function () {
        if (!$form.validate().element(this)) isValid = false;
    });

    return isValid;
};
</script>
