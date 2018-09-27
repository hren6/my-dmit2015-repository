package dmit2015.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dmit2015.model.Loan;
import dmit2015.model.LoanSchedule;



@Named
@ViewScoped


public class LoanController implements Serializable{
	private Loan currentLoan = new Loan();
	private LoanSchedule[] currentLoanScheduleArray = new LoanSchedule[this.currentLoan.getAmortizationPeriod()];
	
	public LoanSchedule[] getCurrentLoanScheduleArray() {
		return currentLoanScheduleArray;
	}
	public void setCurrentLoanScheduleArray(LoanSchedule[] currentLoanScheduleArray) {
		this.currentLoanScheduleArray = currentLoanScheduleArray;
	}
	
	public void getLoanScheduleArray() {
		currentLoanScheduleArray = currentLoan.getLoanScheduleArray();
	}
	
	public Loan getCurrentLoan() {
		return currentLoan;
	}
	public void setCurrentLoan(Loan currentLoan) {
		this.currentLoan = currentLoan;
	}
	public void calculate() {
		
		createBarModels();
		getLoanScheduleArray();
	}

	private BarChartModel barModel;
	
	@PostConstruct
	public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        LoanSchedule[] currentLoanScheduleArray = currentLoan.getLoanScheduleArray();
        ChartSeries remainingBalance = new ChartSeries();
        remainingBalance.setLabel("Remaining Balance");
        for(int i=0; i <currentLoan.getAmortizationPeriod();i++) {
        	remainingBalance.set(i+1, currentLoanScheduleArray[i*12 +11].getRemainingBalance());
        }    
 
        model.addSeries(remainingBalance);

         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
    	
        barModel = initBarModel();
         
        LoanSchedule[] currentLoanScheduleArray = currentLoan.getLoanScheduleArray();
        ChartSeries remainingBalance = new ChartSeries();
        remainingBalance.setLabel("Remaining Balance");
        for(int i=0; i <currentLoan.getAmortizationPeriod();i++) {
        	remainingBalance.set(i+1, currentLoanScheduleArray[i*12 +11].getRemainingBalance());
        }    
        
        
        barModel.setTitle("Mortgage Amortizaion Payment");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setMax(currentLoan.getAmortizationPeriod());
        xAxis.setLabel("Years");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(currentLoan.getMortgageAmount());
    }
     
    
	

}
