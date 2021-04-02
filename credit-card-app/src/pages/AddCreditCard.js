import React, { useState } from "react";
import { Input, Button, message } from "antd";
import { createUseStyles } from "react-jss";
import { addCreditCard } from "../services/creditCardService";

const useStyles = createUseStyles({
  header: {
    fontSize: 25,
    marginBottom: 10,
  },
  input: {
    marginBottom: 10,
  },
  button: {
    marginTop: 5,
    marginBottom: 30,
  },
});

const AddCreditCard = (props) => {
  const classes = useStyles();
  const [name, setName] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [limit, setLimit] = useState(0);
  const reset = () => {
    setName("");
    setCardNumber("");
    setLimit(0);
  };
  const addCard = (name, cardNumber, limit) => {
    const balance = (
      (Math.random() < 0.5 ? -1 : 1) *
      Math.random() *
      10000
    ).toFixed(2);

    const data = {
      name,
      cardNumber,
      balance,
      limit,
    };
    addCreditCard(data)
      .then((data) => {
        props.addCardSuccessCB(data);
        message.success("card successfully created");
        reset();
      })
      .catch((e) => {
        if (e.response && e.response.status === 400) {
          message.error(e.response.data);
        } else {
          message.error("card creation failed");
        }
      });
  };
  return (
    <div>
      <div className={classes.header}>Credit Card System</div>
      <div>
        Name <span style={{ color: "red" }}>*</span>
      </div>
      <div className={classes.input}>
        <Input
          style={{ width: 300 }}
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>
      <div>
        Card number <span style={{ color: "red" }}>*</span>
      </div>
      <div className={classes.input}>
        <Input
          style={{ width: 300 }}
          value={cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
        />
      </div>
      <div>Limit</div>
      <div className={classes.input}>
        <Input
          type="number"
          style={{ width: 300 }}
          prefix="£"
          value={limit}
          min={0}
          onChange={(e) => setLimit(e.target.value)}
        />
      </div>
      <Button
        className={classes.button}
        type="primary"
        disabled={!name || !cardNumber}
        onClick={() => addCard(name, cardNumber, limit)}
      >
        Add
      </Button>
    </div>
  );
};

export default AddCreditCard;
