import React from "react";
import { Route, Redirect, Routes } from "react-router-dom";
import routes from "kioskroutes.js";


function KioskAdminLayout(props) {
    const getRoutes = (routes) => {
        return routes.map((prop, key) => {
            if (prop.layout === "/KioskAdminLayout") {
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
                <Routes>
                    {getRoutes(routes)}
                    <Redirect from="*" to='/kiosk/main'></Redirect>
                </Routes>
        </React.Fragment>
    )
}

export default KioskAdminLayout