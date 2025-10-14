<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
    <div class="card-header header-card">
        <liferay-ui:message key="review-by-ho" />
    </div>
    <div class="card-body">
        <!-- Row 5 -->
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label>
                        <liferay-ui:message key="approval" />
                        <sup class="text-danger">*</sup>
                    </label><br />
                
                    <!-- Approve Radio -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="ddHoDecision" value="true" id="ddHoDecisionApprove"
                            <c:choose>
                                <c:when test="${not empty commonDTO.ddHoRemarks}">disabled</c:when>
                                <c:otherwise>checked</c:otherwise>
                            </c:choose>
                            <c:if test="${commonDTO.ddHoDecision == 'true' && not empty commonDTO.ddHoRemarks}">checked</c:if> />
                        <label class="form-check-label" for="ddHoDecisionApprove">
                            <liferay-ui:message key="approve" />
                        </label>
                    </div>
                
                    <!-- Reject Radio -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="ddHoDecision" value="false" id="ddHoDecisionReject"
                            <c:if test="${not empty commonDTO.ddHoRemarks}">disabled</c:if>
                            <c:if test="${commonDTO.ddHoDecision == 'false' && not empty commonDTO.ddHoRemarks}">checked</c:if> />
                        <label class="form-check-label" for="ddHoDecisionReject">
                            <liferay-ui:message key="reject" />
                        </label>
                    </div>
                </div>
            </div>

            <div class="col-md-12">
                <div class="form-group">
                    <label><liferay-ui:message key="remarks" /> <sup class="text-danger">*</sup></label>
                    <textarea class="form-control" id="ddHoRemarks" name="ddHoRemarks" rows="2" placeholder="<liferay-ui:message key="enter-remarks" />"
                        <c:if test="${not empty commonDTO.ddHoRemarks}">disabled</c:if>>${commonDTO.ddHoRemarks}</textarea>
                </div>
            </div>
        </div> 
    </div>
</div>