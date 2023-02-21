import React from "react";
import Stories from "stories-react";
import "stories-react/dist/index.css";
function Copy() {
  return (
    <p>
      Jaguar shark! So tell me - does it really exist? Checkmate... Eventually,
      you do plan to have dinosaurs on your dinosaur tour, right? Yeah, but your
      scientists were so preoccupied with whether or not they could, they didnt
      stop to think if they should.
    </p>
  );
}
function HelpText() {
  return (
    <div
      className="box"
      style={{
        height: "90%",
        padding: "100px 24px",
        background:
          "linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab)",
      }}
    >
      <h4>Hannad Rehman says:</h4>
      <Copy />
    </div>
  );
}
const stories = [
  {
    type: "image",
    url: "https://images.pexels.com/photos/9470805/pexels-photo-9470805.jpeg?w=300",
    duration: 5000,
  },
  {
    type: "image",
    duration: 6000,
    url: "https://images.pexels.com/photos/9733197/pexels-photo-9733197.jpeg?w=300",
  },
  {
    duration: 7000,
    type: "image",
    url: "https://images.pexels.com/photos/9470805/pexels-photo-9470805.jpeg?w=300",
  },
  {
    type: "video",
    url: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
    duration: 28000,
  },
  {
    type: "component",
    duration: 9000,
    component: HelpText,
  },
];
const StoriesUI = () => {
  return <Stories width="100%" height="640px" stories={stories} />;
};

export default StoriesUI;
