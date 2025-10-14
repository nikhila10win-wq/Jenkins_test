<%@ include file="/init.jsp"%>
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
				      class="form-control competitionLevelA"
				      name="competitionLevelA"
				      id="competitionLevelA"
				      <%-- value="${competitionLevel}" --%>
				      readonly
				    />
				  </div>
				</div>
			
			
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="competition-level" /><sup class="text-danger">*</sup></label>
						<select class="form-control" name="competitionLevelA"
							id="competitionLevelA"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
							<option value="Category 1"
								<c:if test="${awardWinner.sportsPerformanceDetails eq 'State'}">selected</c:if>>1</option>
							<option value="Category 2"
								<c:if test="${awardWinner.sportsPerformanceDetails eq 'National'}">selected</c:if>>2</option>
							<option value="Category 3"
								<c:if test="${awardWinner.sportsPerformanceDetails eq 'International'}">selected</c:if>>3</option>
						</select>
					</div>
				</div> --%>

				<div class="col-md-6">
				  <div class="form-group">
				    <label>
				      <liferay-ui:message key="competition-name" />
				      <sup class="text-danger">*</sup>
				    </label>
				    <select onchange="getCompetitionDetails()" class="form-control competitionNameA" name="competitionNameA" id="competitionNameA" <c:if test="${mode eq 'view'}">disabled</c:if>>
								<option value=""><liferay-ui:message key="select" /></option>
									<c:forEach var="awardApplication" items="${awardApplications }">
								<option value="${awardApplication.competitionName}">${awardApplication.competitionName}</option>
								</c:forEach>
							</select>
				  </div>
				</div>
				
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="competition-year" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control competitionYearA" name="competitionYearA" id="competitionYearA"
							readonly
							<%-- value="${awardApplication.participationYear}" --%> />
					</div>
				</div>

				<div class="col-md-6">
					<div class="form-group">
						<label>
							<liferay-ui:message key="competition-draw" />
							<sup class="text-danger">*</sup>
							<em class="bi bi-info-circle-fill"
								title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty awardWinner.competitionDrawFileURL}">
								<div>
									<a href="${awardWinner.competitionDrawFileURL}"
										target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.competitionDrawFileName}
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="competitionDrawFile" name="competitionDrawFile"
									onchange="handleFileUpload(event, 'competitionDrawFile', 'competitionDrawFileNewPreviewContainer', 'competitionDrawFileNewPreviewLink', 'competitionDrawFileNewDeleteButton')">
								<label class="custom-file-label" for="competitionDrawFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<c:if test="${not empty awardWinner.competitionDrawFileURL}">
								<div class="ownerProofid d-flex mt-3"
									id="competitionDrawFilePreviewContainer">
									<a class="competitionDrawFileProofCls"
										id="competitionDrawFilePreviewLink"
										href="${awardWinner.competitionDrawFileURL}"
										target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.competitionDrawFileName}
									</a>
									<button type="button" id="competitionDrawFiledeleteButton"
										class="dltcompetitionDrawFileBtn close" aria-label="Close"
										onclick="deleteFile('competitionDrawFilePreviewContainer', 'competitionDrawFile')">
										<span aria-hidden="true" class="text-danger"><em
											class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<div class="ownerProofid d-none mt-3"
								id="competitionDrawFileNewPreviewContainer">
								<a class="competitionDrawFileProofCls"
									id="competitionDrawFileNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="competitionDrawFileNewDeleteButton"
									class="dltcompetitionDrawFileBtn close" aria-label="Close"
									onclick="deleteFile('competitionDrawFileNewPreviewContainer', 'competitionDrawFile')">
									<span aria-hidden="true" class="text-danger"><em
										class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="competitionDrawFile" name="competitionDrawFile"
									onchange="handleFileUpload(event, 'competitionDrawFile', 'competitionDrawFileNewPreviewContainer', 'competitionDrawFileNewPreviewLink', 'competitionDrawFileNewDeleteButton')">
								<label class="custom-file-label" for="competitionDrawFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<div class="ownerProofid d-none mt-3"
								id="competitionDrawFileNewPreviewContainer">
								<a class="competitionDrawFileProofCls"
									id="competitionDrawFileNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="competitionDrawFileNewDeleteButton"
									class="dltcompetitionDrawFileBtn close" aria-label="Close"
									onclick="deleteFile('competitionDrawFileNewPreviewContainer', 'competitionDrawFile')">
									<span aria-hidden="true" class="text-danger"><em
										class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
					</div>
				</div>

				
				
					<div class="col-md-6">
				<div class="form-group">
					<label>
						<liferay-ui:message key="organization-selection-letter" />
						<sup class="text-danger">*</sup>
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardWinner.organizationSelectionLetterFileURL}">
							<div>
								<a href="${awardWinner.organizationSelectionLetterFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.organizationSelectionLetterFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="organizationSelectionLetterFile" name="organizationSelectionLetterFile"
								onchange="handleFileUpload(event, 'organizationSelectionLetterFile', 'organizationSelectionLetterFileNewPreviewContainer', 'organizationSelectionLetterFileNewPreviewLink', 'organizationSelectionLetterFileNewDeleteButton')">
							<label class="custom-file-label" for="organizationSelectionLetterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<c:if test="${not empty awardWinner.organizationSelectionLetterFileURL}">
							<div class="ownerProofid d-flex mt-3"
								id="organizationSelectionLetterFilePreviewContainer">
								<a class="organizationSelectionLetterFileProofCls"
									id="organizationSelectionLetterFilePreviewLink"
									href="${awardWinner.organizationSelectionLetterFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.organizationSelectionLetterFileName}
								</a>
								<button type="button" id="organizationSelectionLetterFiledeleteButton"
									class="dltorganizationSelectionLetterFileBtn close" aria-label="Close"
									onclick="deleteFile('organizationSelectionLetterFilePreviewContainer', 'organizationSelectionLetterFile')">
									<span aria-hidden="true" class="text-danger"><em
										class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
			
						<div class="ownerProofid d-none mt-3"
							id="organizationSelectionLetterFileNewPreviewContainer">
							<a class="organizationSelectionLetterFileProofCls"
								id="organizationSelectionLetterFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="organizationSelectionLetterFileNewDeleteButton"
								class="dltorganizationSelectionLetterFileBtn close" aria-label="Close"
								onclick="deleteFile('organizationSelectionLetterFileNewPreviewContainer', 'organizationSelectionLetterFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
			
					<%-- Add/Other Modes --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="organizationSelectionLetterFile" name="organizationSelectionLetterFile"
								onchange="handleFileUpload(event, 'organizationSelectionLetterFile', 'organizationSelectionLetterFileNewPreviewContainer', 'organizationSelectionLetterFileNewPreviewLink', 'organizationSelectionLetterFileNewDeleteButton')">
							<label class="custom-file-label" for="organizationSelectionLetterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<div class="ownerProofid d-none mt-3"
							id="organizationSelectionLetterFileNewPreviewContainer">
							<a class="organizationSelectionLetterFileProofCls"
								id="organizationSelectionLetterFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="organizationSelectionLetterFileNewDeleteButton"
								class="dltorganizationSelectionLetterFileBtn close" aria-label="Close"
								onclick="deleteFile('organizationSelectionLetterFileNewPreviewContainer', 'organizationSelectionLetterFile')">
								<span aria-hidden="true" class="text-danger"><em
									class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
				</div>
			</div>

				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-date" /><sup
							class="text-danger">*</sup></label> <input type="date"
							class="form-control competitionDateA" name="competitionDateA" id="competitionDateA"
							readonly
							<%-- value="${competitionDate}" --%> />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="place-of-competition" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control placeOfCompetitionA" name="placeOfCompetitionA" id="placeOfCompetitionA"
							readonly
							<%-- value="${awardApplication.competitionPlace}" --%> />
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-performance" /><sup
							class="text-danger">*</sup></label> <input type="text"
							class="form-control competitionPerformanceA" name="competitionPerformanceA" id="competitionPerformanceA"
							readonly
							<%-- value="${awardApplication.highestPerformance}" --%> />
					</div>
				</div>
				
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message
								key="competition-rank" /><sup class="text-danger">*</sup></label>
						<select class="form-control" name="competitionRankA"
							id="competitionRankA"
							<c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
							<option value="Category 1"
								<c:if test="${awardWinner.sportsPerformanceDetails eq 'State'}">selected</c:if>>1</option>
							<option value="Category 2"
								<c:if test="${awardWinner.sportsPerformanceDetails eq 'National'}">selected</c:if>>2</option>
							<option value="Category 3"
								<c:if test="${awardWinner.sportsPerformanceDetails eq 'International'}">selected</c:if>>3</option>
						</select>
					</div>
				</div> --%>
				
				<div class="col-md-6">
					<div class="form-group">
						<label><liferay-ui:message key="competition-rank" /><sup class="text-danger">*</sup></label>
						<select class="form-control" name="competitionRankA" id="competitionRankA" <c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
							<option value="Rank 1"
								<c:if test="${awardWinner.competitionRank eq 'Rank 1'}">selected</c:if>><liferay-ui:message key="rank-1" /></option>
							<option value="Rank 2"
								<c:if test="${awardWinner.competitionRank eq 'Rank 2'}">selected</c:if>><liferay-ui:message key="rank-2" /></option>
							<option value="Rank 3"
								<c:if test="${awardWinner.competitionRank eq 'Rank 3'}">selected</c:if>><liferay-ui:message key="rank-3" /></option>
						</select>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label> 
							<liferay-ui:message key="competition-result" /> 
							<sup class="text-danger">*</sup> 
							<em class="bi bi-info-circle-fill"
								title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
						</label>
				
						<%-- View Mode --%>
						<c:if test="${mode eq 'view'}">
							<c:if test="${not empty awardWinner.competitionResultFileURL}">
								<div>
									<a href="${awardWinner.competitionResultFileURL}"
										target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.competitionResultFileName}
									</a>
								</div>
							</c:if>
						</c:if>
				
						<%-- Edit Mode --%>
						<c:if test="${mode eq 'edit'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="competitionResultFile" name="competitionResultFile"
									onchange="handleFileUpload(event, 'competitionResultFile', 'competitionResultFileNewPreviewContainer', 'competitionResultFileNewPreviewLink', 'competitionResultFileNewDeleteButton')">
								<label class="custom-file-label" for="competitionResultFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<c:if test="${not empty awardWinner.competitionResultFileURL}">
								<div class="ownerProofid d-flex mt-3" id="competitionResultFilePreviewContainer">
									<a class="competitionResultFileProofCls"
										id="competitionResultFilePreviewLink"
										href="${awardWinner.competitionResultFileURL}"
										target="_blank" style="flex-grow: 1; text-decoration: none;">
										${awardWinner.competitionResultFileName}
									</a>
									<button type="button" id="competitionResultFiledeleteButton"
										class="dltcompetitionResultFileBtn close" aria-label="Close"
										onclick="deleteFile('competitionResultFilePreviewContainer', 'competitionResultFile')">
										<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									</button>
								</div>
							</c:if>
				
							<div class="ownerProofid d-none mt-3" id="competitionResultFileNewPreviewContainer">
								<a class="competitionResultFileProofCls"
									id="competitionResultFileNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="competitionResultFileNewDeleteButton"
									class="dltcompetitionResultFileBtn close" aria-label="Close"
									onclick="deleteFile('competitionResultFileNewPreviewContainer', 'competitionResultFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
				
						<%-- Add/Other Modes --%>
						<c:if test="${mode eq 'add'}">
							<div class="custom-file">
								<input type="file" class="custom-file-input"
									id="competitionResultFile" name="competitionResultFile"
									onchange="handleFileUpload(event, 'competitionResultFile', 'competitionResultFileNewPreviewContainer', 'competitionResultFileNewPreviewLink', 'competitionResultFileNewDeleteButton')">
								<label class="custom-file-label" for="competitionResultFile">
									<liferay-ui:message key="choose-file" />
								</label>
							</div>
				
							<div class="ownerProofid d-none mt-3" id="competitionResultFileNewPreviewContainer">
								<a class="competitionResultFileProofCls"
									id="competitionResultFileNewPreviewLink" href="" target="_blank"
									style="flex-grow: 1; text-decoration: none;"></a>
								<button type="button" id="competitionResultFileNewDeleteButton"
									class="dltcompetitionResultFileBtn close" aria-label="Close"
									onclick="deleteFile('competitionResultFileNewPreviewContainer', 'competitionResultFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
					</div>
				</div>

				
				<div class="col-md-6">
				<div class="form-group">
					<label> 
						<liferay-ui:message key="competition-certificate" /> 
						<sup class="text-danger">*</sup> 
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardWinner.competitionCertificateFileURL}">
							<div>
								<a href="${awardWinner.competitionCertificateFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.competitionCertificateFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="competitionCertificateFile" name="competitionCertificateFile"
								onchange="handleFileUpload(event, 'competitionCertificateFile', 'competitionCertificateFileNewPreviewContainer', 'competitionCertificateFileNewPreviewLink', 'competitionCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="competitionCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<c:if test="${not empty awardWinner.competitionCertificateFileURL}">
							<div class="ownerProofid d-flex mt-3" id="competitionCertificateFilePreviewContainer">
								<a class="competitionCertificateFileProofCls"
									id="competitionCertificateFilePreviewLink"
									href="${awardWinner.competitionCertificateFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardWinner.competitionCertificateFileName}
								</a>
								<button type="button" id="competitionCertificateFiledeleteButton"
									class="dltcompetitionCertificateFileBtn close" aria-label="Close"
									onclick="deleteFile('competitionCertificateFilePreviewContainer', 'competitionCertificateFile')">
									<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
								</button>
							</div>
						</c:if>
			
						<div class="ownerProofid d-none mt-3" id="competitionCertificateFileNewPreviewContainer">
							<a class="competitionCertificateFileProofCls"
								id="competitionCertificateFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="competitionCertificateFileNewDeleteButton"
								class="dltcompetitionCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('competitionCertificateFileNewPreviewContainer', 'competitionCertificateFile')">
								<span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
							</button>
						</div>
					</c:if>
			
					<%-- Add/Other Modes --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="competitionCertificateFile" name="competitionCertificateFile"
								onchange="handleFileUpload(event, 'competitionCertificateFile', 'competitionCertificateFileNewPreviewContainer', 'competitionCertificateFileNewPreviewLink', 'competitionCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="competitionCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<div class="ownerProofid d-none mt-3" id="competitionCertificateFileNewPreviewContainer">
							<a class="competitionCertificateFileProofCls"
								id="competitionCertificateFileNewPreviewLink" href="" target="_blank"
								style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="competitionCertificateFileNewDeleteButton"
								class="dltcompetitionCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('competitionCertificateFileNewPreviewContainer', 'competitionCertificateFile')">
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