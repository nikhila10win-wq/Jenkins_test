<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>

<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="competition-details" />
		</div>
		<div class="card-body">
			<div class="row">
			
			<div class="col-md-6">
				  <div class="form-group">
				    <label>
				      <liferay-ui:message key="competition-level" />
				      <sup class="text-danger">*</sup>
				    </label>
				    <input
				      type="text"
				      class="form-control competitionLevel"
				      name="competitionLevel"
				      id="competitionLevel"
				      <%-- value="${competitionLevel}" --%>
				      readonly
				    />
				  </div>
				</div>
			
			
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-level" /><sup class="text-danger">*</sup></label>
						<select class="form-control" name="competitionLevel" id="competitionLevel" readonly >
						 <option value="${competitionLevel}">${competitionLevel}</option>
							<option value=""><liferay-ui:message key="select" /></option>
							<option value="International"
								<c:if test="${intSportsComp.competitionLevel eq 'International'}">selected</c:if>><liferay-ui:message key="international" /></option>
							<option value="National"
								<c:if test="${intSportsComp.competitionLevel eq 'National'}">selected</c:if>><liferay-ui:message key="national" /></option>
							<option value="State"
								<c:if test="${intSportsComp.competitionLevel eq 'State'}">selected</c:if>><liferay-ui:message key="state" /></option>
						</select>
					</div>
				</div> --%>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-name" /><sup class="text-danger">*</sup></label> 
						<select onchange="getCompetitionDetails()" class="form-control competitionName" name="competitionName" id="competitionName" <c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
								<c:forEach var="awardApplication" items="${awardApplications }">
							<option value="${awardApplication.competitionName}">${awardApplication.competitionName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="col-md-6">
	              <div class="form-group">
				    <label>
				      <liferay-ui:message key="competition-year" /><sup class="text-danger">*</sup>
				    </label>
				    <input type="text" class="form-control competitionYear" name="competitionYear"
				           id="competitionYear" 
				           <%-- value="${awardApplication.participationYear}" --%>
				           readonly />
				  </div>
			  </div>
				
				
			<%-- 	<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-year" /><sup class="text-danger">*</sup></label>
						<select class="form-control" name="competitionYear" id="competitionYear" readonly>
						
						   <option value="${awardApplication.participationYear}">${awardApplication.participationYear}</option>
							<option value=""><liferay-ui:message key="select" /></option>
							<option value="2022-23"
								<c:if test="${intSportsComp.competitionYear eq '2022-23'}">selected</c:if>><liferay-ui:message key="2022-23" /></option>
							<option value="2023-24"
								<c:if test="${intSportsComp.competitionYear eq '2023-24'}">selected</c:if>><liferay-ui:message key="2023-24" /></option>
							<option value="2024-25"
								<c:if test="${intSportsComp.competitionYear eq '2024-25'}">selected</c:if>><liferay-ui:message key="2024-25" /></option>
						</select>
					</div>
				</div> --%>
				
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="selection-letter" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty intSportsComp.selectionLetterURL}">
								<div>
									<a href="${intSportsComp.selectionLetterURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.selectionLetterName} 
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="selectionLetter" name="selectionLetter"
									onchange="handleFileUpload(event, 'selectionLetter', 'selectionLetterNewPreviewContainer', 'selectionLetterNewPreviewLink', 'selectionLetterNewDeleteButton')">
								<label class="custom-file-label" for="selectionLetter">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty intSportsComp.selectionLetterURL}">
								<div class="ownerProofid d-flex mt-3" id="selectionLetterPreviewContainer">
									<a class="selectionLetterProofCls" id="selectionLetterPreviewLink"
										href="${intSportsComp.selectionLetterURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.selectionLetterName} 
									</a>
									<button type="button" id="selectionLetterdeleteButton" class="dltselectionLetterBtn close" aria-label="Close"
										onclick="deleteFile('selectionLetterPreviewContainer', 'selectionLetter')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="selectionLetterNewPreviewContainer">
								<a class="selectionLetterProofCls" id="selectionLetterNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="selectionLetterNewDeleteButton" class="dltselectionLetterBtn close" aria-label="Close"
									onclick="deleteFile('selectionLetterNewPreviewContainer', 'selectionLetter')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="selectionLetter" name="selectionLetter"
									onchange="handleFileUpload(event, 'selectionLetter', 'selectionLetterNewPreviewContainer', 'selectionLetterNewPreviewLink', 'selectionLetterNewDeleteButton')">
								<label class="custom-file-label" for="selectionLetter"> 
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3" id="selectionLetterNewPreviewContainer">
								<a class="selectionLetterProofCls" id="selectionLetterNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="selectionLetterNewDeleteButton" class="dltselectionLetterBtn close" aria-label="Close"
									onclick="deleteFile('selectionLetterNewPreviewContainer', 'selectionLetter')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
					</div>
				</div>
				
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-date" /><sup class="text-danger">*</sup></label> 
							<input type="Date" class="form-control competitionDate" name="competitionDate" id="competitionDate" readonly
							<%-- value="${competitionDate}"  --%>
							/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="place-of-competition" /><sup class="text-danger ">*</sup></label> 
							<input type="text" class="form-control competitionPlace" name="competitionPlace" id="competitionPlace" readonly
							<%-- value="${awardApplication.competitionPlace}" --%> 
							/>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-performance" /><sup class="text-danger">*</sup></label> 
							<input type="text" class="form-control competitionPerformance" name="competitionPerformance" id="competitionPerformance"  readonly
						   <%--  value="${awardApplication.highestPerformance}"  --%>
						    />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-rank" /><sup class="text-danger">*</sup></label>
						<select class="form-control" name="competitionRank" id="competitionRank" <c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
							<option value="Rank 1"
								<c:if test="${intSportsComp.competitionRank eq 'Rank 1'}">selected</c:if>><liferay-ui:message key="rank-1" /></option>
							<option value="Rank 2"
								<c:if test="${intSportsComp.competitionRank eq 'Rank 2'}">selected</c:if>><liferay-ui:message key="rank-2" /></option>
							<option value="Rank 3"
								<c:if test="${intSportsComp.competitionRank eq 'Rank 3'}">selected</c:if>><liferay-ui:message key="rank-3" /></option>
						</select>
					</div>
				</div>
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="competition-certificate" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill" title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty intSportsComp.competitionCertificateURL}">
								<div>
									<a href="${intSportsComp.competitionCertificateURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.competitionCertificateName} 
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="competitionCertificate" name="competitionCertificate"
									onchange="handleFileUpload(event, 'competitionCertificate', 'competitionCertificateNewPreviewContainer', 'competitionCertificateNewPreviewLink', 'competitionCertificateNewDeleteButton')">
								<label class="custom-file-label" for="competitionCertificate">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<%-- Existing file display (if uploaded before) --%>
							<c:if test="${not empty intSportsComp.competitionCertificateURL}">
								<div class="ownerProofid d-flex mt-3" id="competitionCertificatePreviewContainer">
									<a class="competitionCertificateProofCls" id="competitionCertificatePreviewLink"
										href="${intSportsComp.competitionCertificateURL}" target="_blank" style="flex-grow: 1; text-decoration: none;">
										${intSportsComp.competitionCertificateName} 
									</a>
									<button type="button" id="competitionCertificatedeleteButton" class="dltcompetitionCertificateBtn close" aria-label="Close"
										onclick="deleteFile('competitionCertificatePreviewContainer', 'competitionCertificate')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<%-- New file preview (after selection) --%>
							<div class="ownerProofid d-none mt-3" id="competitionCertificateNewPreviewContainer">
								<a class="competitionCertificateProofCls" id="competitionCertificateNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="competitionCertificateNewDeleteButton" class="dltcompetitionCertificateBtn close" aria-label="Close"
									onclick="deleteFile('competitionCertificateNewPreviewContainer', 'competitionCertificate')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="competitionCertificate" name="competitionCertificate"
									onchange="handleFileUpload(event, 'competitionCertificate', 'competitionCertificateNewPreviewContainer', 'competitionCertificateNewPreviewLink', 'competitionCertificateNewDeleteButton')">
								<label class="custom-file-label" for="competitionCertificate"> 
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
							<%-- Empty container for new file upload preview --%>
							<div class="ownerProofid d-none mt-3" id="competitionCertificateNewPreviewContainer">
								<a class="competitionCertificateProofCls" id="competitionCertificateNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="competitionCertificateNewDeleteButton" class="dltcompetitionCertificateBtn close" aria-label="Close"
									onclick="deleteFile('competitionCertificateNewPreviewContainer', 'competitionCertificate')">
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

<script>

</script> 