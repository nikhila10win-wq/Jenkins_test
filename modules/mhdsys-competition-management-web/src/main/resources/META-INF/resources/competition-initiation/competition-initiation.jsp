<%@ include file="/init.jsp" %>

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5><liferay-ui:message key="create-competition"/></h5>				
						<div>
						<c:if test="${mode eq 'view' }">
						  <a href="/group/guest/competition-initiated-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn">  <i class="bi bi-arrow-left-circle mr-1"></i> <liferay-ui:message key="back" /> </a>
						</c:if>
						<c:if test="${mode eq 'add' }">
						  <a href="/group/guest/competition-master-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn">  <i class="bi bi-arrow-left-circle mr-1"></i> <liferay-ui:message key="back" /> </a>
						</c:if>
						</div>		
					</div>
				<form id="create_comp_initiation"> 
				<div class="card-body">
					<div class="card card-background p-0">
					 <div class="card-header header-card"><liferay-ui:message key="create-competition-master"/></div>
					 <div class="card-body">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="district-taluka"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="districtOrTaluka"
									 id= "districtOrTaluka" onchange="getTalukaForm()" <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value="1" <c:if test="${competitionInitiation.districtOrTaluka == '1'}">selected</c:if>>District</option>
										<option value="2" <c:if test="${competitionInitiation.districtOrTaluka == '2'}">selected</c:if>>Taluka</option>
									</select>
								</div>
							</div>
							<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="district"/></label>
										<input type="text" class="form-control"  name="district"  value="${districtName}" readonly/>
									</div>
								</div>
							</div>
						<div class="row">
							<div class="col-md-4 d-none taluka_div" >
								<div class="form-group">
									<label><liferay-ui:message key="taluka"/></label>
									<select class="form-control" name ="taluka" <c:if test="${ mode eq 'view'}">disabled</c:if>>
										<option value="">Select</option>
										<c:forEach var="taluka" items="${talukas}">
											<option value="${taluka.talukaId}" <c:if test="${competitionInitiation.taluka == taluka.talukaId}">selected="selected"</c:if>>${taluka.talukaName_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="sports-name"/></label>
									<input type="text" class="form-control"  name="sportName"  value="${competitionMaster.sportName }" readonly/>
									<input type="hidden" class="form-control"  name="sportId"  value="${competitionMaster.sportId }" readonly/>
									<input type="hidden" class="form-control"  name="competitionMasterId"  value="${competitionMaster.competitionMasterId }" readonly/>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="category"/></label>
									<input type="text" class="form-control"  name="category"  value="${competitionMaster.categoryName }" readonly/>
									<input type="hidden" class="form-control"  name="categoryId"  value="${competitionMaster.categoryId }" readonly/>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-md-4">
							    <div class="form-group">
							        <label for="ageGroupUnder14"><liferay-ui:message key="age-group-under-14" /></label>
							        <input type="checkbox" id="ageGroupUnder14" name="underForteen" 
							        <c:if test="${competitionMaster.underForteen }">checked </c:if>  disabled class="ageGroupCheckbox" />
							    </div>
							</div>

							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="age-group-under-17"/></label>
									<input type="checkbox"  name="underSeventeen"
									<c:if test="${competitionMaster.underSeventeen }">checked </c:if> disabled class="ageGroupCheckbox" />
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="age-group-under-19"/></label>
									<input type="checkbox" name="underNineteen" 
									<c:if test="${competitionMaster.underNineteen }">checked </c:if> disabled id="underNineteen" class="ageGroupCheckbox"/>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4 d-none taluka_div" >
									<div class="form-group">
										<label><liferay-ui:message key="competition-start-date"/><sup class="text-danger">*</sup></label>
										<input type="date" class="form-control" name="competitionStartDate" 
										 value="${competitionInitiation.competitionStartDateStr}"  <c:if test="${ mode eq 'view'}">readonly</c:if>/>
									</div>
							</div>
							<div class="col-md-4 d-none taluka_div" >
									<div class="form-group">
										<label><liferay-ui:message key="competition-end-date"/><sup class="text-danger">*</sup></label>
										<input type="date" class="form-control" name="competitionEndDate" 
										<c:if test="${ mode eq 'view'}">readonly</c:if> value="${competitionInitiation.competitionEndDateStr }" />
									</div>
							</div>
							<div class="col-md-4 d-none taluka_div" >
								<div class="form-group">
									<label><liferay-ui:message key="venue-details"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="venueDetails" <c:if test="${ mode eq 'view'}">readonly</c:if>
									 value="${competitionInitiation.venueDetails }" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="fees-for-competition"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control mt-3"  name="fees"  value="${competitionInitiation.fees }"
									<c:if test="${ mode eq 'view'}">readonly</c:if> />
								</div>
							</div>
							<%-- <div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees-category-as-per-count-of-total-student"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control"  name="affiliationFeesByTotalCount" 
									 value="${competitionInitiation.affiliationFeesByTotalCount }" <c:if test="${ mode eq 'view'}">readonly</c:if> />
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message key="affiliation-fees-category-as-per-category"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control mt-3"  name="affiliationFeesByCategory"  
									value="${competitionInitiation.affiliationFeesByCategory }" <c:if test="${ mode eq 'view'}">readonly</c:if>/>
								</div>
							</div> --%>
						</div>
						<div class="row">
							<div class="col-md-12">
							    <div class="form-group d-flex mb-1">
							        <input type="checkbox" id="self_Declaration" name="selfDeclaration" 
							        <c:if test="${competitionInitiation.selfDeclaration && mode eq 'view'}">checked disabled </c:if>class="selfDeclaration" />
							        <label for="selfDeclaration" class="ml-3"><liferay-ui:message key="self-declaration" /><sup class="text-danger">*</sup></label>
							    </div>
							</div>
						</div>
					</div>
					</div>
				</form>
					</div>
					
			<c:if test="${mode ne 'view'}">
				<div class="card-footer bg-transparent text-right p-4">
					 <div class="d-flex justify-content-end">
						   <a href="/group/guest/competition-dashboard" class="btn btn-secondary maha-save-btn" id="modalCloseBtn"> <liferay-ui:message key="cancel"/></a>
				            
				           <c:if test="${mode eq 'add'}"> <button type="button" class="btn btn-primary reset-btn" id="reset-btn"> <liferay-ui:message key="reset" /></button> </c:if>
						    
						   <c:if test="${mode eq 'add'}"> <button class="btn btn-primary" onclick="saveCompetitionInitiation()"><liferay-ui:message key="submit" /></button></c:if>
					</div>
				</div>
			</c:if>
				
					<%-- <div class="card-footer bg-transparent text-right p-4">
						<c:if test="${mode eq 'add'}">
							<a href="/group/guest/competition-master-list" type="button" class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
							<button class="btn btn-primary" onclick="saveCompetitionInitiation()"><liferay-ui:message key="save" /></button>
						</c:if>
						<c:if test="${mode eq 'view'}">
							<a href="/group/guest/competition-initiated-list" type="button" class="btn btn-primary"><liferay-ui:message key="cancel" /></a>
						</c:if>
					</div> --%>
					
				</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- modal popup for add competition -->
<div class="modal fade" id="saveCompetitionInitiationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="add-competition-initiation"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup>
                                    <liferay-ui:message key="competition-has-been-successfully-created"/></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/competition-initiated-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for add competition -->

<script>

$("#reset-btn").on("click", function () {
    // Reset only enabled and non-readonly fields
    $('#create_comp_initiation')
        .find(':input')
        .not(':disabled, [readonly]')
        .each(function () {
            if (this.type === 'checkbox' || this.type === 'radio') {
                this.checked = false;
            } else if (this.name === "districtOrTaluka" || this.name === "taluka") {
            	//skip these fields
        	}else {
                $(this).val('');
            }
        });

    // Reset validation
    $('#create_comp_initiation').validate().resetForm();
});

$(document).ready(function () {
	const booleanCheck = '${competitionInitiation.underForteen}'
	console.log("boolean check "+booleanCheck)
    // Add a change event to all checkboxes with the class "ageGroupCheckbox"
    $('.selfDeclaration').on('change', function () {
        if ($(this).is(':checked')) {
            $(this).val('true'); // Set value to true if checked
        } else {
            $(this).val('false'); // Set value to false if unchecked
        }
    });
    $('.ageGroupCheckbox').on('change', function () {
        if ($(this).is(':checked')) {
            $(this).val('true'); // Set value to true if checked
        } else {
            $(this).val('false'); // Set value to false if unchecked
        }
    });
    $('.ageGroupCheckbox').each(function () {debugger
        $(this).val($(this).is(':checked') ? 'true' : 'false');
    });
    if($('#districtOrTaluka').val() == 1){
    	$('.taluka_div').removeClass('d-none');
    	makeFieldMendate('competitionStartDate')
    	makeFieldMendate('competitionEndDate')
    	makeFieldMendate('venueDetails')
    }else{
    	$('.taluka_div').addClass('d-none');
    	removeFieldMendate('competitionStartDate')
    	removeFieldMendate('competitionEndDate')
    	removeFieldMendate('venueDetails')
    }
    
// validation start
     $('#create_comp_initiation').validate({
		onkeyup: function (element) {
			$(element).valid();
		  },
		onchange: function (element) {
			    $(element).valid();
		  },
		rules:{
			fees: {
				required:true,
				pattern: /^\d+(\.\d{1,2})?$/,
				minlength:2,
				maxlength:7,
			},
			/* affiliationFeesByTotalCount: {
				required:true,
				pattern: /^\d+(\.\d{1,2})?$/,
				minlength:2,
				maxlength:7,
			},
			affiliationFeesByCategory: {
				required:true,
				pattern: /^\d+(\.\d{1,2})?$/,
				minlength:2,
				maxlength:7,
			}, */
			selfDeclaration: {
				required:true,
			},
			competitionStartDate: {
				required:true,
			},
			competitionEndDate: {
				required:true,
			},
			venueDetails: {
				required:true,
				minlength:2,
				maxlength:100
			}
			
			
		},messages:{
			fees: {
				required:"<liferay-ui:message key="please-enter-fees"/>",
				pattern: "<liferay-ui:message key="please-enter-valid-fees"/>",
				minlength:"<liferay-ui:message key="minimum-length-is-2"/>",
				maxlength:"<liferay-ui:message key="maximum-length-is-7"/>"
			},
		/* 	affiliationFeesByTotalCount: {
				required:"<liferay-ui:message key="please-enter-affiliation-fees-category-as-per-count-of-total-student"/>",
				pattern: "<liferay-ui:message key="please-enter-valid-affiliation-fees-category-as-per-count-of-total-student"/>",
				minlength:"<liferay-ui:message key="minimum-length-is-2"/>",
				maxlength:"<liferay-ui:message key="maximum-length-is-7"/>"
			},
			affiliationFeesByCategory: {
				required:"<liferay-ui:message key="please-enter-affiliation-fees-category-as-per-category"/>",
				pattern: "<liferay-ui:message key="please-enter-valid-affiliation-fees-category-as-per-category"/>",
				minlength:"<liferay-ui:message key="minimum-length-is-2"/>",
				maxlength:"<liferay-ui:message key="maximum-length-is-7"/>"
			}, */
			selfDeclaration: {
				required:"<liferay-ui:message key="please-check-self-declaration"/>"
			},
			competitionStartDate: {
				required:"<liferay-ui:message key="please-select-competition-start-date"/>"
			},
			competitionEndDate: {
				required:"<liferay-ui:message key="please-select-competition-end-date"/>"
			},
			venueDetails: {
				required:"<liferay-ui:message key="please-enter-venue-details"/>",
				minlength:"<liferay-ui:message key="minimum-length-is-2"/>",
				maxlength:"<liferay-ui:message key="maximum-length-is-100"/>"
			}
			
		},
		 errorPlacement: function (error, element) {
             if (element.attr("type") == "radio") {
                 error.insertAfter(element.parent().parent());
             } else if (element.attr("type") == "checkbox") {
                 error.insertAfter(element.closest('.form-group'));
             } else {
                 error.insertAfter(element);
             }
       },
	}); 
//validation end
});
function getTalukaForm(){
	if($("#districtOrTaluka").val() == 1){
		$(".taluka_div").removeClass('d-none');
	}else{
		$(".taluka_div").addClass('d-none');
	}
}


function saveCompetitionInitiation(){debugger
	var form = $("#create_comp_initiation")[0];
	var formData = new FormData(form);
	if($('#create_comp_initiation').valid()){
 $.ajax({
        type: "POST",
        url: '${saveCompetitionInitiationURL}' ,
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
        	if(data.competitionInitiaion == true){
        		var $jq = jQuery.noConflict();
        		$jq("#saveCompetitionInitiationModal").modal('show');
        		 /* $("#saveCompetitionMasterModal").modal('show');  */
        	}else{
        		var msg = "<liferay-ui:message key="the-competition-initiation-is-unsucessfull"/>";
        		 $("#saveCompetitionInitiationModal").modal('show'); 
        	}
    	 }
       
    });
  }
};
function closeModal() {debugger
    $("#saveCompetitionInitiationModal").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}

function makeFieldMendate(id){
	if(!findProperty($("#"+id).rules(), 'required')){
		$("#"+id).rules("add", {
	        required: true
	       
	    });
	}
}
function removeFieldMendate(id){
	$("#"+id).rules('remove',  'required');
}
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

</script>