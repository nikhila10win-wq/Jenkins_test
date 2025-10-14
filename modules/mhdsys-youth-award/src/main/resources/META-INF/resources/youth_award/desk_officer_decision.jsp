<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
    <div class="card-header header-card">
        <liferay-ui:message key="review-by-desk-officer" />
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
                        <input class="form-check-input" type="radio" name="deskDecision" value="true" id="deskDecisionApprove"
                            <c:choose>
                                <c:when test="${not empty commonDTO.deskRemarks}">disabled</c:when>
                                <c:otherwise>checked</c:otherwise>
                            </c:choose>
                            <c:if test="${commonDTO.deskDecision == 'true' && not empty commonDTO.deskRemarks}">checked</c:if> />
                        <label class="form-check-label" for="deskDecisionApprove">
                            <liferay-ui:message key="approve" />
                        </label>
                    </div>
                
                    <!-- Reject Radio -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="deskDecision" value="false" id="deskDecisionReject"
                            <c:if test="${not empty commonDTO.deskRemarks}">disabled</c:if>
                            <c:if test="${commonDTO.deskDecision == 'false' && not empty commonDTO.deskRemarks}">checked</c:if> />
                        <label class="form-check-label" for="deskDecisionReject">
                            <liferay-ui:message key="reject" />
                        </label>
                    </div>
                </div>
            </div>

            <div class="col-md-12">
                <div class="form-group">
                    <label><liferay-ui:message key="remarks" /> <sup class="text-danger">*</sup></label>
                    <textarea class="form-control" id="deskRemarks" name="deskRemarks" rows="2" placeholder="<liferay-ui:message key="enter-remarks" />"
                        <c:if test="${not empty commonDTO.deskRemarks}">disabled</c:if>>${commonDTO.deskRemarks}</textarea>
                </div>
            </div>
        </div> 
    </div>
</div>