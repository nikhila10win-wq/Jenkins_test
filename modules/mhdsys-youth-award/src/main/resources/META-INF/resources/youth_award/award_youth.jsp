<%@ include file="/init.jsp" %>

<%@page import="com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys"%>

<style>
#newsPaperArticleError{
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

<portlet:resourceURL id="<%=MhdsysAwardYouthWebPortletKeys.SAVEAWARDYOUTH %>" var="addAwardYouthURL" /> 

<c:set var="commonDTO" value="${not empty awardYouthCommonDTO ? awardYouthCommonDTO : awardYouthOrgCommonDTO}" />

<form id="awardYouth" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
    <div class="container my-5 newTabs">
        <div class="row">
            <div class="border-0 card shadow">
                <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
                    <h5 class="mb-0"><liferay-ui:message key="awards-for-youth" /></h5>	
                        <c:if test="${!isDeskOfficer and !isAssistantDirector and !isHOAdmin}">
                       <div><a href="/group/guest/youth-awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                    </c:if>
                     <c:if test="${isDeskOfficer or isAssistantDirector or isHOAdmin}">
                       <div><a href="/group/guest/awards-youth-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                    </c:if>
                </div>

                <div class="card-body">
                 
                
                 
                    <ul class="nav nav-pills nav-justified shadow-sm mb-5" id="myTab" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active border-0" id="personalDetails-tab" type="button" role="tab"
                                data-bs-toggle="tab" data-bs-target="#personalDetails">
                                <liferay-ui:message key="personal-details" />
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
                        <input type="hidden" name="awardYouthId" id="awardYouthId" value="${awardYouthCommonDTO.awardYouthId}"/>
  						<input type="hidden" class="form-control" id="birthCertificateFileEntryId" name="birthCertificateFileEntryId" value="${empty awardYouthCommonDTO.birthCertificate ? 0 : awardYouthCommonDTO.birthCertificate}" />
  						<input type="hidden" class="form-control" id="socialResponsibilityEntryId" name="socialResponsibilityEntryId" value="${empty awardYouthCommonDTO.socialResponsibility ? 0 : awardYouthCommonDTO.socialResponsibility}" />
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
								                   <c:if test="${mode == 'view'}">disabled</c:if> value="${awardYouthCommonDTO.firstName}"
								                    />
								        </div>
								    </div>
								    
								    <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="middle-name" /><sup class="text-danger">*</sup></label>
								            <input type="text" class="form-control" id="middleName" name="middleName"
								                   placeholder="<liferay-ui:message key='enter-middle-name' />"
								                   <c:if test="${mode == 'view'}">disabled</c:if> value="${awardYouthCommonDTO.middleName}"
								                    />
								        </div>
								    </div>
						
								    <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="last-name" /> <sup class="text-danger">*</sup></label>
								            <input type="text" class="form-control" id="lastName" name="lastName"
								                   placeholder="<liferay-ui:message key='enter-last-name' />"
								                   <c:if test="${mode == 'view'}">disabled</c:if> value="${awardYouthCommonDTO.lastName}"
								                   />
								        </div>
								    </div>
								    
								    <div class="col-md-6">
								    <div class="form-group mb-3">
								        <label>
								            <liferay-ui:message key="gender" /> <sup class="text-danger">*</sup>
								        </label><br />
								        
								        <div class="form-check form-check-inline">
								            <input class="form-check-input" type="radio" name="gender" value="Male"  id="gender"

								                <c:if test="${awardYouthCommonDTO.gender == 'Male'}">checked</c:if>
								                <c:if test="${mode == 'view'}">disabled</c:if> 
								            />
								            <label class="form-check-label" for="gender">
								                <liferay-ui:message key="male" />
								            </label>
								        </div>
								        
								        <div class="form-check form-check-inline">
								            <input  class="form-check-input"  type="radio" name="gender" value="Female" id="gender"
								                <c:if test="${awardYouthCommonDTO.gender == 'Female'}">checked</c:if>
								                <c:if test="${mode == 'view'}">disabled</c:if> 
								            />
								            <label class="form-check-label" for="gender">
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
								               value="${awardYouthCommonDTO.address}"
								               <c:if test="${mode == 'view'}">disabled</c:if> />
								    </div>
								</div>

								
								    <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="contact-number" /><sup class="text-danger">*</sup></label>
								            <input type="text" class="form-control" id="contactNo" name="contactNo"
								                   placeholder="<liferay-ui:message key='enter-contact-number' />"
								                   <c:if test="${mode == 'view'}">disabled</c:if> value="${awardYouthCommonDTO.contactNo}"
								                   />
								        </div>
								    </div>
								
								    <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="email-id" /><sup class="text-danger">*</sup></label>
								            <input type="text" class="form-control" id="emailId" name="emailId"
								                   placeholder="<liferay-ui:message key='enter-email-id' />"
								                   <c:if test="${mode == 'view'}">disabled</c:if> value="${awardYouthCommonDTO.emailId}"
								                   />
								        </div>
								    </div>
								
								    <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="date-of-birth" /><sup class="text-danger">*</sup></label>
								            <input type="date" class="form-control" id="dob" name="dob" value="${awardYouthCommonDTO.dobStr}"
								                   <c:if test="${mode == 'view'}">disabled</c:if> />
								        </div>
								    </div>
								    
								     <div class="col-md-6">
								    <div class="form-group">
								        <label>
								            <liferay-ui:message key="birth-certificate" />
								            <sup class="text-danger">*</sup>
								            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file-of-size-2mb" />"></em>
								        </label>
								            <%-- View Mode --%>
								            <c:if test="${mode eq 'view'}">
								                <c:if test="${not empty awardYouthCommonDTO.birthCertificateURL}">
								                    <div>
								                        <a href="${awardYouthCommonDTO.birthCertificateURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
								                            ${awardYouthCommonDTO.birthCertificateName}
								                        </a>
								                    </div>
								                </c:if>
								            </c:if>
								            <%-- Edit Mode --%>
								            <c:if test="${mode eq 'edit'}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="birthCertificateUpload" name="birthCertificateUpload"
								                        onchange="handleFileUpload(event, 'birthCertificateUpload', 'birthCertificateUploadPreviewContainer', 'birthCertificateUploadPreviewLink', 'birthCertificateUploaddeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                <%-- Show existing file with delete option if available --%>
								                <c:if test="${not empty awardYouthCommonDTO.birthCertificateURL}">
								                    <div class="ownerProofid d-flex mt-3" id="birthCertificateUploadPreviewContainer">
								                        <a class="birthCertificateUploadProofCls" id="birthCertificateUploadPreviewLink" 
								                            href="${awardYouthCommonDTO.birthCertificateURL}" target="_blank"
								                            style="flex-grow: 1; text-decoration: none;">
								                            ${awardYouthCommonDTO.birthCertificateName}
								                        </a>
								                        <button type="button" id="birthCertificateUploaddeleteButton" class="dltbirthCertificateUploadBtn close"
								                            aria-label="Close" onclick="deleteFile('birthCertificateUploadPreviewContainer', 'birthCertificateUpload')">
								                            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                        </button>
								                    </div>
								                </c:if>
								                <%-- Empty container for new file upload preview --%>
								                <div class="ownerProofid d-none mt-3" id="birthCertificateUploadNewPreviewContainer">
								                    <a class="birthCertificateUploadProofCls" id="birthCertificateUploadNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="birthCertificateUploadNewDeleteButton" class="dltbirthCertificateUploadBtn close"
								                        aria-label="Close" onclick="deleteFile('birthCertificateUploadNewPreviewContainer', 'birthCertificateUpload')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if>
								            <%-- Add/Other Modes --%>
								            <c:if test="${mode eq 'add'}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="birthCertificateUpload" name="birthCertificateUpload"
								                        onchange="handleFileUpload(event, 'birthCertificateUpload', 'birthCertificateUploadNewPreviewContainer', 'birthCertificateUploadNewPreviewLink', 'birthCertificateUploadNewDeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                <%-- Empty container for new file upload preview --%>
								                <div class="ownerProofid d-none mt-3" id="birthCertificateUploadNewPreviewContainer">
								                    <a class="birthCertificateUploadProofCls" id="birthCertificateUploadNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="birthCertificateUploadNewDeleteButton" class="dltbirthCertificateUploadBtn close"
								                        aria-label="Close" onclick="deleteFile('birthCertificateUploadNewPreviewContainer', 'birthCertificateUpload')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if>
								    </div>
								</div>
							
								   <%--  <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="birth-certificate" /><sup class="text-danger">*</sup></label>
								            <input type="file" class="form-control" id="birthCertificate" name="birthCertificate"
								                   <c:if test="${mode == 'view'}">disabled</c:if> />
								        </div>
								    </div> --%>
								
								   <div class="col-md-6">
								    <div class="form-group">
								        <label><liferay-ui:message key="education-details" /><sup class="text-danger">*</sup></label>
								        <input type="text" class="form-control" id="educationDetails" name="educationDetails"
								               placeholder="<liferay-ui:message key='enter-education-details' />"
								               value="${awardYouthCommonDTO.educationDetails}"
								               <c:if test="${mode == 'view'}">disabled</c:if> />
								    </div>
								</div>

								
                                    </div>
                                </div>
                            </div>
                            </div>
                            <div class="card-footer bg-transparent text-right p-4">
                            <!--   <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
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
							
							  <button type="button" class="btn btn-primary" onclick="saveAwardYouth(event,'Draft')">
			                        <liferay-ui:message key="save-as-draft" />
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
								        <label><liferay-ui:message key="business-details" /><sup class="text-danger">*</sup></label>
								        <input type="text" class="form-control" name="businessDetails" id="businessDetails" placeholder="<liferay-ui:message key='enter-business-details' />"
								               value="${awardYouthCommonDTO.businessDetails}"
								               <c:if test="${mode == 'view'}">disabled</c:if> />
								    </div>
								</div>
								
								<div class="col-md-6">
								    <div class="form-group">
								        <label><liferay-ui:message key="work-area-(for-youth-development)" /><sup class="text-danger">*</sup></label>
								        <input type="text" class="form-control" name="workArea" id="workArea" placeholder="<liferay-ui:message key='enter-work-area' />"
								               value="${awardYouthCommonDTO.workArea}"
								               <c:if test="${mode == 'view'}">disabled</c:if> />
								    </div>
								</div>

								
								    <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="work-details-past-3-years" /><sup class="text-danger">*</sup></label>
								            <textarea class="form-control" name="workDetailsPast3Years" id="workDetailsPast3Years" placeholder="<liferay-ui:message key="enter-work-details-past-3-years" />"
								                      <c:if test="${mode == 'view'}">disabled</c:if>>${awardYouthCommonDTO.workDetailsPast3Years}</textarea>
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
						                value='<c:if test="${mode eq 'edit' and not empty awardYouthCommonDTO.newsPaperArticleNames}">
						                    <c:forEach var="photoName" items="${awardYouthCommonDTO.newsPaperArticleNames}" varStatus="status">
						                        ${photoName}<c:if test="${not status.last}">,</c:if>
						                    </c:forEach>
						                </c:if>'>
						
						            <!-- Preview and Delete Section -->
						            <div class="mt-3" id="newsPaperArticlePreviewContainer" 
						                style='<c:if test="${mode ne 'edit' or empty awardYouthCommonDTO.newsPaperArticleURLs}">display: none;</c:if>'>
						                <ul id="newsPaperArticlePreviewList" name="newsPaperArticlePreviewList" class="list-group">
						                    <c:if test="${mode eq 'edit' and not empty awardYouthCommonDTO.newsPaperArticleURLs}">
						                        <c:forEach var="photoURL" items="${awardYouthCommonDTO.newsPaperArticleURLs}" varStatus="status">
						                            <li class="list-group-item d-flex justify-content-between align-items-center">
						                                <a href="${photoURL}" target="_blank" class="text-truncate">
						                                    ${awardYouthCommonDTO.newsPaperArticleNames[status.index]}
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
						
						        <c:if test="${mode eq 'view' and not empty awardYouthCommonDTO.newsPaperArticleURLs}">
						            <div>
						                <c:forEach var="photoURL" items="${awardYouthCommonDTO.newsPaperArticleURLs}" varStatus="status">
						                    <a href="${photoURL}" target="_blank" class="text-truncate">
						                        ${awardYouthCommonDTO.newsPaperArticleNames[status.index]}
						                    </a><br>
						                </c:forEach>
						            </div>
						        </c:if>
						    </div>
						</div>

								
								   
														
							<div class="col-md-6">
						    <div class="form-group">
						        <label>
						            <liferay-ui:message key="social-responsibility" />
						            <sup class="text-danger">*</sup>
						            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-pdf-file-of-size-2mb' />"></em>
						        </label>
						
						        <%-- View Mode --%>
						        <c:if test="${mode eq 'view'}">
						            <c:if test="${not empty awardYouthCommonDTO.socialResponsibilityURL}">
						                <div>
						                    <a href="${awardYouthCommonDTO.socialResponsibilityURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
						                        ${awardYouthCommonDTO.socialResponsibilityName}
						                    </a>
						                </div>
						            </c:if>
						        </c:if>
						
						        <%-- Edit Mode --%>
						        <c:if test="${mode eq 'edit'}">
						             <div class="custom-file">
								        <input type="file" class="custom-file-input" id="socialResponsibilityUpload" name="socialResponsibilityUpload"
								            onchange="handleFileUpload(event, 'socialResponsibilityUpload', 'socialResponsibilityUploadNewPreviewContainer', 'socialResponsibilityUploadNewPreviewLink', 'socialResponsibilityUploadNewDeleteButton')">
								        <label class="custom-file-label" for="customFile">
								            <liferay-ui:message key="choose-file" />
								        </label>
								    </div>
						            <%-- Show existing file with delete option if available --%>
						            <c:if test="${not empty awardYouthCommonDTO.socialResponsibilityURL}">
						                <div class="ownerProofid d-flex mt-3" id="socialResponsibilityUploadPreviewContainer">
						                    <a class="socialResponsibilityUploadProofCls" id="socialResponsibilityUploadPreviewLink"
						                        href="${awardYouthCommonDTO.socialResponsibilityURL}" target="_blank"
						                        style="flex-grow: 1; text-decoration: none;">
						                        ${awardYouthCommonDTO.socialResponsibilityName}
						                    </a>
						                    <button type="button" id="socialResponsibilityUploaddeleteButton" class="dltsocialResponsibilityUploadBtn close"
						                        aria-label="Close" onclick="deleteFile('socialResponsibilityUploadPreviewContainer', 'socialResponsibilityUpload')">
						                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						                    </button>
						                </div>
						            </c:if>
						
						            <%-- Empty container for new file upload preview --%>
						            <div class="ownerProofid d-none mt-3" id="socialResponsibilityUploadNewPreviewContainer">
						                <a class="socialResponsibilityUploadProofCls" id="socialResponsibilityUploadNewPreviewLink" href="" target="_blank"
						                    style="flex-grow: 1; text-decoration: none;"></a>
						                <button type="button" id="socialResponsibilityUploadNewDeleteButton" class="dltsocialResponsibilityUploadBtn close"
						                    aria-label="Close" onclick="deleteFile('socialResponsibilityUploadNewPreviewContainer', 'socialResponsibilityUpload')">
						                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						                </button>
						            </div>
						        </c:if>
						
						        <%-- Add/Other Modes --%>
						        <c:if test="${mode eq 'add'}">
						            <div class="custom-file">
						                <input type="file" class="custom-file-input" id="socialResponsibilityUpload" name="socialResponsibilityUpload"
						                    onchange="handleFileUpload(event, 'socialResponsibilityUpload', 'socialResponsibilityUploadNewPreviewContainer', 'socialResponsibilityUploadNewPreviewLink', 'socialResponsibilityUploadNewDeleteButton')">
						                <label class="custom-file-label" for="customFile">
						                    <liferay-ui:message key="choose-file" />
						                </label>
						            </div>
						            <%-- Empty container for new file upload preview --%>
						            <div class="ownerProofid d-none mt-3" id="socialResponsibilityUploadNewPreviewContainer">
						                <a class="socialResponsibilityUploadProofCls" id="socialResponsibilityUploadNewPreviewLink" href="" target="_blank"
						                    style="flex-grow: 1; text-decoration: none;"></a>
						                <button type="button" id="socialResponsibilityUploadNewDeleteButton" class="dltsocialResponsibilityUploadBtn close"
						                    aria-label="Close" onclick="deleteFile('socialResponsibilityUploadNewPreviewContainer', 'socialResponsibilityUpload')">
						                    <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
						                </button>
						            </div>
						        </c:if>
						    </div>
						</div>


								
								    <div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="area-worked" /><sup class="text-danger">*</sup></label>
									        <select class="form-control" name="areaWorked" id="areaWorked"
									                <c:if test="${mode == 'view'}">disabled</c:if>>
									            <option value=""><liferay-ui:message key="select" /></option>
									            <option value="Rural" <c:if test="${awardYouthCommonDTO.areaWorked == 'Rural'}">selected</c:if>>Rural</option>
									            <option value="Urban" <c:if test="${awardYouthCommonDTO.areaWorked == 'Urban'}">selected</c:if>>Urban</option>
									            <option value="Schedule caste and tribes" <c:if test="${awardYouthCommonDTO.areaWorked == 'Schedule caste and tribes'}">selected</c:if>>Schedule caste and tribes</option>
									            <option value="Slum-remote areas" <c:if test="${awardYouthCommonDTO.areaWorked == 'Slum-remote areas'}">selected</c:if>>Slum-remote areas</option>
									        </select>
									    </div>
									</div>

								
								    <div class="col-md-6">
								    <div class="form-group">
								        <label><liferay-ui:message key="area-worked-info" /><sup class="text-danger">*</sup></label>
								        <input type="text" class="form-control" name="areaWorkedInfo" id="areaWorkedInfo" placeholder="<liferay-ui:message key='enter-area-worked-info' />"
								               value="${awardYouthCommonDTO.areaWorkedInfo}"
								               <c:if test="${mode == 'view'}">disabled</c:if> />
								    </div>
								</div>

								
								    <div class="col-md-6">
								    <div class="form-group mb-3">
								        <label><liferay-ui:message key="recognized-by-govt" /><sup class="text-danger">*</sup></label><br />
								
								        <div class="form-check form-check-inline">
								            <input class="form-check-input" type="radio" name="recognizedByGovt" id="recognizedByGovtState"
								                   value="State Govt"
								                   <c:if test="${awardYouthCommonDTO.recognizedByGovt == 'State Govt'}">checked</c:if>
								                   <c:if test="${mode == 'view'}">disabled</c:if> />
								            <label class="form-check-label" for="recognizedByGovtState">State Govt</label>
								        </div>
								
								        <div class="form-check form-check-inline">
								            <input class="form-check-input" type="radio" name="recognizedByGovt" id="recognizedByGovtCentral"
								                   value="Central Govt"
								                   <c:if test="${awardYouthCommonDTO.recognizedByGovt == 'Central Govt'}">checked</c:if>
								                   <c:if test="${mode == 'view'}">disabled</c:if> />
								            <label class="form-check-label" for="recognizedByGovtCentral">Central Govt</label>
								        </div>
								
								    </div>
								</div>

								
								   <div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="recognized-work-info" /></label>
									        <input type="text" class="form-control" name="recognizedWorkInfo" id="recognizedWorkInfo" placeholder="<liferay-ui:message key='enter-recognized-work-info' />"
									               value="${awardYouthCommonDTO.recognizedWorkInfo}"
									               <c:if test="${mode == 'view'}">disabled</c:if> />
									    </div>
									</div>

								   <div class="col-md-6">
										    <div class="form-group mb-1">
										        <label><liferay-ui:message key="staff-of" /><sup class="text-danger">*</sup></label><br />
										
										        <div class="form-check form-check-inline">
										            <input class="form-check-input" type="radio" name="staffOf" id="staffOfCentral"
										                   value="Central Govt"
										                   <c:if test="${awardYouthCommonDTO.staffOf == 'Central Govt'}">checked</c:if>
										                   <c:if test="${mode == 'view'}">disabled</c:if> />
										            <label class="form-check-label" for="staffOfCentral">Central Govt</label>
										        </div>
										
										        <div class="form-check form-check-inline">
										            <input class="form-check-input" type="radio" name="staffOf" id="staffOfState"
										                   value="State Govt"
										                   <c:if test="${awardYouthCommonDTO.staffOf == 'State Govt'}">checked</c:if>
										                   <c:if test="${mode == 'view'}">disabled</c:if> />
										            <label class="form-check-label" for="staffOfState">State Govt</label>
										        </div>
										
										        <div class="form-check form-check-inline">
										            <input class="form-check-input" type="radio" name="staffOf" id="staffOfSemi"
										                   value="Semi Govt Offices"
										                   <c:if test="${awardYouthCommonDTO.staffOf == 'Semi Govt Offices'}">checked</c:if>
										                   <c:if test="${mode == 'view'}">disabled</c:if> />
										            <label class="form-check-label" for="staffOfSemi">Semi Govt Offices</label>
										        </div>
										
										        <div class="form-check form-check-inline">
										            <input class="form-check-input" type="radio" name="staffOf" id="staffOfClg"
										                   value="Clgs under Govt Universities"
										                   <c:if test="${awardYouthCommonDTO.staffOf == 'Clgs under Govt Universities'}">checked</c:if>
										                   <c:if test="${mode == 'view'}">disabled</c:if> />
										            <label class="form-check-label" for="staffOfClg">Clgs under Govt Universities</label>
										        </div>
										
										    </div>
										</div>

								
								   <div class="col-md-6">
								    <div class="form-group">
								        <label><liferay-ui:message key="staff-affiliation-info" /></label>
								        <input type="text" class="form-control" name="staffAffiliationInfo" id="staffAffiliationInfo" placeholder="<liferay-ui:message key='enter-staff-affiliation-info' />"
								               value="${awardYouthCommonDTO.staffAffiliationInfo}"
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
							                    <c:if test="${awardYouthCommonDTO.convicted == 'Yes'}">checked</c:if>
							                    <c:if test="${mode == 'view'}">disabled</c:if> />
							               Yes
							            </label>
							            <label>
							                <input type="radio" name="convicted" value="No"
							                    <c:if test="${awardYouthCommonDTO.convicted == 'No'}">checked</c:if>
							                    <c:if test="${mode == 'view'}">disabled</c:if> />
							                No
							            </label>
							        </div>
							    </div>
							</div>

								
								    <div class="col-md-6">
									    <div class="form-group">
									        <label><liferay-ui:message key="convicted-info" /></label>
									        <input type="text" class="form-control" name="convictedInfo" id="convictedInfo" placeholder="<liferay-ui:message key='enter-convicted-info' />"
									               value="${awardYouthCommonDTO.convictedInfo}"
									               <c:if test="${mode == 'view'}">disabled</c:if> />
									    </div>
									</div>

								
								   <div class="col-md-6">
								    <div class="form-group">
								        <label><liferay-ui:message key="future-plans-for-youth-welfare" /><sup class="text-danger">*</sup></label>
								        <input type="text" class="form-control" name="futurePlans" id="futurePlans" placeholder="<liferay-ui:message key='enter-future-plans-for-youth-welfare' />"
								               value="${awardYouthCommonDTO.futurePlans}"
								               <c:if test="${mode == 'view'}">disabled</c:if> />
								    </div>
								</div>


                               <div class="col-md-12">
							    <div class="form-group form-check">
							        <input 
							            type="checkbox" 
							            class="form-check-input" 
							            id="undertakingAccepted" 
							            name="undertakingAccepted"
							            <c:if test="${mode == 'view'}">disabled</c:if>
							            <c:if test="${awardYouthCommonDTO.undertakingAccepted}">checked</c:if>
							        />
							        <label class="form-check-label" for="undertakingAccepted">
							            <liferay-ui:message key="declaration-statement" /><sup class="text-danger">*</sup>
							        </label>
							    </div>
							</div>

                         
								
								</div>
								                                       
                                    </div>
                                </div>
                            </div>
                            
                          
                            
                            <c:if test="${isDeskOfficer or isAssistantDirector or isHOAdmin or not empty awardYouthCommonDTO.deskRemarks}">
                             <%@ include file="/youth_award/desk_officer_decision.jsp"%>
                             </c:if>
                              <c:if test="${isAssistantDirector or isHOAdmin or not empty awardYouthCommonDTO.asstDirRemarks}">
                            <%@ include file="/youth_award/assistant_director_decision.jsp"%>
                            </c:if>
                             <c:if test="${isHOAdmin or not empty awardYouthCommonDTO.ddHoRemarks}">
                            <%@ include file="/youth_award/ddHo_decision.jsp"%>
                            </c:if>
                            
                           
                               
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
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
			                  
			                   <button type="button" class="btn btn-primary" onclick="saveAwardYouth(event,'Draft')">
			                        <liferay-ui:message key="save-as-draft" />
			                    </button>
			                  
			                   <button type="button" class="btn btn-primary" onclick="setActiveTab('personalDetails')">
                                    <liferay-ui:message key="previous" />
                                </button>
			                  
			                    <button type="button" class="btn btn-primary" onclick="saveAwardYouth(event,'Submitted')">
			                        <liferay-ui:message key="submit" />
			                    </button>
			                    </c:if>
			                    
			                   <c:if test="${mode eq 'view' and ( (isDeskOfficer and empty awardYouthCommonDTO.deskRemarks) 
                                or (isAssistantDirector and empty awardYouthCommonDTO.asstDirRemarks) 
                                or (isHOAdmin and empty awardYouthCommonDTO.ddHoRemarks) )}">
                                 <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards-youth-list';">
                                <liferay-ui:message key="cancel" />
                            </button> 
                             <button class="btn btn-primary" type="button"
								onclick="location.reload();">
								<liferay-ui:message key="reset" />
							</button>
			                   <button type="button" class="btn btn-secondary" onclick="saveAwardYouth(event,'Submitted')" >
			                        <liferay-ui:message key="verify" />
			                    </button>
			                    </c:if>
			                    </div>
			                </div>
                        </div>

                    </div> <!-- tab-content -->
                </div> <!-- card-body -->

             
            </div>
        </div>
    </div>
</div>
</form>

<!-- modal popup for establishment -->
<div class="modal fade" id="saveAwardYouthModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="awards-youth"/></h5>
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
					 <a href="/group/guest/dashboard" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveAwardYouthModal','/group/guest/dashboard')"><liferay-ui:message key="close"/></a>
					    <%--  <c:if test="${isSportsPerson }">
					      <a href="/group/guest/dashboard" type="button" id="y" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveAwardYouthModal','/group/guest/dashboard')"><liferay-ui:message key="close"/></a>
					     </c:if>
       					 <c:if test="${isDDD }">
					      <a href="/group/guest/application-certificate-verification-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveAwardYouthModal','/group/guest/application-certificate-verification-list')"><liferay-ui:message key="close"/></a>
					     </c:if> --%>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for establishment -->

<script>

const tabValidationRules = {
		workDetails: [
	        "#firstName", "#middleName", "#lastName", "#gender", "#address",
	        "#contactNo", "#emailId", "#dob", "#birthCertificateUpload", "#educationDetails"
	    ]
	};

// Single file upload
function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {
    debugger;
    
    if(id==="attestedCopyUpload"){
    	$("#birthCertificateFileEntryId").val(0);
    }else if(id==="socialResponsibilityUpload"){
    	$("#socialResponsibilityEntryId").val(0);
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
          //  whiteSpace: "normal",
          //  overflow: "hidden",
         //   maxWidth: "200px"
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
    <c:if test="${mode eq 'edit' and not empty awardYouthCommonDTO.newsPaperArticleNames}">
    uploadedNewsPaperArticle = [
	        <c:forEach var="photoName" items="${awardYouthCommonDTO.newsPaperArticleNames}" varStatus="status">
	            { 
	                name: '${photoName}', 
	                isExisting: true,
	                url: '${awardYouthCommonDTO.newsPaperArticleURLs[status.index]}',
	                id: '${awardYouthCommonDTO.newsPaperArticleEntryIds[status.index]}', 
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
       // fileLink.style.cssText = "flex-grow: 1; text-decoration: none; word-wrap: break-word; white-space: nowrap; overflow: hidden; max-width: 200px;";

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
	
    var mode = '${mode}';
    console.log(mode);
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
	        case 'workDetails-tab':
	            if (!validateTabFields('workDetails')) return e.preventDefault();
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
    
   

        $('#awardYouth').validate({
            onkeyup: function (element) {
                $(element).valid();
            },
            onchange: function (element) {
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
                dob: {
                    required: true,
                    validateDOB:10,
                },
                birthCertificateUpload: {
                    required: function () {
                    	return $("#birthCertificateFileEntryId").val().trim()==="0";
                       /*  return $("#birthCertificateFileEntryId").val().trim() === ""; */
                    },
                    pdfOnly: true,
                    maxFileSize: true
                },
                educationDetails: {
                    required: true,
                    noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                businessDetails: {
                    required: true,
                    noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                workArea: {
                    required: true,
                    noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                workDetailsPast3Years: {
                    required: true,
                    noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 500
                },
                 socialResponsibilityUpload: {
                	 required: function() {
                		    return $("#socialResponsibilityEntryId").val().trim()==="0";
                	      /*   return $("#socialResponsibilityEntryId").val() !== ""; */
                	    },
                	    pdfOnly: true,
                        maxFileSize: true
                }, 
                areaWorked: {
                    required: true
                },
                areaWorkedInfo: {
                	 required: true,
                	 noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                recognizedByGovt: {
                    required: true
                },
                recognizedWorkInfo: {
                	/* required: true, */
                	 noStartEndSpecialChar: true,
                    startEndSpace: true,
                   
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                staffOf: {
                    required: true
                },
                staffAffiliationInfo: {
                	/* required: true, */
                	noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                convicted: {
                    required: true
                },
                convictedInfo: {
                	/* required: true, */
                	 noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                futurePlans: {
                	required: true,
                	 noStartEndSpecialChar: true,
                    startEndSpace: true,
                    minlength: 3,
                    singleSpaceBetweenWords: true,
                    noSpaceAroundDashSlash: true,
                    noConsecutiveSpecialChars: true,
                    generalFieldsValidation:true,
                    maxlength: 75
                },
                undertakingAccepted:{
                	required: true,
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
                dob: {
                    required: "<liferay-ui:message key='please-enter-date-of-birth' />"
                },
                birthCertificateUpload: {
                    required: "<liferay-ui:message key='please-upload-birth-certificate' />"
                },
                educationDetails: {
                    required: "<liferay-ui:message key='please-enter-education-details' />",
                    	 minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                         maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
                },
                businessDetails: {
                    required: "<liferay-ui:message key='please-enter-business-details' />",
                    	 minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                         maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
                },
                workArea: {
                    required: "<liferay-ui:message key='please-enter-work-area' />",
                    minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                    maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
                },
                workDetailsPast3Years: {
                    required: "<liferay-ui:message key='please-enter-work-details-past-3-years' />",
                    minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                    maxlength: "<liferay-ui:message key='maximum-500-characters-allowed' />"
                },
                socialResponsibilityUpload: {
                    required: "<liferay-ui:message key='please-enter-social-responsibility' />"
                },
                areaWorked: {
                    required: "<liferay-ui:message key='please-select-area-worked' />"
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
                   /*  required: "<liferay-ui:message key='please-enter-recognized-work-info' />" */
                	minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                    maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
                },
                staffOf: {
                    required: "<liferay-ui:message key='please-select-staff-of' />"
                },
                staffAffiliationInfo: {
                   /*  required: "<liferay-ui:message key='please-enter-staff-affiliation-info' />" */
                	minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                    maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
                },
                convicted: {
                    required: "<liferay-ui:message key='please-select-convicted-status' />"
                },
                convictedInfo: {
                    /* required: "<liferay-ui:message key='please-enter-convicted-info' />" */
                	minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                    maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
                },
                futurePlans: {
                    required: "<liferay-ui:message key='please-enter-future-plans' />",
                    minlength: "<liferay-ui:message key='minimum-3-characters-required' />",
                    maxlength: "<liferay-ui:message key='maximum-75-characters-allowed' />"
                },
                undertakingAccepted:{
                	required: "<liferay-ui:message key='please-accept-declaration' />",
                },
                deskRemarks: {
                    required: "<liferay-ui:message key='please-enter-desk-remarks' />"
                  },
                  deskDecision: {
                    required: "<liferay-ui:message key='please-select-desk-decision' />"
                  },
                asstDirRemarks: {
                    required: "<liferay-ui:message key='please-enter-assistant-director-remarks' />"
                  },
                  asstDirDecision: {
                    required: "<liferay-ui:message key='please-select-assistant-director-decision' />"
                  },
                  ddHoRemarks: {
                    required: "<liferay-ui:message key='please-enter-ho-remarks' />"
                  },
                  ddHoDecision: {
                    required: "<liferay-ui:message key='please-select-ho-decision' />"
                  },
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
            return this.optional(element) || (/^(?!0+$)(?!.* {2})(?!.*([,.\-\/#])\1)[A-Za-z0-9]+(?:[ ]?[A-Za-z0-9]+|[ ]?[,.\-\/#][ ]?[A-Za-z0-9]+)*$/i.test(value));
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
   	}, "<liferay-ui:message key='no-space-around-hyphen-or-slash-allowed'/>");
   	
   	$.validator.addMethod("noStartEndSpecialChar", function (value, element) {
   	    return this.optional(element) || !/^[.,\/\-]|[.,\/\-]$/.test(value);
   	}, "<liferay-ui:message key='should-not-start-or-end-with-special-characters' />");

   	
   	
});


function saveAwardYouth (event,status) {
	debugger;
		 var mode = '${mode}';
		  var isRole = ('${isDeskOfficer}' === 'true') || 
	     ('${isAssistantDirector}' === 'true') || 
	     ('${isHOAdmin}' === 'true');
	    var form = $("#awardYouth")[0];
	    var formData = new FormData(form);
	    formData.append("formStatus",status);

	  
	    
	    
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

	    
	    
	    /*  console.log(uploadedNewsPaperArticle)
	    uploadedNewsPaperArticle.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            formData.append("newsPaperArticle", fileObj.file);
	            formData.append("newsPaperArticleNames", fileObj.name);
	        }
	    });  */

	    if (event) {
	        event.preventDefault();
	    }
	    
	    console.log("$('#awardYouth').valid()------->",$('#awardYouth').valid())
	    
	    if($('#awardYouth').valid()){
	    $.ajax({
	        type: "POST",
	        url: '${addAwardYouthURL}', 
	        data:  formData,
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data) {
	            if (!data) {
	                $('#success-application').html("Empty response from server.");
	                $("#saveAwardYouthModal").modal('show');
	                return;
	            }

	            if (typeof data === 'string') {
	                try {
	                    data = JSON.parse(data);
	                    console.log(data)
	                } catch (e) {
	                    console.error("JSON parse error:", e);
	                    $('#success-application').html("Invalid JSON response from server.");
	                    $("#saveAwardYouthModal").modal('show');
	                    return;
	                }
	            }
	            
	            if(isRole){
	            	   var msg = data.status ? '<liferay-ui:message key="award-youth-id-"/>' + ' ' + data.awardYouthId +  ' ' + '<liferay-ui:message key="is-verify-succesfully" />' : 'the-details-failed-to-submit';
	               }
	            else if(status==="Submitted"){
	            var msg = data.status ? '<liferay-ui:message key="award-youth-id-"/>' + ' ' + data.awardYouthId +  ' ' + '<liferay-ui:message key="-is-succesfully-submitted" />' : 'the-details-failed-to-submit';
               }else if(status==="Draft"){
            	   var msg = data.status ? '<liferay-ui:message key="award-youth-id-"/>' + ' ' + data.awardYouthId +  ' ' + '<liferay-ui:message key="is-save-as-draft" />' : 'the-details-failed-to-submit';
               }
	            $('#success-application').html(msg);
	            $("#saveAwardYouthModal").modal('show');
	        }
	    });
	    
	    }
	    
}

function validateTabFields(tabId) {
    $('#awardYouth').validate().resetForm();
    let isValid = true;
    console.log("tabId::",tabId)
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

function closeModal(id,redirectUrl) {
    $("#"+id).modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
	 if (redirectUrl) {
	        window.location.href = redirectUrl;
	    }
}

</script>
