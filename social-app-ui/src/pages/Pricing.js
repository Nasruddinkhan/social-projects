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
import {  Container, List, ListItem, ListItemIcon, ListItemText, Tooltip } from "@mui/material";
import CustomPopover from "../components/CustomPopover";
import { useDispatch } from "react-redux";
import { useState } from "react";
import { popoverActions } from "../redux/popover";
import "./Pricing.scss";
import HomeRoundedIcon from "@mui/icons-material/HomeRounded";




import EmailRoundedIcon from "@mui/icons-material/EmailRounded";
import { FacebookSVGIcon } from "../assets/svg-icon/facebook";
const PricingContent = () => {
  const dispatch = useDispatch();
  const [buttonRef, setButtonRef] = useState(null);

  const handleClick = (event) => {
    dispatch(popoverActions.setAnchorEl(buttonRef));
    dispatch(popoverActions.setContent("This is the popover content"));
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
        className="appheader"
        sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}
      >
        <Toolbar sx={{ flexWrap: "wrap" }}>
          <ListItemIcon noWrap sx={{ flexGrow: 1 }} >
            <FacebookSVGIcon />
          </ListItemIcon>
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
            <Link
              sx={{ my: 1, mx: 1 }}
              ref={setButtonRef}
              onClick={handleClick}
            >
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
      <Container maxWidth="false" sx={{ pt: 8.5 }} className="cssContainer">
        <div className="mainContainer">
          <div className="rowContainer">
            <div className="leftColumn">
              <List>
                <ListItem>
                  <ListItemIcon style={{ color: "#2196f3" }}>
                    <HomeRoundedIcon />
                  </ListItemIcon>
                  <ListItemText primary="Home" />
                </ListItem>
                <ListItem>
                  <ListItemIcon>
                    <EmailRoundedIcon />
                  </ListItemIcon>
                  <ListItemText primary="Mail" />
                </ListItem>
              </List>
            </div>
            <div className="midColumn">
              Chevrolet is one of the most recognizable and popular car brands
              in the world. Founded in 1911 by Louis Chevrolet and William C.
              Durant, the brand has become a symbol of quality and reliability
            </div>
            <div className="rightColumn">
              Chevrolet is one of the most recognizable and popular car brands
              in the world. Founded in 1911 by Louis Chevrolet and William C.
              Durant, the brand has become a symbol of quality and reliability
            </div>
          </div>

          {/* <Card className="">
            <CardContent>
              Chevrolet is one of the most recognizable and popular car brands
              in the world. Founded in 1911 by Louis Chevrolet and William C.
              Durant, the brand has become a symbol of quality and reliability
            </CardContent>
          </Card> */}
        </div>
      </Container>
    </React.Fragment>
  );
};

export default PricingContent;
