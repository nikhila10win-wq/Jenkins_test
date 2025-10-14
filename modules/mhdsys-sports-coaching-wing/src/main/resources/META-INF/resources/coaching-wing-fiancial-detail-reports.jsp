<%@ include file="/init.jsp" %>
<!-- DataTables CSS -->
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/fixedcolumns/5.0.4/css/fixedColumns.dataTables.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

DataTables JS
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.7.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/fixedcolumns/5.0.4/js/dataTables.fixedColumns.js"></script>
<script src="https://cdn.datatables.net/fixedcolumns/5.0.4/js/fixedColumns.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src='https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js'></script>
updated 
 -->
<!-- Updated Changes -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/fixedcolumns/5.0.4/css/fixedColumns.dataTables.css"> -->

<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.7.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/vfs_fonts.js"></script>
<script src='https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js'></script>

<!-- <script src="https://cdn.datatables.net/fixedcolumns/5.0.4/js/dataTables.fixedColumns.js"></script>
<script src="https://cdn.datatables.net/fixedcolumns/5.0.4/js/fixedColumns.dataTables.js"></script> -->
<!-- Updated Changes -->

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
    #training-center-list th, 
	#training-center-list td {
    min-width: 330px;
   
}

.selected {
	    background: #eeeef6 important;
       /* color: black important; */
       box-shadow: #eeeef6 important;
	}
	
.dt-buttons{
	display: none
}	
	

</style>
<c:if test="${isDSO}">
	<portlet:renderURL var="addNewtrainingCenterURL">
		<portlet:param name="mvcRenderCommandName" value="<%=SportsCoachinWingMvcCommand.TRAINING_CENTER_MVC_RENDER_COMMAND%>" />
	    <portlet:param name="mode" value="add" />
	</portlet:renderURL>
</c:if>
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="training-center-list" /></h5>
                        <div><a href="/group/guest/sports-coaching-wing-reports" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                    </div>
                    <div class="card-body">

                        <!-- Filter Section -->
                        <div class="filter-container">
                            <button class="btn btn-primary m-0" id="filterBtn">
                                <liferay-ui:message key="filter" />
                            </button>
							<c:if test="${isDSO}">
						        <a href="${addNewtrainingCenterURL}" class="btn btn-primary m-0 ml-3">
						           <liferay-ui:message key='add-new' />
						        </a>
							</c:if>
                            <div class="filter-card mt-2 card-background p-0" id="filterCard">
                                <div class="card card-background p-0">
                                    <div class="card-header header-card">
                                            <liferay-ui:message key="filter-options" />
                                    </div>
                                    <form id="coaching_wing_financial_report_form" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="training-center-id" /></label>
                                                    <input type="text" class="form-control" id="training-center-id" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="division" /></label>
                                                    <input type="text" class="form-control" id="division" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="district" /></label>
                                                    <input type="text" class="form-control" id="district" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="centre-type" /></label>
                                                    <input type="text" class="form-control" id="centre-type" />
                                                </div>
                                                <div class="col-md-3 mt-2">
                                                    <label><liferay-ui:message key="sports-type" /></label>
                                                    <input type="text" class="form-control" id="sports-type" />
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
                            <div class="table-responsive">
                                <table id="training-center-list" class=" table-bordered">
                                    <thead>
                                        <tr>
                                        <th><liferay-ui:message key="trainingcenter" /></th>
                                            <th ><liferay-ui:message key="district" /></th>
                                            <th ><liferay-ui:message key="Sports material and sports training equipment" /></th>
                                            <th ><liferay-ui:message key="5 sports Coaches will provide track suits, tshirts, shorts, shoes etc. to 50 players each who practice regularly at the center " /></th>
                                            <th ><liferay-ui:message key="Honorarium of 3 part time maidan servants" /></th>
                                            <th ><liferay-ui:message key="Sports literature, CDs, Books, periodicals, Monthly etc" /></th>
                                            <th ><liferay-ui:message key="Office/ General expenses" /></th>
                                             <th ><liferay-ui:message key="Honorarium to private trainers, 10 Trainers for 5 sports for 2 months " /></th>
                                             <th ><liferay-ui:message key="Coach's travel Expense" /></th>
                                             <th ><liferay-ui:message key="Playground maintenance and repair" /></th>
                                             <th ><liferay-ui:message key="Seminar/ Comradeship/ Revision class (at least twice a year)" /></th>
                                             <th ><liferay-ui:message key="Counselling" /></th>
                                             <th ><liferay-ui:message key="Preparation of brochures, flex boards, posters, local newspaper/ cable channels for publicity" /></th>
                                             <th ><liferay-ui:message key="Training camp 2 times a year" /></th>
                                             <th ><liferay-ui:message key="Total" /></th>
                                             <th ><liferay-ui:message key="action" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="talentD" items="${talentDev}">
                                        
                                          <portlet:renderURL var="viewFinancialReportURL">
												<portlet:param name="mvcRenderCommandName"
													value="<%=SportsCoachinWingMvcCommand.VIEW_FINANCIAL_REPORT%>" />
												<portlet:param name="trainingCentreId"
													value="${talentD.trainingCenterId}" />
												<portlet:param name="centerType" value="${centerType}" />
												<portlet:param name="mode" value="view" />
										</portlet:renderURL>
                                            <tr>
                                                <td>${talentD.trainingCenterId}</td>
                                                <td>${talentD.districtname}</td>
                                                <c:forEach var="fdamount" items="${talentD.financialTraingData}">
                                                	<td>${fdamount.requestedAmount}</td>
                                                 </c:forEach>
                                                 <td>${talentD.total}</td>
                                                 <td>
											        <div class="tooltip-icon">
								                           <ul class="inline-item">
								                            <%--  <li class="list-inline-item">
					                            				<a class="printrowpbtn btn btn-sm btn-primary" href="javascript:void(0)" ><i class="bi bi-printer icons-color" title="<liferay-ui:message key="print" />"></i></a>
					                            			</li> --%>
					                            			  <li class="list-inline-item">
					                            				<a class="printrowpdfbtn btn btn-sm btn-primary" href="javascript:void(0)" ><i class="bi bi-file-earmark-medical icons-color" title="<liferay-ui:message key="report" />"></i></a>
					                            			</li>
			                            					<li class="list-inline-item">
				                            					<a href="${viewFinancialReportURL}" class="btn btn-primary">
														            <i class="bi bi-eye text-dark" 
														               title="<liferay-ui:message key="view"/>">
														            </i>
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
	    
	 $(document).on('click','.printrowpdfbtn',function(){
	    	console.log("printrowpdf button ::: clicked ");
	    	 $("#training-center-list tr").removeClass("selected");
	    	 //$(this).closest('tr').addClass("selected");
	    	$(".buttons-pdf ").click();
	    	//location.reload();
	    });
	 
	 
	   
	 $(document).on('click','.printrowpbtn',function(){
	    	console.log("printrowpdf button ::: clicked ");
	    	 $("#training-center-list tr").removeClass("selected");
	    	 //$(this).closest('tr').addClass("selected");
	    	$(".buttons-print").click();
	    	//location.reload();
	    });
	
    jQuery.noConflict();

    var table = $('#training-center-list').DataTable({
        paging: true,
        ordering: false,
        searching: true,
      	responsive: true,
        autoWidth: false,
        columnDefs: [{ width: 200, targets: 1 }],
       fixedColumns: true,
       scrollX: true,
       /*  scrollY: 300, */
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
            { extend: 'copy', exportOptions: { columns: '' }},
            { extend: 'csv', exportOptions: {  columns: '' }},
            { extend: 'excel', exportOptions: {  columns: '' } },
            {
            	 extend: 'pdfHtml5',
                exportOptions: { columns: ':not(:last-child)'},
                customize: function(doc) {
                    doc.defaultStyle.fontSize = 6;
                    doc.styles.tableHeader.fontSize = 5;
                    doc.pageMargins = [0,0,0,0];
                    //doc.pageMargins = [20, 30, 30, 30];
                }
            },
            { extend: 'print', exportOptions: { columns: ':not(:last-child)' }}
        ],
        initComplete: function() {
            $('.buttons-copy').html('<i class="d-none bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
            $('.buttons-csv').html('<i class="d-none  bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
            $('.buttons-excel').html('<i class="d-none  bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
            $('.buttons-pdf').html('<i class="d-none  bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
            $('.buttons-print').html('<i class="d-none  bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
        },select: {
            style: 'single',
        }
    });

    // Toggle filter card
    $('#filterBtn').click(function () {
        console.log("clicked :::");
    	const $filterCard = $('#filterCard');
        $filterCard.toggleClass('show');
        $('#coaching_wing_financial_report_form input').val('');
        table.columns().search('').draw();
       	$(this).text($filterCard.hasClass('show') ? 'Close' : 'Filter');
    });

    // Reset filters
    $('#resetBtn').click(function () {
        $('#coaching_wing_financial_report_form input').val('');
        table.columns().search('').draw();
    });

    // Apply search
   $('#searchBtn').click(function (e) {
        e.preventDefault();
        table.columns().search('');

        const trainingCenterId = $('#training-center-id').val();
        const division = $('#division').val();
        const district = $('#district').val();
        const centretype = $('#centre-type').val();
        const sportstype = $('#sports-type').val();

        if (trainingCenterId) table.column(0).search(trainingCenterId);
        if (division) table.column(1).search(division);
        if (district) table.column(2).search(district);
        if (centretype) table.column(3).search(centretype);
        if (sportstype) table.column(4).search(sportstype);
        table.draw();
    });
    
    table 
    .rows( '.selected' )
    .nodes()
    .to$() 
    .removeClass( 'selected' );
});




</script>

<style>
.universal-table .table-bordered {
 margin-top: 0;
}
.universal-table .table-bordered {
border:none;
}
</style>
