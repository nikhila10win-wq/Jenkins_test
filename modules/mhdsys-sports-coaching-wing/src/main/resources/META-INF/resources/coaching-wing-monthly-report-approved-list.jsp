<%@ include file="/init.jsp" %>
<!-- DataTables CSS -->
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css"> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"> -->

<!-- DataTables JS -->
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.7.1/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.66/vfs_fonts.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script> -->
<script src='https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js'></script>

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
    
    
    /* #training-center-list th, 
	#training-center-list td {
    min-width: 150px;
    white-space: nowrap;  */
    
    
  /*  table td, table th {
      white-space: nowrap;
    } */
    
    
    table.dataTable tbody tr.selected>* {
     box-shadow: #eeeef6;
     box-shadow: #eeeef6;
     color: black;
     color: black; 
}

	/* .dt-buttons{
     	display: none;
     } */
     
     
    .selected {
	    background: #eeeef6 important;
       /* color: black important; */
       box-shadow: #eeeef6 important;
	}
	
	.dt-buttons{
		display: none
	}
    
</style>
<div class="common-forms-div">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="border-0 card shadow">
                    <div class="card-header d-flex align-items-center justify-content-between">
                        <h5><liferay-ui:message key="coaching-wing-monthly-report-list" /></h5>
                        <c:if test="${isDSO}">
                        <div><a href="/group/guest/sports-coaching-wing-reports" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                        </c:if>
                         <c:if test="${ishoadmin}">
                        <div><a href="/group/guest/sports-coaching-wing-reports" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                        </c:if>
                        <c:if test="${isDDD}">
                        <div><a href="/group/guest/sports-coaching-wing-reports" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i> <liferay-ui:message key="back" /></a></div>
                        </c:if>
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
                                    
                                    <form id="coaching_wing_filter_form" method="POST">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="timestamp" /></label>
                                                    <input type="text" class="form-control" id="timestamp" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="report-month" /></label>
                                                    <input type="text" class="form-control" id="report-month" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="name-of-coach" /></label>
                                                    <input type="text" class="form-control" id="name-of-coach" />
                                                </div>
                                                <div class="col-md-3">
                                                    <label><liferay-ui:message key="district-name" /></label>
                                                    <input type="text" class="form-control" id="district-name" />
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
                                <table id="training-center-list" class="table-bordered">
                                    <thead>
                                        <tr>
                                            <th><liferay-ui:message key="timestamp" /></th>
                                            <th><liferay-ui:message key="report-month" /></th>
                                            <th><liferay-ui:message key="name-of-coach" /></th>
                                            <th><liferay-ui:message key="district-name" /></th>
                                            <th><liferay-ui:message key="educational -qualifications" /></th>
                                            <th><liferay-ui:message key="If you have completed any other courses for sports guidance, please provide information about it." /></th>
                                            <th><liferay-ui:message key="Sports Name" /></th>
                                            <th><liferay-ui:message key="Training center location/place" /></th>
                                            <th><liferay-ui:message key="Number of players at the training center" /></th>
                                            <th><liferay-ui:message key="Performance of players - International/National/State/Division/District" /></th>
                                            <th><liferay-ui:message key="Training Program (Annual/Quarterly/Monthly)" /></th>
                                            <th><liferay-ui:message key="Has the analysis of the players' performance been done, and if so, what are the methods - physical/mental/medical?" /></th>
                                            <th><liferay-ui:message key="What planning have you done to increase the performance of the best players at the training center?" /></th>
                                            <th><liferay-ui:message key="Your idea about the ideal sports training center" /></th>
                                            <th><liferay-ui:message key="Have you received any kind of assistance from the Sports Science Center at your training center, and what efforts have you made about this?" /></th>
                                              <th><liferay-ui:message key="action" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                        <c:forEach var="monthlyReport" items="${coachingWingMonthlyReportsList}">
                                        
                                          <portlet:renderURL var="viewReportURL">
												<portlet:param name="mvcRenderCommandName"
													value="<%=SportsCoachinWingMvcCommand.VIEW_MONTHLY_REPORT%>" />
												<portlet:param name="coachingWingMonthlyReportId"
													value="${monthlyReport.reportId}" />
												<portlet:param name="mode" value="view" />
										</portlet:renderURL>
                                            <tr>
                                                <td>${monthlyReport.timestampStr}</td>
                                                <td>${monthlyReport.month}</td>
                                                <td>${monthlyReport.coachName}</td>
                                                <td>${monthlyReport.district}</td>
                                                <td>${monthlyReport.educationalQualification}</td>
                                                <td>${monthlyReport.otherCourse}</td>
                                                <td>${monthlyReport.sportsName}</td>
                                                <td>${monthlyReport.trainingCenterPlace}</td>
                                                <td>${monthlyReport.noOfPlayers}</td>
                                                <td>${monthlyReport.performanceOfThePlayers}</td>
                                                <td>${monthlyReport.trainingProgram}</td>
                                                <td>${monthlyReport.playerAnalysisMethod}</td>
                                                <td>${monthlyReport.planningForBestPlayers}</td>
                                                <td>${monthlyReport.idea}</td>
                                                <td>${monthlyReport.assistanceAndEffort}</td>
											     <td>
											     <c:if test="${isDDD}">
													<div class="tooltip-icon">
								                           <ul class="inline-item">
								                           <%--  <li class="list-inline-item">
					                            				<a class="printrowpbtn btn btn-sm btn-primary" href="javascript:void(0)" ><i class="bi bi-printer icons-color" title="<liferay-ui:message key="print" />"></i></a>
					                            			</li> --%>
					                            			 <li class="list-inline-item">
					                            				<a class="printrowpdf btn btn-sm btn-primary" href="javascript:void(0)" ><i class="bi bi-file-earmark-medical icons-color" title="<liferay-ui:message key="report" />"></i></a>
					                            			</li>
					                            			<li class="list-inline-item">
					                            				<a  class="btn btn-sm btn-primary" href="${viewReportURL}" ><i class="bi bi-eye text-dark" title="<liferay-ui:message key="view" />"></i></a>
					                            			</li>
								                           </ul>
								                    </div>
											      </c:if>
											       <c:if test="${ishoadmin}">
													<div class="tooltip-icon">
								                           <ul class="inline-item">
								                            <li class="list-inline-item">
					                            				<%-- <a onclick="generateReport('${monthlyReport.reportId}', '${monthlyReport.reportId}')" class="btn btn-sm btn-primary" href="javascript:void(0);" ><i class="bi bi-file-earmark-medical icons-color" title="<liferay-ui:message key="report" />"></i></a> --%>
					                            				<a  class="btn btn-sm btn-primary" href="javascript:void(0)" ><i class="bi bi-file-earmark-medical icons-color" title="<liferay-ui:message key="report" />"></i></a>
					                            			</li>
					                            			<li class="list-inline-item">
					                            				<a  class="btn btn-sm btn-primary" href="${viewReportURL}" ><i class="bi bi-eye text-dark" title="<liferay-ui:message key="view" />"></i></a>
					                            			</li>
								                           </ul>
								                    </div>
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
//13 aug
/* async function exportRow(button) {
	  const { jsPDF } = window.jspdf;
	  const row = button.closest("tr");
	  const cells = Array.from(row.querySelectorAll("td")).slice(1).map(td => td.innerText);

	  // Create PDF instance
	  const doc = new jsPDF();

	  // Title/Header
	  doc.setFontSize(16);
	  doc.text("Selected Row Export", 15, 15);

	  // Table header and row data
	  doc.autoTable({
	    head: [['timestamp', 'report-month', 'name-of-coach','district-name','educational -qualifications','If you have completed any other courses for sports guidance, please provide information about it.',
	    	'Sports Name','Training center location/place','Number of players at the training center','Performance of players - International/National/State/Division/District','Has the analysis of the players',
	    	'performance been done, and if so, what are the methods - physical/mental/medical?',
	    	'What planning have you done to increase the performance of the best players at the training center?',
	    	'Your idea about the ideal sports training center',
	    	'Have you received any kind of assistance from the Sports Science Center at your training center, and what efforts have you made about this?']],
	    body: [cells],
	    startY: 25
	  });

	  // Save PDF
	  doc.save(`row-${cells[0]}.pdf`);
	} */
//13 aug	

function generateReport(reportId, applicationId) {
  	 console.log("clicked generate report::");
  	 
  	 console.log("reportId ::: "+reportId);
  	 console.log("URL ::"+'${generateMonathlyReportURL}');
  	 
  	    $.ajax({
  	        type: "POST", 
  	        url: '${generateMonathlyReportURL}', 
  	        data: { 
  	        	reportId: reportId, 
  	        	applicationId:applicationId }, 
  	        success: function(resp) { 
  	            console.log("Response data:", resp);
  	            console.log("Download URL : "+resp.downloadURL);
  	            
                  window.open(resp.downloadURL, '_blank');
  	            
  	        },
  	        error: function(xhr, status, error) {
  	            console.error("Error occurred:", error);
  	        }
  	    });
  	}

$(document).ready(function() {
	
	   /* $('#training-center-list').on('click', '.export-row-pdf', function() {
		   var data_row = table.row( $(this).parents('tr') ).data(); // here is the change
	 
	        var rowData = $('#training-center-list').DataTable().row($(this).closest('tr')).data();
	        // Now 'rowData' contains the data for the clicked row
	        generatePdfFromRowData(rowData);
	    });	
	 */
	/* 
	 $('#training-center-list tbody').on('click', '.export-row-pdf', function () {
	        var data = table.row($(this).parents('tr')).data();
	        
	        console.log("data :::" +data);
	        
	        
	        // Now 'data' contains the object for the clicked row
	        // You can then format this data for your PDF
	        generateSingleRowPdf(data); // Call a function to generate the PDF
	    }); */	
	    
	    /* $(document).on('click','.printrowpdf',function(){
	    	console.log("printrowpdf button ::: clicked ");
	    	 $("#training-center-list tr").removeClass("selected");
	    	 //$(this).closest('tr').addClass("selected");
	    	$(".buttons-pdf ").click();
	    });
	     */
	    
	    
	    
	    
	    $(document).on('click','.printrowpbtn',function(){
	    	console.log("printrowpdf button ::: clicked ");
	    	 $("#training-center-list tr").removeClass("selected");
	    	 //$(this).closest('tr').addClass("selected");
	    	$(".buttons-print").click();
	    });
	    

    jQuery.noConflict();
	//13-aug    
	//13-aug    
	    

    var table = $('#training-center-list1').DataTable({
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
            $('.buttons-copy').html('<i class="d-none bi bi-copy" title="<liferay-ui:message key="copy" />"></i>');
            $('.buttons-csv').html('<i class="d-none bi bi-filetype-csv" title="<liferay-ui:message key="csv" />"></i>');
            $('.buttons-excel').html('<i class="d-none  bi bi-file-earmark-excel" title="<liferay-ui:message key="excel" />"></i>');
            $('.buttons-pdf').html('<i class="d-none  bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
            $('.buttons-print').html('<i class="d-none  bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
        }
    });

    
    var table = $('#training-center-list').DataTable({
    	 "lengthMenu": [5, 10, 50, 100],
         "pageLength": 50,
         autoWidth: false,
         columnDefs: [{ width: 200,
        	 targets: [4,5,6,7,8,9,10,11,12,13,14], 
             visible: false}],
         fixedColumns: true,
         layout: {
             topStart: {
                 buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
             }
         },
         /* scrollX: true,
         scrollY: 300,
         "scrollX": true, */
         dom: '<"top"Bf>rt<"bottom"lip><"clear">',
         buttons: [{ extend: 'print', exportOptions: { columns: ':not(:last-child)' }},{
             extend: 'pdf',
             exportOptions: { columns: ':not(:last-child)' },
             customize: function(doc) {
                 doc.defaultStyle.fontSize = 6;
                 doc.styles.tableHeader.fontSize = 5;
                 doc.pageMargins = [0, 0, 0, 0];
             },
         },/* {
                 extend: 'excelHtml5',
                 text: 'Export All',
                 exportOptions: {
                     columns: ':visible:not(.not-exported)'
                 },
                 title: 'Data export'
             }, {
                 extend: 'excelHtml5',
                 text: 'Export selected',
                 exportOptions: {
                     columns: ':visible:not(.not-exported)',
                     modifier: {
                         selected: true
                     }
                 },
                 title: 'Data export'
             } */
         ], initComplete: function() {
             $('.buttons-pdf').html('<i class="bi bi-file-earmark-pdf" title="<liferay-ui:message key="pdf" />"></i>');
             $('.buttons-print').html('<i class="bi bi-printer" title="<liferay-ui:message key="print" />"></i>');
         },
         select: {
             style: 'single',
         }
    });
   // $('table td:not(:last-child)').css('pointer-events', 'none');
    table 
    .rows( '.selected' )
    .nodes()
    .to$() 
    .removeClass( 'selected' );
    
   /*  table.addEventListener('click', function(event) {
        const clickedRow = event.target.closest('tr'); // Get the clicked table row
        if (clickedRow && clickedRow.parentNode === table.querySelector('tbody')) { // Ensure it's a data row
            // Deselect all previously selected rows
            const selectedRows = table.querySelectorAll('tr.selected');
            selectedRows.forEach(row => row.classList.remove('selected'));

            // Select the clicked row
            clickedRow.classList.add('selected');
        }
    }); */
    
    
  //Enable button if row selected
    table.on('select deselect', function() {
        var selectedRows = table.rows({
            selected: true
        }).count();
        table.button('.buttons-pdf').enable(selectedRows > 0);
    });
   
   
   //trigger pdf button event on click on report button
    $(document).on('click','.printrowpdf',function(){
	    	console.log("printrowpdf button ::: clicked ");
	    	 $("#training-center-list tr").removeClass("selected");
	    	 //$(this).closest('tr').addClass("selected");
	    	 $(this).trigger('dblclick');
	    	$(".buttons-pdf ").trigger('click');
	    });
    
    
    
    // Toggle filter card
    $('#filterBtn').click(function () {
        const $filterCard = $('#filterCard');
        $filterCard.toggleClass('show');
        $(this).text($filterCard.hasClass('show') ? 'Close' : 'Filter');
    });

    // Reset filters
    $('#resetBtn').click(function () {
        $('#coaching_wing_filter_form input').val('');
        table.columns().search('').draw();
    });

    // Apply search
    $('#searchBtn').click(function (e) {
        e.preventDefault();
        table.columns().search('');

        const timestamp = $('#timestamp').val();
        const reportmonth = $('#report-month').val();
        const nameofcoach = $('#name-of-coach').val();
        const districtname = $('#district-name').val();

        if (timestamp) table.column(0).search(timestamp);
        if (reportmonth) table.column(1).search(reportmonth);
        if (nameofcoach) table.column(2).search(nameofcoach);
        if (districtname) table.column(3).search(districtname);
        table.draw();
    });
    
    
    
    
});


function generatePdfFromRowData(rowData) {
	
	console.log("rowData ::" +rowData);	

    var docDefinition = {
        content: [
            { text: 'Row Details', style: 'header' },
            // Loop through rowData to add key-value pairs or formatted content
            { text: 'timestamp: ' + rowData[0] },
            { text: 'Age: ' + rowData.age },
            // ... add more fields as needed
        ],
        styles: {
            header: {
                fontSize: 18,
                bold: true,
                margin: [0, 0, 0, 10]
            }
        }
    };
    pdfMake.createPdf(docDefinition).download('row_details.pdf');
}

</script>
