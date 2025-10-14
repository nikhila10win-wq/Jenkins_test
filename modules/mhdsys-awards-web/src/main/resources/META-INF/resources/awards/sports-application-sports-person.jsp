<%@ include file="/init.jsp" %>

<!-- <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.19.5/jquery.validate.min.js"></script> -->

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5>
						<c:if test="${userType eq 'awardsApplicationSportsPerson'}">
								<liferay-ui:message key="sport-person-competition-registration" />
						</c:if>
						
						<c:if test="${userType eq 'awardsApplicationCoach'}">
								<liferay-ui:message key="sport-coach-competition-registration" />
						</c:if>
						</h5>
						<div><a href="/group/guest/awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
					
					<form id="sport-person-coach-reg" enctype="multipart/form-data">
						<!--hidden fields  -->
							<input type="hidden" class="form-control" name="userType" id="userType" value="${userType}" />
							<input type="hidden" name="applicationId" id="applicationId" value="" />
							<input type="hidden" class="form-control" name="status" id="status" />
							<input type="hidden" class="form-control" name="competitionCertificateFileEntyId" id="competitionCertificateFileEntyId" />
							<input type="hidden" class="form-control" name="seperateUndertakingFileEntyId" id="seperateUndertakingFileEntyId" />
						<!--hidden fields  -->
						<div class="card-body">
					<div class="card card-background p-0">
					 <div class="card-header header-card">
					 	<c:if test="${userType eq 'awardsApplicationSportsPerson'}">
								<liferay-ui:message key="sport-person-competition-registration" />
						</c:if>
						
						<c:if test="${userType eq 'awardsApplicationCoach'}">
								<liferay-ui:message key="sport-coach-competition-registration" />
						</c:if>
					 </div>
					 <div class="card-body">
							<div class="row">
								<c:if test="${userType eq 'awardsApplicationCoach'}">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="sport-person-name" /><sup class="text-danger">*</sup></label>
												 <input type="text" class="form-control" name="sportpersonName" id="sportpersonName" />
										</div>
									</div>
								</c:if>
							
								<div class="col-md-6">
									<div class="form-group">
									<c:if test="${roleName eq 'Adventure Person'}">
										<label><liferay-ui:message key="campaign-level" /><sup class="text-danger">*</sup></label>
									</c:if>	
									<c:if test="${roleName ne 'Adventure Person'}">
										<label><liferay-ui:message key="competition-level" /><sup class="text-danger">*</sup></label>
									</c:if>	
										<select class="form-control" name="competitionLevel" id="competitionLevel">
											<option value=""><liferay-ui:message key="select" /></option>
											<c:forEach var="competitionLevel" items="${competitionLevels}">
												<option value="${competitionLevel.competitionLevelMasterId}">${competitionLevel.name_en}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<!-- Competition Name -->
								<div class="col-md-6">
									<div class="form-group">
									<c:if test="${roleName eq 'Adventure Person'}">
										<label><liferay-ui:message key="campaign-name" /><sup class="text-danger">*</sup></label>
									</c:if>	
									<c:if test="${roleName ne 'Adventure Person'}">
										<label><liferay-ui:message key="competition-name" /><sup class="text-danger">*</sup></label>
									</c:if>	
										 <input type="text" class="form-control" name="competitionName" id="competitionName" />
									</div>
								</div>
                             </div>
                             
                             <div class="row">
								<!-- Competition Place -->
								<div class="col-md-6">
									<div class="form-group">
									<c:if test="${roleName eq 'Adventure Person'}">
										<label><liferay-ui:message key="campaign-place" /><sup class="text-danger">*</sup></label>
									</c:if>	
									<c:if test="${roleName ne 'Adventure Person'}">
										<label><liferay-ui:message key="competition-place" /><sup class="text-danger">*</sup></label>
									</c:if>	
										 <input type="text" class="form-control" name="competitionPlace" id="competitionPlace" />
									</div>
								</div>
								
								<!-- Participation Year -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="participation-year" /><sup class="text-danger">*</sup></label> 
									<c:if test="${userType eq 'awardsApplicationSportsPerson'}">
										<select class="form-control"
											name="participationYear" id="participationYear">
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="2019-20" >2019-20</option>
											<option value="2020-21" >2020-21</option>
											<option value="2021-22" >2021-22</option>
											<option value="2022-23" >2022-23</option>
											<option value="2023-24" >2023-24</option>
										</select>
									</c:if>
									<c:if test="${userType eq 'awardsApplicationCoach'}">
										<select class="form-control" name="participationYear" id="participationYear" >
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="2009-10" >2009-10</option>
											<option value="2010-11" >2010-11</option>
											<option value="2011-12" >2011-12</option>
											<option value="2012-13" >2012-13</option>
											<option value="2013-14" >2013-14</option>
											<option value="2014-15" >2014-15</option>
											<option value="2015-16" >2015-16</option>
											<option value="2016-17" >2016-17</option>
											<option value="2017-18" >2017-18</option>
											<option value="2018-19" >2018-19</option>
											<option value="2019-20" >2019-20</option>
											<option value="2020-21" >2020-21</option>
											<option value="2021-22" >2021-22</option>
											<option value="2022-23" >2022-23</option>
											<option value="2023-24" >2023-24</option>
										</select>
									</c:if>
									</div>
								</div>
                              </div>
                              
                              <div class="row">
								<!-- Sport Name -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="sport-name" /><sup class="text-danger">*</sup></label> 
											<select class="form-control" name ="sportsName" id="sportsName" >
												<c:if test="${roleName eq 'Para Athlete'}">
												    <c:forEach var="sportsMaster" items="${sportsMaster}">
												        <c:if test="${sportsMaster.sportMasterId == 49}">
												            <option value="${sportsMaster.sportMasterId}">${sportsMaster.name_en}</option>
												        </c:if>
												    </c:forEach>
												</c:if>
												
												<c:if test="${roleName ne 'Para Athlete'}">
													<option value="">Select</option>
												    <c:forEach var="sportsMaster" items="${sportsMaster}">
												        <option value="${sportsMaster.sportMasterId}">${sportsMaster.name_en}</option>
												    </c:forEach>
												</c:if>
									</select>
									</div>
								</div>
								
								
								
								
								
								
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="sports-type"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name ="sportsType" id="sportsType">
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="sportsType" items="${sportsTypes}">
												<option value="${sportsType.sportsTypeMasterId}">${sportsType.name_en}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="sports-competition-level"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name ="sportsCompetitionLevel" id="sportsCompetitionLevel">
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="sportsCompLevel" items="${sportsCompLevels}">
												<option value="${sportsCompLevel.id}">${sportsCompLevel.name_en}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="award-year"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name="awardYear" id="awardYear">
											<option value=""><liferay-ui:message key="select"/></option>
												<option value="2009-10" >2009-10</option>
												<option value="2010-11" >2010-11</option>
												<option value="2011-12" >2011-12</option>
												<option value="2012-13" >2012-13</option>
												<option value="2013-14" >2013-14</option>
												<option value="2014-15" >2014-15</option>
												<option value="2015-16" >2015-16</option>
												<option value="2016-17" >2016-17</option>
												<option value="2017-18" >2017-18</option>
												<option value="2018-19" >2018-19</option>
												<option value="2019-20" >2019-20</option>
												<option value="2020-21" >2020-21</option>
												<option value="2021-22" >2021-22</option>
												<option value="2022-23" >2022-23</option>
												<option value="2023-24" >2023-24</option>
										</select>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="award-name"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name ="awardName" id="awardName">
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="awardName" items="${awardsNames}">
												<option value="${awardName.awardNameMasterId}">${awardName.name_en}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								
								
								
								
								
								<!--  para athlet sport-->
								<c:if test="${roleName eq 'Para Athlete'}">
									<div class="col-md-4">
										<div class="form-group">
											<label><liferay-ui:message key="sport-name-for-para-athlete" /><sup class="text-danger">*</sup></label> 
											<input type="text" class="form-control" name="otherSportName" id="otherSportName" />
										</div>
									</div>
								</c:if>
								
								<!-- Medal Received -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="medal-recieved" /><sup class="text-danger">*</sup></label> 
											<select class="form-control" name="medalRecieved" id="medalRecieved">
												<option value=""><liferay-ui:message key="select" /></option>
												<option value="Gold">Gold</option>
												<option value="Silver">Silver</option>
												<option value="Bronze">Bronze</option>
												<option value="Participant">Participant</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="row">
								<!-- Category -->
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="category" /><sup class="text-danger">*</sup></label> 
										<select class="form-control" name="category" id="category">
										<option value=""><liferay-ui:message key="select" /></option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.categoryMasterId}">${category.name}</option>
										</c:forEach>
									</select>
									</div>
								</div>

								<!-- Country of Competition -->
								<div class="col-md-6">
									<div class="form-group">
									<c:if test="${roleName eq 'Adventure Person'}">
										<label><liferay-ui:message key="country-state-of-campaign" /><sup class="text-danger">*</sup></label>
									</c:if>	
									<c:if test="${roleName ne 'Adventure Person'}">
										<label><liferay-ui:message key="country-state-of-competition" /><sup class="text-danger">*</sup></label> 
									</c:if>	
										<input type="text" class="form-control" name="countryOfCompetition" id="countryOfCompetition" />
									</div>
								</div>
                               </div>
                               
								<div class="row">
								<!-- <div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="gender" /><sup class="text-danger">*</sup></label>
										<select class="form-control" name="gender" id="gender">
											<option value=""><liferay-ui:message key="select" /></option>
											<option value="1"><liferay-ui:message key="male" /></option>
											<option value="2"><liferay-ui:message key="female" /></option>
										</select>
									</div>
								</div> -->
								<div class="col-md-6">
									<input type="hidden" class="form-control" name="gender" id="gender" value="${gender}"/>
								    <div class="form-group">
								        <label> <liferay-ui:message key="gender" /> <sup class="text-danger">*</sup>
								        </label>
								        <select class="form-control" name="gender1" id="gender1" disabled="disabled">
								            <option value=""> <liferay-ui:message key="select" />
								            </option>
								            <option value="1" <c:if test="${gender == '1'}">selected</c:if>>
								                <liferay-ui:message key="male" />
								            </option>
								            <option value="2" <c:if test="${gender == '2'}">selected</c:if>>
								                <liferay-ui:message key="female" />
								            </option>
								        </select>
								    </div>
								</div>
								
								
							
								<!-- Competition Start Date -->
								<div class="col-md-6">
									<div class="form-group">
									<c:if test="${roleName eq 'Adventure Person'}">
										<label><liferay-ui:message key="campaign-start-date" /><sup class="text-danger">*</sup></label>
									</c:if>	
									<c:if test="${roleName ne 'Adventure Person'}">
										<label><liferay-ui:message key="competition-start-date" /><sup class="text-danger">*</sup></label>
									</c:if>	
										 <input type="date" class="form-control" name="competitionStartDate" id="competitionStartDate" />
									</div>
								</div>
                             </div>
                             
                             <div class="row">
								<!-- Competition End Date -->
								<div class="col-md-6">
									<div class="form-group">
									<c:if test="${roleName eq 'Adventure Person'}">
										<label><liferay-ui:message key="campaign-end-date" /><sup class="text-danger">*</sup></label>
									</c:if>	
									<c:if test="${roleName ne 'Adventure Person'}">
										<label><liferay-ui:message key="competition-end-date" /></label>
									</c:if>	
										<input type="date" class="form-control" name="competitionEndDate" id="competitionEndDate" />
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
									<c:if test="${roleName eq 'Adventure Person'}">
										<label><liferay-ui:message key="campaign-certificate" /><sup class="text-danger">*</sup></label>
									</c:if>	
									<c:if test="${roleName ne 'Adventure Person'}">
										<label><liferay-ui:message key="competition-certificate" /><sup class="text-danger">*</sup></label>
									</c:if>	
										
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="competitionCertificate" name="competitionCertificate"
												onchange="handleFileUpload(event, 'competitionCertificate', 'filePreviewContainer', 'filePreviewLink', 'deleteButton','competitionCertificateFileEntyId')"
												accept="application/pdf"> <label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file" /></label>
										</div>

										<!-- Preview and Delete Section -->
										<div class="competitionCertificateid d-none mt-3" id="filePreviewContainer">
											<a class="competitionCertificateCls text-truncate" id="filePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
											<button type="button" id="deleteButton" class="dltcompetitionCertificateBtn close" aria-label="Close"
												onclick="deleteFile('filePreviewContainer', 'competitionCertificate')">
												<span aria-hidden="true" class="text-danger"><em
													class="bi bi-x-circle-fill"></em></span>
											</button>
										</div>

									</div>
								</div>
								</div>
								
							<c:if test="${userType eq 'awardsApplicationCoach'}">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="coaching-date-from" /><sup class="text-danger">*</sup></label>
											 <input type="date" class="form-control" name="coachFromDate" id="coachFromDate" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="coaching-date-to" /><sup class="text-danger">*</sup></label> 
											<input type="date" class="form-control" name="coachToDate" id="coachToDate" />
									</div>
								</div>
							</div>
							</c:if>
							<c:if test="${roleName eq 'Adventure Person'}">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="capaingn-description" /></label> 
											<textarea  class="form-control" name="campaignDescription" id="campaignDescription" ></textarea>
										</div>
									</div>
							</c:if>
							
							<c:if test="${userType eq 'awardsApplicationSportsPerson'}">
								<div class="row">
									<!-- Highest Performance -->
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="highest-performance" /></label>
											<input type="text" class="form-control" name="highestPerformance" id="highestPerformance" />
										</div>
									</div>
									<!-- Name of Coach -->
									<div class="col-md-6">
										<div class="form-group">
										<c:if test="${roleName eq 'Adventure Person'}">
											<label><liferay-ui:message key="name-of-coach-during-campaign" /><sup class="text-danger">*</sup></label>
										</c:if>	
										<c:if test="${roleName ne 'Adventure Person'}">
											<label><liferay-ui:message key="name-of-coach-during-competition" /><sup class="text-danger">*</sup></label>
										</c:if>	
											 <input type="text" class="form-control" name="coachName" id="coachName" />
										</div>
									</div>
								</div>
							</c:if>
							
							<c:if test="${userType eq 'awardsApplicationCoach'}">
							<div class="row">	
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-sports-person-participated"/></label>
										<input type="text" class="form-control" name="sportsPersons" id="sportsPersons"/>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="no-of-medals-recieved-to-sports-person"/></label>
										<input type="text" class="form-control" name="noOfMedals" id="noOfMedals"/>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label ><liferay-ui:message key="separate-undertaking"/></label>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="separateUndertaking" name="separateUndertaking" onchange="handleFileUpload(event, 'separateUndertaking', 'separateUndertakingfilePreviewContainer', 'separateUndertakingfilePreviewLink', 'separateUndertakingdeleteButton','seperateUndertakingFileEntyId')" accept="application/pdf">
											<label class="custom-file-label" for="customFile"><liferay-ui:message key="choose-file"/></label>
										</div>
										
									 <!-- Preview and Delete Section -->
									    <div class="separateUndertakingid d-none mt-3" id="separateUndertakingfilePreviewContainer">
									        <a class="separateUndertakingCls text-truncate" id="separateUndertakingfilePreviewLink" href="" target="_blank" style="flex-grow: 1; text-decoration: none;"></a>
									        <button type="button" id="separateUndertakingdeleteButton"  class="dltseparateUndertakingBtn close" aria-label="Close"  onclick="deleteFile('separateUndertakingfilePreviewContainer', 'separateUndertaking')" >
									       	 <span aria-hidden="true" class="text-danger"><em class="bi bi-x-circle-fill"></em></span>
									   		</button>
									    </div>
										 
									 </div>
									 	<a href="${fileUrl}" target="_blank" style="flex-grow: 1; text-decoration: none;">${title}</a>
								</div>
							</div>
							</c:if>
							
							<!-- Add/Remove Buttons -->
							<div class="text-right mt-3">
								<button type="button" class="btn btn-primary add-more" onclick="addSportCoachRegistration(event)" title="<liferay-ui:message key="to-add-more-competition-details"/>">
									<liferay-ui:message key="add-more" />
								</button>
							</div>
							</div>
							</div>
							
							</div>
							<!--datatable append here  -->
							<div class="card-body universal-table">
								<div id="sport-person-coach-div">
								
								
								<c:if test="${not empty awardApplications }">
								<div class="" style="
																	    background: #eeeef6;
																	    border-radius: 15px;">
									<table id="sport-person-coach-list" class="table-bordered mt-0"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th hidden="hidden"></th>
												<c:if test="${userType eq 'awardsApplicationCoach'}">
													<th><liferay-ui:message key="sport-person-name" /></th>
												</c:if>
												<th>
													<c:if test="${roleName eq 'Adventure Person'}">
														<liferay-ui:message key="campaign-level" />
													</c:if>
													<c:if test="${roleName ne 'Adventure Person'}">
														<liferay-ui:message key="competition-level" />
													</c:if>
												</th>
												<th>
													<c:if test="${roleName eq 'Adventure Person'}">
														<liferay-ui:message key="campaign-name" />
													</c:if>
													<c:if test="${roleName ne 'Adventure Person'}">
														<liferay-ui:message key="competition-name" />
													</c:if>
												</th>
												<th>
													<c:if test="${roleName eq 'Adventure Person'}">
														<liferay-ui:message key="campaign-place" />
													</c:if>
													<c:if test="${roleName ne 'Adventure Person'}">
														<liferay-ui:message key="competition-place" />
													</c:if>
												</th>
												<th><liferay-ui:message key="sport-name" /></th>
												<c:if test="${roleName eq 'Para Athlete'}">
												<th><liferay-ui:message key="sport-name-for-para-athlete" /></th>
												</c:if>
												<th><liferay-ui:message key="participation-year" /></th>
												<!-- <th><liferay-ui:message key="medal-recieved" /></th>
												<th><liferay-ui:message key="country-state-of-campaign" /></th>
												<th><liferay-ui:message key="gender" /></th>
												<th><liferay-ui:message key="category" /></th>
												<th><liferay-ui:message key="competition-start-date" /></th>
												<th><liferay-ui:message key="competition-end-date" /></th>
												<th><liferay-ui:message key="coaching-date-from" /></th>
												<th><liferay-ui:message key="coaching-date-to" /></th>
												<th hidden="hidden"><liferay-ui:message key="capaingn-description" /></th>
												<th><liferay-ui:message key="highest-performance" /></th>
												<th><liferay-ui:message key="name-of-coach-during-competition" /></th>
												<th><liferay-ui:message key="no-of-sports-person-participated" /></th>
												<th><liferay-ui:message key="no-of-medals-recieved-to-sports-person" /></th> -->
												<th><liferay-ui:message key="status" /></th>
												<th><liferay-ui:message key="action" /></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="awardApplication"
												items="${awardApplications}">
												<tr>
													<td hidden="hidden">${awardApplication.awardApplicationId}</td>
													<c:if test="${userType eq 'awardsApplicationCoach'}">
														<td>${awardApplication.sportpersonName}</td>
													</c:if>
													<td>${awardApplication.competitionLevel}</td>
													<td>${awardApplication.competitionName}</td>
													<td>${awardApplication.competitionPlace}</td>
													<td>${awardApplication.sportsName}</td>
													<c:if test="${roleName eq 'Para Athlete'}">
														<td>${awardApplication.otherSportName}</td>
													</c:if>
													<td>${awardApplication.participationYear}</td>
													<%-- <td>${awardApplication.medalRecieved}</td>
													<td>${awardApplication.countryOfCompetition}</td>
													<td>${awardApplication.genderName}</td>
													<td>${awardApplication.categoryName}</td>
													<td>${awardApplication.competitionStartDateStr}</td>
													<td>${awardApplication.competitionEndDateStr}</td>
													<td>${awardApplication.coachFromDateStr}</td>
													<td>${awardApplication.coachToDateStr}</td>
													<td hidden="hidden">${awardApplication.campaignDescription}</td>
													<td>${awardApplication.highestPerformance}</td>
													<td>${awardApplication.coachName}</td>
													<td>${awardApplication.noOfParticipation}</td>
													<td>${awardApplication.noOfMedalRecieved}</td> --%>
													<td>${awardApplication.status}</td>
													<td>
													<c:if test="${awardApplication.status eq 'Draft' || awardApplication.status eq 'Pending'}"> 
													<div class="tooltip-icon">
						                           <ul class="inline-item">
						                            <li class="list-inline-item">
						                           		<a  class="btn btn-primary" onclick="editAwardApplication(this,'${awardApplication.awardApplicationId}')" ><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key='edit' />"></i></a>
						                            </li>
						                            <li class="list-inline-item">
						                            <a  class="btn btn-primary" onclick="deleteConfirmation(this,'${awardApplication.awardApplicationId}')" ><i class="bi bi-trash icons-color" title="<liferay-ui:message key='delete' />"></i></a>
						                            </li>
						                            </ul>
						                        </div>
													 </c:if>
													 </td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									</div>
								</c:if>
								</div>
								</div>
							<!--datatable  -->
							<div class="card-footer bg-transparent text-right p-4">
							
								<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards';">
		                        	<liferay-ui:message key="cancel" />
		                    	</button>	
		                            		
		                         <button class="btn btn-primary" type="button" onclick="location.reload();">
									<liferay-ui:message key="reset" />
								</button>
							
							
								<button class="btn btn-primary" type="button" onclick="saveAsDraft(event)">
									<liferay-ui:message key="save-as-draft" />
								</button>
								
								<button class="btn btn-primary" type="button" onclick="confirmPopup()">
									<liferay-ui:message key="submit" />
								</button>
								
							</div>
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="saveAwardsApplication" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="exampleModalLongTitle">
					<liferay-ui:message key="awards-application" />
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
				<a href="javascript:void(0)" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal"
					onclick="closeModal('saveAwardsApplication')"><liferay-ui:message key="close" />
				</a>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for awards application for sports person -->


<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="saveAwardsPoints" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="exampleModalLongTitle">
					<liferay-ui:message key="awards-application" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<p id="success-msg"></p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<a href="javascript:void(0)" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal"
					onclick="closeModalAwardsPoints('saveAwardsPoints')"><liferay-ui:message key="close" />
				</a>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for awards application for sports person -->


<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="saveCompetition" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="exampleModalLongTitle">
					<liferay-ui:message key="competition-registration" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<p id="success"></p>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<a href="/group/guest/awards" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal"
					onclick=""><liferay-ui:message key="close" />
				</a>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for awards application for sports person -->


<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="confirmationMassage" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title" id="exampleModalLongTitle">
					<liferay-ui:message key="confirmation" />
				</h5>
				<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 text-center">
						<div>
							<liferay-ui:message key="are-you-sure-you-want-save" />
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<a href="javascript:void(0)" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal"
					onclick="closeModal('confirmationMassage')"><liferay-ui:message key="close" />
				</a>
			
				<a href="/group/guest/awards" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal"
					onclick="saveApplication(event)"><liferay-ui:message key="submit" />
				</a>
			</div>
		</div>
	</div>
</div>
<!-- modal popup for awards application for sports person -->

<!-- Triggered Delete Confirmation Modal -->
<div class="modal fade" id="dltconfirmationMassage" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content modal-bg">
            <div class="modal-header justify-content-center align-items-center">
                <h5 class="modal-title" id="exampleModalLongTitle">
                    <liferay-ui:message key="confirmation" />
                </h5>
                <button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <div>
                            <img src="<%=request.getContextPath()%>/images/check.png" alt=""
                                width="50px" class="my-3"> <span class="text-primary"></span><sup><em
                                class="bi bi-copy mx-2"></em></sup>
                            <liferay-ui:message key="are-you-sure-you-want-delete-this-award-application-detail-note-once-you-deleted-cannot-be-revert-back" />
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" class="form-control" name="" id="appId" value="" />
            <div class="modal-footer d-flex justify-content-end">
                <button type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('dltconfirmationMassage')">
                    <liferay-ui:message key="close" />
                </button>
                <button type="button" id="confirmDelete" class="btn btn-danger maha-save-btn" onclick="deleteAwardApplication('appId')">
                    <liferay-ui:message key="submit" />
                </button>
            </div>
        </div>
    </div>
</div>


<script>

function confirmPopup(){
	$("#confirmationMassage").modal('show');
}

$(document).ready(function() {
 	var table = $('#sport-person-coach-list').DataTable({
 		"ordering" : false,
		 "paging" : true,
		 "scrollX": true,
		  "bLengthChange" : true,
		  "pageLength" : 10,
		  "lengthMenu": [10, 25, 50, 100],
		  "destroy" : true,
		  "bInfo": true
        });
 	
	var roleName = '${roleName}';
	if(roleName == 'Adventure Person'){
		var keyPrefix = 'campaign';
	}else{
		keyPrefix = 'competition';
	}
	//makeDynamicMandNonMand();
//	addValidationMethods();
		
// jQuery validation start
$('#sport-person-coach-reg').validate({
    
    onkeyup: function (element) {
		$(element).valid();
    },
    onchange: function (element) {
		$(element).valid();
    },
    
    rules: {
        competitionLevel: {
            required: true,
        },
        competitionName: {
            required: true,
            pattern: /^[a-zA-Z\s]+$/
        },
        competitionPlace: {
            required: true,
            pattern: /^[a-zA-Z\s]+$/
        },
        participationYear: {
            required: true,
        },
        sportsName: {
            required: true,
            maxlength: 100
        },
        medalRecieved: {
            required: true,
            maxlength: 50
        },
        category: {
            required: true,
            maxlength: 100
        },
        countryOfCompetition: {
            required: true,
        },
        cityOfCompetition: {
            required: true,
        },
        competitionStartDate: {
            required: true,
        },
        competitionEndDate: {
            required: true,
        },
        competitionCertificate: {
        	accept: "application/pdf",
			filesize: 2 * 1024 * 1024,
			filexssFilter: true,
			required: true
        },
       
    },
    messages: {
        competitionLevel: {
            required: "<liferay-ui:message key='please-select-" + keyPrefix + "-level' />",
        },
        competitionName: {
            required: "<liferay-ui:message key='please-enter-"+keyPrefix+"-name' />",
            pattern: "<liferay-ui:message key='please-enter-valid-"+keyPrefix+"-name' />"
        },
        competitionPlace: {
            required: "<liferay-ui:message key='please-enter-"+keyPrefix+"-place' />",
            pattern: "<liferay-ui:message key='please-enter-valid-"+keyPrefix+"-place' />"
        },
        participationYear: {
            required: "<liferay-ui:message key='please-select-participation-year' />",
        },
        sportsName: {
            required: "<liferay-ui:message key='please-enter-sports-name' />",
            maxlength: "<liferay-ui:message key='sports-name-max-length-exceeded' />"
        },
        medalRecieved: {
            required: "<liferay-ui:message key='please-enter-medal-received' />",
            maxlength: "<liferay-ui:message key='medal-max-length-exceeded' />"
        },
        category: {
            required: "<liferay-ui:message key='please-enter-category' />",
            maxlength: "<liferay-ui:message key='category-max-length-exceeded' />"
        },
        countryOfCompetition: {
            required: "<liferay-ui:message key='please-select-country-of-"+keyPrefix+"' />",
        },
        cityOfCompetition: {
            required: "<liferay-ui:message key='please-select-city-of-"+keyPrefix+"' />",
        },
        competitionStartDate: {
            required: "<liferay-ui:message key='please-enter-"+keyPrefix+"-start-date' />",
        },
        competitionEndDate: {
            required: "<liferay-ui:message key='please-enter-"+keyPrefix+"-end-date' />",
        },
        competitionCertificate: {
        	required: "<liferay-ui:message key='please-choose-a-file' />",
        	accept: "<liferay-ui:message key="please-choose-valid-file"/>",
			filesize: "<liferay-ui:message key="maximum-file-size-is-2-mb"/>"
			
        },
    },
});
// jQuery validation end
		
	
function addValidationMethods(){debugger
	$.validator.addMethod("filesize", function(value, element, param) {
	    return this.optional(element) || (element.files[0].size <= param);
	});
	$.validator.addMethod("filexssFilter", function(value, element, param) {
		if (element.files.length === 0) {
	        console.log("No file selected, skipping type validation.");
	        return true; 
	    }
		let validFileRegex = new RegExp(/^[a-zA-Z0-9 ._-]+$/); 
	    if(!validFileRegex.test(element.files[0].name)){
	    	$.validator.messages.filexssFilter = "Special charecters not allowed.Please select a different file.";
	    	return false;
	    }
	    return true;
	});
	}
	
});
	
function saveApplication(event) {debugger;
    $("#confirmationMassage").modal('hide');
    $('#status').val('Save');

    console.log("save as draft function called:");
    var form = $("#sport-person-coach-reg")[0];
    var formData = new FormData(form);

    if (event) {
        event.preventDefault();
    }

    
    
    
    
    
    
    
    
    var tableRows = $('#sport-person-coach-list tbody tr').length;
    if (tableRows > 0) {
        console.log("table has the data :::");
        var applicationIds = [];
        $('#sport-person-coach-list tbody tr').each(function () {
            var appId = $(this).find('td:first').text().trim(); 
            if (appId) {
                applicationIds.push(appId);
            }
        });

        var appIdFormData = new FormData();
        appIdFormData.append("status", "Save"); 
        appIdFormData.append("applicationIds", JSON.stringify(applicationIds)); 
        
        /*  if ($('#sport-person-coach-reg').valid()) { */
           for (var pair of formData.entries()) {
               appIdFormData.append(pair[0], pair[1]);
           }
      /*  } */
        
        $.ajax({
            type: "POST",
            url: '${saveSportsApplicationURL}', 
            data: appIdFormData,
            enctype: 'multipart/form-data',
            contentType: false,
            cache: false,
            processData: false,
            success: function (data) {
            	
                console.log("Application IDs sent successfully: " + data);
                var msg = "<liferay-ui:message key="competition-saved"/>";
        	    $('#success').html(msg);
        		 $("#saveCompetition").modal('show');
        		 
            },
            complete: function (data) {
                console.log("Request completed: " + data);
            }
        });
    } else {
        console.log("table does not have data:");
        
        
        
        $.ajax({
            type: "POST",
            url: '${validateAwardsPointsURL}' ,
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
            	if(data.awardsPoints === true){
        
        
        
        
        
        if ($('#sport-person-coach-reg').valid()) {
            formData.append("status", "Save");

            $.ajax({
                type: "POST",
                url: '${saveSportsApplicationURL}', 
                data: formData,
                enctype: 'multipart/form-data',
                contentType: false,
                cache: false,
                processData: false,
                success: function (data) {
                	
                    console.log("Form data sent successfully: " + data);
                    var msg = "<liferay-ui:message key="competition-saved"/>";
	        	    $('#success').html(msg);
	        		 $("#saveCompetition").modal('show');
                    
                },
                complete: function (data) {
                    console.log("Request completed: " + data);
                }
            });
        }
        
        
        
        
        
        
            	}else if(data.awardsPoints === false){
            		
            		console.log("False : ");
            		
            		var msg = "<liferay-ui:message key="please-enter-valid-awards-details-awards-points-are-not-available-for-entered-details"/>";
            	    $('#success-msg').html(msg);
            		 $("#saveAwardsPoints").modal('show');
            	}
            	}
        
        
        

});
    
    
    
}
    
}
	
function saveAsDraft(event) {debugger;
    $('#status').val('Draft');

    console.log("save as draft function called:");
    var form = $("#sport-person-coach-reg")[0];
    var formData = new FormData(form);

    if (event) {
        event.preventDefault();
    }
    
    

    var tableRows = $('#sport-person-coach-list tbody tr').length;
    if (tableRows > 0) {
        console.log("Table has data:");

        var applicationIds = [];
        $('#sport-person-coach-list tbody tr').each(function () {
            var appId = $(this).find('td:first').text().trim();
            if (appId) {
                applicationIds.push(appId);
            }
        });

        var appIdFormData = new FormData();
        appIdFormData.append("status", "Draft");
        appIdFormData.append("applicationIds", JSON.stringify(applicationIds));

       /*  if ($('#sport-person-coach-reg').valid()) { */
            for (var pair of formData.entries()) {
                appIdFormData.append(pair[0], pair[1]);
            }
       /*  } */

        $.ajax({
            type: "POST",
            url: '${saveSportsApplicationURL}',
            data: appIdFormData,
            enctype: 'multipart/form-data',
            contentType: false,
            cache: false,
            processData: false,
            success: function (data) {
                console.log("Application IDs and form data sent successfully: " + data);

                var msg = "<liferay-ui:message key='competition-saved-as-draft'/>";
                $('#success-application').html(msg);
                $("#saveAwardsApplication").modal('show');
            },
            complete: function (data) {
                console.log("Request completed: " + data);
            }
        });
    } else {
        console.log("Table does not have data:");
        
        
        
        $.ajax({
            type: "POST",
            url: '${validateAwardsPointsURL}' ,
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
            	if(data.awardsPoints === true){
            		
            		
        
        
        
        if ($('#sport-person-coach-reg').valid()) {
            formData.append("status", "Draft");

            $.ajax({
                type: "POST",
                url: '${saveSportsApplicationURL}',
                data: formData,
                enctype: 'multipart/form-data',
                contentType: false,
                cache: false,
                processData: false,
                success: function (data) {
                    console.log("Form data sent successfully: " + data);

                    var msg = "<liferay-ui:message key='competition-saved-as-draft'/>";
                    $('#success-application').html(msg);
                    $("#saveAwardsApplication").modal('show');
                },
                complete: function (data) {
                    console.log("Request completed: " + data);
                }
            });
        }
        
        
        
        
            	}else if(data.awardsPoints === false){
            		
            		console.log("False : ");
            		
            		var msg = "<liferay-ui:message key="please-enter-valid-awards-details-awards-points-are-not-available-for-entered-details"/>";
            	    $('#success-msg').html(msg);
            		 $("#saveAwardsPoints").modal('show');
            	}
            	}
        
        
        
        
    });
  }
    
}

function addSportCoachRegistration(event){
	var form = $("#sport-person-coach-reg")[0];
	var formData = new FormData(form);
	$('#status').val('Pending');
	
	$.ajax({
        type: "POST",
        url: '${validateAwardsPointsURL}' ,
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
        	if(data.awardsPoints === true){
        		
        		console.log("True : ");
        		
        		var userType = '${userType}';
        		var roleName = '${roleName}';
        		$('#status').val('Pending');
        		
        	if($('#sport-person-coach-reg').valid()){
        		console.log(" ###########################################  ");
        	      /*  formData.append('userType',userType); */
        	       formData.append('roleName',roleName);
        	       for (let [key, value] of formData.entries()) {
        	    	    console.log(key, value);
        	    	}
        	       if (event) {
        	        event.preventDefault(); 
        	    }
        	       $('#status').val('Pending');
        	       
        	        console.log("$('#sport-person-coach-reg').valid(): ",$('#sport-person-coach-reg').valid())
        	        $.ajax({
        	            type: "POST",
        	            url: '${saveSportsApplicationURL}',
        	            data: formData,
        	            enctype: 'multipart/form-data',
        	            contentType : false,
        	    		cache : false,
        	    		processData : false,
        	            success: function(data) {
        	                console.log("data: " + data);
        	            },
        	            complete: function(data) {debugger
        	                console.log("data: " + data.responseText);
        	                $("#sport-person-coach-div").html(data.responseText.trim());

        	                // Initialize DataTable
        	                $('#sport-person-coach-list').DataTable({
        	                    "sPaginationType": "full_numbers",
        	                    "ordering": false,
        	                    "bLengthChange": true,
        	                    "pageLength": 10,
        	                    "destroy": true,
        	                    "bInfo": true,
        	                    "searching": false,
        	                });
        	            }
        	        });
        	        /* setTimeout(function () {
        	            window.location.reload();
        	        }, 1000); */
        	        $('#sportpersonName,#competitionName, #competitionPlace,#otherSportName,#countryOfCompetition,#competitionStartDate,#competitionEndDate,#coachFromDate,#coachToDate,#highestPerformance,#coachName,#sportsPersons,#noOfMedals').val('');
        	        
        	      	$('#sport-person-coach-reg select[name!="gender1"]').val('');
        	      	$('#sport-person-coach-reg textarea').val('');
        	        
        	  //      $('#sport-person-coach-reg select, #sport-person-coach-reg textarea').val('');
        	        $('#competitionEndDate').removeAttr('Min')
        	        $('#coachToDate').removeAttr('Min')
        	        deleteFile('filePreviewContainer', 'competitionCertificate');
        	        deleteFile('separateUndertakingfilePreviewContainer', 'separateUndertaking')
        		     
        		}
        		
        		
        		
        		
        		
        		
        		
        		
        	}else if(data.awardsPoints === false){
        		
        		console.log("False : ");
        		
        		var msg = "<liferay-ui:message key="please-enter-valid-awards-details-awards-points-are-not-available-for-entered-details"/>";
        	    $('#success-msg').html(msg);
        		 $("#saveAwardsPoints").modal('show');
        	}
    	 }
       
    });
	
	
	
}
 
function handleFileUpload(event,id,filePreviewContainer,filePreviewLink,deleteBtn,hiddenFileId) {debugger
    const fileInput = event.target;
    const file = fileInput.files[0]; // Get the uploaded file
    const previewContainer = document.getElementById(filePreviewContainer);
    const previewLink = document.getElementById(filePreviewLink);
    const deleteButton = document.getElementById(deleteBtn);
	//&& $('#'+id).valid() add when it resolve
    if (file && $('#'+id).val() != '' ) {   
    	const fileName = file.name;

        // Show the preview container
        previewContainer.classList.remove('d-none');
        previewContainer.classList.add('d-flex');

        // Set the link text and href
        previewLink.textContent = fileName;
        previewLink.href = URL.createObjectURL(file); // Generate a temporary object URL for preview/download
        previewLink.target = "_blank";

        // Attach the delete functionality
        deleteButton.dataset.filename = fileName; // Store file name in button dataset
        
        $('#'+hiddenFileId).val('');
    }
}

	/**
	 * Delete the uploaded file from the frontend.
	 */
function deleteFile(filePreviewContainer,id) {
    const previewContainer = document.getElementById(filePreviewContainer);
    const fileInput = document.getElementById(id);

    // Reset file input
    fileInput.value = "";
	$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("<liferay-ui:message key="choose-file" />"); 
    // Hide the preview container
    previewContainer.classList.add('d-none');
    previewContainer.classList.remove('d-flex');
}
	
	function closeModal(id) {debugger
	    $("#"+id).modal('hide');
		$(".modal-backdrop").remove();
		$("body").removeClass("modal-open");
		location.reload();
	}
	
	function closeModalAwardsPoints(id) {debugger
	    $("#"+id).modal('hide');
	}
	
	
 /* document.addEventListener("DOMContentLoaded", function () {debugger
     const competitionStartDate = document.getElementById("competitionStartDate");
     const competitionEndDate = document.getElementById("competitionEndDate");
     
     //const userType = document.getElementById("userType").value;
     const userType = document.getElementById("userType").value;

     console.log("USER = "+userType);
     
     let startLimit = "2019-07-01";
     const endLimit = "2024-06-30";

     if (userType === "awardsApplicationCoach") {
         startLimit = "2009-07-01";
     }
     
     competitionStartDate.setAttribute("min", startLimit);
     competitionStartDate.setAttribute("max", endLimit);
     competitionEndDate.setAttribute("min", startLimit);
     competitionEndDate.setAttribute("max", endLimit);

     competitionStartDate.addEventListener("change", function () {
         const startDateValue = competitionStartDate.value;
         if (startDateValue) {
             competitionEndDate.setAttribute("min", startDateValue);
         } else {
             competitionEndDate.setAttribute("min", startLimit);
         }
     });
}); */
    
document.addEventListener("DOMContentLoaded", function () {
    const competitionStartDate = document.getElementById("competitionStartDate");
    const competitionEndDate = document.getElementById("competitionEndDate");
    const userType = document.getElementById("userType").value;

    console.log("USER = " + userType);

    let startLimit = "2019-07-01";
    const endLimit = "2024-06-30";

    if (userType === "awardsApplicationCoach") {
        startLimit = "2009-07-01";
    }

    competitionStartDate.setAttribute("min", startLimit);
    competitionStartDate.setAttribute("max", endLimit);
    competitionEndDate.setAttribute("min", startLimit);
    competitionEndDate.setAttribute("max", endLimit);

    competitionEndDate.addEventListener("focus", function () {
        const startDateValue = competitionStartDate.value;

        if (startDateValue) {
            competitionEndDate.setAttribute("min", startDateValue);
        } else {
            competitionEndDate.setAttribute("min", startLimit);
        }
    });
});

    
document.addEventListener("DOMContentLoaded", function () {
    const competitionStartDate = document.getElementById("coachFromDate");
    const competitionEndDate = document.getElementById("coachToDate");

    const startLimit = "2009-07-01";
    const endLimit = "2024-06-30";

    competitionStartDate.setAttribute("min", startLimit);
    competitionStartDate.setAttribute("max", endLimit);
    competitionEndDate.setAttribute("min", startLimit);
    competitionEndDate.setAttribute("max", endLimit);

    competitionStartDate.addEventListener("change", function () {
        const startDateValue = competitionStartDate.value;
        if (startDateValue) {
            competitionEndDate.setAttribute("min", startDateValue);
        } else {
            competitionEndDate.setAttribute("min", startLimit);
        }
    });
});
    
/* function makeDynamicMandNonMand(){debugger
	var userType = '${userType}';
	var roleName = '${roleName}';
	if(userType == 'awardsApplicationCoach'){
		makeFieldMendate('separateUndertaking');
	}else {
		removeFieldMendate('separateUndertaking');
	}
	
	if(roleName == 'Para Athlete'){
		makeFieldMendate('otherSportName');
	}else{
		removeFieldMendate('otherSportName');
	}
}

function makeFieldMendate(id){debugger
	console.log('id: ',id)
	if(!findProperty($('#'+id).rules(), 'required')){
		$('#'+id).rules("add", {
	        required: true
	       
	    });
	}
}
function removeFieldMendate(id){
	$('#'+id).rules('remove',  'required');
} */
function findProperty(obj, key) {
    if (typeof obj === "object") {
        if (key in obj) {
            return true;
        } else {
            return false;
        }
    }
    return false;
}

function editAwardApplication(button, awardApplicationId){debugger
	$('#applicationId').val(awardApplicationId);
	 $.ajax({
		 type: 'get',
	     dataType: 'json',
         url: '${editAwardApplicationURL}',
         data: {
	          "awardApplicationId": awardApplicationId,
	      },
 		 complete: function(data) {debugger
 			console.log("data: " + data.responseText);
         
         // Parse the entire data response first if necessary
         var response = JSON.parse(data.responseText);
         
         // Extract the awardApplication object
         var awardApplicationData = JSON.parse(response.awardApplication);
         
         console.log("awardApplicationData: ", awardApplicationData);

             $('#sportpersonName').val(awardApplicationData.sportpersonName);
             $('#competitionLevel').val(awardApplicationData.competitionLevelId);
             $('#competitionName').val(awardApplicationData.competitionName);
             $('#competitionPlace').val(awardApplicationData.competitionPlace);
             $('#sportsName').val(awardApplicationData.sportId);
             $('#otherSportName').val(awardApplicationData.otherSportName);
             $('#participationYear').val(awardApplicationData.participationYear);
             $('#medalRecieved').val(awardApplicationData.medalRecieved);
             $('#countryOfCompetition').val(awardApplicationData.countryOfCompetition);
             $('#gender').val(awardApplicationData.gender);
             $('#category').val(awardApplicationData.category);
             $('#competitionStartDate').val(response.competitionStartDate);
             $('#competitionEndDate').val(response.competitionEndDate);
             $('#coachFromDate').val(response.coachFromDate);
             $('#coachToDate').val(response.coachToDate);
             $('#sportsPersons').val(awardApplicationData.noOfParticipation);
             $('#noOfMedals').val(awardApplicationData.noOfMedalRecieved);
             $('#campaignDescription').val(awardApplicationData.campaignDescription);
             $('#coachName').val(awardApplicationData.coachName);
             $('#highestPerformance').val(awardApplicationData.highestPerformance);
             $('#competitionCertificateFileEntyId').val(awardApplicationData.certificateId);
             $('#seperateUndertakingFileEntyId').val(awardApplicationData.undertakingId);
             
             console.log("Award Year : " + awardApplicationData.awardYear + "  Award Name : " + awardApplicationData.awardNameId + " Level : " + awardApplicationData.sportsCompetitionLevelId + " Sport Type : " + awardApplicationData.sportsTypeId);
             
             $('#awardYear').val(awardApplicationData.awardYear);
             $('#awardName').val(awardApplicationData.awardNameId);
             $('#sportsCompetitionLevel').val(awardApplicationData.sportsCompetitionLevelId);
             $('#sportsType').val(awardApplicationData.sportsTypeId);
             
             
             if (response.competitionCertificateURL) {
                 $('#filePreviewContainer').removeClass('d-none'); // Show the container
                 $('#filePreviewLink')
                     .attr('href', response.competitionCertificateURL)
                     .text(response.competitionCertificateName);
             } else {
                 $('#filePreviewContainer').addClass('d-none'); // Hide the container
             }

             // Handle undertaking file preview
             if (response.undertakingURL) {
                 $('#separateUndertakingfilePreviewContainer').removeClass('d-none'); // Show the container
                 $('#separateUndertakingfilePreviewLink')
                     .attr('href', response.undertakingURL)
                     .text(response.undertakingName);
             } else {
                 $('#separateUndertakingfilePreviewContainer').addClass('d-none'); // Hide the container
             }
             
         },
     });
	
}

function deleteAwardApplication(awardApplicationId){debugger
	var awardApplicationId = $('#'+awardApplicationId).val();
	 $.ajax({
		 type: 'get',
	     dataType: 'json',
         url: '${deleteAwardApplicationURL}',
         data: {
	          "awardApplicationId": awardApplicationId,
	      },
 		 complete: function(data) {debugger
 			console.log("data: " + data.responseText);
 			closeModal('dltconfirmationMassage');
			 setTimeout(function () {
           	 window.location.reload();
       		 }, 1000); 
         },
     });
	
}

function deleteConfirmation(button, awardApplicationId){
	$('#dltconfirmationMassage').modal('show');
	$('#appId').val(awardApplicationId)
}
</script>