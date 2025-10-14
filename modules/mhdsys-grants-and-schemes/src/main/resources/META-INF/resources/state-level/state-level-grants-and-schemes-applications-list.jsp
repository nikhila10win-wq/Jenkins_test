<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>
<%@ include file="/init.jsp"%>

<style>
.filter-card {
	display: none;
	opacity: 0;
	transition: all 0.3s ease-in-out;
}

.filter-card.show {
	display: block;
	opacity: 1;
}
</style>

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0">
							<liferay-ui:message key="application-list" />
						</h5>
						<div>
							<a href="/group/guest/grants-and-scheme" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>
								 <liferay-ui:message key="back" />
							</a>
						</div>
					</div>

					<div class="card-body">
						<!-- filter -->
						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>
							<!-- Apply Button For New Application -->
						
							<!-- Filter Card -->
							<div class=" filter-card mt-2" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
											<liferay-ui:message key="filter-options" />
									</div>

									<form id="grant_and_scheme" method="POST">
										<div class="card-body">
											<div class="row">
												
												<div class="col-md-3">
													<div class="form-group">
														<label><liferay-ui:message key="category" /></label> 
														<select class="form-control" name="category" id="category">
															<option value=""><liferay-ui:message key="select" /></option>
															<option value="Gymnasium Development"> <liferay-ui:message key="gymnasium-development" /></option>
															<option value="Playground Development"> <liferay-ui:message key="playground-development" /></option>
														</select>
													</div>
												</div>

												<div class="col-md-3">
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

												<div class="col-md-3">
													<div class="form-group">
														<label> <liferay-ui:message key="project" /></label> 
														<select class="form-control" name="project" id="project">
															<option value=""><liferay-ui:message key="select" /></option>
															<option value="Project 1"> <liferay-ui:message key="project-1" /></option>
															<option value="Project 2"> <liferay-ui:message key="project-2" /></option>
															<option value="Project 3"> <liferay-ui:message key="project-3" /></option>
														</select>
													</div>
												</div>

												<div class="col-md-3">
													<div class="form-group">
														<label> <liferay-ui:message key="type" /></label> 
														<select class="form-control" name="type" id="type">
															<option value=""><liferay-ui:message key="select" /></option>
															<option value="Type 1"> <liferay-ui:message key="type-1" /></option>
															<option value="Type 2"> <liferay-ui:message key="type-2" /></option>
															<option value="Type 3"> <liferay-ui:message key="type-3" /></option>
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

						<div class="universal-table mt-4">
						<div class="">
							<table id="application-list" class="table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th><liferay-ui:message key="category" /></th>
										<th><liferay-ui:message key="subcategory" /></th>
										<th><liferay-ui:message key="project" /></th>
										<th><liferay-ui:message key="type" /></th>
										<th><liferay-ui:message key="action" /></th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="application" items="${applicationList}">
									<c:if test="${!(isDeputyDirector && application.hoVerification != 'Approve') || !isDeputyDirector}">
										<portlet:renderURL var="applicationViewURL">
											<portlet:param name="mvcRenderCommandName" value="<%=MhdsysGrantsAndSchemesPortletKeys.DISTRICT_LEVEL_GRANTS_AND_SCHEMES_RENDER%>"></portlet:param>
											<portlet:param name="districtGrantSchemeId" value="${application.districtGrantSchemeId }"></portlet:param>
											<portlet:param name="mode" value="view"></portlet:param>
										</portlet:renderURL>
										
										<portlet:renderURL var="applicationEditURL">
											<portlet:param name="mvcRenderCommandName" value="<%=MhdsysGrantsAndSchemesPortletKeys.DISTRICT_LEVEL_GRANTS_AND_SCHEMES_RENDER%>"></portlet:param>
											<portlet:param name="districtGrantSchemeId" value="${application.districtGrantSchemeId }"></portlet:param>
											<portlet:param name="mode" value="edit"></portlet:param>
										</portlet:renderURL>
										
										<tr>
											<td>${application.category}</td>
											<td>${application.subCategory}</td>
											<td>${application.project}</td>
											<td>${application.type}</td>
											<td>
											<div class="tooltip-icon">
						                           <ul class="inline-item">
											<%-- <c:if test="${!isHoAdmin && !isDeputyDirector}"> 
												<a href="${applicationViewURL}"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a>
												<a href="${applicationEditURL}"><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i></a>
											</c:if>  --%>
											
											<c:if test="${!isDeskOfficer && !isDeputyDirector}"> 
											    <li class="list-inline-item"><a class="btn btn-sm btn-primary" href="${applicationViewURL}"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a></li>
											    <c:if test="${empty application.hoVerification}">
											        <li class="list-inline-item"><a class="btn btn-sm btn-primary" href="${applicationEditURL}"><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i></a></li>
											    </c:if>
											</c:if>
											
											<c:if test="${isDeskOfficer || isDeputyDirector}">
												<li class="list-inline-item"><a class="btn btn-sm btn-primary" href="${applicationEditURL}"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view-verify" />"></i></a></li>
											</c:if>
											
											</ul>
						                        </div>
											</td>
										</tr>
									</c:if>
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
	$('#project').val('');
	$('#type').val('');

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
	var project = $('#project').val().trim();
	var type = $('#type').val().trim();

	console.log("Searching :", category);
	console.log("Searching :", subcategory);

	if (category)table.column(0).search(category);
	if (subcategory)table.column(1).search(subcategory);
	if (project)table.column(2).search(project);
	if (type)table.column(3).search(type);

	table.draw();
});

});

</script>