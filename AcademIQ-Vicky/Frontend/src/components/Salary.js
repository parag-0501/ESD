import React, { useState } from 'react';
import '../styles/salary.css'; // Import your CSS file

const Salary = ({ user, setUser }) => {
  const [salaryDetails] = useState({
    totalSalary: 50000,
    taxes: 15000,
    otherDeductions: 5000,
    netSalary: 30000,
    history: [
      { month: 'January', amount: 5000 },
      { month: 'February', amount: 5200 },
      // Add more history data as needed
    ],
  });

  const handleDownload = (month) => {
    console.log(`Downloading salary slip for ${month}`);
  };

  return (
    <div className="salary-page">
      <div className="salary-container">
        <div className="salary-header">
          <h1 className="salary-heading">Salary Details</h1>
          <p>Name: {salaryDetails.employeeName}</p>
          <p>Payment Date: {salaryDetails.paymentDate}</p>
        </div>
        <div className="total-salary">
          <h2 className="section-heading">Total Salary Breakdown</h2>
          <div className="salary-breakdown">
            <div>
              <p>Gross Salary:</p>
              <p>Taxes:</p>
              <p>Other Deductions:</p>
              <p className="net-salary">Net Salary:</p>
            </div>
            <div>
              <p>₹{salaryDetails.totalSalary}</p>
              <p>₹{salaryDetails.taxes}</p>
              <p>₹{salaryDetails.otherDeductions}</p>
              <p className="net-salary">₹{salaryDetails.netSalary}</p>
            </div>
          </div>
        </div>
        <div className="salary-history">
          <h2 className="section-heading">Salary Disbursement History</h2>
          <ul className="history-list">
            {salaryDetails.history.map((entry, index) => (
              <li key={index} className="history-item">
                <span>{entry.month}</span>
                <span>Amount: ₹{entry.amount}</span>
                <button onClick={() => handleDownload(entry.month)}>
                  Download Slip
                </button>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Salary;
