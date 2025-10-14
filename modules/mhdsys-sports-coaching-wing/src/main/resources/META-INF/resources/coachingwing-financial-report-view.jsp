<%@ include file="/init.jsp" %>

<style>
    		.financialTab .error-container{
    			color: red;
    		}

        .financialTab .form-input, .financialTab .form-control {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid rgba(37, 37, 141, 0.32);
    		border-radius: 5px;
    		height: 45px;
    		font-size: 13px;
    		font-family: Poppins-Meduim;
        }
        .financialTab .form-textarea {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid rgba(37, 37, 141, 0.32);
    		border-radius: 5px;
    		font-size: 13px;
    		font-family: Poppins-Meduim;
        }
        .financialTab .form-input:focus,.financialTab .form-control:focus,.financialTab .form-textarea:focus {
            outline: none;
            border-color: #3b82f6;
            box-shadow: 0 0 0 2px #bfdbfe;
        }
        .financialTab .form-attachment {
            display: block;
            width: 100%;
            padding: 0.75rem;
            border: 1px dashed #d1d5db;
            border-radius: 0.5rem;
            background-color: #f9fafb;
            cursor: pointer;
        }
        .financialTab .attachment-group .form-label {
        	margin: 0px;
    		color: #26268E;
    		font-family: Poppins-Semibold;
    		font-size: 13px;
        }
       
       .financialTab .attachment-group {
            border: 1px solid #e5e7eb;
            padding: 1rem;
            border-radius: 0.5rem;
            margin-top: 1rem;
        }
        /* Styles for validation errors */
        .financialTab .error {
            color: #dc2626; /* red-600 */
            font-size: 0.875rem; /* text-sm */
            margin-top: 0.25rem;
        }
    </style>


<div class="common-forms-div">
		<div class="container">
			<div class="row">
				<div class="card shadow border-0">
					<div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
						<h5 class="mb-0">
							<liferay-ui:message key="coaching-wing-financial-details-report" />
						</h5>
						<div>
								<c:if test="${centerType eq'talent_dev'}">
									<a href="/group/guest/district-sport-talent-development-centre-reports" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
									<i class="bi bi-arrow-left-circle"></i>
									<liferay-ui:message key="back" />
									</a>
								</c:if>
								<c:if test="${centerType eq 'coaching'}">
									<a href="/group/guest/district-coaching-centre-report" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
									<i class="bi bi-arrow-left-circle"></i>
									<liferay-ui:message key="back" />
									</a>
								</c:if>
								<c:if test="${centerType eq 'khelo_india'}">
									<a href="/group/guest/khelo-india-district-centre-report" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
									<i class="bi bi-arrow-left-circle"></i>
									<liferay-ui:message key="back" />
									</a>
								</c:if>
								<c:if test="${centerType eq 'taluka'}">
									<a href="/group/guest/taluka-coaching-centre-report" class="btn btn-primary btn-sm rounded-pill back-btn-cn">
									<i class="bi bi-arrow-left-circle"></i>
									<liferay-ui:message key="back" />
									</a>
								</c:if>
							
						</div>
					</div>
					<div class="card-body">
						<div class="card card-background p-0">
								<div class="personal_details">
									<div class="card-header header-card">
										<liferay-ui:message key="coaching-wing-financial-details-report" />
									</div>
									<div class="card-body financialTab">
					                    <div id="financialDetailsContainer1" class="space-y-4">
						                    	 <c:if test="${not empty talentDev}">
						                    		<c:forEach items="${talentDev.financialTraingData}" var="fd" varStatus="fdcounter">
								                    	<div class="financial-item form-group grid grid-cols-1 md:grid-cols-12 gap-4 items-center" id="${fdcounter.count}">
											                  <div class="md:col-span-7">
											                      <label class="form-label text-sm">${fd.description}</label>
											                  </div>
											                  <div class="md:col-span-2">
											                      <label for="gr_amount_${fdcounter.count}" class="form-label md:hidden">Amount as per GR</label>
											                      <input type="text" id="gr_amount_${fdcounter.count}" name="gr_amount_${fdcounter.count}" class="form-input bg-gray-100" value="${fd.grAmount}" disabled="disabled">
											                  </div>
											                  <div class="md:col-span-3">
											                      <label for="req_amount_${fdcounter.count}" class="form-label md:hidden">Requested Amount</label>
											                      <input type="text" id="req_amount_${fdcounter.count}" name="req_amount_${fdcounter.count}" class="form-input" placeholder="Requested Amount" disabled="disabled" value="${fd.requestedAmount}">
											                  </div>
											              </div>
								                        </c:forEach>
								                        <div class="financial-item form-group grid grid-cols-1 md:grid-cols-12 gap-4 items-center">
								                        <div class="md:col-span-2">
											                      <label for="total" class="form-label md:hidden">Total Amount</label>
											                      <input type="text" id="total" name="total" class="form-input" placeholder="Requested Amount" disabled="disabled" value="1448500">
									                  	</div>
									                  	<div class="md:col-span-3">
										                      <label for="totalrequestedAmount" class="form-label md:hidden">Total Requested Amount</label>
										                      <input type="text" id="totalrequestedAmount" name="totalrequestedAmount" class="form-input" placeholder="Requested Amount" disabled="disabled" value="${talentDev.total}">
										                  </div>
										                  </div>
						                    	</c:if>
						                 </div>
				                	</div>
                              </div>
                         </div>
                   </div>
              </div>
		</div>
	</div>
</div>
