<%@ include file="/init.jsp" %>
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.js"></script> -->

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5><liferay-ui:message key="create-competition-master"/></h5>						
						<!-- <h5>*<liferay-ui:message key="indicates-mandotory-fields"/></h5> -->
						<div>
						  <a href="/group/guest/competition-dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn">  <i class="bi bi-arrow-left-circle mr-1"></i> <liferay-ui:message key="back" /> </a>
						</div>
			
					</div>
				<form id="create_comp_master"> 
					<div class="card-body">
					<div class="card card-background p-0">
					 <div class="card-header header-card"><liferay-ui:message key="create-competition-master"/></div>
					 <div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="sports-name"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="sportName">
										<option value=""><liferay-ui:message key="select" /></option>
										<c:forEach var="sportsMaster" items="${sportsMaster}">
											<option value="${sportsMaster.sportMasterId}">${sportsMaster.name_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="category"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name="category">
										<option value=""><liferay-ui:message key="select" /></option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.categoryMasterId}">${category.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						
						</div>
						
						<div class="row">
							<div class="col-md-3">
							    <div class="form-group age-group">
							        <label><liferay-ui:message key="age-group" /></label>
							        <div>
							        <input type="checkbox" id="ageGroupUnder14" name="underForteen" value="" class="ageGroupCheckbox" />
							        <label for="ageGroupUnder14" class="pl-2"><liferay-ui:message key="under-14" /></label>
							        </div>
							    </div>
							</div>

							<div class="col-md-3">
								<div class="form-group">
									<label><liferay-ui:message key="under-14-cut-off-date"/></label>
									<input type="date" class="form-control" name="underForteenCutOffDate" value="" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group age-group">
								<label><liferay-ui:message key="age-group" /></label>
								<div>
								    <input type="checkbox"  name="underSeventeen" value="" class="ageGroupCheckbox" />
									<label class="pl-2"><liferay-ui:message key="under-17"/></label>	
								</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><liferay-ui:message key="under-17-cut-off-date"/></label>
									<input type="date" class="form-control" name="underSeventeenCutOffDate" value="" class="ageGroupCheckbox"/>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-md-3">
								<div class="form-group age-group">
								<label><liferay-ui:message key="age-group" /></label>
								<div>
								    <input type="checkbox" name="underNineteen" value="" id="underNineteen" class="ageGroupCheckbox"/>
									<label class="pl-2"><liferay-ui:message key="under-19"/></label>
									</div>			
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label><liferay-ui:message key="under-19-cut-off-date"/></label>
									<input type="date" class="form-control" name="underNineteenCutOffDate"  value="" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="fees-for-competition"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="fees"  value="" />
								</div>
							</div>
							</div>
							<div class="row">
								<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="granted/non-granted"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="sportType">
										<option value=""><liferay-ui:message key="select" /></option>
										<option value="1"><liferay-ui:message key="granted" /></option>
										<option value="2"><liferay-ui:message key="non-granted" /></option>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="mandatory-for-current-year" /><sup class="text-danger">*</sup></label>
									<div class="d-flex mt-2">
									<div class="radio-text">
										<input type="radio" class="radio-btn" name="currentYear" id="mandatoryYearYes" value="1"  /> 
										<label for="mandatoryYearYes"><liferay-ui:message key="yes" /></label>
									</div>
									
									<div>
										<input type="radio" class="radio-btn" name="currentYear" id="mandatoryYearNo" value="0" /> 
										<label for="mandatoryYearNo"><liferay-ui:message key="no" /></label>
									</div>
									</div>
								</div>
							</div>
							</div>
							
							
                       
                       </div>
						</div>
					</div>
				</form>
					<div class="card-footer bg-transparent text-right p-4">
					 <div class="d-flex justify-content-end">
					     <a href="/group/guest/competition-dashboard" class="btn btn-secondary maha-save-btn" id="modalCloseBtn">
			                <liferay-ui:message key="cancel"/>
			            </a>
					    <button type="button" class="btn btn-primary reset-btn" id="reset-btn">
					      <liferay-ui:message key="reset" />
					    </button>
					   <button class="btn btn-primary" onclick="saveCompetitionMaster()"><liferay-ui:message key="save" /></button>
					</div>
				</div>
				
				</div>
			</div>
		</div>
	</div>
</div>



<!-- modal popup for add grievance -->
<div class="modal fade" id="saveCompetitionMasterModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="add-competition-master"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <liferay-ui:message key="competition-master-has-been-successfully-created"/></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/competition-dashboard" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for add grievance -->

<script>
var competitionCreationErrorMessage = '<liferay-ui:message key="the-competition-master-creation-is-unsucessfull" />';
var saveCompetitionMasterURL = '${saveCompetitionMasterURL}';

$("#reset-btn").on("click", function () {
	$('#create_comp_master')[0].reset(); // Reset all fields
	$('#create_comp_master').validate().resetForm(); // Clear validation
});

/* code for saving and modal popup close in js file */
 $(document).ready(function() {
		 $('#create_comp_master').validate({
			onkeyup: function (element) {
				$(element).valid();
			  },
			onchange: function (element) {
				    $(element).valid();
			  },
			rules:{
				sportName: {
					required:true,
				},
				category: {
					required:true,
				},
				sportType: {
					required:true,
				},
				fees: {
					required:true,
					pattern: /^\d+(\.\d{1,2})?$/,
					minlength:2,
					maxlength:7,
				},
				currentYear: {
					required:true,
				}	
				
			},messages:{
				sportName: {
					required:"<liferay-ui:message key="please-select-sports-name"/>"
				},
				category: {
					required:"<liferay-ui:message key="please-select-category"/>"
				},
				sportType: {
					required:"<liferay-ui:message key="please-select-sport-type"/>"
				},
				fees: {
					required:"<liferay-ui:message key="please-enter-fees"/>",
					pattern: "<liferay-ui:message key="please-enter-valid-fees"/>",
					minlength:"<liferay-ui:message key="minimum-length-is-2"/>",
					maxlength:"<liferay-ui:message key="maximum-length-is-7"/>"
				},
				currentYear: {
					required:"<liferay-ui:message key="please-check-current-year"/>"
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
	          },
		}); 
	});
</script> 