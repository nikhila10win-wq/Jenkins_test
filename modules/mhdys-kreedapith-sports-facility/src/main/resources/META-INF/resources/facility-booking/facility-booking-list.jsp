<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- jQuery FIRST -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- DataTables CSS -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<!-- DataTables + Buttons JS -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>

<!-- Export Dependencies -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.7.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/vfs_fonts.js"></script>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>


<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.REDIRECT %>" />
</portlet:renderURL>

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

    #facility-list_wrapper .top {
        display: flex;
        justify-content: space-between;
    }

    #afacility-list_wrapper .bottom {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
    }

   @media (max-width: 998px) {
	  .universal-table table {
		    overflow-x: scroll;
		    display: block;
		}
}
</style>
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">

                    <div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="facility-booking-list" /></h5>
						<div><a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backOrClearUrl()"><i class="bi bi-arrow-left-circle mr-1"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
	
                    <div class="card-body">
						<!-- filter -->

						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>

							<!-- Filter Card -->
							<div class="filter-card mt-4 card-background p-0" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
											<liferay-ui:message key="filter-options" />
						 			
									</div>
									<form id="facility_filter_form" method="POST">
										<div class="card-body">
											 <div class="row">
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="facility-Name" /></label>
			                                        <input type="text" class="form-control" id="facility-Name" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="type" /></label>
			                                        <input type="text" class="form-control" id="type" />
			                                    </div>
			                                    <div class="col-md-3	">
			                                        <label><liferay-ui:message key="batch" /></label>
			                                        <input type="text" class="form-control" id="batch" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="sport-court" /></label>
			                                        <input type="text" class="form-control" id="sport-court" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="fees" /></label>
			                                        <input type="text" class="form-control" id="fees" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="creation-date" /></label>
			                                        <input type="text" class="form-control" id="creation-date" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="status" /></label>
			                                        <input type="text" class="form-control" id="status" />
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
                    <div id="datatableWrapper" style="display: none;">
                    <div class="universal-table mt-4">
                    <form id="reviewForm" method="POST" enctype="multipart/form-data">
                    	<input type="hidden" name="adminAction" id="adminAction">
                    	<input type="hidden" name="facilityBookingId" id="facilityBookingId">
                    	
                        <table id="facility-list" class="table-bordered" width="100%">
						    <thead>
						        <tr>
						            <th><liferay-ui:message key="s.no" /></th>
						            <th><liferay-ui:message key="facility-Name" /></th>
						            <th><liferay-ui:message key="user" /></th>
						            <th><liferay-ui:message key="type" /></th>
						            <th><liferay-ui:message key="batch" /></th>
						            <th><liferay-ui:message key="sport-court" /></th>
						            <th><liferay-ui:message key="fees" /></th>
						            <th><liferay-ui:message key="creation-date" /></th>
						            <th><liferay-ui:message key="status" /></th>
						            <th><liferay-ui:message key="action" /></th>
						        </tr>
						    </thead>
						    <tbody>
						        <c:forEach var="tracker" items="${filteredList}" varStatus="status">
						            <tr>
						                <td>${status.index + 1}</td> 
						                <td>${facilityMaster[tracker.selectedFacility]}</td>
						                <td>${tracker.name}</td>
						                <td>${tracker.type}</td>
						                <td>${tracker.batch}</td>
						                <td>${tracker.sportCourt}</td>
						                <td>${tracker.fees}</td>
						                <td><fmt:formatDate value='${tracker.createDate}' pattern='yyyy-MM-dd'/></td>
						                <td>${tracker.bookingStatus == 0 ? 'Pending' : tracker.bookingStatus == 1 ? 'Approved By HO' : 'Rejected By HO'}</td>
						                <c:choose>
										  <c:when test="${isHOAdmin}">
											  <td>
											   <c:choose>
												    <c:when test="${tracker.isApproved || tracker.bookingStatus == 1}">
														<li class="list-inline-item">
															<a href="${redirectUrl}&type=adminAction&mode=view&facilityBookingId=${tracker.facilityBookingId}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i></a>
														</li>
												    </c:when>
												    <c:otherwise>
											    	 	<li class="list-inline-item">
															<a href="${redirectUrl}&type=facilityBookingView&mode=view&facilityBookingId=${tracker.facilityBookingId}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i></a>
														</li>
												    </c:otherwise>
												</c:choose>
												
											   <c:choose>
												    <c:when test="${tracker.bookingStatus == 0}">
														<li class="list-inline-item">
															<a href="${redirectUrl}&type=adminAction&mode=view&facilityBookingId=${tracker.facilityBookingId}" class="btn btn-primary"><i class="bi bi-check-circle" title="<liferay-ui:message key='verify' />"></i></a>
														</li>
												    </c:when>
												    <c:otherwise>
											    	 	<li class="list-inline-item">
															<button class="btn btn-primary" disabled="disabled"><i class="bi bi-check-circle" title="<liferay-ui:message key='verify' />"></i></button>
														</li>
												    </c:otherwise>
												</c:choose>
										  
										    </td>
										  </c:when>
										
										  <c:otherwise>
										 
										 <td>   
											
											<li class="list-inline-item">
												<a href="${redirectUrl}&type=facilityBookingView&mode=view&facilityBookingId=${tracker.facilityBookingId}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key='view' />"></i></a>
											</li>
											
											 <c:choose>
											    <c:when test="${tracker.bookingStatus == 9}">
													<li class="list-inline-item">
														<a href="${redirectUrl}&type=facilityBookingView&mode=edit&facilityBookingId=${tracker.facilityBookingId}" class="btn btn-primary"><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key='edit' />"></i></a>
													</li>
											    </c:when>
											    <c:otherwise>
										    	 	<li class="list-inline-item">
						                                <button class="btn btn-primary" disabled="disabled">
						                                    <i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key='edit' />"></i>
						                                </button>
					                        		</li>
											    </c:otherwise>
											</c:choose>
													
										</td>
													
										  </c:otherwise>
										</c:choose>
						            </tr>
						        </c:forEach>
						    </tbody>
						</table>
                        </form>
                    </div>
                    </div>
                  </div>
                </div>
            </div>
        </div>
</div>
</div>

<script>

function backOrClearUrl() {
    	window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
}

jQuery(document).ready(function($) {
var table = $('#facility-list').DataTable({
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
                // Additional excel formatting if needed
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
    	
    	$('#datatableWrapper').show(); // once dataTable loads only we will shows
    	
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
		
		    if (!$filterCard.hasClass('show')) {
		        $('#facility_filter_form input').val('');
		        table.search('').columns().search('').draw();
		        $(this).text('Filter');
		    } else {
		        $(this).text('Close');
		    }
		});

        // Reset
        $('#resetBtn').click(function () {
            $('#facility_filter_form input').val('');
            table.search('').columns().search('').draw();
        });

     // Search
        $('#searchBtn').click(function (e) {
            e.preventDefault();

            let facilityName = $('#facility-Name').val().trim();
            let type = $('#type').val().trim();
            let batch = $('#batch').val().trim();
            let sportCourt = $('#sport-court').val().trim();
            let fees = $('#fees').val().trim();
            let creationDate = $('#creation-date').val().trim();
            let status = $('#status').val().trim();

            table.columns().search('');
            if (facilityName) table.column(1).search(facilityName);
            if (type) table.column(2).search(type);
            if (batch) table.column(3).search(batch);
            if (sportCourt) table.column(4).search(sportCourt);
            if (fees) table.column(5).search(fees);
            if (creationDate) table.column(6).search(creationDate);
            if (status) table.column(7).search(status);

            table.draw();
        });

  
})
    
</script>