<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp"%>

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

<portlet:renderURL var="awardsRedirectURL">
    <portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.AWARDS %>" />
</portlet:renderURL>

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


#awards-points-list_wrapper .top {
        display: flex;
        justify-content: space-between;
}

#awards-points-list_wrapper .bottom {
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
							<liferay-ui:message key="list" />
						</h5>
						<div>
							<a href="/group/guest/awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
								<i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
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
							
							<button class="btn btn-primary" type="button" onclick="location.href='${awardsRedirectURL}&type=awardsPointCreation';">
			                    <liferay-ui:message key="awards-point-creation"/>
			                </button>

							<!-- Filter Card -->
							<div class="filter-card mt-2" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
										
											<liferay-ui:message key="filter-options" />
										
									</div>

									<form id="buyer_purchase" method="POST">
										<div class="card-body">
											<div class="row">

												<div class="col-md-3">
													<div class="form-group">
														<label><liferay-ui:message key="competition-level" /></label>
														<input type="text" class="form-control" id="competitionLevel" name="competitionLevel" />
													</div>
												</div>

												<div class="col-md-3">
													<div class="form-group">
														<label><liferay-ui:message key="award-name" /></label>
														<input type="text" class="form-control" id="awardName" name="awardName" />
													</div>
												</div>

												<div class="col-md-3">
													<div class="form-group">
														<label> <liferay-ui:message key="sport-name" /></label>
														<input type="text" class="form-control" id="sportName" name="sportName" />
													</div>
												</div>

												<div class="col-md-3">
													<div class="form-group">
														<label> <liferay-ui:message key="sport-type" /></label>
														<input type="text" class="form-control" id="sportType" name="sportType" />
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
							<table id="awards-points-list" class="table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th><liferay-ui:message key="competition-level" /></th>
										<th><liferay-ui:message key="award-name" /></th>
										<th><liferay-ui:message key="sports-name" /></th>
										<th><liferay-ui:message key="sports-type" /></th>
										<th><liferay-ui:message key="action" /></th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="awardPoints" items="${awardsList}">
										<portlet:renderURL var="awardPointsViewURL">
											<portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.VIEW_AWARDS_POINTS%>"></portlet:param>
											<portlet:param name="awardsPointsId" value="${awardPoints.awardPointsId }"></portlet:param>
											<portlet:param name="cmd" value="view"></portlet:param>
										</portlet:renderURL>
										
										<portlet:renderURL var="awardPointsEditURL">
											<portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.VIEW_AWARDS_POINTS%>"></portlet:param>
											<portlet:param name="awardsPointsId" value="${awardPoints.awardPointsId }"></portlet:param>
											<portlet:param name="cmd" value="edit"></portlet:param>
										</portlet:renderURL>
										<tr>
											<td>${awardPoints.competitionLevel}</td>
											<td>${awardPoints.awardName}</td>
											<td>${awardPoints.sportsName}</td>
											<td>${awardPoints.sportsType}</td>
											<td>
											<div class="tooltip-icon">
						                           <ul class="inline-item">
						                            <li class="list-inline-item">
						                           <a class="btn btn-sm btn-primary" href="${awardPointsViewURL}"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i>
											</a>
						                            </li>
						                            <li class="list-inline-item">
						                            <a class="btn btn-sm btn-primary" href="${awardPointsEditURL}"><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i>
											</a>
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

	var table = $('#awards-points-list').DataTable({
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
				buttons : [ 'copy', 'csv',
						'excel', 'pdf',
						'print' ]
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
					customize : function(doc) {
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
	$('#competitionLevel').val('');
	$('#awardName').val('');
	$('#sportName').val('');
	$('#sportType').val('');

	if (typeof table !== 'undefined') {
		table.search('').columns().search('').draw();
		$('.dataTables_filter input').val('');
	}
});

$(document).on('click','#searchBtn', function() {
	table.search('').columns().search('').draw();
	$('.dataTables_filter input').val('');

	var getDisplayText = function(selectElement) {
		return selectElement.options[selectElement.selectedIndex].text.trim();
	};

	var competitionLevel = $('#competitionLevel').val().trim();
	var awardName = $('#awardName').val().trim();
	var sportName = $('#sportName').val().trim();
	var sportType = $('#sportType').val().trim();

	console.log("Searching :", competitionLevel);
	console.log("Searching :", awardName);

	if (competitionLevel)table.column(0).search(competitionLevel);
	if (awardName)table.column(1).search(awardName);
	if (sportName)table.column(2).search(sportName);
	if (sportType)table.column(3).search(sportType);

	table.draw();
  });

});
</script>