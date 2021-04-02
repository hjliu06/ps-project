import axios from "axios";

export const fetchCreditCards = () =>
  axios({
    url: `http://localhost:8080/cards`,
    method: "GET",
  }).then((response) => response.data);

export const addCreditCard = (data) =>
  axios({
    url: `http://localhost:8080/cards`,
    method: "POST",
    data,
  }).then((response) => response.data);
