package com.mhdsys.common.api.budget;

import com.mhdsys.common.pojo.BudgetCommonDTO;
import com.mhdsys.common.pojo.FundDistributionCommonDTO;
import com.mhdsys.schema.model.Budget;
import com.mhdsys.schema.model.FundDistribution;

public interface BudgetCommonApi {

	Budget saveBudget(BudgetCommonDTO budgetCommonDTO);

	FundDistribution saveFundDistribution(FundDistributionCommonDTO fundDistributionCommonDTO);
}
