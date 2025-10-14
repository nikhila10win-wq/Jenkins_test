<%@ include file="/init.jsp" %>

<div class="card card-background p-0">
    <div class="card-header header-card">
        <liferay-ui:message key="review-by-assistant-director" />
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
                        <input class="form-check-input" type="radio" name="asstDirDecision" value="true" id="asstDirDecisionApprove"
                            <c:choose>
                                <c:when test="${not empty commonDTO.asstDirRemarks}">disabled</c:when>
                                <c:otherwise>checked</c:otherwise>
                            </c:choose>
                            <c:if test="${commonDTO.asstDirDecision == 'true' && not empty commonDTO.asstDirRemarks}">checked</c:if> />
                        <label class="form-check-label" for="asstDirDecisionApprove">
                            <liferay-ui:message key="approve" />
                        </label>
                    </div>
                
                    <!-- Reject Radio -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="asstDirDecision" value="false" id="asstDirDecisionReject"
                            <c:if test="${not empty commonDTO.asstDirRemarks}">disabled</c:if>
                            <c:if test="${commonDTO.asstDirDecision == 'false' && not empty commonDTO.asstDirRemarks}">checked</c:if> />
                        <label class="form-check-label" for="asstDirDecisionReject">
                            <liferay-ui:message key="reject" />
                        </label>
                    </div>
                </div>
            </div>

            <div class="col-md-12">
                <div class="form-group">
                    <label><liferay-ui:message key="remarks" /> <sup class="text-danger">*</sup></label>
                    <textarea class="form-control" id="asstDirRemarks" name="asstDirRemarks" rows="2" placeholder="<liferay-ui:message key="enter-remarks" />"
                        <c:if test="${not empty commonDTO.asstDirRemarks}">disabled</c:if>>${commonDTO.asstDirRemarks}</textarea>
                </div>
            </div>
        </div> 
    </div>
</div>