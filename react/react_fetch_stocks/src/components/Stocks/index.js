import React, { useEffect, useState } from "react";
import stocks from "../../stocks";

function Users() {
  const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  const [month, setMonth] = useState("January");
  const [stockByMonth, setStockByMonth] = useState([...stocks]);
  const [profit, setProfit] = useState(0);
  const [loss, setLoss] = useState(0);

  useEffect(() => {
    const stockList = stocks.filter((stock) => stock.date.includes(month));
    let profit = 0;
    let loss = 0;
    stockList.forEach((sto) => {
      if (sto.open > sto.close) {
        loss += sto.open - sto.close;
      } else {
        profit += sto.close - sto.open;
      }
    });
    setProfit(profit);
    setLoss(loss);
    setStockByMonth(stockList);
  }, [month]);

  const handleSort=()=>{
    const mystockSorted=[...stockByMonth].sort((a,b)=>{
      let un1=a.open-a.close;
      let un2=b.open-b.close;
      return un1-un2;
    })
    setStockByMonth(mystockSorted);
  }
  return (
    <>
      <div className="layout-row justify-content-center">
        <select
          className="mt-13 mr-5"
          data-testid="select-month"
          onChange={(e) => setMonth(e.target.value)}
        >
          {months.map((mon, index) => (
            <option key={index} value={mon}>
              {mon}
            </option>
          ))}
        </select>
        <button data-testid="sort" onClick={()=>handleSort()}>Sort</button>
      </div>
      <div
        className="layout-row wrap w-100 justify-content-center"
        data-testid="stocks"
      >
        {stockByMonth.map((sto, index) => (
          <div className="card layout-column w-20 ma-10 px-8">
            <p className="ma-0">Date: {sto.date}</p>
            <p className="ma-0">High: {sto.high}</p>
            <p className="ma-0">Low: {sto.low}</p>
            {sto.close - sto.open > 0 ? (
              <p className="ma-0">
                Profit: ✅ ({(sto.close - sto.open).toFixed(2)})
              </p>
            ) : (
              <p className="ma-0">
                Profit: ❌ ({(sto.open - sto.close).toFixed(2)})
              </p>
            )}
          </div>
        ))}
      </div>
      <div className="layout-column justify-content-center align-items-center">
        <p className="mb-0 font-weight-bold" data-testid="total-profit">
          Total Profit:{" "}
          <span style={{ color: "#1ba94c" }}>{profit.toFixed(2)}</span>
        </p>
        <p className="font-weight-bold" data-testid="total-loss">
          Total Loss: <span style={{ color: "red" }}>{loss.toFixed(2)}</span>
        </p>
      </div>
    </>
  );
}

export default Users;
