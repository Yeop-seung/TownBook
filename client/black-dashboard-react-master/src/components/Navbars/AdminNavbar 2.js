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
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { faHeadset } from "@fortawesome/free-solid-svg-icons";

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
  ModalHeader
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
  //Search창 열ㄷ고닥기
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
                toggled: props.sidebarOpened
              })}
            >
              <NavbarToggler onClick={props.toggleSidebar}>
                <span className="navbar-toggler-bar bar1" />
                <span className="navbar-toggler-bar bar2" />
                <span className="navbar-toggler-bar bar3" />
              </NavbarToggler>
            </div>
            <NavbarBrand href="#pablo" onClick={(e) => e.preventDefault()}>
              {props.brandText}
            </NavbarBrand>
          </div>
          <NavbarToggler onClick={toggleCollapse}>
            <span className="navbar-toggler-bar navbar-kebab" />
            <span className="navbar-toggler-bar navbar-kebab" />
            <span className="navbar-toggler-bar navbar-kebab" />
          </NavbarToggler>
          <Collapse navbar isOpen={collapseOpen}>
            <Nav className="ml-auto" navbar>

              {/* 서치바 아이콘*/}
              <InputGroup className="search-bar">
                <Button color="link" onClick={toggleModalSearch}>
                <FontAwesomeIcon icon={faSearch}/>
                  <span className="d-lg-none d-md-block">Search</span>
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
                  <FontAwesomeIcon icon={faHeadset} size="lg" />
                  <p className="d-lg-none">Notifications</p>
                </DropdownToggle>

                <DropdownMenu className="dropdown-navbar" right tag="ul">
                  <NavLink tag="li">
                    <DropdownItem className="nav-item">
                      고객센터
                    </DropdownItem>
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
                  <p className="d-lg-none">Log out</p>
                </DropdownToggle>

                  {/* 프로필이미지 누르면 나오는 드랍다운 */}
                <DropdownMenu className="dropdown-navbar" right tag="ul">
                  <NavLink tag="li">
                    <DropdownItem className="nav-item">마이페이지</DropdownItem>
                  </NavLink>
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



{/* 서치 모달! */}
      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch}
        toggle={toggleModalSearch}
      >
        <ModalHeader>
          <Input placeholder="검색" type="text" />
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
