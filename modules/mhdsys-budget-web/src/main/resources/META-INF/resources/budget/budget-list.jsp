<%@page import="com.mhdsys.budget.web.constants.MhdsysBudgetWebPortletKeys"%>
<%@ include file="/init.jsp" %>

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

<portlet:resourceURL id="<%=MhdsysBudgetWebPortletKeys.GETMASTERSDATA%>" var="getMasterData" />

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

#budget-list_wrapper .top {
        display: flex;
        justify-content: space-between;
}

#budget-list_wrapper .bottom {
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
						<h5 class="mb-0"> <liferay-ui:message key="budget-list" /></h5>
						<div><a href="/group/guest/budget" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
					
					<div class="card-body">
						<!-- filter -->
						<div class="filter-container">
							<!-- Filter Toggle Button -->
							<button class="btn btn-primary m-0" id="filterBtn">
								<liferay-ui:message key="filter" />
							</button>
							
						<c:if test="${isDeskOfficer}">
							<button class="btn btn-primary" type="button" onclick="location.href='/group/guest/budget-addition';">
			                    <liferay-ui:message key="budget-addition"/>
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
										    
											<div class="col-md-6">
	                                            <div class="form-group">
	                                                <label><liferay-ui:message key="category"/></label>
	                                                <select class="form-control" name ="category" id="category">
	                                                    <option value=""><liferay-ui:message key="select"/></option>
	                                                    <option value="Schemes" > <liferay-ui:message key="schemes"/></option>
	                                                    <option value="Committed" > <liferay-ui:message key="committed"/></option>
	                                                </select>
	                                            </div>
	                                        </div>
	                                        
	                                        <div class="col-md-6">
	                                            <div class="form-group">
	                                                <label><liferay-ui:message key="schemes"/><sup class="text-danger">*</sup></label>
	                                                <select class="form-control" name ="master" id="master" >
	                                                    <option value=""><liferay-ui:message key="select"/></option>
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
					<table id="budget-list" class="table-bordered" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><liferay-ui:message key="scheme-name"/></th>
							<th><liferay-ui:message key="account-head"/></th>
							<th><liferay-ui:message key="approved-funds-rs"/></th>
							<th><liferay-ui:message key="supplementary-demand-rs"/></th>
							<th><liferay-ui:message key="total-fund-rs"/></th>
							<th><liferay-ui:message key="funds-recieved-rs"/></th>
							<th><liferay-ui:message key="expense-rs"/></th>
							<th><liferay-ui:message key="action"/></th>
						</tr>
					</thead> 
					 
					<tbody>
					
					<c:set var="totalApproved" value="0" />
					<c:set var="totalSupplementary" value="0" />
					<c:set var="totalFund" value="0" />
					<c:set var="totalReceived" value="0" />
					<c:set var="totalExpense" value="0" />
					
					 <c:forEach var="budget" items="${budgetList}">
					 
					    <c:set var="totalApproved" value="${totalApproved + budget.approvedBudgetSum}" />
						<c:set var="totalSupplementary" value="${totalSupplementary + budget.supplementaryDemandSum}" />
						<c:set var="totalFund" value="${totalFund + budget.totalFundSum}" />
						<c:set var="totalReceived" value="${totalReceived + budget.recievedAmountSum}" />
						<c:set var="totalExpense" value="${totalExpense + budget.totalSpendSum}" />
					 
					 <portlet:renderURL var="budgetViewURL">
						<portlet:param name="mvcRenderCommandName" value="<%=MhdsysBudgetWebPortletKeys.BUDGETADDITIONVIEW%>"></portlet:param>
						<portlet:param name="budgetId" value="${budget.budgetId }"></portlet:param>
						<portlet:param name="mode" value="view"></portlet:param>
						<portlet:param name="isHoAdmin" value="${isHoAdmin}"></portlet:param>
					 </portlet:renderURL>
					 <portlet:renderURL var="budgetEditURL">
						<portlet:param name="mvcRenderCommandName" value="<%=MhdsysBudgetWebPortletKeys.BUDGETADDITIONVIEW%>"></portlet:param>
						<portlet:param name="budgetId" value="${budget.budgetId }"></portlet:param>
						<portlet:param name="mode" value="edit"></portlet:param>
						<portlet:param name="isHoAdmin" value="${isHoAdmin}"></portlet:param>
					 </portlet:renderURL>
						<tr>
							<td>${budget.masterStr}</td>
							<td>${budget.uniqueId}</td>
							<td>${budget.approvedBudget}</td>
							<%-- <td>${budget.supplementaryDemand}</td> --%>
							<td> 
								<c:forTokens items="${budget.supplementaryDemand}" delims="," var="val">
							        ${val}<br/>
							    </c:forTokens>
							</td>
							<td>${budget.totalFund}</td>
							<td>${budget.recievedAmount}</td>
							<td>${budget.totalSpend}</td>
							<td>
							<div class="tooltip-icon">
								<ul class="inline-item">
									<li class="list-inline-item">
										<c:if test="${isDeskOfficer || isDDD || isDeputyDirector || isHoAdmin}">
											<a href="${budgetViewURL}" class="btn btn-primary"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a>
										</c:if>
									</li>
									<li class="list-inline-item">
										<c:if test="${isDeskOfficer}">
											<a href="${budgetEditURL}" class="btn btn-primary"><i class="bi bi-pencil-square icons-color" title="<liferay-ui:message key="edit" />"></i></a>
										</c:if>
									</li>
								</ul>
							</div>
							</td>
						</tr>
					</c:forEach>
					
					<tr style="font-weight:bold; background:#f2f2f2;">
						<td></td>
					    <td align="right"><liferay-ui:message key="total" /></td>
					    <td>${totalApproved}</td>
					    <td>${totalSupplementary}</td>
					    <td>${totalFund}</td>
					    <td>${totalReceived}</td>
					    <td>${totalExpense}</td>
					    <td></td>
					</tr>
					
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

var table = $('#budget-list').DataTable({
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
	$('#category').val('');
	$('#master').val('');
	
    const $filterCard = $('#filterCard');
    $filterCard.toggleClass('show');
    $(this).text($filterCard.hasClass('show') ? '<liferay-ui:message key='close' />' : '<liferay-ui:message key='filter' />');
});


$('#resetBtn').click(function() {
	$('#category').val('');
    $('#master').val('');
    
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
    
    var master = $('#master').val().trim();
    console.log("Searching :", master);
    
    if (master) table.column(0).search(master);
    
    table.draw();
});


$('select[name="category"]').on('change', function() {
    masters($(this).val());
});

});


function masters(category) {
    console.log("Category :::::    "+category);
    
    $.ajax({
        url: '${getMasterData}',
        type: 'GET',
        data: { category: category },
        success: function(data) {
        	console.log("success success ");
            const $masterSelect = $("#master");
            $masterSelect.empty();
            let masters = typeof data === "string" ? JSON.parse(data) : data;

            if (masters && masters.length > 0) {
                $masterSelect.append('<option value="">Select</option>');
                $.each(masters, function(index, value) {
                	
                	console.log("Values :::;;    "+value.id);
                	console.log("Second Values ::::::::    "+name);
                    $masterSelect.append('<option value="' + value.name + '"' + '>' + value.name + '</option>');
               
                });
            } else {
                $masterSelect.append('<option value="">No masters available</option>');
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching districts:", error);
            $("#master").empty().append('<option value="">Error loading masters</option>');
        }
    });
}

</script>