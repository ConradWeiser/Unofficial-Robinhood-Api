package conrad.weiser.robinhood.api.endpoint.account.data;

import java.net.URL;

import conrad.weiser.robinhood.api.endpoint.account.data.enums.InvestmentExperience;
import conrad.weiser.robinhood.api.endpoint.account.data.enums.InvestmentObjective;
import conrad.weiser.robinhood.api.endpoint.account.data.enums.LiquidityNeeds;
import conrad.weiser.robinhood.api.endpoint.account.data.enums.RiskTolerance;
import conrad.weiser.robinhood.api.endpoint.account.data.enums.SourceOfFunds;
import conrad.weiser.robinhood.api.endpoint.account.data.enums.TimeHorizon;

public class AccountHolderInvestmentElement {
	
	private String annual_income;
	private String investment_experience;
	private String investment_objective;
	private String liquid_net_worth;
	private String liquidity_needs;
	private String risk_tolerance;
	private String source_of_funds;
	private boolean suitability_verified;
	private String tax_bracket;
	private String time_horizon;
	private String total_net_worth;
	
	//TODO: Updated_at
	private URL user;

	
	/**
	 * @return the annual_income
	 */
	public String getAnnualIncome() {
		return annual_income;
	}

	/**
	 * @return the investment_experience as an enum value.
	 */
	public InvestmentExperience getInvestmentExperience() {
		
		switch(this.investment_experience) {
		
		case "extensive_investment_exp": return InvestmentExperience.EXTENSIVE_INVESTMENT_EXPERIENCE;
		case "good_investment_exp": return InvestmentExperience.GOOD_INVESTMENT_EXPERIENCE;
		case "limited_investment_exp": return InvestmentExperience.LIMITED_INVESTMENT_EXPERIENCE;
		case "no_investment_exp": return InvestmentExperience.LIMITED_INVESTMENT_EXPERIENCE;
		default: return InvestmentExperience.ERROR;
		
		}
		
	}

	/**
	 * @return the investment_objective as an enum value
	 */
	public InvestmentObjective getInvestmentObjective() {
		
		switch(this.investment_objective) {
		
		case "cap_preserve_invest_obj": return InvestmentObjective.CAPITAL_PRESERVE_INVESTMENT_OBJECTIVE;
		case "income_invest_obj": return InvestmentObjective.INCOME_INVESTMENT_OBJECTIVE;
		case "growth_invest_obj": return InvestmentObjective.GROWTH_INVESTMENT_OBJECTIVE;
		case "speculation_invest_obj": return InvestmentObjective.SPECULATION_INVESTMENT_OBJECTIVE;
		case "other_invest_obj": return InvestmentObjective.OTHER_INVESTMENT_OBJECTIVE;
		default: return InvestmentObjective.ERROR;
		
		}
	}

	/**
	 * @return the liquid_net_worth
	 */
	public String getLiquidNetWorth() {
		return liquid_net_worth;
	}

	/**
	 * @return the liquidity_needs as an enum value
	 */
	public LiquidityNeeds getLiquidityNeeds() {
		
		switch(this.liquidity_needs) {
		
		case "not_important_liq_need": return LiquidityNeeds.NOT_IMPORTANT_LIQUIDITY_NEED;
		case "somewhat_important_liq_need": return LiquidityNeeds.SOMEWHAT_IMPORTANT_LIQUIDITY_NEED;
		case "very_important_liq_need": return LiquidityNeeds.VERY_IMPORTANT_LIQUIDITY_NEED;
		default: return LiquidityNeeds.ERROR;
		
		}
		
	}

	/**
	 * @return the risk_tolerance
	 */
	public RiskTolerance getRiskTolerance() {
		
		switch(this.risk_tolerance) {
		
		case "low_risk_tolerance": return RiskTolerance.LOW_RISK_TOLERANCE;
		case "med_risk_tolerance": return RiskTolerance.MED_RISK_TOLERANCE;
		case "high_risk_tolerance": return RiskTolerance.HIGH_RISK_TOLERANCE;
		default: return RiskTolerance.ERROR;
		
		}
	}

	/**
	 * @return the source_of_funds
	 */
	public SourceOfFunds getSourceOfFunds() {
		
		switch(this.source_of_funds) {
		
		case "savings_personal_income": return SourceOfFunds.SAVINGS_PERSONAL_INCOME;
		case "pension_retirement": return SourceOfFunds.PENSION_RETIREMENT;
		case "insurance_payout": return SourceOfFunds.INSURANCE_PAYOUT;
		case "inheritance": return SourceOfFunds.INHERITANCE;
		case "gift": return SourceOfFunds.GIFT;
		case "sale_business_or_property": return SourceOfFunds.SALE_BUSINESS_OR_PROPERTY;
		case "other": return SourceOfFunds.OTHER;
		default: return SourceOfFunds.ERROR;
		
		}
	}

	/**
	 * @return the suitability_verified
	 */
	public boolean isSuitabilityVerified() {
		return suitability_verified;
	}

	/**
	 * @return the tax_bracket
	 */
	public String getTaxBracket() {
		return tax_bracket;
	}

	/**
	 * @return the time_horizon
	 */
	public TimeHorizon getTimeHorizon() {
		
		switch(this.time_horizon) {
		
		case "short_time_horizon": return TimeHorizon.SHORT_TIME_HORIZON;
		case "med_time_horizon": return TimeHorizon.MEDIUM_TIME_HORIZON;
		case "long_time_horizon": return TimeHorizon.LONG_TIME_HORIZON;
		default: return TimeHorizon.ERROR;
		
		}
	}

	/**
	 * @return the total_net_worth
	 */
	public String getTotalNetWorth() {
		return total_net_worth;
	}

	/**
	 * @return the user
	 */
	public URL getUser() {
		return user;
	}

}
