<%@ include file="/init.jsp"%>
<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>
<%-- <portlet:resourceURL id="<%=MhdsysGrantsAndSchemesPortletKeys.GET_COMPETITION_DETAILS_MVC_RESOURCE%>" var="getCompetitionDetailsURL" /> 
 --%>

<div class="card card-background p-0">
	<div class="">
		<div class="card-header header-card">
			<liferay-ui:message key="competition-details" />
		</div>
		<div class="card-body">
			<div class="row">
				
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="sport-name" /><sup class="text-danger">*</sup></label>
			        <input type="text" class="form-control sportName" name="sportName" id="sportName" value="" readonly/>
			    </div>
			</div>
			
			
			<div class="col-md-6">
			    <div class="form-group">
			        <label for="sportType">
			            <liferay-ui:message key="sport-type" />
			            <sup class="text-danger">*</sup>
			        </label>
			        <input 
			            type="text" 
			            class="form-control sportType" 
			            name="sportType" 
			            id="sportType" 
<%-- 			           value="${sportType}"
 --%>			            readonly />
			    </div>
			</div>

			
			
			<div class="col-md-6">
			  <div class="form-group">
			    <label>
			      <liferay-ui:message key="competition-level" />
			      <sup class="text-danger">*</sup>
			    </label>
			    <input
			      type="text"
			      class="form-control competitionLevelntCmp"
			      name="competitionLevel"
			      id="competitionLevel"
			     <%--  value="${competitionLevel}" --%>
			      readonly
			    />
			  </div>
			</div>
			
			
			<!-- <div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="competition-level" /><sup class="text-danger">*</sup></label>
			        <select class="form-control" name="competitionLevel" id="competitionLevel">
			            <option value=""><liferay-ui:message key="select" /></option>
			            <option value="State">State</option>
			            <option value="National">National</option>
			            <option value="International">International</option>
			        </select>
			    </div>
			</div> -->
			
			<%-- 4. Competition Name (Dropdown) [Required] --%>
			
			<div class="col-md-6">
			  <div class="form-group">
			    <label>
			      <liferay-ui:message key="competition-name" />
			      <sup class="text-danger">*</sup>
			    </label>
			    <select onchange="getCompetitionDetails()" class="form-control competitionNameAsp" name="competitionNameAsp" id="competitionName" <c:if test="${mode eq 'view'}">disabled</c:if>>
							<option value=""><liferay-ui:message key="select" /></option>
								<c:forEach var="awardApplication" items="${awardApplications }">
							<option value="${awardApplication.competitionName}">${awardApplication.competitionName}</option>
							</c:forEach>
						</select>
			  </div>
			</div>
			
			
			<%-- <div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="competition-name" /><sup class="text-danger">*</sup></label>
			        <select class="form-control" name="competitionName" id="competitionName">
			            <option value=""><liferay-ui:message key="select" /></option>
			             <option value="Compitition 1">Compitition 1</option>
			            Populate dynamically from master data
			        </select>
			    </div>
			</div> --%>
			
			<%-- 5. Place of Competition (Text Field) [Required] --%>
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="place-of-competition" /><sup class="text-danger">*</sup></label>
			        <input type="text" class="form-control placeOfCompetitionIntCmp" name="placeOfCompetition" id="placeOfCompetition" <%-- value="${awardApplication.competitionPlace}" --%> readonly />
			    </div>
			</div>
			
			<%-- 6. Competition Date (Calendar) [Required] --%>
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="competition-date" /><sup class="text-danger">*</sup></label>
			        <input type="date" class="form-control competitionDateIntCmp" name="competitionDate" id="competitionDate" <%-- value="${competitionDate}" --%> readonly />
			    </div>
			</div>
			
			<%-- 7. Competition Rank (Dropdown) [Required] --%>
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="competition-rank" /><sup class="text-danger">*</sup></label>
			        <select class="form-control" name="competitionRank" id="competitionRank">
			            <option value=""><liferay-ui:message key="select" /></option>
			            <option value="1st">1st</option>
			            <option value="2nd">2nd</option>
			            <option value="3rd">3rd</option>
			            <option value="Participation">Participation</option>
			        </select>
			    </div>
			</div>
			
			
			<div class="col-md-6">
			    <div class="form-group mb-3">
			        <label><liferay-ui:message key="medal-achieved-last-3-years" /><sup class="text-danger">*</sup></label><br>
			        <div class="form-check form-check-inline">
			            <input class="form-check-input" type="radio" name="medalAchieved" id="medalYes" value="yes">
			            <label class="form-check-label" for="medalYes">Yes</label>
			        </div>
			        <div class="form-check form-check-inline">
			            <input class="form-check-input" type="radio" name="medalAchieved" id="medalNo" value="no">
			            <label class="form-check-label" for="medalNo">No</label>
			        </div>
			    </div>
			</div>
			</div>
			<div class="row" id="previousCompetitionHidden" style="display:none">
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="previous-competition-name" /></label>
			        <input type="text" class="form-control" name="prevCompetitionName" id="prevCompetitionName" value="${dto.prevCompetitionName}" />
			    </div>
			</div>
			
			<%-- 10. Previous Place of Competition (Text Field) [Optional] --%>
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="previous-place-of-competition" /></label>
			        <input type="text" class="form-control" name="prevPlace" id="prevPlace" value="${dto.prevPlace}" />
			    </div>
			</div>
			
			
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="competition-year-past-3-years" /></label>
			        <input type="number" class="form-control pastCompetitionYear" name="pastCompetitionYear" id="pastCompetitionYear" value="${dto.pastCompetitionYear}" />
			    </div>
			</div>
			
			
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="competition-date-past-3-years" /></label>
			        <input type="date" class="form-control" name="pastCompetitionDate" id="pastCompetitionDate" value="${dto.pastCompetitionDate}" />
			    </div>
			</div>
			
			<%-- 13. Competition Rank (Past) [Optional] --%>
			<div class="col-md-6">
			    <div class="form-group">
			        <label><liferay-ui:message key="competition-rank-past" /></label>
			        <select class="form-control" name="pastCompetitionRank" id="pastCompetitionRank">
			            <option value=""><liferay-ui:message key="select" /></option>
			            <option value="1st">1st</option>
			            <option value="2nd">2nd</option>
			            <option value="3rd">3rd</option>
			            <option value="Participation">Participation</option>
			        </select>
			    </div>
			</div>
			
			<div class="col-md-6">
				<div class="form-group">
					<label>
						<liferay-ui:message key="selection-letter" />
						
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardYouthOrgCommonDTO.selectionLetterFileURL}">
							<div>
								<a href="${awardYouthOrgCommonDTO.selectionLetterFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.selectionLetterFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="selectionLetterFile" name="selectionLetterFile"
								onchange="handleFileUpload(event, 'selectionLetterFile', 'selectionLetterFileNewPreviewContainer', 'selectionLetterFileNewPreviewLink', 'selectionLetterFileNewDeleteButton')">
							<label class="custom-file-label" for="selectionLetterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display (if uploaded before) --%>
						<c:if test="${not empty awardYouthOrgCommonDTO.selectionLetterFileURL}">
							<div class="ownerProofid d-flex mt-3" id="selectionLetterFilePreviewContainer">
								<a class="selectionLetterFileProofCls" id="selectionLetterFilePreviewLink"
									href="${awardYouthOrgCommonDTO.selectionLetterFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.selectionLetterFileName}
								</a>
								<button type="button" id="selectionLetterFileDeleteButton"
									class="dltselectionLetterFileBtn close" aria-label="Close"
									onclick="deleteFile('selectionLetterFilePreviewContainer', 'selectionLetterFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview (after selection) --%>
						<div class="ownerProofid d-none mt-3" id="selectionLetterFileNewPreviewContainer">
							<a class="selectionLetterFileProofCls" id="selectionLetterFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="selectionLetterFileNewDeleteButton"
								class="dltselectionLetterFileBtn close" aria-label="Close"
								onclick="deleteFile('selectionLetterFileNewPreviewContainer', 'selectionLetterFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
			
					<%-- Add Mode --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="selectionLetterFile" name="selectionLetterFile"
								onchange="handleFileUpload(event, 'selectionLetterFile', 'selectionLetterFileNewPreviewContainer', 'selectionLetterFileNewPreviewLink', 'selectionLetterFileNewDeleteButton')">
							<label class="custom-file-label" for="selectionLetterFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- New file preview --%>
						<div class="ownerProofid d-none mt-3" id="selectionLetterFileNewPreviewContainer">
							<a class="selectionLetterFileProofCls" id="selectionLetterFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="selectionLetterFileNewDeleteButton"
								class="dltselectionLetterFileBtn close" aria-label="Close"
								onclick="deleteFile('selectionLetterFileNewPreviewContainer', 'selectionLetterFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
				</div>
			</div>
			
			
			<div class="col-md-6">
				<div class="form-group">
					<label>
						<liferay-ui:message key="selection-certificate" />
						
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardYouthOrgCommonDTO.selectionCertificateFileURL}">
							<div>
								<a href="${awardYouthOrgCommonDTO.selectionCertificateFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.selectionCertificateFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="selectionCertificateFile" name="selectionCertificateFile"
								onchange="handleFileUpload(event, 'selectionCertificateFile', 'selectionCertificateFileNewPreviewContainer', 'selectionCertificateFileNewPreviewLink', 'selectionCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="selectionCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- Existing file display --%>
						<c:if test="${not empty awardYouthOrgCommonDTO.selectionCertificateFileURL}">
							<div class="ownerProofid d-flex mt-3" id="selectionCertificateFilePreviewContainer">
								<a class="selectionCertificateFileProofCls" id="selectionCertificateFilePreviewLink"
									href="${awardYouthOrgCommonDTO.selectionCertificateFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.selectionCertificateFileName}
								</a>
								<button type="button" id="selectionCertificateFileDeleteButton"
									class="dltselectionCertificateFileBtn close" aria-label="Close"
									onclick="deleteFile('selectionCertificateFilePreviewContainer', 'selectionCertificateFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<%-- New file preview --%>
						<div class="ownerProofid d-none mt-3" id="selectionCertificateFileNewPreviewContainer">
							<a class="selectionCertificateFileProofCls" id="selectionCertificateFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="selectionCertificateFileNewDeleteButton"
								class="dltselectionCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('selectionCertificateFileNewPreviewContainer', 'selectionCertificateFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
			
					<%-- Add Mode --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="selectionCertificateFile" name="selectionCertificateFile"
								onchange="handleFileUpload(event, 'selectionCertificateFile', 'selectionCertificateFileNewPreviewContainer', 'selectionCertificateFileNewPreviewLink', 'selectionCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="selectionCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<%-- New file preview container --%>
						<div class="ownerProofid d-none mt-3" id="selectionCertificateFileNewPreviewContainer">
							<a class="selectionCertificateFileProofCls" id="selectionCertificateFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="selectionCertificateFileNewDeleteButton"
								class="dltselectionCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('selectionCertificateFileNewPreviewContainer', 'selectionCertificateFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="form-group">
					<label>
						<liferay-ui:message key="new-record-certificate" />
						
						<em class="bi bi-info-circle-fill"
							title="<liferay-ui:message key='allowed-only-jpg-jpeg-png-pdf-file' />"></em>
					</label>
			
					<%-- View Mode --%>
					<c:if test="${mode eq 'view'}">
						<c:if test="${not empty awardYouthOrgCommonDTO.newRecordCertificateFileURL}">
							<div>
								<a href="${awardYouthOrgCommonDTO.newRecordCertificateFileURL}"
									target="_blank" style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.newRecordCertificateFileName}
								</a>
							</div>
						</c:if>
					</c:if>
			
					<%-- Edit Mode --%>
					<c:if test="${mode eq 'edit'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="newRecordCertificateFile" name="newRecordCertificateFile"
								onchange="handleFileUpload(event, 'newRecordCertificateFile', 'newRecordCertificateFileNewPreviewContainer', 'newRecordCertificateFileNewPreviewLink', 'newRecordCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="newRecordCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<c:if test="${not empty awardYouthOrgCommonDTO.newRecordCertificateFileURL}">
							<div class="ownerProofid d-flex mt-3" id="newRecordCertificateFilePreviewContainer">
								<a class="newRecordCertificateFileProofCls" id="newRecordCertificateFilePreviewLink"
									href="${awardYouthOrgCommonDTO.newRecordCertificateFileURL}" target="_blank"
									style="flex-grow: 1; text-decoration: none;">
									${awardYouthOrgCommonDTO.newRecordCertificateFileName}
								</a>
								<button type="button" id="newRecordCertificateFileDeleteButton"
									class="dltnewRecordCertificateFileBtn close" aria-label="Close"
									onclick="deleteFile('newRecordCertificateFilePreviewContainer', 'newRecordCertificateFile')">
									<span aria-hidden="true" class="text-danger">
										<em class="bi bi-x-circle-fill"></em>
									</span>
								</button>
							</div>
						</c:if>
			
						<div class="ownerProofid d-none mt-3" id="newRecordCertificateFileNewPreviewContainer">
							<a class="newRecordCertificateFileProofCls" id="newRecordCertificateFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="newRecordCertificateFileNewDeleteButton"
								class="dltnewRecordCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('newRecordCertificateFileNewPreviewContainer', 'newRecordCertificateFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
			
					<%-- Add Mode --%>
					<c:if test="${mode eq 'add'}">
						<div class="custom-file">
							<input type="file" class="custom-file-input"
								id="newRecordCertificateFile" name="newRecordCertificateFile"
								onchange="handleFileUpload(event, 'newRecordCertificateFile', 'newRecordCertificateFileNewPreviewContainer', 'newRecordCertificateFileNewPreviewLink', 'newRecordCertificateFileNewDeleteButton')">
							<label class="custom-file-label" for="newRecordCertificateFile">
								<liferay-ui:message key="choose-file" />
							</label>
						</div>
			
						<div class="ownerProofid d-none mt-3" id="newRecordCertificateFileNewPreviewContainer">
							<a class="newRecordCertificateFileProofCls" id="newRecordCertificateFileNewPreviewLink"
								href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
							<button type="button" id="newRecordCertificateFileNewDeleteButton"
								class="dltnewRecordCertificateFileBtn close" aria-label="Close"
								onclick="deleteFile('newRecordCertificateFileNewPreviewContainer', 'newRecordCertificateFile')">
								<span aria-hidden="true" class="text-danger">
									<em class="bi bi-x-circle-fill"></em>
								</span>
							</button>
						</div>
					</c:if>
				</div>
			</div>
			
			
			
			<div class="col-md-6">
			    <div class="form-group mb-3">
			        <label><liferay-ui:message key="granted-non-granted-sport" /></label><br>
			        <div class="form-check form-check-inline">
			            <input class="form-check-input" type="radio" name="grantedSport" id="grantedYes" value="yes">
			            <label class="form-check-label" for="grantedYes">Yes</label>
			        </div>
			        <div class="form-check form-check-inline">
			            <input class="form-check-input" type="radio" name="grantedSport" id="grantedNo" value="no">
			            <label class="form-check-label" for="grantedNo">No</label>
			        </div>
			    </div>
			</div>
			
			
			<div class="col-md-6">
			    <div class="form-group">
			        <label>
			        <liferay-ui:message key="sport-name" />
			       <!--  <liferay-ui:message key="sports-name-if-granted" /> -->
			        </label>
			        <input type="text" class="form-control" name="grantedSportName" id="grantedSportName" value="${sportName}" readonly/>
			    </div>
			</div>
				</div>
          
			</div>
		</div>
	</div>
