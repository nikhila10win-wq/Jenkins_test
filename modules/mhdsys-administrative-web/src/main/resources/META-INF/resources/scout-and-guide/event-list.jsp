<%@page import="com.mhdsys.administrative.web.constants.MhdsysAdministrativeWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<!-- DataTables CSS and JS (reused from grievance list setup) -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
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
</style>

<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                     <div class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0">
							<liferay-ui:message key="event-list" />
						</h5>
						<div>
							<a class="btn btn-primary btn-sm rounded-pill back-btn-cn"
								href="/group/guest/scout-guide-ncc"
								style="background-color: #26268E; border-color: #fff;"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message
									key="back" />
							</a>
						</div>
					</div>
                    <div class="card-body">

                        <!-- Filter Button & Card -->
                        <div class="filter-container">
	                        <div class="d-flex justify-content-between">
	                            <button class="btn btn-primary m-0" id="eventFilterBtn">
	                                <liferay-ui:message key="filter" />
	                            </button>
	                            <a href="/group/guest/event" class="btn btn-primary m-0" id="add">
	                                <liferay-ui:message key="add" />
	                            </a>
							</div>
                            <div class="card filter-card mt-2" id="eventFilterCard">
                                <div class="card-body p-0">
                                    <div class="card-header">
                                        <h5 class="card-title m-0"><liferay-ui:message key="filter-options" /></h5>
                                    </div>
                                    <form id="event_filter_form" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="event-name" /></label>
                                                    <input type="text" class="form-control" id="eventName" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="event-year" /></label>
                                                    <input type="text" class="form-control" id="eventType" />
                                                </div>
                                                 <div class="col-md-3">
                                                    <label><liferay-ui:message key="date" /></label>
                                                    <input type="date" class="form-control" id="eventDate" />
                                                </div> 
                                            </div>
                                        </div>
                                    </form>
                                    <div class="card-footer text-right">
                                        <button type="button" class="btn btn-success" id="eventResetBtn">
                                            <liferay-ui:message key="reset" />
                                        </button>
                                        <button type="submit" class="btn btn-success" id="eventSearchBtn">
                                            <liferay-ui:message key="search" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Table -->
                        <div class="universal-table mt-4">
                        	<div class="">
                            <table id="event-list-table" class="table-bordered" width="100%">
                                <thead>
                                    <tr>
                                        <th><liferay-ui:message key="event-name" /></th>
                                        <th><liferay-ui:message key="event-year" /></th>
                                        <th><liferay-ui:message key="event-date" /></th>
                                        <th><liferay-ui:message key="action" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="event" items="${eventCertificates}" >
                                        <portlet:renderURL var="viewEventURL">
                                            <portlet:param name="mvcRenderCommandName" value="<%=MhdsysAdministrativeWebPortletKeys.EVENT_DETAILS_MVC_RENDER_COMMAND%>" />
                                            <portlet:param name="eventCertificateId" value="${event.eventCertificateId}" />
                                            <portlet:param name="mode" value="view" />
                                        </portlet:renderURL>
                                        <tr>
                                            <td>${event.eventName}</td>
                                            <td>${event.year}</td>
                                            <td>${event.eventDateStr}</td>
                                            <td>
                                            <div class="tooltip-icon">
						                           <ul class="inline-item">
						                            <li class="list-inline-item">
						                           <a href="${viewEventURL}" class="btn btn-sm btn-primary">
                                                    <i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i>
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
                        <!-- Table End -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script>
$(document).ready(function () {
    jQuery.noConflict();
    var table = $('#event-list-table').DataTable({
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
                customize: function (doc) {
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
        "initComplete": function () {
            $('.buttons-copy').html('<i class="bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
            $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
            $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
            $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
            $('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
        }
    });

    $('#eventFilterBtn').click(function () {
        const $card = $('#eventFilterCard');
        $card.toggleClass('show');
        $(this).text($card.hasClass('show') ? 'Close' : 'Filter');
        $('#event_filter_form input').val('');
        table.search('').columns().search('').draw();
    });

    $('#eventResetBtn').click(function () {
        $('#event_filter_form input').val('');
        table.search('').columns().search('').draw();
    });

    $('#eventSearchBtn').click(function (e) {
        e.preventDefault();
        let name = $('#eventName').val().trim();
        let type = $('#eventType').val().trim();
         let date = $('#eventDate').val().trim();
 
        table.columns().search('');
        if (name) table.column(0).search(name);
        if (type) table.column(1).search(type);
        if (location) table.column(2).search(date);
 
        table.draw();
    });
});
</script>
