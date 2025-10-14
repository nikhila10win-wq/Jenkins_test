<%@page import="com.mhdsys.grievance.complaint.web.constants.MhdsysGrievanceComplaintWebPortletKeys"%>
<%@ include file="/init.jsp" %>
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

    #construction-tracker-list_wrapper .top {
        display: flex;
        justify-content: space-between;
    }

    #aconstruction-tracker-list_wrapper .bottom {
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
                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
                        <h5><liferay-ui:message key="grievance-list" /></h5>
                        <div>
							<a href="/group/guest/grievance-dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn" style="background-color: #26268E;
						    border-color: #fff;"><i class="bi bi-arrow-left-circle"></i><liferay-ui:message key="back" /></a>
						</div>
                    </div>
                    <div class="card-body">

                        <!-- Filter -->
                        <div class="filter-container">
                            <button class="btn btn-primary m-0" id="filterBtn">
                                <liferay-ui:message key="filter" />
                            </button>

                            <div class="filter-card mt-4" id="filterCard">
                                <div class="card card-background p-0">
                                    <div class="card-header header-card">
                                        
                                            <liferay-ui:message key="filter-options" />
                                      
                                    </div>
                                    <form id="grievance_filter_form" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="division" /></label>
                                                    <input type="text" class="form-control" id="division" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="district" /></label>
                                                    <input type="text" class="form-control" id="district" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="taluka" /></label>
                                                    <input type="text" class="form-control" id="taluka" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="type-of-complaint" /></label>
                                                    <input type="text" class="form-control" id="complaintType" />
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
                        <!-- Filter End -->

                        <!-- Table -->
                        <div class="universal-table mt-4">
                            <table id="grievance-list" class="table-bordered" width="100%">
                                <thead>
                                    <tr>
                                        <th><liferay-ui:message key="division" /></th>
                                        <th><liferay-ui:message key="district" /></th>
                                        <th><liferay-ui:message key="taluka" /></th>
                                        <th><liferay-ui:message key="type-of-complaint" /></th>
                                        <th><liferay-ui:message key="action" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                    <c:forEach var="grievance" items="${grievanceList}" varStatus="status">
                                      		<portlet:renderURL var="verifyGrievanceURL">
												<portlet:param name="mvcRenderCommandName"
													value="<%=MhdsysGrievanceComplaintWebPortletKeys.GRIEVANCE_MVC_RENDER_COMMAND%>" />
												<portlet:param name="grievanceId"
													value="${grievance.grievanceId}" />
												<portlet:param name="mode"
													value="view" />
												<portlet:param name="isPublished"
													value="Published" />
											</portlet:renderURL>
                                        <tr>
                                            <td>${divisionNames[status.index]}</td>
									        <td>${districtNames[status.index]}</td>
									        <td>${talukaNames[status.index]}</td>
                                            <td>${grievance.complaintType}</td>
                                            <td>
                                                <a href="${verifyGrievanceURL}" class="btn btn-sm btn-primary">
                                                    <i class="bi bi-check-circle" title="<liferay-ui:message key='view' />"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- Table End -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
$(document).ready(function() {
	jQuery.noConflict();
var table = $('#grievance-list').DataTable({
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
        "info": "<liferay-ui:message key='Showing-total-entries' />",
        "zeroRecords": "<liferay-ui:message key='No-matching-records-found' />",
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
            exportOptions: { columns: ':not(:last-child)' }
        },
        {
            extend: 'csv',
            exportOptions: { columns: ':not(:last-child)' }
        },
        {
            extend: 'excel',
            exportOptions: { columns: ':not(:last-child)' }
        },
        {
            extend: 'pdf',
            exportOptions: { columns: ':not(:last-child)' },
            customize: function(doc) {
                doc.defaultStyle.fontSize = 8;
                doc.styles.tableHeader.fontSize = 9;
                doc.pageMargins = [20, 20, 20, 20];
            }
        },
        {
            extend: 'print',
            exportOptions: { columns: ':not(:last-child)' }
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

// Toggle filter
$('#filterBtn').click(function () {
    const $filterCard = $('#filterCard');
    $filterCard.toggleClass('show');
    $(this).text($filterCard.hasClass('show') ? 'Close' : 'Filter');
});

// Reset
$('#resetBtn').click(function () {
    $('#grievance_filter_form input').val('');
    table.search('').columns().search('').draw();
});

// Search
$('#searchBtn').click(function (e) {
    e.preventDefault();

    let division = $('#division').val().trim();
    let district = $('#district').val().trim();
    let taluka = $('#taluka').val().trim();
    let complaintType = $('#complaintType').val().trim();

    // Reset all column searches
    table.columns().search('');

    // Apply search to respective columns
    if (division) table.column(0).search(division);
    if (district) table.column(1).search(district);
    if (taluka) table.column(2).search(taluka);
    if (complaintType) table.column(3).search(complaintType);

    table.draw();
});
});
</script>
