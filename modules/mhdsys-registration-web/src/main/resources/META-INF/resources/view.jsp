<%@page import="com.mhdsys.registartion.web.constants.RegistrationWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<portlet:renderURL var="registrationURL">
    <portlet:param name="mvcRenderCommandName" value="<%=RegistrationWebPortletKeys.SPORTS_REGISTRATION %>" />
</portlet:renderURL>


	<!-- Button trigger modal --> 
	<a href="javascript:;" class="text-decoration-none text-white" data-toggle="modal" data-target="#registrationModal">
 		<i class="bi bi-person mr-2"></i> <liferay-ui:message key="registration"/>
 	</a>


<!-- Modal -->
		<div class="modal fade" id="registrationModal" tabindex="-1" aria-labelledby="registrationModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
			<div class="modal-content">
			  <div class="modal-header">
				<h5 class="modal-title" id="registrationModalLabel"><liferay-ui:message key="registration"/></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				  <span aria-hidden="true"><i class="bi bi-x-circle-fill text-danger"></i></span>
				</button>
			  </div>
			  <div class="modal-body text-center">
					<div class="row">
						<div class="col-md-4 border-right">
							<a href="<%= registrationURL %>&type=SportsDeskOfficer" class="text-decoration-none text-dark display-7 d-block">
								<img src="<%=request.getContextPath()%>/img/desk-officer.png" alt="" />
								<p>
									<liferay-ui:message key="sports-department-desk-officer"/>
								</p>
							</a>
						</div>
						<div class="col-md-4 border-right">
							<a href="<%= registrationURL %>&type=LocalSelfGov" class="text-decoration-none text-dark display-7 d-block">
								<img src="<%=request.getContextPath()%>/img/local-self-gov.png" alt="" />
								<p>
									<liferay-ui:message key="local-self-government-registration"/>
								</p>
							</a>
						</div>
						<div class="col-md-4">
							<a href="<%= registrationURL %>&type=SchoolCollege" onclick="closeModal()" class="text-decoration-none text-dark display-7 d-block">
								<img src="<%=request.getContextPath()%>/img/school-collage.png" alt="" />
								<p>
									<liferay-ui:message key="school-college-education-institutions"/>
								</p>
							</a>
						</div>
					</div>
					<hr/>
					<div class="row">
						<div class="col-md-4 border-right">
							<a href="<%= registrationURL %>&type=SportsAssociation" class="text-decoration-none text-dark display-7 d-block">
								<img src="<%=request.getContextPath()%>/img/sports-association.png" alt="" />
								<p>
									<liferay-ui:message key="sports-associations-registration"/>
								</p>
							</a>
						</div>
						<div class="col-md-4 border-right">
							<a href="<%= registrationURL %>&type=SportsComplex" class="text-decoration-none text-dark display-7 d-block">
								<img src="<%=request.getContextPath()%>/img/govt-department.png" alt="" />
								<p>
									<liferay-ui:message key="sports-complex-government-department-registration"/>
								</p>
							</a>
						</div>
						<div class="col-md-4">
							<a href="<%= registrationURL %>&type=YouthInstitutes" class="text-decoration-none text-dark display-7 d-block">
								<img src="<%=request.getContextPath()%>/img/organization.png" alt="" />
								<p>
									<liferay-ui:message key="youth-institution-organization-clubs-local-self-government"/>
								</p>
							</a>
						</div>
					</div>
			  </div> 
			</div>
		  </div>
		</div>
		
		
		
<script>
function closeModal() {debugger
    $("#registrationModal").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}
</script>		
		