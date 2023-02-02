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

// reactstrap components
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  CardText,
  FormGroup,
  Form,
  Input,
  Row,
  Col,
  
  CardTitle,
  Table,
} from "reactstrap";

function UserProfile() {
  return (
    <>
      
        
          <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">내가 쓴 글</CardTitle>
              </CardHeader>
              <CardBody>
                <Table className="tablesorter" responsive>
                  <thead className="text-primary">
                    <tr>
                      <th>번호</th>
                      <th>제목</th>
                      <th>작성일자</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Dakota Rice</td>
                      <td>Niger</td>
                      <td>Oud-Turnhout</td>
                    </tr>
                    <tr>
                      <td>Minerva Hooper</td>
                      <td>Curaçao</td>
                      <td>Sinaai-Waas</td>
                    </tr>
                    <tr>
                      <td>Sage Rodriguez</td>
                      <td>Netherlands</td>
                      <td>Baileux</td>
                    </tr>
                    <tr>
                      <td>Philip Chaney</td>
                      <td>Korea, South</td>
                      <td>Overland Park</td>
                    </tr>
                    <tr>
                      <td>Doris Greene</td>
                      <td>Malawi</td>
                      <td>Feldkirchen in Kärnten</td>
                    </tr>
                    <tr>
                      <td>Mason Porter</td>
                      <td>Chile</td>
                      <td>Gloucester</td>
                    </tr>
                    <tr>
                      <td>Jon Porter</td>
                      <td>Portugal</td>
                      <td>Gloucester</td>
                    </tr>
                  </tbody>
                </Table>
              </CardBody>
            </Card>
          </Col>
          <Col md="12">
          <Card>
              <CardHeader>
                <CardTitle tag="h4">나의 기부/수령 목록</CardTitle>
              </CardHeader>
              <CardBody>
                <Table className="tablesorter" responsive>
                  <thead className="text-primary">
                    <tr>
                      <th>이름</th>
                      <th>시</th>
                      <th>동</th>
                      <th className="text-center">포인트</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>Dakota Rice</td>
                      <td>Niger</td>
                      <td>Oud-Turnhout</td>
                      <td className="text-center">$36,738</td>
                    </tr>
                    <tr>
                      <td>Minerva Hooper</td>
                      <td>Curaçao</td>
                      <td>Sinaai-Waas</td>
                      <td className="text-center">$23,789</td>
                    </tr>
                    <tr>
                      <td>Sage Rodriguez</td>
                      <td>Netherlands</td>
                      <td>Baileux</td>
                      <td className="text-center">$56,142</td>
                    </tr>
                    <tr>
                      <td>Philip Chaney</td>
                      <td>Korea, South</td>
                      <td>Overland Park</td>
                      <td className="text-center">$38,735</td>
                    </tr>
                    <tr>
                      <td>Doris Greene</td>
                      <td>Malawi</td>
                      <td>Feldkirchen in Kärnten</td>
                      <td className="text-center">$63,542</td>
                    </tr>
                    <tr>
                      <td>Mason Porter</td>
                      <td>Chile</td>
                      <td>Gloucester</td>
                      <td className="text-center">$78,615</td>
                    </tr>
                    <tr>
                      <td>Jon Porter</td>
                      <td>Portugal</td>
                      <td>Gloucester</td>
                      <td className="text-center">$98,615</td>
                    </tr>
                  </tbody>
                </Table>
              </CardBody>
            </Card>
            
          </Col>
          <Col md="12">
            <Card>
              <CardHeader>
                <h5 className="title">찜목록</h5>
                {/* <p className="category">
                  Handcrafted by our friends from{" "}
                  <a href="https://nucleoapp.com/?ref=1712">NucleoApp</a>
                </p> */}
              </CardHeader>
              <CardBody className="all-icons">
                <Row>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-alert-circle-exc" />
                      <p>icon-alert-circle-exc</p>
                    </div>
                  </Col>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-align-center" />
                      <p>icon-align-center</p>
                    </div>
                  </Col>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-align-left-2" />
                      <p>icon-align-left-2</p>
                    </div>
                  </Col>
                  <Col
                    className="font-icon-list col-xs-6 col-xs-6"
                    lg="2"
                    md="3"
                    sm="4"
                  >
                    <div className="font-icon-detail">
                      <i className="tim-icons icon-app" />
                      <p>icon-app</p>
                    </div>
                  </Col>
                  </Row>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
          
        
    </>
  );
}

export default UserProfile;
