import { Table } from "antd";
import React, { useState, useEffect } from "react";
import { fetchCreditCards } from "../services/creditCardService";

const ListCreditCard = ({ newCard }) => {
  const [data, setData] = useState([]);
  useEffect(() => {
    fetchCreditCards().then((data) => setData(data));
  }, []);

  useEffect(() => {
    if (newCard) {
      setData([...data, newCard]);
    }
  }, [newCard]);

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      render: (text) => <div>{text}</div>,
    },
    {
      title: "Card Number",
      dataIndex: "cardNumber",
      key: "cardNumber",
    },
    {
      title: "Balance",
      dataIndex: "balance",
      key: "balance",
      render: (value) => <div>£{value}</div>,
    },
    {
      title: "Limit",
      key: "limit",
      dataIndex: "limit",
      render: (value) => <div>£{value}</div>,
    },
  ];

  return (
    <div>
      <div style={{ fontSize: 20, marginBottom: 15 }}>Existing Cards</div>
      <Table columns={columns} dataSource={data}></Table>
    </div>
  );
};

export default ListCreditCard;
