import React from "react";

import { useDispatch, useSelector } from "react-redux";
import { Popover, Typography } from "@mui/material";
import { popoverActions } from "../redux/popover";

const CustomPopover = () => {
  const dispatch = useDispatch();

  // Define selectors
  //export const selectAnchorEl = (state) => state.popover.anchorEl;
  //export const selectContent = (state) => state.popover.content;
  const anchorEl = useSelector((state) => state.popover.anchorEl);
  const content = useSelector((state) => state.popover.content);

  const handleClose = () => {
    dispatch(popoverActions.setAnchorEl(null));
  };

  return (
    <Popover
      open={Boolean(anchorEl)}
      anchorEl={anchorEl}
      onClose={handleClose}
      anchorOrigin={{
        vertical: "bottom",
        horizontal: "center",
      }}
      transformOrigin={{
        vertical: "top",
        horizontal: "center",
      }}
    >
      <Typography sx={{ p: 2 }}>{content}</Typography>
    </Popover>
  );
};
export default CustomPopover;