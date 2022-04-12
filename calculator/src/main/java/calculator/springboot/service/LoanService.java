package calculator.springboot.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import calculator.springboot.model.Index;
import calculator.springboot.model.Loan;

@Service
public class LoanService {

	public List<Loan> loanCalculate(Index index) {
		
		final DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.UP);
		
		float interest = index.getInterest();
		System.out.println("interest " +interest);
		
		int loanAmount = index.getLoanAmount();
		System.out.println("loanAmount " +loanAmount);
		
		int period = index.getPeriod();
		System.out.println("period " +period);
		
		 float intRate = interest/12/100;
		 System.out.println("intRate " +intRate);
		 
		 int totalMonths = period * 12;
		
		 System.out.println("totalMonths " +totalMonths);
		 
		 float s1 = (float) Math.pow(1+intRate, totalMonths);
		 float s2 = (float) (Math.pow(1+intRate, totalMonths) - 1);
		 float s3 = s1/s2;
		 int emi = (int) (loanAmount * intRate * s3);
		 
		 System.out.println("emi " +emi);
		 
		 int month =1;
		
		 double loanBalance = loanAmount;
		 System.out.println("loanBalance " +loanBalance);
		 double principal = 0.0 ;
		 double monthlyInterest = 0.0;
		 double outStandingBalance = loanAmount;
		 System.out.println("outStandingBalance " +outStandingBalance);
		 
		 int loanBalance1;
		 int principal1;
		 int monthlyInterest1;
		 int outStandingBalance1;
		 
		 
		 List<Loan> loan = new ArrayList<Loan>();
		 while (month <=totalMonths) {
			
			 monthlyInterest = interest / 12 *(loanBalance/100);
			 principal = emi - monthlyInterest;
			 outStandingBalance = (loanBalance - principal);
			 
			 monthlyInterest = Math.round(monthlyInterest * 100.0)/100.0;
			 int s = (int) Math.round(monthlyInterest);
			 monthlyInterest = s;
			
			 principal = Math.round(principal * 100.0)/100.0;
			 outStandingBalance =  Math.round(outStandingBalance *100.0)/100.0;
			 if(month ==totalMonths ) {
				 outStandingBalance =0;
			 }
			 loanBalance1 = (int) loanBalance;
			 principal1 = (int) principal;
			 monthlyInterest1 = (int) monthlyInterest;
			 outStandingBalance1 = (int) outStandingBalance;
			 
			 
			 loan.add(new Loan(month,loanBalance1,emi,principal1,monthlyInterest1,outStandingBalance1));
			 loanBalance = Math.round(outStandingBalance * 100.0)/100.0;
			 ++month;
			 
		 }
		 
		return loan;
}
}
