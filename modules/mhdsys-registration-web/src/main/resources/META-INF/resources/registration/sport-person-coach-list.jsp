<%@ include file="/init.jsp"%>
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="card-header d-flex align-item-center justify-content-between">
						<h5>Sport person coach List</h5>						
					</div>
					<div class="card-body ">
						<div id="sport-person-coach-div">
						<div class="universal-table">
				<table id="sport-person-coach-list" class="table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><liferay-ui:message key="competition-name" /></th>
							<th><liferay-ui:message key="winner-name" /></th>
							<th><liferay-ui:message key="first-runner-up-name" /></th>
							<th><liferay-ui:message key="second-runner-up-name" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sportPersonCoachRegistration"
							items="${sportPersonCoachRegistrations}">
							<tr>
								<td>${sportPersonCoachRegistration.competitionName}</td>
								<td>${sportPersonCoachRegistration.competitionName}</td>
								<td>${sportPersonCoachRegistration.competitionName}</td>
								<td>${sportPersonCoachRegistration.competitionName}</td>
							</tr>
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
</div>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>

<script>
 $(document).ready(function () {
        // Initialize DataTable
        var table = $('#sport-person-coach-list').DataTable({
            "paging": true,
            "ordering": false,
            "searching": true,
            "responsive": true,
			"autoWidth": false,
        });
 });
</script>