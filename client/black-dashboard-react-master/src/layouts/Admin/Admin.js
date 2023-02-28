import React from "react";
import { Route, Switch, Redirect, useLocation } from "react-router-dom";


import routes from "routes.js";

function Admin(props) {

  const getRoutes = (routes) => {
    return routes.map((prop, key) => {
      if (prop.layout === "/admin") {
        return (
          <Route
            path={prop.layout + prop.path}
            component={prop.component}
            key={key}
          />
        );
      } else {
        return null;
      }
    });
  };

  return (
        <React.Fragment>
            
              <Switch>
                {getRoutes(routes)}
                <Redirect from="*" to="/admin/dashboard" />
              </Switch>
              
        </React.Fragment>
      )}

export default Admin;
