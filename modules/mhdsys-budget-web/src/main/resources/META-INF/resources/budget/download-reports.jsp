<%@page import="com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.19.5/jquery.validate.min.js"></script>
 
<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.DOWNLOADREPORT %>" var="downloadReportURL" />

<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETDISTRICTS%>"
var="getDistricts" />

<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETSUBCATEGORIES%>"
var="getSubCategories" />

<form id="download_report" method="POST" enctype="multipart/form-data">

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <!-- <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="report-download" /></h5>
                    </div> -->
                    
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="report-download" /></h5>
						<div><a href="/group/guest/budget" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
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
                                                <label><liferay-ui:message key="financial-year"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="financialYear" id="financialYear">
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <option value="2021-22" > <liferay-ui:message key="2021-22"/></option>
                                                    <option value="2022-23" > <liferay-ui:message key="2022-23"/></option>
                                                    <option value="2023-24" > <liferay-ui:message key="2023-24"/></option>
                                                    <option value="2024-25" > <liferay-ui:message key="2024-25"/></option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="category"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="category" id="category">
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <option value="Schemes" > <liferay-ui:message key="schemes"/></option>
                                                    <option value="Committed"> <liferay-ui:message key="committed"/></option>
                                                    <option value="Consolidate"> <liferay-ui:message key="consolidate"/></option>
                                                </select>
                                            </div>
                                        </div>
                                        <%-- <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="division"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="division" id="division" <c:if test="${mode eq 'view' or isHoAdmin}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <c:forEach var="division" items="${divisions}">
														<option value="${division.divisionId}" <c:if test="${fund.division eq division.divisionId}">selected</c:if>>${division.divisionName_en}</option>
													</c:forEach>
                                                </select>
                                            </div>
                                        </div> --%>
                                        
                                        <%-- <div class="col-md-6">
                                            <div class="form-group">
                                                <label><liferay-ui:message key="district"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="district" id="district" <c:if test="${mode eq 'view' or isHoAdmin}">disabled</c:if>>
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                </select>
                                            </div>
                                        </div> --%>
                                    </div>
                                </div>
                            </div>
                            
                            
                            <%-- <div class="card card-background p-0 mb-4">
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
                                        
                                    </div>
                                
                                </div>
                            </div> --%>
                        </div>
 
                        <div class="card-footer bg-transparent text-right p-4">
								
								
									<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/budget';">
                                		<liferay-ui:message key="cancel" />
                            		</button>	
                            		
                            		<button class="btn btn-primary" type="button" onclick="location.reload();">
										<liferay-ui:message key="reset" />
									</button>	
								
									<button class="btn btn-primary" type="button" onclick="downloadReport(event)">
										<liferay-ui:message key="download"/>
									</button>
								
                        </div>
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
       					 <a href="/group/guest/dashboard" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for budget -->



<script>

$(document).ready(function () {
    var isHoAdmin = ${isHoAdmin}; 
    var isDeskOfficer = ${isDeskOfficer}; 

    // j-Query validation start
    $('#download_report').validate({
        onkeyup: function (element) {
            $(element).valid();
        },
        onchange: function (element) {
            $(element).valid();
        },
        
        rules: {
        	financialYear: {
                required: true, 
            },
            category: {       
                required: true,
            },
           
        },
        messages: {
        	financialYear: {
                required: "<liferay-ui:message key='please-select-financial-year'/>",
            },
            category: {       
                required: "<liferay-ui:message key='please-select-category'/>",
            },
           
        },
    }); 
    // j-Query validation end
});


function downloadReport(event){debugger
	console.log('inside submit ')
	var form = $("#download_report")[0];
	var formData = new FormData(form);
	
	if (event) {
        event.preventDefault();
    }
	
	if($('#download_report').valid()){
	       $.ajax({
	        type: "POST",
	        url: '${downloadReportURL}' ,
	        data:  formData,
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data){
	            console.log("Response data:", data);
	            console.log("Download URL : "+data.downloadURL);
	            
               window.open(data.downloadURL, '_blank');
               window.location.href = "/group/guest/budget";
	        },
	        error: function(xhr, status, error) {
	            console.error("Error occurred:", error);
	        }
	        
      });
   }
};
	

$('select[name="division"]').on('change', function () {
    const divisionId = $(this).val();
    if (divisionId) {
        $.ajax({
            url: '${getDistricts}',
            type: 'GET',
            data: {
                divisionId: divisionId
            },
            success: function (data) {
                const $districtSelect = $("#district");
                $districtSelect.empty();
				console.log("Data ::: "+data);
                let districts = typeof data === "string" ? JSON.parse(data) : data;

                if (districts && districts.length > 0) {
                    $districtSelect.append('<option value="">Select</option>');

                    $.each(districts, function (index, value) {
                    	$districtSelect.append('<option value="' + value.districtId + '">' + value.districtName + '</option>');
                    });
                } else {
                    $districtSelect.append('<option value="">No districts available</option>');
                }
            }
            
        });
    }
}); 


$('select[name="category"]').on('change', function () {
    const categoryId = $(this).val();
    if (categoryId) {
        $.ajax({
            url: '${getSubCategories}',
            type: 'GET',
            data: {
            	categoryId: categoryId
            },
            success: function (data) {
                const $subCategorySelect = $("#subCategory");
                $subCategorySelect.empty();
				console.log("Data ::: "+data);
                let subCategories = typeof data === "string" ? JSON.parse(data) : data;

                if (subCategories && subCategories.length > 0) {
                    $subCategorySelect.append('<option value="">Select</option>');

                    $.each(subCategories, function (index, value) {
                    	$subCategorySelect.append('<option value="' + value.subCategoryId + '">' + value.subCategoryName + '</option>');
                    });
                } else {
                    $subCategorySelect.append('<option value="">No sub-category available</option>');
                }
            }
            
        });
    }
}); 
</script>