
<%@ include file="/init.jsp" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.mhdsys.grants.and.schemes.constants.MhdsysGrantsAndSchemesPortletKeys"%>

 
 <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
 

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<script src="https://cdn.jsdelivr.net/jquery.validation/1.19.5/jquery.validate.min.js"></script>
 
<portlet:resourceURL id="<%=MhdsysGrantsAndSchemesPortletKeys.SAVE_STATE_LEVEL_GRANTS_AND_SCHEMES_APPLICATION %>" var="addStateLevelScheme" /> 

<portlet:resourceURL id="<%=MhdsysGrantsAndSchemesPortletKeys.GET_COMPETITION_DETAILS_MVC_RESOURCE%>" var="getCompetitionDetailsURL" />

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
					<input type="hidden" class="form-control" name="intSportsCompId" id="intSportsCompId" value="" />
					<input type="hidden" class="form-control" name="awardWinnerId" id="awardWinnerId" value="" />
					<input type="hidden" class="form-control" name="councilSportCompetitionDetailsId" id="councilSportCompetitionDetailsId" value="" />
					
					<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="select-scheme"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="typeOfScheme" id="typeOfScheme" <c:if test="${mode eq 'view'}">disabled</c:if>>
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
						
						<div class="international_competition_aspiring_athetes" style="display: none;">
							<jsp:include page="/state-level/competition-aspiring-athetes/international-aspiring-athetes.jsp"></jsp:include>
						</div>	
						
						<div class="financial_assistance" style="display: none;">
							<jsp:include page="/state-level/financial-assistance/financial-assistance.jsp"></jsp:include>
						</div>		
						<div class="international_sports_competition" style="display: none;">
							<jsp:include page="/state-level/international-sport-competition/international-sports-competition.jsp"></jsp:include>
						</div>
						
						<div class="award_winners" style="display: none;">
							<jsp:include page="/state-level/award-winner/award-winners.jsp"></jsp:include>
						</div>
						<div class="sports_council" style="display: none;">
							<jsp:include page="/state-level/mahasports-council/competition-details.jsp"></jsp:include>
						</div> 
						
					
						
						
						</div>
							

						<c:if test="${mode eq 'add'}">
							<div class="card-footer bg-transparent text-right p-4">
								<button class="btn btn-primary" type="button" onclick="submitDetails(event)"><liferay-ui:message key="submit"/></button>
							</div>
						</c:if>
						<c:if test="${mode eq 'edit'}">
							<div class="card-footer bg-transparent text-right p-4">
								<button class="btn btn-primary" type="button" onclick="submitDetails(event)"><liferay-ui:message key="update"/></button>
							</div>
						</c:if>
					</div>
					</div>
					</div>
				</div>
			</div>

</form>



<!-- modal popup for establishment -->
<div class="modal fade" id="saveStateLevelSchemeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="state-level-scheme-details"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
										<%-- <img src="<%=request.getContextPath()%>/images/check.png" alt="" width="50px" class="my-3"> <span class="text-primary"></span><sup><em class="bi bi-copy mx-2"></em></sup> --%>
                                    	<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/state-level" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal('saveStateLevelSchemeModal','/group/guest/state-level')"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
		
		</div>
<!-- modal popup for establishment -->




<script>
$(document).ready(function () {
	  $('input[name="medalAchieved"]').change(function () {
	        if ($(this).val() === 'yes') {
	            $('#previousCompetitionHidden').show();
	        } else {
	            $('#previousCompetitionHidden').hide();
	        }
	    });
	
	
	
    // Hide all financial assistance sections and footer initially
    $(".financial_assistance").hide();
    $(".card-footer, .international_sports_competition, .international_competition_aspiring_athetes, .award_winners, .sports_council").hide();
    
    $("#typeOfScheme").on("change", function () {
        var selected = $(this).val();
        
        // Hide footer and all sections initially
        $(".card-footer").hide();
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
        
        // Show footer if any option is selected
        if (selected) {
            $(".card-footer").show();
        }
    });
    
    $("#state-level-schemes").validate({
        rules: {
        	
        	/* Financial Assistance	 */
        	
         /*  applicantName: { required: true },
          address: { required: true },
          pincode: { required: true },
          contactNumber: { required: true },
          emailId: { required: true }, */
         /*  sportsPerformanceDetails: { required: true }, */
          certifiedLetter: { required: true },
          certifiedLetterFile: { required: true },
          assistanceMatter: { required: true },
          assistanceMatterFile: { required: true },
          itemsEstimate: { required: true },
          itemsEstimateFile: { required: true },
          expenseBudget: { required: true },
          expenseBudgetFile: { required: true },
          priceList: { required: true },
          priceListFile: { required: true },
          expenditureEstimate: { required: true },
          expenditureEstimateFile:{ required: true },
          workReport: { required: true },
          workReportFile: { required: true },
          isDeclarationAccepted: { required: true },
          
          
        /*   award-winners-in-international-sports-competition */
        
          /* fullNameA: { required: true },
          dobA: { required: true },
          mobileNumberA: { required: true },
          emailIdA: { required: true }, */
          aadhaarCardFile: { required: true },
          panCardFile: { required: true },
          domicileCertificateFile: { required: true },
          bankNameA: { required: true },
          ifscCodeA: { required: true },
          accountNumberA: { required: true },
          bankStatementFile: { required: true },
          bankPassbookFile: { required: true },
          cancelledCheckFile: { required: true },
          /* competitionLevelA: { required: true },
          competitionNameA: { required: true },
          competitionYearA: { required: true }, */
          competitionDrawFile: { required: true },
          organizationSelectionLetterFile: { required: true },
         /*  competitionDateA: { required: true }, */
        /*   placeOfCompetitionA: { required: true }, */
         /*  competitionPerformanceA: { required: true }, */
          competitionRankA: { required: true },
          competitionResultFile: { required: true },
          competitionCertificateFile: { required: true },
          playersConsentLetterFile: { required: true },
          affidavitFile: { required: true },
          isDeclarationAcceptedA: { required: true },
          
          
          /* financial-assistance-to-international-competition-aspiring-athetes */
          
         /*  pinCode: { required: true }, */
          domicileFile: { required: true },
         /*  nameOfApplicant: { required: true }, */
         /*  totalCost: { required: true },
          costOfImportSportEquipment: { required: true },
          trainingFee: { required: true },
          guidanceFeeFromExpert: { required: true },
          trainingEquipmentCost: { required: true },
          preCompetitionTrainingFees: { required: true },
          mealCost: { required: true },
          hotelFees: { required: true },
          entryFees: { required: true },
          travelCost: { required: true }, */
         /*  newRecordCertificateFile: { required: true },
          selectionCertificateFile: { required: true },
          selectionLetterFile: { required: true },
          pastCompetitionRank: { required: true },
          pastCompetitionDate: { required: true },
          pastCompetitionYear: { required: true }, */
         /*  prevPlace: { required: true }, */
          medalAchieved: { required: true },
         /*  competitionRank: { required: true },
          competitionDate: { required: true },
          placeOfCompetition: { required: true },
          competitionName: { required: true },
          competitionLevel: { required: true },
          sportType: { required: true },
          sportName: { required: true },
          grantedSport: { required: true },
          grantedSportName: { required: true }, */
          bankName: { required: true },
          ifscCode: { required: true },
          accountNumber: { required: true },
          bankStatement: { required: true },
          affidavit: { required: true },
          affidavitBondpaper: { required: true },
          isDeclarationAcceptedCa: { required: true },
          
         /*  financial-assistance-to-participating-athletes-in-international-sports-competition */
         
         
         /*  fullName: { required: true }, */
         /*  intSportsComp: { required: true }, */
        /*   mobileNo: { required: true }, */
          aadhaarCard: { required: true },
          panCard: { required: true },
          domicileCertificate: { required: true },
          passBook: { required: true },
          cancelledCheck: { required: true },
          competitionRank: { required: true },
         /*  competitionYear: { required: true }, */
          selectionLetter: { required: true },
         /*  competitionPlace: { required: true }, */
          /* competitionPerformance: { required: true }, */
          competitionCertificate: { required: true },
          itemBudgets: { required: function(){
        	  return uploadedFilesItemBudgets.length===0
          }
        	  },
          
         /*  maharashtra-state-sports-council-grant */
          
          councilOrganizationName: { required: true },
          councilOrganizationAddress: { required: true },
          councilCompetitionName: { required: true },
          councilCompetitionLevel: { required: true },
          councilCompetitionSponsor: { required: true },
          councilSponsorAddress: { required: true },
          councilTeamPlayersCount: { required: true },
          councilPlayerListAttachment: { required: true },
          councilCompetitionVenue: { required: true },
          councilCompetitionDuration: { required: true },
          councilEstimatedCost: { required: true },
          councilEstimatedCostAttachment: { required: true },
          /* councilFederationConsent: { required: true }, */
          councilFederationConsentAttachment: { required: true },
          councilRegistrationNumber: { required: true },
          councilDirectorateApprovalNumber: { required: true },
          /* councilDirectorateApprovalAttachment: { required: true }, */
          councilDistrictAffiliationAttachment: { required: true },
          councilCertificateAffiliationAttachment: { required: true },
          councilRule3Approval: { required: true },
          councilDirectorateApproval: { required: true },
         /*  councilDirectorateApprovalAttachment: { required: true }, */
          councilPrescribedFormatDSO: { required: true },
          councilPrescribedFormatAttachment: { required: true },
          councilCaStatement: { required: true },
          councilCaStatementAttachment: { required: true },
          councilOfficeBearersList: { required: true },
          councilOfficeBearersAttachment: { required: true },
          councilOrganizationCertificate: { required: true },
          councilOrganizationCertificateAttachment: { required: true },
          councilSportsTrainingPrevious: { required: true },
          councilSportsTrainingDetails: { required: true },
          councilSportsGrantFormDetails: { required: true },
          councilConcernLetterAttachment: { required: true },
          councilCostEstimationAttachment: { required: true },
          councilDetailedReportAttachment: { required: true },
          councilAuditedStatementAttachment: { required: true },
          councilTeamPlayersListAttachment: { required: true },
          councilFundsRaised: { required: true },
          councilActualLoss: { required: true },
          /* councilSouvenirIssued: { required: true }, */
          councilSouvenirAttachment: { required: true },
          councilCompetitionPhotos: { required: true },
          councilAffidavitAttachment: { required: true },
          
          
        },
        messages: {
        	
        	/* Financial Assistance */
        	
         /*  applicantName: "<liferay-ui:message key='please-enter-applicant-name' />",
          address: "<liferay-ui:message key='please-enter-address' />",
          pincode: "<liferay-ui:message key='please-enter-pincode' />",
          contactNumber: "<liferay-ui:message key='please-enter-contact-number' />",
          emailId: "<liferay-ui:message key='please-enter-email-id' />", */
          /* sportsPerformanceDetails: "<liferay-ui:message key='please-enter-sports-performance-details' />", */
          certifiedLetter: "<liferay-ui:message key='please-enter-certified-letter' />",
          certifiedLetterFile: "<liferay-ui:message key='please-upload-certified-letter' />",
          assistanceMatter: "<liferay-ui:message key='please-enter-assistance-matter' />",
          assistanceMatterFile: "<liferay-ui:message key='please-upload-assistance-matter' />",
          itemsEstimate: "<liferay-ui:message key='please-enter-items-estimate' />",
          itemsEstimateFile: "<liferay-ui:message key='please-upload-items-estimate' />",
          expenseBudget: "<liferay-ui:message key='please-enter-expense-budget' />",
          expenseBudgetFile: "<liferay-ui:message key='please-upload-expense-budget' />",
          priceList: "<liferay-ui:message key='please-enter-price-list' />",
          priceListFile: "<liferay-ui:message key='please-upload-price-list' />",
          expenditureEstimate: "<liferay-ui:message key='please-enter-expenditure-estimate' />",
          expenditureEstimateFile: "<liferay-ui:message key='please-upload-expenditure-estimate' />",
          workReport: "<liferay-ui:message key='please-enter-work-report' />",
          workReportFile: "<liferay-ui:message key='please-upload-work-report-file' />",
          isDeclarationAccepted: "<liferay-ui:message key='please-accept-declaration' />",
          
          /* award-winners-in-international-sports-competition */
          
         /*  fullNameA: "<liferay-ui:message key='please-enter-full-name' />",
          dobA: "<liferay-ui:message key='please-select-date-of-birth' />", */
          mobileNumberA: "<liferay-ui:message key='please-enter-mobile-number' />",
          emailIdA: "<liferay-ui:message key='please-enter-email-id' />",
          aadhaarCardFile: "<liferay-ui:message key='please-upload-aadhaar-card' />",
          panCardFile: "<liferay-ui:message key='please-upload-pan-card' />",
          domicileCertificateFile: "<liferay-ui:message key='please-upload-domicile-certificate' />",
          bankNameA: "<liferay-ui:message key='please-enter-bank-name' />",
          ifscCodeA: "<liferay-ui:message key='please-enter-ifsc-code' />",
          accountNumberA: "<liferay-ui:message key='please-enter-account-number' />",
          bankStatementFile: "<liferay-ui:message key='please-upload-bank-statement' />",
          bankPassbookFile: "<liferay-ui:message key='please-upload-bank-passbook' />",
          cancelledCheckFile: "<liferay-ui:message key='please-upload-cancelled-cheque' />",
          /* competitionLevelA: "<liferay-ui:message key='please-select-competition-level' />",
          competitionNameA: "<liferay-ui:message key='please-enter-competition-name' />",
          competitionYearA: "<liferay-ui:message key='please-select-competition-year' />", */
          competitionDrawFile: "<liferay-ui:message key='please-upload-competition-draw' />",
          organizationSelectionLetterFile: "<liferay-ui:message key='please-upload-selection-letter' />",
         /*  competitionDateA: "<liferay-ui:message key='please-select-competition-date' />",
          placeOfCompetitionA: "<liferay-ui:message key='please-enter-place-of-competition' />",
          competitionPerformanceA: "<liferay-ui:message key='please-enter-competition-performance' />", */
          competitionRankA: "<liferay-ui:message key='please-enter-competition-rank' />",
          competitionResultFile: "<liferay-ui:message key='please-upload-competition-result' />",
          competitionCertificateFile: "<liferay-ui:message key='please-upload-competition-certificate' />",
          playersConsentLetterFile: "<liferay-ui:message key='please-upload-players-consent-letter' />",
          affidavitFile: "<liferay-ui:message key='please-upload-affidavit' />",
          isDeclarationAcceptedA: "<liferay-ui:message key='please-accept-declaration' />",
          
         /*  financial-assistance-to-international-competition-aspiring-athetes */
         
         /*  pinCode: "<liferay-ui:message key='please-enter-pincode' />", */
          domicileFile: "<liferay-ui:message key='please-upload-domicile' />",
          /* nameOfApplicant: "<liferay-ui:message key='please-enter-name-of-applicant' />", */
         /*  totalCost: "<liferay-ui:message key='please-enter-total-cost' />",
          costOfImportSportEquipment: "<liferay-ui:message key='please-enter-cost-of-imported-sport-equipment' />",
          trainingFee: "<liferay-ui:message key='please-enter-training-fee' />",
          guidanceFeeFromExpert: "<liferay-ui:message key='please-enter-guidance-fee' />",
          trainingEquipmentCost: "<liferay-ui:message key='please-enter-training-equipment-cost' />",
          preCompetitionTrainingFees: "<liferay-ui:message key='please-enter-pre-competition-training-fees' />",
          mealCost: "<liferay-ui:message key='please-enter-meal-cost' />",
          hotelFees: "<liferay-ui:message key='please-enter-hotel-fees' />",
          entryFees: "<liferay-ui:message key='please-enter-entry-fees' />",
          travelCost: "<liferay-ui:message key='please-enter-travel-cost' />", */
         /*  newRecordCertificateFile: "<liferay-ui:message key='please-upload-new-record-certificate' />",
          selectionCertificateFile: "<liferay-ui:message key='please-upload-selection-certificate' />",
          selectionLetterFile: "<liferay-ui:message key='please-upload-selection-letter' />",
          pastCompetitionRank: "<liferay-ui:message key='please-enter-past-competition-rank' />",
          pastCompetitionDate: "<liferay-ui:message key='please-select-past-competition-date' />",
          pastCompetitionYear: "<liferay-ui:message key='please-enter-past-competition-year' />", */
         /*  prevPlace: "<liferay-ui:message key='please-enter-previous-place' />", */
          medalAchieved: "<liferay-ui:message key='please-select-medal-achieved' />",
         /*  competitionRank: "<liferay-ui:message key='please-enter-competition-rank' />",
    	  competitionDate: "<liferay-ui:message key='please-select-competition-date' />",
          placeOfCompetition: "<liferay-ui:message key='please-enter-place-of-competition' />", 
          competitionName: "<liferay-ui:message key='please-enter-competition-name' />",
          competitionLevel: "<liferay-ui:message key='please-select-competition-level' />",
          sportType: "<liferay-ui:message key='please-select-sport-type' />",
          sportName: "<liferay-ui:message key='please-enter-sport-name' />",
          grantedSport: "<liferay-ui:message key='please-select-granted-sport' />",
          grantedSportName: "<liferay-ui:message key='please-enter-granted-sport-name' />", */
          bankName: "<liferay-ui:message key='please-enter-bank-name' />",
          ifscCode: "<liferay-ui:message key='please-enter-ifsc-code' />",
          accountNumber: "<liferay-ui:message key='please-enter-account-number' />",
          bankStatement: "<liferay-ui:message key='please-upload-bank-statement' />",
          affidavit: "<liferay-ui:message key='please-upload-affidavit' />",
          affidavitBondpaper: "<liferay-ui:message key='please-upload-affidavit-bondpaper' />",
          isDeclarationAcceptedCa: "<liferay-ui:message key='please-accept-declaration' />",
          
         /*  financial-assistance-to-participating-athletes-in-international-sports-competition */
         
         /*  fullName: "<liferay-ui:message key='please-enter-full-name' />", */
         /*  intSportsComp: "<liferay-ui:message key='please-enter-international-competition-name' />", */
         /*  mobileNo: "<liferay-ui:message key='please-enter-mobile-number' />", */
          aadhaarCard: "<liferay-ui:message key='please-upload-aadhaar-card' />",
          panCard: "<liferay-ui:message key='please-upload-pan-card' />",
          domicileCertificate: "<liferay-ui:message key='please-upload-domicile-certificate' />",
          passBook: "<liferay-ui:message key='please-upload-passbook' />",
          cancelledCheck: "<liferay-ui:message key='please-upload-cancelled-cheque' />",
          competitionRank: "<liferay-ui:message key='please-enter-competition-rank' />",
         /*  competitionYear: "<liferay-ui:message key='please-select-competition-year' />", */
          selectionLetter: "<liferay-ui:message key='please-upload-selection-letter' />",
          /* competitionPlace: "<liferay-ui:message key='please-enter-competition-place' />",
          competitionPerformance: "<liferay-ui:message key='please-enter-competition-performance' />", */
          competitionCertificate: "<liferay-ui:message key='please-upload-competition-certificate' />",
          itemBudgets: "<liferay-ui:message key='please-enter-item-budget-details' />",
          
          /*  maharashtra-state-sports-council-grant */
          
          councilOrganizationName: "<liferay-ui:message key='please-enter-organization-name' />",
          councilOrganizationAddress: "<liferay-ui:message key='please-enter-organization-address' />",
          councilCompetitionName: "<liferay-ui:message key='please-enter-competition-name' />",
          councilCompetitionLevel: "<liferay-ui:message key='please-select-competition-level' />",
          councilCompetitionSponsor: "<liferay-ui:message key='please-enter-competition-sponsor' />",
          councilSponsorAddress: "<liferay-ui:message key='please-enter-sponsor-address' />",
          councilTeamPlayersCount: "<liferay-ui:message key='please-enter-team-players-count' />",
          councilPlayerListAttachment: "<liferay-ui:message key='please-upload-player-list-attachment' />",
          councilCompetitionVenue: "<liferay-ui:message key='please-enter-competition-venue' />",
          councilCompetitionDuration: "<liferay-ui:message key='please-enter-competition-duration' />",
          councilEstimatedCost: "<liferay-ui:message key='please-enter-estimated-cost' />",
          councilEstimatedCostAttachment: "<liferay-ui:message key='please-upload-estimated-cost-attachment' />",
         /*  councilFederationConsent: "<liferay-ui:message key='please-enter-federation-consent' />", */
          councilFederationConsentAttachment: "<liferay-ui:message key='please-upload-federation-consent' />",
          councilRegistrationNumber: "<liferay-ui:message key='please-enter-registration-number' />",
          councilDirectorateApprovalNumber: "<liferay-ui:message key='please-enter-directorate-approval-number' />",
         /*  councilDirectorateApprovalAttachment: "<liferay-ui:message key='please-upload-directorate-approval-attachment' />", */
          councilDistrictAffiliationAttachment: "<liferay-ui:message key='please-upload-district-affiliation-certificate' />",
          councilCertificateAffiliationAttachment: "<liferay-ui:message key='please-upload-certificate-of-affiliation' />",
          councilRule3Approval: "<liferay-ui:message key='please-select-rule-3-approval' />",
          councilDirectorateApproval: "<liferay-ui:message key='please-select-directorate-approval' />",
          /* councilDirectorateApprovalAttachment: "<liferay-ui:message key='please-upload-directorate-approval-attachment' />", */
          councilPrescribedFormatDSO: "<liferay-ui:message key='please-select-prescribed-format-dso' />",
          councilPrescribedFormatAttachment: "<liferay-ui:message key='please-upload-prescribed-format-attachment' />",
          councilCaStatement: "<liferay-ui:message key='please-enter-ca-statement' />",
          councilCaStatementAttachment: "<liferay-ui:message key='please-upload-ca-statement-attachment' />",
          councilOfficeBearersList: "<liferay-ui:message key='please-enter-office-bearers-list' />",
          councilOfficeBearersAttachment: "<liferay-ui:message key='please-upload-office-bearers-attachment' />",
          councilOrganizationCertificate: "<liferay-ui:message key='please-enter-organization-certificate' />",
          councilOrganizationCertificateAttachment: "<liferay-ui:message key='please-upload-organization-certificate-attachment' />",
          councilSportsTrainingPrevious: "<liferay-ui:message key='please-select-sports-training-previously-conducted' />",
          councilSportsTrainingDetails: "<liferay-ui:message key='please-enter-sports-training-details' />",
          councilSportsGrantFormDetails: "<liferay-ui:message key='please-enter-sports-grant-form-details' />",
          councilConcernLetterAttachment: "<liferay-ui:message key='please-upload-concern-letter' />",
          councilCostEstimationAttachment: "<liferay-ui:message key='please-upload-cost-estimation-attachment' />",
          councilDetailedReportAttachment: "<liferay-ui:message key='please-upload-detailed-report' />",
          councilAuditedStatementAttachment: "<liferay-ui:message key='please-upload-audited-statement' />",
          councilTeamPlayersListAttachment: "<liferay-ui:message key='please-upload-team-players-list' />",
          councilFundsRaised: "<liferay-ui:message key='please-enter-funds-raised' />",
          councilActualLoss: "<liferay-ui:message key='please-enter-actual-loss' />",
         /*  councilSouvenirIssued: "<liferay-ui:message key='please-select-souvenir-issued' />", */
          councilSouvenirAttachment: "<liferay-ui:message key='please-upload-souvenir-attachment' />",
          councilCompetitionPhotos: "<liferay-ui:message key='please-upload-competition-photos' />",
          councilAffidavitAttachment: "<liferay-ui:message key='please-upload-affidavit-attachment' />",
         
        },
        errorPlacement: function (error, element) {
            if (element.attr("type") == "radio") {
                error.insertAfter(element.parent().parent());
            } else if (element.attr("type") == "checkbox" && element.hasClass("form-check-input")) {
                error.insertAfter(element.closest('.form-check'));
            } else {
                error.insertAfter(element);
            }
        }
      });
    
    
    
});


function submitDetails(event) {
	debugger;

	    var form = $("#state-level-schemes")[0];
	    var formData = new FormData(form);
	    
	    if (event) {
	        event.preventDefault();
	    }
	   // if($('#awardYouthOrg').valid()){
		   
		   
		   uploadedFilesItemBudgets.forEach((fileObj) => {
	        if (fileObj && !fileObj.markedForDelete) {
	            if (fileObj.isExisting) {
	                formData.append("existingItemBudgets", fileObj.name);
	                formData.append("existingItemBudgetsURLs", fileObj.url);
	                formData.append("existingItemBudgetsIds", fileObj.id);
	                console.log("Id : "+fileObj.id);
	            } else {
	                formData.append("actualItemBudgets", fileObj.file);
	                formData.append("actualItemBudgetsNames", fileObj.name);
	            }
	        }
	    });
		   
	if($('#state-level-schemes').valid()){
       $.ajax({
        type: "POST",
        url: '${addStateLevelScheme}' ,
        data:  formData,
        contentType : false,
		cache : false,
		processData : false,
        success: function(data){
        console.log("data: ", typeof data);
        if (typeof data === 'string') {
            try {
                data = JSON.parse(data);
            } catch (e) {
                console.error("Failed to parse JSON response: ", e);
                return;
            }
        }
        console.log("Parsed data: ", data);
        	if(data.status == true){
        		var msg = "<liferay-ui:message key="the-details-are-saved-successfully"/>";
        	    $('#success-application').html(msg);
        		 $("#saveStateLevelSchemeModal").modal('show');
        	}else{
        		var msg = "<liferay-ui:message key="the-details-are-failed-to-submit"/>";
        	    $('#success-application').html(msg);
        		 $("#saveStateLevelSchemeModal").modal('show');
        	}
    	 }
    });
	}
	    //}
}


// Single File Upload Start

function handleFileUpload(event, id, filePreviewContainer, filePreviewLink, deleteBtn) {debugger
	    
	    const fileInput = event.target;
	    const file = fileInput.files[0];
	    const previewContainer = document.getElementById(filePreviewContainer);
	    const previewLink = document.getElementById(filePreviewLink);
	    const deleteButton = document.getElementById(deleteBtn);

	    previewContainer.classList.add('d-none');
	    previewContainer.classList.remove('d-flex');
	    previewLink.textContent = '';
	    previewLink.href = '';
	    deleteButton.dataset.filename = '';
	    
	    if (file && $('#' + id).val() !== '' && (typeof $('#' + id).valid === 'function' ? $('#' + id).valid() : true)) {   
	        const fileName = file.name;

	        previewContainer.classList.remove('d-none');
	        previewContainer.classList.add('d-flex');

	        previewLink.textContent = fileName;
	        previewLink.href = URL.createObjectURL(file); 
	        previewLink.target = "_blank";
	        
	        Object.assign(previewLink.style, {
	            textDecoration: "none",
	            //wordWrap: "break-word",
	            //whiteSpace: "normal",
	           // overflow: "hidden",
	           // maxWidth: "200px"
	        });

	        deleteButton.dataset.filename = fileName;
	    }
	}

	function deleteFile(filePreviewContainer,id) {
	    const previewContainer = document.getElementById(filePreviewContainer);
	    const fileInput = document.getElementById(id);

	    fileInput.value = "";
	 $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("choose-file"); 
	    previewContainer.classList.add('d-none');
	    previewContainer.classList.remove('d-flex');
	}
	
	// Single file upload end
	
	function closeModal(id,redirectUrl) {debugger
    $("#"+id).modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
	 if (redirectUrl) {
	        window.location.href = redirectUrl;
	    }
}
	
	
	
	var uploadedFilesItemBudgets = [];
	var uploadedFilesCouncilCompetitionPhotos = [];

	function handleMultipleFileUpload(eventOrInput, inputId, previewContainerId, previewListId, errorSpanId, hiddenInputId) {debugger
		
	    const fileInput = eventOrInput.target ? eventOrInput.target : eventOrInput;
	    const previewContainer = document.getElementById(previewContainerId);
	    const previewList = document.getElementById(previewListId);
	    const errorSpan = document.getElementById(errorSpanId);
	    const hiddenInput = document.getElementById(hiddenInputId);

	    let uploadedFiles;
	    if (inputId === 'itemBudgets') {
	        uploadedFiles = uploadedFilesItemBudgets;
	    }  else if (inputId === 'councilCompetitionPhotos') {
	        uploadedFiles = uploadedFilesCouncilCompetitionPhotos;
	    }

	    const newFiles = Array.from(fileInput.files);
	    const activeFilesCount = uploadedFiles.filter(f => !f.markedForDelete).length;
	    const totalFiles = activeFilesCount + newFiles.length;

	    if (totalFiles > 5 && inputId === 'itemBudgets') {
	        errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="You can upload a maximum of 4 files." /></span>';
	        errorSpan.style.display = "block";
	        fileInput.value = "";
	        return;
	    }
	   /*  if (totalFiles < 10 && inputId === 'councilCompetitionPhotos') {
	        errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="maximum-10-photos-allowed" /></span>';
	        errorSpan.style.display = "block";
	        fileInput.value = "";
	        return;
	    }
	 */
	    for (let file of newFiles) {
	        const ext = file.name.split('.').pop().toLowerCase();

	        if (uploadedFiles.some(f => f.name === file.name && !f.markedForDelete)) {
	            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="this-file-is-already-uploaded" /></span>';
	            errorSpan.style.display = "block";
	            fileInput.value = "";
	            return;
	        }

	        if (inputId === 'itemBudgets') {
	            if (!['png', 'jpg', 'jpeg', 'pdf'].includes(ext)) {
	                errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="allowed-only-jpg-jpeg-png-pdf-file" /></span>';
	                errorSpan.style.display = "block";
	                fileInput.value = "";
	                return;
	            }
	        } else if (inputId === 'councilCompetitionPhotos') {
	            if (!['png', 'jpg', 'jpeg', 'pdf'].includes(ext)) {
	                errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="only-jpg-png-allowed" /></span>';
	                errorSpan.style.display = "block";
	                fileInput.value = "";
	                return;
	            }
	        }

	        
	        if (file.size >= 2 * 1024 * 1024) {
	            errorSpan.innerHTML = '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="file-size-limit" /></span>';
	            errorSpan.style.display = "block";
	            fileInput.value = "";
	            return;
	        }
	    }

	    errorSpan.textContent = "";
	    errorSpan.style.display = "none";

	    newFiles.forEach(file => {
	        uploadedFiles.push({
	            file: file,
	            name: file.name,
	            isExisting: false,
	            markedForDelete: false
	        });
	    });

	    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
	    fileInput.value = "";
	}

	function removeFile(index, previewContainerId, previewListId, errorSpanId, hiddenInputId, inputId) {
	    let uploadedFiles;
	    if (inputId === 'itemBudgets') {
	        uploadedFiles = uploadedFilesItemBudgets;
	    } else if (inputId === 'councilCompetitionPhotos') {
	        uploadedFiles = uploadedFilesCouncilCompetitionPhotos;
	    }


	    if (index < 0 || index >= uploadedFiles.length) {
	        console.error("Invalid file index:", index);
	        return;
	    }

	    if (uploadedFiles[index].isExisting) {
	        uploadedFiles[index].markedForDelete = true;
	    } else {
	        uploadedFiles.splice(index, 1);
	    }

	    const previewContainer = document.getElementById(previewContainerId);
	    const previewList = document.getElementById(previewListId);
	    const errorSpan = document.getElementById(errorSpanId);
	    const hiddenInput = document.getElementById(hiddenInputId);

	    if (!previewContainer || !previewList || !errorSpan || !hiddenInput) {
	        console.error("Required DOM elements not found");
	        return;
	    }

	    renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput);
	    const hasActiveFiles = uploadedFiles.some(f => !f.markedForDelete);
	    errorSpan.style.display = hasActiveFiles ? "none" : "block";
	    errorSpan.innerHTML = hasActiveFiles ? "" : '<span style="color: red; margin-top: 5px;"><liferay-ui:message key="please-upload-at-least-one-file" /></span>';
	}

	function renderFilePreviews(inputId, previewContainer, previewList, errorSpan, hiddenInput) {
	    let uploadedFiles;
	    if (inputId === 'itemBudgets') {
	        uploadedFiles = uploadedFilesItemBudgets;
	    } else if (inputId === 'councilCompetitionPhotos') {
	        uploadedFiles = uploadedFilesCouncilCompetitionPhotos;
	    }


	    previewList.innerHTML = "";

	    uploadedFiles.forEach((fileObj, index) => {
	        if (fileObj.markedForDelete) return;

	        const fileItem = document.createElement("li");
	        fileItem.className = "list-group-item d-flex justify-content-between align-items-center";

	        const fileLink = document.createElement("a");
	        fileLink.href = fileObj.isExisting ? (fileObj.url || "#") : URL.createObjectURL(fileObj.file);
	        fileLink.textContent = fileObj.name;
	        fileLink.target = "_blank";
	        fileLink.style.cssText = "flex-grow: 1; text-decoration: none; word-wrap: break-word; white-space: nowrap; overflow: hidden; max-width: 200px;";

	        const deleteBtn = document.createElement("button");
	        deleteBtn.type = "button";
	        deleteBtn.className = "btn btn-danger btn-sm";
	        deleteBtn.innerHTML = '<i class="bi bi-x-circle-fill"></i>';
	        deleteBtn.onclick = () => removeFile(
	            index,
	            previewContainer.id,
	            previewList.id,
	            errorSpan.id,
	            hiddenInput.id,
	            inputId
	        );

	        fileItem.appendChild(fileLink);
	        fileItem.appendChild(deleteBtn);
	        previewList.appendChild(fileItem);
	    });

	    previewContainer.style.display = uploadedFiles.some(f => !f.markedForDelete) ? "block" : "none";
	    
	    hiddenInput.value = uploadedFiles
	        .filter(f => !f.markedForDelete)
	        .map(f => f.name)
	        .join(',');
	}
// Multiple File Upload




function getCompetitionDetails(){
	console.log("competition name: ", $('.competitionName').val())
	console.log("competition name Asp: ", $('.competitionNameAsp').val())
	console.log("competition name Award: ", $('.competitionNameA').val())
	console.log("competition name Council: ", $('.councilCompetitionName').val())
	
	var competitionName = $('.competitionName').val();
	var competitionNameAsp = $('.competitionNameAsp').val();
	var competitionNameA = $('.competitionNameA').val();
	var councilCompetitionName = $('.councilCompetitionName').val();
	
	var form = $("#state-level-schemes")[0]; 
	var formData = new FormData(form);
	 
	 formData.append("competitionName", competitionName);
	 formData.append("competitionNameAsp", competitionNameAsp);
	 formData.append("competitionNameA", competitionNameA);
	 formData.append("councilCompetitionName", councilCompetitionName);
	 
	$.ajax({
        type: "POST",
        url: '${getCompetitionDetailsURL}' ,
        data:  formData,
        contentType : false,
		cache : false,
		processData : false,
        success: function(data){debugger
        console.log("data: ", typeof data);
        if (typeof data === 'string') {
            try {
                data = JSON.parse(data);
            } catch (e) {
                console.error("Failed to parse JSON response: ", e);
                return;
            }
        }
        let awardApp = JSON.parse(data.awardApplication);
        console.log("Parsed data: ", awardApp);
        console.log("competitionLevel: ", data.competitionLevel);
        console.log("competitionYear: ", awardApp.participationYear);
        	$('.competitionLevel').val(data.competitionLevel);
        	$('.competitionYear').val(awardApp.participationYear);
        	$('.competitionDate').val(data.competitionStartDate);
        	$('.competitionPlace').val(awardApp.competitionPlace);
        	$('.competitionPerformance').val(awardApp.highestPerformance);
        	
        	$('.competitionDateIntCmp').val(data.competitionStartDate);
        	$('.placeOfCompetitionIntCmp').val(awardApp.competitionPlace);
        	$('.competitionLevelntCmp').val(data.competitionLevel);
        	$('.sportType').val(data.sportType);
        	$('.sportName').val(data.sportName);
        	
        	$('.competitionPerformanceA').val(awardApp.highestPerformance);
        	$('.placeOfCompetitionA').val(awardApp.competitionPlace);
        	$('.competitionDateA').val(data.competitionStartDate);
        	$('.competitionYearA').val(awardApp.participationYear);
        	$('.competitionLevelA').val(data.competitionLevel);
        	
        	$('.councilCompetitionLevel').val(data.competitionLevel);
        	$('.councilCompetitionVenue').val(awardApp.competitionPlace);
        	$('.councilCompetitionDuration').val(data.competitionStartDate);
    	 }
    });
}
</script>