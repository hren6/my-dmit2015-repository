package dmit2015.model;

public class Loan {
	private double mortgageAmount;
	private double annualInterestRate;
	private int amortizationPeriod;
	public double getMortgageAmount() {
		return mortgageAmount;
	}
	public void setMortgageAmount(double mortgageAmount) {
		if(mortgageAmount >=0) {
			this.mortgageAmount = mortgageAmount;
		}
		
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		if(annualInterestRate >=0) {
			this.annualInterestRate = annualInterestRate;
		}
		
	}
	public int getAmortizationPeriod() {
		return amortizationPeriod;
	}
	public void setAmortizationPeriod(int amortizationPeriod) {
		if(amortizationPeriod >=0) {
			this.amortizationPeriod = amortizationPeriod;
		}
		
	}
	public Loan() {
		mortgageAmount=0;
		amortizationPeriod=0;
		annualInterestRate=0;
	}
	public Loan(double mortgageAmount, double annualInterestRate, int amortizationPeriod) {
		super();
		this.mortgageAmount = mortgageAmount;
		this.annualInterestRate = annualInterestRate;
		this.amortizationPeriod = amortizationPeriod;
	}
	@Override
	public String toString() {
		return "Loan [mortgageAmount=" + mortgageAmount + ", annualInterestRate=" + annualInterestRate
				+ ", amortizationPeriod=" + amortizationPeriod + "]";
	}

	public double getMonthlyPayment() {
		double monthlyPayment = mortgageAmount * (Math.pow((1 + annualInterestRate/200),1/6 )-1)
				/(1 - Math.pow(Math.pow((1 + (annualInterestRate/200)), 1/6), (-12 * amortizationPeriod)));
		return monthlyPayment;
	}

}
