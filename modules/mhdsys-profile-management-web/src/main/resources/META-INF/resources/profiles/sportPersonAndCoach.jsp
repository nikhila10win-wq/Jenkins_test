<%@ include file="/init.jsp" %>
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="card-header d-flex align-item-center justify-content-between">
						<h5>Profile Information</h5>						
					</div>
				<form id="profileInformationForm" method="POST" action="${addSportsPersonAndCoachEntryURL}" enctype="multipart/form-data"> 
					<div class="card-body">
					<div class="card card-background p-0">
					 <div class="card-header header-card">Profile Information</div>
						<div class="form-group" id="fileUploadEntryDiv" style="display: none;">
							<label id="labelHead"><sup class='text-danger'>*</sup><liferay-ui:icon-help message='Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB.'/></label>
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="" name="">
								<label class="custom-file-label" for="customFile">Choose File</label>
							</div>
						</div>
						<div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Date of Birth<sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${dateOfBirth}"/>
								</div>
							</div>
							<c:choose>  
							    <c:when test="${not empty dto.getString('birthCertificateEntryId','') and not empty birthCertificateFile}">  
									<div class="col-md-6 fileDisplayDiv">
										<div class="form-group">
											<label class="d-block">Birth Certificate<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="rounded border align-items-center d-flex dataView justify-content-between p-2 pr-0" style="height: 45px;margin-top: 5px;">
												<a href="${birthCertificateFile.getString('filePreviewUrl','') }" target="_blank">${birthCertificateFile.getString('fileName','') }</a>
												<button type="button" class="bg-danger border-0 text-white rounded" onclick="cloneFileUploadDiv('birthCertificateEntryAppendingDiv',this)"><i class="bi bi-x"></i></button>
											</div>
										</div>
									</div>
									<div id="birthCertificateEntryAppendingDiv"></div>
							    </c:when>   
							    <c:otherwise>  
									<div class="col-md-6">
										<div class="form-group">
											<label>Birth Certificate<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="birthCertificateFile" name="birthCertificateFile">
												<label class="custom-file-label" for="birthCertificateFile">Choose File</label>
											</div>
										</div>
									</div>
							    </c:otherwise>  
							</c:choose> 
						</div>
						
						<c:choose>  
						    <c:when test="${not empty CoachingDates}"> 
								<%
								JSONArray CoachingDates = (JSONArray)request.getAttribute("CoachingDates");
								for(int i=0; i<CoachingDates.length(); i++){
									JSONObject CoachingDate = CoachingDates.getJSONObject(i);
									%>
									<div class="row" id="coachingDatesDiv">
								        <div class="col-md-6">
								            <div class="form-group">
								                <label>Coaching Date (From)<sup class="text-danger">*</sup></label>
								                <input type="date" class="form-control" id="coachingDateFrom" name="coachingDateFrom[]" value="<%=CoachingDate.getString("from")%>"/>
								            </div>
								        </div>
								        <div class="col-md-5">
								            <div class="form-group">
								                <label>Coaching Date (To)<sup class="text-danger">*</sup></label>
								                <input type="date" class="form-control" id="coachingDateTo" name="coachingDateTo[]" value="<%=CoachingDate.getString("to")%>"/>
								            </div>
								        </div>
								        <%
								        if(i == 0){
								        	%>
							        		<div class="col-md-1"> 
												<button type="button" id="cloneCoachingDatesButton" class="btn addBtn mt-4">+</button> 
											</div>
								        	<%
								        }else{
								        	%>
								       		<div class="col-md-1">
								       			<button type="button" class="removeCoachingDatesButton btn addBtn mt-4" onclick="removeCoachingDatesRow(this)">-</button> 
											</div>
								        	<%
								        }
								        %>
								    </div>
									<%
								}
								%>
						    </c:when>   
						    <c:otherwise>  
								<div class="row" id="coachingDatesDiv">
									<div class="col-md-6">
										<div class="form-group">
											<label>Coaching Date (From)<sup class="text-danger">*</sup></label>
											<input type="date" class="form-control" id="coachingDateFrom" name="coachingDateFrom[]"/>
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
											<label>Coaching Date (To)<sup class="text-danger">*</sup></label>
											<input type="date" class="form-control" id="coachingDateTo" name="coachingDateTo[]"/>
										</div>
									</div>
									<div class="col-md-1">
										<button type="button" id="cloneCoachingDatesButton" class="btn addBtn mt-4">+</button> 
									</div>
								</div>
						    </c:otherwise>  
						</c:choose> 
						
						<div id="coachingDatesAppendingDiv"></div>
						
						
						<div class="row">
							<div class="col-md-6">
							    <div class="form-group">
							        <label>Postal Address<sup class="text-danger">*</sup></label>
							        <input type="text" class="form-control" id="postalAddress" name="postalAddress" value="${dto.getString('postalAddress','') }" />
							    </div>
							</div>

							<div class="col-md-6">
								<div class="form-group">
									<label>Pin Code<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="pinCode" name="pinCode" value="${dto.getString('pinCode','') }" />
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<c:choose>  
							    <c:when test="${not empty dto.getString('photoEntryId','') and not empty photoFile}">  
									<div class="col-md-6 fileDisplayDiv">
										<div class="form-group">
											<label class="d-block">Photo<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="rounded border align-items-center d-flex dataView justify-content-between p-2 pr-0" style="height: 45px;margin-top: 5px;">
												<a href="${photoFile.getString('filePreviewUrl','') }" target="_blank">${photoFile.getString('fileName','') }</a>
												<button type="button" class="bg-danger border-0 text-white rounded" onclick="cloneFileUploadDiv('photoEntryAppendingDiv',this)"><i class="bi bi-x"></i></button>
											</div>
										</div>
									</div>
									<div id="photoEntryAppendingDiv"></div>
							    </c:when>   
							    <c:otherwise>  
									<div class="col-md-6">
										<div class="form-group">
											<label>Photo<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="photoFile" name="photoFile">
												<label class="custom-file-label" for="photoFile">Choose File</label>
											</div>
										</div>
									</div>
							    </c:otherwise>  
							</c:choose> 
							
							<c:choose>  
							    <c:when test="${not empty dto.getString('nationalityCertificateEntryId','') and not empty nationalityCertificateFile}">  
									<div class="col-md-6 fileDisplayDiv">
										<div class="form-group">
											<label class="d-block">Nationality Certificate<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="rounded border align-items-center d-flex dataView justify-content-between p-2 pr-0" style="height: 45px;margin-top: 5px;">
												<a href="${nationalityCertificateFile.getString('filePreviewUrl','') }" target="_blank">${nationalityCertificateFile.getString('fileName','') }</a>
												<button type="button" class="bg-danger border-0 text-white rounded" onclick="cloneFileUploadDiv('nationalityCertificateEntryAppendingDiv',this)"><i class="bi bi-x"></i></button>
											</div>
										</div>
									</div>
									<div id="nationalityCertificateEntryAppendingDiv"></div>
							    </c:when>   
							    <c:otherwise>  
									<div class="col-md-6">
										<div class="form-group">
											<label>Nationality Certificate<sup class="text-danger">*</sup></label>
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="nationalityCertificateFile" name="nationalityCertificateFile">
												<label class="custom-file-label" for="nationalityCertificateFile">Choose File</label>
											</div>
										</div>
									</div>
							    </c:otherwise>  
							</c:choose> 
						</div>
						
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="d-block">Disability Applicable<sup class="text-danger">*</sup></label>
									<input type="radio" class="mr-2" name="disabilityApplicable" id="disabilityApplicable" value="Yes" <c:if test = "${dto.getBoolean('disabilityApplicable',false) }">checked</c:if> />YES
									<input type="radio" class="ml-4 mr-2" name="disabilityApplicable" id="disabilityApplicable" value="No" <c:if test = "${!dto.getBoolean('disabilityApplicable',false) }">checked</c:if> />NO
								</div>
							</div>
							
							<c:choose>  
							    <c:when test="${not empty dto.getString('disabilityCertificateEntryId','') and not empty disabilityCertificateFile and dto.getBoolean('disabilityApplicable',false)}">  
									<div class="col-md-6 fileDisplayDiv">
										<div class="form-group">
											<label class="d-block">Disability Certificate<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="rounded border align-items-center d-flex dataView justify-content-between p-2 pr-0" style="height: 45px;margin-top: 5px;">
												<a href="${disabilityCertificateFile.getString('filePreviewUrl','') }" target="_blank">${disabilityCertificateFile.getString('fileName','') }</a>
												<button type="button" class="bg-danger border-0 text-white rounded" onclick="cloneFileUploadDiv('disabilityCertificateEntryAppendingDiv',this)"><i class="bi bi-x"></i></button>
											</div>
										</div>
									</div>
									<div id="disabilityCertificateEntryAppendingDiv"></div>
							    </c:when>   
							    <c:otherwise>  
									<div class="col-md-6 disabilityApplicableRelatedDivs" style="display: none;">
										<div class="form-group">
											<label>Disability Certificate<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="disabilityCertificateFile" name="disabilityCertificateFile">
												<label class="custom-file-label" for="disabilityCertificateFile">Choose File</label>
											</div>
										</div>
									</div>
							    </c:otherwise>  
							</c:choose> 
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Qualification<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="qualification" id="qualification" value="${dto.getString('qualification','') }"/>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label>Type of Job/Business<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="typeofJobBusiness" id="typeofJobBusiness" value="${dto.getString('typeofJobBusiness','') }"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Current Designation<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="Designation" id="Designation" value="${dto.getString('designation','') }"/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Office Address<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="officeAddress" id="officeAddress" value="${dto.getString('officeAddress','') }"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="d-block">Is any crime registered?<sup class="text-danger">*</sup></label>
									<input type="radio" class="mr-2" name="isAnyCrimeRegistered" id="isAnyCrimeRegistered" value="Yes" <c:if test = "${dto.getBoolean('anyCrimeRegistered',false) }">checked</c:if> />YES
									<input type="radio" class="ml-4 mr-2" name="isAnyCrimeRegistered" id="isAnyCrimeRegistered" value="No" <c:if test = "${!dto.getBoolean('anyCrimeRegistered',false) }">checked</c:if> />NO
								</div>
							</div>
							<div class="col-md-6 anyCrimeRegisteredRelatedDivs" style="<c:if test='${!dto.getBoolean("anyCrimeRegistered", false)}'>display: none;</c:if>"> 
								<div class="form-group">
									<label>Police Station Name<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="policeStationName" id="policeStationName" value="${dto.getString('policeStationName','') }"/>
								</div>
							</div>
						</div>
						<div class="row anyCrimeRegisteredRelatedDivs"  style="<c:if test='${!dto.getBoolean("anyCrimeRegistered", false)}'>display: none;</c:if>">
							<div class="col-md-6">
								<div class="form-group">
									<label>Police Station Address<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="policeStationAddress" id="policeStationAddress" value="${dto.getString('policeStationAddress','') }"/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Date of Complaint Registered<sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name="dateOfRegistration" id="dateOfRegistration" value="${dateOfRegistration }"/>
								</div>
							</div>
						</div>
						<div class="row">
							<c:choose>  
							    <c:when test="${not empty dto.getString('characterCertificateEntryId','') and not empty characterCertificateFile}">  
									<div class="col-md-6 fileDisplayDiv">
										<div class="form-group">
											<label class="d-block">Character Certificate<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="rounded border align-items-center d-flex dataView justify-content-between p-2 pr-0" style="height: 45px;margin-top: 5px;">
												<a href="${characterCertificateFile.getString('filePreviewUrl','') }" target="_blank">${characterCertificateFile.getString('fileName','') }</a>
												<button type="button" class="bg-danger border-0 text-white rounded" onclick="cloneFileUploadDiv('characterCertificateEntryAppendingDiv',this)"><i class="bi bi-x"></i></button>
											</div>
										</div>
									</div>
									<div id="characterCertificateEntryAppendingDiv"></div>
							    </c:when>   
							    <c:otherwise>  
									<div class="col-md-6">
										<div class="form-group">
											<label>Character Certificate<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="characterCertificateFile" name="characterCertificateFile">
												<label class="custom-file-label" for="characterCertificateFile">Choose File</label>
											</div>
										</div>
									</div>
							    </c:otherwise>  
							</c:choose> 
							
							<div class="col-md-6">
								<div class="form-group">
									<label>Bank Name<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="bankName" id="bankName" value="${dto.getString('bankName','') }"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Branch Name<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="branchName" id="branchName" value="${dto.getString('branchName','') }"/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Account Number<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="accountNumber" id="accountNumber" value="${dto.getString('accountNumber','') }"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>IFSC Code<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="IFSCcode" id="IFSCcode" value="${dto.getString('ifsccode','') }"/>
								</div>
							</div>
							<c:choose>  
							    <c:when test="${not empty dto.getString('cancelledChequeEntryId','') and not empty cancelledChequeFile}">  
									<div class="col-md-6 fileDisplayDiv">
										<div class="form-group">
											<label class="d-block">Cancelled Cheque<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="rounded border align-items-center d-flex dataView justify-content-between p-2 pr-0" style="height: 45px;margin-top: 5px;">
												<a href="${cancelledChequeFile.getString('filePreviewUrl','') }" target="_blank">${cancelledChequeFile.getString('fileName','') }</a>
												<button type="button" class="bg-danger border-0 text-white rounded" onclick="cloneFileUploadDiv('cancelledChequeFilentryAppendingDiv',this)"><i class="bi bi-x"></i></button>
											</div>
										</div>
									</div>
									<div id="cancelledChequeFilentryAppendingDiv"></div>
							    </c:when>   
							    <c:otherwise>  
									<div class="col-md-6">
										<div class="form-group">
											<label>Cancelled Cheque<sup class="text-danger">*</sup><liferay-ui:icon-help message="Accepted file formats: PDF, PNG, JPG, and JPEG. The file size must not exceed 2MB."/></label>
											<div class="custom-file">
												<input type="file" class="custom-file-input" id="cancelledChequeFile" name="cancelledChequeFile">
												<label class="custom-file-label" for="cancelledChequeFile">Choose File</label>
											</div>
										</div>
									</div>
							    </c:otherwise>  
							</c:choose> 
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Role<sup class="text-danger">*</sup></label>
									<select name="roleName" id="roleName" class="form-control">
									 <option value="" >Select role</option>
										 <c:forEach var="name" items="${roleNames}">
										        <option value="${name}" <c:if test = "${dto.getString('roleName','') eq name }">selected</c:if>>${name}</option>
										    </c:forEach>
										</select>
								</div>
							</div>
							<div class="col-md-6" id="videoLinkDiv" <c:if test = "${dto.getString('roleName','') ne 'Adventure Person' }">style="display: none;"</c:if>>
								<div class="form-group">
									<label>Video Link<sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="videoLink" id="videoLink" value="${dto.getString('videoLink','') }"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-check form-check-inline mb-4">  
									<input type="checkbox" class="form-check-input" name="acknowledgement" id="acknowledgement" <c:if test = "${dto.getBoolean('acknowledgement',false) }">checked</c:if>>
									<label class="form-check-label" for="acknowledgement">I have reviewed the information provided and confirm that it is complete and accurate to the best of my knowledge.<sup class="text-danger">*</sup></label>
								</div>
							</div>
						</div>
						</div>
						</div>
						</div>
						<div class="card-footer bg-transparent text-right p-4">
							<button class="btn btn-primary" type="submit">Submit</button>
						</div>
					
				
				</form>
				</div>
			</div>
		</div>
	</div>
</div>



<script>

$(document).ready(function () {
	
	$.validator.addMethod("pincodeIN", function (value, element) {
	    return this.optional(element) || /^[1-9][0-9]{5}$/.test(value);
	}, "<liferay-ui:message key='please-enter-valid-pincode' />");
	
	$.validator.addMethod("bankankAccount", function (value, element) {
	    return this.optional(element) || /^[0-9]{9,18}$/.test(value);
	}, "<liferay-ui:message key='please-enter-valid-bank-account-number' />");

	$.validator.addMethod("ifscCode", function (value, element) {
	    return this.optional(element) || /^[A-Z]{4}0[A-Z0-9]{6}$/.test(value);
	}, "<liferay-ui:message key='please-enter-valid-ifsc-code' />");

	$.validator.addMethod("youtubeLink", function (value, element) {
	    if (this.optional(element)) return true;
	    const regex = /^(https?:\/\/)?(www\.)?(youtube\.com\/watch\?v=|youtu\.be\/)[\w-]{11}$/;
	    return regex.test(value.trim());
	}, "<liferay-ui:message key='please-enter-valid-youtube-link' />");
	
    $.validator.addMethod("pdfOnly", function (value, element) {
        if (element.files.length === 0) return true; 
        const fileName = element.files[0].name;
        const ext = fileName.split('.').pop().toLowerCase();
        return ext === "pdf";
    }, "<liferay-ui:message key='allowed-only-pdf-file' />");
    
    $.validator.addMethod("imageOnly", function (value, element) {
        if (element.files.length === 0) return true; 
        const fileName = element.files[0].name;
        const ext = fileName.split('.').pop().toLowerCase();
        return ["jpg", "jpeg", "png", "bmp"].includes(ext);
    }, "<liferay-ui:message key='allowed-only-image-file' />");

    $.validator.addMethod("maxFileSize", function (value, element) {
        if (element.files.length === 0) return true;
        return element.files[0].size <= 2 * 1024 * 1024; 
    }, "<liferay-ui:message key='file-size-limit' />");

    $.validator.addMethod("startEndSpace", function(value, element) {
        return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
    }, "<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");
    
    $.validator.addMethod("validateName", function(value, element) {
        return this.optional(element) || /^[A-Za-z]+(?: [A-Za-z]+)*$/.test(value);
    }, "<liferay-ui:message key='only-alphabets-and-space-are-allowed' />");
    
    $.validator.addMethod("validAddress", function (value, element) {
        value = $.trim(value); 
        return this.optional(element) || (/^(?!.*\s{2,})[a-zA-Z0-9\s,.\-/#]{3,250}$/.test(value));
    }, "<liferay-ui:message key='please-enter-valid-address' />");

    $.validator.addMethod("validateEmail", function(value, element) {
        const regex =  /^(?!.*\.\.)(?!.*__)(?!.*[._][._])(?![_\.])[a-zA-Z0-9._]*[a-zA-Z][a-zA-Z0-9._]*(?<![_\.])@[a-zA-Z0-9][a-zA-Z0-9-]*\.[a-zA-Z]{2,}$/;
        return this.optional(element) || regex.test(value);
    }, "<liferay-ui:message key='please-enter-valid-email-address'/>");

    $.validator.addMethod("validateRemarks", function(value, element) {
        const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
        return this.optional(element) || regex.test(value);
    }, "<liferay-ui:message key='please-enter-valid-remarks' />");

    $.validator.addMethod("generalFieldsValidation", function(value, element) {
        const regex = /^(?!0+$)(?!.* {2})(?!.*(\s[\/-]|[\/-]\s))(?!.*([,.\-/])\2)[A-Za-z0-9]+(?:[ ]?[,\.]?[ ]?[A-Za-z0-9]+|[\/-][A-Za-z0-9]+)*$/i;
        return this.optional(element) || regex.test(value);
    }, "<liferay-ui:message key='only-letters-numbers-symbols-allowed' />");

    $.validator.addMethod("singleSpaceBetweenWords", function(value) {
        return !/ {2,}/.test(value);
    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');

    $.validator.addMethod("noConsecutiveSpecialChars", function(value, element) {
        var hasLetterOrDigit = /[A-Za-z0-9]/.test(value);
        var repeatedSpecials = /([.,\/\-])\1+/;
        var mixedSpecials = /[.,\/\-]{2,}/;
        if (hasLetterOrDigit && (repeatedSpecials.test(value) || mixedSpecials.test(value))) {
            return false;
        }
        return true;
    }, "<liferay-ui:message key='no-consecutive-special-characters-allowed' />");

    $.validator.addMethod("noSpaceAroundDashSlash", function (value, element) {
        const regex = /(\s[-]|[-]\s|\/\s|\s\/)/;
        return this.optional(element) || !regex.test(value);
    }, "<liferay-ui:message key='no-space-around-hyphen-or-slash-allowed' />");

    $.validator.addMethod("noStartEndSpecialChar", function (value, element) {
        return this.optional(element) || !/^[.,\/\-]|[.,\/\-]$/.test(value);
    }, "<liferay-ui:message key='should-not-start-or-end-with-special-characters' />");

    $('#profileInformationForm').validate({
        onkeyup: function (element) { $(element).valid(); },
        onchange: function (element) { $(element).valid(); },

        rules: {
            dateOfBirth: { required: true, },
            birthCertificateFile: { required: true, pdfOnly: true, maxFileSize: true, },
            coachingDateFrom: { required: true, },
            coachingDateTo: { required: true, },
            postalAddress: { required: true, startEndSpace: true, validAddress: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            pinCode: { required: true, startEndSpace: true, pincodeIN: true,},
            photoFile: { required: true, imageOnly: true, maxFileSize: true, },
            nationalityCertificateFile: { required: true, pdfOnly: true, maxFileSize: true, },
            disabilityApplicable: { required: true, },
            qualification: { required: true, startEndSpace: true, validateName: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            typeofJobBusiness: { required: true, startEndSpace: true, validateName: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true,},
            Designation: { required: true, startEndSpace: true, validateName: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            officeAddress: { required: true, startEndSpace: true, validAddress: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            
            disabilityApplicable: { required: true,  },
            disabilityCertificateFile: { required: true, pdfOnly: true, maxFileSize: true, },
            
            isAnyCrimeRegistered: { required: true, },
            policeStationName: { required: true, startEndSpace: true, validateName: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            policeStationAddress: { required: true, startEndSpace: true, validAddress: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            dateOfRegistration: { required: true, },
            characterCertificateFile: { required: true, pdfOnly: true, maxFileSize: true, },
            bankName: { required: true, startEndSpace: true, validateName: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            branchName: { required: true, startEndSpace: true, validateName: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            accountNumber: { required: true, startEndSpace: true, bankankAccount: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            IFSCcode: { required: true, startEndSpace: true, ifscCode: true, noStartEndSpecialChar: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, },
            cancelledChequeFile: { required: true, imageOnly: true, maxFileSize: true, },
            videoLink: { required: true, youtubeLink: true, },
            acknowledgement: { required: true, },
            roleName: { required: true, },
            
        },

        messages: {
            dateOfBirth: { required: "<liferay-ui:message key='please-enter-date-of-birth'/>" },
            birthCertificateFile: { required: "<liferay-ui:message key='please-upload-birth-certificate'/>" },
            coachingDateFrom: { required: "<liferay-ui:message key='please-select-coaching-date-from'/>" },
            coachingDateTo: { required: "<liferay-ui:message key='please-select-coaching-date-to'/>" },
            postalAddress: { required: "<liferay-ui:message key='please-enter-postal-address'/>" },
            pinCode: { required: "<liferay-ui:message key='please-enter-pincode'/>" },
            photoFile: { required: "<liferay-ui:message key='please-upload-photo'/>" },
            nationalityCertificateFile: { required: "<liferay-ui:message key='please-upload-nationality-certificate-file'/>" },
            disabilityApplicable: { required: "<liferay-ui:message key='please-select-disability-applicable'/>" },
            qualification: { required: "<liferay-ui:message key='please-enter-qualification'/>" },
            typeofJobBusiness: { required: "<liferay-ui:message key='please-enter-type-of-business'/>" },
            Designation: { required: "<liferay-ui:message key='please-enter-designation'/>" },
            officeAddress: { required: "<liferay-ui:message key='please-enter-office-address'/>" },
            
            disabilityApplicable: { required: "<liferay-ui:message key='please-select-disability-applicable'/>" },
            disabilityCertificateFile: { required: "<liferay-ui:message key='please-upload-disability-certificate'/>" },
            
            isAnyCrimeRegistered: { required: "<liferay-ui:message key='please-select-is-crime-registered'/>" },
            policeStationName: { required: "<liferay-ui:message key='please-enter-police-station-name'/>" },
            policeStationAddress: { required: "<liferay-ui:message key='please-enter-police-station-address'/>" },
            dateOfRegistration: { required: "<liferay-ui:message key='please-select-date-of-registration'/>" },
            characterCertificateFile: { required: "<liferay-ui:message key='please-upload-character-certificate'/>" },
            bankName: { required: "<liferay-ui:message key='please-enter-bank-name'/>" },
            branchName: { required: "<liferay-ui:message key='please-enter-branch-name'/>" },
            accountNumber: { required: "<liferay-ui:message key='please-enter-account-no'/>" },
            IFSCcode: { required: "<liferay-ui:message key='please-enter-ifsc-code'/>" },
            cancelledChequeFile: { required: "<liferay-ui:message key='please-upload-cancelled-cheque'/>" },
            videoLink: { required: "<liferay-ui:message key='please-enter-video-link'/>", },
            acknowledgement: { required: "<liferay-ui:message key='please-select-acknowledgement'/>" },
            roleName: { required: "<liferay-ui:message key='please-select-role'/>" },
            
        },
        
        errorPlacement: function(error, element) {
            if (element.attr("name") === "acknowledgement") {
                error.insertAfter(element.closest("div"));
            } else {
                error.insertAfter(element);
            }
        }
            
    });
    
});

function saveProfileDetails(event){
    if (event) event.preventDefault();
    if($('#profileInformationForm').valid()){
        console.log("Form Submission :::");
        $('#profileInformationForm')[0].submit();  
    }
}

function removeCoachingDatesRow(e){$(e).closest(".row").remove()}function cloneFileUploadDiv(e,i){var t=clonedFileUploadElement.clone();$("#"+e).addClass("col-md-6");var n=$("#"+e),r=t.find("#labelHead");"birthCertificateEntryAppendingDiv"===e?(r.find("sup.text-danger").before("Birth Certificate "),t.find(".custom-file-input").attr({id:"birthCertificateFile",name:"birthCertificateFile"})):"photoEntryAppendingDiv"===e?(r.find("sup.text-danger").before("Photo "),t.find(".custom-file-input").attr({id:"photoFile",name:"photoFile"})):"nationalityCertificateEntryAppendingDiv"===e?(r.find("sup.text-danger").before("Nationality Certificate "),t.find(".custom-file-input").attr({id:"nationalityCertificateFile",name:"nationalityCertificateFile"})):"disabilityCertificateEntryAppendingDiv"===e?(r.find("sup.text-danger").before("Disability Certificate "),t.find(".custom-file-input").attr({id:"disabilityCertificateFile",name:"disabilityCertificateFile"})):"characterCertificateEntryAppendingDiv"===e?(r.find("sup.text-danger").before("Character Certificate "),t.find(".custom-file-input").attr({id:"characterCertificateFile",name:"characterCertificateFile"})):"cancelledChequeFilentryAppendingDiv"===e&&(r.find("sup.text-danger").before("Cancelled Cheque "),t.find(".custom-file-input").attr({id:"cancelledChequeFile",name:"cancelledChequeFile"})),("birthCertificateEntryAppendingDiv"===e||"photoEntryAppendingDiv"===e||"nationalityCertificateEntryAppendingDiv"===e||"disabilityCertificateEntryAppendingDiv"===e||"characterCertificateEntryAppendingDiv"===e||"cancelledChequeFilentryAppendingDiv"===e)&&(n.append(t),n.find("#fileUploadEntryDiv").removeAttr("style"),n.find("#labelHead, .form-group").removeAttr("id"),n.removeAttr("id"),$(i).closest(".fileDisplayDiv").remove(),t.find(".custom-file-input").on("change",function(){var e=$(this).val().split("\\").pop();$(this).next(".custom-file-label").text(e)}))}$(document).ready(function(){let e=new Date;e.setDate(e.getDate()-1);let i=e.toISOString().split("T")[0];document.getElementById("dateOfBirth").setAttribute("max",i),$("#profileInformationForm").validate({rules:{dateOfBirth:{required:!0},birthCertificateFile:{required:!0,validateFile:!0,FileMaxSize:2e6},"coachingDateFrom[]":{required:!0},"coachingDateTo[]":{required:!0},postalAddress:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},pinCode:{required:!0,digits:!0,validPinNumber:!0,minlength:6,maxlength:6},nationalityCertificateFile:{required:!0,validateFile:!0,FileMaxSize:2e6},qualification:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},typeofJobBusiness:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},Designation:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},officeAddress:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},isAnyCrimeRegistered:{required:!0},characterCertificateFile:{required:!0,validateFile:!0,FileMaxSize:2e6},photoFile:{required:!0,validateFile:!0,FileMaxSize:2e6},bankName:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},branchName:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},accountNumber:{required:!0,digits:!0,validAccountNumber:!0,minlength:9,maxlength:16},IFSCcode:{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/},roleName:{required:!0},acknowledgement:{required:!0},cancelledChequeFile:{required:!0,validateFile:!0,FileMaxSize:2e6}}}),$.validator.addMethod("validAccountNumber",function(e,i){return this.optional(i)||/^[1-9][0-9]{8,15}$/.test(e)},"Enter a valid Account number. It cannot begin with 0."),$.validator.addMethod("validPinNumber",function(e,i){return this.optional(i)||/^[1-9][0-9]{0,5}$/.test(e)},"Enter a valid PIN number. It cannot begin with 0."),$.validator.addMethod("validateFile",function(e,i,t){var n=e.split(".").pop().toLowerCase();return -1!=$.inArray(n,["pdf","png","jpg","jpeg"])},"Only PDF, PNG, JPG, and JPEG files are allowed."),$.validator.addMethod("FileMaxSize",function(e,i,t){return!(i.files.length>0)||i.files[0].size<=t},"File size must not exceed 2MB."),$("#cloneCoachingDatesButton").on("click",function(){var e=$("#coachingDatesDiv").clone();e.find("input").val(""),e.find("sup").remove(),e.find("#cloneCoachingDatesButton").attr("id","").addClass("removeCoachingDatesButton").text("-").attr("onclick","removeCoachingDatesRow(this)"),$("#coachingDatesAppendingDiv").append(e)}),$("#roleName").on("change",function(){"Adventure Person"==this.value?($("#videoLinkDiv").show(),$("#videoLink").rules("add",{required:!0})):($("#videoLinkDiv").hide(),$("#videoLink").val(""),$("#videoLink").rules("remove"))})}),$(window).on("load",function(){$("#hamburger-6").click(),clonedFileUploadElement=$("#fileUploadEntryDiv").clone(),$("input[type=radio][name=isAnyCrimeRegistered]").change(function(){"Yes"==(selected_value=$("input[name='isAnyCrimeRegistered']:checked").val())?($(".anyCrimeRegisteredRelatedDivs").show(),$("#policeStationName").rules("add",{required:!0,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/}),$("#policeStationAddress").rules("add",{required:!0,minlength:2,maxlength:30,normalizer:function(e){return $.trim(e)},pattern:/^[^\s].*[^\s]$/}),$("#dateOfRegistration").rules("add",{required:!0})):($(".anyCrimeRegisteredRelatedDivs").hide(),$("#policeStationName").rules("remove"),$("#policeStationAddress").rules("remove"),$("#dateOfRegistration").rules("remove"),$(".anyCrimeRegisteredRelatedDivs").each(function(){$(this).find("input").val("")}))}),$("input[type=radio][name=disabilityApplicable]").change(function(){"Yes"==(selected_value=$("input[name='disabilityApplicable']:checked").val())?($(".disabilityApplicableRelatedDivs").show(),$("#disabilityCertificateFile").rules("add",{required:!0,validateFile:!0,FileMaxSize:2e6})):($(".disabilityApplicableRelatedDivs").hide(),$("#disabilityCertificateFile").rules("remove"),$(".disabilityApplicableRelatedDivs").each(function(){$(this).find('input[type="file"]').each(function(e){$(this).val(""),$('label[for="disabilityCertificateFile"]').text("Choose File")})}))})});

</script>