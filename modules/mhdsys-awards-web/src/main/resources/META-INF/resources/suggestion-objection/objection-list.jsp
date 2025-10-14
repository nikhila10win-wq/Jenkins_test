<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.9/pdfmake.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.9/vfs_fonts.js"></script>

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

<portlet:resourceURL id="<%=AwardsWebPortletKeys.GENERATE_OBJECTION_REPORT%>" var="generateObjectionReportURL" />

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

#objection_list_wrapper .top {
        display: flex;
        justify-content: space-between;
}

#objection_list_wrapper .bottom {
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
						<h5 class="mb-0"> <liferay-ui:message key="objection-list" /></h5>
					<c:if test="${isPtTeacher}">
						<div><a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</c:if>
					<c:if test="${!isPtTeacher}">
						<div><a href="/group/guest/awards" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</c:if>
					</div>
					
					<div class="card-body">
						<!-- filter -->
						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>
							
							<c:if test="${!isDeskOfficer && !isDeputyDirector}">
								<button class="btn btn-primary" type="button" onclick="location.href='${awardsRedirectURL}&type=objection';">
				                    <liferay-ui:message key="suggestion-objection"/>
				                </button>
							</c:if>
							

							<!-- Filter Card -->
							<div class="filter-card mt-4" id="filterCard">
								<div class="card card-background p-0">
									<div class="card-header header-card">
											<liferay-ui:message key="filter-options" />
									</div>

							<form id="buyer_purchase" method="POST">
									<div class="card-body">
										<div class="row">
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="player-name"/></label>
                                                <input type="test" class="form-control" id="playerName" name="playerName" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label><liferay-ui:message key="objector-name"/></label>
                                                <input type="test" class="form-control" id="objectorName" name="objectorName" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="sport-name" /></label>
                                                <input type="test" class="form-control" id="sportName" name="sportName" />
                                            </div>
										    </div>
										
										    <div class="col-md-3">
										        <div class="form-group">
                                                <label> <liferay-ui:message key="award-type" /></label>
                                                <input type="test" class="form-control" id="awardType" name="awardType" />
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
						<table id="objection_list" class="table-bordered" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th><liferay-ui:message key="player-name" /></th>
									<th><liferay-ui:message key="objector-name" /></th>
									<th><liferay-ui:message key="sport-name" /></th>
									<th><liferay-ui:message key="award-type" /></th>
									<c:if test="${isDeskOfficer}">
										<th><liferay-ui:message key="report" /></th>
									</c:if>
									<th><liferay-ui:message key="action" /></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="obj" items="${objList}">
								<c:if test="${not isDeputyDirector or (isDeputyDirector and not empty obj.doVerification)}">
									<portlet:renderURL var="objectionViewURL">
										<portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.OBJECTION%>" />
										<portlet:param name="objectionId" value="${obj.objectionId}" />
										<portlet:param name="cmd" value="view" />
									</portlet:renderURL>

									<portlet:renderURL var="objectionEditURL">
										<portlet:param name="mvcRenderCommandName" value="<%=AwardsWebPortletKeys.OBJECTION%>" />
										<portlet:param name="objectionId" value="${obj.objectionId}" />
										<portlet:param name="cmd" value="edit" />
									</portlet:renderURL>

									<tr>
										<td>${obj.playerName}</td>
										<td>${obj.objectorName}</td>
										<td>${obj.sportNameStr}</td>
										<td>${obj.awardStr}</td>
										<c:if test="${isDeskOfficer}">
										<td>
										<div class="tooltip-icon">
				                           <ul class="inline-item">
					                            <li class="list-inline-item">
		                            				<a class="btn btn-sm btn-primary" href="javascript:void(0);" onclick="generateReport('${obj.objectionId}')">
		                            					<i class="bi bi-file-earmark-medical icons-color" title="<liferay-ui:message key="report" />"></i>
		                            				</a>
		                            			</li>
		
				                            </ul>
						                </div>
			                            </td>
			                            </c:if>
			                            
										<td>
										<div class="tooltip-icon">
											<ul class="inline-item">
												<li class="list-inline-item">
													<c:if test="${!isDeskOfficer && !isDeputyDirector}">
														<a href="${objectionViewURL}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a>
													<c:if test="${empty obj.doVerification}">
														<a href="${objectionEditURL}" class="btn btn-primary"><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i></a>
													</c:if>
													</c:if>
												</li>
												
												<li class="list-inline-item">
												    <c:if test="${isDeskOfficer}">
												            <a href="${objectionEditURL}" class="btn btn-primary">
												                <i class="bi bi-eye icons-color" 
												                   title="<liferay-ui:message key='${empty obj.doVerification ? "view-verify" : "view"}' />">
												                </i>
												            </a>
												    </c:if>
												    
												    <c:if test="${isDeputyDirector}">
												            <a href="${objectionEditURL}" class="btn btn-primary">
												                <i class="bi bi-eye icons-color" 
												                   title="<liferay-ui:message key='${empty obj.ddVerification ? "view-verify" : "view"}' />">
												                </i>
												            </a>
												    </c:if>
												</li>
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


<script>

$(document).ready(function() {
jQuery.noConflict();
	
var table = $('#objection_list').DataTable({
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
	$('#playerName').val('');
    $('#objectorName').val('');
    $('#sportName').val('');
    $('#awardType').val('');
    
    const $filterCard = $('#filterCard');
    $filterCard.toggleClass('show');
    $(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />' : '<liferay-ui:message key='filter' />');
});

$('#resetBtn').click(function() {
    $('#playerName').val('');
    $('#objectorName').val('');
    $('#sportName').val('');
    $('#awardType').val('');
    
    if (typeof table !== 'undefined') {
        table.search('').columns().search('').draw();
        $('.dataTables_filter input').val('');
    }
});

$(document).on('click', '#searchBtn', function () {
    table.search('').columns().search('').draw();
    $('.dataTables_filter input').val('');
    
    var getDisplayText = function(selectElement) {
        return selectElement.options[selectElement.selectedIndex].text.trim();
    };
    
    var playerName = $('#playerName').val().trim();
    var objectorName = $('#objectorName').val().trim();
    var sportName = $('#sportName').val().trim();
    var awardType = $('#awardType').val().trim();
    
    console.log("Searching :", playerName);
    console.log("Searching :", objectorName);
    
    if (playerName) table.column(0).search(playerName);
    if (objectorName) table.column(1).search(objectorName);
    if (sportName) table.column(2).search(sportName);
    if (awardType) table.column(3).search(awardType);
    
    table.draw();
});

});


function generateReport(objectionId) {
	 console.log("Objection ID ::: "+objectionId);
	    $.ajax({
	        type: "POST", 
	        url: '${generateObjectionReportURL}', 
	        data: {objectionId: objectionId }, 
	        success: function(data) { 
	            console.log("Response data:", data);
	            console.log("Download URL : "+data.downloadURL);
	            
               window.open(data.downloadURL, '_blank');
	            
	        },
	        error: function(xhr, status, error) {
	            console.error("Error occurred:", error);
	        }
	    });
	}
	
</script>