<%@page import="com.mhdys.establishment.constants.EstablishmentWebPortletKeys"%>
<%@ include file="/init.jsp"%>

			<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					<div class="card-header d-flex align-item-center justify-content-between">
						<h5><liferay-ui:message key="establishment-list" /></h5>						
					</div>
				
					<div class="card-body ">
					<div  id="sport-person-coach-div">
					<div class="universal-table">
				<table id="establishment-list" class="table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><liferay-ui:message key="employee-name" /></th>
							<th><liferay-ui:message key="category" /></th>
							<th><liferay-ui:message key="action" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details"
							items="${detailses}">
							<tr>
								<td>${details.employeeName}</td>
								<td>${details.category}</td>
								<portlet:renderURL var="editDetailsURL">
									<portlet:param name="mvcRenderCommandName"value="<%=EstablishmentWebPortletKeys.ESTABLISHMENT_MVC_RENDER_COMMAND%>"></portlet:param>
									<portlet:param name="primaryId"value="${details.primaryId }"></portlet:param>
									<portlet:param name="cmd"value="edit"></portlet:param>
								 </portlet:renderURL>
								<portlet:renderURL var="viewDetailsURL">
									<portlet:param name="mvcRenderCommandName"value="<%=EstablishmentWebPortletKeys.ESTABLISHMENT_MVC_RENDER_COMMAND%>"></portlet:param>
									<portlet:param name="primaryId"value="${details.primaryId }"></portlet:param>
									<portlet:param name="cmd"value="view"></portlet:param>
								 </portlet:renderURL>
								<td>
									<a href="${editDetailsURL}"><i class="bi bi-check2-square icons-color" title="<liferay-ui:message key="edit"/>"></i></a>
									<a href="${viewDetailsURL}"><i class="bi bi-eye icons-color" title="<liferay-ui:message key="view" />"></i></a>
								</td>
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
		<!-- </div>
	</div>
</div> -->
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css">
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>

<script>
 $(document).ready(function () {
        // Initialize DataTable
        var table = $('#sport-person-coach-list').DataTable({
            "paging": true,
            "ordering": false,
            "searching": true,
        });
 });
</script> -->