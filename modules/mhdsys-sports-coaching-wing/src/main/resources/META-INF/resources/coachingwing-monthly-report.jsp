<%@ include file="/init.jsp" %>
<style>
	.error-container{
		color: red
	}
</style>
<form action="${submitMonthlyReportActionCommand}" method="post" id="coachingWingMonthlyReportForm" enctype="multipart/form-data">
<input type="hidden" value="${coachingWingMonthlyReportId}" name="wingMonthlyreportId"/>
<input type="hidden" value="${coach.coachId}" name=coachId/>
<input type="hidden" value="${mode}" name="mode" id="mode"/>
<input type="hidden" value="add" name="status" id="status"/>

<div class="common-forms-div">
<div class="container">
<div class="row">
<div class="card shadow border-0">
<div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
<h5 class="mb-0">
<liferay-ui:message key="coaching-wing-monthly-report" />
</h5>
<div>
<a href="/group/guest/sports-coaching-wing-monthly-reports" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
<i class="bi bi-arrow-left-circle"></i>
<liferay-ui:message key="back" />
</a>
</div>
</div>
<div class="card-body">
<div class="card card-background p-0">
<div class="personal_details">
<div class="card-header header-card">
<liferay-ui:message key="coaching-wing-monthly-report" />
</div>
<div class="card-body">
	<div class="row">
				<div class="col-md-6">
			       <div class="form-group">
			           <label><liferay-ui:message key="timestamp" /><sup class="text-danger">*</sup></label>
			           <input type="date" class="form-control" id="timestamp" name="timestamp" placeholder="enter-name"  <c:if test="${mode == 'view'}">disabled="disabled"</c:if> value="${manthlyReport.timestampStr}">
			       		<p class="error-container" id="timestamp-error"></p>
			       </div>
			   </div>
	
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="report-month" /> <sup class="text-danger">*</sup></label>
			            <select class="form-control" id="reportMonth" name="reportMonth"  <c:if test="${mode == 'view'}">disabled="disabled"</c:if>>
			            		<option value=""><liferay-ui:message key="-select-" /></option>
			            		 <c:if test="${mode == 'add'}">
						            	<c:forEach var="reportMonth" items="${reportMonths}">
						            		<option value="${reportMonth}" <c:if test="${manthlyReport.month==reportMonth}">selected</c:if>>${reportMonth}</option>
						            	</c:forEach>
				            	</c:if>
				            	 <c:if test="${mode == 'edit'}">
						            	<c:forEach var="reportMonth" items="${reportMonths}">
						            		<option value="${reportMonth}" <c:if test="${manthlyReport.month==reportMonth}">selected</c:if>>${reportMonth}</option>
						            	</c:forEach>
				            	</c:if>
				            	 <c:if test="${mode == 'view'}">
				            		 <option value="${manthlyReport.month}" selected="selected" disabled="disabled">${manthlyReport.month}</option>
				            	</c:if>
			            </select>
			        	<p class="error-container" id="reportMonth-error"></p>
			        </div>
			    </div>
		
			    <div class="col-md-6">
				    <div class="form-group">
				        <label><liferay-ui:message key="name-of-coach" /><sup class="text-danger">*</sup></label>
				        <c:if test="${mode == 'add' }">
				        	<input type="text" class="form-control" id="coachName" name="coachName" placeholder="" value="${coach.fullName}">
				        </c:if>
				         <c:if test="${mode == 'edit'}">
				        	<input type="text" class="form-control" id="coachName" name="coachName" placeholder="" value="${manthlyReport.coachName}">
				        </c:if>
				        <c:if test="${mode == 'view'}">
				        <input type="text" class="form-control" id="coachName" name="coachName" disabled="disabled" placeholder="" value="${manthlyReport.coachName}">
				        </c:if>
				    	<p class="error-container" id="coachName-error"></p>
				    </div>
				</div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="district-name" /><sup class="text-danger">*</sup></label>
			            <c:if test="${mode == 'add'}">
			            	<input type="text" class="form-control" id="district" name="district" placeholder="" value="${coach.districtName}">
			            </c:if>
			            <c:if test="${mode == 'edit'}">
			             <input type="text" class="form-control" id="district" name="district"  placeholder="" value="${manthlyReport.district}">
			            </c:if>
			              <c:if test="${mode == 'view'}">
			              <input type="text" class="form-control" id="district" name="district" disabled="disabled" placeholder="" value="${manthlyReport.district}">
			              </c:if>
			            <p class="error-container" id="district-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="educational-qualifications" /><sup class="text-danger">*</sup></label>
			            <c:if test="${mode == 'add'}">
			            	<input type="text" class="form-control" id="qualification" name="qualification" placeholder="" value="">
			            </c:if>
			             <c:if test="${mode == 'edit'}">
			             	<input type="text" class="form-control" id="qualification" name="qualification"  placeholder="" value="${manthlyReport.educationalQualification}">
			             </c:if>
			             <c:if test="${mode == 'view'}">
			             	<input type="text" class="form-control" id="qualification" name="qualification" disabled="disabled" placeholder="" value="${manthlyReport.educationalQualification}">
			             </c:if>
			             <p class="error-container" id="qualification-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="any-other-courses" /></label>
			            <c:if test="${mode == 'add'}">
			           		 <input type="text" class="form-control" id="otherCourse" name="otherCourse" placeholder="" value="">
			            </c:if>
			            <c:if test="${mode == 'edit'}">
			             <input type="text" class="form-control" id="otherCourse" name="otherCourse"  placeholder="" value="${manthlyReport.otherCourse}">
			            </c:if>
			            <c:if test="${mode == 'view'}">
			            	 <input type="text" class="form-control" id="otherCourse" name="otherCourse" disabled="disabled" placeholder="" value="${manthlyReport.otherCourse}">
			            </c:if>
			             <p class="error-container" id="otherCourse-error"></p>
			        </div>
			    </div>
			    
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="sports-name" /><sup class="text-danger">*</sup></label>
			            <c:if test="${mode == 'add'}">
			            	<input type="text" class="form-control" id="sportsName" name="sportsName" placeholder="" value="${coach.sportsName}">
			            </c:if>
			             <c:if test="${mode == 'edit'}">
			            	 <input type="text" class="form-control" id="sportsName" name="sportsName"  placeholder="" value="${manthlyReport.sportsName}">
			             </c:if>
			            <c:if test="${mode == 'view'}">
			            	<input type="text" class="form-control" id="sportsName" name="sportsName" disabled="disabled" placeholder="" value="${manthlyReport.sportsName}">
			            </c:if>
			             <p class="error-container" id="sportsName-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="training-center-location" /><sup class="text-danger">*</sup></label>
			            <c:if test="${mode == 'add'}">
			            	<input type="text" class="form-control" id="place" name="place" placeholder="enter-name" value="${coach.divisionName}">
			            </c:if>
			             <c:if test="${mode == 'edit'}">
			             	<input type="text" class="form-control" id="place" name="place" placeholder="enter-name"  value="${manthlyReport.trainingCenterPlace}">
			             </c:if>
			            <c:if test="${mode == 'view'}">
			           	 	<input type="text" class="form-control" id="place" name="place" placeholder="enter-name" disabled="disabled" value="${manthlyReport.trainingCenterPlace}">
			            </c:if>
			             <p class="error-container" id="place-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="number-of-players-at-the-training-center" /><sup class="text-danger">*</sup></label>
			            <c:if test="${mode == 'add'}">
			            	<input type="text" class="form-control" id="noOfPlayers" name="noOfPlayers" placeholder="" value="">
			            </c:if>
			             <c:if test="${mode == 'edit'}">
			             	<input type="text" class="form-control" id="noOfPlayers" name="noOfPlayers" placeholder=""  value="${manthlyReport.noOfPlayers}">
			             </c:if>
			             <c:if test="${mode == 'view'}">
			             	<input type="text" class="form-control" id="noOfPlayers" name="noOfPlayers" placeholder="" disabled="disabled" value="${manthlyReport.noOfPlayers}">
			             </c:if>
			             <p class="error-container" id="noOfPlayers-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="performance-of-players" /><sup class="text-danger">*</sup></label>
			           		<select class="form-control" name="performance" id="performance"  <c:if test="${mode == 'view'}">disabled="disabled"</c:if>>
			           			 	<option value=""><liferay-ui:message key="select" /></option>
			           			 <c:if test="${mode == 'add'}">
				           			<option value="International"><liferay-ui:message key="international" /></option>
				           			<option value="National"><liferay-ui:message key="national" /></option>
				           			<option value="State"><liferay-ui:message key="state" /></option>
				           			<option value="Division"><liferay-ui:message key="division" /></option>
				           			<option value="District"><liferay-ui:message key="district" /></option>
			           			</c:if>
			           			  <c:if test="${mode == 'edit'}">
			           			 	<option value="International" <c:if test="${manthlyReport.performanceOfThePlayers eq 'International'}">selected</c:if>><liferay-ui:message key="international" /></option>
				           			<option value="National" <c:if test="${manthlyReport.performanceOfThePlayers eq 'National'}">selected</c:if>><liferay-ui:message key="national" /></option>
				           			<option value="State" <c:if test="${manthlyReport.performanceOfThePlayers eq 'State'}">selected</c:if>><liferay-ui:message key="state" /></option>
				           			<option value="Division" <c:if test="${manthlyReport.performanceOfThePlayers eq 'Division'}">selected</c:if>><liferay-ui:message key="division" /></option>
				           			<option value="District" <c:if test="${manthlyReport.performanceOfThePlayers eq 'District'}">selected</c:if>><liferay-ui:message key="district" /></option>
			           			 </c:if>
			           			 <c:if test="${mode == 'view'}">
			           			 	<option value="${manthlyReport.performanceOfThePlayers}" selected="selected">${manthlyReport.performanceOfThePlayers}</option>
			           			 </c:if>
			           		</select>
			             <p class="error-container" id="performance-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="training-program" /><sup class="text-danger">*</sup></label>
			           <select class="form-control" name="trainingProgram" id="trainingProgram" <c:if test="${mode == 'view'}">disabled="disabled"</c:if>>
			           				<c:if test="${mode == 'add'}">
			           				<option>-select-</option>
				           			<option value="Annual">Annual</option>
				           			<option value="Quarterly">Quarterly</option>
				           			<option value="Monthly">Monthly</option>
				           			</c:if>
				           			<c:if test="${mode == 'edit'}">
				           				<option>-select-</option>
					           			<option value="Annual" <c:if test="${manthlyReport.trainingProgram eq 'Annual'}">selected</c:if>>Annual</option>
					           			<option value="Quarterly" <c:if test="${manthlyReport.trainingProgram eq 'Quarterly'}">selected</c:if>>Quarterly</option>
					           			<option value="Monthly" <c:if test="${manthlyReport.trainingProgram eq 'Monthly'}">selected</c:if>>Monthly</option>
				           			</c:if>
				           			<c:if test="${mode == 'view'}">
				           				<option value="${manthlyReport.trainingProgram}" selected="selected">${manthlyReport.trainingProgram}</option>
				           			</c:if>
			           		</select>
			             <p class="error-container" id="trainingProgram-error"></p>
			        </div>
			    </div>
			     <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="idea-training-center" /><sup class="text-danger">*</sup></label>
			             <c:if test="${mode == 'add'}">
			            	<input type="text" class="form-control" id="idea" name="idea" placeholder="" value="">
			            </c:if>
			            <c:if test="${mode == 'edit'}">
			            	<input type="text" class="form-control" id="idea" name="idea" placeholder=""  value="${manthlyReport.idea}">
			            </c:if>
			            <c:if test="${mode == 'view'}">
			            	<input type="text" class="form-control" id="idea" name="idea" placeholder="" disabled="disabled" value="${manthlyReport.idea}">
			            </c:if>
			             <p class="error-container" id="idea-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="players-performance" /><sup class="text-danger">*</sup></label>
			           	 <c:if test="${mode == 'add'}">
			           		<input type="text" class="form-control" id="methods" name="methods" placeholder="" value="">
			           	</c:if>
			           	 <c:if test="${mode == 'edit'}">
			           	 	<input type="text" class="form-control" id="methods" name="methods" placeholder=""  value="${manthlyReport.playerAnalysisMethod}">
			           	 </c:if>
			           	 <c:if test="${mode == 'view'}">
			           	 	<input type="text" class="form-control" id="methods" name="methods" placeholder="" disabled="disabled"  value="${manthlyReport.playerAnalysisMethod}">
			           	 </c:if>
			             <p class="error-container" id="methods-error"></p>
			        </div>
			    </div>
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="performance-planning"/><sup class="text-danger">*</sup></label>
			           		<c:if test="${mode == 'add'}">
			           			<input type="text" class="form-control" id="planning" name="planning" placeholder="" value="">
			           		</c:if>
			           		 <c:if test="${mode == 'edit'}">
			           		 	<input type="text" class="form-control" id="planning" name="planning"   placeholder="" value="${manthlyReport.planningForBestPlayers}">
			           		 </c:if>
			           		 <c:if test="${mode == 'view'}">
			           		 	<input type="text" class="form-control" id="planning" name="planning" disabled="disabled"  placeholder="" value="${manthlyReport.planningForBestPlayers}">
			           		 </c:if>
			             <p class="error-container" id="planning-error"></p>
			        </div>
			    </div>
			   
			    <div class="col-md-6">
			        <div class="form-group">
			            <label><liferay-ui:message key="received-assistance-efforts" /><sup class="text-danger">*</sup></label>
			            	<c:if test="${mode == 'add'}">
			            		<input type="text" class="form-control" id="assistance" name="assistance" placeholder="" value="">
			            	</c:if>
			            	 <c:if test="${mode == 'edit'}">
			            	 	<input type="text" class="form-control" id="assistance" name="assistance"   placeholder="" value="${manthlyReport.assistanceAndEffort}">
			            	 </c:if>
			            	 <c:if test="${mode == 'view'}">
			            	 	<input type="text" class="form-control" id="assistance" name="assistance" disabled="disabled"  placeholder="" value="${manthlyReport.assistanceAndEffort}">
			            	 </c:if>
			             <p class="error-container" id="assistance-error"></p>
			        </div>
			    </div>
 
		</div>
		<!-- Content Goes Here -->
		
		
		
		</div> <!-- .card-body (inner) -->
		</div> <!-- .personal_details -->
		</div> <!-- .card-background -->
		
		<c:if test="${isDSO}">
		    <%@include file="dso-review-form.jspf" %>
	    </c:if>
		
		</div> <!-- .card-body (outer) -->
			<div class="card-footer bg-transparent text-right p-4">
			<div class="d-flex justify-content-end">
			<c:if test="${isCoach && mode ne 'view'}">
				<a href="#" class="btn btn-secondary maha-save-btn" id="" onclick="">
				<liferay-ui:message key="cancel"/>
				</a>
				<button type="button" class="btn btn-primary reset-btn" id="reset-btn">
				<liferay-ui:message key="reset" />
				</button>
				
				<button type="submit" class="btn btn-primary submit-btn" id="save-as-draft">
				<liferay-ui:message key="save-as-draft" />
				</button>
				
				<button type="submit" class="btn btn-primary submit-btn" id="submitMonthlyReportBtn">
				<liferay-ui:message key="submit" />
				</button>
			  </c:if>
			  <c:if test="${mode eq 'view' and isDSO}">
				  	<button type="submit" class="btn btn-primary submit-btn" id="submitMonthlyReportBtn">
						<liferay-ui:message key="submit" />
					</button>
			  </c:if>
			</div>
			</div>
		</div> <!-- .card -->
		</div> <!-- .row -->
		
		</div> <!-- .container -->
		</div> <!-- .common-forms-div -->
</form>
<script>
	$(document).ready(function(){
			
		 $('#save-as-draft').click(function(e){
				$('#status').val("draft");
			});
		 $('#submitMonthlyReportBtn').click(function(e){
				$('#status').val("add");
			});
		 
		 $('#timestamp').change(function(){
			 var timestampValue=$(this).val();
		  	if(!timestamp){
				document.getElementById("timestamp-error").textContent = "<liferay-ui:message key='please-enter-date' />";
				errorMessage.push("<liferay-ui:message key='please-enter-date' />");
				$('#timestamp').focus();
			}else{
				document.getElementById("timestamp-error").textContent = "";
			}
		 });
		 
		 
		 $('#reportMonth').change(function(){
			 var reportMonthVal=$(this).val();
			 if(!reportMonthVal){
					document.getElementById("reportMonth-error").textContent = "<liferay-ui:message key='please-select-month' />";
					errorMessage.push("<liferay-ui:message key='please-enter-month' />");
					$(this).focus();
				}else{
					document.getElementById("reportMonth-error").textContent = "";
				}
		 });
		 
		 
		 $('#coachName').keyup(function(){
			 var coachName=$(this).val();
			 if(!coachName){
					document.getElementById("coachName-error").textContent = "<liferay-ui:message key='please-enter-name' />";
					errorMessage.push("<liferay-ui:message key='please-enter-name' />");
					$(this).focus();
				}else{
					document.getElementById("coachName-error").textContent = "";
				}
		 });
		 
		 $('#coachName').blur(function(){
			 var coachName=$(this).val();
			 if(!coachName){
					document.getElementById("coachName-error").textContent = "<liferay-ui:message key='please-enter-name' />";
					errorMessage.push("<liferay-ui:message key='please-enter-name' />");
					$(this).focus();
				}else{
					document.getElementById("coachName-error").textContent = "";
				}
		 });
		 
		 
		 $('#district').keyup(function(){
			 var district=$(this).val();
			 if(!district){
					document.getElementById("district-error").textContent = "<liferay-ui:message key='please-enter-district' />";
					errorMessage.push("<liferay-ui:message key='please-enter-district' />");
					$(this).focus();
				}else{
					document.getElementById("district-error").textContent = "";
				}
		 });
		 
		 
		 
		 $('#district').blur(function(){
			 var district=$(this).val();
			 if(!district){
					document.getElementById("district-error").textContent = "<liferay-ui:message key='please-enter-district' />";
					errorMessage.push("<liferay-ui:message key='please-enter-district' />");
					$(this).focus();
				}else{
					document.getElementById("district-error").textContent = "";
				}
		 });
		 
		 $('#qualification').keyup(function(){
			 var qualification=$(this).val();
			 if(!qualification){
					document.getElementById("qualification-error").textContent = "<liferay-ui:message key='please-enter-qualification' />";
					errorMessage.push("<liferay-ui:message key='please-enter-qualification' />");
					$(this).focus();
				}else{
					document.getElementById("qualification-error").textContent = "";
				}
		 });
		 
		 $('#qualification').blur(function(){
			 var qualification=$(this).val();
			 if(!qualification){
					document.getElementById("qualification-error").textContent = "<liferay-ui:message key='please-enter-qualification' />";
					errorMessage.push("<liferay-ui:message key='please-enter-qualification' />");
					$(this).focus();
				}else{
					document.getElementById("qualification-error").textContent = "";
				}
		 });
		 
		 $('#sportsName').keyup(function(){
			 var sportsName=$(this).val();
			 if(!sportsName){
					document.getElementById("sportsName-error").textContent = "<liferay-ui:message key='please-enter-sportsName' />";
					errorMessage.push("<liferay-ui:message key='please-enter-sportsName' />");
					$(this).focus();
				}else{
					document.getElementById("sportsName-error").textContent = "";
				}
		 });
		 
		 $('#sportsName').blur(function(){
			 var sportsName=$(this).val();
			 if(!sportsName){
					document.getElementById("sportsName-error").textContent = "<liferay-ui:message key='please-enter-sportsName' />";
					errorMessage.push("<liferay-ui:message key='please-enter-sportsName' />");
					$(this).focus();
				}else{
					document.getElementById("sportsName-error").textContent = "";
				}
		 });
		 
		 $('#place').keyup(function(){
			 var place=$(this).val();
			 if(!place){
					document.getElementById("place-error").textContent = "<liferay-ui:message key='please-enter-training-center-place' />";
					errorMessage.push("<liferay-ui:message key='please-enter-training-center-place' />");
					$(this).focus();
				}else{
					document.getElementById("place-error").textContent = "";
				}
		 });
		 
		 $('#noOfPlayers').keyup(function(){
			 var noOfPlayers=$(this).val();
			 if(!noOfPlayers){
					document.getElementById("noOfPlayers-error").textContent = "<liferay-ui:message key='please-enter-no-of-players' />";
					errorMessage.push("<liferay-ui:message key='please-enter-no-of-players' />");
					$(this).focus();
				}else{
					document.getElementById("noOfPlayers-error").textContent = "";
				}
		 });
		 
		 
		 $('#performance').change(function(){
			 var performance=$(this).val();
			  	if(!performance){
					document.getElementById("performance-error").textContent = "<liferay-ui:message key='please-select-performance-of-player' />";
					errorMessage.push("<liferay-ui:message key='please-select-performance-of-player' />");
					$(this).focus();
				}else{
					document.getElementById("performance-error").textContent = "";
				}
		 });
		 
		 $('#trainingProgram').change(function(){
			 var trainingProgram=$(this).val();
			  	if(!trainingProgram){
					document.getElementById("trainingProgram-error").textContent = "<liferay-ui:message key='please-select-trainingProgram' />";
					errorMessage.push("<liferay-ui:message key='please-select-trainingProgram' />");
					$(this).focus();
				}else{
					document.getElementById("trainingProgram-error").textContent = "";
				}
		 });
		 
		 
		 $('#idea').keyup(function(){
			 var idea=$(this).val();
			  	if(!idea){
					document.getElementById("idea-error").textContent = "<liferay-ui:message key='please-enter-any-idea' />";
					errorMessage.push("<liferay-ui:message key='please-enter-any-idea' />");
					$(this).focus();
				}else{
					document.getElementById("idea-error").textContent = "";
				}
		 });

		 $('#methods').keyup(function(){
			 var methods=$(this).val();
			  	if(!methods){
					document.getElementById("methods-error").textContent = "<liferay-ui:message key='please-enter-player-performance-method' />";
					errorMessage.push("<liferay-ui:message key='please-enter-player-performance-method' />");
					$(this).focus();
				}else{
					document.getElementById("methods-error").textContent = "";
				}
		 });
		 
		 
		 $('#planning').keyup(function(){
			 var planning=$(this).val();
			  
			  	if(!planning){
					document.getElementById("planning-error").textContent = "<liferay-ui:message key='please-enter-plannig-details' />";
					errorMessage.push("<liferay-ui:message key='please-enter-plannig-details' />");
					$(this).focus();
				}else{
					document.getElementById("planning-error").textContent = "";
				}
		 });
		
		 $('#assistance').keyup(function(){
				var assistance=$(this).val();
			  	if(!assistance){
					document.getElementById("assistance-error").textContent = "<liferay-ui:message key='please-enter-any-assistance' />";
					errorMessage.push("<liferay-ui:message key='please-enter-any-assistance' />");
					$(this).focus();
				}else{
					document.getElementById("assistance-error").textContent = "";
				}
		 });
		
		$("#coachingWingMonthlyReportForm").submit(function(e){
			  var errorMessage=[];
			  var mode='${mode}';
			  if(mode!='view'){
				  var timestamp=$('#timestamp').val();
				  
				  	if(!timestamp){
						document.getElementById("timestamp-error").textContent = "<liferay-ui:message key='please-enter-date' />";
						errorMessage.push("<liferay-ui:message key='please-enter-date' />");
						$('#timestamp').focus();
					}else{
						document.getElementById("timestamp-error").textContent = "";
					}
				  	
				  	var reportMonth=$('#reportMonth').val();
					  
				  	if(!reportMonth){
						document.getElementById("reportMonth-error").textContent = "<liferay-ui:message key='please-select-month' />";
						errorMessage.push("<liferay-ui:message key='please-enter-month' />");
						$(this).focus();
					}else{
						document.getElementById("reportMonth-error").textContent = "";
					}
				  	
				  	
				  	var coachName=$('#coachName').val();
					  
				  	if(!coachName){
						document.getElementById("coachName-error").textContent = "<liferay-ui:message key='please-enter-name' />";
						errorMessage.push("<liferay-ui:message key='please-enter-name' />");
						$(this).focus();
					}else{
						document.getElementById("coachName-error").textContent = "";
					}
				  	
				  	
				  	var district=$('#district').val();
					  
				  	if(!district){
						document.getElementById("district-error").textContent = "<liferay-ui:message key='please-enter-district' />";
						errorMessage.push("<liferay-ui:message key='please-enter-district' />");
						$(this).focus();
					}else{
						document.getElementById("district-error").textContent = "";
					}
				  	
				  	
				  	var qualification=$('#qualification').val();
					  
				  	if(!qualification){
						document.getElementById("qualification-error").textContent = "<liferay-ui:message key='please-enter-qualification' />";
						errorMessage.push("<liferay-ui:message key='please-enter-qualification' />");
						$(this).focus();
					}else{
						document.getElementById("qualification-error").textContent = "";
					}
				  	
				  	
					
				  	var sportsName=$('#sportsName').val();
					  
				  	if(!sportsName){
						document.getElementById("sportsName-error").textContent = "<liferay-ui:message key='please-enter-sportsName' />";
						errorMessage.push("<liferay-ui:message key='please-enter-sportsName' />");
						$(this).focus();
					}else{
						document.getElementById("sportsName-error").textContent = "";
					}
				  	
				  	var place=$('#place').val();
					  
				  	if(!place){
						document.getElementById("place-error").textContent = "<liferay-ui:message key='please-enter-training-center-place' />";
						errorMessage.push("<liferay-ui:message key='please-enter-training-center-place' />");
						$(this).focus();
					}else{
						document.getElementById("place-error").textContent = "";
					}
				  	
				  	
				  	var noOfPlayers=$('#noOfPlayers').val();
					  
				  	if(!noOfPlayers){
						document.getElementById("noOfPlayers-error").textContent = "<liferay-ui:message key='please-enter-no-of-players' />";
						errorMessage.push("<liferay-ui:message key='please-enter-no-of-players' />");
						$(this).focus();
					}else{
						document.getElementById("noOfPlayers-error").textContent = "";
					}
				  	
				  	var performance=$('#performance').val();
					  
				  	if(!performance){
						document.getElementById("performance-error").textContent = "<liferay-ui:message key='please-select-performance-of-player' />";
						errorMessage.push("<liferay-ui:message key='please-select-performance-of-player' />");
						$(this).focus();
					}else{
						document.getElementById("performance-error").textContent = "";
					}
				  	
				  	
				  	var trainingProgram=$('#trainingProgram').val();
					  
				  	if(!trainingProgram){
						document.getElementById("trainingProgram-error").textContent = "<liferay-ui:message key='please-select-trainingProgram' />";
						errorMessage.push("<liferay-ui:message key='please-select-trainingProgram' />");
						$(this).focus();
					}else{
						document.getElementById("trainingProgram-error").textContent = "";
					}
				  	
					var idea=$('#idea').val();
					  
				  	if(!idea){
						document.getElementById("idea-error").textContent = "<liferay-ui:message key='please-enter-any-idea' />";
						errorMessage.push("<liferay-ui:message key='please-enter-any-idea' />");
						$(this).focus();
					}else{
						document.getElementById("idea-error").textContent = "";
					}
				  	
				  	var methods=$('#methods').val();
					  
				  	if(!methods){
						document.getElementById("methods-error").textContent = "<liferay-ui:message key='please-enter-player-performance-method' />";
						errorMessage.push("<liferay-ui:message key='please-enter-player-performance-method' />");
						$(this).focus();
					}else{
						document.getElementById("methods-error").textContent = "";
					}
				  	
				  	var planning=$('#planning').val();
					  
				  	if(!planning){
						document.getElementById("planning-error").textContent = "<liferay-ui:message key='please-enter-plannig-details' />";
						errorMessage.push("<liferay-ui:message key='please-enter-plannig-details' />");
						$(this).focus();
					}else{
						document.getElementById("planning-error").textContent = "";
					}
				  	
				  	
				  	var assistance=$('#assistance').val();
					  
				  	if(!assistance){
						document.getElementById("assistance-error").textContent = "<liferay-ui:message key='please-enter-any-assistance' />";
						errorMessage.push("<liferay-ui:message key='please-enter-any-assistance' />");
						$(this).focus();
					}else{
						document.getElementById("assistance-error").textContent = "";
					}
			  }else if(mode=='view'){
					var isDSO='${isDSO}';
				  	console.log("isDSO ::" +isDSO);
				  	if(isDSO){
				  		console.log("inside dso");
				  		
				  		var dsoDecision=$('input[name="dsoDecision"]:checked').val();
						var dsoRemarks=$('#dsoRemarks').val();
						console.log("dsoDecision ::::"+dsoDecision);
						console.log("dsoRemarks ::::"+dsoRemarks);
				  				//================================================================
						if(dsoDecision=='false'){
							console.log("ddd rejected ::::");
							if(!dsoRemarks){
								document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='please-enter-remarks' />";
								e.preventDefault();
							}else{
								document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='' />";
								//Validating remarks values
					   			var trimmedValue = $.trim(dsoRemarks.replace(/\s{2,}/g, ' '));
					   			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
					   			var actualValue1=trimmedValue;
					   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.,-]|(?<!\.)$/g, '');
					   			$('#ddHoRemarks').val(actualValue);
								console.log("actualValue.length :::" +actualValue.length);
								if(actualValue){
									if(actualValue.length<5){
										document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='please-enter-minimum-5-character' />";
										errorMessage.push("<liferay-ui:message key='please-enter-minimum-5-character' />");
										//e.preventDefault();
									}else if (!regex.test(actualValue)) {
					       	            console.log("1 not allowed special character  ::");
					       	         	document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
					       	         	errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
					       	         	//e.preventDefault();
					       			}else if(/([.\/ -,])\1+/.test(actualValue)){
					       	         	console.log("2 not allowed special character  ::");
					       	      		document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
					       	      		errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
					       	      		//e.preventDefault();
					       	        }else {
					       	         	console.log("allowed special character  ::");
					       	         document.getElementById("dsoremarks-error").textContent = "";
					       	        }
								}else {
					       	         document.getElementById("dsoremarks-error").textContent = "";
					   	        }
							}
						}else{
							console.log("ddd approved ::::");
							if(dsoRemarks){
								var trimmedValue = $.trim(dsoRemarks.replace(/\s{2,}/g, ' '));
					   			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
					   			var actualValue1=trimmedValue;
					   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.,-]|(?<!\.)$/g, '');
					   			$('#ddHoRemarks').val(actualValue);
								console.log("actualValue.length :::" +actualValue.length);
								if(actualValue){
									if(actualValue.length<5){
										document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='please-enter-minimum-5-character' />";
										errorMessage.push("<liferay-ui:message key='please-enter-minimum-5-character' />");
										//e.preventDefault();
									}else if (!regex.test(actualValue)) {
					       	            console.log("1 not allowed special character  ::");
					       	         	document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
					       	         	errorMessage.push("<liferay-ui:message key='given-special-character-not-allowed' />");
					       	         	//e.preventDefault();
					       			}else if(/([.\/ -,])\1+/.test(actualValue)){
					       	         	console.log("2 not allowed special character  ::");
					       	      		document.getElementById("dsoremarks-error").textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
					       	      		errorMessage.push("<liferay-ui:message key='consecutive-special-characters-not-allowed' />");
					       	      		//e.preventDefault();
					       	        }else {
					       	         	console.log("allowed special character  ::");
					       	         document.getElementById("dsoremarks-error").textContent = "";
					       	        }
								}else {
					       	         document.getElementById("dsoremarks-error").textContent = "";
					   	        }
							}else{
								  document.getElementById("dsoremarks-error").textContent = "";
							}
						}
				  				//================================================================
				  	}
				  	
				  	//validate digital 
				  	
				  //geo tag validation 
				  	var fileValue=$('#digitalSignatureNewPreviewLink').text();
				  	var digitalSignature=$("#digitalSignature")[0].files[0];
				  	console.log("fileValue ::" +digitalSignature);
				  	console.log("digitalSignature ::" +fileValue);
				  	 var maxSize = 2*1024*1024; // 2 MB
					var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
				 if(fileValue==0){
			        	//console.log("inside if dpr");
			        	document.getElementById("digitalSignatureError").textContent = "<liferay-ui:message key='please-select-digitalSignature' />";
			    		errorMessage.push("<liferay-ui:message key='please-select-digitalSignature' />");
			    		$(this).focus();
			        }else if (!allowedExtensions.exec(fileValue)) {
			        	//console.log("inside else  if dpr");
			        	document.getElementById("digitalSignatureError").textContent = "<liferay-ui:message key='please-select-jpg-or-png-or-jpeg-file-only' />";
			    		errorMessage.push("<liferay-ui:message key='please-select-jpg-or-png-or-jpeg-file-only' />");
			        }else if (digitalSignature>maxSize) {
			        	//console.log("inside else  if dpr");
			        	document.getElementById("digitalSignatureError").textContent = "<liferay-ui:message key='select-document-size-less-than-2-mb' />";
			    		errorMessage.push("<liferay-ui:message key='select-document-size-less-than-2-mb' />");
			        }else{
			        	document.getElementById("digitalSignatureError").textContent = "";
			        }
			  }
			  console.log("errorMessage ::" +errorMessage);
			  
			  	if(errorMessage.length>0){
			  		e.preventDefault();
			  	}
			  	
			  	//e.preventDefault();
		});
	});
</script>


