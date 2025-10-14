<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ include file="/init.jsp" %>


<style>

#noEnquiryCertificateError, #noDuesCertificateError, #complaintDetailsError, #confidentialReportsError, #awardCertificationOfHonorError, #languageExamCertificateError, #treasuryVerificationError, #permanencyCertificateError{
    position: absolute;
    width: 100%;
    left: 0;
    bottom: -27px;
    font-size: 12px;
}

</style>

			<div class="service_details">
					<div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="service-details"/>
                                </div>
                                <div class="card-body">
						<input type="hidden" id="serviceDetailId" name="serviceDetailId" value="${serviceDetails.serviceDetailsId}"/>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="nominations"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="nominations" id="nominations" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.nominations}" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="promotion-exam"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="promotionExam" id="promotionExam" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.promotionExam}"/>
									
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="date-of-appointment"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="dateOfAppointment" id="dateOfAppointment"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.dateOfAppointmentStr}"/>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="date-of-retirement"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="dateOfRetirement" id="dateOfRetirement"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.dateOfRetirementStr}"/>
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="probation-period"/></label>
									<input type="text" class="form-control" name="probationPeriod" id="probationPeriod" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.probationPeriod}"/>
									
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="entry-level-departmentExam"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="entryLevelDepartmentExam" id="entryLevelDepartmentExam" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.entryLevelDepartmentExam}"/>
									
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="regular-promotion-details"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="regularPromotionDetails" id="regularPromotionDetails" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.regularPromotionDetails}"/>
									
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="promotion-timeScale"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="promotionTimeScale" id="promotionTimeScale" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.promotionTimeScale}"/>
									
								</div>
							</div>
							</div>
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="regular-pay-fixation"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="regularPayFixation" id="regularPayFixation" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.regularPayFixation}"/>
									
								</div>
							</div>
							
							 <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="select-retirement-type"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="retirementType" id="retirementType" <c:if test="${mode eq 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<option value="1" <c:if test="${serviceDetails.retirementType eq '1'}">selected</c:if>><liferay-ui:message key="yes"/></option>
                                       <option value="0" <c:if test="${serviceDetails.retirementType eq '0'}">selected</c:if>><liferay-ui:message key="no"/></option>

									</select>
								</div>
							</div>
							</div>
							</div>
							</div>
							
							<!-- Medical Records -->
							
							<div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="medical-verification"/>
                                </div>
                                <div class="card-body">
							<div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="medical-certificate-date"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="medicalCertificateDate" id="medicalCertificateDate"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.medicalCertificateDateStr}"/>
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div class="form-group">
									<label>
										<liferay-ui:message key="medical-certificate" />
										<sup class="text-danger">*</sup>
										<em class="bi bi-info-circle-fill"
											title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
									</label>
							
									<!-- File Input (shared for add/edit) -->
									<c:if test="${mode ne 'view'}">
										<div class="custom-file">
											<input type="file" class="custom-file-input"
												id="medicalCertificate" name="medicalCertificate"
												onchange="handleFileUpload(event, 'medicalCertificate', 'medicalCertificatePreviewContainer', 'medicalCertificatePreviewLink', 'medicalCertificateDeleteButton')">
											<label class="custom-file-label" for="medicalCertificate">
												<liferay-ui:message key="choose-file" />
											</label>
										</div>
									</c:if>
							
									<!-- File Preview Container (shared for view/edit/add) -->
									<div class="ownerProofid mt-3 ${empty serviceDetails.medicalCertificateURL ? 'd-none' : 'd-flex'}"
										id="medicalCertificatePreviewContainer">
										<a class="medicalCertificateProofCls text-truncate"
											id="medicalCertificatePreviewLink"
											href="${empty serviceDetails.medicalCertificateURL ? '' : serviceDetails.medicalCertificateURL}"
											target="_blank"
											style="flex-grow: 1; text-decoration: none;">
											${empty serviceDetails.medicalCertificateName ? '' : serviceDetails.medicalCertificateName}
										</a>
							
										<c:if test="${mode ne 'view'}">
											<button type="button" id="medicalCertificateDeleteButton"
												class="dltmedicalCertificateBtn close" aria-label="Close"
												onclick="deleteFile('medicalCertificatePreviewContainer', 'medicalCertificate')">
												<span aria-hidden="true" class="text-danger">
													<em class="bi bi-x-circle-fill"></em>
												</span>
											</button>
										</c:if>
									</div>
								</div>
							</div>
									</div>					
							
							</div>
							</div>
							
							<!-- Police Verification -->
							
						<div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="police-verification"/>
                                </div>
                                <div class="card-body">
							<div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="police-verification-date"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="policeVerificationDate" id="policeVerificationDate"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.policeVerificationDateStr}"/>
								</div>
							</div>
							
						
						<div class="col-md-6">
						<div class="form-group">
							<label>
								<liferay-ui:message key="police-verification" />
								<sup class="text-danger">*</sup>
								<em class="bi bi-info-circle-fill"
									title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
							</label>
					
							<!-- File Input (only for add/edit) -->
							<c:if test="${mode ne 'view'}">
								<div class="custom-file">
									<input type="file" class="custom-file-input"
										id="policeVerification" name="policeVerification"
										onchange="handleFileUpload(event, 'policeVerification', 'policeVerificationPreviewContainer', 'policeVerificationPreviewLink', 'policeVerificationDeleteButton')">
									<label class="custom-file-label" for="policeVerification">
										<liferay-ui:message key="choose-file" />
									</label>
								</div>
							</c:if>
					
							<!-- File Preview Container (shared for add/edit/view) -->
							<div class="ownerProofid mt-3 ${empty serviceDetails.policeVerificationURL ? 'd-none' : 'd-flex'}"
								id="policeVerificationPreviewContainer">
								<a class="policeVerificationProofCls text-truncate"
									id="policeVerificationPreviewLink"
									href="${empty serviceDetails.policeVerificationURL ? '' : serviceDetails.policeVerificationURL}"
									target="_blank"
									style="flex-grow: 1; text-decoration: none;">
									${empty serviceDetails.policeVerificationName ? '' : serviceDetails.policeVerificationName}
								</a>
					
								<c:if test="${mode ne 'view'}">
									<button type="button" id="policeVerificationDeleteButton"
										class="dltpoliceVerificationBtn close" aria-label="Close"
										onclick="deleteFile('policeVerificationPreviewContainer', 'policeVerification')">
										<span aria-hidden="true" class="text-danger">
											<em class="bi bi-x-circle-fill"></em>
										</span>
									</button>
								</c:if>
							</div>
						</div>
					</div>
							
							</div>
						</div>
						</div>
						
						
						<!-- Required Documents (Add More) -->
						
						<div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="required-documents"/>
                                </div>
                                <div class="card-body">
							   <div class="row">

							
							<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="permanency-certificate" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="permanency-Certificate" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input permanencyCertificate" id="permanencyCertificate"
									                    name="permanencyCertificatePhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'permanencyCertificate', 'permanencyCertificatePreviewContainer', 'permanencyCertificatePreviewList', 'permanencyCertificateError', 'certificate')">
									                <label class="custom-file-label" for="permanencyCertificate">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="permanencyCertificateError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="certificate" name="certificate" 
									                value='<c:if test="${not empty serviceDetails.permanencyCertificateNames}"><c:forEach var="photoName" items="${serviceDetails.permanencyCertificateNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="permanencyCertificatePreviewContainer" 
									                style='<c:if test="${empty serviceDetails.permanencyCertificateURLs}">display: none;</c:if>'>
									                <ul id="permanencyCertificatePreviewList" name="permanencyCertificatePreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.permanencyCertificateURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.permanencyCertificateURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.permanencyCertificateNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'permanencyCertificatePreviewContainer', 'permanencyCertificatePreviewList', 'permanencyCertificateError', 'certificate', 'permanencyCertificate')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.permanencyCertificateURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.permanencyCertificateURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.permanencyCertificateNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
									</div>
							

							
							<!-- Treasury Verification -->
							
						<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="treasury-verification" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="treasury-verification" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input treasuryVerification" id="treasuryVerification"
									                    name="treasuryVerificationPhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'treasuryVerification', 'treasuryVerificationPreviewContainer', 'treasuryVerificationPreviewList', 'treasuryVerificationError', 'treasury')">
									                <label class="custom-file-label" for="treasuryVerification">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="treasuryVerificationError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="treasury" name="treasury" 
									                value='<c:if test="${not empty serviceDetails.treasuryVerificationNames}"><c:forEach var="photoName" items="${serviceDetails.treasuryVerificationNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="treasuryVerificationPreviewContainer" 
									                style='<c:if test="${empty serviceDetails.treasuryVerificationURLs}">display: none;</c:if>'>
									                <ul id="treasuryVerificationPreviewList" name="treasuryVerificationPreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.treasuryVerificationURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.treasuryVerificationURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.treasuryVerificationNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'treasuryVerificationPreviewContainer', 'treasuryVerificationPreviewList', 'treasuryVerificationError', 'treasury', 'treasuryVerification')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.treasuryVerificationURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.treasuryVerificationURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.treasuryVerificationNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
						</div>
						
						
						

						</div>
						
						 <div class="row">
						 
						 
						  <!-- Language Exam Certificate -->
							    
							<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="language-exam-certificate" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="language-exam-certificate" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input languageExamCertificate" id="languageExamCertificate"
									                    name="languageExamCertificatePhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'languageExamCertificate', 'languageExamCertificatePreviewContainer', 'languageExamCertificatePreviewList', 'languageExamCertificateError', 'languageExam')">
									                <label class="custom-file-label" for="languageExamCertificate">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="languageExamCertificateError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="languageExam" name="languageExam" 
									                value='<c:if test="${not empty serviceDetails.languageExamCertificateNames}"><c:forEach var="photoName" items="${serviceDetails.languageExamCertificateNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="languageExamCertificatePreviewContainer" 
									                style='<c:if test="${empty serviceDetails.languageExamCertificateURLs}">display: none;</c:if>'>
									                <ul id="languageExamCertificatePreviewList" name="languageExamCertificatePreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.languageExamCertificateURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.languageExamCertificateURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.languageExamCertificateNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'languageExamCertificatePreviewContainer', 'languageExamCertificatePreviewList', 'languageExamCertificateError', 'languageExam', 'languageExamCertificate')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.languageExamCertificateURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.languageExamCertificateURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.languageExamCertificateNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
									</div>
							
							

							
							<!-- Award Certification Of Honor -->
							
					<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="award-certification-of-honor" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="award-certification-of-honor" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input awardCertificationOfHonor" id="awardCertificationOfHonor"
									                    name="awardCertificationOfHonorPhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'awardCertificationOfHonor', 'awardCertificationOfHonorPreviewContainer', 'awardCertificationOfHonorPreviewList', 'awardCertificationOfHonorError', 'awardCertification')">
									                <label class="custom-file-label" for="permanencyCertificate">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="awardCertificationOfHonorError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="awardCertification" name="awardCertification" 
									                value='<c:if test="${not empty serviceDetails.awardCertificationOfHonorNames}"><c:forEach var="photoName" items="${serviceDetails.awardCertificationOfHonorNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="awardCertificationOfHonorPreviewContainer" 
									                style='<c:if test="${empty serviceDetails.awardCertificationOfHonorURLs}">display: none;</c:if>'>
									                <ul id="awardCertificationOfHonorPreviewList" name="awardCertificationOfHonorPreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.awardCertificationOfHonorURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.awardCertificationOfHonorURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.awardCertificationOfHonorNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'awardCertificationOfHonorPreviewContainer', 'awardCertificationOfHonorPreviewList', 'awardCertificationOfHonorError', 'awardCertification', 'awardCertificationOfHonor')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.awardCertificationOfHonorURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.awardCertificationOfHonorURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.awardCertificationOfHonorNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
									</div>
					
					

						   
						 </div>
						 
						 <div class="row">
						 
						  <!-- Confidential Reports -->
							    
							<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="confidential-reports" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="confidential-reports" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input confidentialReports" id="confidentialReports"
									                    name="confidentialReportsPhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'confidentialReports', 'confidentialReportsPreviewContainer', 'confidentialReportsPreviewList', 'confidentialReportsError', 'confidential')">
									                <label class="custom-file-label" for="confidentialReports">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="confidentialReportsError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="confidential" name="confidential" 
									                value='<c:if test="${not empty serviceDetails.confidentialReportsNames}"><c:forEach var="photoName" items="${serviceDetails.confidentialReportsNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="confidentialReportsPreviewContainer" 
									                style='<c:if test="${empty serviceDetails.confidentialReportsURLs}">display: none;</c:if>'>
									                <ul id="confidentialReportsPreviewList" name="confidentialReportsPreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.confidentialReportsURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.confidentialReportsURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.confidentialReportsNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'confidentialReportsPreviewContainer', 'confidentialReportsPreviewList', 'confidentialReportsError', 'confidential', 'confidentialReports')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.confidentialReportsURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.confidentialReportsURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.confidentialReportsNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
									</div>
							
							

							
							<!-- Matta And Dayitwa Received -->
								<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="select-matta-and-dayitwa-received"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="mattaAndDayitwaReceived" id="mattaAndDayitwaReceived">
										<option value=""><liferay-ui:message key="select"/></option>
										<option value="Yes" <c:if test="${serviceDetails.mattaAndDayitwaReceived eq 'Yes'}">selected</c:if> ><liferay-ui:message key="yes"/></option>
										<option value="No" <c:if test="${serviceDetails.mattaAndDayitwaReceived eq 'No'}">selected</c:if> ><liferay-ui:message key="no"/></option>
									</select>
								</div>
							</div>
						 
						 </div>
						 
						 <div class="row">
						 
						  <!-- Complaint Details -->
							    
						<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="complaint-details" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="complaint-details" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input complaintDetails" id="complaintDetails"
									                    name="complaintDetailsPhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'complaintDetails', 'complaintDetailsPreviewContainer', 'complaintDetailsPreviewList', 'complaintDetailsError', 'complaint')">
									                <label class="custom-file-label" for="complaintDetails">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="complaintDetailsError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="complaint" name="complaint" 
									                value='<c:if test="${not empty serviceDetails.complaintDetailsNames}"><c:forEach var="photoName" items="${serviceDetails.complaintDetailsNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="complaintDetailsPreviewContainer" 
									                style='<c:if test="${empty serviceDetails.complaintDetailsURLs}">display: none;</c:if>'>
									                <ul id="complaintDetailsPreviewList" name="complaintDetailsPreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.complaintDetailsURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.complaintDetailsURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.complaintDetailsNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'complaintDetailsPreviewContainer', 'complaintDetailsPreviewList', 'complaintDetailsError', 'complaint', 'complaintDetails')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.complaintDetailsURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.complaintDetailsURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.complaintDetailsNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
									</div>
						
						
						
						
						

							
							<!-- No Dues Certificate -->
							
						<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="no-dues-certificate" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="no-dues-certificate" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input noDuesCertificate" id="noDuesCertificate"
									                    name="noDuesCertificatePhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'noDuesCertificate', 'noDuesCertificatePreviewContainer', 'noDuesCertificatePreviewList', 'noDuesCertificateError', 'noDues')">
									                <label class="custom-file-label" for="noDuesCertificate">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="noDuesCertificateError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="noDues" name="noDues" 
									                value='<c:if test="${not empty serviceDetails.noDuesCertificateNames}"><c:forEach var="photoName" items="${serviceDetails.noDuesCertificateNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="noDuesCertificatePreviewContainer" 
									                style='<c:if test="${empty serviceDetails.noDuesCertificateURLs}">display: none;</c:if>'>
									                <ul id="noDuesCertificatePreviewList" name="noDuesCertificatePreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.noDuesCertificateURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.noDuesCertificateURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.noDuesCertificateNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'noDuesCertificatePreviewContainer', 'noDuesCertificatePreviewList', 'noDuesCertificateError', 'noDues', 'noDuesCertificate')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.noDuesCertificateURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.noDuesCertificateURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.noDuesCertificateNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
									</div>
						
						
						
						
						
						

						 
						 </div>
						 
						 <div class="row">
						  <!-- No-enquiry-certificate -->
							    
							<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit'}">
									    	<label><liferay-ui:message key="no-enquiry-certificate" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />"></em></label>
									    </c:if>
									    <c:if test="${mode eq 'view'}">
									    	<label><liferay-ui:message key="no-enquiry-certificate" /><sup class="text-danger">*</sup></label>
									    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input noEnquiryCertificate" id="noEnquiryCertificate"
									                    name="noEnquiryCertificatePhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'noEnquiryCertificate', 'noEnquiryCertificatePreviewContainer', 'noEnquiryCertificatePreviewList', 'noEnquiryCertificateError', 'noEnquiry')">
									                <label class="custom-file-label" for="noEnquiryCertificate">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="noEnquiryCertificateError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="noEnquiry" name="noEnquiry" 
									                value='<c:if test="${not empty serviceDetails.noEnquiryCertificateNames}"><c:forEach var="photoName" items="${serviceDetails.noEnquiryCertificateNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="noEnquiryCertificatePreviewContainer" 
									                style='<c:if test="${empty serviceDetails.noEnquiryCertificateURLs}">display: none;</c:if>'>
									                <ul id="noEnquiryCertificatePreviewList" name="noEnquiryCertificatePreviewList" class="list-group">
									                    <c:if test="${not empty serviceDetails.noEnquiryCertificateURLs}">
									                        <c:forEach var="photoURL" items="${serviceDetails.noEnquiryCertificateURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${serviceDetails.noEnquiryCertificateNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'noEnquiryCertificatePreviewContainer', 'noEnquiryCertificatePreviewList', 'noEnquiryCertificateError', 'noEnquiry', 'noEnquiryCertificate')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <%-- <c:if test="${mode eq 'view' and not empty serviceDetails.noEnquiryCertificateURLs}">
									            <div>
									                <c:forEach var="photoURL" items="${serviceDetails.noEnquiryCertificateURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${serviceDetails.noEnquiryCertificateNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if> --%>
									    </div>
									</div>
							
							
							
							
							
						 </div>
						 </div>
					  </div>
					  
					  
					  <!-- Add More -->
					  
					  
					  
					 <!--  Suspension Details -->
					  <div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="suspension-details"/>
                                </div>
                                <div class="card-body">
							   <div class="row">
							   <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="date-of-suspension"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="dateOfSuspension" id="dateOfSuspension"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.dateOfSuspensionStr}"/>
								</div>
							    </div>
							    
							    <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="reason-of-suspension"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="reasonOfSuspension" id="reasonOfSuspension" /
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.reasonOfSuspension}">
									
								</div>
							   </div>
							   
							   </div>
							   
							    <div class="row">
							    <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="date-of-reinstate"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="dateOfReinstate" id="dateOfReinstate"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.dateOfReinstateStr}"/>
								</div>
							    </div>
							    
							   <!--  Departmental Enquiry Report -->
								
								<div class="col-md-6">
									<div class="form-group">
										<label>
											<liferay-ui:message key="departmental-enquiry-report" />
											<sup class="text-danger">*</sup>
											<em class="bi bi-info-circle-fill"
												title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
										</label>
								
										<!-- File Input (only for add/edit) -->
										<c:if test="${mode ne 'view'}">
											<div class="custom-file">
												<input type="file" class="custom-file-input"
													id="departmentalEnquiryReport" name="departmentalEnquiryReport"
													onchange="handleFileUpload(event, 'departmentalEnquiryReport', 'departmentalEnquiryReportPreviewContainer', 'departmentalEnquiryReportPreviewLink', 'departmentalEnquiryReportDeleteButton')">
												<label class="custom-file-label" for="departmentalEnquiryReport">
													<liferay-ui:message key="choose-file" />
												</label>
											</div>
										</c:if>
								
										<!-- File Preview Container (shared for add/edit/view) -->
										<div class="ownerProofid mt-3 ${empty serviceDetails.departmentalEnquiryReportURL ? 'd-none' : 'd-flex'}"
											id="departmentalEnquiryReportPreviewContainer">
											<a class="departmentalEnquiryReportProofCls text-truncate"
												id="departmentalEnquiryReportPreviewLink"
												href="${empty serviceDetails.departmentalEnquiryReportURL ? '' : serviceDetails.departmentalEnquiryReportURL}"
												target="_blank"
												style="flex-grow: 1; text-decoration: none;">
												${empty serviceDetails.departmentalEnquiryReportName ? '' : serviceDetails.departmentalEnquiryReportName}
											</a>
								
											<c:if test="${mode ne 'view'}">
												<button type="button" id="departmentalEnquiryReportDeleteButton"
													class="dltdepartmentalEnquiryReportBtn close" aria-label="Close"
													onclick="deleteFile('departmentalEnquiryReportPreviewContainer', 'departmentalEnquiryReport')">
													<span aria-hidden="true" class="text-danger">
														<em class="bi bi-x-circle-fill"></em>
													</span>
												</button>
											</c:if>
										</div>
									</div>
								</div>

							    </div>
							    
							     <div class="row">
							     <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="punishment"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="punishment" id="punishment" 
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.punishment}"/>
								</div>
							    </div>
							     </div>
							   
					  </div>
					</div>
					
					<!-- Seniority Details -->
					<div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="seniority-details"/>
                                </div>
                                <div class="card-body">
                                <div class="row">
                                
								 <div class="col-md-6">
									<div class="form-group">
										<label>
											<liferay-ui:message key="seniority-list" />
											<sup class="text-danger">*</sup>
											<em class="bi bi-info-circle-fill"
												title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
										</label>
								
										<!-- File Input (only for add/edit) -->
										<c:if test="${mode ne 'view'}">
											<div class="custom-file">
												<input type="file" class="custom-file-input"
													id="seniorityList" name="seniorityList"
													onchange="handleFileUpload(event, 'seniorityList', 'seniorityListPreviewContainer', 'seniorityListPreviewLink', 'seniorityListDeleteButton')">
												<label class="custom-file-label" for="seniorityList">
													<liferay-ui:message key="choose-file" />
												</label>
											</div>
										</c:if>
								
										<!-- File Preview Container (shared for all modes) -->
										<div class="ownerProofid mt-3 ${empty serviceDetails.seniorityListURL ? 'd-none' : 'd-flex'}"
											id="seniorityListPreviewContainer">
											<a class="seniorityListProofCls text-truncate"
												id="seniorityListPreviewLink"
												href="${empty serviceDetails.seniorityListURL ? '' : serviceDetails.seniorityListURL}"
												target="_blank"
												style="flex-grow: 1; text-decoration: none;">
												${empty serviceDetails.seniorityListName ? '' : serviceDetails.seniorityListName}
											</a>
								
											<c:if test="${mode ne 'view'}">
												<button type="button" id="seniorityListDeleteButton"
													class="dltseniorityListBtn close" aria-label="Close"
													onclick="deleteFile('seniorityListPreviewContainer', 'seniorityList')">
													<span aria-hidden="true" class="text-danger">
														<em class="bi bi-x-circle-fill"></em>
													</span>
												</button>
											</c:if>
										</div>
									</div>
								</div>
 
                                </div>
                                </div>
                                
                    </div>
                    
                     <!-- Leave Details -->
                     <div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="seniority-details"/>
                                </div>
                                <div class="card-body">
                                <div class="row">
                                
                                <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="select-leave-type"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="leaveType" id="leaveType" <c:if test="${mode eq 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<option value="1" <c:if test="${serviceDetails.leaveType eq '1'}">selected</c:if>><liferay-ui:message key="yes"/></option>
                                        <option value="0" <c:if test="${serviceDetails.leaveType eq '0'}">selected</c:if>><liferay-ui:message key="no"/></option>

									</select>
								</div>
							</div>
							  <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="leave-from-date"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="leaveFromDate" id="leaveFromDate"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.leaveFromDateStr}"/>
								</div>
							    </div>
                                
                             </div>
                             
                             <div class="row">
							    
							     <div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="leave-to-date"/><sup class="text-danger">*</sup></label>
									<input type="date" class="form-control" name ="leaveToDate" id="leaveToDate"
									<c:if test="${mode eq 'view'}">disabled</c:if> value="${serviceDetails.leaveToDateStr}"/>
								</div>
							    </div>
							    
							
							<div class="col-md-6">
							<div class="form-group">
								<label>
									<liferay-ui:message key="leave-document" />
									<sup class="text-danger">*</sup>
									<em class="bi bi-info-circle-fill"
										title="<liferay-ui:message key='allowed-only-pdf-file' />"></em>
								</label>
						
								<!-- File Input (only for add/edit) -->
								<c:if test="${mode ne 'view'}">
									<div class="custom-file">
										<input type="file" class="custom-file-input"
											id="leaveDocument" name="leaveDocument"
											onchange="handleFileUpload(event, 'leaveDocument', 'leaveDocumentPreviewContainer', 'leaveDocumentPreviewLink', 'leaveDocumentDeleteButton')">
										<label class="custom-file-label" for="leaveDocument">
											<liferay-ui:message key="choose-file" />
										</label>
									</div>
								</c:if>
						
								<!-- File Preview Container (shared for all modes) -->
								<div class="ownerProofid mt-3 ${empty serviceDetails.leaveDocumentURL ? 'd-none' : 'd-flex'}"
									id="leaveDocumentPreviewContainer">
									<a class="leaveDocumentProofCls text-truncate"
										id="leaveDocumentPreviewLink"
										href="${empty serviceDetails.leaveDocumentURL ? '' : serviceDetails.leaveDocumentURL}"
										target="_blank"
										style="flex-grow: 1; text-decoration: none;">
										${empty serviceDetails.leaveDocumentName ? '' : serviceDetails.leaveDocumentName}
									</a>
						
									<c:if test="${mode ne 'view'}">
										<button type="button" id="leaveDocumentDeleteButton"
											class="dltleaveDocumentBtn close" aria-label="Close"
											onclick="deleteFile('leaveDocumentPreviewContainer', 'leaveDocument')">
											<span aria-hidden="true" class="text-danger">
												<em class="bi bi-x-circle-fill"></em>
											</span>
										</button>
									</c:if>
								</div>
							</div>
						</div>
										</div>			
							    
                             </div>
                             
                             
                      </div>          

	</div>