import { createContext } from "react";

export const themes = {
  dark: "",
  light: "gray",
  red : "red",
};

//ThemeContext는 편의를 위해 props 대신에 사용 : https://ko.reactjs.org/docs/context.html
export const ThemeContext = createContext({
  theme: themes.dark,
  changeTheme: () => {},
});
