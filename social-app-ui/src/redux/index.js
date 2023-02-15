import { configureStore } from "@reduxjs/toolkit";

import counterReducer from "./counter";
import popoverReducer from "./popover"

const store = configureStore({
  reducer: { counter: counterReducer, popover: popoverReducer },
});

export default store;
