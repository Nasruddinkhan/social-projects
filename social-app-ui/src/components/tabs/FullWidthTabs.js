import * as React from 'react';
import PropTypes from 'prop-types';
import SwipeableViews from 'react-swipeable-views';
import { useTheme } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import StoriesCard from '../stories/cards/StoriesCard';
import "./FullWidthTabs.scss";
import ReelCard from '../reals/ReelCard';
import { StoriesIcon } from "./../../assets/svg-icon/stories";
import { ReelSVGIcon } from "./../../assets/svg-icon/reels";
import { RoomSvgIcon } from "./../../assets/svg-icon/rooms";

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`full-width-tabpanel-${index}`}
      aria-labelledby={`full-width-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `full-width-tab-${index}`,
    'aria-controls': `full-width-tabpanel-${index}`,
  };
}

export default function FullWidthTabs() {
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleChangeIndex = (index) => {
    setValue(index);
  };

  return (
    <Box sx={{ bgcolor: "background.paper", width: "100%" }}>
      <AppBar position="static">
        <Tabs
          value={value}
          onChange={handleChange}
          indicatorColor="secondary"
          textColor="inherit"
          variant="fullWidth"
          aria-label="full width tabs example"
          style={{ backgroundColor: "#fff", color: "black" }}
        >
          <Tab
            icon={<StoriesIcon className="storiesIcon" />}
            iconPosition="start"
            label="Stories"
            className="tabClass"
            {...a11yProps(0)}
          />
          <Tab
            icon={<ReelSVGIcon className="storiesIcon" />}
            iconPosition="start"
            label="Reels"
            {...a11yProps(1)}
            className="tabClass"
          />
          <Tab
            icon={<RoomSvgIcon className="storiesIcon" />}
            iconPosition="start"
            label="Rooms"
            className="tabClass"
            {...a11yProps(2)}
          />
        </Tabs>
      </AppBar>
      <SwipeableViews
        axis={theme.direction === "rtl" ? "x-reverse" : "x"}
        index={value}
        onChangeIndex={handleChangeIndex}
      >
        <TabPanel value={value} index={0} dir={theme.direction}>
          <StoriesCard />
        </TabPanel>
        <TabPanel value={value} index={1} dir={theme.direction}>
          <ReelCard />
        </TabPanel>
        <TabPanel value={value} index={2} dir={theme.direction}>
          Room chat desing later
        </TabPanel>
      </SwipeableViews>
    </Box>
  );
}