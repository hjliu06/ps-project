import React, { useState } from "react";
import ReactDom from "react-dom";
import AddCreditCard from "./pages/AddCreditCard";
import ListCreditCard from "./pages/listCreditCard";
const App = () => {
  const [newCard, setNewCard] = useState(null);
  const addCardSuccessCB = (card) => {
    setNewCard(card);
  };
  return (
    <div style={{ margin: "20px 50px 50px 50px" }}>
      <AddCreditCard addCardSuccessCB={addCardSuccessCB}></AddCreditCard>
      <ListCreditCard newCard={newCard}></ListCreditCard>
    </div>
  );
};

ReactDom.render(<App />, document.getElementById("root"));
