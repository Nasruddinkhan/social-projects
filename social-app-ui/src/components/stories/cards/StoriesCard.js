import React from 'react'

import {
  Card,
  CardContent,
 
} from "@mui/material";

import "./StoriesCard.scss";
import AddCircleTwoToneIcon from "@mui/icons-material/AddCircleTwoTone";

const StoriesCard = () => {
  return (
    <Card sx={{ minWidth: 275 }} className="storyCard" style={{ padding: 0, margin: "10px 10px 0 10px" }}>
      <CardContent>
        <div className="storyContainer">
          <div className="topStoryContainer">
            <img
              className="storyImg"
              alt=""
              src="https://scontent.fruh7-1.fna.fbcdn.net/v/t1.30497-1/143086968_2856368904622192_1959732218791162458_n.png?stp=dst-png_p160x160&_nc_cat=1&ccb=1-7&_nc_sid=7206a8&_nc_ohc=G4ScZzW23xoAX_9X6gZ&_nc_ht=scontent.fruh7-1.fna&oh=00_AfD1mTFPsG9I6NzfkMEVE6Rbqqey4V-LaPouXKdqh_TLDA&oe=6419FDB8"
            />
            <div className="imgDiv">
              <AddCircleTwoToneIcon className="addIcon" />
              <div>Create Story</div>
            </div>
          </div>
          <div className="topStoryContainer">
            <img
              className="storyImg"
              alt=""
              src="https://scontent.fruh7-1.fna.fbcdn.net/v/t1.30497-1/143086968_2856368904622192_1959732218791162458_n.png?stp=dst-png_p160x160&_nc_cat=1&ccb=1-7&_nc_sid=7206a8&_nc_ohc=G4ScZzW23xoAX_9X6gZ&_nc_ht=scontent.fruh7-1.fna&oh=00_AfD1mTFPsG9I6NzfkMEVE6Rbqqey4V-LaPouXKdqh_TLDA&oe=6419FDB8"
            />
            <div className="imgDiv">
              <AddCircleTwoToneIcon className="addIcon" />
              <div>Create Story</div>
            </div>
          </div>
          <div className="topStoryContainer">
            <img
              className="storyImg"
              alt=""
              src="https://scontent.fruh7-1.fna.fbcdn.net/v/t1.30497-1/143086968_2856368904622192_1959732218791162458_n.png?stp=dst-png_p160x160&_nc_cat=1&ccb=1-7&_nc_sid=7206a8&_nc_ohc=G4ScZzW23xoAX_9X6gZ&_nc_ht=scontent.fruh7-1.fna&oh=00_AfD1mTFPsG9I6NzfkMEVE6Rbqqey4V-LaPouXKdqh_TLDA&oe=6419FDB8"
            />
            <div className="imgDiv">
              <AddCircleTwoToneIcon className="addIcon" />
              <div>Create Story</div>
            </div>
          </div>
          <div className="topStoryContainer">
            <img
              className="storyImg"
              alt=""
              src="https://scontent.fruh7-1.fna.fbcdn.net/v/t1.30497-1/143086968_2856368904622192_1959732218791162458_n.png?stp=dst-png_p160x160&_nc_cat=1&ccb=1-7&_nc_sid=7206a8&_nc_ohc=G4ScZzW23xoAX_9X6gZ&_nc_ht=scontent.fruh7-1.fna&oh=00_AfD1mTFPsG9I6NzfkMEVE6Rbqqey4V-LaPouXKdqh_TLDA&oe=6419FDB8"
            />
            <div className="imgDiv">
              <AddCircleTwoToneIcon className="addIcon" />
              <div>Create Story</div>
            </div>
          </div>
        </div>
      </CardContent>
    </Card>
  );
}

export default StoriesCard;