<%@ include file="/init.jsp" %>

<%@page import="com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys"%>
<style>
#newsPaperArticleError{
    position: absolute;
    width: 100%;
    left: 0;
    bottom: -35px;
    font-size: 12x !important;
    color: red !important;
    font-family: Poppins-Meduim !important;
    padding: 0px 10px 0px 10px;
}
</style>

<portlet:resourceURL id="<%=MhdsysAwardYouthWebPortletKeys.SAVEAWARDYOUTHORG %>" var="addAwardYouthOrgURL" /> 
<c:set var="commonDTO" value="${not empty awardYouthCommonDTO ? awardYouthCommonDTO : awardYouthOrgCommonDTO}" />

<form id="awardYouthOrg" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
    <div class="container my-5 newTabs">
        <div class="row">
         <div class="col-md-12">
            <div class="border-0 card shadow">
                <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
                    <h5 class="mb-0"><liferay-ui:message key="awards-organization" /></h5>	
                     <c:if test="${!isDeskOfficer and !isAssistantDirector and !isHOAdmin}">
                       <div><a href="/group/guest/youth-awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                    </c:if>
                     <c:if test="${isDeskOfficer or isAssistantDirector or isHOAdmin}">
                       <div><a href="/group/guest/awards-organization-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                    </c:if>
                </div>
                  
                <div class="card-body">
                  
                     <ul class="nav nav-pills nav-justified shadow-sm mb-4 p-2" id="myTab" role="tablist">
                  
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active border-0" id="personalDetails-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#personalDetails">
                                <liferay-ui:message key="personal-details" />
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link border-0" id="organizationDetails-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#organizationDetails">
                                <liferay-ui:message key="organization-details" />
                            </button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link border-0" id="workDetails-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#workDetails">
                                <liferay-ui:message key="work-details" />
                            </button>
                        </li>
                    </ul>

                    <div class="tab-content" id="myTabContent">
                        <input type="hidden" name="awardYouthOrgId" id="awardYouthOrgId" value="${awardYouthOrgCommonDTO.awardYouthOrgId}"/>
  						<input type="hidden" class="form-control" id="attestedCopyFileEntryId" name="attestedCopyFileEntryId" value="${empty awardYouthOrgCommonDTO.attestedCopy ? 0 : awardYouthOrgCommonDTO.attestedCopy}" />
  						<input type="hidden" class="form-control" id="constitutionEntryId" name="constitutionEntryId" value="${empty awardYouthOrgCommonDTO.constitution? 0: awardYouthOrgCommonDTO.constitution}"  />
  						<input type="hidden" class="form-control" id="officersListEntryId" name="officersListEntryId" value="${empty awardYouthOrgCommonDTO.attestedCopy? 0: awardYouthOrgCommonDTO.attestedCopy}" />

                        <!-- Personal Details -->
                        <div class="tab-pane fade show active" id="personalDetails" role="tabpanel" aria-labelledby="personalDetails-tab">
                            <div class="card card-background p-0">
                                <div class="personal_details">
                                    <div class="card-header header-card"><liferay-ui:message key="personal-details" /></div>
                                    <div class="card-body">
                                        <!-- Row 1 -->
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="first-name" /> <sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="firstName" name="firstName"
                                                           placeholder="<liferay-ui:message key='enter-first-name' />"
                                                           <c:if test="${mode == 'view'}">disabled</c:if>
                                                           value="${awardYouthOrgCommonDTO.firstName}" />
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="middle-name" /></label>
                                                    <input type="text" class="form-control" id="middleName" name="middleName"
                                                           placeholder="<liferay-ui:message key='enter-middle-name' />"
                                                           <c:if test="${mode == 'view'}">disabled</c:if>
                                                           value="${awardYouthOrgCommonDTO.middleName}" />
                                                </div>
                                            </div>
                                
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="last-name" /> <sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="lastName" name="lastName"
                                                           placeholder="<liferay-ui:message key='enter-last-name' />"
                                                           <c:if test="${mode == 'view'}">disabled</c:if>
                                                           value="${awardYouthOrgCommonDTO.lastName}" />
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-6">
                                                <div class="form-group mb-3">
                                                    <label><liferay-ui:message key="gender" /> <sup class="text-danger">*</sup></label><br />
                                                    
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="gender" value="Male" id="gender"
                                                            <c:if test="${awardYouthOrgCommonDTO.gender == 'Male'}">checked</c:if>
                                                            <c:if test="${mode == 'view'}">disabled</c:if> />
                                                        <label class="form-check-label" for="genderMale">
                                                            <liferay-ui:message key="male" />
                                                        </label>
                                                    </div>
                                                    
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="gender" value="Female" id="gender"
                                                            <c:if test="${awardYouthOrgCommonDTO.gender == 'Female'}">checked</c:if>
                                                            <c:if test="${mode == 'view'}">disabled</c:if> />
                                                        <label class="form-check-label" for="genderFemale">
                                                            <liferay-ui:message key="female" />
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="address" /><sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="address" name="address"
                                                           placeholder="<liferay-ui:message key='enter-address' />"
                                                           value="${awardYouthOrgCommonDTO.address}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="contact-number" /><sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="contactNo" name="contactNo"
                                                           placeholder="<liferay-ui:message key='enter-contact-number' />"
                                                           value="${awardYouthOrgCommonDTO.contactNo}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="email-id" /><sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="emailId" name="emailId"
                                                           placeholder="<liferay-ui:message key='enter-email-id' />"
                                                           value="${awardYouthOrgCommonDTO.emailId}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer bg-transparent text-right p-4">
                            	<div class="d-flex flex-wrap justify-content-end gap-2">
                                <!--  <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                            </button> -->
                             <c:if test="${mode ne 'view'}">
                               <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                            </button>
		                     <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
                                <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')">
                                    <liferay-ui:message key="save-as-draft" />
                                </button>
                                
                                 <button type="button" class="btn btn-primary" onclick="validateTabFields('organizationDetails')">
                                    <liferay-ui:message key="next" />
                                </button>
                                </c:if>
                                </div>
                            </div>
                        </div>

                        <!-- organization Details -->
                       <div class="tab-pane fade" id="organizationDetails" role="tabpanel" aria-labelledby="organizationDetails-tab">
                            <div class="card card-background p-0">
                                <div class="personal_details">
                                    <div class="card-header header-card"><liferay-ui:message key="organization-details" /></div>
                                    <div class="card-body">
                                       <div class="row">

								<div class="col-md-6">
							    <div class="form-group">
							        <label>
							            <liferay-ui:message key="attested-copy" />
							            <sup class="text-danger">*</sup>
							            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
							        </label>
							
							        <%-- View Mode --%>
							        <c:if test="${mode eq 'view'}">
							            <c:if test="${not empty awardYouthOrgCommonDTO.attestedCopyURL}">
							                <div>
							                    <a href="${awardYouthOrgCommonDTO.attestedCopyURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
							                        ${awardYouthOrgCommonDTO.attestedCopyName}
							                    </a>
							                </div>
							            </c:if>
							        </c:if>
							
							        <%-- Edit Mode --%>
							       <c:if test="${mode eq 'edit'}">
									    <div class="custom-file">
									        <input type="file" class="custom-file-input" id="attestedCopyUpload" name="attestedCopyUpload"
									            onchange="handleFileUpload(event, 'attestedCopyUpload', 'attestedCopyUploadNewPreviewContainer', 'attestedCopyUploadNewPreviewLink', 'attestedCopyUploadNewDeleteButton')">
									        <label class="custom-file-label" for="attestedCopyUpload">
									            <liferay-ui:message key="choose-file" />
									        </label>
									    </div>
									
									    <%-- Show existing file with delete option if available --%>
									    <c:if test="${not empty awardYouthOrgCommonDTO.attestedCopyURL}">
									        <div class="ownerProofid d-flex mt-3" id="attestedCopyUploadPreviewContainer">
									            <a class="attestedCopyUploadProofCls" id="attestedCopyUploadPreviewLink"
									                href="${awardYouthOrgCommonDTO.attestedCopyURL}" target="_blank"
									                style="flex-grow: 1; text-decoration: none;">
									                ${awardYouthOrgCommonDTO.attestedCopyName}
									            </a>
									            <button type="button" id="attestedCopyUploaddeleteButton" class="dltattestedCopyUploadBtn close"
									                aria-label="Close" onclick="deleteFile('attestedCopyUploadPreviewContainer', 'attestedCopyUpload')">
									                <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									            </button>
									        </div>
									    </c:if>
									
									    <%-- Preview of new file to be uploaded --%>
									    <div class="ownerProofid d-none mt-3" id="attestedCopyUploadNewPreviewContainer">
									        <a class="attestedCopyUploadProofCls" id="attestedCopyUploadNewPreviewLink" href="" target="_blank"
									            style="flex-grow: 1; text-decoration: none;"></a>
									        <button type="button" id="attestedCopyUploadNewDeleteButton" class="dltattestedCopyUploadBtn close"
									            aria-label="Close" onclick="deleteFile('attestedCopyUploadNewPreviewContainer', 'attestedCopyUpload')">
									            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									        </button>
									    </div>
									</c:if>

							
							        <%-- Add/Other Modes --%>
							        <c:if test="${mode eq 'add'}">
							            <div class="custom-file">
							                <input type="file" class="custom-file-input" id="attestedCopyUpload" name="attestedCopyUpload"
							                    onchange="handleFileUpload(event, 'attestedCopyUpload', 'attestedCopyUploadNewPreviewContainer', 'attestedCopyUploadNewPreviewLink', 'attestedCopyUploadNewDeleteButton')">
							                <label class="custom-file-label" for="customFile">
							                    <liferay-ui:message key="choose-file" />
							                </label>
							            </div>
							            <%-- Empty container for new file upload preview --%>
							            <div class="ownerProofid d-none mt-3" id="attestedCopyUploadNewPreviewContainer">
							                <a class="attestedCopyUploadProofCls" id="attestedCopyUploadNewPreviewLink" href="" target="_blank"
							                    style="flex-grow: 1; text-decoration: none;"></a>
							                <button type="button" id="attestedCopyUploadNewDeleteButton" class="dltattestedCopyUploadBtn close"
							                    aria-label="Close" onclick="deleteFile('attestedCopyUploadNewPreviewContainer', 'attestedCopyUpload')">
							                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
							                </button>
							            </div>
							        </c:if>
							    </div>
							</div>
								        
								        
								        <div class="col-md-6">
								    <div class="form-group">
								        <label>
								            <liferay-ui:message key="constitution-copy" />
								            <sup class="text-danger">*</sup>
								            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
								        </label>
								
								        <%-- View Mode --%>
								        <c:if test="${mode eq 'view'}">
								            <c:if test="${not empty awardYouthOrgCommonDTO.constitutionURL}">
								                <div>
								                    <a href="${awardYouthOrgCommonDTO.constitutionURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
								                        ${awardYouthOrgCommonDTO.constitutionName}
								                    </a>
								                </div>
								            </c:if>
								        </c:if>
								
								        <%-- Edit Mode --%>
								        <c:if test="${mode eq 'edit'}">
										    <div class="custom-file">
										        <input type="file" class="custom-file-input" id="constitutionUpload" name="constitutionUpload"
										            onchange="handleFileUpload(event, 'constitutionUpload', 'constitutionUploadNewPreviewContainer', 'constitutionUploadNewPreviewLink', 'constitutionUploadNewDeleteButton')">
										        <label class="custom-file-label" for="constitutionUpload">
										            <liferay-ui:message key="choose-file" />
										        </label>
										    </div>
										
										    <%-- Existing file display (if uploaded before) --%>
										    <c:if test="${not empty awardYouthOrgCommonDTO.constitutionURL}">
										        <div class="ownerProofid d-flex mt-3" id="constitutionUploadPreviewContainer">
										            <a class="constitutionUploadProofCls" id="constitutionUploadPreviewLink"
										                href="${awardYouthOrgCommonDTO.constitutionURL}" target="_blank"
										                style="flex-grow: 1; text-decoration: none;">
										                ${awardYouthOrgCommonDTO.constitutionName}
										            </a>
										            <button type="button" id="constitutionUploaddeleteButton" class="dltconstitutionUploadBtn close"
										                aria-label="Close" onclick="deleteFile('constitutionUploadPreviewContainer', 'constitutionUpload')">
										                <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
										            </button>
										        </div>
										    </c:if>
										
										    <%-- New file preview (after selection) --%>
										    <div class="ownerProofid d-none mt-3" id="constitutionUploadNewPreviewContainer">
										        <a class="constitutionUploadProofCls" id="constitutionUploadNewPreviewLink" href="" target="_blank"
										            style="flex-grow: 1; text-decoration: none;"></a>
										        <button type="button" id="constitutionUploadNewDeleteButton" class="dltconstitutionUploadBtn close"
										            aria-label="Close" onclick="deleteFile('constitutionUploadNewPreviewContainer', 'constitutionUpload')">
										            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
										        </button>
										    </div>
										</c:if>

								
								        <%-- Add/Other Modes --%>
								        <c:if test="${mode eq 'add'}">
								            <div class="custom-file">
								                <input type="file" class="custom-file-input" id="constitutionUpload" name="constitutionUpload"
								                    onchange="handleFileUpload(event, 'constitutionUpload', 'constitutionUploadNewPreviewContainer', 'constitutionUploadNewPreviewLink', 'constitutionUploadNewDeleteButton')">
								                <label class="custom-file-label" for="customFile">
								                    <liferay-ui:message key="choose-file" />
								                </label>
								            </div>
								            <%-- Empty container for new file upload preview --%>
								            <div class="ownerProofid d-none mt-3" id="constitutionUploadNewPreviewContainer">
								                <a class="constitutionUploadProofCls" id="constitutionUploadNewPreviewLink" href="" target="_blank"
								                    style="flex-grow: 1; text-decoration: none;"></a>
								                <button type="button" id="constitutionUploadNewDeleteButton" class="dltconstitutionUploadBtn close"
								                    aria-label="Close" onclick="deleteFile('constitutionUploadNewPreviewContainer', 'constitutionUpload')">
								                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                </button>
								            </div>
								        </c:if>
								    </div>
								</div>
												
												
								<div class="col-md-6">
									    <div class="form-group">
									        <label>
									            <liferay-ui:message key="list-of-officers-of-organizations" />
									            <sup class="text-danger">*</sup>
									            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
									        </label>
									
									        <%-- View Mode --%>
									        <c:if test="${mode eq 'view'}">
									            <c:if test="${not empty awardYouthOrgCommonDTO.officersListURL}">
									                <div>
									                    <a href="${awardYouthOrgCommonDTO.officersListURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
									                        ${awardYouthOrgCommonDTO.officersListName}
									                    </a>
									                </div>
									            </c:if>
									        </c:if>
									
									        <%-- Edit Mode --%>
									        <c:if test="${mode eq 'edit'}">
									            <div class="custom-file">
									               <input type="file" class="custom-file-input" id="officersListUpload" name="officersListUpload"
                                                            onchange="handleFileUpload(event, 'officersListUpload', 'officersListUploadNewPreviewContainer', 'officersListUploadNewPreviewLink', 'officersListUploadNewDeleteButton')">

									                <label class="custom-file-label" for="customFile">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <%-- Show existing file with delete option if available --%>
									            <c:if test="${not empty awardYouthOrgCommonDTO.officersListURL}">
									                <div class="ownerProofid d-flex mt-3" id="officersListUploadPreviewContainer">
									                    <a class="officersListUploadProofCls" id="officersListUploadPreviewLink"
									                        href="${awardYouthOrgCommonDTO.officersListURL}" target="_blank"
									                        style="flex-grow: 1; text-decoration: none;">
									                        ${awardYouthOrgCommonDTO.officersListName}
									                    </a>
									                    <button type="button" id="officersListUploaddeleteButton" class="dltofficersListUploadBtn close"
									                        aria-label="Close" onclick="deleteFile('officersListUploadPreviewContainer', 'officersListUpload')">
									                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									                    </button>
									                </div>
									            </c:if>
									
									            <%-- Empty container for new file upload preview --%>
									            <div class="ownerProofid d-none mt-3" id="officersListUploadNewPreviewContainer">
									                <a class="officersListUploadProofCls" id="officersListUploadNewPreviewLink" href="" target="_blank"
									                    style="flex-grow: 1; text-decoration: none;"></a>
									                <button type="button" id="officersListUploadNewDeleteButton" class="dltofficersListUploadBtn close"
									                    aria-label="Close" onclick="deleteFile('officersListUploadNewPreviewContainer', 'officersListUpload')">
									                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									                </button>
									            </div>
									        </c:if>
									
									        <%-- Add/Other Modes --%>
									        <c:if test="${mode eq 'add'}">
									            <div class="custom-file">
									                <input type="file" class="custom-file-input" id="officersListUpload" name="officersListUpload"
									                    onchange="handleFileUpload(event, 'officersListUpload', 'officersListUploadNewPreviewContainer', 'officersListUploadNewPreviewLink', 'officersListUploadNewDeleteButton')">
									                <label class="custom-file-label" for="customFile">
									                    <liferay-ui:message key="choose-file" />
									                </label>
									            </div>
									            <%-- Empty container for new file upload preview --%>
									            <div class="ownerProofid d-none mt-3" id="officersListUploadNewPreviewContainer">
									                <a class="officersListUploadProofCls" id="officersListUploadNewPreviewLink" href="" target="_blank"
									                    style="flex-grow: 1; text-decoration: none;"></a>
									                <button type="button" id="officersListUploadNewDeleteButton" class="dltofficersListUploadBtn close"
									                    aria-label="Close" onclick="deleteFile('officersListUploadNewPreviewContainer', 'officersListUpload')">
									                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									                </button>
									            </div>
									        </c:if>
									    </div>
									</div>
																        

								
								      </div>
								                                       
                                    </div>
                                </div>
                            </div>
                               <div class="card-footer bg-transparent text-right p-4">
                                <!--  <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                            </button> -->
                             <c:if test="${mode ne 'view'}">
                                <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                            </button>
		                     <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
                                <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')">
                                    <liferay-ui:message key="save-as-draft" />
                                </button>
                                
                                <button type="button" class="btn btn-primary" onclick="setActiveTab('personalDetails')">
                                    <liferay-ui:message key="previous" />
                                </button>
                                
                                  <button type="button" class="btn btn-primary" onclick="validateTabFields('workDetails')">
                                    <liferay-ui:message key="next" />
                                </button>
                                
                                </c:if>
			                </div>
                        </div>
                        <!-- Work Details -->
                        <div class="tab-pane fade" id="workDetails" role="tabpanel" aria-labelledby="workDetails-tab">
                            <div class="card card-background p-0">
                                <div class="personal_details">
                                    <div class="card-header header-card"><liferay-ui:message key="work-details" /></div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="work-area-(for-youth-development)" /><sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="workArea" name="workArea"
                                                           placeholder="<liferay-ui:message key='enter-work-area' />"
                                                           value="${awardYouthOrgCommonDTO.workArea}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="work-details-past-3-years" /><sup class="text-danger">*</sup></label>
                                                    <textarea class="form-control" id="workDetailsPast3Years" name="workDetailsPast3Years"
                                                              placeholder="<liferay-ui:message key='enter-work-details-past-3-years' />"
                                                              <c:if test="${mode == 'view'}">disabled</c:if>>${awardYouthOrgCommonDTO.workDetailsPast3Years}</textarea>
                                                </div>
                                            </div>
                                            
                                           <div class="col-md-6">
											    <div class="form-group">
											        <c:if test="${mode eq 'add' or mode eq 'edit'}">
											            <label><liferay-ui:message key="news-paper-article-photo" />
											                <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em></label>
											        </c:if>
											        <c:if test="${mode eq 'view'}">
											            <label><liferay-ui:message key="news-paper-article-photo" /></label>
											        </c:if>
											
											        <c:if test="${mode eq 'add' or mode eq 'edit'}">
											            <div class="custom-file">
											                <input type="file" class="custom-file-input newsPaperArticle" id="newsPaperArticle"
											                    name="newsPaperArticles" multiple
											                    onchange="handleMultipleFileUpload(this, 'newsPaperArticle', 'newsPaperArticlePreviewContainer', 'newsPaperArticlePreviewList', 'newsPaperArticleError', 'newsPaperArticleHidden')">
											                <label class="custom-file-label" for="newsPaperArticle">
											                    <liferay-ui:message key="choose-file" />
											                </label>
											            </div>
											            <!-- Error message -->
											            <span id="newsPaperArticleError"></span>
											
											            <!-- Hidden input to store file details -->
											            <input type="hidden" id="newsPaperArticleHidden" name="newsPaperArticleHidden" 
											                value='<c:if test="${mode eq 'edit' and not empty awardYouthOrgCommonDTO.newsPaperArticleNames}">
											                    <c:forEach var="photoName" items="${awardYouthOrgCommonDTO.newsPaperArticleNames}" varStatus="status">
											                        ${photoName}<c:if test="${not status.last}">,</c:if>
											                    </c:forEach>
											                </c:if>'>
											
											            <!-- Preview and Delete Section -->
											            <div class="mt-3" id="newsPaperArticlePreviewContainer" 
											                style='<c:if test="${mode ne 'edit' or empty awardYouthOrgCommonDTO.newsPaperArticleURLs}">display: none;</c:if>'>
											                <ul id="newsPaperArticlePreviewList" name="newsPaperArticlePreviewList" class="list-group">
											                    <c:if test="${mode eq 'edit' and not empty awardYouthOrgCommonDTO.newsPaperArticleURLs}">
											                        <c:forEach var="photoURL" items="${awardYouthOrgCommonDTO.newsPaperArticleURLs}" varStatus="status">
											                            <li class="list-group-item d-flex justify-content-between align-items-center">
											                                <a href="${photoURL}" target="_blank" class="text-truncate">
											                                    ${awardYouthOrgCommonDTO.newsPaperArticleNames[status.index]}
											                                </a>
											                                <button type="button" class="btn btn-danger btn-sm" onclick="removeFile(${status.index}, 'newsPaperArticlePreviewContainer', 'newsPaperArticlePreviewList', 'newsPaperArticleError', 'newsPaperArticleHidden', 'newsPaperArticle')">
											                                    <i class="bi bi-x-circle-fill"></i>
											                                </button>
											                            </li>
											                        </c:forEach>
											                    </c:if>
											                </ul>
											            </div>
											        </c:if>
											
											        <c:if test="${mode eq 'view' and not empty awardYouthOrgCommonDTO.newsPaperArticleURLs}">
											            <div>
											                <c:forEach var="photoURL" items="${awardYouthOrgCommonDTO.newsPaperArticleURLs}" varStatus="status">
											                    <a href="${photoURL}" target="_blank" class="text-truncate">
											                        ${awardYouthOrgCommonDTO.newsPaperArticleNames[status.index]}
											                    </a><br>
											                </c:forEach>
											            </div>
											        </c:if>
											    </div>
											</div>
                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="govt-financial-assistance" /><sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="govtFinancialAssistance" name="govtFinancialAssistance"
                                                           placeholder="<liferay-ui:message key='enter-govt-financial-assistance' />"
                                                           value="${awardYouthOrgCommonDTO.govtFinancialAssistance}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                            
                                        <div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="area-worked" /><sup class="text-danger">*</sup></label>
									        <select class="form-control" name="areaWorked" id="areaWorked"
									                <c:if test="${mode == 'view'}">disabled</c:if>>
									            <option value=""><liferay-ui:message key="select" /></option>
									            <option value="Rural" <c:if test="${awardYouthOrgCommonDTO.areaWorked == 'Rural'}">selected</c:if>>Rural</option>
									            <option value="Urban" <c:if test="${awardYouthOrgCommonDTO.areaWorked == 'Urban'}">selected</c:if>>Urban</option>
									            <option value="Schedule caste and tribes" <c:if test="${awardYouthOrgCommonDTO.areaWorked == 'Schedule caste and tribes'}">selected</c:if>>Schedule caste and tribes</option>
									            <option value="Slum-remote areas" <c:if test="${awardYouthOrgCommonDTO.areaWorked == 'Slum-remote areas'}">selected</c:if>>Slum-remote areas</option>
									        </select>
									    </div>
									  </div>
                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="area-worked-info" /><sup class="text-danger">*</sup></label>
                                                    <input type="text" class="form-control" id="areaWorkedInfo" name="areaWorkedInfo"
                                                           placeholder="<liferay-ui:message key='enter-area-worked-info' />"
                                                           value="${awardYouthOrgCommonDTO.areaWorkedInfo}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                            
                                             <div class="col-md-6">
								    <div class="form-group mb-3">
								        <label><liferay-ui:message key="recognized-by-govt" /><sup class="text-danger">*</sup></label><br />
								
								        <div class="form-check form-check-inline">
								            <input class="form-check-input" type="radio" name="recognizedByGovt" id="recognizedByGovtState"
								                   value="State Govt"
								                   <c:if test="${awardYouthOrgCommonDTO.recognizedByGovt == 'State Govt'}">checked</c:if>
								                   <c:if test="${mode == 'view'}">disabled</c:if> />
								            <label class="form-check-label" for="recognizedByGovtState">State Govt</label>
								        </div>
								
								        <div class="form-check form-check-inline">
								            <input class="form-check-input" type="radio" name="recognizedByGovt" id="recognizedByGovtCentral"
								                   value="Central Govt"
								                   <c:if test="${awardYouthOrgCommonDTO.recognizedByGovt == 'Central Govt'}">checked</c:if>
								                   <c:if test="${mode == 'view'}">disabled</c:if> />
								            <label class="form-check-label" for="recognizedByGovtCentral">Central Govt</label>
								        </div>
								
								    </div>
								</div>
                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="recognized-work-info" /></label>
                                                    <input type="text" class="form-control" id="recognizedWorkInfo" name="recognizedWorkInfo"
                                                           placeholder="<liferay-ui:message key='enter-recognized-work-info' />"
                                                           value="${awardYouthOrgCommonDTO.recognizedWorkInfo}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                            
                                         <div class="col-md-6">
											    <div class="form-group">
											        <label>
											            <liferay-ui:message key="any-convicted-crime-or-ongoing-case-by-hon-court" /><sup class="text-danger">*</sup>
											        </label>
											        <div>
											            <label class="mr-3">
											                <input type="radio" name="convicted" value="Yes"
											                    <c:if test="${awardYouthOrgCommonDTO.convicted == 'Yes'}">checked</c:if>
											                    <c:if test="${mode == 'view'}">disabled</c:if> />
											                Yes
											            </label>
											            <label>
											                <input type="radio" name="convicted" value="No"
											                    <c:if test="${awardYouthOrgCommonDTO.convicted == 'No'}">checked</c:if>
											                    <c:if test="${mode == 'view'}">disabled</c:if> />
											               No
											            </label>
											        </div>
											    </div>
											</div>

                                            
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label><liferay-ui:message key="convicted-info" /></label>
                                                    <input type="text" class="form-control" id="convictedInfo" name="convictedInfo"
                                                           placeholder="<liferay-ui:message key='enter-convicted-info' />"
                                                           value="${awardYouthOrgCommonDTO.convictedInfo}"
                                                           <c:if test="${mode == 'view'}">disabled</c:if> />
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-6">
								           <div class="form-group">
								        <label><liferay-ui:message key="future-plans-for-youth-welfare" /><sup class="text-danger">*</sup></label>
								        <input type="text" class="form-control" name="futurePlans" id="futurePlans" placeholder="<liferay-ui:message key='enter-future-plans-for-youth-welfare' />"
								               value="${awardYouthOrgCommonDTO.futurePlans}"
								               <c:if test="${mode == 'view'}">disabled</c:if> />
											    </div>
											</div>
                                            
                                            <div class="col-md-6">
										    <div class="form-group form-check mb-1">
										        <input type="checkbox" class="form-check-input" id="undertakingAccepted" name="undertakingAccepted"  <c:if test="${mode == 'view'}">disabled</c:if>
							                     <c:if test="${awardYouthOrgCommonDTO.undertakingAccepted}">checked</c:if>/>
										        <label class="form-check-label" for="undertakingAccepted">
										            <liferay-ui:message key="declaration-statement" /><sup class="text-danger">*</sup>
										        </label>
										    </div>
										</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <c:if test="${isDeskOfficer or isAssistantDirector or isHOAdmin or not empty awardYouthOrgCommonDTO.deskRemarks}">
                             <%@ include file="/youth_award/desk_officer_decision.jsp"%>
                             </c:if>
                              <c:if test="${isAssistantDirector or isHOAdmin or not empty awardYouthOrgCommonDTO.asstDirRemarks}">
                            <%@ include file="/youth_award/assistant_director_decision.jsp"%>
                            </c:if>
                             <c:if test="${isHOAdmin or not empty awardYouthOrgCommonDTO.ddHoRemarks}">
                            <%@ include file="/youth_award/ddHo_decision.jsp"%>
                            </c:if>
                            
                            
                            <div class="card-footer bg-transparent text-right p-4">
                            
                                <!--  <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                               </button> -->
                               
                            
                            <c:if test="${mode ne 'view'}">
                              <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
                                <liferay-ui:message key="cancel" />
                               </button>
		                     <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
							
							 <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')">
                                    <liferay-ui:message key="save-as-draft" />
                                </button>
							
                                <button type="button" class="btn btn-primary" onclick="setActiveTab('organizationDetails')">
                                    <liferay-ui:message key="previous" />
                                </button>
                                <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Submitted')">
                                    <liferay-ui:message key="submit" />
                                </button>
                                
                                </c:if>
                                
                                <c:if test="${mode eq 'view' and ( (isDeskOfficer and empty awardYouthOrgCommonDTO.deskRemarks) 
                                or (isAssistantDirector and empty awardYouthOrgCommonDTO.asstDirRemarks) 
                                or (isHOAdmin and empty awardYouthOrgCommonDTO.ddHoRemarks) )}">
                                  <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards-organization-list';">
                                <liferay-ui:message key="cancel" />
                            </button> 
                             <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
			                   <button type="button" class="btn btn-secondary" onclick="saveAwardYouthOrg(event,'Submitted')" >
			                        <liferay-ui:message key="verify" />
			                    </button>
			                    </c:if>
                            </div>
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
<div class="modal fade" id="saveAwardYouthOrgModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="awards-organization"/></h5>
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
					 <a href="/group/guest/dashboard" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveAwardYouthOrgModal','/group/guest/dashboard')"><liferay-ui:message key="close"/></a>
					    <%--  <c:if test="${isSportsPerson }">
					      <a href="/group/guest/dashboard" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveAwardYouthOrgModal','/group/guest/dashboard')"><liferay-ui:message key="close"/></a>
					     </c:if>
       					 <c:if test="${isDDD }">
					      <a href="/group/guest/application-certificate-verification-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveAwardYouthOrgModal','/group/guest/application-certificate-verification-list')"><liferay-ui:message key="close"/></a>
					     </c:if> --%>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for establishment -->

<script>




//Single file upload
function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {
	if(id==="attestedCopyUpload"){
    	$("#attestedCopyFileEntryId").val(0);
    }else if(id==="constitutionUpload"){
    	$("#constitutionEntryId").val(0);
    }
    else if(id==="officersListUpload"){
    	$("#officersListEntryId").val(0);
    }
    
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
           // wordWrap: "break-word",
           // whiteSpace: "normal",
          //  overflow: "hidden",
          //  maxWidth: "200px"
        });

        deleteButton.dataset.filename = fileName; // Store file name in button dataset
    }
}

function deleteFile(filePreviewContainer,id) {
    const previewContainer = document.getElementById(filePreviewContainer);
    const fileInput = document.getElementById(id);

    fileInput.value = "";
	$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html(" <liferay-ui:message key='choose-file' />"); 
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}




// multiple file upload
var uploadedNewsPaperArticle = [];

 document.addEventListener('DOMContentLoaded', function() {
    <c:if test="${mode eq 'edit' and not empty awardYouthOrgCommonDTO.newsPaperArticleNames}">
    uploadedNewsPaperArticle = [
	        <c:forEach var="photoName" items="${awardYouthOrgCommonDTO.newsPaperArticleNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${awardYouthOrgCommonDTO.newsPaperArticleURLs[status.index]}',
	                id: '${awardYouthOrgCommonDTO.newsPaperArticleEntryIds[status.index]}', 
	                markedForDelete: false
	            }<c:if test="${not status.last}">,</c:if>
	        </c:forEach>
	    ];
	    console.log("Initialized news Paper Article files:", uploadedNewsPaperArticle);
	    updateHiddenInput('newsPaperArticle', uploadedNewsPaperArticle);
	</c:if>
	
	})
function updateHiddenInput(inputId, filesArray) {
    const activeFiles = filesArray.filter(f => !f.markedForDelete).map(f => f.name);
    document.getElementById(inputId).value = activeFiles.join(',');
    console.log(uploadedNewsPaperArticle)
} 


function handleMultipleFileUpload(eventOrInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {
    const fileInput = eventOrInput.target ? eventOrInput.target : eventOrInput;
    const previewContainer = document.getElementById(previewContainerId);
    const previewList = document.getElementById(previewListId);
    const errorSpan = document.getElementById(errorSpanId);
    const hiddenInput = document.getElementById(hiddenInputId);

    // Only handle certificate uploads
    let uploadedFiles = uploadedNewsPaperArticle;

    const newFiles = Array.from(fileInput.files);
    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
    const totalFiles = activeFilesCount + newFiles.length;

    for (let file of newFiles) {
        const ext = file.name.split('.').pop().toLowerCase();

        if (uploadedFiles.some(f => f.name === file.name && !f.markedForDelete)) {
            errorSpan.innerHTML = '<span ><liferay-ui:message key="this-file-is-already-uploaded" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        // Only allow PDF files
        if (!['pdf'].includes(ext)) {
            errorSpan.innerHTML = '<span><liferay-ui:message key="allowed-only-pdf-file" /></span>';
            errorSpan.style.display = "block";
            fileInput.value = "";
            return;
        }

        if (file.size >= 2 * 1024 * 1024) {
            errorSpan.innerHTML = '<span><liferay-ui:message key="file-size-limit" /></span>';
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
    let uploadedFiles = uploadedNewsPaperArticle;

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
   /*  errorSpan.style.display = hasActiveFiles ? "none" : "block";
    errorSpan.innerHTML = hasActiveFiles ? "" : '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="please-upload-at-least-one-file" /></span>'; */
}

function renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput) {
    // Only handle certificate files
    let uploadedFiles = uploadedNewsPaperArticle;

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


const tabValidationRules = {
		organizationDetails: [
	        "#firstName", "#middleName", "#lastName", "#gender", "#address",
	        "#contactNo", "#emailId"
	    ],
	    workDetails:[
	    	"#attestedCopyUpload", "#constitutionUpload", "#officersListUpload"
	    ]
	};


$(document).ready(function () {
	
	if ($("#attestedCopyFileEntryId").val().trim()==="0") {
	    console.log("Field is empty");
	}else{
		 console.log("Field is not empty ");
	}
    var mode = '${mode}';
    console.log(mode)
    if(mode=='view'){
    	$('.nav-link').click(function(e) {
    		var targetTabId = $(this).attr('id');
            console.log(targetTabId)
            // Remove active classes
            $('.nav-link').removeClass('active');
            $('.tab-pane').removeClass('show active');
            
            // Activate the clicked tab
            $(this).addClass('active');
            $('#' + targetTabId.replace('-tab', '')).addClass('show active');
            
            scrollToTop();
    	});
    }
    else{
    	
    	$('.nav-link').click(function(e) {
		    var targetTabId = $(this).attr('id');
		    console.log(targetTabId)
		    
		    switch (targetTabId) {
	        case 'organizationDetails-tab':
	            if (!validateTabFields('organizationDetails')) return e.preventDefault();
	            break;
	        case 'workDetails-tab':
	            if (!validateTabFields('organizationDetails') || !validateTabFields('workDetails')) return e.preventDefault();
	            break;
	    }

		    console.log($('#' + targetTabId.replace('-tab', '')).length)
	     if ($('#' + targetTabId.replace('-tab', '')).length) {
             // Remove active classes
             $('.nav-link').removeClass('active');
             $('.tab-pane').removeClass('show active');

             // Activate the clicked tab
             $(this).addClass('active');
             console.log(targetTabId.replace('-tab', ''))
             $('#' + targetTabId.replace('-tab', '')).addClass('show active');

             if (typeof scrollToTop === "function") {
                 scrollToTop();
             }
         } else {
             e.preventDefault();
         }
		});
   
    }
    
    
    $('#awardYouthOrg').validate({
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
            middleName: {
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
            gender: {
                required: true
            },
            address: {
                required: true,
                minlength: 3,
                maxlength: 250,
                startEndSpace: true,
                validAddress:true
            },
            contactNo: {
                required: true,
                digits: true,
                pattern: /^[6789]/,
                minlength: 10,
                maxlength: 10
            },
            emailId: {
                required: true,
                startEndSpace: true,
                validateEmail:true,
            },
            attestedCopyUpload: {
            	required: function() {
            		return $("#attestedCopyFileEntryId").val().trim()==="0";
        	    },
        	    pdfOnly: true,
                maxFileSize: true
            },
            constitutionUpload: {
            	required: function() {
            		
        	        return $("#constitutionEntryId").val().trim()==="0";
        	    },
        	    pdfOnly: true,
                maxFileSize: true
            },
            officersListUpload: {
            	required: function() {
            		
        	        return $("#officersListEntryId").val().trim()==="0";
        	    },
        	    pdfOnly: true,
                maxFileSize: true
            },
            workArea: {
                required: true,
                noStartEndSpecialChar: true,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noSpaceAroundDashSlash: true,
                noConsecutiveSpecialChars: true,
                generalFieldsValidation: true,
                minlength: 3,
                maxlength: 75,
            },
            workDetailsPast3Years: {
                required: true,
                noStartEndSpecialChar: true,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noSpaceAroundDashSlash: true,
                noConsecutiveSpecialChars: true,
                generalFieldsValidation: true,
                minlength: 3,
                maxlength: 75,
            },
            newsPaperArticle: {
                required: true
            },
            areaWorked: {
                required: true,
               
            },
            areaWorkedInfo: {
                required: true,
                noStartEndSpecialChar: true,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noSpaceAroundDashSlash: true,
                noConsecutiveSpecialChars: true,
                generalFieldsValidation: true,
                minlength: 3,
                maxlength: 75,
            },
            recognizedByGovt: {
                required: true,
                
            },
            recognizedWorkInfo: {
            	/* required: true */
            	 startEndSpace: true,
            	 noStartEndSpecialChar: true,
            	 singleSpaceBetweenWords: true,
                 noSpaceAroundDashSlash: true,
                 noConsecutiveSpecialChars: true,
            	 generalFieldsValidation: true,
                 minlength: 3,
                 maxlength: 75,
            },
            convicted: {
                required: true
            },
            convictedInfo: {
            	/* required: true */
            	noStartEndSpecialChar: true,
            	startEndSpace: true,
            	
            	 singleSpaceBetweenWords: true,
                 noSpaceAroundDashSlash: true,
                 noConsecutiveSpecialChars: true,
            	 generalFieldsValidation: true,
                 minlength: 3,
                 maxlength: 75,
            },
            futurePlans: {
                required: true,
                noStartEndSpecialChar: true,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noSpaceAroundDashSlash: true,
                noConsecutiveSpecialChars: true,
                generalFieldsValidation: true,
                minlength: 3,
                maxlength: 75,
            },
            govtFinancialAssistance:{
            	 required: true,
            	 noStartEndSpecialChar: true,
                 startEndSpace: true,
                 singleSpaceBetweenWords: true,
                 noSpaceAroundDashSlash: true,
                 noConsecutiveSpecialChars: true,
                 generalFieldsValidation: true,
                 minlength: 3,
                 maxlength: 75,
            },
            undertakingAccepted: {
                required: true
            },
            asstDirRemarks: {
                required: true,
                startEndSpace: true,
                validateRemarks:true,
                minlength: 3,
                maxlength: 500,
              },
              asstDirDecision: {
                required: true
              },
              deskRemarks: {
                required: true,
                startEndSpace: true,
                validateRemarks:true,
                minlength: 3,
                maxlength: 500,
              },
              deskDecision: {
                required: true
              },
              ddHoRemarks: {
                required: true,
                startEndSpace: true,
                validateRemarks:true,
                minlength: 3,
                maxlength: 500,
              },
              ddHoDecision: {
                required: true
              },
        },
        messages: {
            firstName: {
                required: "<liferay-ui:message key='please-enter-first-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            middleName: {
            	 required: "<liferay-ui:message key='please-enter-middle-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            lastName: {
                required: "<liferay-ui:message key='please-enter-last-name' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            gender: {
                required: "<liferay-ui:message key='please-select-gender' />"
            },
            address: {
                required: "<liferay-ui:message key='please-enter-address' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-250-characters-allowed' />"
            },
            contactNo: {
                required: "<liferay-ui:message key='please-enter-contact-number' />",
                pattern: "<liferay-ui:message key='contact-number-must-start-with-678or9' />",
                digits: "<liferay-ui:message key='only-digits-allowed' />",
                minlength: "<liferay-ui:message key='please-enter-10-digit-number' />",
                maxlength: "<liferay-ui:message key='please-enter-10-digit-number' />"
            },
            emailId: {
                required: "<liferay-ui:message key='please-enter-email-id' />",
                email: "<liferay-ui:message key='please-enter-valid-email-id' />"
            },
            attestedCopyUpload: {
                required: "<liferay-ui:message key='please-upload-attested-copy' />"
            },
            constitutionUpload: {
                required: "<liferay-ui:message key='please-upload-constitution-copy' />"
            },
            officersListUpload: {
                required: "<liferay-ui:message key='please-upload-officers-list' />"
            },
            workArea: {
                required: "<liferay-ui:message key='please-enter-work-area' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            workDetailsPast3Years: {
                required: "<liferay-ui:message key='please-enter-work-details' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            newsPaperArticle: {
                required: "<liferay-ui:message key='please-upload-news-paper-article' />"
            },
            areaWorked: {
                required: "<liferay-ui:message key='please-select-area-worked' />",
                	
            },
            areaWorkedInfo: {
                required: "<liferay-ui:message key='please-enter-area-worked-info' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            recognizedByGovt: {
                required: "<liferay-ui:message key='please-select-recognized-by-govt' />"
            },
            recognizedWorkInfo: {
                required: "<liferay-ui:message key='please-enter-recognized-work-info' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            convicted: {
                required: "<liferay-ui:message key='please-select-convicted-status' />"
            },
            convictedInfo: {
                required: "<liferay-ui:message key='please-enter-convicted-info' />"
            },
            futurePlans: {
                required: "<liferay-ui:message key='please-enter-future-plans' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
            },
            undertakingAccepted: {
                required: "<liferay-ui:message key='please-accept-declaration' />"
            },
            asstDirRemarks: {
                required: "<liferay-ui:message key='please-enter-assistant-director-remarks' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-500-characters-allowed' />"
              },
              asstDirDecision: {
                required: "<liferay-ui:message key='please-select-assistant-director-decision' />"
              },
              deskRemarks: {
                required: "<liferay-ui:message key='please-enter-desk-remarks' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-500-characters-allowed' />"
              },
              deskDecision: {
                required: "<liferay-ui:message key='please-select-desk-decision' />"
              },
              ddHoRemarks: {
                required: "<liferay-ui:message key='please-enter-ho-remarks' />",
                minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                maxlength: "<liferay-ui:message key='maximum-500-characters-allowed' />"
              },
              ddHoDecision: {
                required: "<liferay-ui:message key='please-select-ho-decision' />"
              },
              govtFinancialAssistance:{
            	  required: "<liferay-ui:message key='please-enter-govt-financial-assistance' />",
            	  minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                  maxlength: "<liferay-ui:message key='maximum-500-characters-allowed' />"
              }
        },
        errorPlacement: function (error, element) {
            if (element.attr("type") == "radio") {
                error.insertAfter(element.parent().parent());
            } else if (element.attr("type") == "checkbox" && element.hasClass("form-check-input")) {
                error.insertAfter(element.closest('.form-check'));
            } else {
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


function saveAwardYouthOrg(event,formStatus) {
	debugger;
	    var mode = '${mode}';
	    var isRole = ('${isDeskOfficer}' === 'true') || 
        ('${isAssistantDirector}' === 'true') || 
        ('${isHOAdmin}' === 'true');
	    
	    var form = $("#awardYouthOrg")[0];
	    var formData = new FormData(form);
	    formData.append("formStatus",formStatus)
	    console.log(uploadedNewsPaperArticle)
	    
	     uploadedNewsPaperArticle.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingNewsPaperArticle", fileObj.name);
	                formData.append("existingNewsPaperArticleURLs", fileObj.url);
	                formData.append("existingNewsPaperArticleIds", fileObj.id);
	                console.log("News Paper Article ID: " + fileObj.id);
	            } else {
	                formData.append("newsPaperArticle", fileObj.file);
	                formData.append("newsPaperArticleNames", fileObj.name);
	            }
	        }
	    });
	    
	  /*   uploadedNewsPaperArticle.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            formData.append("newsPaperArticle", fileObj.file);
	            formData.append("newsPaperArticleNames", fileObj.name);
	        }
	    }); */
	    
	    
	    if (event) {
	        event.preventDefault();
	    }
	    if($('#awardYouthOrg').valid()){
	    $.ajax({
	        type: "POST",
	        url: '${addAwardYouthOrgURL}', 
	        data:  formData,
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data) {
	            if (!data) {
	                $('#success-application').html("Empty response from server.");
	                $("#saveAwardYouthOrgModal").modal('show');
	                return;
	            }

	            if (typeof data === 'string') {
	                try {
	                    data = JSON.parse(data);
	                    console.log(data)
	                } catch (e) {
	                    console.error("JSON parse error:", e);
	                    $('#success-application').html("Invalid JSON response from server.");
	                    $("#saveAwardYouthOrgModal").modal('show');
	                    return;
	                }
	            }
	            if(isRole){
	            	   var msg = data.status ? '<liferay-ui:message key="award-organization-id-"/>' + ' ' + data.awardYouthOrgId +  ' ' + '<liferay-ui:message key="is-verify-succesfully" />' : 'the-details-failed-to-submit';
	               }
	            else if(formStatus==="Submitted"){
		            var msg = data.status ? '<liferay-ui:message key="award-organization-id-"/>' + ' ' + data.awardYouthOrgId +  ' ' + '<liferay-ui:message key="-is-succesfully-submitted" />' : 'the-details-failed-to-submit';
	               }else if(formStatus==="Draft"){
	            	   var msg = data.status ? '<liferay-ui:message key="award-organization-id-"/>' + ' ' + data.awardYouthOrgId +  ' ' + '<liferay-ui:message key="is-save-as-draft" />' : 'the-details-failed-to-submit';
	               }
	            $('#success-application').html(msg);
	            $("#saveAwardYouthOrgModal").modal('show');
	        }
	    }); 
	    }
}

function validateTabFields(tabId) {
    $('#awardYouthOrg').validate().resetForm();
    let isValid = true;

    const fields = tabValidationRules[tabId] || [];

    fields.forEach((selector) => {
        if (!$(selector).valid()) {
            isValid = false;
        }
    });

    return handleValidationResult(isValid, tabId);
}

function handleValidationResult(isValid, nextTabId) {
    if (isValid) {
        setActiveTab(nextTabId);
        return true;
    } else {
        $('html, body').animate({
            scrollTop: $(".error:visible").first().offset().top - 100
        }, 500);
        return false;
    }
}

function setActiveTab(tabId) {
    $('.nav-link').removeClass('active');
    $('.tab-pane').removeClass('show active');

    $('#' + tabId + '-tab').addClass('active');
    $('#' + tabId).addClass('show active');

    if (typeof scrollToTop === "function") {
        scrollToTop();
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