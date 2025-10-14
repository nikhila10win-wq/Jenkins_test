<%@page import="com.mhdys.establishment.constants.EstablishmentWebPortletKeys"%>
<%@ include file="/init.jsp" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.19.5/jquery.validate.min.js"></script>
 
<portlet:resourceURL id="<%=EstablishmentWebPortletKeys.SAVEESTABLISHMENTMVCRESOURCECOMMAND %>" var="addEstablichmentURL" />

<form id="establishment" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					
					<%-- <div class="card-header d-flex align-item-center justify-content-between">
						<h5><liferay-ui:message key="establishment"/></h5>		
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn" style=" background-color: #26268E; border-color: #fff; margin-top:-5px; "><i class="bi bi-arrow-left-circle"></i>
							 Back</a>
						</div>				
					</div> --%>
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="establishment" /></h5>
						<div><a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
					
					<div class="card-body">
					 <input type="hidden" id="establishmentDetailId" name="establishmentDetailId"value="${establishmentDetails.establishmentDetailId}" />
					 <input	type="hidden" id="personalDetailsId" name="personalDetailsId" value="${personalDetails.personalDetailsId}" />
					 <input	type="hidden" id="castCertificateFileEntryId" name="castCertificateFileEntryId" value="${personalDetails.castCertificate}" />
					 <input	type="hidden" id="castValidityFileEntryId" name="castValidityFileEntryId" value="${personalDetails.castValidity}" />
					 <input	type="hidden" id="specialAchievementsFileEntryId" name="specialAchievementsFileEntryId" value="${personalDetails.specialAchievements}" />
					 <input	type="hidden" id="sportsAchievementsFileEntryId" name="sportsAchievementsFileEntryId" value="${personalDetails.sportsAchievements}" />
					<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="select-category-record"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="typeOfRecord" id="typeOfRecord" <c:if test="${mode eq 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<option value="Personal Details" >
											<liferay-ui:message key="personal-details"/></option>
										<option value="Service Details" >
											<liferay-ui:message key="service-details"/></option>
										<option value="GPF Details" >
											<liferay-ui:message key="gpf-details"/></option>
										<option value="NPS Details" >
											<liferay-ui:message key="nps-details"/></option>
										<option value="Training Details" >
											<liferay-ui:message key="training-details"/></option>
										<option value="Posting Status" >
											<liferay-ui:message key="posting-status"/></option>
										<option value="Roaster Status" >
											<liferay-ui:message key="roaster-status"/></option>
									</select>
								</div>
							</div>
						</div>
							<div class="card card-background p-0 personal_details" style="display: none;">
								<div class="" >
									<div class="card-header header-card">
										<liferay-ui:message key="personal-details" />
									</div>

									<input type="hidden" id="establishmentDetailId"
										name="establishmentDetailId"
										value="${establishmentDetails.establishmentDetailId}" /> <input
										type="hidden" id="personalDetailsId" name="personalDetailsId"
										value="${personalDetails.personalDetailsId}" />
									<div class="card-body">



										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="employee-name" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="employeeName" id="employeeName"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.employeeName}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="category" /><sup
														class="text-danger">*</sup></label> <select class="form-control"
														name="category" id="category"
														<c:if test="${mode eq 'view'}">disabled</c:if>>
														<option value=""><liferay-ui:message key="select" /></option>
														<option value="Category 1"
															<c:if test="${personalDetails.category eq 'Category 1'}">selected</c:if>>Category
															1</option>
														<option value="Category 2"
															<c:if test="${personalDetails.category eq 'Category 2'}">selected</c:if>>Category
															2</option>
														<option value="Category 3"
															<c:if test="${personalDetails.category eq 'Category 3'}">selected</c:if>>Category
															3</option>
													</select>
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="sevarath-id" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="sevarthID" id="sevarthID"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.sevarthID}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="date-of-birth" /><sup
														class="text-danger">*</sup></label> <input type="date"
														class="form-control" name="dob" id="dob"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.dobStr}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="height" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="height" id="height"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.height == 0.0 ? '' : personalDetails.height}" />

												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="body-mark" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="bodyMark" id="bodyMark"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.bodyMark}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="education" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="education" id="education"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.education}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="contact-details" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="contactDetails"
														id="contactDetails"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.contactDetails==0? '':personalDetails.contactDetails}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="mscit" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="mscIT" id="mscIT"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.mscIT}" />
												</div>
											</div>
										</div>
</div>
</div>
</div>
                               <div class="card card-background p-0 personal_details" style="display: none;">
								<div class="">
										<div class="card-header header-card">
											<liferay-ui:message key="address-details" />
										</div>
										<div class="card-body">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="postal-address" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="postalAddress"
														id="postalAddress"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.postalAddress}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="hometown" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="hometown" id="hometown"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.hometown}" />
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label><liferay-ui:message key="permanenet-address" /><sup
														class="text-danger">*</sup></label> <input type="text"
														class="form-control" name="permanentAddress"
														id="permanentAddress"
														<c:if test="${mode eq 'view'}">disabled</c:if>
														value="${personalDetails.permanentAddress}" />
												</div>
											</div>
										</div>
										</div>
</div>
</div>
                                    <div class="card card-background p-0 personal_details" style="display: none;">
								<div class="">
										<div class="card-header header-card">
											<liferay-ui:message key="required-documents" />
										</div>
										<div class="card-body">
										<div class="row">

											<div class="col-md-6">
											<div class="form-group">
												<label>
													<liferay-ui:message key="cast-certificate" />
													<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
												</label>
											
												<!-- File Input (Shared for Add/Edit) -->
												<div class="custom-file">
													<input type="file" class="custom-file-input"
														id="castCertificate" name="castCertificate"
														onchange="handleFileUpload(event, 'castCertificate', 'castCertificatePreviewContainer', 'castCertificatePreviewLink', 'castCertificateDeleteButton')">
													<label class="custom-file-label" for="castCertificate">
														<liferay-ui:message key="choose-file" />
													</label>
												</div>
											
												<!-- File Preview Container (Shared for Add/Edit) -->
												<div class="ownerProofid mt-3 ${empty personalDetails.castCertificateURL ? 'd-none' : 'd-flex'}"
													id="castCertificatePreviewContainer">
													<a class="castCertificateProofCls text-truncate"
														id="castCertificatePreviewLink"
														href="${empty personalDetails.castCertificateURL ? '' : personalDetails.castCertificateURL}"
														target="_blank"
														style="flex-grow: 1; text-decoration: none;">
														${empty personalDetails.castCertificateName ? '' : personalDetails.castCertificateName}
													</a>
													<button type="button" id="castCertificateDeleteButton"
														class="dltcastCertificateBtn close" aria-label="Close"
														onclick="deleteFile('castCertificatePreviewContainer', 'castCertificate')">
															<span aria-hidden="true" class="text-danger">
																<em class="bi bi-x-circle-fill"></em>
															</span>
														</button>
													</div>
												</div>
											</div>
 

											<div class="col-md-6">
												<div class="form-group">
													<label>
														<liferay-ui:message key="cast-validity" />
														<sup class="text-danger">*</sup>
														<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
													</label>
											
													<!-- File Upload Input (Same for Add & Edit) -->
													<div class="custom-file">
														<input type="file" class="custom-file-input"
															id="castValidity" name="castValidity"
															onchange="handleFileUpload(event, 'castValidity', 'castValidityPreviewContainer', 'castValidityPreviewLink', 'castValidityDeleteButton')">
														<label class="custom-file-label" for="castValidity">
															<liferay-ui:message key="choose-file" />
														</label>
													</div>
											
													<!-- Preview Container (Shared for Add & Edit) -->
													<div class="ownerProofid mt-3 ${empty personalDetails.castValidityURL ? 'd-none' : 'd-flex'}"
														id="castValidityPreviewContainer">
														<a class="castValidityProofCls text-truncate" id="castValidityPreviewLink"
															href="${empty personalDetails.castValidityURL ? '' : personalDetails.castValidityURL}"
															target="_blank"
															style="flex-grow: 1; text-decoration: none;">
															${empty personalDetails.castValidityName ? '' : personalDetails.castValidityName}
														</a>
														<button type="button" id="castValidityDeleteButton"
															class="dltcastValidityBtn close" aria-label="Close"
															onclick="deleteFile('castValidityPreviewContainer', 'castValidity')">
															<span aria-hidden="true" class="text-danger">
																<em class="bi bi-x-circle-fill"></em>
															</span>
														</button>
													</div>
												</div>
											</div>
											
											
											 <div class="col-md-6">
												<div class="form-group">
													<label>
														<liferay-ui:message key="special-achievements" />
														<sup class="text-danger">*</sup>
														<em class="bi bi-info-circle-fill"
															title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
													</label>
											
													<!-- File Input (Shared for Add/Edit) -->
													<div class="custom-file">
														<input type="file" class="custom-file-input"
															id="specialAchievements" name="specialAchievements"
															onchange="handleFileUpload(event, 'specialAchievements', 'specialAchievementsPreviewContainer', 'specialAchievementsPreviewLink', 'specialAchievementsDeleteButton')">
														<label class="custom-file-label" for="specialAchievements">
															<liferay-ui:message key="choose-file" />
														</label>
													</div>
											
													<!-- File Preview Container (Shared for Add/Edit) -->
													<div class="ownerProofid mt-3 ${empty personalDetails.specialAchievementsURL ? 'd-none' : 'd-flex'}"
														id="specialAchievementsPreviewContainer">
														<a class="specialAchievementsProofCls text-truncate"
															id="specialAchievementsPreviewLink"
															href="${empty personalDetails.specialAchievementsURL ? '' : personalDetails.specialAchievementsURL}"
															target="_blank"
															style="flex-grow: 1; text-decoration: none;">
															${empty personalDetails.specialAchievementsName ? '' : personalDetails.specialAchievementsName}
														</a>
														<button type="button" id="specialAchievementsDeleteButton"
															class="dltspecialAchievementsBtn close" aria-label="Close"
															onclick="deleteFile('specialAchievementsPreviewContainer', 'specialAchievements')">
															<span aria-hidden="true" class="text-danger">
																<em class="bi bi-x-circle-fill"></em>
															</span>
														</button>
													</div>
												</div>
											</div>
											
										<div class="col-md-6">
											<div class="form-group">
												<label>
													<liferay-ui:message key="sports-achievements" />
													<sup class="text-danger">*</sup>
													<em class="bi bi-info-circle-fill"
														title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
												</label>
										
												<!-- File Input (shared for add/edit) -->
												<div class="custom-file">
													<input type="file" class="custom-file-input"
														id="sportsAchievements" name="sportsAchievements"
														onchange="handleFileUpload(event, 'sportsAchievements', 'sportsAchievementsPreviewContainer', 'sportsAchievementsPreviewLink', 'sportsAchievementsDeleteButton')">
													<label class="custom-file-label" for="sportsAchievements">
														<liferay-ui:message key="choose-file" />
													</label>
												</div>
										
												<!-- File Preview Container (shared for add/edit) -->
												<div class="ownerProofid mt-3 ${empty personalDetails.sportsAchievementsURL ? 'd-none' : 'd-flex'}"
													id="sportsAchievementsPreviewContainer">
													<a class="sportsAchievementsProofCls text-truncate"
														id="sportsAchievementsPreviewLink"
														href="${empty personalDetails.sportsAchievementsURL ? '' : personalDetails.sportsAchievementsURL}"
														target="_blank"
														style="flex-grow: 1; text-decoration: none;">
														${empty personalDetails.sportsAchievementsName ? '' : personalDetails.sportsAchievementsName}
													</a>
													<button type="button" id="sportsAchievementsDeleteButton"
														class="dltsportsAchievementsBtn close" aria-label="Close"
														onclick="deleteFile('sportsAchievementsPreviewContainer', 'sportsAchievements')">
														<span aria-hidden="true" class="text-danger">
															<em class="bi bi-x-circle-fill"></em>
														</span>
													</button>
												</div>
											</div>
										</div>
										</div>
										</div>
										</div>
									</div>
									
									
						<div class="service_details" style="display: none;">
							<jsp:include page="/jsps/service-details.jsp"></jsp:include>
						</div>
						
						<div class="gpf_details" style="display: none;">
							<jsp:include page="/jsps/gpf-details.jsp"></jsp:include>
						</div>
						
						<div class="nps_details" style="display: none;">
							<jsp:include page="/jsps/nps-details.jsp"></jsp:include>
						</div>
						
						<div class="training_details" style="display: none;">
							<jsp:include page="/jsps/training-details.jsp"></jsp:include>
						</div>
						
						<div class="posting_status" style="display: none;">
							<jsp:include page="/jsps/posting-status.jsp"></jsp:include>
						</div>
						
						<div class="roaster_status" style="display: none;">
							<jsp:include page="/jsps/roaster-status.jsp"></jsp:include>
						</div>
									
					</div>
							
						<c:if test="${mode eq 'add'}">
							<div class="card-footer bg-transparent text-right p-4">
								<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
				                    <liferay-ui:message key="cancel" />
				                </button>	
				                
				                <button class="btn btn-primary" type="button" onclick="location.reload();">
				                    <liferay-ui:message key="reset" />
				                </button>
								
								<%-- <button class="btn btn-primary" type="button" onclick="submitDetails(event)"><liferay-ui:message key="submit"/></button> --%>
								<button class="btn btn-primary submit-btn" type="button" onclick="submitDetails(event)"><liferay-ui:message key="submit"/></button>
									
								<button class="btn btn-primary update-btn" type="button" onclick="submitDetails(event)" style="display:none;">
							        <liferay-ui:message key="update"/>
							    </button>
							</div>
						</c:if>
						<c:if test="${mode eq 'edit'}">
							<div class="card-footer bg-transparent text-right p-4">
								<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
				                    <liferay-ui:message key="cancel" />
				                </button>	
				                
				                <button class="btn btn-primary" type="button" onclick="location.reload();">
				                    <liferay-ui:message key="reset" />
				                </button>
								
								<button class="btn btn-primary" type="button" onclick="submitDetails(event)"><liferay-ui:message key="update"/></button>
							</div>
						</c:if>
					</div>
					</div>
					</div>
				</div>
			</div>
		</div>
</div>
</div>
</form>

<!-- modal popup for establishment -->
<div class="modal fade" id="saveEstablishmentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="establishment-details"/></h5>
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
                                  	<!-- Hidden message elements -->
										<p id="msg_personal_details_submitted" hidden><liferay-ui:message key="personal-details-submitted" /></p>
										<p id="msg_service_details_submitted" hidden><liferay-ui:message key="service-details-submitted" /></p>
										<p id="msg_gpf_details_submitted" hidden><liferay-ui:message key="gpf-details-submitted" /></p>
										<p id="msg_nps_details_submitted" hidden><liferay-ui:message key="nps-details-submitted" /></p>
										<p id="msg_training_details_submitted" hidden><liferay-ui:message key="training-details-submitted" /></p>
										<p id="msg_posting_status_submitted" hidden><liferay-ui:message key="posting-status-submitted" /></p>
										<p id="msg_roaster_status_submitted" hidden><liferay-ui:message key="roaster-status-submitted" /></p>
										<p id="msg_failed_submit" hidden><liferay-ui:message key="the-details-are-failed-to-submit" /></p>
                                    	
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/establishment" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
		
		</div>
<!-- modal popup for establishment -->



<script>

// Multiple File Upload Start
//Global file arrays
var uploadedFilesPermanencyCertificate = [];
var uploadedFilesTreasuryVerification = [];
var uploadedFilesLanguageExamCertificate = [];
var uploadedFilesAwardCertificationOfHonor = [];
var uploadedFilesConfidentialReports = [];
var uploadedFilesComplaintDetails = [];
var uploadedFilesNoDuesCertificate = [];
var uploadedFilesNoEnquiryCertificate = [];

document.addEventListener('DOMContentLoaded', function() {
    <c:if test="${mode eq 'add' and not empty serviceDetails.treasuryVerificationNames}">
    uploadedFilesTreasuryVerification = [
	        <c:forEach var="photoName" items="${serviceDetails.treasuryVerificationNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.treasuryVerificationURLs[status.index]}',
	                id: '${serviceDetails.treasuryVerificationEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesTreasuryVerification);
	    updateHiddenInput('treasuryVerification', uploadedFilesTreasuryVerification);
	</c:if>
	
	
	<c:if test="${mode eq 'add' and not empty serviceDetails.permanencyCertificateNames}">
	uploadedFilesPermanencyCertificate = [
	        <c:forEach var="photoName" items="${serviceDetails.permanencyCertificateNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.permanencyCertificateURLs[status.index]}',
	                id: '${serviceDetails.permanencyCertificateEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesPermanencyCertificate);
	    updateHiddenInput('permanencyCertificate', uploadedFilesPermanencyCertificate);
	</c:if>
	
	<c:if test="${mode eq 'add' and not empty serviceDetails.languageExamCertificateNames}">
	uploadedFilesLanguageExamCertificate = [
	        <c:forEach var="photoName" items="${serviceDetails.languageExamCertificateNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.languageExamCertificateURLs[status.index]}',
	                id: '${serviceDetails.languageExamCertificateEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesLanguageExamCertificate);
	    updateHiddenInput('languageExamCertificate', uploadedFilesLanguageExamCertificate);
	</c:if>
	
	<c:if test="${mode eq 'add' and not empty serviceDetails.awardCertificationOfHonorNames}">
	uploadedFilesAwardCertificationOfHonor = [
	        <c:forEach var="photoName" items="${serviceDetails.awardCertificationOfHonorNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.awardCertificationOfHonorURLs[status.index]}',
	                id: '${serviceDetails.awardCertificationOfHonorEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesAwardCertificationOfHonor);
	    updateHiddenInput('awardCertificationOfHonor', uploadedFilesAwardCertificationOfHonor);
	</c:if>
	
	<c:if test="${mode eq 'add' and not empty serviceDetails.confidentialReportsNames}">
	uploadedFilesConfidentialReports = [
	        <c:forEach var="photoName" items="${serviceDetails.confidentialReportsNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.confidentialReportsURLs[status.index]}',
	                id: '${serviceDetails.confidentialReportsEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesConfidentialReports);
	    updateHiddenInput('confidentialReports', uploadedFilesConfidentialReports);
	</c:if>
	
	<c:if test="${mode eq 'add' and not empty serviceDetails.complaintDetailsNames}">
	uploadedFilesComplaintDetails = [
	        <c:forEach var="photoName" items="${serviceDetails.complaintDetailsNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.complaintDetailsURLs[status.index]}',
	                id: '${serviceDetails.complaintDetailsEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesComplaintDetails);
	    updateHiddenInput('complaintDetails', uploadedFilesComplaintDetails);
	</c:if>
	
	<c:if test="${mode eq 'add' and not empty serviceDetails.noDuesCertificateNames}">
	uploadedFilesNoDuesCertificate = [
	        <c:forEach var="photoName" items="${serviceDetails.noDuesCertificateNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.noDuesCertificateURLs[status.index]}',
	                id: '${serviceDetails.noDuesCertificateEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesNoDuesCertificate);
	    updateHiddenInput('noDuesCertificate', uploadedFilesNoDuesCertificate);
	</c:if>
	
	<c:if test="${mode eq 'add' and not empty serviceDetails.noEnquiryCertificateNames}">
	uploadedFilesNoEnquiryCertificate = [
	        <c:forEach var="photoName" items="${serviceDetails.noEnquiryCertificateNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${serviceDetails.noEnquiryCertificateURLs[status.index]}',
	                id: '${serviceDetails.noEnquiryCertificateEntryIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Treasury Verification files:", uploadedFilesNoEnquiryCertificate);
	    updateHiddenInput('noEnquiryCertificate', uploadedFilesNoEnquiryCertificate);
	</c:if>
	
});

function updateHiddenInput(inputId, filesArray) {
    const activeFiles = filesArray.filter(f => !f.markedForDelete).map(f => f.name);
    document.getElementById(inputId).value = activeFiles.join(',');
}

function handleMultipleFileUpload(eventOrInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
	
    const fileInput = eventOrInput.target ? eventOrInput.target : eventOrInput;
	
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    let uploadedFiles;
    if (inputId === 'permanencyCertificate') {
        uploadedFiles = uploadedFilesPermanencyCertificate;
    }  else if (inputId === 'treasuryVerification') {
        uploadedFiles = uploadedFilesTreasuryVerification;
    } else if (inputId === 'languageExamCertificate') {
        uploadedFiles = uploadedFilesLanguageExamCertificate;
    } else if (inputId === 'awardCertificationOfHonor') {
        uploadedFiles = uploadedFilesAwardCertificationOfHonor;
    }  else if (inputId === 'confidentialReports') {
        uploadedFiles = uploadedFilesConfidentialReports;
    } else if (inputId === 'complaintDetails') {
        uploadedFiles = uploadedFilesComplaintDetails;
    } else if (inputId === 'noDuesCertificate') {
        uploadedFiles = uploadedFilesNoDuesCertificate;
    } else if (inputId === 'noEnquiryCertificate') {
        uploadedFiles = uploadedFilesNoEnquiryCertificate;
    } 

    const newFiles = Array.from(fileInput.files);
    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
    const totalFiles = activeFilesCount + newFiles.length;

    if (totalFiles > 4) {
        errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="You can upload a maximum of 4 files." /></span>';
        errorSpan.style.display = "block";
        fileInput.value = "";
        return;
    }

    for (let file of newFiles) {
        const ext = file.name.split('.').pop().toLowerCase();

        if (uploadedFiles.some(f => f.name === file.name && !f.markedForDelete)) {
            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="this-file-is-already-uploaded" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        // File type validation
        if (ext !== 'pdf') {
            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="only-pdf-files-are-allowed" /></span>';
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
    let uploadedFiles;
    if (inputId === 'permanencyCertificate') {
        uploadedFiles = uploadedFilesPermanencyCertificate;
    }  else if (inputId === 'treasuryVerification') {
        uploadedFiles = uploadedFilesTreasuryVerification;
    } else if (inputId === 'languageExamCertificate') {
        uploadedFiles = uploadedFilesLanguageExamCertificate;
    } else if (inputId === 'awardCertificationOfHonor') {
        uploadedFiles = uploadedFilesAwardCertificationOfHonor;
    }  else if (inputId === 'confidentialReports') {
        uploadedFiles = uploadedFilesConfidentialReports;
    } else if (inputId === 'complaintDetails') {
        uploadedFiles = uploadedFilesComplaintDetails;
    } else if (inputId === 'noDuesCertificate') {
        uploadedFiles = uploadedFilesNoDuesCertificate;
    } else if (inputId === 'noEnquiryCertificate') {
        uploadedFiles = uploadedFilesNoEnquiryCertificate;
    } 

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
    let uploadedFiles;
    if (inputId === 'permanencyCertificate') {
        uploadedFiles = uploadedFilesPermanencyCertificate;
    }  else if (inputId === 'treasuryVerification') {
        uploadedFiles = uploadedFilesTreasuryVerification;
    } else if (inputId === 'languageExamCertificate') {
        uploadedFiles = uploadedFilesLanguageExamCertificate;
    } else if (inputId === 'awardCertificationOfHonor') {
        uploadedFiles = uploadedFilesAwardCertificationOfHonor;
    }  else if (inputId === 'confidentialReports') {
        uploadedFiles = uploadedFilesConfidentialReports;
    } else if (inputId === 'complaintDetails') {
        uploadedFiles = uploadedFilesComplaintDetails;
    } else if (inputId === 'noDuesCertificate') {
        uploadedFiles = uploadedFilesNoDuesCertificate;
    } else if (inputId === 'noEnquiryCertificate') {
        uploadedFiles = uploadedFilesNoEnquiryCertificate;
    } 

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

// Multiple File Upload End









$(document).ready(function () {
	
	 $(".personal_details, .service_details, .gpf_details, .nps_details, .training_details, .posting_status, .roaster_status").hide();

	 $(".card-footer").hide();
	 
	 $("#typeOfRecord").on("change", function () {
	  var selected = $(this).val();
	  
	  $(".card-footer").hide();

	  $(".personal_details, .service_details, .gpf_details, .nps_details, .training_details, .posting_status, .roaster_status").hide();

	  if (selected === "Personal Details") {
	   $(".personal_details").show();
	   
	   var personalDetailsId = ${personalDetails.personalDetailsId};
	   console.log("ID ID ID ID ID ID ID      :::::       "+personalDetailsId);
	   if(personalDetailsId > 0){
		   $(".submit-btn").hide();
           $(".update-btn").show();
	   } else {
		   $(".submit-btn").show();
           $(".update-btn").hide();
	   }
	   
	  } else if (selected === "Service Details") {
	   $(".service_details").show();
	  } else if (selected === "GPF Details") {
	   $(".gpf_details").show();
	   
	   var gPFDetailsId = ${gpfDetails.gPFDetailsId};
	   console.log("ID ID ID ID ID ID ID      :::::       "+gPFDetailsId);
	   if(gPFDetailsId > 0){
		   $(".submit-btn").hide();
           $(".update-btn").show();
	   } else {
		   $(".submit-btn").show();
           $(".update-btn").hide();
	   }
	   
	  } else if (selected === "NPS Details") {
	   $(".nps_details").show();
	   
	   var nPSDetailsId = ${npsDetails.nPSDetailsId};
	   console.log("ID ID ID ID ID ID ID      :::::       "+nPSDetailsId);
	   if(nPSDetailsId > 0){
		   $(".submit-btn").hide();
           $(".update-btn").show();
	   } else {
		   $(".submit-btn").show();
           $(".update-btn").hide();
	   }
	   
	  } else if (selected === "Training Details") {
	   $(".training_details").show();
	  } else if (selected === "Posting Status") {
	   $(".posting_status").show();
	  } else if (selected === "Roaster Status") {
	   $(".roaster_status").show();
	  }
	  
	  if (selected) {
	         $(".card-footer").show();
	     }
	 });
	 
	 $("#dateOfRetirement, #dateOfAppointment").on("change", () => {
		  $("#dateOfAppointment").valid();
		  $("#dateOfRetirement").valid();
		});

	 
	 // j-Query validation start
	 $('#establishment').validate({
	     
		  onkeyup: function (element) {
		    $(element).valid();
		    },
		  onchange: function (element) {
		       $(element).valid();
		    },
		    
		  rules:{
		   employeeName: {
			   required: true, minlength: 3, maxlength: 75,startEndSpace:true,validateName:true 
		   },
		   category: {       
		    required:true,
		   },
		   sevarthID: {
		    required:true,minlength: 5, maxlength: 15,startEndSpace:true, pattern: /^[A-Za-z0-9]+$/,
		   },
		   dob: {       
		    required:true,
		    validateDOB:10,
		   },
		   height: {
		    required: true,
            pattern: /^[1-9][0-9]*(\.[0-9]{1,2})?$/,
            startEndSpace:true,
            number: true
		   },
		   bodyMark: {       
		    required:true,minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^(?!0+$)(?!.* {2})(?!.*([,.])\1)(?!^[,.\s])(?!.*[,.\s]$)[A-Za-z0-9]+(?:\s?[,.]?\s?[A-Za-z0-9]+)*$/i,
		   },
		   education: {
		    required:true,minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^(?!0+$)(?!.* {2})(?!.*(\s[\/\-]|[\/\-]\s))(?!.*([,.\-/()])\2)[A-Za-z0-9()]+(?:[ ]?[,.\-\/()]?[ ]?[A-Za-z0-9()]+)*$/i,
		   },
		   contactDetails: {       
			   required: true,
          	    digits: true,
               pattern: /^[6789]/,
               minlength: 10,
               maxlength: 10
		   },
		   mscIT: {
		    required:true, minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^(?!0+$)(?!.* {2})(?!.*([,.])\1)(?!^[,.\s])(?!.*[,.\s]$)[A-Za-z0-9]+(?:\s?[,.]?\s?[A-Za-z0-9]+)*$/i,

		   },
		   postalAddress: {       
		    required:true, minlength: 3, maxlength: 75,startEndSpace:true,validAddress:true,
		   },
		   hometown: {
		    required:true, minlength: 3, maxlength: 75,startEndSpace:true,pattern:/^[A-Za-z]+(?: [A-Za-z]+)*$/,
		   },
		   permanentAddress: {       
		    required:true, minlength: 3, maxlength: 75,startEndSpace:true,validAddress:true
		   },
		   castCertificate: {
		//    required:true,
		    pdfOnly: true,
            maxFileSize: true,
		   },
		   castValidity: {       
		    required:true,
		    pdfOnly: true,
            maxFileSize: true,
		   },
		   specialAchievements: {
		    required:true,
		    pdfOnly: true,
            maxFileSize: true,
		   },
		   sportsAchievements: {
		    required:true,
		    pdfOnly: true,
            maxFileSize: true,
		   },
		   gpfNumber: {
		    required:true,
		    validAmount:true,
		   },
		   refundableAmount: {       
		    required:true,
		    validAmount:true,
		   },
		   refundableAmountDate: {
		    required:true,
		   },
		   nonRefundableAmount: {       
		    required:true,
		    validAmount:true,
		   },
		   nonRefundableAmountDate: {
		    required:true,
		   },
		    monthlyDeductionAmount: {       
		    validAmount:true,
		   },
		   monthlyInstallmentAmount: {
			  validAmount:true,
		   },
		   numberOfMonthlyInstallment: {       
			  validAmount:true,
		   }, 
		   balanceAmount: {
		    required:true,
		    validAmount:true,
		   },
		   npsId: {
		    required:true,
		   },
		   npsRefundableAmount: {       
		    required:true,
		   },
		   npsRefundableAmountDate: {
		    required:true,
		   },
		   npsNonRefundableAmount: {       
		    required:true,
		   },
		   npsNonRefundableAmountDate: {
		    required:true,
		   },
		   /* npsMonthlyDeductionAmount: {       
		    required:true,
		   },
		   npsMonthlyInstallmentAmount: {
		    required:true,
		   },
		   npsNumberOfMonthlyInstallment: {       
		    required:true,
		   }, */
		   npsBalanceAmount: {
		    required:true,
		   },
		   numberOfTrainings: {
		    required:true,
		    digits: true,
		    minlength: 1,
            maxlength: 10
		   },
		   trainingName: {       
			   minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^[A-Za-z]+$/,
		   },
		   trainingStartDate : {
				required: function (element) {
			        return $("#trainingEndDate").val().trim() !== "";
			      },
				 date: true
			}, 
			trainingEndDate : {
				 required: function (element) {
			        return $("#trainingStartDate").val().trim() !== "";
			      },
				 date: true,
                greaterThan: "#trainingStartDate",
				 min : function() {
					return $('#trainingStartDate').val();
				}
			},
		   trainingCertificate: {
			   pdfOnly: true,
	           maxFileSize: true,
		   }, 
		   postingStatus: {
		    required:true, minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^[A-Za-z]+$/,
		   },
		   nominationCodreWisePostWise: {       
		    required:true, minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^[A-Za-z]+$/,
		   },
		   promotionCodreWisePostWise: {
		    required:true, minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^[A-Za-z]+$/,
		   },
		   
		      nominations: {
		    	  required:true,minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^(?!0+$)(?!.* {2})(?!.*([,.])\1)(?!^[,.\s])(?!.*[,.\s]$)[A-Za-z0-9]+(?:\s?[,.]?\s?[A-Za-z0-9]+)*$/i,
			   },
			   promotionExam: {       
			    required:true, minlength: 3, maxlength: 75,startEndSpace:true,
			   },
			   dateOfAppointment: {
				      required: true,
				      notFutureDate: true,
				      beforeRetirement: true
				    },
			    dateOfRetirement: {
			      required: true,
			      notPastDate: true,
			      afterAppointment: true
			    },
			   probationPeriod: {
			    minlength: 3, maxlength: 75,startEndSpace:true,
			   },
			   entryLevelDepartmentExam: {       
				   required:true, minlength: 3, maxlength: 75,startEndSpace:true,singleSpaceBetweenWords: true,noConsecutiveSpecialChars: true,pattern: /^[A-Za-z0-9](?:[A-Za-z0-9\-.,\/ ]*[A-Za-z0-9])?$/
			   },
			   regularPromotionDetails: {
			    required:true, minlength: 3, maxlength: 75,startEndSpace:true,singleSpaceBetweenWords: true,noConsecutiveSpecialChars: true,pattern: /^[A-Za-z0-9](?:[A-Za-z0-9\-.,:\/ ]*[A-Za-z0-9])?$/
			   },
			   promotionTimeScale: {       
			    required:true,minlength: 3, maxlength: 75,startEndSpace:true,singleSpaceBetweenWords: true, pattern: /^[A-Za-z0-9 ]+$/
			   },
			   regularPayFixation: {
			    required:true,
			    pattern: /^[1-9][0-9]*$/,
			    digits: true,

			   },
			   retirementType: {       
			    required:true,
			   },
			   medicalCertificateDate: {
			    required:true,
			   },
			   medicalCertificate: {       
			    required:true,
			   },
			   policeVerificationDate: {
			    required:true,
			   },
			   policeVerification: {       
			    required:true,
			   },
			   permanencyCertificatePhotos:{
				   required: function() {
					   return uploadedFilesPermanencyCertificate.length === 0;
					 }

			   }
			   ,
			   treasuryVerificationPhotos: {
				   required:function() {
					   return uploadedFilesTreasuryVerification.length === 0;
					 }		   
				   },
				   languageExamCertificatePhotos: {
				   required:function() {
					   return uploadedFilesLanguageExamCertificate.length === 0;
					 }
			   },
			   awardCertificationOfHonorPhotos: {       
				   required: function() {
					   return uploadedFilesAwardCertificationOfHonor.length === 0;
					 }
			   },
			   confidentialReportsPhotos: {
				   required: function() {
					   return uploadedFilesConfidentialReports.length === 0;
					 }
			   },
			   mattaAndDayitwaReceived: {       
				   required: true,
			   },
			   complaintDetailsPhotos: {
				   required: function() {
					   return uploadedFilesComplaintDetails.length === 0;
					 }
			   },
			   noDuesCertificatePhotos: {       
				   required: function() {
					   return uploadedFilesNoDuesCertificate.length === 0;
					 }
			   },
			   noEnquiryCertificatePhotos: {
				   required: function() {
					   return uploadedFilesNoEnquiryCertificate.length === 0;
					 }
			   },
			   dateOfSuspension: {       
			    required:true,
			   },
			   reasonOfSuspension: {
			    required:true, minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^(?!0+$)(?!.* {2})(?!.*([,.])\1)(?!^[,.\s])(?!.*[,.\s]$)[A-Za-z0-9]+(?:\s?[,.]?\s?[A-Za-z0-9]+)*$/i,
			   },
			   dateOfReinstate: {
			    required:true,
			   },
			   departmentalEnquiryReport: {       
			    required:true,
			    pdfOnly: true,
			    maxFileSize: true,
			   },
			   punishment: {
				   required:true, minlength: 3, maxlength: 75,startEndSpace:true, pattern: /^(?!0+$)(?!.* {2})(?!.*([,.])\1)(?!^[,.\s])(?!.*[,.\s]$)[A-Za-z0-9]+(?:\s?[,.]?\s?[A-Za-z0-9]+)*$/i,
			   },
			   seniorityList: {       
			    required:true,
			    pdfOnly: true,
			    maxFileSize: true,
			   },
			   leaveType: {
			    required:true,
			   },
			   leaveFromDate: {       
			    required:true,
			   },
			   leaveToDate: {
			    required:true,
			   },
			   leaveDocument: {       
			    required:true,
			    pdfOnly: true,
			    maxFileSize: true,
			   },
			   probationPeriod:{
				   minlength: 3, maxlength: 75,startEndSpace:true,singleSpaceBetweenWords: true,noConsecutiveSpecialChars: true,pattern: /^[A-Za-z0-9](?:[A-Za-z0-9., ]*[A-Za-z0-9])?$/
			   }
			   
		   
		  },messages:{
		   employeeName: {
		    required:"<liferay-ui:message key="please-enter-employee-name"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		   
		   },
		   category: {       
		    required:"<liferay-ui:message key="please-select-category"/>",
		   },
		   sevarthID: {
		    required:"<liferay-ui:message key="please-enter-sevarath-id"/>",
		    minlength: '<liferay-ui:message key="minimum-5-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-15-characters-allowed" />',
		    pattern:"<liferay-ui:message key="only-letters-and-numbers-allowed" />"
		   },
		   dob: {       
		    required:"<liferay-ui:message key="please-enter-dob"/>",
		   },
		   height: {
		    required:"<liferay-ui:message key="please-enter-height"/>",
		    number: "<liferay-ui:message key='please-enter-valid-number' />",
		    pattern: "<liferay-ui:message key='please-enter-valid-number' />",
		   },
		   bodyMark: {       
		    required:"<liferay-ui:message key="please-enter-body-mark"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		    pattern:"<liferay-ui:message key='please-enter-valid-body-mark' />"
		   },
		   education: {
		    required:"<liferay-ui:message key="please-enter-education-details"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		    pattern:"<liferay-ui:message key='please-enter-valid-education' />"
		   },
		   contactDetails: {       
		    required:"<liferay-ui:message key="please-enter-contact-details"/>",
		    pattern: "<liferay-ui:message key='contact-number-must-start-with-678or9' />",
            digits: "<liferay-ui:message key='only-digits-allowed' />",
            minlength: "<liferay-ui:message key='please-enter-10-digit-number' />",
            maxlength: "<liferay-ui:message key='please-enter-10-digit-number' />"
		   },
		   mscIT: {
		    required:"<liferay-ui:message key="please-enter-mscit"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		    pattern:"<liferay-ui:message key='please-enter-valid-mscit' />"
		   },
		   postalAddress: {
		    required:"<liferay-ui:message key="please-enter-postal-address"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		   },
		   hometown: {       
		    required:"<liferay-ui:message key="please-enter-hometown"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		    pattern:  "<liferay-ui:message key='only-alphabets-and-space-are-allowed' />"
		   },
		   permanentAddress: {
		    required:"<liferay-ui:message key="please-enter-permanenet-address"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		   },
		  /*  castCertificate: {       
		    required:"<liferay-ui:message key="please-add-cast-certificate"/>",
		   }, */
		   castValidity: {
		    required:"<liferay-ui:message key="please-add-cast-validity-certificate"/>",
		   },
		   specialAchievements: {       
		    required:"<liferay-ui:message key="please-add-special-achievements-certificate"/>",
		   },
		   sportsAchievements: {
		    required:"<liferay-ui:message key="please-add-sports-achievements-certificate"/>",
		   },
		   gpfNumber: {       
		    required:"<liferay-ui:message key="please-enter-gpf-no"/>",
		   },
		   refundableAmount: {
		    required:"<liferay-ui:message key="please-enter-refundable-amount"/>",
		   },
		   refundableAmountDate: {
		    required:"<liferay-ui:message key="please-enter-refundable-amount-date"/>",
		   },
		   nonRefundableAmount: {       
		    required:"<liferay-ui:message key="please-enter-non-refundable-amount"/>",
		   },
		   nonRefundableAmountDate: {
		    required:"<liferay-ui:message key="please-enter-non-refundable-amount-date"/>",
		   },
		   /* monthlyDeductionAmount: {       
		    required:"<liferay-ui:message key="please-enter-monthly-deduction-amount"/>",
		   },
		   monthlyInstallmentAmount: {
		    required:"<liferay-ui:message key="please-enter-monthly-installment-amount"/>",
		   },
		   numberOfMonthlyInstallment: {       
		    required:"<liferay-ui:message key="please-enter-no-of-monthly-installment"/>",
		   }, */
		   balanceAmount: {
		    required:"<liferay-ui:message key="please-enter-balance-amount"/>",
		   },
		   npsId: {       
		    required:"<liferay-ui:message key="please-enter-nps-id"/>",
		   },
		   npsRefundableAmount: {
		    required:"<liferay-ui:message key="please-enter-refundable-amount"/>",
		   },
		   npsRefundableAmountDate: {
		    required:"<liferay-ui:message key="please-enter-refundable-amount-date"/>",
		   },
		   npsNonRefundableAmount: {       
		    required:"<liferay-ui:message key="please-enter-non-refundable-amount"/>",
		   },
		   npsNonRefundableAmountDate: {
		    required:"<liferay-ui:message key="please-enter-non-refundable-amount-date"/>",
		   },
		   /* npsMonthlyDeductionAmount: {       
		    required:"<liferay-ui:message key="please-enter-monthly-deduction-amount"/>",
		   },
		   npsMonthlyInstallmentAmount: {
		    required:"<liferay-ui:message key="please-enter-monthly-installment-amount"/>",
		   },
		   npsNumberOfMonthlyInstallment: {       
		    required:"<liferay-ui:message key="please-enter-no-of-monthly-installment"/>",
		   }, */
		   npsBalanceAmount: {
		    required:"<liferay-ui:message key="please-enter-balance-amount"/>",
		   },
		   numberOfTrainings: {
		    required:"<liferay-ui:message key="please-enter-no-of-training"/>",
		    digits: "<liferay-ui:message key='only-digits-allowed' />",
		    minlength: "<liferay-ui:message key='please-enter-atleast-1-digit-number' />",
            maxlength: "<liferay-ui:message key='please-enter-10-digit-number' />"
		   },
		    trainingName: {       
		    	minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern:"<liferay-ui:message key='please-enter-valid-training-name' />"
		   },
		   trainingStartDate : {
				 date: "<liferay-ui:message key='please-enter-a-valid-date' />"
			}, 
			trainingEndDate : {
				date: "<liferay-ui:message key='please-enter-a-valid-date' />",
				greaterThan: "<liferay-ui:message key='training-end-date-date-cannot-be-before-training-start-date' />",
				min : "<liferay-ui:message key='training-end-date-must-be-after-training-start-date'/>"
			},
		   postingStatus: {
		    required:"<liferay-ui:message key="please-enter-posting-status"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		    pattern:"<liferay-ui:message key='please-enter-valid-posting-status' />"
		   
		   },
		   nominationCodreWisePostWise: {       
		    required:"<liferay-ui:message key="please-enter-nomination-codre-wise"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		    pattern:"<liferay-ui:message key='please-enter-valid-nomination-codre-wise-post-wise' />"
		   
		   },
		   promotionCodreWisePostWise: {
		    required:"<liferay-ui:message key="please-enter-promotion-codre-wise"/>",
		    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
		    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
		    pattern:"<liferay-ui:message key='please-enter-valid-promotion-codre-wise-post-wise' />"
		   
		   },
		   
		   nominations: {
			    required: "<liferay-ui:message key='please-enter-nominations' />",
			    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern:"<liferay-ui:message key='please-enter-valid-nominations-name' />"
			    
			  },
			  promotionExam: {
			    required: "<liferay-ui:message key='please-enter-promotion-exam' />",
			    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			  },
			  dateOfAppointment: {
			    required: "<liferay-ui:message key='please-enter-date-of-appointment' />"
			  },
			  dateOfRetirement: {
			    required: "<liferay-ui:message key='please-enter-date-of-retirement' />"
			  },
			  probationPeriod: {
				  minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
				  maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
				 
			  },
			  entryLevelDepartmentExam: {
			    required: "<liferay-ui:message key='please-enter-entry-level-department-exam' />",
			    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern:"<liferay-ui:message key='only-az-09-symbols-no-start-end-special-entryleveldepartmentexam' />"
			  },
			  regularPromotionDetails: {
			    required: "<liferay-ui:message key='please-enter-regular-promotion-details' />",
			    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern:"<liferay-ui:message key='only-az-09-symbols-no-start-end-special' />"
			  },
			  promotionTimeScale: {
			    required: "<liferay-ui:message key='please-enter-promotion-time-scale' />",
			    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern:"<liferay-ui:message key='only-letters-numbers-spaces-allowed' />"
			  },
			  regularPayFixation: {
			    required: "<liferay-ui:message key='please-enter-regular-pay-fixation' />",
			    pattern:"<liferay-ui:message key="only-letters-and-numbers-allowed" />",
			    digits: "<liferay-ui:message key='only-digits-allowed' />",
			    pattern:"<liferay-ui:message key='only-positive-integers' />"
			   
			  },
			  retirementType: {
			    required: "<liferay-ui:message key='please-enter-retirement-type' />"
			  },
			  medicalCertificateDate: {
			    required: "<liferay-ui:message key='please-enter-medical-certificate-date' />"
			  },
			  medicalCertificate: {
			    required: "<liferay-ui:message key='please-upload-medical-certificate' />"
			  },
			  policeVerificationDate: {
			    required: "<liferay-ui:message key='please-enter-police-verification-date' />"
			  },
			  policeVerification: {
			    required: "<liferay-ui:message key='please-upload-police-verification' />"
			  },
			  permanencyCertificatePhotos: {
			    required: "<liferay-ui:message key='please-enter-permanency-certificate' />"
			  },
			  treasuryVerificationPhotos: {
			    required: "<liferay-ui:message key='please-enter-treasury-verification' />"
			  },
			  languageExamCertificatePhotos: {
			    required: "<liferay-ui:message key='please-enter-language-exam-certificate' />"
			  },
			  awardCertificationOfHonorPhotos: {
			    required: "<liferay-ui:message key='please-enter-award-certification-of-honor' />"
			  },
			  confidentialReportsPhotos: {
			    required: "<liferay-ui:message key='please-enter-confidential-reports' />"
			  },
			  mattaAndDayitwaReceived: {
			    required: "<liferay-ui:message key='please-select-matta-and-dayitwa-received' />"
			  },
			  complaintDetailsPhotos: {
			    required: "<liferay-ui:message key='please-enter-complaint-details' />"
			  },
			  noDuesCertificatePhotos: {
			    required: "<liferay-ui:message key='please-enter-no-dues-certificate' />"
			  },
			  noEnquiryCertificatePhotos: {
			    required: "<liferay-ui:message key='please-enter-no-enquiry-certificate' />"
			  },
			  dateOfSuspension: {
			    required: "<liferay-ui:message key='please-enter-date-of-suspension' />"
			  },
			  reasonOfSuspension: {
			    required: "<liferay-ui:message key='please-enter-reason-of-suspension' />",
			    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern:"<liferay-ui:message key='please-enter-valid-reason-of-suspension' />"
			  },
			  dateOfReinstate: {
			    required: "<liferay-ui:message key='please-enter-date-of-reinstate' />"
			  },
			  departmentalEnquiryReport: {
			    required: "<liferay-ui:message key='please-upload-departmental-enquiry-report' />"
			  },
			  punishment: {
			    required: "<liferay-ui:message key='please-enter-punishment' />",
			    minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern:"<liferay-ui:message key='please-enter-valid-punishment' />"
			  },
			  seniorityList: {
			    required: "<liferay-ui:message key='please-upload-seniority-list' />"
			  },
			  leaveType: {
			    required: "<liferay-ui:message key='please-select-leave-type' />"
			  },
			  leaveFromDate: {
			    required: "<liferay-ui:message key='please-enter-leave-from-date' />"
			  },
			  leaveToDate: {
			    required: "<liferay-ui:message key='please-enter-leave-to-date' />"
			  },
			  leaveDocument: {
			    required: "<liferay-ui:message key='please-upload-leave-document' />"
			  },
			  probationPeriod:{
				  minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
				  maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
				  pattern:"<liferay-ui:message key='only-az-09-symbols-no-start-end-special-probationperiod' />"
			  }
		  },
		 }); 
		 // j-Query validation end
		 
		   /*  $.validator.addMethod("customTextBoxValidation", function(value, element) {
		        const regex = /^(?!0+$)(?!.* {2})[A-Za-z0-9]+(?:[ ,.\-]?[A-Za-z0-9]+)*$/i;
		        return this.optional(element) || regex.test(value);
		    }); */
		 
		    $.validator.addMethod("startEndSpace", function(value, element) {
			    // Allow only letters (A-Z, a-z) and spaces
			    return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
			},"<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");
		    
		    $.validator.addMethod("validAmount", function(value, element) {
	            return this.optional(element) || (/^\d+(\.\d{1,2})?$/.test(value) && parseFloat(value) > 0);
	        }, "<liferay-ui:message key='please-enter-a-valid-amount-greater-than-0-with-up-to-2-decimal-places' />");
		    
		    $.validator.addMethod("validateName", function(value, element) {
			    // Allow only letters (A-Z, a-z) and spaces
			    return this.optional(element) || /^[A-Za-z]+(?: [A-Za-z]+)*$/.test(value);
			}, "<liferay-ui:message key='only-alphabets-and-space-are-allowed'/>");
		    
		    // Validate PDF extension
	        $.validator.addMethod("pdfOnly", function (value, element) {
	            if (element.files.length === 0) return true; // Don't block other required validation
	            const fileName = element.files[0].name;
	            const ext = fileName.split('.').pop().toLowerCase();
	            return ext === "pdf";
	        }, "<liferay-ui:message key='allowed-only-pdf-file' />");

	        // Validate file size (max 2MB)
	        $.validator.addMethod("maxFileSize", function (value, element) {
	            if (element.files.length === 0) return true;
	            return element.files[0].size <= 2 * 1024 * 1024; // 2 MB
	        }, "<liferay-ui:message key='file-size-limit' />");
	        
	        $.validator.addMethod("validAddress", function (value, element) {
	            value = $.trim(value); 
	            return this.optional(element) || (/^(?!0+$)(?!.* {2})(?!.*([,.\-\/#])\1)[A-Za-z0-9]+(?:[ ]?[A-Za-z0-9]+|[ ]?[,.\-\/#][ ]?[A-Za-z0-9]+)*$/i.test(value));
	        }, "<liferay-ui:message key='please-enter-valid-address' />");

	        $.validator.addMethod("validateDOB", function (value, element, param) {
	            if (!value) return false;

	            var dob = new Date(value);
	            var today = new Date();

	            var age = today.getFullYear() - dob.getFullYear();
	            var m = today.getMonth() - dob.getMonth();
	            if (m < 0 || (m === 0 && today.getDate() < dob.getDate())) {
	                age--;
	            }

	            return age >= param;
	        }, $.validator.format("<liferay-ui:message key='you-must-be-at-least' /> {0} <liferay-ui:message key='years-old' />"));
	        
	        $.validator.addMethod("singleSpaceBetweenWords", function(value) {
		        return !/ {2,}/.test(value);
		    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');
		
	        
	        
	        $.validator.addMethod("greaterThan", function (value, element, param) {
	            let startDate = $(param).val();
	            return !startDate || new Date(value) >= new Date(startDate);
	        });
	        
	        $.validator.addMethod("noConsecutiveSpecialChars", function(value, element) {
	            // Match repeated same special characters like "..", "--", "//", etc.
	            var repeatedSpecials = /([.,:\/\-])\1+/;

	            // Match mixed consecutive special characters like ".,", ",.", ":/", etc.
	            var mixedSpecials = /[.,:\/\-]{2,}/;

	            // Apply only if there are any special characters
	            if (/[.,:\/\-]/.test(value)) {
	                if (repeatedSpecials.test(value) || mixedSpecials.test(value)) {
	                    return false;
	                }
	            }

	            return true;
	        }, "<liferay-ui:message key='no-consecutive-special-characters-allowed' />");
	        
	        
	        $.validator.addMethod("notFutureDate", function(value) {
	            const today = new Date().toISOString().split("T")[0];
	            return value <= today;
	        }, "<liferay-ui:message key='date-cannot-be-in-the-future' />");

	        $.validator.addMethod("notPastDate", function(value) {
	            const today = new Date().toISOString().split("T")[0];
	            return value >= today;
	        }, "<liferay-ui:message key='date-cannot-be-in-the-past' />");

	        $.validator.addMethod("beforeRetirement", function(value, element) {
	            const retirement = $("#dateOfRetirement").val();
	            return retirement === "" || value < retirement;
	        }, "<liferay-ui:message key='appointment-date-before-retirement-date' />");

	        $.validator.addMethod("afterAppointment", function(value, element) {
	            const appointment = $("#dateOfAppointment").val();
	            return appointment === "" || value > appointment;
	        }, "<liferay-ui:message key='retirement-date-after-appointment-date' />");


	        
	});

function submitDetails(event){debugger
	console.log('inside submit ')
	var form = $("#establishment")[0];
	var formData = new FormData(form);
	
	
	
	if (event) {
        event.preventDefault();
    }
	
	if(formData.get("typeOfRecord")==="Service Details"){
		
		
		uploadedFilesTreasuryVerification.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingTreasuryVerification", fileObj.name);
	                formData.append("existingTreasuryVerificationURLs", fileObj.url);
	                formData.append("existingTreasuryVerificationIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("treasuryVerification", fileObj.file);
	                formData.append("treasuryVerificationNames", fileObj.name);
	            }
	        }
	    });
		
		uploadedFilesPermanencyCertificate.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingPermanencyCertificate", fileObj.name);
	                formData.append("existingPermanencyCertificateURLs", fileObj.url);
	                formData.append("existingPermanencyCertificateIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("permanencyCertificate", fileObj.file);
	                formData.append("permanencyCertificateNames", fileObj.name);
	            }
	        }
	    });
		
		uploadedFilesLanguageExamCertificate.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingLanguageExamCertificate", fileObj.name);
	                formData.append("existingLanguageExamCertificateURLs", fileObj.url);
	                formData.append("existingLanguageExamCertificateIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("languageExamCertificate", fileObj.file);
	                formData.append("languageExamCertificateNames", fileObj.name);
	            }
	        }
	    });
		
		uploadedFilesAwardCertificationOfHonor.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingAwardCertificationOfHonor", fileObj.name);
	                formData.append("existingAwardCertificationOfHonorURLs", fileObj.url);
	                formData.append("existingAwardCertificationOfHonorIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("awardCertificationOfHonor", fileObj.file);
	                formData.append("awardCertificationOfHonorNames", fileObj.name);
	            }
	        }
	    });
		
		uploadedFilesConfidentialReports.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingConfidentialReports", fileObj.name);
	                formData.append("existingConfidentialReportsURLs", fileObj.url);
	                formData.append("existingConfidentialReportsIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("confidentialReports", fileObj.file);
	                formData.append("confidentialReportsNames", fileObj.name);
	            }
	        }
	    });
		
		uploadedFilesComplaintDetails.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingComplaintDetails", fileObj.name);
	                formData.append("existingComplaintDetailsURLs", fileObj.url);
	                formData.append("existingComplaintDetailsIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("complaintDetails", fileObj.file);
	                formData.append("complaintDetailsNames", fileObj.name);
	            }
	        }
	    });
		
		uploadedFilesNoDuesCertificate.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingNoDuesCertificate", fileObj.name);
	                formData.append("existingNoDuesCertificateURLs", fileObj.url);
	                formData.append("existingNoDuesCertificateIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("noDuesCertificate", fileObj.file);
	                formData.append("noDuesCertificateNames", fileObj.name);
	            }
	        }
	    });
		
		
		uploadedFilesNoEnquiryCertificate.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingNoEnquiryCertificate", fileObj.name);
	                formData.append("existingNoEnquiryCertificateURLs", fileObj.url);
	                formData.append("existingNoEnquiryCertificateIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("noEnquiryCertificate", fileObj.file);
	                formData.append("noEnquiryCertificateNames", fileObj.name);
	            }
	        }
	    });
		
	}
	
	
	 const category = $('#typeOfRecord').val();
	 if($('#establishment').valid()){
       $.ajax({
        type: "POST",
        url: '${addEstablichmentURL}' ,
        data:  formData,
        contentType : false,
		cache : false,
		processData : false,
        success: function(data){debugger
        console.log("data: ", typeof data);
        if (typeof data === 'string') {
            try {
                data = JSON.parse(data);
            } catch (e) {
                console.error("Failed to parse JSON response: ", e);
                return;
            }
        }
        console.log("Parsed data: ", data);
        let msgKey = "the-details-are-saved-successfully"; // default

        if (data.status === true) {debugger
            // Success message based on category
            if (category === 'Personal Details') {
			    msgKey = "personal-details-submitted";
			} else if (category === 'Service Details') {
			    msgKey = "service-details-submitted";
			} else if (category === 'GPF Details') {
			    msgKey = "gpf-details-submitted";
			} else if (category === 'NPS Details') {
			    msgKey = "nps-details-submitted";
			} else if (category === 'Training Details') {
			    msgKey = "training-details-submitted";
			} else if (category === 'Posting Status') {
			    msgKey = "posting-status-submitted";
			} else if (category === 'Roaster Status') {
			    msgKey = "roaster-status-submitted";
			}
        } else {
            msgKey = "the-details-are-failed-to-submit";
        }
        console.log("msgKey: ",msgKey)
        	if(data.status == true){
/*         		var msg = "<liferay-ui:message key="the-details-are-saved-successfully"/>";
       	    $('#success-application').html('<liferay-ui:message key="' + msgKey + '" />');*/  
       	    
		       	 let domId = "msg_" + msgKey.replace(/-/g, "_");
		       	 let messageText = $("#" + domId).text();
				 $('#success-application').html(messageText);
        		$("#saveEstablishmentModal").modal('show');
        	}else{
/*         		var msg = "<liferay-ui:message key="the-details-are-failed-to-submit"/>";
 */        	    $('#success-application').html('<liferay-ui:message key="' + msgKey + '" />');
        		$("#saveEstablishmentModal").modal('show');
        	}
    	 }
    });
   }
};
 
 

	
	
	function closeModal() {debugger
	    $("#saveEstablishmentModal").modal('hide');
	 $(".modal-backdrop").remove();
	 $("body").removeClass("modal-open");
	}


	function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {debugger
	    
	    const fileInput = event.target;
	    const file = fileInput.files[0];
	    const previewContainer = document.getElementById(filePreviewContainer);
	    const previewLink = document.getElementById(filePreviewLink);
	    const deleteButton = document.getElementById(deleteBtn);

	    previewContainer.classList.add('d-none');
	    previewContainer.classList.remove('d-flex');
	    previewLink.textContent = '';
	    previewLink.href = '';
	    deleteButton.dataset.filename = '';
	    
	    if (file && $('#' + id).val() !== '' && (typeof $('#' + id).valid === 'function' ? $('#' + id).valid() : true)) {   
	        const fileName = file.name;

	        previewContainer.classList.remove('d-none');
	        previewContainer.classList.add('d-flex');

	        previewLink.textContent = fileName;
	        previewLink.href = URL.createObjectURL(file); 
	        previewLink.target = "_blank";
	        
	        Object.assign(previewLink.style, {
	            textDecoration: "none",
	            //wordWrap: "break-word",
	            //whiteSpace: "normal",
	           // overflow: "hidden",
	           // maxWidth: "200px"
	        });

	        deleteButton.dataset.filename = fileName;
	    }
	}

	function deleteFile(filePreviewContainer,id) {
	    const previewContainer = document.getElementById(filePreviewContainer);
	    const fileInput = document.getElementById(id);

	    fileInput.value = "";
	 $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("<liferay-ui:message key="choose-file" />"); 
	    previewContainer.classList.add('d-none');
	    previewContainer.classList.remove('d-flex');
	}
</script>