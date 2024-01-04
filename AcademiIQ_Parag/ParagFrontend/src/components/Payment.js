import React, { useState, useEffect } from 'react';
import '../styles/payment.css';
import axios from 'axios';


const Payment = ({ user, setUser }) => {
    const [bills, setBills] = useState([{
        id: 2,
        student: {
            id: 1,
            roll_no: 101
        },
        dsc: "2nd bill",
        amount: 750.99,
        date: "2023-10-31",
        deadline: "2023-11-30",
        status: "Pending"
    }]);
    const [totalPayment, setTotalPayment] = useState(0);

    // Fetch bills from API
    useEffect(() => {
        const fetchBillsDetails = async () => {
            try {
                const token = JSON.parse(localStorage.getItem('loggedInUser')).access_token; // Replace with your authorization token
                const studentId = localStorage.getItem('ID'); // Replace with the actual faculty ID
                console.log("access token " + token);
                console.log("User Id", studentId)
                //check here... might get error for response
                const response = await axios.get(`/api/v1/student/${studentId}/bills/pending`, {
                    headers: {
                        "access-control-allow-origin": "*",
                        Authorization: `Bearer ${token}`,
                    },

                });
                console.log("response: " + JSON.stringify(response));


                setBills(response.data);
            } catch (error) {
                // Handle error
                console.error('Error fetching Billing details:', error);
            }
        };

        fetchBillsDetails();
    }, []);


    const handleSinglePayment = async (billId, amount) => {
        const token = JSON.parse(localStorage.getItem('loggedInUser')).access_token;
        const studentId = localStorage.getItem('ID');

        try {
            // console.log(username, password);
            let billids = [billId]


            const response = await axios({

                method: 'post',
                url: `/api/v1/student/pay-bills`,

                data: {
                    "billIds": billids
                },
                headers: {
                    "Content-Type": "application/json",
                    "Access-Control-Allow-Origin": "*",
                    Authorization: `Bearer ${token}`,
                },

            });
            const response1 = await axios.get(`/api/v1/student/${studentId}/bills/pending`, {
                headers: {
                    "access-control-allow-origin": "*",
                    Authorization: `Bearer ${token}`,
                },

            });
            setTotalPayment(totalPayment - amount)
            setBills(response1.data);
            // Redirect or perform actions upon successful login
        } catch (error) {
            console.error('Registration Failed:', error);
        }

    }

    const handlePayBill = (billId, amount) => {
        // Logic to handle payment for a single bill
        // You can implement payment processing here
        console.log(`Paying bill ${billId} amount: ${amount}`);
        handleSinglePayment(billId, amount);
    };

    const handlePayAll = () => {
        // Logic to handle paying all bills
        // Iterate through bills and perform payment
        bills.forEach((bill) => {
            // Perform payment for each bill
            handlePayBill(bill.id, bill.amount);
        });


    };

    useEffect(() => {
        // Calculate total payment whenever bills change
        // const total = bills.reduce((acc, bill) => acc + bill.amount, 0);
        let total = 0
        bills.forEach((bill) => {
            // Perform payment for each bill
            total += bill.amount
        });

        setTotalPayment(total);
    }, [bills]);


    return (
        <div className='payment-page'>
            <div className='payment-container'>

                <div className="payment-header">
                    <div className="payment-heading">
                        <h2>Payment Details</h2>
                        <h6>{user.first_name} {user.last_name}</h6>
                    </div>
                </div>

                <div className='bills-container'>
                    <h3>Bills Details</h3>
                    <ul>
                        {bills.map((bill, index) => (
                            <li className='bill-list' key={index}>
                                <span>Bill ID : {bill.id}</span>
                                <span>Amount : ₹{bill.amount}</span>
                                <span>Deadline : {new Date(bill.deadline).toLocaleDateString()}</span>
                                <span>
                                    <button onClick={(e) => (handlePayBill(bill.id, bill.amount))}>Pay</button>
                                </span>
                            </li>
                        ))}
                    </ul>
                </div>

                <div className='total-pay'>
                    <span><h3>Total Payment: {totalPayment.toFixed(2)} <button onClick={(e) => (handlePayAll())}>Pay All</button></h3></span>
                </div>

            </div>
        </div>
    );
};

export default Payment;
