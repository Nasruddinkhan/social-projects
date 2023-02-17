import { configureStore } from "@reduxjs/toolkit";

import popoverReducer from "./popover"

const store = configureStore({
  reducer: {  popover: popoverReducer },
});

export default store;
