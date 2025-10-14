package com.mhdsys.common.service.budget;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.budget.BudgetCommonApi;
import com.mhdsys.common.pojo.BudgetCommonDTO;
import com.mhdsys.common.pojo.FundDistributionCommonDTO;
import com.mhdsys.schema.model.Budget;
import com.mhdsys.schema.model.FundDistribution;
import com.mhdsys.schema.service.BudgetLocalServiceUtil;
import com.mhdsys.schema.service.FundDistributionLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = BudgetCommonApi.class)

public class BudgetCommonService implements BudgetCommonApi {

	private Log LOGGER = LogFactoryUtil.getLog(BudgetCommonService.class);

	@Override
	public Budget saveBudget(BudgetCommonDTO budgetCommonDTO) {
		try {
			LOGGER.info("BUDGET DTO ::: " + budgetCommonDTO);

			if (budgetCommonDTO.getBudgetId() > 0) {
				LOGGER.info("UPDATE EDIT ");
				Budget budget = BudgetLocalServiceUtil.getBudget(budgetCommonDTO.getBudgetId());

				long budgetId = budget.getBudgetId();
				LOGGER.info("Budget Id : " + budgetId);
				BeanPropertiesUtil.copyProperties(budgetCommonDTO, budget);
				budget.setBudgetId(budgetId);
				LOGGER.info(budget.getDepartment() + "Budget Id : " + budgetId);

				return BudgetLocalServiceUtil.updateBudget(budget);
			} else {
				LOGGER.info("Add Budget ");
				Budget budget = BudgetLocalServiceUtil
						.createBudget(CounterLocalServiceUtil.increment(Budget.class.getName()));

				long budgetId = budget.getBudgetId();
				LOGGER.info("Budget Id : " + budgetId);
				BeanPropertiesUtil.copyProperties(budgetCommonDTO, budget);
				budget.setBudgetId(budgetId);
				LOGGER.info(budget.getDepartment() + "Budget Id : " + budgetId);

				return BudgetLocalServiceUtil.addBudget(budget);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public FundDistribution saveFundDistribution(FundDistributionCommonDTO fundDistributionCommonDTO) {
		try {
			LOGGER.info("Fund DTO ::: " + fundDistributionCommonDTO);

			if (fundDistributionCommonDTO.getFundDistributionId() > 0) {
				LOGGER.info("UPDATE EDIT ");
				FundDistribution distribution = FundDistributionLocalServiceUtil
						.getFundDistribution(fundDistributionCommonDTO.getFundDistributionId());

				long fundDistributionId = distribution.getFundDistributionId();
				LOGGER.info("Fund Id : " + distribution);
				BeanPropertiesUtil.copyProperties(fundDistributionCommonDTO, distribution);
				distribution.setFundDistributionId(fundDistributionId);
				LOGGER.info(distribution.getBudget() + "Fund Id : " + fundDistributionId);

				return FundDistributionLocalServiceUtil.updateFundDistribution(distribution);
			} else {
				LOGGER.info("Add Fund ");
				FundDistribution distribution = FundDistributionLocalServiceUtil
						.createFundDistribution(CounterLocalServiceUtil.increment(FundDistribution.class.getName()));

				long fundDistributionId = distribution.getFundDistributionId();
				LOGGER.info("Fund Id : " + fundDistributionId);
				BeanPropertiesUtil.copyProperties(fundDistributionCommonDTO, distribution);
				distribution.setFundDistributionId(fundDistributionId);
				LOGGER.info(distribution.getBudget() + "Fund Id : " + fundDistributionId);

				return FundDistributionLocalServiceUtil.addFundDistribution(distribution);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

}
