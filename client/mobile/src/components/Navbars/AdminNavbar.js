import React, { useEffect } from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// import classes from './black-dashboard-react.css';
// import Sidebar from "components/Sidebar/Sidebar.js";
// import routes from "routes.js";
// import logo from "assets/img/react-logo.png";
import { PropTypes } from "prop-types";
import { useMediaQuery } from "react-responsive";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHeadset,
  faQrcode,
  faSearch,
  faComments,
  faBullhorn,
  faKey,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import axios from "axios";
// reactstrap components
import {
  // Button,
  Collapse,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  UncontrolledDropdown,
  // Input,
  // InputGroup,
  // NavbarBrand,
  Navbar,
  NavLink,
  Nav,
  Container,
  Modal,
  NavbarToggler,
  ModalHeader,
  ModalBody
} from "reactstrap";

function AdminNavbar(props) {
  const [collapseOpen, setcollapseOpen] = React.useState(false);
  // const [hiddenpic, sethiddenpic] = React.useState(false);
  const isPc = useMediaQuery({
    query: "(max-width:993px)",
  });
  const [isToken, setisToken] = React.useState(true);
  const [modalSearch, setmodalSearch] = React.useState(false);
  const [color, setcolor] = React.useState("navbar-transparent");
  const [sidebarOpened, setsidebarOpened] = React.useState(
    document.documentElement.className.indexOf("nav-open") !== -1
  );
  const [imageUrl, setImageUrl] = React.useState('') 

  // let imgurl;
  // const toggleSidebar = () => {
  //   document.documentElement.classList.toggle("nav-open");
  //   setsidebarOpened(!sidebarOpened);
  // };
  // React.useEffect(() => {
  //   window.addEventListener("resize", updateColor);
  //   // Specify how to clean up after this effect:
  //   return function cleanup() {
  //     window.removeEventListener("resize", updateColor);
  //   };
  // });
  // function that adds color white/transparent to the navbar on resize (this is for the collapse)

  //네브바 반응형 색깔 변경!! //collapse는 화면 줄인다음 오른쪽 ... 클릭하면 나오는거
  // const updateColor = () => {
  //   if (window.innerWidth < 993 && collapseOpen) {
  //     setcolor("bg-white");
  //   } else {
  //     setcolor("navbar-transparent");
  //   }
  // };
  // const hiddenPicture = () => {
  //   if (window.innerWidth < 993) {
  //     sethiddenpic(false);
  //   }
  // };

  //화면 크기 테스트

  // const ResizedComponent = () => {
  //   const [windowSize, setWindowSize] = useState({
  //     width: window.innerWidth,
  //     height: window.innerHeight,
  //   });
  //   const handleResize = () => {
  //     console.log(
  //       `브라우저 화면 사이즈 x: ${window.innerWidth}, y: ${window.innerHeight}`
  //     );
  //     setWindowSize({
  //       width: window.innerWidth,
  //       height: window.innerHeight,
  //     });
  //   };
  //   React.useEffect(() => {
  //     window.addEventListener("resize", handleResize);
  //     return () => {
  //       window.removeEventListener("resize", handleResize);
  //     };
  //   }, []);
  //   return (
  //     <div>
  //       브라우저 화면 사이즈 x:{window.innerWidth}, y:{window.innerHeight}
  //     </div>
  //   );
  // };

  // const verify2 = () => {
  //   if (window.innerWidth < 993) {
  //     console.log(window.innerWidth);
  //   }
  // };
  //네브바 로고 설정
  const { logo } = props;

  let logoImg = (
    <Link
      to={logo.innerLink}
      className="simple-text logo-mini"
      onClick={props.toggleSidebar}
    >
      <div className="logo-img">
        <img src={logo.imgSrc} alt="react-logo" />
      </div>
    </Link>
  );
  let logoText = (
    <Link
      to={logo.innerLink}
      className="simple-text logo-normal"
      onClick={props.toggleSidebar}
    >
      {logo.text}
    </Link>
  );

  // 모바일에서 화면 상단 오른쪽 바
  const toggleCollapse = () => {
    if (collapseOpen) {
      setcolor("navbar-transparent");
    } else {
      setcolor("bg-collapse");
    }
    setcollapseOpen(!collapseOpen);
  };

  const verify = () => {
    if (collapseOpen) {
      setcollapseOpen(!collapseOpen);
    }
  };
  // console.log(localStorage.getItem("TOKEN"));
  useEffect(() => {
    if (localStorage.getItem("TOKEN") === null) {
      setisToken(!isToken);
    }
  }, []);
  // console.log(isToken);
  // const sethiddenpic = () => {
  //   if (window.innerWidth < 993) {
  //     return true;
  //   } else {
  //     return false;
  //   }
  // };
  // QR창 열고닫기
  const toggleModalSearch = () => {
    setmodalSearch(!modalSearch);
  };

  const logout = () => {
    let token = localStorage.getItem("TOKEN");

    localStorage.clear();
    window.location.replace("/map");
  };
  let test;
  function getQr(params) {
    axios
      .get(`server/myPage/qr/${localStorage.getItem("accountEmail")}`, {
        responseType: "arraybuffer",
      })
      // .get("https:///townbook/myPage/receive/${receiverNo}")
      .then((res) => {
        // console.log(res);
        // const notices = [];
        // console.log(response)
        // for (const key in response.data) {
        //   const notice = {
        //   id: key,
        //   ...response.data[key]
        // };
        //   notices.push(notice);
        // };
        // if(response=="true"){
        // alert("회원가입에 성공하였습니다.");
        // history.replace("/");
        // }
        // else{

        //   alert("회원가입에 실패하였습니다.");
        // }
        // setIsLoading(false);
        // setLoadedMeetups(notices);
        // console.log(notices)
        // console.log(res.data);
        // const base64ImageString = Buffer.from(res.data, 'binary').toString('base64')
        const base64 = btoa(
          new Uint8Array(res.data).reduce(
            (data, byte) => data + String.fromCharCode(byte),
            ""
          )
        );
        setImageUrl(`data:${res.headers["content-type"]};base64,${base64}`)
        // console.log(`data:${res.headers["content-type"]};base64,${base64}`);
        // return `data:${res.headers["content-type"]};base64,${base64}`;
        // var imgurl = `data:${res.headers["content-type"]};base64,${base64}`;
        // test = `data:${res.headers["content-type"]};base64,${base64}`;
        // console.log(test)
        // console.log(base64ImageString)
        // let srcValue = "data:image/png;base64,"+base64ImageString
      })
      .catch((error) => {
        alert("qr로딩에 실패하였습니다.");
      });
  }
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
              {/* 화면 왼쪽위 토글 */}
              <NavbarToggler
                // onClick={props.toggleSidebar}
                style={{ padding: 0 }}
              >
                {/* 각각 짝대기 */}
                <div className="logo">{logoImg}</div>
              </NavbarToggler>
            </div>
          </div>

          <div className="d-lg-none">
            <FontAwesomeIcon
              icon={faQrcode}
              size="xl"
              color="white"
              onClick={toggleModalSearch}
            />
          </div>
          {/* 화면 줄였을때 오른쪽  ... 바 */}
          <NavbarToggler onClick={toggleCollapse}>
            <span className="navbar-toggler-bar navbar-kebab" />
            <span className="navbar-toggler-bar navbar-kebab" />
            <span className="navbar-toggler-bar navbar-kebab" />
          </NavbarToggler>

          {/* 이 Collapse 안에 있으면 모바일 시 상단 오른쪽으로 감 */}
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

              {/* <Sidebar
                  routes={routes}
                  logo={{
                    // outterLink: "https://www.creative-tim.com/",
                    innerLink: "/admin/dashboard",

                    text: "동네북",
                    imgSrc: logo,
                  }}
                  toggleSidebar={toggleSidebar}
                /> */}
              {/* <Link to="/admin/dashboard" className="{classes.}">
                <div className="photo">
                    <img alt="..." src={require("assets/img/anime3.png")} />
                  </div>
                </Link> */}
              <div className="logo" hidden={collapseOpen}>
                {logoImg}
              </div>

              <Link
                to="/map"
                onClick={verify}
                style={{ paddingTop: 10, paddingInline: 20 }}
              >
                {/* 로고 scss 분석하기 */}
                <div className="logo">
                  <FontAwesomeIcon icon={faSearch} size="xl" color="white" />
                  <p className="d-lg-none">도서검색</p>
                </div>
              </Link>

              {/* </InputGroup> */}

              {/* <Link
                to="/icons"
                onClick={verify}
                style={{ paddingTop: 10, paddingInline: 20 }}
              >
                <div className="logo">
                  <FontAwesomeIcon icon={faComments} size="xl" color="white" />
                  <p className="d-lg-none">커뮤니티</p>
                </div>
              </Link> */}

              <Link
                to="/notice"
                onClick={verify}
                style={{ paddingTop: 10, paddingInline: 20 }}
              >
                <div className="logo">
                  <FontAwesomeIcon icon={faBullhorn} size="xl" color="white" />
                  <p className="d-lg-none">공지사항</p>
                </div>
              </Link>
            </Nav>
            <Nav className="ml-auto" navbar>
              {/* QR 아이콘*/}

              {/* 시간되면 버튼형식으로 바꾸기! */}

              {/* <InputGroup className="search-bar"> */}
              {/* <Button onClick={toggleModalSearch} className={'btn-link'}>
                  <FontAwesomeIcon icon={faQrcode} size="xl"/>
                  <span className="d-lg-none d-md-block">QRcode</span>
                </Button> */}

              {/* </InputGroup> */}

              {/* 고객센터 */}
              {/* <DropdownToggle
                  caret
                  color="default"
                  data-toggle="dropdown"
                  nav
                > */}

              {/* <div className="notification d-none d-lg-block d-xl-block" /> */}
              {/* <i className="tim-icons icon-spaceship" /> */}
              {/* <FontAwesomeIcon icon={faSearch}/> */}

              <div
                hidden={collapseOpen}
                style={{ paddingTop: 10, paddingInline: 20 }}
              >
                <FontAwesomeIcon
                  icon={faQrcode}
                  size="xl"
                  color="white"
                  onClick={()=> {toggleModalSearch();getQr(); }}
                />
              </div>

              <Link
                to="/tables"
                onClick={verify}
                style={{ paddingTop: 10, paddingInline: 20 }}
              >
                <div className="logo">
                  <FontAwesomeIcon icon={faHeadset} size="xl" color="white" />
                  <p className="d-lg-none">고객센터</p>
                </div>
              </Link>

              {/* </DropdownToggle> */}

              {/* <DropdownMenu className="dropdown-navbar" right tag="ul"> */}

              {/* <NavLink tag="li">
                    <DropdownItem className="nav-item">고객센터</DropdownItem>
                  </NavLink> */}

              {/* <NavLink tag="li">
                    <DropdownItem className="nav-item">
                      You have 5 more tasks
                    </DropdownItem>
                  </NavLink>

                  
                {/* </DropdownMenu> */}

              {/* 로그인 안돼있을때만 보이는 */}
              {/* {!localStorage.getItem('token') && (
        <> */}
              <Link
                to="/login"
                onClick={verify}
                style={{ paddingTop: 10, paddingInline: 20 }}
              >
                <div className="logo" hidden={isToken}>
                  <FontAwesomeIcon icon={faKey} size="xl" color="white" />
                  <p className="d-lg-none">로그인</p>
                </div>
              </Link>
              {/* <ResizedComponent /> */}
              <UncontrolledDropdown nav hidden={!isToken}>
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
                  <Link to="/myPage">
                    <DropdownItem className="nav-item">마이페이지</DropdownItem>
                  </Link>
                  {/* <NavLink tag="li">
                    <DropdownItem className="nav-item">Settings</DropdownItem>
                  </NavLink> */}

                  <DropdownItem divider tag="li" />

                  <NavLink tag="li">
                    <DropdownItem className="nav-item" onClick={logout}>
                      로그아웃
                    </DropdownItem>
                  </NavLink>
                </DropdownMenu>
              </UncontrolledDropdown>
              <li className="separator d-lg-none" />
            </Nav>
          </Collapse>
        </Container>
      </Navbar>
      <Navbar
        className={"navbar-abs"}
        expand="lg"
        style={{ padding: 0 }}
        hidden={isPc}
      >
        <img
          alt="..."
          // className="avatar"
          src={require("assets/img/backimg6.png")}
          className={"image"}
        />
        <Navbar className="navbar-abs2">
          <div className="navbar-abs2">{props.brandText}</div>
        </Navbar>
      </Navbar>

      {/* QR 모달! */}
      <Modal
        modalClassName="modal-search"
        isOpen={modalSearch}
        toggle={toggleModalSearch}
      >
        <ModalBody>
          {/* <Input placeholder="QR이미지" type="text" /> */}
          <img alt="..." className="avatar" src={imageUrl} />
               
          <button
            aria-label="Close"
            className="close"
            onClick={toggleModalSearch}
          >
            <i className="tim-icons icon-simple-remove" />
          </button>
        </ModalBody>
      </Modal>
    </>
  );
}

AdminNavbar.propTypes = {
  // if true, then instead of the routes[i].name, routes[i].rtlName will be rendered
  // insde the links of this component
  logo: PropTypes.shape({
    // innerLink is for links that will direct the user within the app
    // it will be rendered as <Link to="...">...</Link> tag
    innerLink: PropTypes.string,
    // outterLink is for links that will direct the user outside the app
    // it will be rendered as simple <a href="...">...</a> tag
    outterLink: PropTypes.string,
    // the text of the logo
    text: PropTypes.node,
    // the image src of the logo
    imgSrc: PropTypes.string,
  }),
};

export default AdminNavbar;
