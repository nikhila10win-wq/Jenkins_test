<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

    #facility-list_wrapper .bottom {
        display: flex;
        justify-content: space-between;
        align-items: baseline;
    }
 .filter-card .card-footer:last-child {
    	border-bottom-left-radius: 20px;
    	border-bottom-right-radius: 20px;
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
						<h5 class="mb-0"> <liferay-ui:message key="facility-rating-list" /></h5>
						<!-- <div><a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backOrClearUrl()"><i class="bi bi-arrow-left-circle mr-1"></i>  <liferay-ui:message key="back" /></a></div> -->
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
			                                        <label><liferay-ui:message key="userName" /></label>
			                                        <input type="text" class="form-control" id="userName" />
			                                    </div>
			                                    
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="rating" /></label>
			                                        <input type="text" class="form-control" id="ratingFilter" />
			                                    </div> 
			                                    
			                                    <div class="col-md-3"> 
			                                        <label><liferay-ui:message key="comment" /></label>
			                                        <input type="text" class="form-control" id="commentFilter" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="ho-reply" /></label>
			                                        <input type="text" class="form-control" id="ho-reply" />
			                                    </div>
			                                    <div class="col-md-3">
			                                        <label><liferay-ui:message key="creation-date" /></label>
			                                        <input type="text" class="form-control" id="creation-date" />
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
                    	
                        <table id="facility-list" class="table-bordered" width="100%">
						    <thead>
						        <tr>
						            <th><liferay-ui:message key="s.no" /></th>
						            <th><liferay-ui:message key="facility-Name" /></th>
						            <th><liferay-ui:message key="user-name" /></th>
						            <th><liferay-ui:message key="rating" /></th>
						            <th><liferay-ui:message key="comment" /></th>
						            <th><liferay-ui:message key="ho-reply" /></th>
						            <th><liferay-ui:message key="creation-date" /></th>
						        </tr>
						    </thead>
						    
						    <tbody>
						        <c:forEach var="tracker" items="${userRatingList}" varStatus="status">
						            <tr>
						                <td>${status.index + 1}</td> 
						                <td>${facilityMaster[tracker.facilityMasterId]}</td>
							            <td>${tracker.userName}</td>
						                <td>${tracker.rating}</td>
						                <td>${tracker.comment}</td>
						                <td>${tracker.hoReplyToReview}</td>
						                <td><fmt:formatDate value='${tracker.createDate}' pattern='yyyy-MM-dd'/></td>
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
            let userName= $('#userName').val().trim();
            let ratingFilter = $('#ratingFilter').val().trim();
            let commentFilter = $('#commentFilter').val().trim();
            let ho_reply = $('#ho-reply').val().trim();
            let creation_date= $('#creation-date').val().trim();

            table.columns().search('');
            if (facilityName) table.column(1).search(facilityName);
            if (userName) table.column(2).search(userName);
            if (ratingFilter) table.column(3).search(ratingFilter);
            if (commentFilter) table.column(4).search(commentFilter);
            if (ho_reply) table.column(5).search(ho_reply);
            if (creation_date) table.column(6).search(creation_date);

            table.draw();
        });
  
})
    
</script>