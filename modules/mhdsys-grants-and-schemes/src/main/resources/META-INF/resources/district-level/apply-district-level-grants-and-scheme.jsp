<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>
<%@ include file="/init.jsp"%>

<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.19.5/jquery.validate.min.js"></script>

<style>

#geoTagPhotosError{
    position: absolute;
    width: 100%;
    left: 0;
    bottom: -27px;
    font-size: 12px;
}

#supportedDocumentsError{
    position: absolute;
    width: 100%;
    left: 0;
    bottom: -27px;
    font-size: 12px;
}

</style>

<portlet:resourceURL id="<%=MhdsysGrantsAndSchemesPortletKeys.SAVE_DISTRICT_LEVEL_GRANTS_AND_SCHEMES_APPLICATION%>" var="addGrantAndSchemeURL" />

<form id="add_grant_and_scheme" method="POST" enctype="multipart/form-data">

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0">
							<liferay-ui:message key="grants-and-schemes" />
						</h5>
						<div>
							<a href="/group/guest/district-level" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>
								<liferay-ui:message key="back" />
							</a>
						</div>
					</div>

					<div class="card-body">
						<div class="card card-background p-0 mb-4">
							<div class="card-header header-card">
								<liferay-ui:message key="district-level-grants-and-schemes" />
							</div>

							<input type="hidden" class="form-control" id="districtGrantSchemeId" name="districtGrantSchemeId" value="${application.districtGrantSchemeId}" />
							<input type="hidden" class="form-control" id="mode" name="mode" value="${mode}" />
							<input type="hidden" class="form-control" id="dprDocumentFileEntryId" name="dprDocumentFileEntryId" value="${empty application.dprDocument ? 0 : application.dprDocument}" />

							<div class="card-body">

								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="category" /><sup class="text-danger">*</sup></label> 
											<select class="form-control" name="category" id="category" <c:if test="${mode eq 'view' or isDeskOfficer or isHoAdmin or isDeputyDirector}">disabled</c:if>>
												<option value=""><liferay-ui:message key="select" /></option>
												<option value="Gymnasium Development" <c:if test="${application.category eq 'Gymnasium Development'}">selected</c:if> > <liferay-ui:message key="gymnasium-development" /></option>
												<option value="Playground Development" <c:if test="${application.category eq 'Playground Development'}">selected</c:if> > <liferay-ui:message key="playground-development" /></option>
											</select>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="sub-category" /><sup class="text-danger">*</sup></label> 
											<select class="form-control" name="subcategory" id="subcategory" <c:if test="${mode eq 'view' or isDeskOfficer or isHoAdmin or isDeputyDirector}">disabled</c:if>>
												<option value=""><liferay-ui:message key="select" /></option>
												<option value="Sub Category 1" <c:if test="${application.subCategory eq 'Sub Category 1'}">selected</c:if>> <liferay-ui:message key="sub-category-1" /></option>
												<option value="Sub Category 2" <c:if test="${application.subCategory eq 'Sub Category 2'}">selected</c:if>> <liferay-ui:message key="sub-category-2" /></option>
												<option value="Sub Category 3" <c:if test="${application.subCategory eq 'Sub Category 3'}">selected</c:if>> <liferay-ui:message key="sub-category-3" /></option>
											</select>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label> <liferay-ui:message key="project" /><sup class="text-danger">*</sup></label> 
											<input type="text" class="form-control" id="project" name="project"  value="${application.project}" 
								                <c:if test="${mode eq 'view' or isDeskOfficer or isHoAdmin or isDeputyDirector}">disabled</c:if> />
											
											<%-- <select class="form-control" name="project" id="project" <c:if test="${mode eq 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
												<option value=""><liferay-ui:message key="select" /></option>
												<option value="Project 1" <c:if test="${application.project eq 'Project 1'}">selected</c:if>> <liferay-ui:message key="project-1" /></option>
												<option value="Project 2" <c:if test="${application.project eq 'Project 2'}">selected</c:if>> <liferay-ui:message key="project-2" /></option>
												<option value="Project 3" <c:if test="${application.project eq 'Project 3'}">selected</c:if>> <liferay-ui:message key="project-3" /></option>
											</select> --%>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label> <liferay-ui:message key="type" /><sup class="text-danger">*</sup></label> 
											<input type="text" class="form-control" id="type" name="type"  value="${application.type}" 
								                <c:if test="${mode eq 'view' or isDeskOfficer or isHoAdmin or isDeputyDirector}">disabled</c:if> />
											
											<%-- <select class="form-control" name="type" id="type" <c:if test="${mode eq 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
												<option value=""><liferay-ui:message key="select" /></option>
												<option value="Type 1" <c:if test="${application.type eq 'Type 1'}">selected</c:if>> <liferay-ui:message key="type-1" /></option>
												<option value="Type 2" <c:if test="${application.type eq 'Type 2'}">selected</c:if>> <liferay-ui:message key="type-2" /></option>
												<option value="Type 3" <c:if test="${application.type eq 'Type 3'}">selected</c:if>> <liferay-ui:message key="type-3" /></option>
											</select> --%>
										</div>
									</div>
								</div>
							</div>
						</div>


						<div class="card card-background p-0 mb-4">
							<div class="card-header header-card">
								<liferay-ui:message key="other-details" />
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label> <liferay-ui:message key="details" /><sup class="text-danger">*</sup></label> 
											<input type="text" class="form-control" id="details" name="details"  value="${application.details}" 
								                <c:if test="${mode eq 'view' or isDeskOfficer or isHoAdmin or isDeputyDirector}">disabled</c:if> />
										</div>
									</div>
									
									<div class="col-md-6">
									    <div class="form-group">
										    <c:if test="${mode eq 'add' or mode eq 'edit' && (!isDeputyDirector && !isDeskOfficer && !isHoAdmin)}">
										    	<label><liferay-ui:message key="geo-tag-photos" /><sup class="text-danger">*</sup>
										                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-jpg-jpeg-png-file-of-size-2mb" />">
										                </em>
										        </label>
										    </c:if>
										    <c:if test="${mode eq 'view' || (isDeputyDirector || isDeskOfficer || isHoAdmin)}">
										    	<label><liferay-ui:message key="geo-tag-photos" /><sup class="text-danger">*</sup></label>
										    </c:if>
									        <c:if test="${mode eq 'add' or mode eq 'edit' && (!isDeputyDirector && !isDeskOfficer && !isHoAdmin)}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input geoTagPhotos" id="geoTagPhotos" name="geoTagPhotos" multiple
									                    onchange="handleMultipleFileUpload(this, 'geoTagPhotos', 'geoTagPhotosPreviewContainer', 'geoTagPhotosPreviewList', 'geoTagPhotosError', 'geoTagPhoto')">
									                <label class="custom-file-label" for="geoTagPhotos">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="geoTagPhotosError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="geoTagPhoto" name="geoTagPhoto" 
									                value='<c:if test="${mode eq 'edit' and not empty application.geoTagPhotosNames}"><c:forEach var="photoName" items="${application.geoTagPhotosNames}" varStatus="status">${photoName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="geoTagPhotosPreviewContainer" 
									                style='<c:if test="${mode ne 'edit' or empty application.geoTagPhotosURLs}">display: none;</c:if>'>
									                <ul id="geoTagPhotosPreviewList" name="geoTagPhotosPreviewList" class="list-group">
									                    <c:if test="${mode eq 'edit' and not empty application.geoTagPhotosURLs && (!isDeputyDirector && !isDeskOfficer && !isHoAdmin)}">
									                        <c:forEach var="photoURL" items="${application.geoTagPhotosURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${photoURL}" target="_blank" class="text-truncate">
									                                    ${application.geoTagPhotosNames[status.index]}
									                                </a>
									                                <!-- FIXED: Correct parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" onclick="removeFile(${status.index}, 'geoTagPhotosPreviewContainer', 'geoTagPhotosPreviewList', 'geoTagPhotosError', 'geoTagPhoto', 'geoTagPhotos')">
																	    <i class="bi bi-x-circle-fill"></i>
																	</button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <c:if test="${mode eq 'view' and not empty application.geoTagPhotosURLs || (isDeputyDirector || isDeskOfficer || isHoAdmin)}">
									            <div>
									                <c:forEach var="photoURL" items="${application.geoTagPhotosURLs}" varStatus="status">
									                    <a href="${photoURL}" target="_blank" class="text-truncate">
									                        ${application.geoTagPhotosNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if>
									    </div>
									</div>
								</div>
								
								
								<div class="row">
									<div class="col-md-12 mb-4">
										<c:if test="${mode ne 'view'}">
										    <div class="form-group">
										        <label><liferay-ui:message key="land-location-coordinates" /></label>
										        <button type="button" class="btn btn-primary" onclick="getLocation()" <c:if test="${mode eq 'view'}">disabled</c:if>><liferay-ui:message key="get-location" /></button>
										    </div>
									    </c:if>
									    <div id="map" class="<c:if test='${empty application.latitude or empty application.longitude}'>d-none</c:if>" style="height: 300px; border: 1px solid #ccc; z-index: 1;"></div> <!-- Map Container -->
									</div>
									<div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="latitude" /><sup class="text-danger">*</sup></label>
									        <input type="text" class="form-control" name="latitude" id="latitude" placeholder="<liferay-ui:message key='latitude' />" value="${application.latitude}" readonly />
									    </div>
									</div>
									<div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="longitude" /><sup class="text-danger">*</sup></label>
									        <input type="text" class="form-control" name="longitude" id="longitude" placeholder="<liferay-ui:message key='longitude' />" value="${application.longitude}" readonly />
									    </div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="card card-background p-0 mb-4">
							<div class="card-header header-card">
								<liferay-ui:message key="required-documents" />
							</div>
							<div class="card-body">
								<div class="row">
								<div class="col-md-6">
								    <div class="form-group">
								        <label>
								            <liferay-ui:message key="dpr-document" />
								            <sup class="text-danger">*</sup>
								            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file-of-size-2mb" />"></em>
								        </label>
								            <%-- View Mode --%>
								            <c:if test="${mode eq 'view' || (isDeputyDirector || isDeskOfficer || isHoAdmin)}">
								                <c:if test="${not empty application.dprDocumentURL}">
								                    <div>
								                        <a href="${application.dprDocumentURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
								                            ${application.dprDocumentName}
								                        </a>
								                    </div>
								                </c:if>
								            </c:if>
								            <%-- Edit Mode --%>
								            <c:if test="${mode eq 'edit' && (!isDeputyDirector && !isDeskOfficer && !isHoAdmin)}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="dprDocument" name="dprDocument"
								                        onchange="handleFileUpload(event, 'dprDocument', 'dprDocumentPreviewContainer', 'dprDocumentPreviewLink', 'dprDocumentdeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                <%-- Show existing file with delete option if available --%>
								                <c:if test="${not empty application.dprDocumentURL}">
								                    <div class="ownerProofid d-flex mt-3" id="dprDocumentPreviewContainer">
								                        <a class="dprDocumentProofCls text-truncate" id="dprDocumentPreviewLink" 
								                            href="${application.dprDocumentURL}" target="_blank"
								                            style="flex-grow: 1; text-decoration: none;">
								                            ${application.dprDocumentName}
								                        </a>
								                        <button type="button" id="dprDocumentdeleteButton" class="dltdprDocumentBtn close"
								                            aria-label="Close" onclick="deleteFile('dprDocumentPreviewContainer', 'dprDocument')">
								                            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                        </button>
								                    </div>
								                </c:if>
								                <%-- Empty container for new file upload preview --%>
								                <div class="ownerProofid d-none mt-3" id="dprDocumentPreviewContainer">
								                    <a class="dprDocumentProofCls text-truncate"  id="dprDocumentNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="dprDocumentNewDeleteButton" class="dltdprDocumentBtn close"
								                        aria-label="Close" onclick="deleteFile('dprDocumentPreviewContainer', 'dprDocument')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if>
								            <%-- Add/Other Modes --%>
								            <c:if test="${mode eq 'add'}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="dprDocument" name="dprDocument"
								                        onchange="handleFileUpload(event, 'dprDocument', 'dprDocumentNewPreviewContainer', 'dprDocumentNewPreviewLink', 'dprDocumentNewDeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                <%-- Empty container for new file upload preview --%>
								                <div class="ownerProofid d-none mt-3" id="dprDocumentNewPreviewContainer">
								                    <a class="dprDocumentProofCls text-truncate" id="dprDocumentNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="dprDocumentNewDeleteButton" class="dltdprDocumentBtn close"
								                        aria-label="Close" onclick="deleteFile('dprDocumentNewPreviewContainer', 'dprDocument')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if>
								    </div>
								</div>
									
									
								<div class="col-md-6">
									    <div class="form-group">
									    <c:if test="${mode eq 'add' or mode eq 'edit' && (!isDeputyDirector && !isDeskOfficer && !isHoAdmin)}">
								    	<label><liferay-ui:message key="supported-documents" /><sup class="text-danger">*</sup>
									                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file-of-size-2mb" />"></em></label>
								    </c:if>
								    <c:if test="${mode eq 'view' || (isDeputyDirector || isDeskOfficer || isHoAdmin)}">
								    	<label><liferay-ui:message key="supported-documents" /><sup class="text-danger">*</sup></label>
								    </c:if>
									    
									        <c:if test="${mode eq 'add' or mode eq 'edit' && (!isDeputyDirector && !isDeskOfficer && !isHoAdmin)}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input supportedDocuments" id="supportedDocuments"
									                    name="supportedDocuments" multiple
									                    onchange="handleMultipleFileUpload(this, 'supportedDocuments', 'supportedDocumentsPreviewContainer', 'supportedDocumentsPreviewList', 'supportedDocumentsError', 'supportedDocument')">
									                <label class="custom-file-label" for="supportedDocuments">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <!-- Error message -->
									            <span id="supportedDocumentsError"></span>
									
									            <!-- Hidden input to store file details -->
									            <input type="hidden" id="supportedDocument" name="supportedDocument" 
									                value='<c:if test="${mode eq 'edit' and not empty application.supportedDocumentsNames }"><c:forEach var="fileName" items="${application.supportedDocumentsNames}" varStatus="status">${fileName}<c:if test="${not status.last}">,</c:if></c:forEach></c:if>'>
									
									            <!-- Preview and Delete Section -->
									            <div class="mt-3" id="supportedDocumentsPreviewContainer" 
									                style='<c:if test="${mode ne 'edit' or empty application.supportedDocumentsURLs}">display: none;</c:if>'>
									                <ul id="supportedDocumentsPreviewList" name="supportedDocumentsPreviewList" class="list-group">
									                    <c:if test="${mode eq 'edit' and not empty application.supportedDocumentsURLs && (!isDeputyDirector && !isDeskOfficer && !isHoAdmin)}">
									                        <c:forEach var="fileURL" items="${application.supportedDocumentsURLs}" varStatus="status">
									                            <li class="list-group-item d-flex justify-content-between align-items-center">
									                                <a href="${fileURL}" target="_blank">
									                                    ${application.supportedDocumentsNames[status.index]}
									                                </a>
									                                <!-- Fixed parameter order in removeFile call -->
									                                <button type="button" class="btn btn-danger btn-sm" 
									                                    onclick="removeFile(${status.index}, 'supportedDocumentsPreviewContainer', 'supportedDocumentsPreviewList', 'supportedDocumentsError', 'supportedDocument', 'supportedDocuments')">
									                                    <i class="bi bi-x-circle-fill"></i>
									                                </button>
									                            </li>
									                        </c:forEach>
									                    </c:if>
									                </ul>
									            </div>
									        </c:if>
									        
									        <c:if test="${mode eq 'view' and not empty application.supportedDocumentsURLs || (isDeputyDirector || isDeskOfficer || isHoAdmin)}">
									            <div>
									                <c:forEach var="fileURL" items="${application.supportedDocumentsURLs}" varStatus="status">
									                    <a href="${fileURL}" target="_blank">
									                        ${application.supportedDocumentsNames[status.index]}
									                    </a><br>
									                </c:forEach>
									            </div>
									        </c:if>
									    </div>
									</div>
								</div>
							</div>
						</div>
						
					<c:if test="${not empty application.hoVerification || isDeskOfficer || isHoAdmin}">
					    <div class="card card-background p-0 mb-4">
					        <div class="card-header header-card">
					            <liferay-ui:message key="desk-officer-remarks" />
					        </div>
					        <div class="card-body">
					            <div class="row">
					                <div class="col-md-6">
					                    <div class="form-group">
					                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
					                        <div>
					                            <label> 
					                                <input type="radio" name="hoVerification" id="hoVerification" value="Approve"
					                                    <c:if test="${not empty application.hoVerification || mode eq 'view'}">disabled</c:if>
					                                    <c:if test="${application.hoVerification == 'Approve'}">checked</c:if>>
					                                <liferay-ui:message key="approve" />
					                            </label> 
					                            <label> 
					                                <input type="radio" name="hoVerification" id="hoVerification" value="Reject"
					                                    <c:if test="${not empty application.hoVerification || mode eq 'view'}">disabled</c:if>
					                                    <c:if test="${application.hoVerification == 'Reject'}">checked</c:if>>
					                                <liferay-ui:message key="reject" />
					                            </label>
					                        </div>
					                    </div>
					                </div>
					                
					                <div class="col-md-6">
					                    <div class="form-group">
					                        <label id="remarksLabelHo">
					                        	<liferay-ui:message key="remarks" />
					                        </label> 
					                        <input type="text" class="form-control" id="hoRemarks" name="hoRemarks" 
					                            value="${application.hoRemarks}"
					                            <c:if test="${not empty application.hoVerification || mode eq 'view'}">disabled</c:if> />
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					</c:if>
					
						
					<c:if test="${isDeputyDirector || (!isDeputyDirector && (not empty application.ddVerification))}">
					    <div class="card card-background p-0 mb-4">
					        <div class="card-header header-card">
					            <liferay-ui:message key="director-remarks" />
					        </div>
					        <div class="card-body">
					            <div class="row">
					                <div class="col-md-6">
					                    <div class="form-group">
					                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
					                        <div>
					                            <label> 
					                                <input type="radio" name="ddVerification" id="ddVerification" value="Approve"
					                                    <c:if test="${not empty application.ddVerification}">disabled</c:if>
					                                    <c:if test="${application.ddVerification == 'Approve'}">checked</c:if>>
					                                <liferay-ui:message key="approve" />
					                            </label> 
					                            <label> 
					                                <input type="radio" name="ddVerification" id="ddVerification" value="Reject"
					                                    <c:if test="${not empty application.ddVerification}">disabled</c:if>
					                                    <c:if test="${application.ddVerification == 'Reject'}">checked</c:if>>
					                                <liferay-ui:message key="reject" />
					                            </label>
					                        </div>
					                    </div>
					                </div>
					                
					                <div class="col-md-6">
					                    <div class="form-group">
					                        <label id="remarksLabelDd">
					                        	<liferay-ui:message key="remarks" />
					                        </label> 
					                        <input type="text" class="form-control" id="ddRemarks" name="ddRemarks" 
					                            value="${application.ddRemarks}"
					                            <c:if test="${not empty application.ddVerification}">disabled</c:if> />
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					</c:if>
					
					</div>

						<div class="card-footer bg-transparent text-right p-4">
						    <c:choose>
						        <%-- Scenario 1: HO Admin with empty remarks/verification --%>
						        <c:when test="${(isHoAdmin or isDeskOfficer) && (empty application.hoVerification && empty application.hoRemarks)}">
						            <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/grants-and-scheme';">
						                <liferay-ui:message key="cancel" />
						            </button>
						            <button class="btn btn-primary" type="button" onclick="location.reload();">
						                <liferay-ui:message key="reset" />
						            </button>
						            <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
						                <liferay-ui:message key="submit" />
						            </button>
						        </c:when>
						        
						        <%-- Scenario 2: Deputy Director with empty remarks/verification --%>
						        <c:when test="${isDeputyDirector && (empty application.ddVerification && empty application.ddRemarks)}">
						            <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/grants-and-scheme';">
						                <liferay-ui:message key="cancel" />
						            </button>
						            <button class="btn btn-primary" type="button" onclick="location.reload();">
						                <liferay-ui:message key="reset" />
						            </button>
						            <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
						                <liferay-ui:message key="submit" />
						            </button>
						        </c:when>
						        
						        <%-- Scenario 3: Regular user based on mode --%>
						        <c:when test="${!isDeskOfficer && !isDeputyDirector && !isHoAdmin}">
						            <c:if test="${mode ne 'view'}">
						                <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/grants-and-scheme';">
						                    <liferay-ui:message key="cancel" />
						                </button>
						                <button class="btn btn-primary" type="button" onclick="location.reload();">
						                    <liferay-ui:message key="reset" />
						                </button>
						                <c:choose>
						                    <c:when test="${mode eq 'add'}">
						                        <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
						                            <liferay-ui:message key="submit" />
						                        </button>
						                    </c:when>
						                    <c:when test="${mode eq 'edit'}">
						                        <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
						                            <liferay-ui:message key="update" />
						                        </button>
						                    </c:when>
						                </c:choose>
						            </c:if>
						        </c:when>
						        
						        <%-- Default case (hide buttons) --%>
						        <c:otherwise>
						            <%-- No buttons shown --%>
						        </c:otherwise>
						    </c:choose>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<!-- modal popup for grants and schemes -->
<div class="modal fade" id="addGrantAndSchemeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="exampleModalLongTitle">
					<liferay-ui:message key="grants-and-schemes" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<p id="success-application"></p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<a href="/group/guest/district-level" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal"
					onclick="closeModal()"><liferay-ui:message key="close" />
				</a>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for grants and schemes -->

<script>

$(document).ready(function() {
	
$('input[name="hoVerification"]:checked').trigger('change');

$('input[name="ddVerification"]:checked').trigger('change');
	
    $('input[name="hoVerification"]').change(function() {
        if ($(this).val() === "Reject") {
            $('input[name="hoRemarks"]').prop('required', true);
            if($('#remarksLabelHo sup').length === 0){ 
                $('#remarksLabelHo').append('<sup class="text-danger">*</sup>');
            }
        } else {
            $('input[name="hoRemarks"]').prop('required', false);
            $('#remarksLabelHo sup').remove();
        }
    });
    
    $('input[name="ddVerification"]').change(function() {
        if ($(this).val() === "Reject") {
            $('input[name="ddRemarks"]').prop('required', true);
            if($('#remarksLabelDd sup').length === 0){ 
                $('#remarksLabelDd').append('<sup class="text-danger">*</sup>');
            }
        } else {
            $('input[name="ddRemarks"]').prop('required', false);
            $('#remarksLabelDd sup').remove();
        }
    });
    
    
    var isHoAdmin = ${isHoAdmin};
    var isDeskOfficer = ${isDeskOfficer};
    var isDeputyDirector = ${isDeputyDirector};

    $('#add_grant_and_scheme').validate({
        onkeyup: function(element) {
            $(element).valid();
        },
        onchange: function(element) {
            $(element).valid();
        },
        rules: {
            category: {
                required: true,
            },
            subcategory: {
                required: true,
            },
            project: {
                required: true,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
            },
            type: {
                required: true,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
            },
            details: {
                required: true,
                pattern: /[A-Za-z0-9]/,
                noConsecutiveSpecialChars: true,
                singleSpaceBetweenWords: true,
                startEndSpace: true
            },
            latitude: {
                required: true,
            },
            longitude: {
                required: true,
            },
            geoTagPhotos:{
			   required: function() {
				   return uploadedFilesGeoTagPhotos.length === 0;
				 }
		    },
		    dprDocument: {
            	required: function() {
            		return $("#dprDocumentFileEntryId").val().trim()==="0";
        	    },
        	    pdfOnly: true,
                maxFileSize: true
            },
            supportedDocuments:{
			   required: function() {
				   return uploadedFilesSupportedDocuments.length === 0;
				 }
		    },
            hoVerification: {
                required: isHoAdmin || isDeskOfficer
            },
            hoRemarks: {
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
                validateRemarks: true
            },
            ddVerification: {
                required: isDeputyDirector
            },
            ddRemarks: {
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
                validateRemarks: true
            }
        },
        messages: {
            category: {
                required: "<liferay-ui:message key='please-select-category'/>"
            },
            subcategory: {
                required: "<liferay-ui:message key='please-select-sub-category'/>"
            },
            project: {
                required: "<liferay-ui:message key='please-enter-project-details'/>"
            },
            type: {
                required: "<liferay-ui:message key='please-enter-type'/>"
            },
            details: {
                required: "<liferay-ui:message key='please-enter-details'/>",
                pattern: "<liferay-ui:message key='please-enter-valid-details'/>"
            },
            latitude: {
                required: "<liferay-ui:message key='please-select-location'/>"
            },
            longitude: {
                required: "<liferay-ui:message key='please-select-location'/>"
            },
            geoTagPhotos: {
		        required: "<liferay-ui:message key='please-enter-geo-tag-photos' />"
		    },
		    dprDocument: {
                required: "<liferay-ui:message key='please-enter-dpr-documents' />"
            },
		    supportedDocuments: {
		        required: "<liferay-ui:message key='please-enter-supported-documents' />"
		    },
            hoVerification: {
                required: "<liferay-ui:message key='please-select-approval-status'/>"
            },
            hoRemarks: {
                required: "<liferay-ui:message key='please-enter-remarks'/>"
            },
            ddVerification: {
                required: "<liferay-ui:message key='please-select-approval-status'/>"
            },
            ddRemarks: {
                required: "<liferay-ui:message key='please-enter-remarks'/>"
            }
        },
        errorPlacement: function(error, element) {
            if (element.attr("name") === "hoVerification" || element.attr("name") === "ddVerification") {
                error.insertAfter(element.closest("div"));
            } else {
                error.insertAfter(element);
            }
        }
    });
    
 // Validate PDF extension
    $.validator.addMethod("pdfOnly", function (value, element) {
        if (element.files.length === 0) return true; // Don't block other required validation
        const fileName = element.files[0].name;
        const ext = fileName.split('.').pop().toLowerCase();
        return ext === "pdf";
    }, "<liferay-ui:message key='allowed-only-pdf-file' />");
 
    $.validator.addMethod("maxFileSize", function (value, element) {
        if (element.files.length === 0) return true;
        return element.files[0].size <= 2 * 1024 * 1024; // 2 MB
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
 		const regex =  /^(?!.*\.\.)(?!.*__)(?!.*[._][._])(?![_\.])[a-zA-Z0-9._]*[a-zA-Z][a-zA-Z0-9._]*(?<![_\.])@[a-zA-Z0-9][a-zA-Z0-9-]*\.[a-zA-Z]{2,}$/
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

	    // Disallow repeated special characters like "..", "--", "//", "  "
	    var repeatedSpecials = /([.,\/\-])\1+/;

	    // Disallow invalid consecutive combinations like ".,", ",.", ",-", "./", etc.
	    var mixedSpecials = /[.,\/\-]{2,}/;

	    if (hasLetterOrDigit && (repeatedSpecials.test(value) || mixedSpecials.test(value))) {
	        return false;
	    }
	    return true;
	}, "<liferay-ui:message key='no-consecutive-special-characters-allowed' />");


	$.validator.addMethod("noSpaceAroundDashSlash", function (value, element) {
	    // Check for space before or after "-" or "/"
	    const regex = /(\s[-]|[-]\s|\/\s|\s\/)/;
	    return this.optional(element) || !regex.test(value);
	}, "<liferay-ui:message key='no-space-around-hyphen-or-slash-allowed' />");

	$.validator.addMethod("noStartEndSpecialChar", function (value, element) {
	    return this.optional(element) || !/^[.,\/\-]|[.,\/\-]$/.test(value);
	}, "<liferay-ui:message key='should-not-start-or-end-with-special-characters' />");
    
    
});






// Map Integration
let map;
let marker;
let latElement = document.getElementById('latitude');
let lonElement = document.getElementById('longitude');

let storedLat = parseFloat(latElement.value);
let storedLon = parseFloat(lonElement.value);
let isViewMode = "${mode}" === "view";
let isEditMode = "${mode}" === "edit";

if (!isNaN(storedLat) && !isNaN(storedLon)) {
    $("#map").removeClass("d-none");
    if(isViewMode){
    	initializeMap(storedLat, storedLon, false); 
    }else{
    	initializeMap(storedLat, storedLon, true); 
    }
}

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
            const lat = position.coords.latitude;
            const lon = position.coords.longitude;
            $("#map").removeClass("d-none");
            initializeMap(lat, lon, true); // Edit mode
        }, showError);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}

function initializeMap(lat, lon, isEditable) {
    if (!map) {
        map = L.map('map').setView([lat, lon], 13);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);
        latElement.value = lat.toFixed(6);
        lonElement.value = lon.toFixed(6);
    } else {
        map.setView([lat, lon], 13);
    }
    if (marker) {
        map.removeLayer(marker);
    }
    marker = L.marker([lat, lon], { draggable: isEditable }).addTo(map);

    if (isEditable) {
        map.on('click', function (e) {
            updateCoordinates(e.latlng.lat, e.latlng.lng);
        });
        marker.on('dragend', function (event) {
            let updatedLatLng = event.target.getLatLng();
            updateCoordinates(updatedLatLng.lat, updatedLatLng.lng);
        });
    }
}

function updateCoordinates(lat, lon) {
    latElement.value = lat.toFixed(6);
    lonElement.value = lon.toFixed(6);
    marker.setLatLng([lat, lon]);
}

function showError(error) {
    switch (error.code) {
        case error.PERMISSION_DENIED:
            alert("User denied the request for Geolocation.");
            break;
        case error.POSITION_UNAVAILABLE:
            alert("Location information is unavailable.");
            break;
        case error.TIMEOUT:
            alert("The request to get user location timed out.");
            break;
        case error.UNKNOWN_ERROR:
            alert("An unknown error occurred.");
            break;
    }
}
// Map Interation


// Multiple File Upload
var uploadedFilesGeoTagPhotos = [];
var uploadedFilesSupportedDocuments = [];

document.addEventListener('DOMContentLoaded', function() {
    <c:if test="${mode eq 'edit' and not empty application.geoTagPhotosNames}">
    uploadedFilesGeoTagPhotos = [
	        <c:forEach var="photoName" items="${application.geoTagPhotosNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${application.geoTagPhotosURLs[status.index]}',
	                id: '${application.geoTagPhotosIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Geo Tag Photos :", uploadedFilesGeoTagPhotos);
	    updateHiddenInput('geoTagPhoto', uploadedFilesGeoTagPhotos);
	</c:if>
	
	<c:if test="${mode eq 'edit' and not empty application.supportedDocumentsNames}">
	uploadedFilesSupportedDocuments = [
	        <c:forEach var="photoName" items="${application.supportedDocumentsNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${application.supportedDocumentsURLs[status.index]}',
	                id: '${application.supportedDocumentsIds[status.index]}', <!-- Add ID here -->
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized Supported Documents files :", uploadedFilesSupportedDocuments);
	    updateHiddenInput('supportedDocument', uploadedFilesSupportedDocuments);
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
    if (inputId === 'geoTagPhotos') {
        uploadedFiles = uploadedFilesGeoTagPhotos;
    } else if (inputId === 'supportedDocuments') {
        uploadedFiles = uploadedFilesSupportedDocuments;
    } 

    const newFiles = Array.from(fileInput.files);
    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
    const totalFiles = activeFilesCount + newFiles.length;

    if (totalFiles > 5) {
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

        if (inputId === 'geoTagPhotos') {
            if (!['png', 'jpg', 'jpeg'].includes(ext)) {
                errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="allowed-only-jpg-jpeg-png-file" /></span>';
                errorSpan.style.display = "block";
                fileInput.value = "";
                uploadedFilesGeoTagPhotos.length = 1;
                return;
            }
        } 
        
        if (inputId === 'supportedDocuments') {
            if (!['pdf'].includes(ext)) {
                errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="allowed-only-pdf-file" /></span>';
                errorSpan.style.display = "block";
                fileInput.value = "";
                uploadedFilesSupportedDocuments.length = 1;
                return;
            }
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
    if (inputId === 'geoTagPhotos') {
        uploadedFiles = uploadedFilesGeoTagPhotos;
    } else if (inputId === 'supportedDocuments') {
        uploadedFiles = uploadedFilesSupportedDocuments;
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
    if (inputId === 'geoTagPhotos') {
        uploadedFiles = uploadedFilesGeoTagPhotos;
    } else if (inputId === 'supportedDocuments') {
        uploadedFiles = uploadedFilesSupportedDocuments;
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
// Multiple File Upload


// Submit Function
function submitDetails(event){debugger
	console.log('inside submit ')
	var form = $("#add_grant_and_scheme")[0];
	var formData = new FormData(form);
	
	if (event) {
        event.preventDefault();
    }
	
	if($('#add_grant_and_scheme').valid()){
		
		uploadedFilesGeoTagPhotos.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingGeoTagPhotos", fileObj.name);
	                formData.append("existingGeoTagPhotosURLs", fileObj.url);
	                formData.append("existingGeoTagPhotosIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("actualGeoTagPhotos", fileObj.file);
	                formData.append("actualGeoTagPhotosNames", fileObj.name);
	            }
	        }
	    });
		
		console.log("Files Added :::   ");
		
		
		uploadedFilesSupportedDocuments.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingSupportedDocuments", fileObj.name);
	                formData.append("existingSupportedDocumentsURLs", fileObj.url);
	                formData.append("existingSupportedDocumentsIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("actualSupportedDocuments", fileObj.file);
	                formData.append("actualSupportedDocumentsNames", fileObj.name);
	            }
	        }
	    });
		
		
	       $.ajax({
	        type: "POST",
	        url: '${addGrantAndSchemeURL}',
	        data:  formData,
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data){
	        console.log("data: ", typeof data);
		        if (typeof data === 'string') {
		            try {
		            	console.log("Date 2:  "+data);
		                data = JSON.parse(data);
		            } catch (e) {
		                console.error("Failed to parse JSON response: ", e);
		                return;
		            }
		        }
	        	console.log("Parsed data: ", data);
	        	if(data.grant == true){
	        		var msg = "";
	        		if(data.isHoAdmin || data.isDeskOfficer){
	        			 msg = "<liferay-ui:message key="deskofficer-successfully-reviewed-the-application"/>";
	        			
	        		} else if(data.isDeputyDirector){
	        			 msg = "<liferay-ui:message key="deputy-director-successfully-reviewed-the-application"/>";
	        			
	        		} else if(data.mode == 'add'){
	        			 msg = "<liferay-ui:message key="the-grants-and-schemes-details-submitted-successfully"/>";
	        			
	        		} else if(data.mode == 'edit'){
	        			 msg = "<liferay-ui:message key="the-grants-and-schemes-details-updated-successfully"/>";
	        			
	        		}
	        	    $('#success-application').html(msg);
	        		 $("#addGrantAndSchemeModal").modal('show');
	        	}else{
	        		var msg = "<liferay-ui:message key="the-grants-and-schemes-details-are-failed-to-submit"/>";
	        	    $('#success-application').html(msg);
	        		 $("#addGrantAndSchemeModal").modal('show');
	        	}
	        }
      });
   }
};
	
function closeModal() {debugger
    $("#addGrantAndSchemeModal").modal('hide');
 	$(".modal-backdrop").remove();
 	$("body").removeClass("modal-open");
}




//Single file upload
function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {
    debugger;
    
    $("#dprDocumentFileEntryId").val(0);
    
    const fileInput = event.target;
    const file = fileInput.files[0]; // Get the uploaded file
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
           // whiteSpace: "normal",
            //overflow: "hidden",
            //maxWidth: "200px"
        });

        deleteButton.dataset.filename = fileName; // Store file name in button dataset
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