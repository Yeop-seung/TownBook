import React from "react";
<<<<<<< HEAD:client/mobile/src/layouts/Admin/Admin.js
import { Route, Switch, useLocation, Redirect } from "react-router-dom";
// javascript plugin used to create scrollbars on windows
import PerfectScrollbar from "perfect-scrollbar";

// core components
import AdminNavbar from "components/Navbars/AdminNavbar.js";
import Footer from "components/Footer/Footer.js";
// import Sidebar from "components/Sidebar/Sidebar.js";
import FixedPlugin from "components/FixedPlugin/FixedPlugin.js";
import NoticeWrite from "views/notice/NoticeWrite";
import routes from "routes.js";
import Notice from "views/notice/Notice";
import logo from "assets/img/동네북.png";
import { BackgroundColorContext } from "contexts/BackgroundColorContext";
import SignUp from "views/account/SignUp";
import IdFind from "views/account/IdFind.js";
import NoticeDetail from "views/notice/NoticeDetail";
import NoticeUpdate from "views/notice/NoticeUpdate";
import BookDetail from "views/map/BookDetail";
import SignUpComplete from "views/account/SignUpComplete";
var ps;

function Admin(props) {
  const location = useLocation();
  const mainPanelRef = React.useRef(null);
  const [sidebarOpened, setsidebarOpened] = React.useState(
    document.documentElement.className.indexOf("nav-open") !== -1
  );
  React.useEffect(() => {
    if (navigator.platform.indexOf("Win") > -1) {
      document.documentElement.className += " perfect-scrollbar-on";
      document.documentElement.classList.remove("perfect-scrollbar-off");
      ps = new PerfectScrollbar(mainPanelRef.current, {
        suppressScrollX: true,
      });
      let tables = document.querySelectorAll(".table-responsive");
      for (let i = 0; i < tables.length; i++) {
        ps = new PerfectScrollbar(tables[i]);
      }
    }
    // Specify how to clean up after this effect:
    return function cleanup() {
      if (navigator.platform.indexOf("Win") > -1) {
        ps.destroy();
        document.documentElement.classList.add("perfect-scrollbar-off");
        document.documentElement.classList.remove("perfect-scrollbar-on");
      }
    };
  });
  React.useEffect(() => {
    if (navigator.platform.indexOf("Win") > -1) {
      let tables = document.querySelectorAll(".table-responsive");
      for (let i = 0; i < tables.length; i++) {
        ps = new PerfectScrollbar(tables[i]);
      }
    }
    document.documentElement.scrollTop = 0;
    document.scrollingElement.scrollTop = 0;
    if (mainPanelRef.current) {
      mainPanelRef.current.scrollTop = 0;
    }
  }, [location]);
  // this function opens and closes the sidebar on small devices
  // const toggleSidebar = () => {
  //   document.documentElement.classList.toggle("nav-open");
  //   setsidebarOpened(!sidebarOpened);
  // };
=======
import { Route, Switch, Redirect, useLocation } from "react-router-dom";


import routes from "routes.js";

function Admin(props) {

>>>>>>> KI:client/black-dashboard-react-master/src/layouts/Admin/Admin.js
  const getRoutes = (routes) => {
    return routes.map((prop, key) => {
      if (prop.layout === "/") {
        return (
          
          <Route
            path={(prop.path)}
            component={prop.component}
            key={key}
          />
          
          
          
        );
      } else {
        return null;
      }
    });
  };
<<<<<<< HEAD:client/mobile/src/layouts/Admin/Admin.js
  const getBrandText = (path) => {
    for (let i = 0; i < routes.length; i++) {
      if (location.pathname.indexOf(routes[i].path) !== -1) {
        return routes[i].name;
      }
    }
    return "Brand";
  };
=======
>>>>>>> KI:client/black-dashboard-react-master/src/layouts/Admin/Admin.js

  return (
        <React.Fragment>
<<<<<<< HEAD:client/mobile/src/layouts/Admin/Admin.js
          <div className="wrapper">
            {/* 사이드바 상단 로고! */}
            {/* <Sidebar
              routes={routes}
              // toggleSidebar={toggleSidebar}
            /> */}

            <div className="main-panel" ref={mainPanelRef} data={color}>
              {/* 네브바! */}
              <AdminNavbar
                logo={{
                  // outterLink: "https://www.creative-tim.com/",
                  innerLink: "/map",

                  text: "동네북",
                  imgSrc: logo,
                }}
                // toggleSidebar={toggleSidebar}
                brandText={getBrandText(location.pathname)}
                // toggleSidebar={toggleSidebar}
                sidebarOpened={sidebarOpened}
              />

=======
            
>>>>>>> KI:client/black-dashboard-react-master/src/layouts/Admin/Admin.js
              <Switch>
                {getRoutes(routes)}
                {<Route path='/notice/write' component={NoticeWrite}/>}
                {<Route exact path='/notice' component={Notice}/>}
                {<Route path='/account/signup' component={SignUp}/>}
                {<Route path='/account/idfind' component={IdFind}/>}
                {<Route exact path='/notice/:id' component={NoticeDetail}/>}
                {<Route path='/notice/modify/:id' component={NoticeUpdate}/>}
                {<Route exact path='/book/:id' component={BookDetail}/>}
                {<Route exact path='/account/signupcomplete' component={SignUpComplete}/>}


                {/* {<Route path='/notice' component={IdFind}/>} */}
                


                <Redirect from="*" to="/map" />
              </Switch>
<<<<<<< HEAD:client/mobile/src/layouts/Admin/Admin.js
              {
                // we don't want the Footer to be rendered on map page
                location.pathname === "/map" ? null : <Footer fluid />
              }
            </div>
          </div>
          {/* <FixedPlugin bgColor={color} handleBgClick={changeColor} /> */}
=======
              
>>>>>>> KI:client/black-dashboard-react-master/src/layouts/Admin/Admin.js
        </React.Fragment>
      )}

export default Admin;
