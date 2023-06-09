import { createContext } from "react";

export const backgroundColors = {
  primary: "primary",
  blue: "blue",
  green: "green",
  back: "pink",
  yellow: "yellow",
};

export const BackgroundColorContext = createContext({
  color: backgroundColors.green,
  changeColor: (color) => {},
});
