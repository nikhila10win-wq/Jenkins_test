<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>
<%@ include file="/init.jsp"%>

<portlet:resourceURL id="<%=MhdsysGrantsAndSchemesPortletKeys.DOWNLOAD_APPLICATIONS%>" var="downloadApplicationURL" />

<style>
#application-list_wrapper .top {
        display: flex;
        justify-content: space-between;
}

#application-list_wrapper .bottom {
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
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0">
							<liferay-ui:message key="application-for-funds-from-deputy-director" />
						</h5>
						<div>
							<a href="/group/guest/grants-and-scheme" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>
								 <liferay-ui:message key="back" />
							</a>
						</div>
					</div>

					<div class="card-body">
					
			<div class="card card-background p-0 mb-4">
				<div class="card-header header-card">
					<liferay-ui:message key="grants-and-schemes-details" />
				</div>
					
						<!-- filter -->
						<!-- <div class="filter-container"> -->
							<!-- Filter Toggle Button -->
							<!-- <button class="btn btn-primary" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button> -->
							
							<!-- Filter Card -->
							<!-- <div class="card filter-card mt-2" id="filterCard"> -->
								<!-- <div class="card-body p-0"> -->
									<!-- <div class="card-header">
										<h5 class="card-title m-0">
											<liferay-ui:message key="filter-options" />
										</h5>
									</div> -->

									<form id="grant_and_scheme" method="POST">
										<div class="card-body">
											<div class="row">
												
												<div class="col-md-6">
													<div class="form-group">
														<label><liferay-ui:message key="category" /></label> 
														<select class="form-control" name="category" id="category">
															<option value=""><liferay-ui:message key="select" /></option>
															<option value="Gymnasium Development"> <liferay-ui:message key="gymnasium-development" /></option>
															<option value="Playground Development"> <liferay-ui:message key="playground-development" /></option>
														</select>
													</div>
												</div>

												<div class="col-md-6">
													<div class="form-group">
														<label><liferay-ui:message key="sub-category" /></label> 
														<select class="form-control" name="subcategory" id="subcategory">
															<option value=""><liferay-ui:message key="select" /></option>
															<option value="Sub Category 1"> <liferay-ui:message key="sub-category-1" /></option>
															<option value="Sub Category 2"> <liferay-ui:message key="sub-category-2" /></option>
															<option value="Sub Category 3"> <liferay-ui:message key="sub-category-3" /></option>
														</select>
													</div>
												</div>

											</div>
											<div class="text-right">
												<button type="button" class="btn btn-primary" id="resetBtn">
													<liferay-ui:message key="reset" />
												</button>
		
												<button type="button" class="btn btn-primary" id="searchBtn">
													<liferay-ui:message key="search" />
												</button>
											</div>
										</div>
									</form>

								<!-- </div> -->
							<!-- </div> -->
						<!-- </div> -->
				
				</div>
						
						
						<!-- filter  -->
		<div class="card card-background p-0 mb-4">
				<div class="card-header header-card">
					<liferay-ui:message key="application-list" />
				</div>
						<div class="universal-table m-3">
							<table id="application-list" class="table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th><input type="checkbox" id="selectAll" /></th>
										<th><liferay-ui:message key="category" /></th>
										<th><liferay-ui:message key="subcategory" /></th>
										<th><liferay-ui:message key="project" /></th>
										<th><liferay-ui:message key="type" /></th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="application" items="${applicationList}">
									<c:if test="${!(isDeputyDirector && application.ddVerification != 'Approve')}">
										
										<tr>
											<td><input type="checkbox" class="row-checkbox"
											value="${application.districtGrantSchemeId}" /></td>
											<td>${application.category}</td>
											<td>${application.subCategory}</td>
											<td>${application.project}</td>
											<td>${application.type}</td>
										</tr>
									</c:if>
									</c:forEach>
								</tbody>
							</table>
								</div>
						</div>
						
							<div class="card-footer bg-transparent text-right p-4">
								<form id="download-form">
									<div class="row mt-3">
										<div class="col-md-12 text-right">
											<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/grants-and-scheme';">
								                <liferay-ui:message key="cancel" />
								            </button>
											<button class="btn btn-primary submitBtn" disabled>
												<liferay-ui:message key="download" />
											</button>
										</div>
									</div>
								</form>
							</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>

<script>

$(document).ready(function() {
jQuery.noConflict();

var table = $('#application-list').DataTable({
	"paging" : true,
	"ordering" : false,
	"searching" : true,
	"responsive" : true,
	"autoWidth" : false,
	"language" : {
		"search" : '<liferay-ui:message key="search" />',
		"emptyTable" : '<liferay-ui:message key="no-data-available-in-table" />',
		"infoFiltered" : "",
		"lengthMenu" : '_MENU_',
		"info" : "<liferay-ui:message key="Showing-total-entries" />",
		"zeroRecords" : "<liferay-ui:message key="No-matching-records-found" />",
		"infoEmpty" : "<liferay-ui:message key='Showing-total-entries' />",
		oPaginate : {
			sNext : '<em class="bi bi-chevron-right"></em>',
			sPrevious : '<em class="bi bi-chevron-left"></em>',
		}
	},
	"layout" : {
		topStart : {
			buttons : [ 'copy', 'csv', 'excel', 'pdf', 'print' ]
		}
	},
	"dom" : '<"top"Bf>rt<"bottom"lip><"clear">',
	"buttons" : [
			{
				extend : 'copy',
				exportOptions : {
					columns : ':not(:last-child)'
				}
			},
			{
				extend : 'csv',
				exportOptions : {
					columns : ':not(:last-child)'
				}
			},
			{
				extend : 'excel',
				exportOptions : {
					columns : ':not(:last-child)'
				},
				customize : function(xlsx) {
					var sheet = xlsx.xl.worksheets['sheet1.xml'];
				}
			},
			{
				extend : 'pdf',
				exportOptions : {
					columns : ':not(:last-child)'
				},
				customize : function(
						doc) {
					doc.defaultStyle.fontSize = 8;
					doc.styles.tableHeader.fontSize = 9;
					doc.pageMargins = [ 20, 20, 20, 20 ];
				}
			},
			{
				extend : 'print',
				exportOptions : {
					columns : ':not(:last-child)'
				}
			} ],
	"initComplete" : function() {
		$('.buttons-copy').html('<i class="bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
		$('.buttons-csv').html('<i class="bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
		$('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
		$('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
		$('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
	}
});

$('#filterBtn').click(function() {
	const $filterCard = $('#filterCard');
	$filterCard.toggleClass('show');
	$(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />': '<liferay-ui:message key='filter' />');
});

$('#resetBtn').click(function() {
	$('#category').val('');
	$('#subcategory').val('');

	if (typeof table !== 'undefined') {
		table.search('').columns().search('').draw();
		$('.dataTables_filter input').val('');
	}
});

$(document).on('click', '#searchBtn', function() {
	table.search('').columns().search('').draw();
	$('.dataTables_filter input').val('');

	var getDisplayText = function(selectElement) {
		return selectElement.options[selectElement.selectedIndex].text.trim();
	};

	var category = $('#category').val().trim();
	var subcategory = $('#subcategory').val().trim();

	console.log("Searching :", category);
	console.log("Searching :", subcategory);

	if (category)table.column(1).search(category);
	if (subcategory)table.column(2).search(subcategory);

	table.draw();
});

});



$('#selectAll').on('change', function() {
	debugger
	console.log("checked: ", $(this).prop('checked'));
	var isChecked = $(this).prop('checked');
	$('.row-checkbox:not(:disabled)').prop('checked', isChecked);
	toggleSubmitButton();
});

$(document).on('change','.row-checkbox',function() {
	var allChecked = $('.row-checkbox:not(:disabled)').length === $('.row-checkbox:checked:not(:disabled)').length;
	$('#selectAll').prop('checked', allChecked);
	toggleSubmitButton();
});

function toggleSubmitButton() {
	var atLeastOneChecked = $('.row-checkbox:checked').length > 0;
	$('.submitBtn').prop('disabled', !atLeastOneChecked);
}



$('.submitBtn').on('click', function(event) {
	event.preventDefault();
	var formData = new FormData($('#download-form')[0]);

	var selectedIds = $('.row-checkbox:checked').map(function() {
		return $(this).val();
	}).get();
	
	formData.append('selectedIds', JSON.stringify(selectedIds));
	$.ajax({
		type : "POST",
		url : '${downloadApplicationURL}',
		data : formData,
		contentType : false,
		cache : false,
		processData : false,
		success : function(data) {
			
			 console.log("Response data:", data);
	            console.log("Download URL : "+data.downloadURL);
	            
            window.open(data.downloadURL, '_blank');
            window.location.href = "/group/guest/district-level";
			
		},
		error : function(error) {
			console.error("Error occurred : ", error);
		}
	});
});
</script>