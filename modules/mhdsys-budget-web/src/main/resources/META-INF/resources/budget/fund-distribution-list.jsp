<%@page import="com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
 
<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.7.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/vfs_fonts.js"></script>

<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETDISTRICTS%>"
var="getDistricts" />

<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETSUBCATEGORIES%>"
var="getSubCategories" />

<style>
/* Custom styles for smooth transition */
.filter-card {
    display: none;
    opacity: 0;
    transition: all 0.3s ease-in-out;
}

.filter-card.show {
    display: block;
    opacity: 1;
}

#fund-distribution-list_wrapper .top {
        display: flex;
        justify-content: space-between;
}

#fund-distribution-list_wrapper .bottom {
    display: flex;
    justify-content: space-between;
    align-items: baseline;
}  
</style>

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <!-- <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="fund-distribution-list" /></h5>
                        
                    </div> -->
                    
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="fund-distribution-list" /></h5>
						<div><a href="/group/guest/budget" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
					
                    
                    <div class="card-body">
						<!-- filter -->
						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>

							<!-- Filter Card -->
							<div class="filter-card mt-4" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
									
											<liferay-ui:message key="filter-options" />
									
									</div>

							<form id="fund_distribution" method="POST">
									<div class="card-body">
										<div class="row">
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="division"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="division" id="division" >
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <c:forEach var="division" items="${divisions}">
														<option value="${division.divisionId}">${division.divisionName_en}</option>
													</c:forEach>
                                                </select>
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="district"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="district" id="district">
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                </select>
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="category"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="category" id="category">
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                    <c:forEach var="category" items="${categories}">
														<option value="${category.categoryMasterId}" >${category.name}</option>
													</c:forEach>
                                                </select>
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="subCategory"/><sup class="text-danger">*</sup></label>
                                                <select class="form-control" name ="subCategory" id="subCategory">
                                                    <option value=""><liferay-ui:message key="select"/></option>
                                                </select>
                                            </div>
										    </div>
										
										</div>
									</div>
								</form>
									
									<div class="card-footer text-right bg-white">
										<button type="button" class="btn btn-success" id="resetBtn">
					                        <liferay-ui:message key="reset" />
					                    </button>
										
										<button type="submit" class="btn btn-success" id="searchBtn">
											<liferay-ui:message key="search" />
										</button>
									</div>
								</div>
							</div>
						</div>
						<!-- filter  -->
						
                    <!-- Table -->
                    <div class="universal-table mt-4">
                    <div class="">
                        <table id="fund-distribution-list" class="table-bordered" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th><liferay-ui:message key="division" /></th>
                                    <th><liferay-ui:message key="district" /></th>
                                    <th><liferay-ui:message key="category" /></th>
                                    <th><liferay-ui:message key="sub-category" /></th>
                                    <th><liferay-ui:message key="action" /></th>
                                </tr>
                            </thead>
                            <tbody>
                               <c:forEach var="fund" items="${fundList}">
                                    <portlet:renderURL var="fundViewURL">
										<portlet:param name="mvcRenderCommandName" value="<%=MhdsysBudgetWebPortletKeys.FUNDDISTRIBUTIONVIEW%>"></portlet:param>
										<portlet:param name="fundDistributionId" value="${fund.fundDistributionId }"></portlet:param>
										<portlet:param name="mode" value="view"></portlet:param>
										<portlet:param name="isHoAdmin" value="${isHoAdmin}"></portlet:param>
									 </portlet:renderURL>
									 <portlet:renderURL var="fundEditURL">
										<portlet:param name="mvcRenderCommandName" value="<%=MhdsysBudgetWebPortletKeys.FUNDDISTRIBUTIONVIEW%>"></portlet:param>
										<portlet:param name="fundDistributionId" value="${fund.fundDistributionId }"></portlet:param>
										<portlet:param name="mode" value="edit"></portlet:param>
										<portlet:param name="isHoAdmin" value="${isHoAdmin}"></portlet:param>
									 </portlet:renderURL>
 
                                     <tr>
                                        <td>${fund.divisionStr}</td>
                                        <td>${fund.districtStr}</td>
                                        <td>${fund.categoryStr}</td>
                                        <td>${fund.subCategoryStr}</td>
                                        <td>
                                        
                                        <div class="tooltip-icon">
											<ul class="inline-item">
												<li class="list-inline-item">
													<c:if test="${isDeskOfficer}">
														<a href="${fundViewURL}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a>
													<c:if test="${empty fund.remarks}">
														<a href="${fundEditURL}" class="btn btn-primary"><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i></a>
													</c:if>
													</c:if>
												</li>
												<%-- <li class="list-inline-item">
													<c:if test="${isHoAdmin}">
														<a href="${fundEditURL}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="verify" />"></i></a>
													</c:if>
												</li> --%>
												<li class="list-inline-item">
												    <c:if test="${isHoAdmin}">
												            <a href="${fundEditURL}" class="btn btn-primary">
												                <i class="bi bi-eye icons-color" 
												                   title="<liferay-ui:message key='${empty fund.remarks ? "view-verify" : "view"}' />">
												                </i>
												            </a>
												    </c:if>
												</li>
												
											</ul>
										</div>
                                        
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                            </tbody>
                        </table>
                        </div>
                    </div>
                  </div>
                </div>
            </div>
        </div>
</div>
</div>

<script>
$(document).ready(function() {
	   
jQuery.noConflict();

var table = $('#fund-distribution-list').DataTable({
    "paging": true,
    "ordering": false,
    "searching": true,
    "responsive": true,
    "autoWidth": false,
    "language": {
		  "search": '<liferay-ui:message key="search" />',
			"emptyTable": '<liferay-ui:message key="no-data-available-in-table" />',
		  "infoFiltered": "",
		  "lengthMenu": '_MENU_',  
		  "info": "<liferay-ui:message key="Showing-total-entries" />",
		  "zeroRecords": "<liferay-ui:message key="No-matching-records-found" />",
		  "infoEmpty": "<liferay-ui:message key='Showing-total-entries' />",
		   oPaginate: {
			    sNext: '<em class="bi bi-chevron-right"></em>',
			    sPrevious: '<em class="bi bi-chevron-left"></em>',
			    }
	    },
	    "layout": {
	        topStart: {
	            buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
	        }
	    },
    "dom": '<"top"Bf>rt<"bottom"lip><"clear">',
    "buttons": [
        {
            extend: 'copy',
            exportOptions: {
                columns: ':not(:last-child)'
            }
        },
        {
            extend: 'csv',
            exportOptions: {
                columns: ':not(:last-child)'
            }
        },
        {
            extend: 'excel',
            exportOptions: {
                columns: ':not(:last-child)'
            },
            customize: function(xlsx) {
                var sheet = xlsx.xl.worksheets['sheet1.xml'];
            }
        },
        {
            extend: 'pdf',
            exportOptions: {
                columns: ':not(:last-child)'
            },
            customize: function(doc) {
                doc.defaultStyle.fontSize = 8;
                doc.styles.tableHeader.fontSize = 9;
                doc.pageMargins = [20, 20, 20, 20];
            }
        },
        {
            extend: 'print',
            exportOptions: {
                columns: ':not(:last-child)'
            }
        }
    ],
    "initComplete": function() {
    	 $('.buttons-copy').html('<i class="bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
	        $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
	        $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
	        $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
	        $('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
    }
});




$('#filterBtn').click(function() {
	$('#division').val('');
    $('#district').val('').html('<option value=""><liferay-ui:message key="select" /></option>');
    
    $('#category').val('');
    $('#subCategory').val('').html('<option value=""><liferay-ui:message key="select" /></option>');
    
    const $filterCard = $('#filterCard');
    $filterCard.toggleClass('show');
    $(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />' : '<liferay-ui:message key='filter' />');
});


$('#resetBtn').click(function() {
    $('#division').val('');
    $('#district').val('').html('<option value=""><liferay-ui:message key="select" /></option>');
    
    $('#category').val('');
    $('#subCategory').val('').html('<option value=""><liferay-ui:message key="select" /></option>');
    
    if (typeof table !== 'undefined') {
        table.search('').columns().search('').draw();
        $('.dataTables_filter input').val('');
    }
});


$(document).on('click', '#searchBtn', function () {
    table.search('').columns().search('').draw();
    $('.dataTables_filter input').val('');
    
    var getDisplayText = function(selectElement) {
        return selectElement.options[selectElement.selectedIndex].text.trim();
    };
    
    var division = getDisplayText(document.getElementById('division'));
    var district = getDisplayText(document.getElementById('district'));
    
    var category = getDisplayText(document.getElementById('category'));
    var subCategory = getDisplayText(document.getElementById('subCategory'));
    
    var selectText = $('#division option[value=""]').text().trim();
    if (division === selectText) division = "";
    if (district === selectText) district = "";
    
    var selectText = $('#category option[value=""]').text().trim();
    if (category === selectText) category = "";
    if (subCategory === selectText) subCategory = "";
    
    console.log("Searching division :", division);
    console.log("Searching district:", district);
    
    if (division) table.column(0).search(division, true, false, true); 
    if (district) table.column(1).search(district, true, false, true); 
    if (category) table.column(2).search(category, true, false, true); 
    if (subCategory) table.column(3).search(subCategory, true, false, true); 
    
    table.draw();
});


});



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

<style>
.universal-table tbody tr td:last-child {
    display: table-cell;
    }
</style>



 