<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<portlet:resourceURL id="<%=AwardsWebPortletKeys.APPLICATION_VERIFICATION%>"  var="saveSportsApplicationURL" />

<portlet:renderURL var="awardsRedirectURL">
    <portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.AWARDS %>" />
</portlet:renderURL>

<form id="sports_application" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					
					<!-- <div class="card-header d-flex align-item-center justify-content-between">
						<h5><liferay-ui:message key="sports-application"/></h5>						
					</div> -->
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="sports-application" /></h5>
						<div>
						<c:if test="${isSportsDeskOfficer || isDeputyDirector || isAssociation}">
							<a href="/group/guest/verify-award-applications" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a>
						</c:if>
						<c:if test="${!isSportsDeskOfficer && !isDeputyDirector && !isAssociation}">
							<a href="/group/guest/awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a>
						</c:if>
						</div>
					</div>
					
					
					<div class="card-body">
						<div id="dynamicFields">
							<div class="row dynamic-row">
							
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="applicant-name"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="applicantName" id="applicantName" value="${application.userName}" disabled="disabled" />
									</div>
								</div>
							
							
								<!-- Competition Level -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-level"/><sup class="text-danger">*</sup></label>
										<input type="hidden" class="form-control" name="applicationId" id="applicationId" value="${application.awardApplicationId}"/>
										<input type="text" class="form-control" name="competitionLevel" id="competitionLevel" value="${application.competitionLevel}" disabled="disabled" />
										<%-- <select class="form-control" name="competitionLevel" id="competitionLevel">
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="competitionLevel" items="${competitionLevels}">
												<option value="${competitionLevel.competitionLevelMasterId}">${competitionLevel.name_en}</option>
											</c:forEach>
										</select> --%>
									</div>
								</div>
								
								<!-- Competition Name -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-name"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="competitionName" id="competitionName" value="${application.competitionName}" disabled="disabled"/>
									</div>
								</div>

								<!-- Competition Place -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-place"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="competitionPlace" id="competitionPlace" value="${application.competitionPlace}" disabled="disabled"/>
									</div>
								</div>

								<!-- Participation Year -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="participation-year"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="participationYear" id="participationYear" value="${application.participationYear}" disabled="disabled"/>
										<!-- <select class="form-control" name="participationYear" id="participationYear">
											<option value="2023">2023</option>
											<option value="2024">2024</option>
											<option value="2025">2025</option>
											<option value="2026">2026</option>
											<option value="2027">2027</option>
										</select> -->
									</div>
								</div>

								<!-- Sport Name -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="sport-name"/></label>
										<input type="text" class="form-control" name="sportsName" id="sportsName" value="${application.sportsName}" disabled="disabled"/>
									</div>
								</div>
								
								
								
								
								
								
								
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="sports-type"/></label>
										<input type="text" class="form-control" name="sportsType" id="sportsType" value="${application.sportsTypeStr}" disabled="disabled"/>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="sports-competition-level"/></label>
										<input type="text" class="form-control" name="sportsCompetitionLevel" id="sportsCompetitionLevel" value="${application.sportsCompetitionLevelStr}" disabled="disabled"/>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="award-year"/></label>
										<input type="text" class="form-control" name="awardYear" id="awardYear" value="${application.awardYear}" disabled="disabled"/>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="award-name"/></label>
										<input type="text" class="form-control" name="awardName" id="awardName" value="${application.awardNameStr}" disabled="disabled"/>
									</div>
								</div>
								
								
								<%-- <div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="award-points-as-per-gr"/></label>
										<input type="text" class="form-control" name="awardPoints" id="awardPoints" value="${application.points}" disabled="disabled"/>
									</div>
								</div> --%>
								
								
								

								<!-- Medal Received -->
								<c:if test="${application.userType == 'Person'}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="medal-recieved"/></label>
										<input type="text" class="form-control" name="medalRecieved" id="medalRecieved" value="${application.medalRecieved}" disabled="disabled"/>
									</div>
								</div>
								</c:if>
								

								<!-- Category -->
								<c:if test="${application.userType == 'Person'}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="category"/></label>
										<input type="text" class="form-control" name="category" id="category" value="${application.categoryStr}" disabled="disabled"/>
									</div>
								</div>
								</c:if>
								
								
								<!-- Country of Competition -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="country-of-competition"/></label>
										<input type="text" class="form-control" name="countryOfCompetition" id="countryOfCompetition" value="${application.countryOfCompetition}" disabled="disabled"/>
										<!-- <select class="form-control" name="countryOfCompetition" id="countryOfCompetition">
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="India">India</option>
										</select> -->
									</div>
								</div>

								<!-- City of Competition -->
								<%-- <div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="city-of-competition"/></label>
										<input type="text" class="form-control" name="cityOfCompetition" id="cityOfCompetition" value="${application.cityOfCompetition}" disabled="disabled"/>
										<!-- <select class="form-control" name="cityOfCompetition" id="cityOfCompetition">
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="Pune">Pune</option>
											<option value="Mumbai">Mumbai</option>
											<option value="Ahilyanagar">Ahilyanagar</option>
										</select> -->
									</div>
								</div> --%>

								<!-- Competition Start Date -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-start-date"/></label>
										<input type="date" class="form-control" name="competitionStartDate" id="competitionStartDate" value="${application.competitionStartDateStr}" disabled="disabled"/>
									</div>
								</div>

								<!-- Competition End Date -->
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="competition-end-date"/></label>
										<input type="date" class="form-control" name="competitionEndDate" id="competitionEndDate" value="${application.competitionEndDateStr}" disabled="disabled"/>
									</div>
								</div>
								
								
								
								<c:if test="${application.userType == 'Coach'}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-sports-person-participated"/></label>
										<input type="text" class="form-control" name="noOfPerson" id="noOfPerson" value="${application.noOfParticipation}" disabled="disabled"/>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-medals-recieved-to-sports-person"/></label>
										<input type="text" class="form-control" name="noOfMedals" id="noOfMedals" value="${application.noOfMedalRecieved}" disabled="disabled"/>
									</div>
								</div>
								</c:if>
								
								
								

								<!-- Highest Performance -->
								<c:if test="${application.userType == 'Person'}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="highest-performance"/></label>
										<input type="text" class="form-control" name="highestPerformance" id="highestPerformance" value="${application.highestPerformance}" disabled="disabled"/>
									</div>
								</div>
								</c:if>

								
								<!-- <div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="competition-certificate"/></label>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="competitionCertificate" name="competitionCertificate" onchange="handleFileUpload(event, 'competitionCertificate', 'filePreviewContainer', 'filePreviewLink', 'deleteButton')" accept=".pdf">
										<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
									</div>
									
								 Preview and Delete Section
								    <div class="competitionCertificateid d-none mt-3" id="filePreviewContainer">
								        <a class="competitionCertificateCls" id="filePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								        <button type="button" id="deleteButton"  class="dltcompetitionCertificateBtn close" aria-label="Close"  onclick="deleteFile('filePreviewContainer', 'competitionCertificate')" >
								       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								   		</button>
								    </div>
									 
								 </div>
							</div> -->
							<div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="competition-certificate"/></label>
									
									 <div style="margin-top: 10px;">
											 <a  href="${application.certificateFileURL }" target="_blank" style="flex-grow: 1; text-decoration: none;">${application.certificateFileEntryName}</a>
											 <input type="hidden" class="form-control"  name="feesRecieptFileEntryId"  value="${application.certificateId }"/>
									 </div>
									 
								 </div>
							</div>
							
							
							<c:if test="${application.userType == 'Coach'}">
							<!-- <div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="separate-undertaking"/></label>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="undertaking" name="undertaking" onchange="handleFileUpload(event, 'undertaking', 'undertakingfilePreviewContainer', 'undertakingfilePreviewLink', 'undertakingdeleteButton')" accept=".pdf">
										<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
									</div>
									
								 Preview and Delete Section
								    <div class="undertakingid d-none mt-3" id="undertakingfilePreviewContainer">
								        <a class="undertakingCls" id="undertakingfilePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
								        <button type="button" id="undertakingdeleteButton"  class="dltundertakingBtn close" aria-label="Close"  onclick="deleteFile('undertakingfilePreviewContainer', 'undertaking')" >
								       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								   		</button>
								    </div>
									 
								 </div>
							</div> -->
							<div class="col-md-4">
								<div class="form-group">
									<label ><liferay-ui:message key="separate-undertaking"/></label>
									
									 <div style="margin-top: 10px;">
											 <a  href="${application.undertakingFileURL }" target="_blank" style="flex-grow: 1; text-decoration: none;">${application.undertakingFileEntryName}</a>
											 <input type="hidden" class="form-control"  name="feesRecieptFileEntryId"  value="${application.undertakingId }"/>
									 </div>
									 
								 </div>
							</div>
							</c:if>
							
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="award-points-as-per-gr"/></label>
									<input type="text" class="form-control" name="awardPoints" id="awardPoints" value="${application.points}" disabled="disabled"/>
								</div>
							</div>
								
								
								
								<!-- Name of Coach -->
								<c:if test="${application.userType == 'Person'}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="name-of-coach-during-competition"/></label>
										<input type="text" class="form-control" name="coachName" id="coachName" value="${application.coachName}" disabled="disabled"/>
									</div>
								</div>
								</c:if>
								
								
								
							<%-- <c:if test="${isDeskOfficer || isAssociation || isHO}">
							</c:if> --%>
							<c:if test="${isSportsDeskOfficer || not empty application.overallDeskOffRemarks}">

										<div class="col-md-4">
											<div class="form-group">
												<label><liferay-ui:message
														key="is-eligible-for-direct-award" /><sup class="text-danger">*</sup></label>
												<div>
													<label> <input type="radio" name="deskOffRemarks"
														id="deskOffRemarksYes" value="Yes"
														<c:if test="${application.deskOffRemarks == 'Yes'}">checked</c:if>
														<c:if test="${isAssistantDirector || isDeputyDirector || isAssociation || not empty application.deskOffRemarks}">disabled</c:if>
														onchange="disableField()"> Yes
													</label> <label> <input type="radio" name="deskOffRemarks"
														id="deskOffRemarksNo" value="No"
														<c:if test="${application.deskOffRemarks == 'No'}">checked</c:if>
														<c:if test="${isAssistantDirector || isDeputyDirector || isAssociation || not empty application.deskOffRemarks}">disabled</c:if>
														onchange="disableField()"> No
													</label>
												</div>
											</div>
										</div>


										<%-- <div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="remarks-if-any-changes-in-auto-generated-points"/></label>
										<input type="text" class="form-control" name="deskOffRemarks" id="deskOffRemarks" value="${application.deskOffRemarks}"
										<c:if test="${isAssociation || isHO}">disabled</c:if>/>
									</div>
								</div> --%>
								
							
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="awards-points-by-desk-officer"/></label>
										<input type="text" class="form-control" name="awardPointByDeskOff" id="awardPointByDeskOff" value="${application.awardPointByDeskOff}"
										<c:if test="${isAssistantDirector || isAssociation || isDeputyDirector || not empty application.overallDeskOffRemarks}">disabled</c:if> />
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="overall-remark-by-desk-officer"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="overallDeskOffRemarks" id="overallDeskOffRemarks" required="required" value="${application.overallDeskOffRemarks}"
										<c:if test="${isAssistantDirector || isAssociation || isDeputyDirector || not empty application.overallDeskOffRemarks}">disabled</c:if> />
									</div>
								</div>
							</c:if>
							
							
							<c:if test="${isDeputyDirector || isAssistantDirector || not empty application.directorRemarks}">
							
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
										<div>
											<label> 
												<input type="radio" name="directorRemarks" id="directorRemarks" value="Approve"
												<c:if test="${application.directorRemarks == 'Approve'}">checked</c:if>
												<c:if test="${not empty application.directorRemarks}">disabled</c:if>> Approve
											</label> 
											<label> 
												<input type="radio" name="directorRemarks" id="directorRemarks" value="Reject"
												<c:if test="${application.directorRemarks == 'Reject'}">checked</c:if>
												<c:if test="${not empty application.directorRemarks}">disabled</c:if> > Reject
											</label>
										</div>
									</div>
								</div>
								
								
								<div class="col-md-4">
									<div class="form-group">
										<label id="remarksLabel">
											<liferay-ui:message key="director-remarks"/>
										</label>
										
										<input type="text" class="form-control" name="directorComments" id="directorComments" value="${application.directorComments}"
										<c:if test="${isAssistantDirector || isAssociation || not empty application.directorRemarks}">disabled</c:if> />
									</div>
								</div>
							
							</c:if>
							
							
							<%-- <c:if test="${isAssociation || isHO}">
							</c:if> --%>
							
							<c:if test="${isAssociation || not empty application.associationName}">
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="association-name"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="associationName" id="associationName" value="${application.associationName}" 
										<c:if test="${isHO || not empty application.associationName}">disabled</c:if> />
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="name"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="name" id="name" value="${application.name}" 
										<c:if test="${isHO || not empty application.name}">disabled</c:if> />
									</div>
								</div>
								
							<%-- <c:if test="${!isSportsDeskOfficer}">
								<div class="col-md-4">
								    <div class="form-group">
								        <label>
								            <liferay-ui:message key="appointment-letter" />
								            <sup class="text-danger">*</sup>
								            <em class="bi bi-info-circle-fill" title="<liferay-ui:message key="allowed-only-pdf-file" />"></em>
								        </label>
								            View Mode
								                <c:if test="${not empty application.appointmentLetterUrl}">
								                    <div>
								                        <a href="${application.appointmentLetterUrl}" target="_blank" style="flex-grow: 1; text-decoration: none;">
								                            ${application.appointmentLetterName}
								                        </a>
								                    </div>
								                </c:if>
								            Edit Mode
								            <c:if test="${mode eq 'edit'}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="appointmentLetter" name="appointmentLetter"
								                        onchange="handleFileUpload(event, 'appointmentLetter', 'appointmentLetterPreviewContainer', 'appointmentLetterPreviewLink', 'appointmentLetterdeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                Show existing file with delete option if available
								                <c:if test="${not empty application.dprDocumentURL}">
								                    <div class="ownerProofid d-flex mt-3" id="appointmentLetterPreviewContainer">
								                        <a class="appointmentLetterProofCls text-truncate" id="appointmentLetterPreviewLink" 
								                            href="${application.dprDocumentURL}" target="_blank"
								                            style="flex-grow: 1; text-decoration: none;">
								                            ${application.dprDocumentName}
								                        </a>
								                        <button type="button" id="appointmentLetterdeleteButton" class="dltappointmentLetterBtn close"
								                            aria-label="Close" onclick="deleteFile('appointmentLetterPreviewContainer', 'appointmentLetter')">
								                            <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                        </button>
								                    </div>
								                </c:if>
								                Empty container for new file upload preview
								                <div class="ownerProofid d-none mt-3" id="appointmentLetterPreviewContainer">
								                    <a class="appointmentLetterProofCls text-truncate"  id="appointmentLetterNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="appointmentLetterNewDeleteButton" class="dltappointmentLetterBtn close"
								                        aria-label="Close" onclick="deleteFile('appointmentLetterPreviewContainer', 'appointmentLetter')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if>
								            Add/Other Modes
								            <c:if test="${cmd eq 'edit' && empty application.appointmentLetterUrl}">
								                <div class="custom-file">
								                    <input type="file" class="custom-file-input" id="appointmentLetter" name="appointmentLetter"
								                        onchange="handleFileUpload(event, 'appointmentLetter', 'appointmentLetterNewPreviewContainer', 'appointmentLetterNewPreviewLink', 'appointmentLetterNewDeleteButton')"> 
								                    <label class="custom-file-label" for="customFile">
								                        <liferay-ui:message key="choose-file" />
								                    </label>
								                </div>
								                Empty container for new file upload preview
								                <div class="ownerProofid d-none mt-3" id="appointmentLetterNewPreviewContainer">
								                    <a class="appointmentLetterProofCls text-truncate" id="appointmentLetterNewPreviewLink" href="" target="_blank"
								                        style="flex-grow: 1; text-decoration: none;"></a>
								                    <button type="button" id="appointmentLetterNewDeleteButton" class="dltappointmentLetterBtn close"
								                        aria-label="Close" onclick="deleteFile('appointmentLetterNewPreviewContainer', 'appointmentLetter')">
								                        <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								                    </button>
								                </div>
								            </c:if>
								    </div>
								</div>
							</c:if> --%>
								
								
								<c:if test="${isDeskOfficer || not empty application.mainDeskOffVerification}">
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
										<div>
											<label> 
												<input type="radio" name="mainDeskOffVerification" id="mainDeskOffVerification" value="Approve"
												<c:if test="${application.mainDeskOffVerification == 'Approve'}">checked</c:if>
												<c:if test="${not empty application.mainDeskOffVerification}">disabled</c:if>> Approve
											</label> 
											<label> 
												<input type="radio" name="mainDeskOffVerification" id="mainDeskOffVerification" value="Reject"
												<c:if test="${application.mainDeskOffVerification == 'Reject'}">checked</c:if>
												<c:if test="${not empty application.mainDeskOffVerification}">disabled</c:if> > Reject
											</label>
										</div>
									</div>
								</div>
								
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="remarks"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="mainDeskOffRemarks" id="mainDeskOffRemarks" value="${application.mainDeskOffRemarks}" 
										<c:if test="${isHO || not empty application.mainDeskOffVerification}">disabled</c:if> />
									</div>
								</div>
								
							</c:if>
								
								
							<c:if test="${not empty application.overallAssoRemarks || isAssociation}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="association-remarks"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="overallAssoRemarks" id="overallAssoRemarks" value="${application.overallAssoRemarks}" 
										<c:if test="${isHO || not empty application.overallAssoRemarks}">disabled</c:if> />
									</div>
								</div>
							</c:if>
							</c:if>
							
							
							
							<%-- <c:if test="${isHO}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="awards-points-by-ho"/></label>
										<input type="text" class="form-control" name="awardPointByHo" id="awardPointByHo" value="${application.awardPointByHo}"/>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="overall-remark-by-ho"/></label>
										<input type="text" class="form-control" name="overallHoRemarks" id="overallHoRemarks" value="${application.overallHoRemarks}"/>
									</div>
								</div>
							</c:if> --%>
							
							
							
							
							
							</div>
						</div>
					</div>
						<%-- <c:if test="${empty application.deskOffRemarks && empty application.awardPointByDeskOff && empty application.overallDeskOffRemarks}">
							<div class="card-footer bg-transparent text-right p-4">
								<button title="Are you sure you want to submit to the Association/Federation? You are not able to edit it later."
									class="btn btn-primary" type="button" onclick="addSportsApplication(event)"> <liferay-ui:message key="submit" />
								</button>
							</div>
						</c:if> --%>

						<%-- <c:if test="${isDeskOfficer && empty application.deskOffRemarks && empty application.awardPointByDeskOff && empty application.overallDeskOffRemarks}">
						</c:if> --%>
							
						<%-- <c:if test="${isSportsDeskOfficer && empty application.deskOffRemarks && empty application.awardPointByDeskOff && empty application.overallDeskOffRemarks}">
							<div class="card-footer bg-transparent text-right p-4">
								<button
									title="Are you sure you want to submit to the Association/Federation? You are not able to edit it later."
									class="btn btn-primary" type="button"
									onclick="addSportsApplication(event)">
									<liferay-ui:message key="submit" />
								</button>
							</div>
						</c:if> --%>

						<%-- <c:if test="${isAssociation && empty application.awardPointByAsso && empty application.overallAssoRemarks}">
						</c:if> --%>
						<c:if test="${(isDeskOfficer && empty application.mainDeskOffRemarks) || (isAssociation && empty application.overallAssoRemarks) || (isDeputyDirector && empty application.directorRemarks) || (isSportsDeskOfficer && empty application.overallDeskOffRemarks || empty application.deskOffRemarks)}">
							<div class="card-footer bg-transparent text-right p-4">
							
								<c:if test="${!isSportsDeskOfficer && !isDeputyDirector}">
									<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards';">
			                        	<liferay-ui:message key="cancel" />
			                    	</button>
								</c:if>
								
								<c:if test="${isSportsDeskOfficer || isDeputyDirector}">
									<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards';">
			                        	<liferay-ui:message key="cancel" />
			                    	</button>
								</c:if>
		                            		
		                         <button class="btn btn-primary" type="button" onclick="location.reload();">
									<liferay-ui:message key="reset" />
								</button>
							
								<button class="btn btn-primary" type="button"
									onclick="addSportsApplication(event)"> <liferay-ui:message key="submit" />
								</button>
								
							</div>
						</c:if>

					</div>
			</div>
		</div>
	</div>
</div>
</form>





<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="saveAwardsApplication" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="awards-application"/></h5>
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
       					 <a href="${awardsRedirectURL}&type=reviewApplication" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for awards application for sports person -->



<script>

function disableField() {
	const yesRadio = document.getElementById("deskOffRemarksYes");
	const noRadio = document.getElementById("deskOffRemarksNo");
	
	const awardPointField = document.getElementById("awardPointByDeskOff");

	const isDeskOfficer = "${isDeskOfficer}" === "true";
	const isHO = "${isHO}" === "true";

	/* if (isAssociation || isHO) {
	    if (awardPointField) awardPointField.disabled = true;
	    return; 
	}
	
	if (isDeskOfficer && awardPointField && awardPointField.value !== null && awardPointField.value !== '' && noRadio.checked) {
	    awardPointField.disabled = true;
	    return;
	} */


	if (yesRadio && yesRadio.checked) {
		if (awardPointField)
			awardPointField.disabled = true;
			awardPointField.value = ''; 
	} else if (noRadio && noRadio.checked) {
		if (awardPointField) awardPointField.disabled = false;
	}
}


$(document).ready(function () {
	
    $('input[name="directorRemarks"]:checked').trigger('change');
	
    $('input[name="directorRemarks"]').change(function() {
        if ($(this).val() === "Reject") {
            $('input[name="directorComments"]').prop('required', true);
            if($('#remarksLabel sup').length === 0){ 
                $('#remarksLabel').append('<sup class="text-danger">*</sup>');
            }
        } else {
            $('input[name="directorComments"]').prop('required', false);
            $('#remarksLabel sup').remove();
        }
    });
    
    
    
	
	disableField();
	
	const isDeskOfficer = "${isSportsDeskOfficer}" === "true"; 
	const isDeputyDirector = "${isDeputyDirector}" === "true";
	const isAssociation = "${isAssociation}" === "true";
	
	console.log("Desk Officer : "+isDeskOfficer);
	console.log("Association Officer : "+isDeputyDirector);
	
	$('#sports_application').validate({
		
		errorPlacement: function (error, element) {
	        if (element.hasClass("select2-hidden-accessible")) {
	            error.insertAfter(element.next(".select2-container"));
	        } else {
	            error.insertAfter(element);
	        }
	    },
		
	    onkeyup: function (element) {
	        $(element).valid();
	    },
	    onchange: function (element) {
	        $(element).valid();
	    },
	    
	    errorPlacement: function (error, element) {
	        error.addClass("text-danger");
	        element.closest(".form-group").append(error);
	    },
	    
	    rules: function () {
	        const rules = {};

	        if (isDeskOfficer) {
	        	rules.deskOffRemarks = { required: true };
	        	rules.awardPointByDeskOff = {noConsecutiveSpecialChars: true, pattern: /^[1-9][0-9]*(\.[0-9]{1,2})?$/};
	            rules.overallDeskOffRemarks = { required: true, pattern: /^[A-Za-z0-9.,\-\/! ]*$/, startEndSpace: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true,
	            		minlength: 3, maxlength: 75};
	        }
	        if (isDeputyDirector) {
	            rules.directorRemarks = { required: true };
	            rules.directorComments = {startEndSpace: true, noConsecutiveSpecialChars: true, singleSpaceBetweenWords: true, validateRemarks: true};
	        }
	        if (isAssociation) {
	            rules.overallAssoRemarks = { required: true, singleSpaceBetweenWords: true, validateRemarks: true, noConsecutiveSpecialChars: true, startEndSpace: true };
	        }

	        return rules;
	    }(),
	    messages: function () {
	        const messages = {};

	        if (isDeskOfficer) {
	        	 messages.deskOffRemarks = {
	 	                required: "<liferay-ui:message key='please-select-yes-or-no' />"
	 	            };
	        	 messages.awardPointByDeskOff = {
	        			 pattern: '<liferay-ui:message key="please-enter-valid-awards-points" />',
	 	            };
	            messages.overallDeskOffRemarks = {
	                required: "<liferay-ui:message key='please-enter-overall-remarks' />",
	                minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
					maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
	            };
	        }
	        if (isDeputyDirector) {
	            messages.overallAssoRemarks = {
	                required: "<liferay-ui:message key='please-select-approve-or-reject' />"
	            };
	        }
	        if (isAssociation) {
	        	 messages.overallAssoRemarks = {
 	                required: "<liferay-ui:message key='please-enter-remarks' />"
 	            };
	        }

	        return messages;
	    }()
	});
	
	
	$.validator.addMethod("startEndSpace", function(value, element) {
	    // Allow only letters (A-Z, a-z) and spaces
	    return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
	},"<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");
    
	$.validator.addMethod("validateRemarks", function(value, element) {
 	   const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
 	    return this.optional(element) || regex.test(value);
 	}, "<liferay-ui:message key='please-enter-valid-remarks' />");

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
    
	$.validator.addMethod("singleSpaceBetweenWords", function(value) {
        return !/ {2,}/.test(value);
    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');

	
});


function addSportsApplication(event){debugger
		var form = $("#sports_application")[0];
		var formData = new FormData(form);
		
		if (event) {
	        event.preventDefault();
	    } 
		if($("#sports_application").valid()){
	 $.ajax({
	        type: "POST",
	        url: '${saveSportsApplicationURL}' ,
	        data:  formData, 
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data){ 
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
	        	if(data.reviewApplication == true){
	        		var msg = "<liferay-ui:message key="award-application-reviewed-successfully"/>";
	        	    $('#success-application').html(msg);
	        		 $("#saveAwardsApplication").modal('show');
	        	}else{
	        		var msg = "<liferay-ui:message key="award-application-review-failed"/>";
	        	    $('#success-application').html(msg);
	        		 $("#saveAwardsApplication").modal('show'); 
	        	}
	    	 }
	       
	    });
	}
};
	
	
//Single file upload
function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {
    debugger;
    
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
	$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("choose-file"); 
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}
</script>