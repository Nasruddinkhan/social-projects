import React from "react";
import HomeRoundedIcon from "@mui/icons-material/HomeRounded";
import { makeStyles } from "@mui/styles";

import { Drawer, List, ListItem, ListItemIcon, ListItemText } from "@mui/material";
import EmailRoundedIcon from "@mui/icons-material/EmailRounded";
const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: "flex",
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
  },
  // Other styles here
}));

const  SideMenu =()=> {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Drawer
        className={classes.drawer}
        variant="permanent"
        classes={{
          paper: classes.drawerPaper,
        }}
        anchor="left"
      >
        <List>
          <ListItem button>
            <ListItemIcon>
              <HomeRoundedIcon />
            </ListItemIcon>
            <ListItemText primary="Inbox" />
          </ListItem>
          <ListItem button>
            <ListItemIcon>
              <EmailRoundedIcon />
            </ListItemIcon>
            <ListItemText primary="Mail" />
          </ListItem>
        </List>
      </Drawer>
    </div>
  );
}
export default SideMenu;