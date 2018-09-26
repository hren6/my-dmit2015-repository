package dmit2015.model;

public class Loan {
	private double mortgageAmount;
	private double annualInterestRate;
	private int amortizationPeriod;
	
	public Loan() {
		super();
	}
	
	public Loan(double mortgageAmount, double annualInterestRate, int amortizationPeriod) {
		super();
		this.mortgageAmount = mortgageAmount;
		this.annualInterestRate = annualInterestRate;
		this.amortizationPeriod = amortizationPeriod;
	}
	
	public double getMonthlyPayment() {
		return Math.round((this.mortgageAmount * (Math.pow((1 + this.annualInterestRate/200),1.0/6 )-1)
				/(1 - Math.pow(Math.pow((1 + (this.annualInterestRate/200)), 1.0/6), (-12 * this.amortizationPeriod))))*100.0)/100.0;
		
	}
	
	public LoanSchedule[] getLoanScheduleArray() {
		double remainingBalance = this.mortgageAmount;
		double principalPaid;
		
		LoanSchedule[] loanScheduleArray = new LoanSchedule[this.amortizationPeriod *12];
		for(int i =0; i < this.amortizationPeriod * 12; i++) {
			double monthlyPercentageRate = (Math.pow((1.0 + this.annualInterestRate /200.0),1.0/6.0 )-1.0);
			
			double interestPaid = monthlyPercentageRate * remainingBalance;
			double interestPaidFormat = Math.round(interestPaid * 100.0)/100.0;
			
			if(i == this.amortizationPeriod * 12 -1) {
				principalPaid = remainingBalance;
				principalPaid = Math.round(principalPaid * 100.0)/100.0;
			}else {
				principalPaid = getMonthlyPayment() - interestPaid;
				principalPaid = Math.round(principalPaid * 100.0)/100.0; 
			}
			
			remainingBalance = remainingBalance - principalPaid;
			remainingBalance = Math.round(remainingBalance * 100.0)/100.0;
			
			LoanSchedule loanSchedue = new LoanSchedule(i + 1,interestPaidFormat, principalPaid,remainingBalance);
			
			loanScheduleArray[i] = loanSchedue;
			
		}
		
		return loanScheduleArray;
	}

	public double getMortgageAmount() {
		return mortgageAmount;
	}

	public void setMortgageAmount(double mortgageAmount) {
		this.mortgageAmount = mortgageAmount;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public int getAmortizationPeriod() {
		return amortizationPeriod;
	}

	public void setAmortizationPeriod(int amortizationPeriod) {
		this.amortizationPeriod = amortizationPeriod;
	}
	
	



	

}
