<%@ include file="/init.jsp" %>
<%@page import="com.mhdsys.youth.award.constants.MhdsysAwardYouthWebPortletKeys"%>

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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

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

    #award_youth_list_wrapper .top {
        display: flex;
        justify-content: space-between;
    }

    #award_youth_list_wrapper .bottom {
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
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="award-youth-list" /></h5>
                        <div><a href="/group/guest/youth-awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                    </div>
                    <div class="card-body">

                        <!-- Filter Section -->
                        <div class="filter-container">
                            <button class="btn btn-primary m-0" id="filterBtn">
                                <liferay-ui:message key="filter" />
                            </button>

                            <div class=" filter-card mt-2 card-background p-0" id="filterCard">
                                <div class="card card-background p-0">
                                    <div class="card-header header-card">
                                       
                                            <liferay-ui:message key="filter-options" />
                                       
                                    </div>
                                    <form id="award_youth_filter_form" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="award-youth-id" /></label>
                                                    <input type="text" class="form-control" id="awardYouthId" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="first-name" /></label>
                                                    <input type="text" class="form-control" id="firstName" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="last-name" /></label>
                                                    <input type="text" class="form-control" id="lastName" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="email-id" /></label>
                                                    <input type="text" class="form-control" id="emailId" />
                                                </div>
                                                <div class="col-md-3 mt-2">
                                                    <label><liferay-ui:message key="contact-number" /></label>
                                                    <input type="text" class="form-control" id="contactNo" />
                                                </div>
                                                <div class="col-md-3 mt-2">
                                                    <label><liferay-ui:message key="staff-of" /></label>
                                                    <input type="text" class="form-control" id="staffOf" />
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
                            <form id="reviewForm" method="POST" enctype="multipart/form-data">
                            <div class="">
                                <table id="award_youth_list" class="table-bordered" width="100%">
                                    <thead>
                                        <tr>
                                            <th><liferay-ui:message key="award-youth-id" /></th>
                                            <th><liferay-ui:message key="first-name" /></th>
                                            <th><liferay-ui:message key="last-name" /></th>
                                            <th><liferay-ui:message key="email-id" /></th>
                                            <th><liferay-ui:message key="contact-number" /></th>
                                            <th><liferay-ui:message key="staff-of" /></th>
                                             <th><liferay-ui:message key="action" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="awardYouths" items="${awardYouthsList}">
                                        
                                          <portlet:renderURL var="AwardYouthViewURL">
												<portlet:param name="mvcRenderCommandName"
													value="<%=MhdsysAwardYouthWebPortletKeys.AWARD_YOUTH_MVC_RENDER_COMMAND%>" />
												<portlet:param name="awardYouthId"
													value="${awardYouths.awardYouthId}" />
												<portlet:param name="mode" value="view" />
											</portlet:renderURL>
                                        
                                        
                                            <tr>
                                                <td>${awardYouths.awardYouthId}</td>
                                                <td>${awardYouths.firstName}</td>
                                                <td>${awardYouths.lastName}</td>
                                                <td>${awardYouths.emailId}</td>
                                                <td>${awardYouths.contactNo}</td>
                                                <td>${awardYouths.staffOf}</td>
											    <td>
											    <c:if test="${isDeskOfficer}">
											        <a href="${AwardYouthViewURL}" class="btn btn-primary">
											            <i class="bi bi-eye text-dark" 
											               title="<liferay-ui:message key='${empty awardYouths.deskRemarks ? "view-verify" : "view"}' />">
											            </i>
											        </a>
											    </c:if>
											
											    <c:if test="${isAssistantDirector}">
											        <a href="${AwardYouthViewURL}" class="btn btn-primary">
											            <i class="bi bi-eye text-dark" 
											               title="<liferay-ui:message key='${empty awardYouths.asstDirRemarks ? "view-verify" : "view"}' />">
											            </i>
											        </a>
											    </c:if>
											
											    <c:if test="${isHOAdmin}">
											        <a href="${AwardYouthViewURL}" class="btn btn-primary">
											            <i class="bi bi-eye text-dark" 
											               title="<liferay-ui:message key='${empty awardYouths.ddHoRemarks ? "view-verify" : "view"}' />">
											            </i>
											        </a>
											    </c:if>
											</td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript -->
<script>
$(document).ready(function() {
    jQuery.noConflict();

    var table = $('#award_youth_list').DataTable({
        paging: true,
        ordering: false,
        searching: true,
        responsive: true,
        autoWidth: false,
        language: {
            search: '<liferay-ui:message key="search" />',
            emptyTable: '<liferay-ui:message key="no-data-available-in-table" />',
            infoFiltered: "",
            lengthMenu: '_MENU_',
            info: "<liferay-ui:message key='Showing-total-entries' />",
            zeroRecords: "<liferay-ui:message key='No-matching-records-found' />",
            infoEmpty: "<liferay-ui:message key='Showing-total-entries' />",
            oPaginate: {
                sNext: '<em class="bi bi-chevron-right"></em>',
                sPrevious: '<em class="bi bi-chevron-left"></em>'
            }
        },
        layout: {
            topStart: {
                buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
            }
        },
        dom: '<"top"Bf>rt<"bottom"lip><"clear">',
        buttons: [
            { extend: 'copy', exportOptions: { columns: ':not(:last-child)' }},
            { extend: 'csv', exportOptions: { columns: ':not(:last-child)' }},
            { extend: 'excel', exportOptions: { columns: ':not(:last-child)' }},
            {
                extend: 'pdf',
                exportOptions: { columns: ':not(:last-child)' },
                customize: function(doc) {
                    doc.defaultStyle.fontSize = 8;
                    doc.styles.tableHeader.fontSize = 9;
                    doc.pageMargins = [20, 20, 20, 20];
                }
            },
            { extend: 'print', exportOptions: { columns: ':not(:last-child)' }}
        ],
        initComplete: function() {
            $('.buttons-copy').html('<i class="bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
            $('.buttons-csv').html('<i class="bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
            $('.buttons-excel').html('<i class="bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
            $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
            $('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
        }
    });

    // Toggle filter card
    $('#filterBtn').click(function () {
        const $filterCard = $('#filterCard');
        $filterCard.toggleClass('show');
        $(this).text($filterCard.hasClass('show') ? 'Close' : 'Filter');
    });

    // Reset filters
    $('#resetBtn').click(function () {
        $('#award_youth_filter_form input').val('');
        table.columns().search('').draw();
    });

    // Apply search
    $('#searchBtn').click(function (e) {
        e.preventDefault();

        table.columns().search('');

        const awardYouthId = $('#awardYouthId').val();
        const firstName = $('#firstName').val();
        const lastName = $('#lastName').val();
        const emailId = $('#emailId').val();
        const contactNo = $('#contactNo').val();
        const staffOf = $('#staffOf').val();

        if (awardYouthId) table.column(0).search(awardYouthId);
        if (firstName) table.column(1).search(firstName);
        if (lastName) table.column(2).search(lastName);
        if (emailId) table.column(3).search(emailId);
        if (contactNo) table.column(4).search(contactNo);
        if (staffOf) table.column(5).search(staffOf);

  
        table.draw();
    });
});




</script>
