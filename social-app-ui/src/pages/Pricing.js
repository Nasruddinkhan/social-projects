import * as React from "react";
import AppBar from "@mui/material/AppBar";


import CssBaseline from "@mui/material/CssBaseline";

import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Link from "@mui/material/Link";
import GlobalStyles from "@mui/material/GlobalStyles";
import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import IconButton from "@mui/material/IconButton";
import PersonIcon from "@mui/icons-material/Person";
import MessageIcon from "@mui/icons-material/Message";
import { Container, Tooltip } from "@mui/material";
import { Button } from "@mui/material";
import CustomPopover from "../components/CustomPopover";
import { useDispatch } from "react-redux";
import { useState } from "react";
import { setAnchorEl, setContent } from "../redux/popover";

const PricingContent = () => {

  const dispatch = useDispatch();
  const [buttonRef, setButtonRef] = useState(null);

  const handleClick = (event) => {
    dispatch(setAnchorEl(buttonRef));
    dispatch(setContent("This is the popover content"));
  };

  return (
    <React.Fragment>
      <GlobalStyles
        styles={{ ul: { margin: 0, padding: 0, listStyle: "none" } }}
      />
      <CssBaseline />
      <AppBar
        position="fixed"
        color="inherit"
        elevation={0}
        sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}
      >
        <Toolbar sx={{ flexWrap: "wrap" }}>
          <Typography variant="h6" color="inherit" noWrap sx={{ flexGrow: 1 }}>
            Company name
          </Typography>
          <nav>
            <Link sx={{ my: 1, mx: 1 }}>
              <Tooltip title="Notification" arrow>
                <IconButton
                  size="large"
                  aria-label="search"
                  style={{ background: "lightgray" }}
                >
                  <MessageIcon />
                </IconButton>
              </Tooltip>
            </Link>
            <Link sx={{ my: 1, mx: 1 }}>
              <Tooltip title="Notification" arrow>
                <IconButton
                  size="large"
                  aria-label="search"
                  style={{ background: "lightgray" }}
                >
                  <NotificationsNoneIcon />
                </IconButton>
              </Tooltip>
            </Link>
            <Link sx={{ my: 1, mx: 1 }}>
              <Tooltip title="Account" arrow>
                <IconButton
                  size="large"
                  aria-label="search"
                  style={{ background: "lightgray" }}
                >
                  <PersonIcon />
                </IconButton>
              </Tooltip>
            </Link>
          </nav>
        </Toolbar>
      </AppBar>
      <Container
        disableGutters
        maxWidth="sm"
        component="main"
        sx={{ pt: 8, pb: 6 }}
      >
        <Button ref={setButtonRef} onClick={handleClick}>
          Open Popover
        </Button>
        <CustomPopover />
      </Container>
    </React.Fragment>
  );
};

export default PricingContent;
