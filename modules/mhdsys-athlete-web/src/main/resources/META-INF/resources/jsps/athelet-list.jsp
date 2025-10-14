<%@page import="com.mhdsys.athelet.web.com.constants.MhdsysAthleteWebPortletKeys"%>
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
                    <div
						class="align-items-center back-btn-cn card-header d-flex justify-content-between">
						<h5 class="mb-0"><liferay-ui:message key="athelet-list" /></h5>
						<div>
							<a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"> <i
								class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" />
							</a>
						</div>
					</div>
                    <div class="card-body">

                        <!-- Filter Button & Card -->
                        <div class="filter-container">
                            <button class="btn btn-primary m-0" id="eventFilterBtn">
                                <liferay-ui:message key="filter" />
                            </button>

                            <div class="filter-card mt-2" id="eventFilterCard">
                                <div class="card card-background p-0">
                                    <div class="card-header header-card">
                                        <liferay-ui:message key="filter-options" />
                                    </div>
									<form id="event_filter_form" method="POST">
										<div class="card-body">
											<div class="row">
												<!-- Application Number Filter -->
												<div class="col-md-3">
													<label><liferay-ui:message key="application-number" /></label>
													<input type="text" class="form-control"
														id="filterApplicationNumber" />
												</div>

												<!-- Athlete Name Filter -->
												<div class="col-md-3">
													<label><liferay-ui:message key="athelet-name" /></label> <input
														type="text" class="form-control" id="filterAthleteName" />
												</div>

												<!-- Sport Name Filter -->
												<div class="col-md-3">
													<label><liferay-ui:message key="sport-name" /></label> <input
														type="text" class="form-control" id="filterSportName" />
												</div>
											</div>
										</div>
									</form>

									<div class="card-footer text-right bg-white">
										<button type="button" class="btn btn-success" id="eventResetBtn">
											<liferay-ui:message key="reset" />
										</button>
										<button type="submit" class="btn btn-success"
											id="eventSearchBtn">
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
                                        <th><liferay-ui:message key="application-number" /></th>
                                        <th><liferay-ui:message key="athelet-name" /></th>
                                        <th><liferay-ui:message key="sport-name" /></th>
                                        <th><liferay-ui:message key="action" /></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="athelet" items="${atheletList}" varStatus="status" >
                                        <portlet:renderURL var="viewProfileURL">
                                            <portlet:param name="mvcRenderCommandName" value="<%=MhdsysAthleteWebPortletKeys.VIEW_PROFILE_MVC_RENDER_COMMAND%>" />
                                            <portlet:param name="participantUserId" value="${athelet.participantUserId}" />
                                        </portlet:renderURL>
                                        <tr>
                                            <td>${athelet.applicationNumber}</td>
                                            <td>${athelet.participantName}</td>
                                            <td>${sportNames[status.index]}</td>
                                            <td>
                                                <a href="${viewProfileURL}" class="btn btn-sm btn-primary">
                                                    <i class="bi bi-eye icons-color" title="<liferay-ui:message key="view-profile" />"></i>
                                                </a>
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
    });

    $('#eventResetBtn').click(function () {
        $('#event_filter_form input').val('');
        table.search('').columns().search('').draw();
    });


    $('#eventSearchBtn').click(function (e) {
        e.preventDefault();

        let applicationNumber = $('#filterApplicationNumber').val().trim();
        let athleteName = $('#filterAthleteName').val().trim();
        let sportName = $('#filterSportName').val().trim();

        table.columns().search('');

        if (applicationNumber) table.column(0).search(applicationNumber);
        if (athleteName) table.column(1).search(athleteName);
        if (sportName) table.column(2).search(sportName);

        table.draw();
    });

});
</script>
