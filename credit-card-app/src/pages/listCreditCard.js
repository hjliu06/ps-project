import { Table } from "antd";
import React, { useState, useEffect } from "react";
import { fetchCreditCards } from "../services/creditCardService";

const ListCreditCard = ({ newCard }) => {
  const [data, setData] = useState([]);

  const formatCardNumber = (cn) => {
    let formattedNumber = "";
    const len = cn.length;
    for (let i = 0; i < len; i++) {
      formattedNumber = formattedNumber + cn.charAt(i);
      if ((i + 1) % 4 === 0 && i != len - 1) {
        formattedNumber = formattedNumber + " ";
      }
    }
    return formattedNumber;
  };

  useEffect(() => {
    fetchCreditCards().then((data) => {
      setData(
        data.map((e) => ({
          ...e,
          cardNumber: formatCardNumber(e.cardNumber),
        }))
      );
    });
  }, []);

  useEffect(() => {
    if (newCard) {
      setData([
        ...data,
        { ...newCard, cardNumber: formatCardNumber(newCard.cardNumber) },
      ]);
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
