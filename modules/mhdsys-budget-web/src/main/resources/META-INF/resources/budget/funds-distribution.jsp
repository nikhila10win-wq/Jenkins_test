<%@page import="com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.19.5/jquery.validate.min.js"></script>
 
<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.FUNDDISTRIBUTION %>" var="fundDistributionURL" />

<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETDISTRICTS%>"
var="getDistricts" />

<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETSUBCATEGORIES%>"
var="getSubCategories" />

<form id="fund_distribution" method="POST" enctype="multipart/form-data">

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <!-- <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="fund-distribution" /></h5>
                    </div> -->
                    
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="fund-distribution" /></h5>
					<c:if test="${mode eq 'view' or mode eq 'edit'}">
						<div><a href="/group/guest/fund-distribution-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</c:if>
					<c:if test="${mode eq 'add'}">
						<div><a href="/group/guest/budget" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</c:if>
					</div>
 
                    <form id="GPFDetails" method="POST" enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="card card-background p-0">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="administration" />
                                </div>
 
                                <input type="hidden" class="form-control" id="fundDistributionId" name="fundDistributionId" value="${fund.fundDistributionId}" />
                                <input type="hidden" class="form-control" id="mode" name="mode" value="${mode}" />
 
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="division"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="division" id="division" <c:if test="${mode eq 'view' or isHoAdmin}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <c:forEach var="division" items="${divisions}">
														<option value="${division.divisionId}" <c:if test="${fund.division eq division.divisionId}">selected</c:if>>${division.divisionName_en}</option>
													</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="district"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="district" id="district" <c:if test="${mode eq 'view' or isHoAdmin}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                            
                            
                            <div class="card card-background p-0 mb-4">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="fund-details" />
                                </div>
                                <div class="card-body">
                                
                                <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="category"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="category" id="category" <c:if test="${mode eq 'view' or isHoAdmin}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <c:forEach var="category" items="${categories}">
														<option value="${category.categoryMasterId}" <c:if test="${fund.category eq category.categoryMasterId}">selected</c:if>>${category.name}</option>
													</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="subCategory"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="subCategory" id="subCategory" <c:if test="${mode eq 'view' or isHoAdmin}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-6">
									        <div class="form-group">
									            <label><liferay-ui:message key="budget" /><sup class="text-danger">*</sup></label>
									            <input type="text" class="form-control" id="budget" name="budget"
									                   value="${fund.budget}" 
									                   <c:if test="${mode == 'view' or isHoAdmin}">disabled</c:if> />
									        </div>
									    </div>
								    
                                    </div>
                                
                                </div>
                                
                            </div>
                            
                           <c:if test="${isHoAdmin or not empty fund.remarks}">
                            <div class="card card-background p-0 mb-4">
                                <div class="card-header header-card">
                                    <liferay-ui:message key="remarks" />
                                </div>
                                <div class="card-body">
                                <div class="row">
                                
								    <div class="col-md-6">
								        <div class="form-group">
								            <label><liferay-ui:message key="remarks" /><sup class="text-danger">*</sup></label>
								            <input type="text" class="form-control" id="remarks" name="remarks"
								                   value="${fund.remarks}" 
								                   <c:if test="${mode == 'view' or not empty fund.remarks or isDeskOfficer}">disabled</c:if> />
								        </div>
								    </div>
                                
                                </div>
                                </div>
                            </div>
                           </c:if>
                            
                            
                            
                        </div>
 						
 						<%-- <c:if test="${empty fund.remarks}">
                        <div class="card-footer bg-transparent text-right p-4">
                            <c:if test="${mode eq 'add'}">
								<div class="card-footer bg-transparent text-right p-4">
								
									<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/budget';">
                                		<liferay-ui:message key="cancel" />
                            		</button>	
                            		
                            		<button class="btn btn-primary" type="button" onclick="location.reload();">
										<liferay-ui:message key="reset" />
									</button>	
								
									<button class="btn btn-primary" type="button" onclick="submitDetails(event)">
										<liferay-ui:message key="submit"/>
									</button>
									
								</div>
							</c:if>
							<c:if test="${mode eq 'edit'}">
								<div class="card-footer bg-transparent text-right p-4">
								
									<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/fund-distribution-list';">
                                		<liferay-ui:message key="cancel" />
                            		</button>	
                            		
                            		<button class="btn btn-primary" type="button" onclick="location.reload();">
										<liferay-ui:message key="reset" />
									</button>	
								
									<button class="btn btn-primary" type="button" onclick="submitDetails(event)">
										<liferay-ui:message key="update"/>
									</button>
									
								</div>
							</c:if>
                        </div>
                        </c:if> --%>
                        
                        <c:if test="${empty fund.remarks}">
						        <c:if test="${mode eq 'add'}">
						            <div class="card-footer bg-transparent text-right p-4">
						                <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/budget';">
						                    <liferay-ui:message key="cancel" />
						                </button>	
						                
						                <button class="btn btn-primary" type="button" onclick="location.reload();">
						                    <liferay-ui:message key="reset" />
						                </button>	
						            
						                <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
						                    <liferay-ui:message key="submit"/>
						                </button>
						            </div>
						        </c:if>
						        <c:if test="${mode eq 'edit'}">
						            <div class="card-footer bg-transparent text-right p-4">
						                <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/fund-distribution-list';">
						                    <liferay-ui:message key="cancel" />
						                </button>	
						                
						                <button class="btn btn-primary" type="button" onclick="location.reload();">
						                    <liferay-ui:message key="reset" />
						                </button>	
						            
						                <c:choose>
						                    <c:when test="${isHoAdmin}">
						                        <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
						                            <liferay-ui:message key="submit"/>
						                        </button>
						                    </c:when>
						                    <c:otherwise>
						                        <button class="btn btn-primary" type="button" onclick="submitDetails(event)">
						                            <liferay-ui:message key="update"/>
						                        </button>
						                    </c:otherwise>
						                </c:choose>
						            </div>
						        </c:if>
						</c:if>
                        
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</form>


<!-- modal popup for budget -->
<div class="modal fade" id="fundModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="budget"/></h5>
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
       					 <a href="/group/guest/fund-distribution-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for budget -->



<script>

$(document).ready(function () {
	var mode = '${mode}'; 
	var district = '${fund.district}';
	var subCategoryId = '${fund.subCategory}';
	var divisionId = '${fund.division}';
	var categoryId = '${fund.category}';

	console.log("Initial values - Mode:", mode, "District:", district, "SubCategory:", subCategoryId, "Division:", divisionId, "Category:", categoryId);

	if(mode === 'view' || mode === 'edit'){
	    console.log("View/Edit mode - Initializing dropdowns");
	    districts(divisionId);
	    subCategories(categoryId);
	}

	$('select[name="division"]').on('change', function() {
	    districts($(this).val());
	});

	$('select[name="category"]').on('change', function() {
	    subCategories($(this).val());
	});
	
	
    var isHoAdmin = ${isHoAdmin}; 
    var isDeskOfficer = ${isDeskOfficer}; 

    // j-Query validation start
    $('#fund_distribution').validate({
        onkeyup: function (element) {
            $(element).valid();
        },
        onchange: function (element) {
            $(element).valid();
        },
        
        rules: {
        	division: {
                required: isDeskOfficer, 
            },
            district: {       
                required: isDeskOfficer,
            },
            category: {
                required: isDeskOfficer,
            },
            subCategory: {       
                required: isDeskOfficer,
            },
            budget: {
                required: isDeskOfficer,
                pattern: /^\d*\.?\d+$/,
            },
            remarks: {       
                required: isHoAdmin, 
                startEndSpace: true,
                validateRemarks:true,
                minlength: 3,
                maxlength: 500,
            }
        },
        messages: {
        	division: {
                required: "<liferay-ui:message key='please-select-division'/>",
            },
            district: {       
                required: "<liferay-ui:message key='please-select-district'/>",
            },
            category: {
                required: "<liferay-ui:message key='please-select-category'/>",
            },
            subCategory: {       
                required: "<liferay-ui:message key='please-select-sub-category'/>",
            },
            budget: {
                required: "<liferay-ui:message key='please-enter-budget'/>",
                pattern: "<liferay-ui:message key='please-enter-valid-budget'/>",
            },
            remarks: {
                required: "<liferay-ui:message key='please-enter-remarks'/>",
            }
        },
    }); 
    
    // Custom validation methods
    $.validator.addMethod("startEndSpace", function(value, element) {
        return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
    }, "<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");
    
    $.validator.addMethod("validateRemarks", function(value, element) {
 	   const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
 	    return this.optional(element) || regex.test(value);
 	}, "<liferay-ui:message key='please-enter-valid-remarks' />");
    
    // j-Query validation end
});


function submitDetails(event){debugger
	console.log('inside submit ')
	var form = $("#fund_distribution")[0];
	var formData = new FormData(form);
	
	if (event) {
        event.preventDefault();
    }
	
	if($('#fund_distribution').valid()){
	       $.ajax({
	        type: "POST",
	        url: '${fundDistributionURL}' ,
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
	        	if(data.fund == true){
	        		var msg = "<liferay-ui:message key="fund-distribution-details-saved-successfully"/>";
	        	    $('#success-application').html(msg);
	        		 $("#fundModal").modal('show');
	        	if (data.mode == 'edit') {
        	        if (data.isHoAdmin) {
        	            msg = "<liferay-ui:message key='fund-distribution-details-verified-successfully'/>";
        	        } else {
        	            msg = "<liferay-ui:message key='fund-distribution-details-updated-successfully'/>";
        	        }
        	    } else { 
        	        msg = "<liferay-ui:message key="fund-distribution-details-saved-successfully"/>";
        	    }
        	    
	        	 $('#success-application').html(msg);
	        	 $("#fundModal").modal('show');
        	} else{
	        		var msg = "<liferay-ui:message key="fund-distribution-details-are-failed-to-submit"/>";
	        	    $('#success-application').html(msg);
	        		 $("#fundModal").modal('show');
	        	}
	        }
      });
   }
};
	
function closeModal() {debugger
    $("#fundModal").modal('hide');
 	$(".modal-backdrop").remove();
 	$("body").removeClass("modal-open");
}

function districts(divisionId) {
    console.log("Division :::::    "+divisionId);
    var mode = '${mode}'; 
	var district = '${fund.district}';
	var subCategoryId = '${fund.subCategory}';
	
	console.log("Initial values - Mode:", mode, "District:", district, "SubCategory:", subCategoryId);

    $.ajax({
        url: '${getDistricts}',
        type: 'GET',
        data: { divisionId: divisionId },
        success: function(data) {
            const $districtSelect = $("#district");
            $districtSelect.empty();
            let districts = typeof data === "string" ? JSON.parse(data) : data;

            if (districts && districts.length > 0) {
                $districtSelect.append('<option value="">Select</option>');
                $.each(districts, function(index, value) {
                	console.log("Values :::;;    "+value.districtId);
                	console.log("Second Values ::::::::    "+district);
                    let selectedAttr = (mode === 'view' || mode === 'edit') && value.districtId == district ? ' selected' : '';
                    console.log("Selected values ::::: ."+selectedAttr +";;");
                    $districtSelect.append('<option value="' + value.districtId + '"' + selectedAttr + '>' + value.districtName + '</option>');
                });
            } else {
                $districtSelect.append('<option value="">No districts available</option>');
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching districts:", error);
            $("#district").empty().append('<option value="">Error loading districts</option>');
        }
    });
}

function subCategories(categoryId) {
	var mode = '${mode}'; 
	var district = '${fund.district}';
	var subCategoryId = '${fund.subCategory}';
	
	console.log("Initial values - Mode:", mode, "District:", district, "SubCategory:", subCategoryId);

    $.ajax({
        url: '${getSubCategories}',
        type: 'GET',
        data: { categoryId: categoryId },
        success: function(data) {
            const $subCategorySelect = $("#subCategory");
            $subCategorySelect.empty();
            let subCategories = typeof data === "string" ? JSON.parse(data) : data;

            if (subCategories && subCategories.length > 0) {
                $subCategorySelect.append('<option value="">Select</option>');
                $.each(subCategories, function(index, value) {
                    let selectedAttr = (mode === 'view' || mode === 'edit') && value.subCategoryId == subCategoryId ? ' selected' : '';
                    $subCategorySelect.append('<option value="' + value.subCategoryId + '"' + selectedAttr + '>' + value.subCategoryName + '</option>');
                });
            } else {
                $subCategorySelect.append('<option value="">No sub-category available</option>');
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching subcategories:", error);
            $("#subCategory").empty().append('<option value="">Error loading subcategories</option>');
        }
    });
}
</script>