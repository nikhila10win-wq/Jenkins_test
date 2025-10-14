<%@ include file="/init.jsp" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script> -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<form id="state-level-schemes" method="POST" enctype="multipart/form-data">

<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0">
							<liferay-ui:message key="state-level-schemes"/>
						</h5>
						<div>
							<a href="/group/guest/apply-state-level-grants-and-schemes" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>
								<liferay-ui:message key="back" />
							</a>
						</div>
					</div>
					
					<div class="card-body">
					
					<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="scheme-list"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="schemesList" id="schemesList" <c:if test="${mode eq 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<option value="Financial Assistance" >
											<liferay-ui:message key="financial-assistance"/></option>
										 <option value="International Sports Competition" >
											<liferay-ui:message key="financial-assistance-to-participating-athletes-in-international-sports-competition"/></option>
										<option value="International Competition Aspiring Athetes" >
											<liferay-ui:message key="financial-assistance-to-international-competition-aspiring-athetes"/></option>
										<option value="Award Winners" >
											<liferay-ui:message key="award-winners-in-international-sports-competition"/></option>
										<option value="Sports Council" >
											<liferay-ui:message key="maharashtra-state-sports-council-grant"/></option>
										
									</select>
								</div>
							</div>
						</div>
						
						
						
						 <div class="international_sports_competition" style="display: none;">
							<jsp:include page="/state-level/int-sports-comp-list.jsp"></jsp:include>
						</div> 
						
						 <div class="international_competition_aspiring_athetes" style="display: none;">
							<jsp:include page="/state-level/aspiring-athelet-list.jsp"></jsp:include>
						</div> 
					
						 <div class="award_winners" style="display: none;">
							<jsp:include page="/state-level/award-winner-list.jsp"></jsp:include>
						</div> 
						
						<div class="sports_council" style="display: none;">
							<jsp:include page="/state-level/council-list.jsp"></jsp:include>
						</div>
						
						  <div class="financial_assistance" style="display: none;">
							<jsp:include page="/state-level/financial-assistance-list.jsp"></jsp:include>
						</div>
						
					</div>
					</div>
					</div>
					</div>
				</div>
			</div>
</form>

<script>

$(document).ready(function () {
    $(".financial_assistance, .international_sports_competition, .international_competition_aspiring_athetes, .award_winners, .sports_council").hide();
    
    $("#schemesList").on("change", function () {
    	console.log("Changed Changed :::   ");
        var selected = $(this).val();
        
        $(".financial_assistance, .international_sports_competition, .international_competition_aspiring_athetes, .award_winners, .sports_council").hide();

        if (selected === "Financial Assistance") {
            $(".financial_assistance").show();
        } else if (selected === "International Sports Competition") {
            $(".international_sports_competition").show();
        } else if (selected === "International Competition Aspiring Athetes") {
            $(".international_competition_aspiring_athetes").show();
        } else if (selected === "Award Winners") {
            $(".award_winners").show();
        } else if (selected === "Sports Council") {
            $(".sports_council").show();
        } 
        
        if (selected) {
            $(".card-footer").show();
        }
    });
});

</script>
