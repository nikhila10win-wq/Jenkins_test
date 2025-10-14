<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>
<%@ include file="/init.jsp" %>

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
    
</style>


<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="application-list" /></h5>
						<!-- <div><a href="/group/guest/state-level" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div> -->
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

							<form id="aspiring-athelet-filter" method="POST">
									<div class="card-body">
										<div class="row">
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="full-name"/></label>
                                                <input type="text" class="form-control" id="fullName" name="fullName" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="competition-level"/></label>
                                                <input type="text" class="form-control" id="competitionLevel" name="competitionLevel" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="competition-name" /></label>
                                                <input type="text" class="form-control" id="competitionName" name="competitionName" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="mobile-number" /></label>
                                                <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" />
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
						<table id="aspiring-athelet-table" class="table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><liferay-ui:message key="full-name"/></th>
							<th><liferay-ui:message key="competition-level"/></th>
							<th><liferay-ui:message key="competition-name"/></th>
							<th><liferay-ui:message key="mobile-number"/></th>
							<th><liferay-ui:message key="action"/></th>
						</tr>
					</thead> 
					 
					<tbody>
					 <c:forEach var="intSportsComp" items="${intSportsCompList}">
					 
					 <portlet:renderURL var="intSportsCompViewURL">
						<portlet:param name="mvcRenderCommandName" value="<%=MhdsysGrantsAndSchemesPortletKeys.STATE_LEVEL_GRANTS_AND_SCHEMES_RENDER%>"></portlet:param>
						<portlet:param name="intSportsCompId" value="${intSportsComp.intSportsCompId }"></portlet:param>
						<portlet:param name="mode" value="view"></portlet:param>
						<portlet:param name="application" value="intSportsComp"></portlet:param>
					 </portlet:renderURL>
					 
					 <%-- <portlet:renderURL var="budgetEditURL">
						<portlet:param name="mvcRenderCommandName" value="<%=MhdsysBudgetWebPortletKeys.BUDGETADDITIONVIEW%>"></portlet:param>
						<portlet:param name="budgetId" value="${budget.budgetId }"></portlet:param>
						<portlet:param name="mode" value="edit"></portlet:param>
						<portlet:param name="isHoAdmin" value="${isHoAdmin}"></portlet:param>
					 </portlet:renderURL> --%>
					 
						<tr>
							<td>${intSportsComp.fullName}</td>
							<td>${intSportsComp.competitionLevel}</td>
							<td>${intSportsComp.competitionName}</td>
							<td>${intSportsComp.mobileNo}</td>
							<td>
							<div class="tooltip-icon">
						                           <ul class="inline-item">
						                            
							<c:if test="${!isDeskOfficer && !isDeputyDirector }">
								<li class="list-inline-item"><a class="btn btn-sm btn-primary" href="${intSportsCompViewURL}"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a></li>
							</c:if>
							<c:if test="${isDeskOfficer || isDeputyDirector}">
								<li class="list-inline-item"><a class="btn btn-sm btn-primary" href="${intSportsCompViewURL}"><i class="bi bi-check-circle icons-color" title="<liferay-ui:message key="verify" />"></i></a></li>
							</c:if>
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


<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>


<script>
$(document).ready(function() {
	   
jQuery.noConflict();
	
var table = $('#aspiring-athelet-table').DataTable({
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
    const $filterCard = $('#filterCard');
    $filterCard.toggleClass('show');
    $(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />' : '<liferay-ui:message key='filter' />');
});


$('#resetBtn').click(function () {
    $('#aspiring-athelet-filter input').val('');
    table.columns().search('').draw();
});


$(document).on('click', '#searchBtn', function () {
    table.search('').columns().search('').draw();
    $('.aspiring-athelet-filter input').val('');
    
    var getDisplayText = function(selectElement) {
        return selectElement.options[selectElement.selectedIndex].text.trim();
    };
    
    var fullName = $('#fullName').val().trim();
    var competitionLevel = $('#competitionLevel').val().trim();
    var competitionName = $('#competitionName').val().trim();
    var mobileNumber = $('#mobileNumber').val().trim();
    
    console.log("Searching :", fullName);
    console.log("Searching :", competitionLevel);
    
    if (department) table.column(0).search(department);
    if (financialYear) table.column(1).search(financialYear);
    if (budgetDate) table.column(2).search(budgetDate);
    if (overallPercentage) table.column(3).search(overallPercentage);
    
    table.draw();
});

});

</script>