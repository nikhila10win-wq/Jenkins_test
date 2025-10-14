<%@ include file="/init.jsp"%>

<div class="card card-background p-0">
	<div class="personal_details">
		<div class="card-header header-card"><liferay-ui:message key="land-details"/></div>
		<div class="card-body">
			<div class="row">
				<%-- <div class="col-md-6">
					<div class="form-group">
					  <label><liferay-ui:message key="land-Available"/> <sup class="text-danger">*</sup></label>
					  <div class="d-flex mt-2">
					    <div class="radio-text">
					      <input type="radio" class="radio-btn" id="landAvailableYes" name="landAvailable" value="Yes"
					        <c:if test="${constructionTrackerDTO.landAvailable eq 'Yes'}">checked</c:if>
					        <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> />
					      <label for="landAvailableYes"><liferay-ui:message key="yes"/></label>
					    </div>
					    <div class="radio-text ms-3">
					      <input type="radio" class="radio-btn" id="landAvailableNo" name="landAvailable" value="No"
					        <c:if test="${constructionTrackerDTO.landAvailable eq 'No'}">checked</c:if>
					        <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> />
					      <label for="landAvailableNo"><liferay-ui:message key="no"/></label>
					    </div>
					  </div>
					</div>
				</div> --%>
				
				<div class="col-md-6">
				  <div class="form-group">
				    <label><liferay-ui:message key="land-Available"/> <sup class="text-danger">*</sup></label>
				    <div class="d-flex mt-2">
				    
				      <div class="radio-text">
				        <input type="radio" class="radio-btn landAvailable" id="landAvailableYes" name="landAvailable" value="Yes"
				          ${empty constructionTrackerDTO.landAvailable || constructionTrackerDTO.landAvailable == 'Yes' ? 'checked' : ''}
				          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
				        <label for="landAvailableYes"><liferay-ui:message key="yes"/></label>
				      </div>
				      
				      <div class="radio-text ms-3">
				        <input type="radio" class="radio-btn landAvailable" id="landAvailableNo" name="landAvailable" value="No"
				          ${constructionTrackerDTO.landAvailable == 'No' ? 'checked' : ''}
				          ${mode == 'view' || mode == 'edit' ? 'disabled' : ''} />
				        <label for="landAvailableNo"><liferay-ui:message key="no"/></label>
				      </div>
				      
				    </div>
				  </div>
				</div>
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="type-Of-Land"/> <sup class="text-danger">*</sup></label>
						<select class="form-control" name="typeOfLand" id="typeOfLand" <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select"/></option>
							<option value="government" <c:if test="${constructionTrackerDTO.typeOfLand eq 'government'}">selected</c:if> ><liferay-ui:message key="government"/></option>
							<option value="private" <c:if test="${constructionTrackerDTO.typeOfLand eq 'private'}">selected</c:if> ><liferay-ui:message key="private"/></option>
						</select>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="area-in-acres"/><sup class="text-danger">*</sup></label>
						<input type="text" class="form-control" pattern="^\d+(\.\d{1,2})?$" name="area" id="area"  <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> value="${constructionTrackerDTO.area <=0 ?'' : constructionTrackerDTO.area}">
<%-- 						<input type="text" class="form-control" pattern="^\d+(\.\d{1,2})?$" name="area" id="area"  <c:if test="${mode eq 'view' || mode eq 'edit'}">disabled</c:if> value="${constructionTrackerDTO.area}"> --%>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="ownership"/> <sup class="text-danger">*</sup></label>
						<select class="form-control" name="ownership" id="ownership" <c:if test="${mode eq 'view'}">disabled</c:if> >
							<option value=""><liferay-ui:message key="select"/></option>
							<option value="ownership" <c:if test="${constructionTrackerDTO.ownership eq 'ownership'}">selected</c:if> ><liferay-ui:message key="ownership"/></option>
							<option value="ownership" <c:if test="${constructionTrackerDTO.ownership eq 'ownership'}">selected</c:if> ><liferay-ui:message key="ownership"/></option>
						</select>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<script>

function reset_land_details(){
	if(!isEditMode){
		resetAllFields();
	}else{
		$('#ownership').val('');
	}
}

window.validate_land_details = function () {
    console.log("In validate_land_details ------ ");
    let isValid = true;

    $form.find(".landAvailable").rules("add", {
        required: true,
        messages: { required: '<liferay-ui:message key="please-select-land-available" />' }
    });

    $form.find("#typeOfLand").rules("add", {
        required: true,
        messages: { required: '<liferay-ui:message key="please-select-type-of-land" />' }
    });

    $form.find("#area").rules("add", {
        required: true, number: true, min: 1, maxlength: 10,
        messages: {
            required: '<liferay-ui:message key="please-enter-area" />',
            number: '<liferay-ui:message key="area-should-be-number" />',
            min: '<liferay-ui:message key="area-should-be-positive-or-greater-than-0" />',
            maxlength: '<liferay-ui:message key="area-should-not-exceed-10-digits" />',
        }
    });

    $form.find("#ownership").rules("add", {
        required: true,
        messages: { required: '<liferay-ui:message key="please-select-ownership" />' }
    });

    $("#land-details").find("input, select, textarea").filter(":visible").each(function () {
        if (!$form.validate().element(this)) isValid = false;
    });

    return isValid;
};
</script>
