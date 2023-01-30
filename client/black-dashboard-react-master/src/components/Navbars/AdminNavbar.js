/*!

=========================================================
* Black Dashboard React v1.2.1
=========================================================

* Product Page: https://www.creative-tim.com/product/black-dashboard-react
* Copyright 2022 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/black-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// import classes from './black-dashboard-react.css';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHeadset,
  faQrcode,
  faSearch,
  faComments,
  faBullhorn,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
// reactstrap components
import {
  Button,
  Collapse,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  UncontrolledDropdown,
  Input,
  InputGroup,
  NavbarBrand,
  Navbar,
  NavLink,
  Nav,
  Container,
  Modal,
  NavbarToggler,
  ModalHeader,
} from "reactstrap";

function AdminNavbar(props) {
  const [collapseOpen, setcollapseOpen] = React.useState(false);
  const [modalSearch, setmodalSearch] = React.useState(false);
  const [color, setcolor] = React.useState("navbar-transparent");
  React.useEffect(() => {
    window.addEventListener("resize", updateColor);
    // Specify how to clean up after this effect:
    return function cleanup() {
      window.removeEventListener("resize", updateColor);
    };
  });
  // function that adds color white/transparent to the navbar on resize (this is for the collapse)

  //네브바 반응형 색깔 변경!! //collapse는 화면 줄인다음 오른쪽 ... 클릭하면 나오는거
  const updateColor = () => {
    if (window.innerWidth < 993 && collapseOpen) {
      setcolor("bg-white");
    } else {
      setcolor("navbar-transparent");
    }
  };

  // this function opens and closes the collapse on small devices
  const toggleCollapse = () => {
    if (collapseOpen) {
      setcolor("navbar-transparent");
    } else {
      setcolor("bg-white");
    }
    setcollapseOpen(!collapseOpen);
  };
  // this function is to open the Search modal

  // QR창 열고닫기
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };
  return (
    <>
      <Navbar className={classNames("navbar-absolute", color)} expand="lg">
        <Container fluid>
          <div className="navbar-wrapper">
            <div
              className={classNames("navbar-toggle d-inline", {
                toggled: props.sidebarOpened,
              })}
            >
              <NavbarToggler onClick={props.toggleSidebar}>
                {/* 각각 짝대기 */}
                <span className="navbar-toggler-bar bar1" />
                <span className="navbar-toggler-bar bar2" />
                <span className="navbar-toggler-bar bar3" />
              </NavbarToggler>
            </div>

            {/* 왼쪽 상단 이름 */}
          </div>

          {/* 화면 줄였을때 오른쪽  ... 바 */}
          <NavbarToggler onClick={toggleCollapse}>
            <span className="navbar-toggler-bar navbar-kebab" />
            <span className="navbar-toggler-bar navbar-kebab" />
            <span className="navbar-toggler-bar navbar-kebab" />
          </NavbarToggler>

          <Collapse navbar isOpen={collapseOpen}>
            <Nav className="mr-auto" navbar>
              {/* 로고자리 */}
              {/* <li>
              <NavbarBrand href="#pablo" onClick={(e) => e.preventDefault()}>
                {props.brandText}
              </NavbarBrand>
              </li> */}
              
              {/* 인풋그룹 왜하는지? */}
              {/* 도서검색 */}
              {/* <InputGroup className="search-bar">  */}
              <li>
                <Link to="/admin/map" className="{classes.}">
                  <FontAwesomeIcon icon={faSearch} size="xl" color="white" />
                </Link>
              </li>

              {/* </InputGroup> */}
              <li>
                <Link to="/admin/icons">
                  <FontAwesomeIcon icon={faComments} size="xl" color="white" />
                </Link>
              </li>

              <li>
                <Link to="/admin/notifications">
                  <FontAwesomeIcon icon={faBullhorn} size="xl" color="white" />
                </Link>
              </li>
            </Nav>
            <Nav className="ml-auto" navbar>
              {/* QR 아이콘*/}
              <InputGroup className="search-bar">
                <Button onClick={toggleModalSearch} color="link">
                  <FontAwesomeIcon icon={faQrcode} size="xl"/>
                  <span className="d-lg-none d-md-block">QRcode</span>
                </Button>
              </InputGroup>

              <UncontrolledDropdown nav>
                {/* 고객센터 */}
                <DropdownToggle
                  caret
                  color="default"
                  data-toggle="dropdown"
                  nav
                >
                  <div className="notification d-none d-lg-block d-xl-block" />
                  {/* <i className="tim-icons icon-spaceship" /> */}
                  {/* <FontAwesomeIcon icon={faSearch}/> */}
                  <FontAwesomeIcon icon={faHeadset} size="xl" />
                  <p className="d-lg-none">고객센터</p>
                </DropdownToggle>

                <DropdownMenu className="dropdown-navbar" right tag="ul">
                  <NavLink tag="li">
                    <DropdownItem className="nav-item">고객센터</DropdownItem>
                  </NavLink>

                  {/* <NavLink tag="li">
                    <DropdownItem className="nav-item">
                      You have 5 more tasks
                    </DropdownItem>
                  </NavLink>

                  <NavLink tag="li">
                    <DropdownItem className="nav-item">
                      Your friend Michael is in town
                    </DropdownItem>
                  </NavLink>

                  <NavLink tag="li">
                    <DropdownItem className="nav-item">
                      Another notification
                    </DropdownItem>
                  </NavLink>

                  <NavLink tag="li">
                    <DropdownItem className="nav-item">
                      Another one
                    </DropdownItem>
                  </NavLink> */}
                </DropdownMenu>
              </UncontrolledDropdown>
              <UncontrolledDropdown nav>
                {/* 프로필이미지 칸 */}
                <DropdownToggle
                  caret
                  color="default"
                  nav
                  onClick={(e) => e.preventDefault()}
                >
                  <div className="photo">
                    <img alt="..." src={require("assets/img/anime3.png")} />
                  </div>
                  <b className="caret d-none d-lg-block d-xl-block" />
                  <p className="d-lg-none">Profile</p>
                </DropdownToggle>

                {/* 프로필이미지 누르면 나오는 드랍다운 */}
                <DropdownMenu className="dropdown-navbar" right tag="ul">
                  <Link to="/admin/user-profile">
                    <DropdownItem className="nav-item">마이페이지</DropdownItem>
                  </Link>
                  {/* <NavLink tag="li">
                    <DropdownItem className="nav-item">Settings</DropdownItem>
                  </NavLink> */}

                  <DropdownItem divider tag="li" />

                  <NavLink tag="li">
                    <DropdownItem className="nav-item">로그아웃</DropdownItem>
                  </NavLink>
                </DropdownMenu>
              </UncontrolledDropdown>
              <li className="separator d-lg-none" />
            </Nav>
          </Collapse>
        </Container>
      </Navbar>

      {/* QR 모달! */}
      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch}
        toggle={toggleModalSearch}
      >
        <ModalHeader>
          {/* <Input placeholder="QR이미지" type="text" /> */}
          <img
            alt="..."
            className="avatar"
            src={require("assets/img/qrcode.png")}
          />

          <button
            aria-label="Close"
            className="close"
            onClick={toggleModalSearch}
          >
            <i className="tim-icons icon-simple-remove" />
          </button>
        </ModalHeader>
      </Modal>
    </>
  );
}

export default AdminNavbar;
