<%@ include file="/init.jsp" %>
<%@page import="com.mhdsys.application.certificate.verification.constants.MhdsysApplicationCertificateVerificationPortletKeys"%>

<style>
#certificateError{
    position: absolute;
    width: 100%;
    left: 0;
    bottom: -30px;
    font-size: 13px;
    color: red !important;
    font-family: Poppins-Meduim !important;
    padding: 0px 10px 0px 10px;
}
</style>


<portlet:resourceURL id="<%=MhdsysApplicationCertificateVerificationPortletKeys.SAVEAPPLICATIONCERTIFICATEVERIFICATIONMVCRESOURCECOMMAND %>" var="addApplicationCertificateVerificationURL" /> 

<portlet:resourceURL id="<%=MhdsysApplicationCertificateVerificationPortletKeys.VERIFYAPPLICATIONCERTIFICATEVERIFICATIONMVCRESOURCECOMMAND %>" var="VerifyApplicationCertificateVerificationURL" /> 







  <form id="certificate-verification" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="application-request-by-sports-person"/></h5>
                         <c:if test="${mode ne 'view'}">
                         <div><a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
                        </c:if>
                        <c:if test="${mode eq 'view' and isSportsPerson}">
                         <div><a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
                        </c:if>
                        <c:if test="${mode eq 'view' and isDDD}">
                         <div><a href="/group/guest/application-certificate-verification-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
                        </c:if>
                        <!-- <span class="text-danger font-weight-light">*Indicates Mandatory Fields</span> -->
                    </div>
                  
                        <div class="card-body">
                            <div class="card card-background p-0">
                                <div class="card-header header-card">
                                <liferay-ui:message key="personal-details"/>
                                </div>
       
                              
                                <div class="card-body">

                                    <!-- Row 1 -->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                             <input type="hidden" id="applicationCertificateVerificationId" name="applicationCertificateVerificationId" 
                                             value="${verificationCommonDTO.certificateVerificationId}" /> 
                                                <label> <liferay-ui:message key="first-name"/> <sup class="text-danger">*</sup></label>
                                                <input type="text" class="form-control" id="firstName" name="firstName"
                                                    placeholder=" <liferay-ui:message key="enter-first-name"/>"
                                                   <c:if test="${not empty verificationCommonDTO.firstName}">readonly</c:if> value="${verificationCommonDTO.firstName}" />
                                            </div>
                                        </div>

                                         <div class="col-md-6">
                                            <div class="form-group">
                                                <label> <liferay-ui:message key="last-name"/> <sup class="text-danger">*</sup></label>
                                                <input type="text" class="form-control" id="lastName" name="lastName"
                                                    placeholder=" <liferay-ui:message key="enter-last-name"/>"
                                                    value="${verificationCommonDTO.lastName}"  <c:if test="${not empty verificationCommonDTO.lastName}">readonly</c:if> />
                                            </div>
                                        </div>
                                        
                                      
                                    </div>

                                    <!-- Row 2 -->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label> <liferay-ui:message key="father-name"/> <sup class="text-danger">*</sup></label>
                                                <input type="text" class="form-control" id="fatherName" name="fatherName"
                                                    placeholder="<liferay-ui:message key="enter-father-name" />"
                                                    <c:if test="${not empty verificationCommonDTO.fathersName}">readonly</c:if>  value="${verificationCommonDTO.fathersName}"  />
                                            </div>
                                        </div>
                                         
                                         <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="mother-name" /><sup class="text-danger">*</sup></label>
                                                <input type="text" class="form-control" id="motherName" name="motherName"
                                                    placeholder="<liferay-ui:message key="enter-mother-name" />"
                                                    <c:if test="${not empty verificationCommonDTO.mothersName}">readonly</c:if>  value="${verificationCommonDTO.mothersName}"  />
                                            </div>
                                        </div>
                                       
                                    </div>

                                    <!-- Row 3 -->
                                    <div class="row">
                                       <div class="col-md-6">
									    <div class="form-group mb-3">
									        <label>
									            <liferay-ui:message key="gender" /> <sup class="text-danger">*</sup>
									        </label><br />
									        
									        <div class="form-check form-check-inline">
									            <input 
									                class="form-check-input" 
									                type="radio" 
									                name="genderRadio"   
									                value="1" 
									                id="genderMale"
									                <c:if test="${verificationCommonDTO.gender == 1}">checked</c:if>
									                <c:if test="${not empty verificationCommonDTO.gender}">disabled</c:if>
									            />
									            <label class="form-check-label" for="genderMale">
									                <liferay-ui:message key="male" />
									            </label>
									        </div>
									        
									        <div class="form-check form-check-inline">
									            <input 
									                class="form-check-input" 
									                type="radio" 
									                name="genderRadio"
									                value="2" 
									                id="genderFemale"
									                <c:if test="${verificationCommonDTO.gender == 2}">checked</c:if>
									                <c:if test="${not empty verificationCommonDTO.gender}">disabled</c:if>
									            />
									            <label class="form-check-label" for="genderFemale">
									                <liferay-ui:message key="female" />
									            </label>
									        </div>
									        
									        <!-- Hidden field to ensure value is submitted -->
									        <c:if test="${not empty verificationCommonDTO.gender}">
									            <input type="hidden" name="gender" value="${verificationCommonDTO.gender}" />
									        </c:if>
									        <c:if test="${empty verificationCommonDTO.gender}">
									            <!-- If no gender present, submit actual selected value -->
									            <input type="hidden" name="gender" id="genderHidden" />
									            <script>
									                document.querySelectorAll("input[name='genderRadio']").forEach((radio) => {
									                    radio.addEventListener("change", function () {
									                        document.getElementById("genderHidden").value = this.value;
									                    });
									                });
									            </script>
									        </c:if>
									    </div>
									</div>
									                                       

                                       
                                        <div class="col-md-6">
                                            <div class="form-group">
                   
                                                <label><liferay-ui:message key="date-of-birth" /><sup class="text-danger">*</sup></label>
                                                
                                                <input type="date" class="form-control" id="dob" name="dob" value="${verificationCommonDTO.dateOfBirthStr}" <c:if test="${not empty verificationCommonDTO.dateOfBirthStr}">readonly</c:if> />
                                                
                                                 
                                            </div>
                                        </div>
                                        
                                    </div>

                                    <!-- Row 4 -->
                                   <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="aadhaar-number" /><sup class="text-danger">*</sup></label>
                                                <input type="text" class="form-control" id="aadhaarNumber" name="aadhaarNumber"
                                                   placeholder="<liferay-ui:message key="enter-aadhaar-number" />"
                                                    <c:if test="${not empty verificationCommonDTO.aadharNumber}">readonly</c:if> value="${verificationCommonDTO.aadharNumber}"  />
                                            </div>
                                        </div>

                                      <div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="certificate" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="certificate" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input certificate" id="certificate"
									                    name="locationPhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'certificate', 'certificatePreviewContainer', 'certificatePreviewList', 'certificateError', 'hiddenCertificate')">
									                <label class="custom-file-label" for="certificate">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="certificateError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="hiddenCertificate" name="hiddenCertificate" 
									                value='<c:if test="${mode eq 'edit' and not empty verificationCommonDTO.certificateNames}"><c:forEach var="photoName" items="${verificationCommonDTO.certificateNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="certificatePreviewContainer" 
									                style='<c:if test="${mode ne 'edit' or empty verificationCommonDTO.certificateURLs}">display: none;</c:if>'>
									                <ul id="certificatePreviewList" name="certificatePreviewList" class="list-group">
									                    <c:if test="${mode eq 'edit' and not empty verificationCommonDTO.certificateURLs}">
									                        <c:forEach var="photoURL" items="${verificationCommonDTO.certificateURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${verificationCommonDTO.certificateNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'certificatePreviewContainer', 'certificatePreviewList', 'certificateError', 'hiddenCertificate', 'certificate')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <c:if test="${mode eq 'view' and not empty verificationCommonDTO.certificateURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${verificationCommonDTO.certificateURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${verificationCommonDTO.certificateNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if>
									    </div>
									</div>
                                    </div> 

                                  
                                </div>
                            </div>
                            
                            <c:if  test="${mode eq 'view' and (isDDD or not empty verificationCommonDTO.remarks)}">
                            <div class="card card-background p-0">
                                <div class="card-header header-card">
                                <liferay-ui:message key="review-by-divisional-deputy-director"/>
                                </div>
                                <div class="card-body">
                                  <!-- Row 5 -->
                                    <div class="row">
                                       <div class="col-md-6">
									    <div class="form-group">
									        <label>
									            <liferay-ui:message key="approval" />
									            <sup class="text-danger">*</sup>
									        </label><br />
									
									        <!-- Approve Radio -->
									        <div class="form-check form-check-inline">
									            <input class="form-check-input" type="radio" name="approveReject" value="true" id="approve"
									                <c:if test="${not empty verificationCommonDTO.remarks}">disabled</c:if>
									                <c:if test="${verificationCommonDTO.approveReject == 'true' || (empty verificationCommonDTO.remarks)}">checked</c:if> />
									            <label class="form-check-label" for="approve">
									                <liferay-ui:message key="approve" />
									            </label>
									        </div>
									
									        <!-- Reject Radio -->
									        <div class="form-check form-check-inline">
									            <input class="form-check-input" type="radio" name="approveReject" value="false" id="reject"
									                <c:if test="${not empty verificationCommonDTO.remarks}">disabled</c:if>
									                <c:if test="${verificationCommonDTO.approveReject == 'false' && not empty verificationCommonDTO.remarks}">checked</c:if> />
									            <label class="form-check-label" for="reject">
									                <liferay-ui:message key="reject" />
									            </label>
									        </div>
									    </div>
									</div>

                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="remarks" /> <sup class="text-danger">*</sup></label>
                                                <textarea class="form-control" id="remarks" name="remarks" rows="2" placeholder="<liferay-ui:message key="enter-remarks" />"
                                                    <c:if test="${not empty verificationCommonDTO.remarks}">disabled</c:if> >${verificationCommonDTO.remarks}</textarea>
                                            </div>
                                        </div>
                                    </div> 
                                
                                </div>
                                </div>
                                </c:if>
                                
                        </div>
                        
                        
                       <c:if test="${mode eq 'add' or (empty verificationCommonDTO.remarks and isDDD)}">
                        <div class="card-footer bg-transparent text-right p-4">
                            
                             <c:if test="${mode eq 'add'}">
                              <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                            </button>
                            </c:if>
                            
                            <c:if test="${mode eq 'view' and empty verificationCommonDTO.remarks and isDDD}">
                             <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                            </button>
                             <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
							<button type="button" class="btn btn-primary" onclick="VerifyCertificateVerification(event)">
						         <liferay-ui:message key="verify" />
						    </button>
							</c:if>
							
                            <c:if test="${mode ne 'view'}">
                             <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
							<button type="submit" class="btn btn-primary" onclick="saveCertificateVerification(event)">
						        <liferay-ui:message key="apply" />
						    </button>
						    </c:if>

						    

                           
                        </div>
                        </c:if>

               
                </div>
            </div>	
        </div>
    </div>
</div>
</form>


<!-- modal popup for establishment -->
<div class="modal fade" id="saveCertificateVerificationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="application-certificate-verification"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<%-- <img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup> --%>
                                    	<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
					     <c:if test="${isSportsPerson }">
					      <a href="/group/guest/dashboard" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveCertificateVerificationModal','/group/guest/dashboard')"><liferay-ui:message key="close"/></a>
					     </c:if>
       					 <c:if test="${isDDD }">
					      <a href="/group/guest/application-certificate-verification-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveCertificateVerificationModal','/group/guest/application-certificate-verification-list')"><liferay-ui:message key="close"/></a>
					     </c:if>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for establishment -->


<script>
var uploadedFilesCertificate = [];

function handleMultipleFileUpload(eventOrInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
    const fileInput = eventOrInput.target ? eventOrInput.target : eventOrInput;
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    // Only handle certificate uploads
    let uploadedFiles = uploadedFilesCertificate;

    const newFiles = Array.from(fileInput.files);
    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
    const totalFiles = activeFilesCount + newFiles.length;

    for (let file of newFiles) {
        const ext = file.name.split('.').pop().toLowerCase();

        if (uploadedFiles.some(f => f.name === file.name && !f.markedForDelete)) {
            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="this-file-is-already-uploaded" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        // Only allow PDF files
        if (!['pdf'].includes(ext)) {
            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="allowed-only-pdf-file" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        if (file.size >= 2 * 1024 * 1024) {
            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="file-size-limit" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }
    }

    errorSpan.textContent = "";
    errorSpan.style.display = "none";

    newFiles.forEach(file => {
        uploadedFiles.push({
            file: file,
            name: file.name,
            isExisting: false,
            markedForDelete: false
        });
    });

    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
    fileInput.value = "";
}

function removeFile(index, previewContainerId, previewListId, errorSpanId, hiddenInputId, inputId) {
    // Only handle certificate files
    let uploadedFiles = uploadedFilesCertificate;

    if (index < 0 || index >= uploadedFiles.length) {
        console.error("Invalid file index:", index);
        return;
    }

    if (uploadedFiles[index].isExisting) {
        uploadedFiles[index].markedForDelete = true;
    } else {
        uploadedFiles.splice(index, 1);
    }

    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    if (!previewContainer || !previewList || !errorSpan || !hiddenInput) {
        console.error("Required DOM elements not found");
        return;
    }

    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
    
    const hasActiveFiles = uploadedFiles.some(f => !f.markedForDelete);
    errorSpan.style.display = hasActiveFiles ? "none" : "block";
    errorSpan.innerHTML = hasActiveFiles ? "" : '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="please-upload-at-least-one-file" /></span>';
}

function renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput) {
    // Only handle certificate files
    let uploadedFiles = uploadedFilesCertificate;

    previewList.innerHTML = "";

    uploadedFiles.forEach((fileObj, index) => {
        if (fileObj.markedForDelete) return;

        const fileItem = document.createElement("li");
        fileItem.className = "list-group-item d-flex justify-content-between align-items-center";

        const fileLink = document.createElement("a");
        fileLink.href = fileObj.isExisting ? (fileObj.url || "#") : URL.createObjectURL(fileObj.file);
        fileLink.textContent = fileObj.name;
        fileLink.target = "_blank";
        fileLink.style.cssText = "flex-grow: 1; text-decoration: none; word-wrap: break-word; white-space: nowrap; overflow: hidden; max-width: 200px;";

        const deleteBtn = document.createElement("button");
        deleteBtn.type = "button";
        deleteBtn.className = "btn btn-danger btn-sm";
        deleteBtn.innerHTML = '<i class="bi bi-x-circle-fill"></i>';
        deleteBtn.onclick = () => removeFile(
            index,
            previewContainer.id,
            previewList.id,
            errorSpan.id,
            hiddenInput.id,
            inputId
        );

        fileItem.appendChild(fileLink);
        fileItem.appendChild(deleteBtn);
        previewList.appendChild(fileItem);
    });

    previewContainer.style.display = uploadedFiles.some(f => !f.markedForDelete) ? "block" : "none";
    
    hiddenInput.value = uploadedFiles
        .filter(f => !f.markedForDelete)
        .map(f => f.name)
        .join(',');
}




$(document).ready(function() {
	
	/*   var today = new Date();
	    var minAge = 10;
	    var maxDate = new Date(today.getFullYear() - minAge, today.getMonth(), today.getDate());
	    var yyyy = maxDate.getFullYear();
	    var mm = String(maxDate.getMonth() + 1).padStart(2, '0');
	    var dd = String(maxDate.getDate()).padStart(2, '0');
	    var formattedMaxDate = yyyy + '-' + mm + '-' + dd;

	    $('#dob').attr('max', formattedMaxDate); */

    $('#certificate-verification').validate({
        onkeyup: function(element) {
            $(element).valid();
        },
        onchange: function(element) {
            $(element).valid();
        },
       
        rules: {
            firstName: {
                required: true, 
                minlength: 3, 
                maxlength: 75,
                startEndSpace: true,
                validateName: true
            },
            lastName: {
                required: true, 
                minlength: 3, 
                maxlength: 75,
                startEndSpace: true,
                validateName: true
            },
            fatherName: {
                required: true, 
                minlength: 3, 
                maxlength: 75,
                startEndSpace: true,
                validateName: true
            },
            motherName: {
                required: true, 
                minlength: 3, 
                maxlength: 75,
                startEndSpace: true,
                validateName: true
            },
            gender: {
                required: true
            },
            dob: {
                required: true,
               /*  validateDOB: 10, */
            },
            aadhaarNumber: {
            	required:true,
            	notAllZeros:true,
            	 startEndSpace: true,
    			aadhaarValidation:true
    			
            },
            locationPhotos: {
                required: function(){
                    return uploadedFilesCertificate.length === 0 && $('#certificateError').text().trim() === "";
                }
            },
            approveReject: {
                required: true
            },
            remarks: {
                required: true,
                minlength: 3,
                validateRemarks:true,
                maxlength: 500
            }
        },
        
        messages: {
            firstName: {
                required: "<liferay-ui:message key='please-enter-first-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            lastName: {
                required: "<liferay-ui:message key='please-enter-last-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            fatherName: {
                required: "<liferay-ui:message key='please-enter-father-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            motherName: {
                required: "<liferay-ui:message key='please-enter-mother-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            gender: {
                required: "<liferay-ui:message key='please-select-gender' />"
            },
            dob: {
                required: "<liferay-ui:message key='please-enter-date-of-birth' />",
               
            },
            aadhaarNumber: {
                required: "<liferay-ui:message key='please-enter-aadhaar-number' />",
                minlength: "<liferay-ui:message key='aadhaar-number-must-be-12-digits' />",
                maxlength: "<liferay-ui:message key='aadhaar-number-must-be-12-digits' />",
                digits: "<liferay-ui:message key='only-digits-allowed' />"
            },
            locationPhotos: {
                required: "<liferay-ui:message key='please-upload-certificate' />"
            },
            approveReject: {
                required: "<liferay-ui:message key='please-select-approval-status' />"
            },
            remarks: {
                required: "<liferay-ui:message key='please-enter-remarks' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-500-characters-allowed' />"
            }
        },
        
        errorPlacement: function(error, element) {
            if (element.attr("type") == "radio") {
                error.insertAfter(element.parent().parent());
            } 
            else {
                error.insertAfter(element);
            }
        }
    });
    
    // Custom validation methods
    $.validator.addMethod("startEndSpace", function(value, element) {
        return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
    }, "<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");
    
    $.validator.addMethod("validateName", function(value, element) {
        return this.optional(element) || /^[A-Za-z]+(?: [A-Za-z]+)*$/.test(value);
    }, "<liferay-ui:message key='only-alphabets-and-space-are-allowed'/>");
    
    $.validator.addMethod("validateRemarks", function(value, element) {
   const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
    return this.optional(element) || regex.test(value);
}, "<liferay-ui:message key='please-enter-valid-remarks' />");
    
    $.validator.addMethod("aadhaarValidation", function(value, element) {
        const regex = /^(?:\d{12}|\d{4} \d{4} \d{4})$/;
        return this.optional(element) || regex.test(value.trim());
    },"<liferay-ui:message key="valid-aadhaar-msg"/>");
    
    $.validator.addMethod("notAllZeros", function(value) {
        // Remove all whitespace from the value before testing
        var cleanedValue = value.replace(/\s+/g, '');
        return !/^0+$/.test(cleanedValue);
    }, '<liferay-ui:message key="employee-id-cannot-be-all-zeros"/>');
    
    /* $.validator.addMethod("validateDOB", function (value, element, param) {
        if (!value) return false;

        var dob = new Date(value);
        var today = new Date();

        var age = today.getFullYear() - dob.getFullYear();
        var m = today.getMonth() - dob.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < dob.getDate())) {
            age--;
        }

        return age >= param;
    }, $.validator.format("<liferay-ui:message key='you-must-be-at-least' /> {0} <liferay-ui:message key='years-old' />")); */

});


function saveCertificateVerification(event) {
	debugger;
	    var form = $("#certificate-verification")[0];
	    var formData = new FormData(form);
	    
	  
	    
	    console.log(uploadedFilesCertificate)
	    uploadedFilesCertificate.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            formData.append("certificate", fileObj.file);
	            formData.append("certificateNames", fileObj.name);
	        }
	    });
	    
	   

	    
	    if (event) {
	        event.preventDefault();
	    }
      
	    if($('#certificate-verification').valid()){
	    $.ajax({
	        type: "POST",
	        url: '${addApplicationCertificateVerificationURL}', 
	        data:  formData,
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data) {
	            if (!data) {
	                $('#success-application').html("Empty response from server.");
	                $("#saveCertificateVerificationModal").modal('show');
	                return;
	            }

	            if (typeof data === 'string') {
	                try {
	                    data = JSON.parse(data);
	                    console.log(data)
	                } catch (e) {
	                    console.error("JSON parse error:", e);
	                    $('#success-application').html("Invalid JSON response from server.");
	                    $("#saveCertificateVerificationModal").modal('show');
	                    return;
	                }
	            }

	            var msg = data.status ? '<liferay-ui:message key="application-certificate-verification-id-"/>' + ' ' + data.certificateVerificationId +  ' ' + '<liferay-ui:message key="-is-succesfully-submitted" />' : 'the-details-failed-to-submit';
	            $('#success-application').html(msg);
	            $("#saveCertificateVerificationModal").modal('show');
	        }
	    });
	    }
}


function VerifyCertificateVerification(event) {
	debugger;
	   
	    var formData = new FormData();
	    formData.append("certificateVerificationId",$("#applicationCertificateVerificationId").val())
	    formData.append("approveReject",$('input[name="approveReject"]:checked').val())
	    formData.append("remarks",$("#remarks").val())

	    if (event) {
	        event.preventDefault();
	    }
     
	    if($('#certificate-verification').valid()){
	    $.ajax({
	        type: "POST",
	        url: '${VerifyApplicationCertificateVerificationURL}', 
	        data:  formData,
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data) {
	            if (!data) {
	                $('#success-application').html("Empty response from server.");
	                $("#saveCertificateVerificationModal").modal('show');
	                return;
	            }

	            if (typeof data === 'string') {
	                try {
	                    data = JSON.parse(data);
	                    console.log(data)
	                } catch (e) {
	                    console.error("JSON parse error:", e);
	                    $('#success-application').html("Invalid JSON response from server.");
	                    $("#saveCertificateVerificationModal").modal('show');
	                    return;
	                }
	            }

	            var msg = data.status ? '<liferay-ui:message key="application-certificate-verification-id-"/>' + ' ' + data.certificateVerificationId +  ' ' + '<liferay-ui:message key="-is-succesfully-verified" />'  : 'the-details-failed-to-submit';
	            $('#success-application').html(msg);
	            $("#saveCertificateVerificationModal").modal('show');
	        }
	    });
	    }
	    
}

function closeModal(id,redirectUrl) {debugger
    $("#"+id).modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
	 if (redirectUrl) {
	        window.location.href = redirectUrl;
	    }
}


</script>
