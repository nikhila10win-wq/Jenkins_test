<%@page import="com.mhdsys.school.change.web.constants.MhdsysSchoolChangeWebPortletKeys"%>
<%@ include file="/init.jsp"%>


<portlet:resourceURL id="<%=MhdsysSchoolChangeWebPortletKeys.SCHOOL_CHANGE_FORM_SUBMIT%>"
var="saveSchoolChangeFormURL" />

<form id="schoolChangeForm" method="POST" enctype="multipart/form-data">
	<div class="common-forms-div">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="border-0 card shadow">
						<div
							class="card-header d-flex align-item-center justify-content-between">
							<h5>
								<liferay-ui:message key="school-change-request" />
							</h5>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="participant-name" /><sup
											class="text-danger">*</sup></label> <input type="text"
											id="participantName" name="participantName"
											class="form-control" />
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="standards" /><sup
											class="text-danger">*</sup></label> <select class="form-control">
											<option value="">Select</option>
											<option value="8th">8th</option>
											<option value="9th">9th</option>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="from-school" /><sup
											class="text-danger">*</sup></label> <select class="form-control">
											<option value="">Select</option>
											<option value="ABC">ABC</option>
											<option value="PQR">PQR</option>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="to-school" /><sup
											class="text-danger">*</sup></label> <select class="form-control">
											<option value="">Select</option>
											<option value="STY">STY</option>
											<option value="REW">REW</option>
										</select>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="year" /><sup
											class="text-danger">*</sup></label> <select class="form-control">
											<option value="">Select</option>
											<option value="2023-24">2023-24</option>
											<option value="2024-25">2024-25</option>
										</select>
									</div>
								</div>
							</div>

						</div>
						<div class="card-footer bg-transparent text-right p-4">
							<button type="button" onclick="submitSchoolChangeForm()" class="btn btn-primary">submit</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

	<script>
		function submitSchoolChangeForm(){
			
			console.log("School Change Form Submission ::: ");
			
			   var form = $('#schoolChangeForm')[0];
		       var formData = new FormData(form);
		       
		       
		        $.ajax({
			       	type: 'POST',
		           url: '${saveSchoolChangeFormURL}',
		           data: formData,
		           contentType : false,
					cache : false,
					async : false,
		           processData: false,
		           success: function(response) {
		               var jsonResponse = JSON.parse(response);
		               
		               if (jsonResponse.success) {
		               		 console.log("Success :::  ");
		               } else {
		                   console.log("Un Successfull ::: ");
		               }
		           },
		           error: function(error) {
		               console.error("Error while saving : ", error);
		           }
		       }); 
		}
	</script>