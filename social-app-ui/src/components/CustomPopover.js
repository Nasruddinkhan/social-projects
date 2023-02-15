import React from "react";

import { useDispatch, useSelector } from "react-redux";
import { Popover, Typography } from "@mui/material";
import { selectAnchorEl, selectContent, setAnchorEl } from "../redux/popover";

const CustomPopover = () => {
  const dispatch = useDispatch();
  const anchorEl = useSelector(selectAnchorEl);
  const content = useSelector(selectContent);

  const handleClose = () => {
    dispatch(setAnchorEl(null));
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