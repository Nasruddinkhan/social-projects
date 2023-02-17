import { createSlice } from "@reduxjs/toolkit";

const popoverSlice = createSlice({
  name: "popover",
  initialState: {
    anchorEl: null,
    content: null,
  },
  reducers: {
    setAnchorEl: (state, action) => {
      state.anchorEl = action.payload;
    },
    setContent: (state, action) => {
      state.content = action.payload;
    },
  }

});
export const popoverActions = popoverSlice.actions;
export default popoverSlice.reducer;
