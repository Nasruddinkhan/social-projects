import { createSlice } from "@reduxjs/toolkit";

const popoverSlice = createSlice({
  name: "popover",
  initialState: {
    anchorEl: null,
    content: "",
  },
  reducers: {
    setAnchorEl: (state, action) => {
      state.anchorEl = action.payload;
    },
    setContent: (state, action) => {
      state.content = action.payload;
    },
  },
  serialize: {
    // Define a custom serializer for `anchorEl`
    anchorEl: (value) => {
      return value ? { id: value.id } : null;
    },
  },
  deserialize: {
    anchorEl: (value) => {
      return value ? document.getElementById(value.id) : null;
    },
  },
});
export const { setAnchorEl, setContent } = popoverSlice.actions;

// Define selectors
export const selectAnchorEl = (state) => state.popover.anchorEl;
export const selectContent = (state) => state.popover.content;

export default popoverSlice.reducer;
